<%@page import="db.DbManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LoginPage</title>
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
	<h4 align="left"> Please log in to start shopping! </h4>
	<br>
	<form name="loginform" action="LoginController" method="post" onsubmit="return loginValidate()" >
	<br>
	Username: <input type="text" name="username" id="username">
	<br>
	Password: <input type="password" name="password" id="password">
	<br>
	<input type="submit" name="submit" value="login">
	<br>
	<a href="register.jsp">registration</a>
	</form>
</body>
</html>