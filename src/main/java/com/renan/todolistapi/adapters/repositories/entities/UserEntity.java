package com.renan.todolistapi.adapters.repositories.entities;

public class UserEntity {
    private String username;
    private String password;
    private String role; 

    public UserEntity(String user, String pass, String role) {
        this.username = user;
        this.password = pass;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
