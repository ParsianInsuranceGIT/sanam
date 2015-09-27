<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="st" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ramtinb
  Date: 3/5/12
  Time: 10:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>گزارش رتبه‌بندي شبكه فروش</title>
    <script type="text/javascript">

        var trs = ['#tr_ostan', '#tr_shahr', '#tr_vahedSodor', '#tr_vahedSodorPedar', '#tr_namayande'];
        $(document).ready(function () {
            hideAllTr();
        })

        function hideAllTr() {
            for (var i = 0; i < trs.length; i++) {
                $(trs[i]).hide();
            }
        }

        function showTr(trId) {
            hideAllTr();
            $(trId).show();
        }
    </script>
</head>
<body>
<form action="/makeGozaresheSaleRanking" id="form_makeGozaresheSaleRanking" method="post">

    <%@include file="/jsp/josteju/searchCity.jsp" %>

    <table class="inputList" cellspacing="5" cellpadding="2" width="90%" border="0">
        <tr>
            <td colspan="3" align="center">
                <div class="dblRadio" id="searchType" style="width:550px;float:none !important;">
                    <input type="radio" id="modeNone" name="rankReport.area" value="none" checked="checked"
                           onclick="hideAllTr()"/>
                    <input type="radio" id="modeNamayande" name="rankReport.area" value="namayande"
                           onclick="showTr('#tr_namayande');"/>
                    <input type="radio" id="modeVahedSodorPedar" name="rankReport.area" value="vahedSodurPedar"
                           onclick="showTr('#tr_vahedSodorPedar');"/>
                    <input type="radio" id="modeVahedSodor" name="rankReport.area" value="vahedSodur"
                           onclick="showTr('#tr_vahedSodor');"/>
                    <input type="radio" id="modeShahr" name="rankReport.area" value="shahr"
                           onclick="showTr('#tr_shahr');"/>
                    <input type="radio" id="modeOstan" name="rankReport.area" value="ostan"
                           onclick="showTr('#tr_ostan')"/>
                    <label for="modeNone">هیجکدام</label>
                    <label for="modeNamayande">نمايندگي</label>
                    <label for="modeVahedSodorPedar">واحد صدور پدر</label>
                    <label for="modeVahedSodor">واحد صدور</label>
                    <label for="modeShahr">شهر</label>
                    <label for="modeOstan">استان</label>
                   &nbsp;<label>رتبه بندی بر اساس</label>
                </div>
            </td>
        </tr>
        <tr id="tr_ostan">
            <td colspan="3" align="center">
                <div style="width: 250px;">
                    <input type="text" name="rankReport.ostan.cityName" id="sale_ostan"/>
                    <input type="hidden" name="rankReport.ostan.cityId" id="sale_ostanId"/>
                    <label for="sale_ostan">استان</label>
                    <input type="button" id="btnOstanSelector" value="انتخاب"
                           onclick="fillOstan('sale_ostanId','sale_ostan','azTarikh')"
                           style="margin:0 200px; position: absolute;"/>
                </div>
            </td>

        </tr>
        <tr id="tr_shahr">
            <td colspan="3" align="center">
                <div style="width: 250px;">
                    <span class="help ui-icon ui-icon-search" onclick="fillShahr('sale_shahrId','sale_shahr','azTarikh');" style="float:left;" title="جستجو"></span>
                    <input type="text" name="rankReport.shahr.cityName" id="sale_shahr"/>
                    <input type="hidden" name="rankReport.shahr.cityId" id="sale_shahrId"/>
                    <label for="sale_shahr">شهر</label>
                </div>
            </td>
        </tr>
        <tr id="tr_vahedSodor">
            <td colspan="3" align="center">
                <div style="width: 250px;">
                    <%--<st:select list="vahedSodurs" key="rankReport.vahedSodur.id" listKey="id" listValue="name" label=""--%>
                               <%--theme="simple"/>--%>
                    <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('saleReport_vahedSodor_id','saleReport_vahedSodor_name','namayandeLaghvShode_yes');" style="float:left;" title="جستجو"></span>
                    <input type=text  name="rankReport.vahedSodur.name" id="saleReport_vahedSodor_name" class="" readonly="readonly"/>
                    <input type=hidden name="rankReport.vahedSodur.id"  id="saleReport_vahedSodor_id"/>
                    &nbsp;<label>واحد صدور</label>

                </div>
            </td>
        </tr>
        <tr id="tr_vahedSodorPedar">
            <td colspan="3" align="center">
                <div style="width: 250px;">
                    <%--<st:select list="vahedSodurs" key="rankReport.vahedSodurPedar.id" listKey="id" listValue="name"--%>
                               <%--label="" theme="simple"/>--%>
                <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('saleReport_vahedSodorPedar_id','saleReport_vahedSodorPedar_name','namayandeLaghvShode_yes');" style="float:left;" title="جستجو"></span>
                <input type=text  name="rankReport.vahedSodurPedar.name" id="saleReport_vahedSodorPedar_name" class="" readonly="readonly"/>
                <input type=hidden name="rankReport.vahedSodurPedar.id"  id="saleReport_vahedSodorPedar_id"/>
                &nbsp;<label>واحد صدور پدر</label>

                </div>
            </td>
        </tr>
        <tr id="tr_namayande">
            <td colspan="3" align="center">
                <div style="width: 250px;">
                    <%--<st:select list="namyandeList" key="rankReport.namNamayande.id" listKey="id" listValue="name"--%>
                               <%--label="" theme="simple"/>--%>
                <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('saleReport_namayandegi_id','saleReport_namayandegi_name','namayandeLaghvShode_yes');" style="float:left;" title="جستجو"></span>
                <input type=text  name="rankReport.namNamayande.name" id="saleReport_namayandegi_name" class="" readonly="readonly"/>
                <input type=hidden name="rankReport.namNamayande.id"  id="saleReport_namayandegi_id"/>
                &nbsp;<label>نمایندگی</label>

                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div style="width: 250px;">
                    <div class="dblRadio" id="div_namayandeLaghvShode">
                        <input type="radio" id="namayandeLaghvShode_yes" name="rankReport.laghvCheckBox" value="yes"/>
                        <input type="radio" id="namayandeLaghvShode_no" name="rankReport.laghvCheckBox"
                               checked="checked" value="no"/>
                        <label for="namayandeLaghvShode_yes">بلی</label>
                        <label for="namayandeLaghvShode_no">خیر</label>

                    </div>
                    <label for="div_namayandeLaghvShode">نمايندگان لغو كد شده</label>
                </div>
            </td>
            <td>
                <input type="text" name="rankReport.azTarikh" value="<%=DateUtil.getAvaleMah()%>"
                       id="azTarikh" class="validate[required,custom[date]] datePkr"/>
                <label for="azTarikh">رتبه‌بندي از تاريخ</label>
            </td>
            <td>
                <input type="text" name="rankReport.taTarikh" value="<st:property value="rankReport.taTarikh"/>"
                       id="taTarikh" class="validate[required,custom[date]] datePkr"/>
                <label for="taTarikh">رتبه‌بندي تا تاريخ</label>
            </td>
        </tr>
            <st:select list="indicatorList" name="rankReport.shakhes" value="rankReport.shakhes" id="shakhesDrop" label="رتبه بندی بر اساس شاخص "  labelSeparator="" cssStyle="float:right;" />
            <td colspan="3">
                <input type="button" onclick="window.location = 'jsp/management/page_mainManagementPage.jsp' " value="بازگشت"/>
                <input type="submit" value="گزارش"/>
            </td>
        </tr>
    </table>

</form>
<div style="width: 950px;overflow: auto;">
    <st:if test="rankReportList.size() >0">
        <display:table export="true" id="tblrankResultList" uid="rpViewResult" htmlId="tblrankResultList"
                       name="rankReportPaginatedList" clearStatus="true" keepStatus="false"
                       requestURI="makeGozaresheSaleRanking">
            <display:column title="ردیف" style="">${(rankReportPaginatedList.pageNumber-1)*30+rpViewResult_rowNum}</display:column>
            <display:column sortable="true" title="دسته بندی">${rpViewResult_rowNum}</display:column>
            <display:column sortable="true" property="ostan.cityName" title="استان"></display:column>
            <display:column sortable="true" property="shahr.cityName" title="شهر"></display:column>
            <display:column sortable="true" property="vahedSodurPedar.name" title="واحد صدور پدر"></display:column>
            <display:column sortable="true" property="vahedSodurPedar.kodeNamayandeKargozar" title="کد واحد صدور پدر"></display:column>
            <display:column sortable="true" property="vahedSodur.name" title="واحد صدور"></display:column>
            <display:column sortable="true" property="vahedSodur.kodeNamayandeKargozar" title="کد واحد صدور"></display:column>
            <display:column sortable="true" property="namNamayande.name" title="نام نماینده"></display:column>
            <display:column sortable="true" property="namNamayande.kodeNamayandeKargozar" title="کد نماینده"></display:column>
            <display:column sortable="true" property="tedadNamayande" title="تعداد نماینده/بازاریاب"></display:column>
            <display:column sortable="true" property="tedadBimenameSadere" title="تعداد بیمه نامه های صادره"></display:column>
            <display:column sortable="true" property="saraneBimenameSadere" title="سرانه بیمه نامه های صادره"></display:column>
            <display:column sortable="true" property="tedadBimenameFaskh" title="تعداد بیمه نامه های فسخ/ابطال"></display:column>
            <display:column sortable="true" property="sareneBimenameFaskh" title="سرانه بیمه نامه های فسخ/ابطال"></display:column>
            <display:column sortable="true" property="haghBimeSadere1" title="حق بیمه صادره سال اول"></display:column>
            <display:column sortable="true" property="saraneHaghBimeSadere1" title="سرانه حق بیمه صادره سال اول"></display:column>
            <display:column sortable="true" property="kolHaghBimeSadere" title="کل حق بیمه صادره"></display:column>
            <display:column sortable="true" property="saraneHaghBimeSadere" title="سرانه حق بیمه صادره"></display:column>
            <display:column sortable="true" property="haghBimeVosuliAzSadereByPardakht" title="حق بیمه وصولی از صادره همان ماه بر اساس تاریخ پرداخت"></display:column>
            <display:column sortable="true" property="saraneHaghBimeVosuliAzSadereByPardakht" title="سرانه حق بیمه وصولی از صادره همان ماه بر اساس تاریخ پرداخت"></display:column>
            <display:column sortable="true" property="haghBimeVosuliAzSadereBySanad" title="حق بیمه وصولی از صادره همان مان براساس تاریخ سند"></display:column>
            <display:column sortable="true" property="saraneHaghBimeVosuliAzSadereBySand"
                            title="سرانه حق بیمه وصولی از صادره همان ماه بر اساس تاریخ سند"></display:column>
            <display:column sortable="true" property="haghBimeVosuliSal1ByPardakht"
                            title="حق بیمه وصولی سال اول بر اساس تاریخ پرداخت"></display:column>
            <display:column sortable="true" property="saraneHaghBimeVosuliSal1ByPardakh"
                            title="سرانه حق بیمه وصولی سال اول براساس تاریخ پرداخت"></display:column>
            <display:column sortable="true" property="haghBimeVosuliSal1BySanad"
                            title="حق بیمه وصولی سال اول بر اساس تاریخ سند"></display:column>
            <display:column sortable="true" property="saraneHaghBimeVosuliSal1BySanad"
                            title="سرانه حق بیمه وصولی سال اول براساس تاریخ سند"></display:column>
            <display:column sortable="true" property="kolHaghBimeVosuliByPardakht"
                            title="کل حق بیمه وصولی براساس تاریخ پرداخت"></display:column>
            <display:column sortable="true" property="saraneKolHaghBimeVosuliByPardakht"
                            title="سرانه حق بیمه وصولی براساس تاریخ پرداخت"></display:column>
            <display:column sortable="true" property="kolHaghBimeVosuliBySanad"
                            title="کل حق بیمه وصولی براساس تاریخ سند"></display:column>
            <display:column sortable="true" property="saraneKolHaghBimeVosuliBySanad"
                            title="سرانه حق بیمه وصولی براساس تاریخ سند"></display:column>
            <display:column sortable="true" property="haghBimeBargashtiSal1"
                            title="حق بیمه برگشتی سال اول"></display:column>
            <display:column sortable="true" property="saraneHaghBimeBargashtiSal1"
                            title="سرانه حق بیمه برگشتی سال اول"></display:column>
            <display:column sortable="true" property="kolHaghBimeBargashti" title="کل حق بیمه برگشتی"></display:column>
            <display:column sortable="true" property="saraneKolHaghBimeBargashti"
                            title="سرانه حق بیمه برگشتی"></display:column>
            <display:column sortable="true" property="mablaghBargashtiBimeGozar"
                            title="مبلغ برگشتی به بیمه گذار"></display:column>
            <display:column sortable="true" property="haghBimeMoavagheSal1"
                            title="حق بیمه معوقه سال اول"></display:column>
            <display:column sortable="true" property="saraneHaghBimeMoavagheSal1"
                            title="سرانه حق بیمه معوقه سال اول"></display:column>
            <display:column sortable="true" property="haghBimeMoavaghe" title="کل حق بیمه معوقه"></display:column>
            <display:column sortable="true" property="saraneHaghBimeMoavaghe"
                            title="سرانه حق بیمه معوقه"></display:column>
            <display:column sortable="true" property="nesbatHaghBimeBargashtiSadere"
                            title="نسبت حق بیمه برگشتی به حق بیمه صادره"></display:column>
            <display:column sortable="true" property="nesbatHaghBimeMoavagheSadere"
                            title="نسبت حق بیمه معوقه به حق بیمه صادره"></display:column>
            <display:column sortable="true" property="sarmayeFot" title="سرمایه فوت"></display:column>
            <display:column sortable="true" property="arzeshBazKharidi" title="ارزش بازخریدی"></display:column>


        </display:table>
        <table>
            <tr>
                <td>
                    <label>میانگین:</label>
                    <st:property value="miangin"/>
                </td>
                <td>
                    <label>چارک اول:</label>
                    <st:property value="charak1"/>
                </td>
                <td>
                    <label>چارک سوم:</label>
                    <st:property value="charak3"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>تعداد زیر چارک اول:</label>
                    <st:property value="tedadZirCharak1"/>
                </td>
                <td></td>
                <td>
                    <label>تعداد بالای چارک سوم:</label>
                    <st:property value="tedadBalaCharak3"/>
                </td>
            </tr>
        </table>
        <%--<st:url id="urlPrint"  action="printGozareshSodur"/>--%>
        <%--<st:a href="%{urlPrint}">print</st:a>--%>
        <input type="button" value="پرینت" onclick="window.open('/printGozareshRanking')">
    </st:if>
</div>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
</body>
</html>