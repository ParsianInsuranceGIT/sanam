<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="allofem">
    <form action="/searchKarmozdGhest.action" id="form_search_for_pishnehads" method="post">
        <input type="hidden" name="selectedTab" id="sel_sel_tab"/>
        <table class="inputList" cellspacing="5" width="90%">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="shomareMoshtari" id="search_shomare_moshtari" value="${shomareMoshtari}"/>
                    &nbsp;<label>شناسه پرداخت</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="shenaseEtebar" id="search_shenase_etebar" value="${shenaseEtebar}"/>
                    &nbsp;<label>شناسه اعتبار</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="shomareBimename" id="search_shomare_bimename" value="${shomareBimename}"/>
                    &nbsp;<label>شماره بیمه نامه</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="kodeNamayande" id="search_kode_namayandegi" value="${kodeNamayande}"/>
                    &nbsp;<label>کد نمایندگی</label>
                </td>
            </tr>
            <tr>
                <td><input type="hidden" name="karmozd.id" value="${karmozd.id}"/> </td>
                <td>
                    <script type="text/javascript">
                        function clearSeachFrom()
                        {
                            $('#search_shomare_moshtari').val('');
                            $('#search_shenase_etebar').val('');
                            $('#search_shomare_bimename').val('');
                            $('#search_kode_namayandegi').val('');
                        }
                    </script>
                    <input type="button" style="margin-left:25px" value="پاک کردن فرم" onclick="clearSeachFrom()">
                    <input type="submit" style="margin-left:25px" value="جستجو">
                </td>
            </tr>
        </table>
    </form>
</div>