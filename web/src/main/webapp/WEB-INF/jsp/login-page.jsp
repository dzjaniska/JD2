<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login-page</title>
</head>
<body>
<form action="/login-page" method="get">
    <input type="text" value="${requestScope.username}">
    <input type="submit">
</form>
</body>
</html>
