<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <style>
    body {
            background-image:url("logo.jpg");
            background-repeat:no-repeat;
            /*background-attachment:fixed;*/
            background-position:center;

         }
    </style>
    <title>ورود</title>
</head>
<body>
<s:actionmessage/>
<s:actionerror/>


<div id="main">

    <div>
        <%--<p><b style="color: #ff0000">بازیابی رمز عبور شامل دو مرحله می باشد که انجام هر دو مرحله به ترتیب زیر ضروری می باشد:</b></p>--%>
        <%--</br>--%>
        <%--<hr>--%>

                    </br></br></br></br></br></br></br></br></br></br></br>
        <form action="forgetPassword" method="post" name="target" id="target">
 <p><b style="color: #ff0000;text-align: right">مرحله اول:</b></p>

                    <p>
                        در صورت فراموش کردن کلمه عبور خود، نام کاربری و تلفن همراه ثبت شده در سیستم خود را وارد کنید.
                    </p>

            <table class="nonGrid" align="center" dir="rtl">

                <tr>
                    <td style="text-align: left">
              نام کاربری
                    </td>
                    <td style="right: 10">
                        <input type="text" name="usernameForget" id="usernameForget"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: left">
تلفن همراه
                    </td>
                    <td>
                        <input type="text" name="mobileForget" id="mobileForget"/>
                    </td>
                </tr>

               <tr>
                    <td colspan="2">
                        <input type="submit" id="forgetPassword" value="فرستادن کلمه عبور جدید"/>
                    </td>
                </tr>
                <tr>
                    <td style="padding:20px;" colspan="2">
                        </br></br></br></br></br></br></br></br></br></br></br>
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<td>--%>
                        <%--<p><b style="color: #ff0000">مرحله دوم:</b></p>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td  colspan="2" style="text-align: center">--%>
                        <%--در صورتی که کد رمز به موبایل شما ارسال شده است، می توانید دکمه زیر را کلیک کنید.--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td colspan="2">--%>
                        <%--<input type="button" id="recPassword" onclick="window.open('page_sendNewPassword.jsp?mobileForget=22','_self')" value="فرستادن کلمه عبور با کد رمز"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            </table>
        </form>
    </div>
</div>

</body>
</html>