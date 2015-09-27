<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 6/8/11
  Time: 9:08 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="inputList" cellspacing="5" width="90%">
    <col class="inputCol">
    <col class="inputCol">
    <tr>
        <td>
            <span class="help" title="بيانگر درجه ريسك بيمه شده به منظور ارائه پوشش اضافي فوت در اثر حادثه و نقص عضو مي باشد"></span>
            <select name="estelamReverse.tabaghe_khatar" id="tabaghe_khatar" disabled="disabled">
                <option value="1" selected="">طبقه یک</option>
                <option <c:if test="${estelamReverse.tabaghe_khatar == '2'}">selected="selected"</c:if> value="2">طبقه دو</option>
                <option <c:if test="${estelamReverse.tabaghe_khatar == '3'}">selected="selected"</c:if> value="3">طبقه سه</option>
                <option <c:if test="${estelamReverse.tabaghe_khatar == '4'}">selected="selected"</c:if> value="4">طبقه چهار</option>
                <option <c:if test="${estelamReverse.tabaghe_khatar == '5'}">selected="selected"</c:if> value="5">طبقه پنج</option>
            </select>&nbsp;<label>طبقه خطر</label>
        </td>
        <td></td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="mode4">
                <input type="radio" id="mode41" name="estelamReverse.pooshesh_fot_dar_asar_haadese"
                       <c:if test="${estelamReverse.pooshesh_fot_dar_asar_haadese != 'yes'}">checked="checked"</c:if>
                       value="no" onchange="if($(this).attr('checked')){pushesheFoutDarAsareHadeseNadarad();}"/>
                <input type="radio" id="mode42" name="estelamReverse.pooshesh_fot_dar_asar_haadese"
                       <c:if test="${estelamReverse.pooshesh_fot_dar_asar_haadese == 'yes'}">checked="checked"</c:if>
                       value="yes" onchange="if($(this).attr('checked')){pushesheFoutDarAsareHadeseDarad();}"/>
                <label for="mode41">ندارد</label>
                <label for="mode42">دارد</label>
            </div>
            &nbsp;<label>
            پوشش فوت در اثر حادثه
        </label>
        </td>
        <td>
                    <span class="help"
                          title="سرمایه ای است که در صورت فوت بیمه شده در اثر حادثه طی مدت بیمه نامه، قابل پرداخت است"></span>
            <input type="text" name="estelamReverse.sarmaye_paye_fot_dar_asar_hadese" id="sarmaye_paye_fot_dar_asar_hadese" onchange="sarmayeNaghsOzvChange();sarmayePoosheshNaghsChange();"
                   class="validate[required,custom[long],funcCall[sarmaye_paye_fot_dar_asar_hadese]] text-input ui-state-disabled digitSeparators"
                   value='${estelamReverse.sarmaye_paye_fot_dar_asar_hadese != null ? estelamReverse.sarmaye_paye_fot_dar_asar_hadese : "0"}' disabled/>&nbsp;<label>سرمایه پایه فوت در اثر حادثه (ریال)</label>
            <%--value="30,000,000" disabled/>&nbsp;<label>سرمایه پایه فوت در اثر حادثه (ریال)</label>--%>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>
            <div class="dblRadio" id="mode1">
                <input type="radio" id="mode11" name="estelamReverse.pooshesh_naghs_ozv" disabled="disabled"
                       <c:if test="${estelamReverse.pooshesh_naghs_ozv != 'yes'}">checked="checked"</c:if>
                       value="no" onchange="if($(this).attr('checked')){$('#sarmaye_pooshesh_naghs_ozv').val('0');deactivate('sarmaye_pooshesh_naghs_ozv');}"/><label for="mode11">ندارد</label>
                <input type="radio" id="mode12" name="estelamReverse.pooshesh_naghs_ozv" disabled="disabled"
                       <c:if test="${estelamReverse.pooshesh_naghs_ozv == 'yes'}">checked="checked"</c:if>
                       value="yes" onchange="if($(this).attr('checked')){activation('sarmaye_pooshesh_naghs_ozv');sarmayePoosheshNaghsChange();}"/><label
                    for="mode12">دارد</label>
            </div>
            &nbsp;<label>پوشش نقص عضو</label>&nbsp; <label style="color: red">(پوشش جدید)</label>
        </td>
        <td>
            <span class="help" title="سرمايه اي است كه در صورت وقوع ريسك نقص عضو متناسب با درصد نقص عضو به بيمه شده پرداخت مي شود"></span>
            <input type="text" name="estelamReverse.sarmaye_pooshesh_naghs_ozv" id="sarmaye_pooshesh_naghs_ozv"
                   class="validate[required,custom[long],funcCall[val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot]] text-input ui-state-disabled digitSeparators"
                   value='${estelamReverse.sarmaye_pooshesh_naghs_ozv != null ? estelamReverse.sarmaye_pooshesh_naghs_ozv : "0"}' disabled/>&nbsp;
            <%--value="30,000,000" disabled/>&nbsp;--%>
            <label>سرمایه پوشش نقص عضو (ریال) </label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>
            <div class="dblRadio" id="mode3">
                <input type="radio" id="mode31" name="estelamReverse.pooshesh_fot_dar_asar_zelzele_fake"
                       <c:if test="${estelamReverse.pooshesh_fot_dar_asar_zelzele != 'yes'}">checked="checked"</c:if>
                       value="no" disabled="disabled"/><label for="mode31">ندارد</label>
                <input type="radio" id="mode32" name="estelamReverse.pooshesh_fot_dar_asar_zelzele_fake"
                       <c:if test="${estelamReverse.pooshesh_fot_dar_asar_zelzele == 'yes'}">checked="checked"</c:if>
                       value="yes" disabled="disabled"/><label for="mode32">دارد</label>
            </div>
            &nbsp;<label>
            پوشش فوت در اثر زلزله
        </label>
        </td>
        <td>
            <%--<span class="nohelp"></span>--%>
            <%--<input type="text" name="estelamReverse.sarmaye_paye_fot_dar_asar_hadese" id="sarmaye_paye_fot_dar_asar_hadese"--%>
            <%--class="validate[required,custom[long],custom[sarmaye_paye_fot_dar_asar_hadese]] text-input ui-state-disabled digitSeparators"--%>
            <%--value="0" disabled/>&nbsp;<label>سرمایه پایه فوت در اثر حادثه (ریال)</label>                    --%>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="mode5">
                <input type="radio" id="mode51" name="estelamReverse.pooshesh_amraz_khas"
                       <c:if test="${estelamReverse.pooshesh_amraz_khas != 'yes'}">checked="checked"</c:if>
                       value="no" onchange="if($(this).attr('checked')){$('#sarmaye_pooshesh_amraz_khas').val('0');deactivate('sarmaye_pooshesh_amraz_khas');}"/><label for="mode51">ندارد</label>
                <input type="radio" id="mode52" name="estelamReverse.pooshesh_amraz_khas"
                       <c:if test="${estelamReverse.pooshesh_amraz_khas == 'yes'}">checked="checked"</c:if>
                       value="yes" onchange="if($(this).attr('checked')){activation('sarmaye_pooshesh_amraz_khas');sarmayeAmrazChange();}"/><label
                    for="mode52">دارد</label>
            </div>
            &nbsp;<label>پوشش امراض خاص</label>
        </td>
        <td>
                    <span class="help"
                          title="نشان دهنده سقف سرمایه ای است که در صورت ابتلاء بیمه شده به یکی از امراض خاص در خلال مدت بیمه نامه، به منظور جبران بخشی از هزینه های بیمارستانی پرداخت می شود"></span>
            <input type="text" name="estelamReverse.sarmaye_pooshesh_amraz_khas" id="sarmaye_pooshesh_amraz_khas"
                   class="validate[required,custom[long],funcCall[val_sarmaye_pushesh_amraaz_khaas]] text-input ui-state-disabled digitSeparators"
                   value='${estelamReverse.sarmaye_pooshesh_amraz_khas != null ? estelamReverse.sarmaye_pooshesh_amraz_khas : "0"}' readonly="readonly" disabled="disabled"/>
            &nbsp;<label>سرمایه
            <%--value="9,000,000"/>&nbsp;<label>سرمایه--%>
            پوشش امراض خاص (ریال)</label>
        </td>
    </tr>

    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>
            <div class="dblRadio" id="mode2">
                <input type="radio" id="mode21" name="estelamReverse.pooshesh_moafiat"
                       <c:if test="${estelamReverse.pooshesh_moafiat != 'yes'}">checked="checked"</c:if>
                       value="no"/><label for="mode21">ندارد</label>
                <input type="radio" id="mode22" name="estelamReverse.pooshesh_moafiat"
                       <c:if test="${estelamReverse.pooshesh_moafiat == 'yes'}">checked="checked"</c:if>
                       value="yes"/><label for="mode22">دارد</label>
            </div>
            &nbsp;<label>پوشش معافیت</label>
        </td>
        <td></td>
    </tr>

</table>