package com.example.taskEpol.repository.impl;

import com.example.taskEpol.model.Task;
import com.example.taskEpol.repository.TaskRepository;
import org.hibernate.Session;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public void createOrUpdateTask(Task task) {
        getSession().saveOrUpdate(task);
    }

    @Override
    public void deleteTask(String name) {
        Query query = getSession().createQuery("delete Task where name = '" + name + "'");
        query.executeUpdate();
    }

    @Override
    public List<Task> findAllTasks() {
        List<Task> result = getSession().createQuery("from Task", Task.class).getResultList();
        return result;
    }

    @Override
    public List<Task> findByParameters(@Param("name") String name, @Param("status") Integer status,@Param("start_date") Date start_date,@Param("end_date") Date end_date) {
       Query query= getSession().createQuery("from Task where name = :name or status = :status or start_date >= :start_date or end_date <= :end_date");
        List<Task> list = query.getResultList();
        return list;
    }

}
