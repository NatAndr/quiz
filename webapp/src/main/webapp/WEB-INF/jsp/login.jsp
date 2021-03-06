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
            <p>
                <%@include file="loginMsg.jsp" %>
            </p>

            <h2 class="form-signin-heading">Please sign in</h2>

            <div class="account-wall">
                <form class="form-signin" action="${pageContext.request.contextPath}/loginCheck" method="post">
                    <input type="text" id="inputLogin" class="form-control" placeholder="Login" required autofocus
                           name="login">
                    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required
                           name="password">

                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="remember-me" name="rememberMe"> Remember me
                        </label>
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                    <br>
                    <a href='<c:url value="/registration"/>'>Registration</a>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
