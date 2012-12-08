<%-- 
    Document   : login
    Created on : Sep 19, 2012, 6:48:31 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <div style="width: 700px; margin: 0 auto;">
        <table><tr><td><strong>Student Name</strong>: Roman Kabir</td></tr>
            <tr><td><strong>Student ID</strong>: 12345</td></tr>
            <tr><td><strong>Charles Darwin university student</strong></td></tr>
        </table><br /><br />
        <label style='color:red;'>
            <c:out value="${requestScope.Error}" />
        </label>
        <br />
        <form method='post' action='login'>
            <label>Enter your user name to access your quitzs: </label>&nbsp;&nbsp;<input type='text' value='' name='txtUserName' />&nbsp;&nbsp;<input type='submit' value='Submit' name='btnUserName' />
            <br /><br />
            <c:set var="TotalPublishQuitz" value="${requestScope.TotalPublishQuitz}" />
            <m:loginTag totalPublishQuitz="${TotalPublishQuitz}" />
        </form>
        </div>
    </body>
</html>