package com.renan.todolistapi.adapters.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @JsonProperty("username")
    private String username;
    @JsonProperty("token")
    private String token;

    public UserDto() {
        
    }

    public void setUser(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
