<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.bimename.Gharardad" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.viewModel.AghsatMoavagh" %>
<%@ page import="com.bitarts.parsian.viewModel.BimenameForGozaresh" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 10/5/11
  Time: 5:21 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="st" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>گزارش بردروي مبالغ پرداختی</title>
</head>
<body>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<form action="/makeGozaresheBordroyeMablaghPardakhti" id="form_makeGozaresheBordroyeMabaleghPardakhti"
      method="post">
    <%@include file="/jsp/josteju/searchCity.jsp" %>
    <table class="inputList" cellspacing="5" width="90%">
        <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="bimenamehSearch.ostan" id="ostan" readonly="readonly" disabled="disabled"  value="${bimenamehSearch.ostan}"/>
                    <input type="hidden" name="bimenamehSearch.ostanId" id="ostanId" readonly="readonly" disabled="disabled" value="${bimenamehSearch.ostanId}" />
                     &nbsp;<label>استان</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="bimenamehSearch.shahr" id="shahr" readonly="readonly" disabled="disabled" value="${bimenamehSearch.shahr}"/>
                    <input type="hidden" name="bimenamehSearch.shahrId" id="shahrId" value="${bimenamehSearch.shahrId}"/>
                    <label>شهر</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type=text  name="bimenamehSearch.vahedSodur" id="vahedSodur_name" class="" readonly="readonly" disabled="disabled" value="${bimenamehSearch.vahedSodur}"/>
                    <input type=hidden name="bimenamehSearch.vahedSodurId"  id="vahedSodor_id" value="${bimenamehSearch.vahedSodurId}"/>
                    <label>واحد صدور</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type=text  name="bimenamehSearch.namayandegi" id="namayande_name" class="" readonly="readonly" value="${user.namayandegi.name}"/>
                    <input type=hidden name="bimenamehSearch.namayandegiId" id="namayande_id" value="${user.namayandegi.id}"/>
                    &nbsp;<label>نمايندگي</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <st:select theme="simple" emptyOption="true" headerValue="" list="noeGharardads" name="bimenamehSearch.noeGharardad" value="bimenamehSearch.noeGharardad" disabled="true"/>
                    &nbsp;<label style="color:#a9a9a9;">نوع قرارداد</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <st:select theme="simple" headerValue="" list="tarhs"
                               listKey="id" listValue="name" disabled="true"
                               name="bimenamehSearch.tarh.id" value="bimenamehSearch.tarh.id"/>

                    &nbsp;<label style="color:#a9a9a9;">نوع طرح</label>
                </td>
            </tr>
        </sec:authorize>
        <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="bimenamehSearch.ostan" id="ostan" value="${bimenamehSearch.ostan}" />
                    <input type="hidden" name="bimenamehSearch.ostanId" id="ostanId" value="${bimenamehSearch.ostanId}" />
                    <input type="button" id="btnOstanSelector" value="انتخاب"
                            onclick="fillOstan('ostanId','ostan','vahedSodor');"
                            style="margin:0 304px; position: absolute;"/>
                     &nbsp;<label>استان</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="bimenamehSearch.shahr" id="shahr" value="${bimenamehSearch.shahr}" />
                    <input type="hidden" name="bimenamehSearch.shahrId" id="shahrId" value="${bimenamehSearch.shahrId}"/>
                    <input type="button"  id="btnOstanShahrSelector" value="انتخاب"
                            onclick="fillShahrOstan('shahrId','shahr','ostanId','ostan','vahedSodor');"
                            style="margin:0 300px; position: absolute;"/>
                    <label>شهر</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <%--<st:select theme="simple" emptyOption="true" headerValue="" list="vahedSodurs"--%>
                               <%--name="bimenamehSearch.vahedSodurId" value="%{bimenamehSearch.vahedSodurId}" listKey="id"--%>
                               <%--listValue="name"/>--%>
                    <input type=text  name="bimenamehSearch.vahedSodur" id="vahedSodur_name" class="" readonly="readonly" value="${bimenamehSearch.vahedSodur}"/>
                    <input type=hidden name="bimenamehSearch.vahedSodurId"  id="vahedSodor_id" value="${bimenamehSearch.vahedSodurId}"/>
                    <input type="button" id="vahedSodur_button" value="انتخاب"onclick="fillNamayandegi('vahedSodor_id','vahedSodur_name');" style="margin:0 312px; position: absolute;"/>
                   <label>واحد صدور</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <%--<st:select theme="simple" emptyOption="true" headerValue="" list="vahedSodurs"--%>
                               <%--name="bimenamehSearch.namayandegi" value="%{bimenamehSearch.namayandegi}" listKey="id"--%>
                               <%--listValue="name"/>--%>
                    <input type=text  name="bimenamehSearch.namayandegi" id="namayande_name" class="" readonly="readonly" value="${bimenamehSearch.namayandegi}"/>
                    <input type=hidden name="bimenamehSearch.namayandegiId" value="${bimenamehSearch.namayandegiId}" id="namayande_id" />
                    <input type="button" id="vahedSodur_button" value="انتخاب"onclick="fillNamayandegi('namayande_id','namayande_name');" style="margin:0 312px; position: absolute;"/>
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
        </sec:authorize>
        <tr>
            <td>
                <st:if test = "%{prepareForGozaresh}">
                    <input type="text" name="bimenamehSearch.azTarikhPardakht" id="azTarikhePardakhtBimename" onkeyup=""
                           value='<%=DateUtil.addMonth(DateUtil.getCurrentDate(),-1)%>' class="validate[custom[date]] datePkr"/>
                </st:if>
                <st:else>
                    <input type="text" name="bimenamehSearch.azTarikhPardakht" id="azTarikhePardakhtBimename" onkeyup=""
                           value="${bimenamehSearch.azTarikhPardakht}" class="validate[custom[date]] datePkr"/>
                </st:else>
                &nbsp;<label>از تاريخ پرداخت</label>

            </td>
            <td>
                <st:if test = "%{prepareForGozaresh}">
                    <input type="text" name="bimenamehSearch.taTarikhPardakht" id="taTarikhePardakhtBimename" onkeyup=""
                           value='<%=DateUtil.getCurrentDate()%>' class="validate[custom[date]] datePkr"/>
                </st:if>
                <st:else>
                    <input type="text" name="bimenamehSearch.taTarikhPardakht" id="taTarikhePardakhtBimename" onkeyup=""
                           value="${bimenamehSearch.taTarikhPardakht}" class="validate[custom[date]] datePkr"/>
                </st:else>
                &nbsp;<label>تا تاريخ پرداخت </label>

            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="bimenamehSearch.azTarikhSanad" id="azTarikhSanad" onkeyup=""
                           value="${bimenamehSearch.azTarikhSanad}" class="validate[custom[date]] datePkr"/>
                &nbsp;<label>از تاریخ سند </label>

            </td>
            <td>
                <input type="text" name="bimenamehSearch.taTarikhSanad" id="taTarikhSanad" onkeyup=""
                       value="${bimenamehSearch.taTarikhSanad}"class="validate[custom[date]] datePkr"/>
                &nbsp;<label>تا تاریخ سند </label>

            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <select id="nahve_pardakht" name="bimenamehSearch.raveshPardakhtBimename">
                    <option value=""></option>
                    <option value="mah" <c:if test="${bimenamehSearch.raveshPardakhtBimename.equals(\"mah\")}"> selected="selected"</c:if>>
                        ماهانه
                    </option>
                    <option value="3mah" <c:if test="${bimenamehSearch.raveshPardakhtBimename.equals(\"3mah\")}"> selected="selected"</c:if>>
                        سه ماهه
                    </option>
                    <option value="6mah" <c:if test="${bimenamehSearch.raveshPardakhtBimename.equals(\"6mah\")}"> selected="selected"</c:if>>
                        شش ماهه
                    </option>
                    <option value="sal" <c:if test="${bimenamehSearch.raveshPardakhtBimename.equals(\"sal\")}"> selected="selected"</c:if>>
                        سالانه
                    </option>
                    <option value="yekja" <c:if test="${bimenamehSearch.raveshPardakhtBimename.equals(\"yekja\")}"> selected="selected"</c:if>>
                        یک جا
                    </option>
                </select>
                &nbsp;<label>نحوه پرداخت</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="bimenamehSearch.salBimeei" id="saleBimeei" value="${bimenamehSearch.salBimeei}" onkeyup="" class="validate[custom[onlyNumber]]"/>
                &nbsp;<label>سال بیمه ای</label>
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
            <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
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
            </sec:authorize>
            <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                <td>
                    <span class="noThing"></span>
                    <select id="pishnehad_gharardad" name="bimenamehSearch.groupId" disabled="disabled">
                        <%for (Gharardad goroh : (List<Gharardad>) request.getAttribute("grouhHa")) { %>
                        <option value="<%=goroh.getId() == null ? "" : goroh.getId()%>"
                                <% if (goroh.getId() != null && request.getAttribute("bimenamehSearch") != null && goroh.getId().equals(((BimenameForGozaresh) request.getAttribute("bimenamehSearch")).getGroupId())) { %>
                                    selected="selected"
                                <%}%>

                                >
                            <%=goroh.getNameSherkat()%>
                        </option>
                        <%}%>
                    </select>
                    &nbsp;<label style="color:#a9a9a9;">گروه بيمه‌نامه</label>
                </td>
            </sec:authorize>
            <td colspan="2">
               <input type="submit" style="margin-left:25px" value="گزارش">
               <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                   <input type="button" onclick="window.location = 'loginUser.action' " value="بازگشت"/>
               </sec:authorize>
                <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                    <input type="button" onclick="window.location = 'jsp/management/page_mainManagementPage.jsp' " value="بازگشت"/>
                </sec:authorize>
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
<c:if test="${requestScope.bimenameForGozareshList != null && fn:length(bimenameForGozareshList) >0 }">
<div style="overflow: auto;">
                        <display:table export="true" id="tblPishnehadResultList" uid="rpViewResult"
                                       htmlId="tblPishnehadResultList"
                                       name="bimenameForGozareshPaginatedList"
                                       requestURI="makeGozaresheBordroyeMablaghPardakhti" clearStatus="true"
                                       keepStatus="false">
                            <display:column title="ردیف">${(bimenameForGozareshPaginatedList.pageNumber-1)*30+rpViewResult_rowNum}</display:column>
                            <display:column property="ghestNumber" title="شماره قسط" />
                            <display:column property="mablaghPardakhtiTavasotBimegozar" title="مبلغ پرداختی توسط بیمه گذار (ریال)"></display:column>
                            <display:column property="tarikhSodurGhabzResid" title="تاریخ صدور قبض رسید"></display:column>
                            <display:column property="shomareGhabzResid" title="شماره قبض رسید"></display:column>
                            <display:column property="tarikhSarresidGhest" title="تاریخ سررسید قسط"></display:column>
                            <display:column property="sarmayeFot" title="سرمایه فوت (ریال)"></display:column>
                            <display:column property="mablaghGhest" title="مبلغ قسط (ریال)"></display:column>
                            <display:column property="maliat" title="مالیات"></display:column>
                            <display:column property="shomareBimename" title="شماره بيمه‌نامه" style="white-space: nowrap;"></display:column>
                            <display:column property="tarikhSodurBimename" title="تاریخ صدور"/>
                            <display:column property="nameBimeGozar" title="نام بیمه گذار"></display:column>
                            <display:column property="tarikhShoru" title="تاریخ شروع"></display:column>
                            <display:column property="tarikhEngheza" title="تاریخ انقضاء"></display:column>
                            <display:column property="noeBimename" title="نوع بیمه نامه"></display:column>
                            <display:column title="حق بیمه کل"></display:column>
                            <display:column title="نام نماينده">
                                <c:if test="${rpViewResult.options != 'CODE_MOVAGHAT'}">
                                    <%--<c:if test="${rpViewResult.poshtibanCode == null}">--%>
                                        ${rpViewResult.namNamayande}
                                    <%--</c:if>--%>
                                    <%--<c:if test="${rpViewResult.poshtibanCode != null}">--%>
                                        <%--کد موقت--%>
                                    <%--</c:if>--%>
                                </c:if>
                                <c:if test="${rpViewResult.options == 'CODE_MOVAGHAT'}">
                                   کد موقت
                                </c:if>
                            </display:column>
                            <display:column  title="کد نمایندگی">
                                <c:if test="${rpViewResult.options == 'CODE_MOVAGHAT'}">
                                   111120
                                </c:if>
                                <c:if test="${rpViewResult.options != 'CODE_MOVAGHAT'}">
                                    <%--<c:if test="${rpViewResult.poshtibanCode == null}">--%>
                                        ${rpViewResult.kodNamayande}
                                    <%--</c:if>--%>
                                    <%--<c:if test="${rpViewResult.poshtibanCode != null}">--%>
                                        <%--111120--%>
                                    <%--</c:if>--%>
                                </c:if>
                            </display:column>
                            <display:column title="ملاحظات"></display:column>
                            <display:column property="modatBimename" title="مدت بیمه نامه"></display:column>
                            <display:column property="modatBimename" title="مدت بیمه نامه"></display:column>
                            <display:column title="روش پرداخت حق‌بيمه">
                                <c:choose>
                                    <c:when test="${rpViewResult.raveshPardakhtBimename == 'mah'}">ماهانه</c:when>
                                    <c:when test="${rpViewResult.raveshPardakhtBimename == '3mah'}">سه ماهه</c:when>
                                    <c:when test="${rpViewResult.raveshPardakhtBimename == '6mah'}">شش ماه</c:when>
                                    <c:when test="${rpViewResult.raveshPardakhtBimename == 'sal'}">سالانه</c:when>
                                    <c:when test="${rpViewResult.raveshPardakhtBimename == 'yekja'}">یکجا</c:when>
                                </c:choose>
                            </display:column>
                            <display:column property="nameBimeShode" title="نام بیمه شده"></display:column>
                            <display:column property="haghBimePoosheshhayeEzafi" title="حق بیمه پوشش های اضافی (ریال)"></display:column>
                            <display:column property="payeAndMaliat" title="حق بیمه پایه"></display:column>
                            <display:column property="haghBimeSaleAvalStr" title="حق بيمه پايه سال اول (ریال)"></display:column>
                            <display:column property="primitiveEstelamSarmayePayeFotStr" title="سرمایه فوت سال اول (ریال)"></display:column>
                            <display:column  title="سال پرداخت بیمه نامه"></display:column>
                            <display:column property="salBimeei" title="سال بیمه ای"/>
                            <display:column property="jameHaghBimeSadere" title="جمع حق بیمه صادره (ریال)"></display:column>
                            <display:column property="ostan" title="استان"></display:column>
                            <display:column property="shahr" title="شهر"></display:column>
                            <display:column property="vahedSodur" title="واحد صدور بیمه نامه"></display:column>
                            <display:column property="kodVahedSodur" title="کد واحد صدور"></display:column>
                            <display:column property="noeGharardad" title="نوع قرارداد"></display:column>
                            <display:column property="tarhName" title="نوع طرح"></display:column>
                            <display:column property="tarikhPardakhtGhest" title="تاریخ پرداخت قسط"></display:column>
                            <display:column property="haghBimePoosheshhayeAsli" title="حق بیمه پوشش های اصلی (ریال)"></display:column>
                            <display:column property="groupBimename" title="گروه بیمه نامه"></display:column>
                            <c:if test="${rpViewResult.options != 'CODE_MOVAGHAT'}">
                                <c:if test="${rpViewResult.poshtibanCode == null}">
                                    <display:column title="نام و کد نماینده ثبت کننده">${rpViewResult.namNamayande} - ${rpViewResult.kodNamayande}</display:column>
                                </c:if>
                                <c:if test="${rpViewResult.poshtibanCode != null}">
                                    <display:column title="نام و کد نماینده ثبت کننده">${rpViewResult.poshtibanName} - ${rpViewResult.poshtibanCode}</display:column>
                                </c:if>
                            </c:if>
                            <c:if test="${rpViewResult.options == 'CODE_MOVAGHAT'}">
                                <display:column title="نام و کد نماینده ثبت کننده">${rpViewResult.namNamayande} - ${rpViewResult.kodNamayande}</display:column>
                            </c:if>
                            <display:column title="نام بازارياب">${rpViewResult.bazaryab}</display:column>
                        </display:table>
                        <st:url id="urlPrint" action="printGozareshMabalegh"/>
                        <input type="button" onclick="window.open('${urlPrint}')" value="پرینت">
                    </c:if>
                    <c:if test="${requestScope.bimenameForGozareshList != null && fn:length(bimenameForGozareshList) ==0 }">
                        اطلاعاتی یافت نشد
                    </c:if>
                </div>

</body>
</html>