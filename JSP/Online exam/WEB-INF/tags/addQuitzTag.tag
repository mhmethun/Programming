<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="publish" required="true"%>
<%@attribute name="allQuestions" type="java.util.List" required="true"%>
<%@attribute name="formName" required="true"%>

<%-- any content can be specified here e.g.: --%>
<c:if test="${ ! empty allQuestions}">
    <table summary="quitz table" border="0" cellspacing="10">
        
        <c:choose>
            <c:when test="${publish == true}">
                <tr><td><strong>Questions</strong></td><td><strong>Avg. Score</strong></td></tr>
            </c:when>
            <c:otherwise>
                <tr><td><strong>Questions</strong></td><td>&nbsp;</td><td>&nbsp;</td></tr>
            </c:otherwise>
        </c:choose>
        
        <c:forEach var="question" items="${allQuestions}">
            <tr>
                <td>${question.qsnName}</td>
                <c:choose>
                    <c:when test="${publish == true}">
                        <td>${question.avgScore}</td>
                    </c:when>
                    <c:otherwise>
                        <td><input type="button" onclick="document.getElementById('update').value=${question.qsnId};document.${formName}.submit();" value="Update" /></td>
                        <td><input type="button" onclick="document.getElementById('delete').value=${question.qsnId};document.${formName}.submit();" value="Delete" /></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
    <input type="hidden" value="" id="update" name="update" />
    <input type="hidden" value="" id="delete" name="delete" />
</c:if>