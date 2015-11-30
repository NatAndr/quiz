<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.11.2015
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/startQuiz" method="get">
    <h2>Quiz</h2>
    <label>
        ${quiz.quizName}
        <br/><br/>
        <input type="submit" value="Start">
    </label>
</form>
</body>
</html>

