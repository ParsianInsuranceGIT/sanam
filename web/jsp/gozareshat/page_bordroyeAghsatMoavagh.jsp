<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.bimename.Gharardad" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.viewModel.AghsatMoavagh" %>
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


<html>
<head>

    <title>گزارش بردروی اقساط معوق</title>
    <script type="text/javascript" src="/js/validation/estelam_valFuncs.js"></script>
</head>
<body>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<form action="/makeGozaresheAghsatMoavagh" id="form_makeGozaresheAghsatMoavagh" method="post">
    <%@include file="/jsp/josteju/searchCity.jsp" %>

    <table class="inputList" cellspacing="5" width="90%">
        <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="aghsatMoavagh.ostan" id="ostan" value="${aghsatMoavagh.ostan}"/>
                    <input type="hidden" name="aghsatMoavagh.ostanId" id="ostanId" value="${aghsatMoavagh.ostanId}"/>
                    <input type="button" id="btnOstanSelector" value="انتخاب"
                           onclick="fillOstan('ostanId','ostan','vahedSodor')"
                           style="margin:0 304px; position: absolute;"/>
                    <label>استان</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="aghsatMoavagh.shahr" id="shahr" value="${aghsatMoavagh.shahr}"/>
                    <input type="hidden" name="aghsatMoavagh.shahrId" id="shahrId" value="${aghsatMoavagh.shahrId}"/>
                    <input type="button" id="btnOstanShahrSelector" value="انتخاب"
                           onclick="fillShahrOstan('shahrId','shahr','ostanId','ostan','vahedSodor')"
                           style="margin:0 300px; position: absolute;"/>
                    <label>شهر</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                        <%--<st:select theme="simple" emptyOption="true" headerValue="" list="vahedSodurs"--%>
                        <%--name="aghsatMoavagh.vahedSodurId" value="%{aghsatMoavagh.vahedSodurId}" listKey="id"--%>
                        <%--listValue="name"/>--%>
                    <input type=text name="aghsatMoavagh.vahedSodur" id="vahedSodur_name" class="" readonly="readonly" value="${aghsatMoavagh.vahedSodur}"/>
                    <input type=hidden name="aghsatMoavagh.vahedSodurId" value="8" id="vahedSodor_id" value="${aghsatMoavagh.vahedSodur}"/>
                    <input type="button" id="vahedSodur_button" value="انتخاب"
                           onclick="fillNamayandegi('vahedSodor_id','vahedSodur_name');$('#vahedSodurHazf_button').show()"
                           style="margin:0 315.5px; position: absolute;"/>
                    <input type="button" id="vahedSodurHazf_button" value="حذف"
                           onclick="$('#vahedSodor_id').val('');$('#vahedSodur_name').val('');"
                           style="display:none;margin:0 103px; position: absolute;"/>
                        <%--<input type="text" name="pishnehadSearch.vahedSodor" id="vahedSodor" onkeyup=""--%>
                        <%--value='${pishnehadSearch.vahedSodor}' class=""/>--%>

                    &nbsp;<label>واحد صدور</label>
                </td>
                <td>
                    <span class="noThing"></span>
                        <%--<st:select theme="simple" emptyOption="true" headerValue="" list="vahedSodurs"--%>
                        <%--name="aghsatMoavagh.namayandeId" value="%{aghsatMoavagh.namayandeId}" listKey="id"--%>
                        <%--listValue="name"/>--%>
                    <input type=text name="aghsatMoavagh.namayande" id="namayande_name" class="" readonly="readonly" value="${aghsatMoavagh.namayande}"/>
                    <input type=hidden name="aghsatMoavagh.namayandeId" value="${aghsatMoavagh.namayandeId}" id="namayande_id"/>
                    <input type="button" id="namayande_button" value="انتخاب"
                           onclick="fillNamayandegi('namayande_id','namayande_name');$('#namayandeHazf_button').show()"
                           style="margin:0 312px; position: absolute;"/>
                    <input type="button" id="namayandeHazf_button" value="حذف"
                           onclick="$('#namayande_id').val('');$('#namayande_name').val('');"
                           style="display:none;margin:0 100px; position: absolute;"/>
                        <%--<input type="text" name="pishnehadSearch.namayande" id="" onkeyup=""--%>
                        <%--value='${pishnehadSearch.namayande}' class=""/>--%>
                    &nbsp;<label>نمايندگي</label>
                </td>
            </tr>
            <tr>
            <td>
                <span class="noThing"></span>
                <st:select theme="simple" emptyOption="true" headerValue="" list="noeGharardads"
                           name="aghsatMoavagh.noeGharardad" value="aghsatMoavagh.noeGharardad"/>
                &nbsp;<label>نوع قرارداد</label>
            </td>
            <td dir="rtl">
                <span class="noThing"></span>
                <st:select theme="simple" headerValue="" list="tarhs"
                           listKey="id" listValue="name"
                           name="aghsatMoavagh.tarh.id" value="aghsatMoavagh.tarh.id"/>
                &nbsp;<label>نوع طرح</label>
            </td>
        </tr>
        </sec:authorize>
        <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="aghsatMoavagh.ostan" id="ostan" readonly="readonly" disabled="disabled" value="${aghsatMoavagh.ostan}"/>
                    <input type="hidden" name="aghsatMoavagh.ostanId" id="ostanId" readonly="readonly" value="${aghsatMoavagh.ostanId}"
                           disabled="disabled"/>
                    <label>استان</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="aghsatMoavagh.shahr" id="shahr" readonly="readonly" disabled="disabled" value="${aghsatMoavagh.shahr}"/>
                    <input type="hidden" name="aghsatMoavagh.shahrId" id="shahrId" readonly="readonly" value="${aghsatMoavagh.shahrId}"
                           disabled="disabled"/>
                    <label>شهر</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type=text name="aghsatMoavagh.vahedSodur" id="vahedSodur_name" class="" readonly="readonly" value="${aghsatMoavagh.vahedSodur}"
                           disabled="disabled"/>
                    <input type=hidden name="aghsatMoavagh.vahedSodurId" id="vahedSodor_id" value="${aghsatMoavagh.vahedSodurId}"/>
                    &nbsp;<label>واحد صدور</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type=text name="aghsatMoavagh.namayande" id="namayande_name" class="" readonly="readonly"
                           value="${user.namayandegi.name}"/>
                    <input type=hidden name="aghsatMoavagh.namayandeId" value="${user.namayandegi.id}"
                           id="namayande_id"/>
                    &nbsp;<label>نمايندگي</label>
                </td>
            </tr>
            <tr>
            <td>
                <span class="noThing"></span>
                <st:select theme="simple" emptyOption="true" headerValue="" list="noeGharardads" disabled="true"
                           name="aghsatMoavagh.noeGharardad" value="aghsatMoavagh.noeGharardad"/>
                &nbsp;<label style="color:#a9a9a9;">نوع قرارداد</label>
            </td>
            <td dir="rtl">
                <span class="noThing"></span>
                <st:select theme="simple" headerValue="" list="tarhs" disabled="true"
                           listKey="id" listValue="name"
                           name="aghsatMoavagh.tarh.id" value="aghsatMoavagh.tarh.id"/>
                &nbsp;<label style="color:#a9a9a9;">نوع طرح</label>
            </td>
        </tr>
        </sec:authorize>

        <tr>
            <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                <td>
                    <span class="noThing"></span>
                    <select id="pishnehad_gharardad" name="aghsatMoavagh.pishnehad.gharardad.id" disabled="disabled">
                        <%for (Gharardad goroh : (List<Gharardad>) request.getAttribute("grouhHa")) { %>
                        <option value="<%=goroh.getId() == null ? "" : goroh.getId()%>"
                                <% if(goroh.getId()!= null && ((AghsatMoavagh)request.getAttribute("aghsatMoavagh"))!=null   && ((AghsatMoavagh)request.getAttribute("aghsatMoavagh")).getPishnehad()!=null && ((AghsatMoavagh)request.getAttribute("aghsatMoavagh")).getPishnehad().getGharardad() != null && goroh.getId().equals(((AghsatMoavagh)request.getAttribute("aghsatMoavagh")).getPishnehad().getGharardad().getId())) {%>
                                    selected = "selected"
                                <%}%>

                                >
                            <%=goroh.getNameSherkat()%>
                        </option>
                        <%}%>
                    </select>
                    &nbsp;<label style="color:#a9a9a9;">گروه بيمه‌نامه</label>
                </td>
            </sec:authorize>
            <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                <td>
                    <span class="noThing"></span>
                    <select id="pishnehad_gharardad" name="aghsatMoavagh.pishnehad.gharardad.id">
                        <%for (Gharardad goroh : (List<Gharardad>) request.getAttribute("grouhHa")) { %>
                        <option value="<%=goroh.getId() == null ? "" : goroh.getId()%>"
                                <% if(goroh.getId()!= null && ((AghsatMoavagh)request.getAttribute("aghsatMoavagh"))!=null   && ((AghsatMoavagh)request.getAttribute("aghsatMoavagh")).getPishnehad()!=null && ((AghsatMoavagh)request.getAttribute("aghsatMoavagh")).getPishnehad().getGharardad() != null && goroh.getId().equals(((AghsatMoavagh)request.getAttribute("aghsatMoavagh")).getPishnehad().getGharardad().getId())) {%>
                                    selected = "selected"
                                <%}%>

                                >
                            <%=goroh.getNameSherkat()%>
                        </option>
                        <%}%>
                    </select>
                    &nbsp;<label>گروه بيمه‌نامه</label>
                </td>
            </sec:authorize>
            <td>
                <st:if test="%{prepareForGozaresh}">
                    <input type="text" name="aghsatMoavagh.tarikhMabna" id="tarikhMabna" onkeyup=""
                           value='<%=DateUtil.getCurrentDate()%>' class="validate[required,custom[date]] datePkr"/>
                </st:if>
                <st:else>
                    <input type="text" name="aghsatMoavagh.tarikhMabna" id="tarikhMabna" onkeyup=""
                           value="${aghsatMoavagh.tarikhMabna}" class="validate[required,custom[date]] datePkr"/>
                </st:else>
                &nbsp;<label>تاریخ مبنای سند</label>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="aghsatMoavagh.azTarikh" id="azTarikheSodoreBimename" onkeyup=""
                    <%--value='<%=DateUtil.addMonth(DateUtil.getCurrentDate(),-1)%>' class="validate[required,custom[date]] datePkr"/>--%>
                       class="validate[custom[date],funcCall[val_aghsateMoavaghOneDatesRequired]] datePkr" value="${aghsatMoavagh.azTarikh}"/>
                &nbsp;<label>از تاريخ صدور بيمه‌نامه</label>

            </td>
            <td>
                <input type="text" name="aghsatMoavagh.taTarikh" id="taTarikheSodoreBimename" onkeyup=""
                <%--value='<%=DateUtil.getCurrentDate()%>' class="validate[required,custom[date]] datePkr"/>--%>
                       class="validate[custom[date]] datePkr" value="${aghsatMoavagh.taTarikh}"/>
                &nbsp;<label>تا تاريخ صدور بيمه‌نامه</label>
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<input type="text" name="aghsatMoavagh.azTarikhePardakht" id="azTarikhePardakht" onkeyup=""--%>
                       <%--value='<%=DateUtil.addMonth(DateUtil.getCurrentDate(),-1)%>' class="validate[custom[date]] datePkr"/>--%>
                <%--&nbsp;<label>از تاريخ پرداخت</label>--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<input type="text" name="aghsatMoavagh.taTarikhePardakht" id="taTarikhePardakht" onkeyup=""--%>
                       <%--value='<%=DateUtil.getCurrentDate()%>' class="validate[custom[date]] datePkr"/>--%>
                <%--&nbsp;<label>تا تاريخ پرداخت</label>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<st:if test = "%{prepareForGozaresh}">--%>
                    <%--<input type="text" name="aghsatMoavagh.azTarikhePardakht" id="azTarikhePardakht" onkeyup=""--%>
                           <%--value='<%=DateUtil.addMonth(DateUtil.getCurrentDate(),-1)%>'--%>
                           <%--class="validate[custom[date]] datePkr"/>--%>
                <%--</st:if>--%>
                <%--<st:else>--%>
                    <%--<input type="text" name="aghsatMoavagh.azTarikhePardakht" id="azTarikhePardakht" onkeyup=""--%>
                           <%--value="${aghsatMoavagh.azTarikhePardakht}"--%>
                           <%--class="validate[custom[date]] datePkr"/>--%>
                <%--</st:else>--%>
                <%--&nbsp;<label>از تاريخ پرداخت</label>--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<st:if test = "%{prepareForGozaresh}">--%>
                    <%--<input type="text" name="aghsatMoavagh.taTarikhePardakht" id="taTarikhePardakht" onkeyup=""--%>
                           <%--value='<%=DateUtil.getCurrentDate()%>' class="validate[custom[date]] datePkr"/>--%>
                <%--</st:if>--%>
                <%--<st:else>--%>
                    <%--<input type="text" name="aghsatMoavagh.taTarikhePardakht" id="taTarikhePardakht" onkeyup=""--%>
                           <%--value="${aghsatMoavagh.taTarikhePardakht}" class="validate[custom[date]] datePkr"/>--%>
                <%--</st:else>--%>
                <%--&nbsp;<label>تا تاريخ پرداخت</label>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <td>
                <st:if test = "%{prepareForGozaresh}">
                    <input type="text" name="aghsatMoavagh.azTarikhSarresid" id="azTarikheSarresid" onkeyup=""
                           value='<%=DateUtil.addMonth(DateUtil.getCurrentDate(),-1)%>'
                           class="validate[custom[date]] datePkr"/>
                </st:if>
                <st:else>
                    <input type="text" name="aghsatMoavagh.azTarikhSarresid" id="azTarikheSarresid" onkeyup=""
                           value="${aghsatMoavagh.azTarikhSarresid}"
                           class="validate[custom[date]] datePkr"/>
                </st:else>
                &nbsp;<label>از تاریخ سررسید</label>
            </td>
            <td>
                <st:if test = "%{prepareForGozaresh}">
                    <input type="text" name="aghsatMoavagh.taTarikhSarresid" id="taTarikheSarresid" onkeyup=""
                           value='<%=DateUtil.getCurrentDate()%>' class="validate[custom[date]] datePkr"/>
                </st:if>
                <st:else>
                    <input type="text" name="aghsatMoavagh.taTarikhSarresid" id="taTarikheSarresid" onkeyup=""
                           value="${aghsatMoavagh.taTarikhSarresid}" class="validate[custom[date]] datePkr"/>
                </st:else>
                &nbsp;<label>تا تاریخ سررسید</label>

            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <select id="nahve_pardakht" name="aghsatMoavagh.pishnehad.estelam.nahve_pardakht_hagh_bime">
                    <option value=""></option>
                    <option value="mah" <c:if test="${aghsatMoavagh.pishnehad.estelam.nahve_pardakht_hagh_bime.equals(\"mah\")}"> selected = "selected" </c:if>>
                        ماهانه
                    </option>
                    <option value="3mah" <c:if test="${aghsatMoavagh.pishnehad.estelam.nahve_pardakht_hagh_bime.equals(\"3mah\")}"> selected = "selected" </c:if>>
                        سه ماهه
                    </option>
                    <option value="6mah" <c:if test="${aghsatMoavagh.pishnehad.estelam.nahve_pardakht_hagh_bime.equals(\"6mah\")}"> selected = "selected" </c:if>>
                        شش ماهه
                    </option>
                    <option value="sal" <c:if test="${aghsatMoavagh.pishnehad.estelam.nahve_pardakht_hagh_bime.equals(\"sal\")}"> selected = "selected" </c:if>>
                        سالانه
                    </option>
                    <option value="yekja" <c:if test="${aghsatMoavagh.pishnehad.estelam.nahve_pardakht_hagh_bime.equals(\"yekja\")}"> selected = "selected" </c:if>>
                        یک جا
                    </option>
                </select>
                &nbsp;<label>نحوه پرداخت</label>
            </td>
            <td colspan="2">
                <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                    <input type="button" onclick="window.location = 'loginUser.action' " value="بازگشت"/>
                </sec:authorize>
                <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                    <input type="button" onclick="window.location = 'jsp/management/page_mainManagementPage.jsp' "
                           value="بازگشت"/>
                </sec:authorize>
                <input type="submit" style="margin-left:2px" value="گزارش"/>
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

    <c:if test="${requestScope.aghsatMoavaghListPaginated != null && fn:length(aghsatMoavaghListPaginated.list) >0 }">
        <display:table export="true" id="tblaghsatMoavaghList" uid="rpViewResult"
                       htmlId="tblaghsatMoavaghList"
                       name="aghsatMoavaghListPaginated" clearStatus="true"
                       requestURI="makeGozaresheAghsatMoavagh" keepStatus="false">
            <display:column title="ردیف">${rpViewResult_rowNum}</display:column>
            <display:column property="tarikhSanadeGhest" title="تاريخ صدور سند"></display:column>
            <display:column property="mandeGhest" title="مانده قسط (ریال)"></display:column>
            <display:column property="tarikhSarresidGhest" title="تاریخ سررسید قسط"></display:column>
            <display:column property="mablaghGhest" title="مبلغ قسط (ریال)"></display:column>
            <display:column property="shomareBimename" title="شماره بيمه‌نامه"
                            style="white-space: nowrap;"></display:column>
            <display:column property="tarikhSodour" title="تاريخ صدور"></display:column>
            <display:column property="nameBimegozar" title="نام بيمه‌گذار"/>
            <display:column property="tarikhShoro" title="تاريخ شروع بيمه"></display:column>
            <display:column property="tarikhEngheza" title="تاريخ پايان بيمه‌نامه"></display:column>
            <display:column property="jamSadere" title="جمع حق بیمه صادره (ریال)"></display:column>
            <display:column title="نام نماينده">
                <c:if test="${rpViewResult.options != 'CODE_MOVAGHAT'}">
                    <%--<c:if test="${rpViewResult.poshtibanCode == null}">--%>
                        ${rpViewResult.namayande}
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
            <display:column property="modatBimename" title="مدت بيمه‌نامه"></display:column>
            <display:column property="modatBimename" title="مدت بيمه‌نامه"></display:column>
            <display:column property="nahvePardakhtHaghBimeFarsi" title="روش پرداخت حق‌بيمه"></display:column>
            <display:column property="nameBimeshode"
                            title="نام بيمه‌شده">${rpViewResult.pishnehad.bimeShode.shakhs.name} &nbsp; ${rpViewResult.pishnehad.bimeShode.shakhs.nameKhanevadegi}</display:column>
            <display:column property="hagheBimeElamBeMaliSaleAvval"
                            title="حق بيمه  اعلام به مالي سال اول (ريال)"></display:column>

            <display:column title="تاريخ پرداخت" property="tarikhePardakht"></display:column>
            <display:column title="گروه بیمه نامه" property="sherkateGharardad"></display:column>
            <display:column property="ostan" title="استان"></display:column>
            <display:column property="shahr" title="شهر"></display:column>
            <display:column property="vahedSodur" title="واحد صدور"></display:column>
            <display:column property="kodVahedSodur" title="کد واحد صدور"></display:column>
            <display:column property="bazaryab" title="نام بازارياب"/>
            <display:column property="noeGharardad" title="نوع قرارداد"></display:column>
            <display:column property="noeTarh" title="نوع طرح"></display:column>
            <display:column property="seneBimeshode" title="سن بيمه‌شده"></display:column>
            <display:column property="shomareMoshtari" title="شناسه پرداخت"></display:column>
            <c:if test="${rpViewResult.options != 'CODE_MOVAGHAT'}">
                <c:if test="${rpViewResult.poshtibanCode == null}">
                    <display:column title="نام و کد نماینده ثبت کننده">${rpViewResult.namayande} - ${rpViewResult.kodNamayande}</display:column>
                </c:if>
                <c:if test="${rpViewResult.poshtibanCode != null}">
                    <display:column title="نام و کد نماینده ثبت کننده">${rpViewResult.poshtibanName} - ${rpViewResult.poshtibanCode}</display:column>
                </c:if>
            </c:if>
            <c:if test="${rpViewResult.options == 'CODE_MOVAGHAT'}">
                <display:column title="نام و کد نماینده ثبت کننده">${rpViewResult.namayande} - ${rpViewResult.kodNamayande}</display:column>
            </c:if>
            <display:column property="hamrahBimeGozar" title="تلفن همراه بیمه گذار"></display:column>
        </display:table>
        <st:url id="urlPrint" action="printAghsatMoavagh"/>
        <%--<input type="button" onclick="window.open('${urlPrint}')" value="پرینت">--%>
    </c:if>
    <c:if test="${requestScope.aghsatMoavaghListPaginated != null && fn:length(aghsatMoavaghListPaginated.list) ==0 }">
        اطلاعاتی یافت نشد
    </c:if>
</div>
</body>
</html>