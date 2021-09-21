package com.renan.todolistapi.application.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;

import java.time.LocalDateTime;

import com.renan.todolistapi.adapters.controllers.dtos.TaskDto;
import com.renan.todolistapi.adapters.repositories.entities.TaskEntity;

import org.junit.Test;

public class TaskUnitTest {

    @Test
    public void mappingTaskFromEntity() {

        LocalDateTime currentDate = LocalDateTime.now();
        TaskEntity taskEntity = new TaskEntity("userId", 1, "title", "description", TaskStatus.COMPLETED.name(),
                currentDate, currentDate);
        Task expectedTask = new Task("userId", 1, "title", "description", TaskStatus.COMPLETED.name());
        expectedTask.setInsertDate(currentDate);
        expectedTask.setUpdateDate(currentDate);
        Task actualTask = Task.fromEntity(taskEntity);

        assertThat(expectedTask, is(samePropertyValuesAs(actualTask)));
    }

    @Test
    public void mappingTaskFromDto() {

        LocalDateTime currentDate = LocalDateTime.now();
        TaskDto taskDto = new TaskDto("userId", 1, "title", "description", TaskStatus.COMPLETED.name(), currentDate,
                currentDate);
        Task expectedTask = new Task("userId", 1, "title", "description", TaskStatus.COMPLETED.name());
        Task actualTask = Task.fromDto(taskDto);

        assertThat(expectedTask, is(samePropertyValuesAs(actualTask)));
    }
}
