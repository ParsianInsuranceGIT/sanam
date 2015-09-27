<%@ page import="com.bitarts.parsian.model.BankInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%--<form name="form1" method="post" action="/enserafBazgashtBeBimeOmr.action">--%>
    <%--<input type="hidden" name="credebit.id" value="<%=request.getParameter("credebit.id")%>"/>--%>
<%--</form>--%>

<form name="form1" method="post" action="/ePaymentMakeFinal.action">
    <input type="hidden" name="credebit.id" value="<%=request.getParameter("credebit.id")%>"/>
    <input type="hidden" name="bankResponse" value="<%=request.getParameter("rs")%>"/>
    <input type="hidden" name="authorityId" value="<%=request.getParameter("au")%>"/>

<table id="tabletaeed4interneti" dir="rtl" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <tr>
        <td>
            <% if (request.getParameter("rs").equalsIgnoreCase("0")){ %>
            با فشردن دکمه ی تایید پرداخت شما تکمیل می گردد.  در صورت انصراف از پرداخت می توانید دکمه بازگشت را بفشارید.
            <%}else{%>
            متاسفانه در پردخات مشکلی بوجود آمده است. لطفا مجددا سعی نمایید و در وارد کردن اطلاعات خود دقت کنید.
            <%}%>
        </td>
    </tr>
    <tr>
        <% if (request.getParameter("rs").equalsIgnoreCase("0")){ %>
        <td>
            <input type="submit" value="تایید نهایی پرداخت"/>
        </td>
        <%}%>
    </tr>
</table>
</form>

