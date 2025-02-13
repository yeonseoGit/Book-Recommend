<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 정보</title>
</head>
<body>
<h2>리뷰 정보</h2>[<a href="/jwbook/reviewController">새로고침</a>]
<hr>
<table border="1">
<tr><th>리뷰 번호</th><th>대출 번호</th><th>학생번호</th><th>책 번호</th><th>점수</th></tr>
<c:forEach items="${REVIEW}" var="r">
	<tr><td>${r.REVIEW_NUM}</td><td>${r.BORROW_NUMBER}</td><td>${r.STUDENT_ID}</td><td>${r.BOOK_NUMBER}</td><td>${r.RATING}</td></tr>
</c:forEach>
</table>
<hr> 
<h2>리뷰 추가</h2>
<hr>
<form method="post" action="/jwbook/bookController?action=insert">
<label>리뷰 번호</label>
<input type="text" name="REVIEW_NUM"><br>
<label>대출 번호</label>
<input type="text" name="BORROW_NUMBER"><br>
<label>학생 번호</label>
<input type="text" name="STUDENT_ID"><br>
<label>책 번호</label>
<input type="text" name="BOOK_NUMBER"><br>
<label>점수</label>
<input type="text" name="RATING"><br>
<button type="submit">등록</button>
</form>
</body>
</html>