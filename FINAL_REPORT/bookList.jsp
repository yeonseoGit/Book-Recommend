<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>대출된 책 목록</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>대출된 책 목록</h1>
    <table>
        <thead>
            <tr>
                <th>책 ID</th>
                <th>책 이름</th>
                <th>대출자 ID</th>
                <th>대출 날짜</th>
                <th>반납 날짜</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="borrow" items="${borrowList}">
                <tr>
                    <td>${borrow.BOOK_NUMBER}</td>
                    <td>${borrow.BOOK_NAME}</td>
                    <td>${borrow.BORROW_NUMBER}</td>
                    <td>${borrow.TODAY_DATE}</td>
                    <td>${borrow.DUE_DATE}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
