<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.bimename.Gharardad" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.Core.ConstantPaging" %>
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
</script>

<div style="overflow:auto;">
    <div style="overflow: auto;">
        <display:table export="true" id="bedehiVosulNashodeCredebitListPaginated" uid="row4" name="bedehiVosulNashodeCredebitListPaginated"
                       defaultsort="14"  defaultorder="ascending" htmlId="bedehiVosulNashodeCredebitListPaginated"
                       partialList="true"
                       size="${bedehiVosulNashodeCredebitListPaginated.fullListSize}"
                       pagesize="${bedehiVosulNashodeCredebitListPaginated.objectsPerPage}"
                       requestURI="?selectedTab=tabs-4&${pagingParams}" clearStatus="true" keepStatus="false"
                       excludedParams="selectedTab" style="width: 100%; margin: 0 auto;">
            <display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.bedehiVosulNashodeResultPageNumber%></display:setProperty>
            <c:choose>
                <c:when test="${row4.finalSarresidDate > 0 && sessionScope.daftar_id==1}">
                    <c:set var="css" value=""/>
                </c:when>

                <c:when test="${row4.finalSarresidDate > 0 && sessionScope.daftar_id!=1}">
                    <c:set var="css" value="background:#ffffcc;"/>
                </c:when>
                <c:when test="${row4.finalSarresidDate <= 0}" >
                    <c:set var="css"  value="background:#f08080;" />
                </c:when>
            </c:choose>
            <display:column title="ردیف" style="${css}">${row4_rowNum+((bedehiVosulNashodeCredebitListPaginated.pageNumber-1)*bedehiVosulNashodeCredebitListPaginated.objectsPerPage)}</display:column>
            <display:column property="rcptName" title="نام بیمه گذار" style="${css}" />
            <display:column property="namayandeName" title="نام نماینده" style="${css}" />
            <display:column property="namayandeCode" title="کد نماینده" style="${css}" />
            <display:column property="createdDate" title="تاریخ ایجاد" style="${css}"/>
            <display:column property="amount" title="مبلغ کل" style="${css}"/>
            <display:column property="paidReceivedAmount" title="مبلغ دریافت شده" style="${css}"/>
            <display:column property="remainingAmount" title="مبلغ مانده" style="${css}"/>
            <display:column property="amountKhateSanad" title="مبلغ خط سند" style="${css}"/>
            <display:column property="identifier" title="شماره بیمه نامه" style="${css}"/>
            <display:column property="shomareGharardad" title="شماره قرارداد" style="${css}"/>
            <display:column property="statusFarsi" title="وضعیت سند" style="${css}"/>
            <display:column property="sarresidDate" sortable="true" title="تاریخ سررسید" style="${css}"/>
            <display:column property="finalSarresidDate" sortable="true" title="مهلت تایید وصول" style="${css}" />
        </display:table>
    </div>
</div>


