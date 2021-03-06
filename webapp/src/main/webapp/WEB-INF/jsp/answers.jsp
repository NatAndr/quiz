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
            <c:set var="i" value="0" scope="page"/>
            <c:forEach var="question" items="${questions}">
                <c:set var="i" value="${i + 1}" scope="page"/>
                <c:set var="j" value="0" scope="page"/>
                <c:forEach var="answer" items="${question.answers}">
                    <c:set var="j" value="${j + 1}" scope="page"/>
                    <tr>
                        <td>
                            <a href="#" data-nameid="${answer.id}" data-toggle="modal" data-target="#modalEdit4"
                               class="triggerEdit" data-action="edit"
                               data-question="${question.id}" data-questions="${questions}">
                                <span class="glyphicon glyphicon-edit"></span></a>
                            &nbsp;&nbsp;
                            <a href="#" data-nameid="${answer.id}" data-name="${answer.answer}"
                               data-question="${question.id}"
                               data-toggle="modal" data-target="#modalRemove4" class="triggerRemove">
                                <span class="glyphicon glyphicon-remove"></span></a>

                        </td>
                        <td>${i}. ${question.question}</td>
                        <td>${i}.${j}. ${answer.answer}</td>
                        <td>
                            <c:choose>
                                <c:when test="${answer.isCorrect eq true}"><input type="checkbox" checked
                                                                                  disabled></c:when>
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
                        <textarea class="form-control answer" id="answer" rows="3"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="setCorrect" class="control-label">Is correct:</label>

                        <div id="setCorrect"><input type="checkbox" class="isCorrect" id="isCorrect" checked="">&nbsp;yes
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
<div id="modalRemove4" class="modal fade">
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
    var questionList = '<c:url value="/questionList"/>';
    var answerDelete = '<c:url value="/answerDelete"/>';
    var answerUpdate = '<c:url value="/answerUpdate"/>';
    var answerInfo = '<c:url value="/answerInfo"/>';
</script>
<script src="<c:url value="../../resources/js/quiz/answers.js" />"></script>

</body>
</html>
