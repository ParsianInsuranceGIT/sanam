<%--
  Created by IntelliJ IDEA.
  User: Arron0
  Date: Aug 31, 2011
  Time: 5:17:58 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/taglibs.jsp"%>
<html>
<head><title>لیست چک ها</title></head>
<body>

<p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
    لیست چک های دسته چک  ${dasteCheck.name}
</p>
<div>
    <s:actionmessage/>
</div>
<table class="jtable" align="center" width="900px" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
    <tr>
        <th width="7%">ردیف</th>
        <th>وضعیت</th>
        <th>شماره</th>
        <th>مقدار</th>
        <th>در وجه</th>
        <th>تاریخ سررسید</th>
        <th width="6%"></th>
        <th width="6%"></th>
    </tr>
    <c:choose>
        <c:when test="${dasteCheck.checkList == null || fn:length(dasteCheck.checkList) == 0}">
            <tr>
                <td colspan="7">
                    رکوردی یافت نشد.
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="row" items="${dasteCheck.checkList}" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${row.status=='NORMAL'?'عادی':row.status=='PRINT_SHODE'?'استفاده شده':row.status=='BATEL_SHODE'?'باطل شده':row.status}</td>
                    <td>${row.shomare}</td>
                    <td>${row.amountTajamoi}</td>
                    <td>${row.darVajhe}</td>
                    <td>${row.tarikh}</td>
                    <td>
                        <c:if test="${row.status=='NORMAL'}">
                            <input type="button" onclick="editCheck('${row.id}','${row.shomare}','${row.darVajhe}','${row.tarikh}');" value="ویرایش">
                        </c:if>
                    </td>
                        <%--<td><input type="button" onclick="window.location='/viewCheckList.action?check.id=${row.id}';" value="پرینت"></td>--%>
                    <td>
                        <c:if test="${row.status=='NORMAL'}">
                            <input type="button" onclick="window.open('/printeCheck.action?pishnehadReport.check.id=${row.id}');" value="پرینت">
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>
<div style="margin:5px;height:30px;">
    <input type="button" onclick="window.location='/fin/viewDasteCheckHa.action';" style="float:left;" value="بازگشت"/>
</div>
<div id="editCheck_div" style="display:none;">
    <form id="editCheck_form" action="/fin/editCheck.action" method="post">
        <input type="hidden" id="checkId" name="check.id">
        <input type="hidden" name="dasteCheck.id" value="${dasteCheck.id}">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" id="checkShomare" disabled="disabled">
                    &nbsp;<label>شماره</label>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" id="checkDarVajhe" name="check.darVajhe" class="validate[required] text-input">
                    &nbsp;<label>در وجه</label>
                </td>
                <td>
                    <input type="text" id="checkTarikh" name="check.tarikh" class="validate[required,custom[date]] text-input datePkr">
                    &nbsp;<label>تاریخ سررسید</label>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function editCheck(id,shomare,darVajhe,tarikh){
        $('#checkId').val(id);
        $('#checkShomare').val(shomare);
        $('#checkDarVajhe').val(darVajhe);
        $('#checkTarikh').val(tarikh);
        dialogForm('editCheck_div', 'ویرایش چک', function(){
            $('#editCheck_form').submit();
        })
    }
</script>
</body>
</html>