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
            <input type="text" name="estelamKhanevade.estelamBimeShodes[0].name" id="nam_bime_shode_pers1" value=""/>
            &nbsp;<label>نام بيمه
            شده</label>
        </td>
        <td>
            <input type="text" id="tarikh_tavalod_pers1" name="estelamKhanevade.estelamBimeShodes[0].tarikhTavalod"
                   class="datePkr validate[required,custom[date],funcCall[val_seneBimeShode_multiple_pers1]]"
                   onchange="var personNo = 1; calculateSenMultiple(personNo);">&nbsp;<label>تاريخ تولد</label>
        </td>
    </tr>
    <tr>
        <td>

        </td>
        <td>
            <span class="nohelp">&nbsp;</span>
            <input type="text" name="estelamKhanevade.estelamBimeShodes[0].sen_bime_shode_v" id="sen_bime_shode_v_pers1"
                   style="font-weight: bold; color: #e17009;"
            <%--class="ui-state-active"--%>
                   disabled/>&nbsp;<label>سن بيمه شده (سال)</label>
            <input type="hidden" name="estelamKhanevade.estelamBimeShodes[0].sen" id="sen_bime_shode_pers1" value="20"/>
        </td>
    </tr>
</table>