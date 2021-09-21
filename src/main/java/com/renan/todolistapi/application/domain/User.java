package com.renan.todolistapi.application.domain;

import com.renan.todolistapi.adapters.repositories.entities.UserEntity;

public class User {

    private String username;
    private String password;
    private UserRole role;
    private Boolean authenticated;

    public User(String user, String pass, String role) {
        this.username = user;
        this.password = pass;
        this.role = Enum.valueOf(UserRole.class, role);
        this.authenticated = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role.name();
    }

    public void setAuthenticated(Boolean auth) {
        authenticated = auth;
    }

    public Boolean isAuthenticated() {
        return authenticated;
    }

    public static User fromEntity(UserEntity userEntity) {
        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.getRole());
    }
}
