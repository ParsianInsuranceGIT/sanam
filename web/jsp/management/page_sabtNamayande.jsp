<%@ page import="com.bitarts.parsian.model.Namayande" %>
<%--
  Created by IntelliJ IDEA.
  User: ramtinb
  Date: 4/10/12
  Time: 10:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <title>ثبت نماینده</title>
</head>
<body>

<c:if test="${namayande.id == null}">
<form class="mainFrame" name="mainForm" method="post" action="/addNamayande.action">
    </c:if>
    <c:if test="${namayande.id != null}">
    <form class="mainFrame" name="mainForm" method="post" action="/editNamayande.action?namayande.id=${namayande.id}">
        </c:if>

        <jsp:include page="/jsp/josteju/searchCity.jsp"></jsp:include>
        <table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0"
               style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
            <thead>
            <tr>
                <th colspan="2">
                    ثبت نماینده
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>نام</td>
                <td><input type="text" name="namayande.name" value="${namayande.name}" id="namayande_name"
                           class="validate[required]"/></td>
            </tr>
            <tr>
                <td>کد نماینده کارگزار</td>
                <td><input type="text" name="namayande.kodeNamayandeKargozar" value="${namayande.kodeNamayandeKargozar}"
                           id="namayande_kodeNamayandeKargozar"
                           class="validate[required]"/></td>
            </tr>
            <tr>
                <script type="text/javascript">
                    function issuanceCodeValidation()
                    {
                        if($("#issuance_code").val()!=null)
                        {
                            $.post("/ajaxlyValidationInsertNamayande.action?namayande.issuanceCode="+$("#issuance_code").val(),function(msg)
                                                                                                                              {
//                                                                                                                                  window.alert(msg.indexOf("msgRepetitiousIssuanceCode")!= -1);
                                                                                                                                  if(msg.indexOf("msgRepetitiousIssuanceCode") != -1)
                                                                                                                                  {
                                                                                                                                      window.alertMessage("این کد قبلاً ثبت شده است.");
                                                                                                                                      $("#issuance_code").val('');
                                                                                                                                  }
                                                                                                                              });
                        }
                    }
                </script>
                <td>Issuance code </td>
                <td><input type="text" name="namayande.issuanceCode" id="issuance_code" value="${namayande.issuanceCode}" onchange="issuanceCodeValidation();" class="validate[custom[Integer]]" maxlength="4"/> </td>
            </tr>
            <tr>
                <td>واحد سرپرستی</td>
                <td>
                    <span class="help ui-icon ui-icon-search"
                          onclick="fillNamayandegi('sarparast_id','sarparast_name','namayande_expDate');"
                          style="float:left;" title="جستجو"></span>
                    <input type=text value="${namayande.sarparast.name}" name="namayande.sarparast.name" id="sarparast_name" readonly="readonly"
                           class="validate[required]"/>
                    <input type=hidden name="namayande.sarparast.id" value="${namayande.sarparast.id}" id="sarparast_id"/>
                </td>
            </tr>
            <tr>
                <td>نمایندگی پدر</td>
                <td>
                    <span class="help ui-icon ui-icon-search"
                          onclick="fillNamayandegi('parent_id','parent_name','namayande_expDate');"
                          style="float:left;" title="جستجو"></span>
                    <input type=text value="${namayande.parent.name}" name="namayande.parent.name"
                           id="parent_name" readonly="readonly"/>
                    <input type=hidden name="namayande.parent.id" value="${namayande.parent.id}"
                           id="parent_id"/>
                </td>
            </tr>
            <tr>
                <td>آدرس</td>
                <td><textarea name="namayande.address" id="namayande_address" class="validate[required]" cols="40"
                              rows="2">${namayande.address}</textarea></td>
            </tr>
            <tr>
                <td>تلفن / موبایل</td>
                <td><input type="text" name="namayande.telephone" value="${namayande.telephone}"
                           id="namayande_telephone"
                           class="validate[required,custom[onlyNumber]]"/></td>
            </tr>
            <tr>
                <td>نوع</td>
                <td>
                    <s:select list="#{
                                      'ICD':'ICD',
                                      'NAMAYANDE_HAGHIGHI':'نماینده حقیقی',
                                      'NAMAYANDE_HOGHUGHI':'نماینده حقوقی',
                                      'BAJE_NAMAYANDE_HOGHUGHI':'باجه نماینده حقوقی',
                                      'FORUSHANDE':'نماینده فروشنده',
                                      'KARGOZAR_HOGHUGHI':'کارگزار حقوقی',
                                      'KARGOZAR_HAGHIGHI':'کارگزار حقیقی',
                                      'MOJTAMA':'مجتمع بیمه ای',
                                      'SHOBE':'شعبه'
                                     }"
                              key="namayande.namayandeType" id="namayande.type" label="" theme="simple"/>
                </td>
            </tr>
            <tr>
                <td>زیر مجموعه</td>
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('namayande_sodur_id','namayande_sodur','namayande_expDate');" style="float:left;" title="جستجو"></span>
                    <input type=text value="${namayande.vahedSodur.name}" id="namayande_sodur" readonly="readonly" class="validate[required]"/>
                    <input type=hidden name="viSodurId" value="${namayande.vahedSodur.id}" id="namayande_sodur_id"/>
                </td>
            </tr>
            <tr>
                <td>تاریخ انقضا</td>
                <td><input type="text" name="namayande.expDate" value="${namayande.expDate}" id="namayande_expDate"
                           class="datePkr"/></td>
            </tr>
            <tr>
                <td>شهر ‫-‬ استان</td>
                <td>
                    <input type="text" value="${namayande.shahr.cityName}"
                           id="namayande_shahar_cityName"
                           readonly="readonly" class="validate[required]"/>
                    <input type="text" value="${namayande.ostan.cityName}"
                           id="namayande_ostan_cityName"
                           readonly="readonly" class="validate[required]"/>

                    <input type="hidden" name="namayande.shahr.cityId" value="${namayande.shahr.cityId}"
                           id="namayande_shahr_cityId"/>
                    <input type="hidden" name="namayande.ostan.cityId" value="${namayande.ostan.cityId}"
                           id="namayande_ostan_cityId"/>
                    <span class="help ui-icon ui-icon-search" onclick="fillShahrOstan('namayande_shahr_cityId','namayande_shahar_cityName','namayande_ostan_cityId','namayande_ostan_cityName','btnSubmit')" style="float:left;" title="جستجو"></span>
                </td>
            </tr>
            <tr>
                <td>مهلت سند زدن (روز)</td>
                <td>
                    <input type="text" value="${namayande.mohlateSanadzadan}" name="namayande.mohlateSanadzadan"  id="namayande_mohlat_sanadzadan" class=""/>
                </td>
            </tr>
            <tr>
                <td>مقدار اعتبار مجاز وصول نشده</td>
                <td>
                    <input type="text" value="${namayande.etebarMojazVosolNashodAmount}" name="namayande.etebarMojazVosolNashodAmount"  id="etebarMojazVosolNashodAmount" class=""/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="dblRadio">
                        <input type="checkbox" name="namayande.pishnehadCrtAccess" id="access_pish" style="margin-left: 80px;"
                               <c:if test="${namayande==null || namayande.pishnehadCrtAccess}">checked="checked"</c:if>
                               value="true"/>
                        <label style="margin-left: 80px;" for="access_pish">دسترسی ایجاد
                            پیشنهاد</label>
                        <input type="checkbox" name="namayande.codeCanceled" id="code_canceled"
                               <c:if test="${namayande.codeCanceled}">checked="checked"</c:if>
                               value="true"/>
                        <label for="code_canceled">لغو کد</label>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" id="btnSubmit" value="ثبت"/>
                    <input type="button" value="بازگشت"
                           onclick="window.location='/listAllNamayande.action'"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
</body>
</html>