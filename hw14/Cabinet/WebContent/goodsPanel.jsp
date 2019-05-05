<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:if test="${logged != 'true'}">
	<c:redirect url="login.jsp" />
</c:if>
<meta charset="ISO-8859-1">
<title>Shop</title>
</head>
<body>
	<div align="left">Hello ${shopSession.user.name}
	<a href="logout"><input type="button" value="Logout"></a></div><br>
	<div align="right">Your balance: <div style="color:green">${shopSession.user.balance}</div></div>
	<h2>Your shop page</h2>
	<p style="color:red">${errMessage}</p>
	<p style="color:green">${successMessage}</p>
	<div align="center">
		<table>
			<tr>
				<th>Photo</th>
				<th>Title</th>
				<th>Description</th>
				<th>Action</th>
			</tr>
			<c:forEach var="book" items="${shopSession.books}">
				<tr>
					<td><img alt="photo" src="${book.imageUrl}" height="252" width="200"></td>
					<td>${book.title}</td>
					<td>Price: ${book.price} <br> Author: ${book.author} <br>
						Year: ${book.year} <br> Pages: ${book.pages} <br> ID:
						${book.id}
					</td>
					<td><a href="buy?bookId=${book.id}"><input type="button"
							value="Buy!"></a></td>
			</c:forEach>
		</table>
	</div>
</body>
</html>