<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Register Restaurant Form</title>
</head>
<body>
<jsp:include page="Navbar2.jsp"/>



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
</body>
</html>