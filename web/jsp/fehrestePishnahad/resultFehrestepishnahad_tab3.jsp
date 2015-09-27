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
    <display:table export="true" id="tblListSemats" uid="row3" htmlId="tblListBimenameha"
                   name="bimenameVMPaginatedList" clearStatus="true" keepStatus="false"
                   requestURI="?selectedTab=tabs-3&${pagingParams}"
                   excludedParams="selectedTab"
                   partialList="true"
                   size="${bimenameVMPaginatedList.fullListSize}"
                   pagesize="${bimenameVMPaginatedList.objectsPerPage}"
                   style="width: 100%; margin: 0 auto;">
        <display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.bimenamehaPageNumber%></display:setProperty>
        <display:column media="html" title="نمایش">
            <a target="_blank" href="/editShowFormReadOnly?pishnehad.id=${row3.pishnehadId}&tabsName=bimename"
               style="padding:7px; display:block;">نمايش</a>
        </display:column>
        <display:column
                title="ردیف">${row3_rowNum+((bimenameVMPaginatedList.pageNumber-1)*bimenameVMPaginatedList.objectsPerPage)}</display:column>
        <display:column property="shomareBimename" title="شماره بیمه نامه"
                        style="white-space: nowrap;"/>
        <display:column property="tarikhSodour" title="تاریخ صدور"/>
        <display:column property="tarikhEngheza" title="تاریخ انقضا"/>
        <display:column property="vaziatField" title="وضعیت"/>
        <display:column
                title="نام بیمه گذار">${row3.bimeGozarFirstName} ${row3.bimeGozarLastName}</display:column>
        <display:column
                title="نام بیمه شده">${row3.bimeShodeFirstName} ${row3.bimeShodeLastName}</display:column>
        <display:column
                title="نام و کد نماینده">${row3.namayandeName}${row3.namayandeCode}</display:column>
        <display:column property="vahedSodurName" title="واحد صدور"/>
        <display:column
                title="کارشناس صدور">${row3.karshenasFirstName} ${row3.karshenasLastName}</display:column>
        <display:column property="karshenasPersonalCode" title="کد کارشناس"/>
        <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN,ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE,ROLE_MOSHAHEDE_BIMENAME">
            <display:column title="عملیات">
                <select>
                    <c:if test="${row3.stateId == 500}">
                        <option onclick="" value="0">انتخاب عملیات</option>
                        <sec:authorize ifNotGranted="ROLE_KARSHENAS_KHESARAT,ROLE_RAEIS_KHESARAT,ROLE_KARSHENAS_MASOUL_KHESARAT">
                            <option onclick="window.location = '/prepareToMakeRequest?bimename.id=${row3.bimenameId}'"
                                    value="1">درخواست بهره مندی از منافع
                            </option>
                            <option onclick="window.location = '/editElhaghie?pishnehad.id=${row3.pishnehadId}'"
                                    value="2">درخواست الحاقیه تغییرات
                            </option>
                        </sec:authorize>
                        <%--<sec:authorize ifAnyGranted="ROLE_NAMAYANDE,ROLE_KARSHENAS_KHESARAT">--%>
                            <%--<option onclick="window.location = '/darkhastKhesarat?selectedTab=tabs_36&pishnehad.id=${row3.pishnehadId}'"--%>
                                    <%--value="3">ثبت خسارت--%>
                            <%--</option>--%>
                        <%--</sec:authorize>--%>
                        <sec:authorize
                                ifAnyGranted="ROLE_TAGHIR_CODE_DAEM,ROLE_PAS_KARSHENAS_MASOUL,ROLE_PAS_KARSHENAS, ROLE_PAS_RAEIS">
                            <option onclick="window.location = '/darkhastTaghirKod?pishnehad.id=${row3.pishnehadId}'"
                                    value="4">در خواست تغییر کد نمایندگی
                            </option>
                        </sec:authorize>
                        <sec:authorize
                                ifAnyGranted="ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_PAS_KARSHENAS_MASOUL,ROLE_PAS_KARSHENAS,ROLE_PAS_RAEIS">
                            <option onclick="window.location = '/darkhastTozih?pishnehad.id=${row3.pishnehadId}'"
                                    value="5">
                                الحاقیه توضیح
                            </option>
                        </sec:authorize>
                    </c:if>
                    <c:if test="${row3.stateId != 500}">
                        <option>-</option>
                    </c:if>
                </select>
                <input type="hidden" id="bimenameholder${row3_rowNum}"
                       value="<s:property value='#row3.getBimenameId()'/>"/>
            </display:column>
        </sec:authorize>
    </display:table>
</div>

