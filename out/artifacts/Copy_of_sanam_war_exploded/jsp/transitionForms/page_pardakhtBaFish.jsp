<%@ page import="com.bitarts.parsian.model.pishnahad.Pishnehad" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
    Integer pishnehadId = (Integer) request.getAttribute("pishnehadId");
    String logmessage = (String) request.getAttribute("logmessage");
    Integer transitionId = (Integer) request.getAttribute("transitionId");
%>
<head>
     <title>لیست نظرات پزشک در مورد پیشنهاد</title>
</head>
<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
    صفحه تکمیل پیش پرداخت
    </p>

    <div class="staticTitleBar" id="result" style="direction:rtl;padding: 5px 45px 5px 5px;">
        <form id="mainform" action="/pardakhtBaFishMakeFinal.action" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
            <input type="hidden" name="pishnehadId" value="<%=pishnehadId%>"/>
            <input type="hidden" name="logmessage" value="<%=logmessage%>"/>
            <input type="hidden" name="transitionId" value="<%=transitionId%>"/>
            <input type="hidden" name="userId" value="<%=request.getSession().getAttribute("userid")%>"/>

                <table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
                    <tbody>
                        <tr>
                            <td>
                         شماره فیش&nbsp;&nbsp;&nbsp;&nbsp;
                            </td>
                            <td>
                                <input type="text" name="fishshomare" id="shomarefish"/>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                تاریخ فیش&nbsp;&nbsp;&nbsp;&nbsp;
                            </td>
                            <td>
                                <input type="text" name="fishtarikh" id="tarikhfish"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                بارگذاری فایل پیشنهاد
                            </td>
                            <td>
                                <input type="file" id="uploadedPishnehadFiles" name="uploadedPishnehads"/>
                                <input type="hidden" id="uploadedPishnehads" name="uploadedPishnehadsNames"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                بارگذاری اسکن فیش
                            </td>
                            <td>
                                <input type="file" name="uploadedScannedFishs" id="uploadedScannedFishsFiles"/>
                                <input type="hidden" id="uploadedScannedFishs" name="uploadedScannedFishsNames"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                بارگذاری سایر فایل ها
                            </td>
                            <td>
                                <input type="file" name="uploadedExtras" id="uploadedExtrasFiles"/>
                                <input type="hidden" id="uploadedExtras" name="uploadedExtrasNames"/>
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2">
                                <input type="button" value="پرداخت" onclick="fillInput();"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
        </form>
        <form action="makeTransition.action" id="log_message" method="post" accept-charset="UTF-8">
            <input type="hidden" name="pishnehadId" value="<%=pishnehadId%>"/>
            <input type="hidden" name="transitionId" value="<%=transitionId%>"/>
            <input type="hidden" name="logmessage" id="<%=logmessage%>"/>
            <input type="submit" value="بازگشت"/>
        </form>
    </div>
</div>
<script type="text/javascript">
    function fillInput(){
        $("#uploadedPishnehads").val($("#uploadedPishnehadFiles").val());
        $("#uploadedScannedFishs").val($("#uploadedScannedFishsFiles").val());
        $("#uploadedExtras").val($("#uploadedExtrasFiles").val());
        $("#mainform").submit();
    }
</script>