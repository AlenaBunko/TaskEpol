package com.example.taskEpol.controllers;

import com.example.taskEpol.model.Task;
import com.example.taskEpol.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/findAll")
    public List<Task> listData() {
        return taskService.findAllTask();
    }

    @PostMapping("/addTask")
    public Task saveTask(@RequestBody Task task) {
        return taskService.createTask(task.getName(), task.getDescription(), task.getStart_date(), task.getEnd_date(), task.getStatus());
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteUsers(@PathVariable String name) {
        taskService.deleteTask(name);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/findByName")
    public List<Task> findByName(@RequestParam String name) {
        return taskService.findByName(name);
    }
}
