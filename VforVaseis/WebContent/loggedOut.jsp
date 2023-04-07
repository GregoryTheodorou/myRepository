<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <link href="mystyle.css" rel="stylesheet" type="text/css">
	<meta charset="ISO-8859-1">
	<title>Logged Out</title>
</head>
<body  class ="background">
	<jsp:include page="Navbar.jsp"/>
	<h1 align="center"><%=request.getAttribute("message") %></h1>
	
	<div align="center">
		  <h1>Log in</h1>
		  <form action="login" method="post">
		  <div class="input-group">
		      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		      <input id="username" type="text" class="form-control" name="username" placeholder="Username">
		    </div>
		    <div class="input-group">
		      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
		      <input id="password" type="password" class="form-control" name="password" placeholder="Password">
		    </div>
		    
		   <button type="submit" class="btn btn-primary navbar-inverse" style ="margin-top:30px">Submit</button>
		  </form>
	 </div>
</body>
</html>