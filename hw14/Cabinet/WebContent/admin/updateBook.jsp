<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Book info</title>
</head>
<body>
<div align="center"><h2>Update book info for <c:out value="${book.title}" /></h2></div>
<div align="center" style="color: red">
		<c:out value="${errMessage}" />
	</div>
	<div align="center" style="color: green">
		<c:out value="${successMessage}" />
	</div>
	<form action="updateBook" method="POST">
		<input type="hidden" name="id" value="${book.id}" />
		<div align="center">
			<table>
				<tr>
					<td>Title:</td>
					<td><input type="text" maxlength="100" name="title" value="${book.title}">
					</td>
				</tr>
				<tr>
					<td>Author:</td>
					<td><input type="text" maxlength="50" name="author" value="${book.author}">
					</td>
				</tr>
				<tr>
					<td>Year:</td>
					<td><input type="text" maxlength="4" name="year" value="${book.year}">
					</td>
				</tr>
				<tr>
					<td>Pages:</td>
					<td><input type="text" name="pages" value="${book.pages}">
					</td>
				</tr>
				<tr>
					<td>Image URL:</td>
					<td><input type="text" name="imageUrl" value="${book.imageUrl}">
					</td>
				</tr>
				<tr>
					<td>Price:</td>
					<td><input type="text" name="price" value="${book.price}">
					</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Update"></td>
				</tr>
			</table>
		</div>
	</form>
	<div align="left"><a href="adminPanel.jsp">Back</a></div>
</body>
</html>