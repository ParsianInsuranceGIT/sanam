<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.Core.Constant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="allofem" class="listenTextFieldEnter" functionCall="searchElhaghieAjax()">
<form action="/searchForElhaghie.action" id="form_search_for_elhaghiye" method="post">
    <input type="hidden" name="selectedTab" id="sel_sel_tab" value="tabs-5"/>
    <table class="inputList" cellspacing="5" width="90%">
        <tr>
            <td>
                <span class="noThing"></span>
                <input type="text" name="elhaghiyeSearch.shomareElhaghiye" id="shomare_elhaghiye"/>
                &nbsp;<label>شماره الحاقیه</label>
            </td>
            <td>
                <span class="noThing"></span>
                    <input type="text" name="elhaghiyeSearch.azTarikhAsar"  id="az_tarikhe_asar"class="datePkr validate[custom[date]]" />
                &nbsp;<label>از تاریخ اثر</label>
            </td>
            <td>
                <span class="noThing"></span>
                    <input type="text" name="elhaghiyeSearch.taTarikhAsar"  id="ta_tarikhe_asar"class="datePkr validate[custom[date]]" />
                &nbsp;<label>تا تاریخ اثر</label>
            </td>
        </tr>
        <tr>
             <td>
                <span class="noThing"></span>
                <input type="text" name="elhaghiyeSearch.shomareBimename" id="shomare_bimename_el"/>
                &nbsp;<label>شماره بیمه نامه</label>
            </td>
            <td>
                <span class="noThing"></span>
                    <input type="text" name="elhaghiyeSearch.azTarikhDarkhast"  id="az_created_date"class="datePkr validate[custom[date]]" />
                &nbsp;<label>از تاریخ درخواست</label>
            </td>
            <td>
                <span class="noThing"></span>
                    <input type="text" name="elhaghiyeSearch.taTarikhDarkhast"  id="ta_created_date"class="datePkr validate[custom[date]]" />
                &nbsp;<label>تا تاریخ درخواست</label>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <script type="text/javascript">
                    function clearSeachFrom_e()
                    {
                        $('#shomare_elhaghiye').val('');
                        $('#az_tarikhe_asar').val('');

                        $('#ta_tarikhe_asar').val('');
                        $('#az_created_date').val('');

                        $('#ta_created_date').val('');
                        $('#shomare_bimename_el').val('');

//                        $('#kodeMelliBimeGozar').val('');
//                        $('#kodeMelliBimeGozar_viewview').val('');
<%----%>
//                        $('#pishnehadSearch_nameBimeShode').val('');
//                        $('#pishnehadSearch_nameBimeShode_viewview').val('');
<%----%>
//                        $('#pishnehadSearch_nameKhanevadegiBimeShode').val('');
//                        $('#pishnehadSearch_nameKhanevadegiBimeShode_viewview').val('');
<%----%>
//                        $('#kodeMelliBimeShode').val('');
//                        $('#kodeMelliBimeShode_viewview').val('');
<%----%>
//                        $('#pishnehadSearch_state option:eq(0)').attr('selected','selected');
//                        $('#pishnehadSearch_state_viewview option:eq(0)').attr('selected','selected');
<%----%>
//                        $('#pishnehadSearch_karshenasId option:eq(0)').attr('selected','selected');
//                        $('#pishnehadSearch_karshenasId_viewview option:eq(0)').attr('selected','selected');
<%----%>
//                        $('#pishnehadSearch_noeGharardad option:eq(0)').attr('selected','selected');
//                        $('#pishnehadSearch_noeGharardad_viewview option:eq(0)').attr('selected','selected');
<%----%>
//                        $('#pishnehadSearch_noePardakht option:eq(0)').attr('selected','selected');
//                        $('#pishnehadSearch_noePardakht_viewview option:eq(0)').attr('selected','selected');
<%----%>
//                        $('#nahve_pardakht_hagh_bime option:eq(0)').attr('selected','selected');
//                        $('#nahve_pardakht_hagh_bime_viewview option:eq(0)').attr('selected','selected');
<%----%>
//                        $('#pishpardakhtOK option:eq(0)').attr('selected','selected');
//                        $('#pishpardakhtOK_viewview option:eq(0)').attr('selected','selected');
<%----%>
//                        $('#pishnehadSearch_noeBimename option:eq(0)').attr('selected','selected');
//                        $('#pishnehadSearch_noeBimename_viewview option:eq(0)').attr('selected','selected');
<%----%>
//                        <sec:authorize ifNotGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB" >
//                            $('#pishnehadSearch_kodeNamayandeKargozar option:eq(0)').attr('selected','selected');
//                            $('#pishnehadSearch_kodeNamayandeKargozar_viewview option:eq(0)').attr('selected','selected');
<%----%>
//                            $('#sale_ostan').val('');
//                            $('#sale_ostan_viewview').val('');


//                        </sec:authorize>


                        <%--<sec:authorize ifAnyGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB">--%>
                            <%--$('#sabt_az_tarikh').val('<%=DateUtil.addMonth(DateUtil.getCurrentDate(),-2)%>');--%>
                            <%--$('#sabt_az_tarikh_viewview').val('<%=DateUtil.addMonth(DateUtil.getCurrentDate(),-2)%>');--%>
                        <%--</sec:authorize>--%>
                        <%--<sec:authorize ifNotGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB">--%>
//                            $('#sabt_az_tarikh').val('1390/01/01');
//                            $('#sabt_az_tarikh_viewview').val('1390/01/01');
                        <%--</sec:authorize>--%>

                       <%--$('#sabt_ta_tarikh').val('<%=DateUtil.getCurrentDate()%>');--%>
                        <%--$('#sabt_ta_tarikh_viewview').val('<%=DateUtil.getCurrentDate()%>');--%>
<%----%>
//                        $('#kodNamayandegi').val('');
//                        $('#kodNamayandegi_viewview').val('');
<%----%>
//                        $('#kodeSarparast').val('');
//                        $('#kodeSarparast_viewview').val('');

                    }
                </script>
                <input type="button" style="margin-left:25px" value="پاک کردن فرم" onclick="clearSeachFrom_e()">
                <%--<input type="submit" style="margin-left:25px" value="جستجو">--%>
                <input type="button" onclick="searchElhaghieAjax()" style="margin-left:25px" value="جستجو">
            </td>
        </tr>
    </table>
</form>
</div>
<script type="text/javascript">
    function searchElhaghieAjax() {
        $.ajax({
            type: "POST",
            url: 'resultTab5.action',
            data: $('#form_search_for_elhaghiye').serialize(),
            success: function (response) {
                // we have the response
                $('#displayTab5').html(response);
            }
        });
    }
</script>