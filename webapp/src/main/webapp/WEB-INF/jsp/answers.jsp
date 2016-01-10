<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.01.2016
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="container">
    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <th class="col-md-1">Action</th>
                <th>Question</th>
                <th class="col-md-3">Answer</th>
                <th class="col-md-1">Correct</th>
            </tr>
            </thead>
            <c:set var="i" value="0" scope="page" />
            <c:forEach var="question" items="${questions}">
                <c:set var="i" value="${i + 1}" scope="page"/>
                <c:set var="j" value="0" scope="page" />
                <c:forEach var="answer" items="${question.answers}">
                    <c:set var="j" value="${j + 1}" scope="page"/>
                    <tr>
                        <td>
                            <a href="#" data-nameid="${answer.id}" data-toggle="modal" data-target="#modalEdit4"
                               class="triggerEdit" data-action="edit"
                               data-question="${question.id}"
                               data-questions="${questions}">
                                <span class="glyphicon glyphicon-edit"></span></a>
                            &nbsp;&nbsp;
                            <a href="#" data-nameid="${answer.id}" data-name="${answer.answer}"
                               data-toggle="modal" data-target="#modalRemove4" class="triggerRemove">
                                <span class="glyphicon glyphicon-remove"></span></a>

                        </td>
                        <td>${i}. ${question.question}</td>
                        <td>${i}.${j}. ${answer.answer}</td>
                        <td>
                            <c:choose>
                                <c:when test="${answer.isCorrect eq true}"><input type="checkbox" checked disabled></c:when>
                                <c:otherwise><input type="checkbox" disabled></c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalEdit4" data-action="add">
            Add new
        </button>
    </div>
    <br>

    <div id="alert_placeholder"></div>
</div>

<!-- Modal Edit -->
<div id="modalEdit4" class="modal fade modal-lg">
    <div class="container">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Question</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="dynamicInputQuestion" class="control-label">Question:</label>
                        <div id="dynamicInputQuestion" class="form-group"></div>
                    </div>
                    <div class="form-group">
                        <label for="answer" class="control-label">Answer:</label>
                        <%--<input type="text" class="form-control question" id="question">--%>
                        <textarea class="form-control answer" id="answer" rows="3"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="setCorrect" class="control-label">Is correct:</label>
                        <div id="setCorrect"></div>
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


<script type="text/javascript">
    var answerId = 0;
    var answerName;

    $('#modalEdit4').on('show.bs.modal', function (e) {
        questionId = $(e.relatedTarget).data('question');
        answerId = $(e.relatedTarget).data('nameid');
        var act = $(e.relatedTarget).data('action');

        $.ajax({
            type: "POST",
            url: '<c:url value="/questionList"/>',
            success: function (obj) {
                createSelectQuestion(obj, 'dynamicInputQuestion');
                $('.optionsQuestion').val(questionId);
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });

        if (act == 'add') {
            resetEdit($('#modalEdit4'));
            answerId = 0;
        } else {
            getAnswerInfo(answerId);
        }

    });

    function getAnswerInfo(id) {
        $.ajax({
            type: "POST",
            url: '<c:url value="/answerInfo"/>',
            data: {id: id},
            success: function (obj) {
                $('#answer').val(obj.answer);
                if (obj.isCorrect) {
                    $('#setCorrect').html('<input type="checkbox" class="isCorrect" id="isCorrect" checked>&nbsp;yes');
                } else {
                    $('#setCorrect').html('<input type="checkbox" class="isCorrect" id="isCorrect">&nbsp;yes');
                }
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    }

    function createSelectQuestion(values, divName) {
        var select = $('<select name="optionsQuestion" class="form-control optionsQuestion"></select>');
        $("#" + divName).html("");
        $.each(values, function (i, obj) {
            var option = $('<option></option>');
            option.attr('value', obj.id);
            option.text(obj.question);
            select.append(option);
        });
        $("#" + divName).append(select);
    }

</script>

</body>
</html>
