<%-- 
    Document   : helppage
    Created on : Oct 14, 2012, 5:38:41 AM
    Author     : Methun-Rimu
--%>

<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="/WEB-INF/hrTags.tld" %>
<!DOCTYPE html>
<html>
<head>
<title>Help page</title>
</head>
<body>
    <div style="width: 700px; margin: 0 auto;">
        <label>Login user: <c:out value="${sessionScope.UserName}" /></label><br /><a href='login?type=logout'>Logout</a><br /><br />
        <br /><br />
        <br /><br />
        <strong><m:printValue value="${requestScope.ExceptionMsg}" /></strong>
        <h:hrTag />
        <a style="float: right;" href="javascript:window.history.go(-1);">Continue</a>
    </div>
</body>
</html>