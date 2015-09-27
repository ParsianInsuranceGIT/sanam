<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page import="com.bitarts.parsian.Core.ConstantPaging" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="../../css/styles.css"/>
<%--<script type="text/javascript" src="/js/common.js"></script>--%>
<script type="text/javascript">
    $(".jtable th").each(function ()
    {
        $(this).addClass("ui-state-default");
    });
    $(".jtable td").each(function ()
    {
        $(this).addClass("ui-widget-content");
    });
    $(".jtable tr").hover(function ()
    {
        //        $(this).children("td").addClass("ui-state-hover");
    }, function ()
    {
        //        $(this).children("td").removeClass("ui-state-hover");
    });
    $("input,textarea").each(function ()
    {
        $(this).addClass("ui-state-default  ui-corner-all");
    });
    $("select").each(function ()
    {
        $(this).addClass("ui-state-default  ui-corner-all");
    });
    $(".help, .comment").tipsy({gravity: 's'});
    $("form, .vld").validationEngine({promptPosition: "topLeft"});
    $('.dblRadio').buttonset();
    $(".tipsi").tipsy({gravity: 's'});


    $(".digitSeparators").each(function ()
    {
        $(this).keyup();
    });
    $(".digitSeparatorsManfi").each(function ()
    {
        $(this).keyup();
    });
</script>
<display:table export="false" id="tblBlackList" uid="row" htmlId="tblBlackList"
               name="PaginatedListSubset.list" partialList="true" clearStatus="true"
               keepStatus="false"
               style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
               size="${PaginatedListSubset.fullListSize}"
               pagesize="${PaginatedListSubset.objectsPerPage}">
    <display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.PAGE%></display:setProperty>

    <display:column title="جزئيات">
        <input type="button" value="..." onclick=""/>
    </display:column>
    <display:column property="contractDateTo" title="تاريخ پايان قرارداد"/>
    <display:column property="contractDateFrom" title="تاريخ شروع قرارداد"/>
    <display:column property="subset.kodeNamayandeKargozar" title="کد نماينده کارگزار"/>
    <display:column title="نام نمايندگي" property="subset.name" style="white-space: nowrap;"/>
    <display:column title="رديف">${row_rowNum}</display:column>
</display:table>
