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
	<li><a href="/EShop/account" >Account</a></li>
	<li><a href="/EShop/history">Order History</a></li>
	<li class="selected"><a href="/EShop/products">Products</a></li>
	<li ><a href="/EShop/signOut">Sign Out</a></li>
</ul>
	<h1> Products</h1>
	
	<table class = "demo">
	<tr>
    <th>Product ID</th> 
    <th>Type</th>
    <th>Price</th>
    <th>Quantity</th>
  </tr>
  <c:forEach items="${productList}" var="product">
    <tr>
      <td><c:out value="${product.productID}" /></td>
      <td><c:out value="${product.type}" /></td>
      <td><c:out value="${product.price}" /></td>
      <td><c:out value="${product.quantity}" /></td>
    </tr>
  </c:forEach>
</table>

	<div class="form">
	
		<form:form id="makeorder" method="post" action="order" modelAttribute="order">

			<form:label path="productID">product ID</form:label>
			<form:input id="productID" name="productID" path="productID" /><br>
			<form:label path="date">Delivery Date</form:label>
			<form:input id="date" name="date" path="date" /><br>
			 <button type="submit" value="Submit">Buy</button>

		</form:form>
		<div class = "inf_div"> ${order_message} </div>
	</div>


</body>
</html>