<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login-page</title>
</head>
<body>
<form action="/login-page" method="get">
    <input type="text" value="${requestScope.admin}"><br>
    <input type="text" value="${requestScope.admin.userInfo.surname}"><br>
    <input type="text" value="${requestScope.admin.userInfo.secondName}"><br>
    <input type="submit">
</form>
</body>
</html>
