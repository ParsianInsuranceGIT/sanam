<%@ page import="com.bitarts.parsian.Core.ConstantPaging" %>
<%@ page import="com.bitarts.common.displaytag.PagingUtil" %>
<%@ page import="com.bitarts.common.displaytag.PaginatedListImpl" %>
<%@ page import="com.bitarts.parsian.model.karmozd.Karmozd" %>
<%--<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>--%>
<script type="text/javascript" src="/js/jquery.validationEngine.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/jsp/taglibs.jsp" %>
<head>
    <title>صفحه اصلی</title>

    <script type="text/javascript">
        //        $(function ()
        //        {
        //            $("#tabs").tabs();
        //        });
        function close() {
            $.validationEngine.closePrompt('.formError', true);
            $('#crt_prj_onvan').val('');
            $('#crt_prj_desc').val('');
            $('#crt_prj_vosoliDate').val('');
        }

        function create() {
            if ($.validationEngine.submitValidation(this) === false) {
                $('#crt_prj_frm').submit();
                $(this).dialog("close");
            }
        }
//        function createA() {
//            if ($.validationEngine.submitValidation(this) === false) {
//                $('#crt_prj_frm_A').submit();
//                $(this).dialog("close");
//            }
//        }

        var dilg;

        function loadCreateProject() {
            $.validationEngine.closePrompt('.formError', true);
            dilg = $('#crt_prj_frm').dialog({
                modal: true,
                width: 840,
                resizable: false,
                closeText: "",
                title: "ایجاد پروژه کارمزد جدید",
                close: close,
                buttons: {
                    "ثبت": create,
                    "انصراف": function () {
                        close();
                        $(this).dialog("close");
                    }
                }
            });
        }

//        function createSeniorKarmozdProj(idProj, azTarikhVosoli, taTarikhVosoli, serialNum,status)
//        {
//            if(status=='final')
//            {
//                $('#crt_prj_id_A').val(idProj);
//                $('#crt_prj_az_vosoliDate_A').val(azTarikhVosoli);
//                $('#crt_prj_ta_vosoliDate_A').val(taTarikhVosoli);
//                $('#crt_prj_desc_A').val('محاسبه کارمزد نمایندگان ارشد از ' + azTarikhVosoli + ' تا '+ taTarikhVosoli);
//                $('#crt_prj_onvan_A').val('کارمزد نمایندگان ارشد برای پروژه '+serialNum);
//                $.validationEngine.closePrompt('.formError', true);
//                dilg = $('#crt_prj_frm_A').dialog({
//                    modal: true,
//                    width: 840,
//                    resizable: false,
//                    closeText: "",
//                    title: "ایجاد پروژه کارمزد نمایندگان ارشد",
//                    close: close,
//                    buttons: {
//                        "ثبت": createA,
//                        "انصراف": function () {
//                            close();
//                            $(this).dialog("close");
//                        }
//                    }
//                });
//            }
//            else
//            {
//                alertMessage("قبل از تعریف پروژه کارمزد نمایندگان ارشد، می بایست پرداخت پروژه جاری را انجام دهید.");
//            }
//
//        }

        function createSenior()
        {
            $.validationEngine.closePrompt('.formError', true);
            dilg = $('#add_senior_frm').dialog({
                modal: true,
                width: 400,
                resizable: false,
                closeText: "",
                title: "نماینده ارشد جدید",
                close: close,
                buttons:
                {
                    "تایید": addSeniors,
                    "انصراف": function (){$(this).dialog("close");}
                }
            });
        }

        function loadSeniorOfNamayande()
        {
            $.validationEngine.closePrompt('.formError', true);
            dilg = $('#load_senior_subset_frm').dialog({
                modal: true,
                width: 400,
                resizable: false,
                closeText: "",
                title: "جستجوی نماینده فروش",
                close: close,
                buttons:
                {
                    "تایید": loadNamayande,
                    "انصراف": function (){$(this).dialog("close");}
                }
            });
        }

        function createSeniorSubset(seniorId)
        {
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'seniorId';
            input.value = seniorId;
            $('#add_senior_subset_frm').append(input);

            $.validationEngine.closePrompt('.formError', true);
             $('#add_senior_subset_frm').dialog({
                modal: true,
                width: 700,
                resizable: false,
                closeText: "",
                title: "افزودن زیرمجموعه جدید",
                close: close,
                buttons:
                {
                    "تایید": addSeniorSubsets,
                    "انصراف": function (){$(this).dialog("close");}
                }
            });
        }

        function viewSubsetList(seniorId)
        {
//            $.validationEngine.closePrompt('.formError', true);
            var viewSubsetDlg = $('#view_subsets_div').dialog({
                modal: true,
                width: 700,
                resizable: false,
                closeText: "",
                title: "زیرمجموعه ها",
                close: close,
                buttons: {
                    "بستن": function ()
                    {
                        $(this).dialog("close");
                    }
                }
            });
            $.ajax
            (
                {
                    type: "POST",
                    url: "/viewSubset.action",
                    data: "seniorId=" + seniorId,
                    success: function (rslt)
                    {viewSubsetDlg.html(rslt);}
                }
            );
        }

        function loadAddToBlackList() {
            $.validationEngine.closePrompt('.formError', true);
            dilg = $('#add_black_list_frm').dialog({
                modal: true,
                width: 600,
                resizable: false,
                closeText: "",
                title: "افزودن به لیست سیاه",
                close: close,
                buttons: {
                    "تایید": add,
                    "انصراف": function () {
                        closeAddBlack();
                        $(this).dialog("close");
                    }
                }
            });
        }

        function changeTypeBlackList()
        {
            if($('#add_black_list_type').val()=='Vosuli')
            {
                $('#gruop_td').show();
                groupOrshomareBimename();
            }
            else
            {
                $('#gruop_td').hide();
                $('#blk_list_grp_bim').hide();
                $('#blk_list_shomare_bim').show();
            }

        }

        function groupOrshomareBimename()
        {
            if($('#batch_chk').is(':checked'))
            {
                $('#blk_list_grp_bim').show();
                $('#blk_list_shomare_bim').hide();
            }
            else
            {
                $('#blk_list_grp_bim').hide();
                $('#blk_list_shomare_bim').show();
            }
        }

        function add() {
            if ($.validationEngine.submitValidation(this) === false) {
                $('#add_black_list_frm').submit();
                $(this).dialog("close");
            }
        }

        function addSeniors() {
            if ($.validationEngine.submitValidation(this) === false)
            {
                if($('#seniorId').val()[0]==3)
                {
                    alertMessage("نماینده فروش نمی تواند به عنوان ارشد تعریف شود.");
                    $('#seniorId').val('');
                    $('#seniorName').val('');
                }
                else
                {
                    $('#add_senior_frm').submit();
                    $(this).dialog("close");
                }
            }
        }

        function loadNamayande()
        {
            if ($.validationEngine.submitValidation(this) === false)
                $.post("/loadNamaSubset?namayande.id="+$('#subsetId_l').val(),function(rslt){$('#rslt_load').html(rslt);});
        }

        function addSeniorSubsets() {
            if ($.validationEngine.submitValidation(this) === false)
            {
                if($('#subsetCode').val()[0]!=3)
                {
                    alertMessage("فقط نماینده فروش می تواند به عنوان زیرمجموعه انتخاب شود.");
                    $('#subsetId').val('');
                    $('#subsetName').val('');
                    $('#subsetCode').val('');
                }
                else
                {
                    $('#add_senior_subset_frm').submit();
                    $(this).dialog("close");
                }
            }
        }

        function closeAddBlack() {
            $.validationEngine.closePrompt('.formError', true);
            $('#add_black_list_shomare_bimename').val('');
        }


        function doAjaxPost(actionName, tabName, forceRefresh) {
            var tabElementName = tabName.replace("#","");
            if (!forceRefresh){
                if (document.getElementById(tabElementName).getElementsByClassName('empty').length > 0)
                    forceRefresh = true;
                else
                    forceRefresh = false;
            }

            var paramUrl = "";
            if (tabName == '#displayTab1'){
                if (getUrlVars()["pageNumber_reportResult"] != undefined)
                    paramUrl = "parametersUrl=pageNumber_reportResult="+getUrlVars()["pageNumber_reportResult"];
            }else if (tabName == '#displayTab2'){
                if (getUrlVars()["pageNumber_viewReportResult"] != undefined)
                    paramUrl = "parametersUrl=pageNumber_viewReportResult="+getUrlVars()["pageNumber_viewReportResult"];
            }else if (tabName == '#displayTab3'){
                if (getUrlVars()["pageNumber_bimenameHa"] != undefined)
                    paramUrl = "parametersUrl=pageNumber_bimenameHa="+getUrlVars()["pageNumber_bimenameHa"];
            }else if (tabName == '#displayTab4'){
                if (getUrlVars()["page"] != undefined)
                    paramUrl = "parametersUrl=page="+getUrlVars()["page"];
            }else if (tabName == '#displayTab8'){
                if (getUrlVars()["pageNumber_allddj"] != undefined)
                    paramUrl = "parametersUrl=pageNumber_allddj="+getUrlVars()["pageNumber_allddj"];
            }else if (tabName == '#displayTab5'){
                if (getUrlVars()["d-49685-p"] != undefined)
                    paramUrl = "parametersUrl=d-49685-p="+getUrlVars()["d-49685-p"];
            }else if (tabName == '#displayTab7'){
                if (getUrlVars()["pageNumber_gharardadHa"] != undefined)
                    paramUrl = "parametersUrl=pageNumber_gharardadHa="+getUrlVars()["pageNumber_gharardadHa"];
            }else if (tabName == '#displayTab9'){
                if (getUrlVars()["PageNumber_vams"] != undefined)
                    paramUrl = "parametersUrl=PageNumber_vams="+getUrlVars()["PageNumber_vams"];
            }

            if (forceRefresh){
                $.ajax({
                    type: "POST",
                    url: actionName,
                    data: paramUrl,
                    success: function (response) {
                        $(tabName).html(response);
                    }
                });
            }

        }

        function getUrlVars()
        {
            var vars = [], hash;
            var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
            for(var i = 0; i < hashes.length; i++)
            {
                hash = hashes[i].split('=');
                vars.push(hash[0]);
                vars[hash[0]] = hash[1];
            }
            return vars;
        }
    </script>
</head>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<c:set var="hasRoleBazaryab" value="-1"/>
<c:forEach var="role" items="${user.roles}">
    <c:if test="${role.roleName == 'ROLE_BAZARYAB'}">
        <c:set var="hasRoleBazaryab" value="1"/>
    </c:if>
</c:forEach>
<sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
    <div class="expandableTitleBar" id="expandableAsl" style="width: 370px;">
        <p class="heading">
            <span class="ui-icon ui-icon-plus" style="float:right;"></span>
            گزارش ها
        </p>

        <div class="content" style="display:none;" id="reportsAslContent">
        <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
            <a style="margin-right: 20px;float:right;" href="javascript:void(0);"
               onclick="window.location='/prepareGozaresheBordroyeSodor'">- گزارش بردرو صدور بیمه نامه های عمر و سرمایه
                گذاری</a>
            <br/><br/>
        </sec:authorize>
            <a style="margin-right: 20px;float:right;" href="javascript:void(0);"
               onclick="window.location='/prepareGozaresheAghsatMoavagh'">- کزارش بردروی اقساط معوق بیمه نامه های عمر و
                سرمایه گذاری</a>
            <br/><br/>
            <a style="margin-right: 20px;float:right;" href="javascript:void(0);"
               onclick="window.location='/prepareGozaresheBordroyeMabaleghPardakhti'">- گزارش بردروي مبالغ پرداختی بیمه
                نامه های عمر و سرمایه گذاری</a>
            <br/><br/>
        <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
            <a style="margin-right: 20px;float:right;" href="javascript:void(0);"
               onclick="window.location='/prepareElhaghieReport.action'">
                - گزارش بردرو الحاقیه های صادر شده
            </a>
            <br/><br/>

            <a style="margin-right: 20px;float:right;" href="javascript:void(0);" onclick="window.location='/batchTaghsitReport.action'">
                - گزارش تقسیط گروهـی
            </a>
            <br/><br/>
        </sec:authorize>
            <hr style="color: #1d5987"/>
        </div>
    </div>
    <br/><br/>
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_PAS_KARSHENAS,ROLE_PAS_RAEIS,ROLE_PAS_KARSHENAS_MASOUL">
    <div class="expandableTitleBar" id="expandableAsl" style="width: 370px;">
        <p class="heading">
            <span class="ui-icon ui-icon-plus" style="float:right;"></span>
            گـــــزارشات
        </p>

        <div class="content" style="display:none;" id="reportsAslContent">
            <a style="margin-right: 20px;float:right;" href="javascript:void(0);" href="javascript:void(0);" onclick="window.location='/batchTaghsitReport.action'">
                - گزارش تقسیط گروهـی
            </a>
            <br/><br/>
    <sec:authorize ifAnyGranted="ROLE_PAS_RAEIS,ROLE_PAS_KARSHENAS_MASOUL">
        <a href="javascript:void(0);" onclick="window.location='/prepareGozaresheBordroyeBazkharidShode'">- گزارش بردروي
            بيمه نامه هاي بازخريد شده</a><br/><br/>
        <a href="javascript:void(0);" onclick="window.location='/prepareGozaresheBordroyeSarresidShode'">- گزارش بردروي
            بيمه نامه هاي سررسيد شده</a><br/><br/>
        <a href="javascript:void(0);" onclick="window.location='/prepareElhaghieReport.action'"> - گزارش بردرو الحاقیه
            های صادر شده</a><br/><br/>
        <a href="javascript:void(0);" onclick="window.location='/prepareAghsatVamReport.action'"> - گزارش اقساط
            وام</a><br/><br/>
        <a style="margin-right: 20px;float:right;" href="javascript:void(0);"
           onclick="window.location='/prepareGozaresheAghsatMoavagh'">- کزارش بردروی اقساط معوق بیمه نامه های عمر و
            سرمایه گذاری</a>
        <br/><br/>
        <a style="margin-right: 20px;float:right;" href="javascript:void(0);"
           onclick="window.location='/prepareGozaresheBordroyeMabaleghPardakhti'">- گزارش بردروي مبالغ پرداختی بیمه
            نامه های عمر و سرمایه گذاری</a>
        <br/><br/>
    </sec:authorize>
            <hr style="color: #1d5987"/>
        </div>
    </div>
    <br/><br/>
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE,ROLE_LIMITED_AMIN_PARSIAN,ROLE_MOSHAHEDE_BIMENAME">  <!--baraye in role ha division zir ra neshan nemidahad-->
    <div>
        <input type="button" onclick="window.location='loadEstelamLoggedIn'"
               value="استعلام" style="margin-right: 20px;float:right;"/>
            <%--<s:if test="%{user.getNamayandegi()==null || !user.getNamayandegi().getKodeNamayandeKargozar().startsWith('4100')}">--%>
        <s:if test="%{user.getNamayandegi()==null || user.getNamayandegi().getPishnehadCrtAccess()}">
            <input type="button" onclick="window.location='/editShowForm'"
                   value="ایجاد پیشنهاد" style="margin-right: 5px;float:right;"/>
            <input type="button" onclick="window.location='/editShowForm?optionsPishnehad='+true"
                   value="ایجاد پیشنهاد برای نمایندگان کد موقت" style="margin-right: 5px;float:right;"/>
        </s:if>
        <input type="button" onclick="addComment();" value="نظر شما"
               style="margin-right: 5px;float:right;"/>
    </div>
</sec:authorize>
<div><s:actionmessage/></div>
<div><s:actionerror/></div>

<br>

<div style="margin-bottom:35px;">
    <sec:authorize
            ifAnyGranted="ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_PEZESHK,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI">
        <a href='/extra/Help_sodor_3583.pdf' style="margin-top:10px; margin-right: 5px;float:left;" type="button">راهنمای
            کاربری
            سیستم ثبت الکترونیکی پیشنهادات</a>
        <span style="margin-top:10px; margin-right: 5px;float:left;">|</span>
        <a href='/extra/helpElhaghie-KARSHENAS(Edition%201).pdf' style="margin-top:10px; margin-right: 5px;float:left;"
           type="button">
            راهنماي الحاقيه
        </a>
        <span style="margin-top:10px; margin-right: 5px;float:left;">|</span>
    </sec:authorize>
    <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
        <a href='/extra/Help_namayande_73737.pdf' style="margin-top:10px; margin-right: 5px;float:left;" type="button">راهنمای
            کاربری
            سیستم ثبت الکترونیکی پیشنهادات</a>
        <span style="margin-top:10px; margin-right: 5px;float:left;">|</span>
        <a href='/extra/helpElhaghie(Edition%201).pdf' style="margin-top:10px; margin-right: 5px;float:left;"
           type="button">
            راهنماي الحاقيه
        </a>
        <span style="margin-top:10px; margin-right: 5px;float:left;">|</span>
    </sec:authorize>
    <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN,ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">
        <a href='javascript:showLegend();' style="margin-top:10px; margin-right: 5px;float:left;" type="button">راهنمای
            رنگ
            ها</a>
    </sec:authorize>


</div>
<div id="tabs">
<ul>
    <sec:authorize
            ifAnyGranted="ROLE_BAZARYAB,ROLE_SUPERVISOR,ROLE_ADMIN,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_PEZESHK,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI,ROLE_NAMAYANDE,ROLE_JAMI_KHAS,ROLE_KARSHENAS_NAZER">
        <li id="litabs1"><a href="#tabs-1"
                            onclick="prepareToSearchForPishnehadsAsl();doAjaxPost('resultTab1.action','#displayTab1',false);clearSeachFrom();">پیشنهادهای
            من</a><span class="ui-icon ui-icon-refresh" onclick="doAjaxPost('resultTab1.action','#displayTab1',true);" style="float:right;margin-top:5px;"></span></li>
    </sec:authorize>
    <%--<sec:authorize ifNotGranted="ROLE_PAS_KARSHENAS_MASOUL,ROLE_PAS_KARSHENAS,ROLE_PAS_RAEIS,ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">--%>
    <sec:authorize
            ifAnyGranted="ROLE_BAZARYAB,ROLE_SUPERVISOR,ROLE_ADMIN,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_PEZESHK,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI,ROLE_NAMAYANDE,ROLE_JAMI_KHAS,ROLE_KARSHENAS_NAZER">
        <li id="litabs2"><a href="#tabs-2"
                            onclick="prepareToSearchForPishnehadsView();doAjaxPost('resultTab2.action','#displayTab2',false);clearSeachFrom();">همه
            پیشنهادها</a><span class="ui-icon ui-icon-refresh"  onclick="doAjaxPost('resultTab2.action','#displayTab2',true);" style="float:right;margin-top:5px;"></span></li>
    </sec:authorize>
    <sec:authorize
            ifAnyGranted="ROLE_LIMITED_AMIN_PARSIAN,ROLE_TAGHIR_CODE_DAEM,ROLE_KARSHENAS_KHESARAT,ROLE_KARSHENAS_MASOUL_KHESARAT,ROLE_RAEIS_KHESARAT,ROLE_NAMAYANDE,ROLE_ADMIN,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI,ROLE_PAS_KARSHENAS_MASOUL,ROLE_PAS_KARSHENAS,ROLE_PAS_RAEIS,ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE,ROLE_KARSHENAS_KHESARAT,ROLE_MOSHAHEDE_BIMENAME">
        <li id="litabs3">
            <a <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN"> onclick="doAjaxPost('resultTab3.action','#displayTab3',false);clearSeachFrom_b();"</sec:authorize > href="#tabs-3">بیمه نامه ها</a>
            <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN"><span class="ui-icon ui-icon-refresh" onclick="doAjaxPost('resultTab3.action','#displayTab3',true);" style="float:right;margin-top:5px;"></span></sec:authorize>
        </li>
    </sec:authorize>
    <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN,ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE,ROLE_BAZARYAB,ROLE_MOSHAHEDE_BIMENAME"> <!--in role ha tabhaye zir ra nemibinand-->
        <li id="litabs4"><a onclick="doAjaxPost('resultTab4.action','#displayTab4',false);" href="#tabs-4">درخواست های در جریان من</a><span class="ui-icon ui-icon-refresh" onclick="doAjaxPost('resultTab4.action','#displayTab4',true);" style="float:right;margin-top:5px;"></span></li>
        <li id="litabs8"><a onclick="doAjaxPost('resultTab8.action','#displayTab8',false);" href="#tabs-8">همه درخواست های درجریان</a><span class="ui-icon ui-icon-refresh" onclick="doAjaxPost('resultTab8.action','#displayTab8',true);" style="float:right;margin-top:5px;"></span></li>
        <li id="litabs5"><a onclick="doAjaxPost('resultTab5.action','#displayTab5',false);" href="#tabs-5">الحاقیه ها</a><span class="ui-icon ui-icon-refresh" onclick="doAjaxPost('resultTab5.action','#displayTab5',true);" style="float:right;margin-top:5px;"></span></li>
        <li id="litabs6"><a onclick="doAjaxPost('resultTab6.action','#displayTab6',false);" href="#tabs-6">خسارت ها</a><span class="ui-icon ui-icon-refresh" onclick="doAjaxPost('resultTab6.action','#displayTab6',true);" style="float:right;margin-top:5px;"></span></li>
        <li id="litabs9"><a onclick="doAjaxPost('resultTab9.action','#displayTab9',false);" href="#tabs-9">بهره مندی از منافع</a><span class="ui-icon ui-icon-refresh" onclick="doAjaxPost('resultTab9.action','#displayTab9',true);" style="float:right;margin-top:5px;"></span></li>
    </sec:authorize>
    <sec:authorize
            ifAnyGranted="ROLE_NAMAYANDE,ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI,ROLE_KARSHENAS_SODUR,ROLE_JAMI_KHAS,ROLE_SUPERVISOR">
        <li id="litabs7"><a href="#tabs-7" onclick="prepareToSearch7();doAjaxPost('resultTab7.action','#displayTab7',false);">قراردادها</a><span class="ui-icon ui-icon-refresh" onclick="doAjaxPost('resultTab7.action','#displayTab7',true);" style="float:right;margin-top:5px;"></span></li>
    </sec:authorize>
    <sec:authorize ifAnyGranted="ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">
        <li id="litabs11"><a href="#tabs-11">کارمــزد</a></li>
    </sec:authorize>
    <sec:authorize ifAnyGranted="ROLE_KARMOZD">
        <li id="litabs12"><a href="#tabs-12">لیست سیاه</a></li>
    </sec:authorize>
    <sec:authorize ifAnyGranted="ROLE_KARMOZD">
        <li id="litabs13"><a href="#tabs-13">نماینده های ارشد</a></li>
    </sec:authorize>
</ul>

<%
    String pagingParams = "";
%>
<sec:authorize ifNotGranted="ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">
    <%
        pagingParams += PagingUtil.prifixForOldValue + ConstantPaging.darkhatsthayeDarJaryanPageNumber + "="
                + ((PaginatedListImpl) request.getAttribute("darkhastsVMPaginatedList")).getPageNumber();
        pagingParams += "&";
        pagingParams += PagingUtil.prifixForOldValue + ConstantPaging.allDarkhatsthayeDarJaryanPageNumber + "="
                + ((PaginatedListImpl) request.getAttribute("alldarkhastsPgList")).getPageNumber();
        pagingParams += "&";
        pagingParams += PagingUtil.prifixForOldValue + ConstantPaging.elhaghiyehaPageNumber + "="
                + ((PaginatedListImpl) request.getAttribute("elhaghiyeha")).getPageNumber();
        pagingParams += "&";
        pagingParams += PagingUtil.prifixForOldValue + ConstantPaging.gharardadhaPageNumber + "="
                + ((PaginatedListImpl) session.getAttribute("gharardadha")).getPageNumber();
        pagingParams += "&";
        pagingParams += PagingUtil.prifixForOldValue + ConstantPaging.reportResultPageNumber + "="
                + ((PaginatedListImpl) request.getAttribute("pishnehadsVMPaginatedList")).getPageNumber();
        pagingParams += "&";
        pagingParams += PagingUtil.prifixForOldValue + ConstantPaging.viewReportResultPageNumber + "="
                + ((PaginatedListImpl) request.getAttribute("allPishnehadsVMPaginatedList")).getPageNumber();
        pagingParams += "&";
    %>
</sec:authorize>
<%
    pagingParams += PagingUtil.prifixForOldValue + ConstantPaging.bimenamehaPageNumber + "="
            + ((PaginatedListImpl) request.getAttribute("bimenameVMPaginatedList")).getPageNumber();
    pageContext.setAttribute("pagingParams", pagingParams);
%>
<sec:authorize ifAnyGranted="ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">
    <div id="tabs-11">
        <div class=expandableTitleBar>
            <div class="staticTitleBar" id="karmozd_t_b" style="width:98%;direction:rtl;margin:0 auto;">
                <sec:authorize ifAnyGranted="ROLE_KARMOZD">
                    <div style="display:none;" id="crt_prj_div">
                        <form id="crt_prj_frm" action="/createProjectKarmozd.action" method="post">
                            <table class="inputList" width="90%">
                                <tr>
                                    <td>
                                        <span class="noThing"></span>
                                        <input type="text" name="karmozd.onvan" id="crt_prj_onvan"/>
                                        &nbsp;<label>عنوان</label>
                                    </td>
                                    <td>
                                        <span class="noThing"></span>
                                        <input type="text" name="karmozd.description" id="crt_prj_desc"/>
                                        &nbsp;<label>شرح</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="text" name="azTarikhVosoli" id="crt_prj_az_vosoliDate"
                                               class="validate[required,custom[date]] datePkr"/>
                                        &nbsp;<label>وصولی از تاریخ</label>
                                    </td>
                                    <td>
                                        <input type="text" name="taTarikhVosoli" id="crt_prj_ta_vosoliDate"
                                               class="validate[required,custom[date]] datePkr"/>
                                        &nbsp;<label>وصولی تا تاریخ</label>
                                    </td>

                                </tr>
                                <tr>
                                    <td>
                                        <span class="noThing"></span>
                                        <select id="crt_prj_type" name="karmozd.type">
                                            <option value="Karmozd_Pardakhti">کارمزد پرداختی</option>
                                            <option value="Karmozd_Vosuli">کارمزد وصولی</option>
                                            <option value="Karmozd_Pooshesh_Ezafi">کارمزد پوشش های اضافه</option>
                                            <option value="Karmozd_Seniors">کارمزد نمایندگان ارشد</option>
                                            <option value="Karmozd_Tashvighi_Vosuli">کارمزد تشویقی وصولی</option>
                                        </select>
                                        <label>نوع پروژه </label>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <%--<div style="display:none;" id="crt_prj_div_A">--%>
                        <%--<form id="crt_prj_frm_A" action="/createProjectKarmozd.action" method="post">--%>
                            <%--<table class="inputList" width="90%" dir="rtl">--%>
                                <%--<tr>--%>
                                    <%--<td>--%>
                                        <%--<span class="noThing"></span>--%>
                                        <%--&nbsp;<label>عنوان</label>--%>
                                        <%--<input type="text" name="karmozd.onvan" id="crt_prj_onvan_A" style="width: 250px;"/>--%>
                                    <%--</td>--%>
                                    <%--<td>--%>
                                        <%--<span class="noThing"></span>--%>
                                        <%--&nbsp;<label>شرح</label>--%>
                                        <%--<input type="text" name="karmozd.description" id="crt_prj_desc_A" style="width: 300px;"/>--%>
                                    <%--</td>--%>
                                <%--</tr>--%>
                                <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
                                        <%--<input type="hidden" name="azTarikhVosoli" id="crt_prj_az_vosoliDate_A"/>--%>
                                    <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
                                        <%--<input type="hidden" name="taTarikhVosoli" id="crt_prj_ta_vosoliDate_A"/>--%>
                                    <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
                                        <%--<input type="hidden" name="karmozdProjectId" id="crt_prj_id_A"/>--%>
                                    <%--&lt;%&ndash;</td>&ndash;%&gt;--%>

                                <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
                                <%--<tr>--%>
                                    <%--<td>--%>
                                        <%--<span class="noThing"></span>--%>
                                        <%--<label>نوع پروژه </label>--%>
                                        <%--<select id="crt_prj_type_A" name="karmozd.type" style="width: 260px;">--%>
                                            <%--<option value="Karmozd_Seniors">کارمزد نمایندگان ارشد</option>--%>
                                        <%--</select>--%>
                                     <%--</td>--%>
                                <%--</tr>--%>
                            <%--</table>--%>
                        <%--</form>--%>
                    <%--</div>--%>
                    <c:if test="${isThereNotFinal==false}">
                        <input type="button" onclick="loadCreateProject();" value="ایجاد پروژه کارمزد جدید"/>
                    </c:if>
                </sec:authorize>
                <script type="text/javascript">
                    function makeView(btn,id)
                    {
                        $.ajax
                        (
                            {
                                type: "POST",
                                url: '/makeViewForNamayande.action?karmozdProjectId='+id,
                                success: function (response)
                                {
                                    if (btn.value == 'نمایش برای نمایندگان')
                                    {
                                        btn.value = 'عدم نمایش برای نمایندگان';
                                    }
                                    else
                                    {
                                        btn.value = 'نمایش برای نمایندگان'
                                    }
                                }
                            }
                        );
                    }
                </script>
                <display:table export="true" id="tblListKarmozd" uid="row" htmlId="tblListKarmozd"
                               name="paginatedListKarmozds.list" partialList="true" clearStatus="true"
                               keepStatus="false"
                               style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
                               size="${paginatedListKarmozds.fullListSize}"
                               pagesize="${paginatedListKarmozds.objectsPerPage}">
                    <sec:authorize ifAllGranted="ROLE_KARMOZD_NAMAYANDE">
                        <%--<c:if test="${row.id!=7490354 && row.id!=7493159}">--%>
                        <c:if test="${row.viewForNamayande!=null}">
                            <display:column title="نمايش">
                                <a target="_blank" href="/viewKarmozdProject.action?karmozd.id=${row.id}">نمایش</a>
                            </display:column>
                            <display:column title="رديف" >${row_rowNum}</display:column>
                            <display:column  title="عنوان">${row.onvan}</display:column>
                            <display:column  title="شرح">${row.description}</display:column>
                            <display:column  title="وصولي از تاريخ" >${row.azTarikhVosoli}</display:column>
                            <display:column  title="وصولي تا تاريخ" >${row.taTarikhVosoli}</display:column>
                            <%--<display:column title="کاربر" property="user.fullName"></display:column>--%>
                            <display:column title="نوع پروژه" property="typeFarsi"></display:column>
                            <display:column title="شماره سريال" >${row.serial}</display:column>
                        </c:if>
                    </sec:authorize>
                    <sec:authorize ifAllGranted="ROLE_KARMOZD">
                        <display:column title="نمايش">
                            <a target="_blank" href="/viewKarmozdProject.action?karmozd.id=${row.id}">نمایش</a>
                        </display:column>
                        <display:column title="رديف">${row_rowNum}</display:column>
                        <display:column property="onvan" title="عنوان"></display:column>
                        <display:column property="description" title="شرح"></display:column>
                        <display:column title="وصولي از تاريخ" property="azTarikhVosoli"></display:column>
                        <display:column title="وصولي تا تاريخ" property="taTarikhVosoli"></display:column>
                        <display:column title="کاربر" property="user.fullName"></display:column>
                        <display:column title="نوع پروژه" property="typeFarsi"></display:column>
                        <display:column title="شماره سريال" property="serial"></display:column>
                        <display:column style="white-space: nowrap;"  title="نمایش برای نمایندگی ها" >
                            <input type="button" onclick="makeView(this,${row.id});"
                               <c:if test ="${row.viewForNamayande==null}">value="نمایش برای نمایندگان"</c:if>
                               <c:if test ="${row.viewForNamayande!=null}">value="عدم نمایش برای نمایندگان"</c:if>
                            />
                        </display:column>

                        <%--<display:column>--%>
                            <%--<c:if test="${row.type=='Karmozd_Pardakhti' && row.karmozdSeniors==null}">--%>
                                <%--<input type="button" onclick="createSeniorKarmozdProj(${row.id},'${row.azTarikhVosoli}','${row.taTarikhVosoli}','${row.serial}','${row.status}');" value="پروژه کارمزد نمایندگان ارشد"/>--%>
                            <%--</c:if>--%>
                        <%--</display:column>--%>
                    </sec:authorize>
                </display:table>
            </div>
        </div>
    </div>
    <sec:authorize ifAnyGranted="ROLE_KARMOZD">
        <div id="tabs-12">
            <div class=expandableTitleBar>
                <div class="staticTitleBar" id="black_list_t_b" style="width:98%;direction:rtl;margin:0 auto;">
                    <div style="display:none;" id="add_black_list_div">
                        <form id="add_black_list_frm" action="/addToBlackList.action" method="post">
                            <table class="inputList" width="90%">
                                    <%--<tr>--%>
                                    <%--<td>--%>
                                    <%--<span class="noThing"></span>--%>
                                    <%--<input type="text" name="" id="add_black_list_onvan"/>--%>
                                    <%--&nbsp;<label>عنوان</label>--%>
                                    <%--</td>--%>
                                    <%--</tr>--%>
                                <tr>
                                    <td>
                                        <span class="noThing"></span>
                                        <select  name="typeBlackList" id="add_black_list_type" >
                                            <option onclick="changeTypeBlackList();" value="Pardakhti">کارمزد پرداختی</option>
                                            <option onclick="changeTypeBlackList();" value="Vosuli">کارمزد وصولی</option>
                                        </select>
                                        &nbsp;<label>نوع لیست سیاه</label>
                                    </td>
                                    <td id="gruop_td" style="display: none;">
                                        <div class="dblRadio" style="">
                                            <input type="checkbox" style="float:left;" value="yes"
                                                   name="batch" id="batch_chk" title="" onclick="groupOrshomareBimename()"/>
                                            <label for="batch_chk" style="float:left;width:100px">گروهی</label>

                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td id="blk_list_shomare_bim">
                                        <span class="noThing"></span>
                                        <input type="text" name="shomareBimename" id="add_black_list_shomare_bimename"/>
                                        &nbsp;<label>شماره بیمه نامه </label>
                                    </td>
                                    <td id="blk_list_grp_bim" style="display: none;">
                                        <%
                                            if (request.getAttribute("grouhHa") != null)
                                            {
                                        %>
                                        <span class="noThing"></span>
                                        <select id="bimename_goroh" name="groupId">
                                            <%
                                                for (Gharardad goroh : (List<Gharardad>) request.getAttribute("grouhHa"))
                                                { %>
                                            <option value="<%=goroh.getId() == null ? "" : goroh.getId()%>">
                                                <%=goroh.getNameSherkat()%>
                                            </option>
                                            <%}%>
                                        </select>
                                        &nbsp;<label>گروه بيمه‌نامه</label>
                                        <%}%>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <input type="button" onclick="loadAddToBlackList();" value="+افزودن به لیست سیاه"/>
                    <display:table export="false" id="tblBlackList" uid="row" htmlId="tblBlackList"
                                   name="paginatedtblBlackList.list" partialList="true" clearStatus="true"
                                   keepStatus="false"
                                   style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
                                   size="${paginatedtblBlackList.fullListSize}"
                                   pagesize="${paginatedtblBlackList.objectsPerPage}">
                        <display:column title="ردیف">${row_rowNum}</display:column>
                        <display:column title="شماره بیمه نامه" property="bimename.shomare"
                                        style="white-space: nowrap;"/>
                        <display:column property="bimename.tarikhSodour" title="تاریخ صدور"/>
                        <display:column property="bimename.tarikhEngheza" title="تاریخ انقضا"/>
                        <display:column property="bimename.state.stateName" title="وضعیت"/>
                        <display:column property="bimename.pishnehad.bimeGozar.shakhs.fullName" title="نام بیمه گذار"/>
                        <display:column property="bimename.pishnehad.bimeShode.shakhs.fullName" title="نام بیمه شده"/>
                        <display:column property="bimename.pishnehad.namayande.name_kod" title="نام و کد نماینده"/>
                        <display:column property="bimename.pishnehad.namayande.vahedSodur.name" title="واحد صدور"/>
                        <display:column property="bimename.pishnehad.karshenas.fullName" title="کارشناس صدور"/>
                        <display:column property="bimename.pishnehad.karshenas.personalCode" title="کد کارشناس"/>
                        <display:column property="typeFarsi" title="نوع لیست سیاه"/>
                    </display:table>
                </div>
            </div>
        </div>
        <div id="tabs-13">
            <div class=expandableTitleBar>
                <div class="staticTitleBar" id="arshad_list" style="width:98%;direction:rtl;margin:0 auto;">
                    <div style="display:none;" id="add_senior_div">
                        <form id="add_senior_frm" action="/addSenior.action" method="post">
                            <table class="inputList" width="90%">
                                <tr>
                                    <td>
                                        <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('seniorId','seniorName', '');" style="float:left;" title="جستجو"></span>
                                        &nbsp;<label>نمايندگي</label>
                                        <input type="hidden" name="seniorId" id="seniorId" />
                                        <input type="text"  id="seniorName" readonly="readonly" class="validate[required] text-input"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <div style="display:none;" id="add_senior_subset_div">
                        <form id="add_senior_subset_frm" action="addSeniorSubset.action" method="post">
                            <table class="inputList" width="90%">
                                <tr>
                                    <td>
                                        &nbsp;<label>تاريخ شروع قرارداد </label>
                                        <input type="text" name="contractDateFrom" id="contract_date_from"class="datePkr"/>
                                    </td>
                                    <td>
                                        &nbsp;<label>تاريخ پايان قرارداد </label>
                                        <input type="text" name="contractDateTo" id="contract_Date_to"class="datePkr"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('subsetId','subsetName', 'subsetCode');" style="float:left;" title="جستجو"></span>
                                        &nbsp;<label>نمايندگي</label>
                                        <input type="hidden" name="namayande.id" id="subsetId" />
                                        <input type="hidden" name="namayande.kodeNamayandeKargozar" id="subsetCode" />
                                        <input type="text"  id="subsetName" readonly="readonly" class="validate[required] text-input"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <div style="display:none;" id="load_senior_subset_div">
                        <form id="load_senior_subset_frm" action="" method="post">
                            <table class="inputList" width="90%">

                                <tr>
                                    <td>
                                        <span class="help ui-icon ui-icon-search"
                                              onclick="fillNamayandegi('subsetId_l','subsetName_l', '');"
                                              style="float:left;" title="جستجو"></span>
                                        &nbsp;<label>نمايندگي</label>
                                        <input type="hidden" name="namayande.id" id="subsetId_l"/>
                                        <input type="text" id="subsetName_l" readonly="readonly" class="validate[required] text-input"/>
                                    </td>
                                </tr>
                            </table>
                            <br/><div id="rslt_load"></div>
                        </form>

                    </div>
                    <div style="display:none;" id="view_subsets_div"></div>
                     <%--<span class="help ui-icon ui-icon-search"onclick="loadSeniorOfNamayande();" style="float:right;" title="جستجوی نماینده های فروش"></span>--%>
                    <input type="button"  onclick="loadSeniorOfNamayande();" value="جستجوی نمایندگان فروش"/>
                    <input type="button" onclick="createSenior();" value="+ نماينده ارشد جديد"/>
                    <display:table export="false" id="tblBlackList" uid="row13" htmlId="tblBlackList"
                                   name="PaginatedListSeniors.list" partialList="true" clearStatus="true"
                                   keepStatus="false"
                                   style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
                                   size="${PaginatedListSeniors.fullListSize}"
                                   pagesize="${PaginatedListSeniors.objectsPerPage}">
                        <display:column title="رديف">${row13_rowNum}</display:column>
                        <display:column title="نام نمايندگي" property="name" style="white-space: nowrap;"/>
                        <display:column property="kodeNamayandeKargozar" title="کد نماينده کارگزار"/>
                        <display:column  title="نمايش زيرمجموعه ها">
                            <input type="button" value="..." onclick="viewSubsetList(${row13.id});"/>
                        </display:column>

                        <display:column  title="افزودن زيرمجموعه جديد">
                            <input type="button" value="+" onclick="createSeniorSubset(${row13.id});"/>
                        </display:column>
                    </display:table>
                </div>
            </div>
        </div>
    </sec:authorize>
</sec:authorize>
<%--<sec:authorize ifNotGranted="ROLE_BAZARYAB,ROLE_PAS_KARSHENAS_MASOUL,ROLE_PAS_KARSHENAS,ROLE_PAS_RAEIS,ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">--%>
<sec:authorize
        ifAnyGranted="ROLE_BAZARYAB,ROLE_SUPERVISOR,ROLE_ADMIN,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_PEZESHK,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI,ROLE_NAMAYANDE,ROLE_JAMI_KHAS,ROLE_KARSHENAS_NAZER">
    <div id="tabs-1">
        <div class="expandableTitleBar" id="expandableAsl2">
            <p class="heading">
                <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                جستجو
            </p>

            <div class="content" style="display:none;" id="searchAslContent">
                <%@include file="/jsp/pishnahad/search.jsp" %>
            </div>
        </div>
        <div id="displayTab1" class="staticTitleBar">
            <%@include file="/jsp/fehrestePishnahad/resultFehrestepishnahad_tab1.jsp" %>
        </div>
    </div>
</sec:authorize>

<%--<sec:authorize ifNotGranted="ROLE_PAS_KARSHENAS_MASOUL,ROLE_PAS_KARSHENAS,ROLE_PAS_RAEIS,ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">--%>
<sec:authorize
        ifAnyGranted="ROLE_BAZARYAB,ROLE_SUPERVISOR,ROLE_ADMIN,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_PEZESHK,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI,ROLE_NAMAYANDE,ROLE_JAMI_KHAS,ROLE_KARSHENAS_NAZER">
    <div id="tabs-2">
        <div class=expandableTitleBar>
            <div class="staticTitleBar" id="viewSearchResult" style="width:98%;direction:rtl;margin:0 auto;">
                <div class="expandableTitleBar" id="expandableView3">
                    <p class="heading">
                        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                        جستجو
                    </p>

                    <div class="content" style="display:none;" id="searchViewContent">
                        <%@include file="/jsp/pishnahad/search.jsp" %>
                    </div>
                </div>
                <div class="staticTitleBar" id="viewresultmoshahede" style="direction:rtl;">
                    <div id="displayTab2" class="staticTitleBar">
                        <%@include file="/jsp/fehrestePishnahad/resultFehrestepishnahad_tab2.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>
<sec:authorize
        ifAnyGranted="ROLE_LIMITED_AMIN_PARSIAN,ROLE_TAGHIR_CODE_DAEM,ROLE_KARSHENAS_KHESARAT,ROLE_KARSHENAS_MASOUL_KHESARAT,ROLE_RAEIS_KHESARAT,ROLE_NAMAYANDE,ROLE_ADMIN,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI,ROLE_PAS_KARSHENAS_MASOUL,ROLE_PAS_KARSHENAS,ROLE_PAS_RAEIS,ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE,ROLE_KARSHENAS_KHESARAT" ifNotGranted="ROLE_MOSHAHEDE_BIMENAME">
    <div id="tabs-3">
        <div class=expandableTitleBar>
            <div class="staticTitleBar" id="viewresultbimenameid" style="direction:rtl;">
                <div class="expandableTitleBar" id="expandableViewid">
                    <p class="heading">
                        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                        جستجو
                    </p>

                    <div class="content" style="display:none;" id="searchBimenameContentid">
                        <%@include file="/jsp/bimename/search.jsp" %>
                    </div>
                </div>
                <div class="staticTitleBar" style="direction:rtl;">
                    <div id="iddisplayTab3" class="staticTitleBar">
                        <%@include file="/jsp/fehrestePishnahad/resultFehrestepishnahad_tab3.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>

<sec:authorize
        ifAnyGranted="ROLE_MOSHAHEDE_BIMENAME">
    <div id="tabs-3">
        <div class=expandableTitleBar>
            <div class="staticTitleBar" id="viewresultbimename" style="direction:rtl;">
                <div class="expandableTitleBar" id="expandableView">
                    <p class="heading">
                        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                        جستجو
                    </p>

                    <div class="content" style="display:none;" id="searchBimenameContent">
                        <%@include file="/jsp/bimename/search.jsp" %>
                    </div>
                </div>
                <div class="staticTitleBar" style="direction:rtl;">
                    <div id="displayTab3" class="staticTitleBar">
                        <%@include file="/jsp/fehrestePishnahad/resultFehrestepishnahad_tab3.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN,ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE,ROLE_BAZARYAB,ROLE_MOSHAHEDE_BIMENAME">
    <div id="tabs-4">
        <div class=expandableTitleBar>
            <div class="staticTitleBar" id="viewresultdarkhast" style="direction:rtl;">
                <div class="expandableTitleBar" id="expandableView1">
                    <p class="heading">
                        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                        جستجو
                    </p>

                    <div class="content" style="display:none;" id="searchDarkhastContent">
                        <%@include file="/jsp/fehrestePishnahad/searchDarkhast.jsp" %>
                    </div>
                </div>
                <div class="staticTitleBar" style="direction:rtl;">
                    <div id="displayTab4" class="staticTitleBar">
                        <%@include file="/jsp/fehrestePishnahad/resultFehrestepishnahad_tab4.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="tabs-8">
        <div class=expandableTitleBar>
            <div class="expandableTitleBar" id="expandableView7">
                <p class="heading">
                    <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                    جستجو
                </p>

                <div class="content" style="display:none;" id="searchViewContent7">
                    <%@include file="/jsp/fehrestePishnahad/searchAllDarkhast.jsp" %>
                </div>
            </div>
            <div class="staticTitleBar" id="allDarkhasts" style="direction:rtl;">
                <div id="displayTab8" class="staticTitleBar">
                    <%@include file="/jsp/fehrestePishnahad/resultFehrestepishnahad_tab8.jsp" %>
                </div>
            </div>
        </div>
    </div>

    <div id="tabs-5">
        <div class=expandableTitleBar>
            <div class="expandableTitleBar" id="expandableView5">
                <p class="heading">
                    <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                    جستجو
                </p>

                <div class="content" style="display:none;" id="searchViewContent5">
                    <%@include file="/jsp/elhaghie/searchElhaghie.jsp" %>
                </div>
            </div>
            <div class="staticTitleBar" id="viewresultelhaghiye" style="direction:rtl;">
                <div id="displayTab5" class="staticTitleBar">
                    <%@include file="/jsp/fehrestePishnahad/resultFehrestepishnahad_tab5.jsp" %>
                </div>
            </div>
        </div>
    </div>

    <div id="tabs-6">
        <div class=expandableTitleBar>
            <div class="expandableTitleBar" id="expandableView6">
                <p class="heading">
                    <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                    جستجو
                </p>

                <div class="content" style="display:none;" id="searchViewContent6">
                    <%@include file="/jsp/fehrestePishnahad/searchKhesarats.jsp" %>
                </div>
            </div>
            <div class="staticTitleBar" id="viewresultKhesarat" style="direction:rtl;">
                <div id="displayTab6" class="staticTitleBar">
                    <%@include file="/jsp/fehrestePishnahad/resultFehrestepishnahad_tab6.jsp" %>
                </div>
            </div>
        </div>
    </div>
    <div id="tabs-9">
        <div class=expandableTitleBar>
            <div class="expandableTitleBar" id="expandableView9">
                <p class="heading">
                    <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                    جستجو
                </p>

                <div class="content" style="display:none;" id="searchViewContent9">
                    <%@include file="/jsp/bimename/searchBahreMandi.jsp" %>
                </div>
            </div>
            <div class="staticTitleBar" id="viewresultBahreMandi" style="direction:rtl;">
                <div id="displayTab9" class="staticTitleBar">
                    <%@include file="/jsp/fehrestePishnahad/resultFehrestepishnahad_tab9.jsp" %>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>
<sec:authorize
        ifAnyGranted="ROLE_NAMAYANDE,ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI,ROLE_KARSHENAS_SODUR,ROLE_JAMI_KHAS,ROLE_SUPERVISOR">
    <div id="tabs-7">
        <div class=expandableTitleBar>
            <div class="expandableTitleBar" id="expandableView2">
                <p class="heading">
                    <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                    جستجو
                </p>

                <div class="content" style="display:none;" id="searchGharardadContent">
                    <%@include file="/jsp/gharardad/search_gharardad.jsp" %>
                </div>
            </div>
            <div class="staticTitleBar" id="viewresultGharardad" style="direction:rtl;">
                <div id="displayTab7" class="staticTitleBar">
                    <%@include file="/jsp/fehrestePishnahad/resultFehrestepishnahad_tab7.jsp" %>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>

</div>

<div id="p_popup" style="display:none;">
    <textarea rows="5" cols="40" name="logmessage" id="loggmessage"></textarea>
</div>
<form action="makeTransition" id="log_message" method="post" accept-charset="UTF-8">
    <input type="hidden" name="pishnehadId" id="pishnehadId"/>
    <input type="hidden" name="transitionId" id="transitionId"/>
    <input type="hidden" name="logmessage" id="logmessage"/>
</form>
<div id="addCommetnDiv" class="vld" style="display:none;direction:rtl;">
    <form id="addCommetnForm">
        <table style="width:600px;border-spacing:10px" align="center" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td colspan="4">
                    <div id="mesg" style="display:none;" class="successMessage">نظر شما با موفقیت ثبت شد.</div>
                </td>
            </tr>
            <tr>
                <td>&nbsp;<label>نام و نام خانوادگی</label></td>
                <td><input type="text" class="validate[required,custom[onlyPersianLetter]] text-input"
                           name="userComment.userName" id="userComment_userName"/></td>
                <td>&nbsp;<label>پست الکترونیک</label></td>
                <td><input type="text" class="validate[required,custom[email]] text-input" name="userComment.email"
                           id="userComment_email"/></td>
            </tr>
            <tr>
                <td>
                    &nbsp;<label>کد نمایندگی</label>
                </td>
                <td>
                    <input type="text" class="validate[required] text-input" name="userComment.codeNamayandegi"
                           id="userComment_codeNamayandegi" readonly="readonly"
                           value="${user.namayandegi.kodeNamayandeKargozar}"/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;<label>نظر</label></td>
                <td colspan="3"><textarea class="validate[required] text-input" name="userComment.nazar"
                                          id="userComment_nazar" rows="5" cols="68" style="width:92%;"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<div id="legendBox" class="vld" style="display:none;direction:rtl;">
    <table style="margin: 0 auto;" cellspacing="4" cellpadding="4">
        <fmt-rt:setBundle basename="com.bitarts.parsian.i18n.enumTypes_fa"/>
        <tbody>
        <tr>
            <td style="background:#99ccff">کارشناس صدور</td>
        </tr>
        <tr>
            <td style="background:#99cc66">کارشناس مسئول</td>
        </tr>
        <tr>
            <td style="background:#ffcc99">پزشک</td>
        </tr>
        <tr>
            <td style="background:#cc99ff">رئیس صدور</td>
        </tr>
        <tr>
            <td style="background:#cccccc">کارشناس بایگانی</td>
        </tr>
        <tr>
            <td style="background:#ffb6c1">نماینده</td>
        </tr>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    $(function () {
        $("#tabs").tabs({
            selected: 0
        });
    });
    <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN">
        $(document).ready(function () {
        var hasRoleBazaryab = "${hasRoleBazaryab}";
//        if (parseInt(hasRoleBazaryab) == 1) {
//            prepareToSearchForPishnehadsView();
//        } else {
            var selectedTab = "${selectedTab}";
            if (selectedTab == "tabs-1") {
                prepareToSearchForPishnehadsAsl();
                $("#tabs").tabs("select", "#tabs-1");
                doAjaxPost('resultTab1.action','#displayTab1',true);
            } else if (selectedTab == "tabs-2") {
                prepareToSearchForPishnehadsView();
                $("#tabs").tabs("select", "#tabs-2");
                if (document.getElementById('displayTab2').getElementsByClassName('empty').length > 0)
                    doAjaxPost('resultTab2.action','#displayTab2',true);
            } else if (selectedTab == "tabs-3") {
                $("#tabs").tabs("select", "#tabs-3");
                if (document.getElementById('displayTab3').getElementsByClassName('empty').length > 0)
                    doAjaxPost('resultTab3.action','#displayTab3',true);
            }
            else if (selectedTab == "tabs-4") {
                $("#tabs").tabs("select", "#tabs-4");
                if (document.getElementById('displayTab4').getElementsByClassName('empty').length > 0)
                    doAjaxPost('resultTab4.action','#displayTab4',true);
            }

            else if (selectedTab == "tabs-5") {
                $("#tabs").tabs("select", "#tabs-5");
                if (document.getElementById('displayTab5').getElementsByClassName('empty').length > 0)
                    doAjaxPost('resultTab5.action','#displayTab5',true);
            }

            else if (selectedTab == "tabs-6") {
                $("#tabs").tabs("select", "#tabs-6");
                if (document.getElementById('displayTab6').getElementsByClassName('empty').length > 0)
                    doAjaxPost('resultTab6.action','#displayTab6',true);
            }

            else if (selectedTab == "tabs-7") {
                prepareToSearchForPishnehadsView();
                $("#tabs").tabs("select", "#tabs-7");
                if (document.getElementById('displayTab7').getElementsByClassName('empty').length > 0)
                    doAjaxPost('resultTab7.action','#displayTab7',true);
            }
            else if (selectedTab == "tabs-8") {
                $("#tabs").tabs("select", "#tabs-8");
                if (document.getElementById('displayTab8').getElementsByClassName('empty').length > 0)
                    doAjaxPost('resultTab8.action','#displayTab8',true);
            }
            else if (selectedTab == "tabs-9") {
                $("#tabs").tabs("select", "#tabs-9");
                if (document.getElementById('displayTab9').getElementsByClassName('empty').length > 0)
                    doAjaxPost('resultTab9.action','#displayTab9',true);
            }
            else if (selectedTab == "tabs-13") {
                $("#tabs").tabs("select", "#tabs-13");
            }
            else {
                if (document.getElementById('tabs-1') != null){
                    $("#tabs").tabs("select", "#tabs-1");
                    if (document.getElementById('displayTab1').getElementsByClassName('empty').length > 0)
                        doAjaxPost('resultTab1.action','#displayTab1',true);
                } else if (document.getElementById('tabs-2') != null){
                    $("#tabs").tabs("select", "#tabs-2");
                    if (document.getElementById('displayTab2').getElementsByClassName('empty').length > 0)
                        doAjaxPost('resultTab2.action','#displayTab2',true);
                } else if (document.getElementById('tabs-3') != null){
                    $("#tabs").tabs("select", "#tabs-3");
                    if (document.getElementById('displayTab3').getElementsByClassName('empty').length > 0)
                        doAjaxPost('resultTab3.action','#displayTab3',true);
                } else if (document.getElementById('tabs-4') != null){
                    $("#tabs").tabs("select", "#tabs-4");
                    if (document.getElementById('displayTab4').getElementsByClassName('empty').length > 0)
                        doAjaxPost('resultTab4.action','#displayTab4',true);
                } else if (document.getElementById('tabs-5') != null){
                    $("#tabs").tabs("select", "#tabs-5");
                    if (document.getElementById('displayTab5').getElementsByClassName('empty').length > 0)
                        doAjaxPost('resultTab5.action','#displayTab5',true);
                } else if (document.getElementById('tabs-6') != null){
                    $("#tabs").tabs("select", "#tabs-6");
                    if (document.getElementById('displayTab6').getElementsByClassName('empty').length > 0)
                        doAjaxPost('resultTab6.action','#displayTab6',true);
                } else if (document.getElementById('tabs-7') != null){
                    $("#tabs").tabs("select", "#tabs-7");
                    if (document.getElementById('displayTab7').getElementsByClassName('empty').length > 0)
                        doAjaxPost('resultTab7.action','#displayTab7',true);
                } else if (document.getElementById('tabs-8') != null){
                    $("#tabs").tabs("select", "#tabs-8");
                    if (document.getElementById('displayTab8').getElementsByClassName('empty').length > 0)
                        doAjaxPost('resultTab8.action','#displayTab8',true);
                } else if (document.getElementById('tabs-9') != null){
                    $("#tabs").tabs("select", "#tabs-9");
                    if (document.getElementById('displayTab9').getElementsByClassName('empty').length > 0)
                        doAjaxPost('resultTab9.action','#displayTab9',true);
                }
            }
//        }
        <c:if test="${fromLogin=='yes'}">
        <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
//        alertMessage("كاربر محترم لطفاً:<br/>1.      فايل راهنماي كاربري سيستم را مطالعه فرماييد.<br/>2.      فايل سوالات متداول واقع در صفحه ورودي سيستم را مطالعه نماييد. به اكثر سوالات شما در اين فايل پاسخ داده شده است.<br/>3.      جهت جلوگيري از بروز مشكلات احتمالي، حتما از جديدترين نسخه مرورگر موزيلا فايرفاكس استفاده نماييد.<br/>4.      مشكلات خود در خصوص كار با اين سيستم را در سامانه مديريت درخواست هاي فناوري اطلاعات به آدرس http://ticket.parsianinsurance.com/It_osticket براي 'اداره راهبري سيستم هاي نرم افزاري' موضوع 'راهبري عمر انفرادي' ارسال نماييد تا پاسخ شما در اسرع وقت از طرق همين سامانه اعلام گردد. براي كار با سامانه مذكور حتما از مروگر اينترنت اكسپلورر استفاده نماييد.");
        alertMessage("كاربر محترم لطفاً:<br/><p align='right' style='font:bold; font-size:12px; color:red;'>1. بازگشت به بخشنامه مورخ 92/02/30 اسکن نمودن کارت ملی بیمه گذار از تاریخ 92/03/15 الزامی می باشد. بدیهی است به کلیه پیشنهاداتی که فاقد اسکن کارت ملی می باشند ترتیب اثر داده نخواهد شد.</p><p align='right' style='font:bold; font-size:12px; color:blue;'>2. احتراما به اطلاع می رساند امکانات گزارشگیری صدور، اقساط معوق و مبالغ پرداختی، صدور الحاقیه های ابطال، تغییر کد نمایندگی موقت به دائم و توضیح در سیستم عملیاتی شده است.</p>3.      فايل راهنماي كاربري سيستم را مطالعه فرماييد.<br/>4.      فايل سوالات متداول واقع در صفحه ورودي سيستم را مطالعه نماييد. به اكثر سوالات شما در اين فايل پاسخ داده شده است.<br/>5.      جهت جلوگيري از بروز مشكلات احتمالي، حتما از جديدترين نسخه مرورگر موزيلا فايرفاكس استفاده نماييد.<br/>6.      مشكلات خود در خصوص كار با اين سيستم را در سامانه مديريت درخواست هاي فناوري اطلاعات به آدرس http://ticket.parsianinsurance.com/It_osticket براي 'اداره راهبري سيستم هاي نرم افزاري' موضوع 'راهبري عمر انفرادي' ارسال نماييد تا پاسخ شما در اسرع وقت از طرق همين سامانه اعلام گردد. براي كار با سامانه مذكور حتما از مروگر اينترنت اكسپلورر استفاده نماييد.");

        </sec:authorize>
        </c:if>
        if ($(".actionMessage").length > 0) {
            alertMessage($(".actionMessage").text());
        }
    });
    </sec:authorize>
    function addComment() {
        $('#addCommetnDiv').dialog({
            modal: true,
            width: 640,
            resizable: false,
            closeText: "",
            title: "نظر شما",
            close: function () {
                $('#addCommetnDiv input, #addCommetnDiv textarea').val('');
                $.validationEngine.closePrompt('.formError', true);
                $('#mesg').hide();
            },
            buttons: {
                "بستن": function () {
                    $(this).dialog("close");
                },
                "ارسال": function () {
                    $.validationEngine.onSubmitValid = true;
                    if ($.validationEngine.submitValidation($('#addCommetnDiv')) === false) {
                        var data = $('#addCommetnForm').serialize();
                        $.post("/saveNazar", data, function (msg) {
                            $.validationEngine.closePrompt('.formError', true);
                            $('#mesg').show();
                            setTimeout("closeComment()", 2000);
                        })
                    }
                }
            }
        });
    }
    function sbt_transition(id, trans) {

        $('#p_popup').dialog({
            modal: true,
            width: 260,
            resizable: false,
            closeText: "",
            title: "گذاشتن پیغام",
            buttons: {
                "بستن": function () {
                    $(this).dialog("close");
                },
                "انجام": function () {
                    $('#pishnehadId').val($('#pishnehadholder' + id).val());
                    $('#transitionId').val($('#transitionSelector' + trans).val());
                    $('#logmessage').val($('#loggmessage').val());
                    <%
                      request.setCharacterEncoding("UTF-8");
                      response.setCharacterEncoding("UTF-8");
                    %>
                    $('#log_message').submit();
                }
            }
        });
    }
    function prepareToSearchForPishnehadsAsl() {
        purify("asl");
        $("#form_search_for_pishnehads_viewview").removeAttr("action");
        $("#form_search_for_pishnehads").attr("action", "searchForPishnehads");
        $('#shomareBimeTdSearch').hide();
        $("#sel_sel_tab").val("tabs-1");
    }
    function prepareToSearchForPishnehadsView() {
        purify("view");
        $("#form_search_for_pishnehads").removeAttr("action");
        $("#form_search_for_pishnehads_viewview").attr("action", "searchForPishnehadsToView");
        $("#sel_sel_tab_viewview").val("tabs-2");
        //doAjaxPost();
    }
    function prepareToSearch7() {
        purify("7");
        $("#form_search_for_pishnehads").removeAttr("action");
        $("#form_search_for_pishnehads_viewview").attr("action", "searchForGharardads");
        $("#sel_sel_tab_viewview").val("tabs-7");
    }
    function purify(forWho) {
        if (forWho == "asl") {
            $("#searchAslContent '[id]'").each(function () {
                var theId = $(this).attr("id");
                theId = theId.replace("_viewview", "");
                $(this).attr("id", theId);
            });
        }
        if (forWho == "view") {
            $("#searchViewContent '[id]'").each(function () {
                var theId = $(this).attr("id");
                if (!(theId.indexOf("_viewview") > -1)) {
                    theId = theId + "_viewview";
                }
                $(this).attr("id", theId);
            });
        }

    }

    function showLegend() {
        dialogBoxWithoutTaeed('legendBox', 'راهنما')
    }

</script>