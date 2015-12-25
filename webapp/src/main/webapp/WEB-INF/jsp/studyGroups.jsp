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
        <c:forEach var="group" items="${studyGroups}">
            <tr>
                <td width="30">
                        <a href='<c:url value="/studyGroupInfo?id=${group.id}"/>'>${group.name}</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/studyGroupAdd" method="post">
        <button type="submit" class="btn btn-primary" name="Add">Add new</button>
    </form>
</div>
</body>
</html>
