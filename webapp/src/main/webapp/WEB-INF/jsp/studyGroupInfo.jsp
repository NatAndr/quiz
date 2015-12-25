<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.11.2015
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Study group</title>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE html>
    <%@include file="header.jsp" %>
    <script type="text/javascript">
        function deleteGroup(id) {
            $.ajax({
                type: "POST",
                url: '<c:url value="/studyGroupDelete" />',
                data: "id=" + id,
                success: function (response) {
                    $('#result').html(response);
                },
                error: function (e) {
                    alert('Error: ' + e);
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Study group</h1>
        <p class="lead" id="groupName">${studyGroup.name}</p>
    </div>
    <form action="${pageContext.request.contextPath}/studyGroupEdit" method="post">
        <label>
            <input type="hidden" name="id" value="${studyGroup.id}">
            <button type="submit" class="btn btn-primary" name="Edit">Edit</button>
        </label>
    </form>
    <button type="submit" class="btn btn-primary" name="Delete" onclick="deleteGroup(${studyGroup.id});">Delete</button>

    <br>
    <div id="result" style="color: green;"></div>
    <br>
    <div>
        <a href='<c:url value="/admin"/>'>Back to admin panel</a>
    </div>
</div>
</body>
</html>