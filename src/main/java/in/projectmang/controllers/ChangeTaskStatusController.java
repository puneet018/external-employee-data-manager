package in.projectmang.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.projectmang.dao.CostDao;
import in.projectmang.dao.ProjectDao;
import in.projectmang.dao.TaskDao;
import in.projectmang.dao.UserDao;
import in.projectmang.models.Cost;
import in.projectmang.models.CostType;
import in.projectmang.models.Project;
import in.projectmang.models.Task;
import in.projectmang.models.User;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/changetaskstatus")
public class ChangeTaskStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		String nextPage = "index.jsp";
		User user = (User) session.getAttribute("user");

		if (user != null) {

			Integer taskStatusId = Integer.parseInt(request.getParameter("task_status_id"));
			Integer taskId = Integer.parseInt(request.getParameter("task_id"));

			String msg = "";

			if (TaskDao.changeTaskStatus(taskId, taskStatusId)) {
				msg = "Task Closed";
			} else {
				msg += "Server Problem please try again later";
			}

			session.setAttribute("statusmag", msg);
			// response.sendRedirect(nextPage);

		} else {
			request.setAttribute("msg", "Session is Expired Please Login Again!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}

	}
}
