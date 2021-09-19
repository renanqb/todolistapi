package com.renan.todolistapi.adapters.controllers.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.renan.todolistapi.application.domain.Task;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDto {

    public TaskDto(String userId, int taskId, String title, String desc, String status, LocalDateTime insDate,
            LocalDateTime updDate) {
        this.userId = userId;
        this.taskId = taskId;
        this.title = title;
        this.description = desc;
        this.status = status;
        this.insertDate = insDate;
        this.updateDate = updDate;
    }

    @JsonProperty("taskId")
    public int taskId;
    @JsonProperty("userId")
    public String userId;
    @JsonProperty("title")
    public String title;
    @JsonProperty("description")
    public String description;
    @JsonProperty("status")
    public String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @JsonProperty("insertDate")
    public LocalDateTime insertDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @JsonProperty("updateDate")
    public LocalDateTime updateDate;

    public static TaskDto fromDomain(Task task) {
        return new TaskDto(task.getUserId(), task.getTaskId(), task.getTitle(), task.getDescription(),
                task.getStatus().name(), task.getInsertDate(), task.getUpdateDate());
    }
}
