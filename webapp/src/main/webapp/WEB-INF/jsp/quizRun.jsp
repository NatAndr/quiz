<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.12.2015
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quiz</title>
    <%--link href="<c:url value="/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet"--%>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>${quizSet.quizName}</h2>
    <div class="page-header">
        <p class="lead">Question ${counter+1}/${questionsNumber}</p>
    </div>
    <form class="form-search" action="${pageContext.request.contextPath}/quizQuestion" method="post">
        ${question.question}
        <br>
        <c:forEach var="answer" items="${question.answers}">
            <input type="hidden" name="id" value="${answer.id}"/>
            <c:choose>
                <c:when test="${question.questionType == 'SINGLE'}">
                    <input type="radio" name="answer" value="${answer.id}"> ${answer.answer}<br>
                </c:when>
                <c:when test="${question.questionType == 'MULTIPLE'}">
                    <input type="checkbox" name="answer" value="${answer.id}"> ${answer.answer}<br>
                </c:when>
                <c:otherwise>
                    <input type="text" class="form-control" name="inputAnswer">
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <br>
        <button type="submit" class="btn btn-primary">Next</button>
    </form>
</div>
<%--script src="<c:url value = "/bootstrap/js/jquery-1.11.3.min.js"/>"></script>
<script src="<c:url value = "/bootstrap/js/bootstrap.min.js"/>"></script--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script>
</body>
</html>