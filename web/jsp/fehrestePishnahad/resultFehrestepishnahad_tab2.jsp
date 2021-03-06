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



<div id="allofem">
    <display:table export="true" id="tableforviewid" uid="rpViewResult" name="allPishnehadsVMPaginatedList"
                   sort="external" partialList="true" htmlId="tblforviewid"
                   size="${allPishnehadsVMPaginatedList.fullListSize}"
                   pagesize="${allPishnehadsVMPaginatedList.objectsPerPage}"
                   requestURI="?selectedTab=tabs-2&${pagingParams}"
                   excludedParams="selectedTab" style="width: 100%; margin: 0 auto;">
        <display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.viewReportResultPageNumber%></display:setProperty>
        <%--<display:setProperty name="pagination.pagenumber.param" value="page"/>--%>
        <display:column title="" style="background:${rpViewResult.roleColor};"
                        media="html">
            <c:if test="${rpViewResult.pishpardakhtOK}"><img src="/img/dollar.png"/></c:if>
        </display:column>
        <display:column title="" style="background:${rpViewResult.roleColor};"
                        media="html">
            <a target="_blank"
                   href="/editShowFormReadOnly?pishnehad.id=${rpViewResult.id}">نمایش</a>
        </display:column>
        <display:column title="ردیف" style="background:${rpViewResult.roleColor}; white-space: nowrap;">
            ${rpViewResult_rowNum+((allPishnehadsVMPaginatedList.pageNumber-1)*allPishnehadsVMPaginatedList.objectsPerPage)}
        </display:column>
        <%--<display:column property="bimename.shomare" title="شماره بیمه نامه"></display:column>--%>
        <display:column property="radif" title="کد رهگیری"
                        style="background:${rpViewResult.roleColor};"/>
        <display:column property="namayandeName" title="نام نماینده/کارگزار"
                        style="background:${rpViewResult.roleColor};"/>
        <display:column title="کارشناس" style="background:${rpViewResult.roleColor};">
            ${rpViewResult.karshenasFirstName}&nbsp;${rpViewResult.karshenasLastName}
        </display:column>
        <display:column property="createdDate" title="تاریخ ایجاد"
                        style="background:${rpViewResult.roleColor};"/>
        <display:column property="stateName" title="وضعیت"
                        style="background:${rpViewResult.roleColor};"/>
        <display:column title="نام بیمه گذار"
                        style="background:${rpViewResult.roleColor};">
            ${rpViewResult.bimeGozarFirstName}&nbsp;${rpViewResult.bimeGozarLastName}
        </display:column>
        <display:column title="نام بیمه شده"
                        style="background:${rpViewResult.roleColor};">
            ${rpViewResult.bimeShodeFirstName}&nbsp;${rpViewResult.bimeShodeLastName}
        </display:column>
        <display:column title="" style="background:${rpViewResult.roleColor};"
                        media="html">
            <input type="button"
                   onclick="window.location='/showTransitionLog?pishnehadId=${rpViewResult.id}'"
                   value="تاریخچه"/>
        </display:column>
    </display:table>
</div>
