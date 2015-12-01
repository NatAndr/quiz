<%--
  Created by IntelliJ IDEA.
  User: Nat
  Date: 25.11.2015
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<html>
<head>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>Quizes search</title>
</head>
<body>
<div class="container">

    <div class="page-header">
        <h1>Quizes search</h1>
        <!-- p class="lead">Basic grid layouts to get you familiar with building within the Bootstrap grid system.</p -->
    </div>

    <%-- form action="${pageContext.request.contextPath}/quizesSearch" method="get">
    <label>Название</label>
        <input type="text" name="searchParams" maxlength="40" size="30">
    <button type="submit" class="btn">Search</button>
</form --%>


    <form class="form-search" action="${pageContext.request.contextPath}/quizesSearch" method="get">
        <div class="input-append">
            <input type="text" name="searchParams" class="span2 search-query">
            <button type="submit" class="btn">Search</button>
        </div>
    </form>


    <%@include file="quezes.jsp" %>
</div>
</body>
</html>
