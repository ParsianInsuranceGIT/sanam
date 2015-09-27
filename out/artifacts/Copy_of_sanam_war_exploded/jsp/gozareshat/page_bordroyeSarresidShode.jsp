<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.bimename.Gharardad" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.viewModel.AghsatMoavagh" %>
<%@ page import="com.bitarts.parsian.viewModel.BimenameForGozaresh" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="st" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>گزارش بردروي بيمه نامه هاي سررسيد شده</title>
</head>
<body>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<form action="/makeGozaresheBordroyeSarresidShode" id="form_makeGozaresheBordroyeSarresidShode"
      method="post">
    <%@include file="/jsp/josteju/searchCity.jsp" %>
    <table class="inputList" cellspacing="5" width="90%">
        <tr>
            <td>
                <span class="noThing"></span>
                <input type=text  name="bimenamehSearch.vahedSodur" id="vahedSodur_name" class="" readonly="readonly" value="${bimenamehSearch.vahedSodur}"/>
                <input type=hidden name="bimenamehSearch.vahedSodurId"  id="vahedSodor_id" value="${bimenamehSearch.vahedSodurId}"/>
                <input type="button" id="vahedSodur_button1" value="انتخاب"onclick="fillNamayandegi('vahedSodor_id','vahedSodur_name');" style="margin:0 312px; position: absolute;"/>
                <label>واحد صدور</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type=text  name="bimenamehSearch.namayandegi" id="namayande_name" class="" readonly="readonly" value="${bimenamehSearch.namayandegi}"/>
                <input type=hidden name="bimenamehSearch.namayandegiId" value="${bimenamehSearch.namayandegiId}" id="namayande_id" />
                <input type="button" id="vahedSodur_button2" value="انتخاب"onclick="fillNamayandegi('namayande_id','namayande_name');" style="margin:0 312px; position: absolute;"/>
                &nbsp;<label>نمايندگي</label>
            </td>
        </tr>

        <tr>
            <td>
                <span class="noThing"></span>
                <st:select theme="simple" emptyOption="true" headerValue="" list="noeGharardads" name="bimenamehSearch.noeGharardad" value="bimenamehSearch.noeGharardad"/>

                &nbsp;<label>نوع قرارداد</label>
            </td>
            <td>
                <span class="noThing"></span>
                <st:select theme="simple" headerValue="" list="tarhs"
                           listKey="id" listValue="name"
                           name="bimenamehSearch.tarh.id" value="bimenamehSearch.tarh.id"/>

                &nbsp;<label>نوع طرح</label>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="bimenamehSearch.azTarikhSodor" id="azTarikheSodoreBimename" value="${bimenamehSearch.azTarikhSodor}" onkeyup=""
                       class="validate[custom[date]] datePkr" value=""/>
                &nbsp;<label>از تاریخ صدور</label>
            </td>
            <td>
                <input type="text" name="bimenamehSearch.taTarikhSodor" id="taTarikheSodoreBimename" value="${bimenamehSearch.taTarikhSodor}" onkeyup=""
                       class="validate[custom[date]] datePkr" value=""/>
                &nbsp;<label>تا تاریخ صدور</label>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="bimenamehSearch.azTarikhEngheza" id="azTarikhEngheza" value="${bimenamehSearch.azTarikhEngheza}" onkeyup=""
                       class="validate[custom[date]] datePkr" value=""/>
                &nbsp;<label>از تاریخ انقضا</label>
            </td>
            <td>
                <input type="text" name="bimenamehSearch.taTarikhEngheza" id="taTarikhEngheza" value="${bimenamehSearch.taTarikhEngheza}" onkeyup=""
                       class="validate[custom[date]] datePkr" value=""/>
                &nbsp;<label>تا تاریخ انقضا</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <select id="pishnehad_gharardad" name="bimenamehSearch.groupId">
                    <%for (Gharardad goroh : (List<Gharardad>) request.getAttribute("grouhHa")) { %>
                    <option value="<%=goroh.getId() == null ? "" : goroh.getId()%>"
                            <% if(goroh.getId() != null && request.getAttribute("bimenamehSearch")!= null && goroh.getId().equals(((BimenameForGozaresh)request.getAttribute("bimenamehSearch")).getGroupId())){ %>
                            selected="selected"
                            <%}%>

                            >
                        <%=goroh.getNameSherkat()%>
                    </option>
                    <%}%>
                </select>
                &nbsp;<label>گروه بيمه‌نامه</label>
            </td>
        </tr>
        <tr>
            <td colspan="2">
               <input type="submit" style="margin-left:25px" value="گزارش">
               <input type="button" onclick="window.location = 'jsp/management/page_mainManagementPage.jsp' " value="بازگشت"/>
               <script type="text/javascript">
                        function clearSeachFrom_b() {
                            $('#vahedSodur_name').val('');
                            $('#vahedSodor_id').val('');
                            $('#namayande_name').val('');
                            $('#namayande_id').val('');
                            $('#azTarikheSodoreBimename').val('');
                            $('#taTarikheSodoreBimename').val('');
                            $('#azTarikhEngheza').val('');
                            $('#taTarikhEngheza').val('');
                        }
               </script>
               <input type="button" style="margin-left:25px" value="پاک کردن فرم" onclick="clearSeachFrom_b()">
            </td>
        </tr>
    </table>
</form>
<%--<c:if test="${requestScope.bimenamePaginatedList != null && fn:length(bimenamePaginatedList) >0 }">--%>
<div style="overflow: auto;">
    <display:table export="true" id="bimenamePaginatedList" uid="rpViewResult"
                   htmlId="bimenamePaginatedList"
                   name="bimenamePaginatedList"
                   requestURI="makeGozaresheBordroyeBazkharidShode" clearStatus="true"
                   keepStatus="false">
        <display:column title="ردیف">${(bimenamePaginatedList.pageNumber-1)*30+rpViewResult_rowNum}</display:column>
        <display:column property="shomare" title="شماره بیمه نامه"></display:column>
        <display:column property="pishnehad.tarh.name" title="نوع طرح"></display:column>
        <display:column property="pishnehad.noeGharardad" title="نوع قرارداد"></display:column>
        <display:column property="pishnehad.gharardad.nameSherkat" title="گروه بیمه نامه"></display:column>
        <display:column property="vaziat" title="وضعیت بیمه نامه"></display:column>
        <display:column property="tarikhSodour" title="تاریخ صدور بیمه نامه"></display:column>
        <display:column property="tarikhShorou" title="تاریخ شروع بیمه نامه"></display:column>
        <display:column property="tarikhEngheza" title="تاریخ انقضا بیمه نامه"></display:column>
        <display:column property="pishnehad.bimeGozar.shakhs.fullName" title="نام بیمه گذار"></display:column>
        <display:column property="pishnehad.bimeShode.shakhs.fullName" title="نام بیمه شده"></display:column>
        <display:column property="pishnehad.bimeGozar.shakhs.kodeMelliShenasayi" title="کد ملی بیمه گذار"></display:column>
        <display:column property="pishnehad.bimeShode.shakhs.kodeMelliShenasayi" title="کد ملی بیمه شده"></display:column>
        <display:column property="pishnehad.bimeShode.shakhs.sen" title="سن بیمه شده"></display:column>
        <display:column property="pishnehad.estelam.modat_bimename" title="مدت بیمه نامه"></display:column>
        <display:column property="pishnehad.bimeShode.shakhs.tarikheTavallod" title="تاریخ تولد بیمه شده"></display:column>
        <display:column title="روش پرداخت حق‌بيمه">
            <c:choose>
                <c:when test="${pishnehad.estelam.nahve_pardakht_hagh_bime == 'mah'}">ماهانه</c:when>
                <c:when test="${pishnehad.estelam.nahve_pardakht_hagh_bime == '3mah'}">سه ماهه</c:when>
                <c:when test="${pishnehad.estelam.nahve_pardakht_hagh_bime == '6mah'}">شش ماه</c:when>
                <c:when test="${pishnehad.estelam.nahve_pardakht_hagh_bime == 'sal'}">سالانه</c:when>
                <c:when test="${pishnehad.estelam.nahve_pardakht_hagh_bime == 'yekja'}">یکجا</c:when>
            </c:choose>
        </display:column>
        <display:column property="pishnehad.estelam.sarmaye_paye_fot_long" title="سرمایه فوت"></display:column>
        <display:column property="pishnehad.estelam.sarmaye_paye_fot_dar_asar_hadese" title="سرمايه فوت در اثر حادثه"></display:column>
        <display:column property="pishnehad.estelam.sarmaye_pooshesh_naghs_ozv" title="سرمايه پوشش اضافي نقص عضو"></display:column>
        <display:column property="pishnehad.estelam.sarmaye_pooshesh_amraz_khas" title="سرمايه پوشش اضافي امراض خاص"></display:column>
        <display:column property="pishnehad.estelam.pooshesh_moafiat" title="پوشش اضافي معافيت"></display:column>
        <display:column property="pishnehad.namayandeName" title="نام نمایندگی"></display:column>
        <display:column property="pishnehad.kodeNamayandeKargozar" title="کد نمایندگی"></display:column>
    </display:table>
</div>
<%--</c:if>--%>
</body>
</html>