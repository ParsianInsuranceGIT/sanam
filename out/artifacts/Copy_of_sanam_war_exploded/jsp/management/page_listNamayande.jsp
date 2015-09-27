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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>فهرست نماینده ها</title>
</head>
<body>
<%@include file="/jsp/josteju/searchCity.jsp" %>
<s:actionmessage/>
<s:actionerror/>
<input type="button" onclick="window.location='/sabtNamyande'" value="ثبت نماینده" >
<sec:authorize ifAnyGranted="ROLE_MALI_OPERATOR">
    <input type="button" onclick="window.location='/listAllUser.action'" value="مدیریت کاربران"/>
</sec:authorize>
<input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
    <div class="expandableTitleBar" id="expandableAsl">
                    <p class="heading">
                        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                        جستجو
                    </p>

                    <div class="content" style="display:none;" id="searchAslContent">
                        <form action="/listAllNamayande" method="get">
    <table dir="rtl" class="inputList">
        <tr>
            <td>نام:</td>
            <td>
                <span class="noThing">&nbsp;</span>
                <s:textfield name="nname" id="nname" theme="simple"/>
            </td>
            <td>کد نمایندگی:</td>
            <td>
                <span class="noThing">&nbsp;</span>
                <s:textfield name="ncode" id="ncode" theme="simple"/>
            </td>
        </tr>
        <tr>
            <td>نوع نمایندگی:</td>
            <td>
                <span class="noThing">&nbsp;</span>
                <s:textfield name="ntype" id="ntype" theme="simple"/>
            </td>
            <td>واحد صدور:</td>
            <td>
                <span class="noThing">&nbsp;</span>
                <s:textfield name="viSodurId" id="viSodurId" theme="simple"/>
            </td>
        </tr>
        <tr>
            <td>
                استان:
            </td>
            <td>
                <span class="noThing">&nbsp;</span>
                <s:textfield name="ostan" id="ostan" theme="simple" readonly="true"/>
                <s:hidden name="ostanId" id="ostanId"/>
            </td>
            <td>
                شهر:
            </td>
            <td>
                <span class="help ui-icon ui-icon-search" onclick="fillShahrOstan('shahrId','shahr','ostanId','ostan','vahedSodor')" style="float:left;" title="جستجو"></span>
                <s:textfield name="shahr" id="shahr" theme="simple" readonly="true"/>
                <s:hidden name="shahrId" id="shahrId"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td>
                <script type="text/javascript">
                        function clearSeachFrom_b() {
                            $('#nname').val('');
                            $('#ncode').val('');
                            $('#ntype').val('');
                            $('#viSodurId').val('');
                            $('#ostan').val('');
                            $('#ostanId').val('');
                            $('#shahr').val('');
                            $('#shahrId').val('');
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



<display:table export="false" id="tblListNamayande" uid="namayande" htmlId="tblListNamayande"
               name="sessionScope.listNamayande.list" partialList="true" clearStatus="true" keepStatus="false"
               style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
               size="${sessionScope.listNamayande.fullListSize}"
               pagesize="${sessionScope.listNamayande.objectsPerPage}">
    <display:column property="name" title="نام"></display:column>
    <display:column property="kodeNamayandeKargozar" title="کد نماینده کارگزار"></display:column>
    <display:column property="type" title="نوع"></display:column>
    <display:column property="ostan.cityName" title="استان"></display:column>
    <display:column property="shahr.cityName" title="شهر"></display:column>
    <display:column property="namayandegiUser.username" title="نام کاریری"></display:column>
    <display:column property="address" title="آدرس"></display:column>
    <display:column property="telephone" title="تلفن / موبایل"></display:column>
    <display:column style="background:${theColor};" media="html"><input type="button" value="ویرایش" style="width: 50px"
                                                                        onclick="window.location='/sabtNamyande?namayande.id=${namayande.id}'"/></display:column>
    <%--<display:column style="background:${theColor};" media="html">--%>
        <%--<input type="button" value="حذف"  style="width:50px"--%>
        <%--onclick="confirmMessage('آیا از حذف نماینده (${namayande.name}) مطمئن هستید؟',--%>
        <%--'', function(){window.location='/deleteNamayande.action?namayande.id=${namayande.id}';})"/>--%>
    <%--</display:column>--%>
</display:table>
</body>

</html>