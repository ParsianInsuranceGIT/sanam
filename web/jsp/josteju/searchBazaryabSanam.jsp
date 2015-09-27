<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var dilg;
    var bazSanmId, bazSanmName, nextField;

    function entekhabb(id, name,code){
        $('#' + bazSanmId).val(id);
        if(bazSanmName != '') $('#' + bazSanmName).val(name);
        if(nextField != '')
        {
            if(nextField.toUpperCase().contains('CODE')||nextField.toUpperCase().contains('KODE'))
            {
                $('#' + nextField).val(code)
            }
            else
            {
                $('#' + nextField).focus();
            }
        }
        $(dilg).dialog('close');
        enseraf();
    }

    function jostejuB() {
        if($.validationEngine.submitValidation(this) === false){
            var mainForm2 = $('#jostejuBForm').serialize();
            $.post('/searchBazaryabSanamDialog', mainForm2, function(msg) {
                $('#jostejuBResult').html(msg);
            });
        }
    }

//    function jostejuBAll() {
//        if($.validationEngine.submitValidation(this) === false){
//            alert('2 jostejuBAll');
//            var mainForm2 = $('#jostejuBForm').serialize();
//            $.post('/searchBazaryabSanamAllDialog', mainForm2, function(msg) {
//                $('#jostejuBResult').html(msg);
//            });
//        }
//    }

    function enseraf(){
        $.validationEngine.closePrompt('.formError', true);
        $('#bazaryabSanamName').val('');
        $('#bazaryabSanamCode').val('');
        $('#jostejuBResult').html('');
    }

    function fillBazaryabSanam(bazSanmId, bazSanmName, nextField){
        this.bazSanmId = bazSanmId;
        this.bazSanmName = bazSanmName;
        this.nextField = nextField;
        $.validationEngine.closePrompt('.formError', true);
        dilg = $('#jostejuB').dialog({
            modal:true,
            width: 840,
            resizable:false,
            closeText: "",
            title:"جستجو",
            close:enseraf,
            buttons: {
                "جستجو": function(){
                    jostejuB();$('#bazaryabSanamName').val('');
                },
                "انصراف": function(){enseraf();$(this).dialog("close");}
            }
        });
    }
//    function fillAllBazaryabSanam(bazSanmId, bazSanmName, nextField){
//        this.bazSanmId = bazSanmId;
//        this.bazSanmName = bazSanmName;
//        this.nextField = nextField;
//        $.validationEngine.closePrompt('.formError', true);
//        dilg = $('#jostejuB').dialog({
//            modal:true,
//            width: 840,
//            resizable:false,
//            closeText: "",
//            title:"جستجو",
//            close:enseraf,
//            buttons: {
//                "جستجو": function(){
//                    alert('hi');
//                    jostejuBAll();$('#bazaryabSanamName').val('');
//                     alert('3');
//                },
//                "انصراف": function(){enseraf();$(this).dialog("close");}
//            }
//        });
//    }
</script>
<div id="jostejuB" style="display:none;">
    <form id="jostejuBForm" method="post" action="/searchBazaryabSanamDialog">
        <table class="inputList" width="90%">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="bazaryabSanamName" id="bazaryabSanamName" class="" value=""/>
                    &nbsp;<label>نام بازاریاب</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="bazaryabSanamCode"  id="bazaryabSanamCode" class="" value=""/>
                    &nbsp;<label>کد بازاریاب</label>
                </td>
            </tr>
        </table>
        <div id="jostejuBResult"></div>
    </form>
</div>
