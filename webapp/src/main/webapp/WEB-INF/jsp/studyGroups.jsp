<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.11.2015
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%--@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>StudyGroups</title>
    <%@include file="header.jsp" %>
</head>
<body--%>
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
                    <a href='<c:url value="studyGroupInfo?id=${group.id}"/>'>${group.name}</a>
                </td>
                    <%--<td width="30">--%>
                    <%--<form action="/editGroup" method="post">--%>
                    <%--<label>--%>
                    <%--<input type="hidden" name="id" value="${group.id}">--%>
                    <%--<input type="submit" class="btn btn-primary launch-modal" name="Edit" value="Edit">--%>
                    <%--</label>--%>
                    <%--</form>--%>
                    <%--</td>--%>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/studyGroupAdd" method="post">
        <!--input type="submit" name="Add" value="Add"-->

        <button type="submit" class="btn btn-primary" name="Add">Добавить</button>
    </form>
</div>
<%--/body>
</html--%>
