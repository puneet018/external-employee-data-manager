<!DOCTYPE html>
<html lang="en">
<%@ page isELIgnored="false"
	import="in.projectmang.models.*,java.util.*,java.sql.*,java.text.*"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Employees Dashboard</title>
<link rel="stylesheet" href="css/bootstrap.min.css" media="screen">
<link rel="stylesheet" href="css/style.css" media="screen">
<script src="js/employee.js"></script>
</head>

<body class="top-navbar-fixed">

	<%
		User userObj = (User) session.getAttribute("user");
		ArrayList<Task> employeeTask = (ArrayList<Task>) session.getAttribute("employeetask");
	%>
	<div class="main-wrapper">
		<ul>
			<li><a href="logout">Logout</a></li>
			<li><span><h3>
						Employee Name -
						<%=userObj.getFullName()%></h3></span></li>
		</ul>
	</div>
	<div class="grid">
		<div class="container">
			<%
				String tasksubmsg = (String) session.getAttribute("tasksubmsg");
				if (!(tasksubmsg == null)) {
					session.setAttribute("tasksubmsg", null);
			%>
			<div class="msg"><%=tasksubmsg%></div>
			<%
				}
			%>

			<div class="heading">Task Assigned</div>

			<%
				if (!employeeTask.isEmpty()) {
					for (Task taskObj : employeeTask) {
			%>
			<%
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String todayDate = df.format((new java.util.Date()));
			%>

			<div class="task form">
				<!-- Task -->
				<div class="form-field">
					<label>Task Title</span></label> <span><%=taskObj.getTaskTitle()%></span>
				</div>
				<div class="form-field">
					<label>Task Instruction</span></label> <span><%=taskObj.getInstruction()%></span>
				</div>
				<div class="form-field">
					<label>Task Date</span></label> <span><%=taskObj.getTaskDate()%></span>
				</div>
				<div class="form-field">
					<label>Project Title</span></label> <span><%=taskObj.getProject().getTitle()%></span>
				</div>

				<form action="submitaskhours" method="post">
					<%
						if (taskObj.getTaskStatus().getId() == 1) { //1 is Active
					%>
					<input type="hidden" name="task_id" value="<%=taskObj.getId()%>" />
					<input type="hidden" name="total_hours_completed"
						value="<%=taskObj.getTotalHoursCompleted()%>" /> <input type="hidden"
						id="sub_date" value="<%=taskObj.getUpdatedAt()%>" />
					<%
						if (!todayDate.equals(taskObj.getUpdatedAt())) {
					%>
					<div class="form-field" id="hour_input_field">
						<label>Hours</span></label> <span><input
							style="width: 70px; height: 35px" name="hours" type="number"></span>
					</div>
					<div class="form-field" id="button_field">
						<label>Action</span></label>
						<button class="btn">Submit</button>
					</div>
					<%
						}
					%>
					<div class="form-field">
						<label>Status</span></label> <span><%=taskObj.getTaskStatus().getStatus()%></span>
					</div>
					<%
						} else {
					%>
					<div class="form-field">
						<label>Status</span></label> <span><%=taskObj.getTaskStatus().getStatus()%></span>
					</div>
					<%
						}
					%>
				</form>
			</div>
			<%
				}
				}
			%>
		</div>
	</div>

</body>

</html>