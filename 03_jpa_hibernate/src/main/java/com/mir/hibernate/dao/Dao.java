package com.mir.hibernate.dao;

import java.util.List;

/**
 * Interface Dao.
 *
 * @author R.M.
 * @since 2022
 */
public interface Dao<E> {

    void create(E entity);

    void update(E entity);

    void remove(E entity);

    List<E> findAll();
}
