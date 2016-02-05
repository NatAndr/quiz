<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.12.2015
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<h3>Question ${counter + 1}/${questionsNumber}</h3>

<div id="question">
    <div class="col-lg-12">
        <div class="row">
            ${question.question}
        </div>
    </div>
    <div class="col-lg-6">
        <div class="row">
            <c:if test="${not empty question.picture}">
                <img style="max-height: 100px;" src="data:image/png;base64,${question.picture}"/>
            </c:if>
        </div>
    </div>
    <div class="col-lg-8">
        <div class="row">
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
        </div>
    </div>
    <br><br>

</div>

