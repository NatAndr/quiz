<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nat
  Date: 25.11.2015
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html>
<head>
    <title>Quizzes management</title>
    <%@include file="header.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="link.jsp" %>

    <div class="page-header">
        <h2>Content management</h2>
    </div>
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#students">Students</a></li>
        <li><a data-toggle="tab" href="#studyGroups">Groups</a></li>
        <li><a data-toggle="tab" href="#quizzes">Quizzes</a></li>
        <li><a data-toggle="tab" href="#questions">Questions</a></li>
        <li><a data-toggle="tab" href="#answers">Answers</a></li>
    </ul>

    <div class="tab-content">
        <div id="students" class="tab-pane fade in active">
        </div>

        <div id="studyGroups" class="tab-pane fade">
            <%--<c:set var="elements" value="${studyGroups}" scope="request" />--%>
            <%--<jsp:include page="entitiesList.jsp"/>--%>
                <%@include file="studyGroups.jsp" %>
        </div>

        <div id="quizzes" class="tab-pane fade">
            <c:set var="elements" value="${quizzes}" scope="request" />
            <jsp:include page="entitiesList.jsp"/>
        </div>

        <div id="questions" class="tab-pane fade">
            <h3>Menu 3</h3>
            <p>Some content in menu 3.</p>
        </div>

        <div id="answers" class="tab-pane fade">
            <h3>Menu 4</h3>
            <p>Some content in menu 4.</p>
        </div>
    </div>
</div>

</body>
</html>
