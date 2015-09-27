<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 6/8/11
  Time: 9:07 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="inputList" cellspacing="5" width="90%">
    <col class="inputCol">
    <col class="inputCol">
    <tr>
        <td>
            <input type="hidden" id="validation_darsad_mojaz_afzayesh_sarmaye_fot"/>
            <input type="hidden" id="validation_max_majmo_modatvasen"/>
            <input type="hidden" id="validation_min_sen_bimeshode"/>
            <input type="hidden" id="validation_max_sen_bimeshode"/>
            <input type="hidden" id="validation_mahdode_paye_sarmaye_fot"/>
            <input type="hidden" id="validation_min_hagh_bime_pardakhti_mah"/>
        </td>
    </tr>
    <tr>
        <td>
                    <span class="help"
                          title="وجهی است که بیمه گذار به منظور ارائه پوشش بیمه ای باید به بیمه گر بپردازد"></span>
            <input type="text" name="estelam.hagh_bime_pardakhti" id="hagh_bime_pardakhti"
                   class="validate[required,custom[long],funcCall[val_mahane_hagh_bime_pardakhti],funcCall[val_3mahe_hagh_bime_pardakhti],funcCall[val_6mahe_hagh_bime_pardakhti],funcCall[val_salane_hagh_bime_pardakhti]]
                           text-input digitSeparators" onkeyup="extraValidation1();"
                   value='${estelam.hagh_bime_pardakhti != null ? estelam.hagh_bime_pardakhti : "300,000"}'/>&nbsp;
            <label>مبلغ حق بيمه منظم پرداختي (ريال)</label>
        </td>
        <td>
            <span class="help" title="مبلغي است كه بيمه گذار مي تواند علاوه بر مبالغ حق بيمه، به عنوان يك سپرده در ابتداي قرارداد پرداخت نمايد"></span>
            <input type="text" name="estelam.mablagh_seporde_ebtedaye_sal" id="mablagh_seporde_ebtedaye_sal" <c:if test="${darkhastBazkharid==null && !khesaratAction}">onkeyup="extraValidation1();"</c:if>
                   value='${estelam.mablagh_seporde_ebtedaye_sal != null ? estelam.mablagh_seporde_ebtedaye_sal : "0"}' class="validate[required,custom[long]] text-input digitSeparators"/>&nbsp;
            <label>مبلغ حق بيمه اوليه(ريال)</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نرخی است که بر اساس آن حق بیمه هر ساله رشد خواهد کرد"></span>
            <select name="estelam.nerkh_afzayesh_salaneh_hagh_bime" id="nerkh_afzayesh_salaneh_hagh_bime" <sec:authorize ifAnyGranted="ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE"> disabled="disabled" </sec:authorize>
                    class="validate[funcCall[val_darsad_hagh_bime_va_darsad_sarmaye_fot]]">
                <option value="0">0 درصد</option>
                <option <c:if test="${estelam.nerkh_afzayesh_salaneh_hagh_bime == '5'}">selected="selected"</c:if> value="5">5 درصد</option>
                <option <c:if test="${estelam.nerkh_afzayesh_salaneh_hagh_bime == '10'}">selected="selected"</c:if> value="10">10 درصد</option>
                <option <c:if test="${estelam.nerkh_afzayesh_salaneh_hagh_bime == '15'}">selected="selected"</c:if> value="15">15 درصد</option>
                <option <c:if test="${estelam.nerkh_afzayesh_salaneh_hagh_bime == '20'}">selected="selected"</c:if> value="20">20 درصد</option>
            </select>
            &nbsp;<label>                    نرخ                    افزایش سالانه حق بیمه</label>
        </td>

        <td>
                    <span class="help"
                          title="نشان دهنده چگونگی پرداخت حق بیمه توسط بیمه گذار در خلال مدت بیمه نامه می باشد"></span>
            <select name="estelam.nahve_pardakht_hagh_bime" id="nahve_pardakht_hagh_bime"
                    onchange="if(this.value == 'yekja'){ $('#mode2').buttonset().buttonset('disable');$('#mode2').buttonset().buttonset('refresh');
            deactivateArray(new Array ('mode2','hagh_bime_pardakhti')); $('#hagh_bime_pardakhti').val('0');} else{ activateArray(new Array('mode2','hagh_bime_pardakhti'));$('#mode2').buttonset().buttonset('enable');$('#mode2').buttonset().buttonset('refresh');}">
            >
                <option <c:if test="${estelam.nahve_pardakht_hagh_bime == 'mah'}">selected="selected"</c:if> value="mah">ماهانه</option>
                <option <c:if test="${estelam.nahve_pardakht_hagh_bime == '3mah'}">selected="selected"</c:if> value="3mah">سه ماهه</option>
                <option <c:if test="${estelam.nahve_pardakht_hagh_bime == '6mah'}">selected="selected"</c:if> value="6mah">شش ماهه</option>
                <option <c:if test="${estelam.nahve_pardakht_hagh_bime == 'sal'}">selected="selected"</c:if> value="sal">سالانه</option>
                <option <c:if test="${estelam.nahve_pardakht_hagh_bime == 'yekja'}">selected="selected"</c:if> value="yekja">یکجا</option>
            </select>&nbsp;<label>نحوه پرداخت حق بیمه</label>
        </td>
    </tr>
    <tr>
        <td>
                    <span class="help"
                          title="مبلغی است که بیمه گر متعهد می شود تا در صورت فوت بیمه شده در خلال مدت بیمه نامه به استفاده کنندگان از بیمه نامه بپردازد"></span>
            <input type="text" name="estelam.sarmaye_paye_fot" id="sarmaye_paye_fot"
                   class="validate[required,custom[long],custom[sarmaye_paye_fot],funcCall[val_mahdodeye_sarmaye_fot]]
                           digitSeparators text-input"
                   value='<c:if test="${estelam.sarmaye_paye_fot != null}"><c:out value="${estelam.sarmaye_paye_fot}"/></c:if>
                   <c:if test="${estelam.sarmaye_paye_fot == null && ijadPishnehad}">0</c:if>
                   <c:if test="${estelam.sarmaye_paye_fot == null && !ijadPishnehad}">30,000,000</c:if>'
                   onchange="sarmayePayeFotChange(false);sarmayePoosheshNaghsChange(false);sarmayeAmrazChange(false);" onkeyup="sarmayePayeFotChange(true);sarmayePoosheshNaghsChange(true);sarmayeAmrazChange(true);" onkeydown="sarmayePayeFotChange(true);sarmayePoosheshNaghsChange(true);sarmayeAmrazChange(true);"/>
            &nbsp;<label>سرمایه پایه فوت (ریال) </label>
        </td>
        <td>
            <span class="help" title="نرخی است که بر اساس آن سرمایه فوت هر ساله رشد خواهد کرد"></span>
            <select name="estelam.nerkh_afzayesh_salaneh_sarmaye_fot" id="nerkh_afzayesh_salaneh_sarmaye_fot"
                    class="validate[funcCall[val_darsadMojazAfzayeshSarmayeFot],funcCall[val_nerkh_afzayesh_salaneh_sarmaye_fot],funcCall[val_darsad_hagh_bime_va_darsad_sarmaye_fot]">
                <option value="0">0 درصد</option>
                <option <c:if test="${estelam.nerkh_afzayesh_salaneh_sarmaye_fot == '5'}">selected="selected"</c:if> value="5">5 درصد</option>
                <option <c:if test="${estelam.nerkh_afzayesh_salaneh_sarmaye_fot == '10'}">selected="selected"</c:if> value="10">10 درصد</option>
                <option <c:if test="${estelam.nerkh_afzayesh_salaneh_sarmaye_fot == '15'}">selected="selected"</c:if> value="15">15 درصد</option>
                <option <c:if test="${estelam.nerkh_afzayesh_salaneh_sarmaye_fot == '20'}">selected="selected"</c:if> value="20">20 درصد</option>
            </select>
            &nbsp;<label>نرخ
            افزایش سالانه سرمایه فوت</label>
        </td>
    </tr>
    <tr>
        <sec:authorize ifAllGranted="ROLE_USER">
            <sec:authorize ifNotGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB,ROLE_USER_KARTABL">
                    <td>
                        <sec:authorize ifNotGranted="ROLE_SUPERVISOR,ROLE_ADMIN,ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">
                            <c:if test="${darkhastBazkharid==null && !khesaratAction}">
                                <span class="ui-icon ui-icon-disk" style="margin-top: 4px; float: left; margin-right: 8px;" <c:if test="${!khesaratAction && pishnehad != null}">onclick="saveEzafeNerkhAjaxly();"</c:if>></span>
                            </c:if>
                        </sec:authorize>
                        <span class="noThing"></span>
                        <label>اضافه نرخ پزشکی</label>
                        <input type="text" value="${estelam.darsad_ezafe_nerkh_pezeshki}" name="estelam.darsad_ezafe_nerkh_pezeshki"
                               <c:if test="${darkhastBazkharid==null && !khesaratAction}"></c:if> id="estelam_darsad_ezafe"
                            <sec:authorize ifAnyGranted="ROLE_SUPERVISOR,ROLE_ADMIN,ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE">
                                             readonly="readonly"
                            </sec:authorize>
                        >
                    </td>
        </sec:authorize>
    </sec:authorize>
        <td>
            <span class="help" title="به مدت زمان بين تاريخ شروع و تاريخ انقضاي بيمه نامه اطلاق مي شود"></span>
            <input type="text" id="modat_bimename" name="estelam.modat_bimename"
                   class="validate[required,custom[modat_bime_naame_30],funcCall[majmoeSenVaModdateBimeName],custom[modat_bime_naame_5] text-input"
                   value='${estelam.modat_bimename != null ? estelam.modat_bimename : "20"}'/>
            &nbsp;<label>مدت بیمه نامه (سال)</label>
        </td>
    </tr>

    <c:if test="${pishnehad.bimename != null}">
        <tr>
            <td>
                <span class="noThing"></span>
                <label>تاريخ شروع بيمه‌نامه</label>
                &nbsp;<input type="text" value="${pishnehad.bimename.tarikhShorou}" readonly="readonly">
            </td>
            <td>
                <span class="noThing"></span>
                <label>تاريخ انقضا</label>
                &nbsp;<input type="text" value="${pishnehad.bimename.tarikhEngheza}" readonly="readonly">
            </td>
        </tr>
    </c:if>
    <c:if test="${inEstelam}">
        <tr>
            <td>
                <span class="noThing"></span>
                <label>طرح</label>
                &nbsp;
                <select name="estelam.pishnehad.tarh.id" onchange="getValidationConstAjax();" id="pishnehad_nameTarh">
                    <option value=""></option>
                    <s:iterator value="tarhs" id="row" status="sts">
                        <s:if test="#row.showForEstelam">
                            <option value="<s:property value="#row.getId()"/>"><s:property value="#row.getName()"/></option>
                        </s:if>
                    </s:iterator>
                </select>
            </td>
        </tr>
    </c:if>
</table>

<script>
    getValidationConstAjax();
</script>