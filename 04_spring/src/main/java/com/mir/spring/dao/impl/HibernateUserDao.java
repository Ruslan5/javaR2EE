package com.mir.spring.dao.impl;

import com.mir.spring.dao.GenericHibernateDao;
import com.mir.spring.dao.UserDao;
import com.mir.spring.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * class HibernateUserDao.
 *
 * @author R.Mirzoiev
 * @see GenericHibernateDao
 * @see UserDao
 * @see User
 * @since 11.02.2022
 */
@Repository
public class HibernateUserDao extends GenericHibernateDao<User> implements UserDao<User> {
    private static Logger logger = Logger.getLogger(HibernateUserDao.class);

    @Override
    public List<User> findAll() {
        List<User> users = null;
        try(Session session = getSession()){
            users = session.createCriteria(User.class).list();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User findByLogin(String login) {

        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.like("login", login));
        return (User) criteria.uniqueResult();
    }

    @Override
    public void create(User user) {
        logger.trace("start method create User");
        Transaction transaction = null;
        try (Session session = getSession()) {
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
        try (Session session = getSession()) {
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
        try (Session session = getSession()) {
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
    public User findByEmail(String email) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.like("email", email));
        return (User) criteria.uniqueResult();
    }

    public User findById(long id) {
        User user;
        Session session = getSession();
        user = session.get(User.class, id);
        return user;
    }
}
