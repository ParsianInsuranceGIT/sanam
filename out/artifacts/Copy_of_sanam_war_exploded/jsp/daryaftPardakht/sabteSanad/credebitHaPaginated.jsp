<%@ page import="com.bitarts.common.displaytag.PaginatedListImpl" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="logicaldoc.doc.List" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>

<%
//    session.removeAttribute("fullListSizeCrdbit");
//    Integer fullListSize=((PaginatedListImpl) request.getAttribute("etebarCredebitListPaginated")).getFullListSize();
//    session.setAttribute("fullListSizeCrdbit",fullListSize);
//    java.util.List listAtt = (java.util.List) session.getAttributeNames();
//    Enumeration enumeration=session.getAttributeNames();

//    boolean count=false;
//    for(String attName: (java.util.List<String>) Collections.list(enumeration))
//    {
//        if(attName=="fullListSizeCrdbit") {count=true;break;}
//    }
//    if (count==true)
%>
fLSize:'${etebarCredebitListPaginated.fullListSize}',
<fmt-rt:bundle basename="com.bitarts.parsian.i18n.enumTypes_fa">
    [
    <c:forEach var="credebit" items="${etebarCredebitListPaginated.list}" varStatus="i">
        {
        id:${credebit.id},
        serial:<c:if test="${credebit.daryafteCheck != null}">'${credebit.daryafteCheck.serial}'</c:if><c:if test="${credebit.daryafteCheck == null}">''</c:if>,
        bazaryab:<c:if test="${credebit.bazarYabSanam != null}">'${credebit.bazarYabSanam.name}'</c:if><c:if test="${credebit.bazarYabSanam == null}">''</c:if>,
        createdDate:'${credebit.createdDate}',
        namayandeName:<c:if test="${credebit.namayande != null}">'${credebit.namayandeName}'</c:if><c:if test="${credebit.namayande == null}">''</c:if>,
        sarresidDate:'${credebit.sarresidDate}',
        type:'${credebit.credebitTypeFarsi}',
        amount:'${credebit.amount}',
        shenaseMoshtari:'${credebit.shomareMoshtari}',
        bimename_serial:'${credebit.identifier}',
        amount_remaining:'${credebit.remainingAmount}',
        bimeGozar: '${credebit.rcptName}',
        shomare_credebit:'${credebit.shenaseCredebit}' ,
        vosoulStateFarsi:'${credebit.vosoulStateFarsi}'

        <c:if test="${credebit.check != null}">,idOfCheck:'${credebit.check.id}'</c:if>
        }
        <c:if test="${i.index+1 != fn:length(etebarCredebitListPaginated.list)}">,</c:if>
    </c:forEach>
    ]
</fmt-rt:bundle>