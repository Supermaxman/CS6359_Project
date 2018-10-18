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
	<title>Edit Product Details</title>
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
		<a href="faq.jsp" >FAQs</a>
		<a href="logout.jsp" >Logout</a>
 	</div>
 	<hr>
	<%
	Integer prodId = (Integer) request.getAttribute("prodId");
	PaintingPersistenceService paintService = new PaintingPersistenceServiceImpl();
	Painting paint = paintService.retrieve(prodId);
	%>
	<h3>Edit Product Details</h3>
	
	
	<br>
	<% if (!paint.isSold()){ %>
	<form name="saveForm" action="PaintingController" method="post" onsubmit="return paintValidate()">
		
		<h4>Name: <input type="text" name="name" value=<%=paint.getName()%> ></h4>
		<h5>Description: <input type="text" name="description" value=<%=paint.getDescription()%>></h5>
		<h5>Price: <input type="text" name="price" value=<%=paint.getPrice()%>></h5>
		<h5>Sold: <%=paint.isSold()%></h5>
		<h5>Canvas Type: <input type="text" name="canvasType" value=<%=paint.getCanvasType()%>></h5>
		<h5>Paint Type: <input type="text" name="paintType" value=<%=paint.getPaintType()%>></h5>
		<h5>Length: <input type="text" name="length" value=<%=paint.getLength()%>></h5>
		<h5>Width: <input type="text" name="width" value=<%=paint.getWidth()%>></h5>
		<input type="hidden" name="prodId" value="<%= prodId.toString() %>">
		<input class="demo" type="submit" name="SaveDetails" value = "Save Changes" style="left: 460px;">
	</form>
	<form name="cancelForm" action="inventory.jsp" >
		<input class="demo" type="submit" name="CacelDetails" value = "Cancel Changes" style="left: 460px;">
	</form>
	<% } %>
</body>
</html>