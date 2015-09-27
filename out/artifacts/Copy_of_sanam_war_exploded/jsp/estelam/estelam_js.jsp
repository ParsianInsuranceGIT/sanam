<%@ page import="java.util.HashMap" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 6/8/11
  Time: 9:03 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">



var mod4Button;
$(function() {
    $('#takhfifeGrouhi').children(".content").hide();
       //            $("#modat_bimename").tipsy();
    $(".help").tipsy({gravity:'s'});
    $(".help").addClass("ui-icon ui-icon-help");
});

var numBimeShode = 1;

function addBimeShode() {
    if(numBimeShode == 4) {
        alertMessage("حداکثر تعداد بیمه شده ها 4 عدد است.");
        return;
    }
    numBimeShode++;
    var newDivId = "bimeShode"+numBimeShode;
    $('#other_bimeshode').append("<div id='"+newDivId+"'>"+$('#bimeShode1').html()+"</div>");
    $('#'+newDivId+' fieldset legend').text("مشخصات بیمه شده " +getOrdinal(numBimeShode));
    scrollTo(newDivId);
}

function removeBimeShode() {
    if(numBimeShode == 1) {
        alertMessage("حداقل تعداد بیمه شده ها 1 عدد است.");
        return;
    }
    $("#bimeShode"+numBimeShode).remove();
    numBimeShode--;
}


function calculateSen() {
    <%--<c:if test="${pishnehad.bimename.tarikhShorou != null && !isHalateElhaghie && darkhastTaghirat.state.id==null}">--%>
    var seneShakhs_v = mohasebeyeSen($('#tarikh_tavalod').val(), '${pishnehad!=null && pishnehad.bimename!=null?pishnehad.bimename.tarikhShorou:'S'}');
    <%--</c:if>--%>
    <%--<c:if test="${pishnehad.bimename.tarikhShorou == null || (isHalateElhaghie || darkhastTaghirat.state.id!=null)}">--%>
    var seneShakhs = mohasebeyeSen($('#tarikh_tavalod').val(), 'S');
    <%--</c:if>--%>
    getValidationConstAjax();
    var maxSenBimeshode = $('#validation_max_sen_bimeshode').val();<%--${validationConstants.get("MAXSENEBIMESHODE")};--%>
    var minSenBimeshode = $('#validation_min_sen_bimeshode').val();<%--${validationConstants.get("MINSENEBIMESHODE")};--%>
    if(maxSenBimeshode=='' || minSenBimeshode=='')
    {
        maxSenBimeshode = ${validationConstants.get("MAXSENEBIMESHODE")};
        minSenBimeshode = ${validationConstants.get("MINSENEBIMESHODE")};
    }
    if(seneShakhs < minSenBimeshode || seneShakhs > maxSenBimeshode || seneShakhs < 0){
        $('#tarikh_tavalod').val('');
        $('#sen_bime_shode').val('0');
        $('#sen_bime_shode_v').val('');
        return;
    }
    $('#sen_bime_shode').val(seneShakhs);
    $('#sen_bime_shode_v').val(seneShakhs_v);
    $('#sen_bime_shode_vv').val(seneShakhs_v);

    getValidationConstAjax();
    $('#tarikh_tavalod').focusout();
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

function resetForm(){
    $.validationEngine.closePrompt('.formError', true);
    $('input:text').val('').change();
    $('#nam_bime_shode').val('').change();
    $('#tarikh_tavalod').val('').change();
    $('#darsad_ezafe_nerkh_pezeshki').val('0').change();
    $('#sen_bime_shode_v').val('').change();
    $('#sen_bime_shode').val('').change();
    $('#sarmaye_paye_fot').val('30,000,000').change();
    $('#nerkh_afzayesh_salaneh_sarmaye_fot').val('0').change();
    $('#nahve_pardakht_hagh_bime').val('mah').change();
    $('#hagh_bime_pardakhti').val('300,000').change();
    $('#nerkh_afzayesh_salaneh_hagh_bime').val('0').change();
    $('#mablagh_seporde_ebtedaye_sal').val('0').change();
    $('#modat_bimename').val('20').change();
    $('#nahveye_daryafte_andukhte_entehaye_modat_bimename').val('yekja').change();
    $('#nerkh_afzayesh_salaneh_mostamari').val('0').change();
    $('#nahve_daryaft_mostamari').val('mah').change();
    $('#moddat_zaman_daryaft_mostamari').val('0').change();
    $('#sarmaye_paye_fot_dar_asar_hadese').val('0').change();
    $('#sarmaye_pooshesh_naghs_ozv').val('0').change();
    $('#sarmaye_pooshesh_amraz_khas').val('0').change();
    $('#darsad_takhfif_karmozd_karmozd').val('0').change();
    $('#darsad_takhfif_vosool').val('0').change();
//    $('#noe_tarh').val('gheir_jamee').change();
    $('#mode42').removeAttr('checked').change();
    $('#mode41').attr('checked', true).change();
    $('#mode52').removeAttr('checked').change();
    $('#mode51').attr('checked', true).change();
    $('#mode21').attr('checked', true).change();
    $('#mode22').removeAttr('checked').change();

    $('#result').html('');
    $('.prt').addClass('ui-state-disabled');
    $('.prt').attr('disabled', true);
    $("#dokme_mostamari_from_2").hide();
    $("#dokme_mostamari").hide();

}

function mohasebeReverse(){

    $.validationEngine.onSubmitValid = true;
    $('.content').slideDown(0);

    if($.validationEngine.submitValidation($('#mainFormReverse')) === false){
        var mainForm2 = $('#mainFormReverse').serialize();

        $.post('/calculateReverseEstelamAction', mainForm2, function(msg) {


            if( msg.indexOf('Error:WrongFieldsValue') == -1 && msg.indexOf('Error:ExtaWrongFieldsValue') == -1){
                $('#result').html(msg);
                $('.prt').removeClass('ui-state-disabled');
                $('.prt').removeAttr('disabled');
                $("#dokme_mostamari_from_2").show();
            }else if(msg.indexOf('Error:WrongFieldsValue') == -1){
                wrongParameteres('extraWrongParameteres');
            }else{
                wrongParameteres('wrongParameteres');
            }

            return false;
        });
        return false;
    }else{
        return false;
    }
}
function mohasebe(){
    $.validationEngine.onSubmitValid = true;
    $('.content').slideDown(0);
    if($.validationEngine.submitValidation($('#mainForm')) === false){
        var mainForm2 = $('#mainForm').serialize();
        $.post('/calculateEstelamAction', mainForm2, function(msg) {
            if( msg.indexOf('Error:WrongFieldsValue') == -1 && msg.indexOf('Error:ExtaWrongFieldsValue') == -1){
                $('#result').html(msg);
                $('.prt').removeClass('ui-state-disabled');
                $('.prt').removeAttr('disabled');
                $("#dokme_mostamari").show();
            }else if(msg.indexOf('Error:WrongFieldsValue') == -1){
                wrongParameteres('extraWrongParameteres');
            }else{
                wrongParameteres('wrongParameteres');
            }

            return false;
        });
        return false;
    }else{
        return false;
    }
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

function sarmayePayeFotChange(typing){
    if($('#sarmaye_paye_fot').val() && $('#sarmaye_paye_fot').val() != ''){
        if(!$('#sarmaye_paye_fot_dar_asar_hadese').is(':disabled')){
            <c:if test="${estelam.pooshesh_fot_dar_asar_haadese == 'yes'}">
            var v = jQuery.global.format(jQuery.global.parseInt('${estelam.sarmaye_paye_fot_dar_asar_hadese}'), "n0");
            </c:if>
            <c:if test="${estelam.pooshesh_fot_dar_asar_haadese != 'yes'}">
            var v = jQuery.global.format(jQuery.global.parseInt($('#sarmaye_paye_fot').val()), "n0");
            </c:if>
            if(typing)
                v = jQuery.global.format(jQuery.global.parseInt($('#sarmaye_paye_fot').val()), "n0");
            $('#sarmaye_paye_fot_dar_asar_hadese').val(v).change();
        }
    }
}
function sarmayeAmrazChange(typing){
    if($('#sarmaye_paye_fot').val() && $('#sarmaye_paye_fot').val() != ''){
        if(!$('#sarmaye_pooshesh_amraz_khas').is(':disabled')){
            <c:if test="${estelam.pooshesh_amraz_khas == 'yes'}">
            if(typing)
            {
                if((0.3*jQuery.global.parseInt($('#sarmaye_paye_fot').val()))>200000000){
                    $('#sarmaye_pooshesh_amraz_khas').val('300,000,000').change();
                }else{
                    var v = jQuery.global.format(0.3*jQuery.global.parseInt($('#sarmaye_paye_fot').val()), "n0");
                    $('#sarmaye_pooshesh_amraz_khas').val(v).change();
                }
            }
            else
            {
                var v = jQuery.global.format(jQuery.global.parseInt('${estelam.sarmaye_pooshesh_amraz_khas}'), "n0");
                $('#sarmaye_pooshesh_amraz_khas').val(v).change();
            }
            </c:if>
            <c:if test="${estelam.pooshesh_amraz_khas != 'yes'}">
            if((0.3*jQuery.global.parseInt($('#sarmaye_paye_fot').val()))>200000000){
                $('#sarmaye_pooshesh_amraz_khas').val('300,000,000').change();
            }else{
                var v = jQuery.global.format(0.3*jQuery.global.parseInt($('#sarmaye_paye_fot').val()), "n0");
                $('#sarmaye_pooshesh_amraz_khas').val(v).change();
            }
            </c:if>
        }
    }
}
function sarmayePoosheshNaghsChange(typing){
    if($('#sarmaye_paye_fot').val() && $('#sarmaye_paye_fot').val() != ''){
        if(!$('#sarmaye_pooshesh_naghs_ozv').is(':disabled')){
            <c:if test="${estelam.pooshesh_naghs_ozv == 'yes'}">
            var v = jQuery.global.format(jQuery.global.parseInt('${estelam.sarmaye_pooshesh_naghs_ozv}'), "n0");
            if(typing)
            {
                var sarPayeFot =jQuery.global.parseInt($('#sarmaye_paye_fot').val());
                var sarFotHadeshe=jQuery.global.parseInt($('#sarmaye_paye_fot_dar_asar_hadese').val());
                var min = sarPayeFot;
                if(min>sarFotHadeshe) min=sarFotHadeshe;
                if(min > 500000000) min = 500000000;
                v = jQuery.global.format(min, "n0");
            }
            </c:if>
            <c:if test="${estelam.pooshesh_naghs_ozv != 'yes'}">
            var sarPayeFot =jQuery.global.parseInt($('#sarmaye_paye_fot').val());
            var sarFotHadeshe=jQuery.global.parseInt($('#sarmaye_paye_fot_dar_asar_hadese').val());
            var min = sarPayeFot;
            if(min>sarFotHadeshe) min=sarFotHadeshe;
            if(min > 500000000) min = 500000000;
            var v = jQuery.global.format(min, "n0");
            </c:if>
//            var v1 = jQuery.global.format(jQuery.global.parseInt($('#sarmaye_paye_fot_dar_asar_hadese').val()), "n0");
//            var v2 = jQuery.global.format(jQuery.global.parseInt(v)+jQuery.global.parseInt(v1));
            $('#sarmaye_pooshesh_naghs_ozv').val(v).change();
        }
    }
}
function pushesheFoutDarAsareHadeseNadarad(){
    document.getElementById('zelzele').value ="no";
    $('#sarmaye_paye_fot_dar_asar_hadese').val('0');
    deactivate('sarmaye_paye_fot_dar_asar_hadese');
    $('#mode31').attr('checked', true).change();
    $('#mode32').removeAttr('checked').change();
    $('#mode1').buttonset().buttonset("disable");
    deactivate('sarmaye_pooshesh_naghs_ozv');
    $('#mode11').attr('checked', true).change();
    $('#mode12').removeAttr('checked').change();
    $("#sarmaye_pooshesh_naghs_ozv").val(0);
}
function pushesheFoutDarAsareHadeseDarad(){

    document.getElementById('zelzele').value="yes";
    activation('sarmaye_paye_fot_dar_asar_hadese');
    sarmayePayeFotChange(false);
    $('#mode32').attr('checked', true).change();
    $('#mode31').removeAttr('checked').change();
    $('#mode1').buttonset().buttonset("enable");

    if($("#mode12").attr('checked')==true){
        activation('sarmaye_pooshesh_naghs_ozv');
    }
}

function saveEzafeNerkhAjaxly() {
    var oldValue = '${pishnehad.estelam.darsad_ezafe_nerkh_pezeshki}';
    $.post("/saveEzafeNerkhAjaxly?pishnehad.id=${pishnehad.id}&darsad_ezafe="+$('#estelam_darsad_ezafe').val()+"&fromReadOnlyMode=${isHalateElhaghie}", function(msg){
        if(msg.indexOf('done') != -1) {
            alertMessage('اضافه نرخ با موفقیت ثبت شد.');
        } else if(msg.indexOf('uneditable') != -1) {
            $('#estelam_darsad_ezafe').val(oldValue);
            alertMessage('اضافه نرخ قابل ویرایش نیست. تغییرات شما ثبت نشد.');
        } else if(msg.indexOf('elhaghie') != -1) {
            alertMessage('اضافه نرخ در الحاقیه منظور شد.');
        }
    });
}

function addComment(){
    $('#addCommetnDiv').dialog({
        modal:true,
        width: 640,
        resizable:false,
        closeText: "",
        title:"نظر شما",
        close:function() {
            $('#addCommetnDiv input, #addCommetnDiv textarea').val('');
            $.validationEngine.closePrompt('.formError', true);
            $('#mesg').hide();
        },
        buttons: {
            "بستن": function() {
                $(this).dialog("close");
            },
            "ارسال": function(){
                $.validationEngine.onSubmitValid = true;
                if($.validationEngine.submitValidation($('#addCommetnDiv')) === false){
                    var data = $('#addCommetnForm').serialize();
                    $.post("/saveNazar", data,function(msg){
                        $.validationEngine.closePrompt('.formError', true);
                        $('#mesg').show();
                        $('#addCommetnDiv input, #addCommetnDiv textarea').val('');
                        setTimeout("closeComment()",2000);
                    })
                }
            }
        }
    });
}
function closeComment(){
    $('#addCommetnDiv').dialog("close");
}

function submitNewTabagheKhatar(){
    <c:if test="${darkhastTaghirat == null}">
    $.post('/changeTabagheKhatarAjaxly?pishnehad.id=${pishnehad.id}&pishnehad.estelam.tabaghe_khatar='+$("#pishnehad_estelam_tabaghe_khatar").val()+'&pishnehad.estelam.tabaghe_khatar_naghsozv='+$("#pishnehad_estelam_tabaghe_khatar_naghsozv").val(),function(msg) {
        });
    }
    </c:if>
    <c:if test="${darkhastTaghirat != null}">
    var tabaghe_khatar='1'
    var tabaghe_khatar_naghs='1'
    <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_haadese == 'yes'}">
        tabaghe_khatar= $("#pishnehad_estelam_tabaghe_khatar").val();
    </c:if>
    <c:if test="${pishnehad.estelam.pooshesh_naghs_ozv == 'yes'}">
        tabaghe_khatar_naghs= $("#pishnehad_estelam_tabaghe_khatar_naghsozv").val();
    </c:if>
    $.post('/changeTabagheKhatarAjaxly?pishnehad.id=${darkhastTaghirat.newPishnehad.id}&pishnehad.estelam.tabaghe_khatar='+tabaghe_khatar+'&pishnehad.estelam.tabaghe_khatar_naghsozv='+tabaghe_khatar_naghs,function(msg) {
        });
    }
    </c:if>

function val_majmoeSenVaModdateBimeName() {
    if ($('#validation_max_majmo_modatvasen').val() != null && $('#validation_max_majmo_modatvasen').val().length > 0)
        return ((($('#sen_bime_shode_v').val()!=''?jQuery.global.parseInt($('#sen_bime_shode_v').val()):jQuery.global.parseInt('${estelam.sen_bime_shode}')) + jQuery.global.parseInt($('#modat_bimename').val())) <= $('#validation_max_majmo_modatvasen').val());
    return true;
}

function val_majmoeSenVaModdateBimeName_ts(){
    return ((jQuery.global.parseInt($('#sen_bime_shode').val())+jQuery.global.parseInt($('#innermodat_bimename').val())) <= $('#validation_max_majmo_modatvasen').val());
}

function val_darsadMojazAfzayeshSarmayeFot(){
    var darsads = $('#validation_darsad_mojaz_afzayesh_sarmaye_fot').val();
    var darsadsMojaz = darsads.split(",");

    for (i=0; i<darsadsMojaz.length; i++){
        if (jQuery.global.parseInt($('#nerkh_afzayesh_salaneh_sarmaye_fot').val()) == darsadsMojaz[i])
            return true;
    }
    return false;
}

function getValidationPoshesheEzafi(poshesheEzafi,senBimeShodeVar){
    if (poshesheEzafi.indexOf('|') > -1){
        var poshesheEzafi_Mahdode = poshesheEzafi.split('|');
        if (poshesheEzafi_Mahdode != undefined){
            var poshesheEzafi_Mahdode_SEN_EN_DIS = (poshesheEzafi_Mahdode[0].split(','));
            var poshesheEzafi_Mahdode_SEN_DIS = poshesheEzafi_Mahdode_SEN_EN_DIS[0].split('-');
            if (Number(poshesheEzafi_Mahdode_SEN_DIS[0]) <= senBimeShodeVar && Number(poshesheEzafi_Mahdode_SEN_DIS[1]) >= senBimeShodeVar ){
                return false;
            }
            else{
                var poshesheEzafi_Mahdode_SEN_EN_EN = (poshesheEzafi_Mahdode[1].split(','));
                var poshesheEzafi_Mahdode_SEN_EN = poshesheEzafi_Mahdode_SEN_EN_EN[0].split('-');
                if (Number(poshesheEzafi_Mahdode_SEN_EN[0]) <= senBimeShodeVar && Number(poshesheEzafi_Mahdode_SEN_EN[1]) >= senBimeShodeVar ){
                    return true;
                }
            }
        }
    } else {
        if (poshesheEzafi == 1){
            return true;
        }
        return false;
    }

}

function setEnableComponentPosheshHayeEzafi(POSHESHFOTDARASARHADESE,POSHESENAGHZOZV,POSHESHEAMRAZKHAS,POSHESHEMOAFIYAT){
    <c:if test="${pishnehad.bimename.tarikhShorou != null && !isHalateElhaghie && darkhastTaghirat.state.id==null}">
    var senBimeShodeVar = mohasebeyeSen($('#tarikh_tavalod').val(), '${pishnehad.bimename.tarikhShorou}');
    </c:if>
    <c:if test="${pishnehad.bimename.tarikhShorou == null || (isHalateElhaghie || darkhastTaghirat.state.id!=null)}">
    var senBimeShodeVar = mohasebeyeSen($('#tarikh_tavalod').val(), 'S');
    </c:if>
    if (!getValidationPoshesheEzafi(POSHESHFOTDARASARHADESE,senBimeShodeVar)){
        $('#mode41').attr('checked',true).change();
        $('#mode42').removeAttr('checked').change();
        $('#mode4').buttonset().buttonset("refresh");
        $('#mode4').buttonset().buttonset("disable");
        deactivateArray(new Array('sarmaye_paye_fot_dar_asar_hadese'));
    }else{
        $('#mode4').buttonset().buttonset("refresh");
        $('#mode4').buttonset().buttonset("enable");
    }


    if (!getValidationPoshesheEzafi(POSHESENAGHZOZV,senBimeShodeVar)){
        $('#mode11').attr('checked',true).change();
        $('#mode12').removeAttr('checked').change();
        $('#mode1').buttonset().buttonset("refresh");
        $('#mode1').buttonset().buttonset("disable");
        deactivateArray(new Array('sarmaye_pooshesh_naghs_ozv'));
    }else{
        $('#mode1').buttonset().buttonset("refresh");
        $('#mode1').buttonset().buttonset("enable");
    }

    if (!getValidationPoshesheEzafi(POSHESHEAMRAZKHAS,senBimeShodeVar)){
        $('#mode51').attr('checked',true).change();
        $('#mode52').removeAttr('checked').change();
        $('#mode5').buttonset().buttonset("refresh");
        $('#mode5').buttonset().buttonset("disable");
        deactivateArray(new Array('sarmaye_pooshesh_amraz_khas'));
    }else{
        $('#mode5').buttonset().buttonset("refresh");
        $('#mode5').buttonset().buttonset("enable");
    }

    if (!getValidationPoshesheEzafi(POSHESHEMOAFIYAT,senBimeShodeVar)){
        $('#mode21').attr('checked',true).change();
        $('#mode22').removeAttr('checked').change();
        $('#mode2').buttonset().buttonset("refresh");
        $('#mode2').buttonset().buttonset("disable");
    }else{
        $('#mode2').buttonset().buttonset("refresh");
        $('#mode2').buttonset().buttonset("enable");
    }

    if ($('#nahve_pardakht_hagh_bime').val() == 'yekja') {
        $('#mode21').attr('checked', true).change();
        $('#mode22').removeAttr('checked').change();
        $('#mode2').buttonset().buttonset("refresh");
        $('#mode2').buttonset().buttonset("disable");
    }
}

function getValidationConstAjax(){
    if ($('#pishnehad_nameTarh').val() == undefined) {
        $.ajax({
            type: "POST",
//            date: "tarhFormChanged=" + $('#pishnehad_nameTarh').val(),
            url: "getValidationConstAjax",
            success: function (response) {
                var records = response.split('#');
                $('#validation_darsad_mojaz_afzayesh_sarmaye_fot').val(records[0].split('=')[1]);
                $('#validation_max_majmo_modatvasen').val(records[1].split('=')[1]);
                $('#validation_min_sen_bimeshode').val(records[2].split('=')[1]);
                $('#validation_max_sen_bimeshode').val(records[3].split('=')[1]);
                $('#validation_mahdode_paye_sarmaye_fot').val(records[4].split('=')[1]);

                var POSHESHFOTDARASARHADESE = records[5].split('=')[1];
                var POSHESENAGHZOZV = records[6].split('=')[1];
                var POSHESHEAMRAZKHAS = records[7].split('=')[1];
                var POSHESHEMOAFIYAT = records[8].split('=')[1];
                setEnableComponentPosheshHayeEzafi(POSHESHFOTDARASARHADESE,POSHESENAGHZOZV,POSHESHEAMRAZKHAS,POSHESHEMOAFIYAT);

                $('#validation_min_hagh_bime_pardakhti_mah').val(records[9].split('=')[1]);
            }
        });
    } else{

        $.ajax({
            type: "POST",
            date: "tarhFormChanged=" + $('#pishnehad_nameTarh').val(),
            url: "getValidationConstAjax?tarhFormChanged="+$('#pishnehad_nameTarh').val()+"&pishnehad.id=${pishnehad.id}",
            success: function (response) {
                var records = response.split('#');
                $('#validation_darsad_mojaz_afzayesh_sarmaye_fot').val(records[0].split('=')[1]);
                $('#validation_max_majmo_modatvasen').val(records[1].split('=')[1]);
                $('#validation_min_sen_bimeshode').val(records[2].split('=')[1]);
                $('#validation_max_sen_bimeshode').val(records[3].split('=')[1]);
                $('#validation_mahdode_paye_sarmaye_fot').val(records[4].split('=')[1]);

                var POSHESHFOTDARASARHADESE = records[5].split('=')[1];
                var POSHESENAGHZOZV = records[6].split('=')[1];
                var POSHESHEAMRAZKHAS = records[7].split('=')[1];
                var POSHESHEMOAFIYAT = records[8].split('=')[1];
                //setEnableComponentPosheshHayeEzafi(POSHESHFOTDARASARHADESE,POSHESENAGHZOZV,POSHESHEAMRAZKHAS,POSHESHEMOAFIYAT);

                $('#validation_min_hagh_bime_pardakhti_mah').val(records[9].split('=')[1]);

                <c:if test="${pishnehad.bimename.tarikhShorou != null && !isHalateElhaghie && darkhastTaghirat.state.id==null}">
                var senBimeShodeVar = mohasebeyeSen($('#tarikh_tavalod').val(), '${pishnehad.bimename.tarikhShorou}');
                </c:if>
                <c:if test="${pishnehad.bimename.tarikhShorou == null || (isHalateElhaghie || darkhastTaghirat.state.id!=null)}">
                var senBimeShodeVar = mohasebeyeSen($('#tarikh_tavalod').val(), 'S');
                </c:if>

                if (!getValidationPoshesheEzafi(POSHESHFOTDARASARHADESE,senBimeShodeVar)){
                    $('#mode41').attr('checked',true).change();
                    $('#mode42').removeAttr('checked').change();
                    $('#mode4').buttonset().buttonset("refresh");
                    $('#mode4').buttonset().buttonset("disable");
                    deactivateArray(new Array('sarmaye_paye_fot_dar_asar_hadese'));
                }else{
                    $('#mode4').buttonset().buttonset("refresh");
                    $('#mode4').buttonset().buttonset("enable");
                }


                if (!getValidationPoshesheEzafi(POSHESENAGHZOZV,senBimeShodeVar)){
                    $('#mode11').attr('checked',true).change();
                    $('#mode12').removeAttr('checked').change();
                    $('#mode1').buttonset().buttonset("refresh");
                    $('#mode1').buttonset().buttonset("disable");
                    deactivateArray(new Array('sarmaye_pooshesh_naghs_ozv'));
                }else{
                    $('#mode1').buttonset().buttonset("refresh");
                    $('#mode1').buttonset().buttonset("enable");
                }

                if (!getValidationPoshesheEzafi(POSHESHEAMRAZKHAS,senBimeShodeVar)){
                    $('#mode51').attr('checked',true).change();
                    $('#mode52').removeAttr('checked').change();
                    $('#mode5').buttonset().buttonset("refresh");
                    $('#mode5').buttonset().buttonset("disable");
                    deactivateArray(new Array('sarmaye_pooshesh_amraz_khas'));
                }else{
                    $('#mode5').buttonset().buttonset("refresh");
                    $('#mode5').buttonset().buttonset("enable");
                }

                if (!getValidationPoshesheEzafi(POSHESHEMOAFIYAT,senBimeShodeVar)){
                    $('#mode21').attr('checked',true).change();
                    $('#mode22').removeAttr('checked').change();
                    $('#mode2').buttonset().buttonset("refresh");
                    $('#mode2').buttonset().buttonset("disable");
                }else{
                    $('#mode2').buttonset().buttonset("refresh");
                    $('#mode2').buttonset().buttonset("enable");
                }

                if ($('#nahve_pardakht_hagh_bime').val() == 'yekja') {
                    $('#mode21').attr('checked', true).change();
                    $('#mode22').removeAttr('checked').change();
                    $('#mode2').buttonset().buttonset("refresh");
                    $('#mode2').buttonset().buttonset("disable");
                }
            }
        });
    }


}

</script>
<div id="wrongParameteres" style="display:none;">
    <div class="failureMessage">
        حق بيمه ي شما براي برآورده كردن تعهدات كافي نيست، لازم است كه حق بيمه پرداختي بيشتر و يا سرمايه فوت كمتر گردد.
    </div>
</div>
<div id="extraWrongParameteres" style="display:none;">
    <div class="failureMessage">
        مبلغ اندوخته سرمايه گذاري صفر و يا منفي است. بايستي سرمايه فوت كاهش و يا مبلغ حق بيمه افزايش پيدا كند.
    </div>
</div>

