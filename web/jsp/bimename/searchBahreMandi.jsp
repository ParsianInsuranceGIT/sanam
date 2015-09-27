<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="allofem" class="listenTextFieldEnter" functionCall="searchBimenameAjax()">
<form action="/resultTab9" id="form_search_for_bahremandi" method="post">

<input type="hidden" name="selectedTab" id="sel_sel_tab" value="tabs-9"/>
<input type="hidden" name="searchBahreMandi" id="srch_bhrmndi" value="yes"/>
<table class="inputList" cellspacing="5" width="90%">
<tr>
    <td>
        <span class="noThing"></span>
        <select name="bahreMandiVMS.karshenasId" id="search_karshenasId">
            <option id="deafultKarshenas" value="0">-</option>
            <c:forEach var="karshenas" items="${karshenasha}">
                <option ${ bahreMandiVMS.karshenasId == karshenas.id ? 'selected=selected' : ''}
                        value="${karshenas.id}">${karshenas.firstName}&nbsp;${karshenas.lastName}</option>
            </c:forEach>
        </select>
        &nbsp;<label>نام کارشناس</label>
    </td>
    <td>
        <span class="noThing"></span>
        &nbsp;<label>مبلغ نوع بهره مندی (از)</label>
        <input type="text" name="bahreMandiVMS.mablagheBahreMandiFrom" id="search_fromMablaghVam" class="" value="${bahreMandiVMS.mablagheBahreMandiFrom}"/>
    </td>
    <td>
        <span class="noThing"></span>
        &nbsp;<label>مبلغ نوع بهره مندی (تا)</label>
        <input type="text" name="bahreMandiVMS.mablagheBahreMandiTo" id="search_toMablaghVam" class="" value="${bahreMandiVMS.mablagheBahreMandiTo}"/>
    </td>
</tr>
<tr>
    <td>
        <span class="noThing"></span>
        <input type="text" name="bahreMandiVMS.shomareBahreMandi" id="search_shomareVam" value="${bahreMandiVMS.shomareBahreMandi}"/>
        &nbsp;<label>شماره نوع بهره مندی</label>
    </td>
    <td>
        <span class="noThing"></span>
        <input type="text" name="bahreMandiVMS.shomareBimename" id="search_shomareBimen" value="${bahreMandiVMS.shomareBimename}"/>
        &nbsp;<label>شماره بیمه نامه</label>
    </td>
    <td id="search_stateId_div">
        <span class="noThing"></span>
        <select name="bahreMandiVMS.stateId" id="search_stateId">
            <c:forEach var="stt" items="${statesBahreMandi}" varStatus="i">
                <option class="${stt.stateMachineName}" ${bahreMandiVMS.stateId == stt.id ? 'selected=selected' : ''} value="${stt.id}">${stt.stateName}</option>
            </c:forEach>
            <%--<option value="0" id="stt_ALL_bahreMandi">-</option>--%>
            <%--<option value="10070">احراز هویت شده</option>--%>
            <%--<option value="10000">موقت</option>--%>
            <%--<option value="10010">جدید</option>--%>
            <%--<option value="10020">مشاهده شده</option>--%>
            <%--<option value="10050">اصلاح درخواست</option>--%>
            <%--<option value="10040">نیازمند اصلاح درخواست</option>--%>
            <%--<option value="10030">کارشناسی</option>--%>
            <%--<option value="10060">عدم تطابق امضاء</option>--%>
            <%--<option value="10130">درخواست نهایی</option>--%>
            <%--<option value="10140">انصراف</option>--%>
            <%--<option value="10150">منتفی</option>--%>
            <%--<option value="10080">استعلام شرایط وام</option>--%>
            <%--<option value="10090">وام قسط‌ بندي شده</option>--%>
            <%--&lt;%&ndash;<option value="10100">اعلام به مالي وام</option>&ndash;%&gt;--%>
            <%--<option value="10110">صدور نهايي واعلام به مالي وام</option>--%>
            <%--<option value="10120">ارسال دفترچه وام</option>--%>
            <%--<option value="10160">دریافت دفترچه وام و خاتمه--%>
            <%--</option>--%>
        </select>
        &nbsp;<label>وضعیـت</label>
    </td>
</tr>
<tr>
    <td>
        <span class="noThing"></span>
        <select name="bahreMandiVMS.darkhastType" id="search_type_bahre_mandi">
            <option selected="selected" onclick="changeStt(' ');" value=""></option>
            <option onclick="changeStt('VAM');" value="VAM">وام</option>
            <option onclick="changeStt('BARDASHT_AZ_ANDOKHTE');" value="BARDASHT_AZ_ANDOKHTE">برداشت از اندوخته</option>
        </select>
        &nbsp;<label>نوع بهره مندی از منافع</label>
    </td>
    <td colspan="3">
        <script type="text/javascript">
            function clearSeachFrom_bahreMandi()
            {
                $('#deafultKarshenas').attr('selected',true);
                $('#stt_ALL_bahreMandi').attr('selected',true);
                $('#search_fromMablaghVam').val('');
                $('#search_toMablaghVam').val('');
                $('#search_shomareVam').val('');
                $('#search_shomareBimen').val('');
            }
        </script>
        <input type="button" style="margin-left:25px" value="پاک کردن فرم" onclick="clearSeachFrom_bahreMandi()">
        <%--<input type="submit" style="margin-left:25px" value="جستجو">--%>
        <input type="button" onclick="searchBahremandiAjax()" style="margin-left:25px" value="جستجو">
    </td>
</tr>
</table>
</form>
</div>
<script type="text/javascript">
    function searchBahremandiAjax()
    {
        $.ajax({
            type: "POST",
            url: 'resultTab9.action',
            data: $('#form_search_for_bahremandi').serialize(),
            success: function (response)
            {
                // we have the response
                $('#displayTab9').html(response);
            }
        });
    }

    function changeStt(arg)
    {
        if (arg == 'VAM')
        {
            $('#search_stateId_div').show();
            $('#search_stateId').show();
            $('.BARDASHT_AZ_ANDOKHTE_SYSTEM').hide();
            $('.VAM_SYSTEM').show();
        }
        else if (arg == 'BARDASHT_AZ_ANDOKHTE')
        {
            $('#search_stateId_div').show();
            $('#search_stateId').show();
            $('.BARDASHT_AZ_ANDOKHTE_SYSTEM').show();
            $('.VAM_SYSTEM').hide();
        }
        else
        {
            $('#search_stateId_div').hide();
            $('#search_stateId').hide();
        }

    }

    $(document).ready(function(){$('.BARDASHT_AZ_ANDOKHTE_SYSTEM').hide();});

</script>