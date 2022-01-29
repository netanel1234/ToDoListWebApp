<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>congrat</title>
</head>
<body>
	<%request.setAttribute("id", session.getAttribute("id"));%>
	<p><%= "userid="+session.getAttribute("id")+"  session id="+session.getId()+" id attribute in request="+request.getAttribute("id")%><p>
	<h2>CONGRATULATIONS!!!</h2>
	<h3>You are now registered in the system</h3>
	<p>Enter your first task:</p>
	<form method="get" action=/todolist/router/user/congrat>
		<input type="text" name="task">
		<input type="submit" >
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