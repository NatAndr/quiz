<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.11.2015
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Study group</title>
</head>
<body>
<h2>Study group</h2>

<form action="${pageContext.request.contextPath}/studyGroupEdit" method="post">
    <label>
        ${studyGroup.groupName}
        <br/><br/>
        <input type="hidden" name="id" value="${studyGroup.id}">
        <input type="submit" name="Edit" value="Edit">
        <input type="submit" name="Add" value="Add">
    </label>
</form>
</body>
</html>
