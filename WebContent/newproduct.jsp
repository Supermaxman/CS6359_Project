<%@ page import="java.util.*" %>    
<%@ page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src="script.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>New Product</title>
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
	String val="";
	if(Integer.valueOf(request.getParameter("id"))==1)
	
	{
	%>
	<h4 align="left"> Create Painting: </h4>
	<form name="newpaintform" action="PaintingController" method="post" onsubmit="return paintValidate()">
	
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
		<input type="submit" name="submit" value="create" >
		</form>
		<% 
	}
	else if (Integer.valueOf(request.getParameter("id"))==2)
	{
		%>
		<h4 align="left"> Create Painting: </h4>
		<form name="newpaintform" action="SculptureController" method="post" onsubmit="return paintValidate()">
		
		Name: <input type="text" name="name" id="name">
		<br>
		Description: <input type="text" name="description" id="description">
		<br>
		Price: <input type="number" min="0" name="price" id="price">
		<br>
		Length: <input type="number" min="0" name="length" id="length">
		<br>
		Width: <input type="number" min="0" name="width" id="width" >
		<br>
		Height: <input type="number" min="0" name="height" id="height" >
		<br>
		Material: <input type="text"  name="material" id="material" >
		<br>
		Weight: <input type="number" min="0" name="weight" id="weight" >
		<br>
		<input type="submit" name="submit" value="create" >
		<br>
		</form>
		
		<% 
	}
	else if(Integer.valueOf(request.getParameter("id"))==3)
	{
		
	
	%>
	<h4 align="left"> Create Painting: </h4>
	<form name="newpaintform" action="CraftController" method="post" onsubmit="return paintValidate()">
	Name: <input type="text" name="name" id="name">
		<br>
		Description: <input type="text" name="description" id="description">
		<br>
		Price: <input type="number" min="0" name="price" id="price">
		<br>
		Usage: <input type="text"  name="usage" id="usage">
		<br>
		<input type="submit" name="submit" value="create" >
		<br>
		
		<% 
	}
	%>
		
	</form>	
</body>
</html> 