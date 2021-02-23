<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: canary
  Date: 14.02.2021
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Auth</title>
</head>
<body>
<c:choose>
    <c:when test="${empty param.author}">
      Podano niepoprawny parametr
    </c:when>
    <c:when test="${param.author == null}">
        Podano niepoprawny parametr
    </c:when>
    <c:otherwise>
        <p>Wybrany autor ${param.author}</p>
    </c:otherwise>
</c:choose>

</body>
</html>
