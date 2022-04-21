package com.mir.jdbc.dao;

/**
 *
 * @author R.M.
 * @since 16.04.2022
 */
public interface RoleDao<Role> extends Dao<Role> {
    Role findById(Long id);
}
