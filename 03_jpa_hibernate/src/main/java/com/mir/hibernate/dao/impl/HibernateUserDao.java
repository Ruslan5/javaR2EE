package com.mir.hibernate.dao.impl;

import com.mir.hibernate.dao.GenericHibernateDao;
import com.mir.hibernate.dao.UserDao;
import com.mir.hibernate.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * class HibernateUserDao.
 *
 * @author R.M.
 * @see GenericHibernateDao
 * @see UserDao
 * @see User
 * @since 11.02.2022
 */
public class HibernateUserDao extends GenericHibernateDao<User> implements UserDao<User> {
    private static Logger logger = Logger.getLogger(HibernateUserDao.class);
    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM USERS_DB";

    public List<User> findAll() {
        logger.debug("start method findAll User");
        List<User> users = getSessionFactory()
                .openSession().createNativeQuery(SQL_FIND_ALL_USERS)
                .addEntity(User.class).list();
        logger.trace("finish method findAll User");
        return users;
    }

    public void create(User user) {
        logger.trace("start method create User");
        Transaction transaction = null;
        try (Session session = getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            logger.debug("User created successfully");
            transaction.commit();
            logger.debug("finish method create User");
        } catch (Exception e) {
            logger.debug("Create Exception: {} ", e);
            transaction.rollback();
            throw new RuntimeException("User has not been created: {} ", e);
        }
    }

    @Override
    public void update(User user) {
        logger.debug("start method update User");
        Transaction transaction = null;
        try (Session session = getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            logger.debug("User updated successfully");
            transaction.commit();
            logger.debug("finish method update User");
        } catch (Exception e) {
            logger.debug("Update Exception: {} ", e);
            transaction.rollback();
            throw new RuntimeException("Update Exception: {} ", e);
        }
    }

    @Override
    public void remove(User user) {
        logger.debug("start method remove User");
        Transaction transaction = null;
        try (Session session = getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            logger.debug("User removed successfully");
            transaction.commit();
            logger.debug("finish method remove User");
        } catch (Exception e) {
            logger.debug("Remove Exception: {} ", e);
            transaction.rollback();
            throw new RuntimeException("User has not been removed: {} ", e);
        }
    }

    @Override
    public User findByLogin(String login) {
        Criteria criteria = getSessionFactory()
                .openSession().createCriteria(User.class);
        criteria.add(Restrictions.like("login", login));
        return (User) criteria.uniqueResult();
    }

    @Override
    public User findByEmail(String email) {
        Criteria criteria = getSessionFactory()
                .openSession().createCriteria(User.class);
        criteria.add(Restrictions.like("email", email));
        return (User) criteria.uniqueResult();
    }
}
