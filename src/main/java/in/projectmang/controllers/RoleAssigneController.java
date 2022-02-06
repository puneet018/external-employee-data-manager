package in.projectmang.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.projectmang.dao.UserDao;
import in.projectmang.models.Role;
import in.projectmang.models.User;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/roleassign")
public class RoleAssigneController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		String nextPage = "index.jsp";
		User user = (User) session.getAttribute("user");

		if (user != null) {

			nextPage = "admindashboard";

			Integer employeeId = Integer.parseInt(request.getParameter("emplyoeeId"));
			Integer roleId = Integer.parseInt(request.getParameter("role_id"));
			String msg = "";

			UserDao userDao = new UserDao();
			User employeeUser = new User();
			employeeUser.setId(employeeId);
			employeeUser.setRole(new Role(roleId));

			if (userDao.roleAssign(employeeUser)) {

				msg = "Successfuly Changed!";
			} else {

				System.out.println("Not Change Successfuly");
				msg += "Not Change Successfuly";
			}

			session.setAttribute("assignmsg", msg);
			response.sendRedirect(nextPage);
		} else {
			request.setAttribute("msg", "Session is Expired Please Login Again!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}
	}
}
