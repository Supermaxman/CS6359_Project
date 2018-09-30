<%@page import="db.DbManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript" src="script.js"></script>
<title>ArtKart : Product Details</title>
</head>
<body>
 <button type="button" Name = "Back" value="Back">Go Back</button> 
	<h1>Product Details</h1>
	<ul>
		<li>Name</li>
		<%=request.getAttribute("name")%>
		<li>Description</li>
		<%=request.getAttribute("desc")%>
		<li>Price</li>
		<%=request.getAttribute("price")%>
		<li>Seller</li>
		<%-- =request.getAttribute("seller")--%>
	<%-- 	<li>isSold</li> --%>
 		<%=request.getAttribute("sold")%>
		<li>canvasType</li>
		<%=request.getAttribute("canvType")%>
		<li>paintType</li>
		<%=request.getAttribute("paintType")%>
		<li>length</li>
		<%=request.getAttribute("leng")%>
		<li>width</li>
		<%=request.getAttribute("width")%>
	</ul>
	<a href="logout.jsp">Logout</a>
</body>
</html>