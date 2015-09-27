<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--SUCCESS-->

<c:if test="${isHalateElhaghie}">
    <c:if test="${biShodeId != null}">
        <input type="hidden" id="newShakhsBimeShode" name="newShakhsBimeShodeId" value="${bimeShoodeShakhs.id}"/>
        <input type="hidden" id="namonamekhanevadegishakhs_Shode" value="${bimeShoodeShakhs.name}&nbsp;${bimeShoodeShakhs.nameKhanevadegi}"/>
    </c:if>
    <c:if test="${bimeGozarShakhs != null}">
        <input type="hidden" id="newShakhsBimeGozar" name="newShakhsBimeGozarId" value="${bimeGozarShakhs.id}"/>
        <input type="hidden" id="namonamekhanevadegishakhs_Gozar" value="${bimeGozarShakhs.name}&nbsp;${bimeGozarShakhs.nameKhanevadegi}"/>
    </c:if>
</c:if>
<c:if test="${!isHalateElhaghie}">
    <input type="hidden" id="namonamekhanevadegishakhs" value="${shakhs.name}&nbsp;${shakhs.nameKhanevadegi}"/>
</c:if>
<input type="hidden" id="editsuccessmessage" value="ویرایش با موفقیت انجام شد"/>