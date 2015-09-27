<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@include file="/jsp/josteju/searchNamayandegi.jsp"%>--%>
<div id="allofem" class="listenTextFieldEnter" functionCall="searchKhesaratsAjax()">
<form action="/searchForKhesarat.action" id="form_search_for_khesarat" method="post">
<input type="hidden" name="selectedTab" id="sel_sel_tab" value="tabs-4"/>
<table class="inputList" cellspacing="5" width="90%">
    <tr>
        <td>
            <span class="noThing"></span>
            <input type="text" name="khesaratVMSrch.shomareBimename" id="ksrt_search_shomareBime"/>
            &nbsp;<label>شماره بیمه نامه</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type="text" name="khesaratVMSrch.shomareParvande" id="ksrt_search_shmr_prvnde"/>
            &nbsp;<label>شماره پرونده</label>
        </td>
        <td>
            <input type="text" name="khesaratVMSrch.createdDate" id="ksrt_search_crtd_date"
                   class="validate[custom[date] datePkr"/>
            &nbsp;<label>تاریخ تشکیل پرونده</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <input type="text" name="khesaratVMSrch.bimeShodeCodeMeli"
                   id="ksrt_search_bimegozar_code_meli"/>
            &nbsp;<label>کد ملی بیمه گذار</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type="text" name="khesaratVMSrch.bimeGozarFirstName"
                   id="ksrt_search_bimegozar_fisrt_name"/>
            &nbsp;<label>نام بیمه گذار</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type="text" name="khesaratVMSrch.bimeGozarLastName"
                   id="ksrt_search_bimegozar_last_name"/>
            &nbsp;<label>نام خانوادگی بیمه گذار</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <select name="khesaratVMSrch.stateId" id="ksrt_search_stt">
                <option></option>
                <option value="600">تشکیل پرونده</option>
                <option value="610">تسویه پرداخت شده</option>
                <option value="620">تسویه غیرقابل پرداخت</option>
                <option value="630">انصراف مشتری</option>
                <option value="640">درخواست موقت خسارت</option>
                <option value="619">درخواست خسارت جدید</option>
                <option value="642">خسارت مشاهده شده</option>
                <option value="644">نياز به اخذ مجوز</option>
                <option value="645">صدور مجوز</option>
                <option value="646">عدم صدور مجوز</option>
                <option value="647">خاتمه پرونده خسارت</option>
                <option value="648">منتظر بررسی کارشناس خسارت</option>
                <option value="649">نقص مدارك درخواست خسارت</option>
                <option value="650">اصلاح مدارك درخواست خسارت</option>
                <option value="651">منتفی</option>
                <option value="652">درخواست الحاقيه خسارت جديد</option>
                <option value="653">بررسي مجدد پرونده خسارت</option>
                <option value="654">تاييد بررسي پرونده خسارت</option>
                <option value="655">منتظر تاييد نهايي پرونده خسارت</option>
                <option value="656">تاييد نهايي خسارت</option>
                <option value="657">الحاقیه خسارت مشاهده شده</option>
                <option value="658">کارشناسی</option>

            </select>
            &nbsp;<label>وضعیت</label>
        </td>
    <td colspan="3">
        <%--<script type="text/javascript">--%>
            <%--function clearSeachFrom_b()--%>
            <%--{--%>
                <%--$('#search_kodMelliBimeShodeDarkhast').val('');--%>
                <%--$('#search_familyBimeShodeDarkhast').val('');--%>
                <%--$('#search_nameBimeShodeDarkhast').val('');--%>
                <%--$('search_kodMelliBimeGozar').val('');--%>
                <%--$('#search_familyBimeGozarDarkhast').val('');--%>
                <%--$('#search_nameBimeGozarDarkhast').val('');--%>
                <%--$('#search_taTarikhDarkhast').val('');--%>
                <%--$('#search_azTarikhDarkhast').val('');--%>
                <%--$('#search_nameKarshenas').val('');--%>
                <%--$('#search_namayandegiNameDarkhast').val('');--%>
                <%--$('#search_shomareBime').val('');--%>
            <%--}--%>
        <%--</script>--%>
        <%--<input type="button" style="margin-left:25px" value="پاک کردن فرم" onclick="clearSeachFrom_b()">--%>

            <input type="button" onclick="searchKhesaratsAjax()" style="margin-left:25px" value="جستجو">
     </td>
    </tr>
</table>
</form>
</div>
<script type="text/javascript">
    function searchKhesaratsAjax()
    {
        $.ajax({
            type: "POST",
            url: 'resultTab6.action',
            data: $('#form_search_for_khesarat').serialize(),
            success: function (response)
            {
                // we have the response
                $('#displayTab6').html(response);
            }
        });
    }
</script>
