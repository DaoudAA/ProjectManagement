package com.enit.jee.metier;
import java.util.List;
import com.enit.jee.entity.Project;
import com.enit.jee.entity.Task;


import jakarta.ejb.Local;
@Local
public interface ProjetMetier {
	public void addProject(Project p);
	public Project viewProject(String code);
	public List<Project> listProjects();
	public void updateProject(Project p);
	public void addTaskToProject(Task t, String code);
	public void removeTaskToProject(Task t, String code);
	public void deleteProject(String code);
}
