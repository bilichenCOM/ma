<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<!-- login check expressions -->
<c:set var="log_value" value="true"/>
<c:if test="${sessionScope.logged != log_value}"><c:redirect url="login.jsp"/></c:if>
<!-- end of login check -->
<meta charset="ISO-8859-1">
<title>Update user</title>
</head>
<body>
<div align="center"><h2>Update user <c:out value="${param.email}" /> data</h2><br></div>
<div align="center" style="color:red"><c:out value="${sessionScope.errUpdateMessage}" /></div>
<div align="center" style="color:green"><c:out value="${sessionScope.successUpdateMessage}" /></div>
<form action="update" method="POST">
<input type="hidden" name="email" value="<c:out value="${param.email}" />">
<div align="center">
<table>
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
		<input type="submit" value="Update">
	</td>
</tr>
</table>
</div>
</form>
<c:set var="errUpdateMessage" value="" scope="session" />
<c:set var="successUpdateMessage" value="" scope="session" />
</body>
</html>