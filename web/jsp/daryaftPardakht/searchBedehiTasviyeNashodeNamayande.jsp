<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="allofem" class="listenTextFieldEnter">

    <form action="/fin/listBedehiHayeTasviyeNashodeNamayande" method="post" id="listBedehiTasviyeNashodeNamayande">
        <input type="hidden" name="selectedTab" id="sel_sel_tab" value="tabs-5"/>
        <input type="hidden" name="searchPage" value="yes"/>
        <table dir="rtl" class="inputList">
            <tr>
                <td>شماره بیمه نامه</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="identifier" id="identifierBedehiTN"/>
                </td>
                <td>نام بیمه گذار</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="rcptName" id="rcptNameBedehiTN"/>
                </td>
            </tr>
            <tr>
                <td>از تاریخ سر رسید</td>
                <td>
                    <input type="text" name="sarresidDateFrom" id="sarresidDateFromTN" class="datePkr" readonly="true"/>
                </td>
                <td>تا تاریخ سر رسید</td>
                <td>
                    <input type="text" name="sarresidDateTo" id="sarresidDateToTN" class="datePkr" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>از تاریخ ایجاد</td>
                <td>
                    <input type="text" name="createdDateFrom" id="createdDateFromTN" class="datePkr" readonly="true"/>
                </td>
                <td>تا تاریخ ایجاد</td>
                <td>
                    <input type="text" name="createdDateTo" id="createdDateToTN" class="datePkr" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>مبلغ کل</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="amount" id="amountBedehi5"  class="digitSeparators" />
                </td>
                <td>مبلغ مانده</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="remainingAmount" id="remainingAmountBedehi5" class="digitSeparators"/>
                </td>

            </tr>
            <tr>
                <td><label>نمايندگي</label></td>
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_namayandegiId5','search_namayandegiName5', '');" style="float:left;" title="جستجو"></span>
                    <input type="hidden" name="search_namayandegiId5" id="search_namayandegiId5" />
                    <input type="text" name="search_namayandegiName5" id="search_namayandegiName5"  readonly="true"/>
                </td>
                <td><label>واحد صدور</label></td>
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_vahedesodorId5','search_vahedesodorName5', '');" style="float:left;" title="جستجو"></span>
                    <input type="hidden" name="search_vahedesodorId5" id="search_vahedesodorId5" />
                    <input type="text" name="search_vahedesodorName5" id="search_vahedesodorName5"  readonly="true"/>
                </td>
            </tr>
            <tr>
                <td><label>بازاریاب</label></td>
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillBazaryabSanam('bsId-tab5','bsName-tab5', '');" style="float:left;" title="جستجو"></span>
                    <input type="hidden" name="bazaryabSanamIdBedehi" id="bsId-tab5" />
                    <input type="text" name="bname-tab5" id="bsName-tab5"  readonly="true"/>
                </td>
                <td><label>رنگ</label></td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <select name="bedehi_color" id="bedehi_color"   >
                        <option  value="" >انتخاب كنيد</option>
                        <option  value="RED_ORANGE" style="background-color: #ee4f33;" >قرمز و نارنجي</option>
                        <option  value="RED" style="background-color: #F75D59;" >قرمز</option>
                        <option value="ORANGE" style="background-color: #FFA62F;"  >نارنجي</option>
                        <option  value="YELLOW" style="background-color: #ffff66;"  >زرد</option>
                        <option  value="NO_COLOR" style="background-color: #ffffff;" >بي رنگ</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label>رشته</label></td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <select name="reshte" id="reshte"   >
                        <option  value="" >انتخاب كنيد</option>
                        <option  value="1" style="" >آتش سوزي</option>
                        <option  value="2" style="" >باربري</option>
                        <option  value="3" style="" >بدنه</option>
                        <option  value="4" style="" >ثالث</option>
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
               <td></td>
                <td colspan="3">
                    <script type="text/javascript">
                        function clearSeachFrom()
                        {
                            $('#identifierBedehiTN').val('');
                            $('#rcptNameBedehiTN').val('');
                            $('#sarresidDateFromTN').val('');
                            $('#sarresidDateToTN').val('');
                            $('#createdDateFromTN').val('');
                            $('#createdDateToTN').val('');
                            $('#amountBedehi5').val('');
                            $('#remainingAmountBedehi5').val('');
                            $('#search_namayandegiName5').val('');
                            $('#search_vahedesodorName5').val('');
                            $('#bsName-tab5').val('');
                            $('#bedehi_color').val('');
                        }


                    </script>

                    <input type="button" onclick="searchBedehiTasviyeNashode()" style="margin-left:25px" value="جستجو">
                    <span class="noThing"></span>
                    <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom()"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function searchBedehiTasviyeNashode() {
        $.ajax({
            type: "POST",
            url: 'listBedehiHayeTasviyeNashodeNamayande.action',
            data: $('#listBedehiTasviyeNashodeNamayande').serialize(),
            success: function (response) {
                // we have the response
                $('#displayTab5').html(response);
            }
        });
    }

</script>