<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.11.2015
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<!--data table -->
<div class="container">
    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <th class="col-md-1">Action</th>
                <th>Study group</th>
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
    var studyGroupDelete = '<c:url value="/studyGroupDelete"/>';
    var studyGroupUpdate = '<c:url value="/studyGroupUpdate"/>';
    var studyGroupInfo = '<c:url value="/studyGroupInfo"/>';
</script>
<%--<script src="<c:url value="../../resources/js/quiz/studyGroups.js" />"></script>--%>

