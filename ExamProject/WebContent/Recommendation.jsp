<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="javax.json.*" %>
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
<title>Recommendation</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="mystyle.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="Navbar.jsp"/>
	
	<div class="bg">
		
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
				<label for="game">Write a game and i will show similar games</label>
				<input type="text" class="form-control" id="game" name =name>
				
			</div>
			<input type="SUBMIT"  class="btn btn-default">
			</form>
			
		</div>
	</div>
	
	<footer class="foo">Greg Theodorou #2020</footer>
</body>
</html>
