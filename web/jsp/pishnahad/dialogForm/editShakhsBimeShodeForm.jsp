<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 4/10/11
  Time: 3:37 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form></form>
<form action="editShakhsAction" id="editBimeshodeshakhsForm">
    <input type="hidden" name="shakhs.type" value="<%=Shakhs.BimeGozarType.HAGHIGHI.toString()%>"/>
    <c:if test="${pishnehad.bimeShode.shakhs.id == null}">
    <input type="hidden" name="shakhs.id" id="shakhs_bimeshode_id" value=""/>
    </c:if>
    <c:if test="${pishnehad.bimeShode.shakhs.id != null}">
        <input type="hidden" name="shakhs.id" id="shakhs_bimeshode_id" value="${pishnehad.bimeShode.shakhs.id}"/>
    </c:if>
    <input type="hidden" name="bimeShodeOrGozar" value="bimeShode"/>
    <table class=inputList style="width:620px;border-spacing:15px 10px;">
        <col class=inputCol>
        <col class=inputCol>
        <tr><td colspan="2"><div id="msgErr_div"></div></td></tr>
        <tr><td colspan="2"><div id="msgErr_editShakhs"></div></td></tr>
        <tr>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.name" value="${pishnehad.bimeShode.shakhs.name}" id="editBimeshodeshakhsName"
                       class="validate[required,custom[onlyPersianLetter]] text-input"/>
                &nbsp;<label>نام</label>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.nameKhanevadegi" value="${pishnehad.bimeShode.shakhs.nameKhanevadegi}" id="editBimeshodeshakhsNameKhanevadegi"
                       class="validate[required,custom[onlyPersianLetter]] text-input"/>
                &nbsp;<label>نام                خانوادگی</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.namePedar" value="${pishnehad.bimeShode.shakhs.namePedar}" id="editBimeshodeshakhsNamePedar"
                       class="validate[required,custom[onlyPersianLetter]] text-input"/>
                &nbsp;<label>نام پدر</label>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <select name="shakhs.pishvand" id="editBimeshodeshakhsPishvand">
                     <c:set var="pishvand" value="<%=ConstantForPishnehadForm.ConstantItemKey.PISHVAND%>"/>
                        <c:forEach var="row" items="${pishnehadConstants.constantForPishnehadFormList}">
                            <c:if test="${row.constantItemKey == pishvand}">
                                <option value="${row.constantItemValue}"
                                <c:if test="${row.constantItemValue == pishnehad.bimeShode.shakhs.pishvand}">selected="selected"</c:if>
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
                <div class="dblRadio">
                    <c:if test="${pishnehad.bimeShode.shakhs.jensiat == 'مرد'}">
                        <input type="radio" id="editBimeshodeASF_jensiat_i" name="shakhs.jensiat" checked="checked" value="مرد"/>
                        <input type="radio" id="editBimeshodeASF_jensiat_j" name="shakhs.jensiat" value="زن"/>
                    </c:if>
                    <c:if test="${pishnehad.bimeShode.shakhs.jensiat != 'مرد'}">
                        <input type="radio" id="editBimeshodeASF_jensiat_i" name="shakhs.jensiat" value="مرد"/>
                        <input type="radio" id="editBimeshodeASF_jensiat_j" name="shakhs.jensiat" checked="checked" value="زن"/>
                    </c:if>
                    <label for="editBimeshodeASF_jensiat_i">مرد</label>
                    <label for="editBimeshodeASF_jensiat_j">زن</label>
                </div>
                &nbsp;<label>جنسیت</label>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <div class="dblRadio">
                    <c:if test="${pishnehad.bimeShode.shakhs.vaziateTaahol == 'MOJARRAD'}">
                        <input type="radio" id="editBimeshodeASF_vaziateTaahol_i" name="shakhs.vaziateTaahol" value="mojarrad"
                               checked="checked"/>
                        <input type="radio" id="editBimeshodeASF_vaziateTaahol_j" name="shakhs.vaziateTaahol" value="moteahel"/>
                    </c:if>
                    <c:if test="${pishnehad.bimeShode.shakhs.vaziateTaahol != 'MOJARRAD'}">
                        <input type="radio" id="editBimeshodeASF_vaziateTaahol_i" name="shakhs.vaziateTaahol" value="mojarrad"/>
                        <input type="radio" id="editBimeshodeASF_vaziateTaahol_j" name="shakhs.vaziateTaahol" value="moteahel"
                                checked="checked"/>
                    </c:if>
                    <label for="editBimeshodeASF_vaziateTaahol_i">مجرد</label>
                    <label for="editBimeshodeASF_vaziateTaahol_j">متأهل</label>
                </div>
                &nbsp;<label>وضعیت تأهل</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.shomareShenasnameh" value="${pishnehad.bimeShode.shakhs.shomareShenasnameh}" id="editBimeshodeshakhsShomareShenasnameh"
                       class="validate[required,custom[shenaasnaame]] text-input"/>
                &nbsp;<label>شماره                شناسنامه</label>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text value="${pishnehad.bimeShode.shakhs.mahalleSodoreShenasnameh.cityName}" id="editBimeshodeshakhsMahalleSodoreShenasnameh" class="validate[required]" readonly="readonly"/>&nbsp;<label>محل صدور شناسنامه</label>
                <input type=hidden name="shakhs.mahalleSodoreShenasnameh.cityId" value="${pishnehad.bimeShode.shakhs.mahalleSodoreShenasnameh.cityId}" id="editBimeshodeshakhsMahalleSodoreShenasnameh_id"/>
                <input type=button value="انتخاب" onclick="fillShahr('editBimeshodeshakhsMahalleSodoreShenasnameh_id','editBimeshodeshakhsMahalleSodoreShenasnameh','editBimeshodeshakhs_tarikheTavallod');" style="margin:-13px 209px 0px 0px; position: absolute;"/>
            </td>
        </tr>
        <tr>
            <td> <input type="hidden" name="isHalateElhaghie" value="${isHalateElhaghie}"/>
<c:if test="${darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050}">
                <input type="hidden" name="darkhastTaghiratId" value="${darkhastTaghirat.id}"/>
    </c:if>
                <input type="hidden" name="" value="${darkhastTaghirat.id}"/>
                <input type="text" name="shakhs.tarikheTavallod" value="${pishnehad.bimeShode.shakhs.tarikheTavallod}" id="editBimeshodeshakhs_tarikheTavallod"
                  class="validate[required,custom[date]] text-input datePkr" onblur="calculateSenForEstelam();" />
                <%--<input type="text" name="shakhs.tarikheTavallod" value="${pishnehad.bimeShode.shakhs.tarikheTavallod}" id="editBimeshodeshakhs_tarikheTavallod"--%>
                       <%--<c:if test="${!isHalateElhaghie}">class="validate[required,custom[date]] text-input datePkr"</c:if> onblur="calculateSenForEstelam();" <c:if test="${isHalateElhaghie}">readonly="readonly" </c:if>--%>
                        <%--/>--%>
                &nbsp;<label for="editBimeshodeshakhs_tarikheTavallod">تاریخ تولد</label>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text value="${pishnehad.bimeShode.shakhs.mahalleTavallod.cityName}" id="editBimeshodeshakhsMahalleTavallod" class="validate[required]" readonly="readonly"/>&nbsp;<label>محل تولد</label>
                <input type=hidden name="shakhs.mahalleTavallod.cityId" value="${pishnehad.bimeShode.shakhs.mahalleTavallod.cityId}" id="editBimeshodeshakhsMahalleTavallod_id"/>
                <input type=button value="انتخاب" onclick="fillShahr('editBimeshodeshakhsMahalleTavallod_id','editBimeshodeshakhsMahalleTavallod','editBimeshodeshakhsKodeMelliShenasayi');" style="margin:0px 187px 0px 0px; position: absolute;"/>
            </td>
        </tr>
        <tr>
            <td>
                <span class="nohelp">&nbsp;</span>
                <div class="dblRadio">

                    <input type="radio" id="editBimeshodeASF_iraniOrAtbaeKhareji_i" name="shakhs.iraniOrAtbaeKhareji"
                           checked="checked" value="IRANI"
                           onchange="if(this.checked){$('#editBimeshodeshakhs_code_melli_shenaasaee_lbl').html('کد ملی')}"/>
                    <input type="radio" id="editBimeshodeASF_iraniOrAtbaeKhareji_j" name="shakhs.iraniOrAtbaeKhareji"
                           value="ATBAE_KHAREJI"
                           onchange="if(this.checked){$('#editBimeshodeshakhs_code_melli_shenaasaee_lbl').html('کد شناسایی')}"/>
                    <label for="editBimeshodeASF_iraniOrAtbaeKhareji_i">ایرانی</label>
                    <label for="editBimeshodeASF_iraniOrAtbaeKhareji_j">اتباع خارجی</label>
                    &nbsp;<label>تبعه</label>
                </div>
            </td>
            <td>
                <script type="text/javascript">
                    function val_codeMelliShenasayi(){
                        if($('#ASF_iraniOrAtbaeKhareji_i').attr('checked')){
                            return val_codeMelli('#editBimeshodeshakhsKodeMelliShenasayi');
                        }
                        return true;
                    }
                </script>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.kodeMelliShenasayi" value="${pishnehad.bimeShode.shakhs.kodeMelliShenasayi}" id="editBimeshodeshakhsKodeMelliShenasayi"
                       class="validate[required,custom[onlyNumber],funcCall[code_melli_shenasayi]] text-input"/>
                &nbsp;<label id="editBimeshodeshakhs_code_melli_shenaasaee_lbl">کد ملی</label>
            </td>
        </tr>
        <tr>
            <td><span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.shoghleAsli" value="${pishnehad.bimeShode.shakhs.shoghleAsli}" id="editBimeshodeshakhsShoghleAsli"
                       class="validate[required,custom[onlyPersianLetter]] text-input"/>&nbsp;<label>شغل
                    اصلی</label></td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.shoghleFarei" value="${pishnehad.bimeShode.shakhs.shoghleFarei}" id="editBimeshodeshoghleFarei"
                       class="validate[custom[onlyPersianLetter]] text-input"/>
                <%--<div class="dblRadio">--%>
                <%--<input type="checkbox" id="editBimeshodeshakhsShoghlFareiChbx" name="shakhs.shoghlFareiChbx"--%>
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
    function calculateSenForEstelam(){
        $("#tarikh_tavalod").val($('#editBimeshodeshakhs_tarikheTavallod').val());
        calculateSen();
    }
</script>