<%@ taglib prefix="function" uri="http://tagutils" %>
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
<html>
<head>
    <title>درخواست الحاقیه</title>
</head>
<body>
<form action="/printDarkhasteElhaghie.action" method="post" id="form_elhaghie_taghirat" target="_blank">
<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        درخواست الحاقیه
    </p>
</div>
<input type="hidden" name="pishnehadReport.pishnehad.id" value="${pishnehad.id}">
<input type="hidden" name="pishnehadReport.newPishnehad.id" value="${newPishnehad.id}">
<table class="mystrippedtable" dir="rtl" cellpadding="2px" cellspacing="0px"
       style="border-spacing:1px;margin-left:auto;margin-right:auto;width:700px" border="0">
    <tr class="odd">
        <td class="even">شماره بیمه نامه</td>
        <td nowrap="true">${pishnehad.bimename.shomare}</td>
        <td class="even">نوع بیمه نامه</td>
        <td>عمر و سرمایه گذاری</td>
        <td class="even">تاريخ صدور بيمه‌نامه</td>
        <td>${pishnehad.bimename.tarikhSodour}</td>
        <td class="even">تاريخ ثبت درخواست الحاقيه</td>
        <td><%=DateUtil.getCurrentDate()%>
        </td>
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
        <td>${pishnehad.addressBimeGozar.telephoneManzel} ${pishnehad.addressBimeGozar.codeTelephoneManzel}</td>
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
        <td>${pishnehad.addressBimeShode.telephoneManzel} ${pishnehad.addressBimeShode.codeTelephoneManzel}</td>
    </tr>
</table>
<%----%>
<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        جدول تغييرات درخواست شده در بيمه‌نامه
    </p>
</div>
<table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"
       style="border-spacing:1px;margin:0 auto;" border="0">
    <tr>
        <th>تغيير مورد درخواست</th>
        <th>شرايط فعلي بيمه‌نامه</th>
        <th>شرايط جديد بيمه‌نامه</th>
    </tr>
    <c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">
        <c:if test="${row.changeType == 'FIELD'&&row.category!='VAZIAT_SALAMATI'}">
            <tr>
            <td>${row.subject}<input type="hidden" name="pishnehadReport.pishnehadFieldChangesList[${i.index}].subject"
                                     value="${row.subject}"></td>
            <td>
                <c:choose>
                    <c:when test="${row.fromValue == 'mah'}">
                        ماهانه
                    </c:when>
                    <c:when test="${row.fromValue == '3mah'}">
                        سه ماهه
                    </c:when>
                    <c:when test="${row.fromValue == '6mah'}">
                        شش مامه
                    </c:when>
                    <c:when test="${row.fromValue == 'sal'}">
                        سالانه
                    </c:when>
                    <c:when test="${row.fromValue == 'yekja'}">
                        یکجا
                    </c:when>
                    <c:otherwise>
                        ${row.fromValue == 'bali'?'بلی':row.fromValue == 'kheir'?'خیر':row.fromValue||row.fromValue == 'yes'?'دارد':row.fromValue == 'no'?'ندارد':row.fromValue}
                    </c:otherwise>
                </c:choose>
                <input type="hidden" name="pishnehadReport.pishnehadFieldChangesList[${i.index}].fromValue"
                       value="${row.fromValue}">
            </td>
            <td>
                <c:choose>
                    <c:when test="${row.toValue == 'mah'}">
                        ماهانه
                    </c:when>
                    <c:when test="${row.toValue == '3mah'}">
                        سه ماهه
                    </c:when>
                    <c:when test="${row.toValue == '6mah'}">
                        شش مامه
                    </c:when>
                    <c:when test="${row.toValue == 'sal'}">
                        سالانه
                    </c:when>
                    <c:when test="${row.toValue == 'yekja'}">
                        یکجا
                    </c:when>
                    <c:otherwise>
                        ${row.toValue == 'bali'?'بلی':row.toValue == 'kheir'?'خیر':row.toValue||row.toValue == 'yes'?'دارد':row.toValue == 'no'?'ندارد':row.toValue}
                    </c:otherwise>
                </c:choose>
                <input type="hidden" name="pishnehadReport.pishnehadFieldChangesList[${i.index}].toValue"
                       value="${row.toValue}">
            </td>

        </c:if>
        </tr>


    </c:forEach>
    <%--<s:if test='option_changes'>--%>
        <tr>
            <td>
                تاریخ اثر
            </td>
            <td style="width:210px">
                <c:if test="${haveBareMali}">
                    <s:select theme="simple" list="pishnehad.bimename.tarikhAsarListForElhaghie" id="saleMohasebe" name="whenApply"
                          style="width:200px"/>
                </c:if>
                <c:if test="${!haveBareMali}">

                    <input type="text" id="tarikhAsar"
                           name="tarikhAsar"
                           class="datePkr validate[custom[date]]"
                           readonly="readonly" onchange="checkTarikhAsar();"
                           value="${pishnehad.bimename.tarikhSodour}" />
                    <%--<s:select theme="simple" list="pishnehad.bimename.tarikhAsarListForElhaghie" id="saleMohasebe" name="whenApply"--%>
                          <%--style="width:200px"/>--%>
                </c:if>
            </td>
        </tr>
    <%--</s:if>--%>
    <%--<s:else>--%>
        <%--<input type="hidden" id="saleMohasebe" value="0"/>--%>
    <%--</s:else>--%>
</table>
<br class="clear"/>

<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        جدول ذينفعان فوت بيمه‌نامه
    </p>
</div>
<% boolean isEmptyFout = true;%>
<c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">
    <c:if test="${row.changeType == 'LIST'}">
        <c:forEach var="estefadeKonande" items="${row.toValues}" varStatus="j">
            <c:if test="${function:hasProperty(estefadeKonande, 'vaziateFotVaHayat')}">
                <c:if test="${estefadeKonande.vaziateFotVaHayat == 'fout'}">
                    <% isEmptyFout = false;%>
                </c:if>
            </c:if>
        </c:forEach>
    </c:if>
</c:forEach>
<c:set var="emptyCheckerFout" value="<%=isEmptyFout%>"/>
<c:if test="${emptyCheckerFout}">
    تغییری در این جدول صورت نگرفته است.
</c:if>
<c:if test="${!emptyCheckerFout}">
    <p>
        قبل از انجام تغییرات:
    </p>
    <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"
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
                <c:forEach var="estefadeKonande" items="${row.fromValues}" varStatus="j">
                    <c:if test="${function:hasProperty(estefadeKonande, 'vaziateFotVaHayat')}">
                        <c:if test="${estefadeKonande.vaziateFotVaHayat == 'fout'}">
                            <tr>
                                <td>${j.index + 1}</td>
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
    <p>
        بعد از انجام تغییرات:
    </p>
    <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"
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
                                <td>${j.index + 1}</td>
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
</c:if>
<br class="clear"/>

<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        جدول ذينفعان حيات بيمه‌نامه
    </p>
</div>
<% boolean isEmptyHayat = true;%>
<c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">
    <c:if test="${row.changeType == 'LIST'}">
        <c:forEach var="estefadeKonande" items="${row.toValues}" varStatus="j">
            <%--!!!!${estefadeKonande.class.name}????--%>
            <%--<c:if test="${estefadeKonande.class.name =='EstefadeKonandeganAzSarmayeBime'}">--%>
            <c:if test="${function:hasProperty(estefadeKonande, 'vaziateFotVaHayat')}">
                <c:if test="${estefadeKonande.vaziateFotVaHayat == 'hayat'}">
                    <% isEmptyHayat = false;%>
                </c:if>
            </c:if>
            <%--</c:if>--%>
        </c:forEach>
    </c:if>
</c:forEach>
<c:set var="emptyCheckerHayat" value="<%=isEmptyHayat%>"/>
<c:if test="${emptyCheckerHayat}">
    تغییری در این جدول صورت نگرفته است.
</c:if>
<c:if test="${!emptyCheckerHayat}">
    <p>
        قبل از انجام تغییرات:
    </p>
    <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"
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
                <c:forEach var="estefadeKonande" items="${row.fromValues}" varStatus="j">
                    <c:if test="${function:hasProperty(estefadeKonande, 'vaziateFotVaHayat')}">
                        <c:if test="${estefadeKonande.vaziateFotVaHayat == 'hayat'}">
                            <tr>
                                <td>${j.index + 1}</td>
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
    <p>
        بعد از انجام تغییرات:
    </p>
    <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"
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
                                <td>${j.index + 1}</td>
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
</c:if>
<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
 جدول سوابق بیمه ای
    </p>
</div>
<% boolean isEmptySavabegh = true;%>
<c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">
    <c:if test="${row.changeType=='LIST'}">
        <c:forEach var="savabegh" items="${row.toValues}" varStatus="j">
            <c:if test="${function:hasProperty(savabegh, 'sharheAdameSodor')}">
                <% isEmptySavabegh = false;%>
            </c:if>
        </c:forEach>
    </c:if>
</c:forEach>
<c:set var="emptyCheckerSavabegh" value="<%=isEmptySavabegh%>"/>
<c:if test="${emptyCheckerSavabegh}">
    در این جدول تغییری صورت نگرفته است.
</c:if>
<c:if test="${!emptyCheckerSavabegh}">
    <p>قبل از انجام تغییرات:</p>
    <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin:0 auto;" border="0">
        <tr>
            <th>ردیف</th>
            <th>نوع بیمه نامه</th>
            <th>نام بیمه نامه</th>
            <th>شرکت بیمه گر</th>
            <th>نتیجه نهایی</th>
            <th>سرمایه فوت</th>
            <th>سرمایه حیات</th>
            <th>شرح عدم صدور</th>
            <th> تاریخ خاتمه</th>
        </tr>
        <c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">
            <c:if test="${row.changeType == 'LIST'}">
                <c:forEach var="savabegh" items="${row.fromValues}" varStatus="j">
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
    <p>        بعد از انجام تغییرات:</p>
    <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin:0 auto;" border="0">
        <tr>
            <th>ردیف</th>
            <th>نوع بیمه نامه</th>
            <th>نام بیمه نامه</th>
            <th>شرکت بیمه گر</th>
            <th>نتیجه نهایی</th>
            <th>سرمایه فوت</th>
            <th>سرمایه حیات</th>
            <th>شرح عدم صدور</th>
            <th> تاریخ خاتمه</th>
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
</c:if>
<br class="clear"/>

<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        جدول وضعیت سلامتی خانواده بیمه شده
    </p>
</div>
<% boolean isEmptyKhanevade = true;%>
<c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">
    <c:if test="${row.changeType == 'LIST'}">
        <c:forEach var="khanevade" items="${row.toValues}" varStatus="j">
            <c:if test="${function:hasProperty(khanevade, 'sharheEllateFout')}">
                <%  isEmptyKhanevade = false;%>
            </c:if>
        </c:forEach>
    </c:if>
</c:forEach>
<c:set var="emptyCheckerKhanevade" value="<%=isEmptyKhanevade%>"/>
<c:if test="${emptyCheckerKhanevade}">
    تغییری در این جدول صورت نگرفته است.
</c:if>
<c:if test="${!emptyCheckerKhanevade}">
    <p>
        قبل از انجام تغییرات:
    </p>
    <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin:0 auto;" border="0">
        <tr>
            <th>ردیف</th>
            <th>نسبت با بیمه شده</th>
            <th>وضعیت حیات</th>
            <th>سن در حیات</th>
            <th>وضعیت سلامتی</th>
            <th>سن در زمان فوت</th>
            <th>علت فوت</th>
            <th>شرح علت فوت</th>
        </tr>

        <c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">
            <c:if test="${row.changeType == 'LIST'}">
                <c:forEach var="khanevade" items="${row.fromValues}" varStatus="j">
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
    <p>
        بعد از انجام تغییرات:
    </p>
    <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin:0 auto;" border="0">
        <tr>
            <th>ردیف</th>
            <th>نسبت با بیمه شده</th>
            <th>وضعیت حیات</th>
            <th>سن در حیات</th>
            <th>وضعیت سلامتی</th>
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
</c:if>
<br class="clear"/>


<br class="clear"/>

<div id="result_elhaghie"></div>
<div>&nbsp;</div>
<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        وضعیت سلامتی بیمه شده
    </p>
    <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin:0 auto;" border="0">
        <thead>
        <th>ردیف</th>
        <th>سوال</th>
        <th>جواب سوال قبل از تغییر</th>
        <th>جواب سوال بعد از تغییر</th>
        </thead>
        <tbody>
        <c:forEach var="row" items="${pishnehadFieldChangesList}" varStatus="i">
            <c:if test="${row.changeType == 'FIELD' && row.category=='VAZIAT_SALAMATI'}">
                <tr>
                    <td>${i.index}</td>
                    <td>${row.subject}</td>
                    <td>
                            ${row.fromValue == 'BALI'?'بلی':row.fromValue == 'KHEIR'?'خیر':row.fromValue}
                    </td>
                    <td>
                            ${row.toValue == 'BALI'?'بلی':row.toValue == 'KHEIR'?'خیر':row.toValue}
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>

</div>
<div id="taeedvaehraz">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        تایید درخواست و احراز هویت بیمه گذار
    </p>
    <style type="text/css">
        .mystrippedtable label, .mystrippedtable input[type=checkbox] {
            float: left;
        }
    </style>
    <table class="mystrippedtable" dir="rtl" cellpadding="2px" cellspacing="0px"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
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
                <input type="text" name="darkhastBazkharid.kodeNamayandegi" readonly="readonly"
                       value="<c:out value='${user.namayandegi.kodeNamayandeKargozar}'/>"/>
            </td>
            <td>
                <label>تلفن:</label>
            </td>
            <td>
                <input type="text" name="darkhastBazkharid.telephoneNamayandegi" readonly="readonly"
                       value="<c:out value='${user.namayandegi.telephone}'/>"/>
            </td>
        </tr>
        <tr class="odd">
            <td colspan="5">
                اینجانب
                <b><c:out value="${user.firstName}"/>&nbsp;<c:out value="${user.lastName}"/></b>
                                ، ضمن احراز هویت بیمه گذار محترم
                <b><c:out value='${pishnehad.bimeGozar.shakhs.name}'/>&nbsp;<c:out
                        value='${pishnehad.bimeGozar.shakhs.nameKhanevadegi}'/></b>
                درخواست وی مبنی بر
                &nbsp;<b> تغيير </b>&nbsp;
                بیمه نامه عمر انفرادی به شماره
                &nbsp;<b><c:out value='${newPishnehad.bimename.shomare}'/></b>&nbsp;
                را تایید و اعلام می دارم. ضمنا توضیحات تکمیلی در خصوص درخواست بیمه گذار، مطابق با شرایط عمومی بهره مندی
                از منافع بیمه نامه های عمر انفرادی ارائه گردید.
            </td>
        </tr>
    </table>
</div>

<div style="width:800px;height:30px;margin-top:10px">
    <input type="submit" value="پرینت" style="float:left;"/>
    <input type="button" onclick="window.location='/deleteUndoneElhaghiye.action?newPishnehad.id=${newPishnehad.id}&pishnehad.id=${pishnehad.id}'"
           value="بازگشت" style="float:left;margin-left:10px"/>
    <c:if test="${!haveBareMali}">
        <input type="button" id="dogmeyeSaveElhaghiye"
               onclick="go();"
               value="ثبت موقت درخواست الحاقيه اصلاحي" style="float: left;margin-left:35px"/>
    </c:if>
    <c:if test="${haveBareMali}">
        <input type="button" id="dogmeyeSaveElhaghiye"
               onclick="window.location='/saveElhaghiyeTaghirat?pishnehad.id=${pishnehad.id}&newPishnehad.id=${newPishnehad.id}&whenApply='+$('#saleMohasebe').val()"
               value="ثبت موقت درخواست الحاقيه اصلاحي" style="float: left;margin-left:35px"/>
    </c:if>
</div>

<div id="wrongParameteresInner" style="display:none;">
    <div class="failureMessage">
        حق بيمه ي شما براي برآورده كردن تعهدات كافي نيست، لازم است كه حق بيمه پرداختي بيشتر و يا سرمايه فوت كمتر گردد.
    </div>
</div>
<div id="extraWrongParameteresInner" style="display:none;">
    <div class="failureMessage">
        مبلغ اندوخته سرمايه گذاري صفر و يا منفي است. بايستي سرمايه فوت كاهش و يا مبلغ حق بيمه افزايش پيدا كند.
    </div>
</div>

</form>
<script type="text/javascript">
    <s:if test="%{!valid_changes}">
        $(document).ready(function(){alertMessageByLock("${message}", goToPage('/deleteUndoneElhaghiye.action?newPishnehad.id=${newPishnehad.id}&pishnehad.id=${pishnehad.id}'));});
    </s:if>
    function anjameElhaghie(fromValue, toValue) {
        $.post("/anjameElhaghie.action?pishnehad.id=${pishnehad.id}&fromValue=" + fromValue + "&toValue=" + toValue + "&saleMohasebe=" + $('#saleMohasebe').val(), function (msg) {
            $('#result_elhaghie').html(msg);
        });
    }
    function wrongParameteres(param) {
        $.validationEngine.closePrompt('.formError', true);
        $('#' + param).dialog({
            modal:true,
            width:620,
            resizable:false,
            closeText:"",
            title:"خطا در مقدار دهي پارامترها",
            close:function () {
                $.validationEngine.closePrompt('.formError', true);
            },
            buttons:{
                "بستن":function () {
                    $.validationEngine.closePrompt('.formError', true);
                    $(this).dialog("close");
                }
            }
        });
    }

    function go()
    {
        $.ajax
        ({
            type: "POST",
            url: "/checkTarikhAsar?tarikhAsar=" + $('#tarikhAsar').val() + "&bimename.id=${pishnehad.bimename.id}",
            success: function (response)
            {
                if (response == 'true')
                {
                    window.location = '/saveElhaghiyeTaghirat?haveBareMali=${haveBareMali}&pishnehad.id=${pishnehad.id}&newPishnehad.id=${newPishnehad.id}&tarikhAsar=' + $('#tarikhAsar').val()
                }
                else
                {
                     alertMessage("تاریخ اثر الحاقیه باید بزرگتر از تاریخ شروع بیمه نامه و همچنین تاریخ اثر الحاقیه های قبلی باشد.");
                }
            }
        });
    }

</script>
</body>
</html>