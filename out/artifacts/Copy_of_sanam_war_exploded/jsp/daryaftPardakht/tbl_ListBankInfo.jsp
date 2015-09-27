<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<display:table export="true"  id="bankInfoPaginatedList" uid="row" name="bankInfoPaginatedList"
               sort="external" htmlId="bankInfoPaginatedList"
               partialList="true"
               size="${bankInfoPaginatedList.fullListSize}"
               pagesize="${bankInfoPaginatedList.objectsPerPage}"
               requestURI="" clearStatus="true" keepStatus="false"  cellpadding="10"
               excludedParams="" style=" width: 100%; margin: 0 auto;" >

    <display:column property="taarikh" title="تاریخ واریز به بانک" style="border-width:thin; border-color:#4297d7; border-style:solid"/>
    <display:column property="time" title="زمان واریز به بانک" style="border-width:thin; border-color:#4297d7; border-style:solid"/>
    <display:column property="codeMoshtari" title="شناسه پرداخت" style="border-width:thin; border-color:#4297d7; border-style:solid"/>
    <display:column property="createdDate" title="تاریخ ایجاد در سیستم" style="border-width:thin; border-color:#4297d7; border-style:solid"/>
    <display:column property="createdTime" title="زمان ایجاد در سیستم" style="border-width:thin; border-color:#4297d7; border-style:solid"/>
    <display:column property="vaziyateEtebarDesc" title="وضعیت" style="border-width:thin; border-color:#4297d7; border-style:solid"/>
    <display:column property="bardasht" title="مبلغ استفاده شده" style="border-width:thin; border-color:#4297d7; border-style:solid"/>
    <display:column property="mablagh" title="مبلغ" style="border-width:thin; border-color:#4297d7; border-style:solid"/>

</display:table>