<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
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
		<a href="/Cabinet/shop"><input type="button" value="go to shop!"></a>
	</div>
	<div align="right">
		<a href="logout"><input type="button"
			value="LogOut"></a>
	</div>
</body>
</html>