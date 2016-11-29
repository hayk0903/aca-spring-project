<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<link type="text/css" rel="stylesheet" href="<c:url value="Resources/css/style.css" />" />
<title>History</title>
</head>
<body>
<ul id="menu-bar">
	<li><a href="/EShop/account" >Account</a></li>
	<li><a href="/EShop/history">Order History</a></li>
	<li class="selected"><a href="/EShop/products">Products</a></li>
	<li ><a href="/EShop/signOut">Sign Out</a></li>
</ul>
	<h1> Order history</h1>
	
	
	<table  class="demo">
	<tr>
    <th>Order ID</th>
    <th>Product ID</th> 
    <th>Order status</th>
    <th>Order price</th>
    <th>Date of delivery</th>
  </tr>
  <c:forEach items="${orderList}" var="order">
    <tr>
      <td><c:out value="${order.orderID}" /></td>
      <td><c:out value="${order.productID}" /></td>
      <td><c:out value="${order.status}" /></td>
      <td><c:out value="${order.price}" /></td>
      <td><c:out value="${order.delivaryDate}" /></td>
    </tr>
  </c:forEach>
</table>

	


</body>
</html>