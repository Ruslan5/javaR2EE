package com.mir.jdbc.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author R.M.
 * @since 16.04.2022
 */
public class ConfigManager {

    private static Logger logger = Logger.getLogger(GenericJdbcDao.class);
    private BasicDataSource basicDataSource;
    String path;

    public ConfigManager(String path) {
        this.path = path;
    }

    private BasicDataSource bsSetUp(){
        BasicDataSource basicDataSource = new BasicDataSource();
        Properties properties = loadResource();
        basicDataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
        basicDataSource.setUrl(properties.getProperty("jdbc.url"));
        basicDataSource.setUsername(properties.getProperty("jdbc.USER"));
        basicDataSource.setPassword(properties.getProperty("jdbc.PASS"));
        return basicDataSource;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = bsSetUp().getConnection();
        } catch (SQLException e) {
            logger.error("error connection: {} ", e);
            throw new RuntimeException(e);
        }
        return connection;
    }

    public Properties loadResource() {
        Properties properties = new Properties();
        try (InputStream inputStream = ConfigManager.class.getResourceAsStream(path)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.debug("IOException : {} ", e);
            throw new RuntimeException(e);
        }
        return properties;
    }
}
