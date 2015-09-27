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
    <% if (((Boolean) request.getAttribute("search")) != null)
    {
        if (((Boolean) request.getAttribute("search")) == true)
        {%>

    <display:table export="false" id="tblDarkhasts" clearStatus="true" keepStatus="false"
                   name="alldarkhastsPgList" uid="row" htmlId="tblListAlldarkhasts"
                   requestURI="?selectedTab=tabs-8&${pagingParams}" excludedParams="selectedTab"
                   partialList="true" size="${alldarkhastsPgList.fullListSize}"
                   pagesize="${alldarkhastsPgList.objectsPerPage}" style="width: 100%; margin: 0 auto;">
        <display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.allDarkhatsthayeDarJaryanPageNumber%></display:setProperty>
        <display:column title="نمایش" style="background:${row.state.peygirKonande.roleColor};">
            <a target="_blank" href="${row.namayeshLinkTabsAll}" style="padding:7px; display:block;">نمایش</a>
        </display:column>
        <display:column title="ردیف" style="background:${row.state.peygirKonande.roleColor};">
            ${row_rowNum+((alldarkhastsPgList.pageNumber-1)*alldarkhastsPgList.objectsPerPage)}
        </display:column>
        <display:column title="تاریخ درخواست" property="darkhastDate"
                        style="background:${row.state.peygirKonande.roleColor};"/>
        <display:column title="شماره بیمه نامه" property="shomareBimename"
                        style="background:${row.state.peygirKonande.roleColor};"/>
        <display:column title="کد و نام نمایندگی" property="name_KodeNamayandegi"
                        style="background:${row.state.peygirKonande.roleColor};"/>
        <display:column title="نوع درخواست" property="darkhastTypeFarsi"
                        style="background:${row.state.peygirKonande.roleColor};"/>
        <display:column title="بیمه گذار" property="bimegozarFullName"
                        style="background:${row.state.peygirKonande.roleColor};"/>
        <display:column title="بیمه شده" property="bimeshodeFullName"
                        style="background:${row.state.peygirKonande.roleColor};"/>
        <display:column title="کارشناس پس از صدور" property="karshenasFullName"
                        style="background:${row.state.peygirKonande.roleColor};"/>
        <display:column title="کارشناس خسارت" property="karshenasKhesaratFullName"
                        style="background:${row.state.peygirKonande.roleColor};"/>
        <display:column title="کاربر ثبت کننده" style="background:${row.state.peygirKonande.roleColor};">
            <c:if test="${row.darkhastBazkharid!=null}">
                ${row.darkhastBazkharid.creator.fullName}
            </c:if>
            <c:if test="${row.darkhastTaghirat!=null}">
                ${row.darkhastTaghirat.creator.fullName}
            </c:if>
        </display:column>
        <display:column title="تاریخ آخرین تغییر وضعیت" property="transitionLogDate"
                        style="background:${row.state.peygirKonande.roleColor};"/>
        <display:column title="وضعیت" property="state.stateName"
                        style="background:${row.state.peygirKonande.roleColor};"/>
        <display:column title="تاریخچه" style="background:${row.state.peygirKonande.roleColor};">
            <input type="button" onclick="window.location='${row.tarikhcheLink}'" value="تاریخچه"/>
        </display:column>
    </display:table>

    <%
        }
        if (((Boolean) request.getAttribute("search")) == false)
        {
    %>
    <display:table export="false" id="tblDarkhasts" clearStatus="true" keepStatus="false"
                   name="darkhastsVMPaginatedList" uid="row4" htmlId="tblListDarkhastha"
                   requestURI="?selectedTab=tabs-8&${pagingParams}" excludedParams="selectedTab"
                   partialList="true" size="${darkhastsVMPaginatedList.fullListSize}"
                   pagesize="${darkhastsVMPaginatedList.objectsPerPage}" style="width: 100%; margin: 0 auto;">
        <display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.allDarkhatsthayeDarJaryanPageNumber%></display:setProperty>
        <display:column title="نمایش" style="background:${row4.roleColor};">
            <a href="${row4.namayeshLinkTabsAll}" style="padding:7px; display:block;">نمایش</a>
        </display:column>
        <display:column title="ردیف" style="background:${row4.roleColor};">
            ${row4_rowNum+((darkhastsVMPaginatedList.pageNumber-1)*darkhastsVMPaginatedList.objectsPerPage)}
        </display:column>
        <display:column title="تاریخ درخواست" property="darkhastDate"
                        style="background:${row4.roleColor};"/>
        <display:column title="شماره بیمه نامه" property="shomareBimename"
                        style="background:${row4.roleColor};"/>
        <display:column title="کد و نام نماینده" style="background:${row4.roleColor};">
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
        <display:column title="کارشناس" style="background:${row4.roleColor};">
            ${row4.karshenasFirstName}&nbsp;${row4.karshenasLastName}
        </display:column>
        <display:column title="کارشناس خسارت" style="background:${row4.roleColor};">
            ${row4.karshenasFirstName}&nbsp;${row4.karshenasLastName}
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

    <%
            }
        }
    %>
</div>

