<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nat
  Date: 25.11.2015
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<html>
<head>
    <title>Quizzes management</title>
    <%@include file="header.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="link.jsp" %>

    <div class="page-header">
        <h2>Quizzes management</h2>
    </div>
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#home">Students</a></li>
        <li><a data-toggle="tab" href="#menu1">Groups</a></li>
        <li><a data-toggle="tab" href="#menu2">Quizzes</a></li>
        <li><a data-toggle="tab" href="#menu3">Questions</a></li>
        <li><a data-toggle="tab" href="#menu4">Answers</a></li>
    </ul>

    <div class="tab-content">
        <div id="home" class="tab-pane fade in active">
            <h3>HOME</h3>
            <p>Some content.</p>
        </div>
        <div id="menu1" class="tab-pane fade">
            <h3>Menu 1</h3>
            <p>Some content in menu 1.</p>
            <%@include file="studyGroups.jsp" %>
        </div>
        <div id="menu2" class="tab-pane fade">
            <h3>Menu 2</h3>
            <p>Some content in menu 2.</p>
        </div>
        <div id="menu3" class="tab-pane fade">
            <h3>Menu 3</h3>
            <p>Some content in menu 3.</p>
        </div>
        <div id="menu4" class="tab-pane fade">
            <h3>Menu 4</h3>
            <p>Some content in menu 4.</p>
        </div>
    </div>
</div>
</body>
</html>
