<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28.12.2015
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
  <thead>
  <tr>

  </tr>

  </thead>
  <tbody>
  <c:forEach items="${students}" var="student">
    <tr>
      <td>
        ${student.firstName}
      </td>
      <td>
          ${student.lastName}
      </td>
      <td>
          ${student.studyGroup}
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
