<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="/WEB-INF/hrTags.tld" %>
<!DOCTYPE html>
<html>
<head>
<title>Available Quizs</title>
</head>
<body>
    <div style="width: 700px; margin: 0 auto;">
        <br /><br />
        <c:set var="AllQuitzList" value="${requestScope.AllQuitzList}" />
        <form name="frmAvailQuitz" method="post" action="availquitz">
            <m:availQuitzTag allquitzs="${AllQuitzList}" formName="frmAvailQuitz" />
        </form>
            <h:hrTag />
        <a href="./login" style="float: left;">Back</a>
    </div>
</body>
</html>