<%@ page import="com.bitarts.parsian.action.CredebitAction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<br class="clear"/>
<input type="hidden" id="NafareEmza"/>
<form id="bardashtAzAndukhteRealForm" name="form4dish" action="/sabteBardashtAzAndukhte" method="post"
      accept-charset="UTF-8">
    <input type="hidden" name="darkhastBazkharid.id" value="${darkhastBazkharid.id}"/>

    <div class=expandableTitleBar>
        <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
            برداشت از اندوخته
            <c:if test="${darkhastBazkharid.state.id!=11090 && darkhastBazkharid.state.id!=11100 && darkhastBazkharid.state.id!=11110}">
                <a  onclick="refresh();" class="tipsi ui-state-default" style="float:left;margin-left:2px"
                   title="بروزرسان?">
                    <span class="ui-icon ui-icon-refresh">&nbsp;</span>
                </a>
            </c:if>
            </p>
    </div>
    <table class="mystrippedtable" id="table4bardashtazandukhte" dir="rtl" cellpadding="2px" cellspacing="0px"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tr class="even">
            <td>
                شماره برداشت از اندوخته:
            </td>
            <td >
                <input type="text" name="darkhastBazkharid.shomareBardashtAzAndukhte" value="${darkhastBazkharid.shomareBardashtAzAndukhte}" id="shomareBardasht" readonly="readonly"/>
            </td>
            <td>
 اندوخته ب?مه نامه:
            </td>
            <td>
                <input type="text" name="darkhastBazkharid.andukhteGhatie"
                       <c:if test="${darkhastBazkharid.credebitBardasht!=null}">value="${darkhastBazkharid.andukhteGhatie}"</c:if>
                       <c:if test="${darkhastBazkharid.credebitBardasht==null}">value="${andukhte}" </c:if>id="andukhte" readonly="readonly"/>
            </td>
        </tr>
        <tr class="odd">
            <td>
                تاريخ ثبت درخواست برداشت:
            </td>
            <td>
                <input type="text" value="${darkhastBazkharid.tarikhDarkhast}" id="tarikhSabt" readonly="readonly"/>
            </td>
            <td>
                تاريخ اعلام به مالي:
            </td>
            <td>
                <input type="text" value="${darkhastBazkharid.credebitBardasht.dateFish}" id="tarikhElamMali" readonly="readonly"/>
            </td>
        </tr>
        <tr class="even">
            <td>
                حداكثر مبلغ قابل برداشت:
            </td>
            <td>
                <input type="text" name="darkhastBazkharid.maxAmountBardasht"
                       <c:if test="${darkhastBazkharid.shomareBardashtAzAndukhte != null && darkhastBazkharid.shomareBardashtAzAndukhte != ''}">
                           value="${darkhastBazkharid.maxAmountBardasht}"
                       </c:if>

                       <c:if test="${darkhastBazkharid.shomareBardashtAzAndukhte == null || darkhastBazkharid.shomareBardashtAzAndukhte == ''}">
                           value="${hadeAksarBardasht}"
                       </c:if>
                   id="hadAksarBardasht" readonly="readonly"/>
            </td>
            <td>
                مبلغ درخواستي برداشت:
            </td>
            <td>
                <c:if test="${darkhastBazkharid.mablaghDarkhastiBardasht!=null && darkhastBazkharid.mablaghDarkhastiBardasht!=''}">
                    <input type="text" name="darkhastBazkharid.mablaghDarkhastiBardasht" id="amount_darkhasti"  class="digitSeparators" onchange="checkDarkhasti();"
                           value="${darkhastBazkharid.mablaghDarkhastiBardasht}"/>
                </c:if>
                <c:if test="${darkhastBazkharid.mablaghDarkhastiBardasht==null || darkhastBazkharid.mablaghDarkhastiBardasht==''}">
                    <input type="text" name="darkhastBazkharid.mablaghDarkhastiBardasht" id="amount_darkhasti"
                           class="digitSeparators" onchange="checkDarkhasti();"
                           value="${hadeAksarBardasht}"/>
                </c:if>
            </td>
        </tr>
        <tr class="odd">
            <td>
                جمع اقساط معوق بيمه نامه:
            </td>
            <td>
                <input type="text" value="${jameAghsatMoavaghBimename}" id="aghsatMoavagh" readonly="readonly"/>
            </td>
            <td>
                جمع كل بدهي وام:
            </td>
            <td>
                <input type="text" value="${jameKolBedehi}" id="kodBedehi" readonly="readonly"/>
            </td>
        </tr>
        <tr class="even">
            <td>
                امضا كننده اول:
            </td>
            <td>
                <input type="text" id="bardasht_emzaKonandeAval" style="width:100px" class="validate[required]" readonly="readonly"
                        <c:if test="${darkhastBazkharid.emzaKonandeAval!=null}">value="${darkhastBazkharid.emzaKonandeAval.user.fullName}"</c:if>
                        <c:if test="${darkhastBazkharid.emzaKonandeAval==null}">value="${user.fullName}"</c:if>
                        />
                <input type="hidden" name="darkhastBazkharid.emzaKonandeAval.id" id="bardasht_emzaKonandeAval_id"
                       <c:if test="${darkhastBazkharid.emzaKonandeAval!=null}">value="${darkhastBazkharid.emzaKonandeAval.id}"</c:if>
                       <c:if test="${darkhastBazkharid.emzaKonandeAval==null}">value="${user.emzaKonande.id}"</c:if>
                        />
                <input type="button" value="جستجو"
                       onclick="$('#NafareEmza').val('#bardasht_emzaKonandeAval');fillEmze();"/>
            </td>
            <td>
                امضا كننده دوم:
            </td>
            <td>
                <input type="text" id="bardasht_emzaKonandeDovom" style="width:100px" class="validate[required]"readonly="readonly"
                       <c:if test="${darkhastBazkharid.emzaKonandeDovom!=null}">value="${darkhastBazkharid.emzaKonandeDovom.user.fullName}"</c:if>
                       <c:if test="${darkhastBazkharid.emzaKonandeDovom==null}">value="مهد? رخشان"</c:if>
                        />
                <input type="hidden" name="darkhastBazkharid.emzaKonandeDovom.id" id="bardasht_emzaKonandeDovom_id"
                       <c:if test="${darkhastBazkharid.emzaKonandeDovom!=null}">value="${darkhastBazkharid.emzaKonandeDovom.id}"</c:if>
                       <c:if test="${darkhastBazkharid.emzaKonandeDovom==null}">value="3356025"</c:if>
                        />
                <input type="button" value="جستجو"
                       onclick="$('#NafareEmza').val('#bardasht_emzaKonandeDovom');fillEmze(); "/>
            </td>
        </tr>
    </table>   </br>
    <%  Bimename theBimename= ((DarkhastBazkharid)request.getAttribute("darkhastBazkharid")).getBimename();
        CredebitAction.mohasebeAndukhteBimename(theBimename, DateUtil.getCurrentDate(), null);
    %>
    <c:if test="${!darkhastBazkharid.bimename.thereYearTaghsitNashode}">
        <c:if test="${darkhastBazkharid.bimename.arzeshBazkharidGhatie > 0 && !timeToElamMali && (darkhastBazkharid.state.id==11050 || darkhastBazkharid.state.id==11070 || darkhastBazkharid.state.id==11020 || darkhastBazkharid.state.id==11030)}">
            <input type="button" onclick="sabt();" value="ثبت برداشت از اندوخته"/>
        </c:if>
        <c:if test="${(darkhastBazkharid.state.id==11090 || darkhastBazkharid.state.id==11100 || darkhastBazkharid.state.id!=11110 ) || (darkhastBazkharid.state.id!=11090 && darkhastBazkharid.state.id!=11100 && darkhastBazkharid.state.id!=11110 && refreshModeBardasht)}">
        <input type="button" class="ui-state-default  ui-corner-all noAnyDisable"
               onclick="window.open('/printeSharayeteBardashtAzAndokhte?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}&pishnehadReport.bimename.id=${darkhastBazkharid.bimename.id}');"
               value="چاپ شرايط برداشت از اندوخته">
        </c:if>
    </c:if>



</form>
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

    function refresh()
    {
        $('#bardashtAzAndukhteRealForm').append('<input type="hidden" name="refreshModeBardasht" value="true"/>');
        $('#bardashtAzAndukhteRealForm').submit();
    }
    function sabt()
    {
        $('#bardashtAzAndukhteRealForm').append('<input type="hidden" name="refreshModeBardasht" value="false"/>');
        $('#bardashtAzAndukhteRealForm').submit();
    }

    function checkDarkhasti()
    {
        if($('#amount_darkhasti').val()!=null && parseInt($('#amount_darkhasti').val().replace(new RegExp(",", "gm"), "")) <= parseInt($('#hadAksarBardasht').val().replace(new RegExp(",", "gm"), "")))
        {

        }
        else
        {
            $('#amount_darkhasti').val('${hadeAksarBardasht}')
        }

    }

    function fillEmze()
    {
        $('#tblEmza').dialog({
            modal: true,
            width: 500,
            resizable: false,
            closeText: "",
            title: "امضاکننده"
        });

    }
    function selectRow(id, fn, ln)
    {
        var ctrlId = $('#NafareEmza').val();
        $(ctrlId).val(fn + " " + ln);
        $(ctrlId + '_id').val(id);

        hideEmzaModal();
    }

    function hideEmzaModal()
    {
        $('#emzaName').val('');
        $('#emzaPersonalCode').val('');
        $('#tblEmza').dialog('close');
        $('#searchResualt').html('');
    }

    function searchEmaz()
    {
        var emzaName = $('#emzaName').val();
        var emzaPersonalCode = $('#emzaPersonalCode').val();


        $.post("/findEmzaKonande.action?" + "elhaghieReq=" + true + "&user.firstName=" + emzaName + "&user.personalCode=" + emzaPersonalCode + "&pishnehad.id=" + "${pishnehad.id}", function (msg)
        {
            $('#searchResualt').html(msg);
        });

        fillEmze();
    }

    $(document).ready
    (
        function ()
        {
            <c:if test="${timeToElamMali && (darkhastBazkharid.state.id==11050 || darkhastBazkharid.state.id==11070 || darkhastBazkharid.state.id==11020 || darkhastBazkharid.state.id==11030)}">
//                $("#dakhastTransitionSelector option[value='11035']").remove();
//                $("#dakhastTransitionSelector option[value='11034']").remove();
//                $("#dakhastTransitionSelector option[value='11032']").remove();
//                $("#dakhastTransitionSelector option[value='11033']").remove();

                $('#send_request_btn').remove('onclick');
                $('#send_request_btn').hide();
                $('#dakhastTransitionSelector').hide();
            </c:if>
            <c:if test="${darkhastBazkharid.bimename.thereYearTaghsitNashode}">
                alertMessage("قبل از ادامه فرآیند بیمه نامه را تقسیط نمایید.");
            </c:if>

        }
    );
</script>