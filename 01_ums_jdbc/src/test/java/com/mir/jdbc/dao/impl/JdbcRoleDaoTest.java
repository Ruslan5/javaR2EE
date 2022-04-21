package com.mir.jdbc.dao.impl;

import com.mir.jdbc.dao.ConfigManager;

import java.sql.Connection;

public class JdbcRoleDaoTest {
    protected final ConfigManager configManager = new ConfigManager("/db.properties");

    protected Connection getConnection() {
        return configManager.getConnection();
    }
}
