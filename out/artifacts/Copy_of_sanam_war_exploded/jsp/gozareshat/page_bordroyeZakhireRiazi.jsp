<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 10/5/11
  Time: 5:21 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="st" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head><title>گزارش بردروی ذخیره ریاضی</title>
    <style>
        #main {
            width: 1200px;
        }
    </style>
</head>
<body>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<form action="/makeGozaresheZakhireRiazi" id="form_makeGozaresheZakhireRiazi" method="post">
    <%@include file="/jsp/josteju/searchCity.jsp" %>

    <table class="inputList" cellspacing="5" width="90%">
        <tr>
            <td>
                <span class="noThing"></span>
                <input type="text" name="zakhireRiazi.ostan" id="ostan" value="${zakhireRiazi.ostan}"/>
                <input type="hidden" name="zakhireRiazi.ostanId" id="ostanId" value="${zakhireRiazi.ostanId}"/>
                <label>استان</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="zakhireRiazi.shahr" id="shahr" value="${zakhireRiazi.shahr}"/>
                <input type="hidden" name="zakhireRiazi.shahrId" id="shahrId" value="${zakhireRiazi.shahrId}"/>
                <input type="button" id="btnOstanShahrSelector" value="انتخاب"
                       onclick="fillShahrOstan('shahrId','shahr','ostanId','ostan','vahedSodor')"
                       style="margin:0 350px; position: absolute;"/>
                <label>شهر</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <%--<st:select theme="simple" emptyOption="true" headerValue="" list="vahedSodurs"--%>
                           <%--name="zakhireRiazi.vahedSodurId" value="%{zakhireRiazi.vahedSodurId}" listKey="id"--%>
                           <%--listValue="name"/>--%>
                <input type=text name="zakhireRiazi.vahedSodur" id="vahedSodur_name"   value="${zakhireRiazi.vahedSodur}" readonly="readonly"/>
                <input type=hidden name="zakhireRiazi.vahedSodurId" id="vahedSodor_id" value="${zakhireRiazi.vahedSodurId}"/>
                <input type="button" id="vahedSodur_button" value="انتخاب" onclick="fillNamayandegi('vahedSodor_id','vahedSodur_name');" style="margin:0 365px; position: absolute;"/>
                <%--<input type="text" name="pishnehadSearch.vahedSodor" id="vahedSodor" onkeyup=""--%>
                <%--value='${pishnehadSearch.vahedSodor}' class=""/>--%>
                &nbsp;<label>واحد صدور</label>
            </td>
            <td>
                <span class="noThing"></span>
                <%--<st:select theme="simple" emptyOption="true" headerValue="" list="vahedSodurs"--%>
                           <%--name="zakhireRiazi.namayandeId" value="%{zakhireRiazi.namayandeId}" listKey="id"--%>
                           <%--listValue="name"/>--%>
                <%--<input type="text" name="pishnehadSearch.namayande" id="" onkeyup=""--%>
                <%--value='${pishnehadSearch.namayande}' class=""/>--%>
                <input type=text name="zakhireRiazi.namayande" id="namayande_name" value="${zakhireRiazi.namayande}" readonly="readonly"/>
                <input type=hidden name="zakhireRiazi.namayandeId" id="namayande_id" value="${zakhireRiazi.namayandeId}"/>
                <input type="button" id="namayande_button" value="انتخاب" onclick="fillNamayandegi('namayande_id','namayande_name');" style="margin:0 360px; position: absolute;"/>
                <label>نمايندگي</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <st:select theme="simple" emptyOption="true" headerValue="" list="noeGharardads"
                           name="zakhireRiazi.noeGharardad" value="zakhireRiazi.noeGharardad"/>
                &nbsp;<label>نوع قرارداد</label>
            </td>
            <td dir="rtl">
                <span class="noThing"></span>
                <st:select theme="simple" headerValue="" list="tarhs"
                           listKey="id" listValue="name"
                           name="zakhireRiazi.tarh.name" value="zakhireRiazi.tarh.name"/>
                &nbsp;<label>نوع طرح</label>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="zakhireRiazi.azTarikh" id="azTarikheSodoreBimename" onkeyup=""
                       value='${zakhireRiazi.azTarikh}' class="validate[required,custom[date]] datePkr"/>
                &nbsp;<label>از تاريخ صدور بيمه‌نامه</label>
            </td>
            <td>
                <input type="text" name="zakhireRiazi.taTarikh" id="taTarikheSodoreBimename" onkeyup=""
                       value='${zakhireRiazi.taTarikh}' class="validate[required,custom[date]] datePkr"/>
                &nbsp;<label>تا تاريخ صدور بيمه‌نامه</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <input type="text" name="zakhireRiazi.azSarmayeFoat" onkeyup=""
                       value='${zakhireRiazi.azSarmayeFoat}' class=""/>
                &nbsp;<label>از سرمايه فوت (ريال)</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="zakhireRiazi.taSarmayeFoat" onkeyup=""
                       value='${zakhireRiazi.taSarmayeFoat}' class=""/>
                &nbsp;<label>تا سرمايه فوت (ريال)</label>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="zakhireRiazi.tarikhMabna" id="tarikhMabna" onkeyup=""
                       value='${zakhireRiazi.tarikhMabna}' class="validate[required,custom[date]] datePkr"/>
                &nbsp;<label>تاریخ مبنای محاسبه</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="zakhireRiazi.shomareBimename" id="shomareBimename" onkeyup=""
                                   value='${zakhireRiazi.shomareBimename}' class=""/>
                            &nbsp;<label>شماره بیمه نامه</label>
                        </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" onclick="window.location='/loginUser'" value="بازگشت"/>

                <input type="submit" value="گزارش"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <div style="overflow: auto;width: 1100px">
                    <c:if test="${sessionScope.zakhireRiaziVMList != null && fn:length(zakhireRiaziVMList) >0 }">
                        <display:table export="true" id="tblzakhireRiaziList" uid="rpViewResult"
                                       htmlId="tblzakhireRiaziList"
                                       name="sessionScope.zakhireRiaziVMList" clearStatus="true"
                                       requestURI="makeGozaresheZakhireRiazi" keepStatus="false">
                            <display:column title="ردیف">${rpViewResult_rowNum}</display:column>
                            <display:column property="ostan"
                                            title="استان"></display:column>
                            <display:column property="city" title="شهر"></display:column>
                            <display:column property="vahedSodorName"
                                            title="واحد صدور"></display:column>
                            <display:column property="vahedSodorCode"
                                            title="کد واحد صدور"></display:column>
                            <display:column property="namayandeName" title="نام نماينده"></display:column>
                            <display:column property="namayandeCode"
                                            title="کد نمایندگی"></display:column>

                            <display:column property="bazaryabFullName" title="نام بازارياب"></display:column>
                            <display:column property="gharardadType" title="نوع قرارداد"></display:column>
                            <display:column property="tarh" title="نوع طرح"></display:column>
                            <display:column property="shomareBimename"
                                            title="شماره بيمه‌نامه"></display:column>
                            <display:column property="bimeGozarFullName" title="نام بيمه‌گذار"></display:column>
                            <display:column property="bimeShodeFullName"
                                    title="نام بيمه‌شده"></display:column>
                            <display:column property="bimeShodeAge"
                                            title="سن بيمه‌شده"></display:column>
                            <display:column property="tarikhSodor"
                                            title="تاريخ صدور"></display:column>
                            <display:column property="tarikhShoroBime"
                                            title="تاريخ شروع بيمه"></display:column>
                            <display:column property="tarikhEngheza"
                                            title="تاريخ پايان بيمه‌نامه"></display:column>
                            <display:column property="modatBimename"
                                            title="مدت بيمه‌نامه"></display:column>
                            <display:column property="sarmayeFot"
                                            title="سرمايه فوت"></display:column>
                            <display:column title="روش پرداخت حق‌بيمه">
                                <c:choose>
                                    <c:when test="${rpViewResult.nahvePardakhtHaghBime == 'mah'}">ماهانه</c:when>
                                    <c:when test="${rpViewResult.nahvePardakhtHaghBime == '3mah'}">سه ماهه</c:when>
                                    <c:when test="${rpViewResult.nahvePardakhtHaghBime == '6mah'}">شش ماه</c:when>
                                    <c:when test="${rpViewResult.nahvePardakhtHaghBime == 'sal'}">سالانه</c:when>
                                    <c:when test="${rpViewResult.nahvePardakhtHaghBime == 'yekja'}">یکجا</c:when>
                                </c:choose>
                            </display:column>
                            <display:column property="jamSadere"
                                            title="جمع حق بیمه صادره"></display:column>
                            <display:column property="jamPardakhti"
                                            title="جمع حق بیمه پرداختی"></display:column>
                            <display:column property="andukhteGhati" format="{0,number}"
                                            title="مبلغ ذخیره ریاضی"></display:column>
                            <display:column property="bazkharidGhati" format="{0,number}"
                                            title="مبلغ بازخرید"></display:column>
                        </display:table>
                        <st:url id="urlPrint" action="printZakhireRiazi"/>
                        <input type="button" onclick="window.open('${urlPrint}')" value="پرینت">
                    </c:if>
                    <c:if test="${sessionScope.zakhireRiaziList != null && fn:length(zakhireRiaziList) ==0 }">
                        اطلاعاتی یافت نشد
                    </c:if>
                </div>
            </td>
        </tr>
    </table>
</form>
</body>
</html>