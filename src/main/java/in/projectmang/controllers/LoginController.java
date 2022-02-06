package in.projectmang.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.projectmang.dao.UserDao;
import in.projectmang.models.User;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		String nextPage = "index.jsp";

		String email = request.getParameter("email_id");
		String password = request.getParameter("password");
		String msg = "";
		boolean flag = true;

		if (email.trim() == null) {
			flag = false;
			msg += "Email is Meditary ";
		}

		if (password.trim().length() <= 6 || password.length() > 12) {
			flag = false;
			msg += "Password should be Greater than 6 and Less than 12 ";
		}

		if (flag) {
			UserDao userDao = new UserDao();
			User user = new User(email, password);

			if (userDao.loginUser(user)) {
				session.setAttribute("user", user);

				
				if (user.getRole().getId() == 2) {// 2 for Project Manager
					nextPage = "loadprojects";
				} else if (user.getRole().getId() == 1) {// 1 Admin
					nextPage = "admindashboard";
				} else if (user.getRole().getId() == 3) {// 3 HR
					nextPage = "hrdashboard";
				} else if (user.getRole().getId() == 4) {// 4 IT Admin
					nextPage = "itadmindashboard";
				} else {
					nextPage = "employeepage";
				}
				response.sendRedirect(nextPage);
			} else {
				System.out.println("Please Check Your ID & Password");
				msg += "Please Check Your ID & Password";
				request.setAttribute("msg", msg);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
				requestDispatcher.forward(request, response);
			}

		}else {
			request.setAttribute("msg", msg);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}

		
	}
}
