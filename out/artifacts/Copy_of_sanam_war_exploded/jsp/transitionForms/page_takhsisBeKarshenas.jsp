<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
%>
<head>
     <title>نخصیص به کارشناس</title>
</head>
    <form class="mainFrame" id="mainForm" method="post" action="/assingToExpert.action?pishnehadId=<%=request.getParameter("pishnehadId")%>&transitionId=<%=request.getParameter("transitionId")%>">
<table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <input type="hidden" name="logmessage" value="<%=request.getParameter("logmessage")%>"/>
    <tbody>
        <tr>
            <td>
            نام کارشناس&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <select name="karshenasmoredenazae">
                    <option>آقای تست</option>
                    <option>خانم تست</option>
                </select>
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