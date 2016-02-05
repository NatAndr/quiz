<%--
  Created by IntelliJ IDEA.
  User: Nat
  Date: 26.11.2015
  Time: 0:02
  To change this template use File | Settings | File Templates.
--%>
<div class="container">

    <table class="table table-striped">
        <c:if test="${foundQuiz!=null}">
            <thead>
            <tr>
                <td>Quiz</td>
            </tr>
            </thead>
            <c:forEach var="quiz" items="${foundQuiz}">
                <tr>
                    <td>
                        <a href='<c:url value="/quizInfo?id=${quiz.id}"/>'>${quiz.name}</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
