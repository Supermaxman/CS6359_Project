<%@page import="db.DbManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="script.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ArtKart : Adding a New Product</title>
</head>
<body>
<%out.print("Enter Product Details"); %>
	
	
	
	
	
	<br/>
	
	<form name="productform" action="AddProductController"  method="post">
	<br>
	${message}<br>
	${successMessage}<br>
	
	
	Product Name: <input type="text" name="name" id="name"><br>
	Description : <input type="text" name="description" id="description"><br>
	Price : <input type="text" name="price" id="price"><br>
	Canvas Type : <input type="text" name="canvastype" id="canvastype"><br>
	Paint Type : <input type="text" name="painttype" id="painttype"><br>
	Length : <input type="text" name="length" id="length"><br>
	Width : <input type="text" name="width" id="width"><br>
	<input type="submit" name="submit" value="Add"><br>
	
	
	</form>
</body>
</html>