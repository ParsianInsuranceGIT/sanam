<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<body>
<center>
    <form action="/sabtDetailHazinePezashki" method="post">
        <input type="hidden" name="moarefiname.id" value="<s:property value="moarefiname.id"/>">
        <table>
            <tr>
                <td>کد رهگیری پیشنهاد</td>
                <td><s:property value="moarefiname.pishnehad.radif"/></td>
            </tr>
            <tr>
                <td>کد معرفی نامه</td>
                <td><s:property value="moarefiname.code"/></td>
            </tr>
            <tr>
                <td>نام بیمه شده</td>
                <td><s:property value="nameBimeshode"/></td>
            </tr>
            <tr>
                <td>نام خانوادگی بیمه شده</td>
                <td><s:property value="nameKhanevadegiBimeShode"/></td>
            </tr>
            <tr>
                <td>کد ملی بیمه شده</td>
                <td><s:property value="kodeMelli"/></td>
            </tr>
            <tr>
                <td>مبلغ کل اعلام شده توسط کلینیک</td>
                <td><input type="text" value="<s:property value="mablaghClinic"/>" name="mablaghClinic"></td>
            </tr>

            <tr>
                <td>مبلغ کل تایید شده توسط واحد خسارت</td>
                <td><input type="text" value="<s:property value="mablaghKhesarat"/>" name="mablaghKhesarat"></td>
            </tr>
            <tr>
                <td>
                    توضیحات
                </td>
                <td><textarea rows="10" cols="20" name="tozihat"><s:property value="tozihat"/></textarea></td>
            </tr>
            <tr align="left">
                <td colspan="2"><input type="submit" value="ثبت" style="width: 100px"></td>
            </tr>
        </table>
        <input type="button" value="بازگشت" onclick="window.location='/sabtHazinePezashki'">
    </form>
</center>
</body>
</html>