<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Users Directory</title>
<style>
	h3 {
	text-align: center;
	font-style: italic;
	color: red;
	}
</style>
</head>

<body>

	<div class="container">

		<h3>User Directory</h3>
		<hr>

		<!-- Add a search form -->
		<hr>
		<sform:form action="http://localhost:8080/EmployeeManagement/userSearch/searchuser" modelAttribute="searchCriteria">
		<sform:errors path="*" cssClass="errorblock" element="div" />
		Search By: <sform:select path="searchBy">
			<sform:options items="${searchCriteria.searchByList}" />
			<sform:errors path="searchByValue" cssClass="error" />
		</sform:select><sform:input path="searchByValue" value=""/> 
		Page Size: <sform:select path="pageSize">
			<sform:options items="${searchCriteria.pageSizeList}" />
		</sform:select> 
		Page No: <sform:select path="pageNo">
			<sform:options items="${searchCriteria.pageNoList}" />
		</sform:select> 
		Sort By: <sform:select path="sortBy">
			<sform:options items="${searchCriteria.sortByList}" />
		</sform:select> 
		Sort Order: <sform:select path="sortOrder">
			<sform:options items="${searchCriteria.sortOrderList}" />
		</sform:select> 
		<input type="submit" value="Search" />
		</sform:form>


		<!-- Add a button -->
		<hr>
			<a href="/EmployeeManagement/user/showFormForAddRole"
				class="btn btn-primary btn-sm mb-3"> Add Role </a> 
			<a href="/EmployeeManagement/logout" 
					class="btn btn-primary btn-sm mb-3 mx-auto"> Logout </a>
					<p>
			<a href="http://localhost:8080/EmployeeManagement/employee/mainmenu">Back to Employee List</a>
		</p>
		<hr>
		<table class="table table-bordered table-striped">
			<thead class="thead-dark">
				<tr>
					<th>Id</th>
					<th>UserName</th>
					<th>Roles</th>
					<th>Amend-Role</th>
					<th>Update/Delete-User</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${Users}" var="tempUser">
					<tr>
						<td><c:out value="${tempUser.id}" /></td>
						<td><c:out value="${tempUser.username}" /></td>
						<td>
							<c:forEach items="${tempUser.roles}" var="tempRole">
								<c:out value="${tempRole.name}" /> 
							</c:forEach>
							
						</td>
						<td>
							<!-- Add "amend" Role button --> 
							<sform:form action="http://localhost:8080/EmployeeManagement/user/amendrole?userId=${tempUser.id}" modelAttribute="Role">
								Roles: <sform:select path="name" >
								<sform:options items="${rolelist}" />
								</sform:select> 
								<input type="hidden" name="userid" id="userid" value="${tempUser.id}"/>
								<input type="submit" value="AmendRole" />
							</sform:form>
							<sform:form action="http://localhost:8080/EmployeeManagement/user/removerole?userId=${tempUser.id}" modelAttribute="Role">
								Roles: <sform:select path="name" >
								<sform:options items="${rolelist}" />
								</sform:select> 
								<input type="hidden" name="userid" id="userid" value="${tempUser.id}"/>
								<input type="submit" value="RemoveRole" />
							</sform:form>
						</td>
						<td>
							<a
							href="/EmployeeManagement/user/showFormForUpdateUser?userId=${tempUser.id}"
							class="btn btn-info btn-sm"> Update </a>
							<a href="/EmployeeManagement/user/deleteUser?userId=${tempUser.id}"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('Are you sure you want to delete this User?'))) return false">
								Delete </a>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
	<h3>
		<strong>${message}</strong>
	</h3>

</body>
</html>
