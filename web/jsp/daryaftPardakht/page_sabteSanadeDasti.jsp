<%@ page import="com.bitarts.parsian.model.asnadeSodor.Sanad" %>
<%@ page import="com.bitarts.parsian.service.factory.InsuranceServiceFactory" %>
<%@ page import="com.bitarts.common.util.DateUtil" %>
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

    jQuery("#sanad_tbl").jqGrid({
        datatype:"local", hidegrid:false, viewrecords:true, sortorder:"desc", caption:'خط سند های ایجاد شده',
        colNames:[
            'id', 'سر رسید بدهی', 'مبلغ بدهی', 'نوع بدهی', 'تاریخ اعتبار', 'مبلغ اعتبار', 'نوع اعتبار', 'شناسه پرداخت', 'مبلغ سند'
        ],
        colModel:[
            {name:'id', index:'id', hidden:true} ,
            {name:'bedehi_sarresidDate', index:'bedehi_sarresidDate', width:120, sortable:true, align:'center'},
            {name:'bedehi_amount', index:'bedehi_amount', width:120, sortable:true, sorttype:"int", align:'center'},
            {name:'bedehi_type', index:'bedehi_type', width:120, sortable:true, align:'center'},
            {name:'etebar_createdDate', index:'bedehi_createdDate', width:120, sortable:true, align:'center'},
            {name:'etebar_amount', index:'etebar_amount', width:120, sortable:true, sorttype:"int", align:'center'},
            {name:'etebar_type', index:'etebar_type', width:120, sortable:true, align:'center'},
            {name:'shenaseMoshtari', index:'shenaseMoshtari', width:120, sortable:true, align:'center'},
            {name:'amount', index:'amount', width:120, sortable:true, sorttype:"int", align:'center'}
        ],
        pager:'#sanad_pager',
        //        sortname: 'createdDate',
        pgbuttons:false,
        pginput:false,
        pgtext:false,
        height:80,
        width:1000
    });
    jQuery("#bedehihayeJari_tbl").jqGrid({
        datatype:"local", hidegrid:false, viewrecords:true, sortorder:"desc", caption:'بدهی های جاری',
        colNames:[
            'id','بازاریاب', 'تاریخ سر رسید','نام نماینده', 'مبلغ', 'نوع بدهی','شناسه پرداخت', 'شماره بیمه نامه','مانده بدهی', 'بیمه گذار','شماره بدهی'
        ],
        colModel:[
            {name:'id', index:'id', hidden:true} ,
            {name:'bazaryab', index:'bazaryab', width:60, sortable:true, align:'center'} ,
            {name:'sarresidDate', index:'sarresidDate', width:60, sortable:true, align:'center'} ,
            {name:'namayandeName', index:'namayandeName', width:50, sortable:true, align:'center'},
            {name:'amount', index:'amount', width:50, sortable:true, sorttype:"int", align:'center'},
            {name:'type', index:'type', width:60, sortable:true, align:'center'},
            {name:'shenaseMoshtari', index:'shenaseMoshtari', width:50, sortable:true, align:'center'},
            {name:'bimename_serial', index:'bimename_serial', width:65, sortable:true, sorttype:'int', align:'center'},
            {name:'amount_remaining', index:'amount_remaining', width:55, sortable:true, sorttype:"int", align:'center'},
            {name:'bimeGozar', index:'bimeGozar', width:50, sortable:true, align:'center'},
            {name:'shomare_credebit', index:'shomare_credebit', width:50, sortable:true, align:'center'}
        ],
        pager:'#bedehihayeJari_pager',
        sortname:'sarresidDate',
        pgbuttons:false,
        pginput:false,
        pgtext:false,
        height:80,
        width: 540
    });
    jQuery("#bedehiHa_tbl").jqGrid({
        datatype:"local", hidegrid:false, viewrecords:true, sortorder:"desc", caption:'بدهی ها',
        colNames:[
            'id','بازاریاب', 'تاریخ سر رسید','نام نماینده', 'مبلغ', 'نوع بدهی','شناسه پرداخت', 'شماره بیمه نامه','مانده بدهی', 'بیمه گذار','شماره بدهی'
        ],
        colModel:[
            {name:'id', index:'id', hidden:true} ,
            {name: 'bazaryab', index: 'bazaryab', width: 60, sortable: true, align: 'center'} ,
            {name:'sarresidDate', index:'sarresidDate', width:60, sortable:true, align:'center'} ,
            {name:'namayandeName', index:'namayandeName', width:50, sortable:true, align:'center'},
            {name:'amount', index:'amount', width:50, sortable:true, sorttype:"int", align:'center'},
            {name:'type', index:'type', width:60, sortable:true, align:'center'},
            {name:'shenaseMoshtari', index:'shenaseMoshtari', width:50, sortable:true, align:'center'},
            {name:'bimename_serial', index:'bimename_serial', width:65, sortable:true, sorttype:'int', align:'center'},
            {name:'amount_remaining', index:'amount_remaining', width:55, sortable:true, sorttype:"int", align:'center'},
            {name:'bimeGozar', index:'bimeGozar', width:50, sortable:true, align:'center'},
            {name:'shomare_credebit', index:'shomare_credebit', width:50, sortable:true, align:'center'}
        ],
        pager:'#bedehiHa_pager',
        sortname:'sarresidDate',
        height:410,
        width: 540
    });
    jQuery("#etebaratJari_tbl").jqGrid({
        datatype:"local", hidegrid:false, viewrecords:true, sortorder:"desc", caption:'اعتبارات جاری',
        colNames:[
            'id','وضعیت وصولی', 'تاریخ','نام نماینده' ,'نوع اعتبار', 'مبلغ', 'شناسه پرداخت', 'شماره بیمه نامه','مانده اعتبار', 'بیمه گذار','شماره اعتبار'
        ],
        colModel:[
            {name:'id', index:'id', hidden:true},
            {name:'vosoulStateFarsi', index:'vosoulStateFarsi', width:60, sortable:true, align:'center'},
            {name:'createdDate', index:'createdDate', width:60, sortable:true, align:'center'},
            {name:'namayandeName', index:'namayandeName', width:50, sortable:true, align:'center'},
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
        height:80,
        width: 540

    });
    jQuery("#etebarat_tbl").jqGrid({
//        url:"/loadEtebarCredebitList.action",
        datatype:"local", hidegrid:false, viewrecords:true, sortorder:"desc", caption:'اعتبارات',
        colNames:[
            'id','شماره چک','وضعیت وصولی', 'تاریخ','نام نماینده' ,'نوع اعتبار', 'مبلغ', 'شناسه پرداخت', 'شماره بیمه نامه','مانده اعتبار', 'بیمه گذار','شماره اعتبار'
        ],
        colModel:[
            {name:'id', index:'id', hidden:true},
            {name: 'serial', index:'daryafteCheck',  width:60, sortable:true, align:'center'},
            {name:'vosoulStateFarsi', index:'vosoulStateFarsi', width:60, sortable:true, align:'center'},
            {name:'createdDate', index:'createdDate', width:60, sortable:true, align:'center'},
            {name:'namayandeName', index:'namayandeName', width:50, sortable:true, align:'center'},
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
        height:410,
        width: 540
    });

    var params = {search:false, add:false, del:false, edit:false, refresh:false};
    jQuery("#etebaratJari_tbl").jqGrid('navGrid', '#etebaratJari_pager', params);
    jQuery("#bedehihayeJari_tbl").jqGrid('navGrid', '#bedehihayeJari_pager', params);
    jQuery("#sanad_tbl").jqGrid('navGrid', '#sanad_pager', params);
    jQuery("#etebarat_tbl").jqGrid('navGrid', '#etebarat_pager', params);
    jQuery("#bedehiHa_tbl").jqGrid('navGrid', '#bedehiHa_pager', params);

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
    var td_print = "<td class='td_print ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='ui-icon ui-icon-print'></span></div></td>";
    var td_joziyateBedehi = "<td class='td_joziyateBedehi ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='ui-icon ui-icon-info'></span></div></td>";
    var td_deleteEtebar = "<td class='td_deleteEtebar ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='ui-icon ui-icon-trash'></span></div></td>";
    var td_deleteBedehi = "<td class='td_deleteBedehi ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='ui-icon ui-icon-trash'></span></div></td>";
    var mandeSection = "<td style='direction:rtl;'><span id='mandeSection'></span></td>";


    $('#bedehihayeJari_pager_left .navtable tr, #etebaratJari_pager_left .navtable tr').append(tbd1 + tdSep);
    $('#etebaratJari_pager_left .navtable tr').append(td_print + tdSep);
    $('#bedehihayeJari_pager_left .navtable tr, #etebaratJari_pager_left .navtable tr').append(tbd2);
    $('#sanad_pager_left .navtable tr').append(mandeSection);
    $('#bedehihayeJari_pager_left .navtable .tbd1_id')
            .attr({"title":'صرف نظر' }).tipsy({gravity:'s'})
            .click(
            function () {
                if (!$(this).hasClass('ui-state-disabled')) {
                    var gr = jQuery("#bedehihayeJari_tbl").jqGrid('getGridParam', 'selrow');
                    if (gr != null) {
                        var gr_data = jQuery("#bedehihayeJari_tbl").jqGrid('getRowData', gr);

                        jQuery("#bedehiHa_tbl").jqGrid('addRowData', gr_data['id'], gr_data);
                        jQuery("#bedehihayeJari_tbl").jqGrid('delRowData', gr);
                        sumCalculation();
                        //                        $('#bedehiHa_tbl tr#' + gr).slideUp(function(){
                    <%----%>
                        //                        });
                    }
                }
                return false;
            }).hover(hover_y, hover_n);
    $('#bedehihayeJari_pager_left .navtable .selAll_s_id')
            .attr({"title":'انصراف همه' }).tipsy({gravity:'s'})
            .click(
            function () {
                if (!$(this).hasClass('ui-state-disabled')) {
                    var ids = jQuery("#bedehihayeJari_tbl").jqGrid('getCol', 'id');
                    for (var i = 0; i < ids.length; i++) {
                        var gr = ids[i];
                        if (gr != null) {
                            var gr_data = jQuery("#bedehihayeJari_tbl").jqGrid('getRowData', gr);

                           jQuery("#bedehiHa_tbl").jqGrid('addRowData', gr_data['id'], gr_data);
                            jQuery("#bedehihayeJari_tbl").jqGrid('delRowData', gr);
                        }
                    }
                    sumCalculation();
                }
                return false;
            }).hover(hover_y, hover_n);
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
    <%----%>
    $('#bedehiHa_pager .navtable tr').append(tbd3 + tdSep + td_filter + tdSep + td_sabteBedehi + tdSep + td_joziyateBedehi + tdSep + td_deleteBedehi + tdSep);
    $('#bedehiHa_pager_left .navtable .tbd1_id')
            .attr({"title":'انتخاب برای سند زنی' }).tipsy({gravity:'s'})
            .click(
            function () {
                if (!$(this).hasClass('ui-state-disabled')) {
                    var gr = jQuery("#bedehiHa_tbl").jqGrid('getGridParam', 'selrow');
                    if (gr != null) {
                        var gr_data = jQuery("#bedehiHa_tbl").jqGrid('getRowData', gr);
                        if (check_bedehiJari_add(gr_data['id'])){
                            var countrecord = jQuery("#bedehihayeJari_tbl").jqGrid('getGridParam', 'records');
                            if(countrecord<30)  {
                            jQuery("#bedehihayeJari_tbl").jqGrid('addRowData', gr_data['id'], gr_data);
                            jQuery("#bedehiHa_tbl").jqGrid('delRowData', gr);
                            sumCalculation();
                            }  else{
                                alertMessage('سیستم حداکثر قادر به ثبت 30 رکورد بدهی می باشد');
                            }
                        } else{
                            alertMessage("این بدهی در لیست بدهی های جاری وجود دارد");
                        }
                    }
                }
                return false;
            }).hover(hover_y, hover_n);
    $('#bedehiHa_pager_left .navtable .selAll_n_id')
            .attr({"title":'انتخاب همه' }).tipsy({gravity:'s'})
            .click(
            function () {
                if (!$(this).hasClass('ui-state-disabled')) {
                    var flt=$('#fltr_bedehiHa').serialize() ;
                    loadAllFltr('bedehihayeJari_tbl', '/fin/loadCredebitsByFilterSendToList', flt, 'bedehiHa_tbl');
                }
                return false;
            }).hover(hover_y, hover_n);
    $('#bedehiHa_pager_left .navtable .tbd_filter')
            .attr({"title":'فیلتر' }).tipsy({gravity:'s'})
            .click(
            function () {
                dialogForm('fltr_bedehiHa', 'فیلتر بدهی ها', function () {
                    var flt=$('#fltr_bedehiHa').serialize() ;
                    $.post('/fin/loadCredebitsByFilter', $('#fltr_bedehiHa').serialize(), function (msg) {
                        //                jQuery("#bedehiHa_tbl").empty();

                        clearMyGrid("bedehiHa_tbl");
                        var fullsize=msg.substring(msg.indexOf("fLSize")+8,msg.indexOf("',") );
                        var bedehi = eval(msg.replace(msg.substring(msg.indexOf("fLSize"),msg.indexOf("[") ),""));
                        for (var i = 0; i < bedehi.length; i++) jQuery("#bedehiHa_tbl").jqGrid('addRowData', bedehi[i]['id'], bedehi[i]);
//                             window.alert(msg.substring(msg.indexOf("fLSize")+7,msg.indexOf(",") ));
                        setPagingValues('#bedehiHa_pager', fullsize, bedPageNumber, (Math.ceil(fullsize/${bedehiCredebitListPaginated.objectsPerPage})));
                        $("#bedehiHa_pager .navtable .selAll_n_id").unbind('click').click(function(){
                            if (!$(this).hasClass('ui-state-disabled')) {
                                loadAllFltr('bedehihayeJari_tbl', '/fin/loadCredebitsByFilterSendToList', flt, 'bedehiHa_tbl');
                            }
                            return false;
                        }).hover(hover_y, hover_n);


                        $("#bedehiHa_pager #prev").unbind('click').click(function(){
                            if(bedPageNumber<(Math.ceil(fullsize/${bedehiCredebitListPaginated.objectsPerPage})))
                            {
                                bedPageNumber++;
                                loadOtherPageFltr('bedehiHa_tbl', '/fin/loadCredebitsByFilter', bedPageNumber, flt, function(){
                                    setPagingValues('#bedehiHa_pager', fullsize, bedPageNumber, (Math.ceil(fullsize/${bedehiCredebitListPaginated.objectsPerPage}))); });
                            }
                        });
                        $("#bedehiHa_pager #first").unbind('click').click(function(){
                            if(bedPageNumber<(Math.ceil(fullsize/${bedehiCredebitListPaginated.objectsPerPage})))
                            {
                                bedPageNumber=(Math.ceil(fullsize/${bedehiCredebitListPaginated.objectsPerPage}));
                                loadOtherPageFltr('bedehiHa_tbl', '/fin/loadCredebitsByFilter', bedPageNumber, flt, function(){
                                    setPagingValues('#bedehiHa_pager', fullsize, bedPageNumber, (Math.ceil(fullsize/${bedehiCredebitListPaginated.objectsPerPage}))); });
                            }
                        });
                        $("#bedehiHa_pager #last").unbind('click').click(function(){
                            if(bedPageNumber>1)
                            {
                                bedPageNumber=1;
                                loadOtherPageFltr('bedehiHa_tbl', '/fin/loadCredebitsByFilter', bedPageNumber, flt, function(){
                                    setPagingValues('#bedehiHa_pager', fullsize, bedPageNumber, (Math.ceil(fullsize/${bedehiCredebitListPaginated.objectsPerPage}))); });
                            }
                        });
                         $("#bedehiHa_pager #next").unbind('click').click(function(){
                            if(bedPageNumber>1)
                            {
                                bedPageNumber--;
                                loadOtherPageFltr('bedehiHa_tbl', '/fin/loadCredebitsByFilter', bedPageNumber, flt, function(){
                                    setPagingValues('#bedehiHa_pager', fullsize, bedPageNumber, (Math.ceil(fullsize/${bedehiCredebitListPaginated.objectsPerPage}))); });
                            }
                         });
                    })
                });
            }).hover(hover_y, hover_n);
    $('#bedehiHa_pager_left .navtable .td_sabteBedehi')
            .attr({"title":'ثبت بدهی' }).tipsy({gravity:'s'})
            .click(
            function () {
                assignOptionNoeBedehiAdd();
                chg_bedehiType($('#noeBedehieDarHaleSabt').val());
                clearAllBedehi();
                if(!$('#noeBedehieDarHaleSabt').val() == '') {
                dialogForm('sabteBedehiDialog', 'ثبت بدهی', function () {
                    chg_bedehiType($('#noeBedehieDarHaleSabt').val());
                    $.post('/sabteCredebiteDasti', sabteBedehiDastiData, function (msg) {
                        var bedehi = eval(msg);
                        for (var i = 0; i < bedehi.length; i++) {
                            jQuery("#bedehihayeJari_tbl").jqGrid('addRowData', bedehi[i]['id'], bedehi[i]);
                            if (bedehi[i]['type'] == 'پرداخت چک') {
                                checkId = bedehi[i]['idOfCheck'];
                                $('#printeCheck_btn').show();
                            }
                          }
                        sumCalculation();
                    })
                });
                } else {
                    alertMessage("بدهی قابل تعریف وجود ندارد.");
                }
            }).hover(hover_y, hover_n);
    $('#etebarat_pager .navtable tr').append(tbd3 + tdSep + td_filter + tdSep + td_sabteEtebar + tdSep + td_joziyateEtebar + tdSep + td_deleteEtebar + tdSep);
    $('#etebarat_pager_left .navtable .tbd1_id')
            .attr({"title":'انتخاب برای سند زنی' }).tipsy({gravity:'s'})
            .click(
            function () {
                if (!$(this).hasClass('ui-state-disabled')) {
                    var gr = jQuery("#etebarat_tbl").jqGrid('getGridParam', 'selrow');
                    if (gr != null) {
                        var gr_data = jQuery("#etebarat_tbl").jqGrid('getRowData', gr);
                        if (check_etebaratJari_add(gr_data['id'])){
                            var countrecord = jQuery("#etebaratJari_tbl").jqGrid('getGridParam', 'records');
                            if(countrecord<30)  {
                            jQuery("#etebaratJari_tbl").jqGrid('addRowData', gr_data['id'], gr_data);
                            jQuery("#etebarat_tbl").jqGrid('delRowData', gr);
                            sumCalculation();
                            selectNoeSanad(gr_data);
                            }else  {
                                alertMessage("سیستم حداکثر قادر به ثبت 30 رکورد  اعتبار  می باشد");
                            }
                        } else{
                            alertMessage("این اعتبار در لیست اعتبارات جاری وجود دارد");
                        }
                    }
                }
                return false;
            }).hover(hover_y, hover_n);
    $('#etebarat_pager_left .navtable .selAll_n_id')
            .attr({"title":'انتخاب همه' }).tipsy({gravity:'s'})
            .click(
            function () {
                if (!$(this).hasClass('ui-state-disabled')) {
                    if (!$(this).hasClass('ui-state-disabled')) {
                        var flt=$('#fltr_etebarHa').serialize();
                        loadAllFltr('etebaratJari_tbl', '/fin/loadCredebitsByFilterSendToList', flt, 'etebarat_tbl');
                    }
                    return false;
                }
            }).hover(hover_y, hover_n);
    $('#etebarat_pager_left .navtable .tbd_filter')
            .attr({"title":'فیلتر' }).tipsy({gravity:'s'})
            .click(
            function () {
                dialogForm('fltr_etebarHa', 'فیلتر اعتبارات', function () {
                    var flt=$('#fltr_etebarHa').serialize();
                    $.post('/fin/loadCredebitsByFilter',$('#fltr_etebarHa').serialize(), function (msg) {
                        //                jQuery("#etebarat_tbl").empty();
                        clearMyGrid("etebarat_tbl");
                        var fullsize=msg.substring(msg.indexOf("fLSize")+8,msg.indexOf("',") );
                        var etebar = eval(msg.replace(msg.substring(msg.indexOf("fLSize"),msg.indexOf("[") ),""));
                        for (var i = 0; i < etebar.length; i++) jQuery("#etebarat_tbl").jqGrid('addRowData', etebar[i]['id'], etebar[i]);
                        setPagingValues('#etebarat_pager', fullsize, bedPageNumber, (Math.ceil(fullsize/${etebarCredebitListPaginated.objectsPerPage})));
                        $("#etebarat_pager .navtable .selAll_n_id").unbind('click').click(function(){
                            if (!$(this).hasClass('ui-state-disabled')) {
                                loadAllFltr('etebaratJari_tbl', '/fin/loadCredebitsByFilterSendToList', flt, 'etebarat_tbl');
                            }
                            return false;
                        }).hover(hover_y, hover_n);
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
                assignOptionNoeEtebarAdd();
//            $('#nama_name_etebar_add').val('');

//                $('#nama_id_etebar_add').val('');
                chg_etebarType($('#noeEtebareDarHaleSabt').val());
                clearAllEtebar();
                etebarAmountCalculation();
                    dialogForm('sabteEtebarDialog', 'ثبت اعتبار', function () {
                        chg_etebarType($('#noeEtebareDarHaleSabt').val());
                        $.post('/sabteCredebiteDasti', sabteEtebareDastiData, function (msg) {
                           if(msg.split(',')[0].split(':')[1]=='\'\'') {
                                alertMessage('امکان ثبت اعتبار وجود ندارد')  ;
                           }else{ var etebar = eval(msg);
                            for (var i = 0; i < etebar.length; i++) {
                                jQuery("#etebaratJari_tbl").jqGrid('addRowData', etebar[i]['id'], etebar[i]);
                            }
                            sumCalculation();
                           }
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
//                  if (gr_data['type'] != "حق بیمه برگشتی (اتومبیل)"){
                        $.post('/loadEtebarat', "loadedIdEtebarat=" + gr_data['id'], function (msg) {
                            addOptionNoeEtebarEdit();
                            loadEtebarFish(msg);
                            loadEtebarTejaratElec(msg);
                            loadEtebarCheck(msg);
                            loadEtebarCash(msg);
                            loadEtebarFishByCodemoshtari(msg);
                            loadEtebarHBB(msg);
                            loadEtebarHesabFiMabeyn(msg);
                            chg_etebarTypew();
                            viewDialogForm('sabteEtebarDialog', 'جزئیات', function () {
                                chg_etebarTypew();
                            });

                        });
//                    }
//                    else {
//                        alertMessageWithTitle("جزئیات برای این اعتبار قابل نمایش نیست","هشدار");
//                    }
                }
                else{
                    alertMessage("رکوردی انتخاب نشده است");
                }


            }).hover(hover_y, hover_n);
    $('#etebarat_pager_left .navtable .td_print')
            .attr({"title":'پرینت' }).tipsy({gravity:'s'})
            .click(
            function () {
                var gr = jQuery("#etebarat_tbl").jqGrid('getGridParam', 'selrow');
                if (gr != null) {
                    var gr_data = jQuery("#etebarat_tbl").jqGrid('getRowData', gr);
                    if (gr_data['type'] == "پرداخت شناسه دار"){
                        $.post('getBankNameCredebitAjax', "credebitId=" + gr_data['id'], function (msg) {
                            if (msg == '2177777733' || msg == '4757575763')
                                window.open('/fin/printeFishBankMellat?credebitReport.id='+gr_data['id']);
                            else if (msg == '17038494' || msg == '17123130')
                                window.open('/fin/printeFishBankTejarat?credebitReport.id='+gr_data['id']);
                            else if (msg == '0200234164006' || msg == '81011989')
                                window.open('/fin/printeFishBankParsian?credebitReport.id='+gr_data['id']);
                            else
                                alertMessage("اعتبار مورد نظر قابل پرینت نمیباشد");
                        });
                    }else{
                        alertMessage("اعتبار مورد نظر قابل پرینت نمیباشد");
                    }
                }
                else{
                    alertMessage("رکوردی انتخاب نشده است");
                }


            }).hover(hover_y, hover_n);
    $('#etebarat_pager_left .navtable .td_deleteEtebar')
            .attr({"title":'حذف' }).tipsy({gravity:'s'})
            .click(
                function () {
                        var gr = jQuery("#etebarat_tbl").jqGrid('getGridParam', 'selrow');
                        if (gr != null) {
                            confirmMessage("آیا مطمئن هستید؟","هشدار", function (){
                                var gr_data = jQuery("#etebarat_tbl").jqGrid('getRowData', gr);
                                if (gr_data['amount_remaining'] == gr_data['amount']){
                                    $.ajax({
                                        type: "POST",
                                        async : false,
                                        data: "credebitId="+gr_data['id'],
                                        url: "deleteEtebarOperation",
                                        success: function (msg) {
                                            if (msg == 'true'){
                                                jQuery("#etebarat_tbl").jqGrid('delRowData', gr);
                                                alertMessage("عملیات حذف اعتبار با موفقیت انجام شد.");
                                            }
                                            else{
                                                alertMessage("این اعتبار قابلیت حذف ندارد.");
                                            }

                                        }
                                    });
                                }else{
                                    alertMessage("این اعتبار قابلیت حذف ندارد.");
                                }
                            })
                        }
                        else{
                            alertMessage("رکوردی انتخاب نشده است.");
                        }

                }).hover(hover_y, hover_n);

    $('#bedehiHa_pager_left .navtable .td_deleteBedehi')
            .attr({"title":'حذف' }).tipsy({gravity:'s'})
            .click(
            function () {
                var gr = jQuery("#bedehiHa_tbl").jqGrid('getGridParam', 'selrow');
                if (gr != null) {
                    confirmMessage("آیا مطمئن هستید؟","هشدار", function (){
                        var gr_data = jQuery("#bedehiHa_tbl").jqGrid('getRowData', gr);
                        if (gr_data['amount_remaining'] == gr_data['amount']){
                            $.ajax({
                                type: "POST",
                                async : false,
                                data: "credebitId="+gr_data['id'],
                                url: "deleteBedehiOperation",
                                success: function (msg) {
                                    if (msg == 'true'){
                                        jQuery("#bedehiHa_tbl").jqGrid('delRowData', gr);
                                        alertMessage("عملیات حذف با موفقیت انجام شد.");
                                    }
                                    else{
                                        alertMessage("این بدهی قابلیت حذف ندارد");
                                    }

                                }
                            });
                        }else{
                            alertMessage("این بدهی قابلیت حذف ندارد");
                        }
                    })
                }
                else{
                    alertMessage("رکوردی انتخاب نشده است.");
                }


            }).hover(hover_y, hover_n);

    $('#etebaratJari_pager_left .navtable .td_print')
            .attr({"title":'پرینت' }).tipsy({gravity:'s'})
            .click(
            function () {
                var gr = jQuery("#etebaratJari_tbl").jqGrid('getGridParam', 'selrow');
                if (gr != null) {
                    var gr_data = jQuery("#etebaratJari_tbl").jqGrid('getRowData', gr);
                    if (gr_data['type'] == "پرداخت شناسه دار"){
                        $.post('getBankNameCredebitAjax', "credebitId=" + gr_data['id'], function (msg) {
                            if (msg == '2177777733' || msg == '4757575763')
                                window.open('/fin/printeFishBankMellat?credebitReport.id='+gr_data['id']);
                            else if (msg == '17038494' || msg == '17123130')
                                window.open('/fin/printeFishBankTejarat?credebitReport.id='+gr_data['id']);
                            else if (msg == '0200234164006' || msg == '81011989')
                                window.open('/fin/printeFishBankParsian?credebitReport.id='+gr_data['id']);
                            else
                                alertMessage("اعتبار مورد نظر قابل پرینت نمیباشد");
                        });
                    }else{
                        alertMessage("اعتبار مورد نظر قابل پرینت نمیباشد");
                    }
                }
                else{
                    alertMessage("رکوردی انتخاب نشده است");
                }


            }).hover(hover_y, hover_n);
    $('#bedehiHa_pager_left .navtable .td_joziyateBedehi')
            .attr({"title":'جزئیات' }).tipsy({gravity:'s'})
            .click(
            function () {
                chg_bedehiType($('#noeBedehieDarHaleSabt').val());
                var gr = jQuery("#bedehiHa_tbl").jqGrid('getGridParam', 'selrow');
                if (gr != null) {
                    var gr_data = jQuery("#bedehiHa_tbl").jqGrid('getRowData', gr);
                    if (gr_data['type'] != "حق بیمه (اتومبیل)"){
                        $.post('/loadBedehiha', "loadedIdEtebarat=" + gr_data['id'], function (msg) {
                            addOptionNoeBedehiEdit();
                            loadBedehiCheck(msg);
                            loadBedehiTankhah(msg);
                            loadBedehiHesabFiMabeyn(msg);
                            chg_bedehiType($('#noeBedehieDarHaleSabt').val());
                            viewDialogForm('sabteBedehiDialog', 'جزئیات', function () {
                                chg_etebarType($('#noeBedehieDarHaleSabt').val());
                            });

                        });
                    }else{
                    alertMessage("جزئیات برای این بدهی قابل نمایش نیست");
                    }
                }
                else{
                    alertMessage("رکوردی انتخاب نشده است");
                }


            }).hover(hover_y, hover_n);

    <%--todo From this Comment to next comment is loading of tables--%>
    <%-- credebitHa.jsp--%>
    <fmt-rt:bundle basename="com.bitarts.parsian.i18n.enumTypes_fa">
    var etebar = [
        <c:forEach var="credebit" items="${etebarCredebitListPaginated.list}" varStatus="i">
        {id:${credebit.id},vosoulStateFarsi:'${credebit.vosoulStateFarsi}', createdDate:'${credebit.createdDate}', namayandeName:'${credebit.namayandeName}', type:'${credebit.credebitTypeFarsi}', amount:'${credebit.amount}', shenaseMoshtari:'${credebit.shomareMoshtari}', bimename_serial:'${credebit.identifier}',amount_remaining:'${credebit.remainingAmount}', bimeGozar: '${credebit.rcptName}',shomare_credebit:'${credebit.shenaseCredebit}'}
        <c:if test="${i.index+1 != fn:length(etebarCredebitListPaginated.list)}">,
        </c:if>
        </c:forEach>
    ];

    var bedehi = [
        <c:forEach var="bedehi" items="${bedehiCredebitListPaginated.list}" varStatus="i">                                                                                   // bedehi.ghest.ghestBandi.bimename.shomare
        {id:${bedehi.id},bazaryab:'${bedehi.bazarYabSanam.name}', sarresidDate:'${bedehi.sarresidDate}',namayandeName:'${bedehi.namayandeName}', amount:'${bedehi.amount}', type:'${bedehi.credebitTypeFarsi}', shenaseMoshtari:'${bedehi.shomareMoshtari}', bimename_serial:'${bedehi.identifier}',amount_remaining:'${bedehi.remainingAmount}', bimeGozar: '${bedehi.rcptName}',shomare_credebit:'${bedehi.shenaseCredebit}'}
        <c:if test="${i.index+1 != fn:length(bedehiCredebitListPaginated.list)}">,
        </c:if>

        </c:forEach>
    ];

    </fmt-rt:bundle>


    for (var i = 0; i < bedehi.length; i++) jQuery("#bedehiHa_tbl").jqGrid('addRowData', bedehi[i]['id'], bedehi[i]);
    for (var i = 0; i < etebar.length; i++) jQuery("#etebarat_tbl").jqGrid('addRowData', etebar[i]['id'], etebar[i]);
    <%----%>
    jQuery("#etebarat_tbl, #etebaratJari_tbl, #bedehiHa_tbl, #bedehihayeJari_tbl").trigger("reloadGrid", [
        {current:true}
    ]);

    setPagingValues('#etebarat_pager', etRowNum, etPageNumber, etTotalPage);
    setPagingValues('#bedehiHa_pager', bedRowNum, bedPageNumber, bedTotalPage);
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
 <%----%>
    $("#bedehiHa_pager #prev").unbind('click').click(function(){
        if(bedPageNumber<bedTotalPage)
        {
            bedPageNumber++;
            loadOtherPage('bedehiHa_tbl', '/loadBedehihaPaginated', bedPageNumber, function(){
                setPagingValues('#bedehiHa_pager', bedRowNum, bedPageNumber, bedTotalPage)})
        }
    });
    $("#bedehiHa_pager #first").unbind('click').click(function(){
        if(bedPageNumber<bedTotalPage)
        {
            bedPageNumber=bedTotalPage;
            loadOtherPage('bedehiHa_tbl', '/loadBedehihaPaginated', bedPageNumber, function(){
                setPagingValues('#bedehiHa_pager', bedRowNum, bedPageNumber, bedTotalPage)})
        }
    });
    $("#bedehiHa_pager #next").unbind('click').click(function(){
        if(bedPageNumber>1)
        {
            bedPageNumber--;
            loadOtherPage('bedehiHa_tbl', '/loadBedehihaPaginated', bedPageNumber, function(){
                setPagingValues('#bedehiHa_pager', bedRowNum, bedPageNumber, bedTotalPage)})
        }
    });
    $("#bedehiHa_pager #last").unbind('click').click(function(){
        if(bedPageNumber>1)
        {
            bedPageNumber=1;
            loadOtherPage('bedehiHa_tbl', '/loadBedehihaPaginated', bedPageNumber, function(){
                setPagingValues('#bedehiHa_pager', bedRowNum, bedPageNumber, bedTotalPage)})
        }
    });
    $('#bedehiHa_pager input.ui-pg-input').unbind('click').bind('keypress', function(e) {
        var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
        if(key == 13) {
            bedPageNumber = ($(this).val()>0) ? $(this).val():bedPageNumber;
            if(bedPageNumber>bedTotalPage)bedPageNumber=bedTotalPage;
            loadOtherPage('bedehiHa_tbl', '/loadBedehihaPaginated', bedPageNumber, function(){
                setPagingValues('#bedehiHa_pager', bedRowNum, bedPageNumber, bedTotalPage)})
            return false;
        }
        return this;
    });
});

var etPageNumber = ${etebarCredebitListPaginated.pageNumber};
var etTotalPage = Math.ceil(${etebarCredebitListPaginated.fullListSize/etebarCredebitListPaginated.objectsPerPage});
var etRowNum = ${etebarCredebitListPaginated.fullListSize};

var bedPageNumber = ${bedehiCredebitListPaginated.pageNumber};
var bedTotalPage = Math.ceil(${bedehiCredebitListPaginated.fullListSize/bedehiCredebitListPaginated.objectsPerPage});
var bedRowNum = ${bedehiCredebitListPaginated.fullListSize};

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

function loadAllFltr(tableId, actionURL, fltr , clearTable)
{
    $.post(actionURL, fltr, function (msg)
    {
        clearMyGrid(tableId);
        clearMyGrid(clearTable);
        var etebar = eval(msg);
        for (var i = 0; i < etebar.length; i++)
           if(i<30)  {
             jQuery('#'+tableId).jqGrid('addRowData', etebar[i]['id'],etebar[i]);
                sumCalculation();
             }  else{
           alertMessage('سیستم حداکثر قادر به ثبت 30 رکورد بدهی می باشد');
             }

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

function check_etebaratJari_add(etebarId){
    var etebarIds = jQuery("#etebaratJari_tbl").jqGrid('getCol', 'id');
    for (var i = 0; i < etebarIds.length; i++) {
        if (etebarIds[i] == etebarId)
            return false;
    }
    return true;
}

function check_bedehiJari_add(bedehiId){
    var bedehiIds = jQuery("#bedehihayeJari_tbl").jqGrid('getCol', 'id');
    for (var i = 0; i < bedehiIds.length; i++) {
        if (bedehiIds[i] == bedehiId)
            return false;
    }
    return true;
}

function check_sanad_rcptName(){
    var bedehiBimeGozars = jQuery("#bedehihayeJari_tbl").jqGrid('getCol', 'bimeGozar');
    var etebarBimeGozars = jQuery("#etebaratJari_tbl").jqGrid('getCol', 'bimeGozar');
    var etebarTypes = jQuery("#etebaratJari_tbl").jqGrid('getCol', 'type');

    for (var i = 0; i < etebarTypes.length; i++) {
        if (etebarTypes[i] == 'حق بیمه برگشتی (اتومبیل)'){
            for (var j = 0; j < bedehiBimeGozars.length; j++) {
                if (etebarBimeGozars[i] != bedehiBimeGozars[j])
                    return 0;
            }
        }
    }

    return 1;
}

function check_sanad_tarikh_checkVaBedehi(){
    var bedehiTarikhSarresid = jQuery("#bedehihayeJari_tbl").jqGrid('getCol', 'sarresidDate');
    var etebarIds = jQuery("#etebaratJari_tbl").jqGrid('getCol', 'id');
    var etebarTypes = jQuery("#etebaratJari_tbl").jqGrid('getCol', 'type');

    for (var i = 0; i < etebarTypes.length; i++) {
        if (etebarTypes[i] == 'دریافت چک'){
            $.ajax({
                type: "POST",
                async: false,
                url: "/fin/getTarikhSarresidCredebitAjax?credebitId="+ etebarIds[i],
                success: function (msg) {
                    for (var j = 0; j < bedehiTarikhSarresid.length; j++) {
                        if (bedehiTarikhSarresid[j] >= msg)
                            return 0;
                    }
                }
            });
        }
    }

    return 1;
}

function check_sanad_sarresideBedehi(){
    var bedehihayeJariTarikh = jQuery("#bedehihayeJari_tbl").jqGrid('getCol', 'sarresidDate');
    for (var i = 0; i < bedehihayeJariTarikh.length; i++) {
        if (bedehihayeJariTarikh[i] <= '<%= DateUtil.getCurrentDate()%>'){
            return 0;
        }
    }
    return 1;
}

function check_bedehi_noesanad(){
    var bedehihayeSanad = jQuery("#bedehihayeJari_tbl").jqGrid('getCol', 'type');
    if ($('#noeSanad').val() != "<%=Sanad.NoeSanad.PARDAKHT%>")
        return 0;
    for (var i = 0; i < bedehihayeSanad.length; i++) {
        if (bedehihayeSanad[i] == 'پرداخت تنخواه'){
            return 1;
        }
    }
    return 0;
}

function submitASanad() {
//    if (check_sanad_rcptName() != 1){
//        alertMessage("بیمه گذار بدهی برابر بیمه گذار اعتبار حق بیمه برگشتی (اتومبیل) نمی باشد");
//        return;
//    }

//    if (check_sanad_sarresideBedehi() != 1){
//        alertMessage("تاریخ سر رسید بدهی باید از فردا به بعد باشد");
//        return;
//    }

    if (check_sanad_tarikh_checkVaBedehi() != 1){
        alertMessage("سر رسید چک در بازه زمانی مجاز نمی باشد");
        return;
    }

    if (check_bedehi_noesanad() == 1){
        var r=confirm("شما در لیست بدهی خود، پرداخت تنخواه دارید و نوع سند را قبض پرداخت انتخاب کرده اید، آیا مطمئن هستید ؟");
        if (r==true){
        }
        else{
            return;
        }
    }
    var bedehiIds = jQuery("#bedehihayeJari_tbl").jqGrid('getCol', 'id');
    var etebarIds = jQuery("#etebaratJari_tbl").jqGrid('getCol', 'id');
    for (var i = 0; i < bedehiIds.length; i++) {
        $('#frm_sbmt').append("<input type='input' name='bedehiCredebitList[" + i + "].id' value='" + bedehiIds[i] + "'/>");
    }
    for (var i = 0; i < etebarIds.length; i++) {
        $('#frm_sbmt').append("<input type='input' name='etebarCredebitList[" + i + "].id' value='" + etebarIds[i] + "'/>");
    }
    $('#frm_sbmt').append("<input type='input' name='noeSanad' value='" + $('#noeSanad').val() + "'/>");
    $('#frm_sbmt').append("<input type='input' name='vaziat' value='" + $('#vaziat').val() + "'/>");
    $('#frm_sbmt').submit();
}
function updateSanadTable() {
    clearMyGrid('sanad_tbl');
    var bedehiIds = jQuery("#bedehihayeJari_tbl").jqGrid('getCol', 'id');
    var etebarIds = jQuery("#etebaratJari_tbl").jqGrid('getCol', 'id');
    var bedehiSize = bedehiIds.length;
    var etebarSize = etebarIds.length;
    if (bedehiSize > 0 && etebarSize > 0) {
        var i = 0;
        var j = 0;
        var bedehiId = bedehiIds[i];
        var etebarId = etebarIds[j];
        var bedehi_data = jQuery("#bedehihayeJari_tbl").jqGrid('getRowData', bedehiId);
        var etebar_data = jQuery("#etebaratJari_tbl").jqGrid('getRowData', etebarId);
        var khateSanad;
        while(true) {
            if (jQuery.global.parseInt(bedehi_data['amount_remaining']) < jQuery.global.parseInt(etebar_data['amount_remaining'])) {
                khateSanad = {
                    id:bedehi_data['id'] + '_' + etebar_data['id'],
                    bedehi_sarresidDate:bedehi_data['sarresidDate'],
                    bedehi_amount:bedehi_data['amount_remaining'],
                    bedehi_type:bedehi_data['type'],
                    etebar_createdDate:etebar_data['createdDate'],
                    etebar_amount:etebar_data['amount_remaining'],
                    etebar_type:etebar_data['type'],
                    shenaseMoshtari:etebar_data['shenaseMoshtari'],
                    amount:bedehi_data['amount_remaining']
                };
                jQuery("#sanad_tbl").jqGrid('addRowData', bedehi_data['id'] + '_' + etebar_data['id'], khateSanad);
                i++;
//                etebar_data['amount_remaining'] = jQuery.global.parseInt(etebar_data['amount_remaining']) - jQuery.global.parseInt(bedehi_data['amount']);
                etebar_data['amount_remaining'] = jQuery.global.parseInt(etebar_data['amount_remaining']) - jQuery.global.parseInt(bedehi_data['amount_remaining']);
                etebar_data['amount_remaining']=jQuery.global.format(etebar_data['amount_remaining'],"n0");
                if (i < bedehiSize) {
                    bedehiId = bedehiIds[i];
                    bedehi_data = jQuery("#bedehihayeJari_tbl").jqGrid('getRowData', bedehiId);


                } else {
                    break;
                }
            } else if (jQuery.global.parseInt(bedehi_data['amount_remaining']) > jQuery.global.parseInt(etebar_data['amount_remaining'])) {
                khateSanad = {
                    id:bedehi_data['id'] + '_' + etebar_data['id'],
                    bedehi_sarresidDate:bedehi_data['sarresidDate'],
                    bedehi_amount:bedehi_data['amount_remaining'],
                    bedehi_type:bedehi_data['type'],
                    etebar_createdDate:etebar_data['createdDate'],
                    etebar_amount:etebar_data['amount_remaining'],
                    etebar_type:etebar_data['type'],
                    shenaseMoshtari:etebar_data['shenaseMoshtari'],
                    amount:etebar_data['amount_remaining']
                };
                jQuery("#sanad_tbl").jqGrid('addRowData', bedehi_data['id'] + '_' + etebar_data['id'], khateSanad);
                j++;
//                bedehi_data['amount'] = jQuery.global.parseInt(bedehi_data['amount']) - jQuery.global.parseInt(etebar_data['amount_remaining']);
//                bedehi_data['amount'] = jQuery.global.format(bedehi_data['amount'],"n0");
                bedehi_data['amount_remaining'] = jQuery.global.parseInt(bedehi_data['amount_remaining']) - jQuery.global.parseInt(etebar_data['amount_remaining']);
                bedehi_data['amount_remaining'] = jQuery.global.format(bedehi_data['amount_remaining'],"n0");
                if (j < etebarSize) {
                    etebarId = etebarIds[j];
                    etebar_data = jQuery("#etebaratJari_tbl").jqGrid('getRowData', etebarId);
                } else {
                    break;
                }
            } else {
                khateSanad = {
                    id:bedehi_data['id'] + '_' + etebar_data['id'],
                    bedehi_sarresidDate:bedehi_data['sarresidDate'],
                    bedehi_amount:bedehi_data['amount_remaining'],
                    bedehi_type:bedehi_data['type'],
                    etebar_createdDate:etebar_data['createdDate'],
                    etebar_amount:etebar_data['amount_remaining'],
                    etebar_type:etebar_data['type'],
                    shenaseMoshtari:etebar_data['shenaseMoshtari'],
                    amount:bedehi_data['amount_remaining']
                };
                jQuery("#sanad_tbl").jqGrid('addRowData', bedehi_data['id'] + '_' + etebar_data['id'], khateSanad);
                i++;
                j++;
                if (i < bedehiSize && j < etebarSize) {
                    bedehiId = bedehiIds[i];
                    etebarId = etebarIds[j];
                    bedehi_data = jQuery("#bedehihayeJari_tbl").jqGrid('getRowData', bedehiId);
                    etebar_data = jQuery("#etebaratJari_tbl").jqGrid('getRowData', etebarId);
                } else {
                    break;
                }
            }
        }
        var sumBedehi = parseInt(jQuery("#bedehihayeJari_tbl").jqGrid('getCol', 'amount_remaining', false, 'sum'));
        var sumEtebar = parseInt(jQuery("#etebaratJari_tbl").jqGrid('getCol', 'amount_remaining', false, 'sum'));
        if (sumBedehi > sumEtebar) {
            var val = jQuery.global.format((sumBedehi - sumEtebar), 'n0');
            $('#sanad_pager_left #mandeSection').text('مانده : ' + val + ' بدهی ');
        } else if (sumBedehi < sumEtebar) {
            var val = jQuery.global.format((sumEtebar - sumBedehi), 'n0');
            $('#sanad_pager_left #mandeSection').text('مانده : ' + val + ' اعتبار ');
        } else {
            $('#sanad_pager_left #mandeSection').text('');
        }
    }
    else {
        $('#sanad_pager_left #mandeSection').text('');
    }
}
function selectNoeSanad(gr_data){
    if (gr_data['type'] == 'حق بیمه برگشتی (اتومبیل)'
    || gr_data['type'] == 'از محل بازخرید'
    || gr_data['type'] == 'اعتبار وام'
    || gr_data['type'] == 'اعتبار برداشت'
    || gr_data['type'] == 'پرداخت کارمزد'
    || gr_data['type'] == 'اعتبار خسارت'
    || gr_data['type'] == 'الحاقیه اضافی'
    || gr_data['type'] == 'الحاقیه برداشتی'
    || gr_data['type'] == 'اعتبار الحاقیه برگشتی'
    || gr_data['type'] == 'اعتبار ابطال'
            ){
        $('#noeSanad').val("<%=Sanad.NoeSanad.PARDAKHT%>");
    }else {
        $('#noeSanad').val("<%=Sanad.NoeSanad.GHABZE_RESID%>");
    }
}

function sumCalculation() {
    updateSanadTable();

    var sumBedehi = jQuery.global.format(jQuery("#bedehihayeJari_tbl").jqGrid('getCol', 'amount_remaining', false, 'sum'), 'n0');
    var sumEtebar = jQuery.global.format(jQuery("#etebaratJari_tbl").jqGrid('getCol', 'amount_remaining', false, 'sum'), 'n0');
    $('#bedehihayeJari_pager_left .majmoeBedehi').text(sumBedehi);
    $('#etebaratJari_pager_left .majmoeBedehi').text(sumEtebar);
    jQuery("#etebarat_tbl, #etebaratJari_tbl, #bedehiHa_tbl, #bedehihayeJari_tbl").trigger("reloadGrid", [
        {current:true}
    ]);
    setPagingValues('#etebarat_pager', etRowNum, etPageNumber, etTotalPage);
    setPagingValues('#bedehiHa_pager', bedRowNum, bedPageNumber, bedTotalPage);

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

function etebarAmountCalculation(){
    var sumBedehi = jQuery("#bedehihayeJari_tbl").jqGrid('getCol', 'amount_remaining', false, 'sum');
    var sumEtebar = jQuery("#etebaratJari_tbl").jqGrid('getCol', 'amount_remaining', false, 'sum');
    var amountEtebar = sumBedehi-sumEtebar;
    if (amountEtebar > 0){
        var amountEtebarString = jQuery.global.format((amountEtebar), 'n0');
        $("#fishAmountAutomobil").val(amountEtebarString);
        $("#cashAmount").val(amountEtebarString);
        $("#amountHBB").val(amountEtebarString);
        $("#fishAmount").val(amountEtebarString);
        $("#fishAmountElec").val(amountEtebarString);
        $("#checkAmount").val(amountEtebarString);
    }

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
                    "دائم کردن سند": function(){$.post("/fin/changeVaziatSanad.action?id="+ sanadId, function(msg) {
                        alertMessage("سند با موفقیت دائم گردید.");
                        window.open("/fin/printeSanad?pishnehadReport.sanad.id=" + sanadId);
                    });
                        $(this).dialog("close");},
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
<s:actionerror/>
<div style="height:470px;margin:0 auto">
    <div style="width:50%;float:left;height:500px">
        <div style="width:540px;margin:0 auto;">
            <table id="bedehiHa_tbl"></table>
            <div id="bedehiHa_pager"></div>
        </div>
    </div>
    <div style="width:50%;float:left;height:500px">
        <div style="width:540px;margin:0 auto;">
            <table id="etebarat_tbl"></table>
            <div id="etebarat_pager"></div>
        </div>
    </div>
    <div style="width:50%;float:left;height:170px">
        <div style="width:540px;margin:0 auto;">
            <table id="bedehihayeJari_tbl"></table>
            <div id="bedehihayeJari_pager"></div>
        </div>
    </div>
    <div style="width:50%;float:left;height:170px">
        <div style="width:540px;margin:0 auto;">
            <table id="etebaratJari_tbl"></table>
            <div id="etebaratJari_pager"></div>
        </div>
    </div>
    <div style="width:100%;float:left;height:170px;">
        <div style="width:1000px;margin:0 auto;">
            <table id="sanad_tbl"></table>
            <div id="sanad_pager"></div>
        </div>
    </div>

</div>
<table class="inputList" cellspacing="5" width="55%">
    <tr>
        <td>
            <select id="noeSanad">
                <option value="<%=Sanad.NoeSanad.GHABZE_RESID%>">قبض رسید</option>
                <option value="<%=Sanad.NoeSanad.PARDAKHT%>">پرداخت</option>
            </select>
            <label>نوع سند</label>
        </td>
        <td>
            <select id="vaziat" disabled="true">
                <%--<option value="<%=Sanad.Vaziat.DAEM%>">دائم</option>--%>
                <option value="<%=Sanad.Vaziat.MOVAGHAT%>">موقت</option>
            </select>
            <label>وضعیت</label>
        </td>
    </tr>
</table>

<div style="height:35px;text-align:center;margin-top:10px;">
    <input type="button" value="تایید" id="sbmt_btn" style="display:inline;" class="ui-state-disabled"
           disabled="disabled" onclick="submitASanad();"/>
    <sec:authorize ifNotGranted="ROLE_KARBAR_MALI">
        <input type="button" value="بازگشت" style="float:left;margin-left:2px;" onclick="window.location='/fin/viewKhateSanadHa'">
    </sec:authorize>
    <sec:authorize ifAnyGranted="ROLE_KARBAR_MALI">
        <input type="button" onclick="window.location='/loginUser.action'" value="بازگشت"/>
    </sec:authorize>
    <input type="button" value="پرینت چک" id="printeCheck_btn" style="display:none;float:left;margin-left:2px;"
           onclick="printCheck();"/>
    <c:if test="${sanad != null}">
        <input type="button" value="پرینت سند" id="printeSanad_btn" style="float:left;margin-left:2px;"
               onclick="printSanad();"/>

    </c:if>
</div>

<div class="vld" style="display:none;">
    <%@include file="sabteSanad/filterDialogForSabteSanad.jsp" %>
</div>
<div id="sabteBedehiDialog" style="display:none;">
    <%@include file="sabteSanad/sabteBedehiDialog.jsp" %>
</div>
<div id="sabteEtebarDialog" style="display:none;">
    <%@include file="sabteSanad/sabteEtebarDialog.jsp" %>
</div>
</body>
</html>