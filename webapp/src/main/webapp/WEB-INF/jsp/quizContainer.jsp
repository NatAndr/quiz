<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.12.2015
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz</title>
    <%@include file="header.jsp" %>
</head>
<body>

<div class="container">
    <div class="row col-lg-offset-2">
        <div class="col-lg-8">
            <div class="row">
                <h2>${quizSetName}</h2>
            </div>
        </div>
        <div class="col-lg-8">
            <div class="row">
                <h3>
                    <div id="countdown"></div>
                </h3>
                <div id="quiz"></div>
            </div>
        </div>
        <div class="col-lg-8">
            <div class="row">
                <div id="nxt" style="display: none;"><a class="btn btn-primary nextBtn">Next</a></div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var showQuestion = '<c:url value="/showQuestion"/>';
    var result = '<c:url value="/result"/>';
    var quizQuestion = '<c:url value="/quizQuestion"/>';
    var quizTime = '${time}';
</script>
<script src="<c:url value="../../resources/js/quiz/quizContainer.js" />"></script>
</body>
</html>
