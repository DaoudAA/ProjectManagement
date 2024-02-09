package com.enit.jee.metier;
import java.util.List;

import com.enit.jee.entity.Task;

import jakarta.ejb.Local;
@Local
public interface TaskMetier {
	public Task viewTask(String code);
	public void updateTask(Task t);
	public List<Task> listTask();
}
