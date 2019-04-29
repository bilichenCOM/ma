<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<!-- login check expressions -->
<c:set var="log_value" value="yes" />
<c:if test="${sessionScope.attemptLogin != log_value}">
	<c:redirect url="login.jsp" />
</c:if>
<!-- end of login check -->

<c:if test="${param.action == 'logout'}">
	<c:set var="auth" value="" scope="session" />
	<c:redirect url="login.jsp" />
</c:if>

<meta charset="ISO-8859-1">
<title>Panel</title>
</head>
<body style="background-color: grey">
	<c:if
		test="${(empty param.email or empty param.passwd) and sessionScope.auth != 'authorized'}">
		<c:set var="errMessage" value="please enter email and password"
			scope="session" />
		<c:redirect url="login.jsp" />
	</c:if>
	<sql:setDataSource var="cabinet_db" driver="org.postgresql.Driver"
		url="jdbc:postgresql://localhost:5432/cabinet_db" user="postgres"
		password="admin" />

	<sql:query dataSource="${cabinet_db}" var="user">
	SELECT count(*) as cnt FROM public.users
	WHERE email='${param.email}'
	AND passwd='${param.passwd}'
	</sql:query>

	<c:forEach items="${user.rows}" var="row">
		<c:choose>
			<c:when test="${row.cnt eq 0 and sessionScope.auth != 'authorized'}">
				<c:set var="errMessage"
					value="wrong email or password, please try again..."
					scope="session" />
				<c:redirect url="login.jsp" />
			</c:when>
			<c:otherwise>
				<c:set var="auth" value="authorized" scope="session" />
				<h1 align="center">
					Hello
					<c:out value="${param.email}" />
				</h1>
				<sql:query dataSource="${cabinet_db}" var="rs">
				SELECT * FROM public.users;
				</sql:query>
				<table border="1">
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
								<td><a
									href="update_panel.jsp?email=<c:out value="${user.email}"/>"><input
										type="button" name="" value="update"></a> <a
									href="delete?email=<c:out value="${user.email}"/>"><input
										type="button" name="" value="delete"></a></td>
							</tr>
						</c:forEach>
					</tr>
				</table>
				<a href="signup.html"><input type="button" value="Add user"></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<div align="right"><a href="panel.jsp?action=logout"><input type="button" value="LogOut"></a></div>
	<hr>
</body>
</html>