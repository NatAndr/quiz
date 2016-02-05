/**
 * Created by user on 15.01.2016.
 */
var questionId = 0;
var questionName;
var questionImage = "";
var blankImageURL = '<img src="../../resources/images/blank.png">';
var quizId;
var imgTag = '<img style="max-height: 135px;" src="data:image/png;base64,';

$('#modalRemove3').on('show.bs.modal', function (e) {
    e.preventDefault();
    questionName = $(e.relatedTarget).data('name');
    questionId = $(e.relatedTarget).data('nameid');
    quizId = $(e.relatedTarget).data('quiz');
    $(this).find('.myval').text('Do you really want to delete ' + questionName + '?');
});
$('#modalRemove3').find('.saveBtn').on('click', function () {
    $.ajax({
        type: "POST",
        url: questionDelete,
        data: {id: questionId, quizId: quizId},
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
    file.empty();
    var imgContainer = $('#imgContainer');
    $('#fileName').empty();

    var isJpg = function (name) {
        return name.match(/jpg$/i)
    };
    var isPng = function (name) {
        return name.match(/png$/i)
    };
    $.ajax({
        type: "POST",
        url: quizSetList,
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

    $('.clearBtn').on('click', function () {
        imgContainer.html(blankImageURL);
        file.empty();
    });

    $('.browseBtn').click(function () {
        $('input[name=filePicture]').click();
    });
    $('input[name=filePicture]').change(function () {
        var filePicture = $(this).val().split('\\').pop();
        if (!(isJpg(filePicture) || isPng(filePicture))) {
            alert('Please upload a JPG/PNG file');
            return;
        }
        $.ajax({
            url: upload,
            type: "POST",
            data: new FormData(document.getElementById('fileForm')),
            enctype: 'multipart/form-data',
            dataType: 'text',
            processData: false,
            contentType: false,
            success: function (data) {
                imgContainer.html('');
                questionImage = data;
                var img = imgTag + data + '" />';
                imgContainer.html(img);
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    });
});

<!--Add or update -->
$('#modalEdit3').find('.saveBtn').on('click', function () {
    var question = $('.question').val();
    var weight = $('.weight').val();
    var quizId = $('.optionsQuizSet option:selected').val();
    var questionType = $('.optionsType option:selected').val();

    $.ajax({
        type: "POST",
        url: questionUpdate,
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
        url: questionInfo,
        data: {id: id},
        success: function (obj) {
            $('#question').val(obj.question);
            $('#weight').val(obj.weight);
            if (obj.picture != null) {
                $('#imgContainer').html(imgTag + obj.picture + '" />');
            } else {
                $('#imgContainer').html(blankImageURL);
            }
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}

function createSelectQuizSet(values, divName) {
    var select = $('<select name="optionsQuizSet" class="form-control optionsQuizSet"></select>');
    $("#" + divName).empty();
    $.each(values, function (i, obj) {
        var option = $('<option></option>');
        option.attr('value', obj.id);
        option.text(obj.name);
        select.append(option);
    });
    $("#" + divName).append(select);
}

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
        url: updateManagement,
        data: {tab: tab},
        success: function (response) {
            $('.tab-content').find('#' + tab).html(response);
            jump('top');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}







