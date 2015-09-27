<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${fishs}" var="fish" varStatus="i">
        <input type="hidden" name="pishnehad.id" id="pishidholder${i.index}" value="${pishnehad.id}"/>
        <input type="hidden" name="fish.id" id="fishidholder${i.index}" value="${fish.id}"/>
        <input type="hidden" name="fish.shomare" id="fishshomareholder${i.index}" value="${fish.shomare}"/>
        <input type="hidden" name="fish.bankName" id="fishbanknameholder${i.index}" value="${fish.bankName}"/>
        <input type="hidden" name="fish.kodeShobe" id="fishbankshobeholder${i.index}" value="${fish.kodeShobe}"/>
        <input type="hidden" name="fish.mablagh" id="fishmablaghholder${i.index}" value="${fish.mablagh}"/>
        <input type="hidden" name="fish.tarikh" id="fishtarikhholder${i.index}" value="${fish.tarikh}"/>
        <input type="hidden" name="fish.time" id="fishtimeholder${i.index}" value="${fish.time}"/>
        <input type="hidden" name="fish.credebit.daryafteFish.tozihat" id="fishtozihatholder${i.index}" value="${fish.credebit.daryafteFish.tozihat}"/>
        <input type="hidden" name="fish.credebit.daryafteFish.shomareSanadBank" id="fishsanadholder${i.index}" value="${fish.credebit.daryafteFish.shomareSanadBank}"/>


            <tr id="fishrow${i.index}">
                <td class="ui-widget-content">
                    ${(i.index)+1}

                </td>
                <td class="ui-widget-content">
                    <c:if test="${fish.found=='true'}">
                    <p class="td_search_final" style="color:#006400;">تایید</p>
                    </c:if>
                    <c:if test="${fish.found!='true'}">
                    <p class="td_search_final" style="color:red;">عدم تایید</p>
                    </c:if>

                </td>
                <td class="ui-widget-content">
                    ${fish.shomare}
                </td>
                <td class="ui-widget-content">
                    ${fish.bankName}
                </td>
                <td class="ui-widget-content">
                    ${fish.kodeShobe}
                </td>
                <td class="ui-widget-content">
                    ${fish.credebit.daryafteFish.tozihat}
                </td>
                <td class="ui-widget-content">
                    ${fish.credebit.daryafteFish.shomareSanadBank}
                </td>
                <td class="ui-widget-content">
                    ${fish.mablagh}
                </td>
                <td class="ui-widget-content">
                    ${fish.tarikh}
                </td>
                <td class="ui-widget-content">
                    ${fish.time}
                </td>
                <td id="taeed_final_result${i.index}" class="ui-widget-content">
                    <c:if test="${fish.found=='true'}">
                    <p style="color:#006400;">تایید شد</p>
                    </c:if>
                    <c:if test="${fish.found!='true'}">
                    <a href="javascript:void(0);" onclick="startSearching(${i.index});">جستجوی فیش</a>
                    </c:if>
                </td>
            </tr>
</c:forEach>