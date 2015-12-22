<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nat
  Date: 26.11.2015
  Time: 0:02
  To change this template use File | Settings | File Templates.
--%>
<%--@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quizzes</title>
</head>
<body--%>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <td></td>
        </tr>
        </thead>
        <c:forEach var="element" items="${elements}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${loopCounter.count}"/></td>
                <td>
                        <%--<a href='<c:url value="quizInfo?id=${element.id}"/>'>${element.name}</a>--%>
                        <c:out value="${element.name}"/>
                </td>
                <td>
                    <form action="/doUpdate" method="post">
                        <label>
                            <input type="hidden" name="id" value="${element.id}">
                            <input type="submit" name="Edit" value="Edit">
                        </label>
                    </form>
                </td>
            </tr>
            <br>
            <form action="/doUpdate" method="post">
                <label>
                    <input type="hidden" name="id" value="${element.id}">
                    <input type="submit" name="Add" value="Add new">
                </label>
            </form>
        </c:forEach>
    </table>
</div>
<%--/body>
</html--%>
