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


function calculateSen() {
    var seneShakhs = mohasebeyeSen($('#tarikh_tavalod').val(), 'S');
    if(seneShakhs < 0){
        $('#tarikh_tavalod').val('');
        $('#sen_bime_shode').val('0');
        $('#sen_bime_shode_v').val('');
        return;
    }

    $('#sen_bime_shode').val(seneShakhs);
    $('#sen_bime_shode_v').val(seneShakhs);

    if(seneShakhs>=20 && seneShakhs<60){
        $('#mode5').buttonset().buttonset("enable");
    }else{
        $('#mode51').attr('checked',true);
        $('#mode52').removeAttr('checked');
        $('#mode5').buttonset().buttonset("refresh");
        $('#mode5').buttonset().buttonset("disable");
        deactivate('sarmaye_pooshesh_amraz_khas');
    }
    if(seneShakhs>=18 && seneShakhs<60){
        $('#mode2').buttonset().buttonset("enable");
    }else{
        $('#mode21').attr('checked',true);
        $('#mode22').removeAttr('checked');
        $('#mode2').buttonset().buttonset("refresh");
        $('#mode2').buttonset().buttonset("enable");
    }

    if(seneShakhs<15){
//        $('#mode11').attr('checked',true);
//        $('#mode31').attr('checked',true).change();
        $('#mode41').attr('checked',true).change();
//        $('#mode12').removeAttr('checked');
//        $('#mode32').removeAttr('checked');
        $('#mode42').removeAttr('checked').change();
//        $('#mode1').buttonset().buttonset("refresh");
//        $('#mode3').buttonset().buttonset("refresh");
        $('#mode4').buttonset().buttonset("refresh");
        $('#nerkh_afzayesh_salaneh_sarmaye_fot').val('0 درصد');
        deactivateArray(new Array('sarmaye_paye_fot_dar_asar_hadese','sarmaye_pooshesh_naghs_ozv', 'nerkh_afzayesh_salaneh_sarmaye_fot', 'tabaghe_khatar'));
        $('#nerkh_afzayesh_salaneh_sarmaye_fot').focusout();
//        $('#mode1').buttonset().buttonset("disable");
//        $('#mode3').buttonset().buttonset("disable");
        $('#mode4').buttonset().buttonset("disable");
    }else{
        activateArray(new Array('nerkh_afzayesh_salaneh_sarmaye_fot'));
        $('#mode4').buttonset().buttonset("enable");
    }
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
    $('#noe_tarh').val('gheir_jamee').change();
    $('#mode42').removeAttr('checked').change();
    $('#mode41').attr('checked', true).change();
    $('#mode52').removeAttr('checked').change();
    $('#mode51').attr('checked', true).change();
    $('#mode21').attr('checked', true).change();
    $('#mode22').removeAttr('checked').change();

    $('#result').html('');
    $('.prt').addClass('ui-state-disabled');
    $('.prt').attr('disabled', true);
}

function mohasebeReverse(){
    $.validationEngine.onSubmitValid = true;
    $('.content').slideDown(0);

    if($.validationEngine.submitValidation($('#mainFormReverse')) === false){
        var mainForm2 = $('#mainFormReverse').serialize();

        $.post('/calculateReverseEstelamAction.action', mainForm2, function(msg) {
            if( msg.indexOf('Error:WrongFieldsValue') == -1 && msg.indexOf('Error:ExtaWrongFieldsValue') == -1){
                $('#result').html(msg);
                $('.prt').removeClass('ui-state-disabled');
                $('.prt').removeAttr('disabled');
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

function sarmayePayeFotChange(){
    if($('#sarmaye_paye_fot').val() && $('#sarmaye_paye_fot').val() != ''){
        if(!$('#sarmaye_paye_fot_dar_asar_hadese').is(':disabled')){
            var v = jQuery.global.format(jQuery.global.parseInt($('#sarmaye_paye_fot').val()), "n0");
            $('#sarmaye_paye_fot_dar_asar_hadese').val(v).change();
        }
        if(!$('#sarmaye_pooshesh_naghs_ozv').is(':disabled')){
            var v = jQuery.global.format(jQuery.global.parseInt($('#sarmaye_paye_fot').val()), "n0");
            $('#sarmaye_pooshesh_naghs_ozv').val(v).change();
        }
        if(!$('#sarmaye_pooshesh_amraz_khas').is(':disabled')){
            if((0.3*jQuery.global.parseInt($('#sarmaye_paye_fot').val()))>200000000){
                $('#sarmaye_pooshesh_amraz_khas').val('300,000,000').change();
            }else{
                var v = jQuery.global.format(0.3*jQuery.global.parseInt($('#sarmaye_paye_fot').val()), "n0");
                $('#sarmaye_pooshesh_amraz_khas').val(v).change();
            }
        }
    }
}
function pushesheFoutDarAsareHadeseNadarad(){
    document.getElementById('zelzele').value ="no"
    $('#sarmaye_paye_fot_dar_asar_hadese').val('0');
    deactivate('sarmaye_paye_fot_dar_asar_hadese');
    $('#mode31').attr('checked', true).change();
    $('#mode32').removeAttr('checked').change();
}
function pushesheFoutDarAsareHadeseDarad(){
    document.getElementById('zelzele').value="yes";
    activation('sarmaye_paye_fot_dar_asar_hadese');
    sarmayePayeFotChange();
    $('#mode32').attr('checked', true).change();
    $('#mode31').removeAttr('checked').change();
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
                    $.post("/saveNazar.action", data,function(msg){
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
