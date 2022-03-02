package com.example.taskEpol.service;

import com.example.taskEpol.model.Task;
import com.example.taskEpol.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(String name, String description, Date start_date, Date end_date, int status) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setStart_date(start_date);
        task.setEnd_date(end_date);
        task.setStatus(status);
        taskRepository.createOrUpdateTask(task);
        return task;
    }

    public List<Task> findAllTask() {
        return taskRepository.findAllTasks();
    }

    public List<Task> findByParameters( int status, Date start_date, Date end_date){
        List<Task> findByParameters = taskRepository.findByParameters(status, start_date, end_date);
        return findByParameters;
    }

    public void deleteTask(String name) {
        taskRepository.deleteTask(name);
    }

    public List<Task> findByName(String name) {
        List<Task> list = taskRepository.findByName(name);
        return  list;
    }
}
