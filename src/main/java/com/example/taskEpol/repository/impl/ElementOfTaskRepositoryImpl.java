package com.example.taskEpol.repository.impl;

import com.example.taskEpol.model.ElementOfTask;
import com.example.taskEpol.model.Task;
import com.example.taskEpol.repository.ElementOfTaskRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Repository
public class ElementOfTaskRepositoryImpl implements ElementOfTaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createElement(ElementOfTask elementOfTask) {
        entityManager.persist(elementOfTask);
    }

    @Override
    public void deleteElement(Integer id) {
        Query query = entityManager.createQuery("delete ElementOfTask where id = '" + id + "'");
        query.executeUpdate();
    }

    @Override
    public void updateElement(ElementOfTask elementOfTask) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(elementOfTask);
    }

    @Override
    public List<ElementOfTask> findAllElements() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("FROM ElementOfTask");
        return query.getResultList();
    }

    @Override
    public Optional<ElementOfTask> findById(Integer id) {
        return Optional.of(entityManager.find(ElementOfTask.class, id));
    }

    @Override
    public List<ElementOfTask> findElementsByIdTask(Task task) {
        Query query = entityManager.createQuery("select e from ElementOfTask e inner join e.task = '" + task.getId() + "'");
        return query.getResultList();
    }

    @Override
    public List<ElementOfTask> findElementByValue(String value) {
        Query query = entityManager.createQuery("from ElementOfTask where value = '" + value + "'");
        return query.getResultList();
    }

    @Override
    public List<ElementOfTask> findTaskWithElements() throws IOException {
        Query query = entityManager.createQuery("select Task.name, e from ElementOfTask e inner join Task on Task.id=ElementOfTask.task_id");
        List<String> list = query.getResultList();
        String listTaskAndElements = String.join("", list);
        FileOutputStream fileOutputStream = new FileOutputStream("C\\...xlsx");
        fileOutputStream.write(listTaskAndElements.getBytes(StandardCharsets.UTF_8));
        fileOutputStream.close();
        return null;
    }
}
