<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var dilg;
    function loadKhateSanads(id)
    {
        $('#khateSanadsOfSanad').dialog({
            modal:true,
            width: 900,
            resizable:false,
            closeText: "",
            title:"خط سندهای انتخاب شده",
            buttons:
            {
                "تایید": function(){changeVaziat(id);$(this).dialog("close");},
                "انصراف": function(){$(this).dialog("close");},
                "چاپ آزمایشی سند": function(){ window.open('/fin/printeSanad?pishnehadReport.sanad.id='+id);}
            }
    }
    );
    $.post("/fin/khateSanadsHaOfSanad.action?sanad.id="+ id,
                function(msg)
                {
                    $('#khateSanadsResult div').html(msg);
                });
    }

    function changeVaziat(id)
    {
        $.post("/fin/changeVaziatSanad.action?id="+ id, function(msg) {location.reload();});
    }
</script>
<div id="khateSanadsOfSanad" style="display:none;">
      </br>
  <div id="khateSanadsResult" style=" background-color:#f5f5f5; border-style:solid;  border-width:thin; border-color:#cccccc">
      <%--<p class="heading ui-widget-header ui-helper-clearfix">--%>
          <%--<span  style=" direction:rtl;float:right;">خط سندهای سند انتخاب شده</span>--%>
      <%--</p>--%>
      <div></div>
  </div>
  <p style=" color:#dc143c;">
      آیا از تغییر وضعیت سند انتخاب شده اطمینان دارید؟
  </p>
</div>
