package com.mir.jdbc.dao;

import java.util.List;

/**
 *
 * @author R.M.
 * @since 16.04.2022
 */
public interface Dao<E> {

    void create(E entity);

    void update(E entity);

    void remove(E entity);

    List<E> findAll();
}
