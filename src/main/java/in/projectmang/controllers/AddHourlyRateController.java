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
import in.projectmang.dao.ExternalEmployeeHourlyRateDao;
import in.projectmang.dao.ProjectDao;
import in.projectmang.dao.UserDao;
import in.projectmang.models.Cost;
import in.projectmang.models.CostType;
import in.projectmang.models.ExternalEmployeeHourlyCost;
import in.projectmang.models.Project;
import in.projectmang.models.User;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/addhourlyrate")
public class AddHourlyRateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		String nextPage = "index.jsp";
		User user = (User) session.getAttribute("user");

		if (user != null) {

			String pageSwitch = request.getParameter("page_switch");

			Integer exEmpId = Integer.parseInt(request.getParameter("ex_emp_id"));
			String description = request.getParameter("description");
			Double hourlyRate = Double.parseDouble(request.getParameter("hourly_rate"));

			String msg = "";

			ExternalEmployeeHourlyCost exEmployeeHourlyRate = new ExternalEmployeeHourlyCost();
			exEmployeeHourlyRate.setExternalEmployee(new User(exEmpId));
			exEmployeeHourlyRate.setUser(user);
			exEmployeeHourlyRate.setHourlyRate(hourlyRate);
			exEmployeeHourlyRate.setDescription(description);

			if (ExternalEmployeeHourlyRateDao.addCost(exEmployeeHourlyRate)) {
				msg = "Successfuly Add Hourly Rate!";
			} else {
				msg += "Not Add Hourly Rate Successfuly";
			}

			if (pageSwitch.equals("HRD")) {
				nextPage = "hrdashboard";
			} else {
				nextPage = "loadprojects";
			}

			session.setAttribute("hratemag", msg);
			response.sendRedirect(nextPage);
		} else {
			request.setAttribute("msg", "Session is Expired Please Login Again!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}

	}
}
