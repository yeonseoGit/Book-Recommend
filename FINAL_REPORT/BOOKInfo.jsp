<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 정보</title>
</head>
<body>
<h2>책 정보</h2>[<a href="/jwbook/bookController">새로고침</a>]
<hr>
<table border="1">
<tr><th>책 번호</th><th>책 이름</th><th>책 갯수</th><th>카테고리</th></tr>
<c:forEach items="${BOOK}" var="b">
	<tr><td>${b.BOOK_NUMBER}</td><td>${b.BOOK_NAME}</td><td>${b.BOOK_COUNT}</td><td>${b.BOOK_CATEGORY}</td></tr>
</c:forEach>
</table>
<hr> 
<h2>도서 추가</h2>
<hr>
<form method="post" action="/jwbook/bookController?action=insert">
<label>책 번호</label>
<input type="text" name="BOOK_NUMBER"><br>
<label>책 이름</label>
<input type="text" name="BOOK_NAME"><br>
<label>책 갯수</label>
<input type="text" name="BOOK_COUNT"><br>
<label>책 카테고리</label>
<input type="text" name="BOOK_CATEGORY"><br>
<button type="submit">등록</button>
</form>
</body>
</html>