<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<body>
<s:actionerror/>
<form action="/gozareshAghsatMahaneSearch" method="post">
    <table>
        <tr>
            <td>
                نوع گزارش:
            </td>
            <td>
                <select name="typeOfReport">
                    <option value="0">حق بیمه</option>
                    <option value="1">وام</option>
                    <option value="2" selected="selected">هر دو</option>
                </select>
            </td>
            <td>
                سال:
            </td>
            <td>
                <s:select list="years" key="year" label=""
                          theme="simple"/>
            </td>
            <td>
                ماه:
            </td>
            <td>
                <select name="month">
                    <option value="1">فروردین</option>
                    <option value="2">اردیبهشت</option>
                    <option value="3">خرداد</option>
                    <option value="4">تیر</option>
                    <option value="5">مرداد</option>
                    <option value="6">شهریور</option>
                    <option value="7">مهر</option>
                    <option value="8">آبان</option>
                    <option value="9">آذر</option>
                    <option value="10">دی</option>
                    <option value="11">بهمن</option>
                    <option value="12">اسفند</option>
                </select>
            </td>

        </tr>
        <tr>
            <td colspan="6">
                <input type="submit" value="جستجو">
                <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'"
                       value="بازگشت">
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
        <display:column sortable="true" title="ردیف">${rpViewResult_rowNum}</display:column>
        <display:column sortable="true" title="بیمه گذار"
                        property="ghestBandi.bimename.pishnehad.bimeGozar.shakhs.fullName"/>
        <display:column sortable="true" title="شماره بیمه نامه" property="ghestBandi.bimename.shomare"/>
        <display:column sortable="true" title="بیمه شده"
                        property="ghestBandi.bimename.pishnehad.bimeShode.shakhs.fullName"/>
        <display:column sortable="true" title="مبلغ قسط" property="credebit.amount"/>
        <display:column sortable="true" title="تاریخ سررسید قسط" property="sarresidDate"/>
        <display:column sortable="true" title="شناسه پرداخت" property="credebit.shomareMoshtari"/>
        <display:column sortable="true" title="نحوه پرداخت"
                        property="ghestBandi.bimename.pishnehad.estelam.nahvePardakhtHaghBimeFarsi"/>
        <display:column sortable="true" title="وضعیت پرداخت" property="credebit.vaziatPardakht"/>
        <display:column sortable="true" title="تلفن/موبایل"
                        property="ghestBandi.bimename.pishnehad.addressBimeGozar.telphoneMobile"/>
    </display:table>
</s:if>
<s:if test="%{results_ghest!=null && results_ghest.size()==0}">
    اطلاعاتی یافت نشد
</s:if>
</body>
</html>