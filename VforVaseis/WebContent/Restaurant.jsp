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
<title>Register Reservation</title>
</head>
<body>
	<jsp:include page="Navbar2.jsp"/>
	
	
	
	<h1>Retrieve data from database in jsp</h1>
 
	<div class="bg">
		<div class="center_form">
			<p class="myp1"><b>Reservation Form.</b></p>
			<form method= "POST" action="RegisterReservation">
			
				<select name="restaurant">
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
		  		<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
	</div>
</body>
</html>