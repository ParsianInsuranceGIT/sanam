<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: Feb 22, 2011
  Time: 2:59:03 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    برخي از قسطهاي اين تقسيط، قبلا سندخورده اند:
    <br>
    <br>
    <table class="jtable" cellpadding="0" cellspacing="0" style="text-align:center;" align="center">
        <thead>
        <tr>
            <th style="padding:0" class="ui-state-default" >تاريخ قسط</th>
            <th style="padding:0" class="ui-state-default" >مقدار بدهي</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reverseCandidateGhests}" var="ghest">
            <tr>
                <td class="ui-widget-content">${ghest.sarresidDate}</td>
                <td class="ui-widget-content">${ghest.credebit.amount}</td>
                <%--todo We can show some other fields if required.--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    در صورت اطمينان از حذف تقسيط گزينه ي تاييد را انتخاب كنيد، كه در اين صورت سندهاي مربوطه برگشت خواهند خورد.
</div>
<br>
<br>
<div ALIGN="center">
    <input type="button" style="float:left;margin-left:20px" onclick="reverseSanad()" value="تاييد">
    <input type="button" style="float:left;margin-left:20px" onclick="showVaziateGhestBandi()" value="انصراف">
</div>
<script type="text/javascript">
    function reverseSanad(){
        confirmMessage("آيا از حذف اين تقسيط مطمئن هستيد؟", "", function(){
            $.post("/hazfeTaghsit.action?pishnehad.id=${pishnehad.id}&gb.id=${gb.id}&tayeedeReverseSanad=true",function(msg){
                $('#div_showVaziateGhestBandi').html(msg);
                $("#div_showVaziateGhestBandi input").each(function(){
                    $(this).addClass("ui-state-default  ui-corner-all ui-helper-clearfix");
                });
            });
        })
    }
</script>