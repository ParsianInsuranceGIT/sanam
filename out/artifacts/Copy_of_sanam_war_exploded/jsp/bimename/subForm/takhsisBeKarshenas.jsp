<%@ page import="com.bitarts.parsian.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<form></form>--%>
<form id="takhsiskarshenasformfordarkhast" name="form4takhsiskarshenas" action="/assignKarshenasToDarkhast" method="post">
    <c:if test="${darkhastBazkharid!=null}">
        <input type="hidden" name="darkhastBazkharid.id" value="<c:out value='${darkhastBazkharid.id}'/>"/>
    </c:if>
    <c:if test="${darkhastTaghirat!=null}">
        <input type="hidden" name="darkhastTaghirat.id" value="<c:out value='${darkhastTaghirat.id}'/>"/>
        <input type="hidden" name="darkhastTaghirat.niazBeAzmayesh" id="darkhat_taghirat_azmayesh" value="no"/>
    </c:if>
    <input type="hidden" id="takhsiskharshenastransidfordakhast" name="transitionId" value="1140"/>
    <input type="hidden" name="logmessage" id="takhsiskharshenastransidmsg" value="test"/>

    <select name="karshenasId" id="whichkarshenas">
        <s:if test="%{darkhastBazkharid!=null && (darkhastBazkharid.state.id==642 || darkhastBazkharid.state.id==645 || darkhastBazkharid.state.id==650)}">
            <c:if test="${karshenasKhesaratha!=null}">
                <c:forEach var="karshenas" items="${karshenasKhesaratha}" varStatus="i">
                <option value='<c:out value="${karshenas.id}"/>'><c:out value="${karshenas.firstName}"/> &nbsp;&nbsp;<c:out value="${karshenas.lastName}"/></option>
                </c:forEach>
            </c:if>
        </s:if>
        <s:else>
            <c:if test="${karshenasha!=null}">
                <c:forEach var="karshenas" items="${karshenasha}" varStatus="i">
                    <option value='<c:out value="${karshenas.id}"/>'><c:out value="${karshenas.firstName}"/>
                        &nbsp;&nbsp;<c:out value="${karshenas.lastName}"/></option>
                </c:forEach>
            </c:if>
        </s:else>
    </select>
    <input type="button" onclick="submitTheFormForTakhsis();" value="تایید"/>
</form>

<script type="text/javascript">
    function submitTheFormForTakhsis(){
        <c:if test="${darkhastBazkharid.darkhastType == 'BARDASHT_AZ_ANDOKHTE'}">
            <c:if test="${darkhastBazkharid.state.id==11020}">
                $("#takhsiskharshenastransidfordakhast").val(11003);
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==11050}">
                $("#takhsiskharshenastransidfordakhast").val(32324615);
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==11070}">
                $("#takhsiskharshenastransidfordakhast").val(32324616);
            </c:if>

        </c:if>
        <c:if test="${darkhastBazkharid.darkhastType == 'VAM'}">
            $("#takhsiskharshenastransidfordakhast").val(10003);
        </c:if>
        <c:if test="${darkhastBazkharid.darkhastType == 'BAZKHARID'}">
            <c:if test="${darkhastBazkharid.state.id==1020}">
                $("#takhsiskharshenastransidfordakhast").val(1140);
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==1100}">
                $("#takhsiskharshenastransidfordakhast").val(1240);
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==1300}">
                $("#takhsiskharshenastransidfordakhast").val(1450);
            </c:if>
        </c:if>
        <c:if test="${darkhastBazkharid.darkhastType == 'TASVIE_PISH_AZ_MOEDE_VAM'}">
            $("#takhsiskharshenastransidfordakhast").val(12004);
        </c:if>
        <c:if test="${darkhastBazkharid.darkhastType == 'KHESARAT'}">
//            $('#lognazarKhesarat').val($('#nazar_karshenas_khesarat').val());
//            $("#" + theForm).append(document.getElementById("lognazarKhesarat"));
            <c:if test="${darkhastBazkharid.state.id==642 || darkhastBazkharid.state.id==642}">
                $("#takhsiskharshenastransidfordakhast").val(620);
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==657}">
                $("#takhsiskharshenastransidfordakhast").val(681);
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==645}">
                $("#takhsiskharshenastransidfordakhast").val(630);
            </c:if>
        </c:if>
        <c:if test="${darkhastTaghirat != null}">
            <c:if test="${darkhastTaghirat.state.id == 9030}">
                $("#takhsiskharshenastransidfordakhast").val(9008);
            </c:if>
            <c:if test="${darkhastTaghirat.state.id == 9070}">
                $("#takhsiskharshenastransidfordakhast").val(9017);
            </c:if>
            <c:if test="${darkhastTaghirat.state.id == 9050}">
                $("#takhsiskharshenastransidfordakhast").val(9011);
            </c:if>
            <c:if test="${darkhastTaghirat.state.id == 9140}">
            $("#takhsiskharshenastransidfordakhast").val(9054);
            </c:if>
            <c:if test="${darkhastTaghirat.state.id == 9160}">
            $("#takhsiskharshenastransidfordakhast").val(9055);
            </c:if>
        </c:if>
        openDialogueForDarkhast("takhsiskharshenastransidmsg","takhsiskarshenasformfordarkhast");
    }
</script>