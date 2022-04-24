package com.mir.spring.service;

import com.mir.spring.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserDetailsImp implements UserDetails {
    private String username;
    private String password;
    private List<? extends GrantedAuthority> grantedAuthorities;

    public UserDetailsImp(String username, String password, List<? extends GrantedAuthority> grantedAuthorities) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    public static UserDetails convertUserToUserdetails(User user){
        UserDetails userDetails = new UserDetailsImp(user.getLogin(), user.getPassword()
                , Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName())));
        return userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
