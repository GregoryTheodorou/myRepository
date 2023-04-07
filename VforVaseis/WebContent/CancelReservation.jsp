<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import ="myhibernate.model.*" %>    
	<%@page import ="org.hibernate.*" %>
	<%@page import="myhibernate.util.HibernateUtil" %>
	<%@page import="java.util.*" %>
	<%@page import="myhibernate.dao.*" %>
	<%@page import="java.time.LocalDateTime"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <link href="mystyle.css" rel="stylesheet" type="text/css">
<meta charset="ISO-8859-1">
<title>Cancel Reservation</title>
</head>
<body  class ="background">
	<jsp:include page="Navbar2.jsp"/>
	 
<div class = "mytable">
	<table class="table table-bordered">
	  <thead>
	  	<tr>
			  <th scope="col">Date-Time</th>
		      <th scope="col">at Restaurant</th>
	    </tr>
		</thead>
		<tbody>
			
	<%
	
	User user = null;
	String username = (String) session.getAttribute("UserID");
	UserDao userdao = new UserDao();
	user = (User) userdao.getUser(username);
	Iterator<Reservation> it = user.getReservation().iterator();
	   Reservation reservation = new Reservation();
	   while (it.hasNext())	{
	   	reservation = it.next();
	   	if(!reservation.getRDateTime().isBefore(LocalDateTime.now().plusHours(2))){
	   		
	%>
	<tr>
	<th><%=reservation.getRDateTime().minusHours(2) %></th>
	<td><%=reservation.getRestaurant().getName() %></td>
	</tr>
	<%
	   	}
	   	}
	%>
	
		</tbody>
	</table>
</div>
	<div class="bg">
		<div class="center_form">
			<p class="myp1"><b>Cancel one of your Reservations</b></p>
			<form method= "POST" action="CancelReservation">
				<div class="form-group">
				    <label for="restaurant">Restaurant:</label>
				    <select class="select-css"  name="restaurant">
						<% 
						    it = user.getReservation().iterator();
						    Restaurant temp = null;
						    while (it.hasNext())	{
						    	temp = it.next().getRestaurant();
						%>
						<option value="<%=temp.getName()%>"><%=temp.getName()%></option>
						<%
						    }
						%>
					</select>
				</div>
		  		<button type="submit" class="btn btn-primary navbar-inverse">Cancel</button>
			</form>
		</div>
	</div>
	

	
</body>
</html>