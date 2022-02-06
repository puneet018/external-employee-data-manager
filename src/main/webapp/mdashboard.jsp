<!DOCTYPE html>
<html lang="en">
<%@ page isELIgnored="false"
	import="in.projectmang.models.*,java.util.*,java.sql.*"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dashboard</title>
<link rel="stylesheet" href="css/bootstrap.min.css" media="screen">
<link rel="stylesheet" href="css/style.css" media="screen">
<script>
function changeTaskStatus(id){
	var http = new XMLHttpRequest();
	var url = 'changetaskstatus';
	var params = 'task_status_id=2&task_id='+id; //close status
	http.open('POST', url, true);

	//Send the proper header information along with the request
	http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

	http.onreadystatechange = function() {//Call a function when the state changes.
	    if(http.readyState == 4 && http.status == 200) {
	        //alert(http.responseText);
	    }
	}
	http.send(params);
	/* window.location.reload(); */
	window.location.href = "loadprojects"
}

function changeProStatus(id){

	var http = new XMLHttpRequest();
	var url = 'changeprojectstatus';
	var params = 'project_id='+id; //close status
	http.open('POST', url, true);

	//Send the proper header information along with the request
	http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

	http.onreadystatechange = function() {//Call a function when the state changes.
	    if(http.readyState == 4 && http.status == 200) {
	        alert("Successfuly Inactive");
	    }
	}
	http.send(params);
	/* window.location.reload(); */
	window.location.href = "loadprojects"
}

function getHours(){
	var deadline = document.getElementById("deadline");
	var start_date = document.getElementById("start_date");
	var end_date = document.getElementById("end_date");
	var t = new Date(end_date.value).valueOf() - new Date(start_date.value).valueOf();
	var delta = Math.abs(t) / 1000;
	var t1 = start_date.value;
	var t2 = end_date.value;

	// calculate (and subtract) whole days
	var days = Math.floor(delta / 86400);
	delta -= days * 86400;

	// calculate (and subtract) whole hours
	var hours = Math.floor(delta / 3600) % 24;
	delta -= hours * 3600;
	deadline.value = days*8
	console.log(t1+" "+days+" "+hours)
	
}
</script>
</head>

<body class="top-navbar-fixed">
	<%
		User userObj = (User) session.getAttribute("user");
		ArrayList<Project> allProjects = (ArrayList<Project>) session.getAttribute("allProjects");
		ArrayList<User> allEmployees = (ArrayList<User>) session.getAttribute("allEmployees");
		ArrayList<Task> allTasks = (ArrayList<Task>) session.getAttribute("allTasks");
	%>
	<div class="main-wrapper">
		<ul>
			<li><a href="logout">Logout</a></li>
			<li><span><h3>
						Project Manager -
						<%=userObj.getFullName()%></h3></span></li>
		</ul>
	</div>
	<div class="grid">
		<!-- <div class="container"> -->
		<div class="form">
			<!-- Add Project -->
			<div class="sign-in-section">
				<%
					String promag = (String) session.getAttribute("promag");
					if (!(promag == null)) {
						session.setAttribute("promag", null);
				%>
				<div class="msg"><%=promag%></div>
				<%
					}
				%>
				<h1>Add Project</h1>
				<form action="addproject" method="POST">
					<input type="hidden" name="project_mang_id"
						value="<%=userObj.getId()%>">
					<div class="form-field">
						<label>Project Title <span class="form-required"
							title="This field is required.">*</span></label> <input type="text"
							id="project_title" name="project_title" value="" size="60"
							maxlength="60" class="form-text required">
					</div>
					<div class="form-field">
						<label>Start Date <span class="form-required"
							title="This field is required.">*</span></label> <input type="Date"
							id="start_date" name="start_date" size="60" maxlength="128"
							class="form-text required">
					</div>
					<div class="form-field">
						<label>End Date <span class="form-required"
							title="This field is required.">*</span></label> <input onchange="getHours()" type="Date"
							id="end_date" name="end_date" size="60" maxlength="128"
							class="form-text required">
					</div>
					<div class="form-field">
						<label>Deadline (in hours)</label> <input type="text" id="deadline" name="deadline" size="60"
							maxlength="128" value="" placeholder="" class="form-text"><span class="position_set">Hrs.</span>
					</div>
					<div class="form-field">
						<label>Budget($)<span class="form-required"
							title="This field is required.">*</span></label> <input type="text"
							id="budget" name="budget" size="60" maxlength="128" value=""
							placeholder="0.00" class="form-text"><span class="position_set">$</span>
					</div>
					<div class="form-field">
						<label>Description</label>
						<textarea type="text" id="description" name="description"
							value="HGGH" size="" maxlength="60" class="form-text"></textarea>
					</div>
					<div class="form-field">
						<input type="submit" id="submit_form" class="btn btn-signin"
							value="Submit" />
					</div>
				</form>
			</div>
			<!-- List of Projects -->
			<div class="sign-in-section">
				<%
					String promsg = (String) session.getAttribute("promsg");
					if (!(promsg == null)) {
						session.setAttribute("promsg", null);
				%>
				<div class="msg"><%=promsg%></div>
				<%
					}
				%>
				<h1>List of Projects</h1>
				<div class="pro_list">
					<table>
						<tr>
							<th>#</th>
							<th>Title</th>
							<th>Description</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Budget</th>
							<th>Deadline</th>
							<th>Status</th>
						</tr>
						<%
							int i = 1;
							if (!allProjects.isEmpty()) {
								for (Project proObj : allProjects) {
									
						%>
						<tr>
							<td><%=i++%></td>
							<td><%=proObj.getTitle()%></td>
							<td><%=proObj.getDescription()%></td>
							<td><%=proObj.getStartDate()%></td>
							<td><%=proObj.getEndDate()%></td>
							<td><%=proObj.getBudget()%></td>
							<td><%=proObj.getDeadline()%>Hr.</td>

							<td><select onchange="changeProStatus(<%=proObj.getId() %>)" name="pro_status">
									<%
										if (proObj.getStatus()) {
									%>
									<option selected value="1">Active</option>
									<option value="0">InActive</option>
									<%
										} else {
									%>
									<option selected value="0">InActive</option>
									<%
										}
									%>
							</select></td>
						</tr>
						<%
							}
							} else {
						%>
						<tr>
							<td colspan="7">NO data Available</td>
						</tr>
						<%
							}
						%>
					</table>

				</div>
			</div>
		</div>
		<div class="form">
			<!-- Employees Task -->
			<div class="sign-in-section">
				<%
					String taskmsg = (String) session.getAttribute("taskmsg");
					if (!(taskmsg == null)) {
						session.setAttribute("taskmsg", null);
				%>
				<div class="msg"><%=taskmsg%></div>
				<%
					}
				%>
				<h1>Task Assign</h1>
				<form action="assignetask" method="POST">
					<input type="hidden" name="project_mang_id"
						value="<%=userObj.getId()%>">
					<div class="form-field">
						<label>Select Project <span class="form-required"
							title="This field is required.">*</span></label> <select
							name="project_id">
							<%
								for (Project proObj : allProjects) {
									
							%>
							<option value="<%=proObj.getId()%>" <%if(!proObj.getStatus()){%> disabled="disabled" <%}%> >
								<%=proObj.getTitle()%>

							</option>
							<%
								}
							%>
						
						</select>
					</div>
					<div class="form-field">
						<label>Task Title <span class="form-required"
							title="This field is required.">*</span></label> <input type="text"
							id="task_title" name="task_title" value="" size="60"
							maxlength="60" class="form-text required">
					</div>
					<div class="form-field">
						<label>Total Work Hours (in hours)</label> <input type="text" id="work_hours" name="work_hours" size="60"
							maxlength="128" value="" placeholder="" class="form-text"><span class="position_set">Hrs.</span>
					</div>
					<div class="form-field">
						<label>Instruction </label>
						<textarea id="instruction" name="instruction" value="" size=""
							maxlength="60" class="form-text"></textarea>
					</div>
					<div class="form-field">
						<label>Select Employee <span class="form-required"
							title="This field is required.">*</span></label><select
							name="employee_id">

							<%
								for (User empObj : allEmployees) {
							%>
							<option value="<%=empObj.getId()%>">
								<%=empObj.getFullName()%>

							</option>
							<%
								}
							%>

						</select>
					</div>
					<div class="form-field">
						<input type="submit" id="submit_login_form" class="btn btn-signin"
							value="Submit" />
					</div>
				</form>
			</div>
			<!-- List of Task Assigned -->
			<div class="sign-in-section">
				<h1>List of Task Assigned</h1>
				<div class="task_list">
					<table>
						<tr>
							<th>#</th>
							<th>Title</th>
							<th>Project</th>
							<th>Instructions</th>
							<th>Date</th>
							<th>Total Hours Assigned</th>
							<th>Total Hours Completed</th>
							<th>Employee Name</th>
							<th>Contact Number</th>
							<th>Status</th>
						</tr>

						<%
							int j = 1;
							if (!(allTasks.isEmpty())) {
								for (Task taskObj : allTasks) {
						%>
						<tr>
							<td><%=j++%></td>
							<td><%=taskObj.getTaskTitle()%></td>
							<td><%=taskObj.getProject().getTitle()%></td>
							<td><%=taskObj.getInstruction()%></td>
							<td><%=taskObj.getTaskDate()%></td>
							<td><%=taskObj.getTotalHours()%>Hrs.</td>
							<td><%=taskObj.getTotalHoursCompleted()%>Hrs.</td>
							<td><%=taskObj.getEmployee().getFullName()%></td>
							<td><%=taskObj.getEmployee().getContactNum()%></td>
							<td>
								<%
									if (taskObj.getTaskStatus().getId() == 1) {
								%>Active
								<button onclick="changeTaskStatus(<%=taskObj.getId()%>)">Close</button>
								<%
									} else {
								%> Close <%
									}
								%>
							</td>
						</tr>
						<%
							}
							} else {
						%>
						<tr>
							<td colspan="5">NO data Available</td>
						</tr>
						<%
							}
						%>
					</table>

				</div>
			</div>
		</div>
		<div class="form">
			<!-- Add Rate -->
			<div class="sign-in-section">
				<%
					String costmag = (String) session.getAttribute("costmag");
					if (!(costmag == null)) {
						session.setAttribute("costmag", null);
				%>
				<div class="msg"><%=costmag%></div>
				<%
					}
				%>
				<h1>Add Miscellaneous Cost</h1>
				<form action="addcost" method="POST">
					<input type="hidden" name="page_switch" value="PMD" />
					<div class="form-field">
						<label>Select Project <span class="form-required"
							title="This field is required.">*</span></label> <select
							name="project_id">
							<%
								for (Project proObj : allProjects) {
							%>
							<option value="<%=proObj.getId()%>" <%if(!proObj.getStatus()){%> disabled="disabled" <%}%>>
								<%=proObj.getTitle()%>

							</option>
							<%
								}
							%>
						</select>
					</div>
					<div class="form-field">
						<label>Cost Title *</label> <input type="text" id="cost_title"
							name="cost_title" size="60" maxlength="128" class="form-text">
					</div>
					<div class="form-field">
						<label>Cost Amount($) *</label> <input type="text" id="cost_amt"
							name="cost_amt" value="" placeholder="0.00" size="60"
							maxlength="128" class="form-text required" /><span class="position_set">$</span>
					</div>
					<div class="form-field">
						<label>Description</label>
						<textarea type="text" id="description" name="description" value=""
							size="60" maxlength="60" class="form-text required"></textarea>
					</div>
					<div class="form-field">
						<input type="submit" id="submit_login_form" class="btn btn-signin"
							value="Submit" />
					</div>
				</form>
			</div>

		</div>
		<div class="form">
			<!-- Add External Employees Hourly Rate  -->
			<div class="sign-in-section">
				<%
					String hratemag = (String) session.getAttribute("hratemag");
					if (!(hratemag == null)) {
						session.setAttribute("hratemag", null);
				%>
				<div class="msg"><%=hratemag%></div>
				<%
					}
				%>
				<h1>Add External Employees Cost</h1>
				<form action="addhourlyrate" method="POST">
					<input type="hidden" name="page_switch" value="PMD" />
					<div class="form-field">
						<label>Select External Employee *</label> <select name="ex_emp_id">

							<%
								for (User empObj : allEmployees) {
							%>
							<option value="<%=empObj.getId()%>">
								<%=empObj.getFullName()%>

							</option>
							<%
								}
							%>

						</select>
					</div>
					<div class="form-field">
						<label>Hourly Rate($) *</label> <input type="text" id="hourly_rate"
							name="hourly_rate" placeholder="0.00" size="60" maxlength="128"
							class="form-text"><span class="position_set">$</span>
					</div>
					<div class="form-field">
						<label>Description</label>
						<textarea type="text" id="description" name="description" value=""
							size="60" maxlength="60" class="form-text required"></textarea>
					</div>
					<div class="form-field">
						<input type="submit" id="submit_login_form" class="btn btn-signin"
							value="Submit" />
					</div>
				</form>
			</div>
		</div>
		<!--      </div> -->
	</div>
</body>

</html>