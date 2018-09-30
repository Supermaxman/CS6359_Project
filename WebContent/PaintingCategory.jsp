<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%@ page import="domain.product.Painting" %>
<%@ page import="domain.product.Product" %>
<%@ page import="domain.user.User" %>
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
   <% HttpSession sess=request.getSession(true); %>
   
   <% List<Painting> painting  = new ArrayList<>(); 
      Painting p = new Painting();
              
      p.name= "Starry Night";
      p.canvasType="Oil";
      p.paintType ="Oil";
      p.length= 15;
      p.width =20;
      painting.add(p);  
           
      p.setPainting(painting);
      List<Painting> prod = new ArrayList<Painting>();
  	  prod=p.getPainting();
  
   %>  
   <table border="1" style="margin-top: 20px; margin-right: 20px; margin-left: 29px; border-top-width: 2px;">
     <tr>
       <th>Product name</th>
       <th>Canvas type</th>
       <th>Paint type</th>
       <th>Length</th>
       <th>Width</th>
       <th>Action</th>   
     </tr>
     
     <%for(int i =0;i<prod.size();i++) {%>
		
		<tr>
				<td><%= prod.get(i).name %></td>
				<td><%= prod.get(i).canvasType %></td>
				<td><%= prod.get(i).paintType %></td>
				<td><%= prod.get(i).length %></td>
				<td><%= prod.get(i).width %></td>
				<td>
				<form name="mainform" action="CategoryController" method="post">
		
		        <input class="demo" type="submit" name="ViewDetails" value ="View Details" style="left: 460px;"> 
				<button type="button" onclick="myFunction()">Add to Cart</button>	
		        <input class="demo" type="submit" name="ViewCart" value="View Cart" style="left: 508px;">
			
		
	</form></td>
		</tr>
		
		<%}%>
   </table><br>
   
</body>
</html>