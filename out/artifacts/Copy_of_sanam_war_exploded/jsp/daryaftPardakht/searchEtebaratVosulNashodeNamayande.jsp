<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="allofem" class="listenTextFieldEnter">

    <form action="/fin/listEtebaratVosulNashodeNamayande" method="post" id="listEtebaratVosulNashodeNamayande">
        <input type="hidden" name="selectedTab" id="sel_sel_tab" value="tabs-2"/>
        <table dir="rtl" class="inputList">
            <tr>
                <td>شناسه پرداخت</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="shomareMoshtari" id="shomareMoshtariEtebarat" />
                </td>
                <td>نام بیمه گذار</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="rcptName" id="rcptNameEtebarat"/>
                </td>
            </tr>
            <tr>
                <td>از تاریخ سر رسید</td>
                <td>
                    <input type="text" name="sarresidDateFrom" id="sarresidDateEtebarFrom" class="datePkr" readonly="true"/>
                </td>
                <td>تا تاریخ سر رسید</td>
                <td>
                    <input type="text" name="sarresidDateTo" id="sarresidDateEtebarTo" class="datePkr" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>از تاریخ ایجاد</td>
                <td>
                    <input type="text" name="createdDateFrom" id="createdDateEtebarFrom" class="datePkr" readonly="true"/>
                </td>
                <td>تا تاریخ ایجاد</td>
                <td>
                    <input type="text" name="createdDateTo" id="createdDateEtebarTo" class="datePkr" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>مبلغ کل</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="amount" id="amountEtebarat"  class="digitSeparators" />
                </td>
                <td>مبلغ دریافت شده</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="paidReceivedAmount" id="paidReceivedAmountEtebarat" class="digitSeparators"/>
                </td>
            </tr>
            <tr>
                <td>شماره بیمه نامه</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="identifier" id="identifierEtebarat"/>
                </td>
                <td><label>نمايندگي</label></td>
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_namayandegiIdEtebarat','search_namayandegiNameEtebarat', '');" style="float:left;" title="جستجو"></span>
                    <input type="hidden" name="search_namayandegiIdEtebarat" id="search_namayandegiIdEtebarat" />
                    <input type="text" name="search_namayandegiNameEtebarat" id="search_namayandegiNameEtebarat"  readonly="true"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td colspan="3">
                    <script type="text/javascript">
                        function clearSeachFromEtebarat()
                        {
                            $('#identifierEtebarat').val('');
                            $('#shomareMoshtariEtebarat').val('');
                            $('#rcptNameEtebarat').val('');
                            $('#amountEtebarat').val('');
                            $('#paidReceivedAmountEtebarat').val('');
                            $('#search_namayandegiIdEtebarat').val('');
                            $('#search_namayandegiNameEtebarat').val('');
                            $('#sarresidDateEtebarFrom').val('');
                            $('#sarresidDateEtebarTo').val('');
                            $('#createdDateEtebarFrom').val('');
                            $('#createdDateEtebarTo').val('');
                        }
                    </script>
                    <input type="button" onclick="searchEtebaratVahedeSodor()" style="margin-left:25px" value="جستجو">
                    <span class="noThing"></span>
                    <input type="button" value="پاک کردن فرم" onclick="clearSeachFromEtebarat()"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function searchEtebaratVahedeSodor() {
        $.ajax({
            type: "POST",
            url: 'listEtebaratVosulNashodeNamayande.action',
            data: $('#listEtebaratVosulNashodeNamayande').serialize(),
            success: function (response) {
                // we have the response
                $('#displayTab2').html(response);
            }
        });
    }

</script>