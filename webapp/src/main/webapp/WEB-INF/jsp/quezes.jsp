<%--
  Created by IntelliJ IDEA.
  User: Nat
  Date: 26.11.2015
  Time: 0:02
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quizes</title>
</head>
<body>
<div class="container" align="center">

    <table class="table table-striped">
        <c:if test="${foundQuiz!=null}">
            <div class="page-header">
                <p class="lead">Quizes</p>
            </div>
            <c:forEach var="quiz" items="${foundQuiz}">
                <tr>
                    <td>
                        <a href='<c:url value="quizInfo?id=${quiz.id}"/>'>${quiz.quizName}</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
</body>
</html>
