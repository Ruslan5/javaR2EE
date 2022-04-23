package com.mir.springserver.controller;

import com.mir.springserver.dao.impl.HibernateRoleDao;
import com.mir.springserver.model.dto.UserDTO;
import com.mir.springserver.model.dto.UserDTOToUser;
import com.mir.springserver.model.Role;
import com.mir.springserver.model.User;
import com.mir.springserver.service.RoleService;
import com.mir.springserver.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/main")
public class EditController {
    private static Logger logger = Logger.getLogger(HibernateRoleDao.class);

    @Autowired
    final UserService userService;

    @Autowired
    final RoleService roleService;

    @Autowired
    private UserDTOToUser userDTOToUser;

    public EditController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/edit/{login}", method = RequestMethod.GET)
    public String displayEditPage(@PathVariable("login") String login, Model model) {
        User user = userService.findByLogin(login);
        model.addAttribute("user", user);
        return "editform";
    }

    @RequestMapping(value = "/editp/{login}", method = RequestMethod.POST)
    public String editUser(@PathVariable("login") String login,
                           @ModelAttribute(name = "user") UserDTO user, Model model) {
        List<String> errorOfCreate = Arrays.asList(null, null, null, null, null);

        Role role = roleService.findById(Long.parseLong(user.getRole()));
        if (!isValidFields(user, errorOfCreate)) {
            model.addAttribute("errors", errorOfCreate);
            return "editform";
        }
        User user1 = userDTOToUser.convertEdit(login, user, role);
        userService.update(user1);
        model.addAttribute("login", user.getLogin());
        return "redirect:/main/list";
    }

    private boolean isValidFields(UserDTO user,
                                  List<String> errorOfCreate) {
        boolean answer = true;

        if (user.getPassword().isEmpty() && user.getPassword().length() < 3) {
            answer = false;
            errorOfCreate.set(0, "password error");
        }
        if (user.getEmail().isEmpty()) {
            answer = false;
            errorOfCreate.set(1, "email error");
        }
        if (user.getFirstName().isEmpty() && user.getFirstName().length() < 2) {
            answer = false;
            errorOfCreate.set(2, "Firstname error");
        }
        if (user.getLastName().isEmpty() && user.getLastName().length() < 2) {
            answer = false;
            errorOfCreate.set(3, "Lastname error");
        }

        if (user.getBirthday() == null) {
            answer = false;
            errorOfCreate.set(4, "birthday error");
        }

        return answer;
    }
}
