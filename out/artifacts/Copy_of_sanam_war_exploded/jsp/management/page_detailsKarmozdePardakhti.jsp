<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var dilg;
    function loadDetails(id)
    {
        $('#khateSanadsOfGhest').dialog(
                                            {
                                                modal:true,
                                                width: 900,
                                                resizable:false,
                                                closeText: "",
                                                title:"جزئیات پرداخت",
                                                buttons:
                                                {
                                                    "بستن": function(){$(this).dialog("close");}
                                                }
                                            }
                                       );
        $.post("/detailsKarmozd.action?credebit.id="+ id ,function(msg){$('#khateSanadsResult').html(msg);});
    }

</script>
<div id="khateSanadsOfGhest" style="display:none;">
      </br>
  <div id="khateSanadsResult" style=" background-color:#f5f5f5; border-style:solid;  border-width:thin; border-color:#cccccc">
      <div></div>
  </div>
</div>
