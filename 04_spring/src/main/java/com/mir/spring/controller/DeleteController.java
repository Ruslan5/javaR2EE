package com.mir.spring.controller;

import com.mir.spring.dao.impl.HibernateRoleDao;
import com.mir.spring.model.User;
import com.mir.spring.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class DeleteController {
    private static Logger logger = Logger.getLogger(HibernateRoleDao.class);

    @Autowired
    final UserService userService;

    public DeleteController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/delete/{login}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("login") String login) {
        User user = userService.findByLogin(login);
        logger.debug("findByLogin(login): " + user);
        userService.remove(user);
        logger.debug("remove: " + user);
        return "redirect:/main/list";
    }
}
