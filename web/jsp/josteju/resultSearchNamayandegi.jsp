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
        $(".namayandegiTable th").each(function(){
            $(this).addClass("ui-state-default");
        });
        $(".namayandegiTable td").each(function(){
            $(this).addClass("ui-widget-content");
        });
        $(".namayandegiTable tr").hover(function(){
            $(this).children("td").addClass("ui-state-hover");
        },function(){
            $(this).children("td").removeClass("ui-state-hover");
        });
        $(".namayandegiTable tr").click(function(){
            $(this).children("td").toggleClass("ui-state-highlight");
        });
    });    
</script>
<hr>
<table class="jtable resultDets inputList namayandegiTable" cellpadding="5" cellspacing="0" style="border-spacing:0px;margin-left:auto;margin-right:auto;width:770px" border="0">
    <thead>
    <tr>
        <th class="ui-state-active" colspan="5">نتایج جستجو</th>
    </tr>
    <s:if test="reportResult == null || reportResult.size == 0">
        <tr>
            <th colspan="5">نمایندگی با مشخصات وارد شده یافت نشد</tr>
        </tr>
    </s:if>
    <s:else>
        <tr>
            <th > نام نمایندگی</th>
            <th >کد نمایندگی</th>
            <th >شهر</th>
            <th >استان</th>
            <th >واحد صدور </th>
        </tr>
    </s:else>
    </thead>

    <tbody>
    <s:iterator value="reportResult" id="row" status="stat">
        <tr id="rw_j" onclick="entekhab('<s:property value="#row.id"/>','<s:property value="#row.getName()"/>','<s:property value="#row.getKodeNamayandeKargozar()"/>')" title="انتخاب">
            <td><s:property value="#row.getName()"/></td>
            <td><s:property value="#row.getKodeNamayandeKargozar()"/></td>
            <td><s:property value="#row.getShahr().getCityName()"/></td>
            <td><s:property value="#row.getOstan().getCityName()"/></td>
            <td><s:property value="#row.getVahedSodur().getName()"/></td>
        </tr>
    </s:iterator>

    </tbody>
</table>