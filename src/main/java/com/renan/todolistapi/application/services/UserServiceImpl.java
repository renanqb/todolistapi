package com.renan.todolistapi.application.services;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.renan.todolistapi.adapters.repositories.UsersRepository;
import com.renan.todolistapi.adapters.repositories.entities.UserEntity;
import com.renan.todolistapi.application.domain.User;
import com.renan.todolistapi.application.exceptions.NotAuthenticatedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    protected UsersRepository usersRepository;

    @Override
    public User authenticate(String user, String pass) {

        Iterable<UserEntity> userEntities = usersRepository.findAll();
        Stream<User> users = StreamSupport.stream(userEntities.spliterator(), true).map(m -> User.fromEntity(m));

        User authUser = users.filter(f -> f.getUsername().equalsIgnoreCase(user) && f.getPassword().equals(pass)).findFirst()
                .orElseThrow(() -> new NotAuthenticatedException("Invalid user or password!"));
        authUser.setAuthenticated(true);

        return authUser;
    }

}
