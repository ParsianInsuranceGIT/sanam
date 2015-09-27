<%--
  Created by IntelliJ IDEA.
  User: Arron0
  Date: Aug 10, 2011
  Time: 12:52:59 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div>
    مبلغ الحاقيه اضافي/برگشتی :
    <fmt:formatNumber type="currency" pattern="#,##0">${viewElhaghie.mablagheElhaghie}</fmt:formatNumber>
    <c:if test="${viewElhaghie.mablagheElhaghie < 0}">
        <br/>
        به میزان تفاوت قسط های پرداخت شده با رقم های بیشتر، در سیستم اعتبار برای بیمه گذار در نظر گرفته شده است.
    </c:if>
</div>
<div class="expandableTitleBar">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        اقساط قدیمی
    </p>
    <table align="center" class="jtable resultDets" cellpadding="0" cellspacing="0" style="margin:0 auto;">
        <thead>
        <tr>
            <th style="padding:5" class="ui-state-default">سال</th>
            <th style="padding:5" class="ui-state-default">تاریخ</th>
            <th style="padding:5" class="ui-state-default">حق بیمه پرداختی</th>
            <th style="padding:5" class="ui-state-default">نوع</th>
            <th style="padding:5" class="ui-state-default">هزینه کارمزد</th>
            <th style="padding:5" class="ui-state-default">هزینه بیمه گری</th>
            <th style="padding:5" class="ui-state-default">هزینه اداری</th>
            <th style="padding:5" class="ui-state-default">هزینه وصول</th>
            <th style="padding:5" class="ui-state-default">حق بيمه خالص فوت يكجا</th>
            <th style="padding:0" class="ui-state-default">سرمایه فوت به هر علت</th>
            <th style="padding:0" class="ui-state-default">سرمایه فوت در اثر حادثه</th>
            <th style="padding:0" class="ui-state-default">سرمایه پوشش اضافی امراض خاص</th>
            <th style="padding:0" class="ui-state-default">حق بیمه پوشش های اضافی</th>
            <%--<th style="padding:5" class="ui-state-default">اندوخته سرمايه گذاري</th>--%>
            <%--<th style="padding:5" class="ui-state-default">ارزش بازخريد</th>--%>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="taghsitReport" id="row" status="stat">
            <tr>
                <td style="padding:5px 5px" class="ui-widget-content"><s:property value="#row.getSaal()"/></td>
                <td style="padding:5px 5px" class="ui-widget-content"><s:property value="#row.getTarikh()"/></td>
                <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getHaghBimePardaakhtiSaal()"/></s:text></td>
                <td style="padding:5px 5px" class="ui-widget-content">قسط</td>
                <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getKaarmozd()"/></s:text></td>
                <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getHazineBimeGari()"/></s:text></td>
                <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getHazineEdari()"/></s:text></td>
                <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getHazineVosul()"/></s:text></td>
                <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getHaghBimeKhaalesFotYekja()"/></s:text></td>
                <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmaayeFotBehHarEllat()"/></s:text></td>
                <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmaayeFotDarAsarHaadeseh()"/></s:text></td>
                <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmaayePusheshEzaafiAmraazKhaas()"/></s:text></td>
                <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getHaghBimePusheshHaayeEzaafi()"/></s:text></td>
                    <%--<td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="taghsitReport.andukhteSarmaayeGozaariBaaSudTazmini15Darsad()"/></s:text></td>--%>
                    <%--<td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="taghsitReport.arzeshBazKharidBaaSudTazmini15Darsad()"/></s:text></td>--%>
            </tr>
        </s:iterator>
        <%--<tr>--%>
        <%--<td>&nbsp;</td>--%>
        <%--</tr>--%>
        </tbody>
    </table>
</div>
<div class="expandableTitleBar">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        اقساط جدید که باید تولید شود
    </p>
    <table align="center" class="jtable resultDets" cellpadding="0" cellspacing="0" style="margin:0 auto;">
        <thead>
        <tr>
            <th style="padding:5" class="ui-state-default">سال</th>
            <th style="padding:5" class="ui-state-default">تاریخ</th>
            <th style="padding:5" class="ui-state-default">حق بیمه پرداختی</th>
            <th style="padding:5" class="ui-state-default">نوع</th>
            <th style="padding:5" class="ui-state-default">هزینه کارمزد</th>
            <th style="padding:5" class="ui-state-default">هزینه بیمه گری</th>
            <th style="padding:5" class="ui-state-default">هزینه اداری</th>
            <th style="padding:5" class="ui-state-default">هزینه وصول</th>
            <th style="padding:5" class="ui-state-default">حق بيمه خالص فوت يكجا</th>
            <th style="padding:0" class="ui-state-default">سرمایه فوت به هر علت</th>
            <th style="padding:0" class="ui-state-default">سرمایه فوت در اثر حادثه</th>
            <th style="padding:0" class="ui-state-default">سرمایه پوشش اضافی امراض خاص</th>
            <th style="padding:0" class="ui-state-default">حق بیمه پوشش های اضافی</th>
            <%--<th style="padding:5" class="ui-state-default">اندوخته سرمايه گذاري</th>--%>
            <%--<th style="padding:5" class="ui-state-default">ارزش بازخريد</th>--%>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="newTaghsitReport" id="row" status="stat">
            <tr>
                <td style="padding:5px 5px" class="ui-widget-content"><s:property value="#row.getSaal()"/></td>
                <td style="padding:5px 5px" class="ui-widget-content"><s:property value="#row.getTarikh()"/></td>
                <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getHaghBimePardaakhtiSaal()"/></s:text></td>
                <td style="padding:5px 5px" class="ui-widget-content">قسط</td>
                <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getKaarmozd()"/></s:text></td>
                <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getHazineBimeGari()"/></s:text></td>
                <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getHazineEdari()"/></s:text></td>
                <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getHazineVosul()"/></s:text></td>
                <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getHaghBimeKhaalesFotYekja()"/></s:text></td>
                <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmaayeFotBehHarEllat()"/></s:text></td>
                <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmaayeFotDarAsarHaadeseh()"/></s:text></td>
                <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmaayePusheshEzaafiAmraazKhaas()"/></s:text></td>
                <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getHaghBimePusheshHaayeEzaafi()"/></s:text></td>
                    <%--<td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="taghsitReport.andukhteSarmaayeGozaariBaaSudTazmini15Darsad()"/></s:text></td>--%>
                    <%--<td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="taghsitReport.arzeshBazKharidBaaSudTazmini15Darsad()"/></s:text></td>--%>
            </tr>
        </s:iterator>
        <%--<tr>--%>
        <%--<td>&nbsp;</td>--%>
        <%--</tr>--%>
        </tbody>
    </table>

    <hr/>
    <table style="width:100%">
        <tr>
            <td width="50%">
                <table class="jtable resultDets" cellpadding="0" cellspacing="0">
                    <tr>
                        <th colspan="4">اقساط قديم</th>
                    </tr>
                    <tr>
                        <th>ردیف</th>
                        <th>مبلغ</th>
                        <th>تاریخ سررسید</th>
                        <th>وضعیت</th>
                    </tr>
                    <c:if test='${viewElhaghie.elamBeMaliShode != "Elam_Be_Mali_Shode"}'>
                        <tr>
                            <td>
                                اعلام به مالی نشده
                            </td>
                        </tr>
                    </c:if>
                    <c:if test='${viewElhaghie.elamBeMaliShode == "Elam_Be_Mali_Shode"}'>
                        <c:forEach var="row" items="${viewElhaghie.oldBedehiList}" varStatus="i">
                            <tr>
                                <td>${i.index+1}</td>
                                <td><fmt:formatNumber type="currency" pattern="#,##0">${row.amount}</fmt:formatNumber></td>
                                <td>${row.sarresidDate}</td>
                                <td>
                                    <c:if test='${row.credebit.status == "SANAD_KHORDE"}'>پرداخت شده</c:if>
                                    <c:if test='${row.credebit.status != "SANAD_NA_KHORDE"}'>پرداخت نشده</c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </td>
            <td>
                <table class="jtable resultDets" cellpadding="0" cellspacing="0">
                    <tr>
                        <th colspan="4">اقساط جديد</th>
                    </tr>
                    <tr>
                        <th>ردیف</th>
                        <th>مبلغ</th>
                        <th>تاریخ سررسید</th>
                        <th>وضعیت</th>
                    </tr>
                    <c:forEach var="row" items="${viewElhaghie.newBedehiList}" varStatus="i">
                        <tr>
                            <td>${i.index+1}</td>
                            <td><fmt:formatNumber type="currency" pattern="#,##0">${row.amount}</fmt:formatNumber></td>
                            <td>${row.sarresidDate}</td>
                            <td>
                                <c:if test='${row.credebit.status == "SANAD_KHORDE"}'>پرداخت شده</c:if>
                                <c:if test='${row.credebit.status != "SANAD_KHORDE"}'>پرداخت نشده</c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>

</div>