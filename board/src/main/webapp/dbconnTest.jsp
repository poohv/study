<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="jdbc.connection.ConnectionProvider" %>
<%@ page import="java.sql.*" %>   
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%
	try
		(Connection conn = ConnectionProvider.getConnection()){
		out.println("目池记 己傍");
		
	}catch(SQLException ex){
		out.println("目池记 角菩");
	}

%>


</body>
</html>