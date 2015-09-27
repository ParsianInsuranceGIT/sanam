<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="allofem" class="listenTextFieldEnter">

    <form action="/fin/listMoghayeratDarVosolNamayande" method="post" id="listMoghayeratDarVosolNamayande">
        <input type="hidden" name="selectedTab" id="sel_sel_tab" value="tabs-3"/>
        <table dir="rtl" class="inputList">
            <tr>
                <td>شناسه پرداخت</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="shomareMoshtari" id="shomareMoshtariEtebarat" />
                </td>
                <td>مبلغ کل</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="amount" id="amountBedehi3"  class="digitSeparators" />
                </td>
            </tr>
            <tr>
                <td>شرح وصول</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <s:select list="#{'':'تمام موارد','MABLAGH_MAZAD':'مبلغ مازاد','MABLAGH_KAMTAR':'مبلغ کمتر'}" name="vosoulDesc" id="vosoulDesc" label="" key="vosoulDesc" theme="simple"/>
                </td>
                <td>نماینده</td>
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_namayandegiIdEtebarat3','search_namayandegiEtebaratName3', '');" style="float:left;" title="جستجو"></span>
                    <input type="hidden" name="search_namayandegiIdEtebarat3" id="search_namayandegiIdEtebarat3" />
                    <input type="text" name="search_namayandegiEtebaratName3" id="search_namayandegiEtebaratName3"  readonly="true"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td colspan="3">
                    <script type="text/javascript">
                        function clearSeachFrom3()
                        {
                            $('#amountBedehi3').val('');
                            $('#search_namayandegiIdEtebarat3').val('');
                            $('#search_namayandegiEtebaratName3').val('');
                        }


                    </script>

                    <input type="button" onclick="searchMoghayeratDarVosolNamayande()" style="margin-left:25px" value="جستجو">
                    <span class="noThing"></span>
                    <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom3()"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function searchMoghayeratDarVosolNamayande() {
        $.ajax({
            type: "POST",
            url: 'listMoghayeratDarVosolNamayande.action',
            data: $('#listMoghayeratDarVosolNamayande').serialize(),
            success: function (response) {
                $('#displayTab3').html(response);
            }
        });
    }

</script>