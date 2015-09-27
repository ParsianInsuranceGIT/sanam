<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.service.calculations.Reports.FRs" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" type="text/css" href="/js/menuSplit.css">
<script type="text/javascript" src="/js/menuSplit.js"></script>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: Feb 22, 2011
  Time: 2:59:03 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<input type="hidden" id="ghestAmount" value="${ghestAmount}"/>
<input type="hidden" id="pishAmount" value="${pishnehad.fishValue}"/>
<%
    boolean fishEQghest= (Boolean) request.getAttribute("fishEQghest");
    if(!fishEQghest)
   {
%>
    <input type="hidden" id="fishEQghest" value="yes"/>
    <%--<input type="hidden" id="ghestAmount" value="${ghestAmount}"/>--%>
    <%--<input type="hidden" id="pishAmount" value="${pishnehad.fishValue}"/>--%>

<%}
    boolean maghadireManfi = false;
    List<FRs> fRsList = (List<FRs>)request.getAttribute("fRsList");
    if(!((Boolean)request.getAttribute("correctCalculation"))){
%>
<p style="color:red;">
    بیمه نامه دارای خطای محاسباتی است.
</p>
<input type="hidden" id="hasError" value="yes"/>
<%
}else{
    for (FRs fRs : fRsList) {
        fRs.setSarmaayeFotBehHarEllat(Math.round(fRs.getSarmaayeFotBehHarEllat()));
        fRs.setSarmaayeFotDarAsarHaadeseh(Math.round(fRs.getSarmaayeFotDarAsarHaadeseh()));
        fRs.setSarmaayePusheshEzaafiAmraazKhaas(Math.round(fRs.getSarmaayePusheshEzaafiAmraazKhaas()));
        fRs.setSarmaayePusheshEzaafiNaghsOzv(Math.round(fRs.getSarmaayePusheshEzaafiNaghsOzv()));
        fRs.setHaghBimePardaakhtiSaal(Math.round(fRs.getHaghBimePardaakhtiSaal()));
        fRs.setMajmuHaghBimePardaakhtiSaal(Math.round(fRs.getMajmuHaghBimePardaakhtiSaal()));
        fRs.setHaghBimePusheshHaayeEzaafi(Math.round(fRs.getHaghBimePusheshHaayeEzaafi()));
        fRs.setMaaliyaatBarArzeshAfzude(Math.round(fRs.getMaaliyaatBarArzeshAfzude()));
        fRs.setMajmuKolMabaaleghPardaakhti(Math.round(fRs.getMajmuKolMabaaleghPardaakhti()));
        fRs.setArzeshBazKharidBaaSudTazmini15Darsad(Math.round(fRs.getArzeshBazKharidBaaSudTazmini15Darsad()));
        fRs.setPishbiniArzeshBazKharidBaaSud20Darsad(Math.round(fRs.getPishbiniArzeshBazKharidBaaSud20Darsad()));
        fRs.setPishbiniArzeshBazKharidBaaSud22Darsad(Math.round(fRs.getPishbiniArzeshBazKharidBaaSud22Darsad()));

        if(fRs.getSen() < 0) {maghadireManfi = true;break;}
        if(fRs.getSarmaayeFotBehHarEllat() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getSarmaayeFotDarAsarHaadeseh() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getSarmaayePusheshEzaafiAmraazKhaas() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getHaghBimePardaakhtiSaal() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getMajmuHaghBimePardaakhtiSaal() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getHaghBimePusheshHaayeEzaafi() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getMaaliyaatBarArzeshAfzude() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getMajmuKolMabaaleghPardaakhti() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getArzeshBazKharidBaaSudTazmini15Darsad() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getPishbiniArzeshBazKharidBaaSud20Darsad() < 0.0) {maghadireManfi = true;break;}
        if(fRs.getPishbiniArzeshBazKharidBaaSud22Darsad() < 0.0) {maghadireManfi = true;break;}
    }
    request.setAttribute("fRsListView", fRsList);

%>
<table class="jtable" cellpadding="0" cellspacing="0" style="text-align:center;">
    <thead>
    <tr>
        <th style="padding:0" class="ui-state-default" >سال</th>
        <th style="padding:0" class="ui-state-default">سن</th>
        <th style="padding:0" class="ui-state-default">سرمایه فوت به هر علت</th>
        <th style="padding:0" class="ui-state-default">سرمایه فوت در اثر حادثه</th>
        <th style="padding:0" class="ui-state-default">سرمایه پوشش اضافی امراض خاص</th>
        <th style="padding:0" class="ui-state-default">سرمایه نقص عضو</th>
        <th style="padding:0" class="ui-state-default">معافیت از کار افتادگی</th>
        <th style="padding:0" class="ui-state-default">حق بیمه پرداختی سال</th>
        <th style="padding:0" class="ui-state-default">مجموع حق بیمه های پرداختی سال</th>
        <th style="padding:0" class="ui-state-default">حق بیمه پوشش های اضافی</th>
        <%--<th style="padding:0" class="ui-state-default">مجموع حق بیمه های پوشش های اضافی</th>--%>
        <th style="padding:0" class="ui-state-default">مالیات بر ارزش افزوده</th>
        <th style="padding:0" class="ui-state-default">مجموع کل مبالغ پرداختی</th>
        <th style="padding:0" class="ui-state-default">ارزش بازخرید با سود تضمینی</th>
        <th style="padding:0" class="ui-state-default">پیش بینی ارزش بازخرید با نرخ بازدهی 20%</th>
        <th style="padding:0" class="ui-state-default">پیش بینی ارزش بازخرید با نرخ بازدهی 25%</th>
        <th style="padding:0" class="ui-state-default">تاریخ تقسیط</th>
        <th style="padding:0" class="ui-state-default">وضعیت قسط بندی</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="row" items="${fRsList}">
        <tr>
            <td class="ui-widget-content">${row.saal}</td>
            <td class="ui-widget-content">${row.sen}</td>
            <td class="ui-widget-content"><fmt:formatNumber type="currency" pattern="#,##0">${row.sarmaayeFotBehHarEllat}</fmt:formatNumber></td>
            <td class="ui-widget-content"><fmt:formatNumber type="currency" pattern="#,##0">${row.sarmaayeFotDarAsarHaadeseh}</fmt:formatNumber></td>
            <td class="ui-widget-content"><fmt:formatNumber type="currency" pattern="#,##0">${row.sarmaayePusheshEzaafiAmraazKhaas}</fmt:formatNumber></td>
            <td class="ui-widget-content"><fmt:formatNumber type="currency" pattern="#,##0">${row.sarmaayePusheshEzaafiNaghsOzv}</fmt:formatNumber></td>
            <td class="ui-widget-content">${row.poosheshMoafiat}</td>
            <td class="ui-widget-content"><fmt:formatNumber type="currency" pattern="#,##0">${row.haghBimePardaakhtiSaal}</fmt:formatNumber></td>
            <td class="ui-widget-content"><fmt:formatNumber type="currency" pattern="#,##0">${row.majmuHaghBimePardaakhtiSaal}</fmt:formatNumber></td>
            <td class="ui-widget-content"><fmt:formatNumber type="currency" pattern="#,##0">${row.haghBimePusheshHaayeEzaafi}</fmt:formatNumber></td>
                <%--<td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value" value="reportResult.majmuHaghBimePusheshHaayeEzaafiRound()[#stat.index]"/></s:text></td>--%>
            <td class="ui-widget-content"><fmt:formatNumber type="currency" pattern="#,##0">${row.maaliyaatBarArzeshAfzude}</fmt:formatNumber></td>
            <td class="ui-widget-content"><fmt:formatNumber type="currency" pattern="#,##0">${row.majmuKolMabaaleghPardaakhti}</fmt:formatNumber></td>
            <td class="ui-widget-content"><fmt:formatNumber type="currency" pattern="#,##0">${row.arzeshBazKharidBaaSudTazmini15Darsad}</fmt:formatNumber></td>
            <td class="ui-widget-content"><fmt:formatNumber type="currency" pattern="#,##0">${row.pishbiniArzeshBazKharidBaaSud20Darsad}</fmt:formatNumber></td>
            <td class="ui-widget-content"><fmt:formatNumber type="currency" pattern="#,##0">${row.pishbiniArzeshBazKharidBaaSud22Darsad}</fmt:formatNumber></td>
            <c:set var="ghestBandiShode" value="false"/>
            <c:forEach var="ghestBandi" items="${pishnehad.bimename.ghestBandiList}" varStatus="i">
                <c:if test="${(row.saal-1) == ghestBandi.saleBimei}">
                    <c:set var="ghestBandiShode" value="true"/>
                    <td class="ui-widget-content">
                        ${ghestBandi.tarikheTaghsit}
                    </td>
                    <td class="ui-widget-content" >
                        <div>
                            <%--<input type="button" class="noAnyDisable" value="نمایش" onclick="namayesh(${row.saal - 1})" style="float:right;">--%>
                                <%--<sec:authorize ifAnyGranted="ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_SODUR,ROLE_JAMI_KHAS,ROLE_SUPERVISOR">--%>
                            <%--<c:if test="${pishnehad.bimename.state.id!=540 && pishnehad.bimename.state.id!=550}">--%>
                                <%--<input type="button" class="noAnyDisable" value="حذف تقسیط" onclick="hazfeTaghsit(${row.saal - 1})" style="float:right;margin-right:2px;">--%>
                            <%--</c:if>--%>
                            <%--<input type="button" class="noAnyDisable" value="پرینت پارسیان" onclick="window.open('/printeAghsat_parsian.action?pishnehadReport.ghestBandi.id=${ghestBandi.id}');" style="float:right;margin-right:2px;">--%>
                            <%--<input type="button" class="noAnyDisable" value="پرینت تجارت" onclick="window.open('/printeAghsat_tejarat.action?pishnehadReport.ghestBandi.id=${ghestBandi.id}');" style="float:right;margin-right:2px;">--%>
                                <div>

                                    <a
                                            <sec:authorize ifNotGranted="ROLE_NAMAYANDE,ROLE_LIMITED_AMIN_PARSIAN">
                                                onclick="namayesh(${row.saal - 1})"
                                            </sec:authorize>
                                            <sec:authorize ifAnyGranted="ROLE_NAMAYANDE,ROLE_LIMITED_AMIN_PARSIAN">
                                                onclick="viewLogs(${ghestBandi.id});"
                                            </sec:authorize>
                                                style="height:22px;text-emphasis-color: #2E6E9E; background: #eaf2ff;border: 1px solid #b7d2ff;filter: none;"
                                       class="easyui-splitbutton noAnyDisable" menu="#mm${i.index}" iconCls="icon-edit">
                                        <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN,ROLE_NAMAYANDE">
                                        <font color="#2E6E9E">نمایش</font>
                                        </sec:authorize>
                                        <sec:authorize ifAnyGranted="ROLE_LIMITED_AMIN_PARSIAN,ROLE_NAMAYANDE">
                                            <font color="#2E6E9E">سوابق</font>
                                        </sec:authorize>
                                    </a>

                                </div>
                                <div id="mm${i.index}" style="width:3px;" class="noAnyDisable">
                                <c:if test="${pishnehad.bimename.state.id!=540 && pishnehad.bimename.state.id!=550&& pishnehad.bimename.state.id!=560}">
                                    <%--<div class="noAnyDisable" onclick="hazfeTaghsit(${ghestBandi.id})">--%>
                                        <%--<a class="noAnyDisable" style="cursor:pointer">حذف تقسیط</a>--%>
                                    <%--</div>--%>
                                    <div class="menu-sep"></div>
                                </c:if>
                                <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN,ROLE_NAMAYANDE">
                                    <div onclick="viewLogs(${ghestBandi.id});" class="noAnyDisable">
                                        <a class="noAnyDisable" style="cursor:pointer">سوابق دفترچه</a>
                                    </div>
                                    <div class="menu-sep"></div>
                                </sec:authorize>
                                    <div>
                                        <span>دفترچه پارسیان</span>
                                        <div>
                                            <div class="noAnyDisable" onclick="window.open('/printeAghsat_parsian.action?genreDaftarche=VIEW&pishnehadReport.ghestBandi.id=${ghestBandi.id}');">
                                                <a class="noAnyDisable" style="cursor:pointer">مشاهده</a>
                                            </div>
                                            <div class="noAnyDisable" onclick="window.open('/printeAghsat_parsian.action?genreDaftarche=PRINT&pishnehadReport.ghestBandi.id=${ghestBandi.id}');">
                                                <a class="noAnyDisable" style="cursor:pointer">چاپ</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <span>دفترچه تجارت</span>
                                        <div>
                                            <div class="noAnyDisable" onclick="window.open('/printeAghsat_tejarat.action?genreDaftarche=VIEW&pishnehadReport.ghestBandi.id=${ghestBandi.id}');">
                                                <a class="noAnyDisable" style="cursor:pointer">مشاهده</a>
                                            </div>
                                            <div class="noAnyDisable" onclick="window.open('/printeAghsat_tejarat.action?genreDaftarche=PRINT&pishnehadReport.ghestBandi.id=${ghestBandi.id}');">
                                                <a class="noAnyDisable" style="cursor:pointer">چاپ</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--</sec:authorize>--%>
                            <%--<input type="button" value="سوابق دفترچه" onclick="viewLogs(${ghestBandi.id});"/>--%>
                        </div>
                    </td>
                </c:if>
            </c:forEach>
            <c:if test="${ghestBandiShode == false}">
                <sec:authorize ifAnyGranted="ROLE_RAEIS_KHESARAT,ROLE_KARSHENAS_MASOUL_KHESARAT,ROLE_KARSHENAS_KHESARAT,ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_SODUR,ROLE_JAMI_KHAS,ROLE_SUPERVISOR,ROLE_PAS_KARSHENAS_MASOUL,ROLE_PAS_KARSHENAS,ROLE_TAGHSIT">
                    <td class="ui-widget-content">
                        تقسیط نشده
                    </td>
                    <td class="ui-widget-content">
                        <c:if test="${pishnehad.state.id != 245 && pishnehad.bimename.state.id!=540 && pishnehad.bimename.state.id!=550&& pishnehad.bimename.state.id!=560}">
                            <input type="button" class="noAnyDisable" value="تقسیط" onclick="anjameTaghsit(${row.saal - 1})" style="width: 80px;">
                        </c:if>
                    </td>
                </sec:authorize>
            </c:if>

        </tr>
    </c:forEach>
    <div id="rslt" style="display: none;">
    </div>
    </tbody>
</table>
<script type="text/javascript">
    function viewLogs(gbId)
    {

        $('#rslt').dialog({
            modal: true,
            width: 550,
            resizable: false,
            closeText: "",
            title: "سوابق دفترچه",
            buttons:
            {
                "بستن": function (){$(this).dialog("close");}
            }
        });
        $.post('/logsDaftarche.action?ghestBandiId='+gbId,function (response){$('#rslt').html(response);});
    }

    function hazfeTaghsit(id){
        confirmMessage("آيا از حذف اين تقسيط مطمئن هستيد؟", "", function(){
            $.post("/hazfeTaghsit.action?pishnehad.id=${pishnehad.id}&gb.id="+id,function(msg){
                $('#div_showVaziateGhestBandi').html(msg);
                $("#div_showVaziateGhestBandi input").each(function(){
                    $(this).addClass("ui-state-default  ui-corner-all ui-helper-clearfix");
                });
            });
        })
    }
    function anjameTaghsit(id){
        $.post("/anjameTaghsit.action?pishnehad.id=${pishnehad.id}&saleBimei="+id,function(msg){
            $('#div_showVaziateGhestBandi').html(msg);
            $("#div_showVaziateGhestBandi input").each(function(){
                $(this).addClass("ui-state-default  ui-corner-all ui-helper-clearfix");
            });
        });
    }
    function namayesh(id){
        $.post("/anjameTaghsit.action?pishnehad.id=${pishnehad.id}&showTaghsit=yes&saleBimei="+id,function(msg){
            $('#div_showVaziateGhestBandi').html(msg);
            $("#div_showVaziateGhestBandi input").each(function(){
                $(this).addClass("ui-state-default  ui-corner-all ui-helper-clearfix");
            });
        });
    }
</script>
<%

    }
%>