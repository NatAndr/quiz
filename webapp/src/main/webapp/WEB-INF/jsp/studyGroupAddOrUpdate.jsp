<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.11.2015
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Study group</title>
    <link href="<c:url value="/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>
<div class="container">

<form action="${pageContext.request.contextPath}/studyGroupAddOrUpdate" method="post">
    <c:if test="${studyGroup!=null}">
        <div class="page-header">
            <p class="lead">Edit group:</p>
        </div>
        <input type="hidden" name="id" value="${studyGroup.id}"/>
        <input class="form-control" type="text" name="name" value="${studyGroup.groupName}"/>
    </c:if>
    <c:if test="${studyGroup==null}">
        <div class="page-header">
            <p class="lead">Add new group:</p>
        </div>
        <input class="form-control" placeholder="Enter group name" type="text" name="name" maxlength="100" size="60" />
    </c:if>
    <input class="btn btn-default btn-sm" type="submit" name="Save" value="Save"/>
</form>
</div>
</body>
</html>
