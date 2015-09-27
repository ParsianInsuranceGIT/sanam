<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<head>
     <title>ویرایش آزمایش</title>
</head>
<form class="mainFrame" id="mainForm" method="post" action="/doEditAzmayesh?azmayesh.id=${azmayesh.id}&clinic.id=<%=request.getParameter("clinic.id")%>">
<table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">

    <thead>
        <tr>
            <th colspan="2">
ویرایش آزمایش
            </th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                نوع آزمایش&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="text" name="azmayesh.type" id="azmayeshType" value="${azmayesh.azmayeshName.azmayeshType.type}"/>
            </td>
        </tr>
        <tr>
            <td>
                نام آزمایش:&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="text" name="azmayesh.name" id="azmayeshName" value="${azmayesh.azmayeshName.name}"/>
            </td>
        </tr>
        <tr>
            <td>
                قیمت:&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="text" name="azmayesh.price" id="azmayeshPrice" value="${azmayesh.price}"/>
            </td>
        </tr>
        <tr>
            <td>
                توضیحات:&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <textarea rows="5" cols="50" name="azmayesh.description" id="azmayeshDescription">${azmayesh.description}</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="ثبت" />
            </td>
        </tr>
    </tbody>
</table>
</form>