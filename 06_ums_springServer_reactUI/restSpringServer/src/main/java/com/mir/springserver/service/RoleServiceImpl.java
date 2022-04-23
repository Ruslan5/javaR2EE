package com.mir.springserver.service;

import com.mir.springserver.dao.impl.HibernateRoleDao;
import com.mir.springserver.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    final HibernateRoleDao hibernateRoleDao;

    @Autowired
    public RoleServiceImpl(HibernateRoleDao hibernateRoleDao) {
        this.hibernateRoleDao = hibernateRoleDao;
    }

    @Override
    @Transactional
    public void create(Role entity) {
        hibernateRoleDao.create(entity);
    }
    @Override
    @Transactional
    public void update(Role entity) {
        hibernateRoleDao.update(entity);
    }

    @Override
    @Transactional
    public void remove(Role entity) {
        hibernateRoleDao.remove(entity);
    }

    @Override
    public List<Role> findAll() {
        return hibernateRoleDao.findAll();
    }

    @Override
    @Transactional
    public Role findById(Long id) {
        return hibernateRoleDao.findById(id);
    }

}
