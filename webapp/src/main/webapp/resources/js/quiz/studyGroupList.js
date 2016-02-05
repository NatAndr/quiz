/**
 * Created by user on 02.02.2016.
 */
function createSelectStudyGroup(values, divName) {
    var select = $('<select name="options" class="form-control options"></select>');
    $("#" + divName).html("");
    select.append('<option value="" disabled selected>Select study group</option>');
    $.each(values, function (i, obj) {
        var option = $('<option></option>');
        option.attr('value', obj.id);
        option.text(obj.name);
        select.append(option);
    });
    $("#" + divName).append(select);
}

function studyGroupList() {
    $.ajax({
        type: "POST",
        url: studyGroupsList,
        success: function (obj) {
            createSelectStudyGroup(obj, 'dynamicInput');
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}
