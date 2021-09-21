package com.renan.todolistapi.application.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.renan.todolistapi.adapters.repositories.TasksRepository;
import com.renan.todolistapi.adapters.repositories.entities.TaskEntity;
import com.renan.todolistapi.adapters.repositories.entities.keys.TaskEntityKey;
import com.renan.todolistapi.application.domain.Task;
import com.renan.todolistapi.application.domain.TaskStatus;
import com.renan.todolistapi.application.domain.UserRole;
import com.renan.todolistapi.application.exceptions.NotFoundException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class TasksServiceImplUnitTest {

    @Mock
    TasksRepository mockedTasksRepository;

    @InjectMocks
    TasksServiceImpl tasksService;

    @Test
    @DisplayName("Call findAll method for Admin (super user)")
    public void findAllForAdminUser() {
        LocalDateTime date = LocalDateTime.now();

        List<TaskEntity> mockTaskEntities = Arrays.asList(
                new TaskEntity("admin", 1, "title1", "desc1", "pending", date, date),
                new TaskEntity("admin", 2, "title2", "desc2", "completed", date, date),
                new TaskEntity("user", 1, "title1", "desc1", "pending", date, date));

        when(mockedTasksRepository.findAll()).thenReturn(mockTaskEntities);

        List<Task> expectedTasks = Arrays.asList(Task.fromEntity(mockTaskEntities.get(0)),
                Task.fromEntity(mockTaskEntities.get(2)), Task.fromEntity(mockTaskEntities.get(1)));

        List<Task> actualTasks = tasksService.findAll("admin", UserRole.ADMIN.name(), TaskStatus.NONE.name());

        assertThat(expectedTasks.get(0), is(samePropertyValuesAs(actualTasks.get(0))));
        assertThat(expectedTasks.get(1), is(samePropertyValuesAs(actualTasks.get(1))));
        assertThat(expectedTasks.get(2), is(samePropertyValuesAs(actualTasks.get(2))));
    }

    @Test
    @DisplayName("Call findAll method for User (regular user)")
    public void findAllForRegularUser() {
        LocalDateTime date = LocalDateTime.now();

        List<TaskEntity> mockTaskEntities = Arrays.asList(
                new TaskEntity("user", 1, "title1", "desc1", "completed", date, date),
                new TaskEntity("user", 2, "title2", "desc2", "completed", date, date));

        when(mockedTasksRepository.findAllByUserId("user")).thenReturn(mockTaskEntities);

        List<Task> expectedTasks = Arrays.asList(Task.fromEntity(mockTaskEntities.get(0)),
                Task.fromEntity(mockTaskEntities.get(1)));

        List<Task> actualTasks = tasksService.findAll("user", UserRole.USER.name(), TaskStatus.COMPLETED.name());

        assertThat(expectedTasks.get(0), is(samePropertyValuesAs(actualTasks.get(0))));
        assertThat(expectedTasks.get(1), is(samePropertyValuesAs(actualTasks.get(1))));
    }

    @Test
    @DisplayName("create a task")
    public void createTask() {

        Task newTask = new Task("user", 1, "title1", "description1", TaskStatus.PENDING.name());

        when(mockedTasksRepository.findById(any())).thenReturn(Optional.empty());
        when(mockedTasksRepository.save(any(TaskEntity.class))).thenReturn(null);

        Task createdTask = tasksService.createOrUpdate(newTask);

        assertThat(newTask, is(samePropertyValuesAs(createdTask)));
    }

    @Test
    @DisplayName("update a task")
    public void updateTask() {
        LocalDateTime date = LocalDateTime.now();
        TaskEntity stubTaskEntitiy = new TaskEntity("admin", 1, "title1", "desc1", "pending", date, date);
        Task newTask = new Task("user", 1, "title1", "description1", TaskStatus.COMPLETED.name());

        when(mockedTasksRepository.findById(any())).thenReturn(Optional.of(stubTaskEntitiy));
        when(mockedTasksRepository.save(any(TaskEntity.class))).thenReturn(null);

        Task createdTask = tasksService.createOrUpdate(newTask);

        assertThat(newTask, is(samePropertyValuesAs(createdTask)));
    }

    @Test
    @DisplayName("delete a task")
    public void deleteTask() {
        doNothing().when(mockedTasksRepository).deleteById(any(TaskEntityKey.class));
        tasksService.deleteById("user", 1);
        
        verify(mockedTasksRepository).deleteById(any(TaskEntityKey.class));
    }
}
