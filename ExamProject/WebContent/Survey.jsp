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
<title>Survey</title>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="mystyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="Navbar.jsp"/>
	
	<div class="bg">

		<div class="center_form">
			<p class="myp1"><b>Share with us you gaming preference.</b></p>
			<form method= "POST" action="Survey.do">
				<div class="form-group">
				    <label for="username">Username:</label>
				    <input type="text" class="form-control" id="username" name="username">
				</div>
		    	<div class="form-group">
		    		<label for="game">Name of the Game you are playing:</label>
		    		<input type="text" class="form-control" id="game" name="game">
		    	</div>
		    	<div class="form-group">
		    		<label for="genre">Genre:</label>
		    		<select  class="select-css" name="genre">
		    		
		    		<%	
		    		try {
		    		      File myObj = new File("/home/user/Downloads/apache-tomcat-9.0.39/webapps/ExamProject/genres.txt");
		    		      Scanner myReader = new Scanner(myObj);
		    		      while (myReader.hasNextLine()) {
		    		        String data = myReader.nextLine();
		    		       // System.out.println(data);
			    			
		    		%>
		    			<option value="<%=data%>" style = "color:black"><%=data%></option>
		    		<%
		    		      }
		    		      myReader.close();
		    		    } catch (FileNotFoundException e) {
		    		      System.out.println("An error occurred.");
		    		      e.printStackTrace();
		    		    }
		    		%>
		    		</select>
		    	</div>
		    	<div class="form-group">
		    		<label for="playedtime">How many hours have you played?</label>
		    		<input type="text" class="form-control" id="playedtime" name="playedtime">
		  		</div>
		  		<div class="form-group">
		    		<label for="description">Give us a e-mall description:</label>
		    		<input type="text" class="form-control" id="description"name="description">
		  		</div>
		  		<div class="form-group">
		    		<label for="likes">What do you like about it?</label>
		    		<input type="text" class="form-control" id="likes" name="likes">
		  		</div>
		  		<div class="form-group">
		    		<label for="dislikes">What did you find could use some fine-tuning?</label>
		    		<input type="text" class="form-control" id="dislikes" name="dislikes">
		  		</div>
		  		<div class="form-group">
		    		<label for="recommend">Do you recommend it(yes/no)</label>
		    		<select  class="select-css" name="recommend">
		    		<option value="YES">YES</option>
		    		<option value="NO">NO</option>
		    		</select>
		  		</div>
		  		<button type="submit" class="btn btn-default">Submit</button>

			</form>
		</div>	
	</div>
	<footer class="foo">Greg Theodorou #2020</footer>
</body>
</html>