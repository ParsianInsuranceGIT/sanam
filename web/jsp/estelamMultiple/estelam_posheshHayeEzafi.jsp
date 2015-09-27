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
                    <span class="help"
                          title="مبلغی است که بیمه گر متعهد می شود تا در صورت فوت بیمه شده در خلال مدت بیمه نامه به استفاده کنندگان از بیمه نامه بپردازد"></span>
            <input type="text" name="estelamKhanevade.estelamBimeShodes[0].sarmayePayeFot" id="sarmaye_paye_fot_pers1"
                   class="validate[required,custom[long],custom[sarmaye_paye_fot],funcCall[val_0_2_sarmaye_paye_fot_multiple_pers1],funcCall[val_3_9_sarmaye_paye_fot_multiple_pers1],funcCall[val_10_14_sarmaye_paye_fot_multiple_pers1],funcCall[val_15_19_sarmaye_paye_fot_multiple_pers1],funcCall[val_20_sayer_sarmaye_paye_fot_multiple_pers1]]
                           digitSeparators text-input"
                   value='30,000,000'
                   onchange="var personNo = 1; sarmayePayeFotChangeMultiple(personNo,false);sarmayePoosheshNaghsChangeMultiple(personNo,false);sarmayeAmrazChangeMultiple(personNo,false);"
                   onkeyup="var personNo = 1; sarmayePayeFotChangeMultiple(personNo,true);sarmayePoosheshNaghsChangeMultiple(personNo,true);sarmayeAmrazChangeMultiple(personNo,true);"
                   onkeydown="var personNo = 1; sarmayePayeFotChangeMultiple(personNo,true);sarmayePoosheshNaghsChangeMultiple(personNo,true);sarmayeAmrazChangeMultiple(personNo,true);"/>
            &nbsp;<label>سرمایه پایه فوت (ریال) </label>
        </td>
        <td>
            <span class="help" title="نرخی است که بر اساس آن سرمایه فوت هر ساله رشد خواهد کرد"></span>
            <select name="estelamKhanevade.estelamBimeShodes[0].nerkhAfzayeshSarmayeFot"
                    id="nerkh_afzayesh_salaneh_sarmaye_fot_pers1"
                    class="validate[funcCall[val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple_pers1],funcCall[val_darsad_hagh_bime_va_darsad_sarmaye_fot_multiple_pers1]">
                <option value="0" selected="selected">0 درصد</option>
                <option &lt;
                %&ndash; &ndash;%&gt; value="5">5 درصد</option>
                <option &lt;
                %&ndash;&ndash;%&gt; value="10">10 درصد</option>
                <option &lt;
                %&ndash;&ndash;%&gt; value="15">15 درصد</option>
                <option &lt;
                %&ndash;&ndash;%&gt; value="20">20 درصد</option>
            </select>
            &nbsp;<label>نرخ
            افزایش سالانه سرمایه فوت</label>
        </td>

    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="mode4_pers1">
                <input type="radio" id="mode41_pers1"
                       name="estelamKhanevade.estelamBimeShodes[0].pusheshFotBarAsareHadese"
                       checked="checked"
                       value="no"
                       onchange="if($(this).attr('checked')){var personNo = 1; pushesheFoutDarAsareHadeseNadaradMultiple(personNo);}"/>
                <input type="radio" id="mode42_pers1"
                       name="estelamKhanevade.estelamBimeShodes[0].pusheshFotBarAsareHadese"
                       value="yes"
                       onchange="if($(this).attr('checked')){var personNo = 1; pushesheFoutDarAsareHadeseDaradMultiple(personNo);}"/>
                <label for="mode41_pers1">ندارد</label>
                <label for="mode42_pers1">دارد</label>
            </div>
            &nbsp;
            <label>
                پوشش فوت در اثر حادثه
            </label>
        </td>
        <td>
                    <span class="help"
                          title="معادل سه برابر سرمايه پايه فوت به شرطي كه از 2 ميليارد ريال تجاوز نكند"></span>
            <input type="text" name="estelamKhanevade.estelamBimeShodes[0].sarmayeFotBarAsareHadese"
                   id="sarmaye_paye_fot_dar_asar_hadese_pers1"
                   onchange="var personNo = 1; /*sarmayeNaghsOzvChange();*/sarmayePoosheshNaghsChangeMultiple(personNo);"
                   class="validate[required,custom[long],funcCall[sarmaye_paye_fot_dar_asar_hadese_multiple_pers1]] text-input ui-state-disabled digitSeparators"
                   disabled/>&nbsp;<label>سرمایه پایه فوت در اثر حادثه (ریال)</label>
            <%--value="30,000,000" disabled/>&nbsp;<label>سرمایه پایه فوت در اثر حادثه (ریال)</label>--%>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="mode1_pers1">
                <input type="radio" id="mode11_pers1" name="estelamKhanevade.estelamBimeShodes[0].pusheshNaghseOzv"
                       checked="checked"
                       value="no" disabled="disabled"
                       onchange="
                       if($(this).attr('checked')){$('#sarmaye_pooshesh_naghs_ozv_pers1').val('0');deactivate('sarmaye_pooshesh_naghs_ozv_pers1');}"
                        /><label
                    for="mode11_pers1">ندارد</label>
                <input type="radio" id="mode12_pers1" name="estelamKhanevade.estelamBimeShodes[0].pusheshNaghseOzv"
                       value="yes" disabled="disabled"
                       onchange="if($(this).attr('checked')){
                       activation('sarmaye_pooshesh_naghs_ozv_pers1');
                       var personNo = 1;sarmayePoosheshNaghsChangeMultiple(personNo);
                       }"
                       value = '0'/><label
                    for="mode12_pers1">دارد</label>
            </div>
            &nbsp;<label>پوشش نقص عضو</label>&nbsp; <label style="color: red">(پوشش جدید)</label>
        </td>
        <td>
            <span class="help"
                  title="سرمايه اي است كه در صورت وقوع ريسك نقص عضو متناسب با درصد نقص عضو به بيمه شده پرداخت مي شود"></span>
            <input type="text" name="estelamKhanevade.estelamBimeShodes[0].sarmayeNaghseOzv"
                   id="sarmaye_pooshesh_naghs_ozv_pers1"
                   class="validate[required,custom[long],funcCall[val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple_pers1]] text-input ui-state-disabled digitSeparators"
                   value='0'
                   disabled/>&nbsp;
            <label>سرمایه پوشش نقص عضو (ریال) </label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="mode3_pers1">
                <input type="radio" id="mode31_pers1"
                       name="estelamKhanevade.estelamBimeShodes[0].pusheshFotBarAsareZelzele"
                       checked="checked"
                       value="no" disabled="disabled"/><label for="mode31_pers1">ندارد</label>
                <input type="radio" id="mode32_pers1"
                       name="estelamKhanevade.estelamBimeShodes[0].pusheshFotBarAsareZelzele"
                       value="yes" disabled="disabled"/><label for="mode32_pers1">دارد</label>
            </div>
            &nbsp;<label>
            پوشش فوت در اثر زلزله
        </label>
        </td>
        <td>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="mode5_pers1">
                <input type="radio" id="mode51_pers1" name="estelamKhanevade.estelamBimeShodes[0].pusheshAmrazeKhas"
                       checked="checked"
                       value="no"
                       onchange="if($(this).attr('checked')){$('#sarmaye_pooshesh_amraz_khas_pers1').val('0');deactivate('sarmaye_pooshesh_amraz_khas_pers1');}"/><label
                    for="mode51_pers1">ندارد</label>
                <input type="radio" id="mode52_pers1" name="estelamKhanevade.estelamBimeShodes[0].pusheshAmrazeKhas"
                       value="yes"
                       onchange="var personNo = 1; if($(this).attr('checked')){activation('sarmaye_pooshesh_amraz_khas_pers1');sarmayeAmrazChangeMultiple(personNo,false);}"/><label
                    for="mode52_pers1">دارد</label>
            </div>
            &nbsp;<label>پوشش امراض خاص</label>
        </td>
        <td>
                    <span class="help"
                          title="نشان دهنده سقف سرمایه ای است که در صورت ابتلاء بیمه شده به یکی از امراض خاص در خلال مدت بیمه نامه، به منظور جبران بخشی از هزینه های بیمارستانی پرداخت می شود"></span>
            <input type="text" name="estelamKhanevade.estelamBimeShodes[0].sarmayeAmrazeKhas"
                   id="sarmaye_pooshesh_amraz_khas_pers1"
                   class="validate[required,custom[long],funcCall[val_sarmaye_pushesh_amraaz_khaas_multiple_pers1]] text-input ui-state-disabled digitSeparators"
                   value='0'
                   disabled/>
            &nbsp;<label>سرمایه
            <%--value="9,000,000"/>&nbsp;<label>سرمایه--%>
            پوشش امراض خاص (ریال)</label>
        </td>
    </tr>


</table>
<table id="pooshesh_moafiat_pers1" class="inputList" cellspacing="5" width="90%">
    <col class="inputCol">
    <col class="inputCol">
    <tr>
        <td colspan="2">
            <p style="color: red;">"پوشش معافيت صرفا به بيمه‌شدگان شاغل قابل ارائه مي‌باشد. عناوين خانه‌دار، دانشجو،
                محصل، بازنشسته شغل محسوب نمي‌شوند."</p>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="mode2_pers1">
                <input type="radio" id="mode21_pers1" name="estelamKhanevade.estelamBimeShodes[0].pusheshMoafiat"
                       checked="checked"
                       value="no"/><label for="mode21_pers1">ندارد</label>
                <input type="radio" id="mode22_pers1" name="estelamKhanevade.estelamBimeShodes[0].pusheshMoafiat"
                       value="yes"/><label for="mode22_pers1">دارد</label>
            </div>
            &nbsp;<label>پوشش معافیت</label>
        </td>
        <td></td>
    </tr>
</table>
