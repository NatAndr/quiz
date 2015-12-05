<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.12.2015
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link href="<c:url value="/bootstrap/css/login.css"/>" rel="stylesheet">
</head>
<body>
<div class="container" align="center">
    <div class="card card-container">
        <!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
        <!--img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" /-->
        <p id="profile-name" class="profile-name-card"></p>

        <form class="form-signin" action="${pageContext.request.contextPath}/loginCheck" method="post">
            <span id="reauth-email" class="reauth-email"></span>
            <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus
                   name="email">
            <br><br>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required
                   name="password">
            <br>
            <div id="remember" class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <br>
            <div>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
            </div>
        </form>
        <!-- /form -->
        <!--a href="#" class="forgot-password">Forgot the password?</a-->
    </div>
    <!-- /card-container -->
</div>
<!-- /container -->

<script src="<c:url value = "/bootstrap/js/jquery-1.11.3.min.js"/>"></script>
<script src="<c:url value = "/bootstrap/js/bootstrap.min.js"/>"></script>
</body>
</html>
