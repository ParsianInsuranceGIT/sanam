<%@ page import="com.bitarts.parsian.model.pishnahad.Pishnehad" %>
<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.model.Clinic" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>صفحه تکمیل معرفی نامه به آزمایشگاه</title>
    <%
        Pishnehad pish = (Pishnehad)request.getAttribute("pishnehad");
        String logmessage = (String) request.getAttribute("logmessage");
        String transitionId = (String) request.getAttribute("transitionId");
        List<Clinic> clinics = (List<Clinic>) request.getAttribute("clinics");

    %>
</head>
<body>
<div class=expandableTitleBar>
    <p class=heading style="padding:5px"><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        مشخصات بیمه شده و کلینیک
    </p>

    <div class="staticTitleBar" id="result" style="direction:rtl;">
        <form action="/jsp/transitionForms/page_pardakhtInterneti.jsp" method="post" accept-charset="UTF-8">
            <input type="hidden" name="pishnehadId" value="<%=pish.getId()%>"/>
            <input type="hidden" name="transitionId" value="<%=transitionId%>"/>
            <input type="hidden" name="logmessage" value="<%=logmessage%>"/>

            <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
                <tbody>
                    <tr>
                        <td width="50%">
                            <label>شماره:&nbsp;&nbsp;&nbsp;&nbsp;<%=pish.getId()%></label>
                        </td>
                        <td width="50%">
                            <label>کد رهگیری:&nbsp;&nbsp;&nbsp;&nbsp;<%=pish.getRadif()%></label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>تاریخ صدور:&nbsp;&nbsp;&nbsp;&nbsp;</label><input type="text" name="moarefiname.tarikheSodur" value="<%=DateUtil.getCurrentDate()%>"/>
                        </td>
                        <td>
                            <label>تاریخ اعتبار:&nbsp;&nbsp;&nbsp;&nbsp;</label><input type="text" name="moarefiname.tarikheEtebar" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>کد نماینده:&nbsp;&nbsp;&nbsp;&nbsp;<%=pish.getNamayande().getKodeNamayandeKargozar()%></label>
                        </td>
                        <td>
                            <label>نام نماینده:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="moarefiname.nameNamayande"/> </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>نام کلینیک:</label>
                            <% if (clinics.size() == 0){%>
                            <label style="color:#8b0000;">هیج کلینیکی در سیستم ثبت نشده است!</label>
                            <%}else{%>
                                <select name="moarefiname.clinicName">
                                    <%for (Clinic clinic : clinics) {%>
                                         <option value="<%=clinic.getClinicName()%>"><%=clinic.getClinicName()%></option>
                                    <%}%>
                                </select>
                            <%}%>
                        </td>
                        <td>

                        </td>
                    </tr>
                </tbody>
            </table>
        </form>

        <input type="button" onclick="window.location='/loginUser.action'" value="بازگشت" style="float:right;margin-right:20px"/>
    </div>

    <div style="clear:both;">&nbsp;</div>
</div>
</body>
</html>