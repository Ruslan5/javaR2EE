package com.mir.springserver.dao;

import java.util.List;

/**
 * Interface Dao.
 *
 * @author R.Mirzoiev
 * @since 11.02.2022
 */
public interface Dao<E> {

    void create(E entity);

    void update(E entity);

    void remove(E entity);

    List<E> findAll();
}
