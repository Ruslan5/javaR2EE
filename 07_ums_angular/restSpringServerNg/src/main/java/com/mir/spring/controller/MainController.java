package com.mir.spring.controller;

import com.mir.spring.service.UserDetailsServiceImp;
import com.mir.spring.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class MainController {
    private static Logger logger = Logger.getLogger(MainController.class);

    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    public UserDetailsServiceImp getUserDetailsService() {
        return userDetailsServiceImp;
    }

    @Autowired
    private UserService userService;

//    @Autowired
//    private RoleService roleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(ModelMap model) {
        String login = SecurityContextHolder.getContext().getAuthentication()
                .getName();
        String roleName = userService.findByLogin(login).getRole().getName();
        model.addAttribute("login", login);
        if (Objects.equals(roleName, "user")) {
            return "userPage";
        } else {
            model.addAttribute("usersList", userService.findAll());
            return "listusers";
        }
    }

}