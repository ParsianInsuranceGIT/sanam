<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<head>
    <title>مشارکت در منافع</title>
</head>
<s:actionmessage/>
<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
بیمه نامه ها
    </p>

    <div class="staticTitleBar" id="result" style="direction:rtl;">
             <display:table export="true" id="paginatedListBimename" uid="row" htmlId="paginatedListBimename"
                    name="paginatedListBimename.list" partialList="true" clearStatus="true" keepStatus="false"
                    style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
                    size="${paginatedListBimename.fullListSize}" pagesize="${paginatedListBimename.objectsPerPage}">
                 <display:column title="ردیف" >${row_rowNum+((paginatedListBimename.pageNumber-1)*paginatedListBimename.objectsPerPage)}</display:column>
                 <display:column title="شماره بیمه نامه" property="shomare" ></display:column>
                 <%--<display:column title="وضعیت" property="stateForMosharekatFa" ></display:column>--%>
                 <display:column title="شماره مشخصه" >2215/${fn:substring(row.shomare,5,-1)}/${mosharekatDarManafe.shomareMosharekat}</display:column>
                 <display:column title="درصد مشارکت این دوره">${mosharekatDarManafe.soud}</display:column>
                 <display:column title="اندوخته قطعی" property="autoAndukhtGhati" format="{0,number}"></display:column>
                 <%--<display:column title="اندوخته قطعی" property="andukhteyeGhatie" format="{0,number}"></display:column>--%>
                 <display:column title="اندوخته سرمایه گذاری علی الحساب" property="autoAndukhtAlalHesab" format="{0,number}"></display:column>
                 <%--<display:column title="اندوخته سرمایه گذاری علی الحساب" property="andukhteyeAlalHesab" format="{0,number}"></display:column>--%>
                 <%--<display:column title="ارزش بازخرید قطعی" property="autoArzeshGhati" format="{0,number}"></display:column>--%>
                 <%--<display:column title="ارزش بازخرید قطعی" property="arzeshBazkharidGhatie" format="{0,number}"></display:column>--%>
                 <%--<display:column title="ارزش بازخرید علی الحساب" property="autoArzeshBazkharidAlalHesab" format="{0,number}"></display:column>--%>
                 <%--<display:column title="ارزش بازخرید علی الحساب" property="arzeshBazkharidAlalHesab" format="{0,number}"></display:column>--%>
                 <%--<display:column title="مبلغ مشارکت قبلی" property="mosharekatKolLongUpTo(${mosharekatDarManafe.tarikhMabnaMohasebe})" ></display:column>--%>
                 <%--<display:column title="سود مبلغ مشارکت قبلی" property="soudMosharekatUpTo(${mosharekatDarManafe.tarikhMabnaMohasebe})" ></display:column>--%>
                 <display:column title="مبلغ مشارکت قبلی" format="{0,number}" property="amountPreviousMosharekatDummy"></display:column>
                 <%--<display:column title="سود مبلغ مشارکت قبلی" format="{0,number}">-</display:column>--%>
                 <display:column title="مبلغ مشارکت" property="mablaghMosharekatDummy" format="{0,number}"></display:column>
                 <display:column title="وضعیت" property="stateNameFarsiForMosharekat"></display:column>
                </display:table>


    </div>
    <br/>

    <div>

        <input type="button" onclick="window.location='/prepareForMohasebeMosharekat?mosharekatDarManafe.id=${mosharekatDarManafe.id}'"
               value="حذف بیمه نامه ها">
        <%--<input type="button" onclick="window.location='/mohasebeAndukhteBimenameha?mosharekatDarManafe.id=${mosharekatDarManafe.id}'"--%>
               <%--value="محاسبه موجودی بیمه نامه ها">--%>
        <%--<input type="button" id="b_mosharekat" style="display: none"--%>
               <%--onclick="window.location='/mohasebeMosharekat?mosharekatDarManafe.id=${mosharekatDarManafe.id}'" value="محاسبه مشارکت">--%>
        <input type="button" id="b_mosharekat2"
               <%--onclick="window.location='/sabteMosharekatAllinOne_calc?mosharekatDarManafe.id=${mosharekatDarManafe.id}'" value="ثبت مشارکت">--%>
        <input type="button" onclick="window.location='jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
    </div>
    <div style="clear:both;"/>
</div>
<s:if test="session.enableMosharekat">
    <script type="text/javascript">
        $("#b_mosharekat").show();
    </script>
</s:if>
<%--<s:if test="session.enableMosharekat2">--%>
    <script type="text/javascript">
        $("#b_mosharekat2").show();
    </script>
<%--</s:if>--%>