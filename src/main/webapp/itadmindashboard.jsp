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
</head>

<body class="top-navbar-fixed">
	<%
		User userObj = (User) session.getAttribute("user");
		ArrayList<Project> allProjects = (ArrayList<Project>) session.getAttribute("allProjects");
	%>
	<div class="main-wrapper">
		<ul>
			<li><a href="logout">Logout</a></li>
			<li><span><h3>
						IT Admin Name -
						<%=userObj.getFullName()%></h3></span></li>
		</ul>
	</div>
	<div class="grid">
		<div class="container">
			<div class="form">
				<!-- Add Cost -->
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
					<h1>Add Project Cost</h1>
					<form action="addcost" method="POST">
						<input type="hidden" name="page_switch" value="ITAD" />
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
							<label>Cost Title</label> <input type="text" id="cost_title"
								name="cost_title" size="60" maxlength="128" class="form-text">
						</div>
						<div class="form-field">
							<label>Cost Amount($)</label>
							<input type="text" id="cost_amt" name="cost_amt"
								value="" placeholder="0.00" size="60" maxlength="128" class="form-text required" /><span class="position_set">$</span>
						</div>
						<div class="form-field">
							<label>Description</label>
							<textarea type="text" id="description" name="description"
								value="" size="60" maxlength="60" class="form-text required"></textarea>
						</div>
						<div class="form-field">
							<input type="submit" id="submit_login_form"
								class="btn btn-signin" value="Submit" />
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>

</body>

</html>