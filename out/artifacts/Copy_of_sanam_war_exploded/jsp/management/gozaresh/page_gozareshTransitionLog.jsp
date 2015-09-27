<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>
    گزارش تاریخچه تغییر وضعیت ها به تفکیک کاربر
</title>

<div align="center">
    <form action="/gozareshTransitionLog" method="post">
        <table>
            <tr>
                <td>
                    انتخاب کاربر:
                </td>
                <td>
                    <s:select list="userList" listValue="fullName" listKey="id" name="userId" theme="simple"
                              headerKey="-1" headerValue="-"/>
                </td>
                <td></td>
                <td></td>
            </tr>
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
                <td><label>از وضعیت:</label></td>
                <td>
                    <s:select list="states" listValue="stateName" listKey="id" name="azVaziat" theme="simple"
                              headerKey="-1" headerValue="-"/>
                </td>
                <td><label>به وضعیت:</label></td>
                <td>
                   <s:select list="states" listValue="stateName" listKey="id" name="beVaziat" theme="simple"
                              headerKey="-1" headerValue="-"/>
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
    <s:if test="%{gStructureTransitionLogList!=null && gStructureTransitionLogList.size()>0}">
        <display:table export="false" id="tblfishList" uid="rpViewResult" partialList="true" pagesize="15"
                       size="${gStructureTransitionLogList.size()}"
                       htmlId="tblfishList"
                       name="gStructureTransitionLogList"
                       requestURI="" clearStatus="true"
                       keepStatus="false">
            <display:column title="کد رهگیری پیشنهاد" property="kodRahgiri"/>
            <display:column title="تاریخ ایجاد پیشنهاد" property="createdDate"/>
            <display:column title="کاربر" property="username"/>
            <display:column title="از وضعیت" property="azstate"/>
            <display:column title="یه وضعیت" property="bestate"/>
            <display:column title="تاریخ انجام تغییر وضعیت" property="tarikh"/>
            <display:column title="ساعت انجام تغییر وضعیت" property="saat"/>
            <display:column title="اختلاف زمان تغییر وضعیت" property="ekhtelaf"/>
            <display:column title="پیغام" property="peygham"/>
        </display:table>
        <table>
            <tr>
                <td colspan="4">تعداد کل تغییر وضعیت ها:</td>
                <td colspan="5"><s:property value="gStructureTransitionLogList.size()"/></td>
            </tr>
            <tr>
                <td colspan="4">کل زمان انجام تغییر وضعیت ها:</td>
                <td colspan="5"><s:property value="kolZamanTaghirVaziat"/></td>
            </tr>
        </table>
    </s:if>
    <s:if test="%{gStructureTransitionLogList!=null && gStructureTransitionLogList.size()==0}">
        رکوردی یافت نشد.
    </s:if>
</div>