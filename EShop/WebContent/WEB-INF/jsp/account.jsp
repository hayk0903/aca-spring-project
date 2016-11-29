<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<link type="text/css" rel="stylesheet" href="<c:url value="Resources/css/style.css" />" />
<title>Login</title>
</head>
<body>

<ul id="menu-bar">
	<li><a href="/EShop/account" >Account</a></li>
	<li><a href="/EShop/history">Order History</a></li>
	<li class="selected"><a href="/EShop/products">Products</a></li>
	<li ><a href="/EShop/signOut">Sign Out</a></li>
</ul>
	<h1>Balance: ${up.account.balance}</h1>
	
	<div class="form">
		<form:form id="accountForm" method="post" action="account" modelAttribute="balance">

			<form:label path="ammount">Fill Balance</form:label>
			<form:input id="ammount" name="ammount" path="ammount" /><br>
			
			 <button type="submit" value="Submit">Submit</button>

		</form:form>
		<div class = "inf_div"> ${account_message} </div>
	</div>
	
	


</body>
</html>