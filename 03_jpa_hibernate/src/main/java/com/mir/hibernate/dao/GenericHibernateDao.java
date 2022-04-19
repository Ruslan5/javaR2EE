package com.mir.hibernate.dao;

import com.mir.hibernate.entity.Role;
import com.mir.hibernate.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * abstract GenericHibernateDao class.
 *
 * @author R.M.
 * @see Dao
 * @since 2022
 */
public abstract class GenericHibernateDao<E> implements Dao<E> {
    private static Logger logger = Logger.getLogger(GenericHibernateDao.class);
    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        logger.debug("Start method");
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Role.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
                logger.debug("Finish method");
            } catch (Exception e) {
                logger.debug("no connected exception: {} ", e);
                throw new RuntimeException("No connected {}: ", e);
            }
        }
        return sessionFactory;
    }
}
