/**
 * Created by user on 01.02.2016.
 */
var grId = 0;
var grName;

$('#modalRemove1').on('show.bs.modal', function (e) {
    e.preventDefault();
    grName = $(e.relatedTarget).data('name');
    grId = $(e.relatedTarget).data('nameid');
    $(this).find('.myval').text('Do you really want to delete ' + grName + '?');
});
$('#modalRemove1').find('.saveBtn').on('click', function () {
    $.ajax({
        type: "POST",
        url: studyGroupDelete,
        data: {id: grId},
        success: function () {
            showAlert($('#modalRemove1'), grName + ' was deleted', 'success');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
});

$('#modalEdit1').on('show.bs.modal', function (e) {
    grId = $(e.relatedTarget).data('nameid');
    var act = $(e.relatedTarget).data('action');
    if (act != 'edit') {
        resetEdit($('#modalEdit1'));
        grId = 0;
    } else {
        getStudyGroupInfo(grId);
    }
});

<!--Add or update group -->
$('#modalEdit1').find('.saveBtn').on('click', function () {
    var groupName = $('#modalEdit1').find('.name').val();

    $.ajax({
        type: "POST",
        url: studyGroupUpdate,
        data: "id=" + grId + "&name=" + groupName,
        success: function (response) {
            showAlert($('#modalEdit1'), response, 'success');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
});

function getStudyGroupInfo(id) {
    $.ajax({
        type: "POST",
        url: studyGroupInfo,
        data: {id: id},
        success: function (obj) {
            $('#modalEdit1').find('.name').val(obj.name);
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}
