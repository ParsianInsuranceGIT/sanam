<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page import="com.bitarts.parsian.Core.ConstantPaging" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="../../css/styles.css"/>
<%--<script type="text/javascript" src="/js/common.js"></script>--%>
<script type="text/javascript">
    $(".jtable th").each(function ()
    {
        $(this).addClass("ui-state-default");
    });
    $(".jtable td").each(function ()
    {
        $(this).addClass("ui-widget-content");
    });
    $(".jtable tr").hover(function ()
    {
        //        $(this).children("td").addClass("ui-state-hover");
    }, function ()
    {
        //        $(this).children("td").removeClass("ui-state-hover");
    });
    $("input,textarea").each(function ()
    {
        $(this).addClass("ui-state-default  ui-corner-all");
    });
    $("select").each(function ()
    {
        $(this).addClass("ui-state-default  ui-corner-all");
    });
    $(".help, .comment").tipsy({gravity: 's'});
    $("form, .vld").validationEngine({promptPosition: "topLeft"});
    $('.dblRadio').buttonset();
    $(".tipsi").tipsy({gravity: 's'});


    $(".digitSeparators").each(function ()
    {
        $(this).keyup();
    });
    $(".digitSeparatorsManfi").each(function ()
    {
        $(this).keyup();
    });
</script>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page import="com.bitarts.parsian.Core.ConstantPaging" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="../../css/styles.css"/>
<%--<script type="text/javascript" src="/js/common.js"></script>--%>
<script type="text/javascript">
    $(".jtable th").each(function ()
    {
        $(this).addClass("ui-state-default");
    });
    $(".jtable td").each(function ()
    {
        $(this).addClass("ui-widget-content");
    });
    $(".jtable tr").hover(function ()
    {
        //        $(this).children("td").addClass("ui-state-hover");
    }, function ()
    {
        //        $(this).children("td").removeClass("ui-state-hover");
    });
    $("input,textarea").each(function ()
    {
        $(this).addClass("ui-state-default  ui-corner-all");
    });
    $("select").each(function ()
    {
        $(this).addClass("ui-state-default  ui-corner-all");
    });
    $(".help, .comment").tipsy({gravity: 's'});
    $("form, .vld").validationEngine({promptPosition: "topLeft"});
    $('.dblRadio').buttonset();
    $(".tipsi").tipsy({gravity: 's'});


    $(".digitSeparators").each(function ()
    {
        $(this).keyup();
    });
    $(".digitSeparatorsManfi").each(function ()
    {
        $(this).keyup();
    });
</script>
<table class="jtable">
    <tr>
        <th>کد نماینده ارشد</th>
        <th>نام نماینده ارشد</th>
        <th>کد نماینده</th>
        <th>نام نماینده</th>
    </tr>
    <%--<s:iterator value="%{namayande}" id="row" status="stt_n">--%>
        <tr>
            <td>${namayande.senior!=null?namayande.senior.kodeNamayandeKargozar:'-'}</td>
            <td>${namayande.senior!=null?namayande.senior.name:'-'}</td>
            <td>${namayande.kodeNamayandeKargozar}</td>
            <td>${namayande.name}</td>
        </tr>
    <%--</s:iterator>--%>
</table>
