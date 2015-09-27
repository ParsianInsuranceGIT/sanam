<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<display:table export="true" id="tblListGhests" uid="row" htmlId="tblListGhests"
                name="paginatedListGhests.list" partialList="true" clearStatus="true" keepStatus="false"
                style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
                size="${paginatedListGhests.fullListSize}" pagesize="${paginatedListGhests.objectsPerPage}">
    <display:column title="ردیف">${row_rowNum}</display:column>
    <display:column title="کارمزد قسط (ریال)" property="karmozdReal"/>
    <display:column title="کارمزد پرداخت شده (ریال)" property="karmozdPaid"/>
    <display:column title="تاریخ سر رسید" property="sarresidDate"></display:column>
    <display:column title="مقدار بدهی (ریال)" property="credebit.amount"></display:column>
    <display:column title="شماره بیمه نامه" property="ghestBandi.bimename.shomare" style="white-space: nowrap;"></display:column>
    <display:column title="شناسه بدهی" property="credebit.shenaseCredebit"></display:column>
    <display:column title="نماینده" property="ghestBandi.bimename.pishnehad.namayande.name"></display:column>
</display:table>

