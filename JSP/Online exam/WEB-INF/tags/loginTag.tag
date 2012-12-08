<%-- 
    Document   : loginTag
    Created on : Oct 14, 2012, 3:35:50 PM
    Author     : Methun-Rimu
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib prefix="h" uri="/WEB-INF/hrTags.tld" %>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="totalPublishQuitz"%>

<%-- any content can be specified here e.g.: --%>
<c:if test="${totalPublishQuitz gt 0}">
    <h:hrTag />
    <a style="float: left;" href="./availquitz">Take quitz</a>
</c:if>