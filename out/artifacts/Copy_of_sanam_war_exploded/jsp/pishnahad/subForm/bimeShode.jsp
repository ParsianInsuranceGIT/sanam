<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 4/7/11
  Time: 2:21 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function() {
        $('#tarikh_tavalod').val($('#bimeShode_shakhs_tarikheTavallod').val());
        calculateSen();
    });

    function fill_bimeShode(msg){
        if($('#bimeShode_shakhs_id').val() != msg.split(',')[1]){
            $('#addressBimeShode_ostaanManzel').val('');
            $('#addressBimeShode_shahrManzel').val('');
            $('#addressBimeShode_neshaniManze').val('');
            $('#addressBimeShode_kodePostiManzel').val('');
            $('#addressBimeShode_codeTelephoneManzel').val('');
            $('#addressBimeShode_telephoneManzel').val('');
            $('#addressBimeShode_telephoneHamrah').val('');
            $('#addressBimeShode_ostaanMahalleKar').val('');
            $('#addressBimeShode_shahrMahalleKar').val('');
            $('#addressBimeShode_neshaniMahalleKar').val('');
            $('#addressBimeShode_kodePostiMahallekar').val('');
            $('#addressBimeShode_codeTelephoneMahalleKar').val('');
            $('#addressBimeShode_telephoneMahalleKar').val('');
            $('#addressBimeShode_posteElectronic').val('');
        }
        $('#bimeShode_shakhs_id').val(msg.split(',')[1]);
        $('#name_bimeShode').val(msg.split(',')[2]);
        $('#bimeShode_shakhs_tarikheTavallod').val(msg.split(',')[3]);
        $('#bimeShode_shakhs_kodemelli').val(msg.split(',')[10]);
        $('#tarikh_tavalod').val(msg.split(',')[3]);
        $('#editBimeshodeshakhsName').val(msg.split(',')[6]);
            $('#editBimeshodeshakhsNameKhanevadegi').val(msg.split(',')[7]);
            $('#editBimeshodeshakhsShomareShenasnameh').val(msg.split(',')[8]);
            $('#editBimeshodeshakhsKodeMelliShenasayi').val(msg.split(',')[10]);
            $('#editBimeshodeshakhs_tarikheTavallod').val(msg.split(',')[12]);
            $('#editBimeshodeshakhsMahalleTavallod_id').val(msg.split(',')[14]);
            $('#editBimeshodeshakhsMahalleTavallod').val(msg.split(',')[27]);
            $('#editBimeshodeshakhsMahalleSodoreShenasnameh_id').val(msg.split(',')[15]);
            $('#editBimeshodeshakhsMahalleSodoreShenasnameh').val(msg.split(',')[29]);
            $('#editBimeshodeshakhsNamePedar').val(msg.split(',')[16]);
            if(msg.split(',')[17] == 'مرد'){
                $('#editBimeshodeASF_jensiat_j').removeAttr('checked');
                $('#editBimeshodeASF_jensiat_i').attr('checked',true).change();
            }else{
                $('#editBimeshodeASF_jensiat_i').removeAttr('checked');
                $('#editBimeshodeASF_jensiat_j').attr('checked',true).change();
            }
            if(msg.split(',')[18] == 'mojarrad'){
                $('#editBimeshodeASF_vaziateTaahol_j').removeAttr('checked');
                $('#editBimeshodeASF_vaziateTaahol_i').attr('checked',true).change();
            }else{
                $('#editBimeshodeASF_vaziateTaahol_i').removeAttr('checked');
                $('#editBimeshodeASF_vaziateTaahol_j').attr('checked',true).change();
            }
            if(msg.split(',')[20] == '<%=Shakhs.Melliat.IRANI.toString()%>'){
                $('#editBimeshodeASF_iraniOrAtbaeKhareji_j').removeAttr('checked');
                $('#editBimeshodeASF_iraniOrAtbaeKhareji_i').attr('checked',true).change();
            }else{
                $('#editBimeshodeASF_iraniOrAtbaeKhareji_i').removeAttr('checked');
                $('#editBimeshodeASF_iraniOrAtbaeKhareji_j').attr('checked',true).change();
            }
            $('#editBimeshodeshakhsShoghleAsli').val(msg.split(',')[21]);
            $('#editBimeshodeshoghleFarei').val(msg.split(',')[22]);
            $('#editBimeshodeshakhsPishvand').val(msg.split(',')[26]);

        calculateSen();
    }
    function fillShakhsBimeShode() {
        $("#bimeGozar_nesbatBaBimeShode").addClass("validate[funcCall[val_1_bimeGozar_nesbatBaBimeShode]]");
        <sec:authorize ifAllGranted="ROLE_SUPERVISOR">
        openJosteju(false, function(id){
            $.post('/fillShakhs.action?shakhsId=' + id, function(msg) {
                fill_bimeShode(msg);
            });
        })
        </sec:authorize>
        <sec:authorize ifNotGranted="ROLE_SUPERVISOR">
        openJosteju(true, function(id){
            $.post('/fillShakhs.action?shakhsId=' + id, function(msg) {
                fill_bimeShode(msg);
            });
        })
        </sec:authorize>

    }
    function addToBimeShode(){
        $("#bimeGozar_nesbatBaBimeShode").addClass("validate[funcCall[val_1_bimeGozar_nesbatBaBimeShode]]");
        openAddShakhsDialog(function(msg){
            fill_bimeShode(msg);
        })
    }
    function copyFromBimeGozar(){
//        if($('#bimeGozar_shakhs_kodemelli').val().length < 10) return;
        $.post("/isBimeShodeValid.action?code_melli=" + $('#bimeGozar_shakhs_kodemelli').val(), function(data){
            if(data.indexOf("NO") != -1)
            {
                confirmMessage('آیا از کپی اطلاعات شخص بیمه گذار به بیمه شده مطمئن هستید؟','تایید کپی',function(){
            $("#bimeGozar_nesbatBaBimeShode").addClass("validate[funcCall[val_1_bimeGozar_nesbatBaBimeShode]]");
            $('#bimeShode_shakhs_id').val($('#bimeGozar_shakhs_id').val());
            $('#name_bimeShode').val($('#name_bimeGozar').val());
            $('#bimeShode_shakhs_tarikheTavallod').val($('#bimeGozar_shakhs_tarikheTavallod').val());
            $('#bimeShode_shakhs_jensiat').val($('#bimeGozar_shakhs_jensiat').val());
            $('#bimeShode_shakhs_kodemelli').val($('#bimeGozar_shakhs_kodemelli').val());
            $('#tarikh_tavalod').val($('#bimeGozar_shakhs_tarikheTavallod').val());
            calculateSen();
            $('#addressBimeShode_id').val($('#addressBimeGozar_id').val());
            $('#bimeShode_OstanZendegi').val($('#bimeGozar_OstanZendegi').val());
            $('#bimeShode_OstanZendegiId').val($('#bimeGozar_OstanZendegiId').val());
            $('#bimeShode_ShahrZendegi').val($('#bimeGozar_ShahrZendegi').val());
            $('#bimeShode_ShahrZendegiId').val($('#bimeGozar_ShahrZendegiId').val());
            $('#bimeShode_OstanKar').val($('#bimeGozar_OstanKar').val());
            $('#bimeShode_OstanKarId').val($('#bimeGozar_OstanKarId').val());
            $('#bimeShode_ShahrKar').val($('#bimeGozar_ShahrKar').val());
            $('#bimeShode_ShahrKarId').val($('#bimeGozar_ShahrKarId').val());
            $('#addressBimeShode_neshaniManze').val($('#addressBimeGozar_neshaniManze').val());
            $('#addressBimeShode_kodePostiManzel').val($('#addressBimeGozar_kodePostiManzel').val());
            $('#addressBimeShode_codeTelephoneManzel').val($('#addressBimeGozar_codeTelephoneManzel').val());
            $('#addressBimeShode_telephoneManzel').val($('#addressBimeGozar_telephoneManzel').val());
            $('#addressBimeShode_telephoneHamrah').val($('#addressBimeGozar_telephoneHamrah').val());
            $('#addressBimeShode_neshaniMahalleKar').val($('#addressBimeGozar_neshaniMahalleKar').val());
            $('#addressBimeShode_kodePostiMahallekar').val($('#addressBimeGozar_kodePostiMahallekar').val());
            $('#addressBimeShode_codeTelephoneMahalleKar').val($('#addressBimeGozar_codeTelephoneMahalleKar').val());
            $('#addressBimeShode_telephoneMahalleKar').val($('#addressBimeGozar_telephoneMahalleKar').val());
            $('#addressBimeShode_posteElectronic').val($('#addressBimeGozar_posteElectronic').val());
            $.post('/fillShakhs.action?shakhsId=' + $('#bimeShode_shakhs_id').val(),function(msg){
            $('#editBimeshodeshakhsName').val(msg.split(',')[6]);
            $('#editBimeshodeshakhsNameKhanevadegi').val(msg.split(',')[7]);
            $('#editBimeshodeshakhsShomareShenasnameh').val(msg.split(',')[8]);
            //            $('#EKASB_shomareSabt').val(msg.split(',')[9]);
            $('#editBimeshodeshakhsKodeMelliShenasayi').val(msg.split(',')[10]);
            //            $('#EKASB_kodeEghtesadi').val(msg.split(',')[11]);
            $('#editBimeshodeshakhs_tarikheTavallod').val(msg.split(',')[12]);
            //            $('#EKASB_tarikhSabt').val(msg.split(',')[13]);
            $('#editBimeshodeshakhsMahalleTavallod_id').val(msg.split(',')[14]);
            $('#editBimeshodeshakhsMahalleTavallod').val(msg.split(',')[27]);
            $('#editBimeshodeshakhsMahalleSodoreShenasnameh_id').val(msg.split(',')[15]);
            $('#editBimeshodeshakhsMahalleSodoreShenasnameh').val(msg.split(',')[29]);
            $('#editBimeshodeshakhsNamePedar').val(msg.split(',')[16]);
            if(msg.split(',')[17] == 'مرد'){
                $('#editBimeshodeASF_jensiat_j').removeAttr('checked');
                $('#editBimeshodeASF_jensiat_i').attr('checked',true).change();
            }else{
                $('#editBimeshodeASF_jensiat_i').removeAttr('checked');
                $('#editBimeshodeASF_jensiat_j').attr('checked',true).change();
            }
            if(msg.split(',')[18] == 'mojarrad'){
                $('#editBimeshodeASF_vaziateTaahol_j').removeAttr('checked');
                $('#editBimeshodeASF_vaziateTaahol_i').attr('checked',true).change();
            }else{
                $('#editBimeshodeASF_vaziateTaahol_i').removeAttr('checked');
                $('#editBimeshodeASF_vaziateTaahol_j').attr('checked',true).change();
            }
            if(msg.split(',')[20] == '<%=Shakhs.Melliat.IRANI.toString()%>'){
                $('#editBimeshodeASF_iraniOrAtbaeKhareji_j').removeAttr('checked');
                $('#editBimeshodeASF_iraniOrAtbaeKhareji_i').attr('checked',true).change();
                $('#shakhs_code_melli_shenaasaee_lbl').html('کد ملی');
                $('#editBimeshodeshakhsKodeMelliShenasayi').removeClass('validate[required,custom[onlyNumber],] text-input');
                $('#editBimeshodeshakhsKodeMelliShenasayi').addClass('validate[required,custom[onlyNumber],funcCall[code_melli_shenasayi]] text-input');
            }else{

                $('#editBimeshodeASF_iraniOrAtbaeKhareji_i').removeAttr('checked');
                $('#editBimeshodeASF_iraniOrAtbaeKhareji_j').attr('checked',true).change();
                $('#shakhs_code_melli_shenaasaee_lbl').html('کد شناسایی');
                $('#editBimeshodeshakhsKodeMelliShenasayi').removeClass('validate[required,custom[onlyNumber],funcCall[code_melli_shenasayi]] text-input');
                $('#editBimeshodeshakhsKodeMelliShenasayi').addClass('validate[required,custom[onlyNumber],] text-input');
            }
            $('#editBimeshodeshakhsShoghleAsli').val(msg.split(',')[21]);
            $('#editBimeshodeshoghleFarei').val(msg.split(',')[22]);
            $('#editBimeshodeshakhsPishvand').val(msg.split(',')[26]);
            });
            $.validationEngine.closePrompt('.formError', true);
        });
            }
            else
            {
                alertMessage("بیمه شده تایید شده نمی باشد.");
            }
        });
    }
</script>
<div style="display:none;" id="bimeshode_haghighi_details_div">
    <%@include file="/jsp/pishnahad/dialogForm/editShakhsBimeShodeForm.jsp"%>
</div>
<div id="result_edit_shakhsBimeShode">

</div>
<input type="hidden" id="bimeShode_id" name="pishnehad.bimeShode.id" value="${pishnehad.bimeShode.id}">
<input type="hidden" id="bimeShode_shakhs_id" name="pishnehad.bimeShode.shakhs.id" value="${pishnehad.bimeShode.shakhs.id}">
<input type="hidden" id="bimeShode_shakhs_tarikheTavallod" value="${pishnehad.bimeShode.shakhs.tarikheTavallod}">
<input type="hidden" id="bimeShode_shakhs_jensiat" value="${pishnehad.bimeShode.shakhs.jensiat}">
<input type="hidden" id="bimeShode_shakhs_kodemelli" value="${pishnehad.bimeShode.shakhs.kodeMelliShenasayi}">
<table class="inputList" border="0" cellspacing="5" cellpadding="1">
    <tr>
        <td style="color: red;">
            <ul><li>
            براي تغيير بيمه‌شده فقط از دكمه هاي جديد يا جستجو استفاده شود.
                </li></ul>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <span class="noThing"></span>

            <input type="button" id="namayesheEtelaateFard2" value="نمایش اطلاعات فرد" onclick="showBimeshodeDetails();"/>
            <c:if test="${!isHalateElhaghie && darkhastTaghirat == null}">
            <input type="button" value="+ جدید" onclick="addToBimeShode();"/>
            <c:if test="${pishnehad.bimeGozar.shakhs.type=='HAGHIGHI'|| pishnehad == null || pishnehad.id == null}">
                <input type="button" id="copyazbimegozarbutton" value="کپی از بیمه گذار" onclick="copyFromBimeGozar();" style="margin:0 0 0 2px"/>
            </c:if>
            <c:if test="${pishnehad.bimeGozar.shakhs.type=='HOGHOGHI'}">
                <input type="button" id="copyazbimegozarbutton" value="کپی از بیمه گذار" onclick="copyFromBimeGozar();" style="margin:0 0 0 2px;display: none;"/>
            </c:if>

            <input type="button" value="جستجو" onclick="fillShakhsBimeShode();" style="margin:0 2px"/>
            </c:if>
            <input type="text" id="name_bimeShode" class="validate[required] text-input"
            <c:if test="${pishnehad.bimeShode.shakhs.name != null || pishnehad.bimeShode.shakhs.nameKhanevadegi != null}">
                value="${pishnehad.bimeShode.shakhs.name} ${pishnehad.bimeShode.shakhs.nameKhanevadegi}"
            </c:if>
            style="width:312px; margin-right: 3px;" readonly="readonly"/>
            <label for="name_bimeShode">بیمه شده </label>
        </td>
    </tr>

    <%--address--%>
    <input type="hidden" name="pishnehad.addressBimeShode.id" value="${pishnehad.addressBimeShode.id}" id="addressBimeShode_id"/>
    <tr>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text value="${pishnehad.addressBimeShode.ostaanManzel.cityName}" id="bimeShode_OstanZendegi" class="validate[required]" readonly="readonly"/>&nbsp;<label>استان</label>
            <input type=hidden name="pishnehad.addressBimeShode.ostaanManzel.cityId" value="${pishnehad.addressBimeShode.ostaanManzel.cityId}" id="bimeShode_OstanZendegiId"/>
        </td>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text value="${pishnehad.addressBimeShode.shahrManzel.cityName}" id="bimeShode_ShahrZendegi" class="validate[required]" readonly="readonly"/>&nbsp;<label>شهر</label>
            <input type=hidden name="pishnehad.addressBimeShode.shahrManzel.cityId" value="${pishnehad.addressBimeShode.shahrManzel.cityId}" id="bimeShode_ShahrZendegiId"/>
            <input type=button value="انتخاب" onclick="fillShahrOstan('bimeShode_ShahrZendegiId','bimeShode_ShahrZendegi','bimeShode_OstanZendegiId','bimeShode_OstanZendegi','addressBimeShode_neshaniManze');" style="margin:0 230px; position: absolute;"/>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <span class="nohelp">&nbsp;</span>
            <input style="width:513px;" type=text name="pishnehad.addressBimeShode.neshaniManzel" value="${pishnehad.addressBimeShode.neshaniManzel}" id="addressBimeShode_neshaniManze"
                   class="validate[required,custom[address]] text-input"/>&nbsp;<label>نشانی
            منزل</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text name="pishnehad.addressBimeShode.kodePostiManzel" value="${pishnehad.addressBimeShode.kodePostiManzel}" id="addressBimeShode_kodePostiManzel"
                   class="validate[custom[code_posti]] text-input"/>&nbsp;<label>كدپستي منزل</label>
        </td>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text name="pishnehad.addressBimeShode.posteElectronic" value="${pishnehad.addressBimeShode.posteElectronic}" id="addressBimeShode_posteElectronic"
                   class="validate[custom[email]] text-input"/>&nbsp;<label>پست الکترونیک</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text name="pishnehad.addressBimeShode.codeTelephoneManzel" value="${pishnehad.addressBimeShode.codeTelephoneManzel}" id="addressBimeShode_codeTelephoneManzel" style="width:35px"
                   class="validate[required,custom[integer],custom[code_shahri]] text-input"/>&nbsp;
            <input type=text name="pishnehad.addressBimeShode.telephoneManzel" value="${pishnehad.addressBimeShode.telephoneManzel}" id="addressBimeShode_telephoneManzel" style="width:96px"
                   class="validate[required,custom[integer]] text-input"/>&nbsp;<label>تلفن منزل</label></td>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text name="pishnehad.addressBimeShode.telephoneHamrah" value="${pishnehad.addressBimeShode.telephoneHamrah}" id="addressBimeShode_telephoneHamrah"
                   class="validate[custom[telephone_hamraah]] text-input"/>&nbsp;<label>تلفن همراه
            (ضروری)</label></td>
    </tr>
    <tr>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text value="${pishnehad.addressBimeShode.ostaanMahalleKar.cityName}" id="bimeShode_OstanKar" class="validate[required]" readonly="readonly"/>&nbsp;<label>استان محل کار</label>
            <input type=hidden name="pishnehad.addressBimeShode.ostaanMahalleKar.cityId" value="${pishnehad.addressBimeShode.ostaanMahalleKar.cityId}" id="bimeShode_OstanKarId"/>
        </td>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text value="${pishnehad.addressBimeShode.shahrMahalleKar.cityName}" id="bimeShode_ShahrKar" class="validate[required]" readonly="readonly"/>&nbsp;<label>شهر محل کار</label>
            <input type=hidden name="pishnehad.addressBimeShode.shahrMahalleKar.cityId" value="${pishnehad.addressBimeShode.shahrMahalleKar.cityId}" id="bimeShode_ShahrKarId"/>
            <input type=button value="انتخاب" onclick="fillShahrOstan('bimeShode_ShahrKarId','bimeShode_ShahrKar','bimeShode_OstanKarId','bimeShode_OstanKar','addressBimeShode_neshaniMahalleKar');" style="margin:0 209px; position: absolute;"/>
        </td>
    </tr>
    <td colspan="2">
        <span class="nohelp">&nbsp;</span>
        <input style="width:513px;" type=text name="pishnehad.addressBimeShode.neshaniMahalleKar" value="${pishnehad.addressBimeShode.neshaniMahalleKar}" id="addressBimeShode_neshaniMahalleKar"
               class="validate[required,custom[address]] text-input"/>&nbsp;<label>نشانی محل کار</label>
    </td>
    </tr>
    <tr>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text name="pishnehad.addressBimeShode.kodePostiMahallekar" value="${pishnehad.addressBimeShode.kodePostiMahallekar}" id="addressBimeShode_kodePostiMahallekar"
                   class="validate[custom[code_posti]] text-input"/>&nbsp;<label>كدپستي محل كار</label>
        </td>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type=text name="pishnehad.addressBimeShode.codeTelephoneMahalleKar" value="${pishnehad.addressBimeShode.codeTelephoneMahalleKar}" id="addressBimeShode_codeTelephoneMahalleKar" style="width:35px"
                   class="validate[required,custom[integer],custom[code_shahri]] text-input"/>&nbsp;
            <input type=text name="pishnehad.addressBimeShode.telephoneMahalleKar" value="${pishnehad.addressBimeShode.telephoneMahalleKar}" id="addressBimeShode_telephoneMahalleKar" style="width:96px"
                   class="validate[custom[integer]] text-input"/>&nbsp;<label>تلفن محل کار</label>
        </td>
    </tr>
    <%--End address--%>
</table>
<script type="text/javascript">
    function showBimeshodeDetails(){
         var bimeShodeShakhsId= $('#bimeShode_shakhs_id').val();
         var myElem = document.getElementById('newShakhsBimeShode');
         <c:if test="${isHalateElhaghie}">
            if(myElem!=null)
                bimeShodeShakhsId=$("#newShakhsBimeShode").val()
         </c:if>
        $("#shakhs_bimeshode_id").val(bimeShodeShakhsId);
        $.post('/fillShakhs.action?shakhsId=' + bimeShodeShakhsId,function(msg){
            $('#editBimeshodeshakhsName').val(msg.split(',')[6]);
            $('#editBimeshodeshakhsNameKhanevadegi').val(msg.split(',')[7]);
            $('#editBimeshodeshakhsShomareShenasnameh').val(msg.split(',')[8]);
            //            $('#EKASB_shomareSabt').val(msg.split(',')[9]);
            $('#editBimeshodeshakhsKodeMelliShenasayi').val(msg.split(',')[10]);
            //            $('#EKASB_kodeEghtesadi').val(msg.split(',')[11]);
            $('#editBimeshodeshakhs_tarikheTavallod').val(msg.split(',')[12]);
            //            $('#EKASB_tarikhSabt').val(msg.split(',')[13]);
            $('#editBimeshodeshakhsMahalleTavallod_id').val(msg.split(',')[14]);
            $('#editBimeshodeshakhsMahalleTavallod').val(msg.split(',')[27]);
            $('#editBimeshodeshakhsMahalleSodoreShenasnameh_id').val(msg.split(',')[15]);
            $('#editBimeshodeshakhsMahalleSodoreShenasnameh').val(msg.split(',')[29]);
            $('#editBimeshodeshakhsNamePedar').val(msg.split(',')[16]);
            if(msg.split(',')[17] == 'مرد'){
                $('#editBimeshodeASF_jensiat_j').removeAttr('checked');
                $('#editBimeshodeASF_jensiat_i').attr('checked',true).change();
            }else{
                $('#editBimeshodeASF_jensiat_i').removeAttr('checked');
                $('#editBimeshodeASF_jensiat_j').attr('checked',true).change();
            }
            if(msg.split(',')[18] == 'mojarrad'){
                $('#editBimeshodeASF_vaziateTaahol_j').removeAttr('checked');
                $('#editBimeshodeASF_vaziateTaahol_i').attr('checked',true).change();
            }else{
                $('#editBimeshodeASF_vaziateTaahol_i').removeAttr('checked');
                $('#editBimeshodeASF_vaziateTaahol_j').attr('checked',true).change();
            }
            if(msg.split(',')[20] == '<%=Shakhs.Melliat.IRANI.toString()%>'){
                $('#editBimeshodeASF_iraniOrAtbaeKhareji_j').removeAttr('checked');
                $('#editBimeshodeASF_iraniOrAtbaeKhareji_i').attr('checked',true).change();
            }else{
                $('#editBimeshodeASF_iraniOrAtbaeKhareji_i').removeAttr('checked');
                $('#editBimeshodeASF_iraniOrAtbaeKhareji_j').attr('checked',true).change();
            }
            $('#editBimeshodeshakhsShoghleAsli').val(msg.split(',')[21]);
            $('#editBimeshodeshoghleFarei').val(msg.split(',')[22]);
            $('#editBimeshodeshakhsPishvand').val(msg.split(',')[26]);
            $('#bimeshode_haghighi_details_div').dialog({
                modal:true,
                width: 640,
                resizable:false,
                closeText: "",
                title:"مشاهده و ویرایش اطلاعات بیمه شده",
                close:function() {
                    $('#shakhsForm')[0].reset();
                    $('#bimeshode_haghighi_details_div #msgErr_div').html('');
                    $.validationEngine.closePrompt('.formError', true);
                }
                <c:if test="${(pishnehad == null || pishnehad.id == null || pishnehad.state.id == 10 || pishnehad.state.id == 20 || pishnehad.state.id == 50) && bimeShodeEditable
                ||(isHalateElhaghie && isEditBimeShode) || (darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050)}">
                ,buttons: {
                    "انصراف": function() {
                        $(this).dialog("close");
                    },
                    "ثبت": function() {
                        $.validationEngine.onSubmitValid = true;
                        if ($.validationEngine.submitValidation($('#bimeshode_haghighi_details_div')) === false) {
                        var input=document.createElement('input');
                        input.type='hidden';
                        input.name= 'nesbatBaBimeShode';//window.alert($("#bimeGozar_nesbatBaBimeShode").val());
                        input.value=$("#bimeGozar_nesbatBaBimeShode").val();  //window.alert($(input).val());
                        $("#editBimeshodeshakhsForm").append(input);
                        var formInfo = $("#editBimeshodeshakhsForm").serialize();
                        msg = "با تغيير مشخصات اين فرد، مشخصات بيمه گذار/بيمه شده در پيشنهادهاي قبلي نيز تغيير خواهد يافت. آيا مطمئنيد؟";
                        <c:if test="${(isHalateElhaghie && isEditBimeShode) || (darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050)}">
                            msg = "آیا از تغییر اطلاعات فرد مطمئنید ؟";
                        </c:if>
                        confirmMessage(msg, "تایید", function(){
                            $.post('/editShakhs.action', formInfo, function(msg) {
                            if(msg.indexOf('SUCCESS') != -1){
                                $("#result_edit_shakhsBimeShode").html(msg);
                                <c:if test="${isHalateElhaghie}">
                                    $("#name_bimeShode").val($("#namonamekhanevadegishakhs_Shode").val());
                                </c:if>
                                <c:if test="${!isHalateElhaghie}">
                                    $("#name_bimeShode").val($("#namonamekhanevadegishakhs").val());
                                </c:if>
                                <c:if test="${darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050}">
                                alertMessageByLock($("#editsuccessmessage").val(), reloadPage);
                                </c:if>
                                <c:if test="${!(darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050)}">
                                alertMessage($("#editsuccessmessage").val());
                                </c:if>
                                $('#bimeshode_haghighi_details_div').dialog('close');
                            }else{
                                $('#bimeshode_haghighi_details_div #msgErr_div').html(msg);
                            }
                        });
                        });
                     }
                    }
                }
                    </c:if>
            });
        });
    }
</script>
