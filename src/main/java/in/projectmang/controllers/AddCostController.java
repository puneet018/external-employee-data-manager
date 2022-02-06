package in.projectmang.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.projectmang.dao.CostDao;
import in.projectmang.models.Cost;
import in.projectmang.models.Project;
import in.projectmang.models.User;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/addcost")
public class AddCostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		String nextPage = "index.jsp";
		User user = (User) session.getAttribute("user");

		if (user != null) {

			String pageSwitch = request.getParameter("page_switch");

			Integer projectId = Integer.parseInt(request.getParameter("project_id"));
			String costTitle = request.getParameter("cost_title");
			String description = request.getParameter("description");
			Double costAmt = Double.parseDouble(request.getParameter("cost_amt"));

			String msg = "";

			Cost cost = new Cost();
			cost.setCostTitle(costTitle);
			cost.setUser(user);
			cost.setProject(new Project(projectId));
			cost.setDescription(description);
			cost.setCost(costAmt);

			if (CostDao.addCost(cost)) {
				msg = "Successfuly Add Project Cost!";
			} else {
				msg += "Not Add Project Cost Successfuly";
			}

			if (pageSwitch.equals("HRD")) {
				nextPage = "hrdashboard";
			} else if (pageSwitch.equals("ITAD")) {
				nextPage = "itadmindashboard";
			} else {
				nextPage = "loadprojects";
			}

			session.setAttribute("costmag", msg);
			response.sendRedirect(nextPage);
		}else {
			request.setAttribute("msg", "Session is Expired Please Login Again!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}
		

	}
}

