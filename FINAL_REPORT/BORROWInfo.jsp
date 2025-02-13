<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대출 정보</title>
</head>
<body>
<h2>대출 정보</h2>[<a href="/jwbook/bookController">새로고침</a>]
<hr>
<table border="1">
<tr><th>대출날짜</th><th>대출 번호</th><th>책 번호</th><th>사용자</th><th>반납기한</th></tr>
<c:forEach items="${BORROW}" var="b">
	<tr><td>${b.TODAY_DATE}</td><td>${b.BORROW_NUMBER}</td><td>${b.BOOK_NUMBER}</td><td>${b.STUDENT_ID}</td><td>${b.DUE_DATE}</td></tr>
</c:forEach>
</table>
</form>
</body>
</html>