package com.mir.spring.controller;

import com.mir.spring.service.RoleService;
import com.mir.spring.service.UserDetailsServiceImp;
import com.mir.spring.service.UserService;
import com.mir.spring.dao.impl.HibernateRoleDao;
import com.mir.spring.model.Role;
import com.mir.spring.model.User;
import com.mir.spring.model.dto.CaptchaResponseDto;
import com.mir.spring.model.dto.UserDTO;
import com.mir.spring.model.dto.UserDTOToUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class LoginController {
    private static Logger logger = Logger.getLogger(HibernateRoleDao.class);
    private static final String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=?&response=?";

    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    public UserDetailsServiceImp getUserDetailsService() {
        return userDetailsServiceImp;
    }
    @Autowired
    private UserDTOToUser userDTOToUser;
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${recaptcha.secret}")
    private String secret = "6LcShpIeAAAAAG9GclUBI1NGV07L7Z5OC5-Zvo8o";

    @Autowired
    private RestTemplate restTemplate;

    public LoginController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    String getRegistrationPage() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    String createNewUser(@RequestParam("g-recaptcha-response") String captchaResponse, @ModelAttribute(name = "user")
            UserDTO user, Model model) {

        List<String> errorOfCreate = Arrays.asList(null, null, null, null, null, null);
        String url = String.format(CAPTCHA_URL, secret, captchaResponse);

        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.singletonList(MediaType.ALL), CaptchaResponseDto.class);

        Role role = roleService.findById(Long.parseLong(user.getRole()));

        if (!isValidFields(user, errorOfCreate)) {
            model.addAttribute("errors", errorOfCreate);
            return "registration";
        }

        User user1 = userDTOToUser.convert(user, role);

        userService.create(user1);
        return "login";
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
