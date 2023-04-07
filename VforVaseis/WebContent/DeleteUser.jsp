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
<link href="mystyle.css" rel="stylesheet" type="text/css">
<meta charset="ISO-8859-1">
<title>Delete User</title>
</head>
<body  class ="background">
	<jsp:include page="NavbarAdmin.jsp"/>
	<div class="bg">
		<div class="center_form">
			<p class="myp1"><b>Delete a User.</b></p>
			<form method= "POST" action="DeleteUser">
				<div class="form-group" style = "padding-top: 20px">
		    		<label for="username">Username:</label>
		    		<select class="select-css" name="username">
						<% 
						User user = null;
						Transaction transaction = null;
						try (Session ses = HibernateUtil.getSessionFactory().openSession()) {
						transaction = ses.beginTransaction();
					    // get an user object
					    List allUsers = new ArrayList();
					    allUsers = (List<User>)ses.createQuery("from User").list();
				
					    transaction.commit();
					    ses.close();
					    
						Iterator<User> it = allUsers.iterator();
						   User temp = new User();
						   while (it.hasNext())	{
						   	temp = it.next();
						%>
						<option value="<%=temp.getUsername()%>"><%=temp.getUsername()%> (<%=temp.getRole()%>)</option>
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