<%@ page import="com.bitarts.parsian.model.Clinic" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
    Clinic clinic = (Clinic) request.getAttribute("clinic");
%>
<head>
    <title>ثبت مشخصات کلینیک</title>
</head>
<jsp:include page="/jsp/josteju/searchCity.jsp"></jsp:include>
<form class="mainFrame" id="mainForm" method="post" action="/updateMoshakhasatClinic">
    <table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <input type="hidden" name="clinic.id" value="<%=clinic.getId()%>"/>
        <thead>
        <tr>
            <th colspan="2">
                ویرایش مشخصات کلینیک
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                نام کلینیک: &nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="text" name="clinic.clinicName" value="<%=clinic.getClinicName()%>" id="clinicName"/>
            </td>
        </tr>
        <tr>
            <td>
                شهر:&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <span class="help ui-icon ui-icon-search" onclick="fillShahr('cityId','cityName','btnSubmit')" style="float:left;" title="جستجو"></span>
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
                <textarea rows="5" cols="50" name="clinic.address" id="address"><%=clinic.getAddress()%>
                </textarea>
            </td>
        </tr>
        <tr>
            <td>
                تاریخ شروع قرارداد
            </td>
            <td>
                <input type="text" id="tarikhShoru" name="clinic.tarikhShorouGharardad"
                       value="<%=clinic.getTarikhShorouGharardad()%>" class="datePkr"/>
            </td>
        </tr>
        <tr>
            <td>
                تاریخ اتمام قرارداد
            </td>
            <td>
                <input type="text" id="tarikhEtmam" name="clinic.tarikhEtmamGharardad"
                       value="<%=clinic.getTarikhEtmamGharardad()%>" class="datePkr"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="ثبت"/>
                <input type="button" onclick="window.location='/moshahedeClinics'" value="بازگشت"/>
            </td>
        </tr>
        </tbody>
    </table>
</form>