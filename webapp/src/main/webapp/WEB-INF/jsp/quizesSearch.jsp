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
    <title>Quizes search</title>
    <%--link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/login.css'/>" rel="stylesheet" --%>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<%@include file="loginRef.jsp" %>
<div class="container" align="center">

    <div class="page-header">
        <h2>Quizes search</h2>
    </div>

    <%-- form action="${pageContext.request.contextPath}/quizesSearch" method="get">
    <label>Название</label>
        <input type="text" name="searchParams" maxlength="40" size="30">
    <button type="submit" class="btn">Search</button>
</form --%>


    <form class="form-search" action="${pageContext.request.contextPath}/quizesSearch" method="get">
        <div class="input-append">
            <label>
                <input type="text" name="searchParams" class="span2 search-query">
            </label>
            <button type="submit" class="btn btn-default btn-sm">Search</button>
        </div>
    </form>

    <%@include file="quezes.jsp" %>
</div>
<%--script src="<c:url value = "/bootstrap/js/jquery-1.11.3.min.js"/>"></script>
<script src="<c:url value = "/bootstrap/js/bootstrap.min.js"/>"></script--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script>
</body>
</html>
