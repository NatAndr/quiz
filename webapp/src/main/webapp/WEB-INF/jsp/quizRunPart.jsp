<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.12.2015
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<div class="page-header">
    <p class="lead">Question ${counter + 1}/${questionsNumber}</p>
</div>
<div id="question">
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
                <input type="text" class="form-control" name="${answer.id}" id="inputAnswer" value="">
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <br>
</div>

