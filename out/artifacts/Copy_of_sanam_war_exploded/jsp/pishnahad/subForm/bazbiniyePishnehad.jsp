<%@ page import="com.bitarts.parsian.model.pishnahad.Fish" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    long bazvalue = 0;
    long baztafazol = 0;
    long bazhagheBime = 0;
%>
<table id="bazbiniyepishnehadtable" dir="rtl" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <tr>
        <td>
            مبلغ پیش پرداخت:&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <%
            if(pishnehadRun!=null){

                List<Fish> fishs = pishnehadRun.getFishs();
                for (Fish fish : fishs) {
                    if(!fish.getFound().equalsIgnoreCase("false")){
                        String mablaghBaz = fish.getMablagh();
                        mablaghBaz = mablaghBaz.replaceAll(",","");
                        bazvalue = bazvalue+Long.parseLong(mablaghBaz);
                    }
                }
            }
        %>
        <td>
            <%=NumberFormat.getNumberInstance().format(bazvalue)%>&nbsp; ریال
        </td>
    </tr>
    <tr>
        <td>
            مبلغ حق بیمه:&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <%

                                bazhagheBime = Integer.parseInt(pishnehadRun.getEstelam().getHagh_bime_pardakhti().replaceAll(",",""))+Integer.parseInt(pishnehadRun.getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",",""));

        %>
        <td>
            <%=NumberFormat.getNumberInstance().format(bazhagheBime)%>&nbsp; ریال
        </td>
    </tr>
    <tr>
        <td>
            مبلغ باقیمانده:&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <%
            baztafazol = bazhagheBime - bazvalue;
        %>
        <td>
            <%=NumberFormat.getNumberInstance().format(baztafazol)%>&nbsp; ریال
        </td>
    </tr>
    <tr>
        <td>
            <input type="button" value="منتظر دریافت مابقی قسط" onclick="montazereMabaghiyeGhest();">
        </td>
        <td>
            <input type="button" value="بازبینی پیشنهاد و تبدیل به بیمه نامه" onclick="bazbiniVaTabdilBeBimename();">
        </td>
    </tr>
</table>
<script type="text/javascript">
    function montazereMabaghiyeGhest(){

    <c:if test="${pishnehad.state.id!=210}">
               $("#transitionSelector").val(34);
        submitTransitionForm();
    </c:if>
                <c:if test="${pishnehad.state.id==210}">
                           $("#transitionSelector").val(30);
        submitTransitionForm();
    </c:if>

    }
    function bazbiniVaTabdilBeBimename(){
        <c:if test="${pishnehad.state.id==240}">
            $("#transitionSelector").val(36);
        </c:if>
        <c:if test="${pishnehad.state.id==210}">
            $("#transitionSelector").val(64);
        </c:if>
        submitTransitionForm();

    }
</script>