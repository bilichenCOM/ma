<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>

<c:if
	test="${(empty param.email or empty param.passwd) and sessionScope.auth != 'authorized'}">
	<c:set var="errMessage" value="please enter email and password"
		scope="session" />
	<c:redirect url="login.jsp" />
</c:if>

<c:if test="${param.action == 'logout'}">
	<c:set var="auth" value="" scope="session" />
	<c:redirect url="login.jsp" />
</c:if>

<meta charset="ISO-8859-1">
<title>Panel</title>
</head>
<body style="background-color: grey">
	<sql:setDataSource var="cabinet_db" driver="org.postgresql.Driver"
		url="jdbc:postgresql://localhost:5432/cabinet_db" user="postgres"
		password="admin" />

	<!-- check login and password query -->
	<sql:query dataSource="${cabinet_db}" var="result">
	SELECT *, COUNT(*) as cnt FROM public.users
	WHERE email='${param.email}'
	AND passwd='${param.passwd}'
	GROUP BY email
	</sql:query>

	<c:forEach items="${result.rows}" var="row">

	<sql:query dataSource="${cabinet_db}" var="role_name">
	SELECT name FROM roles
	WHERE id = '${row.role_id}'
	</sql:query>
		<c:forEach items="${role_name.rows}" var="role_row">
			<c:set var="rname" value="${role_row.name}" />
		</c:forEach>
		<c:set var="auth" value="authorized" scope="session" />
		<c:set var="role" value="${row.role_id}" />

		<h1 align="center">
			Hello
			<c:out value="${rname}" />
			<c:out value="${row.user_name}" />
		</h1>
	</c:forEach>

	<c:if test="${sessionScope.auth != 'authorized'}">
		<c:set var="errMessage"
			value="wrong email or password, please try again..." scope="session" />
		<c:redirect url="login.jsp" />
	</c:if>

	<table border="1">
		<tr>
			<th>Email</th>
			<th>Name</th>
			<th>Surname</th>
			<th>Age</th>
			<th>Password</th>
			<th>Role</th>
			<th>Action</th>
		</tr>
		<c:choose>
			<c:when test="${role ne 3 and role ne null}">
				<sql:query dataSource="${cabinet_db}" var="result">
					SELECT *, roles.name AS role_name FROM public.users
					LEFT JOIN roles ON role_id = roles.id;
					</sql:query>

				<c:forEach var="row" items="${result.rows}">
					<tr>
						<td><c:out value="${row.email}" /></td>
						<td><c:out value="${row.user_name}" /></td>
						<td><c:out value="${row.user_surname}" /></td>
						<td><c:out value="${row.user_age}" /></td>
						<td><c:out value="${row.passwd}" /></td>
						<td><c:out value="${row.role_name}" /></td>
						<td><c:if test="${role eq 1}">
								<a href="update?email=<c:out value="${row.email}"/>"><input
									type="button" name="" value="update"></a>
								<a href="delete?email=<c:out value="${row.email}"/>"><input
									type="button" name="" value="delete"></a>
							</c:if></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td><c:out value="${row.email}" /></td>
					<td><c:out value="${row.user_name}" /></td>
					<td><c:out value="${row.user_surname}" /></td>
					<td><c:out value="${row.user_age}" /></td>
					<td><c:out value="${row.passwd}" /></td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<a href="signup.jsp"><input type="button" value="Add user"></a>
	<div align="right">
		<a href="panel.jsp?action=logout"><input type="button"
			value="LogOut"></a>
	</div>
	<hr>

</body>
</html>