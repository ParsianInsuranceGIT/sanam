<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.bimename.Gharardad" %>
<%@ page import="java.util.List" %>
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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<head><title>گزارش بردروي صدور</title>
</head>
<body>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<form action="/makeGozaresheBordroyeSodor" id="form_makeGozaresheBordroyeSodor" method="post">
<%@include file="/jsp/josteju/searchCity.jsp" %>

<table class="inputList" cellspacing="5" width="90%">
    <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
        <tr>
            <td>
                <span class="noThing"></span> &nbsp;
                <input type="text" name="pishnehadSearch.ostan" id="ostan" value="" readonly="readonly" disabled="disabled"/>
                <input type="hidden" name="pishnehadSearch.ostanId" id="ostanId" />
                <label>استان</label>
            </td>

            <td>
                <span class="noThing"></span>
                <input type="text" name="pishnehadSearch.shahr" id="shahr" readonly="readonly" disabled="disabled"/>
                <input type="hidden" name="pishnehadSearch.shahrId" id="shahrId" />
                <label>شهر</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <label>واحد صدور بیمه نامه</label> &nbsp;
                <input type=text  name="pishnehadSearch.vahedSodor" id="vahedSodur_name" class=""  readonly="readonly" disabled="disabled"/>
                <input type=hidden name="pishnehadSearch.vahedSodorId" id="vahedSodor_id"  />
            </td>
            <td>
                <span class="noThing"></span>
                <input type=text  name="pishnehadSearch.namayande" id="namayande_name" class="" readonly="readonly" value="${user.namayandegi.name}"/> &nbsp;<label>نمايندگي</label>
                <input type=hidden name="pishnehadSearch.namayandeId"  id="namayande_id" value="${user.namayandegi.id}"/>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <st:select theme="simple" emptyOption="true" headerValue="" list="noeGharardads" disabled="true"
                           name="pishnehadSearch.noeGharardad" value="pishnehadSearch.noeGharardad"/>
                &nbsp;<label style="color:#a9a9a9;">نوع قرارداد</label>
            </td>
            <td dir="rtl">
                <span class="noThing"></span>
                <st:select theme="simple" headerValue="" list="tarhs" disabled="true"
                           listKey="id" listValue="name"
                           name="pishnehadSearch.tarh.name" value="pishnehadSearch.tarh.name"/>
                &nbsp;<label style="color:#a9a9a9;">نوع طرح</label>
            </td>
        </tr>
    </sec:authorize>
    <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
        <tr>
            <td>
                <span class="noThing"></span>
                <input type="text" name="pishnehadSearch.ostan" id="ostan" value="${pishnehadSearch.ostan}"/>
                <input type="hidden" name="pishnehadSearch.ostanId" id="ostanId" value="${pishnehadSearch.ostanId}"/>
                <input type="button" id="btnOstanSelector" value="انتخاب"
                       onclick="fillOstan('ostanId','ostan','vahedSodor')"
                       style="margin:0 304px; position: absolute;"/>
                <label>استان</label>
            </td>

            <td>
                <span class="noThing"></span>
                <input type="text" name="pishnehadSearch.shahr" id="shahr" value="${pishnehadSearch.shahr}"/>
                <input type="hidden" name="pishnehadSearch.shahrId" id="shahrId" value="${pishnehadSearch.shahrId}"/>
                <input type="button" id="btnOstanShahrSelector" value="انتخاب"
                       onclick="fillShahrOstan('shahrId','shahr','ostanId','ostan','vahedSodor')"
                       style="margin:0 300px; position: absolute;"/>
                <label>شهر</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <label>واحد صدور بیمه نامه</label> &nbsp;
                <input type=text  name="pishnehadSearch.vahedSodor" id="vahedSodur_name" class="" value="${pishnehadSearch.vahedSodor}" readonly="readonly"/>
                <input type=hidden name="pishnehadSearch.vahedSodorId" id="vahedSodor_id" value="${pishnehadSearch.vahedSodorId}" />
                <input type="button" id="vahedSodur_button" value="انتخاب"onclick="fillNamayandegi('vahedSodor_id','vahedSodur_name');" style="margin:0 262px; position: absolute;"/>


                <%--&lt;%&ndash;<st:select theme="simple" emptyOption="true" headerValue="" list="vahedSodurs"&ndash;%&gt;--%>
                           <%--&lt;%&ndash;name="pishnehadSearch.vahedSodorId" value="%{pishnehadSearch.vahedSodorId}" listKey="id"&ndash;%&gt;--%>
                           <%--&lt;%&ndash;listValue="name"/>&ndash;%&gt;--%>
                <%--<label>واحد صدور</label>--%>

                <%--<input type=button id="vahedSodur_button" value="انتخاب" onclick="fillNamayandegi('vahedSodor_id','vahedSodor_name');" style="margin:0 190px; position: absolute;"/>--%>

                <%--<input type="text" name="pishnehadSearch.vahedSodor" id="vahedSodor" onkeyup=""--%>
                <%--value='${pishnehadSearch.vahedSodor}' class=""/>--%>
            </td>
            <td>
                <span class="noThing"></span>
                <%--<st:select theme="simple" emptyOption="true" headerValue="" list="vahedSodurs"--%>
                           <%--name="pishnehadSearch.namayandeId" value="%{pishnehadSearch.namayandeId}" listKey="id"--%>
                           <%--listValue="name"/>--%>
                <input type=text  name="pishnehadSearch.namayande" id="namayande_name" class="" readonly="readonly"/> &nbsp;<label>نمايندگي</label>
                <input type=hidden name="pishnehadSearch.namayandeId"  id="namayande_id"/>
                <input type=button id="namayande_button" value="انتخاب" onclick="fillNamayandegi('namayande_id','namayande_name');" style="margin:0 266px; position: absolute;"/>
                <%--<input type="text" name="pishnehadSearch.namayande" id="" onkeyup=""--%>
                <%--value='${pishnehadSearch.namayande}' class=""/>--%>

            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <st:select theme="simple" emptyOption="true" headerValue="" list="noeGharardads"
                           name="pishnehadSearch.noeGharardad" value="pishnehadSearch.noeGharardad"/>
                &nbsp;<label>نوع قرارداد</label>
            </td>
            <td dir="rtl">
                <span class="noThing"></span>
                <st:select theme="simple" headerValue="" list="tarhs"
                           listKey="id" listValue="name"
                           name="pishnehadSearch.tarh.name" value="pishnehadSearch.tarh.name"/>
                &nbsp;<label>نوع طرح</label>
            </td>
        </tr>
    </sec:authorize>
<tr>
    <td>
        <span class="noThing"></span>
        <st:select theme="simple" emptyOption="true" headerValue="" list="bazaryabs"
                   name="pishnehadSearch.bazarYabId"  listKey="id"
                   listValue="fullName"/>
        <%--<input type="text" name="pishnehadSearch.bazarYab" id="" onkeyup="" value='${pishnehadSearch.bazarYab}'--%>
        <%--class=""/>--%>
        &nbsp;<label>نام بازارياب</label>
    </td>
    <td>
        <span class="noThing"></span>
        <st:select theme="simple" emptyOption="true" headerValue="" list="userSodors"
                   name="pishnehadSearch.karbareSaderKonnandehId"
                    listKey="id" listValue="fullName"/>
        <%--<input type="text" name="pishnehadSearch.karbareSaderKonnandeh" id="" onkeyup=""--%>
        <%--value='${pishnehadSearch.karbareSaderKonnandeh}' class=""/>--%>
        &nbsp;<label>نام كاربر صادر كننده</label>
    </td>
</tr>
<tr>
    <td>
        <c:if test="${pishnehadSearch.azTarikh!=null}">
            <input type="text" name="pishnehadSearch.azTarikh" id="azTarikheSodoreBimename" onkeyup="" value="${pishnehadSearch.azTarikh}" class="validate[required,custom[date]] datePkr"/>
        </c:if>
        <c:if test="${pishnehadSearch.azTarikh==null}">
            <input type="text" name="pishnehadSearch.azTarikh" id="azTarikheSodoreBimename" onkeyup="" value="<%=DateUtil.addMonth(DateUtil.getCurrentDate(),-1)%>" class="validate[required,custom[date]] datePkr"/>
        </c:if>
        &nbsp;<label>از تاريخ صدور بيمه‌نامه</label>
    </td>
    <td>
        <input type="text" name="pishnehadSearch.taTarikh" id="taTarikheSodoreBimename" onkeyup=""
               value='<%=request.getAttribute("pishnehadSearch.getTaTarikh")!=null?(String)request.getAttribute("pishnehadSearch.getTaTarikh"):DateUtil.getCurrentDate()%>' class="validate[required,custom[date]] datePkr"/>
        &nbsp;<label>تا تاريخ صدور بيمه‌نامه</label>
    </td>
</tr>
<tr>
    <td>
        <input type="text" name="pishnehadSearch.azTarikhEngheza" id="azTarikheEngheza" onkeyup=""
                class="validate[custom[date]] datePkr"/>
        &nbsp;<label>از تاریخ انقضاء</label>
    </td>
    <td>
        <input type="text" name="pishnehadSearch.taTarikhEngheza" id="taTarikheEngheza" onkeyup=""
                class="validate[custom[date]] datePkr"/>
        &nbsp;<label>تا تاریخ انقضاء</label>
    </td>
</tr>
<tr>
    <td>
        <span class="noThing"></span>
        <input type="text" name="pishnehadSearch.azSarmayeFoat" onkeyup=""
               value='${pishnehadSearch.azSarmayeFoat}' class="text-input digitSeparators"/>
        &nbsp;<label>از سرمايه فوت (ريال)</label>
    </td>
    <td>
        <span class="noThing"></span>
        <input type="text" name="pishnehadSearch.taSarmayeFoat" onkeyup=""
               value='${pishnehadSearch.taSarmayeFoat}' class="text-input digitSeparators"/>
        &nbsp;<label>تا سرمايه فوت (ريال)</label>
    </td>
</tr>
<tr>
    <td>
        <span class="noThing"></span>
        <select id="nahve_pardakht" name="pishnehadSearch.nahvePardakhtHagheBime">
            <option value=""></option>
            <option value="mah">ماهانه</option>
            <option value="3mah">سه ماهه</option>
            <option value="6mah">شش ماهه</option>
            <option value="sal">سالانه</option>
            <option value="yekja">یک جا</option>
        </select>
        &nbsp;<label>نحوه پرداخت</label>
    </td>
    <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
        <td>
            <span class="noThing"></span>
                    <select id="pishnehad_gharardad" name="pishnehadSearch.groupId">
                        <%for (Gharardad goroh : (List<Gharardad>) request.getAttribute("grouhHa")) { %>
                        <option value="<%=goroh.getId() == null ? "" : goroh.getId()%>">
                            <%=goroh.getNameSherkat()%>
                        </option>
                        <%}%>
                    </select>
                    &nbsp;<label>گروه بيمه‌نامه</label>
        </td>
    </sec:authorize>
    <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
        <td>
            <span class="noThing"></span>
            &nbsp;<label style="color:#a9a9a9;">گروه بيمه‌نامه</label>
                    <select id="pishnehad_gharardad" name="pishnehadSearch.groupId" disabled="disabled">
                        <%for (Gharardad goroh : (List<Gharardad>) request.getAttribute("grouhHa")) { %>
                        <option value="<%=goroh.getId() == null ? "" : goroh.getId()%>">
                            <%=goroh.getNameSherkat()%>
                        </option>
                        <%}%>
                    </select>
        </td>
    </sec:authorize>
    </tr>
    <tr>
        <td colspan="2">
            <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                <input type="button" onclick="window.location = 'loginUser.action' " value="بازگشت"/>
            </sec:authorize>
            <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                <input type="button" onclick="window.location = 'jsp/management/page_mainManagementPage.jsp' " value="بازگشت"/>
            </sec:authorize>
            <input type="submit" value="گزارش"/>
            <script type="text/javascript">
                        function clearSeachFrom_b() {
                            $('#namayande_id').val('');
                            $('#namayande_name').val('');
                            $('#vahedSodor_id').val('');
                            $('#vahedSodur_name').val('');
                            $('#ostanId').val('');
                            $('#ostan').val('');
                            $('#shahrId').val('');
                            $('#shahr').val('');
                        }
               </script>
               <input type="button" style="margin-left:25px" value="پاک کردن فرم" onclick="clearSeachFrom_b()">
        </td>
    </tr>
</table>
</form>
        <div style="overflow: auto;">
            <c:if test="${requestScope.pes != null && fn:length(pes) >0 }">
                <display:table export="true" id="tblPishnehadResultList" uid="rpViewResult" htmlId="tblPishnehadResultList" name="requestScope.pesPaginated" clearStatus="true"
                            requestURI="makeGozaresheBordroyeSodor" keepStatus="false" >
                    <display:column title="ردیف">${(pesPaginated.pageNumber-1)*30+rpViewResult_rowNum }</display:column>
                    <display:column property="shomareBimename" title="شماره بيمه‌نامه"  style="white-space: nowrap;"></display:column>
                    <display:column property="tarikhSodor" title="تاريخ صدور"></display:column>
                    <display:column title="نام بيمه‌گذار" property="bimeGozarFullName"></display:column>
                    <display:column property="tarikhShorou" title="تاريخ شروع بيمه"></display:column>
                    <display:column property="tarikhEngheza" title="تاريخ پايان بيمه‌نامه"></display:column>
                    <display:column title="سرمایه پوشش نقص عضو (ریال)" property="sarmaye_pooshesh_naghs_ozv"/>
                    <display:column property="haghBimeNaghsOzv" title="حق بیمه پوشش نقص عضو (ریال)"/>
                    <display:column title="نوع بیمه نامه">عمر و سرمایه گذاری</display:column>
                    <display:column property="sarmaye_paye_fot" title="سرمایه فوت (ریال)"></display:column>
                    <display:column property="nerkh_afzayesh_salaneh_hagh_bime" title="درصد افزايش سالانه حق‌بيمه"></display:column>
                    <display:column title="فوت">فوت</display:column>
                    <%--<display:column property="pishnehad.estelam.nerkh_afzayesh_salaneh_sarmaye_fot" title="درصد افزايش سالانه سرمايه فوت"></display:column>--%>
                    <%--<display:column title="روش پرداخت حق‌بيمه">--%>
                        <%--<c:choose>--%>
                            <%--<c:when test="${rpViewResult.pishnehad.estelam.nahve_pardakht_hagh_bime == 'mah'}">ماهانه</c:when>--%>
                            <%--<c:when test="${rpViewResult.pishnehad.estelam.nahve_pardakht_hagh_bime == '3mah'}">سه ماهه</c:when>--%>
                            <%--<c:when test="${rpViewResult.pishnehad.estelam.nahve_pardakht_hagh_bime == '6mah'}">شش ماهه</c:when>--%>
                            <%--<c:when test="${rpViewResult.pishnehad.estelam.nahve_pardakht_hagh_bime == 'sal'}">سالانه</c:when>--%>
                            <%--<c:when test="${rpViewResult.pishnehad.estelam.nahve_pardakht_hagh_bime == 'yekja'}">یکجا</c:when>--%>
                        <%--</c:choose>--%>
                    <%--</display:column>--%>
                    <display:column title="پوشش معافیت">
                        ${rpViewResult.pooshesh_moafiat=="yes"?"دارد":"ندارد"}
                    </display:column>
                    <display:column title="سرمایه پوشش حادثه (ریال)">
                        <%--<c:choose>--%>
                            <%--<c:when test="${rpViewResult.pishnehad.estelam.pooshesh_fot_dar_asar_haadese== 'yes'}">دارد</c:when>--%>
                            <%--<c:when test="${rpViewResult.pishnehad.estelam.pooshesh_fot_dar_asar_haadese == 'no'}">ندارد</c:when>--%>
                        <%--</c:choose>--%>
                        ${rpViewResult.sarmaye_paye_fot_dar_asar_hadese}
                    </display:column>
                    <display:column title="سرمایه پوشش امراض خاص (ریال)">
                        <%--<c:choose>--%>
                            <%--<c:when test="${rpViewResult.pishnehad.estelam.pooshesh_amraz_khas== 'yes'}">دارد</c:when>--%>
                            <%--<c:when test="${rpViewResult.pishnehad.estelam.pooshesh_amraz_khas == 'no'}">ندارد</c:when>--%>
                        <%--</c:choose>--%>
                        ${rpViewResult.sarmaye_pooshesh_amraz_khas}
                    </display:column>
                    <display:column property="senBimeShode" title="سن بیمه شده"></display:column>
                    <display:column property="haghBimeAmraazKhaas" title="حق بیمه پوشش امراض خاص (ریال)"></display:column>
                    <display:column property="haghBimeFotDarAsarHaadese" title="حق بیمه پوشش حادقه (ریال)"></display:column>
                    <display:column property="haghBimeAzKaarOftaadegi" title="حق بیمه پوشش معافیت (ریال)"></display:column>
                    <display:column property="haghBimePusheshHaayeEzaafi" title="مجموع حق بیمه پوشش های اضافی (ریال)"></display:column>
                    <display:column property="jamSadere" title="جمع حق بیمه صادره (ریال)"></display:column>

                    <display:column title="نام نماينده">
                        <c:if test="${rpViewResult.options != 'CODE_MOVAGHAT'}">
                            <%--<c:if test="${rpViewResult.poshtibanCode == null}">--%>
                                ${rpViewResult.namayandeName}
                            <%--</c:if>--%>
                            <%--<c:if test="${rpViewResult.poshtibanCode != null}">--%>
                                <%--کد موقت--%>
                            <%--</c:if>--%>
                        </c:if>
                        <c:if test="${rpViewResult.options == 'CODE_MOVAGHAT'}">
                           کد موقت
                        </c:if>
                    </display:column>
                    <%-------------------------------------------------------------------------------------%>
                    <display:column  title="کد نمایندگی">
                        <c:if test="${rpViewResult.options == 'CODE_MOVAGHAT'}">
                           111120
                        </c:if>
                        <c:if test="${rpViewResult.options != 'CODE_MOVAGHAT'}">
                            <%--<c:if test="${rpViewResult.poshtibanCode == null}">--%>
                                ${rpViewResult.kodeNamayandeKargozar}
                            <%--</c:if>--%>
                            <%--<c:if test="${rpViewResult.poshtibanCode != null}">--%>
                                <%--111120--%>
                            <%--</c:if>--%>
                        </c:if>
                    </display:column>

                    <display:column title="روش پرداخت حق‌بيمه">
                        <c:choose>
                            <c:when test="${rpViewResult.nahve_pardakht_hagh_bime == 'mah'}">ماهانه</c:when>
                            <c:when test="${rpViewResult.nahve_pardakht_hagh_bime == '3mah'}">سه ماهه</c:when>
                            <c:when test="${rpViewResult.nahve_pardakht_hagh_bime == '6mah'}">شش ماه</c:when>
                            <c:when test="${rpViewResult.nahve_pardakht_hagh_bime == 'sal'}">سالانه</c:when>
                            <c:when test="${rpViewResult.nahve_pardakht_hagh_bime == 'yekja'}">یکجا</c:when>
                        </c:choose>
                    </display:column>
                    <display:column title="نام بيمه‌شده" property="bimeShodeFullName"/>
                    <display:column property="haghBimeKhaalesFotYekja" title="حق بیمه خطر فوت (ریال)"></display:column>
                    <display:column property="jamSadere" title="کل اعلام به مالی (ریال)"></display:column>
                    <display:column title="نام بازارياب">${rpViewResult.bazaryabFullName}</display:column>
                    <display:column property="nerkh_afzayesh_salaneh_sarmaye_fot" title="درصد افزايش سالانه سرمايه فوت"></display:column>
                    <display:column property="modat_bimename" title="مدت بيمه‌نامه"></display:column>
                    <display:column property="ostanName" title="استان"></display:column>
                    <display:column property="cityName" title="شهر"></display:column>
                    <display:column property="vahedSodorName"
                                    title="واحد صدور"></display:column>
                    <display:column property="vahedSodorKode"
                                    title="کد واحد صدور"></display:column>

                    <display:column property="noeGharardad" title="نوع قرارداد"></display:column>
                    <display:column property="tarh" title="نوع طرح"></display:column>
                    <display:column title="پوشش معافیت">
                        ${rpViewResult.pooshesh_moafiat=="yes"?"دارد":"ندارد"}
                    </display:column>
                    <display:column property="haghBimePardaakhti" title="حق بیمه صادره سال اول (ریال)"></display:column>

                    <display:column title="نام كاربر صادر كننده">${rpViewResult.karshenas}</display:column>
                    <display:column title="گروه بیمه نامه" property="groupBimename"/>
                    <c:if test="${rpViewResult.options != 'CODE_MOVAGHAT'}">
                        <c:if test="${rpViewResult.poshtibanCode == null}">
                            <display:column title="نام و کد نماینده ثبت کننده">${rpViewResult.namayandeName} - ${rpViewResult.kodeNamayandeKargozar}</display:column>
                        </c:if>
                        <c:if test="${rpViewResult.poshtibanCode != null}">
                            <display:column title="نام و کد نماینده ثبت کننده">${rpViewResult.poshtibanName} - ${rpViewResult.poshtibanCode}</display:column>
                        </c:if>
                    </c:if>
                    <c:if test="${rpViewResult.options == 'CODE_MOVAGHAT'}">
                        <display:column title="نام و کد نماینده ثبت کننده">${rpViewResult.namayandeName} - ${rpViewResult.kodeNamayandeKargozar}</display:column>
                    </c:if>

                </display:table>
                <st:url id="urlPrint" action="printGozareshSodur"/>
                <input type="button" onclick="window.open('${urlPrint}')" value="پرینت">
            </c:if>
            <c:if test="${requestScope.pes != null && fn:length(pes) ==0 }">
                اطلاعاتی یافت نشد
            </c:if>
        </div>

<%--<c:if test="${pishnehadResultList != null || fn:length(pishnehadResultList) >0 }">--%>
<%--<table class="jtable" align="center">--%>
<%--<tr>--%>
<%--<th>ردیف</th>--%>
<%--<th>استان</th>--%>
<%--<th>شهر</th>--%>
<%--<th>واحد صدور</th>--%>
<%--<th>نام نماينده</th>--%>
<%--<th>نام بازارياب</th>--%>
<%--<th>نوع قرارداد</th>--%>
<%--<th>نوع طرح</th>--%>
<%--<th>شماره بيمه‌نامه</th>--%>
<%--<th>نام بيمه‌گذار</th>--%>
<%--<th>نام بيمه‌شده</th>--%>
<%--<th>سن بيمه‌شده</th>--%>
<%--<th>تاريخ صدور</th>--%>
<%--<th>تاريخ شروع بيمه</th>--%>
<%--<th>تاريخ پايان بيمه‌نامه</th>--%>
<%--<th>مدت بيمه‌نامه</th>--%>
<%--<th>سرمايه فوت</th>--%>
<%--<th>درصد افزايش سالانه حق‌بيمه</th>--%>
<%--<th>درصد افزايش سالانه سرمايه فوت</th>--%>
<%--<th>روش پرداخت حق‌بيمه</th>--%>
<%--<th>سرمايه پوشش معافيت</th>--%>
<%--<th>سرمايه پوشش حادثه</th>--%>
<%--<th>سرمايه پوشش امراض خاص</th>--%>
<%--<th>نام كاربر صادر كننده</th>--%>
<%--</tr>--%>

<%--<c:forEach var="row" items="${pishnehadResultList}" varStatus="i">--%>
<%--<tr>--%>
<%--<td>${i.index}</td>--%>
<%--<td>${row.namayande.ostan.cityName}</td>--%>
<%--<td>${row.namayande.shahr.cityName}</td>--%>
<%--<td>${row.vahedSodor}</td>--%>
<%--<td>${row.namayande.name}</td>--%>
<%--<td>${row.bazarYab.firstName} &nbsp; ${row.bazarYab.lastName}</td>--%>
<%--<td>${row.noeGharardad}</td>--%>
<%--<td>${row.nameTarh}</td>--%>
<%--<td>${row.bimename.shomare}</td>--%>
<%--<td>${row.bimeGozar.shakhs.name} &nbsp; ${row.bimeGozar.shakhs.nameKhanevadegi}</td>--%>
<%--<td>${row.bimeShode.shakhs.name} &nbsp; ${row.bimeShode.shakhs.nameKhanevadegi}</td>--%>
<%--<td>${row.bimeShode.shakhs.tarikheTavallod}</td>--%>
<%--<td>${row.bimeShode.shakhs.tarikheSabt}</td>--%>
<%--<td>${row.bimename.tarikhShorou}</td>--%>
<%--<td>${row.bimename.tarikhEngheza}</td>--%>
<%--<td>${row.estelam.modat_bimename}</td>--%>
<%--<td>${row.estelam.sarmaye_paye_fot}</td>--%>
<%--<td>${row.estelam.nerkh_afzayesh_salaneh_hagh_bime}</td>--%>
<%--<td>${row.estelam.nerkh_afzayesh_salaneh_sarmaye_fot}</td>--%>
<%--<c:choose>--%>
<%--<c:when test="${row.estelam.nahve_pardakht_hagh_bime == 'mah'}">--%>
<%--<td>ماهانه</td>--%>
<%--</c:when>--%>
<%--<c:when test="${row.estelam.nahve_pardakht_hagh_bime == '3mah'}">--%>
<%--<td>سه ماهه</td>--%>
<%--</c:when>--%>
<%--<c:when test="${row.estelam.nahve_pardakht_hagh_bime == '6mah'}">--%>
<%--<td>شش ماهه</td>--%>
<%--</c:when>--%>
<%--<c:when test="${row.estelam.nahve_pardakht_hagh_bime == 'sal'}">--%>
<%--<td>سالانه</td>--%>
<%--</c:when>--%>
<%--<c:when test="${row.estelam.nahve_pardakht_hagh_bime == 'yekja'}">--%>
<%--<td>یکجا</td>--%>
<%--</c:when>--%>
<%--</c:choose>--%>
<%--<td>${row.estelam.pooshesh_moafiat}</td>--%>
<%--<td>${row.estelam.pooshesh_fot_dar_asar_haadese}</td>--%>
<%--<td>${row.estelam.pooshesh_amraz_khas}</td>--%>
<%--<td>${row.userCreator.firstName} &nbsp; ${row.userCreator.lastName}</td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</table>--%>
<%--</c:if>--%>
</body>
</html>