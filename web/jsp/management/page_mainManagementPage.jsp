<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<html>
<head>
    <title>داشبورد مدیریتی</title>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <style type="text/css">
        #mainManagementTable td{
            vertical-align:top;
        }
    </style>
</head>
<body>
<input type="button" onclick="window.location='/loginUser'" value="صفحه پیشنهادات"/>
<table width="100%" id="mainManagementTable">
<tr>
    <td rowspan="2">
        <div class=expandableTitleBar>
            <p class=heading><span class="ui-icon ui-icon-plus" style="float:right;"></span>
                گزارشات
            </p>
            <table border="0" cellpadding="5" cellspacing="0" width="98%" style="text-align:right;">
                <sec:authorize ifAnyGranted="ROLE_SUPERVISOR, ROLE_KARSHENAS_MASOUL, ROLE_ADMIN, ROLE_RAEIS_SODUR, ROLE_PAS_KARSHENAS_MASOUL, ROLE_PAS_RAEIS" >
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozaresheBordroyeSodor'">گزارش بردرو صدور بیمه نامه های عمر و سرمایه گذاری</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozaresheSaleRanking' ">گزارش
                            رتبه‌بندي شبكه فروش</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);"
                           onclick="window.location='/prepareGozaresheBordroyeMabaleghPardakhti'">گزارش بردروي مبالغ
                            پرداختی بیمه نامه های عمر و سرمایه گذاری</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozaresheAghsatMoavagh'">گزارش
                            بردروی اقساط معوق بیمه نامه های عمر و سرمایه گذاری</a>
                    </td>
                </tr>
                </sec:authorize>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozaresheZakhireRiazi'">گزارش
                            بردروی ذخیره ریاضی واقعی عمر و سرمایه گذاری</a>
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<td>--%>
                        <%--<a href="javascript:void(0);" onclick="window.location='/prepareGozaresheZakhireRiaziTafkikGhest'">گزارش--%>
                            <%--بردروی ذخیره ریاضی واقعی عمر و سرمایه گذاری (تفکیک قسط)</a>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozaresheBordroyeBazkharidShode'">گزارش بردروي بيمه نامه هاي بازخريد شده</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozaresheBordroyeSarresidShode'">گزارش بردروي بيمه نامه هاي سررسيد شده</a>
                    </td>
                </tr>
                <sec:authorize ifAnyGranted="ROLE_PAS_RAEIS,ROLE_PAS_KARSHENAS_MASOUL">
                    <tr>
                        <td>
                            <a href="javascript:void(0);" onclick="window.location='/prepareElhaghieReport.action'">
                                گزارش بردرو الحاقیه های صادر شده
                            </a>
                        </td>
                    </tr>
                </sec:authorize>

                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozareshKhesarat'">گزارش خسارت عمر و
                            سرمایه گذاری</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/gozareshAghsatMali'">گزارش اقساط مالی</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/gozareshAghsatMoavagh'">گزارش اقساط پرداخت نشده
                            معوق</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/gozareshAghsatMahane'">گزارش حق بیمه قابل
                            پرداخت در بازه زمانی ماهانه</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozareshTedadiVaziatPishnehad'">گزارش
                            تعدادی وضعیت پیشنهاد سیستم</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozareshMoghayeseNamayande'">گزارش
                            مقایسه ای تعداد نمایندگان</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozareshNemudarSabtPishnehad'">گزارش
                            نمودار تعداد ثبت پیشنهادات روزانه</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozareshTedadPishnehadTafkikKarbar'">گزارش
                            تعدادی پیشنهادات به تفکیک کاربر</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozareshTedadPishnehadTafkikVaziat'">گزارش
                            تعدادی پیشنهادات به تفکیک وضعیت</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozareshTransitionLog'">گزارش تاریخچه
                            تغییر وضعیت ها به تفکیک کاربر</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozareshSadereVarizi'">گزارش پیشنهادات
                            صادره و واریزی ها</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareGozareshPardakhtElectronic'">گزارش
                            پرداخت های الکترونیکی</a>
                    </td>
                </tr>
                <tr>
                    <td>

                        <a href="javascript:void(0);" onclick="window.location='/batchTaghsitReport.action'">
گزارش تقسیط گروهی
                        </a>
                    </td>
                </tr>
                <tr>
                    <td>

                        <a href="javascript:void(0);" onclick="window.location='/makeGozaresheAndukhteSaalAvaal.action'">
                            گزارش اندوخته سال اول قراردادهای سیستم
                        </a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/prepareAghsatVamReport.action'">
                            گزارش اقساط وام
                        </a>
                    </td>
                </tr>

            </table>
        </div>
    </td>
    <td>
        <div class=expandableTitleBar>
            <p class=heading><span class="ui-icon ui-icon-plus" style="float:right;"></span>
                عملیات پزشکی
            </p>
            <table border="0" cellpadding="5" cellspacing="0" width="98%" style="text-align:right;">
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/moshahedeClinics'">
                            مدیریت کلینیک ها
                        </a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/listAllAzmayeshTypes'">
                            مدیریت انواع آزمایش ها
                        </a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/listAllAzmayeshNames'">
                            مدیریت نام آزمایش ها
                        </a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/sabtHazinePezashki'">ثبت هزینه های
                            پزشکی</a>
                    </td>
                </tr>
            </table>
        </div>
    </td>
    <td>
        <div class=expandableTitleBar>
            <p class=heading><span class="ui-icon ui-icon-plus" style="float:right;"></span>
                عملیات دریافت و پرداخت
            </p>
            <table border="0" cellpadding="5" cellspacing="0" width="98%" style="text-align:right;">

                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='page_sabtFileTxtAzBank.jsp'">آپلود فایل بانک</a>
                    </td>
                </tr>
                <sec:authorize ifAnyGranted="ROLE_SUPERVISOR">
                    <tr>
                        <td>
                            <a href="javascript:void(0);" onclick="window.location='/fin/viewDasteCheckHa'">دسته چک ها</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="javascript:void(0);" onclick="window.location='/fin/viewKhateSanadHa'">مشاهده اسناد</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="javascript:void(0);" onclick="window.location='/fin/listAllEtebaratCheck'">مشاهده چک های
                                دریافتی</a>
                        </td>
                    </tr>
                </sec:authorize>
            </table>
        </div>
    </td>
</tr>
<tr>
    <td>

        <div class=expandableTitleBar>
            <p class=heading><span class="ui-icon ui-icon-plus" style="float:right;"></span>
                صدور
            </p>
            <table border="0" cellpadding="5" cellspacing="0" width="98%" style="text-align:right;">
                <sec:authorize ifAnyGranted="ROLE_SUPERVISOR, ROLE_KARSHENAS_SODUR, ROLE_KARSHENAS_MASOUL, ROLE_KARSHENAS_BAYEGANI ">
                    <tr>
                        <td>
                            <a href="javascript:void(0)" onclick="window.location='/listAllSerial.action'">مدیریت سریال بیمه نامه</a>
                        </td>
                    </tr>
                </sec:authorize>
                <sec:authorize ifAnyGranted="ROLE_SUPERVISOR, ROLE_RAEIS_SODUR, ROLE_KARSHENAS_MASOUL">
                    <tr>
                        <td>
                            <a href="javascript:void(0);" onclick="window.location='/bazgashtVaziatShow'">بازگشت وضعیت</a>
                        </td>
                    </tr>
                </sec:authorize>
            </table>
        </div>

        <sec:authorize ifAnyGranted="ROLE_SUPERVISOR">
        <div class="expandableTitleBar">
            <p class="heading">
                <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                سمت ها
            <table cellpadding="5" cellspacing="0" width="98%" style="text-align:right">

                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/listAllSemats'">مدیریت سمت ها</a>
                    </td>
                </tr>
            </table>
            </p>
        </div>
        <div class="expandableTitleBar">
            <p class="heading">
                <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                شهرها
            <table cellpadding="5" cellspacing="0" width="98%" style="text-align:right">

                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/listAllCities'">مدیریت شهرها و استان
                            ها</a>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>


            </table>
            </p>
        </div>
        <div class="expandableTitleBar">
            <p class="heading">
                <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                بانک ها
            <table cellpadding="5" cellspacing="0" width="98%" style="text-align:right">

                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/listAllBanks'">مدیریت بانک ها</a>
                    </td>
                </tr>


            </table>
            </p>
        </div>
        </sec:authorize>
    </td>
    <td>
        <sec:authorize ifAnyGranted="ROLE_SUPERVISOR">
            <div class=expandableTitleBar>
                <p class=heading><span class="ui-icon ui-icon-plus" style="float:right;"></span>
                    مشارکت در منافع
                </p>
                <table border="0" cellpadding="5" cellspacing="0" width="98%" style="text-align:right;">
                    <tr>
                        <td>
                            <a href="javascript:void(0);" onclick="window.location='/prepareForMohasebeMosharekat'">تسهیم
                                در سود مشارکت</a>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </div>
            <div class="expandableTitleBar">
                <p class="heading">
                    <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                    نماینده ها
                <table cellpadding="5" cellspacing="0" width="98%" style="text-align:right">

                    <tr>
                        <td>
                            <a href="javascript:void(0);" onclick="window.location='/listAllNamayande'">مدیریت
                                نمایندگان</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="javascript:void(0);" onclick="window.location='/listAllNamayandeForKarmozd'">پرداخت
                                کارمزد</a>
                        </td>
                    </tr>
                </table>
                </p>
            </div>
            <div class="expandableTitleBar">
                <p class="heading">
                    <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                    کاربران
                <table cellpadding="5" cellspacing="0" width="98%" style="text-align:right">

                    <tr>
                        <td>
                            <a href="javascript:void(0);" onclick="window.location='/listAllUser'">مدیریت کاربران</a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="javascript:void(0);" onclick="window.location='/showEmzaKonandegan'">مدیریت
                                امضاکنندگان</a>
                        </td>
                    </tr>
                </table>
                </p>
            </div>
            <div class=expandableTitleBar>
                <p class=heading><span class="ui-icon ui-icon-plus" style="float:right;"></span>
                    مهندسی محصول
                </p>
                <table border="0" cellpadding="5" cellspacing="0" width="98%" style="text-align:right;">
                    <tr>
                        <td>
                            <a href="javascript:void(0);" onclick="window.location='/prepareConstantsItems'">نمایش
                                لیست</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="javascript:void(0);" onclick="window.location='/listAllTarhs'">مدیریت طرح ها</a>
                        </td>
                    </tr>

                </table>
            </div>
        </sec:authorize>
    </td>
</tr>
</table>
</body>
</html>