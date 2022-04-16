package com.mir.jdbc.dao;

import com.mir.jdbc.dao.impl.JdbcUserDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author R.M.
 * @since 16.04.2022
 */
public abstract class GenericJdbcDao<E> implements Dao<E> {
    private static Logger logger = Logger.getLogger(JdbcUserDao.class);

    private ConfigManager configManager;

    public GenericJdbcDao(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public Connection getConnection() {
        return configManager.getConnection();
    }

    protected void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.trace("start closeResultSet");
                throw new RuntimeException(e);
            }
        }
    }

}
