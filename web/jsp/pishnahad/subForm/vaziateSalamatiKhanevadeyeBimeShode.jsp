<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 4/7/11
  Time: 2:32 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="varNames" scope="page" value='<%=new String[]
{
"sharheEllateFout",
"ellateFout",
"senneDarZamaneFout",
"vaziateSalamati",
"senneDarHayat",
"vaziateHayat",
"nesbatBabimeShode"
}
%>'/>
<c:forEach var="vaziateSalamatiKhanevadeyeBimeShode" items="${pishnehad.vaziateSalamatiKhanevadeyeBimeShode}" varStatus="i">
    <input type="hidden" name="pishnehad.vaziateSalamatiKhanevadeyeBimeShode[${i.index}].id" value="${vaziateSalamatiKhanevadeyeBimeShode.id}"/>
</c:forEach>
<div style="width:780px; margin:0 auto">
    <table id="vaziateSalamatiKhanevadeyeBimeShode_tbl"></table>
    <div id="VSKB_pagernav"></div>
</div>
<script type="text/javascript">

    $(function(){
        var colModel=[
            {name:'${varNames[0]}', index:'${varNames[0]}',title:false, width:100, sortable:true, align:'center'},
            {name:'${varNames[1]}', index:'${varNames[1]}',title:false, width:100, sortable:true, align:'center'},
            {name:'${varNames[2]}', index:'${varNames[2]}',title:false, width:100, sortable:true, align:'center'},
            {name:'${varNames[3]}', index:'${varNames[2]}',title:false, width:100, sortable:true, align:'center'},
            {name:'${varNames[4]}', index:'${varNames[2]}',title:false, width:100, sortable:true, align:'center'},
            {name:'${varNames[5]}', index:'${varNames[2]}',title:false, width:100, sortable:true, align:'center'},
            {name:'${varNames[6]}', index:'${varNames[2]}',title:false, width:100, sortable:true, align:'center'}
        ];
        jQuery("#vaziateSalamatiKhanevadeyeBimeShode_tbl").jqGrid({
            datatype: "local",
            colNames:[
                'شرح علت فوت',
                'علت فوت',
                'سن در زمان فوت',
                'وضعيت سلامتي',
                'سن در حيات',
                'وضعيت حيات',
                'نسبت با بيمه شده'
            ],
            colModel:colModel,
            //            rowNum:100,
            //            rowList:[10,20,30],
            pager: '#VSKB_pagernav',
            hidegrid:false,
            sortname: 'tarikheKhateme',
            viewrecords: true,
            sortorder: "desc",
            caption:"وضعیت سلامتی خانواده بیمه شده",
            //            editurl:"someurl.php",
            height:"auto",
            width:780
        });
        jQuery("#vaziateSalamatiKhanevadeyeBimeShode_tbl").jqGrid('navGrid','#VSKB_pagernav', {search:false});
        $("#del_vaziateSalamatiKhanevadeyeBimeShode_tbl").unbind('click').click(function(){jqGrid_DeleteRow('vaziateSalamatiKhanevadeyeBimeShode_tbl')});
        $("#edit_vaziateSalamatiKhanevadeyeBimeShode_tbl").unbind('click').click(function(){jqGrid_editRow('vaziateSalamatiKhanevadeyeBimeShode_tbl','add_vaziateSalamatiKhanevadeyeBimeShode_div','ویرایش وضعیت سلامتی خانواده بیمه شده','VSKB','pishnehad.vaziateSalamatiKhanevadeyeBimeShode')});
        $("#add_vaziateSalamatiKhanevadeyeBimeShode_tbl").unbind('click').click(function(){jqGrid_addNewRow('vaziateSalamatiKhanevadeyeBimeShode_tbl','add_vaziateSalamatiKhanevadeyeBimeShode_div','اضافه کردن وضعیت سلامتی خانواده بیمه شده','VSKB','pishnehad.vaziateSalamatiKhanevadeyeBimeShode')});

        $('#add_vaziateSalamatiKhanevadeyeBimeShode_tbl, #edit_vaziateSalamatiKhanevadeyeBimeShode_tbl, #del_vaziateSalamatiKhanevadeyeBimeShode_tbl, #refresh_vaziateSalamatiKhanevadeyeBimeShode_tbl').tipsy({gravity:'s'});

    <c:if test="${pishnehad.vaziateSalamatiKhanevadeyeBimeShode != null && fn:length(pishnehad.vaziateSalamatiKhanevadeyeBimeShode)>0}">
        var vaziateSalamatiKhanevadeyeBimeShodeData = [
            <c:forEach var="row" items="${pishnehad.vaziateSalamatiKhanevadeyeBimeShode}" varStatus="i">
            {
                '${varNames[0]}':"${row.sharheEllateFout}<input type='hidden' name='pishnehad.vaziateSalamatiKhanevadeyeBimeShode[${i.index}].${varNames[0]}' value='${row.sharheEllateFout}'/>",
                '${varNames[1]}':"${row.ellateFout}<input type='hidden' name='pishnehad.vaziateSalamatiKhanevadeyeBimeShode[${i.index}].${varNames[1]}' value='${row.ellateFout}'/>",
                '${varNames[2]}':"${row.senneDarZamaneFout}<input type='hidden' name='pishnehad.vaziateSalamatiKhanevadeyeBimeShode[${i.index}].${varNames[2]}' value='${row.senneDarZamaneFout}'/>",
                '${varNames[3]}':"${row.vaziateSalamati}<input type='hidden' name='pishnehad.vaziateSalamatiKhanevadeyeBimeShode[${i.index}].${varNames[3]}' value='${row.vaziateSalamati}'/>",
                '${varNames[4]}':"${row.senneDarHayat}<input type='hidden' name='pishnehad.vaziateSalamatiKhanevadeyeBimeShode[${i.index}].${varNames[4]}' value='${row.senneDarHayat}'/>",
                '${varNames[5]}':"${row.vaziateHayat}<input type='hidden' name='pishnehad.vaziateSalamatiKhanevadeyeBimeShode[${i.index}].${varNames[5]}' value='${row.vaziateHayat}'/>",
                '${varNames[6]}':"${row.nesbatBabimeShode}<input type='hidden' name='pishnehad.vaziateSalamatiKhanevadeyeBimeShode[${i.index}].${varNames[6]}' value='${row.nesbatBabimeShode}'/>"
            }<c:if test="${i.index+1 != fn:length(pishnehad.vaziateSalamatiKhanevadeyeBimeShode)}">,</c:if>
            </c:forEach>
        ];
        for(var i=0;i<=vaziateSalamatiKhanevadeyeBimeShodeData.length;i++) jQuery("#vaziateSalamatiKhanevadeyeBimeShode_tbl").jqGrid('addRowData',i+1,vaziateSalamatiKhanevadeyeBimeShodeData[i]);
        $("#refresh_vaziateSalamatiKhanevadeyeBimeShode_tbl").click();
    </c:if>
    });
    
</script>
