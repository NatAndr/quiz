/**
 * Created by user on 04.01.2016.
 */
function resetEdit(modal) {
    modal.find(':input').val('');
    modal.find('.options').prop('selected', false);
}

function showResultModal(modal, msg) {
    modal.modal('hide');
    $('#result').on('show.bs.modal', function () {
        $(this).find('.myval').text(msg);
    });
        //$('#result').on('hide.bs.modal', location.reload());
    $('#result').modal('show');
}

function showAlert(modalToHide, message, alertType) {
    modalToHide.modal('hide');
    $('#alert_placeholder').append('<div id="alertdiv" class="alert alert-' + alertType + '"><a class="close" data-dismiss="alert">×</a><span>' + message + '</span></div>');
    setTimeout(function() {
        $("#alertdiv").remove();
    }, 10000);
    modalToHide.on('hide.bs.modal', showTab('questionsManagement', '#questions'))
}

function showTab(url, tab) {
    $.ajax({
        type: "POST",
        cache: false,
        url: '<c:url value="/'+ url +'" />',
        data: "",
        success: function (response) {
            //$('.tab-content').html(response);
            $('.tab-content').find(tab).html(response);
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}





