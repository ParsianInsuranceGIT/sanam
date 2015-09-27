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

function setButtonSets() {
    calculateSenMultiple(1);
    calculateSenMultiple(2);
    calculateSenMultiple(3);
    calculateSenMultiple(4);
}

var numBimeShodeMultiple = 1;

function addBimeShodeMultiple() {
    //add moshakhaste bime shode for next person
    if (numBimeShodeMultiple == 4) {
        alertMessage("حداکثر تعداد بیمه شده ها 4 عدد است.");
        return;
    }
    numBimeShodeMultiple++;

//    $("#mode111").removeAttr('disabled');
//    $("#mode112").removeAttr('disabled');

    $('.dblRadio').buttonset("destroy");

    var newDivId = "bimeShode" + numBimeShodeMultiple;

    var appendHtml = "<div id='" + newDivId + "'>" + $('#bimeShode1').html() + "</div>";
    while (appendHtml.indexOf("estelamBimeShodes[0]") != -1) {
        appendHtml = appendHtml.replace("estelamBimeShodes[0]", "estelamBimeShodes[" + (numBimeShodeMultiple - 1) + "]");
    }
    while (appendHtml.indexOf("var personNo = 1;") != -1) {
        appendHtml = appendHtml.replace("var personNo = 1;", "var personNo = " + numBimeShodeMultiple + ";")
    }
    while (appendHtml.indexOf("_pers1") != -1) {
        appendHtml = appendHtml.replace("_pers1", "_pers" + numBimeShodeMultiple);
    }

    appendHtml = appendHtml.replace("bimeShodeHeader1", "bimeShodeHeader" + numBimeShodeMultiple);
    appendHtml = appendHtml.replace("مشخصات بیمه شده اول", "مشخصات بیمه شده " + getOrdinal(numBimeShodeMultiple));
    appendHtml = appendHtml.replace("<img id=\'tarikh_tavalod_pers1_btn\' class=\'datePkr_btn\' src=\'/resource_lib/cal.png\'/>","");
    $('#other_bimeshode').append(appendHtml);

    scrollTo(newDivId);

    //add pusheshhaye bime for next person
    newDivId = "pusheshHayeBimeShode" + numBimeShodeMultiple;

    appendHtml = "<div id = '" + newDivId + "'> " + $('#pusheshHayeBimeShode1').html() + "</div>";
    while (appendHtml.indexOf("estelamBimeShodes[0]") != -1) {
        appendHtml = appendHtml.replace("estelamBimeShodes[0]", "estelamBimeShodes[" + (numBimeShodeMultiple - 1) + "]");
    }
    while (appendHtml.indexOf("var personNo = 1;") != -1) {
        appendHtml = appendHtml.replace("var personNo = 1;", "var personNo = " + numBimeShodeMultiple + ";");
    }
    while (appendHtml.indexOf("_pers1") != -1) {
        appendHtml = appendHtml.replace("_pers1", "_pers" + numBimeShodeMultiple);
    }
    appendHtml = appendHtml.replace("بيمه شده اول", "بيمه شده " + getOrdinal(numBimeShodeMultiple));


    $('#pusheshayeBimeShodeOthers').append(appendHtml);

    if (numBimeShodeMultiple > 1) {
        $("#pooshesh_moafiat_pers" + numBimeShodeMultiple).attr("style", "display:none");
    }


    //add zelzele field for next person
    $('#mainForm').append('<input type="hidden" id="zelzele_pers' + numBimeShodeMultiple + '" name="estelamKhanevade.estelamBimeShodes[' + (numBimeShodeMultiple - 1) + '].pusheshFotBarAsareZelzele" value="no"/>');

    //add isRegistered field for next person
    $("#mainForm").append('<input type="hidden" id="is_registered_pers' + numBimeShodeMultiple + '" name="estelamKhanevade.estelamBimeShodes[' + (numBimeShodeMultiple - 1) + '].Registered" value="yes"/>');

    //bind click function for all expandableTitleBars
    $(".expandableTitleBar").each(function() {
        //                        $(this).children(".content").hide();
        $(this).children(".heading").addClass("ui-widget-header ui-corner-all ui-helper-clearfix");
        $(this).children(".heading").unbind('click');
        $(this).children(".heading").click(function() {
            jQuery(this).next(".content").slideToggle(500);
            $.validationEngine.closePrompt('.formError', true);
        });
    });


    $('.dblRadio').buttonset();

    $('#tarikh_tavalod_pers' + numBimeShodeMultiple).val('');

    setButtonSets();

    $("form, .vld").validationEngine({promptPosition:"topLeft"});

    $(".help, .comment").tipsy({gravity:'s'});

    $(".datePkr").each(function() {
        var inputFieldId = this.id;
        if(!this.id){
            alert('Your datePkr does not have id attribute.');
        }else{
            $('#'+this.id+'_btn').remove();
            $(this).before("<img id='"+this.id+"_btn' class='datePkr_btn' src='/resource_lib/cal.png'/>");
            Calendar.setup({
                inputField     :    this.id,
                button         :    this.id+"_btn",
                ifFormat       :    "%Y/%m/%d",
                dateType       :    'jalali',
                onClose        :    function(cal) { cal.hide(); $('#'+inputFieldId).focusout();},
                weekNumbers    :    false
            });
        }
    });

}

function removeBimeShodeMultiple(showAlert) {
    if (numBimeShodeMultiple == 1) {
        if (showAlert) {
            alertMessage("حداقل تعداد بیمه شده ها 1 عدد است.");
        }
        return false;
    }
    $("#bimeShode" + numBimeShodeMultiple).remove();
    $("#pusheshHayeBimeShode" + numBimeShodeMultiple).remove();
    $("#zelzele_pers" + numBimeShodeMultiple).remove();
    $("#is_registered_pers"+numBimeShodeMultiple).remove();
    if(numBimeShodeMultiple ==2){
        $('.dblRadio').buttonset("destroy");
        $("#mode111").attr("disabled","disabled");
        $("#mode112").attr("disabled","disabled");
        $('.dblRadio').buttonset();
    }
    numBimeShodeMultiple--;
    return true;
}


function calculateSenMultiple(personNo) {
    var seneShakhs = mohasebeyeSen($('#tarikh_tavalod_pers' + personNo).val(), 'S');
    if (seneShakhs < 0) {
        $('#tarikh_tavalod_pers' + personNo).val('');
        $('#sen_bime_shode_pers' + personNo).val('0');
        $('#sen_bime_shode_v_pers' + personNo).val('');
        return;
    }

    $('#sen_bime_shode_pers' + personNo).val(seneShakhs);
    $('#sen_bime_shode_v_pers' + personNo).val(seneShakhs);


    if (0 <= seneShakhs && seneShakhs <= 14) {
        $('#mode41_pers' + personNo).attr('checked', true).change();
        $('#mode42_pers' + personNo).removeAttr('checked').change();
        $('#mode4_pers' + personNo).buttonset().buttonset("refresh");
        $('#mode51_pers' + personNo).attr('checked', true);
        $('#mode52_pers' + personNo).removeAttr('checked').change();
        $('#mode5_pers' + personNo).buttonset().buttonset("refresh");
        $('#mode21_pers' + personNo).attr('checked', true);
        $('#mode22_pers' + personNo).removeAttr('checked').change();
        $('#mode2_pers' + personNo).buttonset().buttonset("refresh");
        $('#mode11_pers' + personNo).attr('checked', true).change();
        $('#mode12_pers' + personNo).removeAttr('checked').change();
        $('#mode1_pers' + personNo).buttonset().buttonset("refresh");
        deactivateArray(new Array('sarmaye_paye_fot_dar_asar_hadese_pers' + personNo, 'sarmaye_pooshesh_naghs_ozv_pers' + personNo, 'sarmaye_pooshesh_amraz_khas_pers' + personNo));
        $('#mode4_pers' + personNo).buttonset().buttonset("disable");
        $('#mode5_pers' + personNo).buttonset().buttonset("disable");
        $('#mode2_pers' + personNo).buttonset().buttonset("disable");
        $('#mode1_pers' + personNo).buttonset().buttonset("disable");
    } else if (15 <= seneShakhs && seneShakhs <= 17) {
        $('#mode21_pers' + personNo).attr('checked', true);
        $('#mode22_pers' + personNo).removeAttr('checked').change();
        $('#mode2_pers' + personNo).buttonset().buttonset("refresh");
        $('#mode2_pers' + personNo).buttonset().buttonset("disable");
//        $('#mode41').attr('checked',true);
//        $('#mode42').removeAttr('checked');
        $('#mode4_pers' + personNo).buttonset().buttonset("refresh");
        $('#mode4_pers' + personNo).buttonset().buttonset("enable");
//        $('#mode51').attr('checked',true);
//        $('#mode52').removeAttr('checked');
        $('#mode5_pers' + personNo).buttonset().buttonset("refresh");
        $('#mode5_pers' + personNo).buttonset().buttonset("enable");
//        $('#mode11').attr('checked',true);
//        $('#mode12').removeAttr('checked');
        $('#mode1_pers' + personNo).buttonset().buttonset("refresh");
        $('#mode1_pers' + personNo).buttonset().buttonset("enable");
    } else {
//        $('#mode21').attr('checked',true);
//        $('#mode22').removeAttr('checked');
        $('#mode2_pers' + personNo).buttonset().buttonset("refresh");
        $('#mode2_pers' + personNo).buttonset().buttonset("enable");
//        $('#mode41').attr('checked',true);
//        $('#mode42').removeAttr('checked');
        $('#mode4_pers' + personNo).buttonset().buttonset("refresh");
        $('#mode4_pers' + personNo).buttonset().buttonset("enable");
//        $('#mode51').attr('checked',true);
//        $('#mode52').removeAttr('checked');
        $('#mode5_pers' + personNo).buttonset().buttonset("refresh");
        $('#mode5_pers' + personNo).buttonset().buttonset("enable");
//        $('#mode11').attr('checked',true);
//        $('#mode12').removeAttr('checked');
        $('#mode1_pers' + personNo).buttonset().buttonset("refresh");
        $('#mode1_pers' + personNo).buttonset().buttonset("enable");
    }
    $('#tarikh_tavalod_pers' + personNo).focusout();
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
    for (var idsCounter = 0; idsCounter < ids.length; idsCounter++) {
        $('#' + ids[idsCounter]).removeClass('ui-state-disabled');
        $('#' + ids[idsCounter]).removeAttr("disabled");
    }
}

function deactivateArray(ids) {
    for (var idsCounter = 0; idsCounter < ids.length; idsCounter++) {
        $('#' + ids[idsCounter]).addClass('ui-state-disabled');
        $('#' + ids[idsCounter]).attr("disabled", true);
    }
}

function resetForm() {
    $.validationEngine.closePrompt('.formError', true);
    while (removeBimeShodeMultiple(false));

    $('input:text').val('').change();
    $('#nam_bime_shode_pers1').val('').change();
    $('#tarikh_tavalod_pers1').val('').change();
//    $('#darsad_ezafe_nerkh_pezeshki').val('0').change();
    $('#sen_bime_shode_v_pers1').val('').change();
    $('#sen_bime_shode_pers1').val('').change();
    $('#sarmaye_paye_fot_pers1').val('30,000,000').change();
    $('#nerkh_afzayesh_salaneh_sarmaye_fot_pers1').val('0').change();
    $('#nahve_pardakht_hagh_bime').val('mah').change();
    $('#hagh_bime_pardakhti').val('300,000').change();
    $('#nerkh_afzayesh_salaneh_hagh_bime').val('0').change();
    $('#mablagh_seporde_ebtedaye_sal').val('0').change();
    $('#modat_bimename').val('20').change();
//    $('#nahveye_daryafte_andukhte_entehaye_modat_bimename').val('yekja').change();
//    $('#nerkh_afzayesh_salaneh_mostamari').val('0').change();
//    $('#nahve_daryaft_mostamari').val('mah').change();
//    $('#moddat_zaman_daryaft_mostamari').val('0').change();
    $('#sarmaye_pooshesh_naghs_ozv_pers1').val('0').change();
    $('#sarmaye_pooshesh_amraz_khas_pers1').val('0').change();
//    $('#darsad_takhfif_karmozd_karmozd').val('0').change();
//    $('#darsad_takhfif_vosool').val('0').change();
//    $('#noe_tarh').val('gheir_jamee').change();
    $('#mode42_pers1').removeAttr('checked').change();
    $('#mode41_pers1').attr('checked', true).change();
    $('#mode52_pers1').removeAttr('checked').change();
    $('#mode51_pers1').attr('checked', true).change();
    $('#mode21_pers1').attr('checked', true).change();
    $('#mode22_pers1').removeAttr('checked').change();
    $('#result').html('');
    $('.prt').addClass('ui-state-disabled');
    $('.prt').attr('disabled', true);
    $('#sarmaye_paye_fot_dar_asar_hadese_pers1').val('').change();
    $('#mode4_pers1').buttonset().buttonset("enable");
    $('#mode5_pers1').buttonset().buttonset("enable");
//    $("#dokme_mostamari_from_2").hide();
//    $("#dokme_mostamari").hide();

}

function mohasebeMultiple() {
    $.validationEngine.onSubmitValid = true;
    $('.content').slideDown(0);
    if(numBimeShodeMultiple < 2){
        alertMessage("اطلاعات بيمه شده دوم ثبت نشده است.");
       return false;
    }
    if ($.validationEngine.submitValidation($('#mainForm')) === false) {
        var mainForm2 = $('#mainForm').serialize();
        $.post('/calculateEstelamMultiple', mainForm2, function(msg) {
            if (msg.indexOf('Error:WrongFieldsValue') == -1 && msg.indexOf('Error:ExtaWrongFieldsValue') == -1) {
                $('#result').html(msg);
                $('.prt').removeClass('ui-state-disabled');
                $('.prt').removeAttr('disabled');
//                $("#dokme_mostamari").show();
            } else if (msg.indexOf('Error:WrongFieldsValue') == -1) {
                wrongParameteres('extraWrongParameteres');
            } else {
                wrongParameteres('wrongParameteres');
            }

            return false;
        });
        return false;
    } else {
        return false;
    }
}

function wrongParameteres(param) {
    $.validationEngine.closePrompt('.formError', true);
    $('#' + param).dialog({
        modal:true,
        width: 620,
        resizable:false,
        closeText: "",
        title:"خطا در مقدار دهي پارامترها",
        close:function() {
            $.validationEngine.closePrompt('.formError', true);
        },
        buttons: {
            "بستن": function() {
                $.validationEngine.closePrompt('.formError', true);
                $(this).dialog("close");
            }
        }
    });
}

function sarmayePayeFotChangeMultiple(personNo, typing) {
    if ($('#sarmaye_paye_fot_pers' + personNo).val() && $('#sarmaye_paye_fot_pers' + personNo).val() != '') {
        if (!$('#sarmaye_paye_fot_dar_asar_hadese_pers' + personNo).is(':disabled')) {
            var v = jQuery.global.format(jQuery.global.parseInt($('#sarmaye_paye_fot_pers' + personNo).val()), "n0");
            if (typing)
                v = jQuery.global.format(jQuery.global.parseInt($('#sarmaye_paye_fot_pers' + personNo).val()), "n0");
            $('#sarmaye_paye_fot_dar_asar_hadese_pers' + personNo).val(v).change();
        }
    }
}

function sarmayeAmrazChangeMultiple(personNo, typing) {
    if ($('#sarmaye_paye_fot_pers' + personNo).val() && $('#sarmaye_paye_fot_pers' + personNo).val() != '') {
        if (!$('#sarmaye_pooshesh_amraz_khas_pers' + personNo).is(':disabled')) {

            if ((0.3 * jQuery.global.parseInt($('#sarmaye_paye_fot_pers' + personNo).val())) > 300000000) {
                $('#sarmaye_pooshesh_amraz_khas_pers' + personNo).val('300,000,000').change();
            } else {
                var v = jQuery.global.format(0.3 * jQuery.global.parseInt($('#sarmaye_paye_fot_pers' + personNo).val()), "n0");
                $('#sarmaye_pooshesh_amraz_khas_pers' + personNo).val(v).change();
            }

        }
    }
}

function sarmayePoosheshNaghsChangeMultiple(personNo, typing) {
    if ($('#sarmaye_paye_fot_pers' + personNo).val() && $('#sarmaye_paye_fot_pers' + personNo).val() != '') {
        if (!$('#sarmaye_pooshesh_naghs_ozv_pers' + personNo).is(':disabled')) {

            var sarPayeFot = jQuery.global.parseInt($('#sarmaye_paye_fot_pers' + personNo).val());
            var sarFotHadeshe = jQuery.global.parseInt($('#sarmaye_paye_fot_dar_asar_hadese_pers' + personNo).val());
            var min = sarPayeFot;
            if (min > sarFotHadeshe) min = sarFotHadeshe;
            if (min > 500000000) min = 500000000;
            var v = jQuery.global.format(min, "n0");

//            var v1 = jQuery.global.format(jQuery.global.parseInt($('#sarmaye_paye_fot_dar_asar_hadese').val()), "n0");
//            var v2 = jQuery.global.format(jQuery.global.parseInt(v)+jQuery.global.parseInt(v1));
            $('#sarmaye_pooshesh_naghs_ozv_pers' + personNo).val(v).change();
        }
    }
}

function pushesheFoutDarAsareHadeseNadaradMultiple(personNo) {
    document.getElementById('zelzele_pers' + personNo).value = "no";
    $('#sarmaye_paye_fot_dar_asar_hadese_pers' + personNo).val('0');
    deactivate('sarmaye_paye_fot_dar_asar_hadese_pers'+personNo);
    $('#mode31_pers' + personNo).attr('checked', true).change();
    $('#mode32_pers' + personNo).removeAttr('checked').change();
    $('#mode1_pers' + personNo).buttonset().buttonset("disable");
    deactivate('sarmaye_pooshesh_naghs_ozv_pers'+personNo);
    $('#mode11_pers' + personNo).attr('checked', true).change();
    $('#mode12_pers' + personNo).removeAttr('checked').change();
    $('#sarmaye_pooshesh_naghs_ozv_pers' + personNo).val(0);
}

function pushesheFoutDarAsareHadeseDaradMultiple(personNo) {
    document.getElementById('zelzele_pers' + personNo).value = "yes";
    activation('sarmaye_paye_fot_dar_asar_hadese_pers' + personNo);
    sarmayePayeFotChangeMultiple(personNo, false);
    $('#mode32_pers' + personNo).attr('checked', true).change();
    $('#mode31_pers' + personNo).removeAttr('checked').change();
    $('#mode1_pers' + personNo).buttonset().buttonset("enable");

    if ($('#mode12_pers' + personNo).attr('checked')) {
        activation('sarmaye_pooshesh_naghs_ozv_pers' + personNo);
    }
    else{
        deactivation('sarmaye_pooshesh_naghs_ozv_pers' + personNo);
    }
}

function addComment() {
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
            "ارسال": function() {
                $.validationEngine.onSubmitValid = true;
                if ($.validationEngine.submitValidation($('#addCommetnDiv')) === false) {
                    var data = $('#addCommetnForm').serialize();
                    $.post("/saveNazar", data, function(msg) {
                        $.validationEngine.closePrompt('.formError', true);
                        $('#mesg').show();
                        $('#addCommetnDiv input, #addCommetnDiv textarea').val('');
                        setTimeout("closeComment()", 2000);
                    })
                }
            }
        }
    });
}
function closeComment() {
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