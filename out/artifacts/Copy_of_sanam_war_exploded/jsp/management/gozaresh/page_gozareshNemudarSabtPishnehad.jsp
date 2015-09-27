<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>
    گزارش تعدادی وضعیت پیشنهاد سیستم
</title>

<div align="center">
    <form action="/gozareshNemudarSabtPishnehad" method="post">
        <table>
            <tr>
                <td><label>از تاریخ:</label></td>
                <td>
                    <s:textfield name="azTarikh" cssClass="datePkr" theme="simple"/>
                </td>
                <td><label>تا تاریخ:</label></td>
                <td>
                    <s:textfield key="taTarikh" label="" cssClass="datePkr" theme="simple"/>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="center">
                    <input type="submit" value="جستجو">
                    <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'"
                           value="بازگشت">
                </td>
            </tr>
        </table>
    </form>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <s:if test="%{chartPath!=null && !chartPath.isEmpty()}">
        <img src="<s:property value="chartPath"/>" alt="">
        <div style="margin: 0 auto;">
            <br/>
            تعداد پیشنهادات جدید:
            ${jamJadid}
            <br/>
            تعداد پیشنهادات ارسال شده:
            ${jamErsalShode}
        </div>
    </s:if>
    <s:if test="%{chartPath!=null && chartPath.isEmpty()}">
        رکوردی یافت نشد.
    </s:if>
</div>