package com.renan.todolistapi.application.ports.input;

import com.renan.todolistapi.application.domain.Task;

public interface TasksCommand {
    
    Task create(Task task);
    Task update(Task task);
    void delete(Task task);
}
