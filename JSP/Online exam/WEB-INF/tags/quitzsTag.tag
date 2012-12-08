<%-- 
    Document   : quitzsTag
    Created on : Oct 3, 2012, 9:08:28 PM
    Author     : Administrator
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="h" uri="/WEB-INF/hrTags.tld" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="allquitzs" type="java.util.List"%>
<%@attribute name="name"%>
<%@attribute name="method"%>
<%@attribute name="action"%>
<%@attribute name="adminStatus"%>

<%-- any content can be specified here e.g.: --%>
<form name="${name}" method="${method}" action="${action}">
    <div style="width: 70%; margin-left: 15%;">
        <c:choose>
            <c:when test="${ ! empty allquitzs}">
        <table summary="quitz table" border="0" cellspacing="10">
            <tr>
                <c:if test="${adminStatus == true}" >
                    <td><strong>User</strong></td>
                </c:if>
                <td><strong>Quitz name</strong></td>
                <td><strong>Total taken</strong></td>
                <td><strong>Avg score</strong></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <c:forEach var="quitz" items="${allquitzs}">
                <tr>
                    <c:if test="${adminStatus == true}" >
                    <td>${quitz.userName}</td>
                    </c:if>
                    <td>${quitz.quitzName}</td>
                    <td>${quitz.timesTaken}</td>
                    <td>${quitz.avgScore}</td>
                    <c:choose>
                        <c:when test="${quitz.published == true}">
                            <td><input type="button" onclick="document.getElementById('update').value=${quitz.id};document.${name}.submit();" value="View" /></td>
                            <td>&nbsp;</td>
                        </c:when>
                        <c:otherwise>
                            <td><input type="button" onclick="document.getElementById('update').value=${quitz.id};document.${name}.submit();" value="Update" /></td>
                            <td><input type="button" onclick="document.getElementById('delete').value=${quitz.id};document.${name}.submit();" value="Delete" /></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
            </c:when>
            <c:otherwise>
                <m:printValue value="Currently you have not any quitz configured.<br /> Please add quitz by clicking on add quitz link at below right." />
            </c:otherwise>
    </c:choose>
    <input type="hidden" value="" id="update" name="update" />
    <input type="hidden" value="" id="delete" name="delete" />
    <input type="hidden" value="" id="view" name="view" />
    <br /><br />
    </div>
</form>
<h:hrTag />
<a style="float: right;" href="./addquitz">Add quitz</a>