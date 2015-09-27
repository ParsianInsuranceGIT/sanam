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
    <display:table export="false" id="tblDarkhasts" clearStatus="true" keepStatus="false"
                   name="darkhastsVMPaginatedList" uid="row4" htmlId="tblListDarkhastha"
                   requestURI="?selectedTab=tabs-4&${pagingParams}" excludedParams="selectedTab"
                   partialList="true" size="${darkhastsVMPaginatedList.fullListSize}"
                   pagesize="${darkhastsVMPaginatedList.objectsPerPage}" style="width: 100%; margin: 0 auto;">
        <%--<display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.darkhatsthayeDarJaryanPageNumber%></display:setProperty>--%>
        <display:column title="نمایش" style="background:${row4.roleColor};">
            <a href="${row4.namayeshLink}" style="padding:7px; display:block;">نمایش</a>
        </display:column>
        <display:column title="ردیف" style="background:${row4.roleColor};">
            ${row4_rowNum+((darkhastsVMPaginatedList.pageNumber-1)*darkhastsVMPaginatedList.objectsPerPage)}
        </display:column>
        <display:column title="تاریخ درخواست" property="darkhastDate"
                        style="background:${row4.roleColor};"/>
        <display:column title="شماره بیمه نامه" property="shomareBimename"
                        style="background:${row4.roleColor};"/>
        <display:column title="کد و نام نماینده"  style="background:${row4.roleColor};">
            ${row4.namayandeCode}-${row4.namayandeName}
        </display:column>
        <display:column title="نوع درخواست" property="darkhastTypeFarsi"
                        style="background:${row4.roleColor};"/>
        <display:column title="بیمه گذار" style="background:${row4.roleColor};">
            ${row4.bimeGozarFirstName}&nbsp;${row4.bimeGozrLastName}
        </display:column>
        <display:column title="بیمه شده" style="background:${row4.roleColor};">
            ${row4.bimeShodeFirstName}&nbsp;${row4.bimeShodeLastName}
        </display:column>
        <display:column title="کارشناس"  style="background:${row4.roleColor};">
            ${row4.karshenasFirstName}&nbsp;${row4.karshenasLastName}
        </display:column>
        <display:column title="کارشناس خسارت" style="background:${row4.roleColor};">
            ${row4.karshenasKhesaratFirstName}&nbsp;${row4.karshenasKhesaratLastName}
        </display:column>
        <display:column title="کاربر ثبت کننده" style="background:${row4.roleColor};">
                ${row4.creatorFirstName}&nbsp;${row4.creatorLastName}
        </display:column>
        <display:column title="تاریخ آخرین تغییر وضعیت" property="transitionLogDate"
                        style="background:${row4.roleColor};"/>
        <display:column title="وضعیت" property="stateName" style="background:${row4.roleColor};"/>
        <display:column title="تاریخچه" style="background:${row4.roleColor};">
            <input type="button" onclick="window.location='${row4.tarikhcheLink}'" value="تاریخچه"/>
        </display:column>
    </display:table>
</div>

