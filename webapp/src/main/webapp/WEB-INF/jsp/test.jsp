<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id='newAnswerDialog'>
  <form id='newAnswerForm' name='newAnswerForm' action='/admin/survey/answer/new' onsubmit='return false;' method='post'>
    <label for='severity'><spring:message code='input.answer.severity' text='Severity' /></label>
    <select id='answerSeverity' name='answerSeverity'>
      <option value='MINIMAL'>MINIMAL</option>
      <option value='MINOR'>MINOR</option>
      <option value='MODERATE'>MODERATE</option>
      <option value='SEVERE'>SEVERE</option>
      <option value='URGENT'>URGENT</option>
    </select><br/>
    <label for='answerText'><spring:message code='input.answer.text' text='Answer Text' /></label>
    <input type='text' id='answerText' name='answerText'/><br/>
    <label for='requiresReason'><spring:message code='input.answer.requiresReason' text='Requires Reason?' /></label>
    <input type='checkbox' id='requiresReason' name='requiresReason' onclick='toggleReasonControls(this)'/><br/>
    <label for='answerReasons'><spring:message code='input.question.reasons' text='Reasons' /></label>
    <select class='newReason' id='answerReasons' name='answerReasons' multiple='multiple'>
      <c:forEach items='${reasons}' var='reason'>
        <option value='${reason.key}'>${reason.text}</option>
      </c:forEach>
    </select><br/>
    <input type='button' onclick='createNewAnswer()' value='<spring:message code='submit' />'/><input type='button' onclick='cancelNewAnswer()' value='<spring:message code='cancel' />' />
  </form>
</div>