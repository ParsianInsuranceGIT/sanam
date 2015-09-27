<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<body>
<table>
    <s:if test="type==0">
        <tr>
            <td>شماره بیمه نامه:</td>
            <td><s:property value="shomare"/></td>
            <td>تاریخ صدور:</td>
            <td><s:property value="tarikhSodur"/></td>
            <td>نام بیمه گذار:</td>
            <td><s:property value="bimeGozar"/></td>
        </tr>
    </s:if>
    <s:if test="type==1">
        <tr>
            <td>شماره وام:</td>
            <td><s:property value="shomareVam"/></td>
            <td>تاریخ اخذ وام:</td>
            <td><s:property value="akhzVam"/></td>
            <td>نام بیمه گذار:</td>
            <td><s:property value="bimeGozar"/></td>
        </tr>
    </s:if>
    <tr>
        <td>آدرس:</td>
        <td colspan="2"><s:property value="address"/></td>
        <td>تلفن:</td>
        <td><s:property value="telphone"/></td>
        <td>موبایل:</td>
        <td><s:property value="mobile"/></td>
    </tr>
</table>


<s:if test="results_ghest.size()>0">
    <display:table export="false" id="tblGhestResultList" uid="rpViewResult"
                   htmlId="tblGhestResultList"
                   name="results_ghest"
                   requestURI="" clearStatus="true"
                   keepStatus="false">
        <display:column title="ردیف">${rpViewResult_rowNum}</display:column>
        <display:column title="شناسه پرداخت" property="credebit.shomareMoshtari"/>
        <display:column title="تاریخ سررسید قسط" property="sarresidDate"/>
        <display:column title="تاریخ پرداخت قسط" property="tarikhPardakht">
        </display:column>
        <display:column title="تاریخ سند مالی" property="credebit.tarikhSanadMali"/>
        <display:column title="مبلغ قسط" property="credebit.amount"/>
        <display:column title="وضعیت پرداخت" property="credebit.vaziatPardakht"/>
        <display:column title="کد رهگیری" property="credebit.shenaseCredebit"/>
    </display:table>
</s:if>
<s:if test="%{results_ghest!=null && results_ghest.size()==0}">
    اطلاعاتی یافت نشد
</s:if>
</body>
</html>