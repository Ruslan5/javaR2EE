package com.mir.jdbc;

import com.mir.jdbc.dao.ConfigManager;
import com.mir.jdbc.entity.Role;
import com.mir.jdbc.dao.impl.JdbcRoleDao;
import com.mir.jdbc.dao.impl.JdbcUserDao;
import org.apache.log4j.Logger;

import java.text.ParseException;

/**
 *
 * @author R.M.
 * @since 16.04.2022
 */
public class JdbcApp {
    private static Logger logger = Logger.getLogger(JdbcApp.class);

    public static void main(String[] args) throws ParseException {
        ConfigManager configManager = new ConfigManager("/db.properties");

        JdbcRoleDao jdbcRoleDao = new JdbcRoleDao(configManager);
        logger.info("find all roles " + jdbcRoleDao.findAll());

        Role roles = new Role();
        roles.setId(3l);
        roles.setName("patient");
//        jdbcRoleDao.create(roles);
//        logger.info("find create roles " + jdbcRoleDao.findAll());
//        System.out.println(jdbcRoleDao.findById(1l));
//        logger.info("find remove roles " + jdbcRoleDao.findAll());
//        System.out.println("findById " + jdbcRoleDao.findById(1L)); // не работает



        JdbcUserDao userDao = new JdbcUserDao(configManager);
        System.out.println(userDao.findAll());
//
//        User user = new User();
//        user.setId(3l);
//        user.setLogin("Nnnnnn");
//        user.setPassword("passW");
//        user.setEmail("email@mail.com");
//        user.setFirstName("Fname");
//        user.setLastName("Lname");
//        user.setBirthday(new Date(2000, 5, 10));
//        Role role = new Role();
//        role.setId(3l);
//        role.setName("patient");
//
////        userDao.create(user);
//        System.out.println(userDao.findAll());
//
//        User user1 = new User();
//        user1.setId(2l);
//        user1.setLogin("Nnnnnn1");
//        user1.setPassword("passW1");
//        user1.setEmail("email@mail.com1");
//        user1.setFirstName("Fname1");
//        user1.setLastName("Lname1");
//        user1.setBirthday(new Date(1000, 5, 10));
//
//        userDao.update(user1);
//        System.out.println(userDao.findAll());
//
//
//        userDao.remove(user);
//        System.out.println(userDao.findAll());
//
//
//        jdbcRoleDao.update(role);
//        logger.info("find all roles " + jdbcRoleDao.findAll());
//
////        logger.info("find role by id: " + jdbcRoleDao.findById(1l)); // не работает
//        jdbcRoleDao.create(role); // create role


    }
}
