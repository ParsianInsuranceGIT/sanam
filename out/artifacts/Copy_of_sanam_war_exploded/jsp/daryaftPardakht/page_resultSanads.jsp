<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.model.asnadeSodor.Ghest" %>
<%@ page import="com.bitarts.parsian.model.asnadeSodor.Credebit" %>
<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.asnadeSodor.Sanad" %>
<%@ include file="/jsp/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
    List kolli = (List) request.getAttribute("tarakoneshat");
    String pishnehadId = (String) request.getAttribute("pishnehadId");
    List<Sanad> sanads = (List<Sanad>) request.getAttribute("sanads");
%>

<head>
<title>سند های ایجاد شده به صورت اتوماتیک</title>
</head>
<p style="color: green;">اطلاعات با موفقیت ثبت شد. تعداد رکورد ثبت شده: ${shouldSabt}</p>
<br/>
<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
سند های ایجاد شده
    </p>

    <div id="smoothScrollaasn" style="overflow: auto;">
        <c:if test="${sessionScope.khateSanadListPaginated!=null}">
            <display:table export="true" id="khateSanadListTbl" uid="row" name="sessionScope.khateSanadListPaginated"
                       sort="external" htmlId="khateSanadListTbl"
                       partialList="true"
                       size="${sessionScope.khateSanadListPaginated.fullListSize}"
                       pagesize="${sessionScope.khateSanadListPaginated.objectsPerPage}"
                       requestURI="" clearStatus="true" keepStatus="false"
                       excludedParams="" style="width: 100%; margin: 0 auto;">
            <display:column title="ردیف" style="">${row_rowNum}</display:column>
            <display:column title="نوع سند">${row.sanad.noeSanad == "GHABZE_RESID"? "قبض رسید": row.sanad.noeSanad == "PARDAKHT"? "پرداخت":row.noeSanad}</display:column>
            <display:column title="وضعیت سند">${row.sanad.vaziat == "DAEM"? "دائم":"موقت"}</display:column>
            <display:column title="زمان ثبت" property="sanad.createdDate"/>
            <display:column title="شماره سند" property="sanad.shomare"/>
            <display:column property="amount" title="مبلغ سند" style=""/>
            <display:column title="شناسایی اعتبار / شناسه پرداخت" style="">
                ${row.etebarCredebit.shomareMoshtari == null? '-':row.etebarCredebit.shomareMoshtari}
                / ${row.etebarCredebit.shenaseCredebit == null? '-':row.etebarCredebit.shenaseCredebit}
            </display:column>
            <%--<display:column title="مبلغ اعتبار" property="etebarCredebit.amount"/>--%>
            <%--<display:column title="مانده اعتبار" property="etebarRemaining"/>--%>
            <%--<display:column title="نوع اعتبار" property="etebarCredebit.credebitTypeFarsi"/>--%>
            <%--<display:column title="تاریخ فیش" property="etebarCredebit.dateFish"/>--%>
            <%--<display:column title="شماره سند بانک " property="etebarCredebit.authorityId">--%>
            <%--<display:column title="شماره بیمه نامه" style="">${row.bedehiCredebit.identifier}</display:column>--%>
            <display:column title="شناسایی بدهی/شناسه پرداخت" style="">
                ${row.bedehiCredebit.shomareMoshtari == null? '-':row.bedehiCredebit.shomareMoshtari}
                / ${row.bedehiCredebit.shenaseCredebit == null? '-':row.bedehiCredebit.shenaseCredebit}
            </display:column>
            <%--<display:column title="مقدار بدهی" property="bedehiCredebit.amount"/>--%>
            <%--<display:column title="مانده بدهی" property="bedehiRemaining"/>--%>
            <%--<display:column title="نوع بدهی" property="bedehiCredebit.credebitTypeFarsi"/>--%>
            <%--<display:column title="کد نمایندگی بدهی" property="bedehiCredebit.namayande.kodeNamayandeKargozar"/>--%>
            <%--<display:column title="کد نمایندگی اعتبار" property="etebarCredebit.namayande.kodeNamayandeKargozar"/>--%>
            <%--<display:column title="واحد صدور بدهی" property="bedehiCredebit.vahedeSodor.kodeNamayandeKargozar"/>--%>
            <%--<display:column title="واحد صدور اعتبار" property="etebarCredebit.vahedeSodor.kodeNamayandeKargozar"/>--%>
            <%--<display:column title="نام بیمه گذار بدهی" property="bedehiCredebit.rcptName"/>--%>
            <%--<display:column title="نام بیمه گذار اعتبار" property="etebarCredebit.rcptName"/>--%>
            </display:table>
        </c:if>
    </div>
    <div style="height:30px;margin-top:2px;">
        <sec:authorize ifAnyGranted="ROLE_KARBAR_MALI">
            <input type="button" onclick="window.location='/loginUser.action'" style="float:right;" value="بازگشت"/>
        </sec:authorize>
        <sec:authorize ifNotGranted="ROLE_KARBAR_MALI">
            <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" style="float:right;" value="بازگشت"/>
        </sec:authorize>
    </div>
</div>