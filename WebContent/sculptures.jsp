<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%@ page import="domain.product.Sculpture" %>
<%@ page import="domain.product.Product" %>
<%@page import="db.services.SculpturePersistenceService"%>
<%@page import="db.services.impl.SculpturePersistenceServiceImpl"%>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Sculptures</title>
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
		<a href="userprofile.jsp" >User Profile</a>
		<a href="logout.jsp" >Logout</a>
 	</div>
 	<hr>
	<h4>Sculptures:</h4>
   <% 
   SculpturePersistenceService sculptureService = new SculpturePersistenceServiceImpl();
   List<Sculpture> sculptures = sculptureService.retrieveAll();

   int forSaleCount = 0;
	for (Product prod : sculptures){
		if (!prod.isSold()){
			forSaleCount ++;
		}
	}
   if (forSaleCount > 0){
		
   	%>  
   	<table border="1" style="margin-top: 20px; margin-right: 20px; margin-left: 29px; border-top-width: 2px;">
     	<tr>
       		<th>Name</th>
       		<th>Description</th>
       		<th>Price</th>
       		<th>Action</th>   
   		</tr>
     
     	<%for(Sculpture prod : sculptures) {%>
			<% if (!prod.isSold()){ %>
			<tr>
			<td><%= prod.getName() %></td>
			<td><%= prod.getDescription() %></td>
			<td><%= prod.getPrice() %></td>
			<td>
				<form name="detailsform" action="DetailsController" method="post">
					<input type="hidden" name="prodId" value="<%= prod.getProdId().toString() %>">
					<input type="hidden" name="catId" value="<%= prod.getCategory().getCatId().toString() %>">
					<input class="demo" type="submit" name="ViewDetails" value = "View Details" style="left: 460px;">
				</form>
				<form name="editform" action="UserProfileController" method="post">
						<input type="hidden" name="prodId" value="<%= prod.getProdId().toString() %>">
						<input type="hidden" name="catId" value="<%= prod.getCategory().getCatId().toString() %>">
						<input class="demo" type="submit" <%=prod.isSold() ? "disabled=\"\"" : "" %> name="UserProfile" value = "View User" style="left: 460px;">
				</form>
			</td>
			</tr>
			<%}%>
		<%}%>
   	</table>
   	<br>
	<% } else {%>
		<p> We currently have no products for sale under this category. Bye!</p>
	<%}%>
</body>
</html>