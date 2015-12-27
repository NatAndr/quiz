<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25.12.2015
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form:form commandName="student" modelAttribute="student" method="post" action="/editStudent">
    <form:label path="firstName">Name</form:label>
    <form:input path="student.firstName" />
    <form:label path="lastName">Last name</form:label>
    <form:input path="student.lastName" />
    <%--<form:select path="groups">--%>
        <%--<form:option value="NONE" label="--- Select ---" />--%>
        <%--<form:options items="name" />--%>
    <%--</form:select>--%>
    <input type="submit" value="Save" />
</form:form>

</body>
</html>
