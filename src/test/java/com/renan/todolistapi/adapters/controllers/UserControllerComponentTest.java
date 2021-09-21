package com.renan.todolistapi.adapters.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renan.todolistapi.adapters.controllers.dtos.UserDto;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerComponentTest {

    @LocalServerPort private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    
    @Test
    public void authenticateUser() throws Exception {
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<String> entity = new HttpEntity<String>("user=admin&pass=admin", headers);

        ResponseEntity<String> response = restTemplate.exchange(
          createURLWithPort("/token"), HttpMethod.POST, entity, String.class);

        UserDto user = new ObjectMapper().readValue(response.getBody(), UserDto.class);
        
        assertThat(user.getUsername(), is("admin"));
        assertThat(user.getExpiresIn(), is(300000));
        assertThat(user.getToken(), Is.isA(String.class));
    } 

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
