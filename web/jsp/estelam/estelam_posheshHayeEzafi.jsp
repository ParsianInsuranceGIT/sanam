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
<sec:authorize
        ifNotGranted="ROLE_SUPERVISOR,ROLE_ADMIN,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_PEZESHK,ROLE_RAEIS_SODUR,ROLE_PAS_KARSHENAS_MASOUL,ROLE_PAS_KARSHENAS">
    <c:set var="namayesh" value="namayande"/>
</sec:authorize>
<sec:authorize
        ifAnyGranted="ROLE_LIMITED_AMIN_PARSIAN,ROLE_SUPERVISOR,ROLE_ADMIN,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_PEZESHK,ROLE_RAEIS_SODUR,ROLE_PAS_KARSHENAS_MASOUL,ROLE_PAS_KARSHENAS">
    <c:set var="namayesh" value="jadval"/>
</sec:authorize>
<c:if test="${pishnehad != null && pishnehad.state.id == 245}">
    <c:set var="namayesh" value="namayande"/>
</c:if>
<c:if test="${pishnehad == null}">
    <c:set var="namayesh" value="namayande"/>
</c:if>
<c:if test="${isHalateElhaghie}">
    <c:set var="namayesh" value="namayande"/>
</c:if>
<c:if test="${darkhastTaghirat != null && (darkhastTaghirat.state.id == 9010||
            darkhastTaghirat.state.id == 9150||darkhastTaghirat.state.id == 9130||
            darkhastTaghirat.state.id == 9040)}">
<c:set var="namayesh" value="namayande"/>
</c:if>
<c:if test="${darkhastTaghirat != null && (darkhastTaghirat.state.id == 9080||
            darkhastTaghirat.state.id == 9140|| darkhastTaghirat.state.id == 9160||
            darkhastTaghirat.state.id == 9050||darkhastTaghirat.state.id == 9030 )}">
    <c:set var="namayesh" value="namayandeJadval"/>
</c:if>
<c:if test="${namayesh == 'namayande' || namayesh == 'namayandeJadval'}">
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="mode4">
            <c:if test="${!estelam.insertRaeisSarmayeHadese}">
                <input type="radio" id="mode41" name="estelam.pooshesh_fot_dar_asar_haadese"
                       <c:if test="${estelam.pooshesh_fot_dar_asar_haadese != 'yes'}">checked="checked"</c:if>
                       value="no" onchange="if($(this).attr('checked')){pushesheFoutDarAsareHadeseNadarad();}"/>
                <input type="radio" id="mode42" name="estelam.pooshesh_fot_dar_asar_haadese"
                       <c:if test="${estelam.pooshesh_fot_dar_asar_haadese == 'yes'}">checked="checked"</c:if>
                       value="yes" onchange="if($(this).attr('checked')){pushesheFoutDarAsareHadeseDarad();}"/>
                <label for="mode41">ندارد</label>
                <label for="mode42">دارد</label>
            </c:if>
            <c:if test="${estelam.insertRaeisSarmayeHadese}">
                <input type="radio" id="mode42" name="estelam.pooshesh_fot_dar_asar_haadese"
                       <c:if test="${estelam.pooshesh_fot_dar_asar_haadese == 'yes'}">checked="checked"</c:if>
                       value="yes" onchange="if($(this).attr('checked')){pushesheFoutDarAsareHadeseDarad();}"
                       disabled="disabled"/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label for="mode42">دارد</label>

            </c:if>

            </div>
            &nbsp;<label>
            پوشش فوت در اثر حادثه
        </label>
        </td>
        <td>
                    <span class="help"
                          title="معادل سه برابر سرمايه پايه فوت به شرطي كه از 1 ميليارد ريال تجاوز نكند"></span>
            <c:if test="${estelam.insertRaeisSarmayeHadese}">
            <input type="text" name="estelam.sarmaye_paye_fot_dar_asar_hadese" id="sarmaye_paye_fot_dar_asar_hadese"
                   onchange="sarmayeNaghsOzvChange();sarmayePoosheshNaghsChange();"
                   class="validate[required,custom[long]] text-input ui-state-disabled digitSeparators"
                   value='${estelam.sarmaye_paye_fot_dar_asar_hadese != null ? estelam.sarmaye_paye_fot_dar_asar_hadese : "0"}'
                   readonly="readonly"/>
            </c:if>
            <c:if test="${!estelam.insertRaeisSarmayeHadese}">
            <input type="text" name="estelam.sarmaye_paye_fot_dar_asar_hadese" id="sarmaye_paye_fot_dar_asar_hadese"
                   onchange="sarmayeNaghsOzvChange();sarmayePoosheshNaghsChange();"
                   class="validate[required,custom[long],funcCall[sarmaye_paye_fot_dar_asar_hadese]] text-input ui-state-disabled digitSeparators"
                   value='${estelam.sarmaye_paye_fot_dar_asar_hadese != null ? estelam.sarmaye_paye_fot_dar_asar_hadese : "0"}'
                   disabled/>
            </c:if>
            &nbsp;<label>سرمایه پایه فوت در اثر حادثه (ریال)</label>
                <%--value="30,000,000" disabled/>&nbsp;<label>سرمایه پایه فوت در اثر حادثه (ریال)</label>--%>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="mode1">
                <input type="radio" id="mode11" name="estelam.pooshesh_naghs_ozv"
                       <c:if test="${estelam.pooshesh_naghs_ozv != 'yes'}">checked="checked"</c:if>
                       value="no" disabled="disabled"
                       onchange="if($(this).attr('checked')){$('#sarmaye_pooshesh_naghs_ozv').val('0');deactivate('sarmaye_pooshesh_naghs_ozv');}"/><label
                    for="mode11">ندارد</label>
                <input type="radio" id="mode12" name="estelam.pooshesh_naghs_ozv"
                       <c:if test="${estelam.pooshesh_naghs_ozv == 'yes'}">checked="checked"</c:if>
                       value="yes" disabled="disabled"
                       onchange="if($(this).attr('checked')){activation('sarmaye_pooshesh_naghs_ozv');}"/><label
                    for="mode12">دارد</label>
            </div>
            &nbsp;<label>پوشش نقص عضو</label>&nbsp; <label style="color: red">(پوشش جدید)</label>
        </td>
        <td>
            <span class="help"
                  title="سرمايه اي است كه در صورت وقوع ريسك نقص عضو متناسب با درصد نقص عضو به بيمه شده پرداخت مي شود"></span>
            <input type="text" name="estelam.sarmaye_pooshesh_naghs_ozv" id="sarmaye_pooshesh_naghs_ozv"
                   class="validate[required,custom[long],funcCall[val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot]] text-input ui-state-disabled digitSeparators"
                   value='${estelam.sarmaye_pooshesh_naghs_ozv != null ? estelam.sarmaye_pooshesh_naghs_ozv : "0"}'
                   disabled/>&nbsp;
            <label>سرمایه پوشش نقص عضو (ریال) </label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="mode3">
                <input type="radio" id="mode31" name="estelam.pooshesh_fot_dar_asar_zelzele_fake"
                       <c:if test="${estelam.pooshesh_fot_dar_asar_zelzele != 'yes'}">checked="checked"</c:if>
                       value="no" disabled="disabled"/><label for="mode31">ندارد</label>
                <input type="radio" id="mode32" name="estelam.pooshesh_fot_dar_asar_zelzele_fake"
                       <c:if test="${estelam.pooshesh_fot_dar_asar_zelzele == 'yes'}">checked="checked"</c:if>
                       value="yes" disabled="disabled"/><label for="mode32">دارد</label>
            </div>
            &nbsp;<label>
            پوشش فوت در اثر زلزله
        </label>
        </td>
        <td>
                <%--<span class="nohelp"></span>--%>
                <%--<input type="text" name="estelam.sarmaye_paye_fot_dar_asar_hadese" id="sarmaye_paye_fot_dar_asar_hadese"--%>
                <%--class="validate[required,custom[long],custom[sarmaye_paye_fot_dar_asar_hadese]] text-input ui-state-disabled digitSeparators"--%>
                <%--value="0" disabled/>&nbsp;<label>سرمایه پایه فوت در اثر حادثه (ریال)</label>                    --%>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="mode5">
                <input type="radio" id="mode51" name="estelam.pooshesh_amraz_khas"
                       <c:if test="${estelam.pooshesh_amraz_khas != 'yes'}">checked="checked"</c:if>
                       value="no"
                       onchange="if($(this).attr('checked')){$('#sarmaye_pooshesh_amraz_khas').val('0');deactivate('sarmaye_pooshesh_amraz_khas');}"/><label
                    for="mode51">ندارد</label>
                <input type="radio" id="mode52" name="estelam.pooshesh_amraz_khas"
                       <c:if test="${estelam.pooshesh_amraz_khas == 'yes'}">checked="checked"</c:if>
                       value="yes"
                       onchange="if($(this).attr('checked')){activation('sarmaye_pooshesh_amraz_khas');sarmayeAmrazChange(false);}"/><label
                    for="mode52">دارد</label>
            </div>
            &nbsp;<label>پوشش امراض خاص</label>
        </td>
        <td>
                    <span class="help"
                          title="نشان دهنده سقف سرمایه ای است که در صورت ابتلاء بیمه شده به یکی از امراض خاص در خلال مدت بیمه نامه، به منظور جبران بخشی از هزینه های بیمارستانی پرداخت می شود"></span>
            <input type="text" name="estelam.sarmaye_pooshesh_amraz_khas" id="sarmaye_pooshesh_amraz_khas"
                   class="validate[required,custom[long],funcCall[val_sarmaye_pushesh_amraaz_khaas]] text-input ui-state-disabled digitSeparators"
                   value='${estelam.sarmaye_pooshesh_amraz_khas != null ? estelam.sarmaye_pooshesh_amraz_khas : "0"}'
                   disabled/>
            &nbsp;<label>سرمایه
                <%--value="9,000,000"/>&nbsp;<label>سرمایه--%>
            پوشش امراض خاص (ریال)</label>
        </td>
    </tr>
    <%--<c:if test="${pishnehad != null}">--%>
    <tr>
        <td colspan="2">
            <p style="color: red;">"پوشش معافيت صرفا به بيمه‌شدگان شاغل قابل ارائه مي‌باشد. عناوين خانه‌دار، دانشجو،
                محصل، بازنشسته شغل محسوب نمي‌شوند."</p>
        </td>
    </tr>
    <%--</c:if>--%>
    <tr>
        <td>
            <span class="help" title="نشان دهنده تمایل بیمه شده به استفاده از این پوشش بیمه ای می باشد"></span>

            <div class="dblRadio" id="mode2">
                <input type="radio" id="mode21" name="estelam.pooshesh_moafiat"
                       <c:if test="${estelam.pooshesh_moafiat != 'yes'}">checked="checked"</c:if>
                       value="no"/><label for="mode21">ندارد</label>
                <input type="radio" id="mode22" name="estelam.pooshesh_moafiat"
                       <c:if test="${estelam.pooshesh_moafiat == 'yes'}">checked="checked"</c:if>
                       value="yes"/><label for="mode22">دارد</label>
            </div>

            &nbsp;<label>پوشش معافیت</label>
        </td>
        <td></td>
    </tr>
    <tr>
        <td>
            <span class="help"
                  title="نحوه کسر حق بیمه پوشش های اضافی و مالیات بر ارزش افزوده"></span>
            <select name="estelam.nahve_kasr_hagh_bime_poosheshhaye_ezafi" id="nahve_kasr_hagh_bime_poosheshhaye_ezafi">
                <option value="mazad" selected="">مازاد بر حق بیمه پایه</option>
                <option
                        <c:if test="${estelam.nahve_kasr_hagh_bime_poosheshhaye_ezafi == 'az'}">selected="selected"</c:if> value="az">از حق بیمه پایه
                </option>
            </select>&nbsp;<label>نحوه کسر حق بیمه پوشش های اضافی و مالیات بر ارزش افزوده</label>
        </td>
        <td></td>
    </tr>
</c:if>
<c:if test="${namayesh == 'jadval' || namayesh == 'namayandeJadval'}">
        <table class="mystrippedtable jtable" dir="rtl" cellpadding="2px" cellspacing="0px"
               style="border-spacing:1px;margin-left:auto;margin-right:auto;">
            <thead>
            <tr>
                <th>
                    نام پوشش اضافی
                </th>
                <th>
                    جزئیات خطر
                </th>
                <th>
                    سرمایه تعهد
                </th>
                <th>
                    نرخ تعهد
                </th>
                <th>
                    تاریخ شروع
                </th>
                <th>
                    تاریخ پایان
                </th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_haadese == 'yes'}">
                <tr class="odd">
                    <td>
                        حادثه - فوت در اثر حادثه
                    </td>
                    <td>
                        <select id="pishnehad_estelam_tabaghe_khatar" name="pishnehad.estelam.tabaghe_khatar" onchange="submitNewTabagheKhatar();"
                <c:if test="${darkhastBazkharid==null && !khesaratAction}">class="noAnyDisable"</c:if> <c:if test="${pishnehad.bimename != null && pishnehad.valid}">disabled="disabled"</c:if> >
                            <option ${pishnehad.estelam.tabaghe_khatar == '1'?'selected=selected':''} value="1">طبقه خطر
                                ۱
                            </option>
                            <option ${pishnehad.estelam.tabaghe_khatar == '2'?'selected=selected':''} value="2">طبقه خطر
                                ۲
                            </option>
                            <option ${pishnehad.estelam.tabaghe_khatar == '3'?'selected=selected':''} value="3">طبقه خطر
                                ۳
                            </option>
                            <option ${pishnehad.estelam.tabaghe_khatar == '4'?'selected=selected':''} value="4">طبقه خطر
                                ۴
                            </option>
                            <option ${pishnehad.estelam.tabaghe_khatar == '5'?'selected=selected':''} value="5">طبقه خطر
                                ۵
                            </option>
                        </select>
                    </td>
                    <td>${pishnehad.estelam.sarmaye_paye_fot_dar_asar_hadese}</td>
                    <td>-</td>
                    <td><label class="tarikh_shoru_pooshesh">${pishnehad.estelam.pooshesh_fot_tarikh_shoru}</label></td>
                    <td><label class="tarikh_etmam_pooshesh">${pishnehad.estelam.pooshesh_fot_tarikh_payan}</label></td>
                </tr>
            </c:if>
            <c:if test="${pishnehad.estelam.pooshesh_naghs_ozv == 'yes'}">
                <tr class="odd">
                    <td>
                        حادثه - نقص عضو در صورت از کار افتادگی کامل
                    </td>
                    <td>
                        <select id="pishnehad_estelam_tabaghe_khatar_naghsozv" name="pishnehad.estelam.tabaghe_khatar_naghsozv" onchange="submitNewTabagheKhatar();"
                <c:if test="${darkhastBazkharid==null && !khesaratAction}">class="noAnyDisable"</c:if> <c:if test="${pishnehad.bimename != null && pishnehad.valid}">disabled="disabled"</c:if> >
                            <option ${pishnehad.estelam.tabaghe_khatar_naghsozv == '1'?'selected=selected':''} value="1">طبقه خطر
                                ۱
                            </option>
                            <option ${pishnehad.estelam.tabaghe_khatar_naghsozv == '2'?'selected=selected':''} value="2">طبقه خطر
                                ۲
                            </option>
                            <option ${pishnehad.estelam.tabaghe_khatar_naghsozv == '3'?'selected=selected':''} value="3">طبقه خطر
                                ۳
                            </option>
                            <option ${pishnehad.estelam.tabaghe_khatar_naghsozv == '4'?'selected=selected':''} value="4">طبقه خطر
                                ۴
                            </option>
                            <option ${pishnehad.estelam.tabaghe_khatar_naghsozv == '5'?'selected=selected':''} value="5">طبقه خطر
                                ۵
                            </option>
                        </select>
                    </td>
                    <td>${pishnehad.estelam.sarmaye_pooshesh_naghs_ozv}</td>
                    <td>-</td>
                    <td><label class="tarikh_shoru_pooshesh">${pishnehad.estelam.pooshesh_naghs_tarikh_shoru}</label>
                    </td>
                    <td><label class="tarikh_etmam_pooshesh">${pishnehad.estelam.pooshesh_naghs_tarikh_payan}</label>
                    </td>
                </tr>
            </c:if>
            <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_zelzele == 'yes'}">
                <tr class="odd">
                    <td>
                        حادثه - فوت در اثر زلزله
                    </td>
                    <td>
                        -
                    </td>
                    <td></td>
                    <td>-</td>
                    <td><label class="tarikh_shoru_pooshesh">${pishnehad.estelam.pooshesh_zelzele_tarikh_shoru}</label>
                    </td>
                    <td><label class="tarikh_etmam_pooshesh">${pishnehad.estelam.pooshesh_zelzele_tarikh_payan}</label>
                    </td>
                </tr>
            </c:if>
            <c:if test="${pishnehad.estelam.pooshesh_amraz_khas == 'yes'}">
                <tr class="odd">
                    <td>
                        امراض خاص - با پیوند اعضا
                    </td>
                    <td>
                        <select name="pishnehad.estelam.joziyate_pooshesh_amraz_khas" disabled="disabled">
                            <option>با پيوند اعضا</option>
                            <option>بدون پيوند اعضا</option>
                        </select>
                    </td>
                    <td>${pishnehad.estelam.sarmaye_pooshesh_amraz_khas}</td>
                    <td>-</td>
                    <td><label class="tarikh_shoru_pooshesh">${pishnehad.estelam.pooshesh_amraz_tarikh_shoru}</label>
                    </td>
                    <td><label class="tarikh_etmam_pooshesh">${pishnehad.estelam.pooshesh_amraz_tarikh_payan}</label>
                    </td>
                </tr>
            </c:if>
            <c:if test="${pishnehad.estelam.pooshesh_moafiat == 'yes'}">
                <tr class="odd">
                    <td>
                        معافیت از پرداخت اقساط مانده حق بیمه
                    </td>
                    <td>
                        <select name="pishnehad.estelam.joziyate_pooshesh_moafiat" disabled="disabled">
                            <option>بيمه‌گذار</option>
                            <option>بيمه‌شده</option>
                        </select>
                    </td>
                    <td>-</td>
                    <td>-</td>
                    <td><label class="tarikh_shoru_pooshesh">${pishnehad.estelam.pooshesh_amraz_tarikh_shoru}</label>
                    </td>
                    <td><label class="tarikh_etmam_pooshesh">${pishnehad.estelam.pooshesh_amraz_tarikh_shoru}</label>
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
        <br/>
        <label>نحوه کسر حق بیمه پوشش های اضافی و مالیات بر ارزش افزوده</label>
        <select name="nahve_kasr_hagh_bime_poosheshhaye_ezafi" id="nahve_kasr_hagh_bime_poosheshhaye_ezafi" disabled="disabled">
                <option value="mazad" selected="">مازاد بر حق بیمه پایه</option>
                <option
                        <c:if test="${estelam.nahve_kasr_hagh_bime_poosheshhaye_ezafi == 'az'}">selected="selected"</c:if> value="az">از حق بیمه پایه
                </option>
            </select><br/>
</c:if>
</table>
