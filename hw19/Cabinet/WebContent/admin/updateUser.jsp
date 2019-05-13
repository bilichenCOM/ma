<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update user</title>
</head>
<body>
	<div align="center">
		<h2>
			Update user
			<c:out value="${param.email}" />
			data
		</h2>
		<br>
	</div>
	<div align="center" style="color: red">
		<c:out value="${errMessage}" />
	</div>
	<div align="center" style="color: green">
		<c:out value="${successMessage}" />
	</div>
	<form action="updateUser" method="POST">
		<input type="hidden" name="email" value="${param.email}" />
		<div align="center">
			<table>
				<tr>
					<td>Name:</td>
					<td><input type="text" maxlength="20" name="name" value="${user.name}">
					</td>
				</tr>
				<tr>
					<td>Surname:</td>
					<td><input type="text" name="surname" maxlength="20" value="${user.surname}">
					</td>
				</tr>
				<tr>
					<td>Age:</td>
					<td><input type="text" maxlength="3" name="age" value="${user.age}">
					</td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td><input type="text" name="gender" value="${user.gender}">
					</td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" value="${user.password}">
					</td>
				</tr>
				<tr>
					<td>RoleId:</td>
					<td><input type="text" name="roleId" value="${user.roleId}">
					</td>
				</tr>
				<tr>
					<td>Balance:</td>
					<td><input type="text" name="balance" value="${user.balance}">
					</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Update"></td>
				</tr>
			</table>
		</div>
	</form>
	<div align="left"><a href="/Cabinet/admin">Back</a></div>
</body>
</html>