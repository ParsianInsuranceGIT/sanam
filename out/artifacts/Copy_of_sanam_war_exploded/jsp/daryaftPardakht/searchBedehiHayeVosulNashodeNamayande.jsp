
<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="allofem" class="listenTextFieldEnter">

    <form action="/fin/listBedehiHayeVosulNashodeNamayande" method="post" id="listBedehiHayeVosulNashodeNamayande">
        <input type="hidden" name="selectedTab" id="sel_sel_tab" value="tabs-4"/>
        <input type="hidden" name="searchPage" value="yes"/>
        <table dir="rtl" class="inputList">
            <tr>
                <td>از تاریخ سر رسید</td>
                <td>
                    <input type="text" name="sarresidDateFrom" id="sarresidDateVosulNFrom" class="datePkr" readonly="true"/>
                </td>
                <td>تا تاریخ سر رسید</td>
                <td>
                    <input type="text" name="sarresidDateTo" id="sarresidDateVosulNTo" class="datePkr" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>از تاریخ ایجاد</td>
                <td>
                    <input type="text" name="createdDateFrom" id="createdDateVosulNFrom" class="datePkr" readonly="true"/>
                </td>
                <td>تا تاریخ ایجاد</td>
                <td>
                    <input type="text" name="createdDateTo" id="createdDateVosulNTo" class="datePkr" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>مبلغ کل</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="amount" id="amountBedehi4"  class="digitSeparators" />
                </td>
                <td>نماینده</td>
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_namayandegiIdEtebarat4','search_namayandegiEtebaratName4', '');" style="float:left;" title="جستجو"></span>
                    <input type="hidden" name="search_namayandegiIdEtebarat4" id="search_namayandegiIdEtebarat4" />
                    <input type="text" name="search_namayandegiEtebaratName4" id="search_namayandegiEtebaratName4"  readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>شماره بیمه نامه</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="identifier" id="identifierEtebarat4"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td colspan="3">
                    <script type="text/javascript">
                        function clearSeachFrom4()
                        {
                            $('#amountBedehi4').val('');
                            $('#sarresidDateVosulNFrom').val('');
                            $('#sarresidDateVosulNTo').val('');
                            $('#createdDateVosulNFrom').val('');
                            $('#createdDateVosulNTo').val('');
                            $('#search_namayandegiIdEtebarat4').val('');
                            $('#search_namayandegiEtebaratName4').val('');
                            $('#identifierEtebarat4').val('');
                        }


                    </script>

                    <input type="button" onclick="searchBedehiHayeVosulNashodeNamayande()" style="margin-left:25px" value="جستجو">
                    <span class="noThing"></span>
                    <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom4()"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function searchBedehiHayeVosulNashodeNamayande() {
        $.ajax({
            type: "POST",
            url: 'listBedehiHayeVosulNashodeNamayande.action',
            data: $('#listBedehiHayeVosulNashodeNamayande').serialize(),
            success: function (response) {
                $('#displayTab4').html(response);
            }
        });
    }

</script>