<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>
    گزارش پیشنهادات صادره و واریزی ها
</title>

<div align="center">
    <form action="/gozareshSadereVarizi" method="post">
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
    <s:if test="%{fishList!=null && fishList.size()>0}">

        <display:table export="true" id="tblfishList" uid="rpViewResult" partialList="true" pagesize="15"
                       size="${fishList.size()}"
                       htmlId="tblfishList"
                       name="fishList"
                       requestURI="gozareshSadereVarizi" clearStatus="true"
                       keepStatus="false">
            <display:column title="نام سیستم">پیش پرداخت های بیمه عمر</display:column>
            <display:column title="شماره بیمه نامه" property="pishnehad.bimename.shomare"/>
            <display:column
                    title="مبلغ">${rpViewResult.paymentType.equals('fish')?rpViewResult.mablagh:''}</display:column>
            <display:column
                    title="تاریخ فیش بانک">${rpViewResult.paymentType.equals('fish')?rpViewResult.tarikh:''}</display:column>
            <display:column
                    title="شماره سند بانک">${rpViewResult.paymentType.equals('fish')?rpViewResult.shomare:''}</display:column>
            <%--<display:column title="مبلغ بانک" property="mablagh"/>--%>
            <%--<display:column title="تاریخ فیش تجارت الکترونیک">${rpViewResult.paymentType.equals('interneti')?rpViewResult.tarikh:''}</display:column>--%>
            <%--<display:column title="شماره سند تجارت الکترونیک">${rpViewResult.paymentType.equals('interneti')?rpViewResult.shomare:''}</display:column>--%>
            <%--<display:column title="مبلغ تجارت الکترونیک" >${rpViewResult.paymentType.equals('interneti')?rpViewResult.mablagh:''}</display:column>--%>
            <%--<display:column title="جمع کل پرداختی" property="mablagh"/>--%>

        </display:table>
    </s:if>
    <s:if test="%{fishList!=null && fishList.size()==0}">
        رکوردی یافت نشد.
    </s:if>
</div>