<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>login</title>
</head>
<body>
<%
	HibernateToDoListDAO dao=(HibernateToDoListDAO)application.getAttribute("dao");
	if(dao==null)
	{
		application.setAttribute("dao", HibernateToDoListDAO.getInstance());
		dao=(HibernateToDoListDAO)application.getAttribute("dao");
	}
%>
<h2>Login/Register</h2>
<p>If you do not have an account, you need to register first</p>
<a href=register.jsp>to register</a>
<h3>Login</h3>
<form method="get" action=/todolist/router/user/login>
	<p>username:<input type="text" name="username"></p>
	<p>password:<input type="text" name="password"></p>
	<input type="submit" />
</form>
<%
		
		if(request.getAttribute("list")!=null)
		{
			if(request.getAttribute("list").equals("-1"))
				response.sendRedirect("/todolist/notRegisterYet.jsp");
			else
			{
				session.setAttribute("list", request.getAttribute("list"));
				response.sendRedirect("/todolist/showlist.jsp");
			}
		}
	%>
</body>
</html>