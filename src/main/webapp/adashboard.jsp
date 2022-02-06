<!DOCTYPE html>
<html lang="en">
<%@ page isELIgnored="false"
	import="in.projectmang.models.*,java.util.*,java.sql.*"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dash board</title>
<link rel="stylesheet" href="css/bootstrap.min.css" media="screen">
<link rel="stylesheet" href="css/style.css" media="screen">
<script>
function deactivateUser(id){
	var http = new XMLHttpRequest();
	var url = 'deactivateuser';
	var params = 'user_id='+id; //close status
	http.open('POST', url, true);

	//Send the proper header information along with the request
	http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

	http.onreadystatechange = function() {//Call a function when the state changes.
	    if(http.readyState == 4 && http.status == 200) {
	        alert("Deleted");
	    }
	}
	http.send(params);
	/* window.location.reload(); */
	window.location.href = "admindashboard"
}
</script>
</head>

<body class="top-navbar-fixed">
	<%
		User userObj = (User) session.getAttribute("user");
		ArrayList<User> allEmployees = (ArrayList<User>) session.getAttribute("allEmployees");
		ArrayList<Role> allRoles = (ArrayList<Role>) session.getAttribute("allRoles");
	%>
	<div class="main-wrapper">
		<ul>

			<li><a href="logout">Logout</a></li>
			<li><span><h3>
						Administrator -
						<%=userObj.getFullName()%></h3></span></li>
		</ul>
	</div>
	<div class="grid">
		<div class="container">
			<div class="form">
				<!-- Add User -->
				<div class="sign-in-section">
					<%
						String createmsg = (String) session.getAttribute("createmsg");
						if (!(createmsg == null)) {
							session.setAttribute("createmsg", null);
					%>
					<div class="msg"><%=createmsg%></div>
					<%
						}
					%>
					<h1>Add User</h1>
					<form action="createuser" method="POST">
						<div class="form-field">
							<label>Select Role<span class="form-required"
								title="This field is required.">*</span></label> <select name="role_id">
								<%
									for (Role roleObj : allRoles) {
										if (roleObj.getId() != 1) {
								%>
								<option value="<%=roleObj.getId()%>">
									<%=roleObj.getRole()%>

								</option>
								<%
									}
									}
								%>
							</select>
						</div>
						<div class="form-field">
							<label>Full Name <span class="form-required"
								title="This field is required.">*</span></label> <input type="text"
								id="full_name" name="full_name" value="" size="60"
								maxlength="60" class="form-text required" required>
						</div>
						<div class="form-field">
							<label>Email <span class="form-required"
								title="This field is required.">*</span></label> <input type="text"
								id="email" name="email_id" value="" size="60" maxlength="60"
								class="form-text required" required>
						</div>
						<div class="form-field">
							<label>Contact Number <span class="form-required"
								title="This field is required.">*</span></label> <input type="number"
								id="contact_number" name="contact_number" value="" size="60"
								maxlength="60" class="form-text required" required>
						</div>
						<div class="form-field">
							<label>Password <span class="form-required"
								title="This field is required.">*</span></label> <input type="password"
								id="edit-pass" name="password" size="60" maxlength="128"
								class="form-text required" required>
						</div>
						<div class="form-field">
							<input type="submit" id="submit_login_form"
								class="btn btn-signin" value="Submit" />
						</div>
					</form>
				</div>
			</div>
			<div class="form">

				<div class="sign-in-section">
					<%
						String assignmsg = (String) session.getAttribute("assignmsg");

						if (!(assignmsg == null)) {
							session.setAttribute(assignmsg, null);
					%>
					<div>
						><%=assignmsg%><
					</div>
					<%
						}
					%>
					<h1>Change Employee Role</h1>
					<form action="roleassign" method="POST">
						<div class="form-field">
							<label>Select Employee <span class="form-required"
								title="This field is required.">*</span></label> <select
								name="emplyoeeId">
								<%
									for (User empObj : allEmployees) {
										if (empObj.getId() != 1) { // 1 Admin
								%>
								<option value="<%=empObj.getId()%>">
									<%=empObj.getFullName()%>

								</option>
								<%
									}
									}
								%>
							</select>
						</div>
						<div class="form-field">
							<label>Select Role For Selected Employee<span
								class="form-required" title="This field is required.">*</span></label> <select
								name="role_id">
								<%
									for (Role roleObj : allRoles) {
										if (roleObj.getId() != 1) {
								%>
								<option value="<%=roleObj.getId()%>">
									<%=roleObj.getRole()%>

								</option>
								<%
									}
									}
								%>
							</select>
						</div>
						<div class="form-field">
							<input type="submit" id="submit_login_form"
								class="btn btn-signin" value="Submit" />
						</div>
					</form>
				</div>
				<!-- List of Employees Role -->
				<div class="sign-in-section">
					<h1>Details Of Employees & Role</h1>
					<div class="emp_list">
						<table>
							<tr>
								<th>#</th>
								<th>Name</th>
								<th>Role</th>
								<th>Contact Number</th>
								<th>Email ID</th>
								<th>Password</th>
								<th>Action</th>
							</tr>
							<%
								int i = 1;
								for (User empObj : allEmployees) {

									if (!(empObj.getRole().getId() == 1)&&empObj.getStatus()) {
							%>
							<tr>
								<td><%=i++%></td>
								<td><%=empObj.getFullName()%></td>
								<td><%=empObj.getRole().getRole()%></td>
								<td><%=empObj.getContactNum()%></td>
								<td><%=empObj.getEmailId()%></td>
								<td><%=empObj.getPassword()%></td>
								<td><button onclick="deactivateUser(<%=empObj.getId()%>)">Delete</button></td>
							</tr>
							<%
								}
								}
							%>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>