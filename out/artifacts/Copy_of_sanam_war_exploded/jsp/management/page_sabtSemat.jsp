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
    <title>ثبت سمت</title>
    <script src='/resource_lib/spectrum/spectrum.js'></script>
    <link rel='stylesheet' href='/resource_lib/spectrum/spectrum.css' />
    <script>
        $(function(){
            $("#roleColor").spectrum({
                color: "${role.roleColor != '' ? role.roleColor : 'green'}",
                preferredFormat: "hex",
                theme: "ui-state-default  ui-corner-all",
                cancelText: "انصراف",
                chooseText: "انتخاب"
            });
        });
    </script>
</head>
<body>

<form class="mainFrame" name="mainForm" method="post" action="/addSemat">

    <c:if test="${role.id != null}">
        <s:hidden key="role.id" label="" theme="simple"/>
    </c:if>

    <table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <thead>
        <tr>
            <th colspan="2">افزودن سمت</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>نام</td>
            <td>
                <input type="text" name="role.roleName" value="${role.roleName}" id="rolename" class="validate[required]"/>
            </td>
        </tr>
        <tr>
            <td>رنگ</td>
            <td>
                <input type="text" name="role.roleColor" id="roleColor" style="direction:ltr;"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" id="btnSubmit" value="ثبت"/>
                <input type="button" value="بازگشت"
                       onclick="window.location='/listAllSemats.action'"/>
            </td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>