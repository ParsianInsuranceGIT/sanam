<%@ page import="com.bitarts.parsian.model.transitionwise.PezeshkSabtNazar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    List<PezeshkSabtNazar> nazaraat = (List<PezeshkSabtNazar>) pishnehadRun.getNazaraatePezeshk();
    int majmou = 0;
    for (PezeshkSabtNazar pezeshkSabtNazar : nazaraat) {
        if(pezeshkSabtNazar.isFromPezeshk()){
            majmou = majmou + Integer.valueOf(pezeshkSabtNazar.getEzafeNerkh());
        }
    }
%>

<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        نظرات ثبت شده تا کنون
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
        <% if(nazaraat != null && nazaraat.size()>0){
            for (PezeshkSabtNazar pezeshkSabtNazar : nazaraat) {
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
                <a href="javascript:void(0);" onclick="window.location='/pezeshkHazfeNazar.action?pezeshkSabtNazar.id=<%=pezeshkSabtNazar.getId()%>&pishnehad.id=<%=pishnehadRun.getId()%>'">حذف</a>
            </td>

        </tr>


        <% }  }
        %>
        <tr>
            <td colspan="3">
                مجموع اضافه نرخ:&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <%=majmou%>
            </td>
        </tr>
        <%} else { %>
        <tr>
            <td colspan="3">
                تا کنون نظری توسط پزشک برای این پیشنهاد ثبت نشده است.
            </td>
        </tr>
        <%}
        %>
        </tbody>
    </table>
<br/>
<br/>


    <form action="/sabteNazarePezeshk.action" id="pezeshkekhoshnazar" method="post" accept-charset="UTF-8">
        <% if(pishnehadRun!=null){%>
        <input type="hidden" name="pishnehad.id" value="<%=pishnehadRun.getId()%>" />
        <%
            }
        %>
        <table align="center" width="70%" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
            <tr>
                <td>
                    <label>نوع بیماری:</label>&nbsp;&nbsp;
                </td>
                <td>
                    <input type="text" name="pezeshkSabtNazar.nazar"/>
                </td>
                <td>
                    <label>اضافه نرخ:</label>&nbsp;&nbsp;
                </td>
                <td>
                    <input type="text" id="pezeshk_sabt_nazar" class="validate[custom[onlyNumber],required]" name="pezeshkSabtNazar.ezafeNerkh"/>
                </td>
                <td>
                    <label>توضیحات</label>&nbsp;&nbsp;
                </td>
                <td>
                    <input type="text" name="pezeshkSabtNazar.tozihat"/>
                </td>
                <td>
                    <input type="submit" value="ثبت"/>
                </td>
            </tr>
        </table>
        <br/><br/>
    </form>


<c:if test="${pishnehad.state.id != 246}">
    <table>
        <tr>
            <td>عدم تایید پزشکی</td>
            <td>
                <input type="checkbox" name="taeedornot" id="taeedornot"/>
            </td>
        </tr>
    </table>
</c:if>
    <br/>
    <br/>
    <table>
            <tr>
            <td>توضیحات</td>
            <td>
                <textarea rows="5" cols="65" id="private_message_pezeshk" style="text-align: right;"><%=pishnehadRun.getPrivateMessages() + "\r\n" + "پزشک: "%></textarea>
            </td>
        </tr>
    </table>
    <br/>
    <br/>
    <table align="center" width="70%" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">

        <tr>
            <c:if test="${pishnehad.state.id != 246}">
            <td>
                <input type="button" name="niazeazmayeshemojaddad" id="niazeazmayeshemojaddad" onclick="send4AzmayesheMojaddad();" value="نیاز به آزمایش مجدد"/>
            </td>
            <td>
                <input type="button" name="niazemadareketakmili" id="niazemadareketakmili" onclick="send4TakmileMadarek();" value="نیاز به مدارک تکمیلی"/>
            </td>
            <td>
                <input type="button" name="pezeshkenazzaar" id="pezeshkenazzaar" onclick="send4EtmameNazarDadan();" value="ثبت نظر پزشک"/>
            </td>
            </c:if>
            <c:if test="${pishnehad.state.id == 246}">
                <td>
                    <input type="button" name="pezeshkenazzaar" id="pezeshkenazzaar_nezarat" onclick="send4EtmameNazarDadan_Nezarat();"
                           value="ثبت نظر پزشک"/>
                </td>
            </c:if>
        </tr>
    </table>


</div>
<script type="text/javascript">
function send4TakmileMadarek(){
    $("#transitionSelector").val('20');
    <c:if test="${pishnehad.state.id == 180}">
    $("#transitionSelector").val('62');
    </c:if>
    $('#privateMessage').val($('#private_message_pezeshk').val());
    submitTransitionForm();
}
function send4AzmayesheMojaddad(){
    $("#transitionSelector").val('23');
    <c:if test="${pishnehad.state.id == 180}">
    $("#transitionSelector").val('63');
    </c:if>

    $('#privateMessage').val($('#private_message_pezeshk').val());
    submitTransitionForm();
}
function send4EtmameNazarDadan(){
    var checkValue = "no";
    if($("#taeedornot").is(':checked'))
    {
        checkValue = "yes";
    }
    $.post("/sabteNazarePezeshk_Taeedie.action?pishnehad.id=${pishnehad.id}&taeedCheckBox="+checkValue, function() {
        $("#transitionSelector").val('24');
        <c:if test="${pishnehad.state.id == 180}">
        $("#transitionSelector").val('25');
        </c:if>
        $('#privateMessage').val($('#private_message_pezeshk').val());
        submitTransitionForm();
     });
}

function send4EtmameNazarDadan_Nezarat() {
        $("#transitionSelector").val('102');
        $('#privateMessage').val($('#private_message_pezeshk').val());
        submitTransitionForm();
}
</script>

