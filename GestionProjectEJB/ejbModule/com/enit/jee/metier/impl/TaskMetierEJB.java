package com.enit.jee.metier.impl;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import com.enit.jee.entity.Task;
import com.enit.jee.metier.TaskMetier;

@Stateless(name="task")
public class TaskMetierEJB implements TaskMetier {

    @PersistenceContext(unitName = "projectmanagerUnit")
    EntityManager em;

    @Override
    public Task viewTask(String code) {
        Task t = em.find(Task.class, code);       
        return  t;
    }

    @Override
    public void updateTask(Task t) {
    	em.merge(t);
    }

    @Override
    public List<Task> listTask() {
        String jpql = "select t from Task t join fetch t.project";
        TypedQuery<Task> query = em.createQuery(jpql, Task.class);
        return query.getResultList();
    }

}