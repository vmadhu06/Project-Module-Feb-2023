package com.example.springtaskmgrv2.services;

import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.repositories.TasksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;


@DataJpaTest
@ComponentScan(basePackages = {"com.example.springtaskmgrv2.services"})
public class TaskServiceTest {

    @Autowired
    TasksService tasksService;

    @Autowired
    TasksRepository tasksRepository;

    @Test
    public void deleteTaskTest(){
        TaskEntity task1 = new TaskEntity();
        task1.setTitle("Test Task");
        task1.setDescription("Test Description");
        task1.setCompleted(false);
        TaskEntity taskEntity = tasksRepository.save(task1);
        TaskEntity task = tasksService.deleteTask(taskEntity.getId());
        Assertions.assertNotNull(task);

    }
}
