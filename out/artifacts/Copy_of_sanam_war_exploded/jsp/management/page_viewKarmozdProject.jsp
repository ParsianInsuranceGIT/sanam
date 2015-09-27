<%@ page import="com.bitarts.common.displaytag.PaginatedListImpl" %>
<%@ page import="com.bitarts.parsian.model.karmozd.Karmozd" %>
<%@ page import="com.bitarts.parsian.model.karmozd.KarmozdNamayande" %>
<%@ page import="com.bitarts.parsian.service.calculations.KarmozdCalculate" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="../../css/pishnahadeBimeOmreEnferadi.css"/>
    <script type="text/javascript" src="/jsp/pishnahad/pishnahadeBimeOmreEnferadi.js"></script>

    <title>نمایش کارمزد</title>
</head>
<body>
<script type="text/javascript">

    $(document).ready(function ()
    {
        $('#tabformcontent').tabs("disable",0);
        var selectedTab = "${selectedTab}";
        if(selectedTab == "tab-2"){
            changeMenu(2)
        }else if(selectedTab == "tabs-3"){
            $("#sel_sel_tab").val('tabs-3');
            changeMenu(3)
        }
        else if(selectedTab == "tabs-4")
        {
            changeMenu(4);
        }
        else if(selectedTab =="tabs-5")
        {
            $("#sel_sel_tab").val('tabs-5');
            changeMenu(5);
        }
    });
</script>
    <%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
    <div id="result" style="display:none;"></div>
<br/><br/><table align="center" class="jtable resultDets" cellpadding="0" cellspacing="0" style="margin:0 auto;" width="90%">
    <tr>
        <th rowspan="2" width="8%">مشخصات پروژه</th>
        <th>نوع پروژه</th>
        <th>شماره سریال</th>
        <th>وصولی از تاریخ</th>
        <th>وصولی تا تاریخ</th>
        <th>عنوان</th>
        <%--<th>سن</th>--%>
    </tr>
    <tr>
        <td>${karmozd.typeFarsi}</td>
        <td>${karmozd.serial}</td>
        <td>${karmozd.azTarikhVosoli}</td>
        <td>${karmozd.taTarikhVosoli}</td>
        <td>${karmozd.onvan}</td>
        <%--<td></td>--%>
    </tr>
</table>
<br/><br/>
    <div id="tabcontainer" style="padding: 10px;">
        <div id="tabs">
           <sec:authorize ifAnyGranted="ROLE_KARMOZD">
               <c:if test="${karmozd.type=='Karmozd_Tashvighi_Vosuli'}">
                   <a  id="tab_5"  type="button" onclick="changeMenu('5');changeSelTabSrch('5');" class="tabsbtn" ><span
                           class="ui-icon ui-icon-refresh" title="بازیابی مجدد"
                           onclick="window.location='/viewKarmozdProject.action?karmozd.id='+${karmozd.id}"
                           style="float:right;margin-top:5px;"></span>
                       نمایندگان مشمول/غیرمشمول</a>
               </c:if>
               <c:if test="${karmozd.type!='Karmozd_Tashvighi_Vosuli'}">
                   <a id="tab_1" type="button" onclick="changeMenu('1');" class="tabsbtn activesubmit">اعتبارات</a>
               </c:if>
               <c:if test="${karmozd.type!='Karmozd_Tashvighi_Vosuli' || (karmozd.step=='2')}">
                   <a  id="tab_2" type="button" onclick="changeMenu('2');" class="tabsbtn" >تعدیلات</a>
                   <a  id="tab_3"type="button"  onclick="changeMenu('3');changeSelTabSrch('3');" class="tabsbtn">کارمزد پرداخت</a>
               </c:if>
           </sec:authorize>
        <%--<c:if test="${karmozd.type!='Karmozd_Vosuli' && karmozd.type!='Karmozd_Pooshesh_Ezafi' && karmozd.type!='Karmozd_Seniors'}">--%>
            <c:if test="${karmozd.type!='Karmozd_Tashvighi_Vosuli' || (karmozd.step=='2') }">
                <a  id="tab_4"type="button"  onclick="changeMenu('4');" class="tabsbtn">کل مبالغ پرداختی</a>
            </c:if>
        <%--</c:if>--%>
        </div>
        <s:actionerror/>
        <s:actionmessage/>
        <div id="tabformcontent">
        <sec:authorize ifAnyGranted="ROLE_KARMOZD">
            <c:if test="${karmozd.type=='Karmozd_Tashvighi_Vosuli'}">
                <div style="display:none;" id="desc_tashvighi_div">
                    <table class="inputList" width="90%">
                        <tr>
                            <td>
                                <span class="noThing"></span>
                                <input type="text" id="kn_desc"/>
                                &nbsp;<label>شرح</label>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="content" id="content_5" style="display:none;">
                        <%--<jsp:include page="/jsp/management/page_karmozdForNamayande.jsp"/>--%>
                    <div class="expandableTitleBar" id="expandableAsl5">
                        <p class="heading">
                            <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                            جستجو
                        </p>

                        <div class="content" style="display:none;" id="searchAslContent5">
                            <%@include file="/jsp/management/karmozd/searchKarmozdForNamayande.jsp" %>
                            <hr/>
                        </div>
                    </div>
                    <br/><br/>
                    <div>
                        <span class="ui-icon ui-icon-refresh" title="بازیابی مجدد"
                             onclick="window.location='/viewKarmozdProject.action?karmozd.id='+${karmozd.id}"
                             style="float:left;margin-top:5px;"></span>

                        <c:if test="${(karmozd.step!=null && karmozd.step=='1')}">
                            <form id="go_to_step_II" action="/createProjectKarmozd.action" method="post">
                                <input type="hidden" id="k_id" name="karmozd.id" value="${karmozd.id}"/>
                                <input type="hidden" id="k_type" name="karmozd.type" value="${karmozd.type}"/>
                                <input type="hidden" id="k_step" name="karmozd.step" value="${karmozd.step}"/>
                                <input type="submit" id="stepTwoTsh" value="محاسبه ریز کارمزد"/>
                            </form>
                        </c:if>
                    </div>
                        <display:table export="true" id="tbl_knVM_list" uid="row5" htmlId="tbl_knVM_list"
                                       name="pgListKnVM.list" partialList="true" clearStatus="true"
                                       keepStatus="false"
                                       style="margin-right:auto;margin-left:auto;margin-top:20px; "
                                       requestURI="?selectedTab=tabs-5" excludedParams="selectedTab"
                                       size="${ pgListKnVM.fullListSize}"
                                       pagesize="${ pgListKnVM.objectsPerPage}">
                            <display:column title="ردیف">${row5_rowNum}</display:column>
                            <display:column title="نام و کد نماینده" style="white-space: nowrap;">
                            ${row5.namayandeCodeKargozar} - ${row5.namayandeName}
                            </display:column>
                            <display:column title="مبلغ وصولی (ریال)" property="vosuliAmount"/>
                            <display:column title="ممجموع حق بیمه صادره و برگشتی (ریال)" property="bargashtiSadereAmount"/>
                            <display:column title="نسبت وصولی به صادره (%)" property="percent"/>
                            <display:column title="وضعیت" property="statusFa"/>
                            <display:column title="شرح"  property="description"/>
                            <c:if test="${karmozd.step!='2'}">
                                <display:column media="html" title="تغییر وضعیت">
                                <script type="text/javascript">
                                    function loadDesc(btn,id)
                                    {
                                        $.validationEngine.closePrompt('.formError', true);
                                        dilg = $('#desc_tashvighi_div').dialog({
                                            modal: true,
                                            width: 840,
                                            resizable: false,
                                            closeText: "",
                                            title: "درج توضیح",
                                            buttons: {
                                                "ثبت": function ()
                                                {
                                                    chngSts(btn, id, $('#kn_desc').val())  ;
                                                },
                                                "انصراف": function ()
                                                {
                                                    $(this).dialog("close");
                                                }
                                            }
                                        });
                                    }

                                    function chngSts(btn, id,desc)
                                    {
                                        if(desc==null || desc=='' || desc==' ')
                                        {
                                            alertMessage("لطفاً فیلد شرح را تکمیل نمایید.");
                                            return;
                                        }
                                        else
                                        {
                                            $.ajax
                                            (
                                                {
                                                    type: "POST",
                                                    url: '/changeStatusKN.action?knId=' + id + '&knDesc='+ desc,
                                                    success: function (response)
                                                    {
                                                        if (btn.value == 'مشمول')
                                                        {
                                                            btn.value = 'غیرمشمول';
                                                        }
                                                        else
                                                        {
                                                            btn.value = 'مشمول'
                                                        }
                                                        $('#desc_tashvighi_div').dialog("close");
                                                    }
                                                }
                                            );
                                        }
                                    }
                                </script>
                                <input type="button" onclick="loadDesc(this, ${row5.id})"
                                       <c:if test="${row5.status=='INELIGIBLE'}">value="مشمول"</c:if>
                                       <c:if test="${row5.status=='ELIGIBLE'}">value="غیرمشمول"</c:if>
                                        />
                            </display:column>
                            </c:if>
                        </display:table>

                </div>
            </c:if>
            <c:if test="${karmozd.type!='Karmozd_Tashvighi_Vosuli'}">
                <div class=content id="content_1" >
                    <%--<jsp:include page="/jsp/management/page_etebaratKarmozd.jsp"/>--%>
                    <div class="expandableTitleBar" id="expandableAsl">
                        <p class="heading">
                            <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                            جستجو
                        </p>
                        <div class="content" style="display:none;" id="searchAslContent">
                            <%@include file="/jsp/management/karmozd/searchEtebaratKarmozd.jsp" %>
                        </div>
                    </div>
                    <div class="expandableTitleBar" id="expandableAsltbl">
                        <c:if test="${karmozd.type == 'Karmozd_Vosuli'}">
                            <display:table export="true" id="tblListKarmozdGhests" uid="row" htmlId="tblListKarmozdGhests"
                                   name="karmozdGhestPaginatedList.list" partialList="true" clearStatus="true"
                                   keepStatus="false"
                                   style="margin-right:auto;margin-left:auto;margin-top:20px;width:790px"
                                   requestURI="?selectedTab=tabs-1" excludedParams="selectedTab"
                                   size="${karmozdGhestPaginatedList.fullListSize}"
                                   pagesize="${karmozdGhestPaginatedList.objectsPerPage}">

                                <c:if test="${row.blackList == null}">
                                    <display:column style="background:#withe" title="نمایشه" media="html">
                                        <a target="_blank"
                                           href="/editShowFormReadOnly?pishnehad.id=${row.ghest.ghestBandi.bimename.pishnehad.id}">نمایش</a>
                                    </display:column>
                                    <display:column style="background:white" title="ردیف">${row_rowNum}</display:column>
                                    <display:column style="background:white"
                                                    title="کارمزد وصولی قسط (ریال)">${row.ghest.karmozdVosuliString}</display:column>
                                    <%--<display:column style="background:white"--%>
                                                    <%--title="مبلغ پرداخت شده از کارمزد وصولی (ریال)">${row.ghest.karmozdVosuliPaidString}</display:column>--%>:todo methode karmozdVosuliPaidString bayad ezafe she age khastan. . .
                                    <display:column style="background:white"
                                                    title="کارمزد وصولی قابل پرداخت (ریال)">${row.karmozdAMountString}</display:column>
                                    <display:column style="background:white"
                                                    title="حق بیمه پوشش های اضافی(ریال)">${row.ghest.haghBimePoosheshhayeEzafi}</display:column>
                                    <display:column style="background:white"
                                                    title="مالیات (ریال)">${row.ghest.maliat}</display:column>
                                    <display:column style="background:white" title="مقدار بدهی (ریال)"
                                                    property="ghest.credebit.amount"></display:column>
                                    <display:column style="background:white" title="مبلغ پرداخت شده از بدهی (ریال)"
                                                    property="khateSanad.amount"></display:column>
                                    <display:column style="background:white" title="سرمایه پایه فوت"
                                                    property="ghest.ghestBandi.bimename.pishnehad.estelam.sarmaye_paye_fot"></display:column>
                                    <display:column style="background:white" title="حق بیمه سال اول (ریال)"
                                                    property="ghest.ghestBandi.bimename.haghBimeSaleAval"></display:column>
                                    <display:column style="background:white" title="تاریخ سر رسید"
                                                    property="ghest.sarresidDate"></display:column>
                                    <display:column style="white-space: nowrap;background:white" title="شماره بیمه نامه">
                                        ${row.ghest.ghestBandi.bimename.shomare}
                                    </display:column>
                                    <display:column style="background:white" title="شناسه بدهی"
                                                    property="khateSanad.bedehiCredebit.shenaseCredebit"></display:column>
                                    <display:column style="background:white" title="شناسه اعتبار"
                                                    property="khateSanad.etebarCredebit.shenaseCredebit"></display:column>
                                    <display:column style="background:white" title="شناسه پرداخت"
                                                    property="khateSanad.bedehiCredebit.shomareMoshtari"></display:column>
                                    <display:column style="white-space: nowrap;background:white" title="نام و کد نماینده"
                                                    property="karmozdNamayande.namayande.name_kod"></display:column>
                                    <display:column style="white-space: nowrap;background:white" title="نوع نمایندگی"
                                                    property="karmozdNamayande.namayande.namayandeTypeFarsi"></display:column>
                                    <display:column style="background:white"
                                                    title="سال بیمه ای قسط">${row.ghest.ghestBandi.saleBimeiInt + 1}</display:column>
                                    <%--<display:column title="سال جاری بیمه نامه" >${row.ghest.ghestBandi.bimename.currentSaleBimei + 1}</display:column>--%>
                                    <display:column style="background:white" title="روش پرداخت حق‌بيمه">
                                        <c:choose>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'mah'}">ماهانه</c:when>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == '3mah'}">سه ماهه</c:when>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == '6mah'}">شش ماه</c:when>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'sal'}">سالانه</c:when>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'yekja'}">یکجا</c:when>
                                        </c:choose>
                                    </display:column>
                                    <display:column style="white-space: nowrap; background:white" title="نوع کارمزد">
                                        <c:choose>
                                            <c:when test="${row.type == 'ADI'}">عادی</c:when>
                                            <c:when test="${row.type == 'DAR_ENTEZAR_SARRESID'}">در انتظار سر رسید</c:when>
                                            <c:when test="${row.type == 'TALIGHI'}">تعلیقی</c:when>
                                            <c:when test="${row.type == 'KARMOZD_BARGASHTI'}">کارمزد برگشتی</c:when>
                                            <c:when test="${row.type == 'PARDAKHTE_CODE_MOVAGHATI'}">پرداخت کدموقتی</c:when>
                                            <c:when test="${row.type == 'TAGHIR_CODE_DAEM'}">تغییر کد نمایندگی دائم به دائم</c:when>
                                            <c:when test="${row.type == 'CODE_MOVAGHAT'}">کد موقت</c:when>
                                            <c:when test="${row.type == 'TAGHIRAT'}">الحاقیه تغییرات</c:when>
                                            <c:when test="${row.type == 'VOSULI'}">کارمزد وصولی</c:when>
                                            <c:when test="${row.type == 'POOSHESH_EZAFI'}">کارمزد پوشش های اضافی</c:when>
                                            <c:when test="${row.type == 'SENIOR'}">کارمزد نمایندگان ارشد</c:when>
                                            <c:when test="${row.type == 'TADILI'}">تعدیلی</c:when>
                                        </c:choose>
                                    </display:column>
                                    <display:column style="background:white;" title="تعرفه" property="tarefeFarsi"/>
                                    <display:column style="background:white;" title="لیست سیاه"  property="hasBlackList"/>
                                </c:if>
                                <c:if test="${row.blackList != null}">
                                    <display:column style="background:#cccccc" title="نمایشه" media="html">
                                        <a target="_blank"
                                           href="/editShowFormReadOnly?pishnehad.id=${row.ghest.ghestBandi.bimename.pishnehad.id}">نمایش</a>
                                    </display:column>
                                    <display:column style="background:#cccccc" title="ردیف">${row_rowNum}</display:column>
                                    <display:column style="background:#cccccc"
                                                    title="کارمزد وصولی قسط (ریال)">${row.ghest.karmozdVosuliString}</display:column>
                                    <%--<display:column style="background:#cccccc"--%>
                                    <%--title="مبلغ پرداخت شده از کارمزد وصولی (ریال)">${row.ghest.karmozdVosuliPaidString}</display:column>--%>:todo methode karmozdVosuliPaidString bayad ezafe she age khastan. . .
                                    <display:column style="background:#cccccc"
                                                    title="کارمزد وصولی قابل پرداخت (ریال)">${row.karmozdAMountString}</display:column>
                                    <display:column style="background:#cccccc"
                                                    title="حق بیمه پوشش های اضافی(ریال)">${row.ghest.haghBimePoosheshhayeEzafi}</display:column>
                                    <display:column style="background:#cccccc"
                                                    title="مالیات (ریال)">${row.ghest.maliat}</display:column>
                                    <display:column style="background:#cccccc;" title="مقدار بدهي (ريال)"
                                                    property="ghest.credebit.amount"></display:column>
                                    <display:column style="background:#cccccc;" title="مبلغ پرداخت شده از بدهي (ريال)"
                                                    property="khateSanad.amount"></display:column>
                                    <display:column style="background:#cccccc;" title="سرمايه پايه فوت"
                                                    property="ghest.ghestBandi.bimename.pishnehad.estelam.sarmaye_paye_fot"></display:column>
                                    <display:column style="background:#cccccc;" title="حق بيمه سال اول (ريال)"
                                                    property="ghest.ghestBandi.bimename.haghBimeSaleAval"></display:column>
                                    <display:column style="background:#cccccc;" title="تاريخ سر رسيد"
                                                    property="ghest.sarresidDate"></display:column>
                                    <display:column style="background:#cccccc;white-space: nowrap;" title="شماره بيمه نامه">
                                       ${row.ghest.ghestBandi.bimename.shomare}
                                    </display:column>
                                    <display:column style="background:#cccccc;" title="شناسه بدهي"
                                                    property="khateSanad.bedehiCredebit.shenaseCredebit"></display:column>
                                    <display:column style="background:#cccccc;" title="شناسه اعتبار"
                                                    property="khateSanad.etebarCredebit.shenaseCredebit"></display:column>
                                    <display:column style="background:#cccccc;" title="شماره مشتري"
                                                    property="khateSanad.bedehiCredebit.shomareMoshtari"></display:column>
                                    <display:column style="background:#cccccc;white-space: nowrap;" title="نام و کد نماينده"
                                                    property="karmozdNamayande.namayande.name_kod"></display:column>
                                    <display:column style="background:#cccccc;white-space: nowrap;" title="نوع نمايندگي"
                                                    property="karmozdNamayande.namayande.namayandeTypeFarsi"></display:column>
                                    <display:column style="background:#cccccc;"
                                                    title="سال بيمه اي قسط">${row.ghest.ghestBandi.saleBimeiInt + 1}</display:column>
                                    <%--<display:column style="background:#999999;" title="سال جاري بيمه نامه">${row.ghest.ghestBandi.bimename.currentSaleBimei + 1}</display:column>--%>
                                    <display:column style="background:#cccccc;" title="روش پرداخت حق‌بيمه">
                                        <c:choose>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'mah'}">ماهانه</c:when>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == '3mah'}">سه ماهه</c:when>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == '6mah'}">شش ماه</c:when>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'sal'}">سالانه</c:when>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'yekja'}">يکجا</c:when>
                                        </c:choose>
                                    </display:column>
                                    <display:column style="background:#cccccc; white-space: nowrap;" title="نوع کارمزد">
                                        <c:choose>
                                            <c:when test="${row.type == 'ADI'}">عادي</c:when>
                                            <c:when test="${row.type == 'DAR_ENTEZAR_SARRESID'}">در انتظار سر رسيد</c:when>
                                            <c:when test="${row.type == 'TALIGHI'}">تعليقي</c:when>
                                            <c:when test="${row.type == 'KARMOZD_BARGASHTI'}">کارمزد برگشتي</c:when>
                                            <c:when test="${row.type == 'PARDAKHTE_CODE_MOVAGHATI'}">پرداخت کدموقتي</c:when>
                                            <c:when test="${row.type == 'TAGHIR_CODE_DAEM'}">تغییر کد نمایندگی دائم به دائم</c:when>
                                            <c:when test="${row.type == 'CODE_MOVAGHAT'}">کد موقت</c:when>
                                            <c:when test="${row.type == 'TAGHIRAT'}">الحاقيه تغييرات</c:when>
                                            <c:when test="${row.type == 'VOSULI'}">کارمزد وصولي</c:when>
                                            <c:when test="${row.type == 'POOSHESH_EZAFI'}">کارمزد پوشش هاي اضافي</c:when>
                                            <c:when test="${row.type == 'SENIOR'}">کارمزد نمايندگان ارشد</c:when>
                                        </c:choose>
                                    </display:column>
                                    <display:column style="background:#cccccc;" title="تعرفه" property="tarefeFarsi"/>
                                    <display:column style="background:#cccccc;" title="لیست سیاه"
                                                    property="hasBlackList"/>
                                </c:if>
                            </display:table>
                        </c:if>
                        <c:if test="${karmozd.type == 'Karmozd_Pooshesh_Ezafi'}">
                            <display:table export="true" id="tblListKarmozdGhests" uid="row" htmlId="tblListKarmozdGhests"
                                           name="karmozdGhestPaginatedList.list" partialList="true" clearStatus="true"
                                           keepStatus="false"
                                           style="margin-right:auto;margin-left:auto;margin-top:20px;width:790px"
                                           requestURI="?selectedTab=tabs-1" excludedParams="selectedTab"
                                           size="${karmozdGhestPaginatedList.fullListSize}"
                                           pagesize="${karmozdGhestPaginatedList.objectsPerPage}">

                                <display:column style="background:white" media="html" title="نمایش">
                                    <a target="_blank"
                                       href="/editShowFormReadOnly?pishnehad.id=${row.ghest.ghestBandi.bimename.pishnehad.id}">نمایش</a>
                                </display:column>
                                <display:column style="background:white" title="ردیف">${row_rowNum}</display:column>
                                <display:column style="background:white"
                                                title="کارمزد پوشش های اضافی قسط (ریال)">${row.ghest.karmozdPoosheshEzafiString}</display:column>
                                <%--<display:column style="background:white"--%>
                                <%--title="مبلغ پرداخت شده از کارمزد وصولی (ریال)">${row.ghest.karmozdPoosheshEzafiPaidString}</display:column> :todo methode karmozdPoosheshEzafiPaidString bayad ezafe she age khastan. . .--%>
                                <display:column style="background:white"
                                                title="کارمزد پوشش های اضافی قابل پرداخت (ریال)">${row.karmozdAMountString}</display:column>
                                <display:column style="background:white"
                                                title="حق بیمه پوشش های اضافی(ریال)">${row.ghest.haghBimePoosheshhayeEzafi}</display:column>
                                <display:column style="background:white" title="مقدار بدهی (ریال)"
                                                property="ghest.credebit.amount"></display:column>
                                <display:column style="background:white" title="مبلغ پرداخت شده از بدهی (ریال)"
                                                property="khateSanad.amount"></display:column>
                                <display:column style="background:white" title="سرمایه پایه فوت"
                                                property="ghest.ghestBandi.bimename.pishnehad.estelam.sarmaye_paye_fot"></display:column>
                                <display:column style="background:white" title="حق بیمه سال اول (ریال)"
                                                property="ghest.ghestBandi.bimename.haghBimeSaleAval"></display:column>
                                <display:column style="background:white" title="تاریخ سر رسید"
                                                property="ghest.sarresidDate"></display:column>
                                <display:column style="white-space: nowrap;background:white" title="شماره بیمه نامه">
                                    ${row.ghest.ghestBandi.bimename.shomare}
                                </display:column>
                                <display:column style="background:white" title="شناسه بدهی"
                                                property="khateSanad.bedehiCredebit.shenaseCredebit"></display:column>
                                <display:column style="background:white" title="شناسه اعتبار"
                                                property="khateSanad.etebarCredebit.shenaseCredebit"></display:column>
                                <display:column style="background:white" title="شناسه پرداخت"
                                                property="khateSanad.bedehiCredebit.shomareMoshtari"></display:column>
                                <display:column style="white-space: nowrap;background:white" title="نام و کد نماینده"
                                                property="karmozdNamayande.namayande.name_kod"></display:column>
                                <display:column style="white-space: nowrap;background:white" title="نوع نمایندگی"
                                                property="karmozdNamayande.namayande.namayandeTypeFarsi"></display:column>
                                <display:column style="background:white"
                                                title="سال بیمه ای قسط">${row.ghest.ghestBandi.saleBimeiInt + 1}</display:column>
                                <%--<display:column title="سال جاری بیمه نامه" >${row.ghest.ghestBandi.bimename.currentSaleBimei + 1}</display:column>--%>
                                <display:column style="background:white" title="روش پرداخت حق‌بيمه">
                                    <c:choose>
                                        <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'mah'}">ماهانه</c:when>
                                        <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == '3mah'}">سه ماهه</c:when>
                                        <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == '6mah'}">شش ماه</c:when>
                                        <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'sal'}">سالانه</c:when>
                                        <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'yekja'}">یکجا</c:when>
                                    </c:choose>
                                </display:column>
                                <display:column style="white-space: nowrap; background:white" title="نوع کارمزد">
                                    <c:choose>
                                        <c:when test="${row.type == 'ADI'}">عادی</c:when>
                                        <c:when test="${row.type == 'DAR_ENTEZAR_SARRESID'}">در انتظار سر رسید</c:when>
                                        <c:when test="${row.type == 'TALIGHI'}">تعلیقی</c:when>
                                        <c:when test="${row.type == 'KARMOZD_BARGASHTI'}">کارمزد برگشتی</c:when>
                                        <c:when test="${row.type == 'PARDAKHTE_CODE_MOVAGHATI'}">پرداخت کدموقتی</c:when>
                                        <c:when test="${row.type == 'TAGHIR_CODE_DAEM'}">تغییر کد نمایندگی دائم به دائم</c:when>
                                        <c:when test="${row.type == 'CODE_MOVAGHAT'}">کد موقت</c:when>
                                        <c:when test="${row.type == 'TAGHIRAT'}">الحاقیه تغییرات</c:when>
                                        <c:when test="${row.type == 'VOSULI'}">کارمزد وصولی</c:when>
                                        <c:when test="${row.type == 'POOSHESH_EZAFI'}">کارمزد پوشش های اضافی</c:when>
                                        <c:when test="${row.type == 'SENIOR'}">کارمزد نمایندگان ارشد</c:when>
                                        <c:when test="${row.type == 'TADILI'}">تعدیلی</c:when>
                                    </c:choose>
                                </display:column>
                                <display:column style="background:white;" title="تعرفه" property="tarefeFarsi"/>
                            </display:table>
                        </c:if>
                        <c:if test="${karmozd.type == 'Karmozd_Seniors'}">
                            <display:table export="true" id="tblListKarmozdGhests" uid="row" htmlId="tblListKarmozdGhests"
                                           name="karmozdGhestPaginatedList.list" partialList="true" clearStatus="true"
                                           keepStatus="false"
                                           style="margin-right:auto;margin-left:auto;margin-top:20px;width:790px"
                                           requestURI="?selectedTab=tabs-1" excludedParams="selectedTab"
                                           size="${karmozdGhestPaginatedList.fullListSize}"
                                           pagesize="${karmozdGhestPaginatedList.objectsPerPage}">
                                    <display:column style="background:white" media="html" title="نمایش">
                                        <a target="_blank" href="/editShowFormReadOnly?pishnehad.id=${row.ghest.ghestBandi.bimename.pishnehad.id}">نمایش</a>
                                    </display:column>
                                    <display:column style="background:white" title="ردیف">${row_rowNum}</display:column>
                                    <display:column style="background:white"
                                                    title="کارمزد قسط (ریال)">${row.ghest.karmozdRealString}</display:column>
                                    <%--<display:column style="background:white"--%>
                                                    <%--title="مبلغ پرداخت شده از کارمزد نماینده ارشد (ریال)">${row.ghest.karmozdPaidString}</display:column> todo in method age khastan bayad ezafe she (majmoe karmozd_ghest haye marbut be in ghest ke typeshon ham senior hast)--%>
                                    <display:column style="background:white"
                                                    title="کارمزد نماینده ارشد قابل پرداخت (ریال)">${row.karmozdAMountString}</display:column>
                                    <display:column style="background:white" title="مقدار بدهی (ریال)"
                                                    property="ghest.credebit.amount"></display:column>
                                    <display:column style="background:white" title="مبلغ پرداخت شده از بدهی (ریال)"
                                                    property="khateSanad.amount"></display:column>
                                    <display:column style="background:white" title="سرمایه پایه فوت"
                                                    property="ghest.ghestBandi.bimename.pishnehad.estelam.sarmaye_paye_fot"></display:column>
                                    <display:column style="background:white" title="حق بیمه سال اول (ریال)"
                                                    property="ghest.ghestBandi.bimename.haghBimeSaleAval"></display:column>
                                    <display:column style="background:white" title="تاریخ سر رسید"
                                                    property="ghest.sarresidDate"></display:column>
                                    <display:column style="white-space: nowrap;background:white" title="شماره بیمه نامه">
                                        ${row.ghest.ghestBandi.bimename.shomare}
                                    </display:column>
                                    <display:column style="background:white" title="شناسه بدهی"
                                                    property="khateSanad.bedehiCredebit.shenaseCredebit"></display:column>
                                    <display:column style="background:white" title="شناسه اعتبار"
                                                    property="khateSanad.etebarCredebit.shenaseCredebit"></display:column>
                                    <display:column style="background:white" title="شناسه پرداخت"
                                                    property="khateSanad.bedehiCredebit.shomareMoshtari"></display:column>
                                    <display:column style="white-space: nowrap;background:white" title="نام و کد نماینده"
                                                    property="karmozdNamayande.namayande.name_kod"></display:column>
                                    <display:column style="white-space: nowrap;background:white" title="نوع نمایندگی"
                                                    property="karmozdNamayande.namayande.namayandeTypeFarsi"></display:column>
                                    <display:column style="background:white"
                                                    title="سال بیمه ای قسط">${row.ghest.ghestBandi.saleBimeiInt + 1}</display:column>
                                    <%--<display:column title="سال جاری بیمه نامه" >${row.ghest.ghestBandi.bimename.currentSaleBimei + 1}</display:column>--%>
                                    <display:column style="background:white" title="روش پرداخت حق‌بيمه">
                                        <c:choose>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'mah'}">ماهانه</c:when>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == '3mah'}">سه ماهه</c:when>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == '6mah'}">شش ماه</c:when>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'sal'}">سالانه</c:when>
                                            <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'yekja'}">یکجا</c:when>
                                        </c:choose>
                                    </display:column>
                                    <display:column style="white-space: nowrap; background:white" title="نوع کارمزد">
                                        <c:choose>
                                            <c:when test="${row.type == 'ADI'}">عادی</c:when>
                                            <c:when test="${row.type == 'DAR_ENTEZAR_SARRESID'}">در انتظار سر رسید</c:when>
                                            <c:when test="${row.type == 'TALIGHI'}">تعلیقی</c:when>
                                            <c:when test="${row.type == 'KARMOZD_BARGASHTI'}">کارمزد برگشتی</c:when>
                                            <c:when test="${row.type == 'PARDAKHTE_CODE_MOVAGHATI'}">پرداخت کدموقتی</c:when>
                                            <c:when test="${row.type == 'TAGHIR_CODE_DAEM'}">تغییر کد نمایندگی دائم به دائم</c:when>
                                            <c:when test="${row.type == 'CODE_MOVAGHAT'}">کد موقت</c:when>
                                            <c:when test="${row.type == 'TAGHIRAT'}">الحاقیه تغییرات</c:when>
                                            <c:when test="${row.type == 'VOSULI'}">کارمزد وصولی</c:when>
                                            <c:when test="${row.type == 'POOSHESH_EZAFI'}">کارمزد پوشش های اضافی</c:when>
                                            <c:when test="${row.type == 'SENIOR'}">کارمزد نمایندگان ارشد</c:when>
                                            <c:when test="${row.type == 'TADILI'}">تعدیلی</c:when>
                                        </c:choose>
                                    </display:column>
                                    <display:column style="background:white;" title="تعرفه" property="tarefeFarsi"/>
                            </display:table>
                        </c:if>
                        <c:if test="${karmozd.type == 'Karmozd_Pardakhti'}">
                            <display:table export="true" id="tblListKarmozdGhests" uid="row" htmlId="tblListKarmozdGhests"
                                        name="karmozdGhestPaginatedList.list" partialList="true" clearStatus="true" keepStatus="false"
                                        style="margin-right:auto;margin-left:auto;margin-top:20px;width:790px" requestURI="?selectedTab=tabs-1" excludedParams="selectedTab"
                                        size="${karmozdGhestPaginatedList.fullListSize}" pagesize="${karmozdGhestPaginatedList.objectsPerPage}">
                            <c:if test="${row.type == 'CODE_MOVAGHAT' && row.blackList==null}">
                            <display:setProperty name="pagination.pagenumber.param">${page}</display:setProperty>
                            <display:column style="background:#ffcc33" title="نمایش" media="html">
                                <a target="_blank"
                                   href="/editShowFormReadOnly?pishnehad.id=${row.ghest.ghestBandi.bimename.pishnehad.id}">نمایش</a>
                            </display:column>
                            <display:column  style="background:#ffcc33" title="ردیف">${row_rowNum}</display:column>
                            <display:column  style="background:#ffcc33" title="کارمزد قسط (ریال)">-</display:column>
                            <display:column  style="background:#ffcc33" title="مبلغ پرداخت شده از کارمزد قسط(ریال)" property="ghest.karmozdPaidString"/>
                            <display:column  style="background:#ffcc33" title="کارمزد قابل پرداخت (ریال" >-</display:column>
                            <display:column  style="background:#ffcc33" title="مقدار بدهی (ریال)" property="ghest.credebit.amount"></display:column>
                            <display:column  style="background:#ffcc33" title="مبلغ پرداخت شده از بدهی (ریال)" property="khateSanad.amount"></display:column>
                            <display:column  style="background:#ffcc33" title="سرمایه پایه فوت" property="ghest.ghestBandi.bimename.pishnehad.estelam.sarmaye_paye_fot"></display:column>
                            <display:column  style="background:#ffcc33" title="حق بیمه سال اول (ریال)" property="ghest.ghestBandi.bimename.haghBimeSaleAval"></display:column>
                            <display:column  style="background:#ffcc33" title="تاریخ سر رسید" property="ghest.sarresidDate"></display:column>
                            <display:column  style="white-space: nowrap;background:#ffcc33" title="شماره بیمه نامه"  >
                            ${row.ghest.ghestBandi.bimename.shomare}
                            </display:column>
                            <display:column  style="background:#ffcc33" title="شناسه بدهی" property="khateSanad.bedehiCredebit.shenaseCredebit"></display:column>
                            <display:column  style="background:#ffcc33" title="شناسه اعتبار" property="khateSanad.etebarCredebit.shenaseCredebit"></display:column>
                            <display:column  style="background:#ffcc33" title="شناسه پرداخت" property="khateSanad.bedehiCredebit.shomareMoshtari"></display:column>
                            <display:column  style="white-space: nowrap;background:#ffcc33" title="نام و کد نماینده" property="karmozdNamayande.namayande.name_kod"></display:column>
                            <display:column  style="white-space: nowrap;background:#ffcc33" title="نوع نمایندگی" property="karmozdNamayande.namayande.namayandeTypeFarsi"></display:column>
                            <display:column  style="background:#ffcc33" title="سال بیمه ای قسط" >${row.ghest.ghestBandi.saleBimeiInt + 1}</display:column>
                            <%--<display:column title="سال جاری بیمه نامه" >${row.ghest.ghestBandi.bimename.currentSaleBimei + 1}</display:column>--%>
                            <display:column  style="background:#ffcc33" title="روش پرداخت حق‌بيمه">
                                <c:choose>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'mah'}">ماهانه</c:when>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == '3mah'}">سه ماهه</c:when>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == '6mah'}">شش ماه</c:when>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'sal'}">سالانه</c:when>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'yekja'}">یکجا</c:when>
                                </c:choose>
                            </display:column>
                            <display:column style="white-space: nowrap; background:#ffcc33" title="نوع کارمزد" >
                                <c:choose>
                                    <c:when test="${row.type == 'ADI'}">عادی</c:when>
                                    <c:when test="${row.type == 'DAR_ENTEZAR_SARRESID'}">در انتظار سر رسید</c:when>
                                    <c:when test="${row.type == 'TALIGHI'}">تعلیقی</c:when>
                                    <c:when test="${row.type == 'KARMOZD_BARGASHTI'}">کارمزد برگشتی</c:when>
                                    <c:when test="${row.type == 'PARDAKHTE_CODE_MOVAGHATI'}">پرداخت کدموقتی</c:when>
                                    <c:when test="${row.type == 'TAGHIR_CODE_DAEM'}">تغییر کد نمایندگی دائم به دائم</c:when>
                                    <c:when test="${row.type == 'CODE_MOVAGHAT'}">کد موقت</c:when>
                                    <c:when test="${row.type == 'TAGHIRAT'}">الحاقیه تغییرات</c:when>
                                    <c:when test="${row.type == 'VOSULI'}">کارمزد وصولی</c:when>
                                    <c:when test="${row.type == 'POOSHESH_EZAFI'}">کارمزد پوشش های اضافی</c:when>
                                    <c:when test="${row.type == 'SENIOR'}">کارمزد نمایندگان ارشد</c:when>
                                    <c:when test="${row.type == 'TADILI'}">تعدیلی</c:when>
                                </c:choose>
                            </display:column>
                            <display:column style="background:#ffcc33;" title="تعرفه" property="tarefeFarsi"/>
                            <display:column style="background:#ffcc33;" title="لیست سیاه"  property="hasBlackList"/>
                        </c:if>

                            <c:if test="${(row.type != 'CODE_MOVAGHAT') || row.type == 'PARDAKHTE_CODE_MOVAGHATI' && row.blackList == null}">
                            <display:column style="background:white" media="html" title="نمایش" >
                                <a target="_blank"
                                   href="/editShowFormReadOnly?pishnehad.id=${row.ghest.ghestBandi.bimename.pishnehad.id}">نمایش</a>
                            </display:column>
                            <display:column  style="background:white" title="ردیف">${row_rowNum}</display:column>
                            <display:column  style="background:white" title="کارمزد قسط (ریال)">${row.ghest.karmozdRealString}</display:column>
                            <display:column  style="background:white" title="مبلغ پرداخت شده از کارمزد قسط(ریال)" >${row.ghest.karmozdPaidString}</display:column>
                            <display:column  style="background:white" title="کارمزد قابل پرداخت (ریال" >${row.karmozdAMountString}</display:column>
                            <display:column  style="background:white" title="مقدار بدهی (ریال)" property="ghest.credebit.amount"></display:column>
                            <display:column  style="background:white" title="مبلغ پرداخت شده از بدهی (ریال)" property="khateSanad.amount"></display:column>
                            <display:column  style="background:white" title="سرمایه پایه فوت" property="ghest.ghestBandi.bimename.pishnehad.estelam.sarmaye_paye_fot"></display:column>
                            <display:column  style="background:white" title="حق بیمه سال اول (ریال)" property="ghest.ghestBandi.bimename.haghBimeSaleAval"></display:column>
                            <display:column  style="background:white" title="تاریخ سر رسید" property="ghest.sarresidDate"></display:column>
                            <display:column  style="white-space: nowrap;background:white" title="شماره بیمه نامه" >
                                ${row.ghest.ghestBandi.bimename.shomare}
                            </display:column>
                            <display:column  style="background:white" title="شناسه بدهی" property="khateSanad.bedehiCredebit.shenaseCredebit"></display:column>
                            <display:column  style="background:white" title="شناسه اعتبار" property="khateSanad.etebarCredebit.shenaseCredebit"></display:column>
                            <display:column  style="background:white" title="شناسه پرداخت" property="khateSanad.bedehiCredebit.shomareMoshtari"></display:column>
                            <display:column  style="white-space: nowrap;background:white" title="نام و کد نماینده" property="karmozdNamayande.namayande.name_kod"></display:column>
                            <display:column  style="white-space: nowrap;background:white" title="نوع نمایندگی" property="karmozdNamayande.namayande.namayandeTypeFarsi"></display:column>
                            <display:column  style="background:white" title="سال بیمه ای قسط" >${row.ghest.ghestBandi.saleBimeiInt + 1}</display:column>
                            <%--<display:column title="سال جاری بیمه نامه" >${row.ghest.ghestBandi.bimename.currentSaleBimei + 1}</display:column>--%>
                            <display:column  style="background:white" title="روش پرداخت حق‌بيمه">
                                <c:choose>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'mah'}">ماهانه</c:when>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == '3mah'}">سه ماهه</c:when>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == '6mah'}">شش ماه</c:when>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'sal'}">سالانه</c:when>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'yekja'}">یکجا</c:when>
                                </c:choose>
                            </display:column>
                            <display:column style="white-space: nowrap; background:white" title="نوع کارمزد" >
                                <c:choose>
                                    <c:when test="${row.type == 'ADI'}">عادی</c:when>
                                    <c:when test="${row.type == 'DAR_ENTEZAR_SARRESID'}">در انتظار سر رسید</c:when>
                                    <c:when test="${row.type == 'TALIGHI'}">تعلیقی</c:when>
                                    <c:when test="${row.type == 'KARMOZD_BARGASHTI'}">کارمزد برگشتی</c:when>
                                    <c:when test="${row.type == 'PARDAKHTE_CODE_MOVAGHATI'}">پرداخت کدموقتی</c:when>
                                    <c:when test="${row.type == 'TAGHIR_CODE_DAEM'}">تغییر کد نمایندگی دائم به دائم</c:when>
                                    <c:when test="${row.type == 'CODE_MOVAGHAT'}">کد موقت</c:when>
                                    <c:when test="${row.type == 'TAGHIRAT'}">الحاقیه تغییرات</c:when>
                                    <c:when test="${row.type == 'VOSULI'}">کارمزد وصولی</c:when>
                                    <c:when test="${row.type == 'POOSHESH_EZAFI'}">کارمزد پوشش های اضافی</c:when>
                                    <c:when test="${row.type == 'SENIOR'}">کارمزد نمایندگان ارشد</c:when>
                                    <c:when test="${row.type == 'TADILI'}">تعدیلی</c:when>
                                </c:choose>
                            </display:column>
                            <display:column style="background:white;" title="تعرفه" property="tarefeFarsi"/>
                                <display:column style="background:white;" title="لیست سیاه"  property="hasBlackList"/>

                        </c:if>
                            <c:if test="${row.blackList != null}">
                            <display:column style="background:#cccccc" media="html" title="نمایش">
                                <a target="_blank"
                                   href="/editShowFormReadOnly?pishnehad.id=${row.ghest.ghestBandi.bimename.pishnehad.id}">نمایش</a>
                            </display:column>
                            <display:column style="background:#cccccc" title="ردیف">${row_rowNum}</display:column>
                            <display:column style="background:#cccccc" title="کارمزد قسط (ریال)" property="ghest.karmozdRealString"/>
                            <display:column style="background:#cccccc;" title="مبلغ پرداخت شده از کارمزد قسط(ریال)" property="ghest.karmozdPaidString"/>
                            <display:column style="background:#cccccc;" title="کارمزد قابل پرداخت (ریال" property="karmozdAMountString"/>
                            <display:column style="background:#cccccc;" title="مقدار بدهی (ریال)" property="ghest.credebit.amount"></display:column>
                            <display:column style="background:#cccccc;" title="مبلغ پرداخت شده از بدهی (ریال)" property="khateSanad.amount"></display:column>
                            <display:column style="background:#cccccc;" title="سرمایه پایه فوت" property="ghest.ghestBandi.bimename.pishnehad.estelam.sarmaye_paye_fot"></display:column>
                            <display:column style="background:#cccccc;" title="حق بیمه سال اول (ریال)" property="ghest.ghestBandi.bimename.haghBimeSaleAval"></display:column>
                            <display:column style="background:#cccccc;" title="تاریخ سر رسید" property="ghest.sarresidDate"></display:column>
                            <display:column style="background:#cccccc;white-space: nowrap;" title="شماره بیمه نامه" >
                                ${row.ghest.ghestBandi.bimename.shomare}
                            </display:column>
                            <display:column style="background:#cccccc;" title="شناسه بدهی" property="khateSanad.bedehiCredebit.shenaseCredebit"></display:column>
                            <display:column style="background:#cccccc;" title="شناسه اعتبار" property="khateSanad.etebarCredebit.shenaseCredebit"></display:column>
                            <display:column style="background:#cccccc;" title="شناسه پرداخت" property="khateSanad.bedehiCredebit.shomareMoshtari"></display:column>
                            <display:column style="background:#cccccc;white-space: nowrap;" title="نام و کد نماینده" property="karmozdNamayande.namayande.name_kod"></display:column>
                            <display:column style="background:#cccccc;white-space: nowrap;" title="نوع نمایندگی" property="karmozdNamayande.namayande.namayandeTypeFarsi"></display:column>
                            <display:column style="background:#cccccc;" title="سال بیمه ای قسط" >${row.ghest.ghestBandi.saleBimeiInt + 1}</display:column>
                            <%--<display:column style="background:#999999;" title="سال جاری بیمه نامه">${row.ghest.ghestBandi.bimename.currentSaleBimei + 1}</display:column>--%>
                            <display:column style="background:#cccccc;" title="روش پرداخت حق‌بيمه">
                                <c:choose>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'mah'}">ماهانه</c:when>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == '3mah'}">سه ماهه</c:when>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == '6mah'}">شش ماه</c:when>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'sal'}">سالانه</c:when>
                                    <c:when test="${row.ghest.ghestBandi.bimename.pishnehad.estelam.nahve_pardakht_hagh_bime == 'yekja'}">یکجا</c:when>
                                </c:choose>
                            </display:column>
                            <display:column style="background:#cccccc; white-space: nowrap;" title="نوع کارمزد" >
                                <c:choose>
                                    <c:when test="${row.type == 'ADI'}">عادی</c:when>
                                    <c:when test="${row.type == 'DAR_ENTEZAR_SARRESID'}">در انتظار سر رسید</c:when>
                                    <c:when test="${row.type == 'TALIGHI'}">تعلیقی</c:when>
                                    <c:when test="${row.type == 'KARMOZD_BARGASHTI'}">کارمزد برگشتی</c:when>
                                    <c:when test="${row.type == 'PARDAKHTE_CODE_MOVAGHATI'}">پرداخت کدموقتی</c:when>
                                    <c:when test="${row.type == 'TAGHIR_CODE_DAEM'}">تغییر کد نمایندگی دائم به دائم</c:when>
                                    <c:when test="${row.type == 'CODE_MOVAGHAT'}">کد موقت</c:when>
                                    <c:when test="${row.type == 'TAGHIRAT'}">الحاقیه تغییرات</c:when>
                                    <c:when test="${row.type == 'VOSULI'}">کارمزد وصولی</c:when>
                                    <c:when test="${row.type == 'POOSHESH_EZAFI'}">کارمزد پوشش های اضافی</c:when>
                                    <c:when test="${row.type == 'SENIOR'}">کارمزد نمایندگان ارشد</c:when>
                                    <c:when test="${row.type == 'TADILI'}">تعدیلی</c:when>
                                </c:choose>
                            </display:column>
                            <display:column style="background:#cccccc;" title="تعرفه" property="tarefeFarsi"/>
                            <display:column style="background:#cccccc;" title="لیست سیاه"  property="hasBlackList"/>
                        </c:if>
                        </display:table>
                        </c:if>
                    </div><br/>
                    <div align="left" style=" width:110px;">
                        <div align="right" style="font-size:small;">راهنمای رنگ ها :</div>
                        <hr align="right" width="90px"/>
                        <div align="right"style="font-size:xx-small;" >&nbsp&nbsp;<label style="border-color:black; background:#ffcc33">&nbsp;&nbsp;&nbsp;</label> کد موقت</div>
                        <div align="right"style="font-size:xx-small;" >&nbsp&nbsp;<label style="border-color:black; background:#cccccc">&nbsp;&nbsp;&nbsp;</label> لیست سیاه</div>
                    </div>
                </div>
             </c:if>
             <c:if test="${karmozd.type!='Karmozd_Tashvighi_Vosuli' || (karmozd.step=='2')}">

                <div class=content id="content_2" style="display:none;">
                    <div dir="rtl" class=expandableTitleBar style="text-align:right !important;">
                        <form id="uploadform" action="/doUploadForKarmozdTadilat" method="POST"
                              enctype="multipart/form-data">
                            <input type="hidden" name="karmozd.id" value="${karmozd.id}">
                            <table align="center" width="" cellpadding="0" cellspacing="0"
                                   style="border-spacing:1px;margin:0 auto;" border="0">
                                <tr>
                                    <td colspan="2"><h1>لطفا یک فایل را بارگذاری نمایید:</h1></td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="file" name="upload"/>
                                    </td>
                                    <td align="center">
                                        <input type="submit" value="ارسال"/>
                                            <%--<input type="button" onclick="window.location='/loginUser.action'" value="بازگشت"/>--%>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <display:table export="true" id="tblListKarmozdTadilat" uid="row1" htmlId="tblListKarmozdTadilat"
                                   name="karmozdTadilatPaginatedList.list" partialList="true" clearStatus="true"
                                   keepStatus="false"
                                   style="margin-right:auto;margin-left:auto;margin-top:20px;"
                                   requestURI="?selectedTab=tab-2" excludedParams="selectedTab"
                                   size="${karmozdTadilatPaginatedList.fullListSize}"
                                   pagesize="${karmozdTadilatPaginatedList.objectsPerPage}">
                        <c:if test="${row1.karmozdNamayande==null}">
                            <display:column style="background:#f08080;" title="ردیف">
                                ${row1_rowNum+((karmozdTadilatPaginatedList.pageNumber-1)*karmozdTadilatPaginatedList.objectsPerPage)}
                            </display:column>
                            <display:column style="background:#f08080;" title="رديف در فايل اكسل" property="xlRadif"/>
                            <display:column style="background:#f08080;" title="كد نماينده" property="xlCodeNamayande"/>
                            <display:column style="background:#f08080;" title="كارمزد2-صدور1"
                                            property="xlKarmozde2Sodore1"/>
                            <display:column style="background:#f08080;" title="مبلغ" property="xlMablagh"/>
                            <display:column style="background:#f08080;" title="يبمه نامه1" property="xlBimename1"/>
                            <display:column style="background:#f08080;" title="شماره بيمه نامه"
                                            property="xlShomareBimename"/>
                            <display:column style="background:#f08080;" title="عنوان" property="xlOnvan"/>
                            <display:column style="background:#f08080;" title="شرح" property="xlSharh"/>
                        </c:if>
                        <c:if test="${row1.karmozdNamayande!=null}">
                            <display:column style="background:#00cc66;" title="ردیف">
                                ${row1_rowNum+((karmozdTadilatPaginatedList.pageNumber-1)*karmozdTadilatPaginatedList.objectsPerPage)}
                            </display:column>
                            <display:column style="background:#00cc66;" title="رديف در فايل اكسل" property="xlRadif"/>
                            <display:column style="background:#00cc66;" title="كد نماينده" property="xlCodeNamayande"/>
                            <display:column style="background:#00cc66;" title="كارمزد2-صدور1"
                                            property="xlKarmozde2Sodore1"/>
                            <display:column style="background:#00cc66;" title="مبلغ" property="xlMablagh"/>
                            <display:column style="background:#00cc66;" title="يبمه نامه1" property="xlBimename1"/>
                            <display:column style="background:#00cc66;" title="شماره بيمه نامه"
                                            property="xlShomareBimename"/>
                            <display:column style="background:#00cc66;" title="عنوان" property="xlOnvan"/>
                            <display:column style="background:#00cc66;" title="شرح" property="xlSharh"/>
                        </c:if>
                    </display:table><br/>

                    <div align="left" style=" width:110px;">
                        <div align="right" style="font-size:small; ">راهنمای رنگ ها :</div>
                        <hr align="right" width="90px"/>
                        <div align="right" style="font-size:xx-small;">&nbsp;&nbsp;<label style="background:#00cc66;">
                            &nbsp;&nbsp;&nbsp;</label>نمایندگی موجود
                        </div>
                            <%--</br>--%>
                        <div align="right" style="font-size:xx-small;">&nbsp;&nbsp;<label style="background:#f08080;">
                            &nbsp;&nbsp;&nbsp;</label>نمایندگی ناموجود
                        </div>
                    </div>
                </div>
                 <div class="content" id="content_3" style="display:none;">
                         <%--<jsp:include page="/jsp/management/page_karmozdForNamayande.jsp"/>--%>
                     <div class="expandableTitleBar" id="expandableAsl">
                         <p class="heading">
                             <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                             جستجو
                         </p>

                         <div class="content" style="display:none;" id="searchAslContent3">
                             <%@include file="/jsp/management/karmozd/searchKarmozdForNamayande.jsp" %>
                         </div>
                     </div>
                     <form action="karmozdPayment.action?karmozd.id=${karmozd.id}" method="POST"
                           name="namayandeKarmozdFrm" id="n_k_frm">
                         <display:table export="true" id="tblListKarmozdNamayande" uid="row2"
                                        htmlId="tblListKarmozdNamayande"
                                        name="paginatedListKarmozdNamayande.list" partialList="true" clearStatus="true"
                                        keepStatus="false"
                                        style="margin-right:auto;margin-left:auto;margin-top:20px; "
                                        requestURI="?selectedTab=tabs-3" excludedParams="selectedTab"
                                        size="${ paginatedListKarmozdNamayande.fullListSize}"
                                        pagesize="${ paginatedListKarmozdNamayande.objectsPerPage}">
                             <display:column title="ردیف">${row2_rowNum}</display:column>
                             <display:column title="نام و کد نماینده" style="white-space: nowrap;"
                                             property="namayande.name_kod"/>
                             <display:column title="مبلغ کارمزد اقساط پرداختی (ریال)" property="amountPardakhtiString"/>
                             <display:column title="مبلغ تعدیلی (ریال)" property="tadiliAmountString"/>
                             <%--<display:column title="مبلغ قابل پرداخت (ریال)" property="finalAmount"/>--%>
                             <display:column title="مبلغ کارمزد پرداخت های لیست سیاه (ریال)" property="amountBlack"/>
                             <display:column
                                     title="نرخ مالیات بر ارزش افزوده">%<%=(long) (KarmozdCalculate.getNerkheAfzode(((Karmozd) request.getAttribute("karmozd")).getTaTarikhVosoli(), null) * 100)%>
                             </display:column>
                             <display:column
                                     title="نرخ مالیات تکلیفی">%<%=(long) (KarmozdCalculate.getNerkheTaklifi() * 100)%>
                             </display:column>
                             <display:column title="مبلغ قابل پرداخت (ریال)" property="amountForPaymentWithMaliat"/>
                             <display:column title="بدهی پیشین (ریال)" property="bedehiAmountString"/>
                             <display:column title="مبلغ نهایی (ریال" property="mablaghNahaei"/>
                             <display:column title="وضعیت ">${row2.stateFarsi}</display:column>
                         </display:table>
                         <%
                             if (((PaginatedListImpl<KarmozdNamayande>) request.getAttribute("paginatedListKarmozdNamayande")).getFullListSize() > 0)
                             {
                         %>
                         <c:if test="${karmozd.status!='final'}">
                             <input type="submit" id="sbmt_n_k_frm" value="پرداخت"/>
                         </c:if>
                         <%
                             }
                         %>
                     </form>
                 </div>
            </c:if>
        </sec:authorize>
        <%--<c:if test="${karmozd.type!='Karmozd_Vosuli' && karmozd.type!='Karmozd_Seniors'}">--%>
        <c:if test="${karmozd.type!='Karmozd_Tashvighi_Vosuli' || (karmozd.step=='2')}">
            <div  class="content" id="content_4" <sec:authorize ifAnyGranted="ROLE_KARMOZD">style="display:none;"</sec:authorize>>
                <display:table export="true" id="tblListAllPayment" uid="row3" htmlId="tblListAllPayment"
                                name="allPaymentPaginatedList.list" partialList="true" clearStatus="true" keepStatus="false"
                                style="margin-right:auto;margin-left:auto;margin-top:20px; " requestURI="?selectedTab=tabs-4" excludedParams="selectedTab"
                                size="${ allPaymentPaginatedList.fullListSize}" pagesize="${ allPaymentPaginatedList.objectsPerPage}">
                    <display:column title="ردیف">${row3_rowNum}</display:column>
                    <display:column title="نوع رکورد" property="type"></display:column>
                    <c:if test="${karmozd.type == 'Karmozd_Pardakhti' || karmozd.type == 'Karmozd_Seniors'}">
                        <display:column title="کارمزد قسط(ریال)" property="karmozdReal"></display:column>
                    </c:if>
                    <c:if test="${karmozd.type == 'Karmozd_Vosuli'}">
                        <display:column title="کارمزد وصولی قسط (ریال)" property="karmozdReal"></display:column>
                    </c:if>
                    <c:if test="${karmozd.type == 'Karmozd_Pooshesh_Ezafi'}">
                        <display:column title="کارمزد پوششهای اضافی قسط (ریال)" property="karmozdReal"></display:column>
                    </c:if>
                    <c:if test="${karmozd.type == 'Karmozd_Pardakhti'}">
                        <display:column title="مبلغ پرداخت شده از کارمزد(ریال)" property="karmozdPaid"></display:column>
                    </c:if>
                    <display:column title="کارمزد قابل پرداخت/مبلغ تعدیلات (ریال)" property="mablaghPardakhti"></display:column>
                    <display:column title="مقدار بدهی (ریال)" property="meghdarBedehi"></display:column>
                    <display:column title="مبلغ پرداخت شده از بدهی (ریال)" property="mablaghPardakhtiAzBedehi"></display:column>
                    <display:column title="شناسه پرداخت" property="shomareMoshtari"></display:column>
                    <display:column title="سال بیمه ای" property="saleBimei"></display:column>
                    <display:column title="تاریخ سررسید" property="sarresidDate"></display:column>
                    <display:column title="شماره بیمه نامه" style="white-space: nowrap;" property="shomareBimename"></display:column>
                    <display:column title="بیمه گذار"  style="white-space: nowrap;" >${row3.bimeGozarFirstName} ${row3.bimeGozarLastName}</display:column>
                    <display:column title="سرمایه پایه فوت (ریال)"    property="sarmayePayeFot"></display:column>
                    <display:column title="نحوه پرداخت حق بیمه" >
                        <c:choose>
                            <c:when test="${row3.nahvePardakhtHaghBime == 'mah'}">ماهانه</c:when>
                            <c:when test="${row3.nahvePardakhtHaghBime == '3mah'}">سه ماهه</c:when>
                            <c:when test="${row3.nahvePardakhtHaghBime == '6mah'}">شش ماهه</c:when>
                            <c:when test="${row3.nahvePardakhtHaghBime == 'sal'}">سالانه</c:when>
                            <c:when test="${row3.nahvePardakhtHaghBime == 'yekja'}">یکجا</c:when>
                        </c:choose>
                    </display:column>
                    <display:column title="کد و نام نماینده " style="white-space: nowrap;">${row3.kodeNamayande} - ${row3.nameNamayande}</display:column>
                    <display:column title="نوع نمایندگی">
                        <c:choose>
                            <c:when test="${row3.typeNamayande== 'NAMAYANDE_HAGHIGHI'}">نماینده حقیقی</c:when>
                            <c:when test="${row3.typeNamayande == 'NAMAYANDE_HOGHUGHI'}">نماینده حقوقی</c:when>
                            <c:when test="${row3.typeNamayande == 'KARGOZAR_HOGHUGHI'}">کارگزار حقوقی</c:when>
                            <c:when test="${row3.typeNamayande== 'KARGOZAR_HAGHIGHI'}">کارگزار حقیقی</c:when>
                            <c:when test="${row3.typeNamayande == 'MOJTAMA'}">مجتمع بیمه ای</c:when>
                            <c:when test="${row3.typeNamayande== 'FORUSHANDE'}">نماینده فروش</c:when>
                            <c:when test="${row3.typeNamayande == 'BAJE_NAMAYANDE_HOGHUGHI'}">باجه نماینده حقوقی</c:when>
                            <c:when test="${row3.typeNamayande == 'ICD'}">ICD</c:when>
                            <c:when test="${row3.typeNamayande == 'SHOBE'}">شعبه</c:when>
                        </c:choose>
                    </display:column>
                    <display:column property="typeFa" title="نوع کارمزد" >
                        <%--<c:choose>--%>
                            <%--<c:when test="${row3.typeKarmozd == 'ADI'}">عادی</c:when>--%>
                            <%--<c:when test="${row3.typeKarmozd == 'DAR_ENTEZAR_SARRESID'}">در انتظار سر رسید</c:when>--%>
                            <%--<c:when test="${row3.typeKarmozd == 'TALIGHI'}">تعلیقی</c:when>--%>
                            <%--<c:when test="${row3.typeKarmozd == 'KARMOZD_BARGASHTI'}">کارمزد برگشتی</c:when>--%>
                            <%--<c:when test="${row3.typeKarmozd == 'PARDAKHTE_CODE_MOVAGHATI'}">پرداخت کدموقتی</c:when>--%>
                            <%--<c:when test="${row3.typeKarmozd == 'CODE_MOVAGHAT'}">کد موقت</c:when>--%>
                            <%--<c:when test="${row3.typeKarmozd == 'SENIOR'}">کارمزد نمایندگان ارشد</c:when>--%>
                            <%--<c:when test="${row3.typeKarmozd == 'TADILI'}">تعدیلی</c:when>--%>
                            <%--<c:when test="${row3.typeKarmozd == 'TAGHIRAT'}">تغییرات</c:when>--%>
                            <%--<c:when test="${row3.typeKarmozd == 'POOSHESH_EZAFI'}">پوشش های اضافی</c:when>--%>
                            <%--<c:when test="${row3.typeKarmozd == 'VOSULI'}">وصولی</c:when>--%>
                            <%--<c:when test="${row3.typeKarmozd == 'SENIOR'}">نماینده ارشد</c:when>--%>
                        <%--</c:choose>--%>
                    </display:column>
                    <display:column title="تعرفه">
                        <c:choose>
                            <c:when test="${row3.tarefe == 'NAVADO_YEK'}">تعرفه 01/01/1391</c:when>
                            <c:when test="${row3.tarefe == 'YEK_YEK_NAVAD_DO'}">تعرفه 01/01/1392</c:when>
                            <c:when test="${row3.tarefe == 'YEK_SE_NAVAD_DO'}">تعرفه 01/03/1392</c:when>
                            <c:when test="${row3.tarefe == 'YEK_CHAHAR_NAVAD_DO'}">تعرفه 01/04/1392</c:when>
                        </c:choose>
                    </display:column>
                    <display:column title="عنوان" property="onvan"></display:column>
                    <display:column title="شرح" property="description"></display:column>
                </display:table>
            </div>
        </c:if>
        <%--</c:if>--%>

        </div>  </br>
        <input type="button" onclick="window.location='/karmozdPanel.action'" style="float:left; margin-left:2px; margin-top:2px;" value="بازگشــــت"/>
    </div>
<br class="clear"/>
</body>
</html>