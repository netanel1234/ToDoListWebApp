<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    <%@page import="model.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>Insert title here</title>
</head>
<body>
	<h3>Your list:</h3>
	<%
	Item[] list=(Item[])session.getAttribute("list");
	for(Item i:list)
	{
		%><%="(Task id:"+i.getSerialnumber()+".) Task:"+i.getTask() %><br><%
	}
	%>
	
	<a href=add.jsp>go to add a task</a><br>
	<a href=delete.jsp>go to delete a task</a><br>
	<a href=update.jsp>go to update a task</a><br>
	<a href=logout.jsp>logout</a><br>
</body>
</html>