 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="Resources/css/style.css" />" />
	</head>
	<body>
	<div class="login-block">
		
		<form:form id="loginForm" method="post" action="admin" modelAttribute="model">

			<form:label path="username">Email</form:label>
			<form:input id="username" name="username" path="username" /><br>
			<form:label path="username">Password</form:label>
			<form:password id="password" name="password" path="password" /><br>
			 <button type="submit" value="Submit">Submit</button>
<!-- 			<input type="submit" value="Submit" /> -->
		</form:form>
		<div class = "inf_div"> ${message} </div>
	</div>
	
	
	</body>
</html>