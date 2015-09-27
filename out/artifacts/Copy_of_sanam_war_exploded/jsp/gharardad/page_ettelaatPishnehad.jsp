<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--------------------------------------------------------------------------------------------------------%>
<!--div class="expandableTitleBar" id="expandableAsl">
    <p class="heading">
        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        جستجو
    </p>

    <div class="content" style="display:none;" id="searchAslContent">
        <%--<%@include file="/jsp/pishnahad/search.jsp" %>--%>
    </div>
</div> -->
<script type="text/javascript">
    function displaytagform(formname, fields){
        var objfrm = document.forms[formname];
        for (j=fields.length-1;j>=0;j--){
            $(objfrm).prepend("<input type='hidden' id='embedI_"+fields[j].f+"' name='"+fields[j].f+"' value='"+fields[j].v+"'></input>");
//                    $('#embedI_'+fields[j].f).attr("name", fields[j].f);
//                    $('#embedI_'+fields[j].f).attr("value", fields[j].v);
        }
        objfrm.submit();
    }
    function displaytagform_someTakhsisBeKarshenas(formname, fields){
        var objfrm = document.forms[formname];
        if($(objfrm).serialize().indexOf('_chk') == -1){
            alertMessage("پیشنهادی انتخاب نشده است.");
        }else{
            for (j=fields.length-1;j>=0;j--){
                $(objfrm).prepend("<input type='hidden' id='embedI_"+fields[j].f+"' name='"+fields[j].f+"' value='"+fields[j].v+"'></input>");
//                        $('#embedI_'+fields[j].f).attr("name", fields[j].f);
//                        $('#embedI_'+fields[j].f).attr("value", fields[j].v);
            }
            dialogFormStatic('someTakhsisBeKarshenasDialogDiv', 'تخصیص به کارشناس', function(){
                objfrm.action = "/assignKarshenasToSomePishnehad?"+$('#takhsiskarshenasform').serialize()+"&logmessage="+$('#loggmessageSomeTakhsisBeKarshenasDialogDiv').val();
                objfrm.submit();
            });
        }
    }
    $(function(){
        $('#pishnehadsSelectAll').bind("change" ,function(){
            $('input[name="checkBoxPishnehad"]').each(function(){
                $(this).attr('checked', $('#pishnehadsSelectAll').attr('checked'));
            })
        });
        $('#checkBoxSelectAllPishnehad').bind("change" ,function(){
            $('input[name="checkBoxPishnehad"]').each(function(){
                $(this).attr('checked', $('#checkBoxSelectAllPishnehad').attr('checked'));
            })
        });
    });
</script>
<%--<div style="position:absolute;">--%>
    <%--<input type="button" style="float:right;" onclick="javascript:displaytagform_someTakhsisBeKarshenas('pishListFormOfRKM',[{f:'par',v:['aa%22az']}])" value="تخصیص به کارشناس"/>--%>
<%--</div>--%>
<div style="padding-top:27px"></div>
<form id="pishListFormOfRKM" action="?" method="POST">
    <s:hidden key="gharardad.id" label="" theme="simple"/>
    <s:hidden name="selectedMenu" value="2"/>

                   <%--size="${sessionScope.viewReportResult.fullListSize}"--%>
                   <%--pagesize="${sessionScope.viewReportResult.objectsPerPage}"--%>
                      <%--partialList="true"--%>
                   <%--size="${pishnehadPaginatedList.fullListSize}"--%>
                   <%--pagesize="${pishnehadPaginatedList.objectsPerPage}"--%>
    <c:if test="${pishnehadPaginatedList.fullListSize > pishnehadPaginatedList.objectsPerPage}">
    <table>
        <tr>
            <td>
                <input id="checkBoxSelectAllPishnehad" type="checkbox" name="checkBoxSelectAllPishnehad" value="selected"/>
            </td>
            <td>
                <label for="checkBoxSelectAllPishnehad">
                    انتخاب همه
                    &nbsp;${pishnehadPaginatedList.getFullListSize()}&nbsp;
                    پیشنهاد
                </label>
            </td>
        </tr>
    </table>
    </c:if>
    <display:table form="pishListFormOfRKM" excludedParams="_chk selectedMenu gharardad.id pishnehadSearch*"
                   decorator="org.displaytag.decorator.CheckboxTableDecorator"
                   export="true" id="tableforaslid" uid="rpResult" name="pishnehadPaginatedList"
                   sort="external" htmlId="tblforaslid"
                   requestURI="" clearStatus="true" keepStatus="false"
                   style="width: 100%; margin: 0 auto;">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        %>
        <%--<display:setProperty name="pagination.pagenumber.param" value="pageNum"/>--%>
        <%--<display:column property="checkbox" title="<input id='pishnehadsSelectAll' type='checkbox' title='انتخاب/حذف همه'/>"--%>
                        <%--style="background:${rpResult.state.peygirKonande.roleColor};"/>--%>
        <display:column media="html" style="background:${rpResult.state.peygirKonande.roleColor};" title="<input id='pishnehadsSelectAll' type='checkbox' title='انتخاب/حذف همه'/>">
                <input type="checkbox" name="checkBoxPishnehad" value="${rpResult.id}"/>
        </display:column>
        <display:column title="" style="background:${rpResult.state.peygirKonande.roleColor};" media="html">
            <c:if test="${rpResult.pishpardakhtOK}"><img src="/img/dollar.png"/></c:if>
        </display:column>
        <display:column title="" style="background:${rpResult.state.peygirKonande.roleColor};" media="html">
            <a href="/editShowForm?pishnehad.id=${rpResult.id}" style="padding:7px; display:block;">نمايش</a>
        </display:column>
        <display:column title="ردیف" style="background:${rpResult.state.peygirKonande.roleColor};">${rpResult_rowNum}</display:column>
        <display:column property="radif" sortable="true" title="کد رهگیری"
                        style="background:${rpResult.state.peygirKonande.roleColor}; white-space: nowrap;"/>
        <display:column property="namayande.name" title="نام نماینده/کارگزار"
                        style="background:${rpResult.state.peygirKonande.roleColor};"/>
        <display:column title="کارشناس" style="background:${rpResult.state.peygirKonande.roleColor};">
            ${rpResult.karshenas.firstName}&nbsp;${rpResult.karshenas.lastName}
        </display:column>
        <display:column property="jadidDate" title="تاریخ ثبت وضعیت جدید"
                        style="background:${rpResult.state.peygirKonande.roleColor};"/>
        <display:column property="state.stateName" title="وضعیت"
                        style="background:${rpResult.state.peygirKonande.roleColor};"/>
        <display:column title="نام بیمه شده" style="background:${rpResult.state.peygirKonande.roleColor};">
            ${rpResult.bimeShode.shakhs.name}&nbsp;${rpResult.bimeShode.shakhs.nameKhanevadegi}
        </display:column>
        <display:column property="noeGharardadShort" title="نوع قرارداد"
                        style="background:${rpResult.state.peygirKonande.roleColor};"/>
        <display:column property="namayande.ostan.cityNameWithoutOstan" title="استان نمایندگی"
                        style="background:${rpResult.state.peygirKonande.roleColor};"/>
        <display:column property="estelam.sarmaye_paye_fot" title="سرمایه فوت (ریال)"
                        style="background:${rpResult.state.peygirKonande.roleColor};"/>
    </display:table>
</form>
<div id="someTakhsisBeKarshenasDialogDiv" style="display:none;direction:rtl;">
    <%--<%@include file="/jsp/pishnahad/subForm/takhsisBeKarshenas.jsp"%>--%>
</div>
<br/><br/>
<table style="margin: 0 auto;">
    <tr>
        <sec:authorize ifAnyGranted="ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI,ROLE_KARSHENAS_SODUR,ROLE_JAMI_KHAS,ROLE_SUPERVISOR">
        <td>
            <input type="button" value="صدور بیمه نامه"
                   onclick="$('#sodurJami').show()"
                   style="width: 150px;"/>
        </td>
        <td>
            <input type="button" value="آپلود فایل بیمه گذار" onclick="$('#uploadBimeGozar').show()"
                   style="width: 150px;">
        </td>
        </sec:authorize>
        <td>
            <input type="button" value="افزودن پیشنهاد"
                   onclick="window.location='/editShowForm?pishnehad.gharardad.id=<s:property value="gharardad.id"/>'"
                   style="width: 150px;">
        </td>
        <td>
            <input type="button" value="حذف پیشنهاد"
                   onclick="window.location='/hazfPishnehad?'+$('#pishListFormOfRKM').serialize()"
                   style="width: 150px;">
        </td>
        <sec:authorize ifAnyGranted="ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI,ROLE_KARSHENAS_SODUR,ROLE_JAMI_KHAS,ROLE_SUPERVISOR">
        <td>
            <input type="button" value="ارسال نهایی"
                   onclick="window.location='/ersalNahaiPishnehad?gharardad.id=<s:property value="gharardad.id"/>&selectedMenu=2'"
                   style="width: 150px;">
        </td>
        </sec:authorize>
    </tr>
    <tr>
        <td>
            <input type="button" value="محاسبه مبلغ پیش پرداخت"
                   onclick="window.open('/printMohasebePishPardakht?'+$('#pishListFormOfRKM').serialize())"
                   style="width: 150px;">
        </td>
        <td>
            <input type="button" value="نهایی کردن پیشنهادها"
                   onclick="window.location ='/nahayeeShodJami?'+$('#pishListFormOfRKM').serialize()"
                   style="width: 150px;">
        </td>
        <sec:authorize ifAnyGranted="ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI,ROLE_KARSHENAS_SODUR,ROLE_JAMI_KHAS,ROLE_SUPERVISOR">
            <td>
            <input type="button" value="تغییر وضعیت به ارسال شد"
                   onclick="window.location ='/ersalShodSodurJami?'+$('#pishListFormOfRKM').serialize()"
                   style="width: 150px;">
        </td>
            <td>
            <input type="button" value="ارجاع به پزشک"
                   onclick="window.location='/erjaPezeshPishnehad?'+$('#pishListFormOfRKM').serialize()"
                   style="width: 150px;">
        </td>
        <td>
            <input type="button" value="تخصیص به کارشناس" onclick="$('#takhsisKarshenas').show()" style="width: 150px;">
        </td>
            </sec:authorize>
    </tr>
</table>
<br/><br/>

<div id="uploadBimeGozar" style="display: none">
    <form action="/uploadFileBimeGozar" enctype="multipart/form-data" method="post">
        <s:file name="uploadFile" theme="simple"/>
        <s:hidden key="gharardad.id" theme="simple" label=""/>
        <s:hidden name="selectedMenu" value="2"/>
        <s:submit theme="simple" value="آپلود"/>
    </form>
</div>


<br/>
<div id="takhsisKarshenas" style="display: none">
    <form id="karshenasListGharardad" action="" name="karshenasListGharardad">

        <s:doubleselect formName="karshenasListGharardad" doubleList="karshenasha.get(top)" id="krshnsDblSelct"
                        doubleName="karshenasId" list="karshenasha.keys" doubleListKey="id"
                        doubleListValue="fullName" listValue="cityName" listKey="cityId" theme="simple" value="%{gharardad.namayande.ostan.cityId}" doubleValue="2471"/>
        <input type="button" value="ثبت برای پیشنهاد های انتخاب شده"
               onclick="window.location='/gharardadTakhsisKarshenas?'+$('#pishListFormOfRKM').serialize()+'&'+$('#karshenasListGharardad').serialize()">
    </form>

</div>
<div id="sodurJami" style="display: none">
    <form id="sodurJamiForm" action="/gharardadSodurBimename">
        <table style="margin: 0 auto; width: 80%;">
            <tr>
                <%--<td>--%>
                    <%--تاریخ شروع:--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<s:textfield key="tarikhShoru" label="" theme="simple" cssClass="datePkr"--%>
                                 <%--cssStyle="width: 176px;float:right;margin-right:3px;"/>--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--تاریخ صدور:--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<s:textfield key="tarikhSodur" label="" theme="simple" cssClass="datePkr"--%>
                                 <%--cssStyle="width: 176px;float:right;margin-right:3px;"/>--%>
                <%--</td>--%>
            </tr>
            <tr>
                <td>
                  امضا کننده اول
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" readonly="readonly" id="bimename_emzaKonandeAval" style="width:120px"
                            />
                    <input type="hidden" name="emzaKonandeAval.id" id="bimename_emzaKonandeAval_id"
                            />
                    <input type="button" value="جستجو"
                           onclick="$('#NafareEmza').val('#bimename_emzaKonandeAval');fillEmze();"/>
                </td>
                <td>
امضا کننده دوم
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" readonly="readonly" id="bimename_emzaKonandeDovom"
                           style="width:120px"
                            />
                    <input type="hidden" name="emzaKonandeDovom.id" id="bimename_emzaKonandeDovom_id"
                            />
                    <input type="button" value="جستجو"
                           onclick="$('#NafareEmza').val('#bimename_emzaKonandeDovom');fillEmze(); "/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" value="صدور با اعلام اقساط"
                           onclick="window.location='/gharardadSodurBimename?'+$('#sodurJamiForm').serialize()+'&'+$('#pishListFormOfRKM').serialize()">
                </td>
            </tr>
        </table>
    </form>

</div>
<input type="hidden" id="NafareEmza"/>

<div id="tblEmza" style="display: none">
    <table cellpadding="3" cellspacing="3" border="0"
           style="margin-left:auto;margin-right:auto;align:center;direction: rtl;">
        <tr>
            <td colspan="5">جستحو شخص امضا کننده</td>
        </tr>
        <tr>
            <td>کد پرسنلی</td>
            <td><input type="text" id="emzaPersonalCode"/></td>
            <td>نام</td>
            <td><input type="text" id="emzaName"/></td>
            <td><input type="button" value="جستجو" id="btnSearch"
                       onclick="searchEmaz()"></td>
        </tr>
    </table>
    <div id="searchResualt"></div>
</div>
<script type="text/javascript">
    function searchEmaz() {
        var emzaName = $('#emzaName').val();
        var emzaPersonalCode = $('#emzaPersonalCode').val();
        $.post("/findEmzaKonande?user.firstName=" + emzaName + "&user.personalCode=" + emzaPersonalCode, function (msg) {
            $('#searchResualt').html(msg);
        });
        fillEmze();
    }
    $(document).ready(function () {
        $('#emzaName').val('');
        $('#emzaPersonalCode').val('');
        $('#tblEmza').dialog({
         modal:true, resizable:false, autoOpen:false,
         width:700, maxHeight:500, minHeight:100,
         title:"جستحو شخص امضا کننده"});
    });
    function fillEmze() {
        $('#tblEmza').dialog('open');
    }
    function selectRow(id, fn, ln) {
        var ctrlId = $('#NafareEmza').val();
        $(ctrlId).val(fn + " " + ln);
        $(ctrlId + '_id').val(id);
        hideEmzaModal();
    }
    function hideEmzaModal() {
        $('#emzaName').val('');
        $('#emzaPersonalCode').val('');
        $('#tblEmza').dialog('close');
        $('#searchResualt').html('');
    }
</script>
