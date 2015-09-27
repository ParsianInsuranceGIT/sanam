<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function() {

    $( "#jostejuyeShahr" ).autocomplete({
			source: "/searchCity",
			minLength: 2,
			select: function( event, ui ) {
                $('#selectedShahr').val(ui.item.cityName);
                $('#selectedOstan').val(ui.item.parentName);
                $('#shahrId').val(ui.item.cityId);
                $('#ostanId').val(ui.item.parentId);
                $('#shahrIdForPid').val(ui.item.cityIdForPid);
                $('#ostanIdForPid').val(ui.item.parentIdForPid);
			},
			open: function() {
				$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
			},
			close: function() {
				$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
			}
		});

        });
</script>
<div id="searchCity" style="display:none;">
    <input type=hidden value="" id="shahrId"/>
    <input type=hidden value="" id="ostanId"/>
    <input type=hidden value="" id="shahrIdForPid"/>
    <input type=hidden value="" id="ostanIdForPid"/>
    <table class="inputList" width="90%">
            <tr>
                <td colspan="2">
                    <span class="noThing"></span>
                    <input type=text name="jostejuyeShahr" value="" id="jostejuyeShahr" style="width: 415px;"/>&nbsp;<label>جستجوی شهر</label>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type=text value="" id="selectedOstan" class="" readonly="readonly"/>&nbsp;<label>استان</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type=text id="selectedShahr" value="" class="" readonly="readonly"/>&nbsp;<label>شهر</label>
                </td>
            </tr>
        </table>
</div>