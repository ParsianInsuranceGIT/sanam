<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>
    گزارش اقساط مالی
</title>
<html>
<head></head>
<body>
<s:actionerror/>
<form action="/gozareshAghsatMaliSearch" method="post">
    <table>
        <tr>
            <td>
                شماره بیمه نامه:
            </td>
            <td>
                <s:textfield key="shomare" label="" theme="simple"/>
            </td>
            <td>
                سال بیمه ای:
            </td>
            <td>
                <s:textfield key="saleBimei" label="" theme="simple"/>
            </td>
        </tr>
        <tr>
            <td>
                نام بیمه گذار:
            </td>
            <td>
                <s:textfield key="bimeGozar" label="" theme="simple"/>
            </td>
            <td>
                نام بیمه شده:
            </td>
            <td>
                <s:textfield key="bimeShode" label="" theme="simple"/>
            </td>
        </tr>
        <tr>
            <td>
                وضعیت بیمه نامه:
            </td>
            <td>
                <s:select list="vaziats" key="vaziat" value="500" listKey="id" listValue="stateName" label=""
                          theme="simple"/>
            </td>
            <td>
                شناسه پرداخت:
            </td>
            <td>
                <s:textfield key="shomareMoshtari" label="" theme="simple"/>
            </td>
        </tr>
        <tr>
            <td>
                از تاریخ(صدور بیمه نامه):
            </td>
            <td>
                <s:textfield key="azTarikhSodur" label="" theme="simple" cssClass="datePkr"
                             cssStyle="margin-right: 25px"/>
            </td>
            <td>
                تا تاریخ(صدور بیمه نامه):
            </td>
            <td>
                <s:textfield key="taTarikhSodur" label="" theme="simple" cssClass="datePkr"
                             cssStyle="margin-right: 25px"/>
            </td>
        </tr>
        <tr>
            <td>
                از تاریخ(سررسید قسط):
            </td>
            <td>
                <s:textfield key="azSarresid" label="" theme="simple" cssClass="datePkr" cssStyle="margin-right: 25px"/>
            </td>
            <td>
                تا تاریخ(سررسید قسط):
            </td>
            <td>
                <s:textfield key="taSarresid" label="" theme="simple" cssClass="datePkr" cssStyle="margin-right: 25px"/>
            </td>
        </tr>
        <tr>
            <td>
                از تاریخ(پرداخت قسط):
            </td>
            <td>
                <s:textfield key="azPardakht" label="" theme="simple" cssClass="datePkr" cssStyle="margin-right: 25px"/>
            </td>
            <td>
                تا تاریخ(پرداخت قسط):
            </td>
            <td>
                <s:textfield key="taPardakht" label="" theme="simple" cssClass="datePkr" cssStyle="margin-right: 25px"/>
            </td>
        </tr>
        <tr>
            <td>
                از مبلغ قسط(ریال):
            </td>
            <td>
                <s:textfield key="azMablagh" label="" theme="simple"/>
            </td>
            <td>
                تا مبلغ قسط(ریال):
            </td>
            <td>
                <s:textfield key="taMablagh" label="" theme="simple"/>
            </td>
        </tr>
        <tr>
            <td>
                از تاریخ(سند مالی):
            </td>
            <td>
                <s:textfield key="azSanad" label="" theme="simple" cssClass="datePkr" cssStyle="margin-right: 25px"/>
            </td>
            <td>
                تا تاریخ(سند مالی):
            </td>
            <td>
                <s:textfield key="taSanad" label="" theme="simple" cssClass="datePkr" cssStyle="margin-right: 25px"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="جستجو">
                <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'"
                       value="بازگشت">
            </td>
            <td colspan="2">
                <s:submit theme="simple" action="gozareshAghsatMaliExcel" value="خروجی اکسل"/>
            </td>
        </tr>
    </table>
</form>
<s:if test="results_ghest.size()>0">
    <display:table export="false" id="tblGhestResultList" uid="rpViewResult"
                   htmlId="tblGhestResultList"
                   name="results_ghest"
                   requestURI="" clearStatus="true"
                   keepStatus="false">
        <display:column title="ردیف">${rpViewResult_rowNum}</display:column>
        <display:column title="شماره بیمه نامه">
            <a href="#"
               onclick="window.open('/gozareshAghsatFastView?bimenameId=${rpViewResult.ghestBandi.bimename.id}','','width=800,height=600')">
                    ${rpViewResult.ghestBandi.bimename.shomare}
            </a>
        </display:column>
        <display:column title="بیمه گذار" property="ghestBandi.bimename.pishnehad.bimeGozar.shakhs.fullName"/>
        <display:column title="بیمه شده" property="ghestBandi.bimename.pishnehad.bimeShode.shakhs.fullName"/>
        <display:column title="تاریخ صدور" property="ghestBandi.bimename.tarikhSodour"/>
        <display:column title="سال بیمه ای" property="ghestBandi.saleBimei"/>
        <display:column title="وضعیت بیمه نامه" property="ghestBandi.bimename.state.stateName"/>
        <display:column title="وضعیت وام" property="credebit.vaziatVam"/>
        <display:column title="وضعیت اقساط" property="credebit.vaziatGhest"/>
        <display:column title="مشاهده اقساط">
            <input type="button" value="حق بیمه"
                   onclick="window.location='/gozareshAghsatMaliDetail?ghestBandi=${rpViewResult.ghestBandi.id}&type=0'">
            <input type="button" value="وام"
                   onclick="window.location='/gozareshAghsatMaliDetail?ghestBandi=${rpViewResult.ghestBandi.id}&type=1'">
            <input type="button" value="هردو"
                   onclick="window.location='/gozareshAghsatMaliDetail?ghestBandi=${rpViewResult.ghestBandi.id}&type=2'">
        </display:column>
    </display:table>
</s:if>
<s:if test="%{results_ghest!=null && results_ghest.size()==0}">
    اطلاعاتی یافت نشد
</s:if>

</body>
</html>