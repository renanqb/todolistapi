package com.renan.todolistapi.application.ports.input;

import com.renan.todolistapi.adapters.repositories.entities.keys.TaskEntityKey;

public interface TasksCommand {
    
     abstract void deleteById(TaskEntityKey key);
}
