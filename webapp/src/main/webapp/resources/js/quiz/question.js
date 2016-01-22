/**
 * Created by user on 15.01.2016.
 */
var questionId = 0;
var questionName;
var questionImage = "";
var blankImageURL = '<img src="../../images/blank.png">';

$('#modalRemove3').on('show.bs.modal', function (e) {
    e.preventDefault();
    questionName = $(e.relatedTarget).data('name');
    questionId = $(e.relatedTarget).data('nameid');
    $(this).find('.myval').text('Do you really want to delete ' + questionName + '?');
});
$('#modalRemove3').find('.saveBtn').on('click', function () {
    $.ajax({
        type: "POST",
        url: '<c:url value="/questionDelete" />',
        data: {id: questionId},
        success: function () {
            showAlert($('#modalRemove3'), questionName + ' was deleted', 'success');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
});

$('#modalEdit3').on('show.bs.modal', function (e) {
    questionId = $(e.relatedTarget).data('nameid');
    var act = $(e.relatedTarget).data('action');
    var quizId = $(e.relatedTarget).data('quiz');
    var questionType = $(e.relatedTarget).data('qtype');
    var file = $('[name="file"]');
    file.val('');
    var imgContainer = $('#imgContainer');

    var isJpg = function (name) {
        return name.match(/jpg$/i)
    };
    var isPng = function (name) {
        return name.match(/png$/i)
    };
    $.ajax({
        type: "POST",
        url: '<c:url value="/quizSetList"/>',
        success: function (obj) {
            createSelectQuizSet(obj, 'dynamicInputQuizSet');
            $('.optionsQuizSet').val(quizId);
            $('.optionsType').val(questionType);
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
    if (act == 'add') {
        resetEdit($('#modalEdit3'));
        questionId = 0;
        imgContainer.html(blankImageURL);
    } else {
        getQuestionInfo(questionId);
    }

    $('.uploadBtn').on('click', function () {
        var filename = $.trim(file.val());
        if (!(isJpg(filename) || isPng(filename))) {
            alert('Please upload a JPG/PNG file');
            return;
        }
        $.ajax({
            url: '<c:url value="/upload"/>',
            type: "POST",
            data: new FormData(document.getElementById('fileForm')),
            enctype: 'multipart/form-data',
            dataType: 'text',
            processData: false,
            contentType: false,
            success: function (data) {
                imgContainer.html('');
                questionImage = data;
                var img = '<img class="img-responsive" src="data:image/png;base64,' + data + '" />';
                imgContainer.html(img);
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    });

    $('.clearBtn').on('click', function () {
        imgContainer.html(blankImageURL);
        file.val('');
    });

    $('.browseBtn').click(function () {
        $('input[type=file]').click();
    });
    $('input[type=file]').change(function () {
        console.info("input[type=file]=" + $(this).val());
        $('#fileName').html($(this).val());
    });
});

<!--Add or update -->
$('#modalEdit3').find('.saveBtn').on('click', function () {
    var question = $('.question').val();
    var weight = $('.weight').val();
    var quizId = $(".optionsQuizSet option:selected").val();
    var questionType = $(".optionsType option:selected").val();

    $.ajax({
        type: "POST",
        url: '<c:url value="/questionUpdate" />',
        data: "id=" + questionId + "&question=" + question + "&weight=" + weight + "&quizId=" + quizId +
        "&questionType=" + questionType + "&questionImage=" + questionImage,
        success: function (response) {
            showAlert($('#modalEdit3'), response, 'success');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
});

function getQuestionInfo(id) {
    $.ajax({
        type: "POST",
        url: '<c:url value="/questionInfo"/>',
        data: {id: id},
        success: function (obj) {
            $('#question').val(obj.question);
            $('#weight').val(obj.weight);
            if (obj.picture != null) {
                $('#imgContainer').html('<img class="img-responsive" src="data:image/png;base64,' + obj.picture + '" />');
            } else {
                $('#imgContainer').html('<img src="../../images/blank.png"/>');
            }
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}

//function createSelectQuizSet(values, divName) {
//    var select = $('<select name="optionsQuizSet" class="form-control optionsQuizSet"></select>');
//        $("#" + divName).html("");
//        $.each(values, function (i, obj) {
//            var option = $('<option></option>');
//            option.attr('value', obj.id);
//            option.text(obj.name);
//            select.append(option);
//        });
//    $("#" + divName).append(select);
//}

function updateTab() {
    var tabText = $('.nav-tabs .active').text();
    var tab;
    switch (tabText) {
        case 'Students':
            tab = 'students';
            break;
        case 'Groups':
            tab = 'studyGroups';
            break;
        case 'Quizzes':
            tab = 'quizSets';
            break;
        case 'Questions':
            tab = 'questions';
            break;
        case 'Answers':
            tab = 'answers';
            break;
    }

    $.ajax({
        type: "POST",
        cache: false,
        url: '<c:url value="/updateManagement" />',
        data: {tab : tab},
        success: function (response) {
            $('.tab-content').find('#' + tab).html(response);
            jump('top');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}







