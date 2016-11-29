<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<link type="text/css" rel="stylesheet" href="<c:url value="Resources/css/style.css" />" />
<title>Products</title>
</head>
<body>
<ul id="menu-bar">
	<li><a href="/EShop/manage" >Manage Products</a></li>
	<li><a href="/EShop/addproduct">Add Products</a></li>
	<li class="selected"><a href="/EShop/deliver">Distribution</a></li>
	<li ><a href="/EShop/signOut">Sign Out</a></li>
</ul>
	<h1> Orders Available</h1>
	
	<table class = "demo">
	<tr>
    <th>Order ID</th> 
    <th>User ID</th>
    <th>Product ID</th> 
    <th>Status</th>
    <th>Price</th>
    <th>Delivery Date</th>
    <th>Latitude</th>
    <th>Longitude</th>
  </tr>
  <c:forEach items="${orderList}" var="order">
    <tr>
      <td><c:out value="${order.orderID}" /></td>
      <td><c:out value="${order.userID}" /></td>
      <td><c:out value="${order.productID}" /></td>
      <td><c:out value="${order.status}" /></td>
      <td><c:out value="${order.price}" /></td>
      <td><c:out value="${order.delivaryDate}" /></td>
      <td><c:out value="${order.geolat}" /></td>
      <td><c:out value="${order.geolong}" /></td>
    </tr>
  </c:forEach>
</table>

<a href= "distribute">Make distribution</a>

<h1> Orders Delivered</h1>
	
	<table class = "demo">
	<tr>
    <th>Order ID</th> 
    <th>User ID</th>
    <th>Product ID</th> 
    <th>Status</th>
    <th>Price</th>
    <th>Delivery Date</th>
    <th>Latitude</th>
    <th>Longitude</th>
  </tr>
  <c:forEach items="${deliveredOrders}" var="order">
    <tr>
      <td><c:out value="${order.orderID}" /></td>
      <td><c:out value="${order.userID}" /></td>
      <td><c:out value="${order.productID}" /></td>
      <td><c:out value="${order.status}" /></td>
      <td><c:out value="${order.price}" /></td>
      <td><c:out value="${order.delivaryDate}" /></td>
      <td><c:out value="${order.geolat}" /></td>
      <td><c:out value="${order.geolong}" /></td>
    </tr>
  </c:forEach>
</table>





</body>
</html>