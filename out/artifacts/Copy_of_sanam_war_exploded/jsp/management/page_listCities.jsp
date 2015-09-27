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
    <title>مدیریت شهرها و استان ها</title>
</head>
<body>
<%@include file="/jsp/josteju/searchCity.jsp" %>
<s:actionmessage/>
<%--<s:actionerror/>--%>
<div class="expandableTitleBar" id="expandableAsl">
    <p class="heading">
        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        جستجو
    </p>

    <div class="content" style="display:none;" id="searchAslContent">
        <form action="/listAllCities" method="get">
            <table dir="rtl" class="inputList">
                <tr>
                    <td>نام:</td>
                    <td><s:textfield name="sname" theme="simple"/></td>
                    <td>کد :</td>
                    <td><s:textfield name="scode" theme="simple"/></td>
                </tr>
                <tr>
                    <td>نام تابع:</td>
                    <td><s:textfield name="stabe" theme="simple"/></td>
                    <td>کد تابع:</td>
                    <td><s:textfield name="scodetabe" theme="simple"/></td>
                </tr>
                <tr>
                    <td><s:submit value="جستجو" theme="simple"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<display:table export="false" id="tblListCities" uid="city" htmlId="tblListCities"
               name="sessionScope.listCities.list" partialList="true" clearStatus="true" keepStatus="false"
               style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
               size="${sessionScope.listCities.fullListSize}"
               pagesize="${sessionScope.listCities.objectsPerPage}">
    <display:column property="cityName" title="نام شهر"></display:column>
    <display:column property="cityIdForPid" title="کد شهر"></display:column>
    <display:column title="نام تابع">
        <s:property value="%{getParentCity(#attr['city'].cityId).getCityName()}"/>
    </display:column>
    <display:column title="کد تابع">
        <s:property value="%{getParentCity(#attr['city'].cityId).getCityIdForPid()}"/>
    </display:column>

    <display:column media="html"><input type="button" value="ویرایش" style="width: 50px"
                                        onclick="window.location='/sabtCity?cityId=${city.cityId}'"/></display:column>
    <%--<display:column media="html">--%>
        <%--<input type="button" value="حذف" style="width:50px"--%>
               <%--onclick="--%>
               <%--confirmMessage('آیا از حذف این شهر (${city.cityName}) مطمئن هستید؟',--%>
               <%--'', function(){window.location='/deleteCity.action?cityId=${city.cityId}';})"/>--%>
    <%--</display:column>--%>
</display:table>
<br>
<br>
<div>
    <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
    <input type="button" onclick="window.location='/sabtCity'" value="افزودن شهر">
</div>
</body>

</html>