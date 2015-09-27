<%@ page import="com.bitarts.parsian.model.User" %>
<%@ page import="com.bitarts.parsian.model.constantItems.City" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.model.pishnahad.Pishnehad" %>
<%@ page import="com.bitarts.parsian.service.IPishnehadService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/taglibs.jsp"%>

<%--<% List<User> karshenasha = (List<User>) request.getAttribute("karshenasha"); %>--%>
<%--<% List<City> ostanha = (List<City>) request.getAttribute("ostanha"); %>--%>

<form id="takhsiskarshenasform" name="form4takhsiskarshenas" action="/assignKarshenas.action" method="post">
    <input type="hidden" name="pishnehad.id" value="${pishnehadRun.id}"/>
    <input type="hidden" id="takhsiskharshenastransid" name="transitionId" value="8"/>
    <input type="hidden" name="logmessage" id="takhsisloggid" value="تخصیص به کارشناس"/>
    <select id="karshenas_ostan" onchange="updateKarshenasa(this.value);">
        <c:forEach var="ostan" items="${ostanha}" varStatus="i">
            <option value="${ostan.cityId}" ${pishnehadRun.namayande.ostan.cityId == ostan.cityId ? "selected='selected'" : ""}>${ostan.cityName}</option>
        </c:forEach>
    </select>
    <select name="karshenasId" id="whichkarshenas" class="validate[required]"></select>
    <c:if test="${pishnehad != null}"><input type="button" onclick="submitTheFormForTakhsis();" value="تایید"/></c:if>
</form>

<script type="text/javascript">

    var karshenasaCount = ${fn:length(karshenasha)};
    var karshenasNameArray = new Array();
    var karshenasValueArray = new Array();
    var karshenasOstanArray = new Array();
    <c:forEach var="karshenas" items="${karshenasha}" varStatus="i">
    karshenasNameArray[${i.index}] = '${karshenas.firstName} ${karshenas.lastName} - ${karshenas.assignCount} (${karshenas.sarmayeFotMaxForKarshenasiFormatted})';
    karshenasValueArray[${i.index}] = '${karshenas.id}';
    karshenasOstanArray[${i.index}] = '${karshenas.mojtamaSodoor.ostan.cityId}';
    </c:forEach>
    var ostanIdTehran = '10002';


    function submitTheFormForTakhsis(){
    <c:if test='${pishnehad.state.id == 70}'>
        $("#transitionSelector").val(8);
        $("#takhsiskharshenastransid").val($("#transitionSelector").val());
    </c:if>
    <c:if test='${pishnehad.state.id == 40}'>
        $("#transitionSelector").val(9);
        $("#takhsiskharshenastransid").val($("#transitionSelector").val());
    </c:if>
    <c:if test='${pishnehad.state.id == 60}'>
        $("#transitionSelector").val(10);
        $("#takhsiskharshenastransid").val($("#transitionSelector").val());
    </c:if>

        openDialogBoxAndSubmitTo("takhsisloggid","takhsiskarshenasform");
    }

    function updateKarshenasa(value)
    {
        $('#whichkarshenas').html('');
        for(loop = 0; loop < karshenasaCount; loop++)
        {
            if(karshenasOstanArray[loop] == ostanIdTehran || karshenasOstanArray[loop] == value)
            {
                $('<option value="'+karshenasValueArray[loop]+'">'+karshenasNameArray[loop]+'</option>').appendTo('#whichkarshenas');
            }
        }
    }
</script>
