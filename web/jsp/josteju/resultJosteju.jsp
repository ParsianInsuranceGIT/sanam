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
        $(".OK td").each(function(){
            $(this).addClass("ui-widget-content");
        });
        $(".noOK td").each(function(){
            $(this).addClass("ui-widget-content");
            $(this).css("background", "#ffb6c1");
        });
//        $(".jtable tr").hover(function(){
//            $(this).children("td").addClass("ui-state-hover");
//        },function(){
//            $(this).children("td").removeClass("ui-state-hover");
//        });
//        $(".jtable tr").click(function(){
//            $(this).children("td").toggleClass("ui-state-highlight");
//        });
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
            <th >نام خانوادگی</th>
            <th >نام پدر</th>
            <th >کد ملی</th>
            <th >شماره شناسنامه </th>
        </tr>
    </s:else>
    </thead>

    <tbody>
    <s:iterator value="reportResult" id="row" status="stat">
        <s:if test="#row.getOK() == 'yes'">
            <tr id="rw_j" onclick="entekhabShakhs('<s:property value="#row.id"/>')" title="انتخاب"  class="OK">
        </s:if>
        <s:if test="#row.getOK() == 'no'">
            <tr class="noOK">
        </s:if>
            <td><s:property value="#row.getNaam()"/></td>
            <td><s:property value="#row.getNaamKhaanevaadegi()"/></td>
            <td><s:property value="reportResult[#stat.index].getNaamPedar()"/></td>
            <td><s:property value="reportResult[#stat.index].getCodeMelli()"/></td>
            <td><s:property value="reportResult[#stat.index].getShomareShenaasnaame()"/></td>
        </tr>
    </s:iterator>

    </tbody>
</table>