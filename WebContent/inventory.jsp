<%@ page import="java.util.*" %>    
<%@page import="domain.user.Inventory"%>
<%@page import="db.services.InventoryPersistenceService"%>
<%@page import="db.services.impl.InventoryPersistenceServiceImpl"%>
<%@page import="domain.product.Product"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Inventory</title>
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
 	<div class="addProd" align = "Center">
 	<b><h4>INVENTORY</h4></b>
 	<form>
	<a name = "Painting" href="newproduct.jsp?catId=1">Add a painting</a>
	<a name = "Sculpture" href="newproduct.jsp?catId=2">Add a sculpture</a>
	<a name = "Craft" href="newproduct.jsp?catId=3">Add a craft</a>
	</form>
	</div>
	<hr>
	
 	<h4>Items in Inventory:</h4>
	 <%
	InventoryPersistenceService invnService = new InventoryPersistenceServiceImpl();
	Inventory invn = invnService.retrieve(userId);
	
	List<Product> prods = invn.getProducts();
	
	if (prods.size() > 0){
	
	%>
	<table border="1" style="margin-top: 20px; margin-right: 20px; margin-left: 29px; border-top-width: 2px;">
	<col width="100">
 	<col width="130">
 	<col width="70">
  	<col width="70">
  	<col width="100">	
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Sold</th>
			<th>Action</th>   
		</tr>
	     
		<%for(Product prod : prods) {%>
			<tr>
				<td><%= prod.getName() %></td>
				<td><%= prod.getDescription() %></td>
				<td><%= prod.getPrice() %></td>
				<td><%= prod.isSold() %></td>
				
				<td>
					<form name="detailsform" action="DetailsController" method="post">
						<input type="hidden" name="prodId" value="<%= prod.getProdId().toString() %>">
						<input type="hidden" name="catId" value="<%= prod.getCategory().getCatId().toString() %>">
						<input class="demo" type="submit" name="ViewDetails" value = "View Details" style="left: 460px;">
					</form>	
					<form name="editform" action="EditController" method="post">
						<input type="hidden" name="prodId" value="<%= prod.getProdId().toString() %>">
						<input type="hidden" name="catId" value="<%= prod.getCategory().getCatId().toString() %>">
						<input class="demo" type="submit" <%=prod.isSold() ? "disabled=\"\"" : "" %> name="EditDetails" value = "Edit Details" style="left: 460px;">
					</form>
				</td>
			</tr>
		<%}%>
	</table>
	<br>
	<h4>Total Earnings till date:</h4>
	<table border="1" style="margin-top: 20px; margin-right: 20px; margin-left: 29px; border-top-width: 2px;">
	<col width="130">
  	<col width="80">
  	
	<tr>
	<th>Sale Type</th>
	<th>Amount</th>
	</tr>
	<tr>
	<td>Painting</td>
	<%
	double total = 0.0;
	double catTotal = 0.0;
	for(Product prod : prods) {
	if(prod.getCategory().getCatId() == 1 && prod.isSold())
	{
		catTotal = catTotal+prod.getPrice();
	}
	}
	total = total+catTotal;
	
	%>
	<td><%= catTotal %></td>
	</tr>
	<tr>
	<td>Sculpture</td>
	<%
	catTotal = 0;
	for(Product prod : prods) {
	if(prod.getCategory().getCatId() == 2 && prod.isSold())
	{
		catTotal = catTotal+prod.getPrice();
	}
	}
	total = total+catTotal;
	
	%>
	<td><%= catTotal %></td>
	</tr>
	<tr>
	<td>Craft</td>
	<%
	catTotal = 0;
	for(Product prod : prods) {
	if(prod.getCategory().getCatId() == 3 && prod.isSold())
	{
		catTotal = catTotal+prod.getPrice();
	}
	}
	total = total+catTotal;

	%>
	<td><%= catTotal %></td></tr>
	<tr>
	<td align = "right"><b>Total</b></td>
	<td align = "right"><b><%= total %></b></td>
	</tr>
	</table>
	<% } else {%>
		<p> Your Inventory is empty. </p>
	<%}%>
	
</body>
</html> 