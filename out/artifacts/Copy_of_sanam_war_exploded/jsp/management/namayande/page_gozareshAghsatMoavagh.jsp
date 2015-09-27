<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<body>
<s:actionerror/>
<form action="/gozareshAghsatMoavaghSearch" method="post">
    <table>
        <tr>
            <td>
                شماره بیمه نامه:
            </td>
            <td>
                <s:textfield key="shomare" label="" theme="simple"/>
            </td>
            <td>
                وضعیت بیمه نامه:
            </td>
            <td>
                <s:select list="vaziats" key="vaziat" value="500" listKey="id" listValue="stateName" label=""
                          theme="simple"/>
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
                <s:textfield key="azSarresid" label="" theme="simple"
                             cssClass="validate[required,custom[date]] datePkr" cssStyle="margin-right: 25px"/>
            </td>
            <td>
                تا تاریخ(سررسید قسط):
            </td>
            <td>
                <s:textfield key="taSarresid" label="" theme="simple"
                             cssClass="validate[required,custom[date]] datePkr" cssStyle="margin-right: 25px"/>
            </td>
        </tr>
        <tr>
            <td>
                بررسی پزشکی:
            </td>
            <td>
                <select name="barresiPezeshki">
                    <option value="0">ندارد</option>
                    <option value="1">دارد</option>
                </select>
            </td>
            <td>
                نحوه پرداخت حق بیمه:
            </td>
            <td>
                <select name="nahvePardakht">
                    <option value="0">ماهانه</option>
                    <option value="1">سه ماهه</option>
                    <option value="2">شش ماهه</option>
                    <option value="3">سالانه</option>
                    <option value="4">یکجا</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                از تعداد حق بیمه پرداخت نشده:
            </td>
            <td>
                <s:textfield key="azTedadHaghBime" label="" theme="simple"/>
            </td>
            <td>
                تا تعدادحق بیمه پرداخت نشده:
            </td>
            <td>
                <s:textfield key="taTedadHaghBime" label="" theme="simple"/>
            </td>
        </tr>
        <tr>
            <td>
                از مبلغ اقساط حق بیمه پرداخت نشده:
            </td>
            <td>
                <s:textfield key="azMablaghHaghBime" label="" theme="simple"/>
            </td>
            <td>
                تا مبلغ اقساط حق بیمه پرداخت نشده:
            </td>
            <td>
                <s:textfield key="taMablaghHaghBime" label="" theme="simple"/>
            </td>
        </tr>
        <tr>
            <td>
                از تعداد اقساط وام پرداخت نشده:
            </td>
            <td>
                <s:textfield key="azTedadVam" label="" theme="simple"/>
            </td>
            <td>
                تا تعداد اقساط وام پرداخت نشده:
            </td>
            <td>
                <s:textfield key="taTedadVam" label="" theme="simple"/>
            </td>
        </tr>
        <tr>
            <td>
                از مبلغ اقساط وام پرداخت نشده:
            </td>
            <td>
                <s:textfield key="azMablaghVam" label="" theme="simple"/>
            </td>
            <td>
                تا مبلغ اقساط وام پرداخت نشده:
            </td>
            <td>
                <s:textfield key="taMablaghVam" label="" theme="simple"/>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <input type="submit" value="جستجو">
                <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'"
                       value="بازگشت">
            </td>
        </tr>
    </table>
</form>
<s:if test="result_bimename.size()>0">

    <display:table export="false" id="tblbimenameResultList" uid="rpViewResult"
                   htmlId="tblbimenameResultList"
                   name="result_bimename"
                   requestURI="" clearStatus="true"
                   keepStatus="false">
        <display:caption>
            گزارش اقساط پرداخت نشده از تاریخ ${azSarresid} تا تاریخ ${taSarresid}
        </display:caption>
        <display:column sortable="true" title="ردیف">${rpViewResult_rowNum}</display:column>
        <display:column sortable="true" title="بیمه گذار" property="pishnehad.bimeGozar.shakhs.fullName"/>
        <display:column sortable="true" title="شماره بیمه نامه" property="shomare"/>
        <display:column sortable="true" title="بیمه شده" property="pishnehad.bimeShode.shakhs.fullName"/>
        <display:column sortable="true" title="تعداد اقساط حق بیمه پرداخت نشده" property="tedadAghsatPardakhtNashode"/>
        <display:column sortable="true" title="مجموع مبلغ حق بیمه پرداخت نشده" property="majmuAghsatPardakhtNashode"/>
        <display:column sortable="true" title="تاریخ صدور" property="tarikhSodour"/>
        <display:column sortable="true" title="بررسی پزشکی"/>
        <display:column sortable="true" title="ارزش بازخریدی" property="arzeshBazkharidi"/>
        <display:column sortable="true" title="نحوه پرداخت حق بیمه"
                        property="pishnehad.estelam.nahvePardakhtHaghBimeFarsi"/>
        <display:column sortable="true" title="تعداد اقساط وام پرداخت نشده" property="tedadAghsatVamPardakhtNashode"/>
        <display:column sortable="true" title="مجموع اقساط وام پرداخت نشده" property="majmuAghsatVamPardakhtNashode"/>
        <display:column sortable="true" title="تلفن/موبایل" property="pishnehad.addressBimeGozar.telphoneMobile"/>
    </display:table>
</s:if>
<s:if test="%{result_bimename != null && result_bimename.size() ==0 }">
    اطلاعاتی یافت نشد
</s:if>
</body>
</html>