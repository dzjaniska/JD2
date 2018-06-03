<%@ include file="pattern/lib-content.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>products</title>
</head>
<body>
<div>
    <a href=""></a>
</div>
<div>
    <c:forEach items="${sessionScope.pages}" var="pages">
        <a href="/products?page=+${pages}"></a>

    </c:forEach>
</div>
</body>
</html>
