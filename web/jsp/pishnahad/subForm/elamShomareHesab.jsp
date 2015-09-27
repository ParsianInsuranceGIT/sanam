<%@ page import="com.bitarts.parsian.model.pishnahad.Pishnehad" %>
<%@ page import="java.util.Random" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Pishnehad pishnehadRun = (Pishnehad) request.getAttribute("pishnehadRun");
%>

<c:set var="pishnehad" value="<%=pishnehadRun%>"/>
<div id="elamshomarehesabdiv">
<form id="elameShomareHesab" name="elameShomareHesabForm" method="post" action="/elameShomareHesab.action">
    <input type="hidden" name="pishnehad.id" value="${pishnehad.id}"/>
    <input type="hidden" name="transitionId" value="65"/>
    <input type="hidden" name="logmessage" value="test"/>
<table id="table4elamshomarehesab" dir="rtl" cellpadding="0" class="mystrippedtable" cellspacing="0" style="width:88%;border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <tr class="headline">
        <td colspan="2">کد رهگیری: &nbsp;&nbsp;
            <%Random rand = new Random();
                int randi = rand.nextInt(9999999);
                while (randi < 1000000){
                    randi = rand.nextInt(9999999);
                }
            %>

            <%=randi%>
        </td>
        <td colspan="2">شماره پیشنهاد:&nbsp;&nbsp;
            <%--<%=pishnehadRun.getBimeGozar().getShakhs().getNameKhanevadegi()%>--%>
            ${pishnehad.radif}
        </td>
        <td colspan="2">تاریخ ایجاد:&nbsp;&nbsp;
            ${pishnehad.createdDate}
        </td>
    </tr>
    <tr class="headline1">
        <td colspan="6">
            اطلاعات بیمه گذار
        </td>
    </tr>
    <tr class="even">
        <td>نام:</td>
        <td colspan="2">${pishnehad.bimeGozar.shakhs.name}</td>
        <td>نام خانوادگی:</td>
        <td colspan="2">${pishnehad.bimeGozar.shakhs.nameKhanevadegi}</td>
    </tr>
    <tr class="odd">
        <td>نام پدر:</td>
        <td colspan="5">${pishnehad.bimeGozar.shakhs.namePedar}</td>
    </tr>
    <tr class="even">
        <td>کد ملی:</td>
        <td colspan="2">${pishnehad.bimeGozar.shakhs.kodeMelliShenasayi}</td>
        <td>شماره شناسنامه:</td>
        <td colspan="2">${pishnehad.bimeGozar.shakhs.shomareShenasnameh}</td>
    </tr>
    <tr class="odd">
        <td>تاریخ تولد:</td>
        <td colspan="5">${pishnehad.bimeGozar.shakhs.tarikheTavallod}</td>
    </tr>
    <tr class="even">
        <td>تلفن ثابت:</td>
        <td colspan="2">${pishnehad.addressBimeGozar.telephoneManzel}</td>
        <td>تلفن همراه:</td>
        <td colspan="2">${pishnehad.addressBimeGozar.telephoneHamrah}</td>
    </tr>
    <tr class="odd">
        <td>نسبت با بیمه شده:</td>
        <td colspan="5">${pishnehad.bimeGozar.nesbatBabimeShode}</td>
    </tr>
    <tr class="headline1">
        <td colspan="6">
            اطلاعات بیمه شده
        </td>
    </tr>
    <tr class="even">
        <td>نام:</td>
        <td colspan="2">${pishnehad.bimeShode.shakhs.name}</td>
        <td>نام خانوادگی:</td>
        <td colspan="2">${pishnehad.bimeShode.shakhs.nameKhanevadegi}</td>
    </tr>
    <tr class="odd">
        <td>نام پدر:</td>
        <td colspan="5">${pishnehad.bimeShode.shakhs.namePedar}</td>
    </tr>
    <tr class="even">
        <td>کد ملی:</td>
        <td colspan="2">${pishnehad.bimeShode.shakhs.kodeMelliShenasayi}</td>
        <td>شماره شناسنامه:</td>
        <td colspan="2">${pishnehad.bimeShode.shakhs.shomareShenasnameh}</td>
    </tr>
    <tr class="odd">
        <td>تاریخ تولد:</td>
        <td colspan="5">${pishnehad.bimeShode.shakhs.tarikheTavallod}</td>
    </tr>
    <tr class="even">
        <td>تلفن ثابت:</td>
        <td colspan="2">${pishnehad.addressBimeShode.telephoneManzel}</td>
        <td>تلفن همراه:</td>
        <td colspan="2">${pishnehad.addressBimeShode.telephoneHamrah}</td>
    </tr>
    <tr class="headline1">
        <td colspan="6">
            اطلاعات بانکی
        </td>
    </tr>
    <tr class="odd">
        <td colspan="6">
            خواهشمند است مبلغ پيش پرداخت به شماره حساب بانكي &nbsp;&nbsp;<input type="text" name="elameHesab.shomareHesabBanki" value=""/>&nbsp;به شماره شـِبا٭&nbsp;&nbsp;<input type="text" id="shomare_shaba" name="elameHesab.shomareShaba" value=""/>IR  &nbsp;نزد بانک&nbsp;&nbsp;
            <input type="text" name="elameHesab.bankName"/>&nbsp;شعبه&nbsp;&nbsp;<input type="text" name="elameHesab.shobeName"/>&nbsp;(کد شعبه &nbsp;&nbsp;<input type="text" name="elameHesab.shobeKod"/> )&nbsp;به نام آقای/خانم&nbsp;&nbsp;<input type="text" name="elameHesab.sahebHesab"/>&nbsp;واریز گردد.*
            <p style="color:red">
                ٭ درج شماره حساب بانكي ايران (شِبا)الزامي مي‌باشد.
                <br/>
                ٭مسئوليت هرگونه اشتباه در اعلام شماره حساب، ‌نام صاحب حساب و ساير مشخصات حساب بانكي جهت واريز مبلغ فوق‌الذكر، متوجه بيمه‌گذار خواهد بود.

            </p>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="button" value="چاپ فرم اعلام شماره حساب" onclick="saveElameHesabAjaxly();"/>
        </td>
    </tr>
</table>
</form>
</div>

<script type="text/javascript">
    function saveElameHesabAjaxly(){
        var data = $("#elameShomareHesab").serialize();
        $.post('/elameShomareHesab.action', data, function() {
            window.open('/printeElameShomarehesab.action?pishnehadReport.pishnehad.id=${pishnehad.id}');
        });

    }
</script>