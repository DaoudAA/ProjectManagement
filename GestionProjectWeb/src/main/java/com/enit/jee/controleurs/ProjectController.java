 package com.enit.jee.controleurs;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.enit.jee.entity.Project;
import com.enit.jee.metier.ProjetMetier;
import com.enit.jee.model.ProjectModel;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ControleurServlet
 */
@WebServlet("/ProjectServlet")
public class ProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
	private ProjetMetier projet;

	@Override
	public void init() throws ServletException {
	}
    public ProjectController() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProjectModel pmodel = new ProjectModel();
		request.setAttribute("pmodel", pmodel);
		String action = request.getParameter("action");
		 if (action == null) {
			 editProject(request, response);
	        } else {
	            switch (action) {
	                case "list":
	                    listProjects(request, response);
	                    break;
	                case "edit":
	                    editProject(request, response);
	                    break;
	                case "add":
	                    addOrUpdateProject(request, response, "add");
	                    break;
	                case "update":
	                    addOrUpdateProject(request, response, "update");
	                    break;
	                case "remove":
	                    removeProject(request, response);
	                    break;
	                default:
	                	editProject(request, response);
	            }
	        }
	}
	  private void listProjects(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        List<Project> projects = projet.listProjects();
	        ProjectModel pmodel = (ProjectModel) request.getAttribute("pmodel");
	        pmodel.setProjects(projects);

	        request.getRequestDispatcher("/WEB-INF/listProjects.jsp").forward(request, response);
	    }
	  private void editProject(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException {
		    String code = request.getParameter("code");
		    ProjectModel pmodel = new ProjectModel();

		    if (code != null) {
		        Project project = projet.viewProject(code);

		        pmodel.setCode(project.getCode());
		        pmodel.setDescription(project.getDescription());
		        pmodel.setStartdate(project.getStartDate());
		        pmodel.setMode("edit");
		    } else {
		        pmodel.setMode("add"); 
		    }

		    request.setAttribute("pmodel", pmodel);
		    request.getRequestDispatcher("/WEB-INF/project.jsp").forward(request, response);
		}
	  private void addOrUpdateProject(HttpServletRequest request, HttpServletResponse response, String mode)
	            throws ServletException, IOException {
	        ProjectModel pmodel = (ProjectModel) request.getAttribute("pmodel");

	        pmodel.setCode(request.getParameter("code"));
	        pmodel.setDescription(request.getParameter("description"));
	        String startDateStr = request.getParameter("startdate");
	        if (startDateStr != null && !startDateStr.isEmpty()) {
	        	try {
	                Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
	                pmodel.setStartdate(startDate);
	            } catch (ParseException e) {

	                e.printStackTrace(); 
	            }
	        }

	        try {
	            Project project = new Project();
	            project.setCode(pmodel.getCode());
	            project.setDescription(pmodel.getDescription());
	            project.setStartDate(pmodel.getStartdate());

	            if ("update".equals(mode)) {
	                projet.updateProject(project);
	                request.setAttribute("successMessage", "Project updated successfully");
	                request.getRequestDispatcher("/WEB-INF/SuccessUpdateProject.jsp").forward(request, response);
	            } else {
	                projet.addProject(project);
	                request.setAttribute("successMessage", "Project added successfully");
	                request.getRequestDispatcher("/WEB-INF/SuccessInsertProject.jsp").forward(request, response);

	            }

	        } catch (Exception e) {
	            pmodel.setErrors(e.getMessage());
	            if ("update".equals(mode)) {
	                request.getRequestDispatcher("/WEB-INF/FailedUpdateProject.jsp").forward(request, response);
	            } else {
	                request.getRequestDispatcher("/WEB-INF/FailedInsertProject.jsp").forward(request, response);
	            }
	            }
	    }
	  private void removeProject(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException {
		    ProjectModel pmodel = (ProjectModel) request.getAttribute("pmodel");

		    pmodel.setCode(request.getParameter("code"));

		    try {

		        projet.deleteProject(pmodel.getCode());
		        request.setAttribute("successMessage", "Project removed successfully");
		        request.getRequestDispatcher("/WEB-INF/SuccessRemoveProject.jsp").forward(request, response);
		    } catch (Exception e) {
		        pmodel.setErrors(e.getMessage());
		        request.getRequestDispatcher("/WEB-INF/FailedRemoveProject.jsp").forward(request, response);
		    }

		}
	}