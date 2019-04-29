<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="attemptLogin" value="yes" scope="session" />
<c:if test="${sessionScope.auth == 'authorized'}">
	<c:redirect url="panel.jsp" />
</c:if>
<meta charset="UTF-8">
<title>Login to your Cabinet</title>
</head>
<body style="background-color: grey">
	<form action="panel.jsp" method="POST">
		<div align="center">
			<h2>Login form</h2>
		</div>
		<div align="center" style="color: red">
			<c:out value="${sessionScope.errMessage}" />
		</div>
		<div align="center">
			<table>
				<tbody>
					<tr>
						<td>Email:</td>
						<td><input type="text" name="email"></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="passwd"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div align="center">
			<input type="submit" name="" value="Login">
		</div>
	</form>
	<hr>
	<c:set var="errMessage" value="" scope="session" />
</body>
</html>