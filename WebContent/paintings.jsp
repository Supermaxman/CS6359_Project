<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%@ page import="domain.product.Painting" %>
<%@ page import="domain.product.Product" %>
<%@page import="db.services.PaintingPersistenceService"%>
<%@page import="db.services.impl.PaintingPersistenceServiceImpl"%>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>ArtKart: Paintings</title>
 <script>
 function myFunction() { 
	    alert("Bingo! Your product is added to Cart");
	}
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
   
   <% 
   PaintingPersistenceService paintService = new PaintingPersistenceServiceImpl();
   List<Painting> paintings = paintService.retrieveAll();
  
   %>  
   <table border="1" style="margin-top: 20px; margin-right: 20px; margin-left: 29px; border-top-width: 2px;">
     <tr>
       <th>Name</th>
       <th>Description</th>
       <th>Action</th>   
     </tr>
     
     <%for(Painting prod : paintings) {%>
		
		<tr>
		<td><%= prod.getName() %></td>
		<td><%= prod.getDescription() %></td>
		<td>
			<form name="detailsform" action="DetailsController" method="post">
				<input type="hidden" name="prodId" value="<%= prod.getProdId().toString() %>">
				<input class="demo" type="submit" name="ViewDetails" value = "View Details" style="left: 460px;">
			</form>
		</td>
		</tr>
		
	<%}%>
   </table><br>
   
</body>
</html> 