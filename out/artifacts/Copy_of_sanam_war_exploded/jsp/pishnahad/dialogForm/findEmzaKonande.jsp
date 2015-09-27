<%@include file="/jsp/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--[--%>
<%--<c:forEach items="${emzaKonandeList}" var="emza" varStatus="i">--%>
<%--<c:if test="${i.index > 0 }">,</c:if>--%>
<%--{"id":"${emza.id}", "personalCode":"${emza.personalCode}", "job":"${emza.job}", "username":"${emza.user.username}", "firstName":"${emza.user.firstName}", "lastName":"${emza.user.lastName}"}--%>
<%--</c:forEach>--%>
<%--]--%>


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
<s:if test="%{findUserAction}">
    <table class="jtable resultDets inputList" cellpadding="0" cellspacing="0"
           style="border-spacing:0px;margin-left:auto;margin-right:auto;width:500px" border="0">
        <thead>
        <tr>
            <th class="ui-state-active" colspan="5">نتایج جستجو</th>
        </tr>
        <tr>
            <th>نام</th>
            <th>نام خانوادگی</th>
            <th>کد پرسنلی</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userList}" var="emza" varStatus="i">
            <tr id="rw_j" onclick="selectRow('${emza.id}' , '${emza.firstName}', '${emza.lastName}');"
                title="انتخاب">
                <td> ${emza.firstName} </td>
                <td> ${emza.lastName} </td>
                <td> ${emza.personalCode} </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</s:if>
<s:else>
    <table class="jtable resultDets inputList" cellpadding="0" cellspacing="0" style="border-spacing:0px;margin-left:auto;margin-right:auto;width:500px" border="0">
    <thead>
    <tr>
        <th class="ui-state-active" colspan="5">نتایج جستجو</th>
    </tr>
        <tr>
            <th >نام</th>
            <th >نام خانوادگی</th>
            <th>کد پرسنلی</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${emzaKonandeList}" var="emza" varStatus="i">
        <tr id="rw_j" onclick="selectRow('${emza.id}' , '${emza.user.firstName}', '${emza.user.lastName}');" title="انتخاب">
            <td> ${emza.user.firstName} </td>
            <td> ${emza.user.lastName} </td>
            <td> ${emza.user.personalCode} </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</s:else>