package com.renan.todolistapi;

import com.renan.todolistapi.adapters.controllers.config.JWTAuthorizationFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class TodoListApiApplication {

	static Logger logger = LoggerFactory.getLogger(TodoListApiApplication.class);

	public static void main(String[] args) {
		logger.info("Let's roll!!!");
		SpringApplication.run(TodoListApiApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/token").permitAll().and()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/actuator/health").permitAll()
				.anyRequest().authenticated();
		}
	}

}
