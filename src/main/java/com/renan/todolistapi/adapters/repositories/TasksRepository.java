package com.renan.todolistapi.adapters.repositories;

import com.renan.todolistapi.adapters.repositories.entities.TaskEntity;
import com.renan.todolistapi.adapters.repositories.entities.keys.TaskEntityKey;
import com.renan.todolistapi.application.ports.input.TasksCommand;
import com.renan.todolistapi.application.ports.output.TasksQuery;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface TasksRepository extends CrudRepository<TaskEntity, TaskEntityKey>, TasksCommand, TasksQuery {
}
