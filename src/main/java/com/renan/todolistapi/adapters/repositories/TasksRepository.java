package com.renan.todolistapi.adapters.repositories;

import java.util.Optional;

import com.renan.todolistapi.adapters.repositories.entities.TaskEntity;
import com.renan.todolistapi.adapters.repositories.entities.keys.TaskEntityKey;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface TasksRepository extends CrudRepository<TaskEntity, TaskEntityKey> {

    Iterable<TaskEntity> findAllByUserId(String userId);
    Optional<TaskEntity> findById(TaskEntityKey key);
}
