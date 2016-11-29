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
	<h1> Products</h1>
	
	<table class = "demo">
	<tr>
    <th>Product ID</th> 
    <th>Type</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Available</th>
  </tr>
  <c:forEach items="${productList}" var="product">
    <tr>
      <td><c:out value="${product.productID}" /></td>
      <td><c:out value="${product.type}" /></td>
      <td><c:out value="${product.price}" /></td>
      <td><c:out value="${product.quantity}" /></td>
      <td><c:out value="${product.available}" /></td>
    </tr>
  </c:forEach>
</table>

	<div class="form">
	
		<form:form id="makeorder" method="post" action="manage" modelAttribute="product">

			<form:label path="productID">product ID</form:label>
			<form:input id="productID" name="productID" path="productID" /><br>
			<form:label path="quantity">Quantity</form:label>
			<form:input id="quantity" name="quantity" path="quantity" /><br>
			<form:label path="available" for = "available">Available for sale</form:label>
			<div class="styled-select">
			<form:select path="available" id = "available">
				<option value = "yes">yes</option>
				<option value = "no">no</option>
			</form:select>
			</div>
			 <button type="submit" value="Submit">Submit</button>

		</form:form>
		<div class = "inf_div"> ${manage_message}</div>
	</div>


</body>
</html>