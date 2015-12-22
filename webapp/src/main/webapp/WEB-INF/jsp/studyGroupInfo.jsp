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
    <%@include file="header.jsp" %>

    <script type="text/javascript">
        function addUser() {

            var name = $('#name').val();
//            var positionid = $("#positions option:selected").val();
//            var groupid = $("#groups option:selected").val();

            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/user/addajax.htm",
                data: "username=" + name+"&positionid="+positionid+"&groupid="+groupid,
                success: function(response){
                    $('#info').html(response);
                    $('#name').val('');

                },
                error: function(e){
                    alert('Error: ' + e);
                }
            });
        }
        function deleteUser(id){

            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/user/deleteajax.htm",
                data: "userid=" + id,
                success: function(response){
                    $('#info').html(response);

                },
                error: function(e){
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

        <p class="lead">${studyGroup.name}</p>
    </div>

<form action="${pageContext.request.contextPath}/studyGroupEdit" method="post">
    <label>
        <input type="hidden" name="id" value="${studyGroup.id}">
        <%--<input type="submit" name="Edit" value="Edit">--%>
        <!-- input type="submit" name="Add" value="Add" -->
            <button type="submit" class="btn btn-primary" name="Edit">Редактировать</button>&nbsp;&nbsp;
        <button type="submit" class="btn btn-primary" name="Delete">Удалить</button>
    </label>
</form>
    <input type="button" value="Add Users" onclick="addUser()">
    </div>
</body>
</html>