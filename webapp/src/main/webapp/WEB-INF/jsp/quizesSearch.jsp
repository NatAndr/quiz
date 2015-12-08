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
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <%--link href="<c:url value="css/bootstrap.min.css" />" rel="stylesheet">
    <script src="<c:url value="css/js/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="css/js/bootstrap.min.js" />"></script--%>
    <link rel="icon" href="images/favicon.ico">
</head>
<body>
<div class="container">
    <%@include file="link.jsp" %>

    <div class="page-header">
        <h2>Quizes search</h2>
    </div>
    <form class="form-search" action="${pageContext.request.contextPath}/quizesSearch" method="get">
        <div class="input-append">
            <label>
                <input type="text" name="searchParams" class="span2 search-query">
            </label>
            <button type="submit" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-search"></span> Search
            </button>
        </div>
    </form>

    <%@include file="quezes.jsp" %>
</div>
<%--script src="<c:url value = "/bootstrap/js/jquery-1.11.3.min.js"/>"></script>
<script src="<c:url value = "/bootstrap/js/bootstrap.min.js"/>"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script--%>
</body>
</html>
