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
            </tr>
            </thead>
            <c:forEach var="quizSet" items="${quizzes}">
            <c:forEach var="question" items="${quizSet.questions}">
                <tr>
                    <td>
                        <a href="#" data-nameid="${question.id}" data-toggle="modal" data-target="#modalEdit3"
                           class="triggerEdit" data-action="edit">
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
                </tr>
            </c:forEach>
            </c:forEach>
        </table>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalEdit3" data-action="add">
            Add new
        </button>
    </div>
</div>

<!-- Modal Edit -->
<div id="modalEdit" class="modal fade">
    <div class="container">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Student</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="firstName" class="control-label">First name:</label>
                            <input type="text" class="form-control firstName" id="firstName"
                                   required="required">
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="control-label">Last name:</label>
                            <input type="text" class="form-control lastName" id="lastName" required="required">
                        </div>

                        <div class="form-group">
                            <label for="dynamicInput" class="control-label">Study group:</label>

                            <div id="dynamicInput" class="form-group"></div>
                        </div>
                    </form>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-default saveBtn">Save</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal Edit-->

<!-- Modal Remove -->
<div id="modalRemove" class="modal fade">
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
                <a class="btn btn-danger saveBtn">Delete</a>
            </div>
        </div>
    </div>
</div>
<!-- Modal Remove -->

<script type="text/javascript">
    var studentId = 0;
    var studentName;

    $('#modalRemove3').on('show.bs.modal', function (e) {
        e.preventDefault();
        studentName = $(e.relatedTarget).data('name');
        studentId = $(e.relatedTarget).data('nameid');
        $(this).find('.myval').text('Do you really want to delete ' + studentName + '?');
    });
    $('#modalRemove3').find('.saveBtn').on('click', function () {
        $.ajax({
            type: "POST",
            url: '<c:url value="/studentDelete" />',
            data: {id: studentId},
            success: function () {
                showResultModal($('#modalRemove3'), studentName + ' was deleted');
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    });

    $('#modalEdit3').on('show.bs.modal', function (e) {
        studentId = $(e.relatedTarget).data('nameid');
        var act = $(e.relatedTarget).data('action');
        $.ajax({
            type: "POST",
            url: '<c:url value="/studyGroupsList"/>',
            success: function (obj) {
                createSelectStudyGroup(obj, 'dynamicInput');
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
        if (act != 'edit') {
            resetEdit($('#modalEdit3'));
            studentId = 0;
        } else {
            getStudentInfo(studentId);
        }
    });

    <!--Add or update student -->
    $('#modalEdit3').find('.saveBtn').on('click', function () {
        var firstName = $('#modalEdit3').find('.firstName').val();
        var lastName = $('#modalEdit3').find('.lastName').val();
        var studyGroupId = $(".options option:selected").val();

        $.ajax({
            type: "POST",
            url: '<c:url value="/studentUpdate" />',
            data: "id=" + studentId + "&firstName=" + firstName + "&lastName=" + lastName + "&studyGroupId=" + studyGroupId,
            success: function (response) {
                showResultModal($('#modalEdit3'), response);
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    });

    function getStudentInfo(id) {
        $.ajax({
            type: "POST",
            url: '<c:url value="/studentInfo"/>',
            data: {id: id},
            success: function (obj) {
                $('#modalEdit3').find('.firstName').val(obj.firstName);
                $('#modalEdit3').find('.lastName').val(obj.lastName);
                $('#modalEdit3').find('.options').val(obj.studyGroup.id);
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    }

    function createSelectStudyGroup(values, divName) {
        var select = $('<select name="options" class="form-control options"></select>');
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
