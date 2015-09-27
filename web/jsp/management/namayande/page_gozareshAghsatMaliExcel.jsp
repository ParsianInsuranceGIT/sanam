<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<body>
<s:if test="results_ghest.size()>0">
    <display:table export="true" id="tblGhestResultList" uid="rpViewResult"
                   htmlId="tblGhestResultList"
                   name="results_ghest"
                   requestURI="/gozareshAghsatMaliExcel" clearStatus="true"
                   keepStatus="false">
        <display:column title="ردیف">${rpViewResult_rowNum}</display:column>
        <display:column title="شماره بیمه نامه" property="ghestBandi.bimename.shomare"/>
        <display:column title="بیمه گذار" property="ghestBandi.bimename.pishnehad.bimeGozar.shakhs.fullName"/>
        <display:column title="بیمه شده" property="ghestBandi.bimename.pishnehad.bimeShode.shakhs.fullName"/>
        <display:column title="تاریخ صدور" property="ghestBandi.bimename.tarikhSodour"/>
        <%--<display:column title="سال بیمه ای" property="ghestBandi.saleBimei"/>--%>
        <display:column title="وضعیت بیمه نامه" property="ghestBandi.bimename.state.stateName"/>
        <display:column title="وضعیت وام" property="credebit.vaziatVam"/>
        <display:column title="وضعیت اقساط" property="credebit.vaziatGhest"/>
        <display:column title="شناسه پرداخت" property="credebit.shomareMoshtari"/>
        <display:column title="تاریخ سررسید قسط" property="sarresidDate"/>
        <display:column title="تاریخ پرداخت قسط" property="tarikhPardakht">
        </display:column>
        <display:column title="تاریخ سند مالی" property="credebit.tarikhSanadMali"/>
        <display:column title="مبلغ قسط" property="credebit.amount"/>
        <display:column title="وضعیت پرداخت" property="credebit.vaziatPardakht"/>
        <display:column title="کد رهگیری" property="credebit.shenaseCredebit"/>
        <display:column title="تلفن/موبایل" property="ghestBandi.bimename.pishnehad.addressBimeGozar.telphoneMobile"/>
    </display:table>
</s:if>
<s:if test="%{results_ghest!=null && results_ghest.size()==0}">
    اطلاعاتی یافت نشد
</s:if>
</body>
</html>