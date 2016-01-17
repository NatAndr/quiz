<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 02.12.2015
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Result</title>
    <%@include file="header.jsp" %>
</head>
<body>
<div class="container" align="center">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="page-header">
                <h2>Result</h2>

                <p class="lead">Your score: ${result}</p>
            </div>
            <c:if test="${result==questionsNumber}">
                <div class="alert alert-success" role="alert">Well done! You successfully passes this quiz</div>
            </c:if>
            <c:if test="${result<questionsNumber}">
                <div class="alert alert-danger" role="alert">Quiz is not passed</div>
            </c:if>
            <a href='<c:url value="/repeat"/>'>Try again</a>
        </div>
    </div>
</div>
</body>
</html>
