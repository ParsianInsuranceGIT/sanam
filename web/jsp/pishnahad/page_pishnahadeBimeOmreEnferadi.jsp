<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page import="com.bitarts.parsian.model.Transition" %>
<%@ page import="com.bitarts.parsian.model.asnadeSodor.GhestBandi" %>
<%@ page import="com.bitarts.parsian.model.karmozd.KarmozdGhest" %>
<%@ page import="com.bitarts.parsian.service.calculations.KarmozdCalculate" %>

<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: Feb 16, 2011
  Time: 4:01:02 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/taglibs.jsp" %>
<%
    Pishnehad pishnehadRun = (Pishnehad) request.getAttribute("pishnehad");
%>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="../../css/pishnahadeBimeOmreEnferadi.css"/>
    <script type="text/javascript" src="/jsp/pishnahad/pishnahadeBimeOmreEnferadi.js"></script>
    <%--<link rel="stylesheet" type="text/css" href="/js/menuSplit.css">--%>
    <c:if test="${readOnlyMode || (pishnehad!=null && pishnehad.id!=null && pishnehad.state!=null && pishnehad.state.id==270)}">
        <script type="text/javascript" src="/js/menuSplit.js"></script>
    </c:if>
    <title>سیستم پیشنهاد</title>
</head>
<body>

<div id="result" style="display:none;"></div>
<p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
    <c:if test="${pishnehad != null}">
        <a href="/printPishnehad.action?pishnehad.id=${pishnehad.id}" class="tipsi ui-state-default" target="_blank"
           style="float:left;" title="پرینت"><img src="../../img/pdf.png" alt=""></a>
    </c:if>
    <c:if test="${!isHalateElhaghie}">
        <a href="/loginUser.action" class="tipsi ui-state-default" style="float:left;margin-left:2px" title="بازگشت"><span
            class="ui-icon ui-icon-arrowreturnthick-1-w">&nbsp;</span></a>
    </c:if>
    <c:if test="${isHalateElhaghie}">
        <a href="/loginUser.action?selectedTab=tabs-3" class="tipsi ui-state-default" style="float:left;margin-left:2px" title="بازگشت"><span
            class="ui-icon ui-icon-arrowreturnthick-1-w">&nbsp;</span></a>
    </c:if>

    <%--<a href="javascript:void(0)" onclick="sendPishnehadFormByAjax()" class="tipsi ui-state-default" style="float:left;margin-left:2px" title="ذخیره">--%>
    <a id="savestarlinkid" href="javascript:void(0)" onclick="innerSMainForm()" class="tipsi ui-state-default"
       style="float:left;margin-left:2px" title="ذخیره">
        <span class="ui-icon ui-icon-disk" style="float:left;">&nbsp;</span>
        <span style="color:red" class="notSavedStar"></span>
    </a>
    <s:if test="%{!khesaratAction}">
    <c:if test="${!isHalateElhaghie}">
    <span style="font-weight:bold;color:red;">
    وضعیت پیشنهاد :

        ${pishnehad.state.stateName}

    </span>
        </c:if>
    </s:if>
    <s:else>
        <span style="font-weight:bold;color:red;">
        وضعیت خسارت :

            ${khesarat.state.stateName}

        </span>
    </s:else>
</p>

<form class="mainFrame" id="mainForm" method="post" action="/registerPishnahadeBimeOmreEnferadi.action">
    <input type="hidden" name="pishnehad.id" value="${pishnehad.id}">
    <%--<input type="hidden" name="pishnehad.gharardad.id" value="${pishnehad.gharardad.id}">--%>
    <input type="hidden" name="estelam.id" value="${estelam.id}">
    <input type="hidden" name="pishnehad.zamaem.id" value="${pishnehad.zamaem.id}">

    <table align="center" class="jtable resultDets" cellpadding="0" cellspacing="0" style="margin:0 auto;" width="90%">
        <tr>
            <th rowspan="2" width="8%">مشخصات بیمه شده</th>
            <th>نام و نام خانوادگی</th>
            <th>کد ملی</th>
            <th>تاریخ تولد</th>
            <th>سرمایه فوت</th>
            <th>شغل اصلی</th>
            <th>سن</th>
        </tr>
        <tr>
            <td>${pishnehad.bimeShode.shakhs.name} ${pishnehad.bimeShode.shakhs.nameKhanevadegi}</td>
            <td>${pishnehad.bimeShode.shakhs.kodeMelliShenasayi}</td>
            <td>${pishnehad.bimeShode.shakhs.tarikheTavallod}</td>
            <td>${pishnehad.estelam.sarmaye_paye_fot}</td>
            <td>${pishnehad.bimeShode.shakhs.shoghleAsli}</td>

            <script type="text/javascript">
                $(document).ready(
                    function()
                    {
                        var tt= "${pishnehad.bimeShode.shakhs.tarikheTavallod}";
                        $('#td_sen').html(mohasebeyeSen(tt,'S')) ;
                    });
            </script>
            <td><div id="td_sen"></div></td>
        </tr>
        <c:if test="${fn:length(pishnehad.fishs)>0}">
        <tr>
            <th rowspan="${fn:length(pishnehad.fishs)+1}" width="8%">مشخصات پیش پرداخت</th>
            <th>نوع پرداخت</th>
            <th>شماره فیش/کد رهگیری</th>
            <th>تاریخ</th>
            <th>دقیقه و ثانیه</th>
            <th>مبلغ</th>
        </tr>
        <c:forEach var="row" items="${pishnehad.fishs}" varStatus="i">
        <tr>
            <td>
                <c:if test="${row.paymentType == \"fish\"}">
                    فیش
                </c:if>
                <c:if test="${row.paymentType == \"interneti\"}">
                    اینترنتی
                </c:if>
                <c:if test="${row.paymentType == \"shenasedar\"}">
                    شناسه دار
                </c:if>
            </td>
            <td>${row.shomare}</td>
            <td>${row.tarikh}</td>
            <td>${row.time}</td>
            <td>${row.mablaghFormatted}</td>
        </c:forEach>
        </c:if>
    </table>
    <s:actionerror/>
    <sec:authorize ifNotGranted="ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">
    <p style="direction:rtl;text-align:right;">
        پيشنهاد دهنده گرامي، خواهشمنديم به کليه پرسش هاي مندرج در اين پيشنهاد با دقت و صداقت پاسخ داده،
        <span style="font-weight:bold;">سپس چاپ گرفته و امضا فرماييد.</span>
        در صورت صدور بيمه نامه شرط اصلي اعتبار آن، صحت اطلاعات مندرج در اين پيشنهاد و پاسخ هاي شما بوده و به آن استناد
        خواهد شد. ضمنا تکميل اين پيشنهاد هيچ تعهدي را براي بيمه گر ايجاد نمي نمايد.
    </p>
    </sec:authorize>
    <sec:authorize ifAnyGranted="ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">
        <p style="direction:rtl;text-align:right;"/>
    </sec:authorize>
    <div id="tabcontainer">
        <div id="tabs">
            <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN">
                <a href='javascript:void(0);' type="button" id="tab_1" class="tabsbtn activesubmit">اطلاعات عمومي
                    پيشنهاد</a>
            </sec:authorize>
            <a href='javascript:void(0);' type="button" id="tab_2" class="tabsbtn"> مشخصات بیمه گذار </a>
            <a href='javascript:void(0);' type="button" id="tab_3" class="tabsbtn"> مشخصات بیمه شده </a>
            <a href='javascript:void(0);' type="button" id="tab_4" class="tabsbtn">مشخصات بیمه نامه</a>
            <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN">
                <a href='javascript:void(0);' type="button" id="tab_5" class="tabsbtn">مشخصات استفاده کنندگان</a>
                <a href='javascript:void(0);' type="button" id="tab_6" class="tabsbtn">سؤالات عمومی از بیمه شده</a>
                <a href='javascript:void(0);' type="button" id="tab_7" onclick="checkJensiat();" class="tabsbtn">وضعیت
                    سلامتی بیمه شده</a>
                <a href='javascript:void(0);' type="button" id="tab_8" class="tabsbtn">وضعیت سلامتی خانواده بیمه شده</a>
                <a href='javascript:void(0);' type="button" id="tab_9" class="tabsbtn">سوابق بیمه ای</a>
                <a href='javascript:void(0);' type="button" id="tab_10" class="tabsbtn">توسط فروشنده بیمه تکمیل گردد</a>
            </sec:authorize>
            <sec:authorize ifAnyGranted="ROLE_PAS_KARSHENAS_MASOUL,ROLE_NAMAYANDE,ROLE_PAS_KARSHENAS,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI">
                <a href='javascript:void(0);' type="button" id="tab_106" class="tabsbtn specTabsbtn">بهره مندی از                    منافع</a>
            </sec:authorize>
        <sec:authorize ifAnyGranted="ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">
            <a href='javascript:void(0);' type="button" id="tab_104" class="tabsbtn specTabsbtn">جزئیات محاسبه کارمزد</a>
            <a href='javascript:void(0);' type="button" id="tab_105" class="tabsbtn specTabsbtn">کارمزد پرداختی</a>
            <a href='javascript:void(0);' type="button" id="tab_25" class="tabsbtn specTabsbtn">بیمه نامه</a>
            <a style="display: none;" href='javascript:void(0);' type="button" id="tab_103" class="tabsbtn specTabsbtn">الحاقیه ها</a>

        </sec:authorize>
        <sec:authorize ifNotGranted="ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">
            <c:if test="${pishnehad != null && pishnehad.bimeShode.id != null && !isHalateElhaghie && !khesaratAction}">
            <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN">
                <a style="display: none" href='javascript:void(0);' type="button" id="tab_11"
                   class="tabsbtn specTabsbtn">پیش پرداخت</a>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_12"
                   class="tabsbtn specTabsbtn">ضمائم پیشنهاد</a>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_14"
                   class="tabsbtn specTabsbtn">تایید فیش</a>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_13"
                   class="tabsbtn specTabsbtn">بررسی پیشنهاد</a>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_15"
                   class="tabsbtn specTabsbtn">تخصیص به کارشناس</a>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_16"
                   class="tabsbtn specTabsbtn">صدور معرفی نامه</a>
                <s:if test="khesaratAction==null || !khesaratAction">
                    <a style="display: none;" href='javascript:void(0);' type="button" id="tab_17"
                       class="tabsbtn specTabsbtn">معرفی نامه های پزشکی</a>
                </s:if>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_18"
                   class="tabsbtn specTabsbtn">فرم وضعیت منتظر تغییر شرایط</a>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_19"
                   class="tabsbtn specTabsbtn" onclick="showMohasebatPaye();">جدول محاسبات پایه</a>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_20"
                   class="tabsbtn specTabsbtn">بازبینی پیشنهاد</a>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_21"
                   class="tabsbtn specTabsbtn">فرم تایید پزشکی</a>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_22"
                   class="tabsbtn specTabsbtn">فرم تایید رئیس اداره</a>
                </sec:authorize>
                <sec:authorize ifNotGranted="ROLE_USER_KARTABL">
                    <a style="display: none;" href='javascript:void(0);' type="button" id="tab_23"
                       class="tabsbtn specTabsbtn" onclick="showVaziateGhestBandi();">فرم وضعيت قسط بندي بيمه نامه</a>
                </sec:authorize>
                <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN">
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_24"
                   class="tabsbtn specTabsbtn" onclick="sabteSanad();" name="tab24">ثبت سند</a>
                </sec:authorize>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_25"
                   class="tabsbtn specTabsbtn">بیمه نامه</a>
                <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN">
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_26"
                   class="tabsbtn specTabsbtn">فرم تبدیل به بیمه نامه</a>
                <s:if test="khesaratAction==null || !khesaratAction">
                    <a style="display: none;" href='javascript:void(0);' type="button" id="tab_27"
                       class="tabsbtn specTabsbtn">پرینت پیش پرداخت</a>
                </s:if>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_28"
                   class="tabsbtn specTabsbtn">نمایش بررسی پیشنهاد</a>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_29"
                   class="tabsbtn specTabsbtn">اعلام شماره حساب</a>
                <%--<a style="display: none;" href='javascript:void(0);' type="button" id="tab_30"--%>
                <%--class="tabsbtn specTabsbtn">اعلام شماره حساب</a>--%>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_31"
                   class="tabsbtn specTabsbtn">بررسی شرایط جدید</a>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_33"
                   class="tabsbtn specTabsbtn">اعلام به مالی</a>
                <%--<a style="display: none;" href='javascript:void(0);' type="button" id="tab_35"--%>
                   <%--class="tabsbtn specTabsbtn">اعلام به مالی</a>--%>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_32"
                   class="tabsbtn specTabsbtn">پیغام پیشنهاد</a>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_34"
                   class="tabsbtn specTabsbtn">فرمهای مالی بیمه نامه</a>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_103"
                   class="tabsbtn specTabsbtn">الحاقیه ها</a>
                </sec:authorize>
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_101"
                   class="tabsbtn specTabsbtn">وضعیت اقساط</a>

            </c:if>
        </sec:authorize>
        </div>

        <div id="tabformcontent">
            <div id="asl-container">
                <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN">
                    <div class=content id="content_1">
                        <%
                            if(pishnehadRun != null)
                            request.setAttribute("pishnehad", pishnehadRun);
                        %>
                        <jsp:include page="/jsp/pishnahad/subForm/etelaateOmomiPishnehad.jsp"/>
                    </div>
                </sec:authorize>
                <div style="display:none;" class=content id="content_2">
                    <%@include file="/jsp/pishnahad/subForm/bimeGozar.jsp" %>
                </div>
                <div style="display:none;" class=content id="content_3">
                    <%@include file="/jsp/pishnahad/subForm/bimeShode.jsp" %>
                </div>
                <div style="display:none;" class=content id="content_4">
                    <%@include file="/jsp/pishnahad/subForm/bimeName.jsp" %>
                </div>
                <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN">
                    <div style="display:none;" class=content id="content_5">
                        <%@include file="/jsp/pishnahad/subForm/estefadeKonandeganAzSarmayeBime.jsp" %>
                    </div>
                </sec:authorize>
                <div style="display:none;" class=content id="content_6">
                    <%@include file="/jsp/pishnahad/subForm/soalateOmomiAzBimeShode.jsp" %>
                </div>
                <div style="display:none;" class=content id="content_7">
                    <%@include file="/jsp/pishnahad/subForm/vaziateSalamatiBimeShode.jsp" %>
                </div>
                <div style="display:none;" class=content id="content_8">
                    <%@include file="/jsp/pishnahad/subForm/vaziateSalamatiKhanevadeyeBimeShode.jsp" %>
                </div>
                <div style="display:none;" class=content id="content_9">
                    <%@include file="/jsp/pishnahad/subForm/savabegheBimei.jsp" %>
                </div>
                <div style="display:none;" class=content id="content_10">
                    <%@include file="/jsp/pishnahad/subForm/foroshande.jsp" %>
                </div>
                <sec:authorize ifAnyGranted="ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">
                <div  class="content" id="content_25">
                    <%@include file="/jsp/bimename/subForm/bimenameSummary.jsp" %>
                </div>
                <div style="display:none;" class="content" id="content_104">
                    <table align="center" class="jtable resultDets" cellpadding="0" cellspacing="0" style="margin:0 auto;" width="90%">
                        <tr>
                            <th>سال بيمه اي</th>
                            <th>مجموع حق بيمه سال(ريال)</th>
                            <th>کارمزد سال (ريال)</th>
                            <th>تعرفه</th>
                            <th>نحوه پرداخت</th>
                            <th>سرمايه پايه فوت (ريال)</th>
                        </tr>

                        <%--<c:forEach var="row" items="${ghestBandiList}" varStatus="i">--%>
                            <%--<tr>--%>
                                <%--<td>${row.saleBimeiInt + 1}</td>--%>
                                <%--<td>${row.majmuHaghBimeSal}</td>--%>
                                <%--<td>${row.karmozdSal}</td>--%>
                                <%--<td>${row.tarefe}</td>--%>
                                <%--<td>${row.bimename.pishnehad.estelam.nahvePardakhtHaghBimeFarsi}</td>--%>
                                <%--<td>${row.bimename.pishnehad.estelam.sarmaye_paye_fot}</td>--%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>
                        <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime != 'yekja'}">
                            <c:if test="${ghestBandiList.size()!= 0 && ghestBandiList.size() < 5}">
                                <%
                                    List<GhestBandi> gbList=(List<GhestBandi>)request.getAttribute("ghestBandiList");
                                    Pishnehad p=(Pishnehad)request.getAttribute("pishnehad");
                                    KarmozdGhest.Tarefe tarefe=KarmozdCalculate.getTarefeBimename(p.getBimename());
//                                    long karmozdBimename= KarmozdCalculate.calKarmozdForBimenameGheyreYekja(tarefe, p, null);
                                    long karmozdBimename= -1l;
                                    if(p.getBimename().getKarmozdBimename() != null)
                                        karmozdBimename = p.getBimename().getKarmozdBimename();
                                    String t= p.getBimename().getGhestBandiList().get(0).getTarefe();
//                                    for(int j=gbList.size()+1;j<=5;j++)
                                    for (int j =  1; j <= 5; j++)
                                    {
                                        long karmozd=KarmozdCalculate.calKarmozdSal(karmozdBimename, j - 1, tarefe, false);
                                        long k = KarmozdCalculate.percentPaymentOfKarmozdToNamayande(p.getNamayande(),karmozd,tarefe);
                                        String karmozdSal=NumberFormat.getInstance().format(k);
                                %>
                                        <tr>
                                            <td><%=j%></td>
                                            <td></td>
                                            <td><%=karmozdSal%></td>
                                            <td><%=t%></td>
                                            <td>${pishnehad.estelam.nahvePardakhtHaghBimeFarsi}</td>
                                            <td>${pishnehad.estelam.sarmaye_paye_fot}</td>
                                        </tr>
                                <%
                                    }
                                %>
                            </c:if>
                        </c:if>
                        <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime == 'yekja'}">
                            <%
                                List<GhestBandi> gbList=(List<GhestBandi>)request.getAttribute("ghestBandiList");
                                Pishnehad p=(Pishnehad)request.getAttribute("pishnehad");
                                KarmozdGhest.Tarefe tarefe=KarmozdCalculate.getTarefeBimename(p.getBimename());
                                long kfg=KarmozdCalculate.calRealKarmozdForGhest(gbList.get(0).getGhestList().get(0).getCredebit(),tarefe, null);
                                String t= p.getBimename().getGhestBandiList().get(0).getTarefe();
                                for(int j=2;j<=3;j++)
                                    {
                                        long karmozd=KarmozdCalculate.calKarmozdSal(kfg, j - 1, tarefe, true);
                                        long k= KarmozdCalculate.percentPaymentOfKarmozdToNamayande(p.getNamayande(),karmozd,tarefe);
                                        String karmozdSal=NumberFormat.getInstance().format(k);
                            %>
                                        <tr>
                                            <td><%=j%></td>
                                            <td></td>
                                            <td><%=karmozdSal%></td>
                                            <td><%=t%></td>
                                            <td>${pishnehad.estelam.nahvePardakhtHaghBimeFarsi}</td>
                                            <td>${pishnehad.estelam.sarmaye_paye_fot}</td>
                                        </tr>
                            <%
                                    }
                            %>
                        </c:if>

                    </table>
                    <br/><br/><br/>
                    <table align="center" class="jtable resultDets" cellpadding="0" cellspacing="0" style="margin:0 auto;" width="90%">
                        <tr>
                            <th>مجموع حق بیمه سال اول (ریال)</th>
                            <th>مبلغ حق بيمه اوليه (ریال)</th>
                            <th>مجموع حق بیمه پوشش های اضافی سال اول (ریال)</th>
                            <th>مجموع مالیات سال اول (ریال)</th>
                        </tr>
                        <tr>
                            <td><%=((Pishnehad)request.getAttribute("pishnehad")).getBimename().getHaghBimeSaleAval()%></td>
                            <td><%=((Pishnehad)request.getAttribute("pishnehad")).getEstelam().getMablagh_seporde_ebtedaye_sal()%></td>
                            <td><%=((Pishnehad)request.getAttribute("pishnehad")).getBimename().getHaghBimePosheshEzafiSaleAval()%></td>
                            <td><%=((Pishnehad)request.getAttribute("pishnehad")).getBimename().getMaliatSaleAval()%></td>
                        </tr>
                    </table>
                </div>
                <div style="display:none;" class="content" id="content_105">
                    <%@include file="/jsp/management/page_detailsKarmozdePardakhti.jsp"%>
                    <script type="text/javascript" >
                        function viewDetailsKrmzd(credebitId)
                        {
                            $('#detail_krmzd').dialog
                            (
                                    {
                                        modal: true,
                                        width: 650,
                                        resizable: false,
                                        closeText: "",
                                        title: "جزئیات پرداخت",
                                        buttons: { "بستن": function ()
                                        {
                                            $(this).dialog("close");
                                        }}
                                    }
                            );
                            $.post
                            (
                                    "/detailsKarmozd.action?credebit.id=" + credebitId,
                                    function (rslt)
                                    {
                                        $('#detail_krmzd').html(rslt);
                                    }
                            );
                        }
                    </script>
                    <display:table export="false" id="tblGhestPaginatedList" uid="row" htmlId="tblGhestPaginatedList"
                                name="ghestPaginatedList" partialList="true" clearStatus="true" keepStatus="false"
                                style="margin-right:auto;margin-left:auto;margin-top:20px;width:790px" requestURI="?selectedTab=tabs-1" excludedParams="selectedTab"
                                size="${ghestPaginatedList.fullListSize}" pagesize="${ghestPaginatedList.objectsPerPage}">
                        <display:column title="رديف">${row_rowNum}</display:column>
                        <display:column title="سال بيمه اي">${row.ghestBandi.saleBimeiInt + 1}</display:column>
                        <display:column title="مبلغ قسط (ريال)" property="credebit.amount"></display:column>
                        <display:column title="مبلغ پرداخت شده از قسط(ريال)" property="credebit.paidAmount"></display:column>
                        <display:column title="مبلغ کارمزد قسط (ريال)" property="karmozdRealString"></display:column>
                        <display:column title="مبلغ کارمزد پرداخت شده (ريال)" property="karmozdPaidString"></display:column>
                        <display:column property="karmozdTgrString" title="کارمزد الحاقیه تغییرات (ریال)"></display:column>
                        <display:column property="karmozdVosuliString" title="مبلغ کارمزد وصولی قسط (ریال)"></display:column>
                        <display:column property="karmozdPoosheshEzafiString" title="مبلغ کارمزد پوشش های قسط (ریال)"></display:column>
                        <display:column title="تاريخ سر رسيد" property="sarresidDate"></display:column>
                        <display:column title="شناسه بدهي" property="credebit.shenaseCredebit"></display:column>
                        <display:column title="جزئيات پرداخت" >
                            <input type="button" class="noAnyDisable" onclick="viewDetailsKrmzd(${row.credebit.id});" value="..."  />
                        </display:column>
                    </display:table>
                    <div id="detail_krmzd" style="display: none;">
                    </div>
                </div>
                <div style="display: none" class="mainFrame content" id="content_103">
                    <%@include file="/jsp/user/page_kartablElhaghie.jsp" %>
                </div>
                </sec:authorize>
            </div>
        <sec:authorize ifNotGranted="ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">
            <div id="other_content_place">
                <c:if test="${pishnehad != null && pishnehad.bimeShode.id != null && !isHalateElhaghie}">
    <c:catch>
        <div id="other_content">
                <div style="display: none" class="mainFrame content" id="content_106">
                    <%@include file="/jsp/user/page_bahremandiAzManafe.jsp" %>
                </div>
            <s:if test="sodurElhaghie">
                <%@include file="/jsp/pishnahad/page_sodurElhaghieTaghirKod.jsp" %>
            </s:if>
            <sec:authorize ifAnyGranted="ROLE_LIMITED_AMIN_PARSIAN,ROLE_USER_KARTABL,ROLE_NAMAYANDE,ROLE_PAS_KARSHENAS_MASOUL">
                <div style="display: none" class="mainFrame content" id="content_101">
                    <%@include file="/jsp/user/page_kartablAghsat.jsp" %>
                </div>
            </sec:authorize>
            <sec:authorize ifAllGranted="ROLE_USER">
                <div style="display: none" class="mainFrame content" id="content_103">
                    <%@include file="/jsp/user/page_kartablElhaghie.jsp" %>
                </div>
            </sec:authorize>
            <s:if test="%{(khesaratAction && (khesarat==null||khesarat.state==null)) || havaleHa=='null'}">
                <div style="display: none" class="mainFrame content" id="content_36">
                    <%@include file="/jsp/management/khesarat/page_khesarat.jsp" %>
                </div>
            </s:if>
            <s:if test="%{khesaratAction && khesarat!=null && khesarat.state.id.equals(600)}">
                <div style="display: none" class="mainFrame content" id="content_37">
                    <%@include file="/jsp/management/khesarat/page_khesaratHavale.jsp" %>
                </div>
            </s:if>
            <s:if test="%{khesaratAction && khesarat!=null && khesarat.state!=null}">
                <div style="display: none" class="mainFrame content" id="content_38">
                    <%@include file="/jsp/management/khesarat/page_khesaratVaziat.jsp" %>
                </div>
            </s:if>
            <s:if test="%{khesaratAction && khesarat!=null && khesarat.state.id.equals(610)}">
                <div style="display: none" class="mainFrame content" id="content_39">
                    <%@include file="/jsp/management/khesarat/page_khesaratVaziatHavale.jsp" %>
                </div>
            </s:if>
            <c:if test="${pishnehad.state.id==20||pishnehad.state.id==90||pishnehad.state.id==50}">
                <div style="display:none;" class="content" id="content_11">
                    <%@include file="/jsp/pishnahad/subForm/pishPardakht.jsp" %>
                </div>
            </c:if>
            <sec:authorize ifAnyGranted="ROLE_BAZARYAB">
                <c:if test="${pishnehad.state.id==10}">
                    <div style="display:none;" class="content" id="content_11">
                        <%@include file="/jsp/pishnahad/subForm/pishPardakht.jsp" %>
                    </div>
                </c:if>
            </sec:authorize>
            <div style="display:none;" class="content" id="content_12">
                <%
                    request.setAttribute("pishnehadRun", pishnehadRun);
                %>
                <jsp:include page="/jsp/pishnahad/subForm/listeZamaem.jsp"/>
            </div>
            <%--<div style="display:none;" class="content" id="content_107">--%>
                <%--<%@include file="/jsp/bimename/subForm/zamaemDarkhast.jsp" %>--%>
            <%--</div>--%>
            <c:if test="${pishnehad.state.id==210||pishnehad.state.id==40||pishnehad.state.id==60||pishnehad.state.id==80||pishnehad.state.id==100}">
            <div style="display:none;" class="content" id="content_13">
                <%@include file="/jsp/pishnahad/subForm/eslahePishnehad.jsp" %>
            </div>
            </c:if>
            <c:if test="${pishnehad.state.id==100||pishnehad.state.id==40||pishnehad.state.id==60||pishnehad.state.id==80}">
            <div style="display:none;" class="content" id="content_14">
                <%@include file="/jsp/pishnahad/subForm/taeedFish.jsp" %>
            </div>
            </c:if>
            <c:if test="${pishnehad.state.id==70||pishnehad.state.id==40||pishnehad.state.id==60||pishnehad.state.id==80||pishnehad.state.id==100}">
            <div style="display:none;" class="content" id="content_15">
                <%@include file="/jsp/pishnahad/subForm/takhsisBeKarshenas.jsp" %>
            </div>
            </c:if>
            <c:if test="${pishnehad.state.id==140||pishnehad.state.id==190||pishnehad.state.id==120||pishnehad.state.id==330||pishnehad.state.id==210||pishnehad.state.id==40||pishnehad.state.id==60||pishnehad.state.id==80||pishnehad.state.id==100}">
            <div style="display:none;" class="content" id="content_16">
                <%@include file="/jsp/pishnahad/subForm/sodourMoarefiname.jsp" %>
            </div>
            </c:if>
            <div style="display:none;" class="content" id="content_17">
                <%@include file="/jsp/pishnahad/subForm/listMoarefiname.jsp" %>
            </div>
            <c:if test="${pishnehad.state.id==90||pishnehad.state.id==140||pishnehad.state.id==170||pishnehad.state.id==230||pishnehad.state.id==120}">
                <div style="display:none;" class="content" id="content_18">
                    <%
                        request.setAttribute("pishnehad", pishnehadRun);
                    %>
                    <jsp:include page="/jsp/pishnahad/subForm/taghireSharayet.jsp"/>
                </div>
            </c:if>
            <c:if test="${pishnehad.state.id==150}">
            <div style="display:none;" class="content" id="content_19">
                <%@include file="/jsp/pishnahad/subForm/mohasebatePaye.jsp" %>
            </div>
            </c:if>
            <c:if test="${pishnehad.state.id==240||pishnehad.state.id==210}">
            <div style="display:none;" class="content" id="content_20">
                <%@include file="/jsp/pishnahad/subForm/bazbiniyePishnehad.jsp" %>
            </div>
            </c:if>
            <c:if test="${pishnehad.state.id==180||pishnehad.state.id==160||pishnehad.state.id==246}">
            <div style="display:none;" class="content" id="content_21">
                <%@include file="/jsp/pishnahad/subForm/taeedPezeshki.jsp" %>
            </div>
            </c:if>
            <c:if test="${pishnehad.state.id>90}">
            <div style="display:none;" class="content" id="content_22">
                <%@include file="/jsp/pishnahad/subForm/taeedRaeis.jsp" %>
            </div>
            </c:if>
            <c:if test="${pishnehad.state.id==270||pishnehad.state.id==260||pishnehad.state.id==270||pishnehad.state.id==245||fromBimename==true}">
            <div style="display:none;" class="content" id="content_23">
                <%@include file="/jsp/pishnahad/transitionsForm/vaziateGhestBandiBimename.jsp" %>
            </div>
            </c:if>
            <c:if test="${pishnehad.state.id==280||pishnehad.state.id==270||pishnehad.state.id==290||fromBimename==true}">
            <div style="display:none;" class="content" id="content_25">
                <%@include file="/jsp/bimename/subForm/bimenameSummary.jsp" %>
            </div>
            </c:if>
            <c:if test="${pishnehad.state.id==245}">
            <div style="display:none;" class="content" id="content_26">
                <%@include file="/jsp/pishnahad/subForm/sodourBimename.jsp" %>
            </div>
            </c:if>
            <div style="display:none;" class="content" id="content_27">
                <%@include file="/jsp/pishnahad/transitionsForm/listePishPardakht.jsp" %>
            </div>
            <c:if test="${pishnehad.state.id==50}">
            <div style="display:none;" class="content" id="content_28">
                <%@include file="/jsp/pishnahad/subForm/namayesheEslahePishnehad.jsp" %>
            </div>
            </c:if>
            <c:if test="${pishnehad.state.id==310||pishnehad.state.id==220||pishnehad.state.id==370||pishnehad.state.id==252}">
            <div style="display:none;" class="content" id="content_29">
                <%
                    request.setAttribute("pishnehadRun", pishnehadRun);
                %>
                <jsp:include page="/jsp/pishnahad/subForm/elamShomareHesab.jsp"/>
            </div>
            </c:if>
            <c:if test="${pishnehad.state.id==150}">
            <div style="display:none;" class="content" id="content_31">
                <%
                    request.setAttribute("pishnehad", pishnehadRun);
                %>
                <jsp:include page="/jsp/pishnahad/subForm/barrasiyeSharayetJadid.jsp"/>
            </div>
            </c:if>
            <div style="display:none;" class="content" id="content_32">
                <%
                    request.setAttribute("pishnehad", pishnehadRun);
                %>
                <jsp:include page="/jsp/pishnahad/subForm/tarikhchePishnehad.jsp"/>
            </div>
            <c:if test="${pishnehad.state.id==340}">
            <div style="display:none;" class="content" id="content_33">
                    <jsp:include page="/jsp/pishnahad/subForm/elamShomareHesabForEnseraf.jsp"/>
            </div>
            </c:if>
            <%--<div style="display:none;" class="content" id="content_35">--%>
                <%--<%--%>
                    <%--request.setAttribute("pishnehad", pishnehadRun);--%>
                <%--%>--%>
                <%--<jsp:include page="/jsp/pishnahad/subForm/viewElameShomareHesabForEnseraf.jsp"/>--%>
            <%--</div>--%>


        </div>
    </c:catch>
</c:if>

            </div>
        </sec:authorize>
        </div>
    </div>
</form>



<br class="clear"/>

<div>&nbsp;</div>
<div style="width:100%;">


<%--<c:if test="${pishnehad.state.id==280||pishnehad.state.id==270||pishnehad.state.id==290||fromBimename==true}">--%>
    <sec:authorize ifAnyGranted="ROLE_KARSHENAS,ROLE_KARSHENAS_MASOUL">
        <c:if test="${pishnehad.bimename!=null}">
        <input type="button" onclick="window.open('/printBimename?pishnehadReport.pishnehad.id=${pishnehad.id}')" style="float:left;"
                             value="پرینت بیمه نامه آزمایشی" />
        </c:if>
        <c:if test="${pishnehadRun.getState()!=null && pishnehadRun.getState().getId()==245}">
            <input type="button" onclick="chapAzmayeshi();"   style="float:left;" value="چاپ آزمایشی"/>
        </c:if>
    </sec:authorize>
<%--</c:if>--%>

<s:if test="%{!khesaratAction}">
<input type="button" onclick="window.location='/loginUser.action'" value="بازگشت" style="float:left;"/>
</s:if>
<s:if test="%{khesaratAction}">
    <input type="button" onclick="window.location='/loginUser.action?selectedTab=tabs-6'" value="بازگشت" style="float:left;"/>
    <input type="button"
                       onclick="window.open('/printSoratVaziateMali?pishnehadReport.pishnehad.id=${pishnehad.id}')"
                       value="پرینت صورت وضعیت مالی" style="float:left;margin-left:5px"/>
</s:if>
<c:if test="${pishnehad.id == null && (user.namayandegi==null || user.namayandegi.pishnehadCrtAccess)}">
    <sec:authorize ifNotGranted="ROLE_BAZARYAB">
        <input type="button" onclick="sMainForm()" value="ارسال" style="float:left;margin-left:2px;"/>
    </sec:authorize>
    <sec:authorize ifAllGranted="ROLE_BAZARYAB">
        <input type="button" onclick="sMainFormForBazaryab();" value="ارسال" style="float:left;margin-left:2px;"/>
    </sec:authorize>
</c:if>
<c:if test="${pishnehad.id != null && !isHalateElhaghie && readOnlyMode == false}">
<%--<c:if test="${pishnehad.gharardad.id != null}">--%>
    <%--<input type="button" onclick="sMainForm()" value="ذخیره" style="float:left;margin-left:2px;"/>--%>
<%--</c:if>--%>
<c:if test="${pishnehad.state.id==10}">
    <input type="button"
           onclick="enableSubmit();window.open('/printPishnehad.action?pishnehad.id=<%=pishnehadRun.getId()%>');"
        <%--onclick="enableSubmit();window.open('/printKholasePishnehad.action?pishnehad.id=<%=pishnehadRun.getId()%>');"--%>
           value="چاپ پیشنهاد" style="float:left;margin-left:2px;"/>
    <sec:authorize ifNotGranted="ROLE_BAZARYAB">
        <input type="button" style="float:left;display: none;margin-left:2px;" onclick="submitTransitionForm()"
           value="امضا و تایید نهایی پیشنهاد" id="taeednahaeipishnahad"/>
    </sec:authorize>
    <script type="text/javascript">
        <c:if test="${pishnehad.initialyPrinted=='yes'}">
        $("#taeednahaeipishnahad").show();
        </c:if>
    </script>
</c:if>
<c:if test="${pishnehad.state.id >= 10}">
<input type="button"
                       onclick="printMohasebatRiazi();"
                       value="پرینت محاسبات ریاضی"  style="float:left;margin-left:2px;"/>
</c:if>
<%
    List<Transition> transitionsAllowed = (List<Transition>) request.getAttribute("allowedTransitions");
%>
<div id="div-to-transit" style="display: none;">
    <input type="hidden" id="stateOfPishnehad" value="${pishnehad.state.id}">
    <select style="float:left;margin-left:2px;" id="transitionSelector" name="transitionSelector">
        <% int transize = transitionsAllowed.size();
            for (Transition transition : transitionsAllowed) {
                //if(transitionsAllowed.get(0).getRoles().contains())
        %>
        <option value='<%=transition.getId()%>'><%=transition.getTransitionName()%>
        </option>
        <%
            }
        %>
    </select>
    <% if (pishnehadRun != null) if (pishnehadRun.getState().getId() == 10) { %>
    <%} else { %>
    <input type="button" id="ersalbutton" onclick="submitTransitionForm();" value="ثبت"
           style="float:left;margin-left:2px;"/>
    <%}%>
</div>

<script type="text/javascript">
    <c:if test="${pishnehad.state.hasFormeMiani=='no'}">
    $("#div-to-transit").show();
    </c:if>
</script>
<br class="clear"/>
<br class="clear"/>

<div style="display: none; float:left;height:30px;margin-top:2px">
    <input type="button" onclick="window.open('/printZamimeBimename?pishnehadReport.pishnehad.id=${pishnehad.id}')"
           value="پرینت ضمیمه بیمه نامه" style="float:left;"/>
</div>
<script type="text/javascript">
<c:if test="${pishnehad.id == null || (pishnehad.state.id!=245&&pishnehad.state.id<270)}">
alertMessage("لطفا در انتخاب نوع قرارداد و گروه بیمه نامه دقت فرمایید.")
</c:if>
<c:if test="${pishnehad.state.id >= 10}">
$("#tab_32").show();
$("#tab_27").show();
</c:if>
<sec:authorize ifAnyGranted="ROLE_BAZARYAB">
    <c:if test="${pishnehad.state.id == 10}">
        $("#tab_11").show();
        $("#tab_12").show();
    </c:if>
</sec:authorize>
<% if(pishnehadRun!=null && pishnehadRun.getState()!=null) {
if(pishnehadRun.getState().getId()==20){%>
$("#tab_11").show();
$("#tab_12").show();
$('.content').hide();
$('#content_11').show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_11').addClass('activesubmit');
<%} else if(pishnehadRun.getState().getId()==90){ %>
//$(".tabsbtn").hide();
$('.content').hide();
$('#content_11').show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_11').addClass('activesubmit');
$("#tab_11").show();
$("#tab_12").show();
<%}
else if(pishnehadRun.getState().getId()>=30){ %>
$("#tab_12").show();
<%}%>
<c:if test="${pishnehad.state.id==150}">
$("#tab_31").show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_31').addClass('activesubmit');
$('.content').hide();
$('#content_31').show();
</c:if>
<c:if test="${backfromupload =='true'}">
$('.tabsbtn').removeClass('activesubmit');
$('#tab_12').addClass('activesubmit');
$('.content').hide();
$('#content_12').show();
</c:if>
<c:if test="${pishnehad.state.id==300}">
$("#tab_34" +
        "").show();
</c:if>
<c:if test="${pishnehad.state.id==90}">
$("#tab_18").show();
</c:if>
<c:if test="${pishnehad.state.id==140}">
<sec:authorize ifAllGranted="ROLE_NAMAYANDE">
$("#tab_18").show();
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_RAEIS_SODUR,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL">
$("#tab_16").show();
$('.content').hide();
$('#content_16').show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_16').addClass('activesubmit');
</sec:authorize>
</c:if>
<c:if test="${pishnehad.state.id==210}">
$("#tab_13").show();
$("#tab_22").show();
</c:if>
<c:if test="${pishnehad.state.id==170}">
$("#tab_18").show();
//$("#tab_21").show();
</c:if>
<c:if test="${pishnehad.state.id==180}">

$("#tab_21").show();


</c:if>

<c:if test="${pishnehad.state.id==190}">
<c:forEach var="role" items="${user.roles}">
<c:if test='${role.roleName == "ROLE_KARSHENAS_SODUR"||role.roleName == "ROLE_KARSHENAS_MASOUL"}'>
$('.tabsbtn').removeClass('activesubmit');
$('#tab_16').addClass('activesubmit');
$('.content').hide();
$('#tab_16').show();
$('#content_16').show();
</c:if>
</c:forEach>
</c:if>
<c:if test="${pishnehad.state.id==220||pishnehad.state.id==252}">
$("#tab_12").show();
$("#tab_29").show();
</c:if>
<c:if test="${pishnehad.state.id==340}">
//$("#tab_24").show();
$("#tab_33").show();
</c:if>

<%if(pishnehadRun.getState()!=null && pishnehadRun.getState().getId()==40){ %>

$("#tab_13").show();
$("#tab_12").show();
$("#tab_14").show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_14').addClass('activesubmit');
$('.content').hide();
$('#content_14').show();
<%}
else if(pishnehadRun.getState()!=null && pishnehadRun.getState().getId()==60){ %>
$("#tab_13").show();
<%}else if(pishnehadRun.getState()!=null && pishnehadRun.getState().getId()==50){%>

$("#tab_28").show();
<%
boolean decider = false;
User karshenas = pishnehadRun.getKarshenas();
if (karshenas != null){
        decider =true;
}

if(decider==true){%>
$("#transitionSelector").val(42);
<%}else{%>
$("#transitionSelector").val(5);
<%}%>
<%
if(pishnehadRun.getNaghsPishnehad() != null){
if(
(pishnehadRun.getNaghsPishnehad().getNaghsFish().equalsIgnoreCase("1") ||
pishnehadRun.getNaghsPishnehad().getNaghsMadaarek().equalsIgnoreCase("1"))&&
pishnehadRun.getNaghsPishnehad().getNaghsEtelaat().equalsIgnoreCase("0")){
%>
<% if(pishnehadRun.getProcessor()!=null && pishnehadRun.getProcessor().equalsIgnoreCase(Constant.ROLE_KARSHENAS_MASOUL)){ %>
$("#tab_11").show();
<%}%>
$("#tab_12").show();
<%}
if(pishnehadRun.getNaghsPishnehad().getNaghsEtelaat().equalsIgnoreCase("1")&&(pishnehadRun.getNaghsPishnehad().getNaghsFish().equalsIgnoreCase("1") || pishnehadRun.getNaghsPishnehad().getNaghsMadaarek().equalsIgnoreCase("1"))){
%>
<% if(pishnehadRun.getProcessor()!=null && pishnehadRun.getProcessor().equalsIgnoreCase(Constant.ROLE_KARSHENAS_MASOUL)){ %>
$("#tab_11").show();
<%}%>
$("#tab_12").show();
<%}
}
}else if(pishnehadRun.getState()!=null &&pishnehadRun.getState().getId()==70){ %>
$("#tab_15").show();
<%}else if(pishnehadRun.getState()!=null &&pishnehadRun.getState().getId()==80){ %>
$("#tab_13").show();
$("#naghsfishrow").hide();
<%}else if(pishnehadRun.getState()!=null &&pishnehadRun.getState().getId()==100){ %>
$("#tab_13").show();
$("#tab_14").show();
$('.content').hide();
$('#content_14').show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_14').addClass('activesubmit');
<%}
else if(pishnehadRun.getState()!=null &&(pishnehadRun.getState().getId()==270)){ %>
$("#tab_23").show();
$('.content').hide();
$('#content_23').show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_23').addClass('activesubmit');
showVaziateGhestBandi();
<%}
else if(pishnehadRun.getState()!=null &&pishnehadRun.getState().getId()==120){ %>
$("#tab_17").show();
<sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
$('.content').hide();
$('#content_17').show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_17').addClass('activesubmit');
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_RAEIS_SODUR,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL">
$("#tab_16").show();
$('.content').hide();
$('#content_16').show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_16').addClass('activesubmit');
</sec:authorize>
<%}
else if(pishnehadRun.getState()!=null &&pishnehadRun.getState().getId()==130){ %>
$("#tab_17").show();
<%}
else if(pishnehadRun.getState()!=null && pishnehadRun.getState().getId()==230){ %>
$("#tab_18").show();
<%}
else if(pishnehadRun.getState()!=null && pishnehadRun.getState().getId()==240){ %>
$("#tab_20").show();
<%}
else if(pishnehadRun.getState()!=null && pishnehadRun.getState().getId()==245){ %>
$("#tab_26").show();
$("#tab_23").show();
$('.content').hide();
$('#content_26').show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_26').addClass('activesubmit');
<%}
else if(pishnehadRun.getState()!=null &&pishnehadRun.getState().getId()==150){ %>
$("#tab_19").show();
<%}
else if(pishnehadRun.getState()!=null &&(pishnehadRun.getState().getId()==160||pishnehadRun.getState().getId()==246)){%>
$("#tab_21").show();
$('.content').hide();
$('#content_21').show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_21').addClass('activesubmit');
<%}
else if(pishnehadRun.getState()!=null &&pishnehadRun.getState().getId()==200){%>
$("#tab_22").show();
$('.content').hide();
$('#content_22').show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_22').addClass('activesubmit');
<%}
else if(pishnehadRun.getState()!=null &&pishnehadRun.getState().getId()==260){%>
$("#tab_23").show();
$('.content').hide();
$('#content_1').show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_1').addClass('activesubmit');
<%}
if(pishnehadRun.getState()!=null &&(pishnehadRun.getState().getId()==280||pishnehadRun.getState().getId()==290)){%>
$('.content').hide();
$('.tabsbtn').removeClass('activesubmit');
$("#tab_25").show().addClass('activesubmit');
$('#content_25').show();
<%}
if(pishnehadRun.getState()!=null &&(pishnehadRun.getState().getId()==270)){%>
$("#tab_25").show();
<%}
}%>
<c:if test="${pishnehad.state.id==310||pishnehad.state.id==370}">
$("#tab_29").show();
$('.content').hide();
$('#content_29').show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_29').addClass('activesubmit');
</c:if>
<c:if test="${pishnehad.state.id==330}">
$("#tab_17").show();
$("#tab_16").show();
$('.content').hide();
$('#content_17').show();
$('.tabsbtn').removeClass('activesubmit');
$('#tab_17').addClass('activesubmit');
</c:if>

function openDialogBox(theId) {
    var stateBegin = "${pishnehad.state.stateName}";
    var stateFinish = $("#transitionSelector option:selected").text();


    $('#p_popup').dialog({
        modal:true,
        width:300,
        resizable:false,
        closeText:"",
        title:"تغییر وضعیت از " + stateBegin + " به " + stateFinish,
        buttons:{
            "بستن":function () {
                $(this).dialog("close");
                return false;
            },
            "انجام":function () {
                $('#' + theId).val($('#loggmessage').val());
                $(this).dialog("close");
                return true;
            }
        }
    });
}

function openDialogBoxAndSubmitTo(theId, theForm, title) {
    if(title == undefined) {
        var stateBegin = "${pishnehad.state.stateName}";
        var stateFinish = $("#transitionSelector option:selected").text();
        title = "تغییر وضعیت از " + stateBegin + " به " + stateFinish;
    }

    $('#p_popup').dialog({
        modal:true,
        width:300,
        resizable:false,
        closeText:"",
        title: title,
        buttons:{
            "بستن":function () {
                $(this).dialog("close");
                return false;
            },
            "انجام":function () {
                $('#' + theId).val($('#loggmessage').val());
                $(this).dialog("close");
                $('#' + theForm).submit();
                return true;
            }
        }
    });
}

function printMohasebatRiazi() {
    calculateSen();
    window.open('/printMohasebateRiazi.action?pishnehadReport.pishnehad.id=${pishnehad.id}&pishnehadReport.pishnehad.estelam.sen_bime_shode=' + $('#sen_bime_shode').val());
}

function submitTransitionForm() {
<%
if(pishnehadRun!=null&&pishnehadRun.getState()!=null){
%>

    var tekrari = false;
    if($('#stateOfPishnehad').val() == 10 || $('#stateOfPishnehad').val() == 20) {
        $.ajaxSetup({async: false});
        $.post('/shakhsTekrari.action?shakhs.kodeMelliShenasayi=${pishnehad.bimeShode.shakhs.kodeMelliShenasayi}',function (msg) {
            if(msg.indexOf('OK') == -1) {
                alertMessage('فرد بیمه شده تکراری است.');
                tekrari = true;
            }
        });
    }
    if(tekrari)
    {
        return false;
    }


    <%--<c:if test="${pishnehad.state.id == 10 || pishnehad.state.id == 20">--%>
//    if($('#bimeGozar_shakhs_kodemelli').val().length < 10) return;
//        $.post("/isBimeShodeValid.action?code_melli=" + $('#bimeGozar_shakhs_kodemelli').val(), function(data){
//            if(data.indexOf("NO") == -1)
//            {
//                alertMessage("بیمه شده تایید شده نمی باشد.");
//                return;
//            }
//        });
    <%--</c:if>--%>

    if($("#transitionSelector").val() == '7' || $("#transitionSelector").val() == '3')
    {
        anjamTransition();
        return;
    }

    <c:if test="${pishnehad.noeGharardad != 'قرارداد فروش جمعي'}">
    <c:if test="${pishnehad.getState().getId() != 10}">
    if($("#transitionSelector option:selected").val() == 2 || $("#transitionSelector option:selected").val() == 1)
    {
        <c:if test="${fn:length(pishnehad.fishs) == 0}">
            alertMessage('لطفا فیش ثبت نمایید.');
            return;
        </c:if>
    }
    </c:if>
    </c:if>

    <% if (pishnehadRun.getTransitionLogs().size()>0) { %>
    if($("#transitionSelector option:selected").val() == 5)
    {
        <% if(pishnehadRun.getTransitionLogs().get(pishnehadRun.getTransitionLogs().size()-1).getMessage() != null) { %>
        var msg = '<%= pishnehadRun.getTransitionLogs().get(pishnehadRun.getTransitionLogs().size()-1).getMessage().replace("\r\n","<br/>") %>';
        <% } else { %>
        var msg = '';
        <% } %>
        msg += '<br/><br/>';
        msg += "نماينده/كارگزار محترم فقط در صورتي كه <b><u>كليه تغييرات درخواستي</b></u> كارشناس صدور را در سيستم ثبت نموده ايد اقدام به تغيير وضعيت نماييد.در غير اينصورت علاوه بر اتلاف وقت همكاران،اين فرآيند مجدداً تكرار و باعث تأخير در صدور بيمه نامه و نارضايتي مشتريان خواهد گرديد.";
        confirmMessage(msg, "تایید", function(){
            showTransitionDialog();
        });
    }
    else
    {
        showTransitionDialog();
    }
    <% } else { %>
        showTransitionDialog();
    <% } %>
<%
}
%>
}
function showTransitionDialog()
{
<%if(transize>0){%>
    var stateBegin = "${pishnehad.state.stateName}";
    var stateFinish = $("#transitionSelector option:selected").text();
    $('#p_popup').dialog({
        modal:true,
        width:300,
        resizable:false,
        closeText:"",
        title:"تغییر وضعیت از " + stateBegin + " به " + stateFinish,
        buttons:{
            "بستن":function () {
                $(this).dialog("close");
            },
            "انجام":function () {
                anjamTransition();
            }
        }
    });
<%}else{%>
    alertMessage("شما دسترسی لازم برای اعمال تغییر وضعیت را ندارید.");
<%}%>
}

function anjamTransition()
{
    if ($('#transitionSelector').val() == 1 || $('#stateOfPishnehad').val() == 20
                        || $('#stateOfPishnehad').val() == 140 || $('#stateOfPishnehad').val() == 220 || $('#stateOfPishnehad').val() == 230 || $('#stateOfPishnehad').val() == 252
                        ) {
                    if (validatePishnehadForm()) {
                        var dataForm = $('#mainForm').serialize();
                        $.post($('#mainForm').attr('action'), dataForm, function () {
                            <%--<%if(pishnehadRun.getState().getId()==170){ %>--%>
                            <%--$('#transitionSelector').val(21);--%>
                            <%--<%}%>--%>
                            $('#pishnehadIdForErsal').val(<%=pishnehadRun.getId()%>);
                            $('#transitionId').val($('#transitionSelector').val());
                            $('#logmessage').val($('#loggmessage').val());
                            <%
                              request.setCharacterEncoding("UTF-8");
                              response.setCharacterEncoding("UTF-8");
                            %>
                            $('#transitionForm').submit();
                        })
                    } else {
                        $(this).dialog("close");
                        return false;
                    }
                } else {
                <%if(pishnehadRun.getState().getId()==170){ %>
                    $('#transitionSelector').val(21);
                <%}%>
                    $('#pishnehadIdForErsal').val(<%=pishnehadRun.getId()%>);
                    $('#transitionId').val($('#transitionSelector').val());
                    $('#logmessage').val($('#loggmessage').val());
                <%
                  request.setCharacterEncoding("UTF-8");
                  response.setCharacterEncoding("UTF-8");
                %>
                    $('#transitionForm').submit();
                }
}

function enableSubmit() {
    $.post("/ajaxlySetPrintedForPishnehad.action?pishnehad.id=${pishnehad.id}", function () {
    });
    <sec:authorize ifNotGranted="ROLE_BAZARYAB">
        $("#taeednahaeipishnahad").show();
    </sec:authorize>
}
function showMohasebatPaye() {
<%
if(pishnehadRun!=null){
%>
    $.post("/anjameMohasebatePaye.action?pishnehad.id=${pishnehad.id}", function (msg) {
        $('#div_mohasebat_paye').html(msg);

    });
<%
}
%>

}

function sabteSanad() {
    $.post("/loadSanadZaniForPishnehad.action?pishnehadId=${pishnehad.id}", function (msg) {
        $('#div_sabteSanad').html(msg);
    });
}

function showVaziateGhestBandi() {
    $.post("/showVaziateGhestBandi.action?pishnehad.id=${pishnehad.id}", function (msg) {
        $('#div_showVaziateGhestBandi').html(msg);
        $("#div_showVaziateGhestBandi input").each(function () {
            $(this).addClass("ui-state-default  ui-corner-all ui-helper-clearfix");
        });
    });
}
</script>
</c:if>
<c:if test="${readOnlyMode == true}">
    <script type="text/javascript">
    function showVaziateGhestBandi() {
    $.post("/showVaziateGhestBandi.action?pishnehad.id=${pishnehad.id}&fromReadOnlyMode=y", function (msg) {
        $('#div_showVaziateGhestBandi').html(msg);
        $("#div_showVaziateGhestBandi input").each(function () {
            $(this).addClass("ui-state-default  ui-corner-all ui-helper-clearfix");
        });
    });
}
    </script>
</c:if>
<c:if test="${pishnehad.id != null && isHalateElhaghie}">
    <input type="button" onclick="elhaghie()" value="الحاقیه اصلاحی" style="float:left;margin-left:2px;"/>
</c:if>
</div>
<br class="clear"/>

<div id="shakhsElements" class="vld" style="display:none;">
    <%@include file="/jsp/pishnahad/dialogForm/addShakhsForm.jsp" %>
</div>
<div id="shakhsHoghoghiElements" class="vld" style="display:none;">
    <%@include file="/jsp/pishnahad/dialogForm/addShakhsHoghoghiForm.jsp" %>
</div>
<div id="add_sabeghe_div" class="vld" style="display:none;">
    <%@include file="/jsp/pishnahad/dialogForm/addSavabegheBimei.jsp" %>
</div>
<div id="add_vaziateSalamatiKhanevadeyeBimeShode_div" class="vld" style="display:none;">
    <%@include file="/jsp/pishnahad/dialogForm/addVaziateSalamatiKhanevadeyeBimeShode.jsp" %>
</div>
<div id="add_EKASB_div" class="vld" style="display:none;">
    <%@include file="/jsp/pishnahad/dialogForm/addEstefadeKonandeganAzSarmayeBime.jsp" %>
</div>
<%@include file="/jsp/josteju/josteju.jsp" %>
<%@include file="/jsp/josteju/searchCity.jsp" %>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>

<div id="p_popup" style="display:none;">
    <textarea rows="5" cols="42" name="logmessage" id="loggmessage"></textarea>
</div>

<form action="/makeInnerTransition.action" id="transitionForm" method="post" accept-charset="UTF-8">
    <input type="hidden" name="pishnehad.id" id="pishnehadIdForErsal"/>
    <input type="hidden" name="transitionId" id="transitionId"/>
    <input type="hidden" name="logmessage" id="logmessage"/>
    <input type="hidden" name="privateMessage" id="privateMessage"/>
</form>
<script type="text/javascript">

    <%----%>
    <c:if test="${pishnehad != null && !isHalateElhaghie && readOnlyMode == true}">
    $("#tab_12").show();
    $("#tab_27").show();
    $("#tab_17").show();
    $("#tab_32").show();
    </c:if>
    <sec:authorize ifAnyGranted="ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_PEZESHK,ROLE_RAEIS_SODUR">
    <c:if test="${pishnehad.state.id > 90 && pishnehad.state.id != 180}">
    $("#tab_22").show();
    </c:if>
    </sec:authorize>




    $(document).ready(function () {
        checkJensiat();
        <%
        if(pishnehadRun!=null){
            if(pishnehadRun.getBimeGozar()!=null && pishnehadRun.getBimeGozar().getShakhs()!=null && pishnehadRun.getBimeGozar().getShakhs().getType().equals(Shakhs.BimeGozarType.HOGHOGHI)){
            %>
        disableHaghighiElements();
        <%
        }else{
        %>
        enableHaghighiElements();
        <%
            }
        }
        %>



        <c:if test="${(pishnehad!=null && pishnehad.state!=null && pishnehad.state.id != 10 && pishnehad.state.id != 20 &&
    !isHalateElhaghie && pishnehad.state.id !=50 && pishnehad.state.id !=245)||readOnlyMode==true}">
        disablePishnehadTabs("asl-container");
        $("#savestarlinkid").hide();
        </c:if>

        <c:if test="${isHalateElhaghie}">

        $("#savestarlinkid").hide();
        </c:if>

        var loadMessage = "${pishnehadLoadMessage}";
        if(loadMessage.length > 0)
            alertMessage(loadMessage);

    });
    function checkJensiat() {
    <c:if test="${pishnehad==null}">
        $.post('/fillShakhs.action?shakhsId=' + $('#bimeShode_shakhs_id').val(), function (msg) {
            if (msg.split(',')[17] == 'مرد') {
                $("#banovanHeader").hide();
                $("#soal_x_25").attr("readonly", true);
                $("#soal_x_26").attr("readonly", true);
                $("#theTr26").hide();
                $("#theTr27").hide();
            } else {
                $("#banovanHeader").show();
                $("#soal_x_25").removeAttr("readonly");
                $("#soal_x_26").removeAttr("readonly");
                $("#theTr26").show();
                $("#theTr27").show();
            }
        });
    </c:if>
    <c:if test="${pishnehad!=null}">
        if (($('#editBimeshodeASF_jensiat_i').is(':checked')) || (${pishnehad.bimeShode.shakhs.jensiat=='مرد'})) {
            $("#banovanHeader").hide();
            $("#soal_x_25").attr("readonly", true);
            $("#soal_x_26").attr("readonly", true);
            $("#theTr26").hide();
            $("#theTr27").hide();
        } else {
            $("#banovanHeader").show();
            $("#soal_x_25").removeAttr("readonly");
            $("#soal_x_26").removeAttr("readonly");
            $("#theTr26").show();
            $("#theTr27").show();
        }
    </c:if>
    }
    <s:if test="%{khesaratAction && (khesarat==null||khesarat.state==null)}">
    $("#tab_36").show();
    $('.content').hide();
    $('#content_36').show();
    $('.tabsbtn').removeClass('activesubmit');
    $('#tab_36').addClass('activesubmit');
    </s:if>
    <s:if test="%{khesaratAction && khesarat!=null && khesarat.state.id.equals(600)}">
    $("#tab_37").show();
    $('.content').hide();
    $('#content_37').show();
    $('.tabsbtn').removeClass('activesubmit');
    $('#tab_37').addClass('activesubmit');
    </s:if>
    <s:if test="%{khesaratAction && khesarat!=null && khesarat.state!=null && !khesarat.state!=null && !khesarat.state.id.equals(600)}">
    $("#tab_38").show();
    $('.content').hide();
    $('#content_38').show();
    $('.tabsbtn').removeClass('activesubmit');
    $('#tab_38').addClass('activesubmit');
    </s:if>


    <c:if test="${fromBimename == true}">
    $("#tab_23").show();
    $("#tab_103").show();
    <%--<sec:authorize ifNotGranted="ROLE_NAMAYANDE">--%>
    $("#tab_25").show();
    $('.content').hide();
    $('.tabsbtn').removeClass('activesubmit');
    $('#content_25').show();
    $('#tab_25').addClass('activesubmit');
    <%--</sec:authorize>--%>
    <sec:authorize ifAllGranted="ROLE_NAMAYANDE">
    $("#tab_101").show();
    </sec:authorize>
    </c:if>

    <sec:authorize ifAllGranted="ROLE_USER_KARTABL">
    $("#tab_101").show();
    </sec:authorize>

    <sec:authorize ifAllGranted="ROLE_PAS_KARSHENAS_MASOUL">
    $("#tab_101").show();
    </sec:authorize>



    <sec:authorize ifAllGranted="ROLE_USER,ROLE_USER_KARTABL">
    $("#tab_103").show();
    </sec:authorize>

    <sec:authorize ifAllGranted="ROLE_LIMITED_AMIN_PARSIAN">
        $("#tab_101").show();
    </sec:authorize>

</script>
</body>
</html>