<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 02.12.2015
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Result</title>
    <link href="<c:url value="/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h2>Result</h2>
    </div>
    <div class="alert alert-success" role="alert">Well done! You successfully passes this quiz.</div>

    <!-- div class="alert alert-danger" role="alert">...</div -->
</div>
<script src="<c:url value = "/bootstrap/js/jquery-1.11.3.min.js"/>"></script>
<script src="<c:url value = "/bootstrap/js/bootstrap.min.js"/>"></script>
</body>
</html>
