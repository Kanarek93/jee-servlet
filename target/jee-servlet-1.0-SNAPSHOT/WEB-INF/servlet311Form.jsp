<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: canary
  Date: 14.02.2021
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
<c:when test="${counter > 0}">
        ${message}
</c:when>
<c:otherwise>
        <form action="/servlet312" method="post">
            <select name="language">
                <option value="en">EN</option>
                <option value="pl">PL</option>
                <option value="de">DE</option>
                <option value="es">ES</option>
                <option value="fr">FR</option>
            </select>
            <input type ="submit" value="Wyślij">
        </form>
</c:otherwise>
</c:choose>
</body>
</html>
