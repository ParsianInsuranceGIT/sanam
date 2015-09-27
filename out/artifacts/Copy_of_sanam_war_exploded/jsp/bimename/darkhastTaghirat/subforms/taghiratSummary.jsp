<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table style="margin: 0 auto;">
    <tr><td>
        <input type="button"
                       onclick="window.open('/printMohasebateRiazi?ghableTaghir=yes&pishnehadReport.pishnehad.id=${darkhastTaghirat.oldPishnehad.id}&pishnehadReport.currentElhaghiye.id=${elhaghiye.id}')"
                      value="پرینت محاسبات ریاضی قبل از تغییر"  style="width: 250px;"/>
    </td></tr>
    <tr><td>
        <input type="button"
                       onclick="window.open('/printMohasebateRiazi?ghableTaghir=no&pishnehadReport.pishnehad.id=${darkhastTaghirat.newPishnehad.id}&pishnehadReport.currentElhaghiye.id=${elhaghiye.id}')"
                     value="پرینت محاسبات ریاضی بعد از تغییر"  style="width: 250px;"/>
    </td></tr>
    <tr><td>
        <input type="button"
                       onclick="window.open('/printSoartVaziateBimeName?pishnehadReport.pishnehad.id=${pishnehad.id}')"
                       value="پرینت صورت وضعیت بیمه نامه" style="width: 250px;"/>
    </td></tr>
    <tr><td>
        <input type="button"
                       onclick="window.open('/printSoratVaziateMali?pishnehadReport.pishnehad.id=${pishnehad.id}')"
                       value="پرینت صورت وضعیت مالی" style="width: 250px;"/>
    </td></tr>
</table>