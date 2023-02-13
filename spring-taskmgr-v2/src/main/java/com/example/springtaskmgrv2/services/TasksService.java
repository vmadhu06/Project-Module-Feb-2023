package com.example.springtaskmgrv2.services;

import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.repositories.NotesRepository;
import com.example.springtaskmgrv2.repositories.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
    final TasksRepository tasksRepository;

    final NotesRepository notesRepository;

    public static class TaskNotFoundException extends  IllegalArgumentException{
        public TaskNotFoundException(Integer id){
            super("Task with id "+id+" not found");
        }
    }

    public TasksService(TasksRepository tasksRepository, NotesRepository notesRepository) {
        this.tasksRepository = tasksRepository;
        this.notesRepository = notesRepository;
    }

    public List<TaskEntity> getTasks(){
        return tasksRepository.findAll();
    }

    public TaskEntity getTask(Integer id){
        Optional<TaskEntity> task = tasksRepository.findById(id);
        return task.get();
    }

    public TaskEntity createTask(TaskEntity taskEntity){
        return tasksRepository.save(taskEntity);
    }

    public TaskEntity updateTask(TaskEntity taskEntity, Integer id){
        Optional<TaskEntity> rtask = tasksRepository.findById(id);
        if(rtask.isEmpty()){
            throw new TaskNotFoundException(id);
        }
        TaskEntity task = rtask.get();

        if(taskEntity.getTitle()!=null){
            task.setTitle(taskEntity.getTitle());
        }
        if(taskEntity.getDescription()!=null){
            task.setDescription(taskEntity.getDescription());
        }
        if(taskEntity.getCompleted()!=null){
            task.setCompleted(taskEntity.getCompleted());
        }
        return tasksRepository.save(task);
    }

    public TaskEntity deleteTask(Integer id){
        Optional<TaskEntity> task = tasksRepository.findById(id);
        if(task.isEmpty()){
            throw new TaskNotFoundException(id);
        }
        tasksRepository.delete(task.get());
        return task.get();
    }

    public List<TaskEntity> getAllTasksByGivenTitle(String title){
       return tasksRepository.findAllByTitleContainingIgnoreCase(title);
    }

    public List<TaskEntity> getAllTasksByCompletedStatus(boolean status){
        return tasksRepository.findAllByCompleted(true);
    }
}
