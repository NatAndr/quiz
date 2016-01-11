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
        $(document).ready(function() {
            initQuestions();
            $('#nxt').show();
            $('#nxt').on('click', '.nextBtn', nextQuestion);
        });

        function initQuestions() {
            $.ajax({
                type: "POST",
                cache: false,
                url: '<c:url value="/showQuestion" />',
                data: "",
                success: function (response) {
                    $('#quiz').html(response);
                },
                error: function (e) {
                    alert('Error: ' + e);
                }
            });
        }

        function nextQuestion() {
            var answers = [];
            var inputAnswer = '';

            if ($('input[type=text]').val() != null) {
                inputAnswer = $('input[type=text]').val();
            }
//            if ($('input[name=answer]:checked').val() != null) {
//                answers = $('input[name=answer]:checked').val();
//            }
//            var data = { 'user_ids[]' : []};

            $(":checked").each(function() {
                answers.push($(this).val());
            });

            console.log("answers:" + answers);

            $.ajax({
                type: "POST",
                cache: false,
                url: '<c:url value="/quizQuestion" />',
                data: "inputAnswer=" + inputAnswer + "&answers=" +  answers,
                success: function (response) {
                    $('#quiz').html(response);
                },
                error: function (e) {
                    alert('Error: ' + e);
                }
            });
        }

    </script>
</head>
<body>

<div id="quiz"></div>
<div id="nxt" style="display: none;">
    <a class="btn btn-primary nextBtn">Next</a>
</div>
</body>
</html>
