<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 03.12.2015
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<c:if test="${empty userName}">
    <p class="text-right"><a href='<c:url value="/login"/>'>
        <span class="glyphicon glyphicon-log-in"></span> Login</a>
    </p>
</c:if>
<c:if test="${not empty userName}">
    <p class="text-right">
        <span class="glyphicon glyphicon-user"></span> ${userName}&nbsp;&nbsp;&nbsp;
        <a href='<c:url value="/logout"/>'>
            <span class="glyphicon glyphicon-log-out"></span> Logout
        </a>
    </p>
</c:if>
