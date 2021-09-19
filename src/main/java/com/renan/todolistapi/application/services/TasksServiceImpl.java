package com.renan.todolistapi.application.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.renan.todolistapi.adapters.repositories.TasksRepository;
import com.renan.todolistapi.adapters.repositories.entities.TaskEntity;
import com.renan.todolistapi.adapters.repositories.entities.keys.TaskEntityKey;
import com.renan.todolistapi.application.domain.Task;
import com.renan.todolistapi.application.domain.TaskStatus;
import com.renan.todolistapi.application.domain.UserRole;
import com.renan.todolistapi.application.exceptions.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasksServiceImpl implements TasksService {

    @Autowired
    protected TasksRepository tasksRepository;

    @Override
    public List<Task> findAll(String userId, String userRole, String taskStatus) {

        UserRole userRoleEnum = Enum.valueOf(UserRole.class, userRole.toUpperCase());
        TaskStatus taskStatusEnum = Enum.valueOf(TaskStatus.class, taskStatus.toUpperCase());

        // only admin user can see all tasks.
        Iterable<TaskEntity> taskEntities = null;
        if (userRoleEnum == UserRole.ADMIN) {
            taskEntities = tasksRepository.findAll();
        } else {
            taskEntities = tasksRepository.findAllByUserId(userId);
        }

        Stream<Task> taskList = StreamSupport.stream(taskEntities.spliterator(), true).map(m -> Task.fromEntity(m));

        // only whether status field was supplied
        if (taskStatusEnum != TaskStatus.NONE)
            taskList = taskList.filter(f -> f.getStatus().equals(taskStatusEnum));

        // ordering list by status pending first
        return taskList.sorted((o1, o2) -> o1.getStatus().compareTo(o2.getStatus()))
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
    public void deleteById(String userId, int taskId) {
        tasksRepository.deleteById(new TaskEntityKey(userId, taskId));
    }

    @Override
    public Task createOrUpdate(Task task) {

        Task oldTask = null;
        try {
            oldTask = findById(task.getUserId(), task.getTaskId());
            return update(oldTask, task);
        } catch (NotFoundException nfe) {
            return create(task);
        }
    }

    private Task create(Task task) {
        task.setInsertDate(LocalDateTime.now());
        task.setUpdateDate(null);
        TaskEntity taskEntity = TaskEntity.fromDomain(task);
        tasksRepository.save(taskEntity);
        return task;
    } 

    private Task update(Task oldTask, Task newTask) {
        newTask.setInsertDate(oldTask.getInsertDate());
        newTask.setUpdateDate(oldTask.getUpdateDate());
        if (oldTask.getStatus() != newTask.getStatus()) {
            newTask.setUpdateDate(LocalDateTime.now());
        }

        TaskEntity taskEntity = TaskEntity.fromDomain(newTask);
        tasksRepository.save(taskEntity);
        return newTask;
    }
}
