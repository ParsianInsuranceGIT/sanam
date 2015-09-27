<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>
    گزارش پرداخت های الکترونیکی
</title>

<div align="center">
    <form action="/gozareshPardakhtElectronic" method="post">
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
        <display:table export="true" id="tblfishList" uid="rpViewResult"
                       htmlId="tblfishList"
                       name="fishList"
                       requestURI="gozareshPardakhtElectronic" clearStatus="true"
                       keepStatus="false">
            <display:column title="نام سیستم">پیش پرداخت های بیمه عمر</display:column>
            <display:column title="شماره بیمه نامه" property="pishnehad.bimename.shomare"/>
            <display:column title="تاریخ پرداخت" property="tarikh"/>
            <display:column title="ساعت پرداخت" property="time"/>
            <display:column title="مبلغ" property="mablagh"/>

        </display:table>
    </s:if>
    <s:if test="%{fishList!=null && fishList.size()==0}">
        رکوردی یافت نشد.
    </s:if>
</div>