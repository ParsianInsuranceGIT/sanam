<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<head>
     <title>افزودن بیمه شده</title>
</head>
<form class="mainFrame" id="mainForm" method="post" action="/addEmzaKonande">
<table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <thead>
        <tr>
            <th colspan="2">
ثبت بیمه شده:
            </th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
 نام
            </td>
            <td>
                <input type="text" name="shakhs.name"/>
            </td>
        </tr>
        <tr>
            <td>
نام خانوادگی
            </td>
            <td>
                <input type="text" name="shakhs.nameKhanevadegi"/>
            </td>
        </tr>
        <tr>
            <td>
                نام پدر
            </td>
            <td>
                <input type="text" name="shakhs.namePedar"/>
            </td>
        </tr>
        <tr>
            <td>
پیشوند
            </td>
            <td>
                <input type="text" name="shakhs.pishvand"/>
            </td>
        </tr>
        <tr>
            <td>
کد ملی شناسایی
            </td>
            <td>
                <input type="text" name="shakhs.kodeMelliShenasayi"/>
            </td>
        </tr>
        <tr>
            <td>
شماره شناسنامه
            </td>
            <td>
                <input type="text" name="shakhs.shomareShenasnameh"/>
            </td>
        </tr>
        <tr>
            <td>
محل صدور شناسنامه
            </td>
            <td>
                <input type="text" name="shakhs.mahalleSodoreShenasnameh"/>
            </td>
        </tr>
        <tr>
            <td>
محل تولد
            </td>
            <td>
                <input type="text" name="shakhs.mahalleTavallod"/>
            </td>
        </tr>
        <tr>
            <td>
تاریخ تولد
            </td>
            <td>
                <input type="text" name="shakhs.tarikheTavallod"/>
            </td>
        </tr>
        <tr>
            <td>
وضعیت تاهل
            </td>
            <td>
                <select name="shakhs.vaziateTaahol">
                    <option value="mojarrad">مجرد</option>
                    <option value="motaahel">متاهل</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
جنسیت
            </td>
            <td>
                <select name="shakhs.vaziateTaahol">
                    <option value="mard">مرد</option>
                    <option value="zan">زن</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
شغل اصلی
            </td>
            <td>
                <input type="text" name="shakhs.shoghleAsli"/>
            </td>
        </tr>
        <tr>
            <td>
شغل فرعی
            </td>
            <td>
                <input type="text" name="shakhs.shoghleFarei"/>
            </td>
        </tr>
        <tr>
            <td>
دولتی یا خصوصی
            </td>
            <td>
                <input type="text" name="shakhs.shoghleFarei"/>
            </td>
        </tr>


        <tr>
            <td colspan="2">
                <c:if test="${not empty users}">
                    <input type="submit" value="ثبت" />
                </c:if>
                <input type="button" onclick="window.location='jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
            </td>
        </tr>
    </tbody>
</table>
</form>