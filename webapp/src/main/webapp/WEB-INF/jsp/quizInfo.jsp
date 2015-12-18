<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.11.2015
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<html>
<head>
    <title>Quiz</title>
    <%@include file="header.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="link.jsp" %>

    <div class="page-header">
        <h1>Quiz</h1>

        <p class="lead">${quiz.quizName}</p>
        <p>${questionsNumber} Questions</p>
    </div>
    <form action="${pageContext.request.contextPath}/quizRun" method="post">
        <label>
            <input type="hidden" name="id" value="${quiz.id}">
            <input class="btn btn-default btn-sm" type="submit" value="Start">
        </label>
    </form>
</div>
</body>
</html>
