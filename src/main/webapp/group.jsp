<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 29.06.18
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/fragments/header.jsp" %>

<div>
    <div style="margin-left: 25%; margin-right: 25%">
        <br><br><br><br><br><br>
        <h3>Rozwiązania</h3>
        <br><br>
        <ul>
            <c:forEach items="${solutions}" var="solution">
                <li>${solution.description}, ${solution.created}</li>
            </c:forEach>
        </ul>
    </div>
</div>

<%@ include file="/fragments/footer.jsp" %>
