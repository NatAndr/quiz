<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.12.2015
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <%@include file="header.jsp" %>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="alert alert-danger" role="alert" id="errorRegistration" style="display: none;"></div>
            <h2 class="form-signin-heading">Registration</h2>

            <div class="account-wall">
                <form class="form-signin">
                    <div class="form-group">
                        <label for="firstName" class="control-label">First name:</label>
                        <input type="text" id="firstName" class="form-control required" required
                               autofocus name="firstName">
                    </div>
                    <div class="form-group">
                        <label for="lastName" class="control-label">Last name:</label>
                        <input type="text" id="lastName" class="form-control required" required
                               name="lastName">
                    </div>
                    <div class="form-group">
                        <label for="login" class="control-label">Login:</label>
                        <input type="text" id="login" class="form-control required" required name="login">
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label">Password:</label>
                        <input type="password" id="password" class="form-control required" required
                               name="password">
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword" class="control-label">Confirm password:</label>
                        <span id="confirmPasswordError" style="color: red"></span>
                        <input type="password" id="confirmPassword" class="form-control required"
                               required name="confirmPassword">
                    </div>
                    <div class="form-group">
                        <label for="studyGroupId" class="control-label">Study group:</label>
                        <select name="studyGroupId" id="studyGroupId" class="form-control options required">
                            <option value="" disabled selected>Select study group</option>
                            <c:forEach var="studyGroup" items="${studyGroupList}">
                                <option value="${studyGroup.id}">${studyGroup.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="btn-group btn-group-justified" role="group">
                        <div class="btn-group" role="group">
                            <input id="register" class="btn btn-lg btn-primary" type="button" value="Register"
                                   disabled/>
                        </div>
                        <div class="btn-group" role="group">
                            <input id="cancel" class="btn btn-lg btn-default" type="button" value="Cancel"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var doRegister = '<c:url value="/doRegister"/>';
    var loginUrl = '<c:url value="/login"/>';
</script>
<script src="<c:url value="../../resources/js/quiz/registration.js" />"></script>
</body>
</html>
