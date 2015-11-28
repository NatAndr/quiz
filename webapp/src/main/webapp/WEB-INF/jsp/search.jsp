<%--
  Created by IntelliJ IDEA.
  User: Nat
  Date: 25.11.2015
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quizes search</title>
</head>
<body>
  <div align="left">
    <form action="${pageContext.request.contextPath}/quizesSearch" method="get">
      <h2>Quizes search:</h2>
      <label>
        <input type="text" name="searchParams" maxlength="40" size="30">
        <input type="submit" value="search">
      </label>
    </form>
    <p>
      <%@include file="quezes.jsp"%>
    </p>
  </div>

</body>
</html>
