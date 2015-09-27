<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>تسهیم در سود مشارکت</title>
</head>
<form class="mainFrame" id="mainFormForMosharekat" method="post" action="/tashimSoudMosharekatAllinOne_calc.action">
    <table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <thead>
        <tr>
            <th colspan="4">
                فرم تسهیم در سود مشارکت
            </th>
        </tr>
        </thead>
        <tbody>
        <%--<tr>--%>
            <%--<td>--%>
                <%--واحد صدور:--%>
            <%--</td>--%>
            <%--<td colspan="3" align="right">--%>
                <%--<select name="mosharekatDarManafe.vahedSodour">--%>
                    <%--<option value="111115">111115</option>--%>
                <%--</select>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <td>
                شماره مشارکت:
            </td>
            <td colspan="3">
                <input type="text" name="mosharekatDarManafe.shomareMosharekat" readonly="readonly"
                       value="${mosharekatDarManafe.shomareMosharekat}"/>
            </td>
        </tr>
        <tr>
            <td>
                تاریخ شروع دوره:
            </td>
            <td>
                <input type="text" name="mosharekatDarManafe.tarikhShoruDowre"
                       value="${mosharekatDarManafe.tarikhShoruDowre}" id="shoroudowreid"
                       class="datePkr validate[required,custom[date]]"/>
            </td>
            <td>
                تاریخ پایان دوره:
            </td>
            <td>
                <input type="text" name="mosharekatDarManafe.tarikhPayanDowre"
                       value="${mosharekatDarManafe.tarikhPayanDowre}" id="payandowreid"
                       class="datePkr validate[required,custom[date]]"
                       onchange="$('#mabnamohasebetarikh').val($('#payandowreid').val());"/>
            </td>
        </tr>
        <tr>
            <td>
                عنوان دوره:
            </td>
            <td colspan="3">
                <input type="text" name="mosharekatDarManafe.onvanDowre" id="onvane_dowre"
                       value="${mosharekatDarManafe.onvanDowre}" class="validate[required,custom[onlyPersianLetter]]"/>
            </td>
        </tr>
        <tr>
            <td>
                میزان سود (i):
            </td>
            <td colspan="3">
                <input type="text" name="mosharekatDarManafe.soud" id="soud"
                       value="${mosharekatDarManafe.soud}" class="validate[required]"/>
            </td>
        </tr>
        <tr>
            <td>
                توضیحات:
            </td>
            <td colspan="3">
                <textarea rows="5" cols="75"
                          name="mosharekatDarManafe.tozihat">${mosharekatDarManafe.tozihat}</textarea>
            </td>
        </tr>
        <tr>
            <td>
                مبلغ کل سود قابل تقسیم:
            </td>
            <td colspan="3">
                <input type="text" name="mosharekatDarManafe.mablaghSudTashim"
                       value="${mosharekatDarManafe.mablaghSudTashim}" id="mablagh_sud_tashim"
                       class="text-input validate[required,custom[int]]"/>
            </td>
        </tr>
        <tr>
            <td>
                تاریخ مبنای محاسبه ذخیره:
            </td>
            <td>
                <input type="text" name="mosharekatDarManafe.tarikhMabnaMohasebe"
                       value="${mosharekatDarManafe.tarikhMabnaMohasebe}" id="mabnamohasebetarikh"
                       class="validate[required,custom[date]]"
                        readonly="true"/>
            </td>
            <td>
                تاریخ محاسبه سود مشارکت:
            </td>
            <td>
                <input type="text" name="mosharekatDarManafe.tarikhMohasebeSud"
                       value="${mosharekatDarManafe.tarikhMohasebeSud}" id="mohasebesoudtarikh"
                       value="<%=DateUtil.getCurrentDate()%>" class="datePkr validate[required,custom[date]]"/>
            </td>
        </tr>
        <%--<s:if test="!mosharekatDarManafe.id.equals('')">
            <input type="hidden" name="mosharekatDarManafe.id" value="${mosharekatDarManafe.id}"/>
        </s:if>--%>
        <tr>
            <td colspan="4">
                <input type="submit" value="محاسبه"/>
            </td>
        </tr>
        </tbody>
    </table>
</form>