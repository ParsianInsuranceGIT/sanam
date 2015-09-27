<%@ page import="com.bitarts.parsian.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link type="text/css" rel="stylesheet" href="../../css/andukhteyeSarmayegozari.css"/>
    <%--<script type="text/javascript" src="../../js/validation/estelam_valFuncs.js"></script>--%>
    <script type="text/javascript" src="../../js/validation/taghireSharayet_valFuncs.js"></script>
    <script type="text/javascript" src="../../js/jquery.print.js"></script>
</head>


<p>
    لطفا با توجه به توضيحات داده شده، تغييرات لازم را در فيلدهاي زير اعمال نماييد:
</p>
<%--<form></form>--%>
<form id="sharayetejadid" name="form1" method="post" action="/anjameSharayeteJadid.action">
<input type="hidden" name="pishnehad.id" value="${pishnehad.id}"/>
<input type="hidden" id="sharayetejadidtransid" name="transitionId" value=""/>
<input type="hidden" id="taghirlgmsg" name="logmessage" value=""/>
<table class="inputList" border="0" cellspacing="5" cellpadding="1">
    <tr>
        <td>
            <span class="help" title="مبلغي است كه بيمه گذار مي تواند علاوه بر مبالغ حق بيمه، به عنوان يك سپرده در ابتداي قرارداد پرداخت نمايد"></span>
            <input type="text" class="validate[required,custom[long]] text-input digitSeparators" id="innermablagh_avaliye_miani" name="sharayeteJadid.mablagh_seporde_ebtedaye_sal" value="${pishnehad.estelam.mablagh_seporde_ebtedaye_sal}"/>
            <label>مبلغ حق بيمه اوليه(ريال)</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="وجهی است که بیمه گذار به منظور ارائه پوشش بیمه ای باید به بیمه گر بپردازد"></span>
            <input type="text" class="validate[required,custom[long],funcCall[val_mahane_hagh_bime_pardakhti_ts],funcCall[val_3mahe_hagh_bime_pardakhti_ts],funcCall[val_6mahe_hagh_bime_pardakhti_ts],funcCall[val_salane_hagh_bime_pardakhti_ts]]
                           text-input digitSeparators" id="innerhagh_monazam_miani" name="sharayeteJadid.hagh_bime_pardakhti" value="${pishnehad.estelam.hagh_bime_pardakhti}"/>
            <label>مبلغ حق بيمه منظم پرداختي (ريال)</label>
        </td>
        <td>
            <span class="help"
                          title="نشان دهنده چگونگی پرداخت حق بیمه توسط بیمه گذار در خلال مدت بیمه نامه می باشد"></span>
            <select name="sharayeteJadid.nahve_pardakht_hagh_bime" id="innernahve_pardakht_hagh_bime"
                    onchange="if(this.value == 'yekja'){ deactivateArray(new Array ('innermode2'));deactivateArray(new Array ('innernerkh_afzayesh_salaneh_hagh_bime'));} else{ activateArray(new Array('innermode2'));activateArray(new Array('innernerkh_afzayesh_salaneh_hagh_bime'));}"
                    <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime == 'yekja'}">disabled="disabled"</c:if>>
                <option value="mah">ماهانه</option>
                <option <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime == '3mah'}">selected="selected"</c:if> value="3mah">سه ماهه</option>
                <option <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime == '6mah'}">selected="selected"</c:if> value="6mah">شش ماهه</option>
                <option <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime == 'sal'}">selected="selected"</c:if> value="sal">سالانه</option>
                <option <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime == 'yekja'}">selected="selected"</c:if> value="yekja">یکجا</option>
            </select>&nbsp;<label>نحوه پرداخت حق بیمه</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نرخی است که بر اساس آن حق بیمه هر ساله رشد خواهد کرد"></span>
            <select name="sharayeteJadid.nerkh_afzayesh_salaneh_hagh_bime" id="innernerkh_afzayesh_salaneh_hagh_bime"
                    class="validate[funcCall[val_darsad_hagh_bime_va_darsad_sarmaye_fot_ts]]">
                <option value="0">0 درصد</option>
                <option <c:if test="${pishnehad.estelam.nerkh_afzayesh_salaneh_hagh_bime == '5'}">selected="selected"</c:if> value="5">5 درصد</option>
                <option <c:if test="${pishnehad.estelam.nerkh_afzayesh_salaneh_hagh_bime == '10'}">selected="selected"</c:if> value="10">10 درصد</option>
                <option <c:if test="${pishnehad.estelam.nerkh_afzayesh_salaneh_hagh_bime == '15'}">selected="selected"</c:if> value="15">15 درصد</option>
                <option <c:if test="${pishnehad.estelam.nerkh_afzayesh_salaneh_hagh_bime == '20'}">selected="selected"</c:if> value="20">20 درصد</option>
            </select>
            &nbsp;<label>                    نرخ                    افزایش سالانه حق بیمه</label>
        </td>
        <c:if test="${pishnehad.state.id != 90}">
        <td>
            <span class="help" title="نرخی است که بر اساس آن سرمایه فوت هر ساله رشد خواهد کرد"></span>
            <select name="sharayeteJadid.nerkh_afzayesh_salaneh_sarmaye_fot" id="innernerkh_afzayesh_salaneh_sarmaye_fot"
                    class="validate[funcCall[val_nerkh_afzayesh_salaneh_sarmaye_fot_ts],funcCall[val_darsad_hagh_bime_va_darsad_sarmaye_fot_ts]">
                <option value="0">0 درصد</option>
                <option <c:if test="${pishnehad.estelam.nerkh_afzayesh_salaneh_sarmaye_fot == '5'}">selected="selected"</c:if> value="5">5 درصد</option>
                <option <c:if test="${pishnehad.estelam.nerkh_afzayesh_salaneh_sarmaye_fot == '10'}">selected="selected"</c:if> value="10">10 درصد</option>
                <option <c:if test="${pishnehad.estelam.nerkh_afzayesh_salaneh_sarmaye_fot == '15'}">selected="selected"</c:if> value="15">15 درصد</option>
                <option <c:if test="${pishnehad.estelam.nerkh_afzayesh_salaneh_sarmaye_fot == '20'}">selected="selected"</c:if> value="20">20 درصد</option>
            </select>
            &nbsp;<label>نرخ
            افزایش سالانه سرمایه فوت</label>
        </td>
        </c:if>
    </tr>
    <tr>
        <c:if test="${pishnehad.state.id != 90}">
        <td>
            <span class="help" title="مبلغی است که بیمه گر متعهد می شود تا در صورت فوت بیمه شده در خلال مدت بیمه نامه به استفاده کنندگان از بیمه نامه بپردازد"></span>
            <input type="text" class="validate[required,custom[long],custom[sarmaye_paye_fot],funcCall[val_0_2_sarmaye_paye_fot_ts],funcCall[val_3_9_sarmaye_paye_fot_ts],funcCall[val_10_14_sarmaye_paye_fot_ts],funcCall[val_15_19_sarmaye_paye_fot_ts],funcCall[val_20_sayer_sarmaye_paye_fot_ts]]
                           digitSeparators text-input" id="innersarmaye_fot_miani" name="sharayeteJadid.sarmaye_paye_fot" value="${pishnehad.estelam.sarmaye_paye_fot}"  onChange="sarmayePayeFotChangeInner(false);sarmayePoosheshNaghsChangeInner(false);sarmayeAmrazChangeInner(false);" onkeyup="sarmayePayeFotChangeInner(true);sarmayePoosheshNaghsChangeInner(true);sarmayeAmrazChangeInner(true);" onkeydown="sarmayePayeFotChangeInner(true);sarmayePoosheshNaghsChangeInner(true);sarmayeAmrazChangeInner(true);"/>
            <label>سرمایه پایه فوت (ریال) </label>
        </td>
        </c:if>
        <td>
            <span class="help" title="به مدت زمان بين تاريخ شروع و تاريخ انقضاي بيمه نامه اطلاق مي شود"></span>
            <input type="text" id="innermodat_bimename" name="sharayeteJadid.modat_bimename"
                   class="validate[required,custom[modat_bime_naame_30],funcCall[majmoeSenVaModdateBimeName_ts],custom[modat_bime_naame_5] text-input"
                   value='${pishnehad.estelam.modat_bimename != null ? pishnehad.estelam.modat_bimename : "20"}'/>
            &nbsp;<label>مدت بیمه نامه (سال)</label>
        </td>
    </tr>
    <c:if test="${pishnehad.state.id != 90}">
    <tr>
        <td>

            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="innermode4">
                <input type="radio" id="innermode41" name="sharayeteJadid.pooshesh_fot_dar_asar_haadese"
                       <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_haadese != 'yes'}">checked="checked"</c:if>
                       value="no" onchange="if($(this).attr('checked')){pushesheFoutDarAsareHadeseNadaradInner();}"/>
                <input type="radio" id="innermode42" name="sharayeteJadid.pooshesh_fot_dar_asar_haadese"
                       <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_haadese == 'yes'}">checked="checked"</c:if>
                       value="yes" onchange="if($(this).attr('checked')){pushesheFoutDarAsareHadeseDaradInner();}"/>
                <label for="innermode41">ندارد</label>
                <label for="innermode42">دارد</label>
            </div>
            &nbsp;<label>
            پوشش فوت در اثر حادثه
        </label>
        </td>
        <td>
                    <span class="help"
                          title="معادل سه برابر سرمايه پايه فوت به شرطي كه از 1 ميليارد ريال تجاوز نكند"></span>
            <input type="text" name="sharayeteJadid.sarmaye_paye_fot_dar_asar_hadese" id="innersarmaye_paye_fot_dar_asar_hadese"
                   class="validate[required,custom[long],funcCall[sarmaye_paye_fot_dar_asar_hadese_ts]] text-input ui-state-disabled digitSeparators"
                   value='${pishnehad.estelam.sarmaye_paye_fot_dar_asar_hadese != null ? pishnehad.estelam.sarmaye_paye_fot_dar_asar_hadese : "0"}' disabled/>&nbsp;<label>سرمایه پایه فوت در اثر حادثه (ریال)</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>
            <div class="dblRadio" id="innermode1">
                <input type="radio" id="innermode11" name="sharayeteJadid.pooshesh_naghs_ozv"
                       <c:if test="${pishnehad.estelam.pooshesh_naghs_ozv != 'yes'}">checked="checked"</c:if>
                       value="no" onchange="if($(this).attr('checked')){deactivate('innersarmaye_pooshesh_naghs_ozv');}"/><label for="innermode11">ندارد</label>
                <input type="radio" id="innermode12" name="sharayeteJadid.pooshesh_naghs_ozv"
                       <c:if test="${pishnehad.estelam.pooshesh_naghs_ozv == 'yes'}">checked="checked"</c:if>
                       value="yes" onchange="if($(this).attr('checked')){activation('innersarmaye_pooshesh_naghs_ozv');}"/><label
                    for="innermode12">دارد</label>
            </div>
            &nbsp;<label>پوشش نقص عضو</label>
        </td>
        <td>
            <span class="help" title="سرمايه اي است كه در صورت وقوع ريسك نقص عضو متناسب با درصد نقص عضو به بيمه شده پرداخت مي شود"></span>
            <input type="text" name="sharayeteJadid.sarmaye_pooshesh_naghs_ozv" id="innersarmaye_pooshesh_naghs_ozv"
                   class="validate[required,custom[long],funcCall[val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_ts]] text-input ui-state-disabled digitSeparators"
                   value='${pishnehad.estelam.sarmaye_pooshesh_naghs_ozv != null ? pishnehad.estelam.sarmaye_pooshesh_naghs_ozv : "0"}' />&nbsp;
            <label>سرمایه پوشش نقص عضو (ریال) </label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>
            <div class="dblRadio" id="innermode3">
                <input type="radio" id="innermode31" name="sharayeteJadid.pooshesh_fot_dar_asar_zelzele_fake"
                       <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_zelzele != 'yes'}">checked="checked"</c:if>
                       value="no" disabled="disabled"/><label for="innermode31">ندارد</label>
                <input type="radio" id="innermode32" name="sharayeteJadid.pooshesh_fot_dar_asar_zelzele_fake"
                       <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_zelzele == 'yes'}">checked="checked"</c:if>
                       value="yes" disabled="disabled"/><label for="innermode32">دارد</label>
            </div>
            &nbsp;<label>
            پوشش فوت در اثر زلزله
        </label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="innermode5">
                <input type="radio" id="innermode51" name="sharayeteJadid.pooshesh_amraz_khas"
                       <c:if test="${pishnehad.estelam.pooshesh_amraz_khas != 'yes'}">checked="checked"</c:if>
                       value="no" onchange="if($(this).attr('checked')){deactivate('innersarmaye_pooshesh_amraz_khas');}"/><label for="innermode51">ندارد</label>
                <input type="radio" id="innermode52" name="sharayeteJadid.pooshesh_amraz_khas"
                       <c:if test="${pishnehad.estelam.pooshesh_amraz_khas == 'yes'}">checked="checked"</c:if>
                       value="yes" onchange="if($(this).attr('checked')){activation('innersarmaye_pooshesh_amraz_khas');}"/><label
                    for="innermode52">دارد</label>
            </div>
            &nbsp;<label>پوشش امراض خاص</label>
        </td>
        <td>
                    <span class="help"
                          title="نشان دهنده سقف سرمایه ای است که در صورت ابتلاء بیمه شده به یکی از امراض خاص در خلال مدت بیمه نامه، به منظور جبران بخشی از هزینه های بیمارستانی پرداخت می شود"></span>
            <input type="text" name="sharayeteJadid.sarmaye_pooshesh_amraz_khas" id="innersarmaye_pooshesh_amraz_khas"
                   class="validate[required,custom[long],funcCall[val_sarmaye_pushesh_amraaz_khaas_ts]] text-input ui-state-disabled digitSeparators"
                   value='${pishnehad.estelam.sarmaye_pooshesh_amraz_khas != null ? pishnehad.estelam.sarmaye_pooshesh_amraz_khas : "0"}'/>
            &nbsp;<label>سرمایه
            <%--value="9,000,000"/>&nbsp;<label>سرمایه--%>
            پوشش امراض خاص (ریال)</label>
        </td>
    </tr>

    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>
            <div class="dblRadio" id="innermode2">
                <input type="radio" id="innermode21" name="sharayeteJadid.pooshesh_moafiat"
                       <c:if test="${pishnehad.estelam.pooshesh_moafiat != 'yes'}">checked="checked"</c:if>
                       value="no"/><label for="innermode21">ندارد</label>
                <input type="radio" id="innermode22" name="sharayeteJadid.pooshesh_moafiat"
                       <c:if test="${pishnehad.estelam.pooshesh_moafiat == 'yes'}">checked="checked"</c:if>
                       value="yes"/><label for="innermode22">دارد</label>
            </div>
            &nbsp;<label>پوشش معافیت</label>
        </td>
        <td></td>
    </tr>
    </c:if>
    <tr>
        <td>
            <input type="button" onclick="submitSharayeteJadid();" value="تایید"/>
        </td>
    </tr>
</table>
</form>
<div id="resultholderforcalculation">

</div>

<script type="text/javascript">
    function pushesheFoutDarAsareHadeseNadaradInner(){
        deactivate('innersarmaye_paye_fot_dar_asar_hadese');
        sarmayePayeFotChangeInner(true);sarmayePoosheshNaghsChangeInner(true);sarmayeAmrazChangeInner(true);
        $('#innermode31').attr('checked', true).change();
        $('#innermode32').removeAttr('checked').change();
    }

    function pushesheFoutDarAsareHadeseDaradInner(){
        activation('innersarmaye_paye_fot_dar_asar_hadese');
        sarmayePayeFotChangeInner(true);sarmayePoosheshNaghsChangeInner(true);sarmayeAmrazChangeInner(true);
        $('#innermode32').attr('checked', true).change();
        $('#innermode31').removeAttr('checked').change();
    }

    function sarmayePayeFotChangeInner(typing){
    if($('#innersarmaye_fot_miani').val() && $('#innersarmaye_fot_miani').val() != ''){
        if(!$('#innersarmaye_paye_fot_dar_asar_hadese').is(':disabled')){
            <c:if test="${estelam.pooshesh_fot_dar_asar_haadese == 'yes'}">
            var v = jQuery.global.format(jQuery.global.parseInt('${estelam.sarmaye_paye_fot_dar_asar_hadese}'), "n0");
            </c:if>
            <c:if test="${estelam.pooshesh_fot_dar_asar_haadese != 'yes'}">
            var v = jQuery.global.format(jQuery.global.parseInt($('#innersarmaye_fot_miani').val()), "n0");
            </c:if>
            if(typing)
                v = jQuery.global.format(jQuery.global.parseInt($('#innersarmaye_fot_miani').val()), "n0");
            $('#innersarmaye_paye_fot_dar_asar_hadese').val(v).change();
        }
    }
}
function sarmayeAmrazChangeInner(typing){
    if($('#innersarmaye_fot_miani').val() && $('#innersarmaye_fot_miani').val() != ''){
        if(!$('#innersarmaye_pooshesh_amraz_khas').is(':disabled')){
            <c:if test="${estelam.pooshesh_amraz_khas == 'yes'}">
            if(typing)
            {
                if((0.3*jQuery.global.parseInt($('#innersarmaye_fot_miani').val()))>200000000){
                    $('#innersarmaye_pooshesh_amraz_khas').val('300,000,000').change();
                }else{
                    var v = jQuery.global.format(0.3*jQuery.global.parseInt($('#innersarmaye_fot_miani').val()), "n0");
                    $('#innersarmaye_pooshesh_amraz_khas').val(v).change();
                }
            }
            else
            {
                var v = jQuery.global.format(jQuery.global.parseInt('${estelam.sarmaye_pooshesh_amraz_khas}'), "n0");
                $('#innersarmaye_pooshesh_amraz_khas').val(v).change();
            }
            </c:if>
            <c:if test="${estelam.pooshesh_amraz_khas != 'yes'}">
            if((0.3*jQuery.global.parseInt($('#innersarmaye_fot_miani').val()))>200000000){
                $('#innersarmaye_pooshesh_amraz_khas').val('300,000,000').change();
            }else{
                var v = jQuery.global.format(0.3*jQuery.global.parseInt($('#innersarmaye_fot_miani').val()), "n0");
                $('#innersarmaye_pooshesh_amraz_khas').val(v).change();
            }
            </c:if>
        }
    }
}
function sarmayePoosheshNaghsChangeInner(typing){
    if($('#innersarmaye_fot_miani').val() && $('#innersarmaye_fot_miani').val() != ''){
        if(!$('#innersarmaye_pooshesh_naghs_ozv').is(':disabled')){
            <c:if test="${estelam.pooshesh_naghs_ozv == 'yes'}">
            var v = jQuery.global.format(jQuery.global.parseInt('${estelam.sarmaye_pooshesh_naghs_ozv}'), "n0");
            if(typing)
            {
                var sarPayeFot =jQuery.global.parseInt($('#innersarmaye_fot_miani').val());
                var sarFotHadeshe=jQuery.global.parseInt($('#innersarmaye_paye_fot_dar_asar_hadese').val());
                var min = sarPayeFot;
                if(min>sarFotHadeshe) min=sarFotHadeshe;
                if(min > 500000000) min = 500000000;
                v = jQuery.global.format(min, "n0");
            }
            </c:if>
            <c:if test="${estelam.pooshesh_naghs_ozv != 'yes'}">
            var sarPayeFot =jQuery.global.parseInt($('#innersarmaye_fot_miani').val());
            var sarFotHadeshe=jQuery.global.parseInt($('#innersarmaye_paye_fot_dar_asar_hadese').val());
            var min = sarPayeFot;
            if(min>sarFotHadeshe) min=sarFotHadeshe;
            if(min > 500000000) min = 500000000;
            var v = jQuery.global.format(min, "n0");
            </c:if>
//            var v1 = jQuery.global.format(jQuery.global.parseInt($('#sarmaye_paye_fot_dar_asar_hadese').val()), "n0");
//            var v2 = jQuery.global.format(jQuery.global.parseInt(v)+jQuery.global.parseInt(v1));
            $('#innersarmaye_pooshesh_naghs_ozv').val(v).change();
        }
    }
}
    function activation(id) {
        $('#' + id).removeClass('ui-state-disabled');
        $('#' + id).removeAttr("disabled");
    }
    function deactivate(id) {
        $('#' + id).addClass('ui-state-disabled');
        $('#' + id).attr("disabled", true);
    }

    function activateArray(ids) {
        for(var idsCounter=0;idsCounter<ids.length;idsCounter++){
            $('#' + ids[idsCounter]).removeClass('ui-state-disabled');
            $('#' + ids[idsCounter]).removeAttr("disabled");
        }
    }

    function deactivateArray(ids) {
        for(var idsCounter=0;idsCounter<ids.length;idsCounter++){
            $('#' + ids[idsCounter]).addClass('ui-state-disabled');
            $('#' + ids[idsCounter]).attr("disabled", true);
        }
    }


    function submitSharayeteJadid(){
        var data = $("#sharayetejadid").serialize();
        $.post("/calculateForFormeMiani.action",data,function(msg){
            $("#resultholderforcalculation").html(msg);
            if($("#iscorrectcalculation").val() == "true"){
                <c:if test="${pishnehad.state.id==230}">
                    $("#sharayetejadidtransid").val(31);
                    openDialogBoxAndSubmitTo("taghirlgmsg","sharayetejadid", "پیشنهاد شرایط جدید");
                </c:if>
                <c:if test="${pishnehad.state.id==170}">
                    $("#sharayetejadidtransid").val(22);
                    openDialogBoxAndSubmitTo("taghirlgmsg","sharayetejadid", "پیشنهاد شرایط جدید");
                </c:if>
                <c:if test="${pishnehad.state.id==140}">
                    $("#sharayetejadidtransid").val(71);
                    openDialogBoxAndSubmitTo("taghirlgmsg","sharayetejadid", "پیشنهاد شرایط جدید");
                </c:if>
                <c:if test="${pishnehad.state.id==130}">
                    $("#sharayetejadidtransid").val(72);
                    openDialogBoxAndSubmitTo("taghirlgmsg","sharayetejadid", "پیشنهاد شرایط جدید");
                </c:if>
                <c:if test="${pishnehad.state.id==90}">
                    $("#sharayetejadidtransid").val(73);
                    openDialogBoxAndSubmitTo("taghirlgmsg","sharayetejadid", "پیشنهاد شرایط جدید");
                </c:if>
                <c:if test="${pishnehad.state.id==120}">
                    $("#sharayetejadidtransid").val(56);
                    openDialogBoxAndSubmitTo("taghirlgmsg","sharayetejadid", "پیشنهاد شرایط جدید");
//                    $("#sharayetejadid").submit();
                </c:if>


            }else{
                wrongParameteres('wrongParameteres');
            }
        });


    }
    function wrongParameteres(param){
        $.validationEngine.closePrompt('.formError', true);
        $('#' + param).dialog({
            modal:true,
            width: 620,
            resizable:false,
            closeText: "",
            title:"خطا در مقدار دهي پارامترها",
            close:function() {$.validationEngine.closePrompt('.formError', true);},
            buttons: {
                "بستن": function() {
                    $.validationEngine.closePrompt('.formError', true);
                    $(this).dialog("close");
                }
            }
        });
    }
</script>
