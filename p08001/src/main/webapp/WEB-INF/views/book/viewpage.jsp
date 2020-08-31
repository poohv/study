<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<style>
td.ex1{
width :"115";
color:#333333; 
font-size: 12px;
}
td.ex2{
padding: 7px 0 7px 0;
}
input{
width: 200px; vertical-align: middle; border-width: 1px; 
border-color: rgb(153, 153, 153) rgb(223, 223, 223) rgb(223, 223, 223) rgb(153, 153, 153); 
background-color: rgb(235, 235, 235); color: rgb(51, 51, 51); height: 30px; 
padding: 0px 7px;
}
</style>
<body>
<table>
	<tbody width="905" border="0" align="center" cellpa	dding="0" cellspacing="0">
	<tr>
	<td style="width: 193px; font: 20px malgun gothic; vertical-align: top; letter-spacing: -1px; padding: 25px 0 0 10px">책 등록 하기
        </td>
	<td style="padding: 25px 0 0 0" >
	<table width="50%" border="0" cellspacing="0" cellpadding="0">
	<form action="/sample/book/register" method="get">
				<tr>
					<td class="ex1" >책이름</td>
					<td class="ex2" ><input type="text" name="name" ></td>
				</tr>				
				<tr>
					<td class="ex1">저자 </td>
					<td class="ex2"><input type="text" name="author"></td>
				</tr>
				<tr>
					<td class="ex1" >책번호 </td>
					<td class="ex2"> <input type="text" name="isbn" ></td>
				</tr>
				<tr>
					<td class="ex1">가격 </td>
					<td class="ex2"><input type="text" name="price"></td>
				</tr>
				<tr><td><input type="submit" value="등록하기"></td></tr>
	</form>
	</table>	
	</td>	
		<div style="padding:7px">		
			<ol>
			<c:choose>
				<c:when test="${fn:length(list) > 0}">
					<c:forEach items="${list}" var="item">
					<ui font-size: 1em;>
						<li overflow:hidden;>번호  :${item.isbn}
						이름 : ${item.name}
						저자 :  ${item.author}
						가격  : ${item.price}
						<a href="/sample/book/mod/?isbn=${item.isbn}">수정</a> <a href="/sample/book/del?isbn=${item.isbn}">삭제</a>
						</li>
					</ui>	
					</c:forEach>
				</c:when>
				<c:otherwise>
					book: 0
				</c:otherwise>
			</c:choose>	
				</ol>
			</div>		
	</tbody>
	</table>
</body>
</html>