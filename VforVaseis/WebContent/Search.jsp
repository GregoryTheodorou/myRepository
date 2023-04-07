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
<title>Search Review</title>
</head>
<body  class ="background">
	<jsp:include page="Navbar2.jsp"/>
	<p class="myp1"><b>Search reviews about a Restaurant</b></p>
	<div class="bg">
		<div class="center_form">
			
			<form method= "POST" action="Search.jsp">
				<div class="form-group" >
		    		<label for="restaurant">Restaurant name:</label>
		    		<select class="select-css" name="restaurant">
						<% 
						Transaction transaction = null;
						User user = null;
						try (Session ses = HibernateUtil.getSessionFactory().openSession()) {
						    // start a transaction
						    transaction = ses.beginTransaction();
						    // get an user object
						    List allRes = new ArrayList();
						    allRes = (List<Restaurant>)ses.createQuery("from Restaurant").list();
						   	Iterator <Restaurant> itr = allRes.iterator();
							        
						   
						   	transaction.commit();
						    ses.close();
						    Restaurant temp = null;
						    while (itr.hasNext())	{
						    	temp = itr.next();
			
						%>
						<option value="<%=temp.getName()%>"><%=temp.getName()%></option>
						
						<%
						    }
						} catch (Exception e) {
							if (transaction != null) {
				                transaction.rollback();
				            }
							e.printStackTrace();
						}
						%>
					</select>
		  		</div>
		  		<button type="submit" class="btn btn-primary navbar-inverse" style ="margin-top:30px">Submit</button>
			</form>
		</div>
	</div>
	<% 
	String restaurant = request.getParameter("restaurant");
	System.out.println("phra to parameter " +restaurant); 
	%>
	<h2 align = "center">Reviews for Restaurant: <%=restaurant %></h2>
	<div class = "mytable">
	<table class="table table-bordered">
	<thead>
	    <tr>
	  		<th scope="col">Review</th>
	      	<th scope="col">By User</th>
	    </tr>
	  </thead>
				<tbody>
	<% 
	    List allRes = new ArrayList();
	   	RestaurantDao resDao = new RestaurantDao();
	   	Restaurant res = resDao.getRestaurant(restaurant);
	   	Iterator <Review> itr = res.getReview().iterator();
	   	Review review = new Review();
	    Restaurant temp = null;
	    while (itr.hasNext())	{
	    	review = itr.next();
	%>
		<tr>
			<th scope="row"><%=review.getReview() %></th>
			<td><%=review.getUser().getUsername() %></td>
		</tr>
	<%
	    }
	%>
			</tbody>
	</table>
</div>
</body>
</html>