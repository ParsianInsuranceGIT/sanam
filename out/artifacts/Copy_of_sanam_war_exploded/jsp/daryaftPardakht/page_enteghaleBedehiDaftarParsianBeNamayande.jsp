<%--
  Created by IntelliJ IDEA.
  User: Arron0
  Date: Sep 6, 2011
  Time: 5:53:55 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>




<html>
<head><title>ليست بدهي هاي دفتر پارسيان</title></head>

<body>
<script>
    $(function(){
        $('input[name="_chk"]').each(function () {
            $(this).attr('checked', false);
        })
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
    $("input,textarea").each(function(){$(this).addClass("ui-state-default  ui-corner-all");});
    $("select").each(function(){$(this).addClass("ui-state-default  ui-corner-all");});
    $(".help, .comment").tipsy({gravity:'s'});
    //    $("form, .vld").validationEngine({promptPosition:"topLeft"});
    $('.dblRadio').buttonset();
    $(".tipsi").tipsy({gravity:'s'});


    $(".digitSeparators").each(function(){$(this).keyup();});
    $(".digitSeparatorsManfi").each(function(){$(this).keyup();});


    function displaytagform_bedehiSelection(formname, fields){
        var objfrm = document.forms[formname];
        if($(objfrm).serialize().indexOf('_chk') == -1){
            alertMessage("رکوردی انتخاب نشده است");
        }else{
            for (j=fields.length-1;j>=0;j--){
                $(objfrm).prepend("<input type='hidden' id='embedI_"+fields[j].f+"' name='"+fields[j].f+"' value='"+fields[j].v+"'></input>");
            }
            objfrm.action = "/fin/enteghalGoruhiBedehiParsian";
            objfrm.submit();
        }
    }

    $(function(){
        $('#pishnehadsSelectAll').bind("change" ,function(){
            $('input[name="_chk"]').each(function(){
                $(this).attr('checked', $('#pishnehadsSelectAll').attr('checked'));
            })
        });
    });
</script>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<%@include file="/jsp/josteju/searchBazaryabSanam.jsp"%>
<div class="expandableTitleBar" id="expandableAsl">
    <p class="heading">
        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        جستجو
    </p>
    <div class="content" style="display:none;" id="searchAslContent1">
        <form action="/fin/listBedehihayeDaftarParsian" method="get">
            <table dir="rtl" class="inputList">
                <tr>
                    <td>شماره بیمه نامه</td>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <input type="text" name="identifier" id="identifierBedehi"/>
                    </td>
                    <td>نام بیمه گذار</td>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <input type="text" name="rcptName" id="rcptNameBedehi"/>
                    </td>
                </tr>
                <tr>
                    <td>از تاریخ سر رسید</td>
                    <td>
                        <input type="text" name="sarresidDateFrom" id="sarresidDateFrom" class="datePkr" readonly="true"/>
                    </td>
                    <td>تا تاریخ سر رسید</td>
                    <td>
                        <input type="text" name="sarresidDateTo" id="sarresidDateTo" class="datePkr" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td>از تاریخ ایجاد</td>
                    <td>
                        <input type="text" name="createdDateFrom" id="createdDateFrom" class="datePkr" readonly="true"/>
                    </td>
                    <td>تا تاریخ ایجاد</td>
                    <td>
                        <input type="text" name="createdDateTo" id="createdDateTo" class="datePkr" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td><label>نمايندگي</label></td>
                    <td>
                        <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_namayandegiId','search_namayandegiName', '');" style="float:left;" title="جستجو"></span>
                        <input type="hidden" name="search_namayandegiId" id="search_namayandegiId" />
                        <input type="text" name="search_namayandegiName" id="search_namayandegiName"  readonly="true"/>
                    </td>
                    <td><label>واحد صدور</label></td>
                    <td>
                        <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_vahedesodorId','search_vahedesodorName', '');" style="float:left;" title="جستجو"></span>
                        <input type="hidden" name="search_vahedesodorId" id="search_vahedesodorId" />
                        <input type="text" name="search_vahedesodorName" id="search_vahedesodorName"  readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td><label>بازاریاب</label></td>
                    <td>
                        <span class="help ui-icon ui-icon-search" onclick="fillBazaryabSanam('bsId','bsName', '');" style="float:left;" title="جستجو"></span>
                        <input type="hidden" name="bazaryabSanamId" id="bsId" />
                        <input type="text" name="bname" id="bsName"  readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="3">
                        <script type="text/javascript">
                            function clearSeachFrom()
                            {
                                $('#identifierBedehi').val('');
                                $('#shomareMoshtariBedehi').val('');
                                $('#rcptNameBedehi').val('');
                                $('#amountBedehi').val('');
                                $('#paidReceivedAmountBedehi').val('');
                                $('#search_namayandegiId').val('');
                                $('#search_namayandegiName').val('');
                                $('#sarresidDateFrom').val('');
                                $('#sarresidDateTo').val('');
                                $('#createdDateFrom').val('');
                                $('#createdDateTo').val('');
                            }


                        </script>

                        <input type="submit"  style="margin-left:25px" value="جستجو">
                        <span class="noThing"></span>
                        <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom()"/>
                    </td>
                </tr>
            </table>
            </table>
        </form>
    </div>
</div>

<div id="smoothScrollaasn" style="overflow: auto;">
    <form name="listBedehiForEnteghal" action="?" method="POST">
        <display:table export="true" id="ParsianCredebitsTbl" uid="row" name="ParsianCredebitsListPaginated"
                       sort="external" htmlId="ParsianCredebitsTbl"
                       decorator="org.displaytag.decorator.CheckboxTableDecorator"
                       partialList="true"
                       size="${ParsianCredebitsListPaginated.fullListSize}"
                       pagesize="${ParsianCredebitsListPaginated.objectsPerPage}"
                       requestURI="" clearStatus="true" keepStatus="false"
                       excludedParams="selectedTab pishnehadSearch* _chk" style="width: 100%; margin: 0 auto;">
            <display:column property="checkbox" title="<input id='pishnehadsSelectAll' type='checkbox' title='انتخاب/حذف همه'/>" style=""/>
            <display:column title="رديف" style="">${row_rowNum+((ParsianCredebitsListPaginated.pageNumber-1)*ParsianCredebitsListPaginated.objectsPerPage)}</display:column>
            <display:column property="identifier" title="شماره بيمه نامه" style=""/>
            <display:column property="rcptName" title="نام بيمه گذار" style="" />
            <display:column property="namayandeName" title="نام نماينده" style="" />
            <display:column property="namayandeKodeNamayandeKargozar" title="كد نماينده" style="" />
            <display:column property="sarresidDate" title="تاريخ سررسيد" style=""/>
            <display:column property="createdDate" title="تاريخ ايجاد" style=""/>
            <display:column property="amount" title="مبلغ کل" style=""/>
            <display:column property="paidReceivedAmountFormat" title="مبلغ دريافت شده" style=""/>
            <display:column property="remainingAmount" title="مبلغ مانده" style=""/>
            <display:column property="credebitTypeFarsi" title="نوع" style=""/>
            <display:column property="shomareGharardad" title="شماره قرارداد" style=""/>
            <display:column property="statusFarsiValidationBedehi" title="وضعيت سند" style=""/>
            <display:column property="vosoulStateFarsi" title="وضعيت وصول" style=""/>
            <display:column property="bazarYabSanam.name" title="بازارياب" style=""/>
            <display:column title="عمليات" style=""><input type="button" onclick="window.location='/fin/enteghalTakiBedehiBeDaftarNamayande?bedehiId_ForEnteghal=${row.id}'" value="انتقال"/></display:column>
        </display:table>

    </form>
</div>
<br>
<table class="inputList" cellspacing="1" cellpadding="5" style="width:50%; border:1px solid black;" >
    <tr>
        <td>
          <label>با انتخاب تعدادي از بدهي ها مي توانيد عمليات انتقال را به صورت گروهي انجام دهيد</label>
        </td>
    </tr>
    <tr>
        <td>
            <input type="button" onclick="javascript:displaytagform_bedehiSelection('listBedehiForEnteghal',[{f:'par',v:['aa%22az']}])" value="انتقال گروهي"/>
        </td>
    </tr>
 </table>

</body>
</html>