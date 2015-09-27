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
    <title>ثبت بانک</title>
</head>
<body>

<form class="mainFrame" name="mainForm" method="post" action="/addBank">

    <c:if test="${bank.id != null}">
        <s:hidden key="bank.id" label="" theme="simple"/>
    </c:if>

    <table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <thead>
        <tr>
            <th colspan="2">افزودن بانک</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>نام</td>
            <td><input type="text" name="bank.name" value="${bank.name}"
                       id="banknamej" class="validate[required]"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" id="btnSubmit" value="ثبت"/>
                <input type="button" value="بازگشت"
                       onclick="window.location='/listAllBanks'"/>
            </td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>