<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.service.calculations.Reports.FRsMultiple" %>
<%@ page import="com.bitarts.parsian.action.EstelamAction" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: Feb 22, 2011
  Time: 2:59:03 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    boolean maghadireManfi = false;
    List<FRsMultiple> fRsMultipleList = (List<FRsMultiple>) request.getAttribute("fRsMultipleList");
    if (!((Boolean) request.getAttribute("correctCalculation"))) {

%>
Error:ExtaWrongFieldsValue
<%
} else {
    System.out.println(fRsMultipleList.size());
    for (FRsMultiple fRsMultiple : fRsMultipleList) {
        fRsMultiple.setSarmayeFot(EstelamAction.roundArray(fRsMultiple.getSarmayeFot()));
        fRsMultiple.setSarmayeHadese(EstelamAction.roundArray(fRsMultiple.getSarmayeHadese()));
        fRsMultiple.setSarmayeAmraz(EstelamAction.roundArray(fRsMultiple.getSarmayeAmraz()));
        fRsMultiple.setSarmayeNaghseOzv(EstelamAction.roundArray(fRsMultiple.getSarmayeNaghseOzv()));
        fRsMultiple.setHaghBimePusheshHayeEzafe(Math.round(fRsMultiple.getHaghBimePusheshHayeEzafe()));
        fRsMultiple.setMaliyatBarArzeshAfzude(Math.round(fRsMultiple.getMaliyatBarArzeshAfzude()));
        fRsMultiple.setMajmuHaghBimePardakhti(Math.round(fRsMultiple.getMajmuHaghBimePardakhti()));
        fRsMultiple.setSarmayeMohasebatiMoafiatFot(Math.round(fRsMultiple.getSarmayeMohasebatiMoafiatFot()));
        fRsMultiple.setHaghBime(Math.round(fRsMultiple.getHaghBime()));
        fRsMultiple.setMajmuKoleMabaleghePardakhti(Math.round(fRsMultiple.getMajmuKoleMabaleghePardakhti()));
        fRsMultiple.setArzeshBazkharidBaSud15Darsad(Math.round(fRsMultiple.getArzeshBazkharidBaSud15Darsad()));
        fRsMultiple.setArzeshBazkharidBaSud20Darsad(Math.round(fRsMultiple.getArzeshBazkharidBaSud20Darsad()));
        fRsMultiple.setArzeshBazkharidBaSud25Darsad(Math.round(fRsMultiple.getArzeshBazkharidBaSud25Darsad()));


        if (EstelamAction.hasManfiVal(fRsMultiple.getSen())) {
            maghadireManfi = true;
            break;
        }
        if (EstelamAction.hasManfiVal(fRsMultiple.getSarmayeFot())) {
            maghadireManfi = true;
            break;
        }
        if (EstelamAction.hasManfiVal(fRsMultiple.getSarmayeHadese())) {
            maghadireManfi = true;
            break;
        }
        if (EstelamAction.hasManfiVal(fRsMultiple.getSarmayeAmraz())) {
            maghadireManfi = true;
            break;
        }
        if (EstelamAction.hasManfiVal(fRsMultiple.getSarmayeNaghseOzv())) {
            maghadireManfi = true;
            break;
        }
        if (fRsMultiple.getHaghBimePusheshHayeEzafe() < 0.0) {
            maghadireManfi = true;
            break;
        }
        if (fRsMultiple.getHaghBime() < 0.0) {
            maghadireManfi = true;
            break;
        }
        if (fRsMultiple.getMaliyatBarArzeshAfzude() < 0.0) {
            maghadireManfi = true;
            break;
        }
        if (fRsMultiple.getMajmuHaghBimePardakhti() < 0.0) {
            maghadireManfi = true;
            break;
        }
        if (fRsMultiple.getMajmuKoleMabaleghePardakhti() < 0.0) {
            maghadireManfi = true;
            break;
        }
        if (fRsMultiple.getArzeshBazkharidBaSud15Darsad() < 0.0) {
            maghadireManfi = true;
            break;
        }
        if (fRsMultiple.getArzeshBazkharidBaSud20Darsad() < 0.0) {
            maghadireManfi = true;
            break;
        }
        if (fRsMultiple.getArzeshBazkharidBaSud25Darsad() < 0.0) {
            maghadireManfi = true;
            break;
        }
    }
    request.setAttribute("fRsListView", fRsMultipleList);
    if (maghadireManfi) {
%>
Error:WrongFieldsValue
<%} else {%>
<script type="text/javascript">
    var click = 0;
    $(".prt").bind('click', function(e) {
        if(click > 0) return false;
        click++;
        alertMessageByLock("جهت وضوح بيشتر چاپ محاسبات رياضي تنظيمات چاپگر خود را به Landscape تغيير دهيد.", function(){
            prtPrint();
        });
        return true;
    });
    function prtPrint(){
        var srvr_date = '<%=DateUtil.getCurrentDate()%>';
        $('#Taarikh_akhz_gozaresh').html(srvr_date.split('/')[0] + '/' + srvr_date.split('/')[1] + '/' + srvr_date.split('/')[2]);
        <%
        for(int i=0;i<fRsMultipleList.get(0).getSen().length;++i){
        %>
        var personNo = <%= i+1 %>;
        $('#gozaresh_naam_naam_khaanevaadegi_pers' + personNo).html($('#nam_bime_shode_pers' + personNo).val());
        $('#gozaresh_sen_bime_shode_v_pers' + personNo).html($('#sen_bime_shode_v_pers' + personNo).val());
        $('#gozaresh_darsad_ezafe_nerkh_pezeshki_pers' + personNo).html($('#darsad_ezafe_nerkh_pezeshki_pers' + personNo).val());
        $('#gozaresh_sarmaye_paye_fot_pers' + personNo).html($('#sarmaye_paye_fot_pers' + personNo).val());
        $('#gozaresh_sarmaye_paye_fot_dar_asar_hadese_pers' + personNo).html($('#sarmaye_paye_fot_dar_asar_hadese_pers' + personNo).val());
        $('#gozaresh_sarmaye_pooshesh_amraz_khas_pers' + personNo).html($('#sarmaye_pooshesh_amraz_khas_pers' + personNo).val());
        $('#gozaresh_sarmaye_pooshesh_naghs_ozv_pers' + personNo).html($('#sarmaye_pooshesh_naghs_ozv_pers' + personNo).val());
        $('#gozaresh_nerkh_afzayesh_salaneh_sarmaye_fot_pers' + personNo).html($('#nerkh_afzayesh_salaneh_sarmaye_fot_pers' + personNo).val());
        $('#gozaresh_posheshe_moafiat_pers' + personNo).html($('#mode22_pers' + personNo).is(':checked') ? 'دارد' : 'ندارد');
        <%
        }
        %>
        $('#gozaresh_nerkh_afzayesh_salaneh_hagh_bime').html($('#nerkh_afzayesh_salaneh_hagh_bime').val());
        $('#gozaresh_hagh_bime_pardakhti').html($('#hagh_bime_pardakhti').val());
//        $('#gozaresh_noe_tarh').html($('#noe_tarh option:selected').text());
        if ($('#mode112').attr('checked')) {
            $('#gozaresh_posheshe_moafiat_dar_surate_fote_moghadam').text('دارد');
        } else {
            $('#gozaresh_posheshe_moafiat_dar_surate_fote_moghadam').text('ندارد');
        }
//        if(jQuery.global.parseInt($('#darsad_takhfif_karmozd_karmozd').val()) == 0){
//            $('#titr_gozaresh_darsade_takhfife_hazineye_karmozd').hide();
//        }else{
//            $('#gozaresh_darsade_takhfife_hazineye_karmozd').html($('#darsad_takhfif_karmozd_karmozd').val());
//        }
        if (jQuery.global.parseInt($('#mablagh_seporde_ebtedaye_sal').val()) == 0) {
            $('#titr_gozaresh_haghbime_avalie').hide();
        } else {
            $('#gozaresh_haghbime_avalie').html($('#mablagh_seporde_ebtedaye_sal').val());
        }
        $('#gozaresh_nahve_pardakht_hagh_bime').html($('#nahve_pardakht_hagh_bime option:selected').text());

        $('.jtable').css('direction', 'rtl');
        $('.jtable').css('font-size', '9px');
        <%----%>
//        $("#prtDataContainer").html("<style>@media print{@page{size: landscape;}</style>");
        $('#prtDataContainer').html($('#printHeader').html());
        $('#prtDataContainer').append('<div style="page-break-before:always"></div>');
        $('#prtDataContainer').append($('#prtDiv').html());
        $('#prtDataContainer').append($('#printFooter').html());
        $('#prtDataContainer .jtable').attr('border', 1);
        $('#prtDataContainer .jtable').css('font-family', 'B Nazanin');
        $("#prtDataContainer").print();
        $('.jtable').css('font-size', '11px');
        $('.prt').removeAttr('disabled');
        setTimeout(function(){
            click--;
        },1000);
        return( true );
        <%----%>
    }
</script>

<fieldset>
    <legend>
        محاسبات بیمه نامه عمر و سرمایه گذاری
    </legend>
    <div class="staticTitleBar">
        <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
            <span class="ui-icon ui-icon-plus" style="float:right;"></span>
            محاسبات بیمه نامه عمر و سرمایه گذاری
        </p>

        <div class="content" id="prtDiv" dir="rtl" style="text-align:center;">
            <table class="jtable resultDets" cellpadding="0" cellspacing="0" style="text-align:center;">
                <thead>
                <tr>
                    <th style="padding:0" class="ui-state-default">سال</th>
                    <%
                        for (int i = 0; i < fRsMultipleList.get(0).getSen().length; ++i) {
                            String bimeShodeOrdinalNo = EstelamAction.getOrdinalNumber(i + 1, true);
                    %>
                    <th style="padding:0" class="ui-state-default">
                        سن بيمه شده <%=bimeShodeOrdinalNo%>
                    </th>
                    <%
                        }
                    %>
                    <%
                        for (int i = 0; i < fRsMultipleList.get(0).getSarmayeFot().length; ++i) {
                            String bimeShodeOrdinalNo = EstelamAction.getOrdinalNumber(i + 1, true);
                    %>
                    <th style="padding:0" class="ui-state-default">
                        سرمایه فوت بيمه شده <%=bimeShodeOrdinalNo%>
                    </th>
                    <%
                        }
                    %>
                    <%
                        for (int i = 0; i < fRsMultipleList.get(0).getSarmayeHadese().length; ++i) {
                            String bimeShodeOrdinalNo = EstelamAction.getOrdinalNumber(i + 1, true);
                    %>
                    <th style="padding:0" class="ui-state-default">
                        سرمایه فوت در اثر حادثه بيمه شده <%=bimeShodeOrdinalNo%>
                    </th>
                    <%
                        }
                    %>
                    <%
                        for (int i = 0; i < fRsMultipleList.get(0).getSarmayeAmraz().length; ++i) {
                            String bimeShodeOrdinalNo = EstelamAction.getOrdinalNumber(i + 1, true);
                    %>
                    <th style="padding:0" class="ui-state-default">
                        سرمایه پوشش نقص عضو بيمه شده <%=bimeShodeOrdinalNo%>
                    </th>
                    <%
                        }
                    %>
                    <%
                        for (int i = 0; i < fRsMultipleList.get(0).getSarmayeAmraz().length; ++i) {
                            String bimeShodeOrdinalNo = EstelamAction.getOrdinalNumber(i + 1, true);
                    %>
                    <th style="padding:0" class="ui-state-default">
                        سرمایه پوشش امراض خاص بيمه شده <%=bimeShodeOrdinalNo%>
                    </th>
                    <%
                        }
                    %>

                    <%
//                        for (int i = 0; i < fRsMultipleList.get(0).getSarmayeAmraz().length; ++i) {
                        for (int i = 0; i < 1; ++i) {
                            String bimeShodeOrdinalNo = EstelamAction.getOrdinalNumber(i + 1, true);
                    %>
                    <th style="padding:0" class="ui-state-default">
                        پوشش معافيت از كار افتادگي براي بيمه شده <%=bimeShodeOrdinalNo%>
                    </th>
                    <%
                        }
                    %>

                    <%--<th style="padding:0" class="ui-state-default">معافیت از کار افتادگی</th>--%>
                    <th style="padding:0" class="ui-state-default">منافع پوشش معافيت فوت مقدم بيمه شده اول</th>
                    <th style="padding:0" class="ui-state-default">حق بیمه پوشش های اضافی</th>

                    <th style="padding:0" class="ui-state-default">مالیات بر ارزش افزوده</th>
                    <th style="padding:0" class="ui-state-default">حق بیمه</th>
                    <th style="padding:0" class="ui-state-default">مجموع حق بیمه پرداختي</th>

                    <%--<th style="padding:0" class="ui-state-default">مجموع حق بیمه های پوشش های اضافی</th>--%>

                    <th style="padding:0" class="ui-state-default">مجموع کل مبالغ پرداختی</th>
                    <th style="padding:0" class="ui-state-default">ارزش بازخرید با سود تضمینی %15</th>
                    <th style="padding:0" class="ui-state-default">پیش بینی ارزش بازخرید با نرخ بازدهی 20%</th>
                    <th style="padding:0" class="ui-state-default">پیش بینی ارزش بازخرید با نرخ بازدهی 25%</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="fRsMultipleList" id="row" status="stat">
                    <tr>
                        <td class="ui-widget-content"><s:property value="#row.getSaal()"/></td>
                            <%--
                        <td class="ui-widget-content"><s:property value="#row.getSen()[0]"/></td>
                        <td class="ui-widget-content"><s:property value="#row.getSen()[1]"/></td>
                        <td class="ui-widget-content"><s:property value="#row.getSen()[2]"/></td>
                        <td class="ui-widget-content"><s:property value="#row.getSen()[3]"/></td>

                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeFot()[0]"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeFot()[1]"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeFot()[2]"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeFot()[3]"/></s:text></td>

                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeHadese()[0]"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeHadese()[1]"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeHadese()[2]"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeHadese()[3]"/></s:text></td>

                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeNaghseOzv()[0]"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeNaghseOzv()[1]"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeNaghseOzv()[2]"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeNaghseOzv()[3]"/></s:text></td>

                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeAmraz()[0]"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeAmraz()[1]"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeAmraz()[2]"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmayeAmraz()[3]"/></s:text></td>--%>
                        <s:iterator value="#row.getSen()" id="sen" status="senStat">
                            <td class="ui-widget-content"><s:property value="#sen"/></td>
                        </s:iterator>
                        <s:iterator value="#row.getSarmayeFot()" id="sarmayeFot" status="sarmayeFotStatus">
                            <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                               value="#sarmayeFot"/></s:text></td>
                        </s:iterator>
                        <s:iterator value="#row.getSarmayeHadese()" id="sarmayeHadese" status="sarmayeHadeseStatus">
                            <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                               value="#sarmayeHadese"/></s:text></td>
                        </s:iterator>
                        <s:iterator value="#row.getSarmayeNaghseOzv()" id="sarmayeNaghseOzv"
                                    status="sarmayeNaghseOzvStatus">
                            <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                               value="#sarmayeNaghseOzv"/></s:text></td>
                        </s:iterator>
                        <s:iterator value="#row.getSarmayeAmraz()" id="sarmayeAmraz" status="sarmayeAmrazStatus">
                            <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                               value="#sarmayeAmraz"/></s:text></td>
                        </s:iterator>

                                                <%--<s:iterator value="#row.getPusheshMoafiat()" id="pusheshMoafiat" status="pusheshMoafiatStatus">--%>
                            <%--<td class="ui-widget-content"><s:text name="#pusheshMoafiat"/></td>--%>
                            <td class="ui-widget-content">${row.pusheshMoafiat[0]}</td>
                        <%--</s:iterator>--%>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                           value="#row.getSarmayeMohasebatiMoafiatFot()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                           value="#row.getHaghBimePusheshHayeEzafe()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                           value="#row.getMaliyatBarArzeshAfzude()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                           value="#row.getHaghBime()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                           value="#row.getMajmuHaghBimePardakhti()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                           value="#row.getMajmuKoleMabaleghePardakhti()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                           value="#row.getArzeshBazkharidBaSud15Darsad()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                           value="#row.getArzeshBazkharidBaSud20Darsad()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                           value="#row.getArzeshBazkharidBaSud25Darsad()"/></s:text></td>
                            <%--<s:if test="#stat.count == fRsList.size">
                            <script type="text/javascript">
                                $("#andukhte_15").val(<s:property value="#row.getArzeshBazKharidBaaSudTazmini15Darsad()"/>);
                                $("#sen_plus_moddat_bimename").val(parseInt($("#sen_bime_shode").val())+parseInt($("#modat_bimename").val()));
                            </script>
                            </s:if>--%>
                    </tr>
                </s:iterator>
                <%--<c:forEach var="row" items="fRsList">--%>
                <%--<tr>--%>
                <%--<td class="ui-widget-content">${row.saal}</td>--%>
                <%--<td class="ui-widget-content">${row.sen}</td>--%>
                <%--<td class="ui-widget-content"><s:text name="doubleFormat">${row.sarmaayeFotBehHarEllat}</s:text></td>--%>
                <%--<td class="ui-widget-content"><s:text name="doubleFormat">${row.sarmaayeFotDarAsarHaadeseh}</s:text></td>--%>
                <%--<td class="ui-widget-content"><s:text name="doubleFormat">${row.sarmaayePusheshEzaafiAmraazKhaas}</s:text></td>--%>
                <%--<s:if test="row.getSarmaayePusheshEzaafiNaghsOzv!=0"><td class="ui-widget-content"><s:text name="doubleFormat">${row.sarmaayePusheshEzaafiNaghsOzv}</s:text></td></s:if>--%>
                <%--<td class="ui-widget-content"><s:text name="doubleFormat">${row.haghBimePardaakhtiSaal}</s:text></td>--%>
                <%--<td class="ui-widget-content"><s:text name="doubleFormat">${row.majmuHaghBimePardaakhtiSaal}</s:text></td>--%>
                <%--<td class="ui-widget-content"><s:text name="doubleFormat">${row.haghBimePusheshHaayeEzaafi}</s:text></td>--%>
                <%--&lt;%&ndash;<td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="reportResult.majmuHaghBimePusheshHaayeEzaafiRound()[#stat.index]"/></s:text></td>&ndash;%&gt;--%>
                <%--<td class="ui-widget-content"><s:text name="doubleFormat">${row.maaliyaatBarArzeshAfzude}</s:text></td>--%>
                <%--<td class="ui-widget-content"><s:text name="doubleFormat">${row.majmuKolMabaaleghPardaakhti}</s:text></td>--%>
                <%--<td class="ui-widget-content"><s:text name="doubleFormat">${row.arzeshBazKharidBaaSudTazmini15Darsad}</s:text></td>--%>
                <%--<td class="ui-widget-content"><s:text name="doubleFormat">${row.pishbiniArzeshBazKharidBaaSud20Darsad}</s:text></td>--%>
                <%--<td class="ui-widget-content"><s:text name="doubleFormat">${row.pishbiniArzeshBazKharidBaaSud22Darsad}</s:text></td>--%>
                <%--</tr>--%>
                <%--</c:forEach>--%>
                <%--<tr>--%>
                <%--<td>&nbsp;</td>--%>
                <%--</tr>--%>
                </tbody>
            </table>
        </div>
    </div>
</fieldset>
<div id="prtDataContainer" style="display:none;" dir="rtl"></div>
<div id="printHeader" style="display:none;" dir="rtl">
    <table class="jtable resultDets" cellpadding="0" cellspacing="0" border="0" style="width:100%;margin-bottom:15px;">
        <tr>
            <td style="border:1px solid black;">
                <table width="100%">
                    <tr>
                        <td>
                            <span style="font-family:B Titr, B Nazanin, Tahoma, Arial;font-size:12px;">
                                شركت سهامي بيمه پارسيان
                            </span>
                        </td>
                        <td>
                            <span style="font-family:B Titr, B Nazanin, Tahoma, Arial;font-size:18px;">
                                گزارش محاسبات رياضي بيمه نامه عمر و سرمايه گذاري خانواده
                            </span>
                        </td>
                        <td>
                            <span style="font-family:B Titr, B Nazanin, Tahoma, Arial;font-size:9px;">
                                تاریخ اخذ گزارش: <label id="Taarikh_akhz_gozaresh"></label>
                            </span>
                        </td>
                        <td>
                            <strong>
                                <img src="../../img/Flag2.jpg" width="24" height="24" alt="logo"
                                     style="float:left;padding:2px;">
                            </strong>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td style="border-bottom:1px solid black;border-left:1px solid black;border-right:1px solid black;">
                <table width="100%">
                    <%
                        for (int i = 0; i < fRsMultipleList.get(0).getSen().length; ++i) {
                            int personNo = i + 1;
                            String bimeShodeOrdinalNo = EstelamAction.getOrdinalNumber(personNo, true);
                    %>
                    <tr>
                        <td colspan="4">
                            <span style="font-family:B Titr, B Nazanin, Tahoma, Arial;font-size:12px;">
                                مشخصات بيمه شده <%=bimeShodeOrdinalNo%>
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            نام و نام خانوادگی بيمه شده <%=bimeShodeOrdinalNo%> :
                            <label id="gozaresh_naam_naam_khaanevaadegi_pers<%=personNo%>"></label></td>
                        <td>
                            سن:
                            <label id="gozaresh_sen_bime_shode_v_pers<%=personNo%>"></label></td>
                        <td colspan="2">
                            درصد اضافه نرخ پزشکی:
                            <label id="gozaresh_darsad_ezafe_nerkh_pezeshki_pers<%=personNo%>"></label>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </td>
        </tr>
        <tr>
            <td style="border-bottom:1px solid black;border-left:1px solid black;border-right:1px solid black;">
                <table width="100%">
                    <tr>
                        <td colspan="4">
                            <span style="font-family:B Titr, B Nazanin, Tahoma, Arial;font-size:12px;">
                                مشخصات بيمه نامه
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            نوع طرح:
                            <label id="gozaresh_noe_tarh"></label>
                        </td>
                        <td>
                            نحوه پرداخت حق بيمه:
                            <label id="gozaresh_nahve_pardakht_hagh_bime"></label>
                        </td>
                        <td>
                            <span id="titr_gozaresh_darsade_takhfife_hazineye_karmozd">
                            درصد تخفيف هزينه كارمزد(كارمزد):
                                </span>
                            <label id="gozaresh_darsade_takhfife_hazineye_karmozd"></label>
                        </td>
                        <td>
                            <span id="titr_gozaresh_haghbime_avalie">
                                 مبلغ حق بيمه اولیه (ريال):
                                </span>
                            <label id="gozaresh_haghbime_avalie"></label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            پوشش معافيت در صورت فوت مقدم بيمه شده اول:
                            <label id="gozaresh_posheshe_moafiat_dar_surate_fote_moghadam"></label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            مبلغ حق بيمه(ريال):
                            <label id="gozaresh_hagh_bime_pardakhti"></label>
                        </td>
                        <td>
                            درصد افزايش سالانه حق بيمه:
                            <label id="gozaresh_nerkh_afzayesh_salaneh_hagh_bime"></label>
                        </td>

                    </tr>

                    <%
                        for (int i = 0; i < fRsMultipleList.get(0).getSen().length; ++i) {
                            int personNo = i + 1;
                            String bimeShodeOrdinalNo = EstelamAction.getOrdinalNumber(personNo, true);

                    %>
                    <tr>

                        <td>
                            سرمايه پايه فوت بيمه شده <%=bimeShodeOrdinalNo%> (ريال):
                            <label id="gozaresh_sarmaye_paye_fot_pers<%=personNo%>"></label>
                        </td>
                        <td>
                            درصد افزايش سالانه سرمايه پايه فوت:
                            <label id="gozaresh_nerkh_afzayesh_salaneh_sarmaye_fot_pers<%=personNo%>"></label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            سرمايه پايه فوت در اثر حادثه بيمه شده <%=bimeShodeOrdinalNo%> (ريال):
                            <label id="gozaresh_sarmaye_paye_fot_dar_asar_hadese_pers<%=personNo%>"></label>
                        </td>
                        <td>
                            سرمايه امراض خاص بيمه شده <%=bimeShodeOrdinalNo%> (ريال):
                            <label id="gozaresh_sarmaye_pooshesh_amraz_khas_pers<%=personNo%>"></label>
                        </td>
                        <td>
                            سرمايه نقص عضو بيمه شده <%=bimeShodeOrdinalNo%> (ريال):
                            <label id="gozaresh_sarmaye_pooshesh_naghs_ozv_pers<%=personNo%>"></label>
                        </td>
                        <%
                            if (personNo == 1) {
                        %>
                        <td>
                            پوشش معافیت :
                            <label id="gozaresh_posheshe_moafiat_pers<%=personNo%>"></label>
                        </td>
                        <%
                            }
                        %>
                    </tr>
                    <%
                        }
                    %>

                </table>
            </td>
        </tr>

    </table>
</div>
<div id="printFooter" style="display:none;text-align:right;" dir="rtl">
    <table class="jtable resultDets" cellpadding="0" cellspacing="0" border="0" style="width:100%;margin-top:15px;">
        <tr>
            <td style="border:1px solid black;padding:5px">
                <span style="font-family:B Titr, B Nazanin, Tahoma, Arial;font-size:12px;">
                    توضيحات تكميلي:
                </span>
            </td>
        </tr>
        <tr>
            <td style="border-bottom:1px solid black;border-left:1px solid black;border-right:1px solid black;padding:5px">
                &radic; پس از تكميل پيشنهاد بيمه‌عمر توسط بيمه‌شده و بررسي آن توسط بيمه‌گر، در صورت عدم سلامتي كامل
                متقاضي، سابقه بيماري و يا داشتن فعاليت هاي حرفه اي پر خطر، حق‌بيمه مربوط به پوششهاي بيمه‌اي افزايش پيدا
                خواهد كرد.
            </td>
        </tr>
        <tr>
            <td style="border-bottom:1px solid black;border-left:1px solid black;border-right:1px solid black;padding:5px">
                &radic; درصورت تحقق خطر فوت مقدم بيمه شده اول و درصورت عدم تداوم بيمه نامه به علت فوت احتمالي بيمه شده
                دوم يا فسخ بيمه نامه, منافع پوشش معافيت فوت مقدم بيمه شده اول مندرج درجدول فوق الذكر نيز علاوه بر ساير
                منافع قابل پرداخت خواهد بود.
            </td>

        </tr>
        <tr>
            <td style="border-bottom:1px solid black;border-left:1px solid black;border-right:1px solid black;padding:5px">
                &radic;<strong> سرمايه بيمه در صورت فوت بيمه‌شده در هرسال</strong>
                = سرمايه بيمه فوت به هر علت در آن سال + سرمايه حادثه (در صورت فوت بر اثر حادثه و خريد اين پوشش) در آن
                سال + اندوخته سرمايه گذاري تا تاريخ فوت.
            </td>
        </tr>
        <tr>
            <td style="border-bottom:1px solid black;border-left:1px solid black;border-right:1px solid black;padding:5px">
                &radic; نرخ سود 15% تا 10سال تضمين شده و پس از آن نرخ سود جديد با توجه به وضعيت اقتصادي و آئين‌نامه بيمه
                مركزي اعلام خواهد شد.
            </td>
        </tr>
    </table>
</div>
<%
        }
    }
%>