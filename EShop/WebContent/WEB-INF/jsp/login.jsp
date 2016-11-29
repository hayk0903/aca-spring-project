 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="Resources/css/style.css" />" />
	</head>
	<body>
	<div class="login-block">
		
		<form:form id="loginForm" method="post" action="login" modelAttribute="model">
			<form:label path="username">Email</form:label>
			<form:input id="username" name="username" path="username" /><br>
			<form:label path="username">Password</form:label>
			<form:password id="password" name="password" path="password" /><br>
			 <button type="submit" value="Submit">Submit</button>
		</form:form>
		<div class = "inf_div"> ${login_message} </div>
	</div>
	
	
	</body>
</html>