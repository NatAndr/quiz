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
        <c:forEach var="studyGroup" items="${studyGroups}">
            <tr>
                <td>
                    <a href='<c:url value="studyGroupInfo?id=${studyGroup.id}"/>'>${studyGroup.groupName}</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/studyGroupEdit" method="post">
        <label>
            ${studyGroup.groupName}
            <br/><br/>
            <input type="hidden" name="id" value="${studyGroup.id}">
            <!--input type="submit" name="Add" value="Add"-->
                <input class="btn btn-default btn-sm" type="submit" name="Add" value="Add">
        </label>
    </form>
</div>
<%--/body>
</html--%>
