<%@ include file="/jsp/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    //    $("form, .vld").validationEngine({promptPosition:"topLeft"});
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

<table align="center" class="jtable resultDets" cellpadding="0" cellspacing="0" style="margin:0 auto;" width="90%">
    <tr>
        <th>شماره سریال پروژه کارمزد</th>
        <th>نوع کارمزد</th>
        <th>تعرفه</th>
        <th>مبلغ کارمزد پرداختی</th>
        <th>تاریخ سند</th>
        <th>مقدار سند</th>
        <%--<th>ردیف</th>--%>
    </tr>
    <c:if test="${khateSanadList==null || fn:length(khateSanadList)==0}">
        <tr>
            <td colspan="7">رکوردی پیدا نشد</td>
        </tr>
    </c:if>
    <c:forEach var="row" items="${khateSanadList}" varStatus="i">
        <c:forEach var="row1" items="${row.karmozdGhestList}" varStatus="j">
            <sec:authorize ifAnyGranted="ROLE_KARMOZD_NAMAYANDE">
                <c:if test="${(row1.karmozdNamayande==null || row1.karmozdNamayande.namayande.id==user.namayandegi.id)}">
                    <c:if test="${(row1.type=='ADI' ||row1.type=='TAGHIR_CODE_DAEM'||row1.type=='PARDAKHTE_CODE_MOVAGHATI' ||row1.type=='TAGHIRAT'||row1.type=='TALIGHI'|| row1.type=='TADILI'
                              || row1.type=='KARMOZD_TASHVIGHI_VOSULI' || row1.type=='POOSHESH_EZAFI' || row1.type=='VOSULI' || row1.type=='SENIOR')}">
                        <tr>
                            <td>${row1.karmozd.serial}</td>
                            <td>
                                    ${row1.typeFa}
                            </td>
                            <td>${row1.tarefeFarsi}</td>
                            <td>${row1.karmozdAMountString}</td>
                            <td>${row.sanad.createdDate}</td>
                            <td>${row.amount}</td>
                                <%--<td>${i.index + 1}</td>--%>
                        </tr>
                    </c:if>
                </c:if>
            </sec:authorize>
            <sec:authorize ifAnyGranted="ROLE_KARMOZD">
                <c:if test="${(row1.type=='ADI' ||row1.type=='PARDAKHTE_CODE_MOVAGHATI' ||row1.type=='TAGHIR_CODE_DAEM' ||row1.type=='TAGHIRAT'||row1.type=='TALIGHI'|| row1.type=='TADILI'
                              || row1.type=='KARMOZD_TASHVIGHI_VOSULI' || row1.type=='POOSHESH_EZAFI' || row1.type=='VOSULI' || row1.type=='SENIOR')}">
                    <tr>
                        <td>${row1.karmozd.serial}</td>
                        <td>
                            ${row1.typeFa}
                        </td>
                        <td>${row1.tarefeFarsi}</td>
                        <td>${row1.karmozdAMountString}</td>
                        <td>${row.sanad.createdDate}</td>
                        <td>${row.amount}</td>
                        <%--<td>${i.index + 1}</td>--%>
                    </tr>
                </c:if>
            </sec:authorize>
        </c:forEach>
    </c:forEach>
    <c:forEach var="row2" items="${kgYekjaList}" varStatus="it">
        <c:if test="${row2.type=='ADI' ||row2.type=='PARDAKHTE_CODE_MOVAGHATI' ||row2.type=='TAGHIR_CODE_DAEM' || row2.type=='TALIGHI'}">
            <tr>
                <td>${row2.karmozd.serial}</td>
                <td>
                        ${row2.typeFa}
                </td>
                <td>${row2.tarefeFarsi}</td>
                <td>${row2.karmozdAMountString}</td>
                <td>${row2.sanad.createdDate}</td>
                <td>${row.amount}</td>
                    <%--<td>${i.index + 1}</td>--%>
            </tr>
        </c:if>
    </c:forEach>
</table>
