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
    <display:table export="false" id="tbl_khesarat" clearStatus="true" keepStatus="false"
                   name="khesaratVMPaginatedList" uid="row" htmlId="tbl_khsrt"
                   requestURI="?selectedTab=tabs-6&${pagingParams}" excludedParams="selectedTab"
                   partialList="true" size="${khesaratVMPaginatedList.fullListSize}"
                   pagesize="${khesaratVMPaginatedList.objectsPerPage}" style="width: 100%; margin: 0 auto;">
        <%--<display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.allDarkhatsthayeDarJaryanPageNumber%>--%>
        <%--</display:setProperty>--%>
        <display:column title="ردیف" >
            ${row_rowNum+((khesaratVMPaginatedList.pageNumber-1)*khesaratVMPaginatedList.objectsPerPage)}
        </display:column>
        <display:column title="شماره پرونده" property="shomareParvande"/>
        <display:column title="تاریخ تشکیل پرونده" property="createdDate"/>
        <display:column title="وضعیت پرونده" property="stateName"/>
        <display:column title="کاربر تشکیل دهنده" >${row.userCreatorFirstName}&nbsp;&nbsp;${row.userCreatorLastName}</display:column>
        <display:column title="بیمه شده" >${row.bimeShodeFirstName}&nbsp;&nbsp;${row.bimeShodeLastName}</display:column>
        <display:column title="بیمه گذار" >${row.bimeGozarFirstName}&nbsp;&nbsp;${row.bimeGozarLastName}</display:column>
        <display:column title="شماره بیمه نامه" property="shomareBimename"/>
        <display:column title="نمایش" ><a target="_blank" href="/editDarkhastFormReadOnly?darkhastBazkharid.id=${row.id}">نمایش</a></display:column>

    </display:table>

    <%--<table class="jtable resultDets" width="900px" cellpadding="0" cellspacing="0"--%>
           <%--style="border-spacing:1px;margin:5px auto;" border="0">--%>
        <%--<thead>--%>
        <%--<s:if test="khesaratha == null || khesaratha.size == 0">--%>
            <%--<tr>--%>
                <%--<th colspan="5" class="empty" width="150px">اطلاعاتی یافت نشد</th>--%>
            <%--</tr>--%>
        <%--</s:if>--%>
        <%--<s:else>--%>
            <%--<tr dir="rtl">--%>
                <%--<th>ردیف</th>--%>
                <%--<th>شماره پرونده</th>--%>
                <%--<th>تاریخ تشکیل پرونده</th>--%>
                <%--<th>وضعیت پرونده</th>--%>
                <%--<th>کاربر تشکیل دهنده</th>--%>
                <%--<th>بیمه شده</th>--%>
                <%--<th>بیمه گذار</th>--%>
                <%--<th>شماره بیمه نامه</th>--%>
                <%--<th>عملیات</th>--%>
            <%--</tr>--%>
        <%--</s:else>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--<s:iterator value="khesaratha" id="row" status="stat">--%>
            <%--<tr dir="rtl">--%>
                <%--<td><s:property value="%{#stat.index+1}"/>--%>
                <%--</td>--%>
                <%--<td style="white-space: nowrap;"><s:property value="#row.shomareParvande"/>&nbsp;</td>--%>
                <%--<td><s:property value="#row.createdDate"/>&nbsp;</td>--%>
                <%--<td><s:property value="#row.state.stateName"/>&nbsp;</td>--%>
                <%--<td><s:property value="#row.userCreator.fullName"/>&nbsp;</td>--%>
                <%--<td><s:property value="#row.bimename.pishnehad.bimeShode.shakhs.fullName"/>&nbsp;</td>--%>
                <%--<td><s:property value="#row.bimename.pishnehad.bimeGozar.shakhs.fullName"/>&nbsp;</td>--%>
                <%--<td style="white-space: nowrap;"><s:property value="#row.bimename.pishnehad.bimename.shomare"/>&nbsp;</td>--%>
                <%--<td><input type="button" style="float:right;"--%>
                           <%--onclick="window.location='/showKhesarat?khesarat.id=<s:property--%>
                                   <%--value="#row.getId()"/>'"--%>
                           <%--value="نمایش"></td>--%>
            <%--</tr>--%>

        <%--</s:iterator>--%>
        <%--</tbody>--%>
    <%--</table>--%>
</div>

