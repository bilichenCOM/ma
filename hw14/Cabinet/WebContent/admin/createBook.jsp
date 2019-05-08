<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New book</title>
</head>
<body>
	<form action="signupBook" method="POST">
		<div align="center">
			<h2>Register new book</h2>
		</div>
		<div align="center" style="color: red">
			<c:out value="${errMessage}" />
		</div>
		<div align="center" style="color: green">
			<c:out value="${successMessage}" />
		</div>
		<div align="center">
			<table title="">
				<tbody>
					<tr>
						<td>Title:</td>
						<td><input type="text" name="title" maxlength="100"></td>
					</tr>
					<tr>
						<td>Author:</td>
						<td><input type="text" name="author" maxlength="20">
						</td>
					</tr>
					<tr>
						<td>Year:</td>
						<td><input type="text" name="year" maxlength="3"></td>
					</tr>
					<tr>
						<td>Pages:</td>
						<td><input type="text" name="pages"></td>
					</tr>
					<tr>
						<td>Image URL:</td>
						<td><input type="text" name="imageUrl"></td>
					</tr>
					<tr>
						<td>Price:</td>
						<td><input type="text" name="price"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" name="" value="Register"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>