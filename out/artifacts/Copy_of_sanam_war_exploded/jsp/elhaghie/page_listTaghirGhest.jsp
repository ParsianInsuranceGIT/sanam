<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<div align="center">
    <table>

        <s:if test="logGhest.preCredebitList.size()>0">
            <tr>
                <td>قبل از تغییرات</td>
            </tr>
            <tr>
                <display:table name="logGhest.preCredebitList">
                    <display:column title="تاریخ سررسید" property="sarresidDate"/>
                    <display:column title="مقدار" property="amount"/>
                    <display:column title="شناسه مشتری" property="shomareMoshtari"/>
                    <display:column title="شناسه قسط" property="shenaseCredebit"/>
                </display:table>
            </tr>
        </s:if>
        <s:if test="logGhest.afterCredebitList.size()>0">
            <tr>
                <td>بعد از تغییرات</td>
            </tr>
            <tr>
                <display:table name="logGhest.afterCredebitList">
                    <display:column title="تاریخ سررسید" property="sarresidDate"/>
                    <display:column title="مقدار" property="amount"/>
                    <display:column title="شناسه مشتری" property="shomareMoshtari"/>
                    <display:column title="شناسه قسط" property="shenaseCredebit"/>
                </display:table>
            </tr>
        </s:if>
        <s:if test="logGhest==null || (logGhest.afterCredebitList.size()==0 && logGhest.afterCredebitList.size()==0)">
            <tr>
                <td>تغییری در اقساط ایجاد نشده است</td>
            </tr>
        </s:if>
    </table>
</div>