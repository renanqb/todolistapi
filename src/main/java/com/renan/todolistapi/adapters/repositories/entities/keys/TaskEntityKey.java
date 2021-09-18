package com.renan.todolistapi.adapters.repositories.entities.keys;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

public class TaskEntityKey {
    
    private String userId;
    private int taskId;

    public TaskEntityKey(final String userId, final int taskId) {

        this.userId = userId;
        this.taskId = taskId;
    }

    @DynamoDBHashKey(attributeName = "pk_user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBRangeKey(attributeName = "sk_task_id")
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
