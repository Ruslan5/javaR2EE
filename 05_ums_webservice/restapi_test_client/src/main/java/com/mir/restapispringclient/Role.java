package com.mir.restapispringclient;

/**
 * Entity com.nix.mirzoiev.spring.Role.
 *
 * @author R.Mirzoiev
 * @since 11.02.2022
 *
 */

public class Role {

    private Long id;

    private String name;

    public Role() {
        super();
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addUser(User user){
        user.setRole(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}