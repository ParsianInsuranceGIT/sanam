<%@ page import="com.bitarts.parsian.model.pishnahad.Shakhs" %>
<%@ page import="com.bitarts.parsian.model.constantItems.ConstantForPishnehadForm" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 4/10/11
  Time: 3:37 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form></form>
<form action="/editShakhsAction" id="editBimegozarShakhsForm">
    <input type="hidden" name="shakhs.type" value="<%=Shakhs.BimeGozarType.HAGHIGHI.toString()%>"/>

    <c:if test="${pishnehad.bimeGozar.shakhs.id == null}">
    <input type="hidden" name="shakhs.id" id="shakhs_id" value=""/>
    </c:if>
    <c:if test="${pishnehad.bimeGozar.shakhs.id != null}">
        <input type="hidden" name="shakhs.id" id="shakhs_id" value="${pishnehad.bimeGozar.shakhs.id}"/>
    </c:if>
    <table class=inputList style="width:620px;border-spacing:15px 10px;">
        <col class=inputCol>
        <col class=inputCol>
        <tr><td colspan="2"><div id="msgErr_editShakhs"></div></td></tr>
        <tr>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.name" value="${pishnehad.bimeGozar.shakhs.name}" id="editshakhsName"
                       class="text-input"/>
                &nbsp;<label>نام</label>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.nameKhanevadegi" value="${pishnehad.bimeGozar.shakhs.nameKhanevadegi}" id="editshakhsNameKhanevadegi"
                       class="text-input"/>
                &nbsp;<label>نام                خانوادگی</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.namePedar" value="${pishnehad.bimeGozar.shakhs.namePedar}" id="editshakhsNamePedar"
                       class="text-input"/>
                &nbsp;<label>نام پدر</label>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <select name="shakhs.pishvand" id="editshakhsPishvand">
                    <c:set var="pishvand" value="<%=ConstantForPishnehadForm.ConstantItemKey.PISHVAND%>"/>
                        <c:forEach var="row" items="${pishnehadConstants.constantForPishnehadFormList}">
                            <c:if test="${row.constantItemKey == pishvand}">
                                <option value="${row.constantItemValue}"
                                <c:if test="${row.constantItemValue == pishnehad.bimeGozar.shakhs.pishvand}">selected="selected"</c:if>
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
                    <c:if test="${pishnehad.bimeGozar.shakhs.jensiat == 'مرد'}">
                        <input type="radio" id="editASF_jensiat_i" name="shakhs.jensiat" onclick="enableNezamVazife();" checked="checked" value="مرد"/>
                        <input type="radio" id="editASF_jensiat_j" name="shakhs.jensiat" onclick="disableNezamVazife();" value="زن"/>
                    </c:if>
                    <c:if test="${pishnehad.bimeGozar.shakhs.jensiat != 'مرد'}">
                        <input type="radio" id="editASF_jensiat_i" name="shakhs.jensiat" onclick="enableNezamVazife();" value="مرد"/>
                        <input type="radio" id="editASF_jensiat_j" name="shakhs.jensiat" onclick="disableNezamVazife();" checked="checked" value="زن"/>
                    </c:if>
                    <label for="editASF_jensiat_i">مرد</label>
                    <label for="editASF_jensiat_j">زن</label>
                </div>
                &nbsp;<label>جنسیت</label>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <div class="dblRadio">
                    <c:if test="${pishnehad.bimeGozar.shakhs.vaziateTaahol == 'MOJARRAD'}">
                        <input type="radio" id="editASF_vaziateTaahol_i" name="shakhs.vaziateTaahol" value="mojarrad"
                               checked="checked"/>
                        <input type="radio" id="editASF_vaziateTaahol_j" name="shakhs.vaziateTaahol" value="moteahel"/>
                    </c:if>
                    <c:if test="${pishnehad.bimeGozar.shakhs.vaziateTaahol != 'MOJARRAD'}">
                        <input type="radio" id="editASF_vaziateTaahol_i" name="shakhs.vaziateTaahol" value="mojarrad"/>
                        <input type="radio" id="editASF_vaziateTaahol_j" name="shakhs.vaziateTaahol" value="moteahel"
                                checked="checked"/>
                    </c:if>
                    <label for="editASF_vaziateTaahol_i">مجرد</label>
                    <label for="editASF_vaziateTaahol_j">متأهل</label>
                </div>
                &nbsp;<label>وضعیت تأهل</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.shomareShenasnameh" value="${pishnehad.bimeGozar.shakhs.shomareShenasnameh}" id="editshakhsShomareShenasnameh"
                       class="text-input"/>
                &nbsp;<label>شماره                شناسنامه</label>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text value="${shakhs.mahalleSodoreShenasnameh.cityName}" id="editshakhsMahalleSodoreShenasnameh" class="validate[required]" readonly="readonly"/>&nbsp;<label>محل صدور شناسنامه</label>
                <input type=hidden name="shakhs.mahalleSodoreShenasnameh.cityId" value="${shakhs.mahalleSodoreShenasnameh.cityId}" id="editshakhsMahalleSodoreShenasnameh_id"/>
                <input type=button value="انتخاب" onclick="fillShahr('editshakhsMahalleSodoreShenasnameh_id','editshakhsMahalleSodoreShenasnameh','editshakhs_tarikheTavallod');" style="margin:-13px 209px 0px 0px; position: absolute;"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="shakhs.tarikheTavallod" value="${pishnehad.bimeGozar.shakhs.tarikheTavallod}" id="editshakhs_tarikheTavallod"
                       class="text-input datePkr"/>
                &nbsp;<label for="editshakhs_tarikheTavallod">تاریخ تولد</label>
            </td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text value="${shakhs.mahalleTavallod.cityName}" id="editshakhsMahalleTavallod" class="validate[required]" readonly="readonly"/>&nbsp;<label>محل تولد</label>
                <input type=hidden name="shakhs.mahalleTavallod.cityId" value="${shakhs.mahalleTavallod.cityId}" id="editshakhsMahalleTavallod_id"/>
                <input type=button value="انتخاب" onclick="fillShahr('editshakhsMahalleTavallod_id','editshakhsMahalleTavallod','editshakhsKodeMelliShenasayi');" style="margin:0px 187px 0px 0px; position: absolute;"/>
            </td>
        </tr>
        <tr>
            <td style="display: none;">
                <span class="nohelp">&nbsp;</span>
                <div class="dblRadio">

                    <input type="radio" id="editASF_iraniOrAtbaeKhareji_i" name="shakhs.iraniOrAtbaeKhareji"
                           checked="checked" value="IRANI"
                           onchange="if(this.checked){$('#shakhs_code_melli_shenaasaee_lbl').html('کد ملی')}"/>
                    <input type="radio" id="editASF_iraniOrAtbaeKhareji_j" name="shakhs.iraniOrAtbaeKhareji"
                           value="ATBAE_KHAREJI"
                           onchange="if(this.checked){$('#shakhs_code_melli_shenaasaee_lbl').html('کد شناسایی')}"/>
                    <label for="editASF_iraniOrAtbaeKhareji_i">ایرانی</label>
                    <label for="editASF_iraniOrAtbaeKhareji_j">اتباع خارجی</label>
                    &nbsp;<label>تبعه</label>
                </div>
            </td>
            <td>
                <script type="text/javascript">
                    function val_codeMelliShenasayi(){
                        if($('#ASF_iraniOrAtbaeKhareji_i').attr('checked')){
                            return val_codeMelli('#editshakhsKodeMelliShenasayi');
                        }
                        return true;
                    }
                </script>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.kodeMelliShenasayi" value="${pishnehad.bimeGozar.shakhs.kodeMelliShenasayi}" id="editshakhsKodeMelliShenasayi"
                       class="text-input"/>
                &nbsp;<label id="editshakhs_code_melli_shenaasaee_lbl">کد ملی</label>
            </td>
        </tr>
        <tr>
            <td><span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.shoghleAsli" value="${pishnehad.bimeGozar.shakhs.shoghleAsli}" id="editshakhsShoghleAsli"
                       class="text-input"/>&nbsp;<label>شغل
                    اصلی</label></td>
            <td>
                <span class="nohelp">&nbsp;</span>
                <input type=text name="shakhs.shoghleFarei" value="${pishnehad.bimeGozar.shakhs.shoghleFarei}" id="editshoghleFarei"
                       class="text-input"/>
                <%--<div class="dblRadio">--%>
                <%--<input type="checkbox" id="editshakhsShoghlFareiChbx" name="shakhs.shoghlFareiChbx"--%>
                <%--style="font-size:7pt"--%>
                <%--onchange="if(this.checked) activateArray(new Array('shakhsShoghleFarei'));--%>
                <%--else deactivateArray(new Array('shakhsShoghleFarei'));"/>--%>
                &nbsp;<label>شغل فرعی</label>
                </div>
            </td>
        </tr>
    </table>
</form>

