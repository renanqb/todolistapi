package com.renan.todolistapi.adapters.controllers.dtos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.renan.todolistapi.application.domain.User;
import com.renan.todolistapi.application.domain.UserRole;

import org.junit.Test;

public class UserDtoUnitTest {

    @Test
    public void mappingUserFromDomain() {
        User user = new User("admin", "admin", UserRole.ADMIN.name());
        user.setAuthenticated(true);

        UserDto actualUser = UserDto.fromDomain(user);

        assertThat(actualUser.getUsername(), is("admin"));
        assertThat(actualUser.getExpiresIn(), is(300000));
        assertThat(actualUser.getUserRole(), is("ADMIN"));
        assertThat(actualUser.getToken(), is(""));
    }
}
