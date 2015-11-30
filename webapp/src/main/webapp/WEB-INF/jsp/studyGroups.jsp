<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.11.2015
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>StudyGroups</title>
</head>
<body>
<div>
  <table>
      <c:forEach var="studyGroup" items="${studyGroups}">
        <tr>
          <td>
              <a href='<c:url value="studyGroupInfo?id=${studyGroup.id}"/>'>${studyGroup.groupName}</a>
          </td>
        </tr>
      </c:forEach>
  </table>
</div>
</body>
</html>
