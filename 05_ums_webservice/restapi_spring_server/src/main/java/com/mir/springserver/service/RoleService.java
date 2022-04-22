package com.mir.springserver.service;

import com.mir.springserver.model.Role;

import java.util.List;

public interface RoleService {
    void create(Role entity);

    void update(Role entity);

    void remove(Role entity);

    List<Role> findAll();

    Role findById(Long id);
}
