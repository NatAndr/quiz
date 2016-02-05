<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.01.2016
  Time: 14:28
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
                <th>Quiz set</th>
            </tr>
            </thead>
            <c:forEach var="quiz" items="${quizzes}">
                <tr>
                    <td>
                        <a href="#" data-nameid="${quiz.id}" data-toggle="modal" data-target="#modalEdit2"
                           class="triggerEdit" data-action="edit">
                            <span class="glyphicon glyphicon-edit"></span></a>
                        &nbsp;&nbsp;
                        <a href="#" data-nameid="${quiz.id}" data-name="${quiz.name}"
                           data-toggle="modal" data-target="#modalRemove2" class="triggerRemove">
                            <span class="glyphicon glyphicon-remove"></span></a>
                        &nbsp;&nbsp;
                        <input type="checkbox" name="toXML" value="${quiz.id}">
                    </td>
                    <td>${quiz.name}</td>
                </tr>
            </c:forEach>
        </table>
        <form id="fileFormXML">
            <input id="inputfileXML" type="file" name="fileXML" style="display: none;">
        </form>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalEdit2" data-action="add">
            Add new
        </button>
        <button type="button" class="btn btn-primary" id="toXML" disabled>
            Export to XML
        </button>
        <button type="button" class="btn btn-primary browseXML" id="fromXML">
            Import from XML
        </button>
    </div>
</div>

<!-- Modal Edit -->
<div id="modalEdit2" class="modal fade">
    <div class="container">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Quiz</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="name" class="control-label">Name:</label>
                            <input type="text" class="form-control name" id="name"
                                   required="required">
                        </div>
                    </form>

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
<div id="modalRemove2" class="modal fade">
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
    var quizSetDelete = '<c:url value="/quizSetDelete"/>';
    var quizSetUpdate = '<c:url value="/quizSetUpdate"/>';
    var quizSetInfo = '<c:url value="/quizSetInfo"/>';
    var quizSetToXML = '<c:url value="/quizSetToXML"/>';
    var quizSetFromXML = '<c:url value="/quizSetFromXML"/>';
</script>
<script src="<c:url value="../../resources/js/quiz/quizSets.js" />"></script>
</body>
</html>
