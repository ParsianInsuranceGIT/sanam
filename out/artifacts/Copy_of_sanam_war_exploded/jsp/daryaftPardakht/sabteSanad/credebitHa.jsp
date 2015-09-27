<%--
  Created by IntelliJ IDEA.
  User: Arron1
  Date: 8/17/11
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
[
<fmt-rt:bundle basename="com.bitarts.parsian.i18n.enumTypes_fa">
    <c:forEach var="credebit" items="${etebarCredebitList}" varStatus="i">
        <c:choose>
                <c:when test="${credebit.check != null}">
                    {id:'${credebit.id}',vosoulStateFarsi:'${credebit.vosoulStateFarsi}', createdDate:'${credebit.sarresidDate}', type:'${credebit.credebitTypeFarsi}', amount:'${credebit.amount}', shenaseMoshtari:'${credebit.shomareMoshtari}', bimename_serial:'${credebit.identifier}',amount_remaining:'${credebit.remainingAmount}', bimeGozar: '${credebit.rcptName}',shomare_credebit:'${credebit.shenaseCredebit}',idOfCheck:'${credebit.check.id}'}
                </c:when>
                <c:otherwise>
                    {id:'${credebit.id}',vosoulStateFarsi:'${credebit.vosoulStateFarsi}', createdDate:'${credebit.createdDate}', type:'${credebit.credebitTypeFarsi}', amount:'${credebit.amount}', shenaseMoshtari:'${credebit.shomareMoshtari}', bimename_serial:'${credebit.identifier}',amount_remaining:'${credebit.remainingAmount}', bimeGozar: '${credebit.rcptName}',shomare_credebit:'${credebit.shenaseCredebit}'}
                </c:otherwise>
        </c:choose>
        <c:if test="${i.index+1 != fn:length(etebarCredebitList)}">,</c:if>
    </c:forEach>
</fmt-rt:bundle>
]