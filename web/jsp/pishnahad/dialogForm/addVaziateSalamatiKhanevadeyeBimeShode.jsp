<%@ page import="com.bitarts.parsian.model.constantItems.ConstantForPishnehadForm" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 6/7/11
  Time: 8:56 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
    function chg_VSKB_vaziateHayat(){
        var VSKB_senneDarHayat,VSKB_vaziateSalamati,VSKB_senneDarZamaneFout,VSKB_ellateFout,VSKB_sharheEllateFout;
        if($('#VSKB_vaziateHayat').val() == 'در قید حیات'){
            enableItem('VSKB_senneDarHayat', VSKB_senneDarHayat);
            enableItem('VSKB_vaziateSalamati', VSKB_vaziateSalamati);
            VSKB_senneDarZamaneFout = disableItem('VSKB_senneDarZamaneFout');
            VSKB_ellateFout = disableItem('VSKB_ellateFout');
            VSKB_sharheEllateFout = disableItem('VSKB_sharheEllateFout');

            $('#VSKB_darGheideHayatFileds_tr').show();
            $('#VSKB_foutShodeFields_tr_1').hide();
            $('#VSKB_foutShodeFields_tr_2').hide();
        }else{
            VSKB_senneDarHayat = disableItem('VSKB_senneDarHayat');
            VSKB_vaziateSalamati = disableItem('VSKB_vaziateSalamati');
            enableItem('VSKB_senneDarZamaneFout', VSKB_senneDarZamaneFout);
            enableItem('VSKB_ellateFout', VSKB_ellateFout);
            enableItem('VSKB_sharheEllateFout', VSKB_sharheEllateFout);

            $('#VSKB_darGheideHayatFileds_tr').hide();
            $('#VSKB_foutShodeFields_tr_1').show();
            $('#VSKB_foutShodeFields_tr_2').show();
        }
    }
</script>
<table class="inputList" border="0" cellspacing="1" cellpadding="5">
    <col class="inputCol"><col class="inputCol">
    <tr>
        <td>
            <span class="noThing"></span>
            <select id="VSKB_nesbatBabimeShode">
                <c:forEach var="row" items="${pishnehadConstants.constantForPishnehadFormList}">
                    <c:if test="${row.constantItemKey == nesbatBaBimeShode}">
                        <c:if test="${row.constantItemValue != 'خود شخص'}">
                            <option value="${row.constantItemValue}" ${row.constantItemValue == 'پدر'?'selected=selected':''}>${row.constantItemValue}</option>
                        </c:if>
                    </c:if>
                </c:forEach>
            </select>
            &nbsp;<label>نسبت با بیمه شده</label>
        </td>
        <td>
            <span class="noThing"></span>
            <select id="VSKB_vaziateHayat" onchange="chg_VSKB_vaziateHayat();">
                <option>در قید حیات</option>
                <option>فوت شده</option>
            </select>
            &nbsp;<label>وضعيت حيات</label>
        </td>
    </tr>
    <tr id="VSKB_darGheideHayatFileds_tr">
        <td>
            <span class="noThing"></span>
            <input type="text" id="VSKB_senneDarHayat" class="validate[required,custom[onlyNumber],custom[noZero]] text-input">
            &nbsp;<label>سن در حيات</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type="text" id="VSKB_vaziateSalamati" class="validate[required,custom[onlyPersianLetter]] text-input">
            &nbsp;<label>وضعيت سلامتي</label>
        </td>
    </tr>
    <tr id="VSKB_foutShodeFields_tr_1">
        <td>
            <span class="noThing"></span>
            <input type="text" id="VSKB_senneDarZamaneFout" class="validate[required,custom[onlyNumber],custom[noZero]] text-input ui-state-disabled" disabled="disabled">
            &nbsp;<label>سن در زمان فوت</label>
        </td>
        <td>
            <span class="noThing"></span>
            <select id="VSKB_ellateFout" class="ui-state-disabled" disabled="disabled">
                <option></option>
                <option>سرطان</option>
                <option>سکته قلبی</option>
                <option>سکته مغزی</option>
                <option>سایر</option>
            </select>
            &nbsp;<label>علت فوت</label>
        </td>
    </tr>
    <tr id="VSKB_foutShodeFields_tr_2">
        <td colspan="2">
            <span class="noThing"></span>
            <textarea id="VSKB_sharheEllateFout" rows="5" cols="78" class="validate[required] text-input ui-state-disabled" disabled="disabled"></textarea>
            &nbsp;<label>شرح علت فوت</label>
        </td>
    </tr>
</table>