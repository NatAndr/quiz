/**
 * Created by user on 22.01.2016.
 */
var answerId = 0;
var answerName;
var questionId;

$('#modalEdit4').on('show.bs.modal', function (e) {
    questionId = $(e.relatedTarget).data('question');
    answerId = $(e.relatedTarget).data('nameid');
    var act = $(e.relatedTarget).data('action');

    $.ajax({
        type: "POST",
        url: questionList,
        success: function (obj) {
            createSelectQuestion(obj, 'dynamicInputQuestion');
            $('.optionsQuestion').val(questionId);
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });

    if (act == 'add') {
        resetEdit($('#modalEdit4'));
        answerId = 0;
    } else {
        getAnswerInfo(answerId);
    }

});

$('#modalRemove4').on('show.bs.modal', function (e) {
    e.preventDefault();
    answerName = $(e.relatedTarget).data('name');
    answerId = $(e.relatedTarget).data('nameid');
    questionId = $(e.relatedTarget).data('question');
    $(this).find('.myval').text('Do you really want to delete ' + answerName + '?');
});
$('#modalRemove4').find('.saveBtn').on('click', function () {
    $.ajax({
        type: "POST",
        url: answerDelete,
        data: {id: answerId, questionId: questionId},
        success: function () {
            showAlert($('#modalRemove4'), answerName + ' was deleted', 'success');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
});

<!--Add or update -->
$('#modalEdit4').find('.saveBtn').on('click', function () {
    var answer = $('.answer').val();
    var isCorrect = $('.isCorrect').prop('checked');
    var question = $(".optionsQuestion option:selected").val();

    $.ajax({
        type: "POST",
        url: answerUpdate,
        data: "id=" + answerId + "&questionId=" + question + "&isCorrect=" + isCorrect + "&answer=" + answer,
        success: function (response) {
            showAlert($('#modalEdit4'), response, 'success');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
});

function getAnswerInfo(id) {
    $.ajax({
        type: "POST",
        url: answerInfo,
        data: {id: id},
        success: function (obj) {
            $('#answer').val(obj.answer);
            if (obj.correct == true) {
                $('#setCorrect').html('<input type="checkbox" class="isCorrect" id="isCorrect" checked>&nbsp;yes');
            } else {
                $('#setCorrect').html('<input type="checkbox" class="isCorrect" id="isCorrect">&nbsp;yes');
            }
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}

function createSelectQuestion(values, divName) {
    var select = $('<select name="optionsQuestion" class="form-control optionsQuestion"></select>');
    $("#" + divName).html("");
    $.each(values, function (i, obj) {
        var option = $('<option></option>');
        option.attr('value', obj.id);
        option.text(obj.question);
        select.append(option);
    });
    $("#" + divName).append(select);
}
