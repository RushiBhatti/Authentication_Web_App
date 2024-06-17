<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile Page by RB</title>
</head>
<body>
	<%
	String myName = (String) session.getAttribute("name_key");
	String myEmail = (String) session.getAttribute("email_key");
	%>
	
	<h1 style="text-align:center">WELCOME!</h1>
	<h3 style="text-align:center">This is profile page Created by RB!</h3>
	<h4 style="text-align:center">UserName: <%=myName%>...</h4>
	<h4 style="text-align:center">UserEmail: <%=myEmail%>...</h4>
	<hr>
	<h3 style="text-align:center">Login Successfully done!</h3>
	<a href = "logout" style="text-align:center">Logout</a>
	
</body>
</html>