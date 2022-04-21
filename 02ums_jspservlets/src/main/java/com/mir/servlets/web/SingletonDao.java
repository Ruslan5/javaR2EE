package com.mir.servlets.web;

import com.mir.servlets.dao.ConfigManager;
import com.mir.servlets.dao.impl.JdbcRoleDao;
import com.mir.servlets.dao.impl.JdbcUserDao;

public class SingletonDao {
    private static JdbcUserDao userInstance;
    private static JdbcRoleDao roleInstance;
    private static ConfigManager configManager = new ConfigManager("/db.properties");

    private SingletonDao() {
    }

    public static synchronized JdbcUserDao getUserInstance(){
        if (userInstance == null) {
            userInstance = new JdbcUserDao(configManager);
        }
        return userInstance;
    }

    public static synchronized JdbcRoleDao getRoleInstance(){
        if (roleInstance == null) {
            roleInstance = new JdbcRoleDao(configManager);
        }
        return roleInstance;
    }

}
