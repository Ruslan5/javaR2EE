package com.mir.hibernate;

import com.mir.hibernate.dao.impl.HibernateRoleDao;
import com.mir.hibernate.dao.impl.HibernateUserDao;
import com.mir.hibernate.entity.Role;
import com.mir.hibernate.entity.User;

import org.apache.log4j.Logger;

import java.sql.Date;

public class HibernateApp {
    private static Logger logger = Logger.getLogger(HibernateApp.class);

    public static void main(String[] args) {

        HibernateUserDao userService = new HibernateUserDao();

        HibernateRoleDao roleService = new HibernateRoleDao();
        Role role = new Role("Admin");
        Role role2 = new Role("user");
        roleService.create(role);
        logger.debug("Create role : ---> " + role);

        roleService.create(role2);
        logger.debug("Create role : ---> " + role2);

        User user = new User("login1", "pass1",
                "email1", "fname1",
                "lname1", new Date(2000 - 11 - 11), role);
        userService.create(user);
        logger.debug("Create user ----> : " + user);

        User user2 = new User("login2", "pass2",
                "email2", "fname2",
                "lname2", new Date(2000 - 11 - 11), role2);
        userService.create(user2);
        logger.debug("Create user ----> : " + user2);

        logger.debug("Find all users: ---> " + userService.findAll());
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");

        logger.debug("Find user by login: ---> " + userService.findByLogin("login1"));
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");

        logger.debug("Find all rolls: ---> " + roleService.findAll());
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");

        logger.debug("Find role by id: ---> " + roleService.findById(1l));
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
    }
}
