<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.12.2015
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login</title>
  <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<br>
<div class="container-fluid">
  <div class="panel panel-success">
    <div class="panel-heading" align="center">
      <h4><b><font color="black" style="font-family: fantasy;">My First Login Demo</font> </b></h4>
    </div>
    <div class="panel-body"align="center">

      <div class="container " style="margin-top: 10%; margin-bottom: 10%;">

        <div class="panel panel-success" style="max-width: 35%;" align="left">

          <div class="panel-heading form-group">
            <b><font color="white">
              Login Form</font> </b>
          </div>

          <div class="panel-body" >

            <form action="LoginServlet" method="post" >
              <div class="form-group">
                <label for="exampleInputEmail1">User Name</label> <input
                      type="text" class="form-control" name="txtUserName" id="txtUserName"
                      placeholder="Enter User Name" required="required">

              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">Password</label> <input
                      type="password" class="form-control" name="txtPass" id="txtPass"
                      placeholder="Password" required="required">
              </div>
              <button type="submit" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block" ><b>Login</b></button>

            </form>

          </div>
        </div>

      </div>

    </div>
    <div class="panel-footer" align="center"><font style="color: #111">Copyright @2015  <a href="http://mysite.com/">mysite.com</a>, All Rights Reserved. </font></div>
  </div>
</div>

</body>
</html>
