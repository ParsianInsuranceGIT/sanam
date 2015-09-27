<%--
  Created by IntelliJ IDEA.
  User: e-soleymani
  Date: 11/19/14
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
    $(function() {
        $(".bazaryabTable th").each(function(){
            $(this).addClass("ui-state-default");
        });
        $(".bazaryabTable td").each(function(){
            $(this).addClass("ui-widget-content");
        });
        $(".bazaryabTable tr").hover(function(){
            $(this).children("td").addClass("ui-state-hover");
        },function(){
            $(this).children("td").removeClass("ui-state-hover");
        });
        $(".bazaryabTable tr").click(function(){
            $(this).children("td").toggleClass("ui-state-highlight");
        });
    });
</script>
<hr>
<table class="jtable resultDets inputList bazaryabTable" cellpadding="5" cellspacing="0" style="border-spacing:0px;margin-left:auto;margin-right:auto;width:770px" border="0">
    <thead>
    <tr>
        <th class="ui-state-active" colspan="5">نتایج جستجو</th>
    </tr>
    <s:if test="reportBazaryabResult == null || reportBazaryabResult.size == 0">
        <tr>
            <th colspan="5">بازاریاب با مشخصات وارد شده یافت نشد</tr>
        </tr>
    </s:if>
    <s:else>
        <tr>
            <th>نام بازاریاب</th>
            <th>کد بازاریاب</th>
        </tr>
    </s:else>
    </thead>

    <tbody>
    <s:iterator value="reportBazaryabResult" id="rowbazaryabsanam" status="stat">
        <tr id="rwb_j" onclick="entekhabb('<s:property value="#rowbazaryabsanam.id"/>','<s:property value="#rowbazaryabsanam.getName()"/>','<s:property value="#rowbazaryabsanam.getCode()"/>')" title="انتخاب">
            <td><s:property value="#rowbazaryabsanam.getName()"/></td>
            <td><s:property value="#rowbazaryabsanam.getCode()"/></td>
        </tr>
    </s:iterator>

    </tbody>
</table>