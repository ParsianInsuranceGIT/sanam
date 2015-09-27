<%@ page import="com.bitarts.parsian.model.constantItems.City" %>
<%@include file="/jsp/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
[
<c:forEach var="row" items="${citySearchResult}" varStatus="i">
    <c:if test="${i.index > 0}">,</c:if>
    {"cityId":"${row.city.cityId}","label":"${row.title}","value":"${row.title}","cityName":"${row.city.cityName}","parentId":"${row.parent.cityId}","parentName":"${row.parent.cityName}","cityIdForPid":"${row.city.cityIdForPid}","parentIdForPid":"${row.parent.cityIdForPid}"}
</c:forEach>
]