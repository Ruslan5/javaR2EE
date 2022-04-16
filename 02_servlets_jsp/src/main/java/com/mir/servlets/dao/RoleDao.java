package com.mir.servlets.dao;

/**
 * interface RoleDao.
 *
 * @author R.Mirzoiev
 * @see Dao
 * @see Role
 * @since 31.01.2022
 */
public interface RoleDao<Role> extends Dao<Role> {
    Role findById(Long id);
}
