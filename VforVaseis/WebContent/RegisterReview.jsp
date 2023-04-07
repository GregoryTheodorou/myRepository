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
<title>Register Form</title>
</head>
<body  class ="background">
<jsp:include page="Navbar2.jsp"/>

<div class="bg">
		<div class="center_form">
			<p class="myp1"><b>Register Review</b></p>
			<form method= "POST" action="RegisterReview">
				<div class="form-group" style ="padding-top:30px; padding-bottom:30px;">
				    <label for="Restaurant">Restaurant:</label>
				    
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
				<div class="form-group" style ="width:80% ;margin: 0 auto;">
				    <label for="description">Description:</label>
				    <input type="text" class="form-control" id="description" name="description">
				</div>
		  		<button type="submit" class="btn btn-primary navbar-inverse" style ="margin-top:30px">Submit</button>
			</form>
		</div>
	</div>
</body>
</html>