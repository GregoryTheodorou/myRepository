<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import ="myhibernate.model.*" %>    
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
<title>Admin Restaurant</title>
</head>
<body  class ="background">
<jsp:include page="NavbarAdmin.jsp"/>
<div class="bg">
		<div class="center_form">
			<p class="myp1"><b>Register Form.</b></p>
			<form method= "POST" action="registerRestaurant">
				<div class="form-group">
				    <label for="name">Name:</label>
				    <input type="text" class="form-control" id="name" name="name">
				</div>
		    	<div class="form-group">
		    		<label for="type">type:</label>
		    		<input type="text" class="form-control" id="type" name="type">
		    	</div>
		    	<div class="form-group">
		    		<label for="description">Description:</label>
		    		<input type="text" class="form-control" id="description" name="description">
		    	</div>
		    	<div class="form-group">
		    		<label for="menu">Menu:</label>
		    		<input type="text" class="form-control" id="menu" name="menu">
		  		</div>
		  		<div class="form-group">
		    		<label for="contactnumber">Contact Number:</label>
		    		<input type="text" class="form-control" id="contactnumber" name="contactnumber">
		  		</div>
		  		<div class="form-group">
		    		<label for="city">City:</label>
		    		<input type="text" class="form-control" id="city" name="city">
		  		</div>
		  		<div class="form-group">
		    		<label for="address">Address</label>
		    		<input type="text" class="form-control" id="address" name="address">
		  		</div>
		  		<div class="form-group">
		    		<label for="tables">Number of Tables</label>
		    		<input type="text" class="form-control" id="tables" name="tables">
		  		</div>
		  		<div class="form-group">
		    		<label for="capacity">Capacity</label>
		    		<input type="text" class="form-control" id="capacity" name="capacity">
		  		</div>
		  		<button type="submit" class="btn btn-primary navbar-inverse">Submit</button>
			</form>
		</div>
	</div>
	
	<div class="bg">
		<div class="center_form">
			<p class="myp1"><b>Delete a Restaurant.</b></p>
			<form method= "POST" action="DeleteRestaurant">
				<div class="form-group" style = "padding-top: 20px">
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
							        
						    //System.out.println("ekana to query sto jsp");
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
		  		<button type="submit" class="btn btn-primary navbar-inverse">Delete</button>
			</form>
		</div>
	</div>
</body>
</html>