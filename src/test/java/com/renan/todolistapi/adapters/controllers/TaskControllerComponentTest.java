package com.renan.todolistapi.adapters.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renan.todolistapi.adapters.controllers.dtos.DataContainerDto;
import com.renan.todolistapi.adapters.controllers.dtos.TaskDto;
import com.renan.todolistapi.adapters.controllers.dtos.UserDto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerComponentTest {

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void getOneTaskFromRegularUser() throws Exception {

        UserDto authUser = authRegularUser();
        createTaskForRegularUser(authUser);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + authUser.getToken());
        HttpEntity<String> entity = new HttpEntity<String>("user=generic&pass=123456", headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/tasks/9999"), HttpMethod.GET,
                entity, String.class);

        DataContainerDto expectedData = new DataContainerDto(
                new TaskDto("generic", 9999, "title", "Relatório financeiro 2021", "PENDING", null, null));

        DataContainerDto actualData = new ObjectMapper().readValue(response.getBody(), DataContainerDto.class);

        assertThat(expectedData, is(samePropertyValuesAs(actualData, "insertDate", "updateDate")));

        deleteCreatedTaskForRegularUser(authUser);
    }

    private UserDto authRegularUser() throws JsonMappingException, JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<String> entity = new HttpEntity<String>("user=generic&pass=123456", headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/token"), HttpMethod.POST, entity,
                String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        return new ObjectMapper().readValue(response.getBody(), UserDto.class);
    }

    private void createTaskForRegularUser(UserDto userDto) {
        String body = "{\n" + "\"taskId\": 9999,\n" + "\"title\": \"Relatório financeiro 2021\",\n"
                + "\"description\": \"Lorem ipsum dolor sit amet\",\n" + "\"status\": \"pending\"\n" + "}";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + userDto.getToken());
        HttpEntity<String> entity = new HttpEntity<String>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/tasks"), HttpMethod.POST,
                entity, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
    }

    private void deleteCreatedTaskForRegularUser(UserDto userDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + userDto.getToken());
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/tasks/9999"), HttpMethod.DELETE,
                entity, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
