<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import ="myhibernate.model.*" %>    
	<%@page import ="org.hibernate.*" %>
	<%@page import="myhibernate.util.HibernateUtil" %>
	<%@page import="java.util.*" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="mystyle.css" rel="stylesheet" type="text/css">
<title>Register Reservation</title>
</head>
<body  class ="background">
	<jsp:include page="Navbar2.jsp"/>
	<p class= "myp1">Restaurants details:</p>
<div class = "mytable">
	<table class="table table-bordered">
	  <thead>
	  	<tr>
			  <th scope="col">Restaurant</th>
		      <th scope="col">Type</th>
		      <th scope="col">Description</th>
		      <th scope="col">Menu</th>
		      <th scope="col">Contact Phone</th>
		      <th scope="col">Address</th>
		      <th scope="col">City</th>
	    </tr>
		</thead>
		<tbody>
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
			<tr>
				<th scope="row"><%=temp.getName()%></th>
				 <td><%=temp.getType()%></td>
				 <td><%=temp.getDescription() %></td>
				 <td><%=temp.getMenu()%></td>
				 <td><%=temp.getContactPhone()%></td>
				 <td><%=temp.getAddress()%></td>
				 <td><%=temp.getCity()%></td>
			</tr>
			<%
			    }
			} catch (Exception e) {
				if (transaction != null) {
	                transaction.rollback();
	            }
				e.printStackTrace();
			}
			%>
		</tbody>
	</table>
</div>
 <p class="myp1" style ="padding-top:0;"><b>Reservation Form</b></p>
<div class="bg">
		<div class="center_form">
			<form method= "POST" action="RegisterReservation">
				<div class="form-group">
		    		<label for="restaurant">Restaurant name:</label>
		    		<select class="select-css" name="restaurant">
						<% 
						try (Session ses = HibernateUtil.getSessionFactory().openSession()) {
						    // start a transaction
						    transaction = ses.beginTransaction();
						    // get an user object
						    List allRes = new ArrayList();
						    allRes = (List<Restaurant>)ses.createQuery("from Restaurant").list();
						   	Iterator <Restaurant> itr = allRes.iterator();
							        
						    System.out.println("ekana to query sto jsp");
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
				<div class="form-group">
				    <label for="rdatetime" >Reservation Date (year,month,day):</label>
				       <input type="date" id="start" name="rdatetime"
       					value="2020-12-29"
       					min="2020-12-28" max="2021-12-31">
				       <br>
				    <label for="rdatetime" >Reservation Time:</label>
				    <select class="select-css" name="time">
					    <option value="08:00">8:00</option>
					    <option value="10:00">10:00</option>
					    <option value="12:00">12:00</option>
					    <option value="14:00">14:00</option>
					    <option value="16:00">16:00</option>
					    <option value="18:00">18:00</option>
					    <option value="20:00">20:00</option>
					    <option value="22:00">22:00</option>
				  	</select>
				    
				</div>
		    	<div class="form-group">
		    		<label for="guestnumber" >Number Of Guests:</label>
		    		<select class="select-css" name="guestsnumber">
					    <option value="1">1</option>
					    <option value="2">2</option>
					    <option value="3">3</option>
					    <option value="4">4</option>
					    <option value="5">5</option>
					    <option value="6">6</option>
					    <option value="7">7</option>
					    <option value="8">8</option>
				  	</select>
		    	</div>
		  		<button type="submit" class="btn btn-primary navbar-inverse" style ="margin-top:30px">submit</button>
			</form>
		</div>
	</div>
	
</body>
</html>