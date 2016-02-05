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

    <div class="page-header" id="top">
        <h2>Content management</h2>
    </div>
    <div id="alert_placeholder"></div>
    <ul class="nav nav-tabs" id="myTabs">
        <li class="active"><a data-toggle="tab" href="#students">Students</a></li>
        <li><a data-toggle="tab" href="#studyGroups">Groups</a></li>
        <li><a data-toggle="tab" href="#quizSets">Quizzes</a></li>
        <li><a data-toggle="tab" href="#questions" id="#questions">Questions</a></li>
        <li><a data-toggle="tab" href="#answers">Answers</a></li>
    </ul>

    <div class="tab-content">
        <div id="students" class="tab-pane fade in active">
            <%@include file="students.jsp" %>
        </div>

        <div id="studyGroups" class="tab-pane fade">
            <%@include file="studyGroups.jsp" %>
        </div>

        <div id="quizSets" class="tab-pane fade">
            <%@include file="quizSets.jsp" %>
        </div>

        <div id="questions" class="tab-pane fade">
            <%@include file="questions.jsp" %>
        </div>

        <div id="answers" class="tab-pane fade">
            <%@include file="answers.jsp" %>
        </div>
    </div>
</div>

<!-- Modal Result -->
<div class="modal fade" id="result" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body" align="center">
                <h4 class="myval"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal Result -->

</body>
</html>
