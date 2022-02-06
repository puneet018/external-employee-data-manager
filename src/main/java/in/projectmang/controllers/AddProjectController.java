package in.projectmang.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.projectmang.dao.ProjectDao;
import in.projectmang.models.Project;
import in.projectmang.models.User;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/addproject")
public class AddProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String nextPage = "index.jsp";
		User user = (User) session.getAttribute("user");

		if (user != null) {

			nextPage = "loadprojects";

			//Integer projectMgrId = Integer.parseInt(request.getParameter("project_mang_id"));
			String title = request.getParameter("project_title");
			String startDate = request.getParameter("start_date");
			String endDate = request.getParameter("end_date");
			Double budget = Double.parseDouble(request.getParameter("budget"));
			Integer deadline = Integer.parseInt(request.getParameter("deadline"));
			String description = request.getParameter("description");
			System.out.println(description);
			String msg = "";

			ProjectDao projectDao = new ProjectDao();
			Project project = new Project();
			
			//user.setId(projectMgrId);
			project.setProjectManager(user);
			project.setTitle(title);
			project.setStartDate(startDate);
			project.setEndDate(endDate);
			project.setBudget(budget);
			project.setDeadline(deadline);
			project.setDescription(description);

			if (projectDao.addProject(project)) {
				nextPage = "loadprojects";
				msg = "Successfuly Add Project!";
			} else {
				msg += "Not Add Project Successfuly";
			}

			session.setAttribute("promag", msg);
			response.sendRedirect(nextPage);
		} else {
			request.setAttribute("msg", "Session is Expired Please Login Again!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}

	}
}
