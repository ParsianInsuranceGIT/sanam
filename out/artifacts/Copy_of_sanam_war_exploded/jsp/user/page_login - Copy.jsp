<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 5/11/11
  Time: 3:12 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--<c:set var="userid" value='<%=(Integer)request.getSession().getAttribute("userid")%>'/>--%>
<%--<c:if test="${userid != null}">--%>
<sec:authorize ifAllGranted="ROLE_USER">
    <%response.sendRedirect("/loginUser.action"); %>
</sec:authorize>
<%--</c:if>--%>
<html>
<head>
    <style>
    body {
            background-image:url("/img/logo2.jpg");
        /*background: #009933;*/
            background-repeat:no-repeat;
            /*background-attachment:fixed;*/
            background-position:center;
         }
    </style>
    <title>ورود</title>
</head>
<body>
<%--<s:actionmessage/>--%>
<p style="color:red;font-weight: bold;">
    <c:if test="${nosession=='true'}">
        برای دسترسی به این صفحه لازم است لاگین نمایید!
    </c:if>
</p>


<div id="main">
    <div class="main-login-div">
        <form action="j_spring_security_check" method="post" name="target" id="target">

<p align="center" style=" width:370px">
جهت استعلام نرخ بيمه هاي عمروسرمايه گذاري روي لينك های زير كليك نماييد.
    </br>
    نيازي به درج نام كاربري و كلمه عبور
                    نمي باشد

</p>

            <input type="button" style="width: 220px; margin: 4px;"
                   onclick="window.location='/jsp/estelamMultiple/page_andukhteyeSarmayegozariMultiple.jsp'"
                   value="استعلام بیمه نامه جامع خانواده">
            <input type="button" style="width: 220px;"
                   onclick="window.location='/prepareForEstelam.action'"
                   value="استعلام بیمه نامه عمر و سرمایه گذاری">
            <table class="nonGrid" align="center" dir="rtl">
                <%if (request.getParameter("error") != null && request.getParameter("error").equals("failed")) {%>
                <tr>
                    <td colspan="2" style="color:red">
                        نام کاربری یا کلمه عبور وارد شده صحیح نیست.
                    </td>
                </tr>
                <%}%>
                <%if (request.getParameter("error") != null && request.getParameter("error").equals("invalid_bg")) {%>
                <tr>
                    <td colspan="2" style="color:red">
                        کد ملی شما معتبر نیست. لطفا به نمایندگی خود مراجعه نمایید.
                    </td>
                </tr>
                <%}%>
                <%--<tr>--%>
                <%--<td></td>--%>
                <%--<td><input type="button" style="width:155px" onclick="window.location='/viewCredebits.action'" value=" تست ذخیره"></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                <%--<td></td>--%>
                <%--<td><input type="button" style="width:155px" onclick="window.location='viewNazarat.action'" value="مشاهده نظرات"></td>--%>
                <%--</tr>--%>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
                <%--<tr>--%>
                <%--<td></td>--%>
                <%--<td><input type="button" style="width:155px"--%>
                <%--onclick="window.location='/jsp/estelamReverse/page_andukhteyeSarmayegozari.jsp'"--%>
                <%--value="استعلام بر مبنای اندوخته"></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                <%--<td></td>--%>
                <%--<td><input type="button" style="width:155px" onclick="window.location='/jsp/mostamari/page_mostamari.jsp'" value="مستمری"></td>--%>
                <%--</tr>--%>
                <tr>
                    <td>
                        نام کاربری:&nbsp;&nbsp;&nbsp;
                    </td>


                    <td>
                        <input type="text" name="j_username" id="j_username"
                               <c:if test="${not empty param.login_error}">value='<%= session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY) %>'
                        </c:if>/>
                    </td>
                </tr>
                <tr>
                    <td>
                        کلمه عبور:&nbsp;&nbsp;&nbsp;
                    </td>
                    <td>
                        <input type="password" name="j_password" id="j_password"/>
                    </td>
                </tr>

                <%
                    if (session.getAttribute("numberOfIncorrectPassword") != null){
                        int i = (Integer) session.getAttribute("numberOfIncorrectPassword");
                        session.setAttribute("numberOfIncorrectPassword", i+1);
                    }
                    else{
                        session.setAttribute("numberOfIncorrectPassword", 1);
                    }

                    if (session.getAttribute("numberOfIncorrectPassword") != null && ((Integer) session.getAttribute("numberOfIncorrectPassword") > 3)){
                %>
                <tr>
                    <td colspan="2" align="center">
                        <img id="kaptchaImage" src="/kaptcha.jpg"/>
                        <script type="text/javascript">
                                $('#kaptchaImage').click(function () {
                                    $(this).hide()
                                            .attr('src', '/kaptcha.jpgی' + Math.floor(Math.random() * 100))
                                            .fadeIn();
                                });


                        </script>
                        <br/>
                        <div id="kaptchaInformaion">
                            <small>اگر قادر به خواندن حروف تصویر نیستید روی آن کلیک کنید.</small>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>حروف تصویر</td>
                    <td><input type="text" name="kaptcha" id="kaptcha" value="" onkeypress="searchKeyPress(event);"/></td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <td colspan="2">
                        <label style="margin-right: 115px;">من را بخاطر بسپار</label>
                        <input type='checkbox' name='_spring_security_remember_me'/>
                    </td>
                </tr>
                    <td colspan="2">
                        <% if (session.getAttribute("numberOfIncorrectPassword") != null && ((Integer) session.getAttribute("numberOfIncorrectPassword") > 3)){ %>
                            <input type="button" id="loginBtn" onclick="loginFunc(document.forms[0].kaptcha.value)" value="ورود"/>
                        <% } else { %>
                            <input type="submit" value="ورود"/>
                        <% } %>
                        &nbsp;&nbsp;
                        <br/><br/>
                       <input type="button" id="forgetPassword" onclick="window.open('/jsp/user/page_forgetPassword.jsp','_self')" value="بازیابی کلمه عبور"/>
                       <input type="button" onclick="window.location='/signUp'" value="ثبت نام"/>


                        <%--<input type="button" id="forgetPassword" onclick="window.open('/jsp/user/page_forgetPassword.jsp','_self')" value="بازیابی کلمه عبور"/>--%>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript">
    function getUrl()
    {
        var vars = [], hash;
        var hashes = window.location.href.split('/');
        var url = hashes[0] + "//" + hashes[2];
        return url;
    }

function loginFunc(valueKap){
    $.ajax({
        type: "POST",
        url: getUrl() + '/validateKaptcha.action',
        data: "kaptcha=" + valueKap ,
        success: function (response) {
            if (response=='true'){
                document.forms[0].submit();
            }else{
                $('#kaptchaInformaion').html("<small style='color: red;'>حروف تصویر به درستی وارد نشده است</small>");
                $('#kaptchaImage').click();
            }
        }
    });
}

function searchKeyPress(e)
{
    if (e.keyCode == 13)
    {
        loginFunc(document.forms[0].kaptcha.value);
    }
}

</script>
<%--
<p style="text-align: center; width: 70%; color: red; margin: 0 auto;">
"مشکل موجود در تب "همه پیشنهادها" در دست بررسی و مرتفع سازی می‌باشد."
</p>

<br/><br/>
--%>

<p style="text-align: center; width: 70%;font-size:14pt;font-style: italic;color: blue; margin: 0 auto;">
    :اطلاعيه ها:
</p>

<%--<br/>--%>
<%--<b><p style="text-align: center; width: 70%; color: #ee0101; margin: 0 auto;">--%>
    <%-->>حل مشکل پرداخت اينترنتي اقساط و پيش پرداخت در اتصال به بانک<<--%>
<%--</br>--%>
<%--ضمن تبریک دهه فجر و عذرخواهی بابت قطعی پرداخت های اینترنتی، به اطلاع می رساند برای پرداخت اینترنتی اقساط در صورت دیدن صفحه خطا مراحل زیر را انجام دهید:--%>
<%--</br>--%>
<%--I understand the risks --> Add Exception --> Confirm--%>
<%--</p></b>--%>
<%--</br>--%>
<%--<br/>--%>
<%--<b><p style="text-align: center; width: 70%; color: green; margin: 0 auto;">--%>
    <%--در صورت بروز مشکل در عملکرد سیستم حتما مرورگر موزیلا فایرفاکس خود را به روز رسانی نموده و با کلیدهای Ctrl+F5 سیستم را مجددا بارگذاری نمایید.--%>
<%--</p><b/>--%>
<br/>

<b>
	<p style="font-family:b nazanin;font-size:15px;text-align: center; width: 100%; color: #ee0101; margin: 0 auto;">


- به استحضار مي‌رساند فرم شرايط عمومي بيمه‌نامه عمر و سرمايه‌گذاري به شماره شناسايي PI31R005 و شماره بازنگري 03 آخرين نسخه ويرايش شده مي‌باشد که جهت استفاده همکاران محترم در مسیر زیر قرار  دارد:
 <br/>
 >>> استعلام بيمه نامه هاي عمروسرمايه‌گذاري > تعهدات بيمه گر،استثنائات و محدوديتهاي بيمه نامه > شرايط عمومي.

	</p>
</b>

<br>
<b><p style="text-align: center; width: 70%; color: #000000; margin: 0 auto;">
    - در راستاي اطلاع رساني و افزايش آگاهي متقاضيان محترم بيمه هاي عمروسرمايه گذاري از كليات شرايط اين بيمه نامه فرم "گواهي اطلاع بيمه گذاران ازکليات شرايط عمومي بيمه نامه عمر و سرمايه گذاري "مورد بازنگري قرار گرفت لذا از كليه همكاران و نمايندگان محترم انتظار مي روداز تاريخ 93/06/01 از فايل جديد جايگزين شده  در قسمت فرمها و مستندات مورد نياز  استفاده نمايند.
</p></b>
<br/>
<br>
<b><p style="text-align: center; width: 70%; color: #000000; margin: 0 auto;">
    -تغييرات مالي در بيمه نامه هاي عمر وسرمايه گذاري ،‌صرفا" از ابتداي سال بيمه اي بعد ميسر مي باشد كه مي بايست يك ماه قبل از شروع سال بيمه اي جديد،  درخواست گردد
</p></b>
<br/

<b><p style="text-align: center; width: 70%; color: #ee0101; margin: 0 auto;">
    به استحضار مي رساند فرايند انتقال اطلاعات بيمه نامه هاي عمر و سرمایه گذاری در سيستم قديم عمر انفرادي آغاز شده است و به تدريج بيمه نامه هاي سيستم قديم به سيستم جديد منتقل مي شود. لازم به ذكر است انتقال اطلاعات از انتهاي سال 91 آغاز شده است و به تدريج شامل بيمه نامه هاي قديمي تر نيز مي شود. خواهشمند است در هنگام انجام هرگونه عمليات روي بيمه نامه هاي عمر و سرمایه گذاری، جهت جلوگيري از تداخل ابتدا وجود بيمه نامه در سيستم جديد را چك نماييد و در صورت عدم يافتن بيمه نامه در سيستم جديد، از طريق سيستم قديم اقدام نماييد. لازم به ذکر است بیمه نامه های عمر و سرمایه گذاری انتقال یافته، در سیستم قدیم به جای 2210 با 5510 آغاز می شوند. بنابراین از انجام هر گونه عملیات روی بیمه نامه هایی که با شماره 5510 آغاز می شوند خودداری فرمایید. بیمه گذاران محترم نیز می بایست مجددا روی این سیستم فرایند ثبت نام را انجام دهند.
</p></b>
<br/>
<br/>

<b><p style="text-align: center; width: 70%; color:  #ee0101; margin: 0 auto;">
به اطلاع مي‌رساند، صدور بيمه‌نامه‌هاي عمروسرمايه‌گذاري (طرح جامع خانواده) از تاريخ 01/07/1393 از سر گرفته شده است. همچنين سامانه استعلام براي ارايه جدول محاسبات رياضي بيمه‌نامه جامع خانواده آماده بهره‌برداري مي‌باشد. بنابراين از همكاران و نمايندگان محترم انتظار مي‌رود نسبت به بازاريابي و فروش اين بيمه‌نامه اهتمام ورزيده و اقدام به راهنمايي متقاضيان و بيمه‌گذاران محترم بيمه‌هاي عمرانفرادي نمايند.
همچنين جهت پيگيري بيمه نامه هاي طرح خانواده به آدرس ذيل مراجعه نمائيد
http://lifemvc.parsianinsurance.ir/lifeinsured/Account/LogOn 
</p></b>
<br/>
<p style="text-align: center; width: 70%; color: #000000; margin: 0 auto;">
    بیمه گذار محترم لطفا جهت ثبت نام در سامانه فایل راهنمای زیر را مطالعه فرمایید.
    <br/><a href='/extra/rahnama_bimegozar.pdf' style="text-decoration:none;color: green;" type="button" class="tabsbtn"><b>
        فایل راهنمای ثبت نام بیمه گذار
    </b></a>

</p>
<br/>

<p style="text-align: center; width: 70%; color: black; margin: 0 auto;">
    نمایندگان محترم، خواهشمند است مشکلات خود در خصوص کار با سیستم جدید ثبت الکترونیک پیشنهادات را‌ در <a
        href='http://ticket.parsianinsurance.com'
        style="text-decoration:none; font-weight: bold; color: black;" type="button" class="tabsbtn" >سامانه
    مدیریت درخواست‌های فناوری اطلاعات</a> به آدرس http://ticket.parsianinsurance.com برای راهبری عمر انفرادی
    ارسال نمایید.
</p>
<br/>
<br/>




<p style="text-align: center; width: 70%; margin: 0 auto;">
    احتراما به اطلاع  نمایندگان محترم مي رساند جهت چاپ اقساط بیمه نامه ها می توانید از مسیر ذیل استفاده نمایید :
    </br>
    تب بیمه نامه ها  >  نمایش بیمه نامه مربوطه  >  فرم وضعیت قسط بندی بیمه نامه  >  استفاده از دکمه های پرینت (در ستون آخر جدول)
</p>

<%--<p style="text-align: center; width: 90%; color: blue; margin: 0 auto;">--%>
    <%--بيمه گذاران محترم، جهت پرداخت اینترنتی اقساط عمر و ثبت نام در سامانه اطلاع رساني بيمه نامه هاي عمر و سرمايه گذاري ا ز لينك های زیر استفاده نمایید.</p>--%>
<%--<br/>--%>

<%--<p style="text-align: center; width: 70%; margin: 0 auto; color: blue;">--%>
	<%--***برای بیمه نامه های صادره 1392،--%>
	<%--<b><a href="/signUp">اینجا </a></b>--%>
	<%--را کلیک نمایید***--%>
<%--</p>--%>
<%--<br/>--%>

<%--<p style="text-align: center; width: 70%; margin: 0 auto; color: blue;">--%>
    <%--***برای بیمه نامه های صادره قبل از 1392،--%>
    <%--<b><a href="http://lifemvc.parsianinsurance.ir/lifeinsured">اینجا </a></b>--%>
	<%--را کلیک نمایید***--%>
<%--</p>--%>
<%--<br/>--%>
<br/>

<p style="text-align: center; width: 70%; color: #000000; margin: 0 auto;">
    احتراما به اطلاع مي رساند امکانات گزارشگيري صدور، اقساط معوق و مبالغ پرداختي، صدور الحاقيه هاي ابطال، بازخريد، تغيير
    کد نمايندگي موقت به دائم و توضيح در سيستم عملياتي شده است. همچنين جهت مشاهده کارمزد "
    <a href='/extra/karmozd.pdf' style="text-decoration:none;color: green;" type="button" class="tabsbtn"><b>
        راهنماي دريافت ليست كارمزد بيمه نامه هاي عمر انفرادي صادره سال 1392
    </b></a>
    " را مطالعه فرماييد.
</p>
<br/>


<p style="text-align: center; width: 70%; color: #000000; margin: 0 auto;">
بازگشت به بخشنامه مورخ 92/02/30 اسکن نمودن کارت ملی بیمه گذار از تاریخ 92/03/15 الزامی می باشد. بدیهی است به کلیه پیشنهاداتی که فاقد اسکن کارت ملی می باشند ترتیب اثر داده نخواهد شد.
</p>
<br/>

<%--<p style="text-align: center; width: 70%; color: black; margin: 0 auto;">--%>
    <%--جهت ثبت پيشنهادات خانواده مي بايست حتما--%>
    <%--<b><a href="/extra/excel_khanevade.xls">فايل اكسل خانواده</a></b>--%>
    <%--را دانلود، تكميل و در تب ضمائم پيشنهاد آپلود نماييد--%>
<%--</p>--%>
<%--<br/><br/>--%>



<%--<p style="text-align: center; width: 70%; color: black; margin: 0 auto;">--%>
    <%--به منظور جلوگیری از بروز خطا در ثبت پیشنهادات عمروسرمایه‌گذاری در سیستم جدید حتماً از آخرین نسخه مرورگر موزیلا فایرفاکس استفاده نمایید--%>
<%--</p>--%>
<%----%>
<%--<br/><br/>--%>


<p style="text-align: center; width: 70%; margin: 0 auto;">
    پيشنهاد دهنده گرامي براي استفاده از سيستم فوق دانلود و نصب نرم‌افزار فايرفاكس (Firefox) ضروري است، ضمنا جهت رفاه حال
    كاربران سيستم، سند راهنماي كاربري سيستم استعلام نرخ بيمه عمر و سرمايه گذاري و لينك مستقيم دانلود فايرفاكس، در زير
    آمده اند :
</p><br/>
<a href='/extra/Firefox.exe' style="text-decoration:none" type="button" id="" class="tabsbtn" name="">دانلود نرم افزار
    فايرفاكس</a>
<br/>
<a href='/extra/rahnama.pdf' style="text-decoration:none" type="button" id="" class="tabsbtn" name="">راهنماي كاربري
    سيستم
    استعلام نرخ بيمه عمر و سرمايه گذاري</a>

<br/>
<a href='/extra/faq.pdf' style="text-decoration:none;color: blue;" type="button" id="" class="tabsbtn" name="">سوالات
    متداول</a>
<br/>
<br/>
<br/>
<br/>
</body>
</html>