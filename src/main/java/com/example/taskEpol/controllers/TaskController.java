package com.example.taskEpol.controllers;

import com.example.taskEpol.model.Task;
import com.example.taskEpol.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "mainPage";
    }

    @GetMapping("/addTask")
    public String newTask(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "addTask";
    }

    @PostMapping("/addTask")
    public String saveTask(@ModelAttribute("task") Task task) {
        Task newTask = taskService.createTask(task.getName(), task.getDescription(), task.getStart_date(), task.getEnd_date(), task.getStatus());
        List<Task> tasks = new ArrayList<>();
        tasks.add(newTask);
        return "redirect:/findAll";
    }

    @GetMapping("/findAll")
    public String allTasks(Model model) {
        List<Task> tasks = taskService.findAllTask();
        model.addAttribute("tasks", tasks);
        return "allTasks";
    }

    @GetMapping("/searchByParameters")
    public String taskListByParameters(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "status", required = false) Integer status, @RequestParam(name = "start_date", required = false) Date start_date, @RequestParam(name = "end_date", required = false) Date end_date, Model model) {
        List<Task> tasks = taskService.findByParameters(name, status, start_date, end_date);
        if (tasks == null){
            return "mainPage";
        }
        model.addAttribute("tasks", tasks);
        return "redirect:/findAll";
    }

    @RequestMapping("/delete/{name}")
    public String deleteTasks(@PathVariable(value = "name") String name) {
        taskService.deleteTask(name);
        return "allTasks";
    }

}
