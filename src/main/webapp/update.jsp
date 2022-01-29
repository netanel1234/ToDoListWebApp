<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>update task</title>
</head>
<body>
<%request.setAttribute("id", session.getAttribute("id"));%>
<form method="get" action="/todolist/router/user/update">
	<p>update task by task id:<input type="text" name="taskid"></p>
	<p>update to:<input type="text" name="task"></p>
	<input type="submit">
</form>
<%
		if(request.getAttribute("list")!=null)
		{
			session.setAttribute("list", request.getAttribute("list"));
			response.sendRedirect("/todolist/showlist.jsp");
		}
	%>
</body>
</html>