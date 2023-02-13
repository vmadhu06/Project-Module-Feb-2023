package com.example.springtaskmgrv2.controllers;

import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.services.TasksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping
    public ResponseEntity<List<TaskEntity>> getTasks(){
        return ResponseEntity.ok(tasksService.getTasks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Integer id){
        return ResponseEntity.ok(tasksService.getTask(id));
    }
    @PostMapping
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity task){
        TaskEntity newTask = tasksService.createTask(task);
        return ResponseEntity.created(URI.create("/tasks/"+newTask.getId())).body(newTask);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@RequestBody TaskEntity task, @PathVariable Integer id){
           TaskEntity newTask = tasksService.updateTask(task, id);
           return ResponseEntity.accepted().body(newTask);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskEntity> deleteTask(@PathVariable Integer id){
        TaskEntity newTask = tasksService.deleteTask(id);
        return ResponseEntity.accepted().body(newTask);
    }
    @GetMapping(params = "title")
   public ResponseEntity<List<TaskEntity>> getTaskByTitle(@RequestParam("title") String title){
      List<TaskEntity> tasks = tasksService.getAllTasksByGivenTitle(title);
      return ResponseEntity.ok(tasks);
   }
   @GetMapping(params = "completed")
   public ResponseEntity<List<TaskEntity>> getTasksByCompleted(@RequestParam("completed") Boolean completed){
        List<TaskEntity> tasks = tasksService.getAllTasksByCompletedStatus((completed));
        return ResponseEntity.ok(tasks);
   }

}
