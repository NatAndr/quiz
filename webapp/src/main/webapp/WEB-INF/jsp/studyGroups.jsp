<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.11.2015
  Time: 15:54
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
</head>
<body>
<!--data table -->
<div class="container">
    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <th class="col-md-1">Action</th>
                <th>Stydy group</th>
            </tr>
            </thead>
            <c:forEach var="studyGroup" items="${studyGroups}">
                <tr>
                    <td>
                        <a href="#" data-nameid="${studyGroup.id}" data-toggle="modal" data-target="#modalEdit1"
                           class="triggerEdit" data-action="edit">
                            <span class="glyphicon glyphicon-edit"></span></a>
                        &nbsp;&nbsp;
                        <a href="#" data-nameid="${studyGroup.id}" data-name="${studyGroup.name}"
                           data-toggle="modal" data-target="#modalRemove1" class="triggerRemove">
                            <span class="glyphicon glyphicon-remove"></span></a>

                    </td>
                    <td>${studyGroup.name}</td>
                </tr>
            </c:forEach>
        </table>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalEdit1" data-action="add">
            Add new
        </button>
    </div>
</div>

<!-- Modal Edit -->
<div id="modalEdit1" class="modal fade">
    <div class="container">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Study group</h4>
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
                        <a class="btn btn-default saveBtn" data-dismiss="modal">Save</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal Edit-->

<!-- Modal Remove -->
<div id="modalRemove1" class="modal fade">
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
    var grId = 0;
    var grName;

    $('#modalRemove1').on('show.bs.modal', function (e) {
        e.preventDefault();
        grName = $(e.relatedTarget).data('name');
        grId = $(e.relatedTarget).data('nameid');
        $(this).find('.myval').text('Do you really want to delete ' + grName + '?');
    });
    $('#modalRemove1').find('.saveBtn').on('click', function () {
        $.ajax({
            type: "POST",
            url: '<c:url value="/studyGroupDelete" />',
            data: {id: grId},
            success: function () {
//                showResultModal($('#modalRemove1'), grName + ' was deleted');
                showAlert($('#modalRemove1'), grName + ' was deleted', 'success');
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    });

    $('#modalEdit1').on('show.bs.modal', function (e) {
        grId = $(e.relatedTarget).data('nameid');
        var act = $(e.relatedTarget).data('action');
        if (act != 'edit') {
            resetEdit($('#modalEdit1'));
            grId = 0;
        } else {
            getStudyGroupInfo(grId);
        }
    });

    <!--Add or update group -->
    $('#modalEdit1').find('.saveBtn').on('click', function () {
        var groupName = $('#modalEdit1').find('.name').val();

        $.ajax({
            type: "POST",
            url: '<c:url value="/studyGroupUpdate" />',
            data: "id=" + grId + "&name=" + groupName,
            success: function (response) {
                showAlert($('#modalEdit1'), response, 'success');
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    });

    function getStudyGroupInfo(id) {
        $.ajax({
            type: "POST",
            url: '<c:url value="/studyGroupInfo"/>',
            data: {id: id},
            success: function (obj) {
                $('#modalEdit1').find('.name').val(obj.name);
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    }

</script>
</body>
</html>
