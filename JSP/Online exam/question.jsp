<%-- 
    Document   : question
    Created on : Oct 14, 2012, 7:24:48 AM
    Author     : Methun-Rimu
--%>

<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="/WEB-INF/hrTags.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Question</title>
    </head>
    <body>
        <div style="width: 700px; margin: 0 auto;">
            <br /><br />

            <c:set var="qsnName" value="${requestScope.QuestionName}" />
            <c:set var="type" value="${requestScope.QuestionType}" />
            <c:set var="QID" value="${requestScope.QID}" />
            <c:set var="index" value="${requestScope.index}" />
            <c:set var="CM" value="${requestScope.Mark}" />

            <br /><br />
            <form name="qsnForm" action="question" method="post">
                <div style="width: 70%; margin-left: 15%;">
                    <label style="width: 130px;">Question: </label>&nbsp;&nbsp;<label style="width: 130px;"><strong><m:printValue value="${qsnName}" /></strong></label>
                    <br /><br />
                    <label style="width: 130px;">Put Answer: </label>&nbsp;&nbsp;&nbsp;<label style='color:red;'><c:out value="${requestScope.AnsTypeError}" /></label><br />
                    <div style="margin-left: 50px;">
                        <c:choose>
                            <c:when test="${type == 1}">
                                <label for="YES"><input type="radio" name="rdoType" value="Yes" id="YES" />&nbsp;&nbsp;Yes</label><br />
                                <label for="NO"><input type="radio" name="rdoType" value="No" id="NO" />&nbsp;&nbsp;No</label>
                            </c:when>
                            <c:otherwise>
                                <input type="text" name="txtAnswer" value="" size="70" />
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <br /><br />
                </div>
                    <h:hrTag />
                <input type="hidden" value="${QID}" id="QID" name="QID" />
                <input type="hidden" value="${index}" name="index" />
                <input type="hidden" value="${CM}" name="CM" />
                <a href="javascript:document.qsnForm.submit();" style="float: right;">Next</a>
            </form>
        </div>
    </body>
</html>
