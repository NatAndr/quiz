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
    }, 5000);
    modalToHide.on('hide.bs.modal', updateTab());
}

function jump(h){
    var top = document.getElementById(h).offsetTop;
    window.scrollTo(0, top);
}







