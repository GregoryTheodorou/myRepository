<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import ="myhibernate.model.*" %>   
    <%@page import ="myhibernate.dao.*" %>  
	<%@page import ="org.hibernate.*" %>
	<%@page import="myhibernate.util.HibernateUtil" %>
	<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<link href="mystyle.css" rel="stylesheet" type="text/css">
<title>Search Review (Admin)</title>
</head>
<body  class ="background">
<jsp:include page="NavbarAdmin.jsp"/>

<% 
	String restaurant = request.getParameter("restaurant");
	//System.out.println("phra to parameter " +restaurant); 
	%>
	
<h1 align="center" >Reviews for Restaurant: <%=restaurant %></h1>
<div class = "mytable">
	<table class="table table-bordered">
	  <thead>
	  	<tr>
			  <th scope="col">Reviews</th>
		      <th scope="col">By user</th>
	    </tr>
		</thead>
		<tbody>
	<% 
	    List allRes = new ArrayList();
	   	RestaurantDao resDao = new RestaurantDao();
	   	Restaurant res = resDao.getRestaurant(restaurant);
	   	Iterator <Review> itr = res.getReview().iterator();
	   	Review review = new Review();
	    
	    while (itr.hasNext())	{
	    	review = itr.next();

	%>
		<tr>
		<th><%=review.getReview() %></th>
		<td><%=review.getUser().getUsername() %></td>
		</tr>
	
	<%
	    }
	
	%>

		</tbody>
	</table>
</div>
	<div class="bg">
		<div class="center_form">
			<p class="myp1"><b>Delete a Review.</b></p>
			<form method= "POST" action="DeleteReview">
				<div class="form-group" style = "padding-top: 20px">
		    		<label for="username">Delete Review for User with username:</label>
		    		<select class="select-css" name="username">
						<% 
						User user = null;
		
					    itr = res.getReview().iterator();     
						   while (itr.hasNext())	{
						   	review = itr.next();
						   	String temp = review.getUser().getUsername()+","+restaurant ;
						%>
						
						<option value="<%=temp %>"><%=review.getUser().getUsername()%> (<%=review.getUser().getRole()%>)</option>
						<%
						    }
					   
						%>
					</select>
		  		</div>
		  		<button type="submit" class="btn btn-primary navbar-inverse">Delete</button>
			</form>
		</div>
	</div>

</body>
</html>