<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.12.2015
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
                <%@include file="loginError.jsp" %>
            </p>
            <h2 class="form-signin-heading">Please sign in</h2>

            <div class="account-wall">
                <form class="form-signin" action="${pageContext.request.contextPath}/loginCheck" method="post">
                    <label for="inputEmail" class="sr-only">Email address</label>
                    <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required
                           autofocus
                           name="email">
                    <label for="inputPassword" class="sr-only">Password</label>
                    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required
                           name="password">

                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="remember-me" name="rememberMe"> Remember me
                        </label>
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%--script src="<c:url value = "/bootstrap/js/jquery-1.11.3.min.js"/>"></script>
<script src="<c:url value = "/bootstrap/js/bootstrap.min.js"/>"></script--%>
<%--script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script--%>
</body>
</html>
