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
        <form id="form1" name="form1" method="post" action="/pardakhtInternetiMakeFinal.action">
        <table>
            <tr>
                <td colspan="2">
                    <% if (request.getParameter("rs").equalsIgnoreCase("0")){ %>
                        پاسخ بانک موفقیت آمیز بود برای نهایی کردن پرداخت دکمه ی تایید را بفشارید.
                    <%}else{%>
                    متاسفانه مشکلی در پرداخت بوجود آمده است. مجددا سعی کنید و در وارد کردن اطلاعات خود دقت کنید.
                    <%}%>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="تایید"/>
                </td>
            </tr>
        </table>
        </form>
    </div>
</div>