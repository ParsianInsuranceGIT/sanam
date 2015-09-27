<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<head>
     <title>افزودن نظر</title>
</head>
    <form class="mainFrame" id="mainForm" method="post" action="/addPezeshkNazar.action?pishnehadId=<%=request.getParameter("pishnehadId")%>&transitionId=<%=request.getParameter("transitionId")%>">
<table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <input type="hidden" name="logmessage" value="<%=request.getParameter("logmessage")%>"/>
    <thead>
        <tr>
            <th colspan="2">
ثبت نظر
            </th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
            نظر&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="text" name="pezeshkSabtNazar.nazar" id="pezeshknazar"/>
            </td>
        </tr>
        <tr>
            <td>
                اضافه نرخ&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="text" name="pezeshkSabtNazar.ezafeNerkh" id="pezeshkezafenerkh"/>
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