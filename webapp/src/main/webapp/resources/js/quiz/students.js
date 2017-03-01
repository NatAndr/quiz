/**
 * Created by user on 01.02.2016.
 */
var studentId = 0;
var studentName;

$(document).ready( function () {
    $('#table_students').DataTable();
} );

$('#modalRemove0').on('show.bs.modal', function (e) {
    e.preventDefault();
    studentName = $(e.relatedTarget).data('name');
    studentId = $(e.relatedTarget).data('nameid');
    $(this).find('.myval').text('Do you really want to delete ' + studentName + '?');
});
$('#modalRemove0').find('.saveBtn').on('click', function () {
    $.ajax({
        type: "POST",
        url: studentDelete,
        data: {id: studentId},
        success: function () {
            showAlert($('#modalRemove0'), studentName + ' was deleted', 'success');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
});

$('#modalEdit0').on('show.bs.modal', function (e) {
    studentId = $(e.relatedTarget).data('nameid');
    var act = $(e.relatedTarget).data('action');
    studyGroupList();
    if (act == 'add') {
        resetEdit($('#modalEdit0'));
        studentId = 0;
    } else {
        getStudentInfo(studentId);
    }
});

<!--Add or update student -->
$('#modalEdit0').find('.saveBtn').on('click', function () {
    var firstName = $('.firstName').val();
    var lastName = $('.lastName').val();
    var login = $('.login').val();
    var studyGroupId = $(".options option:selected").val();

    $.ajax({
        type: "POST",
        url: studentUpdate,
        data: "id=" + studentId + "&firstName=" + firstName + "&lastName=" + lastName + "&login=" + login + "&studyGroupId=" + studyGroupId,
        success: function (response) {
            showAlert($('#modalEdit0'), response, 'success');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
});

function getStudentInfo(sId) {
    $.ajax({
        type: "POST",
        url: studentInfo,
        data: {id: sId},
        success: function (obj) {
            $('.firstName').val(obj.firstName);
            $('.lastName').val(obj.lastName);
            $('.options').val(obj.studyGroup.id);
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}


