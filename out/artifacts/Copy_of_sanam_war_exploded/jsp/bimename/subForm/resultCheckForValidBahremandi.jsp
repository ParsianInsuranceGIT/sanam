<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="res_tasviye_vam">
    <c:if test="${not empty allTheVaams}">
        <c:forEach var="vaam" items="${allTheVaams}" varStatus="i">
            </br>
            <table class="jGrid" width="70%" align="center">
                <label>
                        شماره وام جهت تسویه:
                </label>
                    <input type="text" value="${vaam.shomareVam}" readonly="readonly" class=" ui-state-default ui-corner-all"/>
                    <input type="hidden" name="vamId" value="${vaam.id}" readonly="readonly" class=" ui-state-default ui-corner-all"/>
                </tr>

            </table>
            <table class="jGrid" width="70%" align="center">
                <thead>
                <tr>
                    <th>تاریخ صدور</th>
                    <th>مبلغ وام دریافتی</th>
                    <th>مدت بازپرداخت</th>
                    <th>نحوه بازپرداخت</th>
                    <th>مبلغ قسط پرداختی</th>
                    <th>مجموع مبالغ پرداختی</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${vaam.tarikhDarkhast}</td>
                    <td>${vaam.mablagheVamedarkhasti}</td>
                    <td>${vaam.modatebazpardakht}</td>
                    <td>${vaam.nahveyePardakhteAghsatFarsi}</td>
                    <td>
                        <c:forEach var="ghest" items="${vaam.ghestBandi.ghestList}" varStatus="i">
                            <c:if test="${i.index == 1}">
                                ${ghest.credebit.amount}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${vaam.jameMabaleghePardakhti}</td>
                </tr>
                </tbody>
            </table>
            </br>
        </c:forEach>
    </c:if>
</div>

<table width="100%" align="right" class="mystrippedtable" style="border-spacing: 1px;">

    <%--<c:if test="${errosMap == null}">--%>
    <%--<tr>--%>
    <%--<td>--%>
    <%--شرایط اخذ وام برای بیمه شده وجود دارد.--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--</c:if>--%>

    <input type="hidden" id="darkhast_type" value="${type}"/>
    <input type="hidden" id="result_arzesh" value="${resultArzesh}"/>
    <c:if test="${warningsMap['vam_tasviye_nashode_for_bazkharid']!=null}">
        <tr class="odd">
            <td>
                <p style="color: #966200;">
                    <img src="/img/alert.png" height="20px" width="20px"/>
                    &nbsp;${warningsMap['vam_tasviye_nashode_for_bazkharid']}
                </p>
            </td>
        </tr>
    </c:if>
    <c:if test="${warningsMap['mohlate_tasviye_vam']!=null}">
        <tr class="odd">
            <td>
                <p style="color: #966200;">
                    <img src="/img/alert.png" height="20px" width="20px"/> &nbsp;${warningsMap['mohlate_tasviye_vam']}
                </p>
            </td>
        </tr>
    </c:if>
    <c:if test="${warningsMap['taeed_khadamat_required']!=null}">
        <tr class="odd">
            <td>
                <p style="color: #966200;">
                    <img src="/img/alert.png" height="20px" width="20px"/>
                    &nbsp;${warningsMap['taeed_khadamat_required']}
                </p>
            </td>
        </tr>
    </c:if>
    <c:if test="${warningsMap['warning_azmayesh']!=null}">
        <tr class="odd">
            <td>
                <p style="color: #966200;">
                    <img src="/img/alert.png" height="20px" width="20px"/> &nbsp;${warningsMap['warning_azmayesh']}
                </p>
            </td>
        </tr>
    </c:if>
    <c:if test="${warningsMap['warning_20days']!=null}">
        <tr class="odd">
            <td>
                <p style="color: #966200;">
                    <img src="/img/alert.png" height="20px" width="20px"/> &nbsp;${warningsMap['warning_20days']}
                </p>
            </td>
        </tr>
    </c:if>
    <c:if test="${errorsMap['less_than_two_years'] !=null}">
        <tr class="odd">
            <td>
                <p style="color: red;">
                    <img src="/img/error.png" height="15px" width="15px"/> &nbsp;<c:out
                        value="${errorsMap['less_than_two_years']}"/>
                </p>
            </td>
        </tr>
    </c:if>
    <c:if test="${errorsMap['period_not_okay'] !=null}">
        <tr class="odd">
            <td>
                <p style="color: red;">
                    <img src="/img/error.png" height="15px" width="15px"/> &nbsp;<c:out
                        value="${errorsMap['period_not_okay']}"/>
                </p>
            </td>
        </tr>
    </c:if>
    <c:if test="${errorsMap['more_than_3times_bardasht'] !=null}">
        <tr class="odd">
            <td>
                <p style="color: red;">
                    <img src="/img/error.png" height="15px" width="15px"/> &nbsp;<c:out
                        value="${errorsMap['more_than_3times_bardasht']}"/>
                </p>
            </td>
        </tr>
    </c:if>
    <c:if test="${errorsMap['saghf'] !=null}">
        <tr class="odd">
            <td>
                <p style="color: red;">
                    <img src="/img/error.png" height="15px" width="15px"/> &nbsp;<c:out value="${errorsMap['saghf']}"/>
                </p>
            </td>
        </tr>
    </c:if>
    <c:if test="${errorsMap['undone_vaam'] !=null}">
        <tr class="odd">
            <td>
                <p style="color: red;">
                    <img src="/img/error.png" height="15px" width="15px"/>&nbsp;<c:out
                        value="${errorsMap['undone_vaam']}"/>
                </p>
            </td>
        </tr>
    </c:if>
    <c:if test="${errorsMap['undone_taghsit'] !=null}">
        <tr class="odd">
            <td>
                <p style="color: red;">
                    <img src="/img/error.png" height="15px" width="15px"/>&nbsp;<c:out
                        value="${errorsMap['undone_taghsit']}"/>
                </p>
            </td>
        </tr>
    </c:if>
    <c:if test="${errorsMap['bedehi_moavagh'] !=null}">
        <tr class="odd">
            <td>
                <p style="color: red;">
                    <img src="/img/error.png" height="15px" width="15px"/>&nbsp;<c:out
                        value="${errorsMap['bedehi_moavagh']}"/>
                </p>
            </td>
        </tr>
    </c:if>
    <c:if test="${errorsMap['arzesh_zero'] !=null}">
        <tr class="odd">
            <td>
                <p style="color: red;">
                    <img src="/img/error.png" height="15px" width="15px"/>&nbsp;<c:out
                        value="${errorsMap['arzesh_zero']}"/>
                </p>
            </td>
        </tr>
    </c:if>
    <c:if test="${errorsMap['more_than_2times_vaam'] !=null}">
        <tr class="odd">
            <td>
                <p style="color: red;">
                    <img src="/img/error.png" height="15px" width="15px"/>&nbsp;<c:out
                        value="${errorsMap['more_than_2times_vaam']}"/>
                </p>
            </td>
        </tr>
    </c:if>
    <c:if test="${type=='BAZKHARID'}">
        <tr class="odd">
            <td>
                <input type="checkbox" name="bimenameIsMafqud" id="bimenameIsMafqud"/>
                <label>اصل بیمه نامه که در اختیار بیمه گذار است مفقود شده است.</label>
            </td>
        </tr>
    </c:if>

    <c:if test="${errorsMap==null || fn:length(errorsMap)==0}">
        <c:if test="${type=='VAM'}">
            <tr class="odd">
                <td>
                    مبلغ وام درخواستی شما: &nbsp;&nbsp;&nbsp;&nbsp;<input id="mablagh_darkhasti" name="mablagh2"
                                                                          value="${resultArzesh}" type="text"
                                                                          class="digitSeparators"/>
                </td>
            </tr>
        </c:if>
        <c:if test="${type=='BARDASHT_AZ_ANDOKHTE'}">
            <tr class="odd">
                <td>
                    مبلغ برداشت درخواستی شما: &nbsp;&nbsp;&nbsp;&nbsp;<input id="mablagh_darkhasti" name="mablagh2" onchange="checkMablagh();"
                                                                             value="${resultArzesh}" type="text"
                                                                             class="ui-state-default ui-corner-all digitSeparators"/>
                </td>
            </tr>
            <tr class="odd">
                <td>
                   <span style="direction: rtl">
                        <s:property value="resultDarsad"/>
                   </span>
                </td>
            </tr>
        </c:if>

    </c:if>
    <tr class="odd">
        <td colspan="2" style="text-align: center;">
            <c:if test="${(errorsMap==null || fn:length(errorsMap)==0)&& empty allTheVaams }">
                <input type="button" class="ui-state-default  ui-corner-all" onclick="submbitSecondTypeDarkhast()"
                       value="ثبت درخواست"/>
            </c:if>
            <c:if test="${(errorsMap==null || fn:length(errorsMap)==0)&& not empty allTheVaams }">
                <input type="button" class="ui-state-default  ui-corner-all" onclick="submbitSecondTypeDarkhast()"
                       value="ثبت درخواست تسویه پیش از موعد"/>
            </c:if>
            <c:if test="${type=='VAM'}">
                <input type="button" class="ui-state-default  ui-corner-all"
                       onclick="window.open('/printeSharayeteAkhzeVam?pishnehadReport.bimename.id=${bimename.id}');"
                       value="چاپ شرایط اخذ وام">
            </c:if>
            <c:if test="${type=='BARDASHT_AZ_ANDOKHTE'}">
                <c:if test="${errorsMap['undone_taghsit'] == null || errorsMap['undone_taghsit'] == ''}">
                    <input type="button" class="ui-state-default  ui-corner-all"
                           onclick="window.open('/printeSharayeteBardashtAzAndokhte?pishnehadReport.bimename.id=${bimename.id}');"
                           value="چاپ شرايط برداشت از اندوخته">
                </c:if>
            </c:if>
        </td>
    </tr>
</table>
<script type="text/javascript">
    function checkMablagh()
    {
        var mablaghDarkhasti= jQuery.global.parseInt($('#mablagh_darkhasti').val().replace(new RegExp(",", "gm"), ""));
        var resultArzesh= jQuery.global.parseInt('${resultArzesh}'.replace(new RegExp(",", "gm"), ""));
        if(mablaghDarkhasti>resultArzesh)
        {
            alertMessage('مبلغ درخواستی مازاد بر سقف است.');
            $('#mablagh_darkhasti').val(jQuery.global.format(resultArzesh, "n0"))
        }
    }
</script>