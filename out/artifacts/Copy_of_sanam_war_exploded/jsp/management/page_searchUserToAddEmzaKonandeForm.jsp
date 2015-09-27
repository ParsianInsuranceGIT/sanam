<%--
  Created by IntelliJ IDEA.
  User: ramtinb
  Date: 4/10/12
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>لیست کاربران</title>
</head>
<body>
<div class="staticTitleBar" id="expandableAsl">
    <p class="heading">
        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        جستجو
    </p>
    <div class="content" id="searchAslContent">
        <form action="/searchUserToAddEmzaKonande" method="get">
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
                        <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('p_l_vSodurId','p_l_vSodurName', '');" style="float:left;" title="جستجو"></span>
                        <s:textfield name="viSodurName" id="p_l_vSodurName" theme="simple"/>
                        <s:hidden name="viSodurId" id="p_l_vSodurId"/>
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
                            <s:select list="roleList" listKey="id" listValue="getText(roleName)" key="roleId" emptyOption="true"
                                      theme="simple"/>
                        </s:i18n>
                    </td>
                </tr>
                <tr>
                    <td>نمایندگی:</td>
                    <td>
                        <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('p_l_namayandeId','p_l_namayandeName', '');" style="float:left;" title="جستجو"></span>
                        <s:textfield name="namayandeName" theme="simple" id="p_l_namayandeName"/>
                        <s:hidden name="namayandeId" id="p_l_namayandeId"/>
                    </td>
                    <td></td>
                    <td>
                        <script type="text/javascript">
                        function clearSeachFrom_b() {
                            $('#fname').val('');
                            $('#lname').val('');
                            $('#code').val('');
                            $('#p_l_vSodurId').val('');
                            $('#p_l_vSodurName').val('');
                            $('#p_l_namayandeId').val('');
                            $('#p_l_namayandeName').val('');
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
<s:actionmessage/>
<display:table export="false" id="tblListUser" uid="user" htmlId="tblListUser" name="sessionScope.listUser.list"
               partialList="true" clearStatus="true" keepStatus="false"
               style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
               size="${sessionScope.listUser.fullListSize}" pagesize="${sessionScope.listUser.objectsPerPage}">
    <display:column property="firstName" title="نام"></display:column>
    <%--<display:column property="lastName" title="نام خانوادگی"></display:column>--%>
    <display:column property="username" title="نام کاربری"></display:column>
    <display:column property="password" title="رمز عبور"></display:column>
    <display:column property="sematSazmani" title="سمت سازمانی"></display:column>
    <display:column property="namayandegi.name" title="نمایندگی"></display:column>
    <display:column style="background:${theColor};" media="html">
        <input type="button" value="ایجاد امضا کننده" style="width: 120px" onclick="window.location='/prepareAddEmzakonande.action?user.id=${user.id}'"/>
    </display:column>
</display:table>
<br/> <br/>

<div>
    <input type="button" onclick="window.location='/showEmzaKonandegan.action'" value="بازگشت"/>
</div>

<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
</body>
</html>