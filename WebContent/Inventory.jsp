<%@page import="db.DbManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="domain.user.User"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" >
function regValidate() {

	
	var username = document.forms["regform"]["username"].value;

	var password = document.forms["regform"]["password"].value;

	var rpassword = document.forms["regform"]["retry-password"].value;

	if (username == "")
	 {
	        alert("username must be filled out");
	        document.forms["regform"]["username"].focus();
	        return false;
	    }else if (password== "") {
	        alert("password must be filled out");
	        document.forms["regform"]["password"].focus();
	        return false;
	    }else if (rpassword == "") {
	        alert("retry-password must be filled out");
	        document.forms["regform"]["retry-password"].focus();
	        return false;
	    }else if(password != rpassword){
	    	alert("password doesnt match");
	        document.forms["regform"]["password"].focus();
	        return false;
	    }
	}
	</script>
</head>
<body>


<%
	DbManager db = new DbManager();
	
Connection conn = (Connection) db.getConnection();

if(conn == null)
		
out.print("failed");
	
else
		
out.print("succeeded");


%>
<br/>

<%
String value=request.getParameter("userId");
//int v=Integer.parseInt(value);
Class.forName("com.mysql.jdbc.Driver");
User user= new User();

PreparedStatement st=conn.prepareStatement("select * from inventory where userId=?");
st.setString(1, user.getUsername());
ResultSet rs = st.executeQuery();

if(rs.next()){
    
%>
<table border="1" style="margin-top: 45px; margin-right: 27px; margin-left: 299px; border-top-width: 1px; 
	border-right-width: 1px; border-bottom-width: 1px; padding-top: 1px; width: 621px; height: 110px;">
		<tr>
				<th>Product Description</th>
				<th>Product_Name</th>
				<th>Price</th>
				
		</tr>
		
		
		
		
		</table><br><br>
		<% } 
		else {%>
		
		<p> Your Inventory is empty</p>
		<%}%>
<form name="loginform" action="LoginController" 
method="post" >

<br>
${message}<br>
${successMessage}<br>





<a href="newproduct.jsp">Add a Product</a>

</form>
</body>
</html>