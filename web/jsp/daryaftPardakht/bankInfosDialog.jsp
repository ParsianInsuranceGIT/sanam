<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var dilg;
    function loadBankInfosForCredebit(id)
    {
        $('#bankInfosForCredebit').dialog({
            modal:true,
            width: 900,
            resizable:false,
            closeText: "",
            title:"اطلاعات وصولی",
            buttons:
            {
                "انصراف": function(){$(this).dialog("close");}
            }
    }
    );
    $.post("/fin/viewBankInfos.action?credebit.id="+ id,
                function(msg)
                {
                    $('#bankInfosForCredebitResult div').html(msg);
                });
    }

</script>
<div id="bankInfosForCredebit" style="display:none;">
      </br>
  <div id="bankInfosForCredebitResult" style=" background-color:#f5f5f5; border-style:solid;  border-width:thin; border-color:#cccccc">
      <div></div>
  </div>
</div>
