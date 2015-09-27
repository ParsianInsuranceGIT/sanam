$(function () {
    $(".tabsbtn").each(function () {
        $(this).click(function () {
            changeMenu(this.id.split('_')[1]);
        });
    });

//    $(".jtable tr").click(function() {
//        $(this).children("td").toggleClass("ui-state-highlight");
//    });

    $("input, textarea").each(function () {
//        $(this).addClass("ui-state-default  ui-corner-all ui-helper-clearfix");
        $(this).change();
        $(this).bind('change', function () {
            $('.notSavedStar').text('*');
        })
    });

    $(".help, .comment").tipsy({gravity:'s'});
    $(".help").addClass("ui-icon ui-icon-help");
    $(".comment").addClass("ui-icon ui-icon-comment");

    $("select").each(function () {
//        $(this).addClass("ui-state-default  ui-corner-all");
        $(this).change();
        $(this).bind('change', function () {
            $('#notSavedStar').text('*');
        })
        //	$(this).children("input").addClass("{button:{icons:{primary:'ui-icon-triangle-1-s'},text:false}} ui-button ui-widget ui-state-default ui-corner-all ui-button-icons-only");
    });

});

function changeMenu(menuId) {
    $.validationEngine.closePrompt('.formError', true);
    if(menuId != ''){
        $('.content').hide();
        $('#content_' + menuId).show();
        $('.tabsbtn').removeClass('activesubmit');
        $('#tab_' + menuId).addClass('activesubmit');
    }
}
function activate(id) {
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
function and(ids) {
    var output = true;
    for (var idsCounter = 0; idsCounter < ids.length; idsCounter++) {
        if ($('#' + ids[idsCounter]).val() == 'kheir')
            output = false;
    }
    return output;
}
function ezafeKardanShakhs(func) {
    $.validationEngine.onSubmitValid = true;
    if ($.validationEngine.submitValidation($('#shakhsElements')) === false) {
        var mainForm2 = $('#shakhsForm').serialize();
        $.post('/registerShakhs.action', mainForm2, function (msg) {
            if (msg.indexOf('SHAKHS_INFO') != -1) {
                func(msg);
                $('#shakhsElements').dialog('close');
            } else {
                $('#shakhsElements #msgErr_div').html(msg);
            }
        });
    }
}

function barresiVazeeyatBimeShode() {
    var fields = new Array('shoru_zamaan_bimaari', 'daaruye_masrafi', 'modat_masraf', 'darmaan_anjaam_shode', 'vazeeyat_feli_bime_shode');
    if (document.getElementById('bimari_tanafosi_n').checked && document.getElementById('bimari_ghalb_n').checked &&
            document.getElementById('bimari_khuni_n').checked && document.getElementById('bimari_govaareshi_n').checked &&
            document.getElementById('bimari_asaab_n').checked && document.getElementById('bimari_daakheli_n').checked &&
            document.getElementById('bimari_koliye_n').checked && document.getElementById('bimari_gush_n').checked &&
            document.getElementById('bimari_pusti_n').checked && document.getElementById('bimari_ostekhaani_n').checked &&
            document.getElementById('bimari_ghodad_n').checked && document.getElementById('bimari_ofuni_n').checked &&
            document.getElementById('amal_jaraahi_ya_bastari_n').checked && document.getElementById('aareze_digar_n').checked &&
            document.getElementById('naghse_ozv_n').checked && document.getElementById('az_kaar_oftaadegi_n').checked &&
            document.getElementById('shesh_maah_kaahesh_vazn_n').checked)
        deactivateArray(fields);
    else
        activateArray(fields);
}

function openAddShakhsDialog(func) {
    $.validationEngine.closePrompt('.formError', true);
    $('#shakhsElements #msgErr_div').html('');
    $('#shakhsElements').dialog({
        //                autoOpen: false,
        modal:true,
        width:640,
        resizable:false,
        closeText:"",
        title:"اضافه کردن شخص جدید",
        close:function () {
            //$('#shakhsElements input:not(input[type=button],input[type=radio],input[type=submit])').val('');
            $('#shakhsForm')[0].reset();
            $.validationEngine.closePrompt('.formError', true);
        },
        buttons:{
            "انصراف":function () {
                $(this).dialog("close");
            },
            "ثبت":function () {
                ezafeKardanShakhs(func);
            }
        }
    });
}

$(function () {
    $("#mainForm").validationEngine({promptPosition:"topLeft"});
    $("#bimeShodeperson").validationEngine({promptPosition:"topLeft"});
    $("#shakhsForm").validationEngine({promptPosition:"topLeft"});
    $("#address").validationEngine({promptPosition:"topLeft"});
    $("#jostejuForm").validationEngine({promptPosition:"topLeft"});
});
function checkSenBalaye18() {
    if (!$('#bimeGozar_shakhs_tarikheTavallod').val()) return true;
    var isHoghughi = $("#shakhsShomareSabt").val();
    var haghighior = $("#haghighiOrHoghoghi").val();

    if ($("#bimeGozar_OstanZendegi").attr("disabled")) {
        return true;
    } else {

        var seneShakhs = mohasebeyeSen($('#bimeGozar_shakhs_tarikheTavallod').val(), 'S');
        if (seneShakhs < 18) return false;
        return true;
    }
}
function validatePishnehadForm() {
    $.validationEngine.closePrompt('.formError', true);
    var currentTab = $('.activesubmit');
    for (var i = 1; i <= 10; i++) {
//        if(i != 4){
        changeMenu(i);
        if (!$.validationEngine.submitValidation($('#content_' + i)) === false) {
            return false;
        }
//        }
    }

    if (!checkSenBalaye18()) {
        changeMenu(2);
        alertMessage("سن بیمه گذار نباید زیر 18 سال باشد.");
        return false;
    }
    if (jQuery("#EKASB_hayat_tbl").getGridParam("records") == 0) {
        changeMenu(5);
        alertMessage("حداقل يک رکورد در جدول استفاده‌كنندگان در صورت حيات بايد وجود داشته باشد.");
        return false;
    }
    var radif = 1;
    var truthChecker = false;
    $("#EKASB_fout_tbl [name^=pishnehad.estefadeKonandeganAzSarmayeBime]").filter("[name$=kodeMelli]").each(function () {
        if ($(this).val() == $("#bimeShode_shakhs_kodemelli").val()) {
            changeMenu(5);
            alertMessage("شخص بيمه‌شده نمي‌تواند به عنوان ذينفع فوت در ردیف " + radif + " انتخاب شود");
            truthChecker = true;
        }
        radif = parseInt(radif) + 1;

    });
    if (truthChecker) {
        return false;
    }
    if (jQuery("#EKASB_fout_tbl").getGridParam("records") == 0) {
        changeMenu(5)
        alertMessage("حداقل يک رکورد در جدول استفاده‌كنندگان در صورت فوت بايد وجود داشته باشد.");
        return false;
    }
    if (!validateOlaviateEsstefadeKonandegan('EKASB_hayat_tbl') || !validateOlaviateEsstefadeKonandegan('EKASB_fout_tbl')) {
        changeMenu(5)
        alertMessage("خطا در تعیین اولویت های استفاده کنندگان از سرمایه بیمه.");
        return false;
    }
    if (!validateJavabeSoalha_vaziateSalamati()) {
        changeMenu(7);
        return false;
    }
    if (jQuery("#vaziateSalamatiKhanevadeyeBimeShode_tbl").getGridParam("records") < 2) {
        changeMenu(8)
        alertMessage("حداقل دو رکورد در جدول تب وضعيت سلامتي خانواده بيمه‌شده بايد وجود داشته باشد.");
        return false;
    }
    var hasPedar = false;
    var hasMadar = false;
    $("#vaziateSalamatiKhanevadeyeBimeShode_tbl [name^=pishnehad.vaziateSalamatiKhanevadeyeBimeShode]").filter("[name$=nesbatBabimeShode]").each(function () {

        if ($(this).val() == 'پدر') {
            hasPedar = true;
        } else if ($(this).val() == 'مادر') {
            hasMadar = true;
        }

    });
    if (hasPedar != true) {
        changeMenu(8);
        alertMessage("حداقل یک مورد با نام پدر و یک مورد با نام مادر باید ثبت شود.");
        return false;
    }
    if (hasMadar != true) {
        changeMenu(8);
        alertMessage("حداقل یک مورد با نام پدر و یک مورد با نام مادر باید ثبت شود.");
        return false;
    }
    if ($('#etelaat_kaamel_n').attr('checked') || $('#sehat_emzaa_n').attr('checked')) {
        alertMessage('شرایط ثبت پیشنهاد از جهت اطلاع رسانی دقیق یا صحت امضا برقرار نیست.')
        changeMenu(10);
        return false;
    }
    if (!val_1_bimeGozar_nesbatBaBimeShode()) {
        alertMessage('نسبت وارد شده صحیح نیست.');
        changeMenu(2);
        return false;
    }
    currentTab.click();
    return true;
}
function elhaghie() {
    if (validatePishnehadForm()) {
        $('#mainForm').attr('action', '/darkhasteElhaghie.action');
        var form = document.getElementById('mainForm');
        form.submit();
    }
}
function sendPishnehadFormByAjax() {
    if (validatePishnehadForm()) {
        var formData = $('#mainForm').serialize();
        $.post("/registerPishnahadeBimeOmreEnferadi.action", formData, function (msg) {
            $('.notSavedStar').text('');
        });
    }
}
var isSubmitting = false;
function innerSMainForm() {
    if (validatePishnehadForm()) {
        if (!isSubmitting) {
            if ($('#table4fish input').attr('class') != null) {
                $('#table4fish input').attr('class', $('#table4fish input').attr('class').replace('validate', ''));
            }
            isSubmitting = true;
            $("#mainForm").removeAttr("action");
            $("#mainForm").attr("action", "innerRegisterPishnahadeBimeOmreEnferadi.action");
            $("#mainForm").submit();
        }
    }
}
function sMainFormForBazaryab() {

    if (validatePishnehadForm()) {
        if (!isSubmitting) {
            isSubmitting = true;
            $("#mainForm").removeAttr("action");
            $("#mainForm").attr("action", "/registerPishnahadeBimeOmreEnferadiForBazaryab.action")
            var form = document.getElementById('mainForm');
            form.submit();
        }
    }
}
function sMainForm() {
    if (validatePishnehadForm()) {
        if (!isSubmitting) {
            isSubmitting = true;
            var form = document.getElementById('mainForm');
            form.submit();
        }
    }
}

function fillShahrOstan(cityIdField, cityNameField, ostanIdField, ostanNameField, nextField) {
    dialogFormWithFocus('searchCity', 'جستجوی شهر', function () {
        $('#' + cityNameField).val($('#selectedShahr').val());
        $('#' + ostanNameField).val($('#selectedOstan').val());
        $('#' + cityIdField).val($('#shahrId').val());
        $('#' + ostanIdField).val($('#ostanId').val());
    }, nextField);
}

function fillShahr(cityIdField, cityNameField, nextField) {
    dialogFormWithFocus('searchCity', 'جستجوی شهر', function () {
        $('#' + cityNameField).val($('#selectedShahr').val());
        $('#' + cityIdField).val($('#shahrId').val());
    }, nextField);
}

function val_1_bimeGozar_nesbatBaBimeShode() {
    var bg_shakhs_id= $('#bimeGozar_shakhs_id').val();
    var bs_shakhs_id= $('#bimeShode_shakhs_id').val();
    var bgShakhsElement = document.getElementById("newShakhsBimeGozar");
    if (bgShakhsElement != null)
    {
        bg_shakhs_id=$('#newShakhsBimeGozar').val();
    }

    var bsShakhsElement = document.getElementById("newShakhsBimeShode");
    if (bsShakhsElement != null)
    {
        bs_shakhs_id= $('#newShakhsBimeShode').val();
    }


    if (parseInt(bg_shakhs_id) == parseInt(bs_shakhs_id)) {
        if ($('#bimeGozar_nesbatBaBimeShode').val() != 'خود شخص') return false;
    }
    if ($('#bimeGozar_nesbatBaBimeShode').val() == 'خود شخص') {
        if (parseInt(bs_shakhs_id) != parseInt(bg_shakhs_id))
        {
            $.ajax({
                type: "POST",
                async: false,
                data: "bimeShoodeShakhs.id=" + bs_shakhs_id + "&bimeGozarShakhs.id=" + bg_shakhs_id ,
                url: "checkNesbat",
                success: function (msg)
                {
                    if (msg == 'false')
                    {
                        return false;
                    }
                }
            });
        }
    }
    if ($("#haghighiorhoghoughiremember").val() == 'HOGHOGHI') {
        var theNesbat = $('#bimeGozar_nesbatBaBimeShode').val();
        if (theNesbat != 'كارفرما' && theNesbat != 'وام دهنده' && theNesbat != 'نامعلوم') {
            return false;
        }
    }
    var n = $('#bimeGozar_nesbatBaBimeShode').val();
    var m = $('#bimeGozar_shakhs_jensiat').val();
    return nesbatValidate(m, n);
}
function nesbatValidate(m, n) {
    if (m == 'زن') {
        if (n == 'همسر / مرد' || n == 'فرزند / پسر' || n == 'پدر' || n == 'برادر' || n == 'دايي' || n == 'شوهر خاله' || n == 'عمو' || n == 'شوهر عمه' || n == 'نوه / پسري' || n == 'برادرزاده' || n == 'داماد' || n == 'پدربزرگ' || n == 'شوهر خواهر' || n == 'پدر همسر' || n == 'برادر همسر' || n == 'پسر دايي' || n == 'پسر عمو' || n == 'پسر خاله' || n == 'پسر عمه' ||n == 'پدرخوانده') return false;
    } else if (m == 'مرد') {
        if (n == 'همسر / زن' || n == 'فرزند / دختر' || n == 'مادر' || n == 'خواهر' || n == 'زن دايي' || n == 'خاله' || n == 'زن عمو' || n == 'عمه' || n == 'نوه / دختري' || n == 'خواهرزاده' || n == 'عروس' || n == 'مادربزرگ' || n == 'زن برادر' || n == 'مادر همسر' || n == 'خواهر همسر' || n == 'دختر دايي' || n == 'دختر عمو' || n == 'دختر خاله' || n == 'دختر عمه'||n=='مادرخوانده' || n=='همسر برادر شوهر') return false;
    }
    return true;
}
function val_EKASB_nesbatBabimeShode() {
    var m = $('#bimeShode_shakhs_jensiat').val();
    var n = $('#EKASB_nesbatBabimeShode').val();
    if (m == 'زن') {
        if (n == 'همسر / زن') return false;
    } else if (m == 'مرد') {
        if (n == 'همسر / مرد') return false;
    }
    return true;
}

function val_zinfeTekrari(caller) {
    var kodeHayeMelliFout = jQuery("#EKASB_Fout_tbl").getCol("kodeMelli", false, false);
    return true;
}

function val_dastmozd_nonzero(caller) {
    if ($('#bimeGozar_daramdeMahiane').val() != 0) {
        return true;
    }
    return false;
}
function disablePishnehadTabs(divToDisable) {
    $("#" + divToDisable + " input[type=text]").each(function () {
        $(this).removeAttr("onkeyup");
        if (!$(this).hasClass('noAnyDisable')) {
            if ($(this).hasClass('datePkr'))
            {
                $('#'+this.id+'_btn').remove();
            }
            $(this).attr("readonly", "readonly");
        }
        $('#namayesheEtelaateFard').removeAttr('readonly');
        $('#namayesheEtelaateFard2').removeAttr('readonly');
    });
    $("#" + divToDisable + " input[type=button]").each(function () {
        if (this.id != 'namayesheEtelaateFard2' && this.id != 'namayesheEtelaateFard' && !$(this).hasClass('noAnyDisable'))
            $(this).removeAttr("onclick");
    });
    $("#" + divToDisable + " .ui-icon").each(function () {
        if (this.id != 'namayesheEtelaateFard2' && this.id != 'namayesheEtelaateFard' && !$(this).hasClass('noAnyDisable'))
            $(this).removeAttr("onclick");
    });

    $("#" + divToDisable + " input[type=radio]").each(function () {
    });

    $("#" + divToDisable + " select").each(function () {
        if (!$(this).hasClass('noAnyDisable'))
            $(this).attr('disabled', true);
    });
    $("#" + divToDisable + " textarea").each(function () {
        $(this).attr('disabled', true);
    });
    var questionCounter = 0;
    $("#" + divToDisable + " .my-comments").each(function () {
        if (($("#soal_x_" + questionCounter).attr("checked") && (questionCounter != 3)) || ((!$("#soal_x_" + questionCounter).attr("checked")) && (questionCounter == 3))) {
            $(this).hide();
        }
        questionCounter++;
    });
    $("#" + divToDisable + " .ui-pg-table .navtable").each(function () {
        $(this).hide();
    });
    $("#" + divToDisable + " .dblRadio").each(function () {
        $(this).buttonset().buttonset("disable");
    });
    $("#" + divToDisable + " .ui-button-text-only").each(function () {
        $(this).removeClass("ui-state-disabled");
    });

}
function disableHaghighiElements() {
    $('#addressBimeGozar_neshaniManze').attr('disabled', true);
    $('#addressBimeGozar_neshaniManze').addClass("ui-state-disabled");
    $('#addressBimeGozar_kodePostiManzel').attr('disabled', true);
    $('#addressBimeGozar_kodePostiManzel').addClass("ui-state-disabled");
    $('#addressBimeGozar_codeTelephoneManzel').attr('disabled', true);
    $('#addressBimeGozar_codeTelephoneManzel').addClass("ui-state-disabled");
    $('#addressBimeGozar_telephoneManzel').attr('disabled', true);
    $('#addressBimeGozar_telephoneManzel').addClass("ui-state-disabled");
    $('#bimeGozar_daramdeMahiane').attr('disabled', true);
    $('#bimeGozar_daramdeMahiane').addClass("ui-state-disabled");
    $('#bimeGozar_OstanZendegi').attr('disabled', true);
    $('#bimeGozar_OstanZendegi').addClass("ui-state-disabled");
    $('#bimeGozar_ShahrZendegi').attr('disabled', true);
    $('#bimeGozar_ShahrZendegi').addClass("ui-state-disabled");
    $('#buttonforzendegiselect').hide();
    $('#bimeGozar_OstanZendegi').val('');
    $('#bimeGozar_OstanZendegiId').val('');
    $('#bimeGozar_ShahrZendegi').val('');
    $('#bimeGozar_ShahrZendegiId').val('');
//    $('#bimeGozar_nesbatBaBimeShode').val('كارفرما');
}
function enableHaghighiElements() {
    $('#addressBimeGozar_neshaniManze').removeAttr('disabled');
    $('#addressBimeGozar_neshaniManze').removeClass('ui-state-disabled');
    $('#addressBimeGozar_kodePostiManzel').removeAttr('disabled');
    $('#addressBimeGozar_kodePostiManzel').removeClass('ui-state-disabled');
    $('#addressBimeGozar_codeTelephoneManzel').removeAttr('disabled');
    $('#addressBimeGozar_codeTelephoneManzel').removeClass('ui-state-disabled');
    $('#addressBimeGozar_telephoneManzel').removeAttr('disabled');
    $('#addressBimeGozar_telephoneManzel').removeClass('ui-state-disabled');
    $('#bimeGozar_daramdeMahiane').removeAttr('disabled');
    $('#bimeGozar_daramdeMahiane').removeClass("ui-state-disabled");
    $('#bimeGozar_OstanZendegi').removeAttr('disabled');
    $('#bimeGozar_OstanZendegi').removeClass("ui-state-disabled");
    $('#bimeGozar_ShahrZendegi').removeAttr('disabled');
    $('#bimeGozar_ShahrZendegi').removeClass("ui-state-disabled");
    $('#buttonforzendegiselect').show();
    //$('#bimeGozar_nesbatBaBimeShode').val('خود شخص');
}