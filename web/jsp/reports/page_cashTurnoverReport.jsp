<%--
  Created by IntelliJ IDEA.
  User: a-khezri
  Date: 11/2/14
  Time: 10:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>                                                                                <br/><br/>
<form name="" id="form_cash_turnover" action="cashTurnoverReport" method="POST">
    <table dir="rtl" class="inputList" width="90%">
        <tr>
            <td>تاریخ صدور سند(از):
                <span class="noThing">&nbsp;</span>
                <input type="text" name="fromDate" id="frm_dt" class="validate[required,custom[date]] datePkr"
                       readonly="true"/>
            </td>

            <td>تاریخ صدور سند(تا):
                <span class="noThing">&nbsp;</span>
                <input type="text" name="toDate" id="to_dt" class="validate[required,custom[date]] datePkr"
                       readonly="true"/>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        <tr>

            <td colspan="3">
                <div class="dblRadio" style="">
                <input type="radio" value="PDF" name="formatReport" id="format_pdf"  onclick="report_submit();"/>
                <label for="format_pdf" title="PDF"><img src="../../img/pdf.png"/>&nbsp;<b>چاپ گزارش</b></label>

                <input type="radio" value="XLS" name="formatReport" id="format_XLS" onclick="report_submit();" />
                <label  title="EXCEL" for="format_XLS"><img src="../../img/ico_file_excel.png" alt=""/>&nbsp;<b>چاپ گزارش</b></label>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </div>

                <input type="button" style="height: 32px;" value="پاک کردن فرم" onclick="clearSeachFrom()"/>

            </td>

        </tr>
    </table> <br/><br/>
</form>
<script type="text/javascript">
    function report_submit(formatReport)
    {
        $('#form_cash_turnover').submit();
    }
    function clearSeachFrom()
    {
        $('#frm_dt').val('');
        $('#to_dt').val('');
    }
</script>
</body>
</html>