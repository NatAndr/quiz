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
        $(document).ready(function () {
            initQuestions();
            $('#nxt').show();
            $('#nxt').on('click', '.nextBtn', nextQuestion);
            countdown( "countdown", '${time}', 0);
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
            var inputText = $('input[type=text]');

            if (inputText.val() != null) {
                inputAnswer = inputText.val();
                answers.push(inputText.attr("name"));
            }
            $(":checked").each(function () {
                answers.push($(this).val());
            });

            $.ajax({
                type: "POST",
                cache: false,
                url: '<c:url value="/quizQuestion" />',
                data: "inputAnswer=" + inputAnswer + "&answers=" + answers,
                success: function (response) {
                    <%--if ('${finish}' == true) {--%>
                    if (response.indexOf('<title>Result') > -1) {
                        console.log("result");
                        window.location.replace('<c:url value="/result" />');
                    } else {
                        $('#quiz').html(response);
                    }
                },
                error: function (e) {
                    alert('Error: ' + e);
                }
            });
        }

        function countdown( elementName, minutes, seconds ) {
            var element, endTime, hours, mins, msLeft, time;

            function twoDigits( n ) {
                return (n <= 9 ? "0" + n : n);
            }

            function updateTimer() {
                msLeft = endTime - (+new Date);
                if ( msLeft < 1000 ) {
                    window.location.replace('<c:url value="/result" />');
                } else {
                    time = new Date( msLeft );
                    hours = time.getUTCHours();
                    mins = time.getUTCMinutes();
                    element.innerHTML = (hours ? hours + ':' + twoDigits( mins ) : mins) + ':' + twoDigits( time.getUTCSeconds() );
                    setTimeout( updateTimer, time.getUTCMilliseconds() + 500 );
                }
            }
            element = document.getElementById( elementName );
            endTime = (+new Date) + 1000 * (60*minutes + seconds) + 500;
            updateTimer();
        }

    </script>
</head>
<body>

<div class="container">
    <div class="row col-lg-offset-2">
        <h2>${quizSetName}</h2>
        <h3><div id="countdown"></div></h3>
        <div id="quiz"></div>
        <div id="nxt" style="display: none;">
            <a class="btn btn-primary nextBtn">Next</a>
        </div>
    </div>
</div>
</body>
</html>
