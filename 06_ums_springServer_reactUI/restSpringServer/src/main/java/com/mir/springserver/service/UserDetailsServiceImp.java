package com.mir.springserver.service;

import com.mir.springserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private  UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = userService.findByLogin(login);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return UserDetailsImp.convertUserToUserdetails(user);
    }
}
