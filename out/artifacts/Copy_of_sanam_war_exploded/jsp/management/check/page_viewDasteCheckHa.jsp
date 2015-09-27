<%--
  Created by IntelliJ IDEA.
  User: Arron0
  Date: Aug 31, 2011
  Time: 5:17:58 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/taglibs.jsp"%>
<html>
<head><title>دسته چک ها</title></head>
<body>

<div>
    <s:actionmessage/>
</div>
<table class="jtable" align="center" width="900px" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
    <tr>
        <th width="10%">ردیف</th>
        <th width="15%">شماره حساب</th>
        <th>نام دسته چک</th>
        <th width="10%"></th>
    </tr>
    <c:choose>
        <c:when test="${dasteCheckList == null || fn:length(dasteCheckList) == 0}">
            <tr>
                <td colspan="4">
                    رکوردی یافت نشد.
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="row" items="${dasteCheckList}" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${row.shomareHesab}</td>
                    <td>${row.name}</td>
                    <td><input type="button" onclick="window.location='/fin/viewCheckList.action?dasteCheck.id=${row.id}';" value="لیست چک ها"></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>
<div style="margin:5px;height:30px;">
    <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" style="float:left;" value="بازگشت"/>
    <input type="button" onclick="addDasteCheck();" style="float:left;margin-left:2px" value="+ اضافه"/>
</div>
<div id="addDasteCheck_div" style="display:none;">
    <form id="addDasteCheck_form" action="/fin/addDasteCheck.action" method="post">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" id="dc_name" name="dasteCheck.name" class="validate[required] text-input">
                    &nbsp;<label>نام دسته پک</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" id="shomareHesab" name="dasteCheck.shomareHesab" class="validate[required] text-input">
                    &nbsp;<label>شماره حساب</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" id="dc_azShomare" name="dasteCheck.azShomare" class="validate[required,custom[integer]] text-input">
                    &nbsp;<label>از شماره</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" id="dc_taShomare" name="dasteCheck.taShomare" class="validate[required,custom[integer]] text-input">
                    &nbsp;<label>تا شماره</label>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function addDasteCheck(){
        dialogForm('addDasteCheck_div','اضافه کردن دسته چک',function(){
            if($('#dc_taShomare').val()>=$('#dc_azShomare').val())
                $('#addDasteCheck_form').submit();
        });
    }
</script>
</body>
</html>