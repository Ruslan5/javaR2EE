package com.mir.springserver.service;

import com.mir.springserver.dao.impl.HibernateUserDao;
import com.mir.springserver.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    final HibernateUserDao hibernateUserDao;

    private static Logger logger = Logger.getLogger(HibernateUserDao.class);

    public UserServiceImpl(HibernateUserDao hibernateUserDao){
        this.hibernateUserDao = hibernateUserDao;
    }

    @Override
    public List<User> findAll() {
        logger.debug("findAll: " + hibernateUserDao.findAll());
        return hibernateUserDao.findAll();
    }

    @Override
    @Transactional
    public void create(User entity) {
        Session session = hibernateUserDao.getSession();
        session.save(entity);
    }

    @Override
    @Transactional
    public void update(User user) {
        hibernateUserDao.update(user);
    }

    @Override
    @Transactional
    public void remove(User user) {
        hibernateUserDao.remove(user);
    }

    @Override
    @Transactional
    public User findByLogin(String login) {
       return hibernateUserDao.findByLogin(login);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return hibernateUserDao.findByEmail(email);
    }

    @Override
    @Transactional
    public User findById(long id) {
        return hibernateUserDao.findById(id);
    }

    @Transactional
    public void delete(long id) {
        User user = findById(id);
        hibernateUserDao.remove(user);
    }
}
