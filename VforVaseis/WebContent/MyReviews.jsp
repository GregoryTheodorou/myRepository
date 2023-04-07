<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import ="myhibernate.model.*" %>    
<%@page import ="org.hibernate.*" %>
<%@page import="myhibernate.util.HibernateUtil" %>
<%@page import="java.util.*" %>
<%@page import="myhibernate.dao.*" %>
<%@page import="myhibernate.model.*" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="mystyle.css" rel="stylesheet" type="text/css">
<title>My reviews</title>
</head>
<body  class ="background">
<jsp:include page="Navbar2.jsp"/>

<h1 class = "center_header">My reviews</h1>

<div class = "mytable">
	<table class="table table-bordered">
	  <thead>
	    <tr>
	  		<th scope="col">Restaurant</th>
	      	<th scope="col">Review</th>
	    </tr>
	  </thead>
				<tbody>
					<%
					
					User user = null;
					String username = (String) session.getAttribute("UserID");
					UserDao userdao = new UserDao();
					user = (User) userdao.getUser(username);
					Iterator<Review> it = user.getReview().iterator();
					   Review review = new Review();
					   while (it.hasNext())	{
					   	review = it.next();
					%>
					<tr>
						 <th scope="row"><%=review.getRestaurant().getName() %></th>
						 <td><%=review.getReview() %></td>
					</tr>
					<%
					    }
					%>
		  		</tbody>
	</table>
</div>

<p class="myp1"><b>Delete one of your Reviews</b></p>
<div class="bg">
		<div class="center_form">
			<form method= "POST" action="DeleteReview">
				<div class="form-group" style = "padding-top: 20px">
		    		<label class ="mylabel" for="restaurant">Restaurant name:</label>
		    		<select class="select-css" name="restaurant">
						<%  
						   	it = user.getReview().iterator();        
						   // System.out.println("ekana to query sto jsp");
						    
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
		  		<button type="submit" class="btn btn-primary navbar-inverse" style ="margin-top:30px">Delete</button>
			</form>
		</div>
	</div>
</body>
</html>