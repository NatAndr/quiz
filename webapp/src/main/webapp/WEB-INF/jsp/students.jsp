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
</head>
<body>
<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <td></td>
        </tr>
        </thead>
        <c:forEach var="student" items="${students}">
            <tr>
                <td width="30">
                    <a href='<c:url value="/studentInfo?id=${student.id}"/>'>${student.firstName} ${student.lastName}</a>
                </td>
                <td width="30">${student.studyGroup}</td>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/studentAdd" method="post">
        <button type="submit" class="btn btn-primary" name="Add">Add new</button>
    </form>
</div>
</body>
</html>
