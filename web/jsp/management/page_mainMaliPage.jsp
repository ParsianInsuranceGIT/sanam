<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<html>
<head>
    <title>داشبورد مدیریت فرآیند مالی</title>
</head>
<body>

<table class="nonGrid" align="center" dir="rtl">
    <tr>
        <td></td>
        <td><input type="button" style="width:155px" onclick="window.location='/fin/loadSanadZani'" value="ثبت سند دستی"></td>
    </tr>
    <tr>
        <td></td>
        <td><input type="button" style="width:155px" onclick="window.location='/uploadFileBank.action'" value="آپلود فایل بانک"></td>
    </tr>
    <tr>
        <td></td>
        <td><input type="button" style="width:155px" onclick="window.location='/fin/viewDasteCheckHa'" value="دسته چک ها"></td>
    </tr>
    <tr>
        <td></td>
        <td><input type="button" style="width:155px" onclick="window.location='/fin/viewKhateSanadHa'" value="مشاهده اسناد"></td>
    </tr>
    <tr>
        <td></td>
        <td><input type="button" style="width:155px" onclick="window.location='/fin/listEtebarat?credebitTypeFarsi=GHEST'" value="لیست اعتبارات و بدهی ها"></td>
    </tr>
    <tr>
        <td></td>
        <td><input type="button" style="width:155px" onclick="window.location='/fin/listAllEtebaratCheck'" value="مشاهده چک های دریافتی"></td>
    </tr>
    <%--<tr>--%>
        <%--<td></td>--%>
        <%--<td><input type="button" style="width:155px" onclick="window.location='/fin/takhsisCodeMoshtari'" value="تخصیص شناسه پرداخت"></td>--%>
    <%--</tr>--%>
</table>
</body>
</html>