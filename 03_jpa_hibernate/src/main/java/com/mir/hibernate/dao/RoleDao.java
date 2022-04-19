package com.mir.hibernate.dao;

/**
 * interface RoleDao.
 *
 * @author R.M.
 * @see Dao
 * @see Role
 * @since 2022
 */
public interface RoleDao<Role> extends Dao<Role> {
    Role findById(Long id);
}
