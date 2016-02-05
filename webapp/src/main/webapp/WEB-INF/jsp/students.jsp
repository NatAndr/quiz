<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <th class="col-lg-1">Action</th>
                <th class="col-lg-2">First name</th>
                <th class="col-lg-2">Last name</th>
                <th class="col-lg-2">Login</th>
                <th>Group</th>
            </tr>
            </thead>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>
                        <a href="#" data-nameid="${student.id}" data-toggle="modal" data-target="#modalEdit0"
                           class="triggerEdit" data-action="edit">
                            <span class="glyphicon glyphicon-edit"></span></a>
                        &nbsp;&nbsp;
                        <a href="#" data-nameid="${student.id}" data-name="${student.firstName} ${student.lastName}"
                           data-toggle="modal" data-target="#modalRemove0" class="triggerRemove">
                            <span class="glyphicon glyphicon-remove"></span></a>

                    </td>
                    <td>${student.firstName}</td>
                    <td>${student.lastName}</td>
                    <td>${student.login}</td>
                    <td>${student.studyGroup}</td>
                </tr>
            </c:forEach>
        </table>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalEdit0" data-action="add">
            Add new
        </button>
    </div>
</div>

<!-- Modal Edit -->
<div id="modalEdit0" class="modal fade">
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
                        <a class="btn btn-default saveBtn" data-dismiss="modal">Save</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal Edit-->

<!-- Modal Remove -->
<div id="modalRemove0" class="modal fade">
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
    var studentDelete = '<c:url value="/studentDelete"/>';
    var studyGroupsList = '<c:url value="/studyGroupsList"/>';
    var studentUpdate = '<c:url value="/studentUpdate"/>';
    var studentInfo = '<c:url value="/studentInfo"/>';
</script>
<script src="<c:url value="../../resources/js/quiz/studyGroupList.js" />"></script>
<script src="<c:url value="../../resources/js/quiz/students.js" />"></script>

