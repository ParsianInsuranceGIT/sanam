<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<head>
     <title>ویرایش نام آزمایش</title>
</head>
<form class="mainFrame" id="mainForm" method="post" action="/doEditAzmayeshName?azmayeshName.id=<%=request.getParameter("azmayeshName.id")%>">
<table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <thead>
        <tr>
            <th colspan="2">
ویرایش نام آزمایش:
            </th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                نوع آزمایش&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <%--${}--%>
                <select name="azmayeshNameTypeYadak" id="azmayeshNameType">
                    <c:forEach var="row" items="${azmayeshTypes}">
                        <c:if test="${azmayeshName.azmayeshType.id == row.id}">
                            <option selected="selected" value='<c:out value="${row.id}"/>'><c:out value="${row.type}"/></option>
                        </c:if>
                        <c:if test="${azmayeshName.azmayeshType.id != row.id}">
                            <option  value='<c:out value="${row.id}"/>'><c:out value="${row.type}"/></option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                نام آزمایش&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="text" name="azmayeshName.name" id="azmayeshNameName" value="${azmayeshName.name}"/>
            </td>
        </tr>
        <tr>
            <td>
                توضیحات:&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <textarea rows="5" cols="50" name="azmayeshName.description" id="azmayeshNameDescription">${azmayeshName.description}</textarea>
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