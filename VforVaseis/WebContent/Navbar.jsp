<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
	<title>${param.pageTitle}</title>
</head>
<body>
<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	  	<ul class="nav navbar-nav">
      		<li><a href="index.jsp">E-Restaurant</a></li>
    		
    	</ul>
	    <ul class="nav navbar-nav navbar-right">
	      <li><a href="<c:url value = "/Register.jsp"/>"><span class="glyphicon glyphicon-user"></span> Register</a></li>
	      <li><a href="<c:url value = "/index.jsp"/>"><span class="glyphicon glyphicon-log-in"></span> Login/out</a></li>
	    </ul>
	  </div>
	</nav>
</body>
</html>