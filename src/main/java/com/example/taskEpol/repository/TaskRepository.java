package com.example.taskEpol.repository;

import com.example.taskEpol.model.Task;

import java.util.Date;
import java.util.List;

public interface TaskRepository {

    void createOrUpdateTask(Task task);

    void deleteTask(String name);

    List<Task> findAllTasks();

    List<Task> findByParameters( String name, Integer status, Date start_date, Date end_date);

}
