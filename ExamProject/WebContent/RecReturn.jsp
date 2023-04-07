<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page import ="myhibernate.model.*" %>   
    <%@page import ="myhibernate.dao.*" %>  
	<%@page import ="org.hibernate.*" %>
	<%@page import="myhibernate.util.HibernateUtil" %>
	<%@page import="java.util.*" %>
	<%@page import="javax.json.*" %>
	<%@page import="org.json.*" %>
	<%@page import="org.apache.http.client.HttpClient" %>
     <%@page import="org.apache.http.impl.client.HttpClients" %>
     <%@page import="org.apache.http.client.methods.HttpPost" %>
     <%@page import="org.apache.http.entity.StringEntity" %>
     <%@page import="org.apache.http.*" %>
     <%@page import="java.io.InputStream" %>
     <%@page import="org.apache.http.message.*" %>
     <%@page import="java.io.File" %>
     <%@page import="java.io.FileNotFoundException" %>
     <%@page import="java.util.Scanner" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Search</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="mystyle.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body class = "bg">

	<jsp:include page="Navbar.jsp"/>
	<%String genre = request.getParameter("genre");
	//String str = (String)request.getAttribute("obj");
	JSONObject obj =(JSONObject) request.getAttribute("obj");
	String description;
	String name;
	int rating = -1;
	if(obj.has("rating"))	{
		 rating = obj.getInt("rating");
	}
	
	if(obj.has("summary"))	{
		 description =(String) obj.getString("summary");
	}
	else	{
		 description = "---";
	}
	if(obj.has("name"))	{
		 name = (String)obj.getString("name");
	}
	else	{
		 name = "---";
	}
	
	String likes = (String)request.getAttribute("likes");
	String dislikes = (String)request.getAttribute("dislikes");
	String recommend = (String)request.getAttribute("recommend");
	String user = (String)request.getAttribute("user");
	//System.out.println("phra:"+name+" kai:"+description+" kai:"+genre);
	if(likes.equals("---"))	{
	%>
		<p style ="color:white" align= "center">Could not find anything for that category in the database</p>
	<%
	}
	%>
	<h2 align = "center" style ="color:white;">Recommendation for Genre: <%=genre %></h2>
	<div class = "mytable">
	<table class="table table-bordered">
	<thead>
	    <tr>
	    	<th scope="col">Name</th>
	  		<th scope="col">Description</th>
	  		<th scope="col">Rating</th>
	  		<th scope="col">Likes</th>
	  		<th scope="col">Dislikes</th>
	  		<th scope="col">Recommended</th>
	      	<th scope="col">By User</th>
	    </tr>
	  </thead>
				<tbody>
	
		<tr>
			<th scope="row"><%=name%></th>
			<td><%=description %></td>
			<%if(rating != -1){
				
			%>	
			<td> <%= rating%>/100</td>
			<%
			}else{
			%>
			<td>---/100</td>
			<%} %>
			<td><%=likes %></td>
			<td><%=dislikes %></td>
			<td><%=recommend %></td>
			<td><%=user %></td>
		</tr>

		
			</tbody>
	</table>
	</div>
	<div >
		
		<div class="center_form">
			<p class="myp1"><b>Click the genre you want a recommendation on.</b></p>
			<div class="btn-group">
				<form method= "POST" action="SearchByGenre">
	    		<%
	    		try {
	    		      File myObj = new File("/home/user/Downloads/apache-tomcat-9.0.39/webapps/ExamProject/genres.txt");
	    		      Scanner myReader = new Scanner(myObj);
	    		      while (myReader.hasNextLine()) {
	    		        String data = myReader.nextLine();
	    		        //System.out.println(data);
		    			
			    			
		    		%>
		    			<button name =genre type="submit" class="btn btn-primary" value="<%=data%>"><%=data%></button>
		    		<%
	    		      }
	    		      myReader.close();
	    		    } catch (FileNotFoundException e) {
	    		      System.out.println("An error occurred.");
	    		      e.printStackTrace();
	    		    }
		    		%>
	    		</form>
			</div>
			<form method= "POST" action="SearchByName">
			<div class="form-group">
				<label for="game">Find a similar Game</label>
				<input type="text" class="form-control" id="game" name =name>
				
			</div>
			<input type="SUBMIT"  class="btn btn-default">
			</form>
		</div>
	</div>
	
	<footer class="foo">Greg Theodorou #2020</footer>
		
</body>
</html>