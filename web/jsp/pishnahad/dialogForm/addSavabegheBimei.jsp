<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 6/7/11
  Time: 7:44 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    var SB_sarmayeFout;
    var SB_sarmayeHayat;
    function chg_SB_noeBimeName(){
        if($('#SB_noeBimeName').val() == 'به شرط فوت'){
            enableItem('SB_sarmayeFout',SB_sarmayeFout);
            SB_sarmayeHayat = disableItem('SB_sarmayeHayat');
        }else if($('#SB_noeBimeName').val() == 'به شرط حیات'){
            enableItem('SB_sarmayeHayat',SB_sarmayeHayat);
            SB_sarmayeFout = disableItem('SB_sarmayeFout');
        }else{
            enableItem('SB_sarmayeFout',SB_sarmayeFout);
            enableItem('SB_sarmayeHayat',SB_sarmayeHayat);
        }
    }
    var SB_tarikheKhateme;
    var SB_sharheAdameSodor;
    function chg_SB_natijeNahayi(){
        if($('#SB_natijeNahayi').val() == 'صادر شده'){
            enableItem('SB_tarikheKhateme', SB_tarikheKhateme);
            SB_sharheAdameSodor = disableItem('SB_sharheAdameSodor');
        }else{
            SB_tarikheKhateme = disableItem('SB_tarikheKhateme');
            enableItem('SB_sharheAdameSodor', SB_sharheAdameSodor);
        }
    }
    var SB_sherkateBimeGarTozihateSayer;
    function chg_SB_sherkateBimeGar(){
        if($('#SB_sherkateBimeGar').val() == 'سایر'){
            enableItem('SB_sherkateBimeGarTozihateSayer', SB_sherkateBimeGarTozihateSayer);
        }else{
            SB_sherkateBimeGarTozihateSayer = disableItem('SB_sherkateBimeGarTozihateSayer');
        }
    }
</script>
<table class="inputList" border="0" cellspacing="1" cellpadding="5">
    <col class="inputCol"><col class="inputCol">
    <tr>
        <td>
            <span class="noThing"></span>
            <select id="SB_noeBimeName" onchange="chg_SB_noeBimeName();">
                <option>به شرط فوت</option>
                <option selected="selected">به شرط حیات</option>
                <option>به شرط فوت و حیات</option>
            </select>
            &nbsp;<label>نوع بیمه نامه</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type="text" id="SB_nameBimeName" class="validate[required] text-input">
            &nbsp;<label>نام بیمه نامه</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <select id="SB_sherkateBimeGar" onchange="chg_SB_sherkateBimeGar();">
                <c:set var="sherkatHayeBime" value="<%=ConstantForPishnehadForm.ConstantItemKey.SHERKAT_HAYE_BIME%>"/>
                <c:forEach var="row" items="${pishnehadConstants.constantForPishnehadFormList}">
                    <c:if test="${row.constantItemKey == sherkatHayeBime}">
                        <option value="${row.constantItemValue}">${row.constantItemValue}</option>
                    </c:if>
                </c:forEach>
                <option>سایر</option>
            </select>
            &nbsp;<label>شرکت بیمه گر</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type="text" id="SB_sherkateBimeGarTozihateSayer" class="validate[required] text-input ui-state-disabled" disabled="disabled">
            &nbsp;<label>سایر</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <input type="text" id="SB_sarmayeFout" class="validate[required,custom[long]] text-input digitSeparators">
            &nbsp;<label>سرمايه فوت(ريال)</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type="text" id="SB_sarmayeHayat" class="validate[required,custom[long]] text-input digitSeparators">
            &nbsp;<label>سرمايه حيات(ريال)</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <select id="SB_natijeNahayi" onchange="chg_SB_natijeNahayi();">
                <option>صادر شده</option>
                <option>عدم صدور</option>
            </select>
            &nbsp;<label>نتیجه نهایی</label>
        </td>
        <td>
            <input type="text" id="SB_tarikheKhateme" class="validate[required,custom[date]] text-input datePkr">
            &nbsp;<label>تاریخ خاتمه</label>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <span class="noThing"></span>
            <textarea id="SB_sharheAdameSodor" rows="5" cols="77" class="validate[required] ui-state-disabled" disabled="disabled"></textarea>
            &nbsp;<label>شرح عدم صدور</label>
        </td>
    </tr>
</table>