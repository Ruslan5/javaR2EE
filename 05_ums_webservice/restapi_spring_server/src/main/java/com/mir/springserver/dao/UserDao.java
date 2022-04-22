package com.mir.springserver.dao;

/**
 * interface UserDao.
 *
 * @author R.Mirzoiev
 * @see Dao
 * @see User
 * @since 11.02.2022
 */
public interface UserDao<User> extends Dao<User> {
    User findByLogin(String login);

    User findByEmail(String email);
}
