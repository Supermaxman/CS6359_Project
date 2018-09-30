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
<h3 align="center" style="color:brown;"> ${message}, Welcome to ArtKart! </h3>
<h4 align="left";"> We hope you have a great shopping experience! </h4>
<hr>
<div class="menu" align = "Center">
<a href="inventory.jsp" >Inventory</a>
<a href="category.jsp" >Category</a>
<a href="cart.jsp" >Cart</a>

</div>
<div class="content" align="right">
	<p>${message}</p>
	<a href="logout.jsp" >logout</a>
</div>
</body>
</html>