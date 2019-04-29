<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration form</title>
</head>
<body style="background-color: grey">
<form action="signup" method="POST">
<div align="center"><h2>Register new user</h2></div>
<div align="center" style="color:red"><c:out value="${errSignupMessage}" /></div>
<div align="center" style="color:green"><c:out value="${successSignupMessage}" /></div>
<div align="center">
<table title="">
<tbody>
<tr>
<td>
Name:
</td>
<td><input type="text" maxlength="20" name="user_name">
</td>
</tr>
<tr>
<td>
Surname:
</td>
<td>
<input type="text"  name="user_surname" maxlength="20">
</td>
</tr>
<tr>
<td>
Age:
</td>
<td>
<input type="text" maxlength="3" name="user_age">
</td>
</tr>
<tr>
<td>
Gender:
</td>
<td>
<input type="radio" name="user_gender" value="female">Female<input type="radio" name="user_gender" value="male">Male
</td>
</tr>
<tr>
<td>
Email:
</td>
<td>
<input type="text" name="email">
</td>
</tr>
<tr>
<td>
Password:
</td>
<td>
<input type="password" name="passwd">
</td>
</tr>
<tr>
<td>
</td>
<td>
<input type="submit" name="" value="Register">
</td>
</tr>
</tbody>
</table>
</div>
</form>
<hr>
<c:set var="errSignupMessage" value="" scope="session" />
<c:set var="successSignupMessage" value="" scope="session" />
</body>
</html>