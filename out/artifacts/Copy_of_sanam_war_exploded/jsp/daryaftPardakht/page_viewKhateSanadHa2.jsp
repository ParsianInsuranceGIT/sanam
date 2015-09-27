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
<head><title>لیست اسناد</title></head>

<body>
<script>
    $(function(){
        $(".jtable tr").click(function(){
            $(this).children("td").toggleClass("ui-state-highlight");
        });
    });
</script>
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
                        </td>
                    </tr>
                    <tr>
                        <td>تاریخ ثبت سند(از) :</td>
                        <td>
                            <input type="text" name="createdDateAz" id="createdDateAz" class="datePkr" readonly="true"/>
                            <span class="noThing">&nbsp;</span>
                        </td>
                        <td>تاریخ ثبت سند(تا) :</td>
                        <td>
                            <input type="text" name="createdDateTa" id="createdDateTa" class="datePkr" readonly="true"/>
                            <span class="noThing">&nbsp;</span>
                        </td>
                    </tr>
                    <tr>
                        <td>نوع سند :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:select list="#{'':'قبض رسید/پرداخت','GHABZE_RESID':'قبض رسید', 'PARDAKHT':'پرداخت'}"
                               name="noeSanad" id="noeSanad" label="" key="noeSanad" theme="simple"/>
                        </td>
                        <td>وضعیت سند :</td>
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
                            <s:select list="#{'':'تمام موارد','DARYAFTE_FISH':'دریافت فیش', 'DARYAFTE_ELECTRONICI':'دریافت الکترونیکی', 'AZ_MAHALLE_BAZKHARID':'از محل بازخرید',
                                                'ETEBAR_VAM':'اعتبار وام','ETEBAR_BARDASHT':'اعتبار برداشت','DARYAFTE_CHECK':'دریافت چک','PARDAKHT_KARMOZD':'پرداخت کارمزد',
                                                'MOSHAREKAT':'مشارکت','ETEBAR_KHESARAT':'اعتبار خسارت','ELHAGHIE_EZAFI':'الحاقیه اضافی','ELHAGHIE_BARGASHTI':'الحاقیه برگشتی','PISHPARDAKHT':'پیش پرداخت',
                                                'VEHICLE_HAGHBIME_BARGASHTI':'حق بیمه برگشتی (اتومبیل)','ELHAGHIE_BARGASHTI_ETEBAR':'اعتبار الحاقیه برگشتی','ETEBAR_EBTAL':'اعتبار ابطال',
                                                'VEHICLE_DARYAFT_ELECTRONICI':'دریافت الکترونیکی (اتومبیل)', 'HESAB_FI_MA_BEYN':'حساب فی مابین'}"
                               name="etebarType" id="etebarType" label="" key="etebarType" theme="simple"/>
                        </td>
                        <td>نوع بدهی :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:select list="#{'':'تمام موارد','GHEST':'قسط', 'PARDAKHTE_TANKHAH':'پرداخت تنخواه', 'PARDAKHTE_CHECK':'پرداخت چک', 'GHEST_VAM':'قسط وام','VEHICLE_HAGHBIME':'حق بیمه (اتومبیل)',
                                            'VEHICLE_HAGHBIME_ELECTRONICI':'حق بیمه الکترونیکی (اتومبیل)','ACH':'پرداخت پایا','DARMAN_HAGHBIME':'حق بیمه درمان'}"
                               name="bedehiType" id="bedehiType" label="" key="bedehiType" theme="simple"/>
                        </td>
                    </tr>
                    <tr>
                        <td>شناسه پرداخت اعتبار :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shomareMoshtariEtebar" id="shomareMoshtariEtebar"/>
                        </td>
                        <td>شناسه پرداخت بدهی</td>
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
                        <td>شناسه بدهی :</td>
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
                        <td>مقدار بدهی :</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="amountBedehi" id="amountBedehi" class="digitSeparators" />
                        </td>
                    </tr>

                    <tr>
                        <td>شماره بیمه نامه :
                        </td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shoBimenameBedehi" id="shoBimenameBedehi" />
                        </td>
                        <td></td>
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
                                }

                                function printFish(fishId,bankFish,credebitTypeFarsi){
                                    if (credebitTypeFarsi == "پرداخت شناسه دار"){

                                        if (bankFish.contains('2177777733') || bankFish.contains('4757575763'))
                                            window.open('/fin/printeFishBankMellat?credebitReport.id='+fishId);
                                        else if (bankFish.contains('17038494'))
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
                            <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom_b()"/>
                        </td>
                     </tr>
                </table>
            </form>
        </div>
    </div>
        <%@include file="khateSanadsDialog.jsp"%>
    <div style="overflow: auto;">
        <display:table export="true" id="khateSanadListTbl" uid="row" name="khateSanadListPaginated"
                       sort="external" htmlId="khateSanadListTbl"
                       partialList="true"
                       size="${khateSanadListPaginated.fullListSize}"
                       pagesize="${khateSanadListPaginated.objectsPerPage}"
                       requestURI="" clearStatus="true" keepStatus="false"
                       excludedParams="" style="width: 100%; margin: 0 auto;">
            <display:column title="ردیف" style="">${row_rowNum}</display:column>
            <display:column title="شماره سند" property="sanad.shomare"/>
            <display:column title="زمان ثبت" property="sanad.createdDate"/>
            <display:column title="نوع سند">${row.sanad.noeSanad == "GHABZE_RESID"? "قبض رسید": row.sanad.noeSanad == "PARDAKHT"? "پرداخت":row.noeSanad}</display:column>
            <display:column title="وضعیت سند" >${row.sanad.vaziat == "DAEM"? "دائم":"موقت"}</display:column>
            <display:column property="amount" title="مبلغ سند" style=""/>
            <%--<display:column  title="شماره بیمه نامه اعتبار" style=""></display:column>--%>
            <display:column title="شناسایی اعتبار / شناسه پرداخت" style="">
                ${row.etebarCredebit.shomareMoshtari == null? '-':row.etebarCredebit.shomareMoshtari} / ${row.etebarCredebit.shenaseCredebit == null? '-':row.etebarCredebit.shenaseCredebit}
            </display:column>
            <display:column title="مبلغ اعتبار" property="etebarCredebit.amount"/>
            <display:column title="مانده اعتبار" property="etebarRemaining"/>
            <display:column title="نوع اعتبار" property="etebarCredebit.credebitTypeFarsi"/>
            <%--<display:column title="بانک">${row.etebarCredebit.daryafteFish.bank} (${row.etebarCredebit.daryafteFish.bankInfo.bargozarandeh.shomareHesab})</display:column>--%>
            <display:column property="etebarCredebit.bankName" title="بانک"/>
            <display:column title="تاریخ فیش" property="etebarCredebit.dateFish"/>
            <display:column title="شماره سند بانک" property="etebarCredebit.authorityId">
                <%--<s:if test="%{row.etebarCredebit.credebitType==DARYAFTE_ELECTRONICI && row.bedehiCredebit.credebitType==GHEST}">--%>
                    <%--${row.etebarCredebit.authorityId}                    --%>
                <%--</s:if>--%>
                <%--<s:elseif test="%{row.etebarCredebit.credebitType==PISHPARDAKHT && row.bedehiCredebit.credebitType==GHEST}">--%>
                    <%--${row.etebarCredebit.fish.shomare}--%>
                <%--</s:elseif>--%>
                <%--<s:else>--%>
                    <%--${row.etebarCredebit.daryafteFish.shomareSanadBank}--%>
                <%--</s:else>--%>
            </display:column>
            <display:column  title="شماره بیمه نامه" style="">${row.bedehiCredebit.identifier}</display:column>
            <display:column title="شناسایی بدهی/شناسه پرداخت" style="">
                ${row.bedehiCredebit.shomareMoshtari == null? '-':row.bedehiCredebit.shomareMoshtari} /  ${row.bedehiCredebit.shenaseCredebit == null? '-':row.bedehiCredebit.shenaseCredebit}
            </display:column>
            <display:column title="مقدار بدهی" property="bedehiCredebit.amount"/>
            <display:column title="مانده بدهی" property="bedehiRemaining"/>
            <display:column title="نوع بدهی" property="bedehiCredebit.credebitTypeFarsi"/>
            <display:column title="عملیات">
             <c:if test="${row.sanad.vaziat != 'DAEM' }" >
                <input type="button" onclick="loadKhateSanads(${row.sanad.id});"  id="change_btn" value="دائم کردن سند" />
             </c:if>
             <c:if test="${row.sanad.vaziat != 'MOVAGHAT'}">
                 <%--<input type="button" onclick="window.open('/fin/printeSanad?pishnehadReport.sanad.id=${row.sanad.id}');" id="print_btn" value="پرینت  سند" />--%>
             </c:if>
            </display:column>
            <display:column title="عملیات">
                <input type="button" onclick="printFish(${row.etebarCredebit.id},'${row.etebarCredebit.bankName}','${row.etebarCredebit.credebitTypeFarsi}');" id="print_btn" value="پرینت فیش اعتبار" />
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
    <input type="button" onclick="window.location='/fin/loadSanadZani'" value="ثبت سند دستی"/>

</body>
</html>