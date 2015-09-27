<%@ page import="com.bitarts.parsian.model.pishnahad.Pishnehad" %>
<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.log.TransitionLog" %>
<%@ page import="com.bitarts.parsian.model.Transition" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://parsian.bitarts.com/functions" prefix="bitarts_function" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Pishnehad pishnehadRun = (Pishnehad) request.getAttribute("pishnehadRun");
%>


<table align="center" class="jtable resultDets" cellpadding="0" cellspacing="0" style="margin:0 auto;">
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
    <%--<%--%>
        <%--if (pishnehadRun.getTransitionLogs().size()>0 || session.getAttribute("isDarkhast").equals("yes"))--%>
        <%--{    }--%>
    <%--%>--%>
    <c:if test="${pishnehadRun.transitionLogs.size()>0 || sessionScope.isDarkhast=='yes' }">
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
                        <td style="padding:5px 5px" class="ui-widget-content">
                        ${row.transition.stateEnd.stateName}
                            <c:if test="${row.transition.stateEnd.id == 248}">(${pishnehadRun.karshenasNazer.username})</c:if>
                        </td>
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
    </c:if>
    <c:if test="${pishnehadRun.transitionLogs.size()==0}">
    <%--<%--%>
        <%--}--%>
        <%--else--%>
        <%--{        --%>
    <%--%>--%>
        <tr>
            <td colspan="5">پیغامی برای نمایش وجود ندارد.</td>
        </tr>
    <%--<%--%>
        <%--}--%>
    <%--%>--%>
    </c:if>
    </tbody>
</table>