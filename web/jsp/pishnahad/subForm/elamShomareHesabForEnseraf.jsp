<%@ page import="com.bitarts.parsian.model.pishnahad.Pishnehad" %>
<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.pishnahad.Fish" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Pishnehad pishnehadRun = (Pishnehad) request.getAttribute("pishnehadRun");
%>

<c:set var="pishnehad" value="<%=pishnehadRun%>"/>

<form id="elameShomareHesabBarayeEnseraf" name="elameShomareHesabEnserafForm" method="post" action="/elameShomareHesabForEnseraf.action">
    <input type="hidden" name="pishnehad.id" value="${pishnehad.id}"/>
    <input type="hidden" name="transitionId" value="74"/>
    <input type="hidden" id="lgmessage" name="logmessage" value=""/>
<table id="table4elamshomarehesabforenseraf" dir="rtl" cellpadding="0" class="mystrippedtable" cellspacing="0" style="width:88%;border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <tr class="odd">
        <td>
            تاریخ:&nbsp;&nbsp;<b><%=DateUtil.getCurrentDate()%></b><input type="hidden" name="elameHesab.taarikh" value="<%=DateUtil.getCurrentDate()%>"/>
            <input type="hidden" name="elameHesab.id" value="${pishnehad.elameHesab.id}"/>
            <input type="hidden" name="elameHesab.sahebHesab" value="${pishnehad.elameHesab.sahebHesab}"/>
            <input type="hidden" name="elameHesab.shobeKod" value="${pishnehad.elameHesab.shobeKod}"/>
            <br/>
            شماره: &nbsp;&nbsp;<input type="text" class="validate[required]" id="elameenseraf_shomareNameh" name="elameHesab.shomareNameh" value=""/>
            <br/>
            جناب آقای حاتمی
            <br/>
            رئیس محترم اداره ی حسابداری عملیات
            <br/>
            احتراما با توجه به منع صدور بیمه گذار، مقرر فرمایید چک به مبلغ &nbsp;<input type="text" class="validate[required,custom[integer]]" id="elamenseraf_mablagh" name="elameHesab.mablagh" value="${pishnehad.elameHesab.mablagh}"/>&nbsp;&nbsp; در وجه آقای &nbsp;
            <b>${pishnehad.elameHesab.sahebHesab}</b> &nbsp;&nbsp; صادر و به حساب ایشان واریز گردد.
            <br/>
            کد رهگیری: &nbsp;&nbsp;<b>${pishnehad.radif}</b>
            <br/>
            شماره حساب: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="validate[required]" name="elameHesab.shomareHesabBanki" id="elamenseraf_shomarehesab" value="${pishnehad.elameHesab.shomareHesabBanki}"/> &nbsp;&nbsp;
            <br/>
            بانک: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="validate[required]" id="elameenseraf_bankname" name="elameHesab.bankName" value="${pishnehad.elameHesab.bankName}"/>&nbsp;&nbsp;
            <br/>
            نام شعبه:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="validate[required]" id="elameenseraf_shobename" name="elameHesab.shobeName" value="${pishnehad.elameHesab.shobeName}"/>
            <br/>
            شبا: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="validate[required,funcCall[shaba]]" name="elameHesab.shomareShaba" id="elamenseraf_shomaresahba" value="${pishnehad.elameHesab.shomareShaba}"/>IR
            <br/>
            مبلغ پیش پرداخت:&nbsp;&nbsp;
            <%
                List<Fish> fishs = pishnehadRun.getFishs();
                int majmu = 0;
                if(fishs!=null){
                    for (Fish fish : fishs) {
                        if(fish.getFound().equalsIgnoreCase("true")){
                            majmu +=Integer.valueOf(fish.getMablagh().replaceAll(",",""));
                        }
                    }
                }
            %>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="mablagh_pishpardakht" class="validate[required,custom[integer]]" value="<%=majmu%>" name="elameHesab.majmuPishpardakht"/>
            <br/>
            مبلغ هزینه های پزشکی:&nbsp;&nbsp;<input id="mablagh_pezeshki" class="validate[required,custom[integer]]" type="text" name="elameHesab.majmuHazinePezeshki"/>
            <br/>
            <br/>
            <input type="button" value="چاپ فرم" onclick="uploadGhableElamBeMali();"/>
        </td>
    </tr>
</table>
</form>

<script type="text/javascript">

    function uploadGhableElamBeMali(){
        setMablagheCheck();
        $.validationEngine.closePrompt($("#elameShomareHesabBarayeEnseraf"));
        saveElameHesabForEnserafAjaxly();
//        $('.tabsbtn').removeClass('activesubmit');
//        $('#tab_12').addClass('activesubmit');
//        $('.content').hide();
//        $('#content_12').show();

//        openDialogBox("lgmessage");
//        $("#elameShomareHesabBarayeEnseraf").submit();

    }
    function saveElameHesabForEnserafAjaxly(){
        var data = $("#elameShomareHesabBarayeEnseraf").serialize();
        $.post('/elameShomareHesabForEnseraf.action', data, function() {
            window.open('/printeElameShomarehesab_NameBeMali.action?pishnehadReport.pishnehad.id=${pishnehad.id}');
        });

    }
    function setMablagheCheck(){
        var pish = $("#mablagh_pishpardakht").val();
        var pezeshki = $("#mablagh_pezeshki").val();
        var mablagh = parseInt(pish.replace(new RegExp(",","gm"),"")) - parseInt(pezeshki.replace(new RegExp(",","gm"),""));
        $("#elamenseraf_mablagh").val(mablagh);

    }
</script>