package com.renan.todolistapi.adapters.controllers.dtos;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;

import com.renan.todolistapi.application.domain.Task;
import com.renan.todolistapi.application.domain.TaskStatus;

import org.junit.Test;

public class TaskDtoUnitTest {

    @Test
    public void mappingTaskFromDomain() {

        LocalDateTime currentDate = LocalDateTime.now();
        Task task = new Task("userId", 1, "title", "description", TaskStatus.COMPLETED.name());
        task.setInsertDate(currentDate);
        task.setUpdateDate(currentDate);

        TaskDto expectedTask = new TaskDto("userId", 1, "title", "description", TaskStatus.COMPLETED.name(),
                currentDate, currentDate);
        TaskDto actualTask = TaskDto.fromDomain(task);

        assertThat(expectedTask, is(samePropertyValuesAs(actualTask)));
    }
}
