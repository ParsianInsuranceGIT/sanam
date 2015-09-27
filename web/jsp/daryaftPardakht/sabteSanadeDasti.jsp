<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 5/31/11
  Time: 7:45 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<script type="text/javascript">
    $(function() {

        jQuery("#bedehiSanadZani_tbl").jqGrid({
            datatype: "local",hidegrid:false,viewrecords: true,sortorder: "desc",caption:null,
            colNames:[
                'id','تاریخ سررسید','مقدار','نوع بدهی'
            ],
            colModel:[
                {name:'id',index:'id',hidden:true} ,
                {name:'sarresidDate',index:'sarresidDate', width:100, sortable:true, align:'center'} ,
                {name:'amount',index:'amount', width:100, sortable:true, sorttype:"int", align:'center'},
                {name:'type',index:'type', width:100, sortable:true, align:'center'}
            ],
            pager: '#bedehiSanadZani_pager',
            sortname: 'sarresidDate',
            height:80
        });
        jQuery("#bedehiHa_tbl").jqGrid({
            datatype: "local",hidegrid:false,viewrecords: true,sortorder: "desc",caption:null,
            colNames:[
                'id','تاریخ سررسید','مقدار','نوع بدهی'
            ],
            colModel:[
                {name:'id',index:'id',hidden:true} ,
                {name:'sarresidDate',index:'sarresidDate', width:100, sortable:true, align:'center'} ,
                {name:'amount',index:'amount', width:100, sortable:true, sorttype:"int", align:'center'},
                {name:'type',index:'type', width:100, sortable:true, align:'center'}
            ],
            pager: '#bedehiHa_pager',
            sortname: 'sarresidDate',
            height:210
        });
        jQuery("#etebarSanadZani_tbl").jqGrid({
            datatype: "local",hidegrid:false,viewrecords: true,sortorder: "desc",caption:null,
            colNames:[
                'id','تاریخ','نوع اعتبار','مقدار','شناسه مشتری'
            ],
            colModel:[
                {name:'id',index:'id',hidden:true},
                {name:'createdDate',index:'createdDate', width:70, sortable:true, align:'center'},
                {name:'type',index:'type', width:70, sortable:true, align:'center'},
                {name:'amount',index:'amount', width:70, sortable:true, sorttype:"int", align:'center'},
                {name:'shenaseMoshtari',index:'shenaseMoshtari', width:70, sortable:true, align:'center'}
            ],
            pager: '#etebarSanadZani_pager',
            sortname: 'createdDate',
            height:80
        });
        jQuery("#etebar_tbl").jqGrid({
            datatype: "local",hidegrid:false,viewrecords: true,sortorder: "desc",caption:null,
            colNames:[
                'id','تاریخ','نوع اعتبار','مقدار','شناسه مشتری'
            ],
            colModel:[
                {name:'id',index:'id',hidden:true},
                {name:'createdDate',index:'createdDate', width:70, sortable:true, align:'center'},
                {name:'type',index:'type', width:70, sortable:true, align:'center'},
                {name:'amount',index:'amount', width:70, sortable:true, sorttype:"int", align:'center'},
                {name:'shenaseMoshtari',index:'shenaseMoshtari', width:70, sortable:true, align:'center'}
            ],
            pager: '#etebar_pager',
            sortname: 'createdDate',
            height:210
        });

        var params = {search:false, add:false, del:false, edit:false, refresh:false};
        jQuery("#etebar_tbl").jqGrid('navGrid','#etebar_pager', params);
        jQuery("#etebarSanadZani_tbl").jqGrid('navGrid','#etebarSanadZani_pager', params);
        jQuery("#bedehiHa_tbl").jqGrid('navGrid','#bedehiHa_pager', params);
        jQuery("#bedehiSanadZani_tbl").jqGrid('navGrid','#bedehiSanadZani_pager', params);

        var tbd1 = "<td class='tbd1_id ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='ui-icon ui-icon-arrowthick-1-s'></span></div></td>";
        var tdSep = "<td class='ui-pg-button ui-state-disabled' style='width:4px;'><span class='ui-separator'></span></td>";
        var tbd2 = "<td style='direction:rtl;'><span>مجموع : </span><span class='majmoeBedehi'>0</span></td>";
        var tbd3 = "<td class='tbd1_id ui-pg-button ui-corner-all'><div class='ui-pg-div'><span class='ui-icon ui-icon-arrowthick-1-n'></span></div></td>";

        $('#bedehiSanadZani_pager_left .navtable tr, #etebarSanadZani_pager_left .navtable tr').append(tbd1+tdSep+tbd2);
        $('#bedehiSanadZani_pager_left .navtable .tbd1_id')
                .attr({"title":'صرف نظر' }).tipsy({gravity:'s'})
                .click(function(){
            if (!$(this).hasClass('ui-state-disabled')) {
                var gr = jQuery("#bedehiSanadZani_tbl").jqGrid('getGridParam','selrow');
                if( gr != null ){
                    var gr_data = jQuery("#bedehiSanadZani_tbl").jqGrid('getRowData',gr);

                    jQuery("#bedehiHa_tbl").jqGrid('addRowData',gr_data['id'],gr_data);
                    jQuery("#bedehiSanadZani_tbl").jqGrid('delRowData',gr);
                    sumCalculation();
                    //                        $('#bedehiHa_tbl tr#' + gr).slideUp(function(){
                <%----%>
                    //                        });
                }
            }
            return false;
        }).hover(hover_y,hover_n);
        $('#etebarSanadZani_pager_left .navtable .tbd1_id')
                .attr({"title":'صرف نظر' }).tipsy({gravity:'s'})
                .click(function(){
            if (!$(this).hasClass('ui-state-disabled')) {
                var gr = jQuery("#etebarSanadZani_tbl").jqGrid('getGridParam','selrow');
                if( gr != null ){
                    var gr_data = jQuery("#etebarSanadZani_tbl").jqGrid('getRowData',gr);

                    jQuery("#etebar_tbl").jqGrid('addRowData',gr_data['id'],gr_data);
                    jQuery("#etebarSanadZani_tbl").jqGrid('delRowData',gr);
                    sumCalculation();
                    //                        $('#bedehiHa_tbl tr#' + gr).slideUp(function(){
                <%----%>
                    //                        });
                }
            }
            return false;
        }).hover(hover_y,hover_n);
        <%----%>
        $('#bedehiHa_pager .navtable tr, #etebar_pager .navtable tr').append(tbd3);
        $('#bedehiHa_pager_left .navtable .tbd1_id')
                .attr({"title":'انتخاب برای سند زنی' }).tipsy({gravity:'s'})
                .click(function(){
            if (!$(this).hasClass('ui-state-disabled')) {
                var gr = jQuery("#bedehiHa_tbl").jqGrid('getGridParam','selrow');
                if( gr != null ){
                    var gr_data = jQuery("#bedehiHa_tbl").jqGrid('getRowData',gr);

                    jQuery("#bedehiSanadZani_tbl").jqGrid('addRowData',gr_data['id'],gr_data);
                    jQuery("#bedehiHa_tbl").jqGrid('delRowData',gr);
                    sumCalculation();
                    //                        $('#bedehiHa_tbl tr#' + gr).slideUp(function(){
                <%----%>
                    //                        });
                }
            }
            return false;
        }).hover(hover_y,hover_n);
        $('#etebar_pager_left .navtable .tbd1_id')
                .attr({"title":'انتخاب برای سند زنی' }).tipsy({gravity:'s'})
                .click(function(){
            if (!$(this).hasClass('ui-state-disabled')) {
                var gr = jQuery("#etebar_tbl").jqGrid('getGridParam','selrow');
                if( gr != null ){
                    var gr_data = jQuery("#etebar_tbl").jqGrid('getRowData',gr);

                    jQuery("#etebarSanadZani_tbl").jqGrid('addRowData',gr_data['id'],gr_data);
                    jQuery("#etebar_tbl").jqGrid('delRowData',gr);
                    sumCalculation();
                    //                        $('#bedehiHa_tbl tr#' + gr).slideUp(function(){
                <%----%>
                    //                        });
                }
            }
            return false;
        }).hover(hover_y,hover_n);

        var etebar = [
            <c:forEach var="credebit" items="${credebitList}" varStatus="i">
            {id:${credebit.id},createdDate:'${credebit.createdDate}',type:'${credebit.type == 'FISH'? 'فیش':credebit.type == 'ELECTRONICI'?'الکترونیکی':credebit.type}',amount:'${credebit.amount}',shenaseMoshtari:'${credebit.shenaseMoshtari}'}
            <c:if test="${i.index+1 != fn:length(credebitList)}">,</c:if>
            </c:forEach>
        ];
        var bedehi = [
            <c:forEach var="bedehi" items="${bedehiList}" varStatus="i">
            {id:${bedehi.id}, bazaryab: '${bedehi.bazarYabSanam.name}',sarresidDate:'${bedehi.sarresidDate}',amount:'${bedehi.amount}',type:'${bedehi.type == 'GHEST' ? 'قسط': bedehi.type == 'CHECK'?'چک':bedehi.type == 'TANKHAH'?'پرداخت از محل تنخواه':bedehi.type}'}
            <c:if test="${i.index+1 != fn:length(bedehiList)}">,</c:if>
            </c:forEach>
        ];
        for(var i=0;i<bedehi.length;i++) jQuery("#bedehiHa_tbl").jqGrid('addRowData',bedehi[i]['id'],bedehi[i]);
        for(var i=0;i<etebar.length;i++) jQuery("#etebar_tbl").jqGrid('addRowData',etebar[i]['id'],etebar[i]);
        jQuery("#etebar_tbl, #etebarSanadZani_tbl, #bedehiHa_tbl, #bedehiSanadZani_tbl").trigger("reloadGrid", [{current:true}]);

    });

    function submitASanad(){
        var bedehiIds = jQuery("#bedehiSanadZani_tbl").jqGrid('getCol','id');
        var etebarIds = jQuery("#etebarSanadZani_tbl").jqGrid('getCol','id');
        for(var i = 0 ; i < bedehiIds.length ; i++){
            $('#frm_sbmt').append("<input type='input' name='bedehiList["+i+"].id' value='"+bedehiIds[i]+"'/>");
        }
        for(var i = 0 ; i < etebarIds.length ; i++){
            $('#frm_sbmt').append("<input type='input' name='credebitList["+i+"].id' value='"+etebarIds[i]+"'/>");
        }
        $('#frm_sbmt').append("<input type='input' name='pishnehadId' value='${pishnehadId}'/>");
        $('#frm_sbmt').submit();
    }
    function sumCalculation(){
        var sumBedehi = jQuery("#bedehiSanadZani_tbl").jqGrid('getCol','amount',false,'sum');
        var sumEtebar = jQuery("#etebarSanadZani_tbl").jqGrid('getCol','amount',false,'sum');
        $('#bedehiSanadZani_pager_left .majmoeBedehi').text(sumBedehi);
        $('#etebarSanadZani_pager_left .majmoeBedehi').text(sumEtebar);
        jQuery("#etebar_tbl, #etebarSanadZani_tbl, #bedehiHa_tbl, #bedehiSanadZani_tbl").trigger("reloadGrid", [{current:true}]);
        if(sumBedehi == sumEtebar) activation();
        else deactivate();

    }
    function hover_y(){
        if (!$(this).hasClass('ui-state-disabled')) {
            $(this).addClass("ui-state-hover");
        }
    }function hover_n(){
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
</script>
<style type="text/css">
    #credebit span.hd{
        font-family:B Titr, tahoma, times new roman;
        font-size:16px;
        color:#0260A0;
        float:right;
        margin-right:10px;
    }
</style>
<title>سند زنی</title>
</head>
<body>
<form action='/sabteMultiSanadForPishnehad' id='frm_sbmt' style="display:none;"></form>
<s:actionmessage/>
<div style="height:470px;margin:0 auto;width:100%" id="credebit">
    <div style="border-right:1px solid #A5A8B8;width:50%;float:left;height:170px">
        <div style="height:35px"><span class="hd">بدهی های سند زنی</span></div>
        <div style="width:300px;margin:0 auto;">
            <table id="bedehiSanadZani_tbl"></table>
            <div id="bedehiSanadZani_pager"></div>
        </div>
    </div>
    <div style="width:49%;float:left;height:170px">
        <div style="height:35px"><span class="hd">اعتبار برای سند زنی</span></div>
        <div style="width:300px;margin:0 auto;">
            <table id="etebarSanadZani_tbl"></table>
            <div id="etebarSanadZani_pager"></div>
        </div>
    </div>
    <div style="border-right:1px solid #A5A8B8;width:50%;float:left;height:300px">
        <div style="height:35px"><span class="hd">بدهی ها</span></div>
        <div style="width:300px;margin:0 auto;">
            <table id="bedehiHa_tbl"></table>
            <div id="bedehiHa_pager"></div>
        </div>
    </div>
    <div style="width:49%;float:left;height:300px">
        <div style="height:35px"><span class="hd">اعتبار</span></div>
        <div style="width:300px;margin:0 auto;">
            <table id="etebar_tbl"></table>
            <div id="etebar_pager"></div>
        </div>
    </div>
</div>
<div style="height:35px;text-align:center;margin-top:10px;">
    <input type="button" value="تایید"  id="sbmt_btn" style="display:inline;" class="ui-state-disabled" disabled="disabled" onclick="submitASanad();"/>
    <input type="button" class="ui-state-default  ui-corner-all" style="float:left;" onclick="submitTransitionForm();" value="اتمام">
</div>
</body>
</html>