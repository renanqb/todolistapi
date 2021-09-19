package com.renan.todolistapi.adapters.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.renan.todolistapi.adapters.controllers.config.JwtSecretGenerator;
import com.renan.todolistapi.adapters.controllers.dtos.UserDto;
import com.renan.todolistapi.application.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {
    
	@Autowired
	private UserService userService;

    @PostMapping("/token")
	public UserDto login(@RequestParam("user") String userId, @RequestParam("pass") String pass) {
		
		UserDto user = UserDto.fromDomain(userService.authenticate(userId, pass));
		String token = getJWTToken(user);
		user.setToken(token);		
		return user;
	}

    private String getJWTToken(UserDto user) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(user.getUserRole());
		
		String token = Jwts
				.builder()
				.setId("todo-list-api")
				.setSubject(user.getUsername())
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + user.getExpiresIn()))
				.signWith(SignatureAlgorithm.HS256, JwtSecretGenerator.Instance().getSecret().getBytes())
				.compact();

		return token;
	}
}
