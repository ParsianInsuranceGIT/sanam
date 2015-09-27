<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: Feb 16, 2011
  Time: 4:01:02 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/taglibs.jsp" %>


<html>
<head>
    <link type="text/css" rel="stylesheet" href="../../css/pishnahadeBimeOmreEnferadi.css"/>
    <script type="text/javascript" src="/jsp/pishnahad/pishnahadeBimeOmreEnferadi.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            <c:if test="${selectedMenu != null}">
            changeMenu('${selectedMenu}');
            </c:if>
        })
    </script>
    <title>قرارداد</title>
</head>
<body>
<div id="result" style="display:none;"></div>
<p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">

    <a href="/loginUser?selectedTab=tabs-7" class="tipsi ui-state-default" style="float:left;margin-left:2px" title="بازگشت"><span
            class="ui-icon ui-icon-arrowreturnthick-1-w">&nbsp;</span></a>
    <%--<a href="javascript:void(0)" onclick="sendPishnehadFormByAjax()" class="tipsi ui-state-default" style="float:left;margin-left:2px" title="ذخیره">--%>

</p>
<div id="tabcontainer">
    <div id="tabs">
        <a href='javascript:void(0);' type="button" id="tab_1" class="tabsbtn activesubmit">اطلاعات قرارداد</a>
        <c:if test="${gharardad.id != null}">
            <sec:authorize ifAnyGranted="ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI,ROLE_KARSHENAS_SODUR,ROLE_JAMI_KHAS,ROLE_SUPERVISOR">
                <a href='javascript:void(0);' type="button" id="tab_5" class="tabsbtn">مفاد قرارداد</a>
            </sec:authorize>
            <a href='javascript:void(0);' type="button" id="tab_2" class="tabsbtn">پیشنهاد های قرارداد</a>
            <a href='javascript:void(0);' type="button" id="tab_3" class="tabsbtn">بیمه نامه های قرارداد</a>
            <a href='javascript:void(0);' type="button" id="tab_4" class="tabsbtn">اطلاعات پرداخت</a>
        </c:if>
    </div>
    <div id="tabformcontent">
        <div class=content id="content_1">
            <c:if test="${selectedMenu== null || selectedMenu == 1}"><s:actionerror/><s:actionmessage/></c:if>
            <%@include file="/jsp/gharardad/page_ettelaatGharardad.jsp" %>
        </div>
        <c:if test="${gharardad.id != null}">
            <div style="display:none;" class=content id="content_2">
                <c:if test="${selectedMenu == 2}"><s:actionerror/><s:actionmessage/></c:if>
                <%@include file="/jsp/gharardad/page_ettelaatPishnehad.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_3">
                <c:if test="${selectedMenu == 3}"><s:actionerror/><s:actionmessage/></c:if>
                <%@include file="/jsp/gharardad/page_ettelaatBimename.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_4">
                <c:if test="${selectedMenu == 4}"><s:actionerror/><s:actionmessage/></c:if>
                <%@include file="/jsp/gharardad/page_ettelaatPardakht.jsp" %>
            </div>
            <%--<sec:authorize ifAnyGranted="ROLE_JAMI_KHAS">--%>
            <div style="display:none;" class=content id="content_5">
                <c:if test="${selectedMenu == 5}"><s:actionerror/><s:actionmessage/></c:if>
                <%@include file="/jsp/sodureJamieKhas/page_sodureJamieKhas.jsp" %>
            </div>
            <%--</sec:authorize>--%>
        </c:if>
    </div>
</div>


<br class="clear"/>
<br/>
<div>
    <input type="button" onclick="window.location='/loginUser?selectedTab=tabs-7'" value="بازگشت" style="float:left;"/>
</div>
<br/>

<div>&nbsp;</div>

</body>
</html>