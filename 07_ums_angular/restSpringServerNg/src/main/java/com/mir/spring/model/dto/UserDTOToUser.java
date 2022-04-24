package com.mir.spring.model.dto;

import com.mir.spring.service.UserService;
import com.mir.spring.model.Role;
import com.mir.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDTOToUser {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

        public User convert(UserDTO userDTO, Role role) {
        User user = new User();
            user.setLogin(userDTO.getLogin());
            String password = bCryptPasswordEncoder.encode(userDTO.getPassword());
            user.setPassword(password);
            user.setEmail(userDTO.getEmail());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setBirthday(userDTO.getBirthday());
            user.setRole(role);
            return user;
        }

    public User convertEdit(String login, UserDTO userDTO, Role role) {
        User user = userService.findByLogin(login);
        String password = bCryptPasswordEncoder.encode(userDTO.getPassword());
        user.setPassword(password);
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthday(userDTO.getBirthday());
        user.setRole(role);
        return user;
    }
}
