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

<form action="/fin/listBedehiVahedeSodorNamayande" method="post" id="aaa">
    <div style="overflow:auto;">
        <div style="overflow: auto;">
            <display:table export="true" id="etebarCredebitListPaginated" uid="row1" name="etebarCredebitListPaginated"
                           sort="external" htmlId="etebarCredebitListPaginated"
                           partialList="true"
                           size="${etebarCredebitListPaginated.fullListSize}"
                           pagesize="${etebarCredebitListPaginated.objectsPerPage}"
                           requestURI="?selectedTab=tabs-2&${pagingParams}" clearStatus="true" keepStatus="false"
                           excludedParams="selectedTab" style="width: 100%; margin: 0 auto;" >
                <display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.etebarResultPageNumber%></display:setProperty>
                <c:choose>
                    <c:when test="${sessionScope.daftar_id==1}">
                        <c:set var="css" value=""/>
                    </c:when>

                    <c:otherwise>
                        <c:set var="css" value="background:#ffffcc;"/>
                    </c:otherwise>
                </c:choose>
                    <display:column title="ردیف" style="${css}">${row1_rowNum+((etebarCredebitListPaginated.pageNumber-1)*etebarCredebitListPaginated.objectsPerPage)}</display:column>
                    <display:column property="shomareMoshtari" title="شناسه پرداخت" style="${css}"/>
                    <display:column property="rcptName" title="نام بیمه گذار" style="${css}" />
                    <display:column property="namayandeName" title="نام نماینده" style="${css}" />
                    <display:column property="namayandeKodeNamayandeKargozar" title="کد نماینده" style="${css}" />
                    <display:column property="sarresidDate" title="تاریخ سررسید" style="${css}"/>
                    <display:column property="createdDate" title="تاریخ ایجاد" style="${css}"/>
                    <display:column property="amount" title="مبلغ کل" style="${css}"/>
                    <display:column property="paidReceivedAmountFormat" title="مبلغ دریافت شده" style="${css}"/>
                    <display:column property="remainingAmount" title="مبلغ مانده" style="${css}"/>
                    <display:column property="credebitTypeFarsi" title="نوع" style="${css}"/>
                    <display:column property="shomareGharardad" title="شماره قرارداد" style="${css}"/>
                    <display:column property="statusFarsi" title="وضعیت سند" style="${css}"/>
                    <display:column property="vosoulStateFarsi" title="وضعیت وصول" style="${css}"/>
            </display:table>
        </div>
    </div>
</form>

