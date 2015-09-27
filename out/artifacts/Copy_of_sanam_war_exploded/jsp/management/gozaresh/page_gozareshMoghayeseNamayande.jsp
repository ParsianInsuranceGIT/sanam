<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>
    گزارش تعدادی وضعیت پیشنهاد سیستم
</title>

<div align="center">
    <form action="/gozareshMoghayeseNamayande" method="post">
        <table>
            <tr>
                <td>
                    تعداد فیلتر
                </td>
                <td>
                    <select name="filter">
                        <option value="10" selected="selected">10</option>
                        <option value="20">20</option>
                        <option value="30">30</option>
                        <option value="50">50</option>
                        <option value="-1">همه</option>
                    </select>
                </td>
                <td colspan="2"></td>
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
                <td colspan="4" align="center">
                    <input type="submit" value="جستجو">
                    <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'"
                           value="بازگشت">
                </td>
            </tr>
        </table>
    </form>
    <s:if test="%{gStructureNamayandeList!=null && gStructureNamayandeList.size()>0}">
        <display:table name="gStructureNamayandeList" pagesize="15" size="gStructureNamayandeList.size()"
                       requestURI="gozareshMoghayeseNamayande">
            <display:column title="نام/کد نماینده" property="nameAndKod"/>
            <display:column title="تعداد کل پیشنهادات ثبت شده" property="tedadPishnehadSabt"/>
            <display:column title="تعداد پیشنهادات جدید" property="tedadPishnehadJadid"/>
            <display:column title="تعداد پیشنهادات ارسالی" property="tedadPishnehadErsal"/>
            <display:column title="تعداد پیشنهادات نهایی" property="tedadPishnehadNahai"/>
            <display:column title="تعداد پیشنهادات انصرافی" property="tedadPishnehadEnseraf"/>
            <display:column title="تعداد پیشنهادات منتفی" property="tedadPishnehadMontafi"/>

        </display:table>
    </s:if>
    <s:if test="%{gStructureNamayandeList!=null && gStructureNamayandeList.size()==0}">
        رکوردی یافت نشد.
    </s:if>
</div>