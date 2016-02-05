/**
 * Created by user on 22.01.2016.
 */
var quizId = 0;
var quizName;

$('#modalRemove2').on('show.bs.modal', function (e) {
    e.preventDefault();
    quizName = $(e.relatedTarget).data('name');
    quizId = $(e.relatedTarget).data('nameid');
    $(this).find('.myval').text('Do you really want to delete ' + quizName + '?');
});
$('#modalRemove2').find('.saveBtn').on('click', function () {
    $.ajax({
        type: "POST",
        url: quizSetDelete,
        data: {id: quizId},
        success: function () {
            showAlert($('#modalRemove2'), quizName + ' was deleted', 'success');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
});

$('#modalEdit2').on('show.bs.modal', function (e) {
    quizId = $(e.relatedTarget).data('nameid');
    var act = $(e.relatedTarget).data('action');
    if (act != 'edit') {
        resetEdit($('#modalEdit2'));
        quizId = 0;
    } else {
        getQuizSetInfo(quizId);
    }
});

<!--Add or update -->
$('#modalEdit2').find('.saveBtn').on('click', function () {
    quizName = $('#modalEdit2').find('.name').val();

    $.ajax({
        type: "POST",
        url: quizSetUpdate,
        data: "id=" + quizId + "&name=" + quizName,
        success: function (response) {
            showAlert($('#modalEdit2'), response, 'success');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
});

function getQuizSetInfo(id) {
    $.ajax({
        type: "POST",
        url: quizSetInfo,
        data: {id: id},
        success: function (obj) {
            $('#modalEdit2').find('.name').val(obj.name);
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}

$('#toXML').on('click', function () {
    var checkedQuizzes = [];

    $("input:checkbox[name=toXML]:checked").each(function () {
        checkedQuizzes.push($(this).val());
    });

    $.ajax({
        type: "POST",
        url: quizSetToXML,
        data: "checkedQuizzes=" + checkedQuizzes,
        success: function (response) {
            showAlert($('#modalEdit2'), response, 'success');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
});

var countChecked = function () {
    var n = $("input:checkbox[name=toXML]:checked").length;
    if (n > 0) {
        $('#toXML').prop('disabled', false);
    } else {
        $('#toXML').prop('disabled', true);
    }
};
countChecked();

$("input:checkbox[name=toXML]").on('click', countChecked);

var isXml = function (name) {
    return name.match(/xml$/i)
};

$('.browseXML').click(function () {
    $('input[name=fileXML]').click();
});
$('input[name=fileXML]').change(function () {
    var fileXML = $(this).val().split('\\').pop();
    if (!(isXml(fileXML))) {
        alert('Please upload XML file');
        return;
    }
    $.ajax({
        url: quizSetFromXML,
        type: "POST",
        data: new FormData(document.getElementById('fileFormXML')),
        enctype: 'multipart/form-data',
        dataType: 'text',
        processData: false,
        contentType: false,
        success: function (response) {
            showAlert($('#modalEdit2'), response, 'success');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
});
