package com.mir.spring.service;

import com.mir.spring.model.User;

import java.util.List;


public interface UserService {

    List<User> findAll();

    void create(User user);

    void update(User user);

    void remove(User user);

    User findByLogin(String login);

    User findByEmail(String email);

    User findById(long id);
}
