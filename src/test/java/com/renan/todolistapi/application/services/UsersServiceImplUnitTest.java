package com.renan.todolistapi.application.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.renan.todolistapi.adapters.repositories.UsersRepository;
import com.renan.todolistapi.adapters.repositories.entities.UserEntity;
import com.renan.todolistapi.application.domain.User;
import com.renan.todolistapi.application.domain.UserRole;
import com.renan.todolistapi.application.exceptions.NotAuthenticatedException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class UsersServiceImplUnitTest {

    @Mock
    UsersRepository mockedUsersRepository;

    @InjectMocks
    UserServiceImpl usersService;

    @Test
    @DisplayName("authenticate for admin super user")
    public void authenticationForAdminUser() {
        List<UserEntity> mockUsersEntities = Arrays.asList(new UserEntity("admin", "admin", UserRole.ADMIN.name()),
                new UserEntity("user", "user", UserRole.USER.name()));

        when(mockedUsersRepository.findAll()).thenReturn(mockUsersEntities);

        User expectedUser = User.fromEntity(mockUsersEntities.get(0));

        User actualUser = usersService.authenticate("admin", "admin");

        assertThat(expectedUser, is(samePropertyValuesAs(actualUser, "authenticated")));
    }

    @Test(expected = NotAuthenticatedException.class)
    @DisplayName("authenticate for admin super user")
    public void userNotFound() {
        List<UserEntity> mockUsersEntities = Arrays.asList(new UserEntity("admin", "admin", UserRole.ADMIN.name()),
                new UserEntity("user", "user", UserRole.USER.name()));

        when(mockedUsersRepository.findAll()).thenReturn(mockUsersEntities);

        usersService.authenticate("not_user", "not_pass");
    }
}
