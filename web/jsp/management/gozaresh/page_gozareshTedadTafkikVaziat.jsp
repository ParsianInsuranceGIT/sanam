<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>
    گزارش تعدادی پیشنهادات به تفکیک وضعیت
</title>

<div align="center">
    <form action="/gozareshTedadPishnehadTafkikVaziat" method="post">
        <table>
            <tr>
                <td>
                    انتخاب وضعیت:
                </td>
                <td>
                    <s:select list="stateList" listValue="stateName" listKey="id" name="stateId" theme="simple"
                              headerKey="-1" headerValue="-"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="جستجو">
                    <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'"
                           value="بازگشت">
                </td>
            </tr>
        </table>
    </form>
    <s:if test="%{gStructureTafkikVaziatList!=null && gStructureTafkikVaziatList.size()>0}">
        <table class="jtable">
            <s:iterator value="gStructureTafkikVaziatList" var="gstv" status="stat">
                <tr>
                    <th colspan="2">
                        <s:property value="key"/>
                    </th>
                </tr>
                <tr>
                    <th>نام کاربری</th>
                    <th>تعداد</th>
                </tr>
                <s:iterator value="%{#gstv.data.keySet()}" var="data">
                    <tr>
                        <td>
                            <s:property value="%{#data}"/>
                        </td>
                        <td style="min-width: 100px">
                            <s:property value="%{#gstv.data.get(#data)}"/>
                        </td>
                    </tr>
                </s:iterator>
            </s:iterator>
        </table>
    </s:if>
    <s:if test="%{gStructureTafkikVaziatList!=null && gStructureTafkikVaziatList.size()==0}">
        رکوردی یافت نشد.
    </s:if>
</div>