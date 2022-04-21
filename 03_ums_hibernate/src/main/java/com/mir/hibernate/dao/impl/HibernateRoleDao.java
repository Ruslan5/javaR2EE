package com.mir.hibernate.dao.impl;

import com.mir.hibernate.dao.GenericHibernateDao;
import com.mir.hibernate.dao.RoleDao;
import com.mir.hibernate.entity.Role;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * class HibernateRoleDao.
 *
 * @author R.M.
 * @see GenericHibernateDao
 * @see RoleDao
 * @see Role
 * @since 2022
 */
public class HibernateRoleDao extends GenericHibernateDao<Role> implements RoleDao<Role> {
    private static Logger logger = Logger.getLogger(HibernateRoleDao.class);
    private static final String SQL_FIND_ALL_ROLES = "SELECT * FROM ROLES_DB";

    @Override
    public void create(Role entity) {
        logger.trace("start method create Role");
        Transaction transaction = null;
        try (Session session = getSessionFactory()
                .openSession()) {
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
    public void update(Role entity) {
        logger.trace("start method update Role");
        Transaction transaction = null;
        try (Session session = getSessionFactory()
                .openSession()) {
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
        try (Session session = getSessionFactory()
                .openSession()) {
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
        List<Role> roles = getSessionFactory()
                .openSession().createNativeQuery(SQL_FIND_ALL_ROLES)
                .addEntity(Role.class).list();
        logger.trace("finish method findAll Roles");
        return roles;
    }
    @Override
    public Role findById(Long id) {
        logger.trace("start method findById Role");
        return getSessionFactory().openSession()
                .get(Role.class, id);
    }
}
