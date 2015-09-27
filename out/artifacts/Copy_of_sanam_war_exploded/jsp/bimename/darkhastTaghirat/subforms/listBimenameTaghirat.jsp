<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="new_info_elhaghie" style="display:none;">
    <table class="mystrippedtable" id="table4sodourelhaghie" dir="rtl" cellpadding="2px" cellspacing="0px"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">

        <tr class="odd">
            <td >
                تاریخ اثر جدید الحاقیه:
            </td>

            <td>
                <input type="input" id="new_date_txt" class="datePkr validate[custom[date]]" readonly="readonly" style="width: 160px;"/>
            </td>
            <td>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
        </tr>
        <tr class="even">
            <td >
                موضوع الحاقیه جدید:
            </td>

            <td colspan="3">
                <textarea id="new_subject_txt" rows="1" cols="80" style="text-align:right;"></textarea>
            </td>
        </tr>
        <tr class="odd">
            <td >
                متن الحاقیه جدید:
            </td>
            <td colspan="3">
                <textarea id="new_desc_txt" rows="5" cols="80" style="text-align:right;"></textarea>
            </td>
        </tr>
    </table>

</div>

<table align="center" class="jtable resultDets" cellpadding="0" cellspacing="0" style="margin:0 auto;">
    <tr>
        <th>ردیف</th>
        <th>وضعیت</th>
        <th >شماره بیمه نامه / کد رهگیری پیشنهاد</th>
        <th  >بیمه گذار یا بیمه شده</th>
        <th >تغییر در محاسبات بیمه نامه</th>
        <c:if test='${darkhastTaghirat.state.id != 9180}'>
            <th style="white-space: nowrap;">اطلاعات الحاقیه </th>
        </c:if>
        <th>نتیجه بررسی</th>
    </tr>
    <c:forEach var="row" items="${listBimenameTaghirVM}" varStatus="i">
        <tr>
            <td>${i.index+1}</td>
            <td>${row.state}</td>
            <td>${row.shomare}</td>
            <td>${row.shakhsRole}</td>
            <td>
                <c:if test="${row.taghirMohasebati}">
                    دارد
                </c:if>
                <c:if test="${!row.taghirMohasebati}">
                    ندارد
                </c:if>
            </td>
            <c:if test='${darkhastTaghirat.state.id != 9180}'>
            <td style="white-space: nowrap;">
                <div class="dblRadio" id="mod_elhaghie_info${i.index+1}" style="padding-right: 30px;">
                    <input type="radio"  name="infoElhaghie[${i.index+1}]" id="mod_elhaghie_info_new${i.index+1}" value="NEW"
                       onclick="changeElhaghieInfo('${row.shomare}','mod_elhaghie_info_new','${i.index+1}','${row.bimename.tarikhShorou}','${row.bimename.lastTarikhAsarElhaghie}');"
                       title="درج اطلاعات جدید"/>
                    <label id="lbl_elhaghie_info_new${i.index+1}" for="mod_elhaghie_info_new${i.index+1}" >درج اطلاعات جدید</label>

                    <input type="radio" name="infoElhaghie[${i.index+1}]" id="mod_elhaghie_info_current${i.index+1}" value="CURRENT" checked="checked"
                       onclick="changeElhaghieInfo('${row.shomare}','mod_elhaghie_info_current','${i.index+1}');"
                       title="ثبت از اطلاعات الحاقیه جاری"/>
                    <label id="lbl_elhaghie_info_current${i.index+1}" style="" for="mod_elhaghie_info_current${i.index+1}">ثبت از اطلاعات الحاقیه جاری</label>
                </div>
                <%--<input type="button" value="..." onclick="newInfoElhaghie('${row.shomare}',${i.index+1})" />--%>
                <input type="hidden" id="elhaghie_info_subject${i.index+1}" name="darkhastTaghirat.darkhast.elhaghiyeList[${i.index+1}].mozoo"/>
                <input type="hidden" id="elhaghie_info_desc${i.index+1}" name="darkhastTaghirat.darkhast.elhaghiyeList[${i.index+1}].matn"/>
                <input type="hidden" id="elhaghie_info_date${i.index+1}" name="darkhastTaghirat.darkhast.elhaghiyeList[${i.index+1}].tarikhAsar" value="${row.bimename.tarikhSodour}"/>
            </td>
            </c:if>
            <td style="white-space: nowrap;">
                <c:if test='${darkhastTaghirat.state.id != 9180}'>
                    <div class="dblRadio" id="mod_elhaghieAshkhasButton_div${i.index+1}" style="padding-right: 30px;">
                        <input type="radio" name="elhaghieAshkhasButton[${i.index+1}]" id="taeedElhaghieAshkhasButton_${i.index+1}"
                               value="1"
                               onclick="taeed(${i.index+1})"
                               title="تایید"/>
                        <label id="taeedElhaghieAshkhasLbl_${i.index+1}" for="taeedElhaghieAshkhasButton_${i.index+1}">تایید</label>

                        <input type="radio" name="elhaghieAshkhasButton[${i.index+1}]" id="adameTaeedElhaghieAshkhasButton_${i.index+1}"
                               value="0" checked="checked"
                               onclick="adameTaeed(${i.index+1})"
                               title="عدم تایید"/>
                        <label id="taeedElhaghieAshkhasLbl_${i.index+1}" style=""
                               for="adameTaeedElhaghieAshkhasButton_${i.index+1}">عدم تایید</label>
                    </div>
                <%--<input type="button" value="تایید" onclick="taeed(${i.index+1})" id="taeedElhaghieAshkhasButton_${i.index+1}"/>--%>
                <input type="hidden" value="false" class="taeedElhaghieAshkhas" id="taeedElhaghieAshkhas_${i.index+1}"/>
                </c:if>
                <c:if test='${darkhastTaghirat.state.id == 9180}'>
                    صادر شده
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<br/><br/><br/><br/>

<c:if test='${darkhastTaghirat.state.id == 9180}'>
    <div style="margin: 0 auto;">
    <input type="button" style="margin: 10px;"
           onclick="window.open('/printElhaghieFinalA5?darkhastTaghirat.id=${darkhastTaghirat.id}')"
           value="چاپ A5">
    <input type="button" style="margin: 10px;"
           onclick="window.open('/printElhaghieFinalA4?darkhastTaghirat.id=${darkhastTaghirat.id}')"
           value="چاپ A4">
    </div>
</c:if>


<script language="JavaScript">
    function taeed(rowNum) {
        $('#taeedElhaghieAshkhas_' + rowNum).val('true');
        $('#mod_elhaghie_info_current'+rowNum).attr('disabled', true);
        $('#mod_elhaghie_info_new'+rowNum).attr('disabled', true);
        $('#mod_elhaghie_info'+rowNum).buttonset().buttonset("disable");
    }
    function adameTaeed(rowNum)
    {
        $('#taeedElhaghieAshkhas_' + rowNum).val('false');
        $('#mod_elhaghie_info_current' + rowNum).remove('disabled');
        $('#mod_elhaghie_info_new' + rowNum).remove('disabled');
        $('#mod_elhaghie_info' + rowNum).buttonset().buttonset("enable");
    }

    function changeElhaghieInfo(shomareBimename,theIdType,numUnique,tarikhShoru,lastTarikhAsar)
    {
        if ($('#mod_elhaghie_info_new'+numUnique).is(':checked'))
        {
            $('#new_subject_txt').val($('#elhaghie_info_subject' + numUnique).val());
            $('#new_desc_txt').val($('#elhaghie_info_desc' + numUnique).val());
            $('#new_date_txt').val($('#elhaghie_info_date' + numUnique).val());
            $('#new_info_elhaghie').dialog
            (
                {
                    modal: true,
                    width: 600,
                    resizable: false,
                    closeText: "",
                    title: "درج اطلاعات جدید الحاقیه برای بیمه نامه "+shomareBimename,
                    buttons:
                    {
                        "انصراف": function ()
                        {
                            $('#mod_elhaghie_info_new' + numUnique).removeAttr('checked');
                            $('#lbl_elhaghie_info_new' + numUnique).removeClass('ui-state-active');

                            $('#mod_elhaghie_info_current' + numUnique).attr('checked', 'checked');
                            $('#lbl_elhaghie_info_current' + numUnique).addClass('ui-state-active');
                            $(this).dialog("close");
                            return false;
                        },
                        "تایید": function ()
                        {
                            if($('#new_date_txt').val()==null || $('#new_date_txt').val() == '')
                            {
                                alertMessage("فیلد تاریخ اثر الزامی است.");
                            }
                            else
                            {
                                if(!isDateGreaterThanOrEqual($('#new_date_txt').val(),tarikhShoru))
                                {
                                    alertMessage('تاریخ اثر الحاقیه نمی تواند قبل از تاریخ شروع بیمه نامه باشد.');
                                }
                                else if(lastTarikhAsar!=null && lastTarikhAsar!='' && !isDateGreaterThan($('#new_date_txt').val(),lastTarikhAsar))
                                {
                                    alertMessage('تاریخ اثر الحاقیه باید بزرگتر از تاریخ اثر الحاقیه های قبلی باشد.');
                                }
                                else
                                {
                                    $('#elhaghie_info_subject'+numUnique).val($('#new_subject_txt').val());
                                    $('#elhaghie_info_desc' + numUnique).val($('#new_desc_txt').val());
                                    $('#elhaghie_info_date' + numUnique).val($('#new_date_txt').val());
                                    $(this).dialog("close");
                                    return true;
                                }
                            }
                        }
                    }
                }
            );
        }
    }
</script>
