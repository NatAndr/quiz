<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.12.2015
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<div>
    <c:forEach var="question" items="${generatedQuestions}">
        <br>
        <br>
        ${question.question}
        <br>
        <c:forEach var="answer" items="${question.answers}">
            ${answer.answer}
            <br>
        </c:forEach>
    </c:forEach>

    <%@include file="quizQuestion.jsp" %>
</div>

</body>
</html>
