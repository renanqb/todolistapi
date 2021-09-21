package com.renan.todolistapi.adapters.controllers;

import com.renan.todolistapi.adapters.controllers.dtos.UserDto;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {

    protected UserDto getTokenUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        String userRole = authentication.getAuthorities().stream().findFirst().get().getAuthority();
        return new UserDto(username, userRole);
    }
}
