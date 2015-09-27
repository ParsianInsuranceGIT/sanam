<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.bimename.Bimename" %>
<%@ page import="com.bitarts.parsian.model.bimename.Darkhast" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<% String arzesheBazkharid = "";
    String daryafteVaam = "";
    String bardashtAzAndukhte = "";
    String andukhteGhati = "";
    Long andukhte=0l;
    arzesheBazkharid = (String) request.getAttribute("arzesheBazkharid");
    andukhte = (Long) request.getAttribute("andukhte");
    andukhteGhati=andukhte.toString();
    if (!arzesheBazkharid.equalsIgnoreCase("")) {
        daryafteVaam = String.valueOf(Math.round((Double.valueOf(arzesheBazkharid) * 9.0) / 10.0));
        bardashtAzAndukhte = String.valueOf(Math.round((Double.valueOf(arzesheBazkharid) * 0.5)));

    }
    Bimename bimename = (Bimename) request.getAttribute("bimename");
%>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="../../css/pishnahadeBimeOmreEnferadi.css"/>
    <script type="text/javascript" src="/jsp/bimename/darkhastBimenameOmreEnferadi.js"></script>
    <title>سیستم درخواست</title>
</head>
<body>
<p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
    <label style="float:right;">شماره بيمه‌نامه: ${bimename.shomare}</label>
    <label style="float:right;margin-right:210px">نام
        بيمه‌گذار:${bimename.pishnehad.bimeGozar.shakhs.name} ${bimename.pishnehad.bimeGozar.shakhs.nameKhanevadegi}</label>
    <label style="float:left;">وضعیت بیمه نامه:${bimename.state.stateName}</label>
</p>

<p style="direction:rtl;text-align:right;">
    <%--در این بخش شما می توانید درخواست بازخرید بیمه نامه خود را ایجاد نمایید. لطفا با دقت مواردی که از شما خواسته شده است را پاسخ دهید.--%>
</p>

<div id="tabcontainer">
    <div id="tabs">
        <a href='javascript:void(0);' type="button" id="tab_1" class="tabsbtn activesubmit">انتخاب نوع بهره‌مندي از
            منافع</a>
    </div>
    <div id="tabformcontent">
        <div class=content id="content_1">
            <%--<p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">--%>
            <%--انتخاب نوع بهره‌مندي از منافع--%>
            <%--</p>--%>
            <form id="formforsecondtyperequest" action="/prepareToMakeRequest2" method="post">
                <input type="hidden" name="bimename.id" value="${bimename.id}">
                <table class="mystrippedtable" dir="rtl" cellpadding="2px" width="60%" cellspacing="0px"
                       style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
                    <tr class="odd">
                        <td>
                            <input type="radio" id="radio_vam" name="darkhast.darkhastType"
                                   value="<%=Darkhast.DarkhastType.VAM%>"
                                   onclick="ajaxlyCheckForValidBahremandi(<%=arzesheBazkharid%>,'VAM');"
                                   />
                        </td>
                        <td colspan="5">
                            <label style="float:right;margin-top:5px;">دريافت وام به اعتباراندوخته بيمه‌نامه به مبلغ
                                حداكثر</label>
                            <input style="float:right;margin-right:2px;" type="text" class="digitSeparators" name="hadeAksarVam"
                                   readonly="readonly" id="vam_the_arzesh" value="">
                            <label style="float:right;margin-top:5px;margin-right:2px;">ریال</label>
                        </td>
                    </tr>
                    <tr class="odd">
                        <td>
                            <input type="radio" name="darkhast.darkhastType"
                                   value="<%=Darkhast.DarkhastType.TASVIE_PISH_AZ_MOEDE_VAM%>"
                                   onclick="ajaxlyCheckForValidBahremandi(<%=arzesheBazkharid%>,'TASVIE_PISH_AZ_MOEDE_VAM');"
                                    disabled="disabled"/>
                        </td>
                        <td colspan="5">
                            <label style="float:right;">تسويه وام به صورت پيش از موعد</label>
                        </td>
                    </tr>
                    <tr class="odd">
                        <td>
                            <input type="radio" name="darkhast.darkhastType"
                                   value="<%=Darkhast.DarkhastType.BARDASHT_AZ_ANDOKHTE%>"
                                   onclick="ajaxlyCheckForValidBahremandi(<%=andukhteGhati%>,'BARDASHT_AZ_ANDOKHTE');"
                                    disabled="disabled"/>
                        </td>
                        <td colspan="5">
                            <label style="float:right;margin-top:5px;">برداشت از اندوخته بيمه‌نامه به مبلغ
                                حداكثر</label>
                            <input style="float:right;margin-right:2px;" type="text" name="" class="digitSeparators"
                                   readonly="readonly" id="bardasht_the_arzesh" value="-">
                            <label style="float:right;margin-top:5px;margin-right:2px;">ریال</label>
                        </td>
                    </tr>
                    <tr class="odd">
                        <td>
                            <input type="radio" name="darkhast.darkhastType" value="<%=Darkhast.DarkhastType.VAM%>"
                                   disabled="disabled"/>
                        </td>
                        <td colspan="5">
                            <label style="float:right;">واريز به اندوخته</label>
                        </td>
                    </tr>
                    <tr class="odd">
                        <td>
                            <input type="radio" name="darkhast.darkhastType"
                                   value="<%=Darkhast.DarkhastType.BAZKHARID%>"
                                   onclick="ajaxlyCheckForValidBahremandi(<%=arzesheBazkharid%>,'BAZKHARID');" />
                        </td>
                        <td colspan="5">
                            <label style="float:right;margin-top:5px;"> بازخريد بيمه‌نامه به ارزش بازخريدي
                                حداكثر</label>
                            <input style="float:right;margin-right:2px;" type="text" name="mablagh"
                                   class="digitSeparators" readonly="readonly" id="bazkharid_the_arzesh" value="-">
                            <label style="float:right;margin-top:5px;margin-right:2px;">ریال</label>
                        </td>
                    </tr>
                    <tr class="odd">
                        <%
                            String tarikhShorou = bimename.getTarikhShorou();
                            String after60 = DateUtil.addMonth(tarikhShorou, 2);
                            String after30 = DateUtil.addMonth(tarikhShorou, 1);
                            if (!DateUtil.isGreaterThan(after30, DateUtil.getCurrentDate())) {
                        %>
                        <input type="hidden" id="ebtal_warning"
                               value="ابطال بيمه نامه بعد از گذشت 30 روز از تاريخ شروع بيمه نامه مشروط به تاييد اداره خدمات پس از صدور مي باشد."/>
                        <%
                            }
//                            if (!DateUtil.isGreaterThan(after60, DateUtil.getCurrentDate())) {
                        %>

                        <td>
                            <input type="radio" id="ebt_radio" name="darkhast.darkhastType" value="<%=Darkhast.DarkhastType.EBTAL%>"
                                  onclick="ajaxlyCheckForValidBahremandi(<%=arzesheBazkharid%>,'EBTAL');" checked="checked"/>
                        </td>

                        <td colspan="5">
                            <label style="float:right;">ابطال بيمه‌نامه</label>
                        </td>
                    </tr>
                </table>
                <br/>

                <div style="clear: both;"/>
                <input type="hidden" id="thetargetvalue" value=""/>

                <div id="kadre_sharayete_bahremandi"
                     style="display:none; margin: 0 auto; background: #BEDFF7; width: 60%;">

                </div>
                <%--<div align="center" style="width:60%;margin:0 auto;">--%>
                <%--<input type="submit" value="ثبت درخواست" >--%>
                <%--</div>--%>
            </form>
        </div>
    </div>
</div>
<div style="height:30px;">
    <input type="button" onclick="window.location='/loginUser'" value="بازگشت" style="float:left;margin:2px"/>
</div>
<br class="clear"/>
<script type="text/javascript">
    $(document).ready(function () {
        $("#ebt_radio").click();
    });
    function submbitSecondTypeDarkhast() {
        var mablaghDarkhasti = $("#mablagh_darkhasti").val();
        var mablaghMojaz = $("#thetargetvalue").val();
        var namayande = false;
    <sec:authorize ifAllGranted="ROLE_NAMAYANDE">
        namayande = true;
    </sec:authorize>
        if (parseInt(mablaghDarkhasti) > parseInt(mablaghMojaz)) {
            alertMessage("مبلغ درخواستی شما از سقف مجاز بالاتر است. لطف مبلغ درخواستی خود را مجددا و با دقت بیشتری وارد نمایید.");
//        } else if (parseInt($("#bazkharid_the_arzesh").val()) <= 0 && $("#darkhast_type").val() == "BAZKHARID") {
//            alertMessage("ارزش بازخریدی نامعتبر است.");
        } else if (namayande && mablaghMojaz > 20000000 && $("#bimenameIsMafqud").attr('checked') == true) {
            alertMessage("لطفا براي ادامه مراحل انجام بازخريدي بيمه نامه و احراز هويت به همراه دو آگهي درج شده در رونامه هاي كثيرالانتشار (توضيحات) و كارت شناسايي معتبر به مجتمع يا باجه هاي بيمه اي مراجعه نماييد.");
        } else {
            if ($("#darkhast_type").val() == "BAZKHARID" || $("#darkhast_type").val() == "EBTAL") {
                alertMessage("در صورتي كه مدارك شما ظرف 20 روز آينده به دست اداره خدمات پس از صدور نرسد، درخواست شما منقضي شده و باطل مي شود.");
            }
            if ($("#darkhast_type").val() == "EBTAL") {
                alertMessage("در صورت انجام آزمايش پزشكي توسط بيمه شده، هزينه آزمايشات از مبلغ پرداختي به بيمه گذاركسر خواهد شد.");
            }
            $("#formforsecondtyperequest").submit();
        }
    }
    $(document).ready(function(){ajaxlyCheckForValidBahremandi(<%=arzesheBazkharid%>,'EBTAL');});
    function ajaxlyCheckForValidBahremandi(arzesh, type) {
//        $("#kadre_sharayete_bahremandi").hide();

        $.post("/ajaxlyCheckForValidBahremandi?type=" + type + "&arzesheBazkharid=" + arzesh + "&bimename.id=${bimename.id}", function (msg) {
            $("#kadre_sharayete_bahremandi").fadeIn(1000);
            $("#kadre_sharayete_bahremandi").html(msg);
            $("#thetargetvalue").val($("#result_arzesh").val());
            if ($("#darkhast_type").val() == "VAM") {
                $("#vam_the_arzesh").val($("#result_arzesh").val());
            }
            if ($("#darkhast_type").val() == "BARDASHT_AZ_ANDOKHTE") {
                $("#bardasht_the_arzesh").val($("#result_arzesh").val());
            }
            if (type == 'EBTAL') {
                $("#kadre_sharayete_bahremani").html($("#ebtal_warning").val());
            }
            if (type == 'BAZKHARID') {
                $("#bazkharid_the_arzesh").val($("#result_arzesh").val());
            }
            if (type = 'TASVIE_PISH_AZ_MOEDE_VAM') {
                $("#kadre_sharayete_bahremani").html($("#res_tasviye_vam").html());
            }
            $(".digitSeparators").each(function () {
                $(this).keyup();
            });
        });
    }
</script>
</body>
</html>
