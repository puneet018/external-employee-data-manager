package in.projectmang.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.projectmang.dao.EmployeeActivityDao;
import in.projectmang.dao.TaskDao;
import in.projectmang.models.Task;
import in.projectmang.models.User;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/employeepage")
public class EmployeePageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		Integer employeeId = ((User) session.getAttribute("user")).getId();

		String nextPage = "index.jsp";
		User user = (User) session.getAttribute("user");

		if (user != null) {

			nextPage = "employee.jsp";

			ArrayList<Task> empTask = TaskDao.getEmployeeTask(employeeId);
			for (Task task : empTask) {

				if (!EmployeeActivityDao.checkActivity(task.getId())) {
					task.setUpdatedAt("0000-00-00");
				}
			}
			session.setAttribute("employeetask", empTask);

			response.sendRedirect(nextPage);

		} else {
			request.setAttribute("msg", "Session is Expired Please Login Again!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}
	}
}
