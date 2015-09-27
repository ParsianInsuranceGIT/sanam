<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
     <title>افزودن طرح</title>
</head>
<form class="mainFrame" id="mainForm" method="post" action="/addTarh">
<table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <thead>
        <tr>
            <th colspan="2">
ثبت طرح
            </th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                نام طرح&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="text" name="tarh.name" id="name" class="validate[required]" value="${tarh.name}"/>
                <input type="hidden" name="tarh.id" value="${tarh.id}"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="ثبت" />
                <input type="button" onclick="window.location='/listAllTarhs'" value="بازگشت"/>
            </td>

        </tr>
    </tbody>
</table>
</form>