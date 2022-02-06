<!DOCTYPE HTML>
<html>

<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/bootstrap-3.1.1.min.css" rel='stylesheet'
	type='text/css' />
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href='//fonts.googleapis.com/css?family=Oswald:300,400,700'
	rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Ubuntu:300,400,500,700'
	rel='stylesheet' type='text/css'>
<link href="css/font-awesome.css" rel="stylesheet">

</head>

<body>
	<div class="grid">
		<div class="container">
			<div class="form">
				<div class="sign-in-section">
					<%
						String msg = (String) request.getAttribute("msg");
						if (!(msg == null)) {
							request.setAttribute("msg", null);
					%>
					<div class="msg"><%=msg%></div>
					<%
						}
					%>
					<h1>Log in</h1>
					<form action="login" method="post">
						<div class="form-field">
							<label for="email">Email Id</label> <input type="text"
								id="email_id" name="email_id" value="" size="60" maxlength="60"
								class="form-text required" required>
							<div class="help-block with-errors " id="email_id_error_msg"></div>
						</div>
						<div class="form-field">
							<label for="password">Password</label> <input type="password"
								id="password" name="password" size="60" maxlength="128"
								class="form-text required" required>
							<div class="help-block with-errors " id="password_error_msg"></div>
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