package com.mir.spring.controller;

import com.mir.spring.service.RoleService;
import com.mir.spring.service.UserDetailsServiceImp;
import com.mir.spring.service.UserService;
import com.mir.spring.dao.impl.HibernateRoleDao;
import com.mir.spring.model.Role;
import com.mir.spring.model.User;
import com.mir.spring.model.dto.UserDTO;
import com.mir.spring.model.dto.UserDTOToUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/main")
public class AdminController {
    private static Logger logger = Logger.getLogger(HibernateRoleDao.class);

    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    public UserDetailsServiceImp getUserDetailsService() {
        return userDetailsServiceImp;
    }

    @Autowired
    final RoleService roleService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserDTOToUser userDTOToUser;

    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddPage() {
        return "add";
    }

    @PostMapping("/addUser")
    public String addEmployee(@ModelAttribute(name = "user") UserDTO user, Model model) {

        List<String> errorOfCreate = Arrays.asList(null, null, null, null, null, null);
        Role role = roleService.findById(Long.parseLong(user.getRole()));

        if (!isValidFields(user, errorOfCreate)) {
            model.addAttribute("errors", errorOfCreate);
            return "add";
        }

        User user1 = userDTOToUser.convert(user, role);
        userService.create(user1);
        return "redirect:/main/list";
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication()
                .getName();
        List <User> userList = userService.findAll();
        logger.debug(userList);
        model.addAttribute("usersList", getAllUsers());
        model.addAttribute("login", login);
        return "listusers";
    }


    private List<User> getAllUsers() {
        List<User> list = userService.findAll();
        for (User user : list) {
            user.setPassword(null);
        }
        return list;
    }

    private boolean isValidFields(UserDTO user,
                                  List<String> errorOfCreate) {
        boolean answer = true;

        if (user.getLogin().isEmpty() && user.getLogin().length() < 3) {
            answer = false;
            errorOfCreate.set(0, "login error");
        }
        if (user.getPassword().isEmpty() && user.getPassword().length() < 3) {
            answer = false;
            errorOfCreate.set(1, "password error");
        }
        if (user.getEmail().isEmpty()) {
            answer = false;
            errorOfCreate.set(2, "email error");
        }
        if (user.getFirstName().isEmpty() && user.getFirstName().length() < 2) {
            answer = false;
            errorOfCreate.set(3, "Firstname error");
        }
        if (user.getLastName().isEmpty() && user.getLastName().length() < 2) {
            answer = false;
            errorOfCreate.set(4, "Lastname error");
        }
        if (user.getBirthday() == null) {
            answer = false;
            errorOfCreate.set(5, "birthday error");
        }

        return answer;
    }

}
