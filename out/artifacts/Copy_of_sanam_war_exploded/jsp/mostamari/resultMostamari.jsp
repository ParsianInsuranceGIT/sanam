<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

    <div class="staticTitleBar">
        <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
            <span class="ui-icon ui-icon-plus" style="float:right;"></span>
            محاسبات مستمري اندوخته انتهاي مدت
        </p>
        <div class="content" id="prtDivMostamari" dir="rtl" style="text-align:center;">
            <table class="jtable resultDets" cellpadding="0" cellspacing="0" style="text-align:center; margin: 0 auto;">
                <thead>
                <tr>
                    <th style="padding:0" class="ui-state-default" >سال</th>
                    <th style="padding:0" class="ui-state-default">سن</th>
                    <th style="padding:0" class="ui-state-default">مبلغ مستمری سالانه (ریال)</th>
                    <th style="padding:0" class="ui-state-default">مبلغ مستمری ماهانه(ریال)</th>
                    <th style="padding:0" class="ui-state-default">مبلغ مستمری سه ماهه(ریال)</th>
                    <th style="padding:0" class="ui-state-default">مبلغ مستمری شش ماهه(ریال)</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="mostamariFRsList" id="row" status="stat">
                    <tr>
                        <td class="ui-widget-content"><s:property value="#row.getSaal()"/></td>
                        <td class="ui-widget-content"><s:property value="#row.getSen()"/></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getMablaghMostamari()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getMablaghMostamariMahane()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getMablaghMostamariSeMahe()"/></s:text></td>
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="#row.getMablaghMostamariSheshMahe()"/></s:text></td>
                    </tr>
                </s:iterator>
                <c:if test="${checkForMadam == 'true'}">
                    <tr>
                        <td class="ui-widget-content" colspan="6">...&nbsp;&nbsp;&nbsp;&nbsp;...&nbsp;&nbsp;&nbsp;&nbsp;...&nbsp;&nbsp;&nbsp;&nbsp;...&nbsp;&nbsp;&nbsp;&nbsp;...&nbsp;&nbsp;&nbsp;&nbsp;...</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>



<div id="prtDataContainerMostamari" style="display:none;" dir="rtl"></div>
<div id="printHeaderMostamari" style="display:none;" dir="rtl">
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
                                 گزارش محاسبات مستمري اندوخته انتهاي مدت
                            </span>
                        </td>
                        <td>
                            <span style="font-family:B Titr, B Nazanin, Tahoma, Arial;font-size:9px;">
                                تاریخ اخذ گزارش: <label id="Taarikh_akhz_gozareshMostamari"></label>
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
                                مشخصات مستمری بگیر
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            نام و نام خانوادگی:
                            <label id="gozaresh_naam_naam_khaanevaadegi_mostamari"></label></td>
                        <td>
                            سن:
                            <label id="gozaresh_sen_bime_shode_v_mostamari"></label></td>
                        <td colspan="2">
                            درصد اضافه نرخ پزشکی:
                            <label id="gozaresh_darsad_ezafe_nerkh_pezeshki_mostamari"></label>
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
                    <tr>
                        <td>
                            سرمايه پايه فوت به هر علت (ريال):
                            <label id="gozaresh_sarmaye_paye_fot_mostamari"></label>
                        </td>
                        <td>
                            سرمايه پايه فوت در اثر حادثه(ريال):
                            <label id="gozaresh_sarmaye_paye_fot_dar_asar_hadese_mostamari"></label>
                        </td>
                        <td>
                            سرمايه امراض خاص(ريال):
                            <label id="gozaresh_sarmaye_pooshesh_amraz_khas_mostamari"></label>
                        </td>
                        <td>
                            سرمايه نقص عضو(ريال):
                            <label id="gozaresh_sarmaye_pooshesh_naghs_ozv_mostamari"></label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            مبلغ حق بيمه(ريال):
                            <label id="gozaresh_hagh_bime_pardakhti_mostamari"></label>
                        </td>
                        <td>
                            نحوه پرداخت حق بیمه
                            <label id="gozaresh_nahve_pardakht_haghbime_mostamari"></label>
                        </td>
                        <td>
                            درصد افزايش سالانه حق بيمه:
                            <label id="gozaresh_nerkh_afzayesh_salaneh_hagh_bime_mostamari"></label>
                        </td>

                        <td>
                            درصد افزايش سالانه سرمايه پايه فوت:
                            <label id="gozaresh_nerkh_afzayesh_salaneh_sarmaye_fot_mostamari"></label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            نوع مستمری درخواستی:
                            <label id="gozaresh_noe_mostamari_darkhasti"></label>
                        </td>
                        <td>
                            دوره تضمین پرداخت (سال):
                            <label id="gozaresh_dore_tazmin_pardakht"></label>
                        </td>
                        <td>
نحوه پرداخت مستمری:
                            <label id="gozaresh_nahve_pardakht_mostamari"></label>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<div id="printFooterMostamari" style="display:none;text-align:right;" dir="rtl" >
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
                &radic;محاسبات مربوط به مبالغ مستمري بر اساس نرخ سود علي الحساب 15% انجام شده است. بديهي است مبلغ مستمري حسب شرايط اقتصادي و تغييرات نرخ سود در انتهاي مدت بيمه نامه تعديل خواهد شد.
            </td>
        </tr>
        <tr>
            <td style="border-bottom:1px solid black;border-left:1px solid black;border-right:1px solid black;padding:5px">
                &radic;در <b>مستمري مدت معين،</b> بيمه گر متعهد به پرداخت مستمري تا انتهاي دوره تضمين به مستمري بگير يا ذينفعان وي خواهد بود.
            </td>
        </tr>
        <tr>
            <td style="border-bottom:1px solid black;border-left:1px solid black;border-right:1px solid black;padding:5px">
                &radic;در <b>مستمري مادام العمر با دوره تضمين پرداخت،</b> بيمه گر متعهد به پرداخت مستمري تا انتهاي دوره تضمين به مستمري بگير يا ذينفعان وي خواهد بود. بديهي است در صورت حيات مستمري بگير در انتهاي دوره تضمين، بيمه گر متعهد به پرداخت مستمري تا زمان حيات وي خواهد بود.
            </td>
        </tr>
    </table>
</div>
