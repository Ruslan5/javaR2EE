package com.mir.spring.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * abstract GenericHibernateDao class.
 *
 * @author R.Mirzoiev
 * @see Dao
 * @since 11.02.2022
 */

public abstract class GenericHibernateDao<E> implements Dao<E> {
    private static Logger logger = Logger.getLogger(GenericHibernateDao.class);

    @Autowired
    private SessionFactory sessionFactory;
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
