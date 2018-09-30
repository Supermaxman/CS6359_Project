<%@ page import="java.util.*" %>    
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NewProductPage</title>
</head>
<style>
.content {
    max-width: 1000px;
    margin: auto;
    background: white;
    padding: 50px;
    line-height: 0.3;
}
</style> 
<script type="text/javascript" >

</script>
</head>
<body>
	<% 
	HttpSession sess = request.getSession(true);
	Integer userId = (Integer) sess.getAttribute("userId");
	Integer invnId = (Integer) sess.getAttribute("invnId");
	Integer cartId = (Integer) sess.getAttribute("cartId");
	String name = (String) sess.getAttribute("name");
	%>
	<div class="menu" align = "Center">
		<a href="home.jsp">Home</a>
		<a href="category.jsp">Category</a>
		<a href="cart.jsp">Cart</a>
		<a href="inventory.jsp">Inventory</a>
		<a href="transactions.jsp">Transactions</a>
		<a href="logout.jsp" >Logout</a>
 	</div>
 	<hr>
	<h4 align="left"> Create Painting: </h4>
	<br>
	<form name="paintingForm" action="PaintingController" method="post" onsubmit="return paintValidate()" >
	<br>
	Name: <input type="text" name="name" id="name">
	<br>
	Description: <input type="text" name="description" id="description">
	<br>
	Price: <input type="number" min="0" name="price" id="price">
	<br>
	Canvas Type: <input type="text" name="canvasType" id="canvasType">
	<br>
	Paint Type: <input type="text" name="paintType" id="paintType">
	<br>
	Length: <input type="number" min="0" name="length" id="length">
	<br>
	Width: <input type="number" min="0" name="width" id="width" >
	<br>
	<input type="submit" name="submit" value="create">
	<br>
	<a href="inventory.jsp">Inventory</a>
	</form>
	
</body>
</html> 