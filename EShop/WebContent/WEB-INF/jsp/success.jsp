<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<link type="text/css" rel="stylesheet" href="<c:url value="Resources/css/style.css" />" />
<title>MVC</title>
</head>
<body>
<ul id="menu-bar">
	<li><a href="/EShop/account" >Account</a></li>
	<li><a href="/EShop/history">Order History</a></li>
	<li class="selected"><a href="/EShop/products">Products</a></li>
	<li ><a href="/EShop/signOut">Sign Out</a></li>
</ul>

	<h1>Welcome ${up.user.firstName}</h1>
	<h2>Last logged in ${up.account.lastLoginDate }</h2>
	

	


</body>
</html>