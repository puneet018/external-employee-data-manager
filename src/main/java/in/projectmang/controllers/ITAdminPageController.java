package in.projectmang.controllers;

import java.io.IOException;
import java.util.ArrayList;

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

@WebServlet(value = "/itadmindashboard")
public class ITAdminPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		String nextPage = "index.jsp";
		User user = (User) session.getAttribute("user");

		if (user != null) {

			nextPage = "itadmindashboard.jsp";

			ArrayList<Project> allProjects = ProjectDao.getAllProjects();
			session.setAttribute("allProjects", allProjects);

			response.sendRedirect(nextPage);
		} else {
			request.setAttribute("msg", "Session is Expired Please Login Again!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}
	}
}
