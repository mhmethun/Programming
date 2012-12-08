<%-- 
    Document   : resultTag
    Created on : Oct 14, 2012, 9:37:18 AM
    Author     : Methun-Rimu
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="resultList" type="java.util.List" required="true"%>

<%-- any content can be specified here e.g.: --%>
<c:if test="${ ! empty resultList}">
    <table summary="result table" border="0" cellspacing="10">
       <tr><td><strong>Question</strong></td><td><strong>Correct Ans</strong></td><td><strong>User Ans</strong></td><td><strong>Comment</strong></td></tr>
           
        <c:forEach var="result" items="${resultList}">
            <tr>
                <td>${result.question}</td>
                <td>${result.correctAns}</td>
                <td>${result.answer}</td>
                <td>
                    <c:choose>
                        <c:when test="${result.score gt 0}">
                            <m:printValue value="<i style='color:#000;'>Correct Answer</i>" />
                        </c:when>
                        <c:otherwise>
                            <m:printValue value="<i style='color:#ff0000;'>Wrong Answer</i>" />
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>