<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.11.2015
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html; charset=UTF-8" language="java" %>--%>
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
        <p class="lead">${quiz.name}</p>
        <p>Questions number: ${questionsNumber}</p>
        <p>Time: ${time} min</p>
    </div>
    <form action="${pageContext.request.contextPath}/initializeQuiz" method="post">
        <label>
            <input type="hidden" name="id" value="${quiz.id}">
            <c:if test="${not empty userName}">
                <button type="submit" class="btn btn-primary">Start</button>
            </c:if>
            <c:if test="${empty userName}">
                <div class="alert alert-danger" role="alert">
                    Please <a href="<c:url value="/login"/>">sign in</a>
                    or
                    <a href="<c:url value="/registration"/>">register</a>
                    to pass this quiz
                </div>
            </c:if>
        </label>
    </form>
</div>
</body>
</html>
