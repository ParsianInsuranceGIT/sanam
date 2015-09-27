<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<head>
    <title>بارگذاری فایل</title>
</head>
<div dir="rtl" class=expandableTitleBar style="text-align:right !important;">
    <p class=heading ><span class="ui-icon ui-icon-plus" style="float:right;"></span>
        فرم بارگذاری فایل:
    </p>
    <form id="uploadform" action="" method="POST" enctype="multipart/form-data">
        <table align="center" width="900px" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
            <tr>
                <td>
                    نام عملیات

                    <select name="operationType" id="operationType" class="validate[required]">
                        <option selected value="" onclick="changeBankMaghsad(0)">انتخاب کنید</option>
                        <option value="AGHSATE_OMR" onclick="changeBankMaghsad(1)">اقساط عمر</option>
                        <option value="PISHPARDAKHT_OMR" onclick="changeBankMaghsad(2)">پیش پرداخت عمر</option>
                        <option value="TAEED_VOSOL" onclick="changeBankMaghsad(3)">تایید وصول</option>
                    </select>
                </td>

                <td>
                    شماره حساب

                    <select name="bankMaghsad" id="bankMaghsad" class="validate[required]">
                        <option value="">انتخاب کنید</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2"><h1>لطفا یک فایل را بارگذاری نمایید:</h1></td>
            </tr>
            <tr>
                <td>
                    <input type="file" name="upload" />
                </td>
                <td align="center">
                    <input type="button" onclick="decideTheSubmition()" value="ارسال"/>
                    <input type="button" onclick="window.location='/loginUser.action'" value="بازگشت"/>
                </td>
            </tr>
        </table>
    </form>

</div>
<script type="text/javascript">
    $(document).ready(function() {
        $("operationType").prop('selectedIndex', 0);
    });

    function decideTheSubmition(){
        $("#uploadform").attr('action','/doUploadForExcel');
        $("#uploadform").submit();
    }

    function changeBankMaghsad(type){
        if (type == 1){
            $("#bankMaghsad").empty();
            $('#bankMaghsad').append($("<option></option>").attr("value", '').text("انتخاب کنید"));
            $('#bankMaghsad').append($("<option></option>").attr("value", '81011989').text("پارسیان (81011989)"));
            $('#bankMaghsad').append($("<option></option>").attr("value", '17038494').text("تجارت (17038494)"));
            $('#bankMaghsad').append($("<option></option>").attr("value", '0185007111').text("تجارت جدید (0185007111)"));
            $('#bankMaghsad').append($("<option></option>").attr("value", '0200234164006_G').text("پارسیان (0200234164006)"));
            $('#bankMaghsad').append($("<option></option>").attr("value", '0201136462000_G').text("پارسیان - تجارت الکترونیک (02001136462000)"));
        } else if (type == 2){
            $("#bankMaghsad").empty();
            $('#bankMaghsad').append($("<option></option>").attr("value", '').text("انتخاب کنید"));
            $('#bankMaghsad').append($("<option></option>").attr("value", '0200234164006').text("پارسیان (0200234164006)"));
        } else if (type == 3){
            $("#bankMaghsad").empty();
            $('#bankMaghsad').append($("<option></option>").attr("value", '').text("انتخاب کنید"));
            $('#bankMaghsad').append($("<option></option>").attr("value", '0201136462000').text("پارسیان - تجارت الکترونیک (02001136462000)"));
//            $('#bankMaghsad').append($("<option></option>").attr("value", '17123130').text("تجارت - سپهبد قرنی (17123130)"));
//            $('#bankMaghsad').append($("<option></option>").attr("value", '4757575763').text("ملت - ونک (4757575763)"));
        } else{
            $("#bankMaghsad").empty();
            $('#bankMaghsad').append($("<option></option>").attr("value", '').text("انتخاب کنید"));
        }
    }
</script>