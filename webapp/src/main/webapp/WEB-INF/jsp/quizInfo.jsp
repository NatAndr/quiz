<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.11.2015
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script>
</body>
</html>
