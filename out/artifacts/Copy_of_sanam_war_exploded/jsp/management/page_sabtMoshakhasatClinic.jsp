<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<head>
    <title>ثبت مشخصات کلینیک</title>
</head>
<jsp:include page="/jsp/josteju/searchCity.jsp"></jsp:include>
<form class="mainFrame" id="mainForm" method="post" action="/registerMoshakhasatClinic.action">
    <table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <thead>
        <tr>
            <th colspan="2">
                ثبت مشخصات کلینیک
            </th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${duplicate!=null}">
            <tr>
                <td colspan="2">
                    <p style="color:red;font-weight: bold;">
                        اطلاعات وارد شده تکراری است! لطفا در وارد کردن اطلاعات دقت نمایید.
                    </p>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                نام کلینیک: &nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="text" name="clinic.clinicName" id="clinicName" class="validate[required]"/>
            </td>
        </tr>
        <tr>
            <td>
                شهر:&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <span class="help ui-icon ui-icon-search" onclick="fillShahr('cityId','cityName','btnSubmit');" style="float:left;" title="جستجو"></span>
                <input type="text" name="clinic.cityName" value="<s:property value="clinic.cityName"/>"
                       id="cityName"
                       readonly="readonly"/>
                <input type="hidden" id="cityId"/>
            </td>
        </tr>
        <tr>
            <td>
                آدرس:&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <textarea rows="5" cols="50" name="clinic.address" id="address" class="validate[required]"></textarea>
            </td>
        </tr>
        <tr>
            <td>
                تاریخ شروع قرارداد
            </td>
            <td>
                <input type="text" id="tarikhShoru" name="clinic.tarikhShorouGharardad"
                       class="datePkr validate[required,custom[date]]"/>
            </td>
        </tr>
        <tr>
            <td>
                تاریخ اتمام قرارداد
            </td>
            <td>
                <input type="text" id="tarikhEtmam" name="clinic.tarikhEtmamGharardad"
                       class="datePkr validate[required,custom[date]]"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="ثبت"/>
                <input type="button" onclick="window.location='/moshahedeClinics.action'"
                       value="بازگشت"/>
            </td>
        </tr>
        </tbody>
    </table>
</form>