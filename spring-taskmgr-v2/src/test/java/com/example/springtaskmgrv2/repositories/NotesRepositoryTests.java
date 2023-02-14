package com.example.springtaskmgrv2.repositories;

import com.example.springtaskmgrv2.entities.NoteEntity;
import com.example.springtaskmgrv2.entities.TaskEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class NotesRepositoryTests {

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    TasksRepository tasksRepository;

    @Test
    public void findNotesByTasksIdTest(){
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setBody("test for note 1");
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle("Test Task");
        taskEntity.setCompleted(false);
        taskEntity.setDescription("Test Description");
        noteEntity.setTask(taskEntity);
        TaskEntity task = tasksRepository.save(taskEntity);
        noteEntity.setTask(task);
        notesRepository.save(noteEntity);
        Assertions.assertEquals(1, notesRepository.findNotesByTasksId(task.getId()).size());
    }


}
