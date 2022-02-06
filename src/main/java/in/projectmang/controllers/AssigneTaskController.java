package in.projectmang.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.projectmang.dao.TaskDao;
import in.projectmang.models.Project;
import in.projectmang.models.Task;
import in.projectmang.models.User;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/assignetask")
public class AssigneTaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		String nextPage = "index.jsp";
		User user = (User) session.getAttribute("user");

		if (user != null) {

			nextPage = "loadprojects";

			Integer projectId = Integer.parseInt(request.getParameter("project_id"));
			Integer employeeId = Integer.parseInt(request.getParameter("employee_id"));
			String taskTitle = request.getParameter("task_title");
			String instruction = request.getParameter("instruction");
			Integer totalHours = Integer.parseInt(request.getParameter("work_hours"));
			String msg = "";

			TaskDao taskDao = new TaskDao();
			Task task = new Task();
			User employee = new User();
			Project project = new Project();

			employee.setId(employeeId);
			project.setId(projectId);
			task.setEmployee(employee);
			task.setProject(project);
			task.setTaskTitle(taskTitle);
			task.setInstruction(instruction);
			task.setTotalHours(totalHours);

			if (taskDao.addTaskAssign(task)) {
				nextPage = "loadprojects";
				msg = "Successfuly Task Assigned!";
			}

			session.setAttribute("taskmsg", msg);
			response.sendRedirect(nextPage);
		} else {
			request.setAttribute("msg", "Session is Expired Please Login Again!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}
	}
}
