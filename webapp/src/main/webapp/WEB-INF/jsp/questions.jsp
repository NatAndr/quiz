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
        <div id="alert_placeholder"></div>
        <br>
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
                               data-quiz="${quizSet.id}"
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
    </div>

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
                            <div class="col-lg-2">
                                <label for="imgContainer" class="control-label">Image:</label>

                                <form id="fileForm">
                                    <input id="inputfile" type="file" name="filePicture" style="display: none;">
                                    <br>
                                    <a class="btn btn-primary btn-xs browseBtn"><span
                                            class="glyphicon glyphicon-folder-open"></span></a>
                                    <a class="btn btn-primary btn-xs clearBtn"><span
                                            class="glyphicon glyphicon-remove"></span></a>
                                    <br>

                                    <div id="fileName"></div>
                                </form>
                            </div>
                            <div class="col-lg-10">
                                <br>

                                <div id="imgContainer" style="height: 140px;"></div>
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
    var questionDelete = '<c:url value="/questionDelete"/>';
    var quizSetList = '<c:url value="/quizSetList"/>';
    var upload = '<c:url value="/upload"/>';
    var questionUpdate = '<c:url value="/questionUpdate"/>';
    var questionInfo = '<c:url value="/questionInfo"/>';
    var updateManagement = '<c:url value="/updateManagement"/>';
</script>
<script src="<c:url value="../../resources/js/quiz/questions.js" />"></script>
</body>
</html>
