package in.projectmang.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.projectmang.dao.RoleDao;
import in.projectmang.dao.UserDao;
import in.projectmang.models.Role;
import in.projectmang.models.User;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/admindashboard")
public class AdminPageLoadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		String nextPage = "adashboard.jsp";
		
		ArrayList<User> allEmployees = UserDao.getAllEmployees();
		session.setAttribute("allEmployees", allEmployees);
		
		ArrayList<Role> allRoles = RoleDao.getAllRoles();
		session.setAttribute("allRoles", allRoles);
		
		request.getRequestDispatcher(nextPage).forward(request, response);
		
	}
}
