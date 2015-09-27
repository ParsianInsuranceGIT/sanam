<%@ page import="com.bitarts.parsian.model.constantItems.ConstantForPishnehadForm" %>
<%@ page import="com.bitarts.parsian.model.pishnahad.Shakhs" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 4/7/11
  Time: 2:18 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<input type="hidden" id="haghighiorhoghoughiremember"/>
<div id="result_edit_shakhsBimeGozar">

</div>
<div id="haghighi_hoghoghi_div" style="display:none;direction:rtl;width:400px">
    <table class="inputList">
        <tr>
            <td>
                <select id="haghighiOrHoghoghi">
                    <option value="<%=Shakhs.BimeGozarType.HAGHIGHI%>">حقیقی</option>
                    <option value="<%=Shakhs.BimeGozarType.HOGHOGHI%>">حقوقی</option>
                </select>
                &nbsp;<label>نوع شخص بیمه گذار را انتخاب کنید: </label>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    function fill_bimeGozar(msg){
        if($('#bimeGozar_shakhs_id').val() != msg.split(',')[1]){
            $('#addressBimeGozar_ostaanManzel').val('');
            $('#addressBimeGozar_shahrManzel').val('');
            $('#addressBimeGozar_neshaniManze').val('');
            $('#addressBimeGozar_kodePostiManzel').val('');
            $('#addressBimeGozar_posteElectronic').val('');
            $('#addressBimeGozar_codeTelephoneManzel').val('');
            $('#addressBimeGozar_telephoneManzel').val('');
            $('#addressBimeGozar_telephoneHamrah').val('');
            $('#addressBimeGozar_ostaanMahalleKar').val('');
            $('#addressBimeGozar_shahrMahalleKar').val('');
            $('#addressBimeGozar_neshaniMahalleKar').val('');
            $('#addressBimeGozar_kodePostiMahallekar').val('');
            $('#addressBimeGozar_codeTelephoneMahalleKar').val('');
            $('#addressBimeGozar_telephoneMahalleKar').val('');
            $('#bimeGozar_nesbatBaBimeShode').val('خود شخص');
            $('#bimeGozar_daramdeMahiane').val('');
        }
        var bgShakhsId= msg.split(',')[1];
        $('#bimeGozar_shakhs_id').val(parseInt(bgShakhsId.replace(new RegExp(",", "gm"), "")));
        <c:if test="${isHalateElhaghie}">
            $('#newShakhsBimeGozarId_Id').remove();
            $('#changeWithSerach_id').remove();
            var input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "newShakhsBimeGozarId");
            input.setAttribute("id", "newShakhsBimeGozarId_Id");
            input.setAttribute("value", msg.split(',')[1]);
            document.getElementById("divElha").appendChild(input);

            var input1 = document.createElement("input");
            input1.setAttribute("type", "hidden");
            input1.setAttribute("name", "pishnehad.bimeGozar.shakhs.changeWithSerach");
            input1.setAttribute("id", "changeWithSerach_id");
            input1.setAttribute("value", "yes");
            document.getElementById("divElha").appendChild(input1);
        </c:if>
        $('#name_bimeGozar').val(msg.split(',')[2]);
        $('#bimeGozar_shakhs_tarikheTavallod').val(msg.split(',')[3]);
        $('#bimeGozar_shakhs_jensiat').val(msg.split(',')[4]);
        $('#bimeGozar_shakhs_kodemelli').val(msg.split(',')[10]);

        if(msg.split(',')[25] == '<%=Shakhs.BimeGozarType.HOGHOGHI%>'){
            $('#addressBimeGozar_neshaniManze').attr('disabled', true);
            $('#addressBimeGozar_neshaniManze').addClass("ui-state-disabled");
            $('#addressBimeGozar_kodePostiManzel').attr('disabled', true);
            $('#addressBimeGozar_kodePostiManzel').addClass("ui-state-disabled");
            $('#addressBimeGozar_codeTelephoneManzel').attr('disabled', true);
            $('#addressBimeGozar_codeTelephoneManzel').addClass("ui-state-disabled");
            $('#addressBimeGozar_telephoneManzel').attr('disabled', true);
            $('#addressBimeGozar_telephoneManzel').addClass("ui-state-disabled");
            $('#bimeGozar_daramdeMahiane').attr('disabled', true);
            $('#bimeGozar_daramdeMahiane').addClass("ui-state-disabled");
            $('#bimeGozar_OstanZendegi').attr('disabled', true);
            $('#bimeGozar_OstanZendegi').addClass("ui-state-disabled");
            $('#bimeGozar_ShahrZendegi').attr('disabled', true);
            $('#bimeGozar_ShahrZendegi').addClass("ui-state-disabled");
            $('#buttonforzendegiselect').hide();
            $('#bimeGozar_OstanZendegi').val('');
            $('#bimeGozar_OstanZendegiId').val('');
            $('#bimeGozar_ShahrZendegi').val('');
            $('#bimeGozar_ShahrZendegiId').val('');

        }else{
            $('#addressBimeGozar_neshaniManze').removeAttr('disabled');
            $('#addressBimeGozar_neshaniManze').removeClass('ui-state-disabled');
            $('#addressBimeGozar_kodePostiManzel').removeAttr('disabled');
            $('#addressBimeGozar_kodePostiManzel').removeClass('ui-state-disabled');
            $('#addressBimeGozar_codeTelephoneManzel').removeAttr('disabled');
            $('#addressBimeGozar_codeTelephoneManzel').removeClass('ui-state-disabled');
            $('#addressBimeGozar_telephoneManzel').removeAttr('disabled');
            $('#addressBimeGozar_telephoneManzel').removeClass('ui-state-disabled');
            $('#bimeGozar_daramdeMahiane').removeAttr('disabled');
            $('#bimeGozar_daramdeMahiane').removeClass("ui-state-disabled");
            $('#bimeGozar_OstanZendegi').removeAttr('disabled');
            $('#bimeGozar_OstanZendegi').removeClass("ui-state-disabled");
            $('#bimeGozar_ShahrZendegi').removeAttr('disabled');
            $('#bimeGozar_ShahrZendegi').removeClass("ui-state-disabled");
            $('#buttonforzendegiselect').show();
        }
    }
    function fillShakhsBimeGozar() {
        dialogForm('haghighi_hoghoghi_div','نوع شخص بیمه گذار',function(){
            if($('#haghighiOrHoghoghi').val() == '<%=Shakhs.BimeGozarType.HAGHIGHI.toString()%>'){
                $("#haghighiorhoghoughiremember").val("<%=Shakhs.BimeGozarType.HAGHIGHI.toString()%>");
                openJosteju(false, function(id){
                    $.post('/fillShakhs.action?shakhsId=' + id, function(msg) {
                        fill_bimeGozar(msg);
                        $("#copyazbimegozarbutton").show();
                        $('#bimeGozar_nesbatBaBimeShode option:selected').removeAttr('selected');
                        $("#bimeGozar_nesbatBaBimeShode option[value='خود شخص']").attr('selected', 'selected');
                    });
                });
            }else{
                $("#haghighiorhoghoughiremember").val("<%=Shakhs.BimeGozarType.HOGHOGHI.toString()%>");
                openJostejuHoghughi(function(id){
                    $.post('/fillShakhs.action?shakhsId=' +id,function(msg){
                        fill_bimeGozar(msg);
                        $("#copyazbimegozarbutton").hide();
                        $('#bimeGozar_nesbatBaBimeShode option:selected').removeAttr('selected');
                        $("#bimeGozar_nesbatBaBimeShode option[value='كارفرما']").attr('selected', 'selected');
                    });

                });

            }
        });
    }
    function addToBimeGozar(){
        dialogForm('haghighi_hoghoghi_div','نوع شخص بیمه گذار',function(){
            if($('#haghighiOrHoghoghi').val() == '<%=Shakhs.BimeGozarType.HOGHOGHI.toString()%>'){
                $("#haghighiorhoghoughiremember").val("<%=Shakhs.BimeGozarType.HOGHOGHI.toString()%>");
                dialogForm('shakhsHoghoghiElements','اضافه کردن بیمه گذار حقوقی',function(){
                    $.validationEngine.onSubmitValid = true;
                    if ($.validationEngine.submitValidation($('#shakhsHoghoghiElements')) === false) {
                        var mainForm2 = $('#shakhsHoghoghiForm').serialize();
                        $.post('/registerShakhs.action', mainForm2, function(msg) {
                            if(msg.indexOf('SHAKHS_INFO') != -1){
                                fill_bimeGozar(msg);
                            }else{
                                $('#shakhsHoghoghiElements #msgErr_div').html(msg);
                            }
                        });
                    };
                    $("#copyazbimegozarbutton").hide();
                    $('#bimeGozar_nesbatBaBimeShode option:selected').removeAttr('selected');
                    $("#bimeGozar_nesbatBaBimeShode option[value='كارفرما']").attr('selected', 'selected');
                });
            }else{
                $("#haghighiorhoghoughiremember").val("<%=Shakhs.BimeGozarType.HAGHIGHI.toString()%>");
                openAddShakhsDialog(function(msg){
                    fill_bimeGozar(msg);
                    $("#copyazbimegozarbutton").show();
                    $('#bimeGozar_nesbatBaBimeShode option:selected').removeAttr('selected');
                    $("#bimeGozar_nesbatBaBimeShode option[value='خود شخص']").attr('selected', 'selected');
                });
            }
        });
    }
</script>
<div id="divElha"></div>
<input type="hidden" id="bimeGozar_id" name="pishnehad.bimeGozar.id" value="${pishnehad.bimeGozar.id}">
<input type="hidden" id="bimeGozar_shakhs_id" name="pishnehad.bimeGozar.shakhs.id" value="${pishnehad.bimeGozar.shakhs.id}">
<input type="hidden" id="bimeGozar_shakhs_tarikheTavallod" value="${pishnehad.bimeGozar.shakhs.tarikheTavallod}">
<input type="hidden" id="bimeGozar_shakhs_jensiat" value="${pishnehad.bimeGozar.shakhs.jensiat}">
<input type="hidden" id="bimeGozar_shakhs_kodemelli" value="${pishnehad.bimeGozar.shakhs.kodeMelliShenasayi}">
<table class="inputList" border="0" cellspacing="5" cellpadding="1">
    <tr>
        <td style="color: red;">
            <ul><li>
            براي تغيير بيمه‌گذار فقط از دكمه هاي جديد يا جستجو استفاده شود.
                </li></ul>
        </td>
    </tr>
    <tr>
        <td colspan="2"><label for="name_bimeGozar">بیمه گذار </label>
            <span class="noThing"></span>

            <input type="button" id="namayesheEtelaateFard" value="نمایش اطلاعات فرد" onclick="showBimegozarDetails();"/>
            <input type="button" value="+ جدید" onclick="addToBimeGozar();"/>
            <input type="button" value="جستجو" onclick="fillShakhsBimeGozar();" style="margin:0 2px"/>
            <input type="text" id="name_bimeGozar" class="validate[required] text-input"
                    <c:if test="${pishnehad.bimeGozar.shakhs.name != null || pishnehad.bimeGozar.shakhs.nameKhanevadegi != null}">
                        value='${pishnehad.bimeGozar.shakhs.name} ${pishnehad.bimeGozar.shakhs.nameKhanevadegi}'
                    </c:if>
                   style="width:402px;" readonly="readonly"/>
        </td>
    </tr>

    <%--address--%>
    <input type="hidden" name="pishnehad.addressBimeGozar.id" value="${pishnehad.addressBimeGozar.id}" id="addressBimeGozar_id"/>

    <tr>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text value="${pishnehad.addressBimeGozar.ostaanManzel.cityName}" id="bimeGozar_OstanZendegi" class="validate[required]" readonly="readonly"/>&nbsp;<label>استان</label>
            <input type=hidden name="pishnehad.addressBimeGozar.ostaanManzel.cityId" value="${pishnehad.addressBimeGozar.ostaanManzel.cityId}" id="bimeGozar_OstanZendegiId"/>
        </td>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text value="${pishnehad.addressBimeGozar.shahrManzel.cityName}" id="bimeGozar_ShahrZendegi" class="validate[required]" readonly="readonly"/>&nbsp;<label>شهر</label>
            <input type=hidden name="pishnehad.addressBimeGozar.shahrManzel.cityId" value="${pishnehad.addressBimeGozar.shahrManzel.cityId}" id="bimeGozar_ShahrZendegiId"/>
            <input type=button id="buttonforzendegiselect" value="انتخاب" onclick="fillShahrOstan('bimeGozar_ShahrZendegiId','bimeGozar_ShahrZendegi','bimeGozar_OstanZendegiId','bimeGozar_OstanZendegi','addressBimeGozar_neshaniManze');" style="margin:0 230px; position: absolute;"/>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <span class="nohelp">&nbsp;</span>
            <input style="width:509px;" type=text name="pishnehad.addressBimeGozar.neshaniManzel" value="${pishnehad.addressBimeGozar.neshaniManzel}" id="addressBimeGozar_neshaniManze"
                   class="validate[required,custom[address]] text-input"/>&nbsp;<label>نشانی
            منزل</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text name="pishnehad.addressBimeGozar.kodePostiManzel" value="${pishnehad.addressBimeGozar.kodePostiManzel}" id="addressBimeGozar_kodePostiManzel"
                   class="validate[custom[code_posti]] text-input"/>&nbsp;<label>كدپستي منزل</label>
        </td>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text name="pishnehad.addressBimeGozar.posteElectronic" value="${pishnehad.addressBimeGozar.posteElectronic}" id="addressBimeGozar_posteElectronic"
                   class="validate[custom[email]] text-input"/>&nbsp;<label>پست الکترونیک</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text name="pishnehad.addressBimeGozar.codeTelephoneManzel" value="${pishnehad.addressBimeGozar.codeTelephoneManzel}" id="addressBimeGozar_codeTelephoneManzel" style="width:35px"
                   class="validate[required,custom[integer],custom[code_shahri]] text-input"/>&nbsp;
            <input type=text name="pishnehad.addressBimeGozar.telephoneManzel" value="${pishnehad.addressBimeGozar.telephoneManzel}" id="addressBimeGozar_telephoneManzel" style="width:96px"
                   class="validate[required,custom[integer]] text-input"/>&nbsp;<label>تلفن منزل</label></td>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text name="pishnehad.addressBimeGozar.telephoneHamrah" value="${pishnehad.addressBimeGozar.telephoneHamrah}" id="addressBimeGozar_telephoneHamrah"
                   class="validate[custom[telephone_hamraah]] text-input"/>&nbsp;<label>تلفن همراه
            (ضروری)</label></td>
    </tr>
    <tr>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text value="${pishnehad.addressBimeGozar.ostaanMahalleKar.cityName}" id="bimeGozar_OstanKar" class="validate[required]" readonly="readonly"/>&nbsp;<label>استان محل کار</label>
            <input type=hidden name="pishnehad.addressBimeGozar.ostaanMahalleKar.cityId" value="${pishnehad.addressBimeGozar.ostaanMahalleKar.cityId}" id="bimeGozar_OstanKarId"/>
        </td>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text value="${pishnehad.addressBimeGozar.shahrMahalleKar.cityName}" id="bimeGozar_ShahrKar" class="validate[required]" readonly="readonly"/>&nbsp;<label>شهر محل کار</label>
            <input type=hidden name="pishnehad.addressBimeGozar.shahrMahalleKar.cityId" value="${pishnehad.addressBimeGozar.shahrMahalleKar.cityId}" id="bimeGozar_ShahrKarId"/>
            <input type=button value="انتخاب" onclick="fillShahrOstan('bimeGozar_ShahrKarId','bimeGozar_ShahrKar','bimeGozar_OstanKarId','bimeGozar_OstanKar','addressBimeGozar_neshaniMahalleKar');" style="margin:0 209px; position: absolute;"/>
        </td>
    </tr>
    <td colspan="2">
        <span class="nohelp">&nbsp;</span>
        <input style="width:509px;" type=text name="pishnehad.addressBimeGozar.neshaniMahalleKar" value="${pishnehad.addressBimeGozar.neshaniMahalleKar}" id="addressBimeGozar_neshaniMahalleKar"
               class="validate[required,custom[address]] text-input"/>&nbsp;<label>نشانی محل کار</label>
    </td>
    </tr>
    <tr>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text name="pishnehad.addressBimeGozar.kodePostiMahallekar" value="${pishnehad.addressBimeGozar.kodePostiMahallekar}" id="addressBimeGozar_kodePostiMahallekar"
                   class="validate[custom[code_posti]]  text-input"/>&nbsp;<label>كدپستي محل كار</label>
        </td>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text name="pishnehad.addressBimeGozar.codeTelephoneMahalleKar" value="${pishnehad.addressBimeGozar.codeTelephoneMahalleKar}" id="addressBimeGozar_codeTelephoneMahalleKar" style="width:35px"
                   class="validate[required,custom[integer],custom[code_shahri]] text-input"/>&nbsp;
            <input type=text name="pishnehad.addressBimeGozar.telephoneMahalleKar" value="${pishnehad.addressBimeGozar.telephoneMahalleKar}" id="addressBimeGozar_telephoneMahalleKar" style="width:96px"
                   class="validate[custom[integer]] text-input"/>&nbsp;<label>تلفن محل کار</label>
        </td>
    </tr>
    <%--End address--%>
    <tr>
        <td>
            <span class="nohelp">&nbsp;</span>
            <select name="pishnehad.bimeGozar.nesbatBabimeShode" id="bimeGozar_nesbatBaBimeShode" class="">
                <c:set var="nesbatBaBimeShode" value="<%=ConstantForPishnehadForm.ConstantItemKey.NESBAT_BA_BIME_SHODE%>"/>
                <c:forEach var="row" items="${pishnehadConstants.constantForPishnehadFormList}">
                    <c:if test="${row.constantItemKey == nesbatBaBimeShode}">
                        <option value="${row.constantItemValue}"
                                <c:if test="${row.constantItemValue == pishnehad.bimeGozar.nesbatBabimeShode}">selected="selected"</c:if>
                                >
                                ${row.constantItemValue}
                        </option>
                    </c:if>
                </c:forEach>
            </select>&nbsp;<label>نسبت با بیمه
            شده</label>
        </td>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type="text" name="pishnehad.bimeGozar.daramadeMahiane" id="bimeGozar_daramdeMahiane"
                   class="validate[required,custom[long],funcCall[dastmozd-nonzero]] text-input digitSeparators"
                   value='${pishnehad.bimeGozar.daramadeMahiane}'/>&nbsp;<label>درآمد ماهیانه (ريال)</label>
        </td>
    </tr>
</table>

<script type="text/javascript">

    function showBimegozarDetails(){
        $("#msgErr_div ul").html("");
        var divToBePop = '';
        var whichForm = '';
        var bimeGozarShakhsId = $('#bimeGozar_shakhs_id').val()
        var myElem = document.getElementById('newShakhsBimeGozar');
        <c:if test="${isHalateElhaghie}">
            if (myElem != null)
                bimeGozarShakhsId = $("#newShakhsBimeGozar").val();
        </c:if>
        $("#shakhs_id").val(bimeGozarShakhsId);
        $.post('/fillShakhs.action?shakhsId=' + bimeGozarShakhsId,function(msg){
            if(msg.split(',')[25] == '<%=Shakhs.BimeGozarType.HOGHOGHI%>'){
                divToBePop = 'shakhsHoghoghiElements';
                $("#shakhshoghughi_id").val($("#bimeGozar_shakhs_id").val());
                $("#shakhsHoghoghiName").val(msg.split(',')[6]);
                $("#shakhsHoghoghiPishvand").val(msg.split(',')[26]);
                $("#shakhsDolatiKhososi").val(msg.split(',')[23]);
                $("#shakhsNoeFaaliat").val(msg.split(',')[24]);
                $("#shakhsKodeEghtesadi").val(msg.split(',')[11]);
                $("#shakhsShomareSabt").val(msg.split(',')[9]);
                $("#shakhsTarikheSabt").val(msg.split(',')[13]);
                $("#shakhsMahalleSabt_id").val(msg.split(',')[15]);
                $('#shakhsMahalleSabt').val(msg.split(',')[29]);
                whichForm = 'shakhsHoghoghiForm';
            }else{
                divToBePop = "shakhsElements";
                $("#shakhshaghighi_id").val($("#bimeGozar_shakhs_id").val());
                $('#shakhsName').val(msg.split(',')[6]);
//                $('#shakhsHoghoghiName').val(msg.split(',')[6]);
                $('#shakhsNameKhanevadegi').val(msg.split(',')[7]);
                $('#shakhsShomareShenasnameh').val(msg.split(',')[8]);
//                $('#shakhsShomareSabt').val(msg.split(',')[9]);
                $('#shakhsKodeMelliShenasayi').val(msg.split(',')[10]);
//                $('#shakhsKodeEghtesadi').val(msg.split(',')[11]);
                $('#shakhs_tarikheTavallod').val(msg.split(',')[12]);
//                $('#shakhsTarikheSabt').val(msg.split(',')[13]);
                $('#shakhsMahalleTavallod_id').val(msg.split(',')[14]);
                $('#shakhsMahalleTavallod').val(msg.split(',')[27]);
                $('#shakhsMahalleSodoreShenasnameh_id').val(msg.split(',')[15]);
                $('#shakhsMahalleSodoreShenasnameh').val(msg.split(',')[29]);
//                $('#shakhsMahalleSabt').val(msg.split(',')[15]);
                $('#shakhsNamePedar').val(msg.split(',')[16]);
                if(msg.split(',')[17] == 'مرد'){
                    $('#ASF_jensiat_j').removeAttr('checked');
                    $('#ASF_jensiat_i').attr('checked',true).change();
                }else{
                    $('#ASF_jensiat_i').removeAttr('checked');
                    $('#ASF_jensiat_j').attr('checked',true).change();
                }
                if(msg.split(',')[18] == 'mojarrad'){
                    $('#ASF_vaziateTaahol_j').removeAttr('checked');
                    $('#ASF_vaziateTaahol_i').attr('checked',true).change();
                }else{
                    $('#ASF_vaziateTaahol_i').removeAttr('checked');
                    $('#ASF_vaziateTaahol_j').attr('checked',true).change();
                }
                if(msg.split(',')[20] == '<%=Shakhs.Melliat.IRANI.toString()%>'){
                    $('#ASF_iraniOrAtbaeKhareji_j').removeAttr('checked');
                    $('#ASF_iraniOrAtbaeKhareji_i').attr('checked',true).change();
                }else{
                    $('#ASF_iraniOrAtbaeKhareji_i').removeAttr('checked');
                    $('#ASF_iraniOrAtbaeKhareji_j').attr('checked',true).change();
                }
//                $('#ASF_iraniOrAtbaeKhareji_i').val(msg.split(',')[20]);
                $('#shakhsShoghleAsli').val(msg.split(',')[21]);
                $('#shoghleFarei').val(msg.split(',')[22]);
//                $('#shakhsDolatiKhososi').val(msg.split(',')[23]);
//                $('#shakhsNoeFaaliat').val(msg.split(',')[24]);
                $('#shakhsPishvand').val(msg.split(',')[26]);
                whichForm = "shakhsForm";
            }$("#"+whichForm).append($('<input/>').attr('type','hidden').attr('value','${isHalateElhaghie}').attr('name','isHalateElhaghie'));
            var input=document.createElement('input');
            input.type='hidden';
            input.name= 'nesbatBaBimeShode';//window.alert($("#bimeGozar_nesbatBaBimeShode").val());
            input.value=$("#bimeGozar_nesbatBaBimeShode").val();  //window.alert($(input).val());
            $("#"+whichForm).append(input);
            $('#' + divToBePop).dialog({
                modal:true,
                width: 640,
                resizable:false,
                closeText: "",
                title:"مشاهده و ویرایش اطلاعات بیمه گذار",
                close:function() {
                    $('#shakhsForm')[0].reset();
                    $('#'+divToBePop+' #msgErr_div').html('');
                    $.validationEngine.closePrompt('.formError', true);
                }
                <c:if test="${(pishnehad == null || pishnehad.id == null || pishnehad.state.id == 10 || pishnehad.state.id == 20 || pishnehad.state.id == 50) && bimeGozarEditable ||
                (isHalateElhaghie && isEditBimeGozar) || (darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050)}">
                ,buttons: {
                    "انصراف": function() {
                        $(this).dialog("close");
                    },
                    "ثبت": function() {
                        var elementExists = document.getElementById("newShakhsBimeGozarId_Id");
                        if (elementExists == null)
                        {
                        $.validationEngine.onSubmitValid = true;
                        if ($.validationEngine.submitValidation($('#'+divToBePop)) === false) {
                        var formInfo = $("#"+whichForm).serialize();
                        $("#shakhshaghighi_id").val($("#bimeGozar_shakhs_id").val());
                        msg = "با تغيير مشخصات اين فرد، مشخصات بيمه گذار/بيمه شده در پيشنهادهاي قبلي نيز تغيير خواهد يافت. آيا مطمئنيد؟";
                            <c:if test="${(isHalateElhaghie && isEditBimeGozar) || (darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050)}">
                                msg = "در صورت تمایل به تغییر شخص به شخصی جدید از کلیدهای جدید و جستجو استفاده نمایید." ;
                                msg += " " + "آیا از تغییر اطلاعات فرد مطمئنید ؟";
                            </c:if>
                        confirmMessage(msg, "تایید", function(){

                            $.post('/editShakhs.action', formInfo, function(msg) {
                            if(msg.indexOf('SUCCESS') != -1){
                                $('#newShakhsBimeGozarId_Id').remove();
                                $("#result_edit_shakhsBimeGozar").html(msg);
                                <c:if test="${isHalateElhaghie}">
                                $("#name_bimeGozar").val($("#namonamekhanevadegishakhs_Gozar").val());
                                </c:if>
                                <c:if test="${!isHalateElhaghie}">
                                    $("#name_bimeGozar").val($("#namonamekhanevadegishakhs").val());
                                </c:if>
                                $('#bimeGozar_shakhs_tarikheTavallod').val($("editshakhs_tarikheTavallod").val());
                                <c:if test="${darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050}">
                                alertMessageByLock($("#editsuccessmessage").val(), reloadPage);
                                </c:if>
                                <c:if test="${!(darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050)}">
                                alertMessage($("#editsuccessmessage").val());
                                </c:if>
                                $('#'+divToBePop).dialog('close');
                            }else{
                                $('#'+divToBePop+' #msgErr_div').html(msg);
                            }
                            });
                        });
                            <c:if test="${darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050}">
                            location.reload(true);
                            </c:if>
                     }
                    }
                    }
                }

                </c:if>
            });
        });

    }
</script>
