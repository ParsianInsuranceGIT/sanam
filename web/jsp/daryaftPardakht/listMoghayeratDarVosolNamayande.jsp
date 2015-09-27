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
        <display:table export="true" id="etebarMoghayeratCredebitListPaginated" uid="row3" name="etebarMoghayeratCredebitListPaginated"
                       sort="external" htmlId="etebarMoghayeratCredebitListPaginated"
                       partialList="true"
                       size="${etebarMoghayeratCredebitListPaginated.fullListSize}"
                       pagesize="${etebarMoghayeratCredebitListPaginated.objectsPerPage}"
                       requestURI="?selectedTab=tabs-3&${pagingParams}" clearStatus="true" keepStatus="false"
                       excludedParams="selectedTab" style="width: 100%; margin: 0 auto;">
            <display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.moghayeratResultPageNumber%></display:setProperty>
            <c:choose>
                <c:when test="${sessionScope.daftar_id==1}">
                    <c:set var="css" value=""/>
                </c:when>

                <c:otherwise>
                    <c:set var="css" value="background:#ffffcc;"/>
                </c:otherwise>
            </c:choose>
                <display:column title="ردیف" style="${css}">${row3_rowNum+((etebarMoghayeratCredebitListPaginated.pageNumber-1)*etebarMoghayeratCredebitListPaginated.objectsPerPage)}</display:column>
                <display:column property="namayandeName" title="نام نماینده" style="${css}" />
                <display:column property="namayandeKodeNamayandeKargozar" title="کد نماینده" style="${css}" />
                <display:column property="createdDate" title="تاریخ ایجاد" style="${css}"/>
                <display:column property="amount" title="مبلغ کل" style="${css}"/>
                <display:column property="remainingAmount" title="مبلغ مانده" style="${css}"/>
                <display:column property="shomareMoshtari" title="شناسه پرداخت" style="${css}"/>
                <display:column property="credebitTypeFarsi" title="نوع" style="${css}"/>
                <display:column property="statusFarsi" title="وضعیت سند" style="${css}"/>
                <display:column property="vosoulStateFarsi" title="وضعیت وصول" style="${css}"/>
                <display:column property="vaziyateEtebarDesc" title="شرح وصول" style="${css}"/>
        </display:table>
    </div>
</div>

<table class="inputList"  style="width:100%; border:1px solid black;" >
    <tr>
        <td align="right">
            <label>* مقادیر شرح وصول عبارتند از:</label>
        </td>
    </tr>
    <tr>
        <td align="right">
            <label>- مبلغ مازاد: سيستم مبلغ کل و مبلغ مانده اعتبار مربوطه را به اندازه مبلغ واريزي افزايش داده و تاييد وصول شده است. ميتوانيد مانده اعتبار (اضافه پرداخت) را از ليست اعتبارات انتخاب و در سندي ديگر خرج نماييد.</label>
        </td>
    </tr>
    <%--<tr>--%>
        <%--<td align="right">--%>
            <%--<label>- مبلغ مساوي: تاييد وصول شده است.</label>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <tr>
        <td align="right">
            <label>- مبلغ کمتر: بدهي هاي مربوط به اين اعتبار از سند مربوطه خارج شده اند  و مبلغ کل و مبلغ مانده اعتبار نيز به اندازه ي مبلغ واريزي کاهش پيدا کرده و تاييد وصول شده است. اعتبار مربوطه در ليست اعتبارات است و بايد کليه بدهي هايي که در اين اسناد مشارکت داشته اند را دوباره با اعتبارات موجود سند بزنيد.</label>
        </td>
    </tr>
    <%--<tr>--%>
        <%--<td align="right">--%>
            <%--<label>- وصول تکراری: در صورتیکه چند پرداخت با یک شناسه پرداخت انجام شده باشد، مقدار مبلغ کل و مبلغ مانده به ازای پرداخت انجام شده، افزایش یافته است.</label>--%>
        <%--</td>--%>
    <%--</tr>--%>
</table>

