<%--
  Created by IntelliJ IDEA.
  User: Arron1
  Date: 8/17/11
  Time: 5:37 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var sabteBedehiDastiData;
    function chg_bedehiType(type){
        if(type == '<%=Credebit.CredebitType.PARDAKHTE_TANKHAH%>'){
            $('#checkBedehiInfo').hide();
            $('#tankhahBedehiInfo').show();
            $('#hesabFimabeynBedehiInfo').hide();
            $('#checkBedehiInfo #amountTajamoi').attr('disabled', true);
            $('#tankhahBedehiInfo #amount').removeAttr('disabled');
            sabteBedehiDastiData = $('#sabteBedehiDasti_tankhah').serialize();
        }else if(type == '<%=Credebit.CredebitType.PARDAKHTE_CHECK%>'){
            $('#checkBedehiInfo').show();
            $('#tankhahBedehiInfo').hide();
            $('#hesabFimabeynBedehiInfo').hide();
            $('#checkBedehiInfo #amountTajamoi').removeAttr('disabled');
            $('#tankhahBedehiInfo #amount').attr('disabled', true);
            chg_shomareCheck();
            sabteBedehiDastiData = $('#sabteBedehiDasti_check').serialize();
        }else if(type == '<%=Credebit.CredebitType.HESAB_FI_MA_BEYN_BEDEHI%>'){
            $('#checkBedehiInfo').hide();
            $('#tankhahBedehiInfo').hide();
            $('#hesabFimabeynBedehiInfo').show();
            $('#checkBedehiInfo #amountTajamoi').removeAttr('disabled');
            $('#tankhahBedehiInfo #amount').attr('disabled', true);
            sabteBedehiDastiData = $('#sabteBedehiDasti_hesabFimabeyn').serialize();
        }
    }

    function chg_shomareCheck(){
        var t = $('#shomareCheck :selected').attr('id');
        if(t){
            var splited = $('#shomareCheck :selected').attr('id').split('_');
            $('#darVajhe').val(splited[1]);
            $('#shomareHesabeBanki').val(splited[2]);
            $('#chackTarikh').val(splited[3]);
            $('#mameDasteCheck').val(splited[4]);
        }
    }
    function clearAllBedehi(){
        $('#noeBedehieDarHaleSabt').val("");
        $('#shomareCheck2').val("");
        $('#amountTajamoi').val("");
        $('#girande').val("");
        $('#amount').val("");
        $('#tarikh').val("");
        $('#tozihat').val("");
    }

    function loadBedehiCheck(msg){
        var myAttributes = msg.split("@")[0].split("#");

//        if (myAttributes[0].split("=")[1].contains("PARDAKHTE_TANKHAH")){
//            $('#noeBedehieDarHaleSabt').val('پرداخت از محل تنخواه');
//        }else{
//            $('#noeBedehieDarHaleSabt').val("پرداخت چک");
//        }

        $('#noeBedehieDarHaleSabt').val(myAttributes[0].split("=")[1].trim());

        $('#shomareChequeTemp').val(myAttributes[1].split("=")[1]);
        $('#shomareCheck2').val(myAttributes[1].split("=")[1]);
        $('#amountTajamoi').val(myAttributes[2].split("=")[1]);
        $('#namayandeNameEtebar').val(myAttributes[3].split("=")[1]);
    }
    function loadBedehiTankhah(msg){
        var myAttributes = msg.split("@")[1].split("#");
        $('#girande').val(myAttributes[0].split("=")[1]);
        $('#amount').val(myAttributes[1].split("=")[1]);
        $('#tarikh').val(myAttributes[2].split("=")[1]);
        $('#tozihat').val(myAttributes[3].split("=")[1]);
    }

    function loadBedehiHesabFiMabeyn(msg){
        var myAttributes = msg.split("@")[2].split("#");
        $("#hesabFimabeynBedehiAmount").val(myAttributes[0].split("=")[1]);
        $("#hesabFimabeynBedehiTozihate").val(myAttributes[1].split("=")[1]);
    }

    function assignOptionNoeBedehiAdd(){
        $.ajax({
            type: "POST",
            async : false,
            url: "isUserRoleNamayandeAjax",
            success: function (msg) {
                if (msg == 'ADMIN'){
                    $("#noeBedehieDarHaleSabt").empty();
                    $('#noeBedehieDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.PARDAKHTE_CHECK%>').text("پرداخت چک"));
                    $('#noeBedehieDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.PARDAKHTE_TANKHAH%>').text("پرداخت از محل تنخواه"));
                    $('#noeBedehieDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.HESAB_FI_MA_BEYN_BEDEHI%>').text("پرداخت حساب فی ما بین"))
                } else {
                    if(msg.contains('ROLE_NAMAYANDE')) {
                        $("#noeBedehieDarHaleSabt").empty();
                        <%--$('#noeBedehieDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.PARDAKHTE_CHECK%>').text("پرداخت چک"));--%>
                    }
                if(msg.contains('ROLE_PARDAKHTE_TANKHAH'))  {
                    $('#noeBedehieDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.PARDAKHTE_TANKHAH%>').text("پرداخت از محل تنخواه"));
                }
                if(msg.contains('ROLE_HESAB_FIMABEYN')) {
                    $('#noeBedehieDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.HESAB_FI_MA_BEYN_BEDEHI%>').text("پرداخت حساب فی ما بین"));
                }
                }
            }
        });
    }

    function addOptionNoeBedehiEdit(){
        $("#noeEtebareDarHaleSabt").empty();
        $('#noeBedehieDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.PARDAKHTE_CHECK%>').text("پرداخت چک"));
        $('#noeBedehieDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.PARDAKHTE_TANKHAH%>').text("پرداخت از محل تنخواه"));
        $('#noeBedehieDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.HESAB_FI_MA_BEYN_BEDEHI%>').text("پرداخت حساب فی ما بین"));
    }
</script>
<table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%">
    <col class="inputCol"><col class="inputCol">
    <tr>
        <td>
            <span class="noThing"></span>
            <select id="noeBedehieDarHaleSabt" onchange="chg_bedehiType(this.value);">
            </select>
            &nbsp;<label>نوع</label>
        </td>
        <td></td>
    </tr>
</table>
<form id="sabteBedehiDasti_hesabFimabeyn">
    <div id="hesabFimabeynBedehiInfo">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.HESAB_FI_MA_BEYN_BEDEHI.toString()%>">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="hesabFimabeynBedehiAmount" class="validate[required] digitSeparators">
                    &nbsp;<label>مبلغ</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.description" id="hesabFimabeynBedehiTozihate" class="">
                    &nbsp;<label>توضیحات</label>
                </td>
            </tr>
        </table>
    </div>
</form>
<form id="sabteBedehiDasti_check">
    <div id="checkBedehiInfo">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.PARDAKHTE_CHECK%>">
                    <input type="text" id="shomareChequeTemp" class="" disabled="disabled" hidden="true">
                    <span class="noThing"></span>
                    <input type="text" id="shomareHesabeBanki" class="" disabled="disabled">
                    &nbsp;<label>شماره حساب بانکی</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <c:if test="${checkList == null || fn:length(checkList) == 0}">
                    <label style="float:left;color:red">
                        برگه چکی وجود ندارد
                    </label>
                    </c:if>
                    <c:if test="${fn:length(checkList) != 0}">
                        <select id="shomareCheck2" name="credebit.check.id" onchange="chg_shomareCheck();">
                            <c:forEach var="row" items="${checkList}">
                                <option id="chkOpt_${row.darVajhe}_${row.dasteCheck.shomareHesab}_${row.tarikh}_${row.dasteCheck.name}_${row.id}" value="${row.id}">${row.shomare}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    &nbsp;<label>شماره چک</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="amountTajamoi" class="validate[required] digitSeparators">
                    &nbsp;<label>مبلغ</label>
                </td>
                <td>
                    <input type="text" name="credebit.sarresidDate" id="chackTarikh" disabled="disabled" class="datePkr">
                    &nbsp;<label>تاریخ سر رسید</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" id="darVajhe" class="" disabled="disabled">
                    &nbsp;<label>در وجه</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" id="mameDasteCheck" class="" disabled="disabled">
                    &nbsp;<label>نام دسته چک</label>
                </td>
            </tr>
        </table>
    </div>
</form>
<form id="sabteBedehiDasti_tankhah">
    <div id="tankhahBedehiInfo" style="display:none;">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.PARDAKHTE_TANKHAH%>">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.pardakhteTankhah.girande" id="girande" class="">
                    &nbsp;<label>گیرنده</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="amount" class="validate[required] digitSeparators">
                    &nbsp;<label>مبلغ</label>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="credebit.createdDate" id="tarikh" class="datePkr">
                    &nbsp;<label>تاریخ</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.pardakhteTankhah.tozih" id="tozihat" class="">
                    &nbsp;<label>توضیح</label>
                </td>
            </tr>
        </table>
    </div>
</form>
<tr>
    <td>
        <div id="namayandeNameCredebitDiv" hidden="true">
            <span class="noThing"></span>
            <input type="text" name="credebit.namayande.name" id="namayandeNameCredebit" class="">
            &nbsp;<label>نام نماینده</label>
        </div>
    </td>
    <td></td>
</tr>
<script type="text/javascript">
    function setShomareChequeForLoad(){
        $('#shomareCheck2').val($('#shomareChequeTemp').val());
    }
</script>
