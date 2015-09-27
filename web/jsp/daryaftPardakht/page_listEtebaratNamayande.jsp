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
<head><title>لیست اعتبارات و بدهی ها</title></head>
<body>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<script>
    $(function(){
        $(".jtable tr").click(function(){
            $(this).children("td").toggleClass("ui-state-highlight");
        });
    });

    function deleteEtebarByNamayandefun(etebarId){
        $.ajax({
            type: "POST",
            async : false,
            data: "credebitId="+etebarId,
            url: "deleteEtebarOperation",
            success: function (msg) {
                if (msg == 'true'){
                    alertMessage("عملیات حذف اعتبار با موفقیت انجام شد");
                    window.open(window.location.pathname,"_self");
                   // window.open(document.URL, "_self");
                }
                else{
                    alertMessage("به دلیل سند داشتن اعتبار، قابل حذف کردن نمی باشد");
                }

            }
        });
    }
    <%--function editEtebarfun(id) //wirayesh etebar dar safhe liste etebarat va bedehiha--%>
    <%--{--%>
        <%--$.post('/editEtebarOperation', "loadedIdEtebarat=" + id , function (msg) {--%>
            <%--//msg = page loadetebarat.jsp--%>
            <%--addOptionNoeEtebarEdit();--%>
            <%--loadEtebarCheckEdit(msg);--%>
            <%--$("#noeEtebareDarHaleSabt").val("<%=Credebit.CredebitType.DARYAFTE_CHECK%>");--%>
            <%--editCheckEtebarat($("#noeEtebareDarHaleSabt").val());--%>
            <%--dialogFormEtebar('sabteEtebarDialog', 'ویرایش اعتبار', function () {--%>
                <%--sabteEtebareDastiData.push({name: 'credebit.id', value: id});--%>
                <%--$.post('/wirayeshEtebareCheck', sabteEtebareDastiData , function () {--%>
                    <%--window.open(window.location.pathname,"_self");--%>
                <%--})--%>
            <%--})--%>
        <%--});--%>
    <%--}--%>
//    function dialogFormEtebar(id,title,func) //taeid va enseraf dar dialog wirayesh etebar
//    {
//        $('#'+id).dialog({
//            modal:true, resizable:false, closeText: "",
//            width: 640, maxHeight:600, minHeight: 25,
//            title:title,
//            buttons: {
//                "تایید": function() {
//                    if($.validationEngine.submitValidation($('#'+id)) === false){
//                        if($('#noeEtebareDarHaleSabt') != undefined && $('#noeEtebareDarHaleSabt').val() == "DARYAFTE_CHECK"){
//                            chg_etebarType($('#noeEtebareDarHaleSabt').val());
//                            $.ajax({
//                                type: "POST",
//                                url: "checkEtebarDasti",
//                                data: sabteEtebareDastiData,
//                                success: function (response) {
//                                    if (response.indexOf('SARRESID_BEFORE_TODAY') != -1) {
//                                        alertMessage("تاریخ سررسید باید بعد از امروز باشد.") ;
//                                    }else{
//                                        func();
//                                        $(".ui-dialog-content").dialog("close");
//                                    }
//                                }
//                            });
//                        }
//                        $('#addressBimeGozar_neshaniMahalleKar').focus();
//                    }
//                },
//                "انصراف": function() {$(this).dialog("close");}
//            }
//        });
//    }
</script>

    <div class="expandableTitleBar" id="expandableAsl">
        <p class="heading">
            <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                            جستجو
        </p>
        <div class="content" style="display:none;" id="searchAslContent1">
            <form action="/fin/listEtebaratNamayande" method="post">
                <table dir="rtl" class="inputList">
                    <tr>
                        <td>ماهیت</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:select list="#{'':'تمام موارد','BEDEHI':'بدهی','ETEBAR':'اعتبار'}" name="credebitNoe" id="credebitNoe" label="" key="credebitNoe" theme="simple"/>
                        </td>
                        <td>شماره بیمه نامه</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="identifier" id="identifier"/>
                        </td>
                    </tr>
                    <tr>
                        <td>شناسه پرداخت</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shomareMoshtari" id="shomareMoshtari" />
                        </td>
                        <td>نام بیمه گذار</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="rcptName" id="rcptName"/>
                        </td>
                    </tr>
                    <tr>
                        <td>از تاریخ سر رسید</td>
                        <td>
                            <input type="text" name="sarresidDateFrom" id="sarresidDateFrom" class="datePkr" readonly="true"/>
                        </td>
                        <td>تا تاریخ سر رسید</td>
                        <td>
                            <input type="text" name="sarresidDateTo" id="sarresidDateTo" class="datePkr" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>تاریخ ایجاد</td>
                        <td>
                            <input type="text" name="createdDateFrom" id="createdDateFrom" class="datePkr" readonly="true"/>
                        </td>
                        <td>تاریخ ایجاد</td>
                        <td>
                            <input type="text" name="createdDateTo" id="createdDateTo" class="datePkr" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>مبلغ کل</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="amount" id="amount"  class="digitSeparators" />
                        </td>
                        <td>مبلغ دریافت شده</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="paidReceivedAmount" id="paidReceivedAmount" class="digitSeparators"/>
                        </td>
                    </tr>
                    <tr>
                        <td>مبلغ مانده</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="remainingAmount" id="remainingAmount" class="digitSeparators"/>
                        </td>
                        <td>نوع</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:select list="#{'':'تمام موارد', 'PARDAKHTE_TANKHAH':'پرداخت تنخواه', 'PARDAKHTE_CHECK':'پرداخت چک', 'GHEST_VAM':'قسط وام','VEHICLE_HAGHBIME':'حق بیمه (اتومبیل)',
                                            'VEHICLE_HAGHBIME_ELECTRONICI':'حق بیمه الکترونیکی (اتومبیل)','ACH':'پرداخت پایا','DARYAFTE_FISH':'دریافت فیش', 'DARYAFTE_ELECTRONICI':'دریافت الکترونیکی', 'AZ_MAHALLE_BAZKHARID':'از محل بازخرید',
                                                'ETEBAR_VAM':'اعتبار وام','ETEBAR_BARDASHT':'اعتبار برداشت','DARYAFTE_CHECK':'دریافت چک','PARDAKHT_KARMOZD':'پرداخت کارمزد',
                                                'MOSHAREKAT':'مشارکت','ETEBAR_KHESARAT':'اعتبار خسارت','ELHAGHIE_EZAFI':'الحاقیه اضافی','ELHAGHIE_BARGASHTI':'الحاقیه برگشتی','PISHPARDAKHT':'پیش پرداخت',
                                                'VEHICLE_HAGHBIME_BARGASHTI':'حق بیمه برگشتی (اتومبیل)','ELHAGHIE_BARGASHTI_ETEBAR':'اعتبار الحاقیه برگشتی','ETEBAR_EBTAL':'اعتبار ابطال',
                                                'VEHICLE_DARYAFT_ELECTRONICI':'دریافت الکترونیکی (اتومبیل)','DARMAN_HAGHBIME':'حق بیمه درمان' , 'HESAB_FI_MA_BEYN':'حساب فی مابین','PARDAKHT_SHENASEDAR':'پرداخت شناسه دار','DARMAN_ELHAGHIYE_BARGASHTI':'الحاقیه برگشتی درمان',
                                                'VEHICLE_KHESARAT':'خسارت (اتومبیل)','KASR_AZ_HOGHUGH':'کسر از حقوق','AZ_MAHALLE_TABLIGHAT_MANUAL':'از محل تبلیغات (دستی)', 'DARYAFTE_FISH_NAMAYANDE':'اعتبار فیش (نمایندگی)','AZ_MAHALE_HAZINE_BIME_DARAYIHA':'از محل هزينه بيمه دارايي ها','ETEBAR_DARMAN_KHANEVADE':'اعتبار درمان خانواده'}"
                                      name="credebitTypeFarsi" id="credebitTypeFarsi" label="" key="credebitTypeFarsi" theme="simple" />
                        </td>
                    </tr>
                    <tr>
                        <td>شماره قرارداد</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shomareGharardad" id="shomareGharardad" />
                        </td>
                        <td>وضعیت سند</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:select list="#{'':'تمام موارد','SANAD_KHORDE':'سند خورده','SANAD_NA_KHORDE':'سند نخورده'}" name="statusFarsi" id="statusFarsi" label="" key="statusFarsi" theme="simple"/>
                        </td>
                    </tr>
                    <tr>
                        <td>وضعیت وصول</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:select list="#{'':'تمام موارد','TAEED_VOSOUL':'وصول شده','TAEED_NASHODE':'وصول نشده'}" name="vosoulStateFarsi" id="vosoulStateFarsi" label="" key="vosoulStateFarsi" theme="simple"/>
                        </td>
                        <td><label>نمايندگي</label></td>
                        <td>
                            <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('namayandeId','namayandeName', '');" style="float:left;" title="جستجو"></span>
                            <input type="hidden" name="namayandeId" id="namayandeId" />
                            <input type="text" name="namayandeName" id="namayandeName"  readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>نام بانک</label>
                        </td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <select name="bankName" id="bankName">
                                <option value="">تمام موارد</option>
                                <option value="بانک سپه">بانک سپه</option>
                                <option value="پست بانک">پست بانک</option>
                                <option value="بانک ملی ایران">بانک ملی ایران</option>
                                <option value="بانک توسعه صادرات ایران">بانک توسعه صادرات ایران</option>
                                <option value="بانک صنعت و معدن">بانک صنعت و معدن</option>
                                <option value="بانک کشاورزی">بانک کشاورزی</option>
                                <option value="بانک مسکن">بانک مسکن</option>
                                <option value="بانک توسعه تعاون">بانک توسعه تعاون</option>
                                <option value="بانک آینده">بانک آینده</option>
                                <option value="بانک اقتصاد نوین">بانک اقتصاد نوین</option>
                                <option value="بانک پارسیان">بانک پارسیان</option>
                                <option value="بانک کارآفرین">بانک کارآفرین</option>
                                <option value="بانک سامان">بانک سامان</option>
                                <option value="بانک پاسارگاد">بانک پاسارگاد</option>
                                <option value="بانک سرمایه">بانک سرمایه</option>
                                <option value="بانک شهر">بانک شهر</option>
                                <option value="بانک سینا">بانک سینا</option>
                                <option value="بانک دی">بانک دی</option>
                                <option value="بانک انصار">بانک انصار</option>
                                <option value="بانک حکمت ایرانیان">بانک حکمت ایرانیان</option>
                                <option value="بانک قوامین">بانک قوامین</option>
                                <option value="بانک مهراقتصاد">بانک مهراقتصاد</option>
                                <option value="بانک تجارت">بانک تجارت</option>
                                <option value="بانک رفاه کارگران">بانک رفاه کارگران</option>
                                <option value="بانک صادرات ایران">بانک صادرات ایران</option>
                                <option value="بانک ملت">بانک ملت</option>
                                <option value="بانک گردشگری">بانک گردشگری</option>
                                <option value="بانک ایران زمین">بانک ایران زمین</option>
                                <option value="بانک خاورمیانه">بانک خاورمیانه</option>
                                <option value="صندوق قرض الحسنه پارسیان">صندوق قرض الحسنه پارسیان</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>شماره چک</label>
                        </td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shomareCheck" id="shomareCheckId" />
                        </td>
                        <td>
                            <label>شماره فیش</label>
                        </td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shomareFish" id="shomareFishId">
                        </td>

                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="3">
                            <script type="text/javascript">
                                function clearSeachFrom()
                                {
                                    $('#credebitNoe').val('');
                                    $('#identifier').val('');
                                    $('#shomareMoshtari').val('');
                                    $('#rcptName').val('');
                                    $('#sarresidDateFrom').val('');
                                    $('#sarresidDateTo').val('');
                                    $('#createdDateFrom').val('');
                                    $('#createdDateTo').val('');
                                    $('#amount').val('');
                                    $('#paidReceivedAmount').val('');
                                    $('#remainingAmount').val('');
                                    $('#credebitTypeFarsi').val('');
                                    $('#shomareGharardad').val('');
                                    $('#statusFarsi').val('');
                                    $('#namayandeName').val('');
                                    $('#namayandeId').val('');
                                    $('#bankName').val('');
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
                                                alertMessage("قابلیت پرینت برای این رکورد وجود ندارد");

                                    }else{
                                        alertMessage("قابلیت پرینت برای این رکورد وجود ندارد");
                                    }
                                }
                            </script>
                            <span class="noThing">&nbsp;</span>
                            <input type="submit" value="جستجو" theme="simple"/>
                            <span class="noThing"></span>
                            <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom()"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <%@include file="bankInfosDialog.jsp"%>
    <div style="overflow: auto;">
        <display:table export="true" id="credebitListTbl" uid="row" name="credebitListPaginated"
                       sort="external" htmlId="credebitListTbl"
                       partialList="true"
                       size="${credebitListPaginated.fullListSize}"
                       pagesize="${credebitListPaginated.objectsPerPage}"
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
                <display:column title="ردیف" style="">${row_rowNum+((credebitListPaginated.pageNumber-1)*credebitListPaginated.objectsPerPage)}</display:column>
                <display:column property="credebitNoe" title="ماهیت" style=""/>
                <display:column property="identifier" title="شماره بیمه نامه" style=""/>
                <display:column property="shomareMoshtari" title="شناسه پرداخت" style=""/>
                <display:column property="rcptName" title="نام بیمه گذار" style="" />
                <display:column property="namayandeName" title="نام نماینده" style="" />
                <display:column property="mohlatSarresid" title="مهلت سررسید" style=""/>
                <display:column property="sarresidDate" title="تاریخ سررسید" style=""/>
                <display:column property="createdDate" title="تاریخ ایجاد" style=""/>
                <display:column property="amount" title="مبلغ کل" style=""/>
                <display:column property="paidReceivedAmountFormat" title="مبلغ دریافت شده" style=""/>
                <display:column property="remainingAmount" title="مبلغ مانده" style=""/>
                <display:column property="noeArz" title="نوع ارز" style=""/>
                <display:column property="nerkhArz" title="نرخ ارز" style=""/>
                <display:column property="credebitTypeFarsi" title="نوع" style=""/>
                <display:column property="shomareGharardad" title="شماره قرارداد" style=""/>
                <display:column property="statusFarsi" title="وضعیت سند" style=""/>
                <display:column property="vosoulStateFarsi" title="وضعیت وصول" style=""/>
                <display:column property="bankName" title="نام بانک" style=""/>
                <display:column property="daryafteFish.shomareFish" title="شماره فیش" style=""/>
                <c:if test="${row.credebitNoe == 'اعتبار' }" >
                    <display:column property="daryafteCheck.serial" title="شماره چک"  style=""/>
                </c:if>
                <c:if test="${row.credebitNoe == 'بدهی' }" >
                    <display:column property="check.shomare" title="شماره چک"  style=""/>
                </c:if>
                <display:column property="daryafteCheckStatus" title="وضعیت چک" style=""/>
                <display:column property="bazarYabSanam.name" title="نام بازاریاب" style=""/>
                <display:column style="" title="عملیات">
                    <input type="button" onclick="printFish(${row.id},'${row.bankName}','${row.credebitTypeFarsi}');" id="print_btn" value="پرینت فیش اعتبار" />
                </display:column>
                <display:column style="" title="عملیات">
                    <c:if test="${row.credebitNoe == 'اعتبار' }" >
                        <c:if test="${row.amount == row.remainingAmount}" >
                            <input type="button" onclick="deleteEtebarByNamayandefun(${row.id})"  id="change_btn" value="حذف" />
                        </c:if>
                    </c:if>
                </display:column>
                <display:column style="" title="عملیات">
                    <c:if test="${row.vosoulStateFarsi == 'وصول شده' }" >
                        <input type="button" onclick="loadBankInfosForCredebit(${row.id});"  id="change_btn2" value="اطلاعات وصولی" />
                    </c:if>
                </display:column>


            <%--<display:column title="عملیات" >--%>
                <%--<c:if test="${row.credebitNoe == 'اعتبار' }" >--%>
                    <%--<c:if test="${(row.credebitType == 'DARYAFTE_CHECK') && (row.namayande.id == user.namayandegi.id)}">--%>
                        <%--<input type="button" onclick="editEtebarfun('${row.id}');" id="change_btn3" value="ویرایش">--%>
                    <%--</c:if>--%>
                <%--</c:if>--%>
            <%--</display:column>--%>
        </display:table>
    </div>
<div id="sabteEtebarDialog" style="display:none;">
    <%@include file="sabteSanad/sabteEtebarDialog.jsp" %>
</div>

<br>
    <sec:authorize ifNotGranted="ROLE_KARBAR_MALI">
        <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
    </sec:authorize>
    <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
        <input type="button" onclick="window.location='/loginUser.action'" value="بازگشت"/>
    </sec:authorize>
</body>
</html>