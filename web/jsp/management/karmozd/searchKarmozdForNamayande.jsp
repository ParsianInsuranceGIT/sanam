<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    function changeSelTabSrch(tab)
    {
        $('#sel_sel_tab').val('tabs-'+tab);
    }
</script>
<div id="karForNam">
    <form action="/searchKarmozdNamayande.action" id="form_search_for_pishnehads" method="post">
        <input type="hidden" name="selectedTab" id="sel_sel_tab" value="tabs-3"/>
        <table class="inputList" cellspacing="1" width="30%">
            <tr>
                <td>
                    <label>نمایندگی</label>
                    <span class="ui-icon ui-icon-search" onclick="fillNamayandegi('nId','nName', '');" style="float:left;" title="جستجو"></span>
                    <input type="hidden" name="namayande.id" id="nId"/>
                    <input type="text" name="namayande.name"  id="nName" readonly="readonly" />
                    <input type="hidden" name="karmozd.id" value="${karmozd.id}"/>
                </td>

                <td>
                    <script type="text/javascript">
                        function clearSeachFrom()
                        {
                            $('#namayandegiName').val('');
                            $('#namayandegiId').val('');
                        }
                    </script>
                    <%--<input type="button" style="margin-left:25px" value="پاک کردن فرم" onclick="clearSeachFrom()">--%>
                     &nbsp; &nbsp;<input type="submit" style="margin-left:25px; width:100px;" value="جستجو">
                </td>
            </tr>
        </table>
    </form>
</div>