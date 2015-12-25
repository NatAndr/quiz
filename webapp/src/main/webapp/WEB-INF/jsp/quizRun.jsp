<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.12.2015
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%@include file="header.jsp" %>
</head>
<body>
<div class="container">
    <h2>${quizSet.name}</h2>

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
                    <input type="text" class="form-control" name="inputAnswer" value="">
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <br>
        <button type="submit" class="btn btn-primary" id="next">Next</button>
    </form>

    <%--<form:form action="" commandName="question">--%>
        <%--<c:choose>--%>
            <%--<c:when test="${question.questionType == 'SINGLE'}">--%>
                <%--<form:radiobuttons path="question.answers.anser" items="${question.answers}"/>--%>
            <%--</c:when>--%>
            <%--<c:when test="${question.questionType == 'MULTIPLE'}">--%>
                <%--<form:checkboxes path="question.answers.anser" items="${question.answers}"/>--%>
            <%--</c:when>--%>
            <%--&lt;%&ndash;<c:otherwise>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<form:input path="inputAnswer"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</c:otherwise>&ndash;%&gt;--%>
        <%--</c:choose>--%>
        <%--<input type="submit" value="Next"/>--%>
    <%--</form:form>--%>
</div>
</body>
</html>
