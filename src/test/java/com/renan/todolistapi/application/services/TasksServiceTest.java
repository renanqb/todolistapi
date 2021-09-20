// package com.renan.todolistapi.application.services;

// import static org.mockito.Mockito.when;

// import java.time.LocalDateTime;
// import java.util.Arrays;
// import java.util.List;

// import com.renan.todolistapi.adapters.repositories.TasksRepository;
// import com.renan.todolistapi.adapters.repositories.entities.TaskEntity;
// import com.renan.todolistapi.application.domain.Task;
// import com.renan.todolistapi.application.domain.UserRole;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.springframework.beans.factory.annotation.Autowired;


// public class TasksServiceTest {

//     @Autowired TasksService tasksService;
//     @Mock TasksRepository tasksRepository;

//     @Test
//     @DisplayName("Call findAll method For Admin (super user)")
//     public void findAllForAdminUser() {
//         LocalDateTime date = LocalDateTime.now();

//         List<TaskEntity> mockTaskEntities = Arrays.asList(
//             new TaskEntity("admin", 1, "title1", "desc1", "pending", date, date),
//             new TaskEntity("admin", 2, "title2", "desc2", "completed", date, date),
//             new TaskEntity("user", 1, "title1", "desc1", "pending", date, date)
//         );

//         when(tasksRepository.findAll()).thenReturn(Arrays.asList(mockTaskEntities.get(0), mockTaskEntities.get(1), mockTaskEntities.get(2)));

//         List<Task> expectedTasks =  Arrays.asList(
//             Task.fromEntity(mockTaskEntities.get(0)),
//             Task.fromEntity(mockTaskEntities.get(3)),
//             Task.fromEntity(mockTaskEntities.get(2))
//         );

//         List<Task> actualTasks = tasksService.findAll("admin", UserRole.ADMIN.name(), UserRole.NONE.name());

//         Assertions.assertEquals(expectedTasks, actualTasks);
//     }
// }
