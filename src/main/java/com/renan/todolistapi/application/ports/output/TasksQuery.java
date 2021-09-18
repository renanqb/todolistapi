package com.renan.todolistapi.application.ports.output;

import java.util.List;

import com.renan.todolistapi.application.domain.Task;

public interface TasksQuery {
    
    List<Task> listAll(String userId);
    Task findById(String userId, int taskId);
}
