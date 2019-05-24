<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Card</title>
</head>
<body>
	<div align="right">
		Your balance:
		<div style="color: green">${shopSession.user.balance} &#8372;</div>
	</div>

	<h2 align="center">Your items in card:</h2>
	<div align="center" style="color: red">${sessionScope.errMessage}</div>
	<c:set var="errMessage" value="" scope="session"/>
	<div align="center" style="color: green">${sessionScope.successMessage}</div>
	<c:set var="successMessage" value="" scope="session"/>
	<table>
		<tr>
			<th>Photo</th>
			<th>Title</th>
			<th>Description</th>
		</tr>
		<c:if test="${shopSession.user.card.size eq 0}">
			<tr> 
			<td>No goods on your card... please go to shop and add something </td>
			</tr>
		</c:if>
		<c:forEach var="good" items="${shopSession.user.card.goods}">
			<tr>
				<td><img alt="photo" src="${good.imageUrl}" height="252"
					width="200"></td>
				<td> ${good.type} </td>
				<td> ${good.description} </td>
			</tr>
		</c:forEach>
	</table>
	<div align="center"><a href="/Cabinet/shop/card/buy"><input type="button" value="Purchase All!"></a><br>
		<a href="/Cabinet/shop"><input type="button" value="Back to shop"></a>
	</div>
</body>
</html>