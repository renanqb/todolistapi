package com.renan.todolistapi.application.ports.output;

import com.renan.todolistapi.adapters.repositories.entities.TaskEntity;

public interface TasksQuery {
    
    Iterable<TaskEntity> findAllByUserId(String userId);
}
