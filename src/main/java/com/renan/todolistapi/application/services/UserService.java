package com.renan.todolistapi.application.services;

import javax.inject.Singleton;

import com.renan.todolistapi.application.domain.User;

public interface UserService {
    
    User authenticate(String user, String pass);
}
