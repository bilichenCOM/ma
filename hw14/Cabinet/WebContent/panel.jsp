<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<!-- login check expressions -->
<c:set var="log_value" value="true"/>
<c:if test="${sessionScope.logged != log_value}"><c:redirect url="login.html"/></c:if>
<!-- end of login check -->
<meta charset="ISO-8859-1">
<title>Panel</title>
</head>
<body>
<sql:setDataSource var="cabinet_db"
	driver="org.postgresql.Driver"
	url="jdbc:postgresql://localhost:5432/cabinet_db"
	user="postgres"
	password="admin"/>
<sql:query dataSource="${cabinet_db}" var="rs">
SELECT * FROM public.users;
</sql:query>
<table border="2" width="100%">
	<tr>
	<th>Email</th>
	<th>Name</th>
	<th>Surname</th>
	<th>Age</th>
	<th>Password</th>
	<th>Action</th>
	</tr>
	<tr>
	<c:forEach var="user" items="${rs.rows}">
	<tr>
		<td><c:out value="${user.email}" /></td>
		<td><c:out value="${user.user_name}" /></td>
		<td><c:out value="${user.user_surname}" /></td>
		<td><c:out value="${user.user_age}" /></td>
		<td><c:out value="${user.passwd}" /></td>
		<td>
			<a href="update_panel.jsp?email=<c:out value="${user.email}"/>"><input type="button" name="" value="update"></a>
			<a href="delete?email=<c:out value="${user.email}"/>"><input type="button" name="" value="delete"></a>
		</td>
	</tr>
	</c:forEach>
	</tr>
</table>
<a href="signup.html"><input type="button" value="Add user"></a>
</body>
</html>