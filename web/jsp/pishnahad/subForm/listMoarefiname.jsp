<%@ page import="com.bitarts.parsian.model.User" %>
<%@ page import="com.bitarts.parsian.model.pishnahad.Moarefiname" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="expandableTitleBar" id="poosheshasli">
    <p class="heading">
        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        جدول معرفی نامه های پزشکی
    </p>
</div>
<table class="inputList jtable" border="0" cellspacing="1" cellpadding="1">
    <tr>
        <th>شماره معرفي نامه</th>
        <th>تاريخ ايجاد</th>
        <th>نام كلينيك</th>
        <th>وضعيت</th>
        <th>چاپ معرفي نامه</th>
        <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
        <c:if test = '${readOnlyMode != true}'>
        <th>ابطال معرفی نامه</th>
        </c:if>
        </sec:authorize>
    </tr>
    <c:choose>
        <c:when test="${pishnehad.moarefinameList == null || fn:length(pishnehad.moarefinameList) == 0}">
            <tr>
                <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                <td colspan="6">
                </sec:authorize>
                <sec:authorize ifAllGranted="ROLE_NAMAYANDE">
                <td colspan="5">
                </sec:authorize>
                    تا کنون معرفی نامه ای برای این پیشنهاد ثبت نشده است.
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="row" items="${pishnehad.moarefinameList}" varStatus="i">
                <c:if test="${row.vaziat=='BATEL_SHODE'}">
                    <c:set var="theColor" value="lightgray"/>
                </c:if>
                <c:if test="${row.vaziat=='DAR_JARYAN'}">
                    <c:set var="theColor" value="white"/>
                </c:if>
                <tr>
                    <td style="background:${theColor};">${i.index+1}</td>
                    <td style="background:${theColor};">${row.tarikhSodur}</td>
                    <td style="background:${theColor};">
                    <c:if test="${row.clinic.id == 111111}">
                        ${row.clinicNameSayer}
                    </c:if>
                    <c:if test="${row.clinic.id != 111111}">
                        ${row.clinic.clinicName}
                    </c:if>
                    </td>
                    <td style="background:${theColor};">
                        <c:if test="${row.vaziat=='BATEL_SHODE'}">
                            باطل شده
                        </c:if>
                        <c:if test="${row.vaziat=='DAR_JARYAN'}">
                            در جریان
                        </c:if>
                    </td>
                    <td style="background:${theColor};">
                        <c:if test="${row.vaziat=='DAR_JARYAN'}">
                        <a style="font-weight: bold; color:red" onclick="chapMoarefiname(${pishnehad.id},${row.id});" href="javascript:void(0);">چاپ معرفی نامه</a>
                        </c:if>
            <c:if test="${row.vaziat=='BATEL_SHODE'}">
-
            </c:if>
                    </td>
                    <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                    <c:if test = '${readOnlyMode != true}'>
                    <td style="background:${theColor};">
                        <c:if test="${row.vaziat=='BATEL_SHODE'}">
                            -
                        </c:if>
                        <c:if test="${row.vaziat=='DAR_JARYAN'}">
                            <a href="javascript:void(0);" onclick="window.location='/deleteMoarefiname.action?pishnehad.id=${pishnehad.id}&transitionId=59&moarefiname.id=${row.id}'">ابطال</a>
                        </c:if>
                    </td>
                    </c:if>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>

<sec:authorize ifAllGranted="ROLE_NAMAYANDE">
<c:if test="${pishnehad.state.id==120 && readOnlyMode == false}">
    <table align="center">
        <tr>
            <td>
                <input type="button" value="چاپ معرفي‌نامه و منتظر پاسخ آزمايش" onclick="chapMoarrefiName();" disabled="disabled" class="ui-state-disabled" id="chapTransButton"/>
            </td>
            <%--<td>--%>
                <%--<input type="button" value="درخواست ابطال و صدور معرفي‌نامه جديد" onclick="sodourMoarefinameJadid();"/>--%>
            <%--</td>--%>
            <td>
                <input type="button" value="ابطال معرفي‌نامه و پيشنهاد شرايط جديد" onclick="sharayeteJadid();"/>
            </td>
        </tr>
    </table>
</c:if>
</sec:authorize>
<script type="text/javascript">
    function chapMoarefiname(pid, rid){
        $('#chapTransButton').removeAttr('disabled');
        $('#chapTransButton').removeClass('ui-state-disabled');
        window.open('/print_darkhasteMoayenatePezeshki.action?pishnehadReport.pishnehad.id='+pid+'&pishnehadReport.moarefiname.id='+rid);
    }
    function chapMoarrefiName(){
        $("#transitionSelector").val('17');
        submitTransitionForm();
    }
    function sodourMoarefinameJadid(){
        $("#transitionSelector").val('57');
        submitTransitionForm();
    }
    function sharayeteJadid(){
        $("#tab_18").show();
        $('.tabsbtn').removeClass('activesubmit');
        $('#tab_18').addClass('activesubmit');
        $('.content').hide();
        $('#content_18').show();
    }
</script>