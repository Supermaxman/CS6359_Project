<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HomePage</title>
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
	<h3 align="center" style="color:brown;"> <%out.print(name);%>, Welcome to ArtKart! </h3>
	<h4 align="left"> We hope you have a great shopping experience! </h4>
	<hr>
	<div class="menu" align = "Center">
		<a href="inventory.jsp">Inventory</a>
		<a href="category.jsp">Category</a>
		<a href="cart.jsp">Cart</a>
 	</div>
	<div class="content" align="right">
		<p><%out.print(name);%></p>
		<a href="logout.jsp" >logout</a>
	</div>
</body>
</html> 