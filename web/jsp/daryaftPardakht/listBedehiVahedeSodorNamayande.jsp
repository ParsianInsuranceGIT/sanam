<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.bimename.Gharardad" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.Core.ConstantPaging" %>
<%@ page import="com.bitarts.parsian.Core.Constant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ include file="/jsp/taglibs.jsp" %>

<script type="text/javascript">
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
            alertMessage("رکوردي انتخاب نشده است");
        }else{
            for (j=fields.length-1;j>=0;j--){
                $(objfrm).prepend("<input type='hidden' id='embedI_"+fields[j].f+"' name='"+fields[j].f+"' value='"+fields[j].v+"'></input>");
            }
            objfrm.action = "/fin/operationSelectBedehiNamayande";
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

<div style="overflow:auto;">
    <div style="overflow: auto;">
        <form name="pishListFormOfRKM" action="?" method="POST">
            <display:table export="true" id="bedehiCredebitListPaginated" uid="row" name="bedehiCredebitListPaginated"
                           sort="external" htmlId="bedehiCredebitListPaginated"
                           decorator="org.displaytag.decorator.CheckboxTableDecorator"
                           partialList="true"
                           size="bedehiCredebitListPaginated.fullListSize"
                           pagesize="${bedehiCredebitListPaginated.objectsPerPage}"
                           requestURI="?selectedTab=tabs-1&${pagingParams}" clearStatus="true" keepStatus="false"
                           excludedParams="selectedTab decorator pishnehadSearch* _chk" style="width: 100%; margin: 0 auto;">
                <%--<c:choose>--%>
                    <%--<c:when test="${sessionScope.daftar_id==1}">--%>
                        <%--<c:set var="css" value=""/>--%>
                    <%--</c:when>--%>

                    <%--<c:otherwise>--%>
                        <%--<c:set var="css" value="background:#ffffcc;"/>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
                    <display:column property="checkbox" title="<input id='pishnehadsSelectAll' type='checkbox' title='انتخاب/حذف همه'/>" style=""/>
                    <%--<display:column title="Select" >--%>
                        <%--<input type="checkbox" name="updateGrp" value="${row.id}" checked="checked"/> </display:column>--%>
                    <display:column title="رديف" style="">${row_rowNum+((bedehiCredebitListPaginated.pageNumber-1)*bedehiCredebitListPaginated.objectsPerPage)}</display:column>
                <display:column title="رديف" style="">${pagingParams}</display:column>
                    <display:column property="identifier" title="شماره بيمه نامه" style=""/>
                    <display:column property="rcptName" title="نام بيمه گذار" style="" />
                    <display:column property="namayandeName" title="نام نماينده" style="" />
                    <display:column property="namayandeKodeNamayandeKargozar" title="کد نماينده" style="" />
                    <display:column property="sarresidDate" title="تاريخ سررسيد" style=""/>
                    <display:column property="createdDate" title="تاريخ ايجاد" style=""/>
                    <display:column property="amount" title="مبلغ کل" style=""/>
                    <display:column property="paidReceivedAmountFormat" title="مبلغ دريافت شده" style=""/>
                    <display:column property="remainingAmount" title="مبلغ مانده" style=""/>
                    <display:column property="credebitTypeFarsi" title="نوع" style=""/>
                    <display:column property="shomareGharardad" title="شماره قرارداد" style=""/>
                    <display:column property="statusFarsiValidationBedehi" title="وضعيت سند" style=""/>
                    <display:column property="vosoulStateFarsi" title="وضعيت وصول" style=""/>
                    <%--<c:if test="${sessionScope.daftar=='NAMAYANDE'}">--%>
                        <%--<display:column title="عمليات" style=""> <input type="button" onclick="window.location='/fin/loadSanadZani'" value="انتقال"/></display:column>--%>
                    <%--</c:if>--%>
                </display:table>
        </form>
    </div>
</div>
<%--<sec:authorize ifAllGranted="ROLE_MALI_PARDAKHT_INTERNETI">--%>
<%--<table  style="width: 100%">--%>
    <%--<tr>--%>
        <%--<td style="width: 20%"></td>--%>
        <%--<td style="width: 60%">--%>
            <%--<div>--%>
                <%--<table class="inputList" cellspacing="1" cellpadding="5" style="border:1px solid black;text-align: center" >--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label>با انتخاب بدهي ها از جدول بالا و انتخاب حساب از منوي زير، مي توانيد بدهي هاي خود را به صورت اينترنتي پرداخت و سند بزنيد</label>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<select name="credebit.bankName" id="bankMaghsadAutomobil" style="width: 300px;">--%>
                                <%--<option value="<%=Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD%>"><%=Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD%></option>--%>
                            <%--</select>--%>

                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<input type="button" onclick="javascript:displaytagform_bedehiSelection('pishListFormOfRKM',[{f:'par',v:['aa%22az']}])" value="ارسال به پرداخت اينترنتي"/>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
            <%--</div>--%>
        <%--</td>--%>
        <%--<td style="width: 20%"></td>--%>
    <%--</tr>--%>
<%--</table>--%>

<%--</sec:authorize>--%>

