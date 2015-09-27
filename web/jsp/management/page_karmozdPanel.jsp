
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>کارمــــــزد</title>
    <script type="text/javascript">
        $(function ()
        {
            $("#tabs").tabs();
        });
        function close()
        {
            $.validationEngine.closePrompt('.formError', true);
            $('#crt_prj_onvan').val('');
            $('#crt_prj_desc').val('');
            $('#crt_prj_vosoliDate').val('');
        }

        function create()
        {
            if($.validationEngine.submitValidation(this) === false)
            {
                $('#crt_prj_frm').submit();
                $(this).dialog("close");
            }
        }

        var dilg;

        function loadCreateProject()
        {
            $.validationEngine.closePrompt('.formError', true);
            dilg = $('#crt_prj_frm').dialog({
                modal:true,
                width: 840,
                resizable:false,
                closeText: "",
                title:"ایجاد پروژه کارمزد جدید",
                close:close,
                buttons:
                {
                    "ثبت": create,
                    "انصراف": function(){close();$(this).dialog("close");}
                }
            });
        }

        function loadAddToBlackList()
        {
            $.validationEngine.closePrompt('.formError', true);
            dilg = $('#add_black_list_frm').dialog({
                modal:true,
                width: 400,
                resizable:false,
                closeText: "",
                title:"افزودن به لیست سیاه",
                close:close,
                buttons:
                {
                    "تایید": add,
                    "انصراف": function(){closeAddBlack();$(this).dialog("close");}
                }
            });
        }

        function add()
        {
            if($.validationEngine.submitValidation(this) === false)
            {
                $('#add_black_list_frm').submit();
                $(this).dialog("close");
            }
        }

        function closeAddBlack()
        {
            $.validationEngine.closePrompt('.formError', true);
            $('#add_black_list_shomare_bimename').val('');
        }

    </script>
</head>
<body>
    <s:actionmessage/>
    <div id="tabs">
        <ul>
            <li id="litabs1"><a href="#tabs-1">کارمزد</a></li>
            <li id="litabs2"><a href="#tabs-2">لیست سیاه</a></li>
        </ul>
        <div id="tabs-1">
            <div class=expandableTitleBar>
                <div class="staticTitleBar" id="karmozd_t_b" style="width:98%;direction:rtl;margin:0 auto;">
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
                                        <input type="text" name="taTarikhVosoli" id="crt_prj_ta_vosoliDate"  class="validate[required,custom[date]] datePkr" />
                                        &nbsp;<label>وصولی از تاریخ</label>
                                    </td>
                                    <td>
                                        <input type="text" name="azTarikhVosoli" id="crt_prj_az_vosoliDate"  class="validate[required,custom[date]] datePkr" />
                                        &nbsp;<label>وصولی تا تاریخ</label>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <input type="button" onclick="loadCreateProject();" value="ایجاد پروژه کارمزد جدید"/>
                    <display:table export="true" id="tblListKarmozd" uid="row" htmlId="tblListKarmozd"
                        name="paginatedListKarmozds.list" partialList="true" clearStatus="true" keepStatus="false"
                        style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
                        size="${paginatedListKarmozds.fullListSize}" pagesize="${paginatedListKarmozds.objectsPerPage}">
                        <display:column title="نمایش">
                            <input type="button" onclick="window.location='viewKarmozdProject.action?karmozd.id=${row.id}'" value="نمایش"/>
                        </display:column>
                        <display:column title="ردیف" >${row_rowNum}</display:column>
                        <display:column property="onvan" title="عنوان"></display:column>
                        <display:column property="description" title="شرح"></display:column>
                        <display:column  title="وصولی تا تاریخ">${row.taTarikhVosoli}</display:column>
                        <display:column title="کاربر" property="user.fullName"></display:column>
                    </display:table>
                </div>
            </div>
        </div>
        <div id="tabs-2">
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
                                        <input type="text" name="shomareBimename" id="add_black_list_shomare_bimename"/>
                                        &nbsp;<label>شماره بیمه نامه </label>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <input type="button" onclick="loadAddToBlackList();" value="+افزودن به لیست سیاه"/>
                    <display:table export="false" id="tblBlackList" uid="row" htmlId="tblBlackList"
                        name="paginatedtblBlackList.list" partialList="true" clearStatus="true" keepStatus="false"
                        style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
                        size="${paginatedtblBlackList.fullListSize}" pagesize="${paginatedtblBlackList.objectsPerPage}">
                        <display:column title="ردیف" >${row_rowNum}</display:column>
                        <display:column title="شماره بیمه نامه" property="bimename.shomare" style="white-space: nowrap;"/>
                        <display:column property="bimename.tarikhSodour" title="تاریخ صدور"/>
                    <display:column property="bimename.tarikhEngheza" title="تاریخ انقضا"/>
                    <display:column property="bimename.state.stateName" title="وضعیت"/>
                    <display:column property="bimename.pishnehad.bimeGozar.shakhs.fullName" title="نام بیمه گذار"/>
                    <display:column property="bimename.pishnehad.bimeShode.shakhs.fullName" title="نام بیمه شده"/>
                    <display:column property="bimename.pishnehad.namayande.name_kod" title="نام و کد نماینده"/>
                    <display:column property="bimename.pishnehad.namayande.vahedSodur.name" title="واحد صدور"/>
                    <display:column property="bimename.pishnehad.karshenas.fullName" title="کارشناس صدور"/>
                    <display:column property="bimename.pishnehad.karshenas.personalCode" title="کد کارشناس"/>
                    </display:table>
                </div>
            </div>
        </div>
    </div>

    <!-------------------------------------------------------------------------------------->


</body>
</html>
