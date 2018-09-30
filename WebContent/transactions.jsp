<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%@ page import="domain.transaction.Transaction" %>
<%@ page import="domain.product.Product" %>
<%@page import="db.services.TransactionPersistenceService"%>
<%@page import="db.services.impl.TransactionPersistenceServiceImpl"%>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>TransactionsPage</title>
 <script>
 </script>   
</head>
<body>
<% 
	HttpSession sess = request.getSession(true);
	Integer userId = (Integer) sess.getAttribute("userId");
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
   
   <% 
   TransactionPersistenceService trxnService = new TransactionPersistenceServiceImpl();
   List<Transaction> trxns = trxnService.retrieveByUser(userId);

	if (trxns.size() > 0){
	%>  
  	<table border="1" style="margin-top: 20px; margin-right: 20px; margin-left: 29px; border-top-width: 2px;">
	    <tr>
	      <th>Date</th>
	      <th>Price</th>
	      <th>Size</th>  
	      <th>Size</th>    
	    </tr>
	    
	    <%for(Transaction trxn : trxns) {%>
		<tr>
		<td><%= trxn.getDate().toString() %></td>
		<td><%= trxn.getPrice() %></td>
		<td><%= trxn.getProducts().size() %></td>
		<td>
			<form name="trxnDetailsform" action="TransactionDetailsController" method="post">
				<input type="hidden" name="trxnId" value="<%= trxn.getTrxnId().toString() %>">
				<input class="demo" type="submit" name="ViewDetails" value = "View Details" style="left: 460px;">
			</form>
		</td>
		</tr>
		<%}%>
	</table>
	<br>
	<% } else {%>
		<p> You have no transactions. </p>
	<%}%>
   
</body>
</html> 