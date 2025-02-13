<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 정보</title>
</head>
<body>
<h2>학생 정보</h2>[<a href="/jwbook/usagerController">새로고침</a>]
<hr>
<table border="1">
<tr><th>학번</th><th>이름</th><th>대출횟수</th></tr>
<c:forEach items="${USAGER}" var="u">
	<tr><td>${u.STUDENT_ID}</td><td>${u.USER_NAME}</td><td>${u.BOOK_LOANCOUNT}</td></tr>
</c:forEach>
</table>
<hr> 
<h2>학생 추가</h2>
<hr>
<form method="post" action="/jwbook/usagerController?action=insert">
<label>학번</label>
<input type="text" name="STUDENT_ID"><br>
<label>이름</label>
<input type="text" name="USER_NAME"><br>
<label>대출횟수</label>
<input type="text" name="BOOK_LOANCOUNT"><br>
<button type="submit">등록</button>
</form>
</body>
</html>