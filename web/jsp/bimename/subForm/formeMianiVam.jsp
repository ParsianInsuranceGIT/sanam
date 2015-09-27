<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.service.MohasebateFaniVam" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="/struts-tags" prefix="st" %>
<st:actionerror/>

<script type="text/javascript">
    <c:if test="${darkhastBazkharid.state.id==10090 && avalinTarikhGhestInvalid}">
//        alertMessage("حداقل سررسید اولین قسط می باید یک ماه بعد از تاریخ روز باشد.");
    alertMessage("تاریخ اولین قسط وام اشتباه است.");
    </c:if>
</script>
<form method="post" action="/calcVam">
<input type="hidden" name="bimename.id" value="<c:out value='${bimename.id}'/>"/>
<input type="hidden" name="darkhastBazkharid.id" value="<c:out value='${darkhastBazkharid.id}'/>"/>

<input type="hidden" id="NafareEmza"/>
<div id="tblEmza" style="display: none">
    <table cellpadding="3" cellspacing="3" border="0"
           style="margin-left:auto;margin-right:auto;align:center;direction: rtl;">
        <tr>
            <td colspan="5">جستحو شخص امضا کننده</td>
        </tr>
        <tr>
            <td>کد پرسنلی</td>
            <td><input type="text" id="emzaPersonalCode"/></td>
            <td>نام</td>
            <td><input type="text" id="emzaName"/></td>
            <td><input type="button" value="جستجو" id="btnSearch"
                       onclick="searchEmaz()"></td>
        </tr>
    </table>
    <div id="searchResualt"></div>
</div>

<div id="moshakhasat">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        مشخصات
    </p>
    <table class=inputList border="0" cellspacing="5" cellpadding="1" style="width:99%">
        <col class=inputCol>
        <col class=inputCol>
        <tr>
            <td>
                <span class="noThing"></span>
                <input type=text name="" id="shomarePishnehadeVam" value='${darkhastBazkharid.id}'
                       class="validate[required]" disabled="disabled"/>
                &nbsp;<label>شماره پیشنهاد وام</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type=text name="" id="shomareDarkhasteVam" value='${darkhastBazkharid.shomareDarkhast}'
                       class="validate[required]" disabled="disabled"/>
                &nbsp;<label>شماره درخواست وام</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <input type=text name="" id="tarikheDarkhast" value='${darkhastBazkharid.tarikhDarkhast}'
                       class="validate[required]" disabled="disabled"/>
                &nbsp;<label>تاریخ درخواست</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type=text disabled="disabled" name="" id="mahalleSodor"
                       value='<st:property value="user.mojtamaSodoor.name"/>'
                       class="validate[required]" disabled="disabled"/>
                &nbsp;<label>محل صدور</label>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <span class="noThing"></span>
                <input type=text name="" id="tarikheSodor" value='<%=DateUtil.getCurrentDate()%>'
                       class="validate[required]" disabled="disabled"/>
                &nbsp;<label>تاریخ صدور</label>
            </td>
        </tr>
    </table>
</div>
<div id="bimename">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        بیمه نامه
    </p>
    <table class=inputList border="0" cellspacing="5" cellpadding="1" style="width:99%">
        <col class=inputCol>
        <col class=inputCol>
        <tr>
            <td>
                <span class="noThing"></span>
                <input type=text name="" id="shomareBimename" value='${bimename.shomare}' class="validate[required]"
                       disabled="disabled"/>
                &nbsp;<label>شماره بیمه نامه</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type=text name="" id="tarikheSodoreBimename" value='${bimename.tarikhSodour}'
                       class="validate[required]" disabled="disabled"/>
                &nbsp;<label>تاریخ صدور</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <input type=text name="" id="tarikheShoro" value='${bimename.tarikhShorou}' class="validate[required]"
                       disabled="disabled"/>
                &nbsp;<label>تاریخ شروع</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type=text name="" id="tarikheEngheza" value='${bimename.tarikhEngheza}'
                       class="validate[required]" disabled="disabled"/>
                &nbsp;<label>تاریخ انقضا</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <input type=text name="" id="modateBime" value='${bimename.pishnehad.estelam.modat_bimename}'
                       class="validate[required]" disabled="disabled"/>
                &nbsp;<label>مدت بیمه</label>
            </td>
            <td>
            </td>
        </tr>
    </table>
</div>
<div id="mohasebe">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        محاسبه
    </p>
    <table class=inputList border="0" cellspacing="5" cellpadding="1" style="width:99%">
        <col class=inputCol>
        <col class=inputCol>
        <tr>
            <td>
                <span class="noThing"></span>
                <input type=text name="" id="arzesheBazkharid" value='<fmt:formatNumber type="currency" pattern="#,##0">${bimename.arzeshBazkharidGhatie}</fmt:formatNumber>'
                       class="validate[required]" disabled="disabled"/>
                &nbsp;<label>ارزش بازخرید</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type=text name="" id="mablagheHadaksarVam" value='${hadeAksarVam}' class="validate[required]"
                       disabled="disabled"/>
                &nbsp;<label>مبلغ حداکثر وام</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <input type=text name="" id="tarikheMohasebe" value='<%=DateUtil.getCurrentDate()%>'
                       class="validate[required]" disabled="disabled"/>
                &nbsp;<label>تاریخ محاسبه</label>
            </td>
            <td>
            </td>
        </tr>
    </table>
</div>
<div id="sharayet">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        شرایط
    </p>
    <table class=inputList border="0" cellspacing="5" cellpadding="1" style="width:99%">
        <col class=inputCol>
        <col class=inputCol>
        <tr>
            <td>
                <span class="noThing"></span>
                <input type=text name="darkhastBazkharid.mablagheVamedarkhasti" <c:if test="${darkhastBazkharid.state.id>=10100}">disabled="disabled"</c:if>
                       <%--value='${hadeAksarVam}'--%>
                       value="<st:property value="darkhastBazkharid.mablagheVamedarkhasti"/>"
                       id="mablagheVamedarkhasti"
                      class="validate[required]"/>
                &nbsp;<label>مبلغ وام درخواستی</label>
            </td>
            <td>
                <span class="noThing"></span>
                <select name="darkhastBazkharid.nahveyePardakhteAghsat" id="nahveyePardakhteAghsat"
                        <c:if test="${darkhastBazkharid.state.id>=10100}">disabled="disabled"</c:if>>
                    <option value="<%=MohasebateFaniVam.BazPardakhtType.MAHANE%>" ${darkhastBazkharid.nahveyePardakhteAghsat == 'MAHANE'? "selected='selected'":''} >
                        ماهانه
                    </option>
                    <option value="<%=MohasebateFaniVam.BazPardakhtType.SE_MAHE%>" ${darkhastBazkharid.nahveyePardakhteAghsat == 'SE_MAHE'? "selected='selected'":''}>
                        سه ماهه
                    </option>
                </select>
                &nbsp;<label>نحوه پرداخت اقساط</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <%--<input type=text name="darkhastBazkharid.modatebazpardakht"--%>
                       <%--value="${darkhastBazkharid.modatebazpardakht}" id="modatebazpardakht" value=''--%>
                       <%--class="validate[required]"/>--%>
                <select id="modatebazpardakht" name="darkhastBazkharid.modatebazpardakht"
                        <c:if test="${darkhastBazkharid.state.id>=10100}">disabled="disabled"</c:if>>
                    <option value="48" <c:if test="${darkhastBazkharid.modatebazpardakht==48}">selected</c:if>>48</option>
                    <option value="42" <c:if test="${darkhastBazkharid!=null&&darkhastBazkharid.modatebazpardakht!=null&&darkhastBazkharid.modatebazpardakht==42}">selected</c:if>>42</option>
                    <option value="36" <c:if test="${darkhastBazkharid!=null&&darkhastBazkharid.modatebazpardakht!=null&&darkhastBazkharid.modatebazpardakht==36}">selected</c:if>>36</option>
                    <option value="30" <c:if test="${darkhastBazkharid!=null&&darkhastBazkharid.modatebazpardakht!=null&&darkhastBazkharid.modatebazpardakht==30}">selected</c:if>>30</option>
                    <option value="24" <c:if test="${darkhastBazkharid!=null&&darkhastBazkharid.modatebazpardakht!=null&&darkhastBazkharid.modatebazpardakht==24}">selected</c:if>>24</option>
                    <option value="18" <c:if test="${darkhastBazkharid!=null&&darkhastBazkharid.modatebazpardakht!=null&&darkhastBazkharid.modatebazpardakht==18}">selected</c:if>>18</option>
                    <option value="12" <c:if test="${darkhastBazkharid!=null&&darkhastBazkharid.modatebazpardakht!=null&&darkhastBazkharid.modatebazpardakht==12}">selected</c:if>>12</option>
                    <option value="6"  <c:if test="${darkhastBazkharid!=null&&darkhastBazkharid.modatebazpardakht!=null&&darkhastBazkharid.modatebazpardakht==6}">selected</c:if>>6</option>
                </select>
                &nbsp;<label>مدت بازپرداخت</label>
            </td>
            <td>
                <%--<span class="noThing"></span>--%>
                <input type=text name="darkhastBazkharid.tarikheAvalinGhest" value="${darkhastBazkharid.tarikheAvalinGhest}" id="tarikheAvalinGhest" class="datePkr validate[custom[date]]"
                       <c:if test="${darkhastBazkharid.state.id>=10100}">disabled="disabled"</c:if>/>
                &nbsp;<label>تاریخ اولین قسط</label>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <span class="noThing"></span>
                <select name="" id="bankePardakhteAghsat"
                        <c:if test="${darkhastBazkharid.state.id>=10100}">disabled="disabled"</c:if>>
                    <option>پارسیان</option>
                    <option>تجارت</option>
                </select>
                &nbsp;<label>بانک پرداخت اقساط</label>
            </td>
        </tr>
    </table>
</div>

<div id="emza">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        امضا
    </p>
    <table class=inputList border="0" cellspacing="5" cellpadding="1" style="width:99%">
        <col class=inputCol>
        <col class=inputCol>
        <tr>
            <td>
                <span class="noThing"></span>
                <input type="text" readonly="readonly" id="bimename_emzaKonandeAval" style="width:100px" class="validate[required]"
                       value="${darkhastBazkharid.emzaKonandeAval.user.fullName}"/>
                <input type="hidden" name="darkhastBazkharid.emzaKonandeAval.id" id="bimename_emzaKonandeAval_id"
                       value="${darkhastBazkharid.emzaKonandeAval.id}"/>
                <input type="button" value="جستجو"
                       onclick="$('#NafareEmza').val('#bimename_emzaKonandeAval');fillEmze();"
                       <c:if test="${darkhastBazkharid.state.id>=10100}">disabled="disabled"</c:if>
                        />
                &nbsp;<label>امضا کننده اول</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" readonly="readonly" id="bimename_emzaKonandeDovom" style="width:100px" class="validate[required]"
                       value="${darkhastBazkharid.emzaKonandeDovom.user.fullName}"/>
                <input type="hidden" name="darkhastBazkharid.emzaKonandeDovom.id" id="bimename_emzaKonandeDovom_id"
                       value="${darkhastBazkharid.emzaKonandeDovom.id}"/>
                <input type="button" value="جستجو"
                       <c:if test="${darkhastBazkharid.state.id>=10100}">disabled="disabled"</c:if>
                       onclick="$('#NafareEmza').val('#bimename_emzaKonandeDovom');fillEmze(); "/>
                &nbsp;<label>امضا کننده دوم</label>
            </td>
        </tr>
    </table>
</div>
<c:if test="${darkhastBazkharid.ghestBandi.id != null}">

    <div id="bazPardakht">
        <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
            بازپرداخت
        </p>
        <table class=inputList border="0" cellspacing="5" cellpadding="1" style="width:99%">
            <col class=inputCol>
            <col class=inputCol>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type=text name="" id="mablagheGhesteVam" value='${mablaghGhestVam}' disabled="disabled"/>
                    &nbsp;<label>مبلغ قسط وام</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type=text name="" id="maliatbarArzesheAfzodeh" value='${maliatBarArzeshAfzude}'
                           disabled="disabled"/>
                    &nbsp;<label>مالیات بر ارزش افزوده</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type=text name="" id="jameKolleAghsateVam" value='${jamKolAghsatVam}' disabled="disabled"/>
                    &nbsp;<label>جمع کل اقساط وام</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type=text name="" id="bahreyepardakhti" value='${bahrePardakhti}' disabled="disabled"/>
                    &nbsp;<label>بهره پرداختی</label>
                </td>
            </tr>
        </table>
    </div>
    <div id="ghest">
        <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
            اقساط تولید شده
        </p>
        <table class=jtable border="0" cellspacing="1" cellpadding="1" style="width:99%">
            <tr>
                <th style="padding:5" class="ui-state-default">شماره قسط</th>
                <th style="padding:5" class="ui-state-default">تاریخ سررسید</th>
                <th style="padding:5" class="ui-state-default">مقدار قسط</th>
                <th style="padding:5" class="ui-state-default">مبلغ بهره پرداختی</th>
                <th style="padding:5" class="ui-state-default">مبلغ پرداختی از اصل وام</th>
                <th style="padding:5" class="ui-state-default">شماره/شناسه مشتری</th>
            </tr>
            <c:forEach var="row" items="${darkhastBazkharid.ghestBandi.ghestList}" varStatus="ind">
                <tr>
                    <td width="7%" style="padding:5px 5px" class="ui-widget-content">${row.shomareGhest}</td>
                    <td width="7%" style="padding:5px 5px" class="ui-widget-content">${row.sarresidDate}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.credebit.amount}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.hazineKarmonz}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.hazineVosoul}</td>
                    <td style="padding:5px 5px"
                        class="ui-widget-content">${row.credebit.shenaseCredebit}/${row.credebit.shomareMoshtari}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</c:if>
<div style="height:30px">

<c:if test="${darkhastBazkharid.state.id == 10090}">
    <input type="button" style="float:left;margin-left:2px;" onclick="submitTransitionForDarkhast();" value="صدور وام"/>
</c:if>
<c:if test="${darkhastBazkharid.state.id == 10080 || darkhastBazkharid.state.id == 10090}">
    <input type="submit" style="float:left;margin-left:2px;" value="قسط بندی وام">
</c:if>
    <c:if test="${darkhastBazkharid.ghestBandi.id == null}">
        <input type="button" onclick="alertMessage('تقسیط انجام نشده است.')" style="float:left;margin-left:2px;"
               value="چاپ دفترچه اقساط وام">
    </c:if>
    <c:if test="${darkhastBazkharid.ghestBandi.id != null}">
        <c:if test="${darkhastBazkharid.state.id>=10100}">
            <st:url action="printDaftarcheGhestVamParsian" id="printDaftarcheParsian">
                <st:param name="darkhastBazkharid.id" value="darkhastBazkharid.id"/>
            </st:url>
            <st:url action="printDaftarcheGhestVamTejarat" id="printDaftarcheTejarat">
                <st:param name="darkhastBazkharid.id" value="darkhastBazkharid.id"/>
            </st:url>
            <input type="button" onclick="window.open('${printDaftarcheParsian}');" style="float:left;margin-left:2px;" class="noAnyDisable"
                   value="چاپ دفترچه اقساط وام پارسیان">
            <input type="button" onclick="window.open('${printDaftarcheTejarat}');" style="float:left;margin-left:2px;" class="noAnyDisable"
                   value="چاپ دفترچه اقساط وام تجارت">
        </c:if>
        <st:url action="printSuratVaziatGhestVam" id="printSuratVaziat">
            <st:param name="darkhastBazkharid.id" value="darkhastBazkharid.id"/>
        </st:url>
        <input type="button" onclick="window.open('${printSuratVaziat}');" style="float:left;margin-left:2px;"
               class="noAnyDisable"
               value="صورت وضعیت اقساط وام">
    </c:if>
    <input type="button" class="noAnyDisable"
           onclick="window.open('/printeSharayeteAkhzeVam?pishnehadReport.bimename.id=${bimename.id}');"
           style="float:left;margin-left:2px;" value="چاپ شرایط اخذ وام">
    <%--<input type="button" onclick="" style="float:left;margin-left:2px;" value="صدور وام">--%>

</div>
</form>

<script language="JavaScript">
    function searchEmaz() {
        var emzaName = $('#emzaName').val();
        var emzaPersonalCode = $('#emzaPersonalCode').val();
        $.post("/findEmzaKonande?user.firstName=" + emzaName + "&user.personalCode=" + emzaPersonalCode, function (msg) {
            $('#searchResualt').html(msg);
        });
        fillEmze();
    }
    $(document).ready(function () {
        $('#emzaName').val('');
        $('#emzaPersonalCode').val('');
        $('#tblEmza').dialog({
            modal: true, resizable: false, autoOpen: false,
            width: 700, maxHeight: 500, minHeight: 100,
            title: "جستحو شخص امضا کننده"});
    });
    function fillEmze() {
        $('#tblEmza').dialog('open');
    }
    function selectRow(id, fn, ln) {
        var ctrlId = $('#NafareEmza').val();
        $(ctrlId).val(fn + " " + ln);
        $(ctrlId + '_id').val(id);
        hideEmzaModal();
    }
    function hideEmzaModal() {
        $('#emzaName').val('');
        $('#emzaPersonalCode').val('');
        $('#tblEmza').dialog('close');
        $('#searchResualt').html('');
    }
</script>