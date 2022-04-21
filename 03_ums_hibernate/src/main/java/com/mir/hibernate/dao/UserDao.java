package com.mir.hibernate.dao;

/**
 * interface UserDao.
 *
 * @author R.M.
 * @see Dao
 * @see User
 * @since 2022
 */
public interface UserDao<User> extends Dao<User> {
    User findByLogin(String login);

    User findByEmail(String email);
}
