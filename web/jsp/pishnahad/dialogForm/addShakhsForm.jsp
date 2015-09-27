<%@ page import="com.bitarts.parsian.model.pishnahad.Shakhs" %>
<%@ page import="static com.bitarts.parsian.model.constantItems.ConstantForPishnehadForm.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 4/10/11
  Time: 3:37 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="" id="shakhsForm">
    <input type="hidden" name="bimeShodeOrGozar" value="bimegozar"/>
    <input type="hidden" name="shakhs.type" value="<%=Shakhs.BimeGozarType.HAGHIGHI.toString()%>"/>
    <input type="hidden" name="shakhs.id" id="shakhshaghighi_id" value="${shakhs.id}"/>
    <table class=inputList style="width:620px;border-spacing:15px 10px;">
        <col class=inputCol>
        <col class=inputCol>
        <tr><td colspan="2"><div id="msgErr_div"></div></td></tr>
        <tr>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.name" value="${shakhs.name}" id="shakhsName"
                       class="validate[required,custom[onlyPersianLetter]] text-input"/>
                &nbsp;<label>نام</label>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.nameKhanevadegi" value="${shakhs.nameKhanevadegi}" id="shakhsNameKhanevadegi"
                       class="validate[required,custom[onlyPersianLetter]] text-input"/>
                &nbsp;<label>نام                خانوادگی</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.namePedar" value="${shakhs.namePedar}" id="shakhsNamePedar"
                       class="validate[required,custom[onlyPersianLetter]] text-input"/>
                &nbsp;<label>نام پدر</label>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <select name="shakhs.pishvand" id="shakhsPishvand" onchange="setJensiat(this.value);">
                    <c:set var="pishvand" value="<%=ConstantItemKey.PISHVAND%>"/>
                        <c:forEach var="row" items="${pishnehadConstants.constantForPishnehadFormList}">
                            <c:if test="${row.constantItemKey == pishvand}">
                                <option value="${row.constantItemValue}"
                                <c:if test="${row.constantItemValue == shakhs.pishvand}">selected="selected"</c:if>
                                >
                                ${row.constantItemValue}
                                </option>
                            </c:if>
                        </c:forEach>
                </select>
                &nbsp;<label>پیشوند</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="nohelp">&nbsp;</span>
                <div class="dblRadio" id="jensiatRadio">
                    <input type="radio" id="ASF_jensiat_i" name="shakhs.jensiat" checked="checked" onclick="enableNezamVazife();" value="مرد"/>
                    <input type="radio" id="ASF_jensiat_j" name="shakhs.jensiat" onclick="disableNezamVazife();" value="زن"/>
                    <label for="ASF_jensiat_i">مرد</label>
                    <label for="ASF_jensiat_j">زن</label>
                </div>

                &nbsp;<label>جنسیت</label>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <div class="dblRadio">
                    <input type="radio" id="ASF_vaziateTaahol_i" name="shakhs.vaziateTaahol" value="mojarrad"
                           checked="checked"/>
                    <input type="radio" id="ASF_vaziateTaahol_j" name="shakhs.vaziateTaahol" value="moteahel" />
                    <label for="ASF_vaziateTaahol_i">مجرد</label>
                    <label for="ASF_vaziateTaahol_j">متأهل</label>
                </div>
                &nbsp;<label>وضعیت تأهل</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.shomareShenasnameh" value="${shakhs.shomareShenasnameh}" id="shakhsShomareShenasnameh"
                       class="validate[required,custom[shenaasnaame]] text-input"/>
                &nbsp;<label>شماره                شناسنامه</label>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text value="${shakhs.mahalleSodoreShenasnameh.cityName}" id="shakhsMahalleSodoreShenasnameh" class="validate[required]" readonly="readonly"/>&nbsp;<label>محل صدور شناسنامه</label>
                <input type=hidden name="shakhs.mahalleSodoreShenasnameh.cityId" value="${shakhs.mahalleSodoreShenasnameh.cityId}" id="shakhsMahalleSodoreShenasnameh_id"/>
                <input type=button value="انتخاب" onclick="fillShahr('shakhsMahalleSodoreShenasnameh_id','shakhsMahalleSodoreShenasnameh','shakhs_tarikheTavallod');" style="margin:-13px 209px 0px 0px; position: absolute;"/>
            </td>
        </tr>
        <tr>
            <td>
                <c:if test="${isHalateElhaghie}">
                    <input type="text" name="shakhs.tarikheTavallod" value="${shakhs.tarikheTavallod}" id="shakhs_tarikheTavallod"
                           <%--<c:if test="${isHalateElhaghie && pishnehad.bimeShode.shakhs.id!=pishnehad.bimeGozar.shakhs.id}"> --%>
                               class="validate[required,custom[date]] text-input datePkr"
                           <%--</c:if> --%>
                           onblur="calculateSenForEstelam();"/>
                    &nbsp;<label for="shakhs_tarikheTavallod">تاریخ تولد</label>
                </c:if>
                <c:if test="${!isHalateElhaghie}">
                    <input type="text" name="shakhs.tarikheTavallod" value="${shakhs.tarikheTavallod}" id="shakhs_tarikheTavallod"
                           class="validate[required,custom[date]] text-input datePkr" onblur="calculateSenForEstelam();"/>
                    &nbsp;<label for="shakhs_tarikheTavallod">تاریخ تولد</label>
                </c:if>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text value="${shakhs.mahalleTavallod.cityName}" id="shakhsMahalleTavallod" class="validate[required]" readonly="readonly"/>&nbsp;<label>محل تولد</label>
                <input type=hidden name="shakhs.mahalleTavallod.cityId" value="${shakhs.mahalleTavallod.cityId}" id="shakhsMahalleTavallod_id"/>
                <input type=button value="انتخاب" onclick="fillShahr('shakhsMahalleTavallod_id','shakhsMahalleTavallod','shakhsKodeMelliShenasayi');" style="margin:0px 187px 0px 0px; position: absolute;"/>
            </td>
        </tr>
        <tr>
            <td>
                <span class="nohelp">&nbsp;</span>
                <div class="dblRadio">
                    <input type="radio" id="ASF_iraniOrAtbaeKhareji_i" name="shakhs.iraniOrAtbaeKhareji"
                           checked="checked" value="IRANI"
                           onchange="if(this.checked)
                                     {
                                        $('#shakhs_code_melli_shenaasaee_lbl').html('کد ملی');
                                        $('#shakhsKodeMelliShenasayi').removeClass('validate[required,custom[onlyNumber],] text-input');
                                        $('#shakhsKodeMelliShenasayi').addClass('validate[required,custom[onlyNumber],funcCall[code_melli_shenasayi]] text-input');
                                     }"/>
                    <input type="radio" id="ASF_iraniOrAtbaeKhareji_j" name="shakhs.iraniOrAtbaeKhareji"
                           value="ATBAE_KHAREJI"
                           onchange="if(this.checked)
                                     {
                                        $('#shakhs_code_melli_shenaasaee_lbl').html('کد شناسایی');
                                        $('#shakhsKodeMelliShenasayi').removeClass('validate[required,custom[onlyNumber],funcCall[code_melli_shenasayi]] text-input');
                                        $('#shakhsKodeMelliShenasayi').addClass('validate[required,custom[onlyNumber],] text-input');
                                     }"/>
                    <label for="ASF_iraniOrAtbaeKhareji_i">ایرانی</label>
                    <label for="ASF_iraniOrAtbaeKhareji_j">اتباع خارجی</label>
                    &nbsp;<label>تبعه</label>
                </div>
            </td>
            <td>
                <script type="text/javascript">
                    function val_codeMelliShenasayi(){
                        if($('#ASF_iraniOrAtbaeKhareji_i').attr('checked')){
                            return val_codeMelli('#shakhsKodeMelliShenasayi');                            
                        }
                        return true;
                    }
                </script>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.kodeMelliShenasayi" value="${shakhs.kodeMelliShenasayi}" id="shakhsKodeMelliShenasayi" />
                &nbsp;<label id="shakhs_code_melli_shenaasaee_lbl">کد ملی</label>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                لطفا نوع شغل یا کاری را که به آن اشتغال دارید، به صورت دقیق ذکر نمایید.                                            عناوین شغلی مانند ازاد یا کارمند مورد قبول نمی باشد.
            </td>
        </tr>
        <tr>
            <td><span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.shoghleAsli" value="${shakhs.shoghleAsli}" id="shakhsShoghleAsli"
                       class="validate[required,custom[onlyPersianLetter]] text-input"/>&nbsp;<label>شغل
                    اصلی</label></td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.shoghleFarei" value="${shakhs.shoghleFarei}" id="shoghleFarei"
                       class="validate[custom[onlyPersianLetter]] text-input"/>
                <%--<div class="dblRadio">--%>
                <%--<input type="checkbox" id="shakhsShoghlFareiChbx" name="shakhs.shoghlFareiChbx"--%>
                <%--style="font-size:7pt"--%>
                <%--onchange="if(this.checked) activateArray(new Array('shakhsShoghleFarei'));--%>
                <%--else deactivateArray(new Array('shakhsShoghleFarei'));"/>--%>
                &nbsp;<label>شغل فرعی</label>
                </div>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    function disableNezamVazife(){
        $("#shakhsPishvand").val('خانم');
        $("#trfornezamvazife").hide();
    }
    function enableNezamVazife(){
        $("#shakhsPishvand").val('آقاي');
        $("#trfornezamvazife").show();
    }
    function calculateSenForEstelam(){
        // check if copied
        if($("#bimeGozar_shakhs_id").val() == $("#bimeShode_shakhs_id").val())
        {
            <c:if test="${!isHalateElhaghie}">
                $("#tarikh_tavalod").val($('#shakhs_tarikheTavallod').val());
            </c:if>
            calculateSen();
        }
    }
    function setJensiat(value)
    {
        if(value == "آقاي")
        {
            $('#ASF_jensiat_j').removeAttr('checked');
            $('#ASF_jensiat_i').attr('checked',true);
            $("#ASF_jensiat_i").click();
            $('#jensiatRadio').buttonset().buttonset("refresh");
        }
        else if(value == "خانم")
        {
            $('#ASF_jensiat_i').removeAttr('checked');
            $('#ASF_jensiat_j').attr('checked',true);
            $("#ASF_jensiat_j").click();
            $('#jensiatRadio').buttonset().buttonset("refresh");
        }
        else
        {
            $('#ASF_jensiat_j').removeAttr('checked');
            $('#ASF_jensiat_i').attr('checked',true);
            $("#ASF_jensiat_i").click();
            $('#jensiatRadio').buttonset().buttonset("refresh");
        }
    }
</script>