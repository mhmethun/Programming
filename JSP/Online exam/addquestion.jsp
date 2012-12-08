<%-- 
    Document   : addquestion
    Created on : Oct 9, 2012, 12:21:38 AM
    Author     : Methun-Rimu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="/WEB-INF/hrTags.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Question</title>
    </head>
    <body>
        <div style="width: 700px; margin: 0 auto;">
            <label>Login user: <c:out value="${sessionScope.UserName}" /></label><br />
            <a href='login?type=logout'>Logout</a>
            <br /><br />

            <c:set var="qsnName" value="${requestScope.QsnName}" />
            <c:set var="corrAns" value="${requestScope.CorrectAns}" />
            <c:set var="type" value="${requestScope.AnsType}" />
            <c:set var="QtzID" value="${requestScope.QuitzID}" />
            <c:set var="QsnID" value="${requestScope.QuestionID}" />

            <br /><br />
            <form name="qsnForm" action="addquestion" method="post">
                <div style="width: 70%; margin-left: 15%;">
                    <label style="width: 130px;">Question: </label>&nbsp;<input type="text" name="txtQuestion" value="${qsnName}" size="70" />&nbsp;&nbsp;&nbsp;<label style='color:red;'><c:out value="${requestScope.QsnNameError}" /></label>
                    <br /><br />
                    <label style="width: 130px;">Correct Answer: </label>&nbsp;<input type="text" name="txtCorrectAns" value="${corrAns}" size="70" />&nbsp;&nbsp;&nbsp;<label style='color:red;'><c:out value="${requestScope.CorrectAnsError}" /></label>
                    <br /><br />
                    <label style="width: 130px;">Type: </label>&nbsp;&nbsp;&nbsp;<label style='color:red;'><c:out value="${requestScope.AnsTypeError}" /></label><br />
                    <div style="margin-left: 50px;">
                        <label for="YESNO">
                            <c:choose>
                                <c:when test="${type == 1}">
                                    <input type="radio" name="rdoType" value="YES/NO" id="YESNO" checked="checked" />&nbsp;&nbsp;Yes/No
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="rdoType" value="YES/NO" id="YESNO" />&nbsp;&nbsp;Yes/No
                                </c:otherwise>
                            </c:choose>
                        </label>
                        <br />
                        <label for="STR">
                            <c:choose>
                                <c:when test="${type == 2}">
                                    <input type="radio" name="rdoType" value="S" id="STR" checked="checked" />&nbsp;&nbsp;String
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="rdoType" value="S" id="STR" />&nbsp;&nbsp;String
                                </c:otherwise>
                            </c:choose>
                        </label>
                        <br />
                        <label for="DBL">
                            <c:choose>
                                <c:when test="${type == 3}">
                                    <input type="radio" name="rdoType" value="D" id="DBL" checked="checked" />&nbsp;&nbsp;Double
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="rdoType" value="D" id="DBL" />&nbsp;&nbsp;Double
                                </c:otherwise>
                            </c:choose>
                        </label>
                    </div>
                    <br /><br />
                </div>
                <h:hrTag />
                <input type="hidden" value="${QtzID}" name="QuitzID" />
                <input type="hidden" value="${QsnID}" name="QsnID" />
                <c:choose>
                    <c:when test="${QsnID gt 0}">
                        <a href="javascript:document.qsnForm.submit();" style="float: right;">Update question</a>
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:document.qsnForm.submit();" style="float: right;">Add question</a>
                    </c:otherwise>
                </c:choose>
                <a href="./addquitz?QID=${QtzID}" style="float: left;">Back</a>
            </form>
        </div>
    </body>
</html>
