<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 8/15/11
  Time: 2:32 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table align="center" class="jtable resultDets" cellpadding="0" cellspacing="0" style="margin:0 auto;">
    <tr>
        <th>ردیف</th>
        <th>نوع پرداخت</th>
        <th>مبلغ</th>
        <th>نام پرداخت کننده</th>
        <th>شماره سند</th>
        <th>نام بانک</th>
        <th>کد شعبه</th>
        <th>شماره</th>
        <th>تاریخ</th>
        <th>ساعت</th>
        <th>وضعیت پرداخت</th>
        <th>چاپ رسید</th>
    </tr>
    <c:forEach var="row" items="${pishnehad.fishs}" varStatus="i">
        <tr>
            <td>${i.index+1}</td>
            <td>
                <c:if test="${row.paymentType == \"fish\"}">
                    فیش
                </c:if>
                <c:if test="${row.paymentType == \"interneti\"}">
                    اینترنتی
                </c:if>
                <c:if test="${row.paymentType == \"shenasedar\"}">
                    شناسه دار
                </c:if>

            </td>
            <td>${row.mablaghFormatted}</td>
            <td>${row.credebit.daryafteFish.tozihat}</td>
            <td>${row.credebit.daryafteFish.shomareSanadBank}</td>
            <td>
                ${row.bankName}
            </td>
            <td>${row.kodeShobe}</td>
            <td>${row.shomare}</td>
            <td>${row.tarikh}</td>
            <td>${row.time}</td>
            <td>
                <c:if test="${row.found == \"false\"}">
منتظر تایید
                </c:if>
                <c:if test="${row.found == \"true\"}">
                    تایید شده
                </c:if>
            </td>
            <td><input type="button" onclick="window.open('/print_residePishPardakht.action?pishnehadReport.pishnehad.id=${pishnehad.id}&pishnehadReport.fish.id=${row.id}');" value="پرینت رسید"></td>
            <c:if test="${ (pishnehad.state.id == 20 || pishnehad.state.id == 90 || pishnehad.state.id == 50 ) && row.found == \"false\" }" >
                <td> <input type="button" value="حذف فیش" onclick="window.location='/deleteFish.action?fishId=${row.id}&pishnehad.id=${pishnehad.id}'" ></td>
            </c:if>
        </tr>
        <%--</c:if>--%>
    </c:forEach>
</table>
