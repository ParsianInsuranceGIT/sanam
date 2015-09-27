<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<head>
     <title>افزودن نام آزمایش</title>
</head>
<form class="mainFrame" id="mainForm" method="post" action="/addAzmayeshName">
<table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <thead>
        <tr>
            <th colspan="2">
                ثبت نام آزمایش
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
                نوع آزمایش&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <select name="azmayeshNameTypeYadak" id="azmayeshNameType">
                    <s:iterator value="azmayeshTypes" id="row" status="stat">
                        <option value='<s:property value="#row.getId()"/>'><s:property value="#row.getType()"/></option>
                    </s:iterator>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                نام آزمایش&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="text" name="azmayeshName.name" id="azmayeshNameName" class="validate[required]"/>
            </td>
        </tr>
        <tr>
            <td>
                توضیحات:&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <textarea rows="5" cols="50" name="azmayeshName.description" id="azmayeshNameDescription" class="validate[required]"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="ثبت" />
                <input type="button" onclick="window.location='/listAllAzmayeshNames'" value="بازگشت"/>
            </td>

        </tr>
    </tbody>
</table>
</form>