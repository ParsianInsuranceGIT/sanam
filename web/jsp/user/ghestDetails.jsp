<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%--
  Created by IntelliJ IDEA.
  User: a-khezri
  Date: 12/16/13
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="../../css/styles.css"/>
<script type="text/javascript" src="/js/common.js"></script>
<table class="jtable">
    <tr>
        <th>مبلغ سند به ریال</th>
        <th>مبلغ پرداختی به ریال</th>
        <th>تاریخ پرداخت</th>
        <%--<th>شماره چک/فیش بانکی/الحاقیه برگشتی</th>--%>
        <th>نوع اعتبار</th>
        <th>تاریخ صدور سند</th>
        <th>شماره اعتبار</th>
        <th>شماره سند</th>
    </tr>
    <c:if test="${khateSanadList==null || fn:length(khateSanadList)==0}">
        <tr><td colspan="7"><p>رکوردی پیدا نشد</p></td></tr>
    </c:if>
    <s:iterator value="%{khateSanadList}" id="row" status="stt_gst">
        <tr>
            <td><s:property value="#row.getAmount()"/></td>
            <td><s:property value="#row.getEtebarCredebit().getAmount()"/></td>
            <td><s:property value="#row.getEtebarCredebit().getDateFish()"/></td>
            <%--<td></td>--%>
            <td><s:property value="#row.getEtebarCredebit().getCredebitTypeFarsi()"/></td>
            <td><s:property value="#row.getSanad().getCreatedDate()"/></td>
            <td><s:property value="#row.getEtebarCredebit().getShenaseCredebit()"/></td>
            <td><s:property value="#row.getSanad().getShomare()"/></td>
        </tr>
    </s:iterator>
</table>