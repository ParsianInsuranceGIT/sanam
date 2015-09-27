<%@ page import="com.bitarts.common.util.DateUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: a-khezri
  Date: 2/20/13
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">

    function enserafEbtal(){
        $.validationEngine.closePrompt('.formError', true);
        $('#ebt_frm_tarikheSabt').val('');
        $('#ebt_frm_tozihat').val('');
        $('#ebt_frm_serialFirst').val('');
        $('#ebt_frm_serialLast').val('');
        //$('#ebt_frm_elateEbtal').
    }

    function ebtalSerials()
    {
        $.validationEngine.closePrompt('.formError', true);
        dilg = $('#ebtalSerials').dialog({
            modal:true,
            width: 840,
            resizable:false,
            closeText: "",
            title:"ابطال سریال ها",
            close:enserafEbtal,
            buttons: {
                "ابطال": ebtal,
                "انصراف": function(){enserafEbtal();$(this).dialog("close");}
            }
        });
    }

    function ebtal() {
        if($.validationEngine.submitValidation(this) === false)
        {
            $('#ebtalSerialsForm').submit();
            $(this).dialog("close");
        }
    }

    function sabt() {
        if($.validationEngine.submitValidation(this) === false)
        {
            $('#sabtSerialsForm').submit();
            $(this).dialog("close");
        }
    }

    function edit() {
        if($.validationEngine.submitValidation(this) === false)
        {
            $('#editDasteSerialsForm').submit();
            $(this).dialog("close");
        }
    }

    function view()
    {
        if($.validationEngine.submitValidation(this) === false)
        {
            $("#viewSerialsForm").attr('target', '_blank');
            $('#viewSerialsForm').submit();
            $(this).dialog("close");
        }
    }

    function enserafEdit()
    {
        $.validationEngine.closePrompt('.formError', true);
//        $('#edit_frm_vaziatDaste').val('');
        $('#edit_frm_kodeDaste').val('');
        $('#edit_frm_idDaste').val('');
    }

    function enserafView()
    {
        $.validationEngine.closePrompt('.formError', true);
        $('#view_frm_serialFirst').val('');
        $('#view_frm_serialLast').val('');

    }

    function enserafSabt()
    {
        $.validationEngine.closePrompt('.formError', true);
//        $('#sbt_frm_kodeDaste').val('');
//        $('#sbt_frm_serialFirst').val('');
//        $('#sbt_frm_serialLast').val('');
//        $('#sbt_frm_bimenameType').val('');
//        $('#sbt_frm_tarikheSabt').val('');
        $('#sbt_frm_namayandegi').val('');
        $('#sbt_frm_vahedeSodur').val('');
        $('#sbt_frm_tedadeKol').val('');
        $('#sbt_frm_tedadEbtali').val('');
        $('#sbt_frm_mizaneJabejayi').val('');
    }

    function viewSerialsEstefadeNashode()
    {
        $.validationEngine.closePrompt('.formError', true);
        dilg = $('#viewSerials').dialog({
            modal:true,
            width: 840,
            resizable:false,
            closeText: "",
            title:"نمایش سریال های استفاده نشده",
            close:enserafView,
            buttons: {
                "نمایش": view,
                "انصراف": function(){enserafView();$(this).dialog("close");}
            }
        });
    }

    function createDasteSerial()
    {
        $.validationEngine.closePrompt('.formError', true);
        dilg = $('#sabtSerials').dialog({
            modal:true,
            width: 840,
            resizable:false,
            closeText: "",
            title:"ایجاد دسته جدید",
            close:enserafEdit,
            buttons: {
                "ثبت": sabt,
                "انصراف": function(){enserafSabt();$(this).dialog("close");}
            }
        });
    }
    var n;
    function editDasteSerial(dasteSerialId,dasteSerialKode,n)
    {

        $('#edit_frm_idDaste').val(parseInt(dasteSerialId));
        $('#edit_frm_kodeDaste').val(parseInt(dasteSerialKode));
        $.validationEngine.closePrompt('.formError', true);
                            if(n==1)
                            {
                                $('#edit_frm_vaziatDaste').show();
                                $('#edit_frm_vaziatDaste').empty();
                                var data={'FAAL':'فعال', 'GHEYRE_FAAL':'غیرفعال', 'BASTE_SHODE':'بسته شده'};
                            }

                            else
                            {
                                $('#edit_frm_vaziatDaste').show();
                                $('#edit_frm_vaziatDaste').empty();
                                var data={'FAAL':'فعال', 'GHEYRE_FAAL':'غیرفعال'}
                            }

                            for(var val in data)
                            {
                               $('<option />', {value: val, text: data[val]}).appendTo('#edit_frm_vaziatDaste');
                            }


        dilg = $('#editDasteSerials').dialog({
            modal:true,
            width: 840,
            resizable:false,
            closeText: "",
            title:"تغییر وضعیت دسته",
            close:enseraf,
            buttons: {
                "ثبت": edit,
                "انصراف": function(){enserafEdit();$(this).dialog("close");}
            }
        });
    }
</script>

    <div id="sabtSerials" style="display:none;">
        <form id="sabtSerialsForm" action="/sabtSerials.action" method="post">

            <table class="inputList" width="90%">
                <tr>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="dasteSerial.kodeDaste" id="sbt_frm_kodeDaste" readonly="true" class="validate[required,custom[onlyNumber]]" value="${lastDasteSerial.kodeDaste + 1}" />
                        &nbsp;<label>کد دسته</label>
                    </td>
                    <td>
                        <span class="noThing"></span>
                        <s:select list="#{'FAAL':'فعال', 'GHEYRE_FAAL':'غیرفعال', 'BASTE_SHODE':'بسته شده'}"
                                   name="vaziateDaste" id="sbt_frm_vaziatDaste" label="" key="vaziateDaste" theme="simple"/>
                        &nbsp;<label>وضعیت دسته</label>
                    </td>
                </tr>
                <tr>
                    <%--<script>--%>
                        <%--$('#sbt_frm_serialFirst').keyup(function () {--%>
                            <%--var value = 99;--%>
                            <%--value+=parseInt($(this).val());--%>
                            <%--$('#sbt_frm_serialLast').val(value);--%>
                        <%--}).keyup();--%>
<%--//                    </script>--%>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="serialFirst"  id="sbt_frm_serialFirst" class="validate[required,custom[onlyNumber]]"  />
                        &nbsp;<label>از سریال</label>
                    </td>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="serialLast"  id="sbt_frm_serialLast"  class="validate[required,custom[onlyNumber]]"   />
                        &nbsp;<label>تا سریال</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="dasteSerial.bimenameType" id="sbt_frm_bimenameType" class="validate[required]" readonly="true" value="بیمه نامه عمر و سرمایه گذاری"/>
                        &nbsp;<label>نوع بیمه نامه</label>
                    </td>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="dasteSerial.tarikheSabt" id="sbt_frm_tarikheSabt" value="<%=DateUtil.getCurrentDate()%>" class="validate[required,custom[date]]" readonly="readonly"/>
                        &nbsp;<label>تاریخ ثبت</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('sbt_frm_namayandegiId','sbt_frm_namayandegiName', '');" style="float:left;" title="جستجو"></span>
                        &nbsp;<label>نمایندگی</label>
                        <input type="hidden" name="namayandegiId" id="sbt_frm_namayandegiId"
                               value="${user.mojtamaSodoor.id}"/>
                        <input type="text" name="namayandegiName"  id="sbt_frm_namayandegiName" class="validate[required]" readonly="true"
                               value="${user.mojtamaSodoor.name}"/>
                    </td>
                    <td>
                        <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('sbt_frm_vahedeSodurId','sbt_frm_vahedeSodurName', '');" style="float:left;" title="جستجو"></span>
                        &nbsp;<label>واحد صدور</label>
                        <input type="hidden" name="vahedeSodurId" id="sbt_frm_vahedeSodurId" class="validate[required]" value="${user.mojtamaSodoor.id}"/>
                        <input type="text" name="vahedeSodurName" id="sbt_frm_vahedeSodurName" class="validate[required]" readonly="true" value="${user.mojtamaSodoor.name}"/>
                    </td>
                </tr>
                <tr>
                    <script>
                        $('#sbt_frm_serialLast').keyup(function () {
                            var value =(parseInt($('#sbt_frm_serialLast').val()) - parseInt($('#sbt_frm_serialFirst').val()))+1 ;
                            $('#sbt_frm_countSerials').val(value);
                        }).keyup();
//                  </script>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="countSerials" id="sbt_frm_countSerials" readonly="true" value=""/>
                        &nbsp;<label>تعداد کل</label>
                    </td>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="countSerialsEbtali" id="sbt_frm_countSerialsEbtali" readonly="true" value="0"/>
                        &nbsp;<label>تعداد سریال های ابطالی</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="dasteSerial.mizaneJabejayi" id="sbt_frm_mizaneJabejayi" class="validate[required,custom[onlyNumber]]" />
                        &nbsp;<label>میزان جابه جایی (به میلیمتر)</label>
                    </td>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="bazaryabId" readonly="true"/>
                        <%--<s:select theme="simple" headerValue="" list="bazaryabs"--%>
                            <%--name="bazaryabId"  listKey="id" --%>
                             <%--listValue="fullName"/>--%>
                        &nbsp;<label>بازاریاب</label>
                    </td>
                </tr>
            </table>
            <div id="sabtMessage"></div>
        </form>
    </div>

    <div id="editDasteSerials" style="display:none;">
        <form id="editDasteSerialsForm" method="post" action="/editDasteSerials.action">
            <table class="inputList" width="90%">
                <tr>
                    <td>
                        <span class="noThing"></span>
                        <input type="hidden" name="dasteSerialId" id="edit_frm_idDaste"/>
                        <input type="text" name="kodeDaste" id="edit_frm_kodeDaste" class="validate[required,custom[onlyNumber]]" readonly="true" />
                        &nbsp;<label>کد دسته</label>
                    </td>
                    <td>
                        <span class="noThing"></span>
                        <%--<s:select list="#{'FAAL':'فعال', 'GHEYRE_FAAL':'غیرفعال', 'BASTE_SHODE':'بسته شده'}"--%>
                                   <%--name="vaziateDaste" id="edit_frm_vaziatDaste" label="" key="vaziateDaste" theme="simple"/>--%>
                        <select name="vaziateDaste" id="edit_frm_vaziatDaste"/>
                        &nbsp;<label>وضعیت دسته</label>
                    </td>
                </tr>
            </table>
            <div id="editMessage"></div>
        </form>
    </div>

    <div id="viewSerials" style="display:none;">
        <form id="viewSerialsForm" method="post" action="/serialsEstefadeNashode.action">
            <table class="inputList" width="90%">
                <tr>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="serialFirst"  id="view_frm_serialFirst" class="validate[required,custom[onlyNumber]]"  />
                        &nbsp;<label>از سریال</label>
                    </td>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="serialLast"  id="view_frm_serialLast"  class="validate[required,custom[onlyNumber]]" />
                        &nbsp;<label>تا سریال</label>
                    </td>
                </tr>
            </table>
            <div id="serialsReport"></div>
        </form>
    </div>

    <div id="ebtalSerials" style="display:none;">
        <form id="ebtalSerialsForm" method="post" action="/ebtalSerials.action">
            <table class="inputList" width="90%">
                <tr>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="serialFirst"  id="ebt_frm_serialFirst" class="validate[required,custom[onlyNumber]]" />
                        &nbsp;<label>از سریال</label>
                    </td>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="serialLast"  id="ebt_frm_serialLast"  class="validate[required,custom[onlyNumber]]" />
                        &nbsp;<label>تا سریال</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="noThing"></span>
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
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="tozihat"  id="ebt_frm_tozihat"  value=""/>
                        &nbsp;<label>توضیحات</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="tarikheSabt" id="ebt_frm_tarikheSabt" class="validate[required,custom[date]] datePkr"/>
                        &nbsp;<label>تاریخ ابطال</label>
                    </td>
                    <td></td>
                </tr>
            </table>
            <div id="ebtalMessage"></div>
        </form>
    </div>
