<%@ page import="com.bitarts.parsian.model.pishnahad.Pishnehad" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<html>
<head>
    <title>لیست نظرات پزشک در مورد پیشنهاد</title>
    <%Pishnehad pish = (Pishnehad)request.getAttribute("pishnehad");
        String logmessage = (String) request.getAttribute("logmessage");
        String transitionId = (String) request.getAttribute("transitionId");
    %>
</head>
<body>
<div class=expandableTitleBar>
    <p class=heading style="padding:5px"><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        صفحه تکمیل پیش پرداخت
    </p>

    <div class="staticTitleBar" id="result" style="direction:rtl;">
        <form action="/jsp/transitionForms/page_pardakhtInterneti.jsp" method="post" accept-charset="UTF-8">
            <input type="hidden" name="pishnehadId" value="<%=pish.getId()%>"/>
            <input type="hidden" name="transitionId" value="<%=transitionId%>"/>
            <input type="hidden" name="logmessage" value="<%=logmessage%>"/>
            <input type="button" value="پرداخت اینترنتی" style="float:right;" onclick="window.location='/jsp/transitionForms/page_pardakhtInterneti.jsp'"/>
        </form>
        <form action="/pardakhtBaFish.action" method="post" accept-charset="UTF-8">
            <input type="hidden" name="pishnehadId" value="<%=pish.getId()%>"/>
            <input type="hidden" name="transitionId" value="<%=transitionId%>"/>
            <input type="hidden" name="logmessage" value="<%=logmessage%>"/>
            <input type="submit" value="پرداخت با فیش" style="float:right;margin-right:20px"/>
        </form>
        <input type="button" onclick="window.location='/loginUser.action'" value="بازگشت" style="float:right;margin-right:20px"/>
    </div>

    <div style="clear:both;">&nbsp;</div>
</div>
</body>
</html>