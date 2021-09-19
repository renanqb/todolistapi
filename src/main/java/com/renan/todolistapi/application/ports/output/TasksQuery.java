package com.renan.todolistapi.application.ports.output;

import java.util.Optional;

import com.renan.todolistapi.adapters.repositories.entities.TaskEntity;
import com.renan.todolistapi.adapters.repositories.entities.keys.TaskEntityKey;

public interface TasksQuery {
    
    Iterable<TaskEntity> findAllByUserId(String userId);
    Optional<TaskEntity> findById(TaskEntityKey key);
}
