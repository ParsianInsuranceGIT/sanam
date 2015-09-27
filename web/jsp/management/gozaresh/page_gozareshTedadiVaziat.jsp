<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>
    گزارش تعدادی وضعیت پیشنهاد سیستم
</title>

<div align="center">
    <form action="/gozareshTedadiVaziatPishnehad" method="post">
        <table>
            <tr>
                <td>
                    انتخاب نقش:
                </td>
                <td>
                    <s:i18n name="com.bitarts.parsian.i18n.enumTypes_fa">
                        <s:select list="roleList" listValue="getText(roleName)" listKey="id" name="roleId"
                                  theme="simple"
                                  headerKey="-1" headerValue="-"/>
                    </s:i18n>
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
    <s:if test="%{gStructureList!=null && gStructureList.size()>0}">
        <table class="jtable">
            <tr>
                <th>وضعیت</th>
                <th>تعداد پیشنهاد</th>
                <th>درصد از کل</th>
                <th>مربوط به نقش</th>
            </tr>
            <s:iterator value="gStructureList" status="stat">
                <tr>
                    <td><s:property value="vaziat"/></td>
                    <td><s:property value="tedadPishnehad"/></td>
                    <td><s:property value="darsadKol"/></td>
                    <td style="background: <s:property value="color"/>">
                <s:i18n name="com.bitarts.parsian.i18n.enumTypes_fa">
                    <s:property value="getText(naghsh)"/>
                </s:i18n>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </s:if>
    <s:if test="%{gStructureList!=null && gStructureList.size()==0}">
        رکوردی یافت نشد.
    </s:if>
</div>