<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<head>
     <title>ویرایش نوع آزمایش</title>
</head>
<form class="mainFrame" id="mainForm" method="post" action="/doEditAzmayeshType?azmayeshType.id=<%=request.getParameter("azmayeshType.id")%>">
<table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <thead>
        <tr>
            <th colspan="2">
ویرایش نوع آزمایش:
            </th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                نوع آزمایش&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="text" name="azmayeshType.type" id="azmayeshTypeType" value="${azmayeshType.type}"/>
            </td>
        </tr>
        <tr>
            <td>
                توضیحات:&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <textarea rows="5" cols="50" name="azmayeshType.description" id="azmayeshTypeDescription">${azmayeshType.description}</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="ثبت" />
                <input type="button" onclick="window.location='/listAllAzmayeshTypes'" value="بازگشت"/>
            </td>
        </tr>
    </tbody>
</table>
</form>