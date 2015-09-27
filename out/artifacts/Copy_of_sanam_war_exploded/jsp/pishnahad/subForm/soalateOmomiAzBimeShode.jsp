<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 4/7/11
  Time: 2:25 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" name="pishnehad.soalateOmomiAzBimeShode.id" value="${pishnehad.soalateOmomiAzBimeShode.id}">
<table class=inputList border="0" cellspacing="5" cellpadding="1" style="width:99%">
    <col class=inputCol><col class=inputCol>
    <tr>
        <td>
            <input type=text name="pishnehad.soalateOmomiAzBimeShode.ghad_bime_shode" id="ghad_bime_shode" value='${pishnehad.soalateOmomiAzBimeShode.ghad_bime_shode}'
                   class="validate[required,custom[integer]] text-input"/>
            &nbsp;<label>قد بیمه شده (سانتی            متر)</label>
        </td>
        <td>
            <input type=text name="pishnehad.soalateOmomiAzBimeShode.vazn_bime_shode" id="vazn_bime_shode" value='${pishnehad.soalateOmomiAzBimeShode.vazn_bime_shode}'
                   class="validate[required,custom[integer]] text-input"/>
            &nbsp;<label>وزن بیمه شده            (کیلوگرم)</label>
        </td>
    </tr>
    <c:if test="${pishnehad.bimeShode.shakhs.jensiat == 'مرد' || pishnehad==null}">
    <tr id="trfornezamvazife">
        <td>
            <select name="pishnehad.soalateOmomiAzBimeShode.khdemat_nezaam_vazife" id="khdemat_nezaam_vazife" onchange="if(this.value == '2'||this.value == '5'){$('#moaafiyat_pezeshki_lbl').show();activate('moaafiyat_pezeshki');}else{$('#moaafiyat_pezeshki_lbl').hide();deactivate('moaafiyat_pezeshki');}">
                <option value="1" <c:if test="${pishnehad.soalateOmomiAzBimeShode.khdemat_nezaam_vazife == '1'}">selected="selected"</c:if>>داراي كارت پايان خدمت</option>
                <option value="2" <c:if test="${pishnehad.soalateOmomiAzBimeShode.khdemat_nezaam_vazife == '2'}">selected="selected"</c:if>>معافيت پزشكي</option>
                <option value="3" <c:if test="${pishnehad.soalateOmomiAzBimeShode.khdemat_nezaam_vazife == '3'}">selected="selected"</c:if>>معافيت غير پزشكي</option>
                <option value="4" <c:if test="${pishnehad.soalateOmomiAzBimeShode.khdemat_nezaam_vazife == '4'}">selected="selected"</c:if>>غير مشمول</option>
                <option value="5" <c:if test="${pishnehad.soalateOmomiAzBimeShode.khdemat_nezaam_vazife == '5'}">selected="selected"</c:if>>سایر</option>
            </select>
            &nbsp;<label>وضعيت نظام وظيفه</label>
        </td>
        <td>
            <input type=text name="pishnehad.soalateOmomiAzBimeShode.moaafiyat_pezeshki" id="moaafiyat_pezeshki" value='${pishnehad.soalateOmomiAzBimeShode.moaafiyat_pezeshki}'
                   class="validate[required] text-input ui-state-disabled" disabled="disabled"/>
            &nbsp;<label id="moaafiyat_pezeshki_lbl">در صورت سایر بودن توضیحات آن، و در صورت معافیت پزشکی علت آن را توضیح دهید</label>
        </td>
    </tr>
    </c:if>
    <tr>
        <td colspan="2">
            <input type="text" name="pishnehad.soalateOmomiAzBimeShode.tozih_faaliyat_janbi" id="tozih_faaliyat_janbi" value="${pishnehad.soalateOmomiAzBimeShode.tozih_faaliyat_janbi}" style="width:532px">
            &nbsp;<label>فعاليت جنبي مانند ورزش حرفه اي و مسافرت </label>
        </td>
    </tr>
</table>