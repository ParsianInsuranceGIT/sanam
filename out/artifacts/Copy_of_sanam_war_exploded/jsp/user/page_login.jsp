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
<body >
<script >
    function setDaftarSession(){

        var selectedDaftar = document.getElementById("daftarType").value;
        var username=document.getElementById("j_username").value;
//        document.cookie="daftar="+selectedDaftar;
        var result = "";
        $.ajax({
            type: "GET",
            data:"daftarType="+selectedDaftar+"&j_username="+username,
            async: false,
            url: "setSessionForDaftarType",
            success: function (msg) {
                result=msg;
//                    alert(msg);
//                  if(response=='true'){
//                      alert("this is true!");
//                      return true;
//                  }
//
//                   else
//                    return false;
//                document.getElementById("shomareMoshtariAutomobil").value = response.trim();
//                result =  response.trim();
//                window.open(window.location.pathname,"_self");
            }
        });
        alert(result);
        if(result=='true')
           return true;
        else {
//            alert("اين نمايندگي داراي دفتر نمايندگي نمي باشد")  ;
            return false;
        }

//        return result;
//        e.preventDefault();
//        return false;
    }


</script>
<%--<s:actionmessage/>--%>
<p style="color:red;font-weight: bold;">
    <c:if test="${nosession=='true'}">
        براي دسترسي به اين صفحه لازم است لاگين نماييد!
    </c:if>
    <%--<c:if test="${sessionScope.errorDaftarNamayande=='true'}">--%>
      <%--اين نمايندگي داراي دفتر نمايندگي در سنام نمي باشد--%>
    <%--</c:if>--%>
</p>


<div id="main">
    <div class="main-login-div">
        <form action="j_spring_security_check" method="post" name="target" id="target" >

            <p style="text-align: center; width: 70%;font-size:15pt;font-style:normal;color: #1d5987; margin: 0 auto;">
            سامانه نوين امور مالي (سنام)
            </p><br></br>
<%--            <input type="button" style="width: 220px; margin: 4px;"
                   onclick="window.location='/jsp/estelamMultiple/page_andukhteyeSarmayegozariMultiple.jsp'"
                   value="استعلام بيمه نامه جامع خانواده">
            <input type="button" style="width: 220px;"
                   onclick="window.location='/prepareForEstelam.action'"
                   value="استعلام بيمه نامه عمر و سرمايه گذاري">--%>
            <table class="nonGrid" align="center" dir="rtl">
                <%if (request.getParameter("error") != null && request.getParameter("error").equals("failed")) {%>
                <tr>
                    <td colspan="2" style="color:red">
                        نام کاربري يا کلمه عبور وارد شده صحيح نيست.
                    </td>
                </tr>
                <%}%>
                <%--<tr>--%>
                <%--<td></td>--%>
                <%--<td><input type="button" style="width:155px" onclick="window.location='/viewCredebits.action'" value=" تست ذخيره"></td>--%>
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
                <%--value="استعلام بر مبناي اندوخته"></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                <%--<td></td>--%>
                <%--<td><input type="button" style="width:155px" onclick="window.location='/jsp/mostamari/page_mostamari.jsp'" value="مستمري"></td>--%>
                <%--</tr>--%>
                <tr>
                    <td>
                        نام کاربري:&nbsp;&nbsp;&nbsp;
                    </td>


                    <td>
                        <input type="text" name="j_username" id="j_username"
                               <c:if test="${not empty param.login_error}">value='<%= session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY) %>'
                        </c:if> />
                        <%--value='<%= session.getAttribute("username") %>'--%>
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
                <%--<tr>--%>
                   <%--<td>--%>
                       <%--دفتر حسابداري--%>
                   <%--</td>--%>
                    <%--<td>--%>
                        <%--<select name="daftarType" id="daftarType" class="validate[required]"  >--%>
                            <%--<option  value="" >انتخاب كنيد</option>--%>
                            <%--<option  value="PARSIAN" >پارسيان</option>--%>
                            <%--<option value="NAMAYANDE" >نماينده</option>--%>

                        <%--</select>--%>
                    <%--</td>--%>
                <%--</tr>--%>
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
                                            .attr('src', '/kaptcha.jpgي' + Math.floor(Math.random() * 100))
                                            .fadeIn();
                                });


                        </script>
                        <br/>
                        <div id="kaptchaInformaion">
                            <small>اگر قادر به خواندن حروف تصوير نيستيد روي آن کليک کنيد.</small>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>حروف تصوير</td>
                    <td><input type="text" name="kaptcha" id="kaptcha" value="" onkeypress="searchKeyPress(event);"/></td>
                </tr>
                <%
                    }
                %>
                <%--<tr>--%>
                    <%--<td colspan="2">--%>
                        <%--<label style="margin-right: 115px;">من را بخاطر بسپار</label>--%>
                        <%--<input type='checkbox' name='_spring_security_remember_me'/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                    <td colspan="2">
                        <% if (session.getAttribute("numberOfIncorrectPassword") != null && ((Integer) session.getAttribute("numberOfIncorrectPassword") > 3)){ %>
                            <input type="button" id="loginBtn" onclick="loginFunc(document.forms[0].kaptcha.value)" value="ورود"/>
                        <% } else { %>
                            <input type="submit"  value="ورود"/>
                        <% } %>
                        &nbsp;&nbsp;
                       <input type="button" id="forgetPassword" onclick="window.open('/jsp/user/page_forgetPassword.jsp','_self')" value="بازیابی کلمه عبور"/>
                        <%--<input type="button" id="forgetPassword" onclick="window.open('/jsp/user/page_forgetPassword.jsp','_self')" value="بازيابي کلمه عبور"/>--%>
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
                $('#kaptchaInformaion').html("<small style='color: red;'>حروف تصوير به درستي وارد نشده است</small>");
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
    <%--function setSessionForDaftar(){--%>
        <%--var type = document.getElementById("daftarType").value;--%>
    <%--switch(type){--%>
    <%--case ("daftar_parsian"):--%>
    <%--alert("parsian");--%>
    <%--&lt;%&ndash;<% session.setAttribute("daftar","PARSIAN");&ndash;%&gt;--%>
    <%--&lt;%&ndash;System.out.println("hello parsian");%>&ndash;%&gt;--%>
    <%--break;--%>

    <%--case("daftar_namayande"):--%>
    <%--alert("namayande");--%>
    <%--&lt;%&ndash;<% session.setAttribute("daftar","NAMAYANDE");&ndash;%&gt;--%>
    <%--&lt;%&ndash;System.out.println("hello namayande");%>&ndash;%&gt;--%>
    <%--break;--%>
    <%--}--%>
    <%--}--%>
</script>
<%--
<p style="text-align: center; width: 70%; color: red; margin: 0 auto;">
"مشکل موجود در تب "همه پيشنهادها" در دست بررسي و مرتفع سازي مي‌باشد."
</p>

<br/><br/>
--%>

<p style="text-align: center; width: 70%;font-size:14pt;font-style: italic;color: blue; margin: 0 auto;">
    :اطلاعيه ها:
</p>
<br/><br/>






<%--<p style="text-align: center; width: 90%; color: blue; margin: 0 auto;">--%>
<%--<br/>--%>

<%--<p style="text-align: center; width: 70%; margin: 0 auto; color: blue;">--%>
	<%--***براي بيمه نامه هاي صادره 1392،--%>
	<%--<b><a href="/signUp">اينجا </a></b>--%>
	<%--را کليک نماييد***--%>
<%--</p>--%>
<%--<br/>--%>

<%--<p style="text-align: center; width: 70%; margin: 0 auto; color: blue;">--%>
    <%--***براي بيمه نامه هاي صادره قبل از 1392،--%>
    <%--<b><a href="http://lifemvc.parsianinsurance.ir/lifeinsured">اينجا </a></b>--%>
	<%--را کليک نماييد***--%>
<%--</p>--%>
<%--<br/>--%>

<p style="font-family:b nazanin;font-size:15px;text-align: justify; width: 70%; color: #ee0101; margin: 0 auto;">
    <b style="font-size:120%;">
    قابل توجه نمايندگان محترم
    </b>
	<br>
	<%--<b style="font-size:120%;color: red">--%>
	 <%---   پیرو برخی اصلاحات سیستمی، امروز مورخ 1393/06/29 از ساعت 12 به مدت یک ساعت سیستم قطع می باشد. --%>
	<%--</b>--%>
	<%--<br>--%>
	<b style="font-size:120%;color: red">
        - با توجه به اینکه حذف سند های دائم در سیستم سنام امکان پذیر نمی باشد، لازم است تا در دریافت و واریز اعتبار های شناسه پرداخت دقت لازم را بعمل آورید
        و کل مبلغ اعتبار به حساب بیمه پارسیان واریز گردد.
        <br>
        - در صورتیکه مبلغی کمتر به حساب بیمه پارسیان واریز گردید، لازم است تا مابه التفاوت آن نیز واریز گردد و طی ارسال درخواست از طریق سیستم FNC جهت تایید وصول پیگیری لازم را بعمل آورید.



<%--
- به استحضار میرساند با عنایت به رفع  مشکلات مربوط به دستگاههای pos
و فعال شدن مجدد اعتبار تجارت الکترونیک پارسیان در سیستم، امکان استفاده از اعتبار مربوطه مانند سابق
برقرار میباشد.
	<br>
	لازم بذکر است جهت واریز اعتبارات تجارت الکترونیک فقط از دستگاه pos استفاده گردد.
 --%>
    </b>

	<br><br>

	
<%--	<br>
    <b style="font-size:100%;color: black">
- به استحضار كاربران محترم  مي رساند امكان پرداخت اينترنتي بدهي هاي سند نخورده  از مسير "عمليات مالي-مشاهدات-پرداخت اينترنتي بدهي" در سيستم سنام فراهم شده است
    </b>
    <br>
    <br>
--%>	
<%--    <b style="font-size:100%;color: black">
        - از تاريخ 93/02/22 ، مسدود شدن سيستمهاي صدور سايبون بدليل عدم تسويه حق بيمه طبق تاريخ صدور بيمه نامه انجام خواهد گرفت. لطفا در تسويه حق بيمه ها براي رفع اين مسأله تسريع نماييد. "مديريت صدور خودرو"
    </b>
    <br><br>--%>

</p>
<p style="font-family:b nazanin;font-size:15px;text-align: justify; width: 70%; color: #111111; margin: 0 auto;">
    <b style="font-size:100%; color: #000000">
        - نظربه بخشنامه شماره 200/1143-93/3 و با توجه به کنترل هاي سيستم مالي حق بيمه هاي دريافتي بايد در همان روز به حساب شرکت بيمه پارسيان واريز شود. در غيراين صورت سيستم صدورشما تا زمان تاييد وصول مالي قطع مي گردد.
        (شرح بخشنامه را مي توانيد از تابلو اعلانات در سيستم سايبون ملاحظه فرماييد)
    </b>
    <br>
    <b style="font-size:100% ; color: #000000">
          - جهت تسريع در پاسخگويي به مشکلات و سوالات خود در ارتباط با سيستم خواهشمند است از سامانه تيکت (به آدرس
              <b ><a style="font:time new roman;font-size:115% ;font-style:italic ; color: #ee0101" href="http://ticket.parsianinsurance.com">http://ticket.parsianinsurance.com</a></b>
        )        استفاده نماييد.
        و از تماس تلفني با مرکز جدا خودداري نماييد. درصورت عدم رفع مشکل با توجه به راهنمايي هاي دريافتي در سامانه تيکت با شماره مستقیم
        <b style="font-size:180% ; color: #ee0101">5-42169100</b>
        تماس حاصل نماييد.
    </b>
    <br>
    <b style="font-size:100%">
            - نمايندگاني که جهت واريز حق بيمه ها در روز جاري اقدام نموده اند و سيستم آنها بسته مي باشد از تماس با واحد هاي ستادي خودداري نمايند. همانگونه که مستحضريد اعلام وصولي حق بيمه از بانک يک الي دو روز طول مي کشد.
    </b>
<%--    <br>
    <b style="font-size:100%">
            - نمايندگاني که جهت واريز حق بيمه ها در روز هاي قبل از امروز اقدام نموده اند و در سيستم (fnc يا سنام) وصولي را نمي بينند، با واحد مالي تماس بگيرند.
    </b>
    <br>
    <b style="font-size:100%">
            - نمايندگاني که عليرغم مشاهده اعتبارات کاملا وصول شده سيستم آنها کماکان بسته است، با واحد فناوري اطلاعات تماس بگيرند.
    </b>
    <br>
    <b style="font-size:100%">
            - لطفا و حتما راهنماي سيستم سنام را مطالعه فرماييد (در صفحه اصلي – مستند مالي)
    </b>
    <br>
    <b style="font-size:100%">
            - با احترام، بدينوسيله مجددا يادآوري و تاکيد مي گردد که جهت بدهي هاي سررسيد شده، حداکثر تا پايان همان روز سررسيد، مي بايست سند صادر و بدهي مربوطه به حساب شرکت واريز گردد.
    </b>
    <br>
    <b style="font-size:100%">
            - ليست بدهي ها و ليست اعتبارات وصول نشده در صفحه اول ورود به سيستم نمايش داده مي شود و همواره بايد ليست هاي مذکور خالي باشد تا از بستن سيستم صدور جلوگيري شود.
    </b>
    <br>--%>
</p>

<br >
<p style="font-family:b nazanin;font-size:15px;text-align: center; width: 70%; margin: 0 auto;">
     براي استفاده از سيستم فوق دانلود و نصب نرم‌افزار فايرفاكس (Firefox) ضروري است، ضمنا  لينك مستقيم دانلود فايرفاكس، در زير
    آمده اند :
</p><br/>

<a href='/extra/Firefox.exe' style="text-decoration:none" type="button" id="" class="tabsbtn" name="">دانلود نرم افزار
    فايرفاكس</a>
<br/>

<br/>
<br/>
<br/>
</body>
</html>