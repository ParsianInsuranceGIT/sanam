<%@ page import="com.bitarts.parsian.model.Clinic" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>مشاهده لیست امضا کنندگان</title>
</head>
<body>
<% Clinic clinic = (Clinic) request.getAttribute("clinic");
%>

<div class="expandableTitleBar" id="expandableAsl">
    <p class="heading">
        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        جستجو
    </p>
    <div class="content" style="display:none;" id="searchAslContent">
        <form action="/showEmzaKonandegan" method="get">
            <table dir="rtl" class="inputList">
                <tr>
                    <td>نام کاربر:</td>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <s:textfield name="fname" id="fname" theme="simple"/>
                    </td>
                    <td>نام خانوادگی:</td>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <s:textfield name="lname" id="lname" theme="simple"/>
                    </td>
                </tr>
                <tr>
                    <td>کد کاربر:</td>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <s:textfield name="code" id="code" theme="simple"/>
                    </td>
                    <td>واحد صدور:</td>
                    <td>
                        <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('p_lE_vSodurId','p_lE_vSodurName', '');" style="float:left;" title="جستجو"></span>
                        <s:textfield name="viSodurName" id="p_lE_vSodurName" theme="simple"/>
                        <s:hidden name="viSodurId" id="p_lE_vSodurId"/>
                    </td>
                </tr>
                <tr>
                    <td>از اعتبار امضا:</td>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <s:textfield name="azEtebar" id="azEtebar" theme="simple"/>
                    </td>
                    <td>تا اعتبار امضا:</td>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <s:textfield name="taEtebar" id="taEtebar" theme="simple"/>
                    </td>
                </tr>
                <tr>
                    <td>وضعیت:</td>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <s:select list="#{'-1':'-','0':'فعال','1':'غیر فعال'}"
                          key="uiStatus" id="uiStatus" label="" theme="simple"/>
                    </td>
                    <td>سمت سازمانی:</td>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <s:i18n name="com.bitarts.parsian.i18n.enumTypes_fa">
                            <s:select list="roleList" listKey="id" listValue="getText(roleName)" key="roleId"
                                      emptyOption="true" theme="simple" id="roleId"/>
                        </s:i18n>
                    </td>
                </tr>
                <tr>
                    <td>نمایندگی:</td>
                    <td>
                        <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('p_lE_namayandeId','p_lE_namayandeName', '');" style="float:left;" title="جستجو"></span>
                        <s:textfield name="namayandeName" id="p_lE_namayandeName" theme="simple"/>
                        <s:hidden name="namayandeId" id="p_lE_namayandeId"/>
                    </td>
                    <td></td>
                    <td>
                         <script type="text/javascript">
                        function clearSeachFrom_b() {
                            $('#fname').val('');
                            $('#lname').val('');
                            $('#code').val('');
                            $('#p_lE_vSodurName').val('');
                            $('#p_lE_vSodurId').val('');
                            $('#azEtebar').val('');
                            $('#taEtebar').val('');
                            $('#p_lE_namayandeName').val('');
                            $('#p_lE_namayandeId').val('');
                            $('#uiStatus option:eq(0)').attr('selected', 'selected');
                            $('#roleId option:eq(0)').attr('selected', 'selected');
                        }
                    </script>
                        <span class="noThing">&nbsp;</span>
                        <s:submit value="جستجو" theme="simple"/>
                        <span class="noThing"></span>
                        <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom_b()">
                    </td>
                </tr>
            </table>

        </form>
    </div>
</div>
<s:if test="sessionScope.listEmzakonande.list == null || sessionScope.listEmzakonande.list.size == 0">
    <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin:0 auto;" border="0">
        <thead>
        <tr>
            <td></td>
        </tr>
        <tr>
            <th colspan="5" width="150px">اطلاعاتی یافت نشد</th>
        </tr>
        <tr>
            <td></td>
        </tr>
        </thead>
    </table>
</s:if>
<s:else>
    <display:table export="false" id="tblListUser" uid="user" htmlId="tblListUser"
                   name="sessionScope.listEmzakonande.list"
                   partialList="true" clearStatus="true" keepStatus="false"
                   style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
                   size="${sessionScope.listEmzakonande.fullListSize}"
                   pagesize="${sessionScope.listEmzakonande.objectsPerPage}">
        <display:column property="user.firstName" title="نام"></display:column>
        <display:column property="user.lastName" title="نام خانوادگی"></display:column>
        <display:column property="user.mojtamaSodoor.name" title="واحد صدور"></display:column>
        <display:column property="user.sematSazmani" title="سمت"></display:column>
        <display:column property="maxCapitalString" title="حداکثر اعتبار امضا"></display:column>
        <display:column property="user.personalCode" title="کد پرسنلی"></display:column>
        <display:column property="typeFarsi" title="نوع امضاکننده"></display:column>
        <display:column style="background:${theColor};" media="html"><input type="button" value="ویرایش"
                                                                            style="width:50px"
                                                                            onclick="window.location='/prepareEditEmzaKonande?emzaKonande.id=${user.id}'"></display:column>
        <display:column style="background:${theColor};" media="html">
            <input type="button" value="حذف"
            onclick="confirmMessage('آیا از حذف (${user.user.firstName}) مطمئن هستید؟',
            '', function(){window.location='/removeEmzaKonande?emzaKonande.id=${user.id}';})"/>
        </display:column>

    </display:table>


</s:else>

<br>
<br>
<div>
    <input type="button" onclick="window.location='jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
    <%--<input type="button" onclick="window.location='/prepareToAddEmzakonande.action'" value="اضافه کردن امضا کننده"/>--%>
    <input type="button" onclick="window.location='/searchUsersToPrepareToAddEmzakonande'" value="اضافه کردن امضا کننده"/>
</div>

<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
</body>
</html>
