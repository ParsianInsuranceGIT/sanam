$(function(){
    // -----------------------------------------------------------------------------------------------------------------
    $(".expandableTitleBar").each(function(){
        //                        $(this).children(".content").hide();
        $(this).children(".heading").addClass("ui-widget-header ui-corner-all ui-helper-clearfix");

        $(this).children(".heading").click(function()
        {
            jQuery(this).next(".content").slideToggle(500);
            $.validationEngine.closePrompt('.formError', true);
        });
    });
    $(".staticTitleBar").each(function(){
        $(this).children(".heading").addClass("ui-widget-header ui-corner-all ui-helper-clearfix");
    });

    $(".listenTextFieldEnter").each(function(){
        var functionCallName = $(this).attr('functionCall');
        $(this).children().find("input,textarea").each(function(){
            $(this).addClass("listenEnter");
            $(this).attr('functionCall',functionCallName);
        });
    });

    $(".jtable th").each(function(){
        $(this).addClass("ui-state-default");
    });
    $(".jtable td").each(function(){
        $(this).addClass("ui-widget-content");
    });
    $(".jtable tr").hover(function(){
//        $(this).children("td").addClass("ui-state-hover");
    },function(){
//        $(this).children("td").removeClass("ui-state-hover");
    });
//    $(".jtable tr").click(function(){
        //                $(this).children("td").toggleClass("ui-state-highlight");
//    });
    // -----------------------------------------------------------------------------------------------------------------
    $("input,textarea").each(function(){$(this).addClass("ui-state-default  ui-corner-all");});
    $("select").each(function(){$(this).addClass("ui-state-default  ui-corner-all");});
    $(".help, .comment").tipsy({gravity:'s'});
    $("form, .vld").validationEngine({promptPosition:"topLeft"});
    $('.dblRadio').buttonset();
    $(".tipsi").tipsy({gravity:'s'});

    $(".datePkr").each(function() {
        var inputFieldId = this.id;
        if(!this.id){
            alert('Your datePkr does not have id attribute.');
        }else{
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
    $(".digitSeparators").each(function(){$(this).keyup();});
    $(".digitSeparatorsManfi").each(function(){$(this).keyup();});
});


$(".listenEnter").live('keyup', function(e) {
    if(e.keyCode == 13) {
        eval($(this).attr('functionCall'));
    }
});

$(".digitSeparators").live('keyup', function() {
    var v=jQuery.global.format(jQuery.global.parseInt($(this).val()), "n0");
    if(v.indexOf("NaN")!=-1){
        v="";
    }
    $(this).val(v);
});

$(".digitSeparatorsManfi").live('keyup', function() {
    if($(this).val() != '-')
    {
    var v=jQuery.global.format(jQuery.global.parseInt($(this).val()), "n0");
    if(v.indexOf("NaN")!=-1){
        v="";
    }
    $(this).val(v);
    }
});

function confirmMessage(msg, title, okFunc){
    if(msg == '')msg = "آیا تایید می کنید؟";
    if(title == '')title = "تایید";
    $('body').append('<div id="cnfMsg" style="display:none;direction:rtl;">'+msg+'</div>');
    $('#cnfMsg').dialog({
        modal:true, resizable:false, closeText: "",
        maxWidth: 640, maxHeight:600, minHeight: 25, minWidth: 150,
        title:title,
        close:function() {$("#cnfMsg").remove();$.validationEngine.closePrompt('.formError', true);},
        buttons: {
            "cancel": {
                text:'انصراف',
                click:function() {
                    $(this).dialog("close");
                    return false;
                }
            },
            "ok":{
                text:'تایید',
                click:function() {
                    $(this).dialog("close");
                    okFunc();
                    return true;
                }
            }
        }
    });
}
function alertMessage(msg){
    $('body').append('<div id="alrtMsg" style="display:none;direction:rtl;text-align: right;">'+msg+'</div>');
    $('#alrtMsg').dialog({
        modal:true, resizable:false, closeText: "",
        maxWidth: 640, maxHeight:600, minHeight: 25, minWidth: 150,
        close:function() {$("#alrtMsg").remove();$.validationEngine.closePrompt('.formError', true);},
        buttons: {
            "بستن":function() {
                $(this).dialog("close");
                return false;
            }
        }
    });
}

function alertMessageWithTitle(msg,titleTxt){
    $('body').append('<div id="alrtMsg" style="display:none;direction:rtl;text-align: right;">'+msg+'</div>');
    $('#alrtMsg').dialog({
        title:titleTxt,modal:true, resizable:false, closeText: "",
        maxWidth: 640, maxHeight:600, minHeight: 25, minWidth: 150,
        close:function() {$("#alrtMsg").remove();$.validationEngine.closePrompt('.formError', true);},
        buttons: {
            "بستن":function() {
                $(this).dialog("close");
                return false;
            }
        }
    });
}

function alertMessageByLock(msg, func){
    $('body').append('<div id="alrtMsg" style="display:none;direction:rtl;text-align: right;">'+msg+'</div>');
    $('#alrtMsg').dialog({
        modal:true, resizable:false, closeText: "",
        maxWidth: 640, maxHeight:600, minHeight: 25, minWidth: 150,
        close:function() {$("#alrtMsg").remove();$.validationEngine.closePrompt('.formError', true);},
        buttons: {
            "بستن":function() {
                $(this).dialog("close");
                func();
                return false;
            }
        }
    });
}

function reloadPage() {
    location.reload(true);
}

function goToPage(url) {
    window.location = url;
}

function dialogForm(id,title,func){
    $.validationEngine.closePrompt('.formError', true);
    var formArray = new Array();
    if ($("#shomareMoshtariAutomobil") != undefined){
        $("#shomareMoshtariAutomobil").hide();
        $("#shomareMoshtariAutomobilLbl").hide();
    }
    if ($("#namayandeNameCredebitDiv") != undefined){
        $("#namayandeNameCredebitDiv").hide();
    }
    $('#'+id+' input,#'+id+' select,#'+id+' textarea').each(function(){
        formArray.push(this.value);
        $(this).attr("disabled", false);
    });
    formArray.reverse();
    $('#'+id).dialog({
        modal:true, resizable:false, closeText: "",
        width: 640, maxHeight:600, minHeight: 25,
        title:title,
        close:function() {
            $('#'+id+' input,#'+id+' select,#'+id+' textarea').each(function(){
                $(this).val(formArray.pop()).change();
            });
            $.validationEngine.closePrompt('.formError', true);},
        buttons: {
            "تایید": function() {
                if($.validationEngine.submitValidation($('#'+id)) === false){
                    if ($('#noeEtebareDarHaleSabt') != undefined && $('#noeEtebareDarHaleSabt').val() == "PARDAKHT_SHENASEDAR" && $('#fishAmountAutomobil').val() != undefined && $('#fishAmountAutomobil').val() != ""){
                        var bankName = document.getElementById("bankMaghsadAutomobil").value;
                        $.ajax({
                            type: "POST",
                            async: false,
                            data: $("#sabteEtebareDasti_fishAutomobil").serialize(),
                            url: "generateCodeMoshtariCredebit?bankNameCodeMoshtari="+bankName,
                            success: function (response) {
                                $('#shomareMoshtariAutomobil').val(response.trim());
//                                if ($('#shomareMoshtariAutomobil').val() != "")
//                                    alertMessage("شناسه پرداخت برابر است با" + "  " + $("#shomareMoshtariAutomobil").val(),"شناسه پرداخت");
//                                else
//                                    alertMessage("به دلیل نداشتن شناسه پرداخت، اعتبار مورد نظر ایجاد نشد");
                                if ($('#shomareMoshtariAutomobil').val() == "")
                                    alertMessage("به دلیل نداشتن شناسه پرداخت، اعتبار مورد نظر ایجاد نشد");
                            }
                        });
                        func();
                        $(this).dialog("close");
                    }else if($('#noeEtebareDarHaleSabt') != undefined && $('#noeEtebareDarHaleSabt').val() == "DARYAFTE_CHECK"){
                        chg_etebarType($('#noeEtebareDarHaleSabt').val());
                                $.ajax({
                                    type: "POST",
                                    url: "checkEtebarDasti",
                                    data: sabteEtebareDastiData,
                                    success: function (response) {
                                        if (response.indexOf('SARRESID_BEFORE_TODAY') != -1) {
                                            alertMessage("تاریخ سررسید باید بعد از امروز باشد.") ;
                                        }else{
                                            func();
                                            $(".ui-dialog-content").dialog("close");
                                        }
                                    }
                                });
                    }
                    else{
                        func();
                        $(this).dialog("close");
                    }

                    $('#addressBimeGozar_neshaniMahalleKar').focus();
                }
            },
            "انصراف": function() {$(this).dialog("close");}
        }
    });
}

function viewDialogForm(id,title,func){
    $.validationEngine.closePrompt('.formError', true);
    if ($("#shomareMoshtariAutomobil") != undefined){
        $("#shomareMoshtariAutomobil").show();
        $("#shomareMoshtariAutomobilLbl").show();
    }
    if ($("#namayandeNameCredebitDiv") != undefined){
        $("#namayandeNameCredebitDiv").show();
    }
    var formArray = new Array();
    $('#'+id+' input,#'+id+' select,#'+id+' textarea').each(function(){
        formArray.push(this.value);
    });
    formArray.reverse();
    $('#'+id).dialog({
        modal:true, resizable:false, closeText: "",
        width: 640, maxHeight:600, minHeight: 25,
        open : func,
        title:title,
        close:function() {
            $('#'+id+' input,#'+id+' select,#'+id+' textarea').each(function(){
                $(this).val(formArray.pop()).change();
                $(this).attr("disabled", "disabled");


            });
            $.validationEngine.closePrompt('.formError', true);},
        buttons: {
            "انصراف": function() {$(this).dialog("close");}
        }
    });
}

function dialogBoxWithoutTaeed(id,title){
    $.validationEngine.closePrompt('.formError', true);
    var formArray = new Array();
    $('#'+id+' input,#'+id+' select,#'+id+' textarea').each(function(){
        formArray.push(this.value);
    });
    formArray.reverse();
    $('#'+id).dialog({
        modal:true, resizable:false, closeText: "",
        width: 200, maxHeight:600, minHeight: 25,
        title:title,
        close:function() {
            $('#'+id+' input,#'+id+' select,#'+id+' textarea').each(function(){
                $(this).val(formArray.pop()).change();
            });
            $.validationEngine.closePrompt('.formError', true);
        }
    });
}

function dialogFormWithFocus(id,title,func,nextField){
    $.validationEngine.closePrompt('.formError', true);
    var formArray = new Array();
    $('#'+id+' input,#'+id+' select,#'+id+' textarea').each(function(){
        formArray.push(this.value);
    });
    formArray.reverse();
    $('#'+id).dialog({
        modal:true, resizable:false, closeText: "",
        width: 640, maxHeight:600, minHeight: 25,
        title:title,
        close:function() {
            $('#'+id+' input,#'+id+' select,#'+id+' textarea').each(function(){
                $(this).val(formArray.pop()).change();
            });
            $.validationEngine.closePrompt('.formError', true);},
        buttons: {
            "تایید": function() {
                if($.validationEngine.submitValidation($('#'+id)) === false){
                    func();
                    $(this).dialog("close");
                    $('#' + nextField).focus();
                }
            },
            "انصراف": function() {$(this).dialog("close");}
        }
    });
}


function enableItem(id, val) {
    if($('#' + id).attr('disabled')){
        $('#' + id).removeClass('ui-state-disabled');
        $('#' + id).removeAttr("disabled");
        if(val) $('#' + id).val(val);
    }
}
function disableItem(id) {
    var val = $('#' + id).val();
    if(!$('#' + id).attr('disabled')){
        $('#' + id).addClass('ui-state-disabled');
        $('#' + id).attr("disabled", true);
        $('#' + id).val('');
    }
    $.validationEngine.closePrompt($('#' + id));
    return val;
}
//------------------------------------------------------------------------------------------------------------ShahrOstan
function fillShahr(cityIdField, cityNameField, nextField)
{
    dialogFormWithFocus('searchCity','جستجوی شهر',function(){
        $('#' + cityNameField).val($('#selectedShahr').val());
        $('#' + cityIdField).val($('#shahrId').val());
    }, nextField);
}
function fillShahrForPid(cityIdField, cityNameField, nextField) {
    dialogFormWithFocus('searchCity', 'جستجوی شهر', function () {
        $('#' + cityNameField).val($('#selectedShahr').val());
        $('#' + cityIdField).val($('#shahrIdForPid').val());
    }, nextField);
}
function fillOstan(ostanIdField, ostanNameField, nextField)
{
    dialogFormWithFocus('searchCity','جستجوی استان',function(){
        $('#' + ostanNameField).val($('#selectedOstan').val());
        $('#' + ostanIdField).val($('#ostanId').val());
    }, nextField);
}
function fillShahrOstan(cityIdField, cityNameField, ostanIdField, ostanNameField, nextField) {
    dialogFormWithFocus('searchCity', 'جستجوی شهر', function () {
        $('#' + cityNameField).val($('#selectedShahr').val());
        $('#' + ostanNameField).val($('#selectedOstan').val());
        $('#' + cityIdField).val($('#shahrId').val());
        $('#' + ostanIdField).val($('#ostanId').val());
    }, nextField);
}

function dialogFormWithFocus(id, title, func, nextField) {
    $.validationEngine.closePrompt('.formError', true);
    var formArray = new Array();
    $('#' + id + ' input,#' + id + ' select,#' + id + ' textarea').each(function () {
        formArray.push(this.value);
    });
    formArray.reverse();
    $('#' + id).dialog({
        modal:true, resizable:false, closeText:"",
        width:640, maxHeight:600, minHeight:25,
        title:title,
        close:function () {
            $('#' + id + ' input,#' + id + ' select,#' + id + ' textarea').each(function () {
                $(this).val(formArray.pop()).change();
            });
            $.validationEngine.closePrompt('.formError', true);
        },
        buttons:{
            "تایید":function () {
                if ($.validationEngine.submitValidation($('#' + id)) === false) {
                    func();
                    $(this).dialog("close");
                    $('#' + nextField).focus();
                }
            },
            "انصراف":function () {
                $(this).dialog("close");
            }
        }
    });
}

function getOrdinal(num) {
    if(num == 1)
        return "اول";
    if(num == 2)
        return "دوم";
    if(num == 3)
        return "سوم";
    if(num == 4)
        return "چهارم";
    return "?";
}

function scrollTo(id) {
    $('html,body').animate({
        scrollTop: $("#"+id).offset().top},
        2000);
}

function dialogFormStatic(id, title, func) {
    $.validationEngine.closePrompt('.formError', true);
    var formArray = new Array();
    $('#' + id + ' input,#' + id + ' select,#' + id + ' textarea').each(function () {
        formArray.push(this.value);
    });
    formArray.reverse();
    $('#' + id).dialog({
        modal:true, resizable:false, closeText:"",
        width:640, maxHeight:600, minHeight:25,
        title:title,
        close:function () {
            $('#' + id + ' input,#' + id + ' select,#' + id + ' textarea').each(function () {
                $(this).val(formArray.pop()).change();
            });
            $.validationEngine.closePrompt('.formError', true);
        },
        buttons:{
            "تایید":function () {
                if ($.validationEngine.submitValidation($('#' + id)) === false) {
                    func();
                }
            },
            "انصراف":function () {
                $(this).dialog("close");
            }
        }
    });
}
//----------------------------------------------------------------------------------------------------------------jqGrid
function jqGrid_DeleteRow(tblId) {
    var gr = jQuery("#"+tblId).jqGrid('getGridParam','selrow');
    if( gr != null ){
        confirmMessage('آیا از حذف این ردیف مطمئن هستید؟', 'تایید حذف', function(){
                jQuery("#"+tblId).jqGrid('delRowData',gr);
            $('.notSavedStar').text('*');
        });
    }else {
        alertMessage("لطفا یکی از ردیف ها را انتخاب کنید.");
    }
}
function jqGrid_addNewRow(tblId,popFormDivId,popFormTitle,popFormFieldsIdPrefix,inputNames){
    var sizeOfRecords = jQuery("#"+tblId).getGridParam("records");
    dialogForm(popFormDivId,popFormTitle,function(){
        var newRow = {};
        var canAdd = true;
        var pedarCounter = 0;
        $('#'+popFormDivId+' input,#'+popFormDivId+' select,#'+popFormDivId+' textarea').each(function(){
            var fId = this.id.split(popFormFieldsIdPrefix+'_')[1];
            if(!$('#'+popFormFieldsIdPrefix+'_'+fId).is(':disabled')){
                if($('#'+popFormFieldsIdPrefix+'_'+fId).val() == 'پدر'){
                    $("#content_8 input[value=پدر]").each(function(){
                        pedarCounter++;
                    });
                    if(pedarCounter==0){
                        newRow[fId] = $('#'+popFormFieldsIdPrefix+'_'+fId).val()+"<input type='hidden' class='khanevadeadder' name='"+inputNames+"["+sizeOfRecords+"]."+fId+"' value='"+$('#'+popFormFieldsIdPrefix+'_'+fId).val()+"'/>";
                    }else{
                        newRow[fId] == '';
                        alertMessage("شما نمی توانید دو ورودی برای نسبت بیمه ای پدر ثبت نمایید.");
                        canAdd=false;
                    }
                }else if($('#'+popFormFieldsIdPrefix+'_'+fId).val() == 'مادر'){
                    $("#content_8 input[value=مادر]").each(function(){
                        pedarCounter++;
                    });
                    if(pedarCounter==0){
                        newRow[fId] = $('#'+popFormFieldsIdPrefix+'_'+fId).val()+"<input type='hidden' class='khanevadeadder' name='"+inputNames+"["+sizeOfRecords+"]."+fId+"' value='"+$('#'+popFormFieldsIdPrefix+'_'+fId).val()+"'/>";
                    }else{
                        newRow[fId] == '';
                        alertMessage("شما نمی توانید دو ورودی برای نسبت بیمه ای مادر ثبت نمایید.");
                        canAdd=false;
                    }
                }else if($('#'+popFormFieldsIdPrefix+'_'+fId).val() == 'پدر همسر'){
                    $("#content_8 input[value=پدر همسر]").each(function(){
                        pedarCounter++;
                    });
                    if(pedarCounter==0){
                        newRow[fId] = $('#'+popFormFieldsIdPrefix+'_'+fId).val()+"<input type='hidden' class='khanevadeadder' name='"+inputNames+"["+sizeOfRecords+"]."+fId+"' value='"+$('#'+popFormFieldsIdPrefix+'_'+fId).val()+"'/>";
                    }else{
                        newRow[fId] == '';
                        alertMessage("شما نمی توانید دو ورودی برای نسبت بیمه ای پدر همسر ثبت نمایید.");
                        canAdd=false;
                    }
                }else if($('#'+popFormFieldsIdPrefix+'_'+fId).val() == 'مادر همسر'){
                    $("#content_8 input[value=مادر همسر]").each(function(){
                        pedarCounter++;
                    });
                    if(pedarCounter==0){
                        newRow[fId] = $('#'+popFormFieldsIdPrefix+'_'+fId).val()+"<input type='hidden' class='khanevadeadder' name='"+inputNames+"["+sizeOfRecords+"]."+fId+"' value='"+$('#'+popFormFieldsIdPrefix+'_'+fId).val()+"'/>";
                    }else{
                        newRow[fId] == '';
                        alertMessage("شما نمی توانید دو ورودی برای نسبت بیمه ای مادر همسر ثبت نمایید.");
                        canAdd=false;
                    }
                }else{
                    newRow[fId] = $('#'+popFormFieldsIdPrefix+'_'+fId).val()+"<input type='hidden' class='khanevadeadder' name='"+inputNames+"["+sizeOfRecords+"]."+fId+"' value='"+$('#'+popFormFieldsIdPrefix+'_'+fId).val()+"'/>";
                }
            }else{newRow[fId] = '';}
        });

        if(canAdd){
            sizeOfRecords++;
            jQuery("#"+tblId).jqGrid('addRowData',sizeOfRecords,newRow);
            $('.notSavedStar').text('*');
        }



    });
}
function jqGrid_editRow(tblId,popFormDivId,popFormTitle,popFormFieldsIdPrefix,inputNames){
    var gr = jQuery("#"+tblId).jqGrid('getGridParam','selrow');
    if( gr != null ){
        var rowData = jQuery("#"+tblId).getRowData(gr);
        dialogForm(popFormDivId,popFormTitle,function(){
            var editedRowData = {};
            $('#'+popFormDivId+' input,#'+popFormDivId+' select,#'+popFormDivId+' textarea').each(function(){
                var fId = this.id.split(popFormFieldsIdPrefix+'_')[1];
                if(!$('#'+popFormFieldsIdPrefix+'_'+fId).is(':disabled')){
                    if(rowData[fId]){
                    var dbId = rowData[fId].split('[')[1].split(']')[0];
                    if(fId == "darsadeSahm"){
                    editedRowData[fId] = $('#'+popFormFieldsIdPrefix+'_'+fId).val()+"<input type='hidden' name='"+inputNames+"["+dbId+"]."+fId+"' id='pishnehad_estefadeKonandeganAzSarmayeBime_"+dbId+"_"+fId+"' value='"+$('#'+popFormFieldsIdPrefix+'_'+fId).val()+"'/>";
                    }else{
                    editedRowData[fId] = $('#'+popFormFieldsIdPrefix+'_'+fId).val()+"<input type='hidden' name='"+inputNames+"["+dbId+"]."+fId+"' value='"+$('#'+popFormFieldsIdPrefix+'_'+fId).val()+"'/>";
                    }}
                }else{editedRowData[fId] = '';}
            });
            jQuery("#"+tblId).jqGrid('setRowData',gr,editedRowData);
            $('.notSavedStar').text('*');
        });
        $('#'+popFormDivId+' input,#'+popFormDivId+' select,#'+popFormDivId+' textarea').each(function(){
            var fId = this.id.split(popFormFieldsIdPrefix+'_')[1];
            if(fId && fId.substring(fId.length-3, fId.length) != '_id')
            {
                $('#'+popFormFieldsIdPrefix+'_'+fId).val(rowData[fId].split('<input')[0]).change();
            }
        });
    }else {
        alertMessage("لطفا یکی از ردیف ها را انتخاب کنید.");
    }
}
//----------------------------------------------------------------------------------------------------------------------
function val_codeMelli(caller){
    var codeMelli = $(caller).val();
    if(codeMelli.length == 0 ) {
       return true;
    }

    if(codeMelli.length != 10  ||
       codeMelli=='0000000000' ||
       codeMelli=='1111111111' ||
       codeMelli=='2222222222' ||
       codeMelli=='3333333333' ||
       codeMelli=='4444444444' ||
       codeMelli=='5555555555' ||
       codeMelli=='6666666666' ||
       codeMelli=='7777777777' ||
       codeMelli=='8888888888' ||
       codeMelli=='9999999999' ){
        return false;
    }
    c = parseInt(codeMelli.charAt(9));
    n = parseInt(codeMelli.charAt(0))*10 +
        parseInt(codeMelli.charAt(1))*9 +
        parseInt(codeMelli.charAt(2))*8 +
        parseInt(codeMelli.charAt(3))*7 +
        parseInt(codeMelli.charAt(4))*6 +
        parseInt(codeMelli.charAt(5))*5 +
        parseInt(codeMelli.charAt(6))*4 +
        parseInt(codeMelli.charAt(7))*3 +
        parseInt(codeMelli.charAt(8))*2;
    r = n - parseInt(n/11)*11;
    if ((r == 0 && r == c) || (r == 1 && c == 1) || (r > 1 && c == 11 - r)){
        return true;
    }else{
        return false;
    }
}

function isDateGreaterThan(currentDate, targetDate)
{
    var target_m,target_y, target_d;
    var current_m,current_y, current_d;
    if(targetDate==null || targetDate== '' || currentDate == null || currentDate == '')
        return false;

    target_y = jQuery.global.parseInt(targetDate.split('/')[0]);
    target_m = jQuery.global.parseInt(targetDate.split('/')[1]);
    target_d = jQuery.global.parseInt(targetDate.split('/')[2]);

    current_y = jQuery.global.parseInt(currentDate.split('/')[0]);
    current_m = jQuery.global.parseInt(currentDate.split('/')[1]);
    current_d = jQuery.global.parseInt(currentDate.split('/')[2]);

    if(current_y>target_y)
    {
        return true;
    }
    else if(current_y<target_y)
    {
        return false;
    }
    else //current_y==target_y
    {
        if (current_m > target_m)
        {
            return true;
        }
        else if (current_m < target_m)
        {
            return false;
        }
        else //current_m==target_m
        {
            if (current_d > target_d)
            {
                return true;
            }
            else //(current_d==target_d) || (current_d < target_d)
            {
                return false;
            }
        }
    }
}
function isDateGreaterThanOrEqual(currentDate, targetDate)
{
    var target_m,target_y, target_d;
    var current_m,current_y, current_d;
    if(targetDate==null || targetDate== '' || currentDate == null || currentDate == '')
        return false;

    target_y = jQuery.global.parseInt(targetDate.split('/')[0]);
    target_m = jQuery.global.parseInt(targetDate.split('/')[1]);
    target_d = jQuery.global.parseInt(targetDate.split('/')[2]);

    current_y = jQuery.global.parseInt(currentDate.split('/')[0]);
    current_m = jQuery.global.parseInt(currentDate.split('/')[1]);
    current_d = jQuery.global.parseInt(currentDate.split('/')[2]);

    if(current_y>target_y)
    {
        return true;
    }
    else if(current_y<target_y)
    {
        return false;
    }
    else //current_y==target_y
    {
        if (current_m > target_m)
        {
            return true;
        }
        else if (current_m < target_m)
        {
            return false;
        }
        else //current_m==target_m
        {
            if (current_d > target_d || current_d == target_d)
            {
                return true;
            }
            else//(current_d < target_d)
            {
                return false;
            }
        }
    }
}

function mohasebeyeSen(tarikheTavallod, baseDate){
    if(!tarikheTavallod) return 0;
    var base_m, base_y, base_day;
    if(baseDate == 'S') {
        base_m = jQuery.global.parseInt(srvr_date_mounth);
        base_y = jQuery.global.parseInt(srvr_date_year);
        base_day = jQuery.global.parseInt(srvr_date_day);
    } else {
        base_m = baseDate.split('/')[1];
        base_y = baseDate.split('/')[0];
        base_day = baseDate.split('/')[2];
    }
    var part1 = tarikheTavallod.split('/')[0];
    var m = tarikheTavallod.split('/')[1];
    var part2 = tarikheTavallod.split('/')[2];
    var y = (part1.length < part2.length) ? part2 : part1;
    var day = (part1.length < part2.length) ? part1 : part2;
    var ekhtelaafSaalBeMaah=12*(base_y - jQuery.global.parseInt(y));
    var ekhtelaafMaah=base_m-jQuery.global.parseInt(m);
    var senBarAsaasMaah=ekhtelaafSaalBeMaah+ekhtelaafMaah;
    if(base_day<day) senBarAsaasMaah--;
    return parseInt(senBarAsaasMaah/12)+parseInt((senBarAsaasMaah%12)/6);
}

function exportClicked() {
    $('.pagelinks').hide();
}

function validateShaba() {
    return true;
    // todo: you need to use a big number lib here to calculate the mod
//    var shaba = $('#shomareShaba').val();
//    alert(shaba);
//    if (shaba.length != 24) {
//        return false;
//    }
//
//    var checkNumber = (shaba.substring(2) + "1827" + shaba.substring(0, 2)) % 97;
//
//    return checkNumber == 1;
}