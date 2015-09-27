<%--
  Created by IntelliJ IDEA.
  User: Arron0
  Date: Sep 6, 2011
  Time: 5:53:55 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>




<html>
<head><title>ليست اسناد</title></head>

<body>
<script>
    $(function(){
        $(".jtable tr").click(function(){
            $(this).children("td").toggleClass("ui-state-highlight");
        });
    });
</script>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<%@include file="/jsp/josteju/searchBazaryabSanam.jsp"%>
    <div class="expandableTitleBar" id="expandableAsl">
        <p class="heading">
            <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                            جستجو
        </p>
        <div class="content" style="display:none;" id="searchAslContent1">
            <form action="/fin/viewKhateSanadHa" method="get">
                <table dir="rtl" class="inputList">
                    <tr>
                        <td>شماره سند :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:textfield name="shomareSanad" id="shomareSanad" theme="simple"/>
                        </td>
                        <script type="text/javascript">
                        </script>
                        <td>مبلغ سند :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="amountSanad" id="amountSanad" class="digitSeparators text-input"/>
                            <input type="hidden" name="searchShode" id="searchShode" value="yes"/>
                        </td>
                    </tr>
                    <tr>
                        <td>تاريخ ثبت سند(از) :</td>
                        <td>
                            <input type="text" name="createdDateAz" id="createdDateAz" class="datePkr" readonly="true"/>
                            <span class="noThing">&nbsp;</span>
                        </td>
                        <td>تاريخ ثبت سند(تا) :</td>
                        <td>
                            <input type="text" name="createdDateTa" id="createdDateTa" class="datePkr" readonly="true"/>
                            <span class="noThing">&nbsp;</span>
                        </td>
                    </tr>
                    <tr>
                        <td>نوع سند :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:select list="#{'':'قبض رسيد/پرداخت','GHABZE_RESID':'قبض رسيد', 'PARDAKHT':'پرداخت'}"
                               name="noeSanad" id="noeSanad" label="" key="noeSanad" theme="simple"/>
                        </td>
                        <td>وضعيت سند :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:select list="#{'':'دائم/موقت','MOVAGHAT':'موقت', 'DAEM':'دائم'}"
                               name="vaziat" id="vaziat" label="" key="vaziat" theme="simple"/>
                        </td>
                    </tr>
                    <tr>
                        <td>نوع اعتبار :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:select list="#{'':'تمام موارد','DARYAFTE_FISH':'دريافت فيش', 'DARYAFTE_ELECTRONICI':'دريافت الکترونيکي', 'AZ_MAHALLE_BAZKHARID':'از محل بازخريد',
                                                'ETEBAR_VAM':'اعتبار وام','ETEBAR_BARDASHT':'اعتبار برداشت','DARYAFTE_CHECK':'دريافت چک','PARDAKHT_KARMOZD':'پرداخت کارمزد',
                                                'MOSHAREKAT':'مشارکت','ETEBAR_KHESARAT':'اعتبار خسارت','ELHAGHIE_EZAFI':'الحاقيه اضافي','ELHAGHIE_BARGASHTI':'الحاقيه برگشتي','PISHPARDAKHT':'پيش پرداخت',
                                                'VEHICLE_HAGHBIME_BARGASHTI':'حق بيمه برگشتي (اتومبيل)','ELHAGHIE_BARGASHTI_ETEBAR':'اعتبار الحاقيه برگشتي','ETEBAR_EBTAL':'اعتبار ابطال',
                                                'VEHICLE_DARYAFT_ELECTRONICI':'دريافت الکترونيکي (اتومبيل)', 'HESAB_FI_MA_BEYN':'حساب في مابين', 'PARDAKHT_SHENASEDAR':'پرداخت شناسه دار','DARMAN_ELHAGHIYE_BARGASHTI':'الحاقيه برگشتي درمان',
                                                'VEHICLE_KHESARAT':'خسارت (اتومبيل)','AZ_MAHALE_HAZINE_BIME_DARAYIHA':'از محل هزينه بيمه دارايي ها','ETEBAR_DARMAN_KHANEVADE':'اعتبار درمان خانواده'}"
                               name="etebarType" id="etebarType" label="" key="etebarType" theme="simple"/>
                        </td>
                        <td>نوع بدهي :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:select list="#{'':'تمام موارد','GHEST':'قسط', 'PARDAKHTE_TANKHAH':'پرداخت تنخواه', 'PARDAKHTE_CHECK':'پرداخت چک', 'GHEST_VAM':'قسط وام','VEHICLE_HAGHBIME':'حق بيمه (اتومبيل)',
                                            'VEHICLE_HAGHBIME_ELECTRONICI':'حق بيمه الکترونيکي (اتومبيل)','ACH':'پرداخت پايا','DARMAN_HAGHBIME':'حق بيمه درمان'}"
                               name="bedehiType" id="bedehiType" label="" key="bedehiType" theme="simple"/>
                        </td>
                    </tr>
                    <tr>
                        <td>شناسه پرداخت اعتبار :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shomareMoshtariEtebar" id="shomareMoshtariEtebar"/>
                        </td>
                        <td>شناسه پرداخت بدهي</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shomareMoshtariBedehi" id="shomareMoshtariBedehi"/>
                        </td>
                    </tr>
                    <tr>
                        <td>شناسه اعتبار :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shenaseEtebar" id="shenaseEtebar" />
                        </td>
                        <td>شناسه بدهي :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shenaseBedehi" id="shenaseBedehi" />
                        </td>
                    </tr>
                    <tr>
                        <td>مبلغ اعتبار :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="amountEtebar" id="amountEtebar" class="digitSeparators" />
                        </td>
                        <td>مقدار بدهي :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="amountBedehi" id="amountBedehi" class="digitSeparators" />
                        </td>
                    </tr>

                    <tr>
                        <td>شماره بيمه نامه :
                        </td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shoBimenameBedehi" id="shoBimenameBedehi" />
                        </td>
                        <td>شماره سند بانک :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shomareSanadBank" id="shomareSanadBank" />
                        </td>
                    </tr>
                    <tr>
                        <td><label>نمايندگي</label></td>
                        <td>
                            <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('namayandeId','namayandeName', '');" style="float:left;" title="جستجو"></span>
                            <input type="hidden" name="namayandeId" id="namayandeId" />
                            <input type="text" name="namayandeName" id="namayandeName"  readonly="true"/>
                        </td>
                        <td><label>رشته</label></td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <select name="subSystemName" id="subSystemName"   >
                                <option  value="" >انتخاب كنيد</option>
                                <option  value="1" style="" >آتش سوزي</option>
                                <option  value="2" style="" >باربري</option>
                                <option  value="3" style="" >بدنه</option>
                                <option  value="4" style="" >ثالث</option>
                                <option  value="20" style="" >اتومبيل</option>
                                <option  value="5" style="" >حوادث انفرادي</option>
                                <option  value="6" style="" >درمان</option>
                                <option  value="7" style="" >عمر انفرادي</option>
                                <option  value="8" style="" >عمر و حوادث گروهي</option>
                                <option  value="9" style="" >مسئوليت</option>
                                <option  value="10" style="">مهندسي</option>
                                <option  value="11" style="">نفت و انرژي</option>
                                <option  value="12" style="">درمان مسافرتي</option>
                            </select>

                        </td>
                    </tr>
                    <tr>
                        <td><label>بازارياب</label></td>
                        <td>
                            <span class="help ui-icon ui-icon-search" onclick="fillBazaryabSanam('bsId','bsName', '');" style="float:left;" title="جستجو"></span>
                            <input type="hidden" name="bazaryabSanamId" id="bsId" />
                            <input type="text" name="bname" id="bsName"  readonly="true"/>
                        </td>
                        <td>شماره فيش: </td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shomareFish" id="shomareFishId"/>
                        </td>
                    </tr>
                    <tr>
                        <td>شماره چک: </td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shomareCheck" id="shomareCheck"/>
                        </td>
                        <td><label>سيستم</label></td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <select name="SystemName" id="SystemName">
                                <option  value = "" >انتخاب كنيد</option>
                                <option  value = "SIMAB"   style="" >سيماب</option>
                                <option  value = "VEHICLE" style="" >سايبون</option>
                                <option  value = "SIB"     style="" >سيب</option>
                                <option  value = "DARMAN"  style="" >درمان خانواده</option>

                            </select>

                        </td>
                      </tr>
                      <tr>
                        <td>
                            <script type="text/javascript">
                                function clearSeachFrom_b()
                                {
                                    $('#shoBimenameBedehi').val('');
                                    $('#shoBimenameEtebar').val('');
                                    $('#shomareSanad').val('');
                                    $('#createdDateTa').val('');
                                    $('#createdDateAz').val('');
                                    $('#amountSanad').val('');
                                    $('#amountBedehi').val('');
                                    $('#amountEtebar').val('');
                                    $('#shomareMoshtariBedehi').val('');
                                    $('#shomareMoshtariEtebar').val('');
                                    $('#shenaseEtebar').val('');
                                    $('#shenaseBedehi').val('');
                                    $('#shomareSanadBank').val('');
                                    $('#namayandeId').val('');
                                    $('#namayandeName').val('');
                                    $('#subSystemName').val('');
                                    $('#shomareCheck').val('');
                                    $('#shomareFishId').val('');
                                    $('#bsId').val('');
                                    $('#bsName').val('');
                                }

                                function printFish(fishId,bankFish,credebitTypeFarsi){
                                    if (credebitTypeFarsi == "پرداخت شناسه دار"){

                                        if (bankFish.contains('2177777733') || bankFish.contains('4757575763'))
                                            window.open('/fin/printeFishBankMellat?credebitReport.id='+fishId);
                                        else if (bankFish.contains('17038494') || bankFish.contains('17123130'))
                                            window.open('/fin/printeFishBankTejarat?credebitReport.id='+fishId);
                                        else if (bankFish.contains('0200234164006') || bankFish.contains('81011989'))
                                            window.open('/fin/printeFishBankParsian?credebitReport.id='+fishId);
                                        else
                                            alertMessage("قابليت پرينت براي اين رکورد وجود ندارد");

                                    }else{
                                        alertMessage("قابليت پرينت براي اين رکورد وجود ندارد");
                                    }
                                }

                                function deleteSanad(sanadId){

                                    $.ajax({
                                        type: "POST",
                                        async: false,
                                        url: "/fin/deleteSanadOperation?formSanadId="+ sanadId,
                                        success: function (msg) {
                                            //document.forms[0].submit();
                                            window.open(window.location.pathname,"_self");
                                        }
                                    });
                                }


//                                // Initialize the plugin with no custom options
//                                $(document).ready(function () {
//                                    // None of the options are set
//                                    $("#smoothScrollaasn").smoothDivScroll({
//                                        autoScrollingMode: "onStart",
//                                        hotSpotScrollingInterval: 45,
//                                        hotSpotMouseDownSpeedBooster: 5,
//                                        hotSpotScrollingStep: 30
//                                    });
//                                });

                            </script>
                            <span class="noThing">&nbsp;</span>
                            <input type="submit" value="جستجو" theme="simple"/>
                            <span class="noThing"></span>
                            <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom_b()"/>
                        </td>
                     </tr>
                </table>
            </form>
        </div>
    </div>
        <%@include file="khateSanadsDialog.jsp"%>
<s:actionmessage/>
<%--<s:actionerror/>--%>
    <div id="smoothScrollaasn" style="overflow: auto;">
        <display:table export="true" id="khateSanadListTbl" uid="row" name="khateSanadListPaginated"
                       sort="external" htmlId="khateSanadListTbl"
                       partialList="true"
                       size="${khateSanadListPaginated.fullListSize}"
                       pagesize="${khateSanadListPaginated.objectsPerPage}"
                       requestURI="" clearStatus="true" keepStatus="false"
                       excludedParams="" style="width: 100%; margin: 0 auto;">
            <c:choose>
                <c:when test="${sessionScope.daftar_id==1}">
                    <c:set var="css" value=""/>
                </c:when>

                <c:otherwise>
                    <c:set var="css" value="background:#ffffcc;"/>
                </c:otherwise>
            </c:choose>
                <display:column title="رديف" style="">${row_rowNum}</display:column>
                <display:column title="شماره سند" property="shomare_sanad" style=""/>
                <display:column title="زمان ثبت" property="zaman_sabt" style=""/>
                <display:column title="نوع سند" property="noe_sanad_str" style=""></display:column>
                <display:column title="وضعيت سند"   property="vaziat_str" style=""></display:column>
                <display:column title="مبلغ سند"  property="mablagh_khate_sanad" style=""/>
                <display:column title="شماره بيمه نامه" property="bimename"  style=""></display:column>
                <display:column title="نام بيمه گذار" property="name_bimegozar" style=""/>

                <display:column title="شناسه پرداخت اعتبار"  style="" property="shenase_pardakht_etebar"></display:column>
                <display:column title="شماره مشتري اعتبار"  style="" property="shomare_moshtari_etebar"></display:column>
                <display:column title="نوع اعتبار" property="noe_etebar_str" style=""/>
                <display:column title="شناسه پرداخت بدهي"  style="" property="shenase_pardakht_bedehi"></display:column>
                <display:column title="شماره مشتري بدهي"  style="" property="shomare_moshtari_bedehi"></display:column>
                <display:column title="نوع بدهي" property="noe_bedehi_str" style=""/>
                <display:column title="مبلغ اعتبار" property="mablagh_etebar" style=""/>
                <display:column title="مبلغ بدهي" property="mablagh_bedehi" style=""/>
                <display:column title="مانده اعتبار" property="mande_etebar" style=""/>
                <display:column title="مانده بدهي" property="mande_bedehi" style=""/>
                <display:column title="تاريخ سررسيد اعتبار" property="sarresid_date_etebar" style=""/>
                <display:column title="تاريخ سررسيد بدهي" property="sarresid_date_bedehi" style=""/>
                <display:column title="كد واحد صدور اعتبار" property="kode_vahed_sodor_etebar" style=""/>
                <display:column title="نام واحد صدور اعتبار" property="name_vahed_sodor_etebar" style=""/>
                <display:column title="كد واحد ثبت اعتبار" property="kode_vahed_sabt_etebar" style=""/>
                <display:column title="نام واحد ثبت اعتبار" property="name_vahed_sabt_etebar" style=""/>
                <display:column title="كد واحد صدور بدهي" property="kode_vahed_sodor_bedehi" style=""/>
                <display:column title="نام واحد صدور بدهي" property="name_vahed_sodor_bedehi" style=""/>
                <display:column title="كد واحد ثبت بدهي" property="kode_vahed_sabt_bedehi" style=""/>
                <display:column title="نام واحد ثبت بدهي" property="name_vahed_sabt_bedehi" style=""/>
                <display:column title="بانک" property="bank" style=""/>
                <display:column title="تاريخ سند بانك " property="tarikh_sanad_bank" style=""/>
                <display:column title="شماره سند بانک " property="shomare_sanad_bank" style=""/>
                <display:column title="شماره فيش" property="shomare_fish" style=""/>
                <display:column title="سريال چك" property="serial_check" style=""/>
                <display:column title="تاريخ سررسيد چك" property="sarresid_tarikh" style=""/>
                <display:column title="كد واحد ثبت سند"  property="kode_vahed_sabt_sanad" style=""/>
                <display:column title="نام واحد ثبت سند" property="name_vahed_sabt_sanad" style=""/>



            <%--<display:column title="بانک">${row.etebarCredebit.daryafteFish.bank} (${row.etebarCredebit.daryafteFish.bankInfo.bargozarandeh.shomareHesab})</display:column>--%>
            <%--<s:if test="%{row.etebarCredebit.credebitType==DARYAFTE_ELECTRONICI && row.bedehiCredebit.credebitType==GHEST}">--%>
            <%--${row.etebarCredebit.authorityId}                    --%>
            <%--</s:if>--%>
            <%--<s:elseif test="%{row.etebarCredebit.credebitType==PISHPARDAKHT && row.bedehiCredebit.credebitType==GHEST}">--%>
            <%--${row.etebarCredebit.fish.shomare}--%>
            <%--</s:elseif>--%>
            <%--<s:else>--%>
            <%--${row.etebarCredebit.daryafteFish.shomareSanadBank}--%>
            <%--</s:else>--%>
            <%--b-h--%>
                <%--<display:column title="نام بيمه گذار اعتبار" property="etebarCredebit.rcptName" style=""/>--%>
                <%--<display:column title="کاربر ثبت کننده سند" property="sanad.user.fullName" style=""/>--%>
                <display:column title="عمليات"    media="html" style="">
                    <sec:authorize ifNotGranted="ROLE_MALI_VIEW">

                        <c:if test="${row.vaziat.toString() != 'DAEM'}" >
                            <input type="button" onclick="loadKhateSanads(${row.sanad_id});"  id="change_btn" value="دائم کردن سند" />
                        </c:if>
                    </sec:authorize>
                    <c:if test="${row.vaziat != 'MOVAGHAT'}">
                        <input type="button" onclick="window.open('/fin/printeSanad?pishnehadReport.sanad.id=${row.sanad_id}');" id="print_btn" value="پرينت  سند" />
                    </c:if>
                </display:column>
                <display:column title="عمليات"   media="html" style="">
                    <input type="button" onclick="printFish(${row.etebar_id},'${row.bank}','${row.noe_etebar_str}');" id="print_btn" value="پرينت فيش اعتبار" />
                </display:column>
                <display:column title="عمليات"  media="html" style="">
                    <sec:authorize ifAllGranted="ROLE_KARBAR_MALI,ROLE_NAMAYANDE" ifNotGranted="ROLE_MALI_VIEW">
                        <c:if test="${row.vaziat == 'MOVAGHAT'}">
                            <%--<c:if test="${row.bedehiCredebit.subsystemName == 'VEHICLE' }">--%>
                            <input type="button" onclick="deleteSanad(${row.sanad_id});"  id="deleteSanad_btn" value="حذف سند" />
                            <%--</c:if>--%>
                        </c:if>
                    </sec:authorize>
                </display:column>
                <display:column title="عمليات"  media="html" style="">
                    <sec:authorize ifAllGranted="ROLE_MALI_HAZFESANAD" ifNotGranted="ROLE_MALI_VIEW">
                        <c:if test="${row.subsystem_name == 'VEHICLE'}">
                            <input type="button" onclick="deleteSanad(${row.sanad_id});"  id="deleteSanad_btn" value="حذف سند" />
                        </c:if>
                    </sec:authorize>
                </display:column>


        </display:table>
    </div>
<br>
    <sec:authorize ifNotGranted="ROLE_KARBAR_MALI">
        <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
    </sec:authorize>
    <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
        <input type="button" onclick="window.location='/loginUser.action'" value="بازگشت"/>
    </sec:authorize>
    <input type="button" onclick="window.location='/fin/loadSanadZani'" value="ثبت سند دستي"/>

</body>
</html>