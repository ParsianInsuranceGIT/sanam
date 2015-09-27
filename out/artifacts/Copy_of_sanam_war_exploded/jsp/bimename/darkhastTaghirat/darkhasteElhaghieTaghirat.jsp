<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.util.OmrUtil" %>
<%@ taglib prefix="function" uri="http://tagutils" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron0
  Date: Jul 20, 2011
  Time: 3:16:40 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="/printDarkhasteElhaghie.action" method="post" id="form_elhaghie_taghirat" target="_blank">
<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        درخواست الحاقیه
    </p>
</div>
<input type="hidden" name="pishnehadReport.pishnehad.id" value="${darkhastTaghirat.oldPishnehad.id}">
<input type="hidden" name="pishnehadReport.newPishnehad.id" value="${darkhastTaghirat.newPishnehad.id}">
<table class="mystrippedtable" dir="rtl" cellpadding="2px" cellspacing="0px"
       style="border-spacing:1px;margin-left:auto;margin-right:auto;width:700px" border="0">
    <tr class="odd">
        <td class="even">شماره بیمه نامه</td>
        <td>${darkhastTaghirat.oldPishnehad.bimename.shomare}</td>
        <td class="even">نوع بیمه نامه</td>
        <td>عمر و سرمایه گذاری</td>
        <td class="even">تاريخ صدور بيمه‌نامه</td>
        <td>${darkhastTaghirat.oldPishnehad.bimename.tarikhSodour}</td>
        <td class="even">تاريخ ثبت درخواست الحاقيه</td>
        <td>${darkhastTaghirat.darkhast.elhaghiye.createdDate}</td>

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
        <td>${darkhastTaghirat.oldPishnehad.bimeGozar.shakhs.name}</td>
        <td class="even">نام خانوادگی</td>
        <td>${darkhastTaghirat.oldPishnehad.bimeGozar.shakhs.nameKhanevadegi}</td>
        <td class="even">نام پدر</td>
        <td>${darkhastTaghirat.oldPishnehad.bimeGozar.shakhs.namePedar}</td>
    </tr>
    <tr class="odd">
        <td class="even">کد ملی</td>
        <td>${darkhastTaghirat.oldPishnehad.bimeGozar.shakhs.kodeMelliShenasayi}</td>
        <td class="even">شماره شناسنامه</td>
        <td>${darkhastTaghirat.oldPishnehad.bimeGozar.shakhs.shomareShenasnameh}</td>
        <td class="even">تلفن</td>
        <td>${darkhastTaghirat.oldPishnehad.addressBimeGozar.codeTelephoneManzel} ${darkhastTaghirat.oldPishnehad.addressBimeGozar.telephoneManzel}</td>
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
        <td>${darkhastTaghirat.oldPishnehad.bimeShode.shakhs.name}</td>
        <td class="even">نام خانوادگی</td>
        <td>${darkhastTaghirat.oldPishnehad.bimeShode.shakhs.nameKhanevadegi}</td>
        <td class="even">نام پدر</td>
        <td>${darkhastTaghirat.oldPishnehad.bimeShode.shakhs.namePedar}</td>
    </tr>
    <tr class="odd">
        <td class="even">کد ملی</td>
        <td>${darkhastTaghirat.oldPishnehad.bimeShode.shakhs.kodeMelliShenasayi}</td>
        <td class="even">شماره شناسنامه</td>
        <td>${darkhastTaghirat.oldPishnehad.bimeShode.shakhs.shomareShenasnameh}</td>
        <td class="even">تلفن</td>
        <td>${darkhastTaghirat.oldPishnehad.addressBimeShode.codeTelephoneManzel}&nbsp;${darkhastTaghirat.oldPishnehad.addressBimeShode.telephoneManzel}</td>
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
    <%--<s:if test="option_changes">--%>
        <tr>
            <td>
              زمان اثر الحاقیه
            </td>
            <td style="width:230px">
                <c:if test="${darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050}">
                    <span class="ui-icon ui-icon-disk" style="margin-top: 4px; float: left; margin-right: 8px;"
                          onclick="saveSaleAsarAjaxly();"></span>
                </c:if>
                <c:if test="${darkhastTaghirat.haveBareMali==null || !darkhastTaghirat.haveBareMali}">
                    <input type="text" id="elhaghie_asar_date"
                           name="elhaghiye.tarikhAsar"
                           <c:if test="${elhaghiye.tarikhAsar!=null}">
                           value="${elhaghiye.tarikhAsar}"
                               </c:if>
                    <c:if test="${darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050}">
                        class="datePkr validate[custom[date]]"
                    </c:if>
                           readonly="readonly"
                    <c:if test="${elhaghiye.tarikhAsar!=null}">
                        value="${bimename.tarikhSodour}"
                    </c:if>
                                />
                </c:if>
                <c:if test="${darkhastTaghirat.haveBareMali}">
                    <s:select theme="simple" list="pishnehad.bimename.tarikhAsarListForElhaghie" id="saleMohasebe"
                          name="whenApply" disabled="true" value="darkhastTaghirat.whenApply"
                          style="width:200px"/>
                </c:if>

            </td>
        </tr>
    <%--</s:if>--%>

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
                        <td>${estefadeKonande.namePedar}</td>
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
                        <td>${estefadeKonande.namePedar}</td>
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
 جدول سوابق بيمه اي
    </p>
</div>
<%--<% boolean isEmptySavabegh = true;%>--%>
<%--<c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">--%>
    <%--<c:if test="${row.changeType=='LIST'}">--%>
        <%--<c:forEach var="savabegh" items="${row.toValues}" varStatus="j">--%>
            <%--<c:if test="${function:hasProperty(savabegh, 'sharheAdameSodor')}">--%>
                <%--<% isEmptySavabegh = false;%>--%>
            <%--</c:if>--%>
        <%--</c:forEach>--%>
    <%--</c:if>--%>
<%--</c:forEach>--%>
<%--<c:set var="emptyCheckerSavabegh" value="<%=isEmptySavabegh%>"/>--%>
<%--<c:if test="${emptyCheckerSavabegh}">--%>
    <%--در اين جدول تغييري صورت نگرفته است.--%>
<%--</c:if>--%>
<%--<c:if test="${!emptyCheckerSavabegh}">--%>
    <%--<p>قبل از انجام تغييرات:</p>--%>
    <%--<table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"--%>
           <%--style="border-spacing:1px;margin:0 auto;" border="0">--%>
        <%--<tr>--%>
            <%--<th>رديف</th>--%>
            <%--<th>نوع بيمه نامه</th>--%>
            <%--<th>نام بيمه نامه</th>--%>
            <%--<th>شرکت بيمه گر</th>--%>
            <%--<th>نتيجه نهايي</th>--%>
            <%--<th>سرمايه فوت</th>--%>
            <%--<th>سرمايه حيات</th>--%>
            <%--<th>شرح عدم صدور</th>--%>
            <%--<th> تاريخ خاتمه</th>--%>
        <%--</tr>--%>
        <%--<c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">--%>
            <%--<c:if test="${row.changeType == 'LIST'}">--%>
                <%--<c:forEach var="savabegh" items="${row.fromValues}" varStatus="j">--%>
                    <%--<c:if test="${function:hasProperty(savabegh, 'sharheAdameSodor')}">--%>
                        <%--<tr>--%>
                            <%--<td>${j.index+1}</td>--%>
                            <%--<td>${savabegh.noeBimeName}</td>--%>
                            <%--<td>${savabegh.nameBimeName}</td>--%>
                            <%--<td>${savabegh.sherkateBimeGar}</td>--%>
                            <%--<td>${savabegh.natijeNahayi}</td>--%>
                            <%--<td>${savabegh.sarmayeFout}</td>--%>
                            <%--<td>${savabegh.sarmayeHayat}</td>--%>
                            <%--<td>${savabegh.sharheAdameSodor}</td>--%>
                            <%--<td>${savabegh.tarikheKhateme}</td>--%>
                        <%--</tr>--%>
                    <%--</c:if>--%>
                <%--</c:forEach>--%>
            <%--</c:if>--%>
        <%--</c:forEach>--%>
    <%--</table>--%>
    <%--<p>        بعد از انجام تغييرات:</p>--%>
    <table class="jtable resultDets" align="center" width="700px" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin:0 auto;" border="0">
        <tr>
            <th>رديف</th>
            <th>نوع بيمه نامه</th>
            <th>نام بيمه نامه</th>
            <th>شرکت بيمه گر</th>
            <th>نتيجه نهايي</th>
            <th>سرمايه فوت</th>
            <th>سرمايه حيات</th>
            <th>شرح عدم صدور</th>
            <th> تاريخ خاتمه</th>
        </tr>
        <c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">
            <c:if test="${row.changeType == 'LIST'}">
                <c:forEach var="savabegh" items="${row.toValues}" varStatus="j">
                    <c:if test="${function:hasProperty(savabegh, 'sharheAdameSodor')}">
                        <tr>
                            <td>${j.index+1}</td>
                            <td>${savabegh.noeBimeName}</td>
                            <td>${savabegh.nameBimeName}</td>
                            <td>${savabegh.sherkateBimeGar}</td>
                            <td>${savabegh.natijeNahayi}</td>
                            <td>${savabegh.sarmayeFout}</td>
                            <td>${savabegh.sarmayeHayat}</td>
                            <td>${savabegh.sharheAdameSodor}</td>
                            <td>${savabegh.tarikheKhateme}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:if>
        </c:forEach>
    </table>
<%--</c:if>--%>
<br class="clear"/>

<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        جدول وضعيت سلامتي خانواده بيمه شده
    </p>
</div>
<%--<% boolean isEmptyKhanevade = true;%>--%>
<%--<c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">--%>
    <%--<c:if test="${row.changeType == 'LIST'}">--%>
        <%--<c:forEach var="khanevade" items="${row.toValues}" varStatus="j">--%>
            <%--<c:if test="${function:hasProperty(khanevade, 'sharheEllateFout')}">--%>
                <%--<%  isEmptyKhanevade = false;%>--%>
            <%--</c:if>--%>
        <%--</c:forEach>--%>
    <%--</c:if>--%>
<%--</c:forEach>--%>
<%--<c:set var="emptyCheckerKhanevade" value="<%=isEmptyKhanevade%>"/>--%>
<%--&lt;%&ndash;<c:if test="${emptyCheckerKhanevade}">&ndash;%&gt;--%>
    <%--&lt;%&ndash;تغييري در اين جدول صورت نگرفته است.&ndash;%&gt;--%>
<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
<%--<c:if test="${!emptyCheckerKhanevade}">--%>
    <%--<p>--%>
        <%--قبل از انجام تغييرات:--%>
    <%--</p>--%>
    <%--<table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"--%>
           <%--style="border-spacing:1px;margin:0 auto;" border="0">--%>
        <%--<tr>--%>
            <%--<th>رديف</th>--%>
            <%--<th>نسبت با بيمه شده</th>--%>
            <%--<th>وضعيت حيات</th>--%>
            <%--<th>سن در حيات</th>--%>
            <%--<th>وضعيت سلامتي</th>--%>
            <%--<th>سن در زمان فوت</th>--%>
            <%--<th>علت فوت</th>--%>
            <%--<th>شرح علت فوت</th>--%>
        <%--</tr>--%>

        <%--<c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">--%>
            <%--<c:if test="${row.changeType == 'LIST'}">--%>
                <%--<c:forEach var="khanevade" items="${row.fromValues}" varStatus="j">--%>
                    <%--<c:if test="${function:hasProperty(khanevade, 'sharheEllateFout')}">--%>
                        <%--<tr>--%>
                            <%--<td>${j.index + 1}</td>--%>
                            <%--<td>${khanevade.nesbatBabimeShode}</td>--%>
                            <%--<td>${khanevade.vaziateHayat}</td>--%>
                            <%--<td>${khanevade.senneDarHayat}</td>--%>
                            <%--<td>${khanevade.vaziateSalamati}</td>--%>
                            <%--<td>${khanevade.senneDarZamaneFout}</td>--%>
                            <%--<td>${khanevade.ellateFout}</td>--%>
                            <%--<td>${khanevade.sharheEllateFout}</td>--%>
                        <%--</tr>--%>
                    <%--</c:if>--%>
                <%--</c:forEach>--%>
            <%--</c:if>--%>
        <%--</c:forEach>--%>
    <%--</table>--%>
    <%--<p>--%>
        <%--بعد از انجام تغييرات:--%>
    <%--</p>--%>
    <table class="jtable resultDets" align="center" width="700px" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin:0 auto;" border="0">
        <tr>
            <th>رديف</th>
            <th>نسبت با بيمه شده</th>
            <th>وضعيت حيات</th>
            <th>سن در حيات</th>
            <th>وضعيت سلامتي</th>
            <th>سن در زمان فوت</th>
            <th>علت فوت</th>
            <th>شرح علت فوت</th>
        </tr>

        <c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">
            <c:if test="${row.changeType == 'LIST'}">
                <c:forEach var="khanevade" items="${row.toValues}" varStatus="j">
                    <c:if test="${function:hasProperty(khanevade, 'sharheEllateFout')}">
                        <tr>
                            <td>${j.index + 1}</td>
                            <td>${khanevade.nesbatBabimeShode}</td>
                            <td>${khanevade.vaziateHayat}</td>
                            <td>${khanevade.senneDarHayat}</td>
                            <td>${khanevade.vaziateSalamati}</td>
                            <td>${khanevade.senneDarZamaneFout}</td>
                            <td>${khanevade.ellateFout}</td>
                            <td>${khanevade.sharheEllateFout}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:if>

        </c:forEach>
    </table>
<%--</c:if>--%>
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
                <b><c:out value='${darkhastTaghirat.oldPishnehad.bimeGozar.shakhs.name}'/>&nbsp;<c:out value='${darkhastTaghirat.oldPishnehad.bimeGozar.shakhs.nameKhanevadegi}'/></b>
                درخواست وی مبنی بر
                &nbsp;<b>بازخرید</b>&nbsp;
                بیمه نامه عمر انفرادی به شماره
                &nbsp;<b><c:out value='${darkhastTaghirat.oldPishnehad.bimename.shomare}'/></b>&nbsp;
                را تایید و اعلام می دارم. ضمنا توضیحات تکمیلی در خصوص درخواست بیمه گذار، مطابق با شرایط عمومی بهره مندی از منافع بیمه نامه های عمر انفرادی ارائه گردید.
            </td>
        </tr>
    </table>

</div>
-->
<input type="submit" value="پرینت" style="float:left;"/>
<%--<input type="button" onclick="window.alert($('#form_elhaghie_taghirat').serialize());" value="Test. . . "/>--%>
<c:if test="${darkhastTaghirat.id == null}">
    <div style="width:800px;height:30px;margin-top:10px">
        <input type="submit" value="پرینت" style="float:left;"/>
        <input type="button"
               onclick="window.location='/editShowForm?pishnehad.id=${darkhastTaghirat.oldPishnehad.id}'"
               value="بازگشت" style="float:left;margin-left:10px"/>
    </div>
</c:if>
</form>


<script type="text/javascript">
    $(document).ready(function () {
        <c:if test="${darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050}">
        $('#saleMohasebe').removeAttr("disabled");
        </c:if>
        <c:if test="${darkhastTaghirat.state.id == 9180}">
        <c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">
            <c:if test="${row.subject=='طرح'}">
                alertMessage("کاربر گرامی دقت داشته باشید، طرح بیمه نامه تغییر کرده است.");
            </c:if>
        </c:forEach>
        </c:if>
    });
    function goPrint()
    {
//        window.alert('0k');
        $("#form_elhaghie_taghirr").submit();
    }
    function anjameElhaghie(fromValue, toValue) {
        $.post("/anjameElhaghie?pishnehad.id=${pishnehad.id}&fromValue=" + fromValue + "&toValue=" + toValue + "&saleMohasebe=" + $('#saleMohasebe').val(), function (msg) {
            $('#result_elhaghie').html(msg);
        });
    }
    function saveSaleAsarAjaxly() {
        var url="/saveSaleAsarAjaxly?darkhastTaghirat.id=${darkhastTaghirat.id}&elhaghiye.tarikhAsar=" + $('#elhaghie_asar_date').val();
        <c:if test="${darkhastTaghirat.haveBareMali}">
            url= "/saveSaleAsarAjaxly?darkhastTaghirat.id=${darkhastTaghirat.id}" + "&whenApply=" + $('#saleMohasebe').val();
        </c:if>
        $.post(url, function (msg) {
            if (msg.indexOf('done') != -1) {
                alertMessage('تغییرات ثبت شد.');
                window.location = '/editDarkhastTaghirForm?darkhastTaghirat.id=${darkhastTaghirat.id}';
            }
            else if(msg.indexOf('error') != -1)
            {
                alertMessage('خطا در عملیات');
            }
            else if (msg.indexOf('err_before_start_date')!= -1)
            {
                alertMessage('تاریخ اثر الحاقیه نمی تواند قبل از تاریخ شروع بیمه نامه باشد.');
            }
            else if (msg.indexOf('err_befor_asar_date')!= -1)
            {
                alertMessage('تاریخ اثر الحاقیه باید بزرگتر از تاریخ اثر الحاقیه های قبلی باشد.');
            }
            else
            {
                //Nothing. .
            }
        });
    }
</script>