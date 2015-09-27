<%@ page import="com.bitarts.parsian.model.BankInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form name="form1" method="post" action="/enserafBazgashtBeBimeOmr.action">
    <input type="hidden" name="pishnehad.id" value="<%=request.getParameter("pishnehad.id")%>"/>
</form>

<form name="form1" method="post" action="/pardakhtInternetiMakeFinal.action">
    <input type="hidden" name="pishnehad.id" value="<%=request.getParameter("pishnehad.id")%>"/>
    <input type="hidden" name="bankResponse" value="<%=request.getParameter("rs")%>"/>
    <input type="hidden" name="authorityId" value="<%=request.getParameter("au")%>"/>
    <input type="hidden" name="fish.mablagh" value="<%=request.getParameter("fish.mablagh")%>"/>

<table id="tabletaeed4interneti" dir="rtl" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <tr>
        <td>
            کد رهگیری:
        <b><%=request.getParameter("au")%></b>
    </tr>
    <tr>
        <td>
            <% if (request.getParameter("rs").equalsIgnoreCase("0")){ %>
            با فشردن دکمه ی تایید پرداخت شما تکمیل می گردد.
            <%}else
            {
                System.out.println(
                                    "page_taeedInterneti.jsp_Err_rs_returned: " + request.getParameter("rs") +
                                    " , pishnehadId: " + request.getParameter("pishnehad.id") +
                                    " , authorityId: " + request.getParameter("au"));
            %>
            متاسفانه در پرداخت مشکلی بوجود آمده است. لطفا مجددا سعی نمایید و در وارد کردن اطلاعات خود دقت کنید.
            <%
            }
            %>
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

