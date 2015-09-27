<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<br class="clear"/>

<form id="bardashtAzAndukhteForm" name="form4dish" action="/sabteBardashtAzAndukhte" method="post"
      accept-charset="UTF-8">
    <input type="hidden" name="darkhastBazkharid.id" value="${darkhastBazkharid.id}"/>

    <div class=expandableTitleBar>
        <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
            اعلام به مالی
        </p>
    </div>
    <table class="mystrippedtable" id="s"  dir="rtl"  cellpadding="2px" cellspacing="0px"
           style="margin-right:auto; margin-left: 8%;"  border="0">
        <tr class="even">
        <td style="padding-left:10px;"> <p style="text-align: justify">
    <%--<table id="table4bardashtazandukhte" dir="rtl" cellpadding="2px" cellspacing="0px"--%>
           <%--style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">--%>
        <%--<tr align="right">--%>
            <%--<td>--%>
                جناب آقاي/سركار خانم
            <%--</td>--%>
            <%--<td>--%>
                <b>یاسور عینی</b>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr align="right">--%>
            <%--<td>--%>
        <br/>       </br>
                رئيس محترم اداره حسابداري عملياتي
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr align="right">--%>
            <%--<td colspan="2">--%>
        </br></br>
        &nbsp;&nbsp;&nbsp;&nbsp;احتراماً به پيوست يك برگ اعلاميه بستانكار برداشت از اندوخته مربوط به بيمه‌نامه
                شماره&nbsp;&nbsp;<b>${darkhastBazkharid.bimename.shomare}</b>&nbsp;&nbsp;تقدیم میگردد.
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr align="right">--%>
            <%--<td colspan="2">--%>
        <br/>
        &nbsp;&nbsp;&nbsp;&nbsp;با توجه به تقاضاي كتبي بيمه
                گذار&nbsp;&nbsp;<b>${darkhastBazkharid.bimename.pishnehad.bimeGozar.shakhs.name}&nbsp;&nbsp;${darkhastBazkharid.bimename.pishnehad.bimeGozar.shakhs.nameKhanevadegi}</b>&nbsp;&nbsp;
                در تاريخ&nbsp;&nbsp;<b>${darkhastBazkharid.tarikhDarkhast}</b> &nbsp;&nbsp;خواهشمند است دستور فرمائيد
                مبلغ&nbsp;&nbsp;<b>${darkhastBazkharid.mablaghDarkhastiBardasht}</b>&nbsp;&nbsp; ریال<br/> &nbsp;&nbsp;&nbsp;&nbsp; از محل اندوخته
                بيمه‌نامه در وجه نامبرده پرداخت فرمايند.
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr align="right">--%>
            <%--<td colspan="2">--%>
        <br/><br/> <br/>
        &nbsp;&nbsp;&nbsp;&nbsp;                شماره برداشت از اندوخته:&nbsp;&nbsp;<b>${darkhastBazkharid.shomareBardashtAzAndukhte}</b>&nbsp;&nbsp;
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr align="right">--%>
            <%--<td colspan="2">--%>
        <br/>     <br/>
        &nbsp;&nbsp;&nbsp;&nbsp;شماره حساب:&nbsp;&nbsp;بانک&nbsp;<b>${darkhastBazkharid.bankName}&nbsp;&nbsp;</b>
                شعبه&nbsp;&nbsp;<b>${darkhastBazkharid.shobeName}</b>&nbsp;&nbsp; : &nbsp;&nbsp;
                <b>${darkhastBazkharid.shomareHesab}</b>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr align="right">--%>
            <%--<td colspan="2">--%>
        <br/>      <br/>
        &nbsp;&nbsp;&nbsp;&nbsp;                شبا: &nbsp;&nbsp;<b>${darkhastBazkharid.shomareShaba}</b>
            <%--</td>--%>
        <%--</tr>--%>

    <%--</table>--%>
            </p>
      </td>
        </tr>
    </table>
        </br>
        <input type="button"
               onclick="enableButton(1);window.open('/printeElamieBestankarBardashtAzAndokhte?darkhastBazkharid.id=${darkhastBazkharid.id}');"
               value="چاپ اعلامیه بستانکار برداشت از اندوخته"/>

        <input type="button"
               onclick="enableButton(2);window.open('/printeElamBeMali?darkhastBazkharid.id=${darkhastBazkharid.id}');"
               value="چاپ نامه مالی"/>
        <c:if test="${darkhastBazkharid.state.id<11090}">
        <input type="button" id="k_maliButton" disabled="disabled" onclick="submitTheElamMaliTransition();"
               value="اعلام به مالی"/>
            </c:if>
</form>
<script type="text/javascript">
    var enable1 = false;
    var enable2 = false;
    function enableButton(a) {
        if (a == 1)
            enable1 = true;
        else if (a == 2)
            enable2 = true;
        if (enable1 && enable2)
            $("#k_maliButton").removeAttr("disabled");

    }
    <c:if test="${timeToElamMali == 'true'}">
    $("#tab_8").hide();
    $("#content_8").hide();
    </c:if>
    function submitTheElamMaliTransition() {
        <c:if test="${darkhastBazkharid.bimename.thereYearTaghsitNashode}">
            alertMessage("<p style='color: #8b0000'> "+"قبل از اعلام به مالی برداشت می بایست تمام سال های بیمه نامه را تا امروز تقسیط نمایید."+"</p>")
        </c:if>
        <c:if test="${!darkhastBazkharid.bimename.thereYearTaghsitNashode}">
            <c:if test="${darkhastBazkharid.state.id==11030}">
//                $('#dakhastTransitionSelector').val(11008);
                $('#dakhastTransitionSelector').append($("<option selected='selected'></option>").attr("value", '11008').text("اعلام به مالی"));
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==11020}">
//                $('#dakhastTransitionSelector').val(11032);
                $('#dakhastTransitionSelector').append($("<option selected='selected'></option>").attr("value", '11032').text("اعلام به مالی"));
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==11050}">
//                $('#dakhastTransitionSelector').val(11014);
                $('#dakhastTransitionSelector').append($("<option selected='selected'></option>").attr("value", '11014').text("اعلام به مالی"));
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==11070}">
//                $('#dakhastTransitionSelector').val(11018);
                $('#dakhastTransitionSelector').append($("<option selected='selected'></option>").attr("value", '11018').text("اعلام به مالی"));
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==11080}">
//                $('#dakhastTransitionSelector').val(11009);
                $('#dakhastTransitionSelector').append($("<option selected='selected'></option>").attr("value", '11009').text("اعلام به مالی"));
            </c:if>
            submitTransitionForDarkhast();
        </c:if>
    }

    <c:if test="${timeToElamMali && darkhastBazkharid.state.id<11090}">
    $(document).ready
    (
            function ()
            {
                alertMessage('پیش از اعلام به مالی،چاپ های اعلامیه بستانکار و نامه مالی را دریافت کنید.');
            }
    );
    </c:if>
</script>