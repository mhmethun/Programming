<%-- 
    Document   : quitzs
    Created on : Sep 19, 2012, 7:47:34 PM
    Author     : Administrator
--%>

<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>My Quitzs</title>
</head>
<body>
    <div style="width: 700px; margin: 0 auto;">
        <label><strong>Login Success!!</strong></label><br />
        <label>Login user: <c:out value="${sessionScope.UserName}" /></label><br /><a href='login?type=logout'>Logout</a><br /><br />
        <br /><br />
        <c:set var="AllQuitzList" value="${requestScope.AllQuitzList}" />
        <c:set var="IsAdmin" value="${requestScope.AdminStatus}" />
        <m:quitzsTag name="frmQuitz" method="post" action="quitzs" allquitzs="${AllQuitzList}" adminStatus="${IsAdmin}" />
    </div>
</body>
</html>