<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table style="margin: 0 auto;">
    <tr><td>

    <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN,ROLE_KARMOZD_NAMAYANDE,ROLE_KARMOZD,ROLE_NAMAYANDE">
        <input type="button" class="noAnyDisable"
            <c:if test="${pishnehad.bimename.state.id!=540}">
               onclick=" window.open('/printMohasebateRiazi?pishnehadReport.pishnehad.id=${pishnehad.id}')"
            </c:if>
            <c:if test="${pishnehad.bimename.state.id==540}">
                onclick=" alertMessage('بیمه نامه ابطال شده است');"
            </c:if>
                       value="پرینت محاسبات ریاضی"  style="width: 250px;"/>
    </sec:authorize>

    </td></tr>
    <tr><td>
    <sec:authorize ifNotGranted="ROLE_KARMOZD_NAMAYANDE,ROLE_KARMOZD">
        <input type="button" class="noAnyDisable"
                       onclick="$('#soratVaziatBimename').show()"
                       value="پرینت صورت وضعیت بیمه نامه" style="width: 250px;"/>
        </sec:authorize>
    </td></tr>
    <tr><td>
    <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
        <input type="button" class="noAnyDisable"
                       onclick="window.open('/printSoratVaziateMali?pishnehadReport.pishnehad.id=${pishnehad.id}')"
                       value="پرینت صورت وضعیت مالی" style="width: 250px;"/>
    </sec:authorize>
    </td></tr>
    <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN,ROLE_NAMAYANDE,ROLE_BAZARYAB,ROLE_USER_KARTABL,ROLE_KARMOZD_NAMAYANDE,ROLE_KARMOZD">
    <tr><td>
        <input type="button" class="noAnyDisable"
                       onclick="window.open('/printBimename?pishnehadReport.pishnehad.id=${pishnehad.id}')"
                       value="پرینت بیمه نامه آزمایشی" style="width: 250px;"/>
    </td></tr>
    <tr><td>
        <input type="button" class="noAnyDisable"
                       onclick="window.open('/printBimenameHalateAvalie?pishnehadReport.pishnehad.id=${pishnehad.id}')"
                       value="پرینت بیمه نامه آزمایشی (حالت اوليه)" style="width: 250px;"/>
    </td></tr>
    <tr><td>
        <input type="button" class="noAnyDisable"
                        onclick="$('#serialPrint').show()"
                       value="پرینت بیمه نامه چاپی" style="width: 250px;"/>
    </td></tr>
    <tr><td>
        <input type="button" class="noAnyDisable"
                       onclick="window.open('/printZamimeBimename?pishnehadReport.pishnehad.id=${pishnehad.id}')"
                       value="پرینت ضمیمه بیمه نامه" style="width: 250px;"/>
    </td></tr>
    </sec:authorize>
    </table>

<%@include file="serialEbtalDialog.jsp"%>
<div id="serialPrint" style="display: none;margin-top: 50px">
    <form id="serialPrintForm" action="/printBimenameChappi">
        <table style="margin: 0 auto; width: 50%;">
            <tr>
                <td>
                    سریال :
                    <c:if test="${pishnehad.bimename.serialPrint==null}">
                        <input type="text"  class=" validate[required,custom[integer]]" id="serialStart" name="serialStart"/>
                    </c:if>
                    <c:if test="${pishnehad.bimename.serialPrint!=null}">
                        <c:if test="${pishnehad.bimename.serialPrint.vaziatSerial!=\"FAAL\"}">
                            <input type="text"  class="validate[required,custom[integer]]" id="serialStart" name="serialStart"/>
                        </c:if>
                        <c:if test="${pishnehad.bimename.serialPrint.vaziatSerial==\"FAAL\"}">
                            <input type="text"  class="validate[required,custom[integer]]" readonly="readonly"
                            value="${pishnehad.bimename.serialPrint.shomareSerial}" id="serialStart" name="serialStart"/>
                        </c:if>
                    </c:if>
                    <input type="hidden" name="pishnehadReport.pishnehad.id" value="${pishnehad.id}"/>
                    <c:if test="${pishnehad.bimename.serialPrint!=null}">
                        <c:if test="${pishnehad.bimename.serialPrint.vaziatSerial==\"FAAL\"}">
                            <input type="button" onclick="ebtalSerial(${pishnehad.bimename.serialPrint.shomareSerial});" value="ابطال سریال"/>
                        </c:if>
                    </c:if>
                </td>
            </tr>
        </table>
        <br/><br/>
    </form>
     <input type="button" value="چاپ بیمه نامه"onclick="go();" />
    <script type="text/javascript">
            function go()
            {
                if($.validationEngine.submitValidation($('#serialPrintForm')) === false)
                {
                    window.open('/printBimenameChappi?'+$('#serialPrintForm').serialize());
                }
            }
        </script>
    </div>

<div id="soratVaziatBimename" style="display: none;margin-top: 50px">
    <form id="soratVaziatForm">
        <table style="margin: 0 auto; width: 30%;">
            <tr>
                <td>
تاریخ مبنا:

                        <input type="text" class=" noAnyDisable datePkr validate[required, custom[date]]" id="tarikhMabnaSoratVaziat"
                               name="pishnehadReport.tarikhMabna" value="<%=DateUtil.getCurrentDate()%>"/>
                    <input type="hidden" name="pishnehadReport.pishnehad.id" value="${pishnehad.id}"/>
                </td>
            </tr>
        </table>
        <br/><br/>
    </form>
    <input type="button" value="چاپ صورت وضعیت بیمه نامه" onclick="goSoratVaziat();" class="noAnyDisable"/>
    <script type="text/javascript">
        function goSoratVaziat() {
            if ($.validationEngine.submitValidation($('#soratVaziatForm')) === false) {
                window.open('/printSoartVaziateBimeName?' + $('#soratVaziatForm').serialize());
            }
        }
    </script>
</div>