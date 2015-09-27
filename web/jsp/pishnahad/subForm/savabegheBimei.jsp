<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 4/7/11
  Time: 2:32 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="varNames" scope="page" value='<%=new String[]{"tarikheKhateme","sharheAdameSodor","sarmayeHayat","sarmayeFout","natijeNahayi","sherkateBimeGarTozihateSayer","sherkateBimeGar","nameBimeName","noeBimeName"}%>'/>
<div class="topComment">* در صورت داشتن پيشنهاد بيمه عمر صادر شده، در جريان صدور و يا صادر نشده در ساير شرکتهاي بيمه اي، اين بخش راتكميل نماييد. در غير اينصورت نيازي به تكميل آن نيست.</div>
<c:forEach var="row" items="${pishnehad.savabegheBimei}" varStatus="i">
    <input type="hidden" name="pishnehad.savabegheBimei[${i.index}].id" value="${row.id}"/>
</c:forEach>
<div style="width:780px; margin:0 auto">
    <table id="savabegheBimei_tbl"></table>
    <div id="pagernav"></div>
</div>

<script type="text/javascript">
    $(function(){
        var colModel=[
            {name:'${varNames[0]}', index:'${varNames[0]}',title:false, width:80, sortable:true, align:'center'} ,
            {name:'${varNames[1]}', index:'${varNames[1]}',title:false, width:100, sortable:true, align:'center'},
            {name:'${varNames[2]}', index:'${varNames[2]}',title:false, width:100, sortable:true, align:'center'},
            {name:'${varNames[3]}', index:'${varNames[3]}',title:false, width:100,align:"right", sortable:true, align:'center'},
            {name:'${varNames[4]}', index:'${varNames[4]}',title:false, width:100, sortable:true, align:'center'},
            {name:'${varNames[5]}', index:'${varNames[5]}',title:false, hidden:true },
            {name:'${varNames[6]}', index:'${varNames[6]}',title:false, width:100, sortable:true, align:'center'},
            {name:'${varNames[7]}', index:'${varNames[7]}',title:false, width:100, sortable:true, align:'center'},
            {name:'${varNames[8]}', index:'${varNames[8]}',title:false, width:100, sortable:true, align:'center', editable:false,editoptions:{readonly:true,size:10}}
        ];
        jQuery("#savabegheBimei_tbl").jqGrid({
            datatype: "local",
            colNames:[
                'تاریخ خاتمه',
                'شرح عدم صدور',
                'سرمایه حیات',
                'سرمایه فوت',
                'نتیجه نهایی',
                'شرکت بیمه گر - سایر',
                'شرکت بیمه گر',
                'نام بیمه نامه',
                'نوع بیمه نامه'
            ],
            colModel:colModel,
//            rowNum:100,
//            rowList:[10,20,30],
            pager: '#pagernav',
            hidegrid:false,
            sortname: 'tarikheKhateme',
            viewrecords: true,
            sortorder: "desc",
            caption:"سوابق بيمه اي",
//            editurl:"someurl.php",
            height:"auto",
            width:780
        });
        jQuery("#savabegheBimei_tbl").jqGrid('navGrid','#pagernav', {search:false});
        $("#del_savabegheBimei_tbl").unbind('click').click(function(){jqGrid_DeleteRow('savabegheBimei_tbl')});
        $("#edit_savabegheBimei_tbl").unbind('click').click(function(){jqGrid_editRow('savabegheBimei_tbl','add_sabeghe_div','ویرایش سابقه بیمه ای','SB','pishnehad.savabegheBimei')});
        $("#add_savabegheBimei_tbl").unbind('click').click(function(){jqGrid_addNewRow('savabegheBimei_tbl','add_sabeghe_div','اضافه کردن سابقه جدید','SB','pishnehad.savabegheBimei')});

        $('#add_savabegheBimei_tbl, #edit_savabegheBimei_tbl, #del_savabegheBimei_tbl, #refresh_savabegheBimei_tbl').tipsy({gravity:'s'});

        <c:if test="${pishnehad.savabegheBimei != null && fn:length(pishnehad.savabegheBimei)>0}">

        var savabegheBimeiData = [
            <c:forEach var="row" items="${pishnehad.savabegheBimei}" varStatus="i">
            {
                '${varNames[0]}':"${row.tarikheKhateme}<input type='hidden' name='pishnehad.savabegheBimei[${i.index}].${varNames[0]}' value='${row.tarikheKhateme}'/>",
                '${varNames[1]}':"${row.sharheAdameSodor}<input type='hidden' name='pishnehad.savabegheBimei[${i.index}].${varNames[1]}' value='${row.sharheAdameSodor}'/>",
                '${varNames[2]}':"${row.sarmayeHayat}<input type='hidden' name='pishnehad.savabegheBimei[${i.index}].${varNames[2]}' value='${row.sarmayeHayat}'/>",
                '${varNames[3]}':"${row.sarmayeFout}<input type='hidden' name='pishnehad.savabegheBimei[${i.index}].${varNames[3]}' value='${row.sarmayeFout}'/>",
                '${varNames[4]}':"${row.natijeNahayi}<input type='hidden' name='pishnehad.savabegheBimei[${i.index}].${varNames[4]}' value='${row.natijeNahayi}'/>",
                '${varNames[5]}':"${row.sherkateBimeGarTozihateSayer}<input type='hidden' name='pishnehad.savabegheBimei[${i.index}].${varNames[5]}' value='${row.sherkateBimeGarTozihateSayer}'/>",
                '${varNames[6]}':"${row.sherkateBimeGar}<input type='hidden' name='pishnehad.savabegheBimei[${i.index}].${varNames[6]}' value='${row.sherkateBimeGar}'/>",
                '${varNames[7]}':"${row.nameBimeName}<input type='hidden' name='pishnehad.savabegheBimei[${i.index}].${varNames[7]}' value='${row.nameBimeName}'/>",
                '${varNames[8]}':"${row.noeBimeName}<input type='hidden' name='pishnehad.savabegheBimei[${i.index}].${varNames[8]}' value='${row.noeBimeName}'/>"
            }<c:if test="${i.index+1 != fn:length(pishnehad.savabegheBimei)}">,</c:if>
            </c:forEach>
        ];
        for(var i=0;i<=savabegheBimeiData.length;i++) jQuery("#savabegheBimei_tbl").jqGrid('addRowData',i+1,savabegheBimeiData[i]);
        $("#refresh_savabegheBimei_tbl").click();
        </c:if>
    });
</script>
