<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%@ page import="domain.product.Painting" %>
<%@ page import="domain.product.Product" %>
<%@page import="db.services.PaintingPersistenceService"%>
<%@page import="db.services.impl.PaintingPersistenceServiceImpl"%>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Product Details</title>
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
<body>
	<% 
	HttpSession sess = request.getSession(true);
	Integer userId = (Integer) sess.getAttribute("userId");
	if (userId == null){
		response.sendRedirect("login.jsp");
		return;
	}
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
		<a href="about.jsp">About</a>
		<a href="faq.jsp" >FAQs</a>
		<a href="logout.jsp" >Logout</a>
 	</div>
 	<hr>
	<%
	Integer prodId = (Integer) request.getAttribute("prodId");
	
	String category = request.getAttribute("category"); 
	if (category == "painting"){
		PaintingPersistenceService paintService = new PaintingPersistenceServiceImpl();
		Painting paint = paintService.retrieve(prodId); %>
		<table>
			<tr><th>Product Details</th></tr>
			<tr><td>Name: </td><td><%=paint.getName()%></td></tr>
			<tr><td>Description: </td><td><%=paint.getDescription()%></td></tr>
			<tr><td>Price: </td><td><%=paint.getPrice()%></td></tr>
			<tr><td>Sold: </td><td><%=paint.isSold()%></td></tr>
			<tr><td>Canvas Type: </td><td><%=paint.getCanvasType()%></td></tr>
			<tr><td>Paint Type: </td><td><%=paint.getPaintType()%></td></tr>
			<tr><td>Length: </td><td><%=paint.getLength()%></td></tr>
			<tr><td>Width: </td><td><%=paint.getWidth()%></td></tr>
		</table>
	} else if (category == "craft") {
		
	}
	
	PaintingPersistenceService paintService = new PaintingPersistenceServiceImpl();
	Painting paint = paintService.retrieve(prodId);
	Product prod = null;
	Boolean isPainting = prod instanceof Painting;
	%>
	<table>
		<tr><th>Product Details</th></tr>
		<tr><td>Name: </td><td><%=paint.getName()%></td></tr>
		<tr><td>Description: </td><td><%=paint.getDescription()%></td></tr>
		<tr><td>Price: </td><td><%=paint.getPrice()%></td></tr>
		<tr><td>Sold: </td><td><%=paint.isSold()%></td></tr>
		<% if (isPainting) {%><tr><td>Canvas Type: </td><td><%=paint.getCanvasType()%></td></tr><% } %>
		<tr><td>Paint Type: </td><td><%=paint.getPaintType()%></td></tr>
		<tr><td>Length: </td><td><%=paint.getLength()%></td></tr>
		<tr><td>Width: </td><td><%=paint.getWidth()%></td></tr>
	</table>
	<br>
	<% if (!paint.isSold()){ %>
	<form name="addCartForm" action="CartController" method="post">
		<input type="hidden" name="prodId" value="<%= prodId.toString() %>">
		<input class="demo" type="submit" name="AddToCart" value = "Add to Cart" style="left: 460px;">
	</form>
	<% } %>
</body>
</html>