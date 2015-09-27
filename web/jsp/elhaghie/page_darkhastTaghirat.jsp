<%@ page import="com.bitarts.common.util.DateUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron0
  Date: Jul 20, 2011
  Time: 3:16:40 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form action="/printDarkhasteElhaghie" method="post">
<input type="hidden" value="${pishnehad.id}" name="pishnehadReport.pishnehad.id"/>
<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        درخواست الحاقیه
    </p>
</div>

<table class="mystrippedtable" dir="rtl" cellpadding="2px" cellspacing="0px"
       style="border-spacing:1px;margin-left:auto;margin-right:auto;width:700px" border="0">
    <tr class="odd">
        <td class="even">شماره بیمه نامه</td>
        <td>${pishnehad.bimename.shomare}</td>
        <td class="even">نوع بیمه نامه</td>
        <td>عمر و سرمایه گذاری</td>
        <td class="even">تاريخ صدور بيمه‌نامه</td>
        <td>${pishnehad.bimename.tarikhSodour}</td>
        <td class="even">تاريخ ثبت درخواست الحاقيه</td>
        <td>${darkhastTaghirat.tarikhDarkhast}</td>
    </tr>
</table>
<%----%>
<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        بیمه گذار
    </p>
</div>
<table class="mystrippedtable" dir="rtl" cellpadding="2px" cellspacing="0px"
       style="border-spacing:1px;margin-left:auto;margin-right:auto;width:500px" border="0">
    <%--<col width="25%"><col width="25%"><col width="25%"><col width="25%">--%>
    <tr class="odd">
        <td class="even">نام</td>
        <td>${pishnehad.bimeGozar.shakhs.name}</td>
        <td class="even">نام خانوادگی</td>
        <td>${pishnehad.bimeGozar.shakhs.nameKhanevadegi}</td>
        <td class="even">نام پدر</td>
        <td>${pishnehad.bimeGozar.shakhs.namePedar}</td>
    </tr>
    <tr class="odd">
        <td class="even">کد ملی</td>
        <td>${pishnehad.bimeGozar.shakhs.kodeMelliShenasayi}</td>
        <td class="even">شماره شناسنامه</td>
        <td>${pishnehad.bimeGozar.shakhs.shomareShenasnameh}</td>
        <td class="even">تلفن</td>
        <td>${pishnehad.addressBimeGozar.codeTelephoneManzel} ${pishnehad.addressBimeGozar.telephoneManzel}</td>
    </tr>
</table>
<%----%>
<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        بیمه شده
    </p>
</div>
<table class="mystrippedtable" dir="rtl" cellpadding="2px" cellspacing="0px"
       style="border-spacing:1px;margin-left:auto;margin-right:auto;width:500px" border="0">
    <%--<col width="25%"><col width="25%"><col width="25%"><col width="25%">--%>
    <tr class="odd">
        <td class="even">نام</td>
        <td>${pishnehad.bimeShode.shakhs.name}</td>
        <td class="even">نام خانوادگی</td>
        <td>${pishnehad.bimeShode.shakhs.nameKhanevadegi}</td>
        <td class="even">نام پدر</td>
        <td>${pishnehad.bimeShode.shakhs.namePedar}</td>
    </tr>
    <tr class="odd">
        <td class="even">کد ملی</td>
        <td>${pishnehad.bimeShode.shakhs.kodeMelliShenasayi}</td>
        <td class="even">شماره شناسنامه</td>
        <td>${pishnehad.bimeShode.shakhs.shomareShenasnameh}</td>
        <td class="even">تلفن</td>
        <td>${pishnehad.addressBimeShode.codeTelephoneManzel}&nbsp;${pishnehad.addressBimeShode.telephoneManzel}</td>
    </tr>
</table>
<%----%>
<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        جدول تغييرات درخواست شده در بيمه‌نامه
    </p>
</div>
<table class="jtable resultDets" align="center" width="700px" cellpadding="0" cellspacing="0"
       style="border-spacing:1px;margin:0 auto;" border="0">
    <tr>
        <th>تغيير مورد درخواست</th>
        <th>شرايط فعلي بيمه‌نامه</th>
        <th>شرايط جديد بيمه‌نامه</th>
    </tr>
    <c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">
        <c:if test="${row.changeType == 'FIELD'}">
            <tr>
            <td>${row.subject}<input type="hidden" name="pishnehadReport.pishnehadFieldChangesList[${i.index}].subject"
                                     value="${row.subject}"></td>
            <td>${row.fromValue == 'bali'?'بلی':row.fromValue == 'kheir'?'خیر':row.fromValue}<input type="hidden"
                                                                                                    name="pishnehadReport.pishnehadFieldChangesList[${i.index}].fromValue"
                                                                                                    value="${row.fromValue}">
            </td>
            <td>${row.toValue == 'bali'?'بلی':row.toValue == 'kheir'?'خیر':row.toValue}<input type="hidden"
                                                                                              name="pishnehadReport.pishnehadFieldChangesList[${i.index}].toValue"
                                                                                              value="${row.toValue}">
            </td>

        </c:if>
        </tr>
    </c:forEach>
    <s:if test="option_changes">
        <tr>
            <td style="width:210px">
                شروع از ابتدای سال بیمه ای${darkhastTaghirat.whenApply + 1}
            </td>
        </tr>
    </s:if>
</table>
<br class="clear"/>

<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        جدول اصلاح شده ذينفعان فوت بيمه‌نامه
    </p>
</div>
<table class="jtable resultDets" align="center" width="700px" cellpadding="0" cellspacing="0"
       style="border-spacing:1px;margin:0 auto;" border="0">
    <tr>
        <th>ردیف</th>
        <th>نام</th>
        <th>نام خانوادگی</th>
        <th>نام پدر</th>
        <th>كدملي</th>
        <th>شماره شناسنامه</th>
        <th>تاريخ تولد</th>
        <th>محل تولد</th>
        <th>نسبت با بيمه‌شده</th>
        <th>درصد سهم</th>
        <th>الويت</th>
    </tr>
    <c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">
        <c:if test="${row.changeType == 'LIST'}">
            <c:forEach var="estefadeKonande" items="${row.toValues}" varStatus="j">
                <c:if test="${function:hasProperty(estefadeKonande, 'vaziateFotVaHayat')}">
                <c:if test="${estefadeKonande.vaziateFotVaHayat == 'fout'}">
                    <tr>
                        <td>${j.index}</td>
                        <td>${estefadeKonande.name}</td>
                        <td>${estefadeKonande.nameKhanevadegi}</td>
                        <td>${estefadeKonande.nameKhanevadegi}</td>
                        <td>${estefadeKonande.kodeMelli}${estefadeKonande.kodeEghtesadi}</td>
                        <td>${estefadeKonande.shomareShenasname}</td>
                        <td>${estefadeKonande.tarikhTavallod}</td>
                        <td>${estefadeKonande.mahalleTavallod}</td>
                        <td>${estefadeKonande.nesbatBabimeShode}</td>
                        <td>${estefadeKonande.darsadeSahm}</td>
                        <td>${estefadeKonande.olaviat}</td>
                    </tr>
                </c:if>
                </c:if>
            </c:forEach>
        </c:if>
    </c:forEach>
</table>
<br class="clear"/>

<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        جدول اصلاح شده ذينفعان حيات بيمه‌نامه
    </p>
</div>
<table class="jtable resultDets" align="center" width="700px" cellpadding="0" cellspacing="0"
       style="border-spacing:1px;margin:0 auto;" border="0">
    <tr>
        <th>ردیف</th>
        <th>نام</th>
        <th>نام خانوادگی</th>
        <th>نام پدر</th>
        <th>كدملي</th>
        <th>شماره شناسنامه</th>
        <th>تاريخ تولد</th>
        <th>محل تولد</th>
        <th>نسبت با بيمه‌شده</th>
        <th>درصد سهم</th>
        <th>الويت</th>
    </tr>
    <c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">
        <c:if test="${row.changeType == 'LIST'}">
            <c:forEach var="estefadeKonande" items="${row.toValues}" varStatus="j">
                <c:if test="${function:hasProperty(estefadeKonande, 'vaziateFotVaHayat')}">
                <c:if test="${estefadeKonande.vaziateFotVaHayat == 'hayat'}">
                    <tr>
                        <td>${j.index}</td>
                        <td>${estefadeKonande.name}</td>
                        <td>${estefadeKonande.nameKhanevadegi}</td>
                        <td>${estefadeKonande.nameKhanevadegi}</td>
                        <td>${estefadeKonande.kodeMelli}${estefadeKonande.kodeEghtesadi}</td>
                        <td>${estefadeKonande.shomareShenasname}</td>
                        <td>${estefadeKonande.tarikhTavallod}</td>
                        <td>${estefadeKonande.mahalleTavallod}</td>
                        <td>${estefadeKonande.nesbatBabimeShode}</td>
                        <td>${estefadeKonande.darsadeSahm}</td>
                        <td>${estefadeKonande.olaviat}</td>
                    </tr>
                </c:if>
                </c:if>
            </c:forEach>
        </c:if>
    </c:forEach>
</table>
<br class="clear"/>

<div id="result_elhaghie"></div>
<div>&nbsp;</div>

<!--
<div id="taeedvaehraz">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        تایید درخواست و احراز هویت بیمه گذار
    </p>
    <style type="text/css">
        .mystrippedtable label,.mystrippedtable input[type=checkbox]{
            float:left;
        }
    </style>
    <table class="mystrippedtable" dir="rtl" cellpadding="2px" cellspacing="0px" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tr class="odd">
            <td>
                <c:choose>
                    <c:when test="${user.namayandegi.type==1}">
                        <input type="checkbox" checked="checked" name=""/><label>مجتمع بیمه ای</label>
                        <input type="checkbox" name=""/><label>شعبه</label>
                        <input type="checkbox" name=""/><label>نمایندگی</label>
                        <input type="checkbox" name=""/><label>مجتمع بیمه ای</label>
                    </c:when>
                    <c:when test="${user.namayandegi.type==2}">
                        <input type="checkbox" name=""/><label>مجتمع بیمه ای</label>
                        <input type="checkbox" checked="checked" name=""/><label>شعبه</label>
                        <input type="checkbox" name=""/><label>نمایندگی</label>
                        <input type="checkbox" name=""/><label>مجتمع بیمه ای</label>
                    </c:when>
                    <c:when test="${user.namayandegi.type==3}">
                        <input type="checkbox" name=""/><label>مجتمع بیمه ای</label>
                        <input type="checkbox" name=""/><label>شعبه</label>
                        <input type="checkbox" checked="checked" name=""/><label>نمایندگی</label>
                        <input type="checkbox" name=""/><label>مجتمع بیمه ای</label>
                    </c:when>
                    <c:when test="${user.namayandegi.type==4}">
                        <input type="checkbox" name=""/><label>مجتمع بیمه ای</label>
                        <input type="checkbox" name=""/><label>شعبه</label>
                        <input type="checkbox" name=""/><label>نمایندگی</label>
                        <input type="checkbox" checked="checked" name=""/><label>ICD</label>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" name=""/><label>مجتمع بیمه ای</label>
                        <input type="checkbox" name=""/><label>شعبه</label>
                        <input type="checkbox" name=""/><label>نمایندگی</label>
                        <input type="checkbox" name=""/><label>مجتمع بیمه ای</label>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <label>کد:</label>
            </td>
            <td>
                <input type="text" name="darkhastBazkharid.kodeNamayandegi" readonly="readonly" value="<c:out value='${user.namayandegi.kodeNamayandeKargozar}'/>"/>
            </td>
            <td>
                <label>تلفن:</label>
            </td>
            <td>
                <input type="text" name="darkhastBazkharid.telephoneNamayandegi" readonly="readonly" value="<c:out value='${user.namayandegi.telephone}'/>"/>
            </td>
        </tr>
        <tr class="odd">
            <td colspan="5">
                اینجانب
                <b><c:out value="${user.firstName}"/>&nbsp;<c:out value="${user.lastName}"/></b>
                ، ضمن احراز هویت بیمه گذار محترم آقای/خانم
                <b><c:out value='${pishnehad.bimeGozar.shakhs.name}'/>&nbsp;<c:out value='${pishnehad.bimeGozar.shakhs.nameKhanevadegi}'/></b>
                درخواست وی مبنی بر
                &nbsp;<b>بازخرید</b>&nbsp;
                بیمه نامه عمر انفرادی به شماره
                &nbsp;<b><c:out value='${pishnehad.bimename.shomare}'/></b>&nbsp;
                را تایید و اعلام می دارم. ضمنا توضیحات تکمیلی در خصوص درخواست بیمه گذار، مطابق با شرایط عمومی بهره مندی از منافع بیمه نامه های عمر انفرادی ارائه گردید.
            </td>
        </tr>
    </table>

</div>
-->

<div style="width:800px;height:30px;margin-top:10px">
    <input type="submit" value="پرینت" style="float:left;"/>
</div>

</form>

</script>