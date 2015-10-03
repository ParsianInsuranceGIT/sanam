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
        <%--<form name="pishListFormOfRKM" action="?" method="POST">--%>
            <display:table export="true" id="bedehiTasviyeNashodePaginatedList" uid="row5" name="bedehiTasviyeNashodePaginatedList"
                           sort="external" htmlId="bedehiTasviyeNashodePaginatedList"
                           decorator="org.displaytag.decorator.CheckboxTableDecorator"
                           partialList="true"
                           size="${bedehiTasviyeNashodePaginatedList.fullListSize}"
                           pagesize="${bedehiTasviyeNashodePaginatedList.objectsPerPage}"
                           requestURI="?selectedTab=tabs-5&${pagingParams}" clearStatus="true" keepStatus="false"
                           excludedParams="selectedTab decorator pishnehadSearch* _chk" style="width: 100%; margin: 0 auto;">
                <display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.bedehiTasviyeNashodeResultPageNumber%></display:setProperty>
                <c:set var="todayDate"  value="<%=DateUtil.getCurrentDate()%>"></c:set>
                <c:set var="towDaysLaterDate"  value="<%=DateUtil.addDays(DateUtil.getCurrentDate(),2)%>"></c:set>

                <c:choose>
                   <%--zard--%>
                <c:when test="${row5.finalsarresid.compareTo(towDaysLaterDate)<=0 &&
                                row5.finalsarresid.compareTo(todayDate)>0 &&
                                row5.tasvieNashode!=0}">
                    <c:set var="css" value="background:#ffff66;"/>
                </c:when>
                    <%--narenji--%>
                <c:when test="${row5.finalsarresid.compareTo(todayDate)<=0 && row5.sanadNakhorde==0 && row5.tasvieNashode!=0}">
                     <c:set var="css" value="background:#FFA62F;"/>
                </c:when>
                    <%--ghermez--%>

                <c:when test="${row5.finalsarresid.compareTo(todayDate)<=0 && row5.sanadNakhorde!=0}">
                    <c:set var="css" value="background:#F75D59;"/>
                </c:when>
                <c:otherwise>
                <c:set var="css" value=""/>
                </c:otherwise>
                </c:choose>
                <%--<display:column title="رديف" style="${css}">${todayDate}</display:column>--%>
                <%--<display:column title="رديف" style="${css}">${towDaysLaterDate}</display:column>--%>
                <display:column title="رديف" style="${css}">${row5_rowNum+((bedehiTasviyeNashodePaginatedList.pageNumber-1)*bedehiTasviyeNashodePaginatedList.objectsPerPage)}</display:column>

                <display:column title="شماره بيمه نامه" style="${css}" property="bimenameID" />
                <%--<display:column property="credebit.identifier" title="شماره بيمه نامه" style="${css}">${bimenameID}</display:column>--%>
                <display:column title="نام بيمه گذار" style="${css}" property="bimeGozarID" />
                <display:column title="كد  نماينده" style="${css}" property="namayandeID" />
                <display:column title="نام نماينده" style="${css}" property="namayandeName" />
                <display:column title="كد واحد صدور " style="${css}" property="vsodoorID" />
                <display:column title=" نام واحد صدور" style="${css}" property="vsodoorName" />
                <display:column title="تاريخ سررسيد" style="${css}" property="sarresid_date" />
                <display:column title="تاريخ ايجاد" style="${css}" property="created_date" />
                <display:column title="مبلغ کل" style="${css}" property="mablaghKol" />
                <%--<display:column property="paidReceivedAmountFormat" title="مبلغ دريافت شده" style=""/>--%>
                <display:column title="مبلغ سند نخورده" style="${css}" property="sanadNakhorde" />
                <display:column title="مبلغ تسويه نشده" style="${css}" property="tasvieNashode" />
                <%--<display:column title= "نوع"  style="${css}" property="CreType" />--%>
                <display:column title="كد بازارياب" style="${css}" property="bazaryab_sanam_id" />
                <display:column title="مهلت سررسيد" style="${css}" property="mohlatsarresid" />
                <display:column title="كسرسيوم" style="${css}" property="consortiumText" />



                <%--<display:column property="vosoulStateFarsi" title="وضعيت وصول" style=""/>--%>
                <%--<c:if test="${sessionScope.daftar=='NAMAYANDE'}">--%>
                <%--<display:column title="عمليات" style=""> <input type="button" onclick="window.location='/fin/loadSanadZani'" value="انتقال"/></display:column>--%>
                <%--</c:if>--%>
            </display:table>
        <%--</form>--%>
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

