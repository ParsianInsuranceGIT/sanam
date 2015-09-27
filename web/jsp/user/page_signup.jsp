<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="h" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>
    ثبت نام
</title>
<s:actionmessage/>
<div align="center">

    <script type="text/javascript">
        function checkIrani()
        {
            $('#shmreMelliId').show();
            $('#codeShenasayiId').hide();
            $('#shmreSabtId').hide();
            $('#tarikhSabtId').hide();
            $('#flagId').val('0'); //0 = shomare melli
            document.getElementById("iraniId").checked = true;
            document.getElementById("kharejiId").checked = false;
            document.getElementById("hoghooghiId").checked = false;

        }
        function checkKhareji()
        {
            $('#shmreMelliId').hide();
            $('#codeShenasayiId').show();
            $('#shmreSabtId').hide();
            $('#tarikhSabtId').hide();
            $('#flagId').val('1'); //1 = code farde khareji
            document.getElementById("iraniId").checked = false;
            document.getElementById("kharejiId").checked = true;
            document.getElementById("hoghooghiId").checked = false;
        }
        function checkHoghooghi()
        {
            $('#shmreMelliId').hide();
            $('#codeShenasayiId').hide();
            $('#shmreSabtId').show();
            $('#tarikhSabtId').show();
            $('#flagId').val('2'); //2 = code hoghooghi
            document.getElementById("iraniId").checked = false;
            document.getElementById("kharejiId").checked = false;
            document.getElementById("hoghooghiId").checked = true;
        }
    </script>

    <div style=" border: 1px solid #999999;  width:30%; margin-top:5px; margin-bottom:10px; text-align: justify; padding: 15px;"
         id="err_pish_payment">
        <img src="/img/delete.png" height="15px" width="15px" style="float: left;"
             onclick="$('#err_pish_payment').fadeOut(50);" title="بستن"/>
    <span dir="rtl" style="text-align: justify;color: #cd0a0a;">
    <b>
        لطفا در رابطه با انتخاب رمز عبور به موارد زیر توجه نمایید:
    </b>
    </span><br/><br/><br/>

    <span dir="rtl" style="text-align: justify; ">
* در انتخاب کلمه عبور از حروف فارسی استفاده نکنید.
    </span><br/><br/>
    <span dir="rtl" style="text-align: justify;">
* رمز عبور باید حداقل 8 کاراکتر شامل حرف و عدد باشد.
    </span><br/><br/>
    </div>
    <div style="width: 30%">
        <table>
            <tr>
                <td>
                    <input type="radio" onclick="checkIrani();" id="iraniId"> <!-- irani-->
                    <label>حقیقی</label><label>(ایرانی)</label>
                </td>
                <td>
                    <input type="radio" onclick="checkKhareji();" id="kharejiId">  <!-- khareji-->
                    <label>حقیقی</label><label>(اتباع خارجی)</label>
                </td>
                <td><input type="radio" onclick="checkHoghooghi();" id="hoghooghiId">  <!-- hoghooghi-->
                    &nbsp;<label>حقوقی</label></td>
            </tr>
        </table>
    </div>
    <br>
    <form action="/signUpDo" method="post">
        <input type="hidden" name="flagPage" id="flagId" value="0"/>

        <table>
            <tr>
                <td>شماره بیمه نامه</td>
                <td><s:textfield key="shomareBimename" label="" theme="simple"/></td>
            </tr>
            <tr>
                <td>تاریخ صدور بیمه نامه</td>
                <td><s:textfield key="tarikhSodur" label="" theme="simple" cssClass="datePkr" readonly="true"/></td>
            </tr>
            <tr id="shmreMelliId">
                <td>شماره ملی بیمه گذار</td>
                <td><s:textfield key="shomareMelli" label="" theme="simple"/></td>
            </tr>
            <tr id="codeShenasayiId">
                <td><label>کد شناسایی</label></td>
                <td><s:textfield key="codeShenasayi" label="" theme="simple"/></td>
            </tr>
            <tr id="shmreSabtId">
                    <td>شماره ثبت</td>
                    <td><s:textfield key="shmreSabt" label="" theme="simple"/></td>
            </tr>
            <tr id="tarikhSabtId">
                <td>تاریخ ثبت</td>
                <td><s:textfield key="tarikhSabt" label="" theme="simple" cssClass="datePkr" readonly="true"/></td>
            </tr>
            <tr>
                <td>رمز عبور</td>
                <td><s:password key="password1" label="" theme="simple"/></td>
            </tr>
            <tr>
                <td>تکرار رمز عبور</td>
                <td><s:password key="password2" label="" theme="simple"/></td>
            </tr>
            <%--<tr>--%>
            <%--<td colspan="2" align="center">--%>
            <%--<img src="/stickyImg.captcha"/>--%>
            <%--<br/>--%>
            <%--<input name="answer"/>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <tr>
                <td colspan="2" align="center">
                    <img id="kaptchaImage" src="/kaptcha.jpg"/>
                    <script type="text/javascript">
                        $(function () {
                            $('#kaptchaImage').click(function () {
                                $(this).hide()
                                        .attr('src', '/kaptcha.jpg?' + Math.floor(Math.random() * 100))
                                        .fadeIn();
                            })
                        });
                    </script>
                    <br/>
                    <small>اگر قادر به خواندن حروف تصویر نیستید روی آن کلیک کنید.</small>
                </td>
            </tr>
            <tr>
                <td>حروف تصویر</td>
                <td><input type="text" name="kaptcha" value=""/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="ثبت نام" style="margin-left: 22px"/>
                </td>
            </tr>

        </table>
        <script type="text/javascript">
            $('#shmreMelliId').show();
            $('#codeShenasayiId').hide();
            $('#shmreSabtId').hide();
            $('#tarikhSabtId').hide();
            //---------reset-------------//
            $('#shomareMelli').val('');
            $('#codeShenasayi').val('');
            $('#shmreSabt').val('');
            $('#tarikhSabt').val('');
            document.getElementById("iraniId").checked = true;
            $('#flagId').val('0'); //0 = shomare melli
        </script>
    </form>
</div>