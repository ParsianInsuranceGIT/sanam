<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 5/18/11
  Time: 5:29 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bitarts.parsian.model.asnadeSodor.Credebit" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 5/18/11
  Time: 3:30 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>سند های تولید شده</title>
</head>
<body>
<div class="expandableTitleBar">
    <p class="heading">
        سند های تولید شده
    </p>
    <input type="button" value="بازگشت" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'">
    <table class="jtable inputList nonGrid" border="0" cellpadding="5" cellspacing="0" width="90%" align="center">
        <tr>
            <th>تاریخ ثبت سند</th>
            <th> شناسه پرداخت</th>
            <th>مقدار (ریال)</th>
            <th>تاریخ پرداخت</th>
            <th>تاریخ بدهی</th>
        </tr>
        <c:forEach var="sanad" items="${sanadList}">
            <tr>
                <td>${sanad.createdDate}</td>
                <td>${sanad.bedehi.shenaseBedehi}</td>
                <td>${sanad.credebit.amount}</td>
                <td>${sanad.credebit.createdDate}</td>
                <td>${sanad.bedehi.sarresidDate}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>