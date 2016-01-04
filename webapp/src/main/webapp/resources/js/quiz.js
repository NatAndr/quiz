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
//        $('#result').on('hidden.bs.modal', location.reload());
    $('#result').modal('show');
}


