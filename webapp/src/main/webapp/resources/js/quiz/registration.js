/**
 * Created by user on 02.02.2016.
 */

var countEmptyFields = function () {
    var n = $('.required').filter(function () {
            return !$.trim($(this).val());
        }).length + checkPasswords() + checkStudyGroup();
    console.log('n =' + n);
    if (n) {
        $('#register').prop('disabled', true);
    } else {
        $('#register').prop('disabled', false);
    }
};


var checkPasswords = function () {
    var password = $('#password').val();
    var password2 = $('#confirmPassword').val();
    if (password != password2) {
        $('#confirmPasswordError').text('Passwords are not equal');
        return 1;
    } else {
        $('#confirmPasswordError').empty();
        return 0;
    }
};

var checkStudyGroup = function () {
    //$('#studyGroupId').change(function () {
    if ($('#studyGroupId').val() == '') {
        $('#studyGroupIdError').text('Select study group');
        return 1;
    } else {
        $('#studyGroupIdError').empty();
        return 0;
    }
    //});
};

function register() {
    var firstName = $('#firstName').val();
    var lastName = $('#lastName').val();
    var login = $('#login').val();
    var password = $('#password').val();
    var studyGroupId = $(".options option:selected").val();

    $.ajax({
        type: "POST",
        url: doRegister,
        data: "login=" + login + "&password=" + password + "&firstName=" + firstName + "&lastName=" + lastName +
        "&studyGroupId=" + studyGroupId,
        success: function (response) {
            if (response == 'ok') {
                window.location.replace(loginUrl);
            } else {
                showAlertDiv('errorRegistration', response);
            }
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}

function showAlertDiv(divId, msg) {
    $('#' + divId).text(msg);
    $('#' + divId).show();
}

$('#confirmPassword').on('keyup', checkPasswords);
$('#password').on('keyup', checkPasswords);
$('#register').on('click', register);
countEmptyFields();
$('.required').on('blur', countEmptyFields);

$('#cancel').on('click', function () {
    window.location.replace(loginUrl);
});
