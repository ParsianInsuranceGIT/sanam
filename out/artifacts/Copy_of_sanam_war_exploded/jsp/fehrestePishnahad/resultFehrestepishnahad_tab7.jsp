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
    <sec:authorize ifAllGranted="ROLE_JAMI_KHAS">
        <div style="text-align:right;">
            <input type="button"
                   onclick="window.location='/addGharardad'"
                   value="ایجاد قرارداد جدید">
        </div>
    </sec:authorize>
    <display:table export="true" id="tableforviewid" uid="gharardadha" name="sessionScope.gharardadha"
                   sort="external" partialList="true" htmlId="tblforviewid"
                   size="${sessionScope.gharardadha.fullListSize}"
                   pagesize="${sessionScope.gharardadha.objectsPerPage}"
                   requestURI="?selectedTab=tabs-7&${pagingParams}" clearStatus="true" keepStatus="false"
                   excludedParams="selectedTab" style="width: 100%; margin: 0 auto;">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        %>
        <display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.gharardadhaPageNumber%></display:setProperty>
        <display:column title="" style="">
            <c:if test="${(fn:length(gharardadha.pishnehadList)-fn:length(gharardadha.bimenameList)) == 0}"><img src="/img/ok.gif"/></c:if>
        </display:column>
        <display:column title="" style="">
            <input type="button" onclick="window.location='/showGharardad?gharardad.id=${gharardadha.id}'"
                   value="نمایش">
        </display:column>
        <%--<display:setProperty name="pagination.pagenumber.param" value="pageNum"/>--%>
        <display:column title="ردیف" style="">
            ${gharardadha_rowNum+((sessionScope.gharardadha.pageNumber-1)*sessionScope.gharardadha.objectsPerPage)}
        </display:column>
        <display:column property="shomare" title="شماره قرارداد" style="white-space: nowrap;"/>
        <display:column property="createdDate" title="تاريخ ايجاد" style=""/>
        <display:column property="namayande.name" title="نام نماینده" style=""/>
        <%--<display:column property="namayande.kodeNamayandeKargozar" title="کد نماینده" style=""/>--%>
        <display:column property="ostan.cityName" title="استان محل شرکت" style=""/>
        <%--<display:column property="shahr.cityName" title="شهر محل شرکت" style=""/>--%>
        <display:column property="nameSherkat" title="نام شرکت" style=""/>

        <display:column property="shomareSabt" title="شماره ثبت" style=""/>
        <display:column title="تعداد پیشنهاد ها"
                        style="">${fn:length(gharardadha.pishnehadList)-fn:length(gharardadha.bimenameList)}</display:column>
        <display:column title="تعداد بیمه نامه"
                        style="">${fn:length(gharardadha.bimenameList)}</display:column>

        <display:column property="userCreator.fullName" title="کاربر ثبت کننده قرارداد" style=""/>
    </display:table>
</div>

