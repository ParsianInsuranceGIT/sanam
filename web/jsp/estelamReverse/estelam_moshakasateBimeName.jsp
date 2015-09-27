<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 6/8/11
  Time: 9:07 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="inputList" cellspacing="5" width="90%">
    <col class="inputCol">
    <col class="inputCol">
    <tr>
        <td>
            <script type="text/javascript">
                var darsad_takhfif_karmozd_karmozd_val = '0';
                var darsad_takhfif_vosool_val = '0';
            </script>
            <span class="nohelp"></span>
            <select name="estelamReverse.noe_tarh" id="noe_tarh" onchange="if(this.value == 'jamee'){
                         $('#darsad_takhfif_karmozd_karmozd').val(darsad_takhfif_karmozd_karmozd_val);
                         $('#darsad_takhfif_vosool').val(darsad_takhfif_vosool_val);
                    activateArray(new Array ('darsad_takhfif_karmozd_karmozd','darsad_takhfif_vosool'));}
                        else if(this.value=='gheir_jamee'){
                        darsad_takhfif_karmozd_karmozd_val = $('#darsad_takhfif_karmozd_karmozd').val();
                        darsad_takhfif_vosool_val = $('#darsad_takhfif_vosool').val();
                         $('#darsad_takhfif_karmozd_karmozd').val('0');
                         $('#darsad_takhfif_vosool').val('0');
                        deactivateArray(new Array ('darsad_takhfif_karmozd_karmozd','darsad_takhfif_vosool'));} ">
                <option <c:if test="${estelamReverse.noe_tarh != 'jamee'}">selected="selected"</c:if> value="gheir_jamee">انفرادی</option>
                <option <c:if test="${estelamReverse.noe_tarh == 'jamee'}">selected="selected"</c:if> value="jamee">جمعی</option>
            </select>&nbsp;<label>نوع طرح</label>
        </td>
        <td>
            <span class="help" title="مبلغي است كه بيمه گذار مي تواند علاوه بر مبالغ حق بيمه، به عنوان يك سپرده در ابتداي قرارداد پرداخت نمايد"></span>
            <input type="text" name="estelamReverse.mablagh_seporde_ebtedaye_sal" id="mablagh_seporde_ebtedaye_sal" onkeyup="extraValidation1();"
                   value='${estelamReverse.mablagh_seporde_ebtedaye_sal != null ? estelamReverse.mablagh_seporde_ebtedaye_sal : "0"}' class="validate[required,custom[long]] text-input digitSeparators"/>&nbsp;
            <label>مبلغ حق بيمه اوليه(ريال)</label>
        </td>
    </tr>
    <tr>
        <td>
                    <span class="help"
                          title="مبلغي است كه در صورت زنده بودن بيمه شده در انتهاي قرارداد و به شرط ثابت بودن شرايط بيمه نامه، به ذينفعان حيات بيمه نامه قابل پرداخت است."></span>
            <input type="text" name="estelamReverse.andukhte_entehaye_modate_bimename" id="hagh_bime_pardakhti"
                   class="validate[required,custom[long],funcCall[val_mahane_hagh_bime_pardakhti],funcCall[val_3mahe_hagh_bime_pardakhti],funcCall[val_6mahe_hagh_bime_pardakhti],funcCall[val_salane_hagh_bime_pardakhti]]
                           text-input digitSeparators" onkeyup="extraValidation1();"
                   value='${estelamReverse.andukhte_entehaye_modate_bimename != null ? estelamReverse.andukhte_entehaye_modate_bimename : "30,000,000"}'/>&nbsp;
            <label>اندوخته انتهاي مدت بيمه نامه (ریال)</label>
        </td>
        <td>
                    <span class="help"
                          title="نشان دهنده چگونگی پرداخت حق بیمه توسط بیمه گذار در خلال مدت بیمه نامه می باشد"></span>
            <select name="estelamReverse.nahve_pardakht_hagh_bime" id="nahve_pardakht_hagh_bime"
                    onchange="if(this.value == 'yekja'){ deactivateArray(new Array ('mablagh_seporde_ebtedaye_sal'));} else{ activateArray(new Array('mablagh_seporde_ebtedaye_sal'));}">

                <option value="mah">ماهانه</option>
                <option <c:if test="${estelamReverse.nahve_pardakht_hagh_bime == '3mah'}">selected="selected"</c:if> value="3mah">سه ماهه</option>
                <option <c:if test="${estelamReverse.nahve_pardakht_hagh_bime == '6mah'}">selected="selected"</c:if> value="6mah">شش ماهه</option>
                <option <c:if test="${estelamReverse.nahve_pardakht_hagh_bime == 'sal'}">selected="selected"</c:if> value="sal">سالانه</option>
                <%--<option value="yekja">یکجا</option>--%>
            </select>&nbsp;<label>نحوه پرداخت حق بیمه</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نرخی است که بر اساس آن حق بیمه هر ساله رشد خواهد کرد"></span>
            <select name="estelamReverse.nerkh_afzayesh_salaneh_hagh_bime" id="nerkh_afzayesh_salaneh_hagh_bime"
                    class="validate[funcCall[val_darsad_hagh_bime_va_darsad_sarmaye_fot]]" >
                <option value="0">0 درصد</option>
                <option <c:if test="${estelamReverse.nerkh_afzayesh_salaneh_hagh_bime == '5'}">selected="selected"</c:if> value="5">5 درصد</option>
                <option <c:if test="${estelamReverse.nerkh_afzayesh_salaneh_hagh_bime == '10'}">selected="selected"</c:if> value="10">10 درصد</option>
                <option <c:if test="${estelamReverse.nerkh_afzayesh_salaneh_hagh_bime == '15'}">selected="selected"</c:if> value="15">15 درصد</option>
                <option <c:if test="${estelamReverse.nerkh_afzayesh_salaneh_hagh_bime == '20'}">selected="selected"</c:if> value="20">20 درصد</option>
            </select>
            &nbsp;<label>                    نرخ                    افزایش سالانه حق بیمه</label>
        </td>
        <td>
            <span class="help" title="نرخی است که بر اساس آن سرمایه فوت هر ساله رشد خواهد کرد"></span>
            <select name="estelamReverse.nerkh_afzayesh_salaneh_sarmaye_fot" id="nerkh_afzayesh_salaneh_sarmaye_fot"
                    class="validate[funcCall[val_nerkh_afzayesh_salaneh_sarmaye_fot],funcCall[val_darsad_hagh_bime_va_darsad_sarmaye_fot]">
                <option value="0">0 درصد</option>
                <option <c:if test="${estelamReverse.nerkh_afzayesh_salaneh_sarmaye_fot == '5'}">selected="selected"</c:if> value="5">5 درصد</option>
                <option <c:if test="${estelamReverse.nerkh_afzayesh_salaneh_sarmaye_fot == '10'}">selected="selected"</c:if> value="10">10 درصد</option>
                <option <c:if test="${estelamReverse.nerkh_afzayesh_salaneh_sarmaye_fot == '15'}">selected="selected"</c:if> value="15">15 درصد</option>
                <option <c:if test="${estelamReverse.nerkh_afzayesh_salaneh_sarmaye_fot == '20'}">selected="selected"</c:if> value="20">20 درصد</option>
            </select>
            &nbsp;<label>نرخ
            افزایش سالانه سرمایه فوت</label>
        </td>
    </tr>
    <tr>
        <td>
                    <span class="help"
                          title="مبلغی است که بیمه گر متعهد می شود تا در صورت فوت بیمه شده در خلال مدت بیمه نامه به استفاده کنندگان از بیمه نامه بپردازد"></span>
            <input type="text" name="estelamReverse.sarmaye_paye_fot" id="sarmaye_paye_fot"
                   class="validate[required,custom[long],custom[sarmaye_paye_fot],funcCall[val_0_1_sarmaye_paye_fot],funcCall[val_1_10_sarmaye_paye_fot],funcCall[val_10_15_sarmaye_paye_fot],funcCall[val_15_sayer_sarmaye_paye_fot]]
                           digitSeparators text-input"
                   value='${estelamReverse.sarmaye_paye_fot != null ? estelamReverse.sarmaye_paye_fot : "30,000,000"}' onchange="sarmayePayeFotChange();sarmayePoosheshNaghsChange();sarmayeAmrazChange();" onkeyup="sarmayePayeFotChange();sarmayePoosheshNaghsChange();sarmayeAmrazChange();" onkeydown="sarmayePayeFotChange();sarmayePoosheshNaghsChange();sarmayeAmrazChange();"/>
            &nbsp;<label>سرمایه پایه فوت (ریال) </label>
        </td>
        <td>
            <span class="help" title="به مدت زمان بين تاريخ شروع و تاريخ انقضاي بيمه نامه اطلاق مي شود"></span>
            <input type="text" id="modat_bimename" name="estelamReverse.modat_bimename"
                   class="validate[required,custom[modat_bime_naame_30],funcCall[majmoeSenVaModdateBimeName]] text-input"
                   value='${estelamReverse.modat_bimename != null ? estelamReverse.modat_bimename : "20"}'/>&nbsp;<label>مدت بیمه نامه (سال)</label>
        </td>
    </tr>
</table>
