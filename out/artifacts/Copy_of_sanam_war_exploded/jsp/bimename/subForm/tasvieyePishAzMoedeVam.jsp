<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://parsian.bitarts.com/functions" prefix="bitarts_function" %>

<script  type="text/javascript">
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

    $(document).ready(function ()
    {

        $('#emzaName').val('');
        $('#emzaPersonalCode').val('');

        $('#tblEmza').dialog({
            modal: true, resizable: false, autoOpen: false,
            width: 700, maxHeight: 500, minHeight: 100,
            title: "جستحو شخص امضا کننده"});
    });

    function fillEmze()
    {
        $('#tblEmza').dialog('open');
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
</script>

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

<br class="clear"/>
<form id="bardashtAzAndukhteForm" name="form4dish" action="/sabteBardashtAzAndukhte" method="post" accept-charset="UTF-8">
    <input type="hidden" name="darkhastBazkharid.id" value="${darkhastBazkharid.id}"/>
    <table class="mystrippedtable" id="table4bardashtazandukhte" dir="rtl" cellpadding="2" cellspacing="0px" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tr class="even">
            <td>
                شماره تماس بيمه گذار تلفن ثابت:
            </td>
            <td>
                ${bimename.pishnehad.addressBimeGozar.telephoneManzel}
            </td>
            <td>
                موبايل:
            </td>
            <td>
                ${bimename.pishnehad.addressBimeGozar.telephoneHamrah}
            </td>
        </tr>
        <tr class="odd">
            <td>
                شماره وام :
            </td>
            <td>
                ${darkhastBazkharid.shomareVam}
            </td>
            <td>
                تاريخ ثبت بدهي:
            </td>
            <td>
                ${darkhastBazkharid.tarikhDarkhast}
            </td>
        </tr>
        <tr class="even">
            <td>
                تاريخ ثبت درخواست تسويه:
            </td>
            <td>
                <%=DateUtil.getCurrentDate()%>
            </td>
            <td>
                مهلت پرداخت
            </td>
            <td>
                <%--<%=darkhastRun.getDarkhastType() == DarkhastBazkharid.DarkhastType.%>--%>
                <c:if test="${darkhastBazkharid.darkhastType=='TASVIE_PISH_AZ_MOEDE_VAM'}">
                    ${bitarts_function:addMonth(darkhastBazkharid.tarikhDarkhast, 1)}
                </c:if>
            </td>
        </tr>
        <tr class="odd">
            <td>
                جمع جرايم ديركرد:
            </td>
            <td id="takhir_asl">
                0
            </td>
            <td>
                جمع اقساط معوق وام:
            </td>
            <td id="tavigh_asl">
                0
            </td>
        </tr>
        <tr class="even">
            <td>
                جمع اصل اقساط پرداخت نشده آتي:
            </td>
            <td id="jame_asl">
                0
            </td>
            <td>
                جمع كل بدهي وام:
            </td>
            <td id="total_jame_asl">
                0
            </td>
        </tr>
        <tr class="odd">
            <td colspan="1">
                امضا کننده اول:
            </td>
            <td>
                <s:if test="showElhaghiye">
                    <input type="text" readonly="readonly" id="elhaghiye_emzaKonandeAval" style="width:100px"
                           value="${elhaghiye.emzaKonandeAval.user.firstName} ${elhaghiye.emzaKonandeAval.user.lastName}"/>
                    <input type="hidden" name="elhaghiye.emzaKonandeAval.id" id="elhaghiye_emzaKonandeAval_id"
                           value="${elhaghiye.emzaKonandeAval.id}"/>

                </s:if>
                <s:else>
                    <input type="text" id="elhaghiye_emzaKonandeAval" style="width:100px" readonly="readonly"
                           value="${user.firstName} ${user.lastName}"/>
                    <input type="hidden" name="elhaghiye.emzaKonandeAval.id" id="elhaghiye_emzaKonandeAval_id"
                           value="${user.emzaKonande.id}"/>
                    <input type="button" value="جستجو"
                           onclick="$('#NafareEmza').val('#elhaghiye_emzaKonandeAval');fillEmze();"/>
                </s:else>

            </td>
            <td colspan="1">
                امضا کننده دوم:
            </td>
            <td colspan="2">
                <s:if test="showElhaghiye">
                    <input type="text" readonly="readonly" id="elhaghiye_emzaKonandeDovom" style="width:100px"
                           class="validate[required]"
                           value="${elhaghiye.emzaKonandeDovom.user.firstName} ${elhaghiye.emzaKonandeDovom.user.lastName}"/>
                    <input type="hidden" name="elhaghiye.emzaKonandeDovom.id" id="elhaghiye_emzaKonandeDovom_id"
                           value="${elhaghiye.emzaKonandeDovom.id}"/>
                </s:if>
                <s:else>
                    <input type="text" readonly="readonly" id="elhaghiye_emzaKonandeDovom" style="width:100px" class="validate[required]"/>
                    <input type="hidden" name="elhaghiye.emzaKonandeDovom.id" id="elhaghiye_emzaKonandeDovom_id"/>
                    <input type="button" value="جستجو"
                           onclick="$('#NafareEmza').val('#elhaghiye_emzaKonandeDovom');fillEmze(); "/>
                </s:else>
            </td>
        </tr>
    </table>
    <c:if test="${darkhastBazkharid.state.id == 12100}">
        <div style="color:red;margin:5px;padding:5px">
            مهلت مقرر جهت تسويه پيش از موعد بدهي وام، حداكثر 30 روز از تاريخ ثبت مانده بدهي وام توسط اداره خدمات پس از صدور مي‌باشد.
        </div>
    </c:if>
    <div style="margin-top:5px">
        <input type="button" onclick="window.open('/printeSharayeteTasvieyeVam?pishnehadReport.bimename.id=${bimename.id}&pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}');" style="float:right;" value="چاپ مانده بدهی وام"/>
        <input type="hidden" id="NafareEmza"/>
        <c:if test="${darkhastBazkharid.state.id == 12090}">
            <input type="button" onclick="$('#dakhastTransitionSelector').val('12020');submitTransitionForDarkhast();" style="float:right;margin-right:2px;" value="اعلام به مالی"/>
        </c:if>
        <c:if test="${darkhastBazkharid.state.id == 12160}">
            <input type="hidden" id="andukhte" name="andukhte" value="${andukhte}"/>
            <input type="button" onclick="tasvieAzAndukhte()" style="float:right;margin-right:2px;" value="تسويه از محل اندوخته"/>
        </c:if>
        <c:if test="${darkhastBazkharid.state.id == 12100}">
            <input type="button" onclick="$('#dakhastTransitionSelector').val('12020');submitTransitionForDarkhast();" style="float:right;margin-right:2px;" value="انجام پرداخت"/>
        </c:if>
    </div>
</form>

<script type="text/javascript">
    function tasvieAzAndukhte()
    {
        var bedehi=parseInt($('#total_jame_asl').html().replace(new RegExp(",", "gm"), ""))
        var andukhte=parseInt($('#andukhte').html().replace(new RegExp(",", "gm"), ""))
        if(andukhte>=bedehi)
            $('#dakhastTransitionSelector').val('12029');
        else
            $('#dakhastTransitionSelector').val('12028');
        submitTransitionForDarkhast();
    }
</script>