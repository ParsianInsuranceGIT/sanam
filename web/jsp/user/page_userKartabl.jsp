<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/jsp/taglibs.jsp" %>
<head>
    <title>صفحه اصلی</title>
</head>
<s:actionerror/>
<s:actionmessage/>
<div align="center">
    <%--<s:if test="%{bimenameList.size()>0}">--%>
        <display:table name="bimenamePaginatedList" uid="res">
            <display:column title="ردیف">${res_rowNum}</display:column>
            <display:column title="نحوه مشاهده"><input type="button" value="مشاهده"
                                                       onclick="window.location='/editShowFormReadOnly?pishnehad.id=${res.pishnehad.id}'"></display:column>
            <display:column title="شماره بیمه نامه" property="shomare"/>
            <display:column title="تاریخ صدور" property="tarikhSodour"/>
            <display:column title="وضعیت بیمه نامه" property="state.stateName"/>
            <display:column title="بیمه شده" property="pishnehad.bimeShode.shakhs.fullName"/>
            <display:column title="واحد صدور" property="pishnehad.namayande.vahedSodur.name"/>
        </display:table>
    <%--</s:if>--%>
</div>