<%@ page import="com.bitarts.parsian.model.transitionwise.PezeshkSabtNazar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    List<PezeshkSabtNazar> pezeshkSabtNazars = (List<PezeshkSabtNazar>) pishnehadRun.getNazaraatePezeshk();
    int pezmajmou = 0;
    for (PezeshkSabtNazar pezeshkSabtNazar : pezeshkSabtNazars) {
        if(pezeshkSabtNazar.isFromPezeshk()){
            pezmajmou = pezmajmou + Integer.valueOf(pezeshkSabtNazar.getEzafeNerkh());
        }
    }
%>

<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        نظرات ثبت شده توسط پزشک
    </p>
    <table class="jtable" align="center" width="80%" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
        <thead>
        <tr>
            <th>
                نوع بیماری
            </th>
            <th>
                اضافه نرخ
            </th>
            <th>
                توضیحات
            </th>
            <th>
                حذف نظر
            </th>
        </tr>
        </thead>
        <tbody>
        <% if(pezeshkSabtNazars != null && pezeshkSabtNazars.size()>0){
            for (PezeshkSabtNazar pezeshkSabtNazar : pezeshkSabtNazars) {
            if(pezeshkSabtNazar.isFromPezeshk()){%>
        <tr>
            <td>
                <%=pezeshkSabtNazar.getNazar()%>
            </td>
            <td>
                <%=pezeshkSabtNazar.getEzafeNerkh()%>
            </td>
            <td>
                <c:set var="nazar" value="<%=pezeshkSabtNazar.getTozihat()%>"/>
                <c:if test="${nazar==null }">
                    -
                </c:if>
                ${nazar}
            </td>
            <td>
                <c:if test="${pishnehad.state.id==210 || pishnehad.state.id==110 || pishnehad.state.id==100 || pishnehad.state.id==175 || pishnehad.state.id==180 || pishnehad.state.id==190 || pishnehad.state.id==250 || pishnehad.state.id==165 || pishnehad.state.id==150 || pishnehad.state.id==240 || pishnehad.state.id>240 || readOnlyMode}">
                        -
                </c:if>
                <c:if test="${(pishnehad.state.id!=210 && pishnehad.state.id!=110 && pishnehad.state.id!=100 && pishnehad.state.id!=175 && pishnehad.state.id!=180 && pishnehad.state.id!=190 && pishnehad.state.id!=250 && pishnehad.state.id!=165 && pishnehad.state.id!=150&& pishnehad.state.id!=200 && pishnehad.state.id!=240 && pishnehad.state.id<240) && !readOnlyMode}">
                    <a href="javascript:void(0);" onclick="window.location='/pezeshkHazfeNazar.action?pezeshkSabtNazar.id=<%=pezeshkSabtNazar.getId()%>&pishnehad.id=<%=pishnehadRun.getId()%>'">حذف</a>
                </c:if>
            </td>
        </tr>


        <% }  }%>
        <tr>
            <td colspan="3">
                مجموع اضافه نرخ:&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <%=pezmajmou%>
            </td>
        </tr>
        <%} else { %>
        <tr>
            <td colspan="4">
                تا کنون نظری توسط پزشک برای این پیشنهاد ثبت نشده است.
            </td>
        </tr>
        <%}
        %>
        </tbody>
    </table>
<br/>
<br/>
<c:if test="${pishnehad.state.id==210 || pishnehad.state.id==110 || pishnehad.state.id==100 || pishnehad.state.id==175 || pishnehad.state.id==180 || pishnehad.state.id==190 || pishnehad.state.id==250 || pishnehad.state.id==165 || pishnehad.state.id==150 || pishnehad.state.id==240 || pishnehad.state.id>240 || readOnlyMode}">
    اضافه نرخ پزشکی (رئیس اداره):
    <b>${pishnehad.estelam.darsad_ezafe_nerkh_pezeshki}</b>
</c:if>
<br/>
<br/>
</div>

<c:if test="${pishnehad.state.id!=210 && pishnehad.state.id!=110 && pishnehad.state.id!=100 && pishnehad.state.id!=160 && pishnehad.state.id!=175 && pishnehad.state.id!=180 && pishnehad.state.id!=190 && pishnehad.state.id!=250 && pishnehad.state.id!=165 && pishnehad.state.id!=150 && pishnehad.state.id!=240 && pishnehad.state.id<240 && !readOnlyMode}">
<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        نظر رئیس اداره
    </p>
    <table class="resultDets" align="center" width="80%" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
        <tr>
            <td>توضیحات</td>
            <td colspan="3">
                <textarea rows="5" cols="65" id="private_message_raees" style="text-align: right;"><%=pishnehadRun.getPrivateMessages() + "\r\n" + "رئیس اداره: "%></textarea>
            </td>
        </tr>
        <tr>
            <%--show here--%>
            <td>عدم تایید پزشکی</td>
            <td>
                <input type="checkbox" name="taeedornot" id="raeistaeedornot" onchange="checkEverything();" <c:if test="${pishnehad.adamTaeedPezeshki == 'yes'}">checked</c:if> />
            </td>
        </tr>
        <tr>
            <td>
                اضافه نرخ:
            </td>
            <td>
                <input type="text" value="<%=pezmajmou%>"  id="pezeshk_sabt_nazar_raees" class="validate[custom[onlyNumber]]" />
            </td>
        </tr>
        <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_haadese=='yes'}">
            <tr>
                <td>
                    سرمایه پایه فوت در اثر حادثه (ریال):
                </td>
                <td>
                    <input type="text"  id="sarmaye_fot_hadese_raees" class="digitSeparators" onchange="checkHadese();" />
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                &nbsp;
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <p style="color: red;">
                    توجه داشته باشید که بعد از کلیک دکمه «تایید» یا «عدم تایید» امکان تغییر اطلاعات را نخواهید داشت!
                </p>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <input type ="button" style="display: none;" id="raeistaeedmikonam" onclick="taeedKon()" value="تایید"/>
                <input type ="button" style="display: none;" id="raeistaeednemikonam" onclick="taeedNakon()" value="عدم تایید"/>
            </td>
        </tr>
    </table>
</div>
<form action="/taeedeNazarePezeshk.action" id="raeisekhoshnazar" method="post" accept-charset="UTF-8">
    <input type="hidden" name="transitionId" id="raeistransitionid"/>
    <input type="hidden" name="logmessage" id="raeislogmessage"/>
    <input type="hidden" name="pishnehad.id" value="<%=pishnehadRun.getId()%>"/>
</form>
</c:if>
<c:if test="${pishnehad.state.id==210 || pishnehad.state.id==110 || pishnehad.state.id==100 || pishnehad.state.id==160 || pishnehad.state.id==175 || pishnehad.state.id==180 || pishnehad.state.id==190 || pishnehad.state.id==250 || pishnehad.state.id==165 || pishnehad.state.id==150 || pishnehad.state.id==240 || pishnehad.state.id>240 || readOnlyMode}">
    <table align="center" width="70%" class="jtable resultDets" cellpadding="0" cellspacing="0" style="margin:0 auto;">
            <thead>
            <tr>
                <th style="padding:5" class="ui-state-default">تاریخ</th>
                <th style="padding:5" class="ui-state-default">ساعت</th>
                <th style="padding:5" class="ui-state-default">کاربر</th>
                <th style="padding:5" class="ui-state-default">پیغام</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${transitionLogs}" varStatus="i">
                <c:if test="${row.transition.id == 24 || row.transition.id == 27 || row.transition.id == 28 || row.transition.id == 25}">
                <tr>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.date}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.time}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.user.firstName}&nbsp;${row.user.lastName}</td>
                    <td style="padding:5px 5px" class="ui-widget-content">${row.message}</td>
                </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    <br/>
    <br/>
    <div style="float: right; text-align: right">
توضیحات:
        <br/>
    <b>${pishnehad.privateMessagesHTML}</b>
    </div>
    </c:if>
<script type="text/javascript">
    function checkEverything(){
        var raeisChecked = $("#raeistaeedornot").attr("checked");
        if(raeisChecked){
            $("#raeistaeednemikonam").show();
            $("#raeistaeedmikonam").hide();
        }else{
            $("#raeistaeednemikonam").hide();
            $("#raeistaeedmikonam").show();
        }
    }
    function taeedKon(){
        $.post("/saveEzafeNerkheRaees.action?pishnehad.id=${pishnehad.id}&darsad_ezafe="+$("#pezeshk_sabt_nazar_raees").val()
               +"&pishnehad.estelam.sarmaye_paye_fot_dar_asar_hadese=" <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_haadese=='yes'}">+$("#sarmaye_fot_hadese_raees").val()</c:if>
                 ,function() {
            $("#transitionSelector").val('27');
//            $('#loggmessage').val($('#nazarraeislog').val());
            $('#privateMessage').val($('#private_message_raees').val());
            submitTransitionForm();
        });
    }
    function taeedNakon(){
        $("#transitionSelector").val('28');
//        $('#loggmessage').val($('#nazarraeislog').val());
        $('#privateMessage').val($('#private_message_raees').val());
        submitTransitionForm();
    }
    <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_haadese=='yes'}">
        function checkHadese()
        {
            if(parseInt($('#sarmaye_fot_hadese_raees').val().replace(new RegExp(",", "gm"), ""))<10000000)
            {
                $('#sarmaye_fot_hadese_raees').val('');
                alertMessage("حداقل مبلغ سرمایه فوت در اثر حادثه 10,000,000ریال می باشد.");
            }
            if(parseInt($('#sarmaye_fot_hadese_raees').val().replace(new RegExp(",", "gm"), "")) > 3 * ${pishnehad.estelam.sarmaye_paye_fot_long})
            {
                $('#sarmaye_fot_hadese_raees').val('');
                alertMessage("مبلغ سرمایه فوت در اثر حادثه نباید بیشتر از 3 برابر سرمایه پایه فوت باشد.");
            }
        }
    </c:if>


</script>