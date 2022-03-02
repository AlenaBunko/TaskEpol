package com.example.taskEpol.service;

import com.example.taskEpol.exceptions.ElementOfTaskNotFoundExeprion;
import com.example.taskEpol.model.ElementOfTask;
import com.example.taskEpol.model.Task;
import com.example.taskEpol.repository.ElementOfTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ElementOfTaskService {

    private ElementOfTaskRepository elementOfTaskRepository;

    @Autowired
    public ElementOfTaskService(ElementOfTaskRepository elementOfTaskRepository) {
        this.elementOfTaskRepository = elementOfTaskRepository;
    }

    public ElementOfTask createElementOfTask(String name, String description, String value, Task task) {
        ElementOfTask elementOfTask = new ElementOfTask();
        elementOfTask.setName(name);
        elementOfTask.setDescription(description);
        elementOfTask.setValue(value);
        elementOfTask.setTask(task);
        elementOfTaskRepository.createElement(elementOfTask);
        return elementOfTask;
    }

    void updateElement(int id, ElementOfTask elementOfTask) throws ElementOfTaskNotFoundExeprion {
        Optional<ElementOfTask> optionalElementOfTask = elementOfTaskRepository.findById(id);
        if (optionalElementOfTask.isPresent()) {
            ElementOfTask element = optionalElementOfTask.get();
            element.setName(elementOfTask.getName());
            element.setDescription(elementOfTask.getValue());
        } else throw new ElementOfTaskNotFoundExeprion();
    }

    List<ElementOfTask> findAllElements() {
        return elementOfTaskRepository.findAllElements();
    }

    void deleteElement(int id) {
        elementOfTaskRepository.deleteElement(id);
    }

    List<ElementOfTask> findElementByValue(String value) {
        List<ElementOfTask> list = new ArrayList<>(elementOfTaskRepository.findElementByValue(value));
        return list;
    }

    List<ElementOfTask> findElementByIdTask(Task task) {
        List<ElementOfTask> list = new ArrayList<>(elementOfTaskRepository.findElementsByIdTask(task));
        return list;
    }
}
