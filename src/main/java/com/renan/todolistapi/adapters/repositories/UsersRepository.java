package com.renan.todolistapi.adapters.repositories;

import java.util.Arrays;

import com.renan.todolistapi.adapters.repositories.entities.UserEntity;
import com.renan.todolistapi.application.ports.output.UsersQuery;

import org.springframework.stereotype.Service;

@Service
public class UsersRepository implements UsersQuery {

    @Override
    public Iterable<UserEntity> findAll() {
        
        Iterable<UserEntity> stubUsers = Arrays.asList(
            new UserEntity("admin", "admin", "ADMIN"),
            new UserEntity("renan", "123456", "USER"), 
            new UserEntity("generic", "123456", "USER")
        );

        return stubUsers;
    }
}
