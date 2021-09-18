package com.renan.todolistapi.adapters.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import com.renan.todolistapi.adapters.dtos.UserDto;
import com.renan.todolistapi.config.JwtSecretGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@RestController
public class UserController {
    
    @PostMapping("/user")
	public UserDto login(@RequestParam("username") String username, @RequestParam("password") String pwd) {
		
		String token = getJWTToken(username);
		UserDto user = new UserDto();
		user.setUser(username);
		user.setToken(token);		
		return user;
		
	}

    private String getJWTToken(String username) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS256, JwtSecretGenerator.Instance().getSecret().getBytes())
				.compact();

		return token;
	}
}
