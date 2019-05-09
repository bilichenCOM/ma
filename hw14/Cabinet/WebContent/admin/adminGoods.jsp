<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage your books</title>
</head>
<body>
<div align="center"><h2>Please chose the action:</h2></div>
<br>
<div align="center" style="color: red">
		<c:out value="${errMessage}" />
	</div>
	<div align="center" style="color: green">
		<c:out value="${successMessage}" />
	</div>
	<table>
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Author</th>
			<th>Year</th>
			<th>Pages</th>
			<th>Image URL</th>
			<th>Price</th>
			<th>Action</th>
		</tr>
		<c:forEach var="book" items="${books}">
			<tr>
				<td>${book.id}</td>
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.year}</td>
				<td>${book.pages}</td>
				<td>${book.imageUrl}</td>
				<td>${book.price}</td>
				<td><a href="updateBook?id=${book.id}"><input type="button"
						value="Update"></a> <a href="deleteBook?id=${book.id}"><input
						type="button" value="Delete"></a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="signupBook"><input type="button" value="Create books"></a>
</body>
</html>