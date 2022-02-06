package in.projectmang.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.projectmang.dao.EmployeeActivityDao;
import in.projectmang.dao.TaskDao;
import in.projectmang.models.EmployeeActivity;
import in.projectmang.models.Task;
import in.projectmang.models.User;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/submitaskhours")
public class SubmitTaskHourController extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		String nextPage = "index.jsp";
		User user = (User) session.getAttribute("user");

		if (user != null) {
			nextPage = "employeepage";

			Integer hours = Integer.parseInt(request.getParameter("hours"));
			Integer taskId = Integer.parseInt(request.getParameter("task_id"));
			Integer totalHoursCompleted = Integer.parseInt(request.getParameter("total_hours_completed"));
			String msg = "";

			Task task = new Task();
			task.setId(taskId);

			EmployeeActivity activity = new EmployeeActivity();
			activity.setHours(hours);
			activity.setTask(task);

			if (EmployeeActivityDao.submitHours(activity)) {

				task.setTotalHoursCompleted(totalHoursCompleted + hours);
				System.out.println(totalHoursCompleted + hours);
				TaskDao.submitHours(task);

				msg = "Successfuly submit Task";
			} else {
				msg += "Not submit Task Successfuly";
			}

			session.setAttribute("tasksubmsg", msg);
			response.sendRedirect(nextPage);
		} else {
			request.setAttribute("msg", "Session is Expired Please Login Again!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}
	}
}
