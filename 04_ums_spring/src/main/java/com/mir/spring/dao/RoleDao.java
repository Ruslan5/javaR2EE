package com.mir.spring.dao;

/**
 * interface RoleDao.
 *
 * @author R.Mirzoiev
 * @see Dao
 * @see Role
 * @since 11.02.2022
 */
public interface RoleDao<Role> extends Dao<Role> {
    Role findById(Long id);
}
