<%--
  Created by IntelliJ IDEA.
  User: a.sabzechian
  Date: Sep 6, 2011
  Time: 5:53:55 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<html>
<head><title>لیست واگذاری</title></head>
<body>
<script>
    $(function(){
        $(".jtable tr").click(function(){
            $(this).children("td").toggleClass("ui-state-highlight");
        });
    });
</script>
<div style="overflow: auto;">
    <display:table export="true" id="vagozariListTbl" uid="row" name="vagozariPaginatedList"
                   sort="external" htmlId="vagozariListTbl"
                   size="${vagozariPaginatedList.fullListSize}"
                   pagesize="${vagozariPaginatedList.objectsPerPage}"
                   requestURI="" clearStatus="true" keepStatus="false"
                   excludedParams="" style="width: 100%; margin: 0 auto;">
        <c:choose>
            <c:when test="${sessionScope.daftar_id==1}">
                <c:set var="css" value=""/>
            </c:when>

            <c:otherwise>
                <c:set var="css" value="background:#ffffcc;"/>
            </c:otherwise>
        </c:choose>
        <display:column title="ردیف" style="${css}">${row_rowNum}</display:column>
        <display:column property="shomare" title="شماره" style="${css}"/>
        <display:column property="createDate" title="تاریخ" style="${css}"/>
        <display:column property="namayandeName" title="نام نماینده" style="${css}"/>
        <display:column property="vagozariPerCredebit" title="تعداد" style="${css}"/>
        <display:column style="${css}" title="عملیات">
            <input type="button" onclick="window.open('/fin/printCheckVagozarShode?credebitReport.id=${row.id}');" id="print_btn" value="چاپ لیست چک های واگذار شده" />
            <input type="button" onclick="window.open('/fin/printVagozariBeBank?credebitReport.id=${row.id}');" id="print_btn" value="چاپ فرم واگذاری بانک" />
            <input type="button" onclick="window.open('/fin/excelCheckVagozarshode?credebitReport.id=${row.id}');" id="print_btn" value="فايل واگذاري اسناد كلر"/>
        </display:column>
    </display:table>
</div>
</body>
</html>