<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/js/jquery.validationEngine.js"></script>
<%--<s:property value="error"/>--%>
<%--<s:property value="message"/>--%>
<s:actionerror/>
<s:actionmessage/>
<html>
<head></head>
<body>
<c:if test="${etebarList!=null && fn:length(etebarList)>0}">
    <p>---------------------------------------Etebar----------------------------------------------</p>
    <table>

            <tr style="border-style: dashed;">
                <td style="border-style: solid;">id</td>
                <td style="border-style: solid;">amount_long</td>
                <td style="border-style: solid;">remianing_amount</td>
                <td style="border-style: solid;">identifier</td>
                <td style="border-style: solid;">created_date</td>
                <td style="border-style: solid;">status</td>
                <td style="border-style: solid;">credebit_type</td>
            </tr>
            <s:iterator value="etebarList" id="row" status="stat">
                <tr style="border-style: solid;">
                    <td style="border-style: solid;"><s:property value="#row.getId()"/></td>
                    <td style="border-style: solid;"><s:property value="#row.getAmount_long()"/></td>
                    <td style="border-style: solid;"><s:property value="#row.getRemainingAmount_long()"/></td>
                    <td style="border-style: solid;"><s:property value="#row.getIdentifier()"/></td>
                    <td style="border-style: solid;"><s:property value="#row.getCreatedDate()"/></td>
                    <td style="border-style: solid;"><s:property value="#row.getStatus()"/></td>
                    <td style="border-style: solid;"><s:property value="#row.getCredebitType()"/></td>
                </tr>
            </s:iterator>
    </table>
    <p>---------------------------------------Bedehi----------------------------------------------</p>
    <table>

        <tr style="border-style: solid;">
            <td  style="border-style: solid;">id</td>
            <td  style="border-style: solid;">amount_long</td>
            <td  style="border-style: solid;">remianing_amount</td>
            <td  style="border-style: solid;">identifier</td>
            <td  style="border-style: solid;">sarresid_date</td>
            <td  style="border-style: solid;">status</td>
            <td style="border-style: solid;">credebit_type</td>
        </tr>
        <s:iterator value="bedehiList" id="row1" status="stat">
            <tr style="border-style: solid;">
                <td style="border-style: solid;"><s:property value="#row1.getId()"/></td>
                <td style="border-style: solid;"><s:property value="#row1.getAmount_long()"/></td>
                <td style="border-style: solid;"><s:property value="#row1.getRemainingAmount_long()"/></td>
                <td style="border-style: solid;"><s:property value="#row1.getIdentifier()"/></td>
                <td style="border-style: solid;"><s:property value="#row1.getGhest().getSarresidDate()"/></td>
                <td style="border-style: solid;"><s:property value="#row1.getStatus()"/></td>
                <td style="border-style: solid;"><s:property value="#row1.getCredebitType()"/></td>
            </tr>
        </s:iterator>
    </table>
</c:if>
</body>
</html>