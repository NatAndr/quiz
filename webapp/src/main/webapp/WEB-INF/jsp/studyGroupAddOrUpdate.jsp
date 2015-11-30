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
</head>
<body>
<form action="${pageContext.request.contextPath}/studyGroupAddOrUpdate" method="post">
    <c:if test="${studyGroup!=null}">
        Edit group:
        <br>
        <input type="hidden" name="id" value="${studyGroup.id}"/>
        <input type="text" name="name" value="${studyGroup.groupName}"/>
    </c:if>
    <c:if test="${studyGroup==null}">
        Add new group:
        <br>
        <input type="text" name="name" maxlength="100" size="60" />
    </c:if>
    <input type="submit" name="Save" value="Save"/>
</form>

</body>
</html>
