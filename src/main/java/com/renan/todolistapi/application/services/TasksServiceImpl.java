package com.renan.todolistapi.application.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.renan.todolistapi.adapters.repositories.TasksRepository;
import com.renan.todolistapi.adapters.repositories.entities.TaskEntity;
import com.renan.todolistapi.adapters.repositories.entities.keys.TaskEntityKey;
import com.renan.todolistapi.application.domain.Task;
import com.renan.todolistapi.application.exceptions.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasksServiceImpl implements TasksService {

    @Autowired
    protected TasksRepository tasksRepository;

    @Override
    public List<Task> findAll(String userId) {
        Iterable<TaskEntity> taskEntities = tasksRepository.findAllByUserId(userId);
        return StreamSupport.stream(taskEntities.spliterator(), true).map(m -> Task.fromEntity(m))
                .collect(Collectors.toList());
    }

    @Override
    public Task findById(String userId, int taskId) {
        Optional<TaskEntity> taskEntity = tasksRepository.findById(new TaskEntityKey(userId, taskId));

        if (taskEntity.isEmpty()) 
            throw new NotFoundException(String.format("Task %s not found for user %s", taskId, userId));

        return Task.fromEntity(taskEntity.get());
    }

    @Override
    public Task createOrUpdate(Task task) {
        TaskEntity taskEntity = TaskEntity.fromDomain(task);
        tasksRepository.save(taskEntity);
        return task;
    }

    @Override
    public void deleteById(String userId, int taskId) {
        tasksRepository.deleteById(new TaskEntityKey(userId, taskId));
    }
}
