<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<c:if test="${logged != 'true'}">
	<c:redirect url="login.jsp" />
</c:if>
<c:if test="${param.action == 'logout'}">
	<c:set var="logged" value="false" scope="session" />
	<c:redirect url="login.jsp" />
</c:if>
<meta charset="ISO-8859-1">
<title>User Panel</title>
</head>
<body style="background-color: grey">
	<div align="center">
		<h1>
			Hello
			<c:out value="${sessionScope.user.name}" />
			!
		</h1>
		<h2 style="color: yellow">Here it is going to be a great e-shop
			so hold on</h2>
		<a href="shop"><input type="button" value="go to shop!"></a>
	</div>
	<div align="right">
		<a href="userPanel.jsp?action=logout"><input type="button"
			value="LogOut"></a>
	</div>
</body>
</html>