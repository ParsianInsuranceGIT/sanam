<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
[
    <c:forEach var="sanad" items="${khateSanadListPaginated.list}" varStatus="i">
        {
            id:${sanad.id},
            amount:'${sanad.amount}',
            shMoshtariEtebar:'${sanad.etebarCredebit.shomareMoshtari == null? '-':sanad.etebarCredebit.shomareMoshtari}/${sanad.etebarCredebit.shenaseCredebit == null? '-':sanad.etebarCredebit.shenaseCredebit}',
            shMoshtariBedehi:'${sanad.bedehiCredebit.shomareMoshtari == null? '-':sanad.bedehiCredebit.shomareMoshtari}/${sanad.bedehiCredebit.shenaseCredebit == null? '-':sanad.bedehiCredebit.shenaseCredebit}',
            etebarAmount:'${sanad.etebarCredebit.remainingAmount}',
            etebarType: '${sanad.etebarCredebit.credebitTypeFarsi}',
            bedehiAmount:'${sanad.bedehiCredebit.remainingAmount}',
            bedehiType: '${sanad.bedehiCredebit.credebitTypeFarsi}'
        }
        <c:if test="${i.index+1 != fn:length(etebarCredebitListPaginated.list)}">,</c:if>
    </c:forEach>
]