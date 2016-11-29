 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>SIgn Up</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="Resources/css/style.css" />" />
	</head>
	<body>
	<div class="login-block">
		<form:form id="loginForm" method="post" action="register" modelAttribute="model">

			<form:label path="firstName">First Name</form:label>
			<form:input id="firstName" name="firstName" path="firstName" /><br>
			<form:label path="lastName">Last Name</form:label>
			<form:input id="lastName" name="lastName" path="lastName" /><br>
			<form:label path="geolat">Latitude</form:label>
			<form:input id="geolat" name="geolat" path="geolat" /><br>
			<form:label path="geolong">Longitude</form:label>
			<form:input id="geolong" name="geolong" path="geolong" /><br>
			<form:label path="email">Email</form:label>
			<form:input id="email" name="email" path="email" /><br>
			<form:label path="password">Password</form:label>
			<form:password id="password" name="password" path="password" /><br>
			 <button type="submit" value="Submit">Sign up</button>

		</form:form>
		<div class = "inf_div"> ${signup_message}</div>
	</div>
	
	</body>
</html>