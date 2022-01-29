<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>register</title>
</head>
<body>
<% session.setMaxInactiveInterval(60*60*24*30);%>
<h1>REGISTER</h1>
<form method="get" action=/todolist/router/user/register>
	<p>username:<input type="text" name="username"></p>
	<p>password:<input type="text" name="password"></p>
	<input type="submit">
</form>
	<%
		if(request.getAttribute("id")!=null)
		{
			session.setAttribute("id",request.getAttribute("id"));
			response.sendRedirect("/todolist/congrat.jsp");
		}
	%>
</body>
</html>