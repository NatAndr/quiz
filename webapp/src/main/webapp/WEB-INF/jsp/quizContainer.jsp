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
    <script type="text/javascript">
        //        $('#screenId').click(function() {
        //        $(document).ready(function () {
        function doRequest() {
            <%--var header = '<div class="container"><h2>${quizSet.name}</h2><div class="page-header"><p class="lead">Question ${counter+1}/${questionsNumber}</p></div><form class="form-search">';--%>
            var footer = '<button type="submit" class="btn btn-primary" id="next">Next</button></form></div>';
            $.ajax({
                type: "POST",
                cache: false,
                url: '<c:url value="/showQuestion" />',
//                url: uu,
                data: "",
                success: function (response) {
                    $('#quiz').html(response);
                },
                error: function (e) {
                    alert('Error: ' + e);
                }
            });
        }
        ;
        $(document).ready(doRequest());
        <%--$('#next').click(function () {--%>
            <%--var footer = '<button type="submit" class="btn btn-primary" id="next">Next</button></form></div>';--%>
            <%--$.ajax({--%>
                <%--type: "POST",--%>
                <%--cache: false,--%>
                <%--url: '<c:url value="/quizQuestion" />',--%>
                <%--data: "",--%>
                <%--success: function (response) {--%>
                    <%--$('#quiz').html(response + footer);--%>
                <%--},--%>
                <%--error: function (e) {--%>
                    <%--alert('Error: ' + e);--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>
    </script>
</head>
<body>
<div id="quiz">

</div>
</body>
</html>
