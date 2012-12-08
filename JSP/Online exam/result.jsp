<%-- 
    Document   : result
    Created on : Oct 14, 2012, 9:03:42 AM
    Author     : Methun-Rimu
--%>

<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="/WEB-INF/hrTags.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Result</title>
    </head>
    <body>
        <div style="width: 700px; margin: 0 auto;">
            <br /><br />

            <c:set var="resultList" value="${requestScope.ResultList}" />
            <c:set var="precCorrect" value="${requestScope.PercentCorrect}" />

            <br /><br />
            <form name="qsnForm" action="question" method="post">
                <div style="width: 70%; margin-left: 15%;">
                    <label style="width: 130px;">Question: </label>&nbsp;&nbsp;<label style="width: 130px;"><strong><m:printValue value="${qsnName}" /></strong></label>
                    <br /><br />
                    <m:resultTag resultList="${resultList}" />
                    <br /><br />
                    <label style="width: 100%; text-align: right; font-weight: bold;">Percent correct:&nbsp;&nbsp;<m:printValue value="${precCorrect}" />&#37;</label>
                </div>
                <br />
                <h:hrTag />
                <a href="./availquitz" style="float: Left;">Take another quitz</a>
            </form>
        </div>
    </body>
</html>
