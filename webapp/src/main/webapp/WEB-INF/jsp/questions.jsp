<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.01.2016
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE html>
    <%@include file="header.jsp" %>
    <style type="text/css">
        .bs-example {
            margin: 20px;
        }
    </style>
    <title></title>
</head>
<body>

<!--data table -->
<div class="container">
    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <th class="col-md-1">Action</th>
                <th>Quiz</th>
                <th>Question</th>
                <th class="col-md-1">Type</th>
                <th class="col-md-1">Weight</th>
                <th class="col-md-1">Image</th>
            </tr>
            </thead>
            <c:forEach var="quizSet" items="${quizzes}">
                <c:forEach var="question" items="${quizSet.questions}">
                    <tr>
                        <td>
                            <a href="#" data-nameid="${question.id}" data-toggle="modal" data-target="#modalEdit3"
                               class="triggerEdit" data-action="edit"
                               data-quiz="${quizSet.id}" data-qtype="${question.questionType}"
                               data-quizzes="${quizzes}">
                                <span class="glyphicon glyphicon-edit"></span></a>
                            &nbsp;&nbsp;
                            <a href="#" data-nameid="${question.id}" data-name="${question.question}"
                               data-toggle="modal" data-target="#modalRemove3" class="triggerRemove">
                                <span class="glyphicon glyphicon-remove"></span></a>

                        </td>
                        <td>${quizSet.name}</td>
                        <td>${question.question}</td>
                        <td>${question.questionType}</td>
                        <td>${question.weight}</td>
                        <td><c:if test="${not empty question.picture}"><input type="checkbox" checked disabled></c:if>
                            <c:if test="${empty question.picture}"><input type="checkbox" disabled></c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalEdit3" data-action="add">
            Add new
        </button>
        <img  src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAMAAABHPGVmAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAA8UExURQAAAAAAOgAAZgA6kABmtjoAADqQ22YAAGa2/5A6AJDb/7ZmALb//9uQOtv///+2Zv/bkP//tv//2////+YRgBkAAAEoSURBVGje7ZdrD4IwDEWr4nPO+fj//9WEJQaQXW6nIRJvv5ETd+ZKW2aPGcIkkUQSSSSRRBJJJJFEEkk+k1y3luPgp5wkWTc2Fw8lJbe9DWJHU1ZyP9pbNCR1Stbn9iEOd4upS9LZW15odaIon5PQ/03sHwmmtXXSOyAn5YsxwYpIE/VCSvJLu6uitCSfSFNF+d4V4DJhOZJZjmuWxOe+fqiitCTAzhEm+oo5arGpoqwkwRGYJgeksUkvbRVTRhLhjI3sBDbmP4yvgikteX2QjJ44pm7J+KjA1CcproGpLyfFZTD9QUnxxcFUkgVL2rHa1FHdGRcsaVNbbByY0hJ88YjT9zhGEuDUC9TN948ks+Sk3Wx5q+ErvUsVL4kkkkgiiSSSSPK1eAICtbqXmpeAKwAAAABJRU5ErkJggg==" />
        <img  src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAIAAAD/gAIDAAAAAXNSR…CjAUYCzAWYCzAWICxAGMBxgKMBRgLMBZgLMBYgLEAYwHGAowFGAsIxHo77nfrDqfbnwzKTe5TF t0uH3wpv3x7fZv uQmQyWx rf51rdXbvKI38d6/m40NV6e3ORBiVgrn/TZ3i fgtzkQaWxWue5ttiLpXKTBxXcWdd7pfeTrWz1PERu8piSCx5ZLlW1U27yzfxYK19xZTvlJl9tEWuxU93tkpv8YZNYi/el7AHITf6wSazFA1C3Um7yhbEAX0PACx7YItZffbC2iPV4 nXHn5v8aXqsxUJlp5 b/GVurOU VRvlJv8wLdbaNhcFC UmP0rFWly1a4aulNzkplCsJ6f90 A ucltmVit0x9/RXKTO0yO9etdcpM7zIlVt0lucoc5d1bhSrnJbcYCpsSq/HrKTW4zFmAswFiAsYBQrPufQ5VfWbnJbaFY/5OxAGMBxgKmXPCV93Buclsk1uN/DVwULZWb3CMS6/630FXRD6Lc5B7GAowFeGcBkVgXd49A6eHnJjelYv1LxgKMBRgLMBZgLMBYgLEAYwHGAowFGAswFmAswFiAsQBjAcYCjAUYCzAWYCzAWICxup3P72/ 8m5FTkPPAAAAAElFTkSuQmCC" />
    </div>
    <br>

    <div id="alert_placeholder"></div>
</div>

<!-- Modal Edit -->
<div id="modalEdit3" class="modal fade modal-lg">
    <div class="container">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Question</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="dynamicInputQuizSet" class="control-label">Quiz:</label>

                        <div id="dynamicInputQuizSet" class="form-group"></div>
                    </div>
                    <div class="form-group">
                        <label for="question" class="control-label">Question:</label>
                        <%--<input type="text" class="form-control question" id="question">--%>
                        <textarea class="form-control question" id="question" rows="3"></textarea>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-lg-6">
                                <label for="weight" class="control-label">Weight:</label>
                                <input type="text" class="form-control weight" id="weight" maxlength="3">
                            </div>
                            <div class="col-lg-6">
                                <label for="dynamicInputType" class="control-label">Type:</label>
                                <select id="dynamicInputType" name="optionsType" class="form-control optionsType">
                                    <option value="SINGLE">Single</option>
                                    <option value="MULTIPLE">Multiple</option>
                                    <option value="INPUT">Input</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-lg-4">
                                <label for="imgContainer" class="control-label">Image:</label>

                                <div id="imgContainer"></div>
                            </div>
                            <div class="col-lg-8">
                                <form id="fileForm">
                                    <%--<input type="file" name="file" class="filestyle" data-buttonName="btn-primary" />--%>
                                    <%--<label for="inputfile" class="control-label">Browse:</label>--%>
                                    <input id="inputfile" type="file" name="file" style="display: none;">
                                    <br>
                                    <a class="btn btn-primary browseBtn"><span class="glyphicon glyphicon-folder-open"></span></a>
                                        <a class="btn btn-primary uploadBtn"><span class="glyphicon glyphicon-ok"></span></a>
                                        <a class="btn btn-primary clearBtn"><span class="glyphicon glyphicon-remove"></span></a>
                                        <br><div id="fileName"></div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary saveBtn" data-dismiss="modal">Save</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal Edit-->

<!-- Modal Remove -->
<div id="modalRemove3" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Confirmation</h4>
            </div>
            <div class="modal-body">
                <h4 class="myval"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <a class="btn btn-danger saveBtn" data-dismiss="modal">Delete</a>
            </div>
        </div>
    </div>
</div>
<!-- Modal Remove -->

<script type="text/javascript">
    var questionId = 0;
    var questionName;
    var questionImage = "";

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
//                showResultModal($('#modalRemove3'), questionName + ' was deleted');
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
            imgContainer.html('<img src="../../resources/images/blank.png">');
        } else {
            getQuestionInfo(questionId);
//            getQuestionPicture(questionId);
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
//                    imgContainer.html('');
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
            imgContainer.html('<img src="../../resources/images/blank.png">');
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
                console.info("getQuestionInfo");
                $('#question').val(obj.question);
                $('#weight').val(obj.weight);
                if (obj.picture != null) {
                    console.info('<img class="img-responsive" src="data:image/png;base64,' + obj.picture + '" />');
                    $('#imgContainer').html('<img class="img-responsive" src="data:image/png;base64,' + obj.picture + '" />');
                } else {
                    $('#imgContainer').html('<img src="../../resources/images/blank.png"/>');
                }
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    }

    function createSelectQuizSet(values, divName) {
        var select = $('<select name="optionsQuizSet" class="form-control optionsQuizSet"></select>');
        $("#" + divName).html("");
        $.each(values, function (i, obj) {
            var option = $('<option></option>');
            option.attr('value', obj.id);
            option.text(obj.name);
            select.append(option);
        });
        $("#" + divName).append(select);
    }

</script>
</body>
</html>
