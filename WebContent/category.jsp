<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Category</title>
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
		<a href="logout.jsp" >Logout</a>
 	</div>
 	<hr>
	<h3 align="center" style="color:brown;"> Choose a category. </h3>
	<h4 align="left"> Select from a list of product categories: </h4>
	<hr>
	<div class="menu" align = "Center">
	<a href="paintings.jsp" >Paintings</a>
	<a href="sculptures.jsp">Sculptures</a>
	<a href="craft.jsp">Craft</a>
	 </div>
</body>
</html> 