<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>

</head>
<body>
<s:if test="constantsList.size()>0">
    <table class="jtable" border="1" width = "100%">
        <tr>
            <th width="16%">نام</th>
            <th width="16%">متغییر</th>
            <th width="16%">مقدار</th>
            <th width="16%">زمان تاثیر</th>
            <th width="16%">نحوه تاثیر</th>
            <th width="16%">طرح</th>
        </tr>
        <s:iterator value="constantsList" status="stat">
            <tr>
                <td width="16%"><s:property value="%{farsiName(name)}"/></td>
                <td width="16%"><s:property value="%{farsiName2(name2)}"/></td>
                <td width="16%" style="word-break: break-all;"><s:property value="value"/></td>
                <td width="16%"><s:property value="dateEffect"/></td>
                <td width="16%"><s:property value="%{farsiStyle(applyStyle)}"/></td>
                <td width="16%"><s:property value="tarh.name"/></td>
            </tr>
        </s:iterator>
    </table>
</s:if><s:else>
    رکوردی یافت نشد
</s:else>
<input type="button" value="اضافه" onclick="window.location='/prepareAddConstant'"/>
<input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
</body>
</html>