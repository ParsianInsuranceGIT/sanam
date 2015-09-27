<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="../../css/styles.css"/>
<script type="text/javascript" src="/js/common.js"></script>
<table class="jtable" style="margin-left: 5%;margin-right: 5%;">
    <tr>
        <th style="white-space: nowrap;">نوع عملیات</th>
        <th style="white-space: nowrap;">نوع دفترچه</th>
        <th style="white-space: nowrap;">کاربر</th>
        <th style="white-space: nowrap;">ساعت چاپ/مشاهده</th>
        <th style="white-space: nowrap;">تاریخ چاپ/مشاهده</th>
        <th style="white-space: nowrap;">ردیف</th>
    </tr>
    <c:if test="${logPrintList==null || fn:length(logPrintList)==0}">
        <tr>
            <td colspan="7"><p>رکوردی پیدا نشد</p></td>
        </tr>
    </c:if>
    <%int i=0;%>
    <s:iterator value="%{logPrintList}" id="row" status="dftrche">
        <tr>
            <td><s:property value="#row.getGenreFa()"/></td>
            <td><s:property value="#row.getTypeFarsi()"/></td>
            <td><s:property value="#row.getUser().getFullName()"/></td>
            <td><s:property value="#row.getPrintTime()"/></td>
            <td><s:property value="#row.getPrintDate()"/></td>
            <td><%=++i%></td>
        </tr>
    </s:iterator>
</table>