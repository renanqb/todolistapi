package com.renan.todolistapi.adapters.repositories.entities;

import java.time.LocalDateTime;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.renan.todolistapi.adapters.repositories.config.LocalDateTimeConverter;
import com.renan.todolistapi.adapters.repositories.entities.keys.TaskEntityKey;
import com.renan.todolistapi.application.domain.Task;

import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "tasks-table")
public class TaskEntity {

    @Id
    private TaskEntityKey partitionKey;
    private String title;
    private String description;
    private String status;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;

    public TaskEntity(String userId, int taskId, String title, String description, String status,
            LocalDateTime insertDate, LocalDateTime updateDate) {
        this.partitionKey = new TaskEntityKey("", 0);
        setUserId(userId);
        setTaskId(taskId);
        setTitle(title);
        setDescription(description);
        setStatus(status);
        setInsertDate(insertDate);
        setUpdateDate(updateDate);
    }

    @DynamoDBHashKey(attributeName = "pk_user_id")
    public String getUserId() {
        return partitionKey.getUserId();
    }

    public void setUserId(String userId) {
        this.partitionKey.setUserId(userId);
    }

    @DynamoDBRangeKey(attributeName = "sk_task_id")
    public int getTaskId() {
        return partitionKey.getTaskId();
    }

    public void setTaskId(int taskId) {
        this.partitionKey.setTaskId(taskId);
    }

    @DynamoDBAttribute(attributeName = "task_title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName = "task_description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "task_status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @DynamoDBAttribute(attributeName = "task_insert_date")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }

    @DynamoDBAttribute(attributeName = "task_update_date")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public static TaskEntity fromDomain(Task task) {
        return new TaskEntity(task.getUserId(), task.getTaskId(), task.getTitle(), task.getDescription(),
                task.getStatus().name(), task.getInsertDate(), task.getUpdateDate());
    }
}
