<%@ page import="com.bitarts.parsian.model.pishnahad.Pishnehad" %>
<%@ page import="java.util.Random" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Pishnehad pishnehadRun = (Pishnehad) request.getAttribute("pishnehadRun");
%>

<c:set var="pishnehad" value="<%=pishnehadRun%>"/>

<table id="table4viewelamshomarehesab" dir="rtl" cellpadding="0" class="mystrippedtable" cellspacing="0" style="width:88%;border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <tr class="headline1">
        <td colspan="6">
            اطلاعات بانکی
        </td>
    </tr>
    <tr class="odd">
        <td colspan="6">
            خواهشمند است مبلغ پيش پرداخت به شماره حساب بانكي &nbsp;&nbsp;<input readonly="readonly" type="text" name="elameHesab.shomareHesabBanki" value="${pishnehad.elameHesab.shomareHesabBanki}"/>&nbsp;به شماره شـِبا٭&nbsp;&nbsp;<input type="text" name="elameHesab.shomareShaba" value="${pishnehad.elameHesab.shomareShaba}"/>&nbsp;نزد بانک&nbsp;&nbsp;
            <input type="text" readonly="readonly" name="elameHesab.bankName" value="${pishnehad.elameHesab.bankName}"/>&nbsp;شعبه&nbsp;&nbsp;<input readonly="readonly" type="text" name="elameHesab.shobeName" value="${pishnehad.elameHesab.shobeName}"/>&nbsp;(کد شعبه &nbsp;&nbsp;<input readonly="readonly" type="text" name="elameHesab.shobeKod" value="${pishnehad.elameHesab.shobeKod}"/> )&nbsp;به نام آقای/خانم&nbsp;&nbsp;<input type="text" readonly="readonly" name="elameHesab.sahebHesab" value="${pishnehad.elameHesab.sahebHesab}"/>&nbsp;واریز گردد
            .*
            <p style="color:red">
                ٭ درج شماره حساب بانكي ايران (شِبا)الزاميمي‌باشد.
                <br/>
                ٭مسئوليت هرگونه اشتباه در اعلام شماره حساب، ‌نام صاحب حساب و ساير مشخصات حساب بانكي جهت واريز مبلغ فوق‌الذكر، متوجه بيمه‌گذار خواهد بود.

            </p>
        </td>
    </tr>
</table>