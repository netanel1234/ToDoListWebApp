<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>logout</title>
</head>
<body>
<%
	session.invalidate();
%>
<a href=login.jsp>login</a><br>
</body>
</html>