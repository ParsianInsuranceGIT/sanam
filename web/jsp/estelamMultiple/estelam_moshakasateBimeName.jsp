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
            <select name="estelamKhanevade.noeTarh" id="noe_tarh" onchange="if(this.value == 'jamee'){
                         $('#darsad_takhfif_karmozd_karmozd').val(darsad_takhfif_karmozd_karmozd_val);
                         $('#darsad_takhfif_vosool').val(darsad_takhfif_vosool_val);
                    activateArray(new Array ('darsad_takhfif_karmozd_karmozd','darsad_takhfif_vosool'));}
                        else if(this.value=='gheir_jamee'){
                        darsad_takhfif_karmozd_karmozd_val = $('#darsad_takhfif_karmozd_karmozd').val();
                        darsad_takhfif_vosool_val = $('#darsad_takhfif_vosool').val();
                         $('#darsad_takhfif_karmozd_karmozd').val('0');
                         $('#darsad_takhfif_vosool').val('0');
                        deactivateArray(new Array ('darsad_takhfif_karmozd_karmozd','darsad_takhfif_vosool'));} ">
                <option <%--<c:if test="${estelam.noe_tarh != 'jamee'}">selected="selected"</c:if>--%> value="gheir_jamee">انفرادی</option>
                <option <%--<c:if test="${estelam.noe_tarh == 'jamee'}">selected="selected"</c:if>--%> value="jamee">جمعی</option>
            </select>&nbsp;<label>نوع طرح</label>
        </td>
        <td>
            <span class="help" title="مبلغي است كه بيمه گذار مي تواند علاوه بر مبالغ حق بيمه، به عنوان يك سپرده در ابتداي قرارداد پرداخت نمايد"></span>
            <input type="text" name="estelamKhanevade.mablagheHagheBimeAvalie" id="mablagh_seporde_ebtedaye_sal" onkeyup="extraValidation1_multiple();"
                   class="validate[required,custom[long]] text-input digitSeparators" value="0"/>&nbsp;
            <label>مبلغ حق بيمه اوليه(ريال)</label>
        </td>

    </tr>
    <tr>

        <td>
                    <span class="help"
                          title="وجهی است که بیمه گذار به منظور ارائه پوشش بیمه ای باید به بیمه گر بپردازد"></span>
            <input type="text" name="estelamKhanevade.mablagheHagheBimeMonazam" id="hagh_bime_pardakhti"
                   class="validate[required,custom[long],funcCall[val_mahane_hagh_bime_pardakhti],funcCall[val_3mahe_hagh_bime_pardakhti],funcCall[val_6mahe_hagh_bime_pardakhti],funcCall[val_salane_hagh_bime_pardakhti]]
                           text-input digitSeparators" onkeyup="extraValidation1_multiple();"
                    value="300,000"/>&nbsp;
            <label>مبلغ حق بيمه منظم پرداختي (ريال)</label>
        </td>
        <td>
                    <span class="help"
                          title="نشان دهنده چگونگی پرداخت حق بیمه توسط بیمه گذار در خلال مدت بیمه نامه می باشد"></span>
            <select name="estelamKhanevade.nahveyePardakht" id="nahve_pardakht_hagh_bime"
                    onchange="if(this.value == 'yekja'){ deactivateArray(new Array ('mode2_pers1','mode2_pers2','mode2_pers3','mode2_pers4','hagh_bime_pardakhti')); $('#hagh_bime_pardakhti').val('0');} else{ activateArray(new Array('hagh_bime_pardakhti'));}">
            >
                <option selected = "selected" value="mah">ماهانه</option>
                <option value="3mah">سه ماهه</option>
                <option value="6mah">شش ماهه</option>
                <option value="sal">سالانه</option>
                <%--<option value="yekja">یکجا</option>--%>
            </select>&nbsp;<label>نحوه پرداخت حق بیمه</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نرخی است که بر اساس آن حق بیمه هر ساله رشد خواهد کرد"></span>
            <select name="estelamKhanevade.nerkheAfzayesheHagheBime" id="nerkh_afzayesh_salaneh_hagh_bime"
                    class="validate[funcCall[val_darsad_hagh_bime_va_darsad_sarmaye_fot_multiple_all]]">
                <option value="0" selected ="selected">0 درصد</option>
                <option value="5">5 درصد</option>
                <option value="10">10 درصد</option>
                <option value="15">15 درصد</option>
                <option value="20">20 درصد</option>
            </select>
            &nbsp;<label>                    نرخ                    افزایش سالانه حق بیمه</label>
        </td>
        <td>
            <span class="help" title="به مدت زمان بين تاريخ شروع و تاريخ انقضاي بيمه نامه اطلاق مي شود"></span>
            <input type="text" id="modat_bimename" name="estelamKhanevade.modateBimeName"
                   class="validate[required,custom[modat_bime_naame_30],funcCall[majmoeSenVaModdateBimeName_multiple],custom[modat_bime_naame_5] text-input"
                   value="20"
                   />
            &nbsp;<label>مدت بیمه نامه (سال)</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="mode11">
                <input type="radio" id="mode111" name="estelamKhanevade.poosheshMoafiatDarSoorateFoteMoghadam"
                       value="no" disabled="disabled"/><label for="mode111">ندارد</label>
                <input type="radio" id="mode112" name="estelamKhanevade.poosheshMoafiatDarSoorateFoteMoghadam"
                       checked="checked"
                       value="yes" disabled="disabled"/><label for="mode112">دارد</label>
            </div>
            &nbsp;<label>پوشش معافيت در صورت فوت مقدم بيمه شده اول</label>
        </td>
    </tr>
</table>