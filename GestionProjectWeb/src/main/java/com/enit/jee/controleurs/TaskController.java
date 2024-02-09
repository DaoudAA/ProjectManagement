package com.enit.jee.controleurs;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.enit.jee.metier.TaskMetier;
import com.enit.jee.metier.ProjetMetier;
import com.enit.jee.entity.Project;
import com.enit.jee.entity.Task;
import com.enit.jee.model.ProjectModel;
import com.enit.jee.model.TaskModel;
/**
 * Servlet implementation class TaskController
 */
@WebServlet("/TaskServlet")
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    private TaskMetier taskejb;
    @EJB
    private ProjetMetier projet;
    
    public TaskController() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TaskModel tmodel = new TaskModel();
		request.setAttribute("tmodel", tmodel);
		ProjectModel pmodel = new ProjectModel();
		List<Project> projects = projet.listProjects();
        pmodel.setProjects(projects);
        request.setAttribute("pmodel", pmodel);
		String action = request.getParameter("action");
		if (action == null) {
			editTask(request, response);
        } else {
            switch (action) {
                case "list":
                    listTasks(request, response);
                    break;
                case "edit":
                    editTask(request, response);
                    break;
                case "add":
                	addOrUpdateTask(request, response,"add");
                    break;
                case "remove":
                    removeTask(request, response);
                    break;
                case "update":
                	addOrUpdateTask(request, response,"update");
                    break;
                default:
                	editTask(request, response);
            }
        }
    }
	private void listTasks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Task> tasks = taskejb.listTask();
        TaskModel tmodel = (TaskModel) request.getAttribute("tmodel");
        tmodel.setTasks(tasks);

        request.getRequestDispatcher("/WEB-INF/listTasks.jsp").forward(request, response);
    }
	private void editTask(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String code = request.getParameter("code");
	    TaskModel tmodel = new TaskModel();

	    if (code != null) {
	        Task task = taskejb.viewTask(code);
	        
	        tmodel.setCode(task.getCode());
	        tmodel.setDescription(task.getDescription());
	        tmodel.setStartdate(task.getStartDate());
	        tmodel.setEnddate(task.getEndDate());
	        tmodel.setProject(task.getProject());
	        tmodel.setMode("edit"); 
	    } else {
	        tmodel.setMode("add");
	    }
	    request.setAttribute("tmodel", tmodel);
	    request.getRequestDispatcher("/WEB-INF/Task.jsp").forward(request, response);
	}
	private void addOrUpdateTask(HttpServletRequest request, HttpServletResponse response, String mode)
            throws ServletException, IOException {
        TaskModel tmodel = (TaskModel) request.getAttribute("tmodel");

        tmodel.setCode(request.getParameter("code"));
        tmodel.setDescription(request.getParameter("description"));
        tmodel.setProject(projet.viewProject(request.getParameter("projectcode")));
        String startDateStr = request.getParameter("startdate");
        if (startDateStr != null && !startDateStr.isEmpty()) {
            try {
                Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
                tmodel.setStartdate(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        String endDateStr = request.getParameter("enddate");
        if (endDateStr != null && !endDateStr.isEmpty()) {
            try {
                Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
                tmodel.setEnddate(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        try {
            Task task = new Task();
            task.setCode(tmodel.getCode());
            task.setDescription(tmodel.getDescription());
            task.setStartDate(tmodel.getStartdate());
            task.setEndDate(tmodel.getEnddate());
            task.setProject(tmodel.getProject());
            if ("update".equals(mode)) {
                taskejb.updateTask(task);
                request.setAttribute("successMessage", "Task updated successfully");
                request.getRequestDispatcher("/WEB-INF/SuccessUpdateTask.jsp").forward(request, response);
            } else {
                projet.addTaskToProject(task, task.getProject().getCode());
                request.setAttribute("successMessage", "Task added successfully");
                request.getRequestDispatcher("/WEB-INF/SuccessInsertTask.jsp").forward(request, response);
            }

        } catch (Exception e) {
        	if ("update".equals(mode)) {
                request.getRequestDispatcher("/WEB-INF/FailedUpdateTask.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/FailedInsertTask.jsp").forward(request, response);
            }
        }
    }
	private void removeTask(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    TaskModel tmodel = (TaskModel) request.getAttribute("tmodel");
	    tmodel.setCode(request.getParameter("code"));
	    Task t = taskejb.viewTask(tmodel.getCode());

	    try {
	        if (t != null) {
	        	Project p = t.getProject();
	            projet.removeTaskToProject(t, p.getCode());

	            request.setAttribute("successMessage", "Task removed successfully");
	            request.getRequestDispatcher("/WEB-INF/SuccessRemoveTask.jsp").forward(request, response);

	        } else {
	            tmodel.setErrors("Task not found");
	            request.getRequestDispatcher("/WEB-INF/FailedRemoveTask.jsp").forward(request, response);

	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        tmodel.setErrors("Error removing task: " + e.getMessage());
	        request.getRequestDispatcher("/WEB-INF/FailedRemoveTask.jsp").forward(request, response);
	    }

	}
	}

