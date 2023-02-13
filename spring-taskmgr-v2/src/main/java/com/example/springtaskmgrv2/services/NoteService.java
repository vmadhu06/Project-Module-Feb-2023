package com.example.springtaskmgrv2.services;

import com.example.springtaskmgrv2.entities.NoteEntity;
import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.repositories.NotesRepository;
import com.example.springtaskmgrv2.repositories.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class NoteService {
    NotesRepository notesRepository;
    TasksRepository tasksRepository;
    public NoteService(NotesRepository notesRepository, TasksRepository tasksRepository){
        this.notesRepository = notesRepository;
        this.tasksRepository = tasksRepository;
    }

    public static class NoteNotFoundException extends  IllegalArgumentException{
        public NoteNotFoundException(Integer id){
            super("Note with id "+id+" not found");
        }
    }

    public static class TaskNotFoundException extends  IllegalArgumentException{
        public TaskNotFoundException(Integer id){
            super("Task with id "+id+" not found");
        }
    }

    public List<NoteEntity> getNotesForTaskId(Integer id){
       return  notesRepository.findNotesByTasksId(id);
    }

    public NoteEntity createNotesForTaskId(Integer id, NoteEntity noteEntity){
        Optional<TaskEntity> tasks = tasksRepository.findById(id);
        if(!tasks.isPresent()){
            throw new TaskNotFoundException(id);
        }
        TaskEntity task = tasks.get();
        noteEntity.setTask(task);
        return notesRepository.save(noteEntity);
    }

    public NoteEntity deleteNotes(Integer id){
        Optional<NoteEntity> note = notesRepository.findById(id);
        if(!note.isPresent()){
            throw new NoteNotFoundException(id);
        }
        notesRepository.delete(note.get());
        return note.get();
    }

}
