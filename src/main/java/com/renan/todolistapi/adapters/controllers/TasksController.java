package com.renan.todolistapi.adapters.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.renan.todolistapi.adapters.controllers.dtos.DataContainerDto;
import com.renan.todolistapi.adapters.controllers.dtos.TaskDto;
import com.renan.todolistapi.application.domain.Task;
import com.renan.todolistapi.application.services.TasksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TasksController extends BaseController {

    @Autowired
    protected TasksService taskService;

    @GetMapping("/tasks")
    public DataContainerDto getAll(@RequestParam(name = "status", defaultValue = "NONE") String status) {
        String username = getTokenUser().getUsername();
        String userRole = getTokenUser().getUserRole();

        List<TaskDto> tasks = taskService.findAll(username, userRole, status).stream()
                .map(m -> TaskDto.fromDomain(m)).collect(Collectors.toList());

        return new DataContainerDto(tasks);
    }

    @GetMapping("/tasks/{id}")
    public DataContainerDto getById(@PathVariable(value = "id") int id) {
        TaskDto task = TaskDto.fromDomain(taskService.findById(getTokenUser().getUsername(), id));
        return new DataContainerDto(task);
    }

    @PostMapping("/tasks")
    @ResponseStatus(code = HttpStatus.CREATED)
    public DataContainerDto create(@RequestBody TaskDto task) {
        task.userId = getTokenUser().getUsername();
        return new DataContainerDto(taskService.createOrUpdate(Task.fromDto(task)));
    }

    @PutMapping("/tasks/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void update(@RequestBody TaskDto task) {
        task.userId = getTokenUser().getUsername();
        taskService.createOrUpdate(Task.fromDto(task));
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        taskService.deleteById(getTokenUser().getUsername(), id);
    }
}
