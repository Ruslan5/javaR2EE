package com.mir.jdbc.dao;

/**
 *
 * @author R.M.
 * @since 16.04.2022
 */
public interface UserDao<User> extends Dao<User> {
    User findByLogin(String login);

    User findByEmail(String email);
}
