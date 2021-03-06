package com.renan.todolistapi.adapters.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.renan.todolistapi.application.domain.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @JsonProperty("username")
    private String username;
    @JsonProperty("token")
    private String token;
    @JsonProperty("expiresIn")
    private int expiresIn;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    private String userRole;

    public UserDto() {
        
    }

    public UserDto(String username, String userRole) {
        setUser(username);
        setToken("");
        setExpiresIn(300000);
        setUserRole(userRole);
        this.type = "Bearer";
    }

    public String getUsername() {
        return this.username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(int exp) {
        this.expiresIn = exp;
    }

    public String getUserRole() {
        return this.userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public static UserDto fromDomain(User user) {
        return new UserDto(user.getUsername(), user.getRole());
    }
    
}
