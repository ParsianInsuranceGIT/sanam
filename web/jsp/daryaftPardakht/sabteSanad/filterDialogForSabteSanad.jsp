<%@ page import="com.bitarts.parsian.viewModel.search.CredebitSearchForm" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron1
  Date: 8/17/11
  Time: 2:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<%@include file="/jsp/josteju/searchBazaryabSanam.jsp"%>
<div>
    <form action="" id="fltr_bedehiHa">
        <input type="hidden" name="credebitSearchForm.type" value="<%=CredebitSearchForm.Credebit_type.BEDEHI%>">
        <table class="inputList" border="0" cellspacing="1" cellpadding="0" style="width:100%">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebitSearchForm.identifier" id="fltr_shomareBimename" class="">
                    &nbsp;<label>شماره بیمه نامه</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebitSearchForm.shomareBedehi" id="fltr_shomareBedehi" class="">
                    &nbsp;<label>شماره بدهی</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebitSearchForm.shomareGharardad" id="fltr_shomareGharardad" class="">
                    &nbsp;<label>شماره قرارداد</label>
                </td>
                <td>
                    <span class="noThing"></span>
                     &nbsp;<lable>نوع بدهی</lable>
                    <select id="fltr_credebitType" name="credebitSearchForm.credebitType">
                        <option value="all">همه موارد</option>
                        <option value="<%=Credebit.CredebitType.GHEST%>">قسط</option>
                        <option value="<%=Credebit.CredebitType.GHEST_VAM%>">قسط وام</option>
                        <option value="<%=Credebit.CredebitType.PARDAKHTE_CHECK%>">پرداخت چک</option>
                        <option value="<%=Credebit.CredebitType.PARDAKHTE_TANKHAH%>">پرداخت تنخواه</option>
                        <option value="<%=Credebit.CredebitType.VEHICLE_HAGHBIME%>">حق بیمه (اتومبیل)</option>
                        <option value="<%=Credebit.CredebitType.DARMAN_HAGHBIME%>">حق بیمه درمان</option>
                        <option value="<%=Credebit.CredebitType.VEHICLE_HAGHBIME_ELECTRONICI%>">حق بیمه الکترونیکی (اتومبیل)</option>
                        <%--<option value="<%=Credebit.CredebitType.ACH%>">پرداخت پایا</option>--%>
                        <option value="<%=Credebit.CredebitType.HESAB_FI_MA_BEYN_BEDEHI%>">پرداخت حساب فی مابین</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="credebitSearchForm.azTarikh" id="azTarikh" class="datePkr">
                    &nbsp;<label>از تاریخ</label>
                </td>
                <td>
                    <input type="text" name="credebitSearchForm.taTarikh" id="taTarikh" class="datePkr">
                    &nbsp;<label>تا تاریخ</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebitSearchForm.shomareMoshtari" id="fltr_shomareMoshtari" class="">
                    &nbsp;<label>شناسه پرداخت</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebitSearchForm.rcptName" id="fltr_rcptName" class="">
                    &nbsp;<label>نام بیمه گذار</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('fltr_namayandeId','namayandegiName', '');" style="float:left;" title="جستجو"></span>
                       <input type=text  name="credebitSearchForm.namayandeName" id="namayandegiName" class="" readonly="readonly"/>
                       <input type=hidden name="namayandeId"  id="fltr_namayandeId"/>
                    &nbsp;<label>نام نماینده</label>
                </td>

                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillBazaryabSanam('bazaryabSanamId','bname', '');" style="float:left;" title="جستجو"></span>
                    <input type="hidden" name="credebitSearchForm.bazaryabSanamId" id="bazaryabSanamId" />
                    <input type="text" name="credebitSearchForm.bazaryabSanamName" id="bname"  />
                    &nbsp;<label>نام بازاریاب</label>
                </td>
            </tr>
        </table>
    </form>
</div>
<div>
    <form action="" id="fltr_etebarHa">
        <input type="hidden" name="credebitSearchForm.type" value="<%=CredebitSearchForm.Credebit_type.ETEBAR%>">
        <table class="inputList" border="0" cellspacing="1" cellpadding="0" style="width:100%">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebitSearchForm.identifier" id="fltr_shomareBimename_2" class="">
                    &nbsp;<label>شماره بیمه نامه</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebitSearchForm.shomareEtebar" id="fltr_shomareEtebar" class="">
                    &nbsp;<label>شماره اعتبار</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebitSearchForm.shomareGharardad" id="fltr_shomareGharardad_2" class="">
                    &nbsp;<label>شماره قرارداد</label>
                </td>
                <td>
                    <span class="noThing"></span>
                     &nbsp;<lable>نوع اعتبار</lable>
                    <select id="fltr_credebitType2" name="credebitSearchForm.credebitType">
                        <option value="all">همه موارد</option>
                        <option value="<%=Credebit.CredebitType.DARYAFTE_FISH%>">دریافت فیش</option>
                        <option value="<%=Credebit.CredebitType.DARYAFTE_ELECTRONICI%>">دریافت الکترونیک</option>
                        <option value="<%=Credebit.CredebitType.AZ_MAHALLE_BAZKHARID%>">از محل بازخرید</option>
                        <option value="<%=Credebit.CredebitType.ETEBAR_VAM%>">اعتبار وام</option>
                        <option value="<%=Credebit.CredebitType.ETEBAR_BARDASHT%>">اعتبار برداشت</option>
                        <option value="<%=Credebit.CredebitType.DARYAFTE_CHECK%>">دریافت چک</option>
                        <option value="<%=Credebit.CredebitType.PARDAKHT_KARMOZD%>">دریافت کارمزد</option>
                        <option value="<%=Credebit.CredebitType.PARDAKHT_SHENASEDAR%>">پرداخت شناسه دار</option>
                        <option value="<%=Credebit.CredebitType.MOSHAREKAT%>">مشارکت</option>
                        <option value="<%=Credebit.CredebitType.ETEBAR_KHESARAT%>">اعتبار خسارت</option>
                        <option value="<%=Credebit.CredebitType.ELHAGHIE_EZAFI%>">الحاقیه اضافه</option>
                        <option value="<%=Credebit.CredebitType.ELHAGHIE_BARGASHTI%>">الحاقیه برگشتی</option>
                        <option value="<%=Credebit.CredebitType.ELHAGHIE_BARGASHTI_ETEBAR%>">اعتبار الحاقیه برگشتی</option>
                        <option value="<%=Credebit.CredebitType.VEHICLE_HAGHBIME_BARGASHTI%>">حق بیمه برگشتی (اتومبیل)</option>
                        <option value="<%=Credebit.CredebitType.VEHICLE_KHESARAT%>">خسارت (اتومبیل)</option>
                        <option value="<%=Credebit.CredebitType.ETEBAR_EBTAL%>">اعتبار ابطال</option>
                        <option value="<%=Credebit.CredebitType.VEHICLE_DARYAFT_ELECTRONICI%>">دریافت الکترونیکی (اتومبیل)</option>
                        <option value="<%=Credebit.CredebitType.HESAB_FI_MA_BEYN%>">حساب فی ما بین</option>
                        <option value="<%=Credebit.CredebitType.DARMAN_ELHAGHIYE_BARGASHTI%>">الحاقیه برگشتی درمان</option>
                        <option value="<%=Credebit.CredebitType.DARYAFTE_FISH_NAMAYANDE%>">اعتبار فیش (نمایندگی)</option>
                        <option value="<%=Credebit.CredebitType.AZ_MAHALLE_TABLIGHAT_MANUAL%>">از محل تبلیغات(دستی)</option>
                        <option value="<%=Credebit.CredebitType.KASR_AZ_HOGHUGH%>">کسر از حقوق</option>
                        <option value="<%=Credebit.CredebitType.AZ_MAHALE_HAZINE_BIME_DARAYIHA%>"/>از محل هزينه بيمه دارايي ها<option>
                        <option value="<%=Credebit.CredebitType.ETEBAR_DARMAN_KHANEVADE%>"/>اعتبار درمان خانواده<option>
                        <option value="<%=Credebit.CredebitType.KASR_AZ_KARMOZD%>"/>آعتبار كسر از كارمزد<option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebitSearchForm.shomareMoshtari" id="fltr_shomareMoshtari_2" class="">
                    &nbsp;<label>شناسه پرداخت</label>
                </td>
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('fltr_namayandeId_2','namayandegiName_2', '');" style="float:left;" title="جستجو"></span>
                    <input type=text  name="credebitSearchForm.namayandeName" id="namayandegiName_2" class="" readonly="readonly"/>
                    <input type=hidden name="namayandeId"  id="fltr_namayandeId_2"/>
                    &nbsp;<label>نام نماینده</label>
                </td>

            </tr>
            <tr>
                <td>
                    <input type="text" name="credebitSearchForm.azTarikhEtebar" id="azTarikh_2" class="datePkr">
                    &nbsp;<label>از تاریخ</label>
                </td>
                <td>
                    <input type="text" name="credebitSearchForm.taTarikhEtebar" id="taTarikh_2" class="datePkr">
                    &nbsp;<label>تا تاریخ</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebitSearchForm.amount" id="amount_2" class="digitSeparators">
                    &nbsp;<label>مبلغ</label>
                </td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="credebitSearchForm.shomareCheck" id="shomareCheckId" />
                    &nbsp;<label>شماره چک</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="credebitSearchForm.shomareFish" id="shomareFishId" />
                    &nbsp;<label>شماره فیش</label>
                </td>
            </tr>
        </table>
    </form>
</div>
