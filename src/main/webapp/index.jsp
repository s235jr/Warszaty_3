<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 29.06.18
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>Rozwiązania</h3>
<ul>
    <c:forEach items="${solutions}" var="solution">
        <li>${solution.description}, ${solution.created}</li>
    </c:forEach>
</ul>
</body>
</html>
