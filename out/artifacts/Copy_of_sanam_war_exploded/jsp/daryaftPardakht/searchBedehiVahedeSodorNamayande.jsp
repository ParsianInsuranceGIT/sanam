<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="allofem" class="listenTextFieldEnter">

    <form action="/fin/listBedehiVahedeSodorNamayande" method="post" id="listBedehiVahedeSodorNamayande">
        <input type="hidden" name="selectedTab" id="sel_sel_tab" value="tabs-1"/>
        <table dir="rtl" class="inputList">
            <tr>
                <td>شماره بیمه نامه</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="identifier" id="identifierBedehi"/>
                </td>
                <td>نام بیمه گذار</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="rcptName" id="rcptNameBedehi"/>
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
                <td>از تاریخ ایجاد</td>
                <td>
                    <input type="text" name="createdDateFrom" id="createdDateFrom" class="datePkr" readonly="true"/>
                </td>
                <td>تا تاریخ ایجاد</td>
                <td>
                    <input type="text" name="createdDateTo" id="createdDateTo" class="datePkr" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>مبلغ کل</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="amount" id="amountBedehi"  class="digitSeparators" />
                </td>
                <td>مبلغ دریافت شده</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="paidReceivedAmount" id="paidReceivedAmountBedehi" class="digitSeparators"/>
                </td>

            </tr>
            <tr>
                <td><label>نمايندگي</label></td>
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_namayandegiId','search_namayandegiName', '');" style="float:left;" title="جستجو"></span>
                    <input type="hidden" name="search_namayandegiId" id="search_namayandegiId" />
                    <input type="text" name="search_namayandegiName" id="search_namayandegiName"  readonly="true"/>
                </td>
                <td><label>واحد صدور</label></td>
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_vahedesodorId','search_vahedesodorName', '');" style="float:left;" title="جستجو"></span>
                    <input type="hidden" name="search_vahedesodorId" id="search_vahedesodorId" />
                    <input type="text" name="search_vahedesodorName" id="search_vahedesodorName"  readonly="true"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td colspan="3">
                    <script type="text/javascript">
                        function clearSeachFrom()
                        {
                            $('#identifierBedehi').val('');
                            $('#shomareMoshtariBedehi').val('');
                            $('#rcptNameBedehi').val('');
                            $('#amountBedehi').val('');
                            $('#paidReceivedAmountBedehi').val('');
                            $('#search_namayandegiId').val('');
                            $('#search_namayandegiName').val('');
                            $('#sarresidDateFrom').val('');
                            $('#sarresidDateTo').val('');
                            $('#createdDateFrom').val('');
                            $('#createdDateTo').val('');
                        }


                    </script>

                    <input type="button" onclick="searchBedehiVahedeSodor()" style="margin-left:25px" value="جستجو">
                    <span class="noThing"></span>
                    <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom()"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function searchBedehiVahedeSodor() {
        $.ajax({
            type: "POST",
            url: 'listBedehiVahedeSodorNamayande.action',
            data: $('#listBedehiVahedeSodorNamayande').serialize(),
            success: function (response) {
                // we have the response
                $('#displayTab1').html(response);
            }
        });
    }

</script>