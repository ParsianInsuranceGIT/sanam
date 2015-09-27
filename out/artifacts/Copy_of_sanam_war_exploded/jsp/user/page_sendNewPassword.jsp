<%@ page import="com.bitarts.common.util.StringUtil" %>
<%@ page import="com.bitarts.common.util.DateUtil" %>
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
    <s:if test="%{!userKrtbl}">
        <div class="main-login-div">
        <form action="/pageLogin" method="post" name="target" id="target">
        <div id="passwordRecComp" >
        <p id="tr1">
            کد رمز به موبایل شما ارسال خواهد شد. بعد از دریافت، اطلاعات زیر را پر کنید.
        </p>
        <P id="tr2">
            این کد تا پایان امروز به تاریخ <%=DateUtil.getCurrentDate()%> معتبر می باشد.
        </P>
        <p id="tr3">
            نام کاربری و کد رمز ارسال شده را برای بازیابی کلمه عبور وارد نمایید.
        </p>

            <table class="nonGrid" align="center" dir="rtl" >
                <tr>
                    <td>
              نام کاربری
                    </td>


                    <td>
                        <input type="text" name="usernameForget" id="usernameForget"/>
                    </td>
                </tr>
                <tr>
                    <td>
کد رمز
                    </td>
                    <td>
                        <input type="text" name="codeRamzForget" id="codeRamzForget"/>
                    </td>
                </tr>

               <tr>
                    <td colspan="2">
                        <input type="button" id="forgetPassword" onclick="showPassword()" value="بازیابی کلمه عبور"/>
                    </td>
                </tr>
            </table>
        </div>
            <table class="nonGrid" align="center" dir="rtl" id="passwordRecComp2">
                <tr>
                    <td colspan="2">
                        <p id="matnMovafagh">
                            رمز عبور شما با موفقیت بازیابی شد. رمز شما " + msg + " می باشد.
                        </p>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" id="returnLogin"  value="بازگشت"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    </s:if>
    <s:if test="%{userKrtbl}">
</br></br></br></br></br></br></br></br>کلمه عبور جدید به موبایل شما ارسال شد.<br/><br/><br/><br/><br/><br/><br/><br/>
    </s:if>

</div>

<script type="text/javascript">
<s:if test="%{!userKrtbl}">
    $('#matnMovafagh').hide();
    $('#returnLogin').hide();
    function showPassword()
    {
        $.ajax({
            type: "POST",
            async : false,
            data: $("#target").serialize(),
            url: "sendNewPassword",
            success: function (msg) {
                if (msg == 'false1'){
                    alertMessage("اطلاعات ورودی صحیح نمی باشد.");
                } else if (msg == 'false2'){
                    alertMessage("اعتبار تاریخ کد رمز گذشته است.");
                }
                else{
                    $('#matnMovafagh').text("رمز عبور شما با موفقیت بازیابی شد. رمز شما " + msg + " می باشد.");
                    $('#matnMovafagh').show();
                    $('#returnLogin').show();
                    $('#passwordRecComp').hide();
                }

            }
        });
    }
    function getUrlVars()
    {
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for(var i = 0; i < hashes.length; i++)
        {
            hash = hashes[i].split('=');
            vars.push(hash[1]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    }

    $( document ).ready(function() {
        if (getUrlVars() == "22"){
            $('#tr1').hide();
            $('#tr2').hide();
            $('#tr3').show();
        } else {
            $('#tr1').show();
            $('#tr2').show();
            $('#tr3').hide();
        }
    });
</s:if>
</script>

</body>
</html>