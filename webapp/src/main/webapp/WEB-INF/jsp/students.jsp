<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <script type="text/javascript">
        function setStudentDetails(id) {
            console.info("id=" + id);
            $.ajax({
                type: "POST",
                url: '<c:url value="/studentInfo"/>',
                data: "id=" + id,
                success: function (response) {
                    var obj = JSON.parse(response);
                    $("#modalEdit.firstName").val(obj.$1);
                    $("#modalEdit.lastName").val(obj.$2);
                },
                error: function (e) {
                    alert('Error: ' + e);
                }
            });
        }
        $(document).ready(function () {
            $(".triggerEdit").click(function (e) {
                e.preventDefault();
                console.info("href=" + $(this).attr('href'));
                setStudentDetails($(this).attr("name"));
                $("#modalEdit.saveBtn").attr("href", $(this).attr("href"));
                console.info("href=%s, id=%d", $(this).attr("href"), $(this).attr("id"));
                $("#modalEdit").modal();
            });
            $('.triggerRemove').click(function (e) {
                e.preventDefault();
                console.info($(this).attr('href'));
                console.info($(this).attr('id'));
                console.info($(this).attr('name'));
                $('#modalRemove.saveBtn').attr('href', $(this).attr('href'));
                $('#modalRemove.modal-body').html($(this).attr('name'));
                $('#modalRemove').modal();
            });
        });
    </script>
</head>
<body>

<div class="container">
    <table class="table table-striped">
        <thead>
        <td class="col-md-2">Action</td>
        <td class="col-md-3">First name</td>
        <td class="col-md-3">Last name</td>
        <td class="col-md-3">Group</td>
        </thead>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>
                    <a id="edit" name="${student.id}" class="triggerEdit"
                       href='<spring:url value="/studentEdit?id=${student.id}"/>'>
                        <span class="glyphicon glyphicon-edit"></span></a>
                    &nbsp;&nbsp;
                    <a id="delete" name="${student.firstName} ${student.lastName}" class="triggerRemove"
                       href='<c:url value="/studentDelete?id=${student.id}"/>'>
                        <span class="glyphicon glyphicon-remove"></span></a>
                </td>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td>${student.studyGroup}</td>
            </tr>
        </c:forEach>
    </table>
    <%--<form action="${pageContext.request.contextPath}/studentAddOrUpdate" method="post">--%>
        <%--<button type="submit" class="btn btn-primary" name="Add">Add new</button>--%>
    <%--</form>--%>
    <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modalAdd">
        Add new
    </button>
</div>

<!-- Modal Edit -->
<div id="modalEdit" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Student</h4>
            </div>
            <div class="modal-body">
                <%--<form action="${pageContext.request.contextPath}/studentAddOrUpdate" method="post">--%>
                <c:if test="${studyGroup!=null}">
                    <input type="hidden" name="id" value="${student.id}"/>
                </c:if>
                <div class="form-group">
                    <label for="firstName" class="col-lg-3 control-label">First name<sup>*</sup></label>

                    <div class="col-lg-8">
                        <div class="row">
                            <div class="col-lg-4">
                                <input id="firstName" name="firstName" class="form-control firstName"
                                       required="required"
                                       type="text"
                                       value="${student.firstName}" maxlength="30"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastName" class="col-lg-3 control-label">Last name<sup>*</sup></label>

                    <div class="col-lg-8">
                        <div class="row">
                            <div class="col-lg-4">
                                <input id="lastName" name="lastName" class="form-control lastName"
                                       required="required"
                                       type="text"
                                       value="${student.lastName}" maxlength="30"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="group" class="col-lg-3 control-label">Study group</label>

                    <div class="col-lg-8">
                        <div class="row">
                            <div class="col-lg-4">
                                <select id="group">
                                    <option>${student.studyGroup}</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <br><br>

                <p class="text-warning">
                    <small>If you don't save, your changes will be lost.</small>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
                <a href="" class="btn btn-default saveBtn">Save</a>
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
                <%--<p class="name">Do you really want to remove</p>--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <%--<a href="" class="btn btn-danger saveBtn">Remove</a>--%>
                <a href="" class="btn btn-danger saveBtn">Remove</a>
            </div>
        </div>
    </div>
</div>
<!-- Modal Remove -->

<form:form commandName="student" cssClass="">
<!-- Modal Add -->
<div class="modal fade" id="modalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div> <!-- Modal Add -->
</form:form>
</body>
</html>
