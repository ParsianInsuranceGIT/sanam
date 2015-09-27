<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.Core.Constant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="allofem" class="listenTextFieldEnter" functionCall="searchAllPishnehadAjax()">
<form action="/searchForPishnehads.action" id="form_search_for_pishnehads" method="post">
    <input type="hidden" name="selectedTab" id="sel_sel_tab"/>
    <table class="inputList" cellspacing="5" width="90%">
        <tr>
            <td>
                <span class="noThing"></span>
                <%--<% Pishnehad pishnehad = new Pishnehad();--%>
                <%--pishnehad.getRadif();--%>
                <%--pishnehad.getNamayande().getOstan();%>--%>
                <input type="text" name="pishnehadSearch.radif" id="search_kod_rahgiri" value="${pishnehadSearch.radif}"/>
                &nbsp;<label>كد رهگيري پيشنهاد‌</label>
            </td>
            <td>
                <span class="noThing"></span>

                <sec:authorize ifAnyGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB">
                    <input type="text" name="pishnehadSearch.kodeNamayandeKargozar" value="${user.namayandegi.kodeNamayandeKargozar}" readonly="readonly" id="kodNamayandegi"/>
                </sec:authorize>
                <sec:authorize ifNotGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB" >
                    <input type="text" name="pishnehadSearch.kodeNamayandeKargozar" value="${pishnehadSearch.kodeNamayandeKargozar}" id="kodNamayandegi">
                </sec:authorize>

                &nbsp;<label>کد نماینده/کارگزار</label>
            </td>
            <td>
                <span class="noThing"></span>

                <sec:authorize ifAnyGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB">
                    <input type="text" name="pishnehadSearch.ostan" value="${user.namayandegi.ostan.cityName}" readonly="readonly">
                </sec:authorize>

                <sec:authorize ifNotGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB" >
                    <input type="text" name="pishnehadSearch.ostan" id="sale_ostan" value="${pishnehadSearch.ostan}"/>
                </sec:authorize>

                &nbsp;<label>استان نمایندگی</label>
            </td>
        </tr>
        <tr>
            <td>

                <%--<sec:authorize ifAnyGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB">--%>
                    <%--<input type="text" name="pishnehadSearch.azTarikh" value="<%=DateUtil.addMonth(DateUtil.getCurrentDate(),-2)%>" id="sabt_az_tarikh" class="datePkr validate[custom[date]]" />--%>
                <%--</sec:authorize>--%>
                <%--<sec:authorize ifNotGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB">--%>
                    <input type="text" name="pishnehadSearch.azTarikh" value="1386/01/01" id="sabt_az_tarikh" class="datePkr validate[custom[date]]" />
                <%--</sec:authorize>--%>
                &nbsp;<label>از تاریخ (ثبت پیشنهاد)</label>
            </td>
            <td>
                <input type="text" name="pishnehadSearch.taTarikh" value="<%=DateUtil.getCurrentDate()%>" id="sabt_ta_tarikh" class="datePkr validate[custom[date]]"/>
                &nbsp;<label>تا تاریخ (ثبت پیشنهاد)</label>
            </td>
            <td>
                <span class="noThing"></span>
                <select name="pishnehadSearch.state" id="pishnehadSearch_state">
                    <option value="">-</option>
                    <c:forEach var="state" items="${states}">
                        <option ${pishnehadSearch.state == state.stateName ? "selected=selected" : ""} value="${state.stateName}">${state.stateName}</option>
                    </c:forEach>
                </select>
                &nbsp;<label>وضعیت</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <input type="text" name="pishnehadSearch.nameBimeGozar" id="pishnehadSearch_nameBimeGozar" value="${pishnehadSearch.nameBimeGozar}" />
                &nbsp;<label>نام بیمه گذار</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="pishnehadSearch.nameKhanevadegiBimeGozar"  id="pishnehadSearch_nameKhanevadegiBimeGozar" value="${pishnehadSearch.nameKhanevadegiBimeGozar}" />
                &nbsp;<label>نام خانوادگی بیمه گذار</label>
            </td>
            <td>
                <script type="text/javascript">
                    function val_kodeMelliBimeGozar(){
                        return val_codeMelli('#kodeMelliBimeGozar');
                    }
                </script>
                <span class="noThing"></span>
                <input type="text" name="pishnehadSearch.kodeMelliBimeGozar" id="kodeMelliBimeGozar" class="validate[custom[onlyNumber],funcCall[val_kodeMelliBimeGozar]] text-input" value="${pishnehadSearch.kodeMelliBimeGozar}" />
                &nbsp;<label>کد ملی بیمه گذار</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <input type="text" name="pishnehadSearch.nameBimeShode" id="pishnehadSearch_nameBimeShode" value="${pishnehadSearch.nameBimeShode}" />
                &nbsp;<label>نام بیمه شده</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="pishnehadSearch.nameKhanevadegiBimeShode" id="pishnehadSearch_nameKhanevadegiBimeShode" value="${pishnehadSearch.nameKhanevadegiBimeShode}" />
                &nbsp;<label>نام خانوادگی بیمه شده</label>
            </td>
            <td>
                <span class="noThing"></span>
                <script type="text/javascript">
                    function val_kodeMelliBimeShode(){
                            return val_codeMelli('#kodeMelliBimeShode');
                    }
                </script>
                <input type="text" name="pishnehadSearch.kodeMelliBimeShode" id="kodeMelliBimeShode" class="validate[custom[onlyNumber],funcCall[val_kodeMelliBimeShode]] text-input" value="${pishnehadSearch.kodeMelliBimeShode}" />
                &nbsp;<label>کد ملی بیمه شده</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <select name="pishnehadSearch.karshenasId" id="pishnehadSearch_karshenasId">
                    <option value="-1">-</option>
                    <option value="-2">ندارد</option>
                    <c:forEach var="karshenas" items="${karshenasha}">
                        <option ${ pishnehadSearch.karshenasId == karshenas.id ? 'selected=selected' : ''} value="${karshenas.id}">${karshenas.firstName}&nbsp;${karshenas.lastName}</option>
                    </c:forEach>
                </select>

                &nbsp;<label>نام کارشناس</label>
            </td>
            <td>
                <span class="noThing"></span>
                <select name="pishnehadSearch.noeGharardad" id="pishnehadSearch_noeGharardad">
                    <option ${ pishnehadSearch.noeGharardad == '' ? 'selected=selected' : ''} value="">-</option>
                    <option ${ pishnehadSearch.noeGharardad == 'قرارداد عمومي بيمه مركزي' ? 'selected=selected' : ''} value="قرارداد عمومي بيمه مركزي">قرارداد عمومي بيمه مركزي</option>
                    <option ${ pishnehadSearch.noeGharardad == 'قرارداد بيمه-بانك پارسيان(BankAssurance)' ? 'selected=selected' : ''} value="قرارداد بيمه-بانك پارسيان(BankAssurance)">قرارداد بيمه-بانك پارسيان(BankAssurance)</option>
                    <option ${ pishnehadSearch.noeGharardad == 'قرارداد كاركنان بيمه پارسيان' ? 'selected=selected' : ''} value="قرارداد كاركنان بيمه پارسيان">قرارداد كاركنان بيمه پارسيان</option>
                    <option ${ pishnehadSearch.noeGharardad == 'قرارداد فروش جمعي' ? 'selected=selected' : ''} value="قرارداد فروش جمعي">قرارداد فروش جمعي</option>
                    <option ${ pishnehadSearch.noeGharardad == 'ساير' ? 'selected=selected' : ''} value="ساير">ساير</option>
                </select>
                &nbsp;<label>نوع قرارداد</label>
            </td>
            <td>
                <span class="noThing"></span>
                <select name="pishnehadSearch.noePardakht" id="pishnehadSearch_noePardakht">
                    <option ${ pishnehadSearch.noePardakht == '-' ? 'selected=selected' : '' }  value="">-</option>
                    <option ${ pishnehadSearch.noePardakht == 'fish' ? 'selected=selected' : '' } value="<%=Constant.FISH%>">فیش</option>
                    <option ${ pishnehadSearch.noePardakht == '1' ? 'selected=selected' : '' } value="-1">چک</option>
                    <option ${ pishnehadSearch.noePardakht == 'interneti' ? 'selected=selected' : '' } value="<%=Constant.INTERNETI%>">پرداخت اینترنتی</option>
                    <%--<option ${ pishnehadSearch.noePardakht == 'shenasedar' ? 'selected=selected' : '' } value="<%=Constant.SHENASEDAR%>">شناسه دار</option>--%>
                </select>
                &nbsp;<label>نوع پرداخت</label>
            </td>
        </tr>
        <tr>
            <td>
                <span class="noThing"></span>
                <select name="pishnehadSearch.nahvePardakhtHagheBime" id="nahve_pardakht_hagh_bime">
                    <option ${pishnehadSearch.nahvePardakhtHagheBime == '-' ? 'selected=selected' : '' }  value="">-</option>
                    <option ${pishnehadSearch.nahvePardakhtHagheBime == 'mah' ? 'selected=selected' : '' }  value="mah">ماهانه</option>
                    <option ${pishnehadSearch.nahvePardakhtHagheBime == '3mah' ? 'selected=selected': '' } value="3mah">سه ماهه</option>
                    <option ${pishnehadSearch.nahvePardakhtHagheBime == '6mah' ? 'selected=selected': '' } value="6mah">شش ماهه</option>
                    <option ${pishnehadSearch.nahvePardakhtHagheBime == 'sal' ? 'selected=selected' : '' } value="sal">سالانه</option>
                    <option ${pishnehadSearch.nahvePardakhtHagheBime == 'yekja' ? 'selected=selected': '' } value="yekja">یکجا</option>
                </select>
                &nbsp;<label>نحوه پرداخت حق بیمه</label>
            </td>
            <td>
                <span class="noThing"></span>
                <select name="pishnehadSearch.pishpardakhtOK" id="pishpardakhtOK">
                    <option ${pishnehadSearch.pishpardakhtOK == '-' ? 'selected=selected' : '' }  value="">-</option>
                    <option ${pishnehadSearch.pishpardakhtOK == 'true' ? 'selected=selected' : '' }  value="true">تایید شده</option>
                    <option ${pishnehadSearch.pishpardakhtOK == 'false' ? 'selected=selected': '' } value="false">منتظر تایید</option>
                </select>
                &nbsp;<label>وضعيت پيش‌پرداخت</label>
            </td>
            <td>

                <span class="noThing"></span>
                <select name="pishnehadSearch.noeBimename" id="pishnehadSearch_noeBimename">
                    <option ${ pishnehadSearch.noeBimename == '' ? 'selected=selected' : ''} value="">-</option>
                    <option ${ pishnehadSearch.noeBimename == 'انفرادی' ? 'selected=selected' : ''} value="انفرادی">انفرادی</option>
                    <option ${ pishnehadSearch.noeBimename == 'خانواده' ? 'selected=selected' : ''} value="خانواده">خانواده</option>
                </select>
                &nbsp;<label>نوع بیمه نامه</label>
            </td>
        </tr>

        <tr>
            <sec:authorize ifAnyGranted="ROLE_KARSHENAS_MASOUL">
            <td>
                <span class="noThing"></span>
                    <input type="text" name="pishnehadSearch.kodeSarparast" value="${pishnehadSearch.kodeSarparast}" id="kodeSarparast">
                &nbsp;<label>کد سرپرست</label>
            </td>
            </sec:authorize>
            <td>
                <span class="noThing"></span>
                <select id="pish_bimename_goroh" name="pishnehadSearch.groupId">
                    <%for (Gharardad goroh : (List<Gharardad>)request.getAttribute("grouhHa")) { %>
                        <option value="<%=goroh.getId() == null ? "" : goroh.getId()%>" >
                            <%=goroh.getNameSherkat()%>
                        </option>
                    <%}%>
                </select>
                &nbsp;<label>گروه بيمه‌نامه</label>
            </td>
        </tr>

        <tr>
            <td id="shomareBimeTdSearch">
                <span class="noThing"></span>
                <input type="text" name="pishnehadSearch.shomareBimename" id="search_shomare_b" value="${bimename.shomare}"/>
                &nbsp;<label>شماره بیمه نامه</label>
            </td>
            <td colspan="3">
                <script type="text/javascript">
                    function clearSeachFrom()
                    {
                        $('#search_kod_rahgiri').val('');
                        $('#search_kod_rahgiri_viewview').val('');

                        $('#pishnehadSearch_nameBimeGozar').val('');
                        $('#pishnehadSearch_nameBimeGozar_viewview').val('');

                        $('#pishnehadSearch_nameKhanevadegiBimeGozar').val('');
                        $('#pishnehadSearch_nameKhanevadegiBimeGozar_viewview').val('');

                        $('#kodeMelliBimeGozar').val('');
                        $('#kodeMelliBimeGozar_viewview').val('');

                        $('#pishnehadSearch_nameBimeShode').val('');
                        $('#pishnehadSearch_nameBimeShode_viewview').val('');

                        $('#pishnehadSearch_nameKhanevadegiBimeShode').val('');
                        $('#pishnehadSearch_nameKhanevadegiBimeShode_viewview').val('');

                        $('#kodeMelliBimeShode').val('');
                        $('#kodeMelliBimeShode_viewview').val('');

                        $('#pishnehadSearch_state option:eq(0)').attr('selected','selected');
                        $('#pishnehadSearch_state_viewview option:eq(0)').attr('selected','selected');

                        $('#pishnehadSearch_karshenasId option:eq(0)').attr('selected','selected');
                        $('#pishnehadSearch_karshenasId_viewview option:eq(0)').attr('selected','selected');

                        $('#pishnehadSearch_noeGharardad option:eq(0)').attr('selected','selected');
                        $('#pishnehadSearch_noeGharardad_viewview option:eq(0)').attr('selected','selected');

                        $('#pishnehadSearch_noePardakht option:eq(0)').attr('selected','selected');
                        $('#pishnehadSearch_noePardakht_viewview option:eq(0)').attr('selected','selected');

                        $('#nahve_pardakht_hagh_bime option:eq(0)').attr('selected','selected');
                        $('#nahve_pardakht_hagh_bime_viewview option:eq(0)').attr('selected','selected');

                        $('#pishpardakhtOK option:eq(0)').attr('selected','selected');
                        $('#pishpardakhtOK_viewview option:eq(0)').attr('selected','selected');

                        $('#pishnehadSearch_noeBimename option:eq(0)').attr('selected','selected');
                        $('#pishnehadSearch_noeBimename_viewview option:eq(0)').attr('selected','selected');

                        <sec:authorize ifNotGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB" >
                            $('#pishnehadSearch_kodeNamayandeKargozar option:eq(0)').attr('selected','selected');
                            $('#pishnehadSearch_kodeNamayandeKargozar_viewview option:eq(0)').attr('selected','selected');

                            $('#sale_ostan').val('');
                            $('#sale_ostan_viewview').val('');


                        </sec:authorize>


                        <%--<sec:authorize ifAnyGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB">--%>
                            <%--$('#sabt_az_tarikh').val('<%=DateUtil.addMonth(DateUtil.getCurrentDate(),-2)%>');--%>
                            <%--$('#sabt_az_tarikh_viewview').val('<%=DateUtil.addMonth(DateUtil.getCurrentDate(),-2)%>');--%>
                        <%--</sec:authorize>--%>
                        <%--<sec:authorize ifNotGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB">--%>
                            $('#sabt_az_tarikh').val('1386/01/01');
                            $('#sabt_az_tarikh_viewview').val('1386/01/01');
                        <%--</sec:authorize>--%>

                       $('#sabt_ta_tarikh').val('<%=DateUtil.getCurrentDate()%>');
                        $('#sabt_ta_tarikh_viewview').val('<%=DateUtil.getCurrentDate()%>');

                        $('#kodNamayandegi').val('');
                        $('#kodNamayandegi_viewview').val('');

                        $('#kodeSarparast').val('');
                        $('#kodeSarparast_viewview').val('');

                    }
                </script>
                <input type="button" style="margin-left:25px" value="پاک کردن فرم" onclick="clearSeachFrom()">
                <%--<input type="submit" style="margin-left:25px" value="جستجو">--%>
                <input type="button" onclick="searchAllPishnehadAjax();" style="margin-left:25px" value="جستجو">

            </td>
        </tr>
    </table>
</form>
</div>
<script type="text/javascript">

    function searchAllPishnehadAjax() {
        if ($('#form_search_for_pishnehads').attr('action') != undefined && $('#form_search_for_pishnehads').attr('action') != ""){
            $.ajax({
                type: "POST",
                url: 'resultTab1.action',
                data: $('#form_search_for_pishnehads').serialize(),
                success: function (response) {
                    // we have the response
                    $('#displayTab1').html(response);
                }
            });
        }
        else if ($('#form_search_for_pishnehads_viewview').attr('action') != undefined){
            $.ajax({
                type: "POST",
                url: 'resultTab2.action',
                data: $('#form_search_for_pishnehads_viewview').serialize(),
                success: function (response) {
                    // we have the response
                    $('#displayTab2').html(response);
                }
            });
        }
    }

    function fillOstan2(ostanIdField, ostanNameField, nextField, parentId) {
        dialogFormWithFocus('searchCity', 'جستجوی استان', function () {
            if (parentId.toString().lastIndexOf("_viewview") > 0) {
                $('#' + ostanNameField + '_viewview').val($('#selectedOstan').val());
                $('#' + ostanIdField + '_viewview').val($('#ostanId').val());
            }
            else {
                $('#' + ostanNameField).val($('#selectedOstan').val());
                $('#' + ostanIdField).val($('#ostanId').val());
            }
        }, nextField);
    }




</script>
