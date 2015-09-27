<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var dilg;
    var namayandegiIdField, namayandegiNameField, nextField;

    function entekhab(id, name,code){
        $('#' + namayandegiIdField).val(id);
        if(namayandegiNameField != '') $('#' + namayandegiNameField).val(name);
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

    function jostejuN() {
        if($.validationEngine.submitValidation(this) === false){
            var mainForm2 = $('#jostejuNForm').serialize();
            $.post('/searchNamayandegiDialog', mainForm2, function(msg) {
                $('#jostejuNResult').html(msg);
            });
        }
    }

    function jostejuNAll() {
        if($.validationEngine.submitValidation(this) === false){
            var mainForm2 = $('#jostejuNForm').serialize();
            $.post('/searchNamayandegiAllDialog', mainForm2, function(msg) {
                $('#jostejuNResult').html(msg);
            });
        }
    }

    function enseraf(){
        $.validationEngine.closePrompt('.formError', true);
        $('#namayandegiName').val('');
        $('#namayandegiCode').val('');
        $('#jostejuNResult').html('');
    }

    function fillNamayandegi(namayandegiIdField, namayandegiNameField, nextField){
        this.namayandegiIdField = namayandegiIdField;
        this.namayandegiNameField = namayandegiNameField;
        this.nextField = nextField;
        $.validationEngine.closePrompt('.formError', true);
        dilg = $('#jostejuN').dialog({
            modal:true,
            width: 840,
            resizable:false,
            closeText: "",
            title:"جستجو",
            close:enseraf,
            buttons: {
                "جستجو": function(){
                    jostejuN();$('#namayandegiName').val('');},
                "انصراف": function(){enseraf();$(this).dialog("close");}
            }
        });
    }

    function fillAllNamayandegi(namayandegiIdField, namayandegiNameField, nextField){
        this.namayandegiIdField = namayandegiIdField;
        this.namayandegiNameField = namayandegiNameField;
        this.nextField = nextField;
        $.validationEngine.closePrompt('.formError', true);
        dilg = $('#jostejuN').dialog({
            modal:true,
            width: 840,
            resizable:false,
            closeText: "",
            title:"جستجو",
            close:enseraf,
            buttons: {
                "جستجو": function(){
                    jostejuNAll();$('#namayandegiName').val('');},
                "انصراف": function(){enseraf();$(this).dialog("close");}
            }
        });
    }
</script>
<div id="jostejuN" style="display:none;">
    <form id="jostejuNForm" method="post" action="/searchNamayandegiDialog">
        <table class="inputList" width="90%">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="namayandegiName" id="namayandegiName" class="" value=""/>
                    &nbsp;<label>نام نمایندگی</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="namayandegiCode"  id="namayandegiCode" class="" value=""/>
                    &nbsp;<label>کد نمایندگی</label>
                </td>
            </tr>
        </table>
        <div id="jostejuNResult"></div>
    </form>
</div>
