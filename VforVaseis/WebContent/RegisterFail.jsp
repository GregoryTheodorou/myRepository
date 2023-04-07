<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Register Form</title>
</head>
<body>
<jsp:include page="Navbar.jsp"/>

<div class="bg">
		<div class="center_form">
			<p class="myp1"><b>Register Form.</b></p>
			<p style="font-family:courier;">Something went wrong.Username or phone already exists.All entries are required.</p>
			<form method= "POST" action="register">
				<div class="form-group">
				    <label for="username">Username:</label>
				    <input type="text" class="form-control" id="username" name="username">
				</div>
		    	<div class="form-group">
		    		<label for="password">Password:</label>
		    		<input type="password" class="form-control" id="password" name="password">
		    	</div>
		    	<div class="form-group">
		    		<label for="fname">First Name:</label>
		    		<input type="text" class="form-control" id="fname" name="fname">
		    	</div>
		    	<div class="form-group">
		    		<label for="lname">Last Name:</label>
		    		<input type="text" class="form-control" id="lname" name="lname">
		  		</div>
		  		<div class="form-group">
		    		<label for="email">E-mail:</label>
		    		<input type="text" class="form-control" id="email" name="email">
		  		</div>
		  		<div class="form-group">
		    		<label for="city">City:</label>
		    		<input type="text" class="form-control" id="city" name="city">
		  		</div>
		  		<div class="form-group">
		    		<label for="mphone">Mobile Phone</label>
		    		<input type="text" class="form-control" id="mphone" name="mphone">
		  		</div>
		  		<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>	
	</div>
</body>
</html>