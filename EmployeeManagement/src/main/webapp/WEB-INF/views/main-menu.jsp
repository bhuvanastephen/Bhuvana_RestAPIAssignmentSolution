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

<title>Employee Directory</title>
</head>

<body>
	<sec:authorize access="hasAnyRole('ROLE_ADMIN')">  
Hello ADMIN  
</sec:authorize> 


	<div class="container">

		<h3>Employee Directory</h3>
		<hr>

		<!-- Add a search form -->
		<hr>
		<sform:form action="http://localhost:8080/EmployeeManagement/employeeSearch/searchemployee" modelAttribute="searchCriteria">
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
			<a href="/EmployeeManagement/employee/showFormForAdd"
				class="btn btn-primary btn-sm mb-3"> Add Employee </a> 
			<a href="/EmployeeManagement/logout" 
					class="btn btn-primary btn-sm mb-3 mx-auto"> Logout </a>
			<a href="/EmployeeManagement/user/usermenu" 
					class="btn btn-primary btn-sm mb-3 mx-auto"> Users </a>
		<hr>
		<table class="table table-bordered table-striped">
			<thead class="thead-dark">
				<tr>
					<th>Id</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${Employees}" var="tempEmployee">
					<tr>
						<td><c:out value="${tempEmployee.employeeid}" /></td>
						<td><c:out value="${tempEmployee.firstName}" /></td>
						<td><c:out value="${tempEmployee.lastName}" /></td>
						<td><c:out value="${tempEmployee.email}" /></td>
						<td>
							<!-- Add "update" button/link --> <a
						
							href="/EmployeeManagement/employee/showFormForUpdate?employeeId=${tempEmployee.employeeid}"
							class="btn btn-info btn-sm"> Update </a> <!-- Add "delete" button/link -->
							<a href="/EmployeeManagement/employee/delete?employeeId=${tempEmployee.employeeid}"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('Are you sure you want to delete this Employee?'))) return false">
								Delete </a>

						</td>

					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
	

</body>
</html>
