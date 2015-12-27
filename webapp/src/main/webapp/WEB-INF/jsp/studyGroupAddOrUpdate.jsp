<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.11.2015
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Study group</title>
    <%@include file="header.jsp" %>
</head>
<body>
<div class="container">
    <div class="row col-lg-offset-1">
        <form action="${pageContext.request.contextPath}/studyGroupAddOrUpdate" method="post">
            <%--<c:if test="${studyGroup!=null}">--%>
            <div class="page-header">
                <p class="lead">Edit group:</p>
            </div>
            <c:if test="${studyGroup!=null}">
                <input type="hidden" name="id" value="${studyGroup.id}"/>
            </c:if>
            <div class="form-group">
                <label for="name" class="col-lg-3 control-label">Group name<sup>*</sup></label>
                <div class="col-lg-8">
                    <div class="row">
                        <div class="col-lg-4">
                            <input id="name" name="name" class="form-control" required="required" type="text"
                                   value="${studyGroup.name}" maxlength="30"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-lg-9">
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
