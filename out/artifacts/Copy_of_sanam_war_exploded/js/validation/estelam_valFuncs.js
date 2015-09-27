var maxBimeShodeNum = 4;
function val_empty(){
    return true;
}
function val_nerkh_afzayesh_salaneh_sarmaye_fot() {
    if (parseInt($('#sen_bime_shode').val()) >= 0 && parseInt($('#sen_bime_shode').val()) <= 15) {
        if ($('#nerkh_afzayesh_salaneh_sarmaye_fot').val() == 0 || $('#nerkh_afzayesh_salaneh_sarmaye_fot').val() == 5)
            return true;
        else return false;
    } else if (parseInt($('#sen_bime_shode').val()) >= 16 && parseInt($('#sen_bime_shode').val()) <= 20) {
        if ($('#nerkh_afzayesh_salaneh_sarmaye_fot').val() == 0 || $('#nerkh_afzayesh_salaneh_sarmaye_fot').val() == 5 || $('#nerkh_afzayesh_salaneh_sarmaye_fot').val() == 10)
            return true;
        else return false;
    }
    return true;
}

function val_aghsateMoavaghOneDatesRequired(){
    if($("#azTarikheSodoreBimename").val() && $("#taTarikheSodoreBimename").val()){
         return true;
    }
    if($("#azTarikhePardakht").val() && $("#taTarikhePardakht").val()){
         return true;
    }
    if($("#azTarikheSarresid").val() && $("#taTarikheSarresid").val()){
        return true;
    }
    return false;
}
function val_seneBimeShode_multiple_pers1(){
    if(parseInt($('#sen_bime_shode_pers1').val()) < 18){
        return false;
    }
    return true;
}
function val_seneBimeShode_multiple_pers2(){
    if(parseInt($('#sen_bime_shode_pers2').val()) < 18){
        return false;
    }
    return true;
}
function val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple_pers1() {
    return val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple(1);
}

function val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple_pers2() {
    return val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple(2);
}

function val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple_pers3() {
    return val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple(3);
}

function val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple_pers4() {
    return val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple(4);
}

function val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple(personNo){
    if (parseInt($('#sen_bime_shode_pers' + personNo).val()) >= 0 && parseInt($('#sen_bime_shode_pers' + personNo).val()) <= 15) {
        if ($('#nerkh_afzayesh_salaneh_sarmaye_fot_pers' + personNo).val() == 0 || $('#nerkh_afzayesh_salaneh_sarmaye_fot_pers' + personNo).val() == 5)
            return true;
        else return false;
    } else if (parseInt($('#sen_bime_shode_pers' + personNo).val()) >= 16 && parseInt($('#sen_bime_shode_pers' + personNo).val()) <= 20) {
        if ($('#nerkh_afzayesh_salaneh_sarmaye_fot_pers' + personNo).val() == 0 || $('#nerkh_afzayesh_salaneh_sarmaye_fot_pers' + personNo).val() == 5 || $('#nerkh_afzayesh_salaneh_sarmaye_fot_pers' + personNo).val() == 10)
            return true;
        else return false;
    }
    return true;
}

function val_3_9_sarmaye_paye_fot() {
    if (parseInt($('#sen_bime_shode').val()) >= 3 && parseInt($('#sen_bime_shode').val()) <= 9) {
//        if (jQuery.global.parseInt($('#sarmaye_paye_fot').val()) >= 30000000 && jQuery.global.parseInt($('#sarmaye_paye_fot').val()) <= 100000000)
        if (jQuery.global.parseInt($('#sarmaye_paye_fot').val()) >= 10000000 && jQuery.global.parseInt($('#sarmaye_paye_fot').val()) <= 100000000)
            return true;
        else return false;
    }
    return true;
}
function val_3_9_sarmaye_paye_fot_multiple_pers1(){
    return val_3_9_sarmaye_paye_fot_multiple(1);
}

function val_3_9_sarmaye_paye_fot_multiple_pers2(){
    return val_3_9_sarmaye_paye_fot_multiple(2);
}

function val_3_9_sarmaye_paye_fot_multiple_pers3(){
    return val_3_9_sarmaye_paye_fot_multiple(3);
}

function val_3_9_sarmaye_paye_fot_multiple_pers4(){
    return val_3_9_sarmaye_paye_fot_multiple(4);
}

function val_3_9_sarmaye_paye_fot_multiple(personNo){
    if (parseInt($('#sen_bime_shode_pers'+personNo).val()) >= 3 && parseInt($('#sen_bime_shode_pers'+personNo).val()) <= 9) {
//        if (jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) >= 30000000 && jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) <= 100000000)
        if (jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) >= 10000000 && jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) <= 100000000)
            return true;
        else return false;
    }
    return true;
}

function val_mahdodeye_sarmaye_fot(){

    var mahdodeStr = $('#validation_mahdode_paye_sarmaye_fot').val();
    var mahdodeRec = mahdodeStr.split('|');

    if (mahdodeRec.length > 1){
        for (var i=0; i < mahdodeRec.length; i++){

            var mahdodeSen = mahdodeRec[i].split(',')[0];

            var minMahdodeSen = mahdodeSen.split('-')[0];
            var maxMahdodeSen = mahdodeSen.split('-')[1];


            var mahdodeSarmaye = mahdodeRec[i].split(',')[1];
            var minMahdodeSarmaye = mahdodeSarmaye.split('-')[0];
            var maxMahdodeSarmaye = mahdodeSarmaye.split('-')[1];

            var sarmayeAmount = $('#sarmaye_paye_fot').val();
            sarmayeAmount = sarmayeAmount.replace(/\,/g, '');

            if (parseInt($('#sen_bime_shode').val()) >= minMahdodeSen && parseInt($('#sen_bime_shode').val()) <= maxMahdodeSen) {
                if (jQuery.global.parseInt(sarmayeAmount) >= minMahdodeSarmaye && jQuery.global.parseInt(sarmayeAmount) <= maxMahdodeSarmaye){
                    return true;
                }
            }
        }
    }else{
        if (mahdodeStr != null && mahdodeStr.length > 0){
            var mahdodeSen = mahdodeStr.split(',')[0];

            var minMahdodeSen = mahdodeSen.split('-')[0];

            var maxMahdodeSen = mahdodeSen.split('-')[1];


            var mahdodeSarmaye = mahdodeStr.split(',')[1];
            var minMahdodeSarmaye = mahdodeSarmaye.split('-')[0];
            var maxMahdodeSarmaye = mahdodeSarmaye.split('-')[1];

            var sarmayeAmount = $('#sarmaye_paye_fot').val();
            sarmayeAmount = sarmayeAmount.replace(/\,/g, '');

            if (parseInt($('#sen_bime_shode').val()) >= minMahdodeSen && parseInt($('#sen_bime_shode').val()) <= maxMahdodeSen) {
                if (jQuery.global.parseInt(sarmayeAmount) >= minMahdodeSarmaye && jQuery.global.parseInt(sarmayeAmount) <= maxMahdodeSarmaye){
                    return true;
                }
            }
        }else{
            return true;
        }
    }
    return false;

}


function val_0_2_sarmaye_paye_fot() {
    if (parseInt($('#sen_bime_shode').val()) >= 0 && parseInt($('#sen_bime_shode').val()) <= 2) {
        if (jQuery.global.parseInt($('#sarmaye_paye_fot').val()) >= 10000000 && jQuery.global.parseInt($('#sarmaye_paye_fot').val()) <= 50000000)
            return true;
        else return false;
    }
    return true;
}
function val_0_2_sarmaye_paye_fot_multiple_pers1(){
    return val_0_2_sarmaye_paye_fot_multiple(1);
}
function val_0_2_sarmaye_paye_fot_multiple_pers2(){
    return val_0_2_sarmaye_paye_fot_multiple(2);
}
function val_0_2_sarmaye_paye_fot_multiple_pers3(){
    return val_0_2_sarmaye_paye_fot_multiple(3);
}
function val_0_2_sarmaye_paye_fot_multiple_pers4(){
    return val_0_2_sarmaye_paye_fot_multiple(4);
}
function val_0_2_sarmaye_paye_fot_multiple(personNo){
    if (parseInt($('#sen_bime_shode_pers'+personNo).val()) >= 0 && parseInt($('#sen_bime_shode_pers'+personNo).val()) <= 2) {
        if (jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) >= 10000000 && jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) <= 50000000)
            return true;
        else return false;
    }
    return true;
}
function val_10_14_sarmaye_paye_fot() {
    if (parseInt($('#sen_bime_shode').val()) >= 10 && parseInt($('#sen_bime_shode').val()) <= 14) {
//        if (jQuery.global.parseInt($('#sarmaye_paye_fot').val()) >= 30000000 && jQuery.global.parseInt($('#sarmaye_paye_fot').val()) <= 150000000)
        if (jQuery.global.parseInt($('#sarmaye_paye_fot').val()) >= 10000000 && jQuery.global.parseInt($('#sarmaye_paye_fot').val()) <= 150000000)
            return true;
        else return false;
    }
    return true;
}
function val_10_14_sarmaye_paye_fot_multiple_pers1(){
    return val_10_14_sarmaye_paye_fot_multiple(1)
}
function val_10_14_sarmaye_paye_fot_multiple_pers2(){
    return val_10_14_sarmaye_paye_fot_multiple(2)
}
function val_10_14_sarmaye_paye_fot_multiple_pers3(){
    return val_10_14_sarmaye_paye_fot_multiple(3)
}
function val_10_14_sarmaye_paye_fot_multiple_pers4(){
    return val_10_14_sarmaye_paye_fot_multiple(4)
}
function val_10_14_sarmaye_paye_fot_multiple(personNo){
    if (parseInt($('#sen_bime_shode_pers'+personNo).val()) >= 10 && parseInt($('#sen_bime_shode_pers'+personNo).val()) <= 14) {
//        if (jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) >= 30000000 && jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) <= 150000000)
        if (jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) >= 10000000 && jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) <= 150000000)
            return true;
        else return false;
    }
    return true;
}
function val_15_19_sarmaye_paye_fot() {
    if (parseInt($('#sen_bime_shode').val()) >= 15 && parseInt($('#sen_bime_shode').val()) <= 19) {
//        if (jQuery.global.parseInt($('#sarmaye_paye_fot').val()) >= 30000000 && jQuery.global.parseInt($('#sarmaye_paye_fot').val()) <= 400000000)
        if (jQuery.global.parseInt($('#sarmaye_paye_fot').val()) >= 10000000 && jQuery.global.parseInt($('#sarmaye_paye_fot').val()) <= 400000000)
            return true;
        else return false;
    }
    return true;
}
function val_15_19_sarmaye_paye_fot_multiple_pers1(){
    return val_15_19_sarmaye_paye_fot_multiple(1);
}
function val_15_19_sarmaye_paye_fot_multiple_pers2(){
    return val_15_19_sarmaye_paye_fot_multiple(2);
}
function val_15_19_sarmaye_paye_fot_multiple_pers3(){
    return val_15_19_sarmaye_paye_fot_multiple(3);
}
function val_15_19_sarmaye_paye_fot_multiple_pers4(){
    return val_15_19_sarmaye_paye_fot_multiple(4);
}
function val_15_19_sarmaye_paye_fot_multiple(personNo) {
    if (parseInt($('#sen_bime_shode_pers'+personNo).val()) >= 15 && parseInt($('#sen_bime_shode_pers'+personNo).val()) <= 19) {
//        if (jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) >= 30000000 && jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) <= 400000000)
        if (jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) >= 10000000 && jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) <= 400000000)
            return true;
        else return false;
    }
    return true;
}
function val_20_sayer_sarmaye_paye_fot() {
    if (parseInt($('#sen_bime_shode').val()) >= 20) {
        if (jQuery.global.parseInt($('#sarmaye_paye_fot').val()) >= 10000000 && jQuery.global.parseInt($('#sarmaye_paye_fot').val()) <= 2000000000)
            return true;
        else return false;
    }
    return true;
}
function val_20_sayer_sarmaye_paye_fot_multiple_pers1(){
    return val_20_sayer_sarmaye_paye_fot_multiple(1);
}
function val_20_sayer_sarmaye_paye_fot_multiple_pers2(){
    return val_20_sayer_sarmaye_paye_fot_multiple(2);
}
function val_20_sayer_sarmaye_paye_fot_multiple_pers3(){
    return val_20_sayer_sarmaye_paye_fot_multiple(3);
}
function val_20_sayer_sarmaye_paye_fot_multiple_pers4(){
    return val_20_sayer_sarmaye_paye_fot_multiple(4);
}
function val_20_sayer_sarmaye_paye_fot_multiple(personNo) {
    if (parseInt($('#sen_bime_shode_pers'+personNo).val()) >= 20) {
        if (jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) >= 10000000 && jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val()) <= 2000000000)
            return true;
        else return false;
    }
    return true;
}
function val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot() {
    var sarPayeFot = jQuery.global.parseInt($('#sarmaye_paye_fot').val());
    var sarFotHadeshe = jQuery.global.parseInt($('#sarmaye_paye_fot_dar_asar_hadese').val());
    var min = sarPayeFot;
    if (min > sarFotHadeshe) min = sarFotHadeshe;
    if (min > 500000000) min = 500000000;
    if (jQuery.global.parseInt($('#sarmaye_pooshesh_naghs_ozv').val()) <= min) {
        return true;
    }
    return false;
}

function val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple_pers1(){
    return val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple(1);
}
function val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple_pers2(){
    return val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple(2);
}
function val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple_pers3(){
    return val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple(3);
}
function val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple_pers4(){
    return val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple(4);
}
function val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple(personNo) {
    var sarPayeFot = jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val());
    var sarFotHadeshe = jQuery.global.parseInt($('#sarmaye_paye_fot_dar_asar_hadese_pers'+personNo).val());
    var min = sarPayeFot;
    if (min > sarFotHadeshe) min = sarFotHadeshe;
    if (min > 500000000) min = 500000000;
    if (jQuery.global.parseInt($('#sarmaye_pooshesh_naghs_ozv_pers'+personNo).val()) <= min) {
        return true;
    }
    return false;
}

function val_sarmaye_pushesh_amraaz_khaas() {
    var sarPayeFot = jQuery.global.parseInt($('#sarmaye_paye_fot').val());
    var min = sarPayeFot * 0.3;
    if (min > 300000000) min = 300000000;
    if (jQuery.global.parseInt($('#sarmaye_pooshesh_amraz_khas').val()) <= min) {
        return true;
    }
    return false;
}

function val_sarmaye_pushesh_amraaz_khaas_multiple_pers1(){
    return val_sarmaye_pushesh_amraaz_khaas_multiple(1);
}

function val_sarmaye_pushesh_amraaz_khaas_multiple_pers2(){
    return val_sarmaye_pushesh_amraaz_khaas_multiple(2);
}

function val_sarmaye_pushesh_amraaz_khaas_multiple_pers3(){
    return val_sarmaye_pushesh_amraaz_khaas_multiple(3);
}

function val_sarmaye_pushesh_amraaz_khaas_multiple_pers4(){
    return val_sarmaye_pushesh_amraaz_khaas_multiple(4);
}

function val_sarmaye_pushesh_amraaz_khaas_multiple(personNo) {
    var sarPayeFot = jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val());
    var min = sarPayeFot * 0.3;
    if (min > 300000000) min = 300000000;
    if (jQuery.global.parseInt($('#sarmaye_pooshesh_amraz_khas_pers'+personNo).val()) <= min) {
        return true;
    }
    return false;
}


function val_hagh_bime_pardakhti1() {
    if ($('#nahve_pardakht_hagh_bime').val() == "mah") {
        if ($('#pishnehad_noeGharardad').val() == "قرارداد فروش جمعي") {
            if (jQuery.global.parseInt($('#hagh_bime_pardakhti').val()) < 200000) {
                if ($('#mablagh_seporde_ebtedaye_sal').val() != '0' && $('#hagh_bime_pardakhti').val() == '0') return true;
                return false;
            }
        } else {
            if (jQuery.global.parseInt($('#hagh_bime_pardakhti').val()) < $('#validation_min_hagh_bime_pardakhti_mah').val()) {
                if ($('#mablagh_seporde_ebtedaye_sal').val() != '0' && $('#hagh_bime_pardakhti').val() == '0') return true;
                return false;
            }
        }
    }
    return true;
}
function val_hagh_bime_pardakhti2() {
    if ($('#nahve_pardakht_hagh_bime').val() == "3mah" && jQuery.global.parseInt($('#hagh_bime_pardakhti').val()) < 750000) {
        if ($('#mablagh_seporde_ebtedaye_sal').val() != '0' && $('#hagh_bime_pardakhti').val() == '0') return true;
        return false;
    }
    return true;
}
function val_hagh_bime_pardakhti3() {
    if ($('#nahve_pardakht_hagh_bime').val() == "6mah" && jQuery.global.parseInt($('#hagh_bime_pardakhti').val()) < 1200000) {
        if ($('#mablagh_seporde_ebtedaye_sal').val() != '0' && $('#hagh_bime_pardakhti').val() == '0') return true;
        return false;
    }
    return true;
}
function val_hagh_bime_pardakhti4() {
    if ($('#nahve_pardakht_hagh_bime').val() == "sal" && jQuery.global.parseInt($('#hagh_bime_pardakhti').val()) < 2000000) {
        if ($('#mablagh_seporde_ebtedaye_sal').val() != '0' && $('#hagh_bime_pardakhti').val() == '0') return true;
        return false;
    }
    return true;
}

function val_darsad_hagh_bime_darsad_sarmaye_fot() {
    if ($('#nerkh_afzayesh_salaneh_hagh_bime').is(':disabled')) return true;
    return (parseInt($('#nerkh_afzayesh_salaneh_sarmaye_fot').val()) <= parseInt($('#nerkh_afzayesh_salaneh_hagh_bime').val()));
}
function val_darsad_hagh_bime_darsad_sarmaye_fot_multiple_pers1(){
    return val_darsad_hagh_bime_darsad_sarmaye_fot_multiple(1);
}
function val_darsad_hagh_bime_darsad_sarmaye_fot_multiple_pers2(){
    return val_darsad_hagh_bime_darsad_sarmaye_fot_multiple(2);
}
function val_darsad_hagh_bime_darsad_sarmaye_fot_multiple_pers3(){
    return val_darsad_hagh_bime_darsad_sarmaye_fot_multiple(3);
}
function val_darsad_hagh_bime_darsad_sarmaye_fot_multiple_pers4(){
    return val_darsad_hagh_bime_darsad_sarmaye_fot_multiple(4);
}
function val_darsad_hagh_bime_darsad_sarmaye_fot_multiple_all(){
    for(var personNo = 1;personNo<=maxBimeShodeNum;++personNo){
        if($('#nerkh_afzayesh_salaneh_sarmaye_fot_pers'+personNo).length){
            if(!val_darsad_hagh_bime_darsad_sarmaye_fot_multiple(personNo)){
                return false;
            }
        }
    }
    return true;
}
function val_darsad_hagh_bime_darsad_sarmaye_fot_multiple(personNo){
    if ($('#nerkh_afzayesh_salaneh_hagh_bime').is(':disabled')) return true;
    if (parseInt($('#nerkh_afzayesh_salaneh_sarmaye_fot_pers'+personNo).val()) > parseInt($('#nerkh_afzayesh_salaneh_hagh_bime').val())) {
        return false;
    }
    return true;
}


function val_majmoeSenVaModdateBimeName_multiple() {
    for(var personNo = 1;personNo<= maxBimeShodeNum;++personNo){
        if($('#sen_bime_shode_pers'+personNo).length){
            if ((jQuery.global.parseInt($('#sen_bime_shode_pers'+personNo).val()) + jQuery.global.parseInt($('#modat_bimename').val())) > 71)
                return false;
        }
    }
    return true;
}

function extraValidation1() {
    if (jQuery.global.parseInt($('#mablagh_seporde_ebtedaye_sal').val()) != 0 && jQuery.global.parseInt($('#hagh_bime_pardakhti').val()) == 0) {
        $('#nahve_pardakht_hagh_bime').val('yekja').change();
//        $('#nahve_pardakht_hagh_bime').attr('disabled', true);
        //here we show that nahveye pardakhts is disabled while it's not actually!!
        $('#nahve_pardakht_hagh_bime').focus(function() {
            this.blur();
        })
        $('#nahve_pardakht_hagh_bime').css('background-color', '#ededed');
        // prevent tab from focusing select
        $('#nahve_pardakht_hagh_bime').attr('tabindex', '-1')
        // prevent click change and focus from happening( this requires jquery 1.4.3, otherwise make
        // a function and return false
        $('#nahve_pardakht_hagh_bime').bind('click change focus select', function() {
            return false;
        });

        $('#nerkh_afzayesh_salaneh_hagh_bime').attr('disabled', true);
//        $('#mode21').attr('checked', true);
//        $('#mode22').removeAttr('checked');
//        $('#mode2').buttonset().buttonset("disable");
    } else {
//        $('#nahve_pardakht_hagh_bime').removeAttr("disabled");

        //here we undo the fake disabling process
        $('#nahve_pardakht_hagh_bime').removeAttr('style')
        $('#nahve_pardakht_hagh_bime').removeAttr('tabindex')
        $('#nahve_pardakht_hagh_bime').unbind();

        $('#nerkh_afzayesh_salaneh_hagh_bime').removeAttr("disabled");
//        $('#mode2').buttonset().buttonset("enable");
    }
}

function extraValidation1_multiple() {
    if (jQuery.global.parseInt($('#mablagh_seporde_ebtedaye_sal').val()) != 0 && jQuery.global.parseInt($('#hagh_bime_pardakhti').val()) == 0) {
        $('#nahve_pardakht_hagh_bime').val('yekja').change();
//        $('#nahve_pardakht_hagh_bime').attr('disabled', true);
        //here we show that nahveye pardakhts is disabled while it's not actually!!
        $('#nahve_pardakht_hagh_bime').focus(function() {
            this.blur();
        })
        $('#nahve_pardakht_hagh_bime').css('background-color', '#ededed');
        // prevent tab from focusing select
        $('#nahve_pardakht_hagh_bime').attr('tabindex', '-1')
        // prevent click change and focus from happening( this requires jquery 1.4.3, otherwise make
        // a function and return false
        $('#nahve_pardakht_hagh_bime').bind('click change focus select', function() {
            return false;
        });

        $('#nerkh_afzayesh_salaneh_hagh_bime').attr('disabled', true);
        for(var personNo = 1;personNo <= maxBimeShodeNum;++personNo){
            $('#mode21_pers'+personNo).attr('checked', true);
            $('#mode22_pers'+personNo).removeAttr('checked');
            $('#mode2_pers'+personNo).buttonset().buttonset("disable");
        }

    } else {
//        $('#nahve_pardakht_hagh_bime').removeAttr("disabled");

        //here we undo the fake disabling process
        $('#nahve_pardakht_hagh_bime').removeAttr('style')
        $('#nahve_pardakht_hagh_bime').removeAttr('tabindex')
        $('#nahve_pardakht_hagh_bime').unbind();

        $('#nerkh_afzayesh_salaneh_hagh_bime').removeAttr("disabled");
        for(var personNo = 1;personNo <= maxBimeShodeNum;++personNo){
            $('#mode2_pers'+personNo).buttonset().buttonset("enable");
        }

    }
}

function val_sarmaye_paye_fot_dar_asar_hadese() {
    if (jQuery.global.parseInt($('#sarmaye_paye_fot_dar_asar_hadese').val()) < 30000000 || jQuery.global.parseInt($('#sarmaye_paye_fot_dar_asar_hadese').val()) > 2000000000) {
        return false;
    } else if (jQuery.global.parseInt($('#sarmaye_paye_fot_dar_asar_hadese').val()) > 3 * jQuery.global.parseInt($('#sarmaye_paye_fot').val())) {
        return false;
    }
    return true;
}

function val_sarmaye_paye_fot_dar_asar_hadese_multiple_pers1() {
    return val_sarmaye_paye_fot_dar_asar_hadese_multiple(1);
}

function val_sarmaye_paye_fot_dar_asar_hadese_multiple_pers2() {
    return val_sarmaye_paye_fot_dar_asar_hadese_multiple(2);
}

function val_sarmaye_paye_fot_dar_asar_hadese_multiple_pers3() {
    return val_sarmaye_paye_fot_dar_asar_hadese_multiple(3);
}

function val_sarmaye_paye_fot_dar_asar_hadese_multiple_pers4() {
    return val_sarmaye_paye_fot_dar_asar_hadese_multiple(4);
}

function val_sarmaye_paye_fot_dar_asar_hadese_multiple(personNo) {
    if (jQuery.global.parseInt($('#sarmaye_paye_fot_dar_asar_hadese_pers'+personNo).val()) < 30000000 || jQuery.global.parseInt($('#sarmaye_paye_fot_dar_asar_hadese_pers'+personNo).val()) > 2000000000) {
        return false;
    } else if (jQuery.global.parseInt($('#sarmaye_paye_fot_dar_asar_hadese_pers'+personNo).val()) > 3 * jQuery.global.parseInt($('#sarmaye_paye_fot_pers'+personNo).val())) {
        return false;
    }
    return true;
}