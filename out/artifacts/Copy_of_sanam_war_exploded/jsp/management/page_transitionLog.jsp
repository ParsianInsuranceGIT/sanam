<%@ page import="com.bitarts.common.util.DateUtil" %>

<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 4/20/11
  Time: 5:27 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="../taglibs.jsp"%>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://parsian.bitarts.com/functions" prefix="bitarts_function" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <script type="text/javascript">
        $(function() {

            $(".help").tipsy({gravity:'s'});
            $(".help").addClass("ui-icon ui-icon-help");

        });
    </script>
    <c:if test="${pishnehad != null}">
        <title>تاریخچه پیشنهاد</title>
    </c:if>
    <c:if test="${darkhastBazkharid != null}">
        <title>تاریخچه درخواست بازخرید</title>
    </c:if>
</head>
<body>
<div class="expandableTitleBar">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        <c:if test="${pishnehad != null}">
            تاریخچه پیشنهاد
        </c:if>
        <c:if test="${darkhastBazkharid != null}">
            تاریخچه درخواست بازخرید
        </c:if>
    </p>
    کد رهگیری پیشنهاد:
    <b><c:out value="${pishnehad.radif}"/></b><br/><br/>
    <div class="content" dir="rtl" style="text-align:center;">
        <table align="center" width="80%" class="jtable resultDets" cellpadding="0" cellspacing="0" style="margin:0 auto;">
            <thead>
            <tr>
                <th style="padding:5" class="ui-state-default">&nbsp;</th>
                <th style="padding:5" class="ui-state-default">تاریخ</th>
                <th style="padding:5" class="ui-state-default">اختلاف زمان</th>
                <th style="padding:5" class="ui-state-default">ساعت</th>
                <th style="padding:5" class="ui-state-default">کاربر</th>
                <th style="padding:5" class="ui-state-default">از حالت</th>
                <th style="padding:5" class="ui-state-default">به حالت</th>
                <th style="padding:5" class="ui-state-default">انجام عمل</th>
                <th style="padding:5" class="ui-state-default">پیغام</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${transitionLogs}" varStatus="i">
                <tr>
                    <td style="padding:5px 5px" class="ui-widget-content">${i.index+1}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.date}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${i.index != 0 ?bitarts_function:getTimeDifferenceByStringDescription(row.date, transitionLogs[i.index-1].date):'-'}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.time}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.user.firstName}&nbsp;${row.user.lastName}</td>
                    <c:if test="${row.transition.stateBegin==null}">
                        <td style="padding:5px 5px" class="ui-widget-content">-</td>
                    </c:if>
                    <c:if test="${row.transition.stateBegin!=null}">
                        <td style="padding:5px 5px" class="ui-widget-content">${row.transition.stateBegin.stateName}</td>
                    </c:if>
                    <c:if test="${row.transition.stateEnd==null}">
                        <td style="padding:5px 5px" class="ui-widget-content">پیشنهاد موقت</td>
                    </c:if>
                    <c:if test="${row.transition.stateEnd!=null}">
                        <td style="padding:5px 5px" class="ui-widget-content">${row.transition.stateEnd.stateName}</td>
                    </c:if>
                    <c:if test="${row.transition==null}">
                        <td style="padding:5px 5px" class="ui-widget-content">ایجاد پیشنهاد موقت</td>
                    </c:if>
                    <c:if test="${row.transition!=null}">
                        <td style="padding:5px 5px" class="ui-widget-content">${row.transition.transitionName}</td>
                    </c:if>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.message}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!--
<c:if test="${historyLogs!=null}">

<div class="expandableTitleBar">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
    <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        تاریخچه تغییرات
    </p>
    <div class="content" dir="rtl" style="text-align:center;">
        <table align="center" width="80%" class="jtable resultDets" cellpadding="0" cellspacing="0" style="margin:0 auto;">
            <thead>
            <tr>
                <th style="padding:5" class="ui-state-default">&nbsp;</th>
                <th style="padding:5" class="ui-state-default">تاریخ</th>
                <th style="padding:5" class="ui-state-default">زمان</th>
                <th style="padding:5" class="ui-state-default">اختلاف زمان</th>
                <th style="padding:5" class="ui-state-default">نام کاربر تغییر دهنده</th>
                <th style="padding:5" class="ui-state-default">نام فیلد</th>
                <th style="padding:5" class="ui-state-default">مقدار قبلی</th>
                <th style="padding:5" class="ui-state-default">مقدار بعدی</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${fn:length(historyLogs)==0}">
                <tr>
                    <td colspan="8">
                        اطلاعاتی پیدا نشد.
                    </td>
                </tr>
            </c:if>
            <c:forEach var="row" items="${historyLogs}" varStatus="i">
                <tr>
                    <td style="padding:5px 5px" class="ui-widget-content">${i.index}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.date}</td>
                    <td style="padding:5px 5px" class="ui-widget-content"></td>
                    <td style="padding:5px 5px" class="ui-widget-content">${i.index != 0 ?bitarts_function:getTimeDifferenceByStringDescription(row.date, transitionLogs[i.index-1].date):''}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.time}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.user.firstName} ${row.user.lastName}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.pishnehadFields.subject}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.fromValue}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.toValue}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</c:if>
-->
<input type="button" onclick="window.location='/loginUser.action'" value="بازگشت"/>

</body>
</html>

