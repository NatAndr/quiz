<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28.11.2015
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Show Error Page</title>
</head>
<body>
<h1>Opps...</h1>

<p>Sorry, an error occurred.</p>

<p>Here is the exception stack trace: </p>

<p>
    <% exception.printStackTrace(response.getWriter()); %>
</p>
</body>
</html>

