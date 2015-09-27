<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table style="margin: 0 auto;">
    <tr><td>
        <input type="button"
                       onclick="window.open('/printMohasebateRiazi?pishnehadReport.pishnehad.id=${pishnehad.id}')"
                       value="پرینت محاسبات ریاضی"  style="width: 250px;"/>
    </td></tr>
</table>