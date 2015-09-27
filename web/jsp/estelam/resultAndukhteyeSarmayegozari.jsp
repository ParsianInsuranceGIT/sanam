<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.service.calculations.Reports.FRs" %>
<%@ page import="java.util.List" %>
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
    List<FRs> fRsList = (List<FRs>)request.getAttribute("fRsList");
    if(!((Boolean)request.getAttribute("correctCalculation"))){

%>
Error:ExtaWrongFieldsValue
<%
}else{
    for (FRs fRs : fRsList) {
        fRs.setSarmaayeFotBehHarEllat(Math.round(fRs.getSarmaayeFotBehHarEllat()));
        fRs.setSarmaayeFotDarAsarHaadeseh(Math.round(fRs.getSarmaayeFotDarAsarHaadeseh()));
        fRs.setSarmaayePusheshEzaafiAmraazKhaas(Math.round(fRs.getSarmaayePusheshEzaafiAmraazKhaas()));
        fRs.setHaghBimePardaakhtiSaal(Math.round(fRs.getHaghBimePardaakhtiSaal()));
        fRs.setMajmuHaghBimePardaakhtiSaal(Math.round(fRs.getMajmuHaghBimePardaakhtiSaal()));
        fRs.setHaghBimePusheshHaayeEzaafi(Math.round(fRs.getHaghBimePusheshHaayeEzaafi()));
        fRs.setMaaliyaatBarArzeshAfzude(Math.round(fRs.getMaaliyaatBarArzeshAfzude()));
        fRs.setMajmuKolMabaaleghPardaakhti(Math.round(fRs.getMajmuKolMabaaleghPardaakhti()));
        fRs.setArzeshBazKharidBaaSudTazmini15Darsad(Math.round(fRs.getArzeshBazKharidBaaSudTazmini15Darsad()));
        fRs.setPishbiniArzeshBazKharidBaaSud20Darsad(Math.round(fRs.getPishbiniArzeshBazKharidBaaSud20Darsad()));
        fRs.setPishbiniArzeshBazKharidBaaSud22Darsad(Math.round(fRs.getPishbiniArzeshBazKharidBaaSud22Darsad()));

        if(fRs.getSen() < 0) {maghadireManfi = true;break;}
        if(fRs.getSarmaayeFotBehHarEllat() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getSarmaayeFotDarAsarHaadeseh() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getSarmaayePusheshEzaafiAmraazKhaas() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getHaghBimePardaakhtiSaal() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getMajmuHaghBimePardaakhtiSaal() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getHaghBimePusheshHaayeEzaafi() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getMaaliyaatBarArzeshAfzude() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getMajmuKolMabaaleghPardaakhti() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getArzeshBazKharidBaaSudTazmini15Darsad() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getPishbiniArzeshBazKharidBaaSud20Darsad() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getPishbiniArzeshBazKharidBaaSud22Darsad() < 0.0) {maghadireManfi = true;break;}
    }
    request.setAttribute("fRsListView", fRsList);
    if(maghadireManfi){
%>
Error:WrongFieldsValue
<%}else{%>
<script type="text/javascript">
    $( ".prt" ).bind('click', function(e){
        var srvr_date = '<%=DateUtil.getCurrentDate()%>';
        $('#Taarikh_akhz_gozaresh').html(srvr_date.split('/')[0]+'/'+srvr_date.split('/')[1]+'/'+srvr_date.split('/')[2]);
        $('#gozaresh_naam_naam_khaanevaadegi').html($('#nam_bime_shode').val());
        $('#gozaresh_sen_bime_shode_v').html($('#sen_bime_shode_v').val());
        $('#gozaresh_darsad_ezafe_nerkh_pezeshki').html($('#darsad_ezafe_nerkh_pezeshki').val());
        $('#gozaresh_sarmaye_paye_fot').html($('#sarmaye_paye_fot').val());
        $('#gozaresh_sarmaye_paye_fot_dar_asar_hadese').html($('#sarmaye_paye_fot_dar_asar_hadese').val());
        $('#gozaresh_sarmaye_pooshesh_amraz_khas').html($('#sarmaye_pooshesh_amraz_khas').val());
        $('#gozaresh_sarmaye_pooshesh_naghs_ozv').html($('#sarmaye_pooshesh_naghs_ozv').val());
        $('#gozaresh_hagh_bime_pardakhti').html($('#hagh_bime_pardakhti').val());
//        $('#gozaresh_noe_tarh').html($('#noe_tarh option:selected').text());
//        if(jQuery.global.parseInt($('#darsad_takhfif_karmozd_karmozd').val()) == 0){
//            $('#titr_gozaresh_darsade_takhfife_hazineye_karmozd').hide();
//        }else{
//            $('#gozaresh_darsade_takhfife_hazineye_karmozd').html($('#darsad_takhfif_karmozd_karmozd').val());
//        }
        if(jQuery.global.parseInt($('#mablagh_seporde_ebtedaye_sal').val()) == 0){
            $('#titr_gozaresh_haghbime_avalie').hide();
        }else{
            $('#gozaresh_haghbime_avalie').html($('#mablagh_seporde_ebtedaye_sal').val());
        }
        $('#gozaresh_nahve_pardakht_hagh_bime').html($('#nahve_pardakht_hagh_bime option:selected').text());
        $('#gozaresh_nerkh_afzayesh_salaneh_hagh_bime').html($('#nerkh_afzayesh_salaneh_hagh_bime').val());
        $('#gozaresh_nerkh_afzayesh_salaneh_sarmaye_fot').html($('#nerkh_afzayesh_salaneh_sarmaye_fot').val());
        $('#gozaresh_posheshe_moafiat').html($('#mode22').is(':checked')? 'دارد': 'ندارد');

        $('.jtable').css('direction', 'rtl');
        $('.jtable').css('font-size', '9px');
    <%----%>
        $('#prtDataContainer').html($('#printHeader').html());
        $('#prtDataContainer').append($('#prtDiv').html());
        $('#prtDataContainer').append($('#printFooter').html());
        $('#prtDataContainer .jtable').attr('border', 1);
        $('#prtDataContainer .jtable').css('font-family', 'B Nazanin');
        $("#prtDataContainer").print();
        $('.jtable').css('font-size', '11px');
        return( true );
    <%----%>
    });
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
                    <th style="padding:0" class="ui-state-default" >سال</th>
                    <th style="padding:0" class="ui-state-default">سن</th>
                    <th style="padding:0" class="ui-state-default">سرمایه فوت به هر علت</th>
                    <th style="padding:0" class="ui-state-default">سرمایه فوت در اثر حادثه</th>
                    <th style="padding:0" class="ui-state-default">سرمایه پوشش اضافی امراض خاص</th>
                    <c:if test="${naghsDarad=='true'}"><th style="padding:0" class="ui-state-default">سرمایه پوشش اضافی نقص عضو</th></c:if>
                    <th style="padding:0" class="ui-state-default">معافیت از کار افتادگی</th>
                    <th style="padding:0" class="ui-state-default">حق بیمه پرداختی سال</th>
                    <th style="padding:0" class="ui-state-default">مجموع حق بیمه های پرداختی سال</th>
                    <th style="padding:0" class="ui-state-default">حق بیمه پوشش های اضافی</th>
                    <%--<th style="padding:0" class="ui-state-default">مجموع حق بیمه های پوشش های اضافی</th>--%>
                    <th style="padding:0" class="ui-state-default">مالیات بر ارزش افزوده</th>
                    <c:if test="${!fRsList[0].nahvePardakhteHagheBime.equals('یکجا')}">
                    <th style="padding:0" class="ui-state-default">مجموع کل مبالغ پرداختی</th>
                    </c:if>
                    <th style="padding:0" class="ui-state-default">ارزش بازخرید با سود تضمینی %15</th>
                    <th style="padding:0" class="ui-state-default">پیش بینی ارزش بازخرید با نرخ بازدهی 20%</th>
                    <th style="padding:0" class="ui-state-default">پیش بینی ارزش بازخرید با نرخ بازدهی 25%</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="fRsList" id="row" status="stat">
                    <tr>
                        <td class="ui-widget-content"><s:property value="#row.getSaal()"/></td>
                        <td class="ui-widget-content"><s:property value="#row.getSen()"/></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmaayeFotBehHarEllat()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmaayeFotDarAsarHaadeseh()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmaayePusheshEzaafiAmraazKhaas()"/></s:text></td>
                        <c:if test="${naghsDarad=='true'}"><td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getSarmaayePusheshEzaafiNaghsOzv()"/></s:text></td></c:if>
                        <td class="ui-widget-content"><s:text name="#row.getPoosheshMoafiat()"/></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getHaghBimePardaakhtiSaal()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getMajmuHaghBimePardaakhtiSaal()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getHaghBimePusheshHaayeEzaafi()"/></s:text></td>
                            <%--<td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="reportResult.majmuHaghBimePusheshHaayeEzaafiRound()[#stat.index]"/></s:text></td>--%>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getMaaliyaatBarArzeshAfzude()"/></s:text></td>
                        <c:if test="${!row.nahvePardakhteHagheBime.equals('یکجا')}">
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getMajmuKolMabaaleghPardaakhti()"/></s:text></td>
                        </c:if>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getArzeshBazKharidBaaSudTazmini15Darsad()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getPishbiniArzeshBazKharidBaaSud20Darsad()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getPishbiniArzeshBazKharidBaaSud22Darsad()"/></s:text></td>
                        <s:if test="#stat.count == fRsList.size">
                        <script type="text/javascript">
                            $("#andukhte_15").val(<s:property value="#row.getArzeshBazKharidBaaSudTazmini15Darsad()"/>);
                            $("#sen_plus_moddat_bimename").val(parseInt($("#sen_bime_shode").val())+parseInt($("#modat_bimename").val()));
                        </script>
                        </s:if>
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
                                گزارش محاسبات رياضي بيمه نامه عمر و سرمايه گذاري
                            </span>
                        </td>
                        <td>
                            <span style="font-family:B Titr, B Nazanin, Tahoma, Arial;font-size:9px;">
                                تاریخ اخذ گزارش: <label id="Taarikh_akhz_gozaresh"></label>
                            </span>
                        </td>
                        <td>
                            <strong>
                                <img src="../../img/Flag2.jpg" width="24" height="24" alt="logo" style="float:left;padding:2px;">
                            </strong>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td style="border-bottom:1px solid black;border-left:1px solid black;border-right:1px solid black;">
                <table width="100%">
                    <tr>
                        <td colspan="4">
                            <span style="font-family:B Titr, B Nazanin, Tahoma, Arial;font-size:12px;">
                                مشخصات بيمه شده
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            نام و نام خانوادگی:
                            <label id="gozaresh_naam_naam_khaanevaadegi"></label></td>
                        <td>
                            سن:
                            <label id="gozaresh_sen_bime_shode_v"></label></td>
                        <td colspan="2">
                            درصد اضافه نرخ پزشکی:
                            <label id="gozaresh_darsad_ezafe_nerkh_pezeshki"></label>
                        </td>
                    </tr>
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
                            مبلغ حق بيمه(ريال):
                            <label id="gozaresh_hagh_bime_pardakhti"></label>
                        </td>
                        <td>
                            درصد افزايش سالانه حق بيمه:
                            <label id="gozaresh_nerkh_afzayesh_salaneh_hagh_bime"></label>
                        </td>
                        <td>
                            سرمايه پايه فوت به هر علت (ريال):
                            <label id="gozaresh_sarmaye_paye_fot"></label>
                        </td>
                        <td>
                            درصد افزايش سالانه سرمايه پايه فوت:
                            <label id="gozaresh_nerkh_afzayesh_salaneh_sarmaye_fot"></label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            سرمايه پايه فوت در اثر حادثه(ريال):
                            <label id="gozaresh_sarmaye_paye_fot_dar_asar_hadese"></label>
                        </td>
                        <td>
                            سرمايه امراض خاص(ريال):
                            <label id="gozaresh_sarmaye_pooshesh_amraz_khas"></label>
                        </td>
                        <td>
                            سرمايه نقص عضو(ريال):
                            <label id="gozaresh_sarmaye_pooshesh_naghs_ozv"></label>
                        </td>
                        <td>
                            پوشش معافیت :
                            <label id="gozaresh_posheshe_moafiat"></label>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>

    </table>
</div>
<div id="printFooter" style="display:none;text-align:right;" dir="rtl" >
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
                &radic; پس از تكميل  پيشنهاد بيمه‌عمر توسط بيمه‌شده و بررسي آن توسط بيمه‌گر، در صورت عدم سلامتي كامل متقاضي، سابقه بيماري و يا داشتن فعاليت هاي حرفه اي پر خطر، حق‌بيمه مربوط به پوششهاي بيمه‌اي افزايش پيدا خواهد كرد.
            </td>
        </tr>
        <tr>
            <td style="border-bottom:1px solid black;border-left:1px solid black;border-right:1px solid black;padding:5px">
                &radic;<strong>	سرمايه بيمه در صورت فوت بيمه‌شده در هرسال</strong>
                = سرمايه بيمه فوت به هر علت در آن سال + سرمايه حادثه (در صورت فوت بر اثر حادثه و خريد اين پوشش) در آن سال + اندوخته سرمايه گذاري  تا تاريخ فوت.
            </td>
        </tr>
        <tr>
            <td style="border-bottom:1px solid black;border-left:1px solid black;border-right:1px solid black;padding:5px">
                &radic; نرخ سود 15% تا 10سال تضمين شده و پس از آن نرخ سود جديد با توجه به وضعيت اقتصادي و آئين‌نامه بيمه مركزي اعلام خواهد شد.
            </td>
        </tr>
    </table>
</div>
<%
        }
    }
%>