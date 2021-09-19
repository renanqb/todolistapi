package com.renan.todolistapi.application.ports.output;

import com.renan.todolistapi.adapters.repositories.entities.UserEntity;

public interface UsersQuery {
    
    Iterable<UserEntity> findAll();
}
