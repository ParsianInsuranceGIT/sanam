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
<title>مشاهده ليست بدهي ها</title>

<script type="text/javascript">
    $(function(){
        $(".jtable tr").click(function(){
            $(this).children("td").toggleClass("ui-state-highlight");
        });
    });

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
        if (getUrlVars()["page"] != undefined)
            paramUrl = "selectedTab=tabs-1&page="+getUrlVars()["page"];
    }else if (tabName == '#displayTab2'){
        if (getUrlVars()["pageNumber_etebar"] != undefined)
            paramUrl = "selectedTab=tabs-2&pageNumber_etebar="+getUrlVars()["pageNumber_etebar"];
    }else if (tabName == '#displayTab3'){
        if (getUrlVars()["pageNumber_moghayerat"] != undefined)
            paramUrl = "selectedTab=tabs-3&pageNumber_moghayerat="+getUrlVars()["pageNumber_moghayerat"];
    } else if (tabName == '#displayTab4'){
        if (getUrlVars()["pageNumber_bedehiVosulNashode"] != undefined)
            paramUrl = "selectedTab=tabs-4&pageNumber_bedehiVosulNashode="+getUrlVars()["pageNumber_bedehiVosulNashode"];
    }
    else if (tabName == '#displayTab5'){
        if (getUrlVars()["pageNumber_bedehiTasviyeNashode"] != undefined)
            paramUrl = "selectedTab=tabs-5&pageNumber_bedehiTasviyeNashode="+getUrlVars()["pageNumber_bedehiTasviyeNashode"];
    }
    else {
        if (getUrlVars()["pageNumber_moghayerat"] != undefined)
            paramUrl = "selectedTab=tabs-3&pageNumber_moghayerat="+getUrlVars()["pageNumber_moghayerat"];
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


    <link rel="stylesheet" type="text/css" href="/js/menuSplit.css">
    <script type="text/javascript" src="/js/menuSplit.js"></script>
</head>

<div><s:actionmessage/></div>
<div><s:actionerror/></div>
<br>

<div id="tabs">
    <%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
    <%@include file="/jsp/josteju/searchBazaryabSanam.jsp"%>
    <ul>
        <li id="litabs1"><a href="#tabs-1" onclick="doAjaxPost('listBedehiVahedeSodorNamayande.action','#displayTab1',false);clearSeachFrom();">ليست بدهي هاي واحد صدور من
        </a><span class="ui-icon ui-icon-refresh" onclick="doAjaxPost('listBedehiVahedeSodorNamayande.action','#displayTab1',true);" style="float:right;margin-top:5px;"></span></li>

        <li id="litabs2"><a href="#tabs-2" onclick="doAjaxPost('listEtebaratVosulNashodeNamayande.action','#displayTab2',false);clearSeachFrom();">ليست اعتبارات وصول نشده</a>
            <span class="ui-icon ui-icon-refresh" onclick="doAjaxPost('listEtebaratVosulNashodeNamayande.action','#displayTab2',true);" style="float:right;margin-top:5px;"></span></li>

        <li id="litabs3"><a href="#tabs-3" onclick="doAjaxPost('listMoghayeratDarVosolNamayande.action','#displayTab3',false);clearSeachFrom3();">مغايرت در وصولي</a>
            <span class="ui-icon ui-icon-refresh" onclick="doAjaxPost('listMoghayeratDarVosolNamayande.action','#displayTab3',true);" style="float:right;margin-top:5px;"></span></li>

        <li id="litabs4"><a href="#tabs-4" onclick="doAjaxPost('listBedehiHayeVosulNashodeNamayande.action','#displayTab4',false);clearSeachFrom4();">ليست بدهي هاي تسويه نشده</a>
            <span class="ui-icon ui-icon-refresh" onclick="doAjaxPost('listBedehiHayeVosulNashodeNamayande.action','#displayTab4',true);" style="float:right;margin-top:5px;"></span></li>
        <li id="litabs5"><a href="#tabs-5" onclick="doAjaxPost('listBedehiHayeTasviyeNashodeNamayande.action','#displayTab5',false);clearSeachFrom5();">گزارش مطالبات</a>
            <span class="ui-icon ui-icon-refresh" onclick="doAjaxPost('listBedehiHayeTasviyeNashodeNamayande.action','#displayTab5',true);" style="float:right;margin-top:5px;"></span></li>
    </ul>



    <div id="tabs-1">
        <div class="expandableTitleBar" id="expandableAsl2">
            <p class="heading">
                <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                جستجو
            </p>

            <div class="content" style="display:none;" id="searchAslContent">
                <%@include file="/jsp/daryaftPardakht/searchBedehiVahedeSodorNamayande.jsp" %>
            </div>
        </div>
        <div id="displayTab1" class="staticTitleBar">
            <%@include file="/jsp/daryaftPardakht/listBedehiVahedeSodorNamayande.jsp" %>
        </div>
    </div>

    <div id="tabs-2">
        <div class=expandableTitleBar>
            <div class="staticTitleBar" id="viewSearchResult" style="width:98%;direction:rtl;margin:0 auto;">
                <div class="expandableTitleBar" id="expandableView3">
                    <p class="heading">
                        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                        جستجو
                    </p>

                    <div class="content" style="display:none;" id="searchViewContent">
                        <%@include file="/jsp/daryaftPardakht/searchEtebaratVosulNashodeNamayande.jsp" %>
                    </div>
                </div>
                <div id="displayTab2" class="staticTitleBar">
                    <%@include file="/jsp/daryaftPardakht/listEtebaratVosulNashodeNamayande.jsp" %>
                </div>
            </div>
        </div>
    </div>

    <div id="tabs-3">
        <div class="expandableTitleBar" id="expandableAsl3">
            <p class="heading">
                <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                جستجو
            </p>

            <div class="content" style="display:none;" id="searchAs3Content">
                <%@include file="/jsp/daryaftPardakht/searchMoghayeratDarVosolNamayande.jsp" %>
            </div>
        </div>
        <div id="displayTab3" class="staticTitleBar">
            <%@include file="/jsp/daryaftPardakht/listMoghayeratDarVosolNamayande.jsp" %>
        </div>
    </div>

        <div id="tabs-4">
            <div class="expandableTitleBar" id="expandableAsl4">
                <p class="heading">
                    <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                    جستجو
                </p>

                <div class="content" style="display:none;" id="searchAs4Content">
                    <%@include file="/jsp/daryaftPardakht/searchBedehiHayeVosulNashodeNamayande.jsp" %>
                </div>
            </div>
            <div id="displayTab4" class="staticTitleBar">
                <%@include file="/jsp/daryaftPardakht/listBedehiHayeVosulNashodeNamayande.jsp" %>
            </div>
        </div>
    <div id="tabs-5">
        <div class=expandableTitleBar>
            <div class="staticTitleBar" id="expandableAsl5" style="width:98%;direction:rtl;margin:0 auto;">
                <div class="expandableTitleBar" id="expandableView5">
                    <p class="heading">
                        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                        جستجو
                    </p>

                    <div class="content" style="display:none;" id="searchAs5Content">
                        <%@include file="/jsp/daryaftPardakht/searchBedehiTasviyeNashodeNamayande.jsp" %>
                    </div>
                </div>
                <div id="displayTab5" class="staticTitleBar">
                    <%@include file="/jsp/daryaftPardakht/listBedehiHayeTasviyeNashodeNamayande.jsp" %>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
    $(function () {
        $("#tabs").tabs({
            selected: 0
        });
    });
    $(document).ready(function () {
        var selectedTab = getUrlVars()["selectedTab"];
        if (selectedTab == "tabs-1") {
            $("#tabs").tabs("select", "#tabs-1");
            doAjaxPost('listBedehiVahedeSodorNamayande.action','#displayTab1',true);
        } else if (selectedTab == "tabs-2") {
            $("#tabs").tabs("select", "#tabs-2");
            if (document.getElementById('displayTab2').getElementsByClassName('empty').length > 0)
                doAjaxPost('listEtebaratVosulNashodeNamayande.action','#displayTab2',true);
        } else if (selectedTab == "tabs-3") {
            $("#tabs").tabs("select", "#tabs-3");
            if (document.getElementById('displayTab3').getElementsByClassName('empty').length > 0)
                doAjaxPost('listMoghayeratDarVosolNamayande.action','#displayTab3',true);
        }else if (selectedTab == "tabs-4") {
            $("#tabs").tabs("select", "#tabs-4");
            if (document.getElementById('displayTab4').getElementsByClassName('empty').length > 0)
                doAjaxPost('listBedehiHayeVosulNashodeNamayande.action','#displayTab4',true);
        } else if (selectedTab == "tabs-5") {
            $("#tabs").tabs("select", "#tabs-5");
            if (document.getElementById('displayTab5').getElementsByClassName('empty').length > 0)
                doAjaxPost('listBedehiHayeTasviyeNashodeNamayande.action','#displayTab5',true);
        }
        else {
            if (document.getElementById('tabs-1') != null){
                $("#tabs").tabs("select", "#tabs-1");
                if (document.getElementById('displayTab1').getElementsByClassName('empty').length > 0)
                    doAjaxPost('listBedehiVahedeSodorNamayande.action','#displayTab1',true);
            } else if (document.getElementById('tabs-2') != null){
                $("#tabs").tabs("select", "#tabs-2");
                if (document.getElementById('displayTab2').getElementsByClassName('empty').length > 0)
                    doAjaxPost('listEtebaratVosulNashodeNamayande.action','#displayTab2',true);
            } else if (selectedTab == "tabs-3") {
                $("#tabs").tabs("select", "#tabs-3");
                if (document.getElementById('displayTab3').getElementsByClassName('empty').length > 0)
                    doAjaxPost('listMoghayeratDarVosolNamayande.action','#displayTab3',true);
            } else if (selectedTab == "tabs-4") {
                $("#tabs").tabs("select", "#tabs-4");
                if (document.getElementById('displayTab4').getElementsByClassName('empty').length > 0)
                    doAjaxPost('listBedehiHayeVosulNashodeNamayande.action','#displayTab4',true);
            } else if (selectedTab == "tabs-5") {
                $("#tabs").tabs("select", "#tabs-5");
                if (document.getElementById('displayTab5').getElementsByClassName('empty').length > 0)
                    doAjaxPost('listBedehiHayeTasviyeNashodeNamayande.action','#displayTab5',true);
            }
            else{
                doAjaxPost('listBedehiVahedeSodorNamayande.action','#displayTab1',true);
            }
        }


        if ($(".actionMessage").length > 0) {
            alertMessage($(".actionMessage").text());
        }
    });

    </script>
</div>