<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: Feb 22, 2011
  Time: 2:59:03 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
    $(function() {
        $(".jtable th").each(function(){
            $(this).addClass("ui-state-default");
        });
        $(".jtable td").each(function(){
            $(this).addClass("ui-widget-content");
        });
        $(".jtable tr").hover(function(){
            $(this).children("td").addClass("ui-state-hover");
        },function(){
            $(this).children("td").removeClass("ui-state-hover");
        });
        $(".jtable tr").click(function(){
            $(this).children("td").toggleClass("ui-state-highlight");
        });
    });    
</script>
<hr>
<table class="jtable resultDets inputList" cellpadding="5" cellspacing="0" style="border-spacing:0px;margin-left:auto;margin-right:auto;width:770px" border="0">
    <thead>
    <tr>
        <th class="ui-state-active" colspan="5">نتایج جستجو</th>
    </tr>
    <s:if test="reportResult == null || reportResult.size == 0">
        <tr>
            <th colspan="5">شخصی با مشخصات وارد شده یافت نشد</tr>
        </tr>
    </s:if>
    <s:else>
        <tr>
            <th >نام</th>
            <th >شماره ثبت</th>
            <th >محل ثبت</th>
            <th >تاریخ ثبت</th>
            <th >کد اقتصادی</th>
        </tr>
    </s:else>
    </thead>

    <tbody>
    <s:iterator value="reportResult" id="row" status="stat">
        <tr id="rw_j" onclick="entekhabHoghughi('<s:property value="#row.id"/>')" title="انتخاب">
            <td><s:property value="#row.getNaam()"/></td>
            <td><s:property value="#row.getShomareSabt()"/></td>
            <td><s:property value="reportResult[#stat.index].getMahalleSabt()"/></td>
            <td><s:property value="reportResult[#stat.index].getTarikheSabt()"/></td>
            <td><s:property value="reportResult[#stat.index].getKodeEghtesadi()"/></td>
        </tr>
    </s:iterator>

    </tbody>
</table>