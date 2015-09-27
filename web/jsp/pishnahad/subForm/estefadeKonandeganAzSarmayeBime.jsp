<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 4/7/11
  Time: 2:24 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="varNames" scope="page" value='<%=new String[]
{
"olaviat",
"darsadeSahm",
"mahalleSodoorShenasnameh",
"jensiat",
"namePedar",
"mahalleSabt",
"mahalleTavallod",
"tarikhSabt",
"tarikhTavallod",
"kodeEghtesadi",
"kodeMelli",
"shomareSabt",
"shomareShenasname",
"nameKhanevadegi",
"name",
"nesbatBabimeShode",
"noeZiNafea"
}
%>'/>
<c:forEach var="row" items="${pishnehad.estefadeKonandeganAzSarmayeBime}" varStatus="i">
    <input type="hidden" name="pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].id" value="${row.id}"/>
</c:forEach>
<div style="width:780px; margin:0 auto">
    <table id="EKASB_hayat_tbl"></table>
    <div id="EKASB_hayat_pagernav"></div>
    <br/><input type="button" onclick="addEstefadeKonandeDarHayat();" style="margin:0 2px" value="جستجو"/>
    <br/><br/>
    <table id="EKASB_fout_tbl"></table>
    <div id="EKASB_fout_pagernav"></div>
    <br/><input type="button" onclick="addEstefadeKonandeDarFout();" style="margin:0 2px" value="جستجو" />
</div>

<script type="text/javascript">
function clearTable(){
    $("#EKASB_name").val("");
    $("#EKASB_nameKhanevadegi").val("");
    $("#EKASB_jensiat").val("مرد");
    $("#EKASB_shomareShenasname").val("");
    $("#EKASB_shomareSabt").val("");
    $("#EKASB_kodeMelli").val("");
    $("#EKASB_kodeEghtesadi").val("");
    $("#EKASB_tarikhTavallod").val("");
    $("#EKASB_tarikhSabt").val("");
    $("#EKASB_mahalleTavallod").val("");
    $("#EKASB_mahalleSabt").val("");
    $("#EKASB_darsadeSahm").val("");
    $("#EKASB_olaviat").val("");
}
function addEstefadeKonandeDarHayat(){
    openJosteju(false, function(id){
        $.post('/fillShakhs.action?shakhsId=' + id, function(msg) {
            $('#add_EKASB_hayat_tbl').click();
            EKASB_fillElements(msg);
        });
    });

}
function fillShakhsInfoByAjax(id){
    $.post('/fillShakhs.action?shakhsId=' + id, function(msg) {
        EKASB_fillElements(msg);
    });
}
function disableShakhsInfoFields(){
    EKASB_disableElements();
}
function enableShakhsInfoFields(){
    EKASB_enableElements();
}
function addEstefadeKonandeDarFout(){

    openJosteju(false, function(id){
        $.post('/fillShakhs.action?shakhsId=' + id, function(msg) {
            $('#add_EKASB_fout_tbl').click();
            EKASB_fillElements(msg);
        });
    });
}
function EKASB_enableElements(){
    $('#EKASB_name').removeAttr("disabled");
    $('#EKASB_name').removeClass("ui-state-disabled");
    $('#EKASB_nameKhanevadegi').removeAttr("disabled");
    $('#EKASB_nameKhanevadegi').removeClass("ui-state-disabled");
    $('#EKASB_jensiat').removeAttr("disabled");
    $('#EKASB_jensiat').removeClass("ui-state-disabled");
    $('#EKASB_shomareShenasname').removeAttr("disabled");
    $('#EKASB_shomareShenasname').removeClass("ui-state-disabled");
    $('#EKASB_shomareSabt').removeAttr("disabled");
    $('#EKASB_shomareSabt').removeClass("ui-state-disabled");
    $('#EKASB_kodeMelli').removeAttr("disabled");
    $('#EKASB_kodeMelli').removeClass("ui-state-disabled");
    $('#EKASB_kodeEghtesadi').removeAttr("disabled");
    $('#EKASB_kodeEghtesadi').removeClass("ui-state-disabled");
    $('#EKASB_tarikhTavallod').removeAttr("disabled");
    $('#EKASB_tarikhTavallod').removeClass("ui-state-disabled");
    $('#EKASB_tarikhSabt').removeAttr("disabled");
    $('#EKASB_tarikhSabt').removeClass("ui-state-disabled");
    $('#EKASB_mahalleTavallod').removeAttr("disabled");
    $('#EKASB_mahalleTavallod').removeClass("ui-state-disabled");
    $('#EKASB_mahalleSabt').removeAttr("disabled");
    $('#EKASB_mahalleSabt').removeClass("ui-state-disabled");
    $('#EKASB_mahalleSodoorShenasnameh').removeAttr("disabled");
    $('#EKASB_mahalleSodoorShenasnameh').removeClass("ui-state-disabled");
    $('#EKASB_namePedar').removeAttr("disabled");
    $('#EKASB_namePedar').removeClass("ui-state-disabled");
}
function EKASB_disableElements(){
    $('#EKASB_name').attr("disabled",true);
    $('#EKASB_name').addClass("ui-state-disabled");
    $('#EKASB_nameKhanevadegi').attr("disabled",true);
    $('#EKASB_nameKhanevadegi').addClass("ui-state-disabled");
    $('#EKASB_jensiat').attr("disabled",true);
    $('#EKASB_jensiat').addClass("ui-state-disabled");
    $('#EKASB_shomareShenasname').attr("disabled",true);
    $('#EKASB_shomareShenasname').addClass("ui-state-disabled");
    $('#EKASB_shomareSabt').attr("disabled",true);
    $('#EKASB_shomareSabt').addClass("ui-state-disabled");
    $('#EKASB_kodeMelli').attr("disabled",true);
    $('#EKASB_kodeMelli').addClass("ui-state-disabled");
    $('#EKASB_kodeEghtesadi').attr("disabled",true);
    $('#EKASB_kodeEghtesadi').addClass("ui-state-disabled");
    $('#EKASB_tarikhTavallod').attr("disabled",true);
    $('#EKASB_tarikhTavallod').addClass("ui-state-disabled");
    $('#EKASB_tarikhSabt').attr("disabled",true);
    $('#EKASB_tarikhSabt').addClass("ui-state-disabled");
    $('#EKASB_mahalleTavallod').attr("disabled",true);
    $('#EKASB_mahalleTavallod').addClass("ui-state-disabled");
    $('#EKASB_mahalleSabt').attr("disabled",true);
    $('#EKASB_mahalleSabt').addClass("ui-state-disabled");
    $('#entekhabTavallodButton').attr("disabled",true);
    $('#entekhabTavallodButton').addClass("ui-state-disabled");
    $('#entekhabSabtButton').attr("disabled",true);
    $('#entekhabSabtButton').addClass("ui-state-disabled");
    $('#EKASB_namePedar').attr("disabled",true);
    $('#EKASB_namePedar').addClass("ui-state-disabled");
    $('#EKASB_mahalleSodoorShenasnameh').attr("disabled",true);
    $('#EKASB_mahalleSodoorShenasnameh').addClass("ui-state-disabled");
    $('#entekhabSodorButton').attr("disabled",true);
    $('#entekhabSodorButton').addClass("ui-state-disabled");
}

function EKASB_fillElements(msg){
    $('#EKASB_name').val(msg.split(',')[6]);
    $('#EKASB_nameKhanevadegi').val(msg.split(',')[7]);
    var jensiat= msg.split(',')[30];
    if(jensiat.indexOf("زن") > -1)
    {
        $('#jensiat_zan').attr('selected', true);
        $('#jensiat_mard').attr('selected', '');
    }
    else
    {
        $('#jensiat_zan').attr('selected', '');
        $('#jensiat_mard').attr('selected', true);
    }
//    $('#EKASB_jensiat').val(msg.split(',')[30]);;
    $('#EKASB_shomareShenasname').val(msg.split(',')[8]);
    $('#EKASB_shomareSabt').val(msg.split(',')[9]);
    $('#EKASB_kodeMelli').val(msg.split(',')[10]);
    $('#EKASB_kodeEghtesadi').val(msg.split(',')[11]);
    $('#EKASB_tarikhTavallod').val(msg.split(',')[12]);
    $('#EKASB_tarikhSabt').val(msg.split(',')[13]);
    $('#EKASB_mahalleTavallod').val(msg.split(',')[27]);
    $('#EKASB_mahalleSabt').val(msg.split(',')[15]);
    $('#EKASB_mahalleSodoorShenasnameh').val(msg.split(',')[29]);
    $('#EKASB_namePedar').val(msg.split(',')[16]);
    if(val_codeMelli('#EKASB_kodeMelli'))
    {
        $('#zinaf_code_melli_shenaasaee_lbl').html('کد ملی');
        $('#ASF_iraniOrAtbaeKhareji_ei').attr('checked',true);
        $('#ASF_iraniOrAtbaeKhareji_ej').attr('checked',false);
    }
    else
    {
        $('#zinaf_code_melli_shenaasaee_lbl').html('کد شناسایی');
        $('#ASF_iraniOrAtbaeKhareji_ej').attr('checked', true);
        $('#ASF_iraniOrAtbaeKhareji_ei').attr('checked', false);

    }
}
$(function(){
    var colNames = [
        'اولويت',
        'درصد سهم',
        'محل صدور',
        'جنسیت',
        'نام پدر',
        'محل ثبت',
        'محل تولد',
        'تاريخ ثبت',
        'تاريخ تولد',
        'كد اقتصادي',
        'كد ملي',
        'شماره ثبت',
        'شماره شناسنامه ',
        'نام خانوادگي',
        'نام',
        'نسبت با بيمه شده',
        'نوع ذينفع',
        'vaziateFotVaHayat'
    ];
    var colModel=[
        <c:forEach var="varName" items="${varNames}" varStatus="i">
        {name:'${varName}', index:'${varName}',title:false,width:20,sortable:true, align:'center'},
        </c:forEach>
        {name:'vaziateFotVaHayat', index:'vaziateFotVaHayat',hidden:true}
//        {name:'1', index'1'},{name:'2'},{name:'1'},{name:'2'},{name:'1'},{name:'2'},{name:'1'},{name:'2'},{name:'1'},{name:'2'},{name:'1'},{name:'2'},{name:'1'},{name:'2'},{name:'1'},{name:'2'},{name:'1'},{name:'2'}
    ];

    jQuery("#EKASB_hayat_tbl").jqGrid({
        datatype: "local",hidegrid:false,viewrecords: true,sortorder: "desc",colNames:colNames,colModel:colModel,
        pager: '#EKASB_hayat_pagernav',
        caption:"در صورت حیات",
        height:"auto",width:780
    });
    jQuery("#EKASB_fout_tbl").jqGrid({
        datatype: "local",hidegrid:false,viewrecords: true,sortorder: "desc",colNames:colNames,colModel:colModel,
        pager: '#EKASB_fout_pagernav',
        caption:"در صورت فوت",
        height:"auto",width:780
    });
    jQuery("#EKASB_hayat_tbl").jqGrid('navGrid','#EKASB_hayat_pagernav',{search:false});
    jQuery("#EKASB_fout_tbl").jqGrid('navGrid','#EKASB_fout_pagernav',{search:false});
    $("#del_EKASB_hayat_tbl").unbind('click').click(function(){jqGrid_DeleteRow('EKASB_hayat_tbl')});
    $("#del_EKASB_fout_tbl").unbind('click').click(function(){jqGrid_DeleteRow('EKASB_fout_tbl')});
    $("#edit_EKASB_hayat_tbl").unbind('click').click(function(){$('#EKASB_for_chg').find('input[type="text"]').val('');if($('#EKASB_nesbatBabimeShode option[value=خود شخص]').length <= 0){$('#EKASB_nesbatBabimeShode').prepend('<option value="خود شخص">خود شخص</option>')};$('#EKASB_vaziateFotVaHayat').val('hayat');jqGrid_editRow('EKASB_hayat_tbl','add_EKASB_div','ویرایش استفاده کننده در قید حیات','EKASB','pishnehad.estefadeKonandeganAzSarmayeBime')});
    $("#edit_EKASB_fout_tbl").unbind('click').click(function(){$('#EKASB_for_chg').find('input[type="text"]').val('');$('#EKASB_vaziateFotVaHayat').val('fout');$('#EKASB_nesbatBabimeShode option[value=خود شخص]').remove();jqGrid_editRow('EKASB_fout_tbl','add_EKASB_div','ویرایش استفاده کننده فوت شده','EKASB','pishnehad.estefadeKonandeganAzSarmayeBime')});
    $("#add_EKASB_hayat_tbl").unbind('click').click(function(){$('#EKASB_for_chg').find('input[type="text"]').val('');if($('#EKASB_nesbatBabimeShode option[value=خود شخص]').length <= 0){$('#EKASB_nesbatBabimeShode').prepend('<option value="خود شخص">خود شخص</option>')};$('#EKASB_vaziateFotVaHayat').val('hayat');EKASB_jqGrid_addNewRow('EKASB_hayat_tbl','add_EKASB_div','اضافه کردن استفاده کننده در قید حیات','EKASB','pishnehad.estefadeKonandeganAzSarmayeBime')});
    $("#add_EKASB_fout_tbl").unbind('click').click(function(){$('#EKASB_for_chg').find('input[type="text"]').val('');$('#EKASB_vaziateFotVaHayat').val('fout');$('#EKASB_nesbatBabimeShode option[value=خود شخص]').remove();EKASB_jqGrid_addNewRow('EKASB_fout_tbl','add_EKASB_div','اضافه كردن استفاده كننده در صورت فوت','EKASB','pishnehad.estefadeKonandeganAzSarmayeBime')});

    $('#add_EKASB_hayat_tbl, #edit_EKASB_hayat_tbl, #del_EKASB_hayat_tbl, #refresh_EKASB_hayat_tbl').tipsy({gravity:'s'});
    $('#add_EKASB_fout_tbl, #edit_EKASB_fout_tbl, #del_EKASB_fout_tbl, #refresh_EKASB_fout_tbl').tipsy({gravity:'s'});

    <c:if test="${pishnehad.estefadeKonandeganAzSarmayeBime != null && fn:length(pishnehad.estefadeKonandeganAzSarmayeBime)>0}">

    var EKAZB_hayat_Data = [
        <c:forEach var="row" items="${pishnehad.estefadeKonandeganAzSarmayeBime}" varStatus="i">
        <c:if test="${row.vaziateFotVaHayat != 'fout'}">
        {
            '${varNames[0]}':"${row.olaviat}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[0]}' id='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[0]}' value='${row.olaviat}'/>",
            '${varNames[1]}':"${row.darsadeSahm}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[1]}' id='pishnehad_estefadeKonandeganAzSarmayeBime_${i.index}_${varNames[1]}' value='${row.darsadeSahm}'/>",
            '${varNames[2]}':"${row.mahalleSodoorShenasnameh}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[2]}' id='pishnehad_estefadeKonandeganAzSarmayeBime_${i.index}_${varNames[2]}' value='${row.mahalleSodoorShenasnameh}'/>",
            '${varNames[3]}':"${row.jensiat}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[3]}' value='${row.jensiat}'/>",
            '${varNames[4]}':"${row.namePedar}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[4]}' value='${row.namePedar}'/>",
            '${varNames[5]}':"${row.mahalleSabt}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[5]}' value='${row.mahalleSabt}'/>",
            '${varNames[6]}':"${row.mahalleTavallod}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[6]}' value='${row.mahalleTavallod}'/>",
            '${varNames[7]}':"${row.tarikhSabt}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[7]}' value='${row.tarikhSabt}'/>",
            '${varNames[8]}':"${row.tarikhTavallod}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[8]}' value='${row.tarikhTavallod}'/>",
            '${varNames[9]}':"${row.kodeEghtesadi}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[9]}' value='${row.kodeEghtesadi}'/>",
            '${varNames[10]}':"${row.kodeMelli}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[10]}' value='${row.kodeMelli}'/>",
            '${varNames[11]}':"${row.shomareSabt}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[11]}' value='${row.shomareSabt}'/>",
            '${varNames[12]}':"${row.shomareShenasname}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[12]}' value='${row.shomareShenasname}'/>",
            '${varNames[13]}':"${row.nameKhanevadegi}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[13]}' value='${row.nameKhanevadegi}'/>",
            '${varNames[14]}':"${row.name}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[14]}' value='${row.name}'/>",
            '${varNames[15]}':"${row.nesbatBabimeShode}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[15]}' value='${row.nesbatBabimeShode}'/>",
            '${varNames[16]}':"${row.noeZiNafea}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[16]}' value='${row.noeZiNafea}'/>",
            vaziateFotVaHayat:"${row.vaziateFotVaHayat}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].vaziateFotVaHayat' value='${row.vaziateFotVaHayat}'/>"
        }<c:if test="${i.index+1 != fn:length(pishnehad.estefadeKonandeganAzSarmayeBime)}">,</c:if>
        </c:if>
        </c:forEach>
    ];
    var EKAZB_fout_Data = [
        <c:forEach var="row" items="${pishnehad.estefadeKonandeganAzSarmayeBime}" varStatus="i">
        <c:if test="${row.vaziateFotVaHayat == 'fout'}">
        {
            '${varNames[0]}':"${row.olaviat}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[0]}' id='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[0]}' value='${row.olaviat}'/>",
            '${varNames[1]}':"${row.darsadeSahm}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[1]}' id='pishnehad_estefadeKonandeganAzSarmayeBime_${i.index}_${varNames[1]}' value='${row.darsadeSahm}'/>",
            '${varNames[2]}':"${row.mahalleSodoorShenasnameh}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[2]}' value='${row.mahalleSodoorShenasnameh}'/>",
            '${varNames[3]}':"${row.jensiat}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[3]}' value='${row.jensiat}'/>",
            '${varNames[4]}':"${row.namePedar}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[4]}' value='${row.namePedar}'/>",
            '${varNames[5]}':"${row.mahalleSabt}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[5]}' value='${row.mahalleSabt}'/>",
            '${varNames[6]}':"${row.mahalleTavallod}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[6]}' value='${row.mahalleTavallod}'/>",
            '${varNames[7]}':"${row.tarikhSabt}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[7]}' value='${row.tarikhSabt}'/>",
            '${varNames[8]}':"${row.tarikhTavallod}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[8]}' value='${row.tarikhTavallod}'/>",
            '${varNames[9]}':"${row.kodeEghtesadi}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[9]}' value='${row.kodeEghtesadi}'/>",
            '${varNames[10]}':"${row.kodeMelli}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[10]}' value='${row.kodeMelli}'/>",
            '${varNames[11]}':"${row.shomareSabt}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[11]}' value='${row.shomareSabt}'/>",
            '${varNames[12]}':"${row.shomareShenasname}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[12]}' value='${row.shomareShenasname}'/>",
            '${varNames[13]}':"${row.nameKhanevadegi}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[13]}' value='${row.nameKhanevadegi}'/>",
            '${varNames[14]}':"${row.name}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[14]}' value='${row.name}'/>",
            '${varNames[15]}':"${row.nesbatBabimeShode}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[15]}' value='${row.nesbatBabimeShode}'/>",
            '${varNames[16]}':"${row.noeZiNafea}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].${varNames[16]}' value='${row.noeZiNafea}'/>",
            vaziateFotVaHayat:"${row.vaziateFotVaHayat}<input type='hidden' name='pishnehad.estefadeKonandeganAzSarmayeBime[${i.index}].vaziateFotVaHayat' value='${row.vaziateFotVaHayat}'/>"
        }<c:if test="${i.index+1 != fn:length(pishnehad.estefadeKonandeganAzSarmayeBime)}">,</c:if>
        </c:if>
        </c:forEach>
    ];
    for(var i=0;i<=EKAZB_hayat_Data.length;i++) jQuery("#EKASB_hayat_tbl").jqGrid('addRowData',i+1,EKAZB_hayat_Data[i]);
    for(var i=0;i<=EKAZB_fout_Data.length;i++) jQuery("#EKASB_fout_tbl").jqGrid('addRowData',i+1,EKAZB_fout_Data[i]);
    $("#refresh_EKASB_hayat_tbl").click();
    $("#refresh_EKASB_fout_tbl").click();
    </c:if>
});

function validateOlaviateEsstefadeKonandegan(id){
    var sizeOfRecords = jQuery("#"+id).getGridParam("records");
    var olaviatha = new Array();
    var counter = 0;
    var entries = $("#"+id+" [name^=pishnehad.estefadeKonandeganAzSarmayeBime]").filter("[name$=olaviat]");
    var darsadSahm = $("#"+id+" [name^=pishnehad.estefadeKonandeganAzSarmayeBime]").filter("[name$=darsadeSahm]");
    for(var en in entries){
        counter ++;
        var numb = $(entries[en]).attr('name').split('[')[1].split(']')[0];
        if(!olaviatha[$(entries[en]).val()]) olaviatha[$(entries[en]).val()] = 0;
//        olaviatha[$(entries[en]).val()] += parseInt($('#pishnehad_estefadeKonandeganAzSarmayeBime_'+numb+'_darsadeSahm').val());
        olaviatha[$(entries[en]).val()] += parseInt($(darsadSahm[en]).val());
        if(counter == sizeOfRecords){
            var counter2 = 0
            for(var en2 in entries){
                counter2++;
                var numb = $(entries[en2]).attr('name').split('[')[1].split(']')[0];
                if(olaviatha[$(entries[en2]).val()] != 100){return false;}
                if(counter2 == sizeOfRecords){
                    return true;
                }
            }
        }
    };
}

function EKASB_jqGrid_addNewRow(tblId,popFormDivId,popFormTitle,popFormFieldsIdPrefix,inputNames){
    $("#estefadekonandemorde").val("true");
    var sizeOfRecords = jQuery("#EKASB_hayat_tbl").getGridParam("records")+jQuery("#EKASB_fout_tbl").getGridParam("records");
    dialogForm(popFormDivId,popFormTitle,function(){
        var newRow = {};

        $('#'+popFormDivId+' input,#'+popFormDivId+' select,#'+popFormDivId+' textarea').each(function(){
            var fId = this.id.split(popFormFieldsIdPrefix+'_')[1];
            if(!$('#'+popFormFieldsIdPrefix+'_'+fId).is(':disabled')){
                if(fId == "darsadeSahm"){
                    newRow[fId] = $('#'+popFormFieldsIdPrefix+'_'+fId).val()+"<input type='hidden' name='"+inputNames+"["+sizeOfRecords+"]."+fId+"' id='pishnehad_estefadeKonandeganAzSarmayeBime_"+sizeOfRecords+"_"+fId+"' value='"+$('#'+popFormFieldsIdPrefix+'_'+fId).val()+"'/>";
                }else{
                    newRow[fId] = $('#'+popFormFieldsIdPrefix+'_'+fId).val()+"<input type='hidden' name='"+inputNames+"["+sizeOfRecords+"]."+fId+"' value='"+$('#'+popFormFieldsIdPrefix+'_'+fId).val()+"'/>";
                }
            }else{newRow[fId] = '';}
        });
        var canAdd = true;
        for (var i = 1;i<$("#EKASB_olaviat").val();i++){
            if(tblId == 'EKASB_hayat_tbl'){
                var darsad = 0;
                darsad = darsad * 1;
                $("#"+tblId+" [name^=pishnehad.estefadeKonandeganAzSarmayeBime]").filter("[name$=olaviat]").each( function() {

                    if($(this).attr('value')==i){
                        var tr = $(this).parent().parent();

                        var counter = 0;
                        $(tr).find('td').each(function(){
                            counter++;
                            if (counter == 2){
                                var pardarsad = $(this).text();
                                pardarsad = pardarsad *1 ;

                                darsad = darsad+pardarsad;
                            }
                        });
                    }

                });
                if (darsad != 100){
                    canAdd = false;
                }

            }else{
                var darsad = 0;
                darsad = darsad * 1;
                $("#"+tblId+" [name^=pishnehad.estefadeKonandeganAzSarmayeBime]").filter("[name$=olaviat]").each( function() {

                    if($(this).attr('value')==i){
                        var tr = $(this).parent().parent();

                        var counter = 0;
                        $(tr).find('td').each(function(){
                            counter++;
                            if (counter == 2){
                                var pardarsad = $(this).text();
                                pardarsad = pardarsad *1 ;

                                darsad = darsad+pardarsad;
                            }
                        });
                    }

                });
                if (darsad != 100){
                    canAdd = false;
                }
            }
        }



        if(canAdd == true){
            sizeOfRecords++;
            jQuery("#"+tblId).jqGrid('addRowData',sizeOfRecords,newRow);
            $('.notSavedStar').text('*');
        }else{
            alertMessage("برای ثبت اولیت کمتر بایستی ابتدا میزان مجموع درصد همه ی اولویت های بالاتر برابر با 100 باشد.");
        }
    });
}
</script>