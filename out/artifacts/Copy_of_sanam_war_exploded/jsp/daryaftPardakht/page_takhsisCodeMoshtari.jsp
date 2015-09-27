<%@ page import="com.bitarts.parsian.model.asnadeSodor.Sanad" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 5/31/11
  Time: 7:45 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%--<fmt-rt:setBundle basename="com.bitarts.parsian.i18n.messages_fa"/>--%>
<html>
<head>
<script type="text/javascript">
$(function () {
    jQuery("#etebaratJari_tbl").jqGrid({
        datatype:"local", hidegrid:false, viewrecords:true, sortorder:"desc", caption:'اعتبارات جاری',
        colNames:[
            'id', 'تاریخ', 'نوع اعتبار', 'مقدار', 'شناسه پرداخت', 'شماره بیمه نامه','مانده اعتبار', 'بیمه گذار','شماره اعتبار'
        ],
        colModel:[
            {name:'id', index:'id', hidden:true},
            {name:'createdDate', index:'createdDate', width:60, sortable:true, align:'center'},
            {name:'type', index:'type', width:60, sortable:true, align:'center'},
            {name:'amount', index:'amount', width:50, sortable:true, sorttype:"int", align:'center'},
            {name:'shenaseMoshtari', index:'shenaseMoshtari', width:50, sortable:true, align:'center'},
            {name:'bimename_serial', index:'bimename_serial', width:65, sortable:true, sorttype:'int', align:'center'},
            {name:'amount_remaining', index:'amount_remaining', width:55, sortable:true, sorttype:"int", align:'center'},
            {name:'bimeGozar', index:'bimeGozar', width:50, sortable:true, align:'center'},
            {name:'shomare_credebit', index:'shomare_credebit', width:50, sortable:true, align:'center'}
        ],
        pager:'#etebaratJari_pager',
        sortname:'createdDate',
        pgbuttons:false,
        pginput:false,
        pgtext:false,
        height:80
    });
    jQuery("#etebarat_tbl").jqGrid({
//        url:"/loadEtebarCredebitList.action",
        datatype:"local", hidegrid:false, viewrecords:true, sortorder:"desc", caption:'اعتبارات',
        colNames:[
            'id', 'تاریخ', 'نوع اعتبار', 'مقدار', 'شناسه پرداخت', 'شماره بیمه نامه','مانده اعتبار', 'بیمه گذار','شماره اعتبار'
        ],
        colModel:[
            {name:'id', index:'id', hidden:true},
            {name:'createdDate', index:'createdDate', width:60, sortable:true, align:'center'},
            {name:'type', index:'type', width:60, sortable:true, align:'center'},
            {name:'amount', index:'amount', width:50, sortable:true, sorttype:"int", align:'center'},
            {name:'shenaseMoshtari', index:'shenaseMoshtari', width:50, sortable:true, align:'center'},
            {name:'bimename_serial', index:'bimename_serial', width:65, sortable:true, sorttype:'int', align:'center'},
            {name:'amount_remaining', index:'amount_remaining', width:55, sortable:true, sorttype:"int", align:'center'},
            {name:'bimeGozar', index:'bimeGozar', width:50, sortable:true, align:'center'},
            {name:'shomare_credebit', index:'shomare_credebit', width:50, sortable:true, align:'center'}
        ],
        pager:'#etebarat_pager',
        sortname:'createdDate',
        height:410

    });

    var params = {search:false, add:false, del:false, edit:false, refresh:false};
    jQuery("#etebaratJari_tbl").jqGrid('navGrid', '#etebaratJari_pager', params);
    jQuery("#etebarat_tbl").jqGrid('navGrid', '#etebarat_pager', params);

    var tbd1 = "<td class='tbd1_id ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='ui-icon ui-icon-arrowthick-1-n'></span></div></td>"
            + "<td class='selAll_s_id ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='ui-icon ui-icon-arrowthickstop-1-n'></span></div></td>";
    var tdSep = "<td class='ui-pg-button ui-state-disabled' style='width:4px;'><span class='ui-separator'></span></td>";
    var tbd2 = "<td style='direction:rtl;'><span>مجموع : </span><span class='majmoeBedehi'>0</span></td>";
    var tbd3 = "<td class='tbd1_id ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='ui-icon ui-icon-arrowthick-1-s'></span></div></td>"
            + "<td class='selAll_n_id ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='ui-icon ui-icon-arrowthickstop-1-s'></span></div></td>";
    var td_filter = "<td class='tbd_filter ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='filer_btn'></span></div></td>";
    var td_sabteBedehi = "<td class='td_sabteBedehi ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='ui-icon ui-icon-plus'></span></div></td>";
    var td_sabteEtebar = "<td class='td_sabteEtebar ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='ui-icon ui-icon-plus'></span></div></td>";
    var td_joziyateEtebar = "<td class='td_joziyateEtebar ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='ui-icon ui-icon-info'></span></div></td>";
    var td_joziyateBedehi = "<td class='td_joziyateBedehi ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='ui-icon ui-icon-info'></span></div></td>";
    var mandeSection = "<td style='direction:rtl;'><span id='mandeSection'></span></td>";

    $('#bedehihayeJari_pager_left .navtable tr').append(tbd1 + tdSep + tbd2);
    $('#etebaratJari_pager_left .navtable .tbd1_id')
            .attr({"title":'صرف نظر' }).tipsy({gravity:'s'})
            .click(
            function () {
                if (!$(this).hasClass('ui-state-disabled')) {
                    var gr = jQuery("#etebaratJari_tbl").jqGrid('getGridParam', 'selrow');
                    if (gr != null) {
                        var gr_data = jQuery("#etebaratJari_tbl").jqGrid('getRowData', gr);

                        jQuery("#etebarat_tbl").jqGrid('addRowData', gr_data['id'], gr_data);
                        jQuery("#etebaratJari_tbl").jqGrid('delRowData', gr);
                        sumCalculation();
                        //                        $('#bedehiHa_tbl tr#' + gr).slideUp(function(){
                        <%----%>
                        //                        });
                    }
                }
                return false;
            }).hover(hover_y, hover_n);
    $('#etebaratJari_pager_left .navtable .selAll_s_id')
            .attr({"title":'انصراف همه' }).tipsy({gravity:'s'})
            .click(
            function () {
                if (!$(this).hasClass('ui-state-disabled')) {
                    var ids = jQuery("#etebaratJari_tbl").jqGrid('getCol', 'id');
                    for (var i = 0; i < ids.length; i++) {
                        var gr = ids[i];
                        if (gr != null) {
                            var gr_data = jQuery("#etebaratJari_tbl").jqGrid('getRowData', gr);

                            jQuery("#etebarat_tbl").jqGrid('addRowData', gr_data['id'], gr_data);
                            jQuery("#etebaratJari_tbl").jqGrid('delRowData', gr);
                        }
                    }
                    sumCalculation();
                }
                return false;
            }).hover(hover_y, hover_n);



    $('#etebarat_pager .navtable tr').append(tbd3 + tdSep + td_filter + tdSep + td_sabteEtebar + tdSep + td_joziyateEtebar);
    $('#etebarat_pager_left .navtable .tbd1_id')
            .attr({"title":'انتخاب برای سند زنی' }).tipsy({gravity:'s'})
            .click(
            function () {
                if (!$(this).hasClass('ui-state-disabled')) {
                    var gr = jQuery("#etebarat_tbl").jqGrid('getGridParam', 'selrow');
                    if (gr != null) {
                        var gr_data = jQuery("#etebarat_tbl").jqGrid('getRowData', gr);
                        jQuery("#etebaratJari_tbl").jqGrid('addRowData', gr_data['id'], gr_data);

                        jQuery("#etebarat_tbl").jqGrid('delRowData', gr);
                        sumCalculation();
                    }
                }
                return false;
            }).hover(hover_y, hover_n);
    $('#etebarat_pager_left .navtable .selAll_n_id')
            .attr({"title":'انتخاب همه' }).tipsy({gravity:'s'})
            .click(
            function () {
                if (!$(this).hasClass('ui-state-disabled')) {
                    var etebarIds = jQuery("#etebarat_tbl").jqGrid('getCol', 'id');
                    for (var i = 0; i < etebarIds.length; i++) {
                        var gr = etebarIds[i];
                        if (gr != null) {
                            var gr_data = jQuery("#etebarat_tbl").jqGrid('getRowData', gr);

                            jQuery("#etebaratJari_tbl").jqGrid('addRowData', gr_data['id'], gr_data);
                            jQuery("#etebarat_tbl").jqGrid('delRowData', gr);
                        }
                    }
                    sumCalculation();
                }
                return false;
            }).hover(hover_y, hover_n);
    $('#etebarat_pager_left .navtable .tbd_filter')
            .attr({"title":'فیلتر' }).tipsy({gravity:'s'})
            .click(
            function () {
                dialogForm('fltr_etebarHa', 'فیلتر اعتبارات', function () {
                    var flt=$('#fltr_bedehiHa').serialize();
                    $.post('/fin/loadCredebitsByFilter',$('#fltr_etebarHa').serialize(), function (msg) {
                        //                jQuery("#etebarat_tbl").empty();
                        clearMyGrid("etebarat_tbl");
                        var fullsize=msg.substring(msg.indexOf("fLSize")+8,msg.indexOf("',") );
                        var etebar = eval(msg.replace(msg.substring(msg.indexOf("fLSize"),msg.indexOf("[") ),""));
                        for (var i = 0; i < etebar.length; i++) jQuery("#etebarat_tbl").jqGrid('addRowData', etebar[i]['id'], etebar[i]);
                        setPagingValues('#etebarat_pager', fullsize, bedPageNumber, (Math.ceil(fullsize/${etebarCredebitListPaginated.objectsPerPage})));
                        $("#etebarat_pager #prev").unbind('click').click(function(){
                            if(bedPageNumber<(Math.ceil(fullsize/${etebarCredebitListPaginated.objectsPerPage})))
                            {
                                bedPageNumber++;
                                loadOtherPageFltr('etebarat_tbl', '/fin/loadCredebitsByFilter', bedPageNumber, flt, function(){
                                    setPagingValues('#etebarat_pager', fullsize, bedPageNumber, (Math.ceil(fullsize/${etebarCredebitListPaginated.objectsPerPage}))); });
                            }
                        });
                        $("#etebarat_pager #first").unbind('click').click(function(){
                            if(bedPageNumber<(Math.ceil(fullsize/${etebarCredebitListPaginated.objectsPerPage})))
                            {
                                bedPageNumber=(Math.ceil(fullsize/${etebarCredebitListPaginated.objectsPerPage}));
                                loadOtherPageFltr('etebarat_tbl', '/fin/loadCredebitsByFilter', bedPageNumber, flt, function(){
                                    setPagingValues('#etebarat_pager', fullsize, bedPageNumber, (Math.ceil(fullsize/${etebarCredebitListPaginated.objectsPerPage}))); });
                            }
                        });
                        $("#etebarat_pager #last").unbind('click').click(function(){
                            if(bedPageNumber>1)
                            {
                                bedPageNumber=1;
                                loadOtherPageFltr('etebarat_tbl', '/fin/loadCredebitsByFilter', bedPageNumber, flt, function(){
                                    setPagingValues('#etebarat_pager', fullsize, bedPageNumber, (Math.ceil(fullsize/${etebarCredebitListPaginated.objectsPerPage}))); });
                            }
                        });
                        $("#etebarat_pager #next").unbind('click').click(function(){
                            if(bedPageNumber>1)
                            {
                                bedPageNumber--;
                                loadOtherPageFltr('etebarat_tbl', '/fin/loadCredebitsByFilter', bedPageNumber, flt, function(){
                                    setPagingValues('#etebarat_pager', fullsize, bedPageNumber, (Math.ceil(fullsize/${etebarCredebitListPaginated.objectsPerPage}))); });
                            }
                        });
                    })
                });
            }).hover(hover_y, hover_n);
    $('#etebarat_pager_left .navtable .td_sabteEtebar')
            .attr({"title":'ثبت اعتبار' }).tipsy({gravity:'s'})
            .click(
            function () {
                chg_etebarType($('#noeEtebareDarHaleSabt').val());
                clearAllEtebar();
                dialogForm('sabteEtebarDialog', 'ثبت اعتبار', function () {
                    chg_etebarType($('#noeEtebareDarHaleSabt').val());
                    $.post('/sabteCredebiteDasti', sabteEtebareDastiData, function (msg) {
                        var etebar = eval(msg);
                        for (var i = 0; i < etebar.length; i++) {
                            jQuery("#etebaratJari_tbl").jqGrid('addRowData', etebar[i]['id'], etebar[i]);
                        }
                        sumCalculation();
                    })
                });
            }).hover(hover_y, hover_n);
    $('#etebarat_pager_left .navtable .td_joziyateEtebar')
            .attr({"title":'جزئیات' }).tipsy({gravity:'s'})
            .click(
            function () {
                chg_etebarType($('#noeEtebareDarHaleSabt').val());
                var gr = jQuery("#etebarat_tbl").jqGrid('getGridParam', 'selrow');
                if (gr != null) {
                    var gr_data = jQuery("#etebarat_tbl").jqGrid('getRowData', gr);

                    $.post('/loadEtebarat', "loadedIdEtebarat=" + gr_data['id'], function (msg) {
                        loadEtebarFish(msg);
                        loadEtebarTejaratElec(msg);
                        loadEtebarCheck(msg);
                        chg_etebarType($('#noeEtebareDarHaleSabt').val());
                        viewDialogForm('sabteEtebarDialog', 'جزئیات', function () {
                            chg_etebarType($('#noeEtebareDarHaleSabt').val());
                        });
                    });
                }
                else{
                    alertMessage("رکوردی انتخاب نشده است");
                }


            }).hover(hover_y, hover_n);



    <fmt-rt:bundle basename="com.bitarts.parsian.i18n.enumTypes_fa">
    var etebar = [
        <c:forEach var="credebit" items="${etebarCredebitListPaginated.list}" varStatus="i">
        {id:${credebit.id}, createdDate:'${credebit.createdDate}', type:'${credebit.credebitTypeFarsi}', amount:'${credebit.amount}', shenaseMoshtari:'${credebit.shomareMoshtari}', bimename_serial:'${credebit.identifier}',amount_remaining:'${credebit.remainingAmount}', bimeGozar: '${credebit.rcptName}',shomare_credebit:'${credebit.shenaseCredebit}'}
        <c:if test="${i.index+1 != fn:length(etebarCredebitListPaginated.list)}">,
        </c:if>
        </c:forEach>
    ];


    </fmt-rt:bundle>

    for (var i = 0; i < etebar.length; i++) jQuery("#etebarat_tbl").jqGrid('addRowData', etebar[i]['id'], etebar[i]);
    <%----%>
    jQuery("#etebarat_tbl, #etebaratJari_tbl").trigger("reloadGrid", [
        {current:true}
    ]);

    setPagingValues('#etebarat_pager', etRowNum, etPageNumber, etTotalPage);
    $("#etebarat_pager #prev").unbind('click').click(function(){
        if(etPageNumber<etTotalPage)
        {
            etPageNumber++;
            loadOtherPage('etebarat_tbl', '/loadEtebarCredebitListPagiated', etPageNumber, function(){
                setPagingValues('#etebarat_pager', etRowNum, etPageNumber, etTotalPage)})
        }
    });
    $("#etebarat_pager #first").unbind('click').click(function(){
        if(etPageNumber<etTotalPage)
        {
            etPageNumber=etTotalPage;
            loadOtherPage('etebarat_tbl', '/loadEtebarCredebitListPagiated', etPageNumber, function(){
                setPagingValues('#etebarat_pager', etRowNum, etPageNumber, etTotalPage)})
        }
    });
    $("#etebarat_pager #next").unbind('click').click(function(){
        if(etPageNumber>1)
        {
            etPageNumber--;
            loadOtherPage('etebarat_tbl', '/loadEtebarCredebitListPagiated', etPageNumber, function(){
                setPagingValues('#etebarat_pager', etRowNum, etPageNumber, etTotalPage)})
        }
    });
    $("#etebarat_pager #last").unbind('click').click(function(){
        if(etPageNumber>1)
        {
            etPageNumber=1;
            loadOtherPage('etebarat_tbl', '/loadEtebarCredebitListPagiated', etPageNumber, function(){
                setPagingValues('#etebarat_pager', etRowNum, etPageNumber, etTotalPage)})
        }
    });
    $('#etebarat_pager input.ui-pg-input').unbind('click').bind('keypress', function(e) {
        var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
        if(key == 13) {
            etPageNumber = ($(this).val()>0) ? $(this).val():etPageNumber;
            if(etPageNumber>etTotalPage)etPageNumber=etTotalPage;
            loadOtherPage('etebarat_tbl', '/loadEtebarCredebitListPagiated', etPageNumber, function(){
                setPagingValues('#etebarat_pager', etRowNum, etPageNumber, etTotalPage)})
            return false;
        }
        return this;
    });


});

var etPageNumber = ${etebarCredebitListPaginated.pageNumber};
var etTotalPage = Math.ceil(${etebarCredebitListPaginated.fullListSize/etebarCredebitListPaginated.objectsPerPage});
var etRowNum = ${etebarCredebitListPaginated.fullListSize};

function loadOtherPageFltr(tableId, actionURL, pageNum, fltr, funcBack)
{
    $.post(actionURL+'?page='+pageNum, fltr, function (msg)
    {
        clearMyGrid(tableId);
        var etebar = eval(msg);
        for (var i = 0; i < etebar.length; i++) jQuery('#'+tableId).jqGrid('addRowData', etebar[i]['id'],etebar[i]);
        funcBack();
    });
}

function loadOtherPage(tableId, actionURL, pageNum, funcBack){
    $.post(actionURL+'?page='+pageNum, function (msg) {
        clearMyGrid(tableId);
        var etebar = eval(msg);
        for (var i = 0; i < etebar.length; i++) jQuery('#'+tableId).jqGrid('addRowData', etebar[i]['id'],etebar[i]);
        funcBack();
    })
}
function setPagingValues(pagerDiv, rowNum, pageNum, totalPage){
    $(pagerDiv+'_center .ui-pg-input').val(pageNum);
    $(pagerDiv+'_center #sp_1').text(totalPage);
    var from = ((pageNum-1)*30+1);
    var to = Math.min(((pageNum-1)*30+30), rowNum);
    $(pagerDiv+'_right .ui-paging-info').text('نمایش '+from+' - '+to+' از '+rowNum);
    if(pageNum==1 || pageNum === 0) {
        $("#next, #last" ,pagerDiv).addClass('ui-state-disabled').removeClass('ui-state-hover');
    } else {
        $("#next, #last",pagerDiv).removeClass('ui-state-disabled');
    }
    if(pageNum==totalPage || pageNum === 0) {
        $("#first, #prev",pagerDiv).addClass('ui-state-disabled').removeClass('ui-state-hover');
    } else {
        $("#first, #prev",pagerDiv).removeClass('ui-state-disabled');
    }
}


function sumCalculation() {
    var sumEtebar = jQuery.global.format(jQuery("#etebaratJari_tbl").jqGrid('getCol', 'amount_remaining', false, 'sum'), 'n0');

    $('#etebaratJari_pager_left .majmoeBedehi').text(sumEtebar);
    jQuery("#etebarat_tbl, #etebaratJari_tbl").trigger("reloadGrid", [
        {current:true}
    ]);
    setPagingValues('#etebarat_pager', etRowNum, etPageNumber, etTotalPage);

    //    if(sumBedehi != 0 && sumBedehi == sumEtebar){
    if (sumBedehi != 0 && sumEtebar != 0) {
        activation();
    }
    else {
        deactivate();
    }

}

function clearMyGrid(id) {
    var ids = jQuery("#" + id).jqGrid('getCol', 'id');
    for (var i = 0; i < ids.length; i++) {
        if (ids[i] != null) {
            jQuery("#" + id).jqGrid('delRowData', ids[i]);
        }
    }
}
function hover_y() {
    if (!$(this).hasClass('ui-state-disabled')) {
        $(this).addClass("ui-state-hover");
    }
}
function hover_n() {
    $(this).removeClass("ui-state-hover");
}
function activation() {
    $('#sbmt_btn').removeClass('ui-state-disabled');
    $('#sbmt_btn').removeAttr("disabled");
}
function deactivate() {
    $('#sbmt_btn').addClass('ui-state-disabled');
    $('#sbmt_btn').attr("disabled", true);
}
var checkId;
function printCheck() {
    window.open('/fin/printeCheck?pishnehadReport.check.id=' + checkId);
}
function printSanad() {
    window.open('/fin/printeSanad?pishnehadReport.sanad.id=${sanad.id}');
}
<%--if(${sanad!=null}) window.open('/printeSanad?pishnehadReport.sanad.id=${sanad.id}');--%>
<%--&lt;%&ndash;if(${sanad!=null})&ndash;%&gt;   $(document).ready(function(){--%>
$(document).ready(function()
{
    if(${sanad != null})
    {
        var sanadId="${sanad.id}" ;
        function changeVaziat(id)
        {
            $.post("/fin/changeVaziatSanad.action?id="+ id, function(msg) {location.reload();});
        }
        $('#khateSanadsOfSanad').dialog({
            modal:true,
            width: 900,
            resizable:false,
            closeText: "",
            title:"خط سندهای ایجادشده",
            buttons:
            {
                "دائم کردن سند": function(){$.post("/fin/changeVaziatSanad.action?id="+ sanadId, function(msg) {window.location.replace("/fin/viewKhateSanadHa");});$(this).dialog("close");},
                "انصراف": function(){$(this).dialog("close");},
                "چاپ آزمایشی سند": function(){ window.open('/fin/printeSanad?pishnehadReport.sanad.id='+sanadId);}
            }
        } );

        $.post("/fin/khateSanadsHaOfSanad.action?sanad.id="+ sanadId,
                function(msg)
                {
                    $('#khateSanadsResult div').html(msg);
                });
    }});
function generateShomareMoshtari(){
    var bankName = document.getElementById("bankMaghsadAutomobil").value;
    $.ajax({
        type: "POST",
        url: "generateCodeMoshtariCredebit?bankNameCodeMoshtari="+bankName,
        success: function (response) {
            document.getElementById("shomareMoshtariAutomobil").value = response.trim();
        }
    });
}

</script>
<title>ثبت سند دستی</title>
</head>
<body>
<%--//-----------------------------------------------%>
<div id="khateSanadsOfSanad" style="display:none;">
    <br/>
    <div id="khateSanadsResult" style=" background-color:#f5f5f5; border-style:solid;  border-width:thin; border-color:#cccccc">
        <%--<p class="heading ui-widget-header ui-helper-clearfix">--%>
        <%--<span  style=" direction:rtl;float:right;">خط سندهای ایجاد شده</span>--%>
        <%--</p>--%>
        <div></div>
    </div>
    <p style=" color:#dc143c;">
    </p>
</div>
<%--//-----------------------------------------------%>
<form action='/fin/sabteMultiSanad' id='frm_sbmt' style="display:none;"></form>
<s:actionmessage/>
<div style="height:470px;margin:0 auto">

    <div style="width:100%;float:left;height:100px">
        <tr>
            <td>
                <span class="noThing"></span>
                <select name="bankNameValue" id="bankNameValue" onchange="generateShomareMoshtari()">
                    <option value="<%=Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD%>"><%=Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD%></option>
                    <option value="<%=Constant.CREDEBIT_BANK_PARSIAN_BOLVAR_KESHAVARZ%>"><%=Constant.CREDEBIT_BANK_PARSIAN_BOLVAR_KESHAVARZ%></option>
                    <option value="<%=Constant.CREDEBIT_BANK_PARSIAN_VANAK%>"><%=Constant.CREDEBIT_BANK_PARSIAN_VANAK%></option>
                    <option value="<%=Constant.CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI%>"><%=Constant.CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI%></option>
                    <%--<option value="<%=Constant.CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI_NEW%>"><%=Constant.CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI_NEW%></option>--%>
                    <option value="<%=Constant.CREDEBIT_BANK_MELAT_VANAK%>"><%=Constant.CREDEBIT_BANK_MELAT_VANAK%></option>
                    <option value="<%=Constant.CREDEBIT_BANK_MELAT_ESKAN%>"><%=Constant.CREDEBIT_BANK_MELAT_ESKAN%></option>
                </select>
                <label>شماره حساب</label>
            </td>
            <td>
                <span class="ui-icon ui-icon-refresh" onclick="generateShomareMoshtari()" style="float:left;margin-top:5px;"></span></li>
                <input type="text" name="shomareMoshtariValue" id="shomareMoshtariValue" class="validate[required]" readonly="true" >
                &nbsp;<label>شناسه پرداخت</label>

            </td>
        </tr>
    </div>
    <div style="width:100%;float:left;height:500px">
        <div style="width:480px;margin:0 auto;">
            <table id="etebarat_tbl"></table>
            <div id="etebarat_pager"></div>
        </div>
    </div>
    <div style="width:100%;float:left;height:170px">
        <div style="width:480px;margin:0 auto;">
            <table id="etebaratJari_tbl"></table>
            <div id="etebaratJari_pager"></div>
        </div>
    </div>
</div>
<table class="inputList" cellspacing="5" width="55%">
    <tr>
        <td>

        </td>
        <td>

        </td>
    </tr>
</table>
<div style="height:35px;text-align:center;margin-top:10px;">
    <input type="button" value="تایید" id="sbmt_btn" style="display:inline;" />
</div>
<div class="vld" style="display:none;">
    <%@include file="sabteSanad/filterDialogForSabteSanad.jsp" %>
</div>
<div id="sabteEtebarDialog" style="display:none;">
    <%@include file="sabteSanad/sabteEtebarDialog.jsp" %>
</div>
</body>
</html>