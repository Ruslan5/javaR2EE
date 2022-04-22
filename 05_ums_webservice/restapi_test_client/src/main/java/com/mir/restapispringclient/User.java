package com.mir.restapispringclient;

import java.sql.Date;

/**
 * Entity com.nix.mirzoiev.spring.model.User.
 *
 * @author R.Mirzoiev
 * @since 11.02.2022
 */

public class User {

    private long id;

    private String login;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private Date birthday;

    private Role role;

    public User() {
        super();
    }

    public User(String login, String password,
                String email, String firstName,
                String lastName, Date birthday, Role role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    @Override
    public String toString() {
        return login + " " + password + " " + email + " " + firstName +" " + lastName + " " + birthday+ " " + role;
    }
}
