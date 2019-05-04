<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<c:if test="${logged != 'true' or user.roleId ne 1}">
	<c:redirect url="login.jsp" />
</c:if>
<c:if test="${param.action == 'logout'}">
	<c:set var="logged" value="false" scope="session" />
	<c:redirect url="login.jsp" />
</c:if>
<meta charset="ISO-8859-1">
<title>Admin Panel</title>
</head>
<body style="background-color: grey">
	<div align="center">
		<h1>
			Hello
			<c:out value="${user.name}" />
			!
		</h1>
	</div>
	<div align="center" style="color:red">${errMessage}</div>
	<div align="center" style="color:green">${successMessage}</div>

	<sql:setDataSource var="cabinet_db" driver="org.postgresql.Driver"
		url="jdbc:postgresql://localhost:5432/cabinet_db" user="postgres"
		password="admin" />

	<sql:query var="usersResultSet" dataSource="${cabinet_db}">
SELECT users.*, roles.name AS role_name FROM users LEFT JOIN roles ON role_id = roles.id;
</sql:query>

	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Surname</th>
			<th>Age</th>
			<th>Gender</th>
			<th>Email</th>
			<th>Password</th>
			<th>Role ID</th>
			<th>Action</th>
		</tr>

		<c:forEach var="row" items="${usersResultSet.rows}">
			<tr>
				<td>${row.id}</td>
				<td>${row.name}</td>
				<td>${row.surname}</td>
				<td>${row.age}</td>
				<td>${row.gender}</td>
				<td>${row.email}</td>
				<td>${row.password}</td>
				<td>${row.role_name}</td>
				<td><a href="update?email=${row.email}"><input
						type="button" value="update"></a> <a
					href="delete?email=${row.email}"><input type="button"
						value="delete"></a></td>
		</c:forEach>

	</table>
	<a href="adminPanel.jsp?action=logout"><input type="button"
		value="LogOut"></a>
</body>
</html>