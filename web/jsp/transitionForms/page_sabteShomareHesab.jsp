<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
%>
<head>
     <title>ثبت شماره حساب</title>
</head>
    <form class="mainFrame" id="mainForm" method="post" action="/sabteShomareHesab.action?pishnehadId=<%=request.getParameter("pishnehadId")%>&transitionId=<%=request.getParameter("transitionId")%>">
<table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <input type="hidden" name="logmessage" value="<%=request.getParameter("logmessage")%>"/>
    <tbody>
        <tr>
            <td>
                شماره حساب:&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="hesab.shomareHesab"/>
            </td>
            <td>
                نام و نام خانوادگی صاحب حساب:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="hesab.fullName"/>
            </td>
        </tr>
        <tr>
            <td>
                نام بانک:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="hesab.bankName"/>
            </td>
            <td>
                شماره شبا:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="hesab.shabaNumber"/>
            </td>
        </tr>
        <tr>
            <td>
                کد شعبه:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="hesab.branchCode"/>
            </td>
            <td>
                نام شعبه:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="hesab.branchName"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                شماره تماس بیمه گذار:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="hesab.bimegozarPhoneNumber"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                مسئوليت هر گونه اشتباه در اعلام شماره حساب، نام صاحب حساب و ساير مشخصات حساب بانكي متوجه اينجانب، نماينده خواهد بود.
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="تایید" />
            </td>
        </tr>
    </tbody>
</table>
</form>