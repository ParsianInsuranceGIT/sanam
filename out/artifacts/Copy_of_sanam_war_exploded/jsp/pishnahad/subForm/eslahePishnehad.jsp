<%@ page import="com.bitarts.parsian.model.Role" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.bitarts.parsian.model.pishnahad.Fish" %>
<%@ page import="com.bitarts.parsian.Core.Constant" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    long value = 0;
    long tafazol = 0;
    String hagheBime = "";
    long haghBimeValue = 0;
%>

<c:if test="${pishnehad.state.id != 210}">
<table id="niazbeeslah" dir="rtl" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <tbody>
        <tr>
            <td>
                آیا پیشنهاد نیازمند اصلاح است؟&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="button" value="بله" onclick="showForm();"/>
            </td>
            <td>
                <input type="button" value="خیر"  onclick="hideForm();"/>
            </td>
        </tr>
    </tbody>
</table>
<form id="naghsdarad" name="form1" method="post" action="/sendNaghs.action">
    <table id="naghsdaradtable" dir="rtl" cellpadding="0" cellspacing="2" style="border-spacing:1px;margin-left:auto;margin-right:auto;display: none;" border="0">
        <input type="hidden" name="pishnehad.id" value="<%=pishnehadRun.getId()%>"/>
        <input type="hidden" id="lgmsg" name="logmessage" value=""/>
        <input type="hidden" id="transidnaghs" name="transitionId" value=""/>
        <tr>
            <td>
                <label>نقص مدارک</label>
            </td>
            <td>
                <input id="naqs_madrak" type="checkbox" name="naghsPishnehad.naghsMadaarek" ${pishnehad.state.id == 100?'disabled=disabled':''}/>
            </td>
        </tr>
        <tr>
            <td>
                <label>نقص اطلاعات</label>
            </td>
            <td>
                <input id="naqs_etelaat" type="checkbox" name="naghsPishnehad.naghsEtelaat" ${pishnehad.state.id == 100?'disabled=disabled':''}/>
            </td>
        </tr>

        <c:if test="${pishnehad.karshenas == null}">
        <tr id="naghsfishrow">
            <td>
                <label>نقص فیش</label>
            </td>
            <td>
                <input id="naqs_fish" type="checkbox" name="naghsPishnehad.naghsFish"/>
            </td>
        </tr>
        </c:if>
        <tr>
            <td>
                توضیحات:
            </td>
            <td>
                <input type="text" name="naghsPishnehad.tozihat" value="-" id="naqs_tozihat" style="width: 300px;"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" value="تایید" onclick="checkBeforeSubmitNaghs();"/>
            </td>
        </tr>
    </table>
</form>
</c:if>
<c:if test="${pishnehad.state.id == 210}">
<table id="niazbetaghirsharayet" dir="rtl" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <tr>
        <td>
            آیا پیشنهاد نیاز به تغییر شرایط دارد؟&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <td>
            <input type="button" value="بلی" onclick="niazBeTaghirSharayet();"/>
        </td>
        <td>
            <input type="button" value="خیر" onclick="adameNiazBeTaghireSharayet();"/>
        </td>
    </tr>
</table>
</c:if>
    <table id="niazbeazmayesh" dir="rtl" cellpadding="0" cellspacing="0" style="display: none; border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tr>
            <td>
                آیا بیمه شده نیازمند آزمایش است؟&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="button" value="بلی" onclick="niazBeAzmayesh();"/>
            </td>
            <td>
                <input type="button" value="خیر" onclick="adameNiazBeAzmayesh();"/>
            </td>
            <td>
                <%--<input type="button" value= "بررسی مجدد اصلاح پیشنهاد"onclick="bazgashtBeEslahePishnahad();"/>--%>
            </td>
        </tr>
    </table>


<c:if test="${pishnehad.state.id != 210}">
<table id="nizbebarresipezeshki" dir="rtl" cellpadding="0" cellspacing="0" style="display: none; border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <tr>
        <td>
            آيا اين پيشنهاد نيازمند بررسي پزشك است؟&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <td>
            <input type="button" value="بلی" onclick="niazBeBarresiPezeshki();"/>
        </td>
        <td>
            <input type="button" value="خیر" onclick="adameNiazBeBarresiPezeshki();"/>
        </td>
        <td>
            <%--<input type="button" value= "بررسی مجدد انجام آزمایش" onclick="bazgashtBeNiazBeAzmayesh();"/>--%>
        </td>

    </tr>
</table>

    <table id="mohasebatemalitable" dir="rtl" cellpadding="0" cellspacing="0" style="display: none; border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <tr>
        <td>
            مبلغ پیش پرداخت:&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <%
            if(pishnehadRun!=null){

                List<Fish> fishs = pishnehadRun.getFishs();
                if(fishs.size()>0){
                    for (Fish fish : fishs) {
                        if(!fish.getFound().equalsIgnoreCase("false")){
                            String mablagh = fish.getMablagh();
                            mablagh = mablagh.replaceAll(",","");
                            value = value+Long.parseLong(mablagh);
                        }
                    }
                }
            }
        %>
        <td>
            <%=NumberFormat.getNumberInstance().format(value)%>&nbsp; ریال
        </td>
    </tr>
    <tr>
        <td>
            مبلغ حق بیمه:&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <%
            hagheBime = pishnehadRun.getEstelam().getHagh_bime_pardakhti();
            hagheBime = hagheBime.replaceAll(",","");
            haghBimeValue = Long.parseLong(hagheBime);
            haghBimeValue+=Long.parseLong(pishnehadRun.getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",",""));
        %>
        <td>
            <%=NumberFormat.getNumberInstance().format(haghBimeValue)%>&nbsp; ریال
        </td>
    </tr>
    <tr>
        <td>
            مبلغ باقیمانده:&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <%
            tafazol = haghBimeValue - value;
        %>
        <td>
            <%=NumberFormat.getNumberInstance().format(tafazol)%>&nbsp; ریال
        </td>
    </tr>
    <tr>
        <td>
            <input type="button" value="منتظر دریافت مابقی قسط" onclick="submitMontazereDaryaft();">
        </td>
        <td>
            <input type="button" value="بازبینی پیشنهاد و تبدیل به بیمه نامه" onclick="submitTabdilBeBimename();">
        </td>

    </tr>
</table>
<%--<form></form>--%>
<form id="naghsnadarad" name="form2" method="post" action="/makeInnerTransition.action">
    <input type="hidden" name="pishnehad.id" value="<%=pishnehadRun.getId()%>"/>
    <%--<input type="hidden" name="pishnehadId" value="<%=pishnehadRun.getId()%>"/>--%>
    <input type="hidden" name="logmessage" value="test"/>
    <input type="hidden" id="transitbinaghs" name="transitionId" value="9"/>
</form>
</c:if>

<script type="text/javascript">
    <c:if test="${pishnehad.state.id==100}">
        $("#mohasebatemalitable").show();
        $("#niazbeeslah").hide();
        $("#nizbebarresipezeshki").hide();
        $("#niazbeazmayesh").hide();
    </c:if>
    function adameNiazBeBarresiPezeshki(){
//        $("#nizbebarresipezeshki").hide();
        $("#mohasebatemalitable").show();
    }
    function niazBeBarresiPezeshki(){
        <c:if test="${pishnehad.state.id==60}">
            $("#transitionSelector").val('42');
        </c:if>
        <c:if test="${pishnehad.state.id==80}">
            $("#transitionSelector").val('48');
        </c:if>
        submitTransitionForm();
    }
    function niazBeTaghirSharayet(){
        $("#transitionSelector").val('29');
        submitTransitionForm();
    }
    function adameNiazBeTaghireSharayet(){
        $("#tab_20").show();
        $('.tabsbtn').removeClass('activesubmit');
        $('#tab_20').addClass('activesubmit');
        $('.content').hide();
        $('#content_20').show();
    }
    function showForm(){
        $("#naghsdaradtable").show();
    }
    function hideForm(){
//        $("#naghsdaradtable").hide();

        <%if(pishnehadRun!=null){
            boolean decider = false;
            User karshenas = pishnehadRun.getKarshenas();
            if (karshenas != null){
                    decider = true;
            }
            if (decider){%>
                $("#niazbeazmayesh").show();
            <%}else{
                boolean fishpaymentChecker = false;
                if(pishnehadRun.getFishs()!=null){
                    for(Fish theFish : pishnehadRun.getFishs()){
                        if (theFish.getPaymentType().equalsIgnoreCase(Constant.FISH)){
                                fishpaymentChecker=true;
                        }
                    }
                }
                if(fishpaymentChecker){
                %>
                $.post('/ajaxFishSearch.action?pishnehad.id=<%=pishnehadRun.getId()%>',function(msg) {
                    $("#tbodyforfoundfishes").html(msg);
                });
                $("#tab_14").show();
                $(".content").hide();
                $("#content_14").show();
                $('.tabsbtn').removeClass('activesubmit');
                $('#tab_14').addClass('activesubmit');
            <%}else{%>
                $("#tab_15").show();
                $(".content").hide();
                $("#content_15").show();
                $('.tabsbtn').removeClass('activesubmit');
                $('#tab_15').addClass('activesubmit');
//                $("#transitionSelector").val('9');
//                $("#naghsnadarad").submit();
            <%}
            }%>
        <%}%>

    }
    function checkBeforeSubmitNaghs(){
        if($("#naqs_madrak").attr("checked")==false&&$("#naqs_etelaat").attr("checked")==false&&$("#naqs_fish").attr("checked")==false){
            alertMessage("لطفا حداقل یکی از گزینه های موجود را انتخاب نمایید.")
        }else{
            if($("#naqs_tozihat").val()==""){
                alertMessage("فیلد توضیحات حتما بایستی توسط شما پر شود.");
            }else{
                submitNaghs();
            }
        }
    }
    function submitNaghs(){
//        document.getElementById("transid").value = $('#transitionSelector').val();
        <%if(pishnehadRun!=null)if(pishnehadRun.getState().getId()==40){%>
            $("#transitionSelector").val('4');
        <%} else if (pishnehadRun.getState().getId()==80){%>
            $("#transitionSelector").val('11');
        <%}else if (pishnehadRun.getState().getId()==100){%>
            $("#transitionSelector").val('69');
        <%}%>
        <c:if test="${pishnehad.state.id==60}">
            $("#transitionSelector").val('43');
        </c:if>

        $("#transidnaghs").val($("#transitionSelector").val());
        $('#lgmsg').val($('#naqs_tozihat').val());
        $("#naghsdarad").submit();
    }
    function adameNiazBeAzmayesh(){
//        $("#niazbeazmayesh").hide();
        $("#nizbebarresipezeshki").show();

//        $("#mohasebatemalitable").show();
    }
    function bazgashtBeEslahePishnahad(){
        $("#niazbeazmayesh").hide();
        $("#mohasebatemalitable").hide();
        $("#nizbebarresipezeshki").hide();
        $("#niazbeeslah").show();
    }
    function bazgashtBeNiazBeAzmayesh(){
        $("#nizbebarresipezeshki").hide();
        $("#mohasebatemalitable").hide();
        $("#niazbeazmayesh").show();
    }
    function niazBeAzmayesh(){


        $("#tab_16").show();
        $(".content").hide();
        $("#content_16").show();
        $('.tabsbtn').removeClass('activesubmit');
        $('#tab_16').addClass('activesubmit');
    }
    function submitMontazereDaryaft(){
        <c:if test="${pishnehad.state.id==80}">
        $("#transitionSelector").val('12');
        </c:if>
        <c:if test="${pishnehad.state.id!=80}">
        $("#transitionSelector").val('45');
        </c:if>
        submitTransitionForm();
    }
    function submitTabdilBeBimename(){
        <c:if test="${pishnehad.state.id==210}">
        $("#transitionSelector").val('64');
        </c:if>
        <c:if test="${pishnehad.state.id==60}">
        $("#transitionSelector").val('46');
        </c:if>
        <c:if test="${pishnehad.state.id==80}">
        $("#transitionSelector").val('51');
        </c:if>
        submitTransitionForm();
    }
</script>
