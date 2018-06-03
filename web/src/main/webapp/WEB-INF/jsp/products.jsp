<%@ include file="pattern/lib-content.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>products</title>
</head>
<body>
<form method="get" action="products.jsp">
    <p>
        <select name="category">Category
            <option value="RAM">RAM</option>
            <option value="HDD">HDD</option>
            <option value="CPU">CPU</option>
        </select>
    </p>
    <p>
        <select name="optionValue">Year Of Production
            <option value="2017">2017</option>
            <option value="2018">2018</option>
        </select>
    </p>
    <p>
        <select name="size">Items On Page
            <option value="5">5</option>
            <option value="10">10</option>
            <option value="20">20</option>
        </select>
    </p>
    <p>
        <select name="page">Page
            <option value="5">5</option>
            <option value="10">10</option>
            <option value="20">20</option>
        </select>
    </p>


</form>
</body>
</html>
