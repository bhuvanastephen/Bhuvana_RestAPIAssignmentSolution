<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<style>
h2 {
	text-align: center;
}
 h3 {
	text-align: center;
	font-style: italic;
	color: red;
	}
	
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<div 
		style="margin: 50px; background-color: hsla(120, 100%, 25%, 0.3)">
		<p>
			<a href="http://localhost:8080/EmployeeManagement/employee/mainmenu"><strong>Home</strong></a>
		</p>
		<h2>
			<strong>Update User</strong>
		</h2>
		<form:form cssClass="formcss"
			action="http://localhost:8080/EmployeeManagement/user/updateUser"
			modelAttribute="User">
			<form:errors path="*" cssClass="errorblock" element="div" />
			<br>
			<!-- Add hidden form field to handle update -->
			<input type="hidden" name="id" value="${User.id}" />
			<br>
<strong>UserName 	:</strong> <form:input path="username" value="${User.username}" />
			<form:errors path="username" cssClass="error" />
			<br>
			<br>
<strong>Password 	:</strong> <form:input path="password" value="${User.password}" />
			<form:errors path="password" cssClass="error" />
			<br>
			<br>
		
<strong>Role 	:</strong><c:forEach items="${User.roles}" var="tempRole">
								<tr> <c:out value="${tempRole.name}" /> </tr>
								 
							</c:forEach>
								
			<form:errors path="roles" cssClass="error" />
			<br>
			<br>
			<input type="submit" value="Update" />
		</form:form>
				<h3>
			<strong>${message}</strong>
		</h3>
	</div>

</body>
</html>