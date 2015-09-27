<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.bimename.Gharardad" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.Core.ConstantPaging" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ include file="/jsp/taglibs.jsp" %>

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
    //    $("form, .vld").validationEngine({promptPosition:"topLeft"});
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

<div style="overflow:auto;">
     <%
        if(1==1)
        {
            int i=0;
        }
     %>
    <display:table export="true" id="tableBahreMandi"  name="bahreMandiVMPaginatedList" uid="vams"
                   sort="external" partialList="true" htmlId="tableBahreMandi"
                   size="${bahreMandiVMPaginatedList.fullListSize}"
                   pagesize="${bahreMandiVMPaginatedList.objectsPerPage}"
                   requestURI="?selectedTab=tabs-9&${pagingParams}" clearStatus="true" keepStatus="false"
                   excludedParams="selectedTab" style="width: 100%; margin: 0 auto;">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        %>
        <display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.vamResultPageNumber%>
        </display:setProperty>
        <display:column media="html" title="نمایش">
            <a target="_blank" href="/editDarkhastFormReadOnly?darkhastBazkharid.id=${vams.id}&type=tabs-103">نمایش</a>
        </display:column>
        <display:column title="ردیف" style="">
            ${vams_rowNum+((bahreMandiVMPaginatedList.pageNumber-1)*bahreMandiVMPaginatedList.objectsPerPage)}
        </display:column>
        <display:column property="shomareBimename" title="شماره بیمه نامه" style="white-space: nowrap;"/>
        <display:column property="darkhstTypeFarsi" title="نوع بهره مندی" style="white-space: nowrap;"/>
        <display:column property="shomareBahreMandi" title="شماره نوع بهره مندی (ریال)" style=""/>
        <display:column property="namayandeName" title="نام نماینده" style=""/>
        <display:column property="namayandeCode" title="کد نماینده" style=""/>
        <display:column title="بیمه گذار" style="">${vams.bimegozarFirstName} ${vams.bimegozarLastName}</display:column>
        <display:column property="mablaghBahreMandi" title="مبلغ نوع بهره مندی (ریال)" style=""/>
        <display:column media="html" title="صورت وضعیت اقساط وام">
            <c:if test="${vams.darkhastType=='VAM'}">
                <input type="button"  onclick="window.open('/printSuratVaziatGhestVam?darkhastBazkharid.id=${vams.id}')" value="..."/>
            </c:if>
        </display:column>
    </display:table>
</div>

