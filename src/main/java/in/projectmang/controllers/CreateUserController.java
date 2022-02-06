package in.projectmang.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.projectmang.dao.UserDao;
import in.projectmang.models.Role;
import in.projectmang.models.User;

@WebServlet(value = "/createuser")
public class CreateUserController extends HttpServlet {

	@SuppressWarnings("static-access")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		String nextPage = "index.jsp";
		User user = (User) session.getAttribute("user");

		if (user != null) {

			nextPage = "admindashboard";
			String msg = "";
			boolean flag = true;

			// get parameter
			Integer roleId = Integer.parseInt(request.getParameter("role_id"));
			String fullName = request.getParameter("full_name");
			String email = request.getParameter("email_id");
			String password = request.getParameter("password");
			String contactNum = request.getParameter("contact_number");

			// validation
			if (fullName.trim().equals("")) {
				flag = false;
				msg += "*Please Enter valid Name <br />";
			}

			if (email.trim().equals("")) {
				flag = false;
				msg += " *Please Enter valid Email <br />";
			}

			if (!(password.length() > 6 && password.length() < 12)) {
				flag = false;
				msg += " *passowrd should be greater then 6 and lessthen 12 <br />";
			}

			if (contactNum.length() != 10) {
				flag = false;
				msg += " *Please Enter valid Contact Number <br />";
			}
			if (flag) {
				User createUser = new User();
				createUser.setRole(new Role(roleId));
				createUser.setContactNum(contactNum);
				createUser.setEmailId(email);
				createUser.setFullName(fullName);
				createUser.setPassword(password);

				UserDao userDao = new UserDao();
				userDao.createUser(createUser);

				msg = "Successfuly Added";
			}

			session.setAttribute("createmsg", msg);
			response.sendRedirect(nextPage);

		} else {
			request.setAttribute("msg", "Session is Expired Please Login Again!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}
	}

}
