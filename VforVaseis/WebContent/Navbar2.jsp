<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	
	<title>${param.pageTitle}</title>
</head>
<body>
<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	  	<ul class="nav navbar-nav">
      		<li><a href="HomePage.jsp">E-Restaurant</a></li>
      		<li><a href="RegisterReview.jsp">register your review</a></li>
    		<li><a href="MyReviews.jsp">My Reviews</a></li>
    		<li><a href="SearchReview.jsp">Search Review</a></li>
    		<li><a href="RegisterReservation.jsp">register your reservation</a></li>
    		<li><a href="CancelReservation.jsp">My reservation-Cancel</a></li>
    		
    	</ul>
	    <ul class="nav navbar-nav navbar-right">
	      <li><a href="<c:url value = "loggedOut"/>"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
	    </ul>
	  </div>
	</nav>
</body>
</html>