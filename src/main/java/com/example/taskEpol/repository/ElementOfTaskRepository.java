package com.example.taskEpol.repository;

import com.example.taskEpol.model.ElementOfTask;
import com.example.taskEpol.model.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ElementOfTaskRepository {

    void createElement(ElementOfTask elementOfTask);

    void deleteElement (int id);

    void updateElement (ElementOfTask elementOfTask);

    List<ElementOfTask> findAllElements();

    Optional<ElementOfTask> findById(int id);

    List<ElementOfTask> findElementsByIdTask(Task task);

    List<ElementOfTask> findElementByValue(String value);

    List<ElementOfTask> findTaskWithElements() throws IOException;
}
