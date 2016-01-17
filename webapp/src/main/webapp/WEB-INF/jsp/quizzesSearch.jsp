<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nat
  Date: 25.11.2015
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html; charset=UTF-8" language="java" %>--%>
<%@ page errorPage="error.jsp" %>
<html>
<head>
    <title>Quizzes search</title>
    <%@include file="header.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="link.jsp" %>

    <div class="page-header">
        <h2>Quizzes search</h2>
    </div>
    <form class="form-search" action="${pageContext.request.contextPath}/quizzesSearch" method="get">
        <div class="input-append">
            <label>
                <input type="text" name="searchParams" class="span2 search-query" placeholder="Quiz">
            </label>
            <button type="submit" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-search"></span> Search
            </button>
        </div>
    </form>

    <%@include file="quizzes.jsp" %>
</div>
</body>
</html>
