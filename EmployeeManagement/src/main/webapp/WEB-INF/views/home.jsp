<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management System</title>
<style type="text/css">
h2 {
	text-align: center;
}
 h3 {
	text-align: center;
	font-style: italic;
	color: red;
}
.myTable {
	width: 500px;
	margin: 50px auto;
	font-size: 20px;
	background-color: #eee;
	border-collapse: collapse;
	box-shadow: 0 0 10px black;
}

.myTable th {
	background-color: #000;
	color: white;
	width: 50%;
}

.myTable td, .myTable th {
	padding: 5px;
	border: 1px solid #000;
}

tr{
	  height:45px;
	}
	td, th{
	  border:1px solid black;
	  text-align:left
	}
	
</style>
</head>
<body>

	<div class="container"
		style="margin: 50px; background-color: hsla(120, 100%, 25%, 0.3)">

		
		<h2>
			<strong>Employee Management System</strong>
		</h2>

		<br> 
		<table class="myTable" border="1">
			<caption><strong></strong>Home Page Menu</strong></caption>
			<thead>
				<tr>
					<th><a href="http://localhost:8080/EmployeeManagement/home/signin">Sign In</a></th>
					<th><a href="http://localhost:8080/EmployeeManagement/home/signup-form">Sign Up</a></th>
				</tr>
			</thead>
		</table>
		<h3>
			<strong>"Welcome Home"</strong>
		</h3>
	</div>
</body>
</html>