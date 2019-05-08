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
	<div align="center" style="color: red">${errMessage}</div>
	<div align="center" style="color: green">${successMessage}</div>

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

		<c:forEach var="usr" items="${sessionScope.userList}">
			<tr>
				<td>${usr.id}</td>
				<td>${usr.name}</td>
				<td>${usr.surname}</td>
				<td>${usr.age}</td>
				<td>${usr.gender}</td>
				<td>${usr.email}</td>
				<td>${usr.password}</td>
				<td>${usr.roleId}</td>
				<td><a href="update?email=${usr.email}"><input
						type="button" value="update"></a> <a
					href="delete?email=${usr.email}"><input type="button"
						value="delete"></a></td>
		</c:forEach>

	</table>
	<a href="adminGoods"><input type="button" value="Modify books"></a>
	<a href="logout"><input type="button" value="LogOut"></a>
</body>
</html>