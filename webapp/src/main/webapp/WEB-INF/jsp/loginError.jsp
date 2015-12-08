<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 05.12.2015
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<div class="container">
    <c:if test="${errorLoginMsg!=null}">
        <div class="alert alert-danger" role="alert">${errorLoginMsg}</div>
    </c:if>
</div>
</body>
</html>
