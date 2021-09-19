package com.renan.todolistapi.application.domain;

import java.time.LocalDateTime;

import com.renan.todolistapi.adapters.controllers.dtos.TaskDto;
import com.renan.todolistapi.adapters.repositories.entities.TaskEntity;

public class Task {

    private String userId;
    private int taskId;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;

    public Task(String userId, int taskId, String title, String description, String status) {

        this.userId = userId;
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = Enum.valueOf(TaskStatus.class, status.toUpperCase());
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

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public static Task fromDto(TaskDto taskDto) {
        return new Task(taskDto.userId, taskDto.taskId, taskDto.title, taskDto.description, taskDto.status);
    }

    public static Task fromEntity(TaskEntity taskEntity) {
        Task task = new Task(taskEntity.getUserId(), taskEntity.getTaskId(), taskEntity.getTitle(),
                taskEntity.getDescription(), taskEntity.getStatus());
        task.setInsertDate(taskEntity.getInsertDate());
        task.setUpdateDate(taskEntity.getUpdateDate());

        return task;
    }
}
