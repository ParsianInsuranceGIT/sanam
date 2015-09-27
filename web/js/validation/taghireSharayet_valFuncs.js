function val_hagh_bime_pardakhti10() {
    if ($('#innernahve_pardakht_hagh_bime').val() == "mah"){
        if ($('#pishnehad_noeGharardad').val() == "قرارداد فروش جمعي") {
            if(jQuery.global.parseInt($('#innerhagh_monazam_miani').val()) < 200000){
                if($('#innermablagh_avaliye_miani').val() != '0' && $('#innerhagh_monazam_miani').val() == '0') return true;
                return false;
            }
        }else{
            if(jQuery.global.parseInt($('#innerhagh_monazam_miani').val()) < $('#validation_min_hagh_bime_pardakhti_mah').val()){
                if($('#innermablagh_avaliye_miani').val() != '0' && $('#innerhagh_monazam_miani').val() == '0') return true;
                return false;
            }
        }
    }
    return true;
}function val_hagh_bime_pardakhti20() {
    if ($('#innernahve_pardakht_hagh_bime').val() == "3mah" &&jQuery.global.parseInt($('#innerhagh_monazam_miani').val()) < 750000){
        if($('#innermablagh_avaliye_miani').val() != '0' && $('#innerhagh_monazam_miani').val() == '0') return true;
        return false;
    }
    return true;
}function val_hagh_bime_pardakhti30() {
    if ($('#innernahve_pardakht_hagh_bime').val() == "6mah" && jQuery.global.parseInt($('#innerhagh_monazam_miani').val()) < 1200000){
        if($('#innermablagh_avaliye_miani').val() != '0' && $('#innerhagh_monazam_miani').val() == '0') return true;
        return false;
    }
    return true;
}function val_hagh_bime_pardakhti40() {
    if ($('#innernahve_pardakht_hagh_bime').val() == "sal" && jQuery.global.parseInt($('#innerhagh_monazam_miani').val()) < 2000000){
        if($('#innermablagh_avaliye_miani').val() != '0' && $('#innerhagh_monazam_miani').val() == '0') return true;
        return false;
    }
    return true;
}

function val_darsad_hagh_bime_darsad_sarmaye_fot_ts(){
    if($('#innernerkh_afzayesh_salaneh_hagh_bime').is(':disabled')) return true;
    if(parseInt($('#innernerkh_afzayesh_salaneh_sarmaye_fot').val())>parseInt($('#innernerkh_afzayesh_salaneh_hagh_bime').val())){
        return false;
    }
    return true;
}
function val_nerkh_afzayesh_salaneh_sarmaye_fot_ts() {
    if (parseInt($('#sen_bime_shode').val()) >= 0 && parseInt($('#sen_bime_shode').val()) <= 15) {
        if ($('#innernerkh_afzayesh_salaneh_sarmaye_fot').val() == 0 || $('#innernerkh_afzayesh_salaneh_sarmaye_fot').val() == 5)
            return true;
        else return false;
    } else if (parseInt($('#sen_bime_shode').val()) >= 16 && parseInt($('#sen_bime_shode').val()) <= 20) {
        if ($('#innernerkh_afzayesh_salaneh_sarmaye_fot').val() == 0 || $('#innernerkh_afzayesh_salaneh_sarmaye_fot').val() == 5 || $('#innernerkh_afzayesh_salaneh_sarmaye_fot').val() == 10)
            return true;
        else return false;
    }
    return true;
}
function val_3_9_sarmaye_paye_fot_ts() {
    if (parseInt($('#sen_bime_shode').val()) >= 3 && parseInt($('#sen_bime_shode').val()) <= 9) {
//        if (jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) >= 30000000 && jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) <= 100000000)
        if (jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) >= 10000000 && jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) <= 100000000)
            return true;
        else return false;
    }
    return true;
}
function val_0_2_sarmaye_paye_fot_ts() {
    if (parseInt($('#sen_bime_shode').val()) >= 0 && parseInt($('#sen_bime_shode').val()) <= 2) {
        if (jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) >= 10000000 && jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) <= 50000000)
            return true;
        else return false;
    }
    return true;
}
function val_10_14_sarmaye_paye_fot_ts() {
    if (parseInt($('#sen_bime_shode').val()) >= 10 && parseInt($('#sen_bime_shode').val()) <= 14) {
//        if (jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) >= 30000000 && jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) <= 150000000)
        if (jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) >= 10000000 && jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) <= 150000000)
            return true;
        else return false;
    }
    return true;
}
function val_15_19_sarmaye_paye_fot_ts() {
    if (parseInt($('#sen_bime_shode').val()) >= 15 && parseInt($('#sen_bime_shode').val()) <= 19) {
//        if (jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) >= 30000000 && jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) <= 400000000)
        if (jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) >= 10000000 && jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) <= 400000000)
            return true;
        else return false;
    }
    return true;
}
function val_20_sayer_sarmaye_paye_fot_ts() {
    if (parseInt($('#sen_bime_shode').val()) >= 20) {
        if (jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) >= 10000000 && jQuery.global.parseInt($('#innersarmaye_fot_miani').val()) <= 2000000000)
            return true;
        else return false;
    }
    return true;
}

function val_sarmaye_paye_fot_dar_asar_hadese_ts(){
    if(jQuery.global.parseInt($('#innersarmaye_paye_fot_dar_asar_hadese').val()) < 30000000 || jQuery.global.parseInt($('#innersarmaye_paye_fot_dar_asar_hadese').val()) > 2000000000){
        return false;
    }else if(jQuery.global.parseInt($('#innersarmaye_paye_fot_dar_asar_hadese').val()) > 3*jQuery.global.parseInt($('#innersarmaye_fot_miani').val())){
        return false;
    }
    return true;
}

function val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_ts(){
    var sarPayeFot =jQuery.global.parseInt($('#innersarmaye_fot_miani').val());
    var sarFotHadeshe=jQuery.global.parseInt($('#innersarmaye_paye_fot_dar_asar_hadese').val());
    var min = sarPayeFot;
    if(min>sarFotHadeshe) min=sarFotHadeshe;
    if(min > 500000000) min = 500000000;
    if(jQuery.global.parseInt($('#innersarmaye_pooshesh_naghs_ozv').val())<=min){
        return true;
    }
    return false;
}

function val_sarmaye_pushesh_amraaz_khaas_ts(){
    var sarPayeFot =jQuery.global.parseInt($('#innersarmaye_fot_miani').val());
    var min = sarPayeFot * 0.3;
    if(min > 300000000) min = 300000000;
    if(jQuery.global.parseInt($('#innersarmaye_pooshesh_amraz_khas').val())<=min){
        return true;
    }
    return false;
}
