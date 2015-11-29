<%--
  Created by IntelliJ IDEA.
  User: Nat
  Date: 26.11.2015
  Time: 0:02
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quizes</title>
</head>
<body>
<div>
    <table>
        <%--@elvariable id="foundQuiz" type="java.util.List"--%>
        <c:if test="${foundQuiz!=null}">
            <c:forEach var="quiz" items="${foundQuiz}">
                <tr>
                    <td>
                            ${quiz.quizName}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

</div>

</body>
</html>
