<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 05.12.2015
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<c:if test="${not empty loginMsg}">
    <div class="alert alert-${alertType}" role="alert">${loginMsg}</div>
</c:if>
