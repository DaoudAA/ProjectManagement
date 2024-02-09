package com.enit.jee.metier.impl;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;

import com.enit.jee.entity.Project;
import com.enit.jee.entity.Task;
import com.enit.jee.metier.ProjetMetier;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless(name="project")
public class ProjectMetierEJB implements ProjetMetier {

    @PersistenceContext(unitName = "projectmanagerUnit")
    EntityManager em;

    @Override
    public void addProject(Project p) {
        em.persist(p);
    }

    @Override
    public Project viewProject(String code) {
        Project project = em.find(Project.class, code);
        return project;
    }



	@Override
    public List<Project> listProjects() {
        String jpql = "select p from Project p";
        Query query = em.createQuery(jpql);
        
        		List<Project> projects= (List<Project>)query.getResultList();
        return projects;
    }

    @Override
    public void updateProject(Project p) {
    	em.merge(p);
    }

    @Override
    public void addTaskToProject(Task t, String code) {
        Project project = em.find(Project.class, code);
        if (project != null) {
        	Collection<Task> tasks = project.getTasks();
            if (tasks == null) {
                tasks = new ArrayList<>();
                project.setTasks(tasks);
            }

            t.setProject(project);
            tasks.add(t);

            em.persist(t);
        }
    }

    @Override
    public void removeTaskToProject(Task t, String code) {
        Project p = em.find(Project.class, code);
        if (p != null) {
            Collection<Task> tasks = p.getTasks();
            
            if (tasks.contains(t)) {
                Task tt = em.find(Task.class, t.getCode());
                
                if (tt != null) {
                    tasks.remove(tt);
                    em.merge(p);
                    em.remove(tt);
                }
            }
        }
    }
    @Override
    public void deleteProject(String code) {
       /* String jpql = "SELECT p FROM Project p WHERE p.code = :code";
        Query query = em.createQuery(jpql);
        query.setParameter("code", code);
        Project p = (Project) query.getSingleResult();*/
        Project p = viewProject(code);
        em.remove(p);
    }
    
    
}