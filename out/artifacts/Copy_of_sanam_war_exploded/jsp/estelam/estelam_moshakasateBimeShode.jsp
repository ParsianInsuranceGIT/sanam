<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 6/8/11
  Time: 9:06 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="inputList" cellspacing="5" width="90%">
    <col class="inputCol">
    <col class="inputCol">
    <tr>
        <td>
                        <span class="help"
                              title="شخصي است که فوت يا حيات او موضوع قرارداد بيمه است و سن، شغل و وضعيت سلامتي وي مبناي محاسبه حق بيمه خواهد بود"></span>
            <input type="text" name="estelam.nam_bime_shode" id="nam_bime_shode" value=""/>
            &nbsp;<label>نام بيمه
            شده</label>
        </td>
        <td>
            <input type="text" id="tarikh_tavalod" name="estelam.tarikh_tavalod"
                   <%--value="${estelam.tarikh_tavalod}" class="validate[required,custom[date]] text-input datePkr"--%>
                   value="${estelam.tarikh_tavalod}" class="datePkr validate[required,custom[date]]"
                   onchange="calculateSen();">&nbsp;<label>تاريخ تولد</label>
        </td>
    </tr>
    <tr>
        <td>
                        <%--<span class="help"--%>
                              <%--title="نشان دهنده درجه ريسک بيمه شده به لحاظ سلامتي بر حسب درصد مي باشد"></span>--%>
            <%--<input type="text" name="estelam.darsad_ezafe_nerkh_pezeshki" id="darsad_ezafe_nerkh_pezeshki"--%>
                   <%--value="0"/>&nbsp;<label>درصد اضافه نرخ--%>
            <%--پزشکي</label>--%>
        </td>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type="text" id="sen_bime_shode_vv" disabled="disabled" />
            <input type="hidden" name="estelam.sen_bime_shode" id="sen_bime_shode_v"/>
            &nbsp;<label>سن بيمه شده (سال)</label>
            <input type="hidden" name="estelam.sen_bime_shode_v" id="sen_bime_shode" value="20"/>
        </td>
    </tr>
</table>