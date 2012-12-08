<%-- 
    Document   : addquitz
    Created on : Oct 4, 2012, 8:38:44 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="h" uri="/WEB-INF/hrTags.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Quitz</title>
    </head>
    <body>
        <div style="width: 700px; margin: 0 auto;">
            <label>Login user: <c:out value="${sessionScope.UserName}" />
            </label><br /><a href='login?type=logout'>Logout</a>
            <br /><br />
            <c:set var="status" value="${requestScope.Status}" />
            <c:set var="allQuestions" value="${requestScope.AllQuestions}" />
            <form name="frmAddNew" action="addquitz" method="post">
                <div style="width: 70%; margin-left: 15%;">
                    <label style='color:red;'><c:out value="${requestScope.QsnNameError}" /></label><br /><br />
                    <c:set var="QtzName" value="${requestScope.QtzName}" />
                    <label style="width: 130px;">Quitz name: </label>&nbsp;
                    <c:choose>
                        <c:when test="${status == false}">
                            <input type="text" value="${QtzName}" name="txtQuitzName" size="90" />
                        </c:when>
                        <c:otherwise>
                            <m:printValue value="${QtzName}" />
                        </c:otherwise>
                    </c:choose>
                    <br /><br />
                    <c:set var="formName" value="frmAddNew" />
                    <m:addQuitzTag publish="${status}" allQuestions="${allQuestions}" formName="${formName}"  />
                </div>
                    <h:hrTag />
                <c:set var="qtzID" value="${requestScope.QID}" />
                <c:if test="${status == false}">
                    <a href="javascript:document.frmAddNew.submit();" style="float: right;">Add Question</a>
                    <c:if test="${! empty allQuestions}">
                        <a href="javascript:document.getElementById('status').value='true';document.frmAddNew.submit();" style="float: right; margin-right: 10px;">Make Publish</a>
                    </c:if>
                </c:if>
                <input type="hidden" value="" id="status" name="status" />
                <a href="./quitzs" style="float: left;">Back to quitz list</a>
                <input type="hidden" value="${requestScope.QID}" name="QID" />
            </form>
        </div>
    </body>
</html>
