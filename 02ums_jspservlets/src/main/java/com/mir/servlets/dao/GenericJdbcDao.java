package com.mir.servlets.dao;

import com.mir.servlets.dao.impl.JdbcUserDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * abstract GenericJdbcDao.
 *
 * @author R.M.
 * @see Dao
 * @since 2022
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
