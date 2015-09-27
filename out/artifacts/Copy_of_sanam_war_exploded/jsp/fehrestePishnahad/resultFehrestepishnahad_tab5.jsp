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
    <display:table export="false" id="tblElhaghiye" clearStatus="true" keepStatus="false"
                   name="elhaghiyeha.list" uid="row5" htmlId="tblListElhaghiye"
                   requestURI="?selectedTab=tabs-5&${pagingParams}" excludedParams="selectedTab"
                   partialList="true" size="${elhaghiyeha.fullListSize}"
                   pagesize="${elhaghiyeha.objectsPerPage}" style="width: 100%; margin: 0 auto;">
        <%--<display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.elhaghiyehaPageNumber%></display:setProperty>--%>
        <display:column title="" media="html">
            <input type="button"
                   onclick="window.location='/showElhaghie?elhaghie.id=${row5.id}'"
                   value="نمایش">
        </display:column>
        <display:column title="ردیف">
            ${row5_rowNum+((elhaghiyeha.pageNumber-1)*elhaghiyeha.objectsPerPage)}
        </display:column>
        <display:column title="شماره الحاقیه" property="shomareElhaghiye"/>
        <display:column title="تاریخ اثـر" property="tarikhAsar"/>
        <display:column title="تاریخ درخواست" property="darkhast.darkhastDate"/>
        <display:column title="وضعیت" property="state.stateName"/>
        <display:column title="نوع الحاقیه" property="elhaghieTypeFarsi"/>
    </display:table>
</div>

