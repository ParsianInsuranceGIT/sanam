<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.bitarts.parsian.model.User" %>
<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="expandableTitleBar" id="poosheshasli">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        جدول معرفی نامه های پزشکی
    </p>
</div>
<table class="inputList jtable" border="0" cellspacing="5" cellpadding="1">
    <tr>
        <th width="9%">شماره</th>
        <th>تاريخ ايجاد</th>
        <th>نام كلينيك</th>
        <th>وضعيت</th>
        <th>پرینت</th>
        <th>ابطال معرفيسسسس نامه</th>
    </tr>
    <c:if test="${darkhastTaghirat.moarefinameList == null || fn:length(darkhastTaghirat.moarefinameList)==0}">
        <tr>
            <td id="nll" colspan="5">تا کنون معرفی نامه ای برای این درخواست ثبت نشده است.</td>
        </tr>
    </c:if>
    <c:if test="${darkhastTaghirat.moarefinameList != null && fn:length(darkhastTaghirat.moarefinameList)>0}">
        <c:forEach var="row" items="${darkhastTaghirat.moarefinameList}" varStatus="i">
            <tr>
                <td>${i.index + 1}</td>
                <td>${row.tarikhSodur}</td>
                <td>${row.clinic.clinicName}</td>
                <td>${row.vaziat}</td>
                <td>
                    <a onclick="window.open('/print_darkhasteMoayenatePezeshki?pishnehadReport.pishnehad.id=${pishnehad.id}&pishnehadReport.moarefiname.id=${row.id}');"
                       href="javascript:void(0);">پرینت درخواست معاینات پزشکی</a>

                </td>
                <td><a href="javascript:void(0);">ابطال</a></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
