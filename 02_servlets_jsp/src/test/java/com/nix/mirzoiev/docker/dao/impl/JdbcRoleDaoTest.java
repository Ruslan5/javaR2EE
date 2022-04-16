package com.nix.mirzoiev.docker.dao.impl;

import com.mir.servlets.dao.ConfigManager;

import java.sql.Connection;

public class JdbcRoleDaoTest {
    protected final ConfigManager configManager = new ConfigManager("/db.properties");

    protected Connection getConnection() {
        return configManager.getConnection();
    }
}
