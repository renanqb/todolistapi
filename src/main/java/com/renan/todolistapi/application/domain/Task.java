package com.renan.todolistapi.application.domain;

import java.time.LocalDateTime;

import com.renan.todolistapi.adapters.dtos.TaskDto;
import com.renan.todolistapi.adapters.repositories.entities.TaskEntity;

public class Task {

    private String userId;
    private int taskId;
    private String title;
    private String description;
    private String status;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;

    public Task(String userId, int taskId, String title, String description, String status, LocalDateTime insertDate,
            LocalDateTime updateDate) {

        this.userId = userId;
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.insertDate = insertDate;
        this.updateDate = updateDate;
    }

    public String getUserId() {
        return userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public static Task fromDto(TaskDto taskDto) {
        return new Task(taskDto.userId, taskDto.taskId, taskDto.title, taskDto.description, taskDto.status,
            taskDto.insertDate, taskDto.updateDate);
    }

    public static Task fromEntity(TaskEntity taskEntity) {
        return new Task(taskEntity.getUserId(), taskEntity.getTaskId(), taskEntity.getTitle(), taskEntity.getDescription(), taskEntity.getStatus(),
            taskEntity.getInsertDate(), taskEntity.getUpdateDate());
    }
}
