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
<title>ProductDetailsPage</title>
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
	Integer invnId = (Integer) sess.getAttribute("invnId");
	Integer cartId = (Integer) sess.getAttribute("cartId");
	String name = (String) sess.getAttribute("name");
	%>
	<%
	Integer paintId = (Integer) request.getAttribute("prodId");
	PaintingPersistenceService paintService = new PaintingPersistenceServiceImpl();
	Painting paint = paintService.retrieve(paintId);
	%>
	<button type="button" Name = "Back" value="Back">Go Back</button> 
	<h3>Product Details</h3>
	<h4>Name: <%=paint.getName()%></h4>
	<h5>Description: <%=paint.getDescription()%></h5>
	<h5>Price: <%=paint.getPrice()%></h5>
	<h5>Sold: <%=paint.isSold()%></h5>
	<h5>Canvas Type: <%=paint.getCanvasType()%></h5>
	<h5>Paint Type: <%=paint.getPaintType()%></h5>
	<h5>Length: <%=paint.getLength()%></h5>
	<h5>Width: <%=paint.getWidth()%></h5>
	<br>
</body>
</html>