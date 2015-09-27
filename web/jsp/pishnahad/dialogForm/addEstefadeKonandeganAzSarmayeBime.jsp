<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 6/8/11
  Time: 6:54 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript">

    var EKASB_nesbatBabimeShode,EKASB_nameKhanevadegi,EKASB_shomareShenasname,EKASB_shomareSabt,EKASB_kodeMelli,EKASB_kodeEghtesadi,EKASB_tarikhTavallod,EKASB_tarikhSabt,EKASB_mahalleTavallod,EKASB_mahalleSabt,EKASB_namePedar,EKASB_mahalleSodoorShenasnameh,EKASB_jensiat;

    function chg_EKASB_noeZiNafea(){
        if($('#EKASB_noeZiNafea').val() == 'حقیقی'){
//            enableItem('EKASB_nesbatBabimeShode',EKASB_nesbatBabimeShode);
            enableItem('EKASB_nameKhanevadegi',null);
            enableItem('EKASB_jensiat',null);
            enableItem('EKASB_shomareShenasname',null);
            enableItem('EKASB_kodeMelli',null);
            enableItem('EKASB_tarikhTavallod',null);
            enableItem('EKASB_mahalleTavallod',null);
            enableItem('entekhabTavallodButton',null);
            enableItem('entekhabSodorButton',null);
            enableItem('EKASB_mahalleSodoorShenasnameh',null);
            enableItem('EKASB_namePedar',null);
            $('#tabeiat').show();
            EKASB_shomareSabt = disableItem('EKASB_shomareSabt');
            EKASB_kodeEghtesadi = disableItem('EKASB_kodeEghtesadi');
            EKASB_tarikhSabt = disableItem('EKASB_tarikhSabt');
            EKASB_mahalleSabt = disableItem('EKASB_mahalleSabt');
            $('#entekhabSabtButton').val(disableItem('entekhabSabtButton'));
        }else{
//            EKASB_nesbatBabimeShode = disableItem('EKASB_nesbatBabimeShode');
            EKASB_nameKhanevadegi = disableItem('EKASB_nameKhanevadegi');
            EKASB_jensiat = disableItem('EKASB_jensiat');
            EKASB_shomareShenasname = disableItem('EKASB_shomareShenasname');
            EKASB_kodeMelli = disableItem('EKASB_kodeMelli');
            EKASB_tarikhTavallod = disableItem('EKASB_tarikhTavallod');
            EKASB_mahalleTavallod = disableItem('EKASB_mahalleTavallod');
            EKASB_mahalleSodoorShenasnameh = disableItem('EKASB_mahalleSodoorShenasnameh');
            EKASB_namePedar = disableItem('EKASB_namePedar');
            $('#tabeiat').hide();
            $('#entekhabTavallodButton').val(disableItem('entekhabTavallodButton'));
            $('#entekhabSodorButton').val(disableItem('entekhabSodorButton'));
            enableItem('EKASB_shomareSabt',null);
            enableItem('EKASB_kodeEghtesadi',null);
            enableItem('EKASB_tarikhSabt', null);
            enableItem('EKASB_mahalleSabt',null);
            enableItem('entekhabSabtButton',null);
        }
    }
</script>
<input type="hidden" id="EKASB_vaziateFotVaHayat">
<table id="EKASB_for_chg" class="inputList" border="0" cellspacing="1" cellpadding="5">
    <col class="inputCol"><col class="inputCol">
    <tr>
        <td>
            <span class="noThing"></span>
            <select id="EKASB_noeZiNafea" onchange="chg_EKASB_noeZiNafea();">
                <option>حقیقی</option>
                <option>حقوقی</option>
            </select>
            &nbsp;<label>نوع ذينفع</label>
        </td>
        <td>
            <span class="noThing"></span>
            <select id="EKASB_nesbatBabimeShode" class="validate[funcCall[val_EKASB_nesbatBabimeShode]]" onchange="if(this.value == 'خود شخص'){enableShakhsInfoFields();chg_EKASB_noeZiNafea();fillShakhsInfoByAjax($('#bimeShode_shakhs_id').val());}else if(this.value == 'وراث قانوني' ){disableShakhsInfoFields();}else{enableShakhsInfoFields();chg_EKASB_noeZiNafea()}">
                <c:set var="nesbatBaBimeShode" value="<%=ConstantForPishnehadForm.ConstantItemKey.NESBAT_BA_BIME_SHODE%>"/>
                <c:forEach var="row" items="${pishnehadConstants.constantForPishnehadFormList}">
                    <c:if test="${row.constantItemKey == nesbatBaBimeShode}">
                        <option value="${row.constantItemValue}" ${row.constantItemValue == 'نامعلوم'?'selected=selected':" "}>${row.constantItemValue}</option>
                    </c:if>
                </c:forEach>
            </select>
            &nbsp;<label>نسبت با بیمه شده</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <input type="text" id="EKASB_name" class="validate[required] text-input">
            &nbsp;<label>نام</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type="text" id="EKASB_nameKhanevadegi" class="validate[required] text-input">
            &nbsp;<label>نام خانوادگی</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <select id="EKASB_jensiat">
                <option id="jensiat_mard">مرد</option>
                <option id="jensiat_zan">زن</option>
            </select>
            &nbsp;<label>جنسیت</label>
            </td>
        <td id="tabeiat">
            <%--<span class="noThing"></span>--%>

                                 <script type="text/javascript">
                                     function ej()
                                     {
//                                             $('#ASF_iraniOrAtbaeKhareji_ei').checked = "";
                                             $('#zinaf_code_melli_shenaasaee_lbl').html('کد شناسایی');
                                             $('#EKASB_kodeMelli').removeClass('validate[required,custom[onlyNumber],funcCall[code_melli_shenasayi],parsianFuncCall[zinfeTekrari]] text-input');
                                             $('#EKASB_kodeMelli').addClass('validate[required,custom[onlyNumber],parsianFuncCall[zinfeTekrari]] text-input');
                                     }
                                     function ei()
                                     {
//                                            $('#ASF_iraniOrAtbaeKhareji_ej').checked="";
                                             $('#zinaf_code_melli_shenaasaee_lbl').html('کد ملی');
                                             $('#EKASB_kodeMelli').removeClass('validate[required,custom[onlyNumber],parsianFuncCall[zinfeTekrari]] text-input');
                                             $('#EKASB_kodeMelli').addClass('validate[required,custom[onlyNumber],funcCall[code_melli_shenasayi],parsianFuncCall[zinfeTekrari]] text-input');
                                     }
                                 </script>
            <div class="dblRadio">

                <input type="radio" id="ASF_iraniOrAtbaeKhareji_ei" name="shakhs.iraniOrAtbaeKhareji"
                       checked="checked" value="IRANI"
                       onchange="if(this.checked)
                                     {
                                         $('#zinaf_code_melli_shenaasaee_lbl').html('کد ملی');
                                         $('#EKASB_kodeMelli').removeClass('validate[required,custom[onlyNumber],parsianFuncCall[zinfeTekrari]] text-input');
                                         $('#EKASB_kodeMelli').addClass('validate[required,custom[onlyNumber],parsianFuncCall[code_melli],parsianFuncCall[zinfeTekrari]] text-input');
                                     }"/>
                <input type="radio" id="ASF_iraniOrAtbaeKhareji_ej" name="shakhs.iraniOrAtbaeKhareji"
                       value="ATBAE_KHAREJI"
                       onchange="if(this.checked)
                                     {
                                         $('#zinaf_code_melli_shenaasaee_lbl').html('کد شناسایی');
                                         $('#EKASB_kodeMelli').removeClass('validate[required,custom[onlyNumber],parsianFuncCall[code_melli],parsianFuncCall[zinfeTekrari]] text-input');
                                         $('#EKASB_kodeMelli').addClass('validate[required,custom[onlyNumber],parsianFuncCall[zinfeTekrari]] text-input');
                                     }"/>
                <label for="ASF_iraniOrAtbaeKhareji_ei">ایرانی</label>
                <label for="ASF_iraniOrAtbaeKhareji_ej">اتباع خارجی</label>
            </div>
            &nbsp;<label>تبعه</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <input type="text" id="EKASB_shomareShenasname" class="validate[required,custom[onlyNumber]] text-input">
            &nbsp;<label>شماره شناسنامه </label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type="text" id="EKASB_shomareSabt" class="validate[required,custom[onlyNumber]] text-input">
            &nbsp;<label>شماره ثبت</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <input type="text" id="EKASB_kodeMelli"   class="validate[required,custom[onlyNumber],parsianFuncCall[code_melli],parsianFuncCall[zinfeTekrari]] text-input">
            &nbsp;<label id="zinaf_code_melli_shenaasaee_lbl">كد ملي</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type="text" id="EKASB_kodeEghtesadi" class="validate[required] text-input">
            &nbsp;<label>كد اقتصادي</label>
        </td>
    </tr>
    <tr>
        <td>
            <input type="text" id="EKASB_tarikhTavallod" class="validate[required,custom[date]] text-input datePkr">
            &nbsp;<label>تاريخ تولد</label>
        </td>
        <td>
            <input type="text" id="EKASB_tarikhSabt" class="validate[required,custom[date]] text-input datePkr">
            &nbsp;<label>تاريخ ثبت</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <input type=text value="" id="EKASB_mahalleTavallod" class="validate[required]" readonly="readonly"/>&nbsp;<label>محل تولد</label>
            <input type=hidden name="" value="" id="EKASB_mahalleTavallod_id"/>
            <input type=button value="انتخاب" onclick="fillShahr('EKASB_mahalleTavallod_id','EKASB_mahalleTavallod','entekhabSodorButton');" style="margin:0px 188px 0px 0px; position: absolute;" id="entekhabTavallodButton"/>
        </td>
        <td>
            <span class="noThing"></span>
            <input type=text value="" id="EKASB_mahalleSabt" class="validate[required]" readonly="readonly"/>&nbsp;<label>محل ثبت</label>
            <input type=hidden name="" value="" id="EKASB_mahalleSabt_id"/>
            <input type=button value="انتخاب" onclick="fillShahr('EKASB_mahalleSabt_id','EKASB_mahalleSabt','EKASB_darsadeSahm');" style="margin:0px 180px 0px 0px; position: absolute;" id="entekhabSabtButton"/>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <input type=text value="" id="EKASB_mahalleSodoorShenasnameh" class="validate[required]" readonly="readonly"/>&nbsp;<label>محل صدور شناسنامه</label>
            <input type=hidden name="" value="" id="EKASB_mahalleSodoorShenasnameh_id"/>
            <input type=button value="انتخاب" onclick="fillShahr('EKASB_mahalleSodoorShenasnameh_id','EKASB_mahalleSodoorShenasnameh','EKASB_namePedar');" style="margin:-12px 210px 0px 0px; position: absolute;" id="entekhabSodorButton"/>
        </td>
        <td>
            <span class="noThing"></span>
            <input type=text value="" id="EKASB_namePedar" class="validate[required] text-input"/>&nbsp;<label>نام پدر</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <input type="text" id="EKASB_darsadeSahm" class="validate[required,custom[integer],custom[rangeDarsad],custom[noZero]] text-input">
            &nbsp;<label>درصد سهم</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type="text" id="EKASB_olaviat" class="validate[required,custom[integer]] text-input">
            &nbsp;<label>اولویت</label>
        </td>
    </tr>
</table>