package com.example.springtaskmgrv2.services;

import com.example.springtaskmgrv2.entities.NoteEntity;
import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.repositories.NotesRepository;
import com.example.springtaskmgrv2.repositories.TasksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;


@DataJpaTest
@ComponentScan(basePackages = {"com.example.springtaskmgrv2.services"})
public class NoteServiceTest {
    @Autowired
    NoteService noteService;
    @Autowired
    NotesRepository notesRepository;

    @Autowired
    TasksRepository tasksRepository;

    @Test
    public void getNotesForTaskId() {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setBody("test for note 1");
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle("Test Task");
        taskEntity.setCompleted(false);
        taskEntity.setDescription("Test Description");
        noteEntity.setTask(taskEntity);
        TaskEntity task = tasksRepository.save(taskEntity);
        Integer id = task.getId();
        noteEntity.setTask(task);
        notesRepository.save(noteEntity);
        Assertions.assertEquals(1,  noteService.getNotesForTaskId(id).size());
    }
}
