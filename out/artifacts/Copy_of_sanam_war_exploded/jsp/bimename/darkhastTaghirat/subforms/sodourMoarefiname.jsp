<%@ page import="com.bitarts.parsian.model.transitionwise.PezeshkSabtNazar" %>
<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript">
    var azemayeshIdArray = new Array();
    var azemayeshArray = new Array();
    var azemayeshTypeArray = new Array();
    <c:forEach var="row" items="${clinics}" varStatus="i">
    azemayeshIdArray[${i.index}] = new Array(
            <c:forEach var="azmayesh" items="${row.azmayeshs}" varStatus="j">
            '${azmayesh.id}'
            <c:if test="${j.index+1 lt fn:length(row.azmayeshs)}">,</c:if>
            </c:forEach>
            );
    azemayeshArray[${i.index}] = new Array(
            <c:forEach var="azmayesh" items="${row.azmayeshs}" varStatus="j">
            '${azmayesh.azmayeshName.name}'
            <c:if test="${j.index+1 lt fn:length(row.azmayeshs)}">,</c:if>
            </c:forEach>
            );
    azemayeshTypeArray[${i.index}] = new Array(
            <c:forEach var="azmayesh" items="${row.azmayeshs}" varStatus="j">
            '${azmayesh.azmayeshName.azmayeshType.type}'
            <c:if test="${j.index+1 lt fn:length(row.azmayeshs)}">,</c:if>
            </c:forEach>
            );
    </c:forEach>

    $(document).ready
    (
        function()
        {
            <c:if test="${darkhastTaghirat.moarefinameList == null || fn:length(darkhastTaghirat.moarefinameList)==0}">
            $('#btn_1').show();
            $('#btn_2').hide();
            </c:if>
            <c:if test="${darkhastTaghirat.moarefinameList != null && fn:length(darkhastTaghirat.moarefinameList)>0}">
            $('#btn_2').show();
            $('#btn_1').hide();
            </c:if>
        }
    );

    function fillAzemayeshs(index){
        var str = "";
        if(azemayeshArray[index]){
            for(var j = 0 ; j < azemayeshArray[index].length ; j++)
                str += "<tr><td><input type=\"checkbox\" id=\"azemayesh_chb_"+j+"\"/><input type=\"hidden\" id=\"azemayesh_inp_"+j+"\" name=\"moarefiname.azmayeshList["+j+"].id\" value=\""+azemayeshIdArray[index][j]+"\"/></td>"+
                       "<td>"+azemayeshArray[index][j]+"</td>"+
                       "<td>"+azemayeshTypeArray[index][j]+"</td></tr>";
        }
        $('#azemayeshHa').html(str);
    }
    function fillAndSubmit()
    {
        $('body').append('<form action="/addMoarefinameForTgrAjaxly" id="sodurMoarefinameForTaghiratForm" method="post" style="display:none;"></form>');
        $('#sodurMoarefinameForTaghiratForm').html($('#sodurMoarefiname').html());
        if ($('#sodurMoarefinameForTaghiratForm #moarefiname_clinicName').val() == '111111')
        {
            $('#sodurMoarefinameForTaghiratForm #moarefiname_clinicName').remove();
        }
        $.post('/addMoarefinameForTgrAjaxly?darkhastTaghirat.id=${darkhastTaghirat.id}&pishnehad.id=${pishnehad.id}',
                $('#sodurMoarefinameForTaghiratForm').serialize(),
                function (msg)
                {
                    $('#div_pshs_asli').html(msg);
                    $(".jtable th").each(function ()
                    {
                        $(this).addClass("ui-state-default");
                    });
                    $(".jtable td").each(function ()
                    {
                        $(this).addClass("ui-widget-content");
                    });
                    if(msg.indexOf('nll')==-1)
                    {
                        $('#btn_2').show();
                        $('#btn_1').hide();
                    }
                }
        );
    }

    function submitForTransition(){


        $('#pdarkhast_popup').dialog({
                    modal:true,
                    width: 260,
                    resizable:false,
                    closeText: "",
                    title:"گذاشتن پیغام",
                    buttons: {
                        "بستن": function() {
                            $(this).dialog("close");
                        },
                        "انجام": function(){

                            $('#inlog_message_darkhast_taghirat').val($('#darkhastloggmessage').val());
                            $('body').append('<form action="/sodurMoarefinameForTaghirat" id="sodurMoarefinameForTaghiratForm1" method="post" style="display:none;"></form>');
                            $('#sodurMoarefinameForTaghiratForm1').html($('#sodurMoarefiname').html());
                            $('#sodurMoarefinameForTaghiratForm1').append('<input type="hidden" name="darkhastTaghirat.id" value="${darkhastTaghirat.id}"/>')
                            $('#sodurMoarefinameForTaghiratForm1').append('<input type="hidden" name="transitionId" value="9020"/>')
                            $('#sodurMoarefinameForTaghiratForm1').append('<input type="text" name="logmessage" id="inlog_message_darkhast_taghirat"/>')
                            if($('#sodurMoarefinameForTaghiratForm1 #moarefiname_clinicName').val() == '111111'){
                                $('#sodurMoarefinameForTaghiratForm1 #moarefiname_clinicName').remove();
                            }
                            $('#sodurMoarefinameForTaghiratForm1').submit();
                        }
                    }
                });

    }
    function submitSodorMoarefiForm(){
        $("#dakhastTransitionSelector").val('9020');
        //                        $("#moarefitransid").val($("#transitionSelector").val());
        var elems = $("#azemayeshHa input[type='checkbox']").nextAll();
        var size = elems.length;
        var counter = 0;
        if(size != 0){
            elems.each(function(){
                if(!$(this).is(':checked')){
                    $('#azemayesh_inp_'+this.id.split('azemayesh_chb_')[1]).attr('name','');
                }
                counter++;
                if(counter == size){
                    fillAndSubmit();
                }
            });
        }else{
            fillAndSubmit();
        }
    }

</script>
<div id="sodurMoarefiname">
    <div id="div_pshs_asli">
        <div class="expandableTitleBar" id="poosheshasli">
            <p class="heading">
                <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                جدول معرفی نامه های پزشکی
            </p>
        </div>
        <table class="inputList jtable" border="0" cellspacing="5" cellpadding="1">
        <tr>
            <th width="9%">شماره</th>
            <th>تاريخ ايجاد</th>
            <th>نام كلينيك</th>
            <th>وضعيت</th>
            <th>پرینت</th>
            <th>ابطال معرفي نامه</th>
        </tr>
            <c:if test="${darkhastTaghirat.moarefinameList == null || fn:length(darkhastTaghirat.moarefinameList)==0}"><tr><td colspan="5">تا کنون معرفی نامه ای برای این درخواست ثبت نشده است.</td></tr></c:if>
            <c:if test="${darkhastTaghirat.moarefinameList != null && fn:length(darkhastTaghirat.moarefinameList)>0}">
            <c:forEach var="row" items="${darkhastTaghirat.moarefinameList}" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${row.tarikhSodur}</td>
                    <td>${row.clinic.clinicName}</td>
                    <td>${row.vaziat}</td>
                    <td>
                            <a onclick="window.open('/print_darkhasteMoayenatePezeshki?pishnehadReport.pishnehad.id=${pishnehad.id}&pishnehadReport.moarefiname.id=${row.id}');" href="javascript:void(0);">پرینت درخواست معاینات پزشکی</a>

                    </td>
                    <td><a href="javascript:void(0);">ابطال</a></td>
                </tr>
            </c:forEach>
        </c:if>
        </table>
    </div>
    <c:if test="${darkhastTaghirat.state.id==9080}">
        <table class="inputList" style="margin-top:30px" border="0" cellspacing="5" cellpadding="1">
            <col class="inputCol">
            <col class="inputCol">
            <tr>
                <td>
                    <input type="text" name="moarefiname.tarikhSodur" id="moarefiname_tarikhSodur" readonly="readonly" value="<%=DateUtil.getCurrentDate()%>">
                    <label>تاریخ صدور : </label>
                </td>
                <td>
                    <input type="text" id="moarefiname_tarikheEtebar" readonly="readonly" value="<%=DateUtil.addDays(DateUtil.getCurrentDate(), 15)%>">
                    <label>تاریخ اعتبار : </label>
                </td>
            </tr>
            <tr>
                <td>

                    <select name="moarefiname.clinic.id" id="moarefiname_clinicName" onchange="fillAzemayeshs(this.selectedIndex);">
                        <c:if test="${clinics != null}">
                            <c:forEach var="row" items="${clinics}">
                                <option value="${row.id}">${row.clinicName}</option>
                            </c:forEach>
                        </c:if>
                        <option>سایر</option>
                    </select>
                    <label>نام کلینیک : </label>
                </td>
                <td>
                    <input type="text" id="moarefiname_sayer" readonly="readonly" disabled="disabled">
                    <label>سایر : </label>
                </td>
            </tr>
        </table>
        <table class="inputList jtable" style="margin-top:30px" border="0" cellspacing="5" cellpadding="1">
            <tr>
                <th width='6%'>انتخاب</th>
                <th>نوع آزمايش</th>
                <th>نام آزمايش</th>
            </tr>
            <tbody id="azemayeshHa"></tbody>
        </table>

            <input id="btn_1" type="button" onclick="submitSodorMoarefiForm();" style="float:left;margin-left:20px; display: none;" value="صدور معرفی نامه" >
            <input id="btn_2" type="button" onclick="submitForTransition();" style="float:left;margin-left:20px; display: none;" value="اتمام صدور معرفی نامه" >
    </c:if>
</div>