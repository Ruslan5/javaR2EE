package com.mir.servlets.dao;

import java.util.List;
/**
 * Interface Dao.
 *
 * @author R.Mirzoiev
 * @since 31.01.2022
 */
public interface Dao<E> {

    void create(E entity);

    void update(E entity);

    void remove(E entity);

    List<E> findAll();
}
