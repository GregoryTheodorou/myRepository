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
	<%String game = request.getParameter("name");
	String str = (String)request.getAttribute("array");
	JSONArray array = new JSONArray(str);
	Extra myarray;
	int[] indexarray ;
	String[] likesarray ;
	String[] dislikesarray ;
	String[] recommendarray ;
	String[] userarray;
	
	
	int temp = -1;
	String description;
	String name;
	String likes = (String)request.getAttribute("likes");
	String dislikes = (String)request.getAttribute("dislikes");
	String recommend = (String)request.getAttribute("recommend");
	String user = (String)request.getAttribute("user");
	//System.out.println("phra:"+name+" kai:"+description+" kai:"+genre);
	int rating = -1;
	if( request.getAttribute("myarray").equals("nothing")){
		if(likes.equals("---"))	{
			%>
				<p style ="color:white" align= "center">Could not find any similar games in the database</p>
			<%
			}
			%>
			<h2 align = "center" style ="color:white;">Similar Games for: <%=game %></h2>
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
			<%for(int i=0;i<array.length();i++)	{
				
				if(array.getJSONObject(i).has("summary"))	{
					 description =(String) array.getJSONObject(i).getString("summary");
				}
				else	{
					 description = "---";
				}
				if(array.getJSONObject(i).has("name"))	{
					 name = (String)array.getJSONObject(i).getString("name");
				}
				else	{
					 name = "---";
				}
				if(array.getJSONObject(i).has("rating"))	{
					 rating =array.getJSONObject(i).getInt("rating");
				}
				
				%>
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
				<%
			}	
				%>
					</tbody>
			</table>
		</div>
	<% 
	}
	else{
		myarray = new Extra((String) request.getAttribute("myarray"));
		 indexarray = myarray.getIndexArray();
		 likesarray = myarray.getLikesArray();
		 dislikesarray = myarray.getDislikesArray();
		 recommendarray =myarray.getRecommendArray();
		 userarray = myarray.getUserArray();
		 
		 %>
		 <h2 align = "center" style ="color:white;">Similar Games for: <%=game %></h2>
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
			<%for(int i=0;i<array.length();i++)	{
				likes = "---";
				dislikes="---";
				recommend="---";
				user ="---";
				for(int j = 0;j<myarray.getSize();j++)	{
					if(indexarray[j] == i)	{
						temp = j;
					}
				}
				if(temp != -1){
					likes = likesarray[temp];
					dislikes = dislikesarray[temp];
					recommend = recommendarray[temp];
					user = userarray[temp];
				}
				if(array.getJSONObject(i).has("summary"))	{
					 description =(String) array.getJSONObject(i).getString("summary");
				}
				else	{
					 description = "---";
				}
				if(array.getJSONObject(i).has("name"))	{
					 name = (String)array.getJSONObject(i).getString("name");
				}
				else	{
					 name = "---";
				}
				
				if(array.getJSONObject(i).has("rating"))	{
					 rating =array.getJSONObject(i).getInt("rating");
				}
				
				%>
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
				<%
				temp = -1;
				rating = -1;
			}	
	}
	%>
			</tbody>
	</table>
	</div>
	
	
	
	<footer class="foo">Greg Theodorou #2020</footer>
		
</body>
</html>