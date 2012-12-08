<%-- 
    Document   : quitzsTag
    Created on : Oct 3, 2012, 9:08:28 PM
    Author     : Administrator
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="allquitzs" type="java.util.List"%>
<%@attribute name="formName" %>

<%-- any content can be specified here e.g.: --%>
<c:if test="${ ! empty allquitzs}">
    <ol type="1">
        <c:forEach var="quitz" items="${allquitzs}">
            <li><a href="javascript:document.getElementById('QID').value=${quitz.id};document.${formName}.submit();">${quitz.quitzName}</a></li>
        </c:forEach>
    </ol>
    <input type="hidden" value="" id="QID" name="QID" />
    <input type="hidden" value="0" name="index" />
</c:if>