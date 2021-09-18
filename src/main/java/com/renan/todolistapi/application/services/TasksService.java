package com.renan.todolistapi.application.services;

import java.util.List;

import com.renan.todolistapi.application.domain.Task;

public interface TasksService {
    List<Task> findAll(String userId);
    Task findById(String userId, int taskId);
    Task createOrUpdate(Task task);
    void deleteById(String userId, int taskId);
}
