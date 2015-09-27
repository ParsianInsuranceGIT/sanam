<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="inputList jtable" border="0" cellspacing="1" cellpadding="1">
        <tr>
            <th width="9%">شماره</th>
            <th>تاريخ ايجاد</th>
            <th>نام كلينيك</th>
            <th>وضعيت</th>
            <th>پرینت</th>
            <th>ابطال معرفي نامه</th>
        </tr>
        <c:if test="${fn:length(pishnehad.moarefinameList)==0}"><tr><td colspan="6">تا کنون معرفی نامه ای برای این پیشنهاد ثبت نشده است.</td></tr></c:if>
        <c:if test="${fn:length(pishnehad.moarefinameList)>0}">
            <c:forEach var="row" items="${pishnehad.moarefinameList}" varStatus="i">
                <c:if test="${row.vaziat=='BATEL_SHODE'}">
                    <c:set var="theColor" value="lightgray"/>
                </c:if>
                <c:if test="${row.vaziat=='DAR_JARYAN'}">
                    <c:set var="theColor" value="white"/>
                </c:if>
                <tr>
                    <td style="background:${theColor};">${i.index + 1}</td>
                    <td style="background:${theColor};">${row.tarikhSodur}</td>
                    <td style="background:${theColor};">
                    <c:if test="${row.clinic.id == 111111}">
                        ${row.clinicNameSayer}
                    </c:if>
                    <c:if test="${row.clinic.id != 111111}">
                        ${row.clinic.clinicName}
                    </c:if>
                    </td>
                    <td style="background:${theColor};">
                        <c:if test="${row.vaziat=='BATEL_SHODE'}">
                            باطل شده
                        </c:if>
                        <c:if test="${row.vaziat=='DAR_JARYAN'}">
                            در جریان
                        </c:if>
                    </td>
                    <td style="background:${theColor};"><a onclick="window.open('/print_darkhasteMoayenatePezeshki.action?pishnehadReport.pishnehad.id=${pishnehad.id}&pishnehadReport.moarefiname.id=${row.id}');" href="javascript:void(0);">پرینت درخواست معاینات پزشکی</a></td>
                    <td style="background:${theColor};">
                        <c:if test="${row.vaziat=='BATEL_SHODE'}">
                            -
                        </c:if>
                        <c:if test="${row.vaziat=='DAR_JARYAN'}">
                            <a onclick="ebtalMoarefiname(${row.id});">ابطال</a>
                            <%--<a href="/ebtalMoarefiname.action?pishnehad.id=${pishnehad.id}&moarefiname.id=${row.id}">ابطال</a>--%>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

<input type="hidden" id="moarefinameSize" value="${pishnehad.moarefinameListDarJaryan}"/>