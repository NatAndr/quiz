/**
 * Created by user on 01.02.2016.
 */
$(document).ready(function () {
    initQuestions();
    $('#nxt').show();
    $('#nxt').on('click', '.nextBtn', nextQuestion);
    $('#nxt').on('click', '.endBtn', showResult);
    countdown("countdown", quizTime, 0);
});

function initQuestions() {
    $.ajax({
        type: "POST",
        cache: false,
        url: showQuestion,
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
        url: quizQuestion,
        data: "inputAnswer=" + inputAnswer + "&answers=" + answers,
        success: function (response) {
            if (response.indexOf('<title>Result') > -1) {
                window.location.replace(result);
            } else {
                $('#quiz').html(response);
            }
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}

function countdown(elementName, minutes, seconds) {
    var element, endTime, hours, mins, msLeft, time;

    if (!minutes) {
        return;
    }

    function twoDigits(n) {
        return (n <= 9 ? "0" + n : n);
    }

    function updateTimer() {
        msLeft = endTime - (+new Date);
        if (msLeft < 1000) {
            window.location.replace(result);
        } else {
            time = new Date(msLeft);
            hours = time.getUTCHours();
            mins = time.getUTCMinutes();
            element.innerHTML = (hours ? hours + ':' + twoDigits(mins) : mins) + ':' + twoDigits(time.getUTCSeconds());
            setTimeout(updateTimer, time.getUTCMilliseconds() + 500);
        }
    }

    element = document.getElementById(elementName);
    endTime = (+new Date) + 1000 * (60 * minutes + seconds) + 500;
    updateTimer();
}

function showResult() {
    console.log('showResult');
    window.location.replace(result);
}
