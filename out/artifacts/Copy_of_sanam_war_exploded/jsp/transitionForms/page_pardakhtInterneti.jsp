<%@ page import="com.bitarts.parsian.model.pishnahad.Pishnehad" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Integer pishnehadId = (Integer) request.getAttribute("pishnehadId");
    String logmessage = (String) request.getAttribute("logmessage");
    Integer transitionId = (Integer) request.getAttribute("transitionId");
%>
<head>
     <title>پرداخت اینترنتی</title>
</head>
<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
    صفحه تکمیل پیش پرداخت
    </p>

    <div class="staticTitleBar" id="result" style="direction:rtl;padding: 5px 45px 5px 5px;">
        <form id="form1" name="form1" method="post" action="/pardakhtInterneti.action">
        <table>
            <colgroup>
                <col />
                <col />
                <col />
                <col />
            </colgroup>
            <tr>
                <td>OrderId</td>
                <td>
                    <input type="text" id="txtOrderId" name="txtOrderId"/>
                </td>
            </tr>
            <tr>
                <td>Amount:</td>
                <td>
                    <input type="text" id="txtAmount" name="txtAmount"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="پرداخت"/>
                </td>
            </tr>
        </table>
        </form>
        <form action="makeTransition.action" id="log_message" method="post" accept-charset="UTF-8">
            <input type="hidden" name="pishnehadId" value="<%=pishnehadId%>"/>
            <input type="hidden" name="transitionId" value="<%=transitionId%>"/>
            <input type="hidden" name="logmessage" id="<%=logmessage%>"/>
            <input type="submit" value="بازگشت"/>
        </form>
    </div>
</div>