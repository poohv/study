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
		out.println("Ŀ�ؼ� ����");
		
	}catch(SQLException ex){
		out.println("Ŀ�ؼ� ����");
	}

%>


</body>
</html>