<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 3/9/11
  Time: 11:54 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var funcToBeCall;
    var dilg;
    function entekhabShakhs(id){
        $(dilg).dialog('close');
        funcToBeCall(id);
        enseraf();
    }
    function entekhabHoghughi(id){
        $(dilg).dialog('close');
        funcToBeCall(id);
        enserafHoghughi();
    }
    function josteju(bimeShode) {
        if($.validationEngine.submitValidation($("#josteju")) === false){
            $('#searchBimeShode').val(bimeShode);
            var mainForm2 = $('#jostejuForm').serialize();
            $.post('/jostejuMoshakhasaat', mainForm2, function(msg) {
                $('#jostejuResult').html(msg);
            });
        }
    };
    function jostejuHoghughi() {
//        $.validationEngine.onSubmitValid = true;
        if($.validationEngine.submitValidation(this) === false){
            var mainForm2 = $('#jostejuHoghughiForm').serialize();
            $.post('/jostejuHoghughiMoshakhasaat', mainForm2, function(msg) {
                $('#jostejuHoghughiResult').html(msg);
            });
        }
    };
    function enseraf(){
        $.validationEngine.closePrompt('.formError', true);
        $('#search_code_melli').val('');
        $('#search_shomare_shenaasnaame').val('');
        $('#jostejuResult').html('');
    };
    function enserafHoghughi(){
        $.validationEngine.closePrompt('.formError', true);
        $('#search_nam_sherkat').val('');
        $('#search_shomare_sabt').val('');
        $('#jostejuHoghughiResult').html('');
    };
    function openJosteju(bimeShode, funcToCall){
        funcToBeCall = funcToCall;
        $.validationEngine.closePrompt('.formError', true);
        dilg = $('#josteju').dialog({
            modal:true,
            width: 840,
            resizable:false,
            closeText: "",
            title:"جستجو",
            close:enseraf,
            buttons: {
                "جستجو": function(){josteju(bimeShode);},
                "انصراف": function(){enseraf();$(this).dialog("close");}
            }
        });
    };
    function openJostejuHoghughi(funcToCall){

        funcToBeCall = funcToCall;
        $.validationEngine.closePrompt('.formError', true);
        dilg = $('#jostejuHoghughi').dialog({
            modal:true,
            width: 840,
            resizable:false,
            closeText: "",
            title:"جستجو",
            close:enseraf,
            buttons: {
                "جستجو": jostejuHoghughi,
                "انصراف": function(){enserafHoghughi();$(this).dialog("close");}
            }
        });
    };
</script>
<div id="josteju" style="display:none;">
    <form id="jostejuForm" method="post" action="/jostejuMoshakhasaat">
        <table class="inputList" width="90%">
            <tr>
                <c:if test="${pishnehad.id != null && isHalateElhaghie}">
                    <input type="hidden" name="halateElhaghie" value="${isHalateElhaghie}"/>
                </c:if>
                <input type="hidden" name="naam" id="naam"/>
                <input type="hidden" name="naam_khaanevaadegi" id="naam_khaanevaadegi"/>
                <input type="hidden" name="searchBimeShode" id="searchBimeShode"/>
                <input type="hidden" name="naam_pedar" id="naam_pedar"/>
                <td style="width: 40%;">
                    <span class="noThing"></span>
                    <input type="text" name="code_melli" id="search_code_melli"
                           class="validate[required,custom[integer]] text-input" value=""/>
                    &nbsp;<label>کدملی یا شماره شناسایی</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <%--<input type="text" name="shomare_shenaasnaame" id="search_shomare_shenaasnaame"--%>
                           <%--class="validate[required,custom[integer]] text-input" value=""/>--%>
                    <%--&nbsp;<label>شماره شناسنامه</label>--%>
                </td>
            </tr>
        </table>
        <div id="jostejuResult"></div>
    </form>
</div>

<div id="jostejuHoghughi" style="display:none;">
    <form id="jostejuHoghughiForm" method="post" action="/jostejuHoghughiMoshakhasaat">
        <table class="inputList" width="90%">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="naam" id="search_nam_sherkat"
                           class="validate[required] text-input" value=""/>
                    &nbsp;<label>نام شرکت</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="shomareSabt" id="search_shomare_sabt"
                           class="validate[required,custom[integer]] text-input" value=""/>
                    &nbsp;<label>شماره ثبت</label>
                </td>
            </tr>
        </table>
        <div id="jostejuHoghughiResult"></div>
    </form>
</div>