<%-- 
    Document   : loginTag
    Created on : Oct 4, 2012, 4:29:53 PM
    Author     : Administrator
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="value" required="true"%>

<%-- any content can be specified here e.g.: --%>
<c:choose>
    <c:when test="${value == null}" >
        <jsp:doBody />
    </c:when>
    <c:otherwise>
        ${value}
    </c:otherwise>
</c:choose>