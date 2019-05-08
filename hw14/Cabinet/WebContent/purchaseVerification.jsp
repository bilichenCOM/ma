<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Verify your purchase</title>
</head>
<body>
	<div align="center">
		<h2>Please enter verification code:</h2>
		<p>code has been sent to email ${user.email}</p>
		<form action="purchaseVerification" method="POST">
		<input type="hidden" value="${bookId}" name="bookId">
			<input type="hidden" name="verCode" value="${verCode}"> <input
				type="text" name="code"> <input type="submit"
				value="Send confirmation">
		</form>
	</div>
</body>
</html>