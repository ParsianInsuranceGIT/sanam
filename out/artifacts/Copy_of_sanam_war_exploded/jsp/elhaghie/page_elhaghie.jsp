<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/jsp/taglibs.jsp" %>
<%@ taglib prefix="function" uri="http://tagutils" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="../../css/pishnahadeBimeOmreEnferadi.css"/>
    <script type="text/javascript" src="/jsp/bimename/darkhastBimenameOmreEnferadi.js"></script>
    <script type="text/javascript" src="/jsp/pishnahad/pishnahadeBimeOmreEnferadi.js"></script>
    <title>سیستم درخواست</title>
</head>
<body>
<p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
    <label style="float:right;">وضعیت درخواست:${darkhastBazkharid.state.stateName}</label>
    <label style="float:right;margin-right:85px;">شماره بيمه‌نامه: ${bimename.shomare}</label>
    <label style="float:right;margin-right:85px;">نام
        بيمه‌گذار:${bimename.pishnehad.bimeGozar.shakhs.name} ${bimename.pishnehad.bimeGozar.shakhs.nameKhanevadegi}</label>
    <label style="float:left;">وضعیت بیمه نامه:${bimename.state.stateName}</label>

</p>
<c:set var="pishnehad" value="${bimename.pishnehad}"/>
<c:set var="estelam" value="${bimename.pishnehad.estelam}"/>


<p style="direction:rtl;text-align:right;">
    <%--در این بخش شما می توانید درخواست بازخرید بیمه نامه خود را ایجاد نمایید. لطفا با دقت مواردی که از شما خواسته شده است را پاسخ دهید.--%>
</p>

<div id="tabcontainer">
    <div id="tabs">
        <c:if test="${darkhastBazkharid!=null || darkhastTaghirat!=null}">
            <a href='javascript:void(0);' type="button" id="tab_100" class="tabsbtn"> مشخصات بیمه گذار </a>
            <a href='javascript:void(0);' type="button" id="tab_101" class="tabsbtn"> مشخصات بیمه شده </a>
            <a href='javascript:void(0);' type="button" id="tab_102" class="tabsbtn">مشخصات بیمه نامه</a>
            <a href='javascript:void(0);' type="button" id="tab_103" class="tabsbtn">وضعیت سلامتی بیمه شده</a>
            <a href='javascript:void(0);' type="button" id="tab_104" class="tabsbtn">وضعیت سلامتی خانواده بیمه شده</a>
            <a href='javascript:void(0);' type="button" id="tab_105" class="tabsbtn">سؤالات عمومی از بیمه شده</a>
            <a href='javascript:void(0);' type="button" id="tab_106" class="tabsbtn">الحاقیه</a>
        </c:if>
        <c:if test="${(darkhastBazkharid.darkhastType == DarkhastType.BAZKHARID || darkhastBazkharid.darkhastType == 'BAZKHARID') && (bimenameIsMafqud == 'on' || darkhastBazkharid.bimenameIsMafqud == 'on')}">
            <a href='javascript:void(0);' type="button" id="tab_15" class="tabsbtn specTabsbtn">اعلام مفقودی بیمه
                نامه</a>
        </c:if>
        <s:if test="%{darkhastBazkharid!=null && darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')}">
            <a href='javascript:void(0);' type="button" id="tab_1" class="tabsbtn activesubmit">درخواست تغییر کد
                نمایندگی</a>
        </s:if>
        <s:elseif test="%{darkhastBazkharid!=null && darkhastBazkharid.darkhastType.name().equals('TOZIH')}">
            <a href='javascript:void(0);' type="button" id="tab_1" class="tabsbtn activesubmit">درخواست توضیح</a>
        </s:elseif>
        <s:elseif test="%{darkhastTaghirat!=null}">
            <a href='javascript:void(0);' type="button" id="tab_1" class="tabsbtn activesubmit">درخواست تغییرات</a>
            <a href='javascript:void(0);' type="button" id="tab_3" class="tabsbtn">تغییر قسط ها</a>
        </s:elseif>
        <s:else>
            <a href='javascript:void(0);' type="button" id="tab_1" class="tabsbtn activesubmit">درخواست بهره مندی از
                منافع</a>
        </s:else>
        <%--<c:if test="${darkhastBazkharid == null}">--%>
        <%--<a href='javascript:void(0);' type="button" id="tab_2" class="tabsbtn specTabsbtn">ضمائم</a>--%>
        <%--</c:if>--%>
        <%--<c:if test="${darkhastBazkharid.state.id <= 10000}">--%>
        <s:if test="%{darkhastBazkharid!=null && (darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')||darkhastBazkharid.darkhastType.name().equals('TOZIH'))}"></s:if>
        <s:else>
            <c:if test="${darkhastBazkharid!=null || darkhastTaghirat!=null}">
                <a href='javascript:void(0);' type="button" id="tab_2" class="tabsbtn specTabsbtn">آپلود ضمائم بهره مندی
                    از
                    منافع</a>
            </c:if>
            <%--</c:if>--%>
        </s:else>
        <%--<c:if test="${darkhastTaghirat.state.id == 50000}">--%>
        <%--<a href='javascript:void(0);' type="button" id="tab_2" class="tabsbtn specTabsbtn">ضمائم</a>--%>
        <%--</c:if>--%>

    </div>
    <div id="tabformcontent">

        <div id="pish_container">
            <div style="display:none;" class=content id="content_100">

                <%@include file="/jsp/pishnahad/subForm/bimeGozar.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_101">
                <%@include file="/jsp/pishnahad/subForm/bimeShode.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_102">
                <%@include file="/jsp/pishnahad/subForm/bimeName.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_103">
                <%@include file="/jsp/pishnahad/subForm/vaziateSalamatiBimeShode.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_104">
                <%@include file="/jsp/pishnahad/subForm/vaziateSalamatiKhanevadeyeBimeShode.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_105">
                <%@include file="/jsp/pishnahad/subForm/soalateOmomiAzBimeShode.jsp" %>
            </div>
        </div>
        <s:if test="%{darkhastBazkharid!=null && (darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')||darkhastBazkharid.darkhastType.name().equals('TOZIH')||darkhastBazkharid.darkhastType.name().equals('KHESARAT')||darkhastBazkharid.darkhastType.name().equals('BAZKHARID')||darkhastBazkharid.darkhastType.name().equals('EBTAL'))}">
            <div class=content id="content_1">
                <%--<%@include file="/jsp/elhaghie/page_elhaghieTaghirKod.jsp" %>--%>
                <%@include file="/jsp/transitionForms/sodooreElhaghie.jsp" %>
            </div>
        </s:if>
        <s:elseif test="%{darkhastTaghirat!=null}">
            <div class=content id="content_1">
                <%@include file="/jsp/elhaghie/page_darkhastTaghirat.jsp" %>
            </div>
            <div class=content style="display:none;" id="content_3">
                <%@include file="/jsp/elhaghie/page_listTaghirGhest.jsp" %>
            </div>
        </s:elseif>
        <s:else>
            <div class=content id="content_1">
                <%@include file="/jsp/elhaghie/formeDarkhast_elhaghie.jsp" %>
            </div>
        </s:else>
        <div style="display: none;" class=content id="content_15">
            <%@include file="/jsp/bimename/subForm/mafqudi.jsp" %>
        </div>

        <div style="display: none;" class=content id="content_2">
            <%@include file="/jsp/bimename/subForm/zamaemDarkhast.jsp" %>
        </div>
        <div style="display: none" class="mainFrame content" id="content_106">
            <%@include file="/jsp/user/page_kartablElhaghie.jsp" %>
        </div>

    </div>
    <div id="sabteDarkhastButtons">
        <input type="button" onclick="window.location='/loginUser'" value="بازگشت"
               style="float:left;margin:2px"/>
    </div>
</div>
<br class="clear"/>

<%--

<div>&nbsp;</div>
<c:if test="${allowedTransitions != null}">
    <div id="controllerbuttons" style="float: left;">
        <c:if test="${darkhastBazkharid.state.id==10000}">
            <input type="button" onclick="window.open('/printeVam.action?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}');" value="پرینت">
        </c:if>
        <input type="button" onclick="window.location='/loginUser.action'" value="بازگشت"  style="float:left;"/>
        <select style="float:left; margin-left: 10px;" id="dakhastTransitionSelector" name="transitionSelector">
            <c:forEach var="transition" items="${allowedTransitions}" varStatus="i">
                <option value='<c:out value="${transition.id}"/>'><c:out value="${transition.transitionName}"/></option>
            </c:forEach>
        </select>
        <input type="button" style="float:left;margin-left:2px" value="ارسال" onclick="submitTransitionForDarkhast();"/>
    </div>
    <div id="printbuttons" style="float: right; padding-right: 20px;">
        <c:if test="${darkhastBazkharid.state.id == 1500}">
            <input type="button" value="پرینت درخواست صدور چك بازخريدي" onclick="window.open('/printeDarkhasteSodoreCheckeBazkharidi.action?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}');">
            <input type="button" value="پرینت حواله خسارت" onclick="window.open('/printeHavaleKhesarat.action?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}');">
        </c:if>
    </div>
</c:if>
<br class="clear" />
<div>&nbsp;</div>
<form action="/makeDarkhastTransition.action" id="darkhastTransitionForm" method="post" accept-charset="UTF-8">
    <input type="hidden" name="darkhastBazkharid.id" id="darkhast_bazkharidId" value="<c:out value='${darkhastBazkharid.id}'/>"/>
    <input type="hidden" name="transitionId" id="darkhast_transitionId"/>

    <input type="hidden" name="logmessage" id="log_message_darkhast"/>
</form>
<form action="/makeDarkhastTaghiratTransition.action" id="darkhastTaghiratTransitionForm" method="post" accept-charset="UTF-8">
    <input type="hidden" name="darkhastTaghirat.id" id="darkhast_taghiratId" value="<c:out value='${darkhastTaghirat.id}'/>"/>
    <input type="hidden" name="transitionId" id="darkhasttaghirat_transitionId"/>

    <input type="hidden" name="logmessage" id="log_message_darkhast_taghirat"/>
</form>


<div id="pdarkhast_popup" style="display:none;">
    <textarea rows="5" cols="32" name="logmessage" id="darkhastloggmessage"></textarea>
</div>
<div id="jarime_holder" style="display: none;"/>
--%>

<script type="text/javascript">

    function submitDarkhast() {
        $("#mainForm4Darkhast").submit();
    }

</script>

</body>
</html>
