<%--
  Created by IntelliJ IDEA.
  User: ramtinb
  Date: 4/10/12
  Time: 10:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>ثبت شهر</title>
</head>
<body>

<form class="mainFrame" name="mainForm" method="post" action="/addCity">

    <c:if test="${newCity.cityId != null}">
        <s:hidden key="newCity.cityId" label="" theme="simple"/>
    </c:if>

    <jsp:include page="/jsp/josteju/searchCity.jsp"></jsp:include>
    <table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <thead>
        <tr>
            <th colspan="2">
                افزودن شهر
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>نام شهر</td>
            <td><input type="text" name="newCity.cityName" value="${newCity.cityName}" id="namayande_name"
                       class="validate[required]"/></td>
        </tr>
                <tr>
            <td>کد شهر</td>
            <td>
                <input type="text" name="newCity.cityIdForPid" value="${newCity.cityIdForPid}"/>
            </td>
        </tr>
        <tr>
            <td>تابع</td>
            <td>
                <span class="help ui-icon ui-icon-search" onclick="fillShahrForPid('cityPId','cityName','btnSubmit');" style="float:left;" title="جستجو"></span>
                <input type="text" value="<s:property value="%{getParentCity(newCity.cityId).getCityName()}"/>"
                       id="cityName" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td>کد تابع</td>
            <td>
                <input type="text" name="newCity.cityPid"
                       value="<s:property value="%{getParentCity(newCity.cityId).getCityIdForPid()}"/>"
                       id="cityPId" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td>نوع شهر</td>
            <td>
                <s:select list="#{'3':'شهر' , '2':'استان' , '1':'کشور' , '4':'بخش' , '5':'مناطق خاص'}"
                          key="newCity.cityType" id="cityType" label="" theme="simple"/>
            </td>
        </tr>



        <tr>
            <td colspan="2">
                <input type="submit" id="btnSubmit" value="ثبت"/>
                <input type="button" value="بازگشت"
                       onclick="window.location='/listAllCities'"/>
            </td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>