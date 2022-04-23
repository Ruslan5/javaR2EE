package com.mir.springserver.dao.impl;

import com.mir.springserver.dao.GenericHibernateDao;
import com.mir.springserver.dao.RoleDao;
import com.mir.springserver.model.Role;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * class HibernateRoleDao.
 *
 * @author R.Mirzoiev
 * @see GenericHibernateDao
 * @see RoleDao
 * @see Role
 * @since 11.01.2022
 */
@Repository
public class HibernateRoleDao extends GenericHibernateDao<Role> implements RoleDao<Role> {
    private static Logger logger = Logger.getLogger(HibernateRoleDao.class);
    private static final String SQL_FIND_ALL_ROLES = "SELECT * FROM ROLES_DB";

    @Autowired
    private final SessionFactory sessionFactory;

    public HibernateRoleDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Role entity) {
        logger.trace("start method create Role");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            logger.trace("finish method create Role");
        } catch (Exception e) {
            logger.trace("Create Exception: {} ", e);
            transaction.rollback();
            throw new RuntimeException("Role has not been created: {} ", e);
        }

    }


    @Override
    public Role findById(Long id) {
        return sessionFactory.getCurrentSession().get(Role.class, id);
    }

    @Override
    public void update(Role entity) {
        logger.trace("start method update Role");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            logger.trace("finish method update Role");
        } catch (Exception e) {
            logger.trace("Update Exception {} ", e);
            transaction.rollback();
            throw new RuntimeException("Role has not been updated: {} ", e);
        }
    }

    @Override
    public void remove(Role entity) {
        logger.trace("start method remove Role");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            logger.trace("finish method remove Role");
        } catch (Exception e) {
            logger.trace("Remove Exception: {} ", e);
            transaction.rollback();
            throw new RuntimeException("Role has not been removed: {} ", e);
        }
    }

    @Override
    public List<Role> findAll() {
        logger.trace("start method findAll User");
        List<Role> roles = sessionFactory.openSession().createNativeQuery(SQL_FIND_ALL_ROLES)
                .addEntity(Role.class).list();
        logger.trace("finish method findAll Roles");
        return roles;
    }
}
