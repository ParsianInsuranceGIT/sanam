<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 6/8/11
  Time: 9:09 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="inputList" cellspacing="5" width="90%">
    <col class="inputCol">
    <col class="inputCol">
    <%--<td><input type="text" name="darsad_takhfif_bimegari" id="darsad_takhfif_bimegari"--%>
    <%--class="validate[required,custom[rangeDarsad]] text-input ui-state-disabled" value="0" disabled/>&nbsp;<label>درصد تخفیف هزینه--%>
    <%--بیمه گری</label>--%>
    <%--</td>--%>
    <%--<tr>--%>
    <%--<td><input type="text" name="darsad_takhfif_edari" id="darsad_takhfif_edari"--%>
    <%--class="validate[required,custom[rangeDarsad]] text-input ui-state-disabled" value="0" disabled/>&nbsp;<label></label> درصد تخفیف--%>
    <%--هزینه اداری--%>
    <%--</td>--%>

    <%--<td><input type="text" name="darsad_takhfif_karmozd_hagh" id="darsad_takhfif_karmozd_hagh"--%>
    <%--class="validate[required,custom[rangeDarsad]] text-input ui-state-disabled" value="0" disabled/>&nbsp;<label>درصد تخفیف--%>
    <%--هزینه کارمزد (حق بیمه) </label>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <tr>
        <td><input type="text" name="estelam.darsad_takhfif_karmozd_karmozd" id="darsad_takhfif_karmozd_karmozd"
                   value='${estelam.darsad_takhfif_karmozd_karmozd != null ? estelam.darsad_takhfif_karmozd_karmozd : "0"}' class="validate[required,custom[rangeDarsad]] text-input ui-state-disabled" disabled/>&nbsp;<label>درصد تخفیف
            هزینه کارمزد (کارمزد) </label>
        </td>
        <td><input type="text" name="estelam.darsad_takhfif_vosool" id="darsad_takhfif_vosool"
                   value='${estelam.darsad_takhfif_vosool != null ? estelam.darsad_takhfif_vosool : "0"}' class="validate[required,custom[rangeDarsad]] text-input ui-state-disabled" disabled/>&nbsp;<label>درصد تخفیف هزینه
            وصول</label>
        </td>
        <td></td>
    </tr>
</table>