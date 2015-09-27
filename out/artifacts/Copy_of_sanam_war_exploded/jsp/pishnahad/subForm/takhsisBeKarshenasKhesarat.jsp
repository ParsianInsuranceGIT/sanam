<%@ page import="com.bitarts.parsian.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<form></form>--%>
<form id="takhsiskarshenasformfordarkhast" name="form4takhsiskarshenas" action="/assignKarshenasKhesarat" method="post">
    <input type="hidden" id="takhsiskharshenastransidfordakhast" name="transitionId" value="603"/>
    <input type="hidden" name="logmessage" id="takhsiskharshenastransidmsg" value="test"/>
    <input type="hidden" name="pishnehad.id" value="<c:out value='${pishnehadRun.id}'/>"/>
    <input type="hidden" name="darkhastBazkharid.id" value="<c:out value='${darkhastBazkharid.id}'/>"/>

    <select name="karshenasId" id="whichkarshenas">
        <c:if test="${karshenasKhesaratha!=null}">
            <c:forEach var="karshenas" items="${karshenasKhesaratha}" varStatus="i">
            <option value='<c:out value="${karshenas.id}"/>'><c:out value="${karshenas.firstName}"/> &nbsp;&nbsp;<c:out value="${karshenas.lastName}"/></option>
            </c:forEach>
        </c:if>
    </select>
    <input type="submit"  value="تایید"/>
</form>