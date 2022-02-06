package in.projectmang.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.projectmang.dao.ProjectDao;
import in.projectmang.dao.TaskDao;
import in.projectmang.dao.UserDao;
import in.projectmang.models.Project;
import in.projectmang.models.Task;
import in.projectmang.models.User;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/loadprojects")
public class ProjectManagerPageLoadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		String nextPage = "index.jsp";
		User user = (User) session.getAttribute("user");

		if (user != null) {

			Integer projectMangId = user.getId();

			nextPage = "mdashboard.jsp";

			ArrayList<Project> allProjects = ProjectDao.getManagerProjects(projectMangId);

			ArrayList<User> allEmployees = UserDao.getExternalEmployees();

			ArrayList<Task> allTasks = TaskDao.getAllTask(projectMangId);

			session.setAttribute("allProjects", allProjects);
			session.setAttribute("allEmployees", allEmployees);
			session.setAttribute("allTasks", allTasks);

			response.sendRedirect(nextPage);

		} else {
			request.setAttribute("msg", "Session is Expired Please Login Again!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}

	}
}
