
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
     function enserafEbtal(){
        $.validationEngine.closePrompt('.formError', true);
        $('#ebt_frm_tarikheSabt').val('');
        $('#ebt_frm_tozihat').val('');
//        $('#ebt_frm_serialF').val('');
        //$('#ebt_frm_elateEbtal').
    }
    var ebtSer,dilg;
    function ebtalSerial(ebtSerial)
    {
//        var ebtSerialVal =$('#'+ebtSerialId).val();
//        $.validationEngine.closePrompt('.formError', true);
        ebtSer=ebtSerial;
        $('#ebt_frm_tozihat').val('');
        $('#ebt_frm_serialFirst').val(ebtSer);
        $('#ebt_frm_serialLast').val(ebtSer);
        dilg = $('#ebtalSerials').dialog({
            modal:true,
            width: 840,
            resizable:false,
            closeText: "",
            title:"ابطال سریال",
            close:enserafEbtal,
            buttons: {
                "ابطال": ebtal,
                "انصراف": function(){enserafEbtal();$(this).dialog("close");}
            }
        });
    }

    function ebtal() {
        if($.validationEngine.submitValidation(dilg) === false)
        {
//            $('#ebt_frm_serialFirst').val(ebtSer)
//            $('#ebt_frm_serialLast').val(ebtSer)
//            window.alert($('#ebt_frm_elateEbtal').val());
            var form=$('#ebtalSerialsForm').serialize();
            $.post('/ebtalSerials.action', form, function(msg){window.location.reload();});
            $(dilg).dialog("close");
        }
    }
</script>

<div id="ebtalSerials" style="display:none;">
    <form id="ebtalSerialsForm" method="post" action="/ebtalSerials.action">
        <%--<input type="button" onclick="window.alert($('#ebt_frm_tozihat').val());"/>--%>
        <table class="inputList" width="90%">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="serialFirst"  id="ebt_frm_serialFirst" class="validate[required,custom[onlyNumber]]" readonly="readonly"/>
                    &nbsp;<label>سریال ابطالی</label>
                    <input type="hidden" name="serialLast"  id="ebt_frm_serialLast"  class="validate[required,custom[onlyNumber]]" />
                </td>
                <td>
                    <input type="text" name="tarikheSabt" id="ebt_frm_tarikheSabt" class="validate[required,custom[date]]" value="<%=DateUtil.getCurrentDate()%>" readonly="readonly"/>
                    &nbsp;<label>تاریخ ابطال</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="tozihat"  id="ebt_frm_tozihat"  value=""/>
                    &nbsp;<label>توضیحات</label>
                </td>
                <td>

                    <s:select list="#{'ESHTEBAH_DAR_SABT':'اشتباه در ثبت',
                                      'ENTEGHAL_BE_NAMAYANDEGIE_DIGAR':'انتقال به نماینده دیگر',
                                      'TAGHIRE_NOE_DAFTARCHE':'تغییر نوع دفترچه',
                                      'KHARABI_KAGHAZ_HENGAME_CHAP':'خرابی کاغذ هنگام چاپ',
                                       'SODURE_BIMENAME_ALMOSANNA':'صدور بیمه نامه المثنی',
                                       'KEYFIAT_NAMONASEBE_CHAP':'کیفیت نامناسب چاپ',
                                       'MAKHDOSH_BODAN_KAGHAZ_GHABL_AZ_CHAP':'مخدوش بودن کاغذ قبل از چاپ',
                                       'MAFGHOD_SHODANE_BIMENAME':'مفقود شدن بیمه نامه'}"
                               name="elateEbtal" id="ebt_frm_elateEbtal" label="" key="elateEbtal" theme="simple"/>
                    &nbsp;<label>علت ابطال</label>
                </td>
            </tr>
        </table>
    </form>
</div>