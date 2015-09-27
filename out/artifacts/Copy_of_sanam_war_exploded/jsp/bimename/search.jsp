<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.bimename.Gharardad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="allofem" class="listenTextFieldEnter" functionCall="searchBimenameAjax()">
    <form action="/searchForBimename" id="form_search_for_bimename" method="post">

        <input type="hidden" name="selectedTab" id="sel_sel_tab" value="tabs-3"/>
        <table class="inputList" cellspacing="5" width="90%">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <select name="pishnehad.noeGharardad" id="search_noeGharardad">
                        <option ${ pishnehad.noeGharardad == '' ? 'selected=selected' : ''} value="">-</option>
                        <option ${ pishnehad.noeGharardad == 'قرارداد عمومي بيمه مركزي' ? 'selected=selected' : ''} value="قرارداد عمومي بيمه مركزي">قرارداد عمومي بيمه مركزي</option>
                        <option ${ pishnehad.noeGharardad == 'قرارداد بيمه-بانك پارسيان(BankAssurance)' ? 'selected=selected' : ''} value="قرارداد بيمه-بانك پارسيان(BankAssurance)">قرارداد بيمه-بانك پارسيان(BankAssurance)</option>
                        <option ${ pishnehad.noeGharardad == 'قرارداد كاركنان بيمه پارسيان' ? 'selected=selected' : ''} value="قرارداد كاركنان بيمه پارسيان">قرارداد كاركنان بيمه پارسيان</option>
                        <option ${ pishnehad.noeGharardad == 'قرارداد فروش جمعي' ? 'selected=selected' : ''} value="قرارداد فروش جمعي">قرارداد فروش جمعي</option>
                        <option ${ pishnehad.noeGharardad == 'ساير' ? 'selected=selected' : ''} value="ساير">ساير</option>
                    </select>
                    &nbsp;<label>نوع قرارداد</label>
                </td>
                <td>
                    <sec:authorize ifAnyGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB,ROLE_KARMOZD_NAMAYANDE">
                        <span class="noThing"></span>
                        &nbsp;<label>نمایندگی</label>
                        <input type="hidden" name="pishnehad.namayande.id" id="search_namayandegiId" value="${user.namayandegi.id}"/>
                        <input type="text" name="pishnehad.namayande.name"  id="search_namayandegiName" value="${user.namayandegi.name}" readonly="true"/>
                    </sec:authorize>
                    <sec:authorize ifNotGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB,ROLE_KARMOZD_NAMAYANDE" >
                        <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_namayandegiId','search_namayandegiName', '');" style="float:left;" title="جستجو"></span>
                        &nbsp;<label>نمایندگی</label>
                        <input type="hidden" name="pishnehad.namayande.id" id="search_namayandegiId"/>
                        <input type="text" name="pishnehad.namayande.name"  id="search_namayandegiName" readonly="true"/>
                    </sec:authorize>

                </td>
                <td>
                    <sec:authorize ifAnyGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB,ROLE_KARMOZD_NAMAYANDE">
                        <span class="noThing"></span>
                        &nbsp;<label>واحد صدور</label>
                        <input type="hidden" name="pishnehad.namayande.vahedSodur.id" id="search_vahedeSodurId" value="${user.namayandegi.vahedSodur.id}" />
                        <input type="text" name="pishnehad.namayande.vahedSodur.name" id="search_vahedeSodurName" value="${user.namayandegi.vahedSodur.name}" readonly="true"/>
                    </sec:authorize>
                    <sec:authorize ifNotGranted="ROLE_NAMAYANDE,ROLE_BAZARYAB,ROLE_KARMOZD_NAMAYANDE" >
                        <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_vahedeSodurId','search_vahedeSodurName', '');" style="float:left;" title="جستجو"></span>
                        &nbsp;<label>واحد صدور</label>
                        <input type="hidden" name="pishnehad.namayande.vahedSodur.id" id="search_vahedeSodurId" />
                        <input type="text" name="pishnehad.namayande.vahedSodur.name" id="search_vahedeSodurName"  readonly="true"/>
                    </sec:authorize>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.radif" id="search_radif"/>
                    &nbsp;<label>کد رهگیری پیشنهاد</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="bimename.shomare" id="search_shomare_b" value="${bimename.shomare}"/>
                    &nbsp;<label>شماره بیمه نامه</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.estelam.sarmaye_paye_fot" id="search_sarmaye_fot_b" value="${pishnehad.estelam.sarmaye_paye_fot=="0"?"" : pishnehad.estelam.sarmaye_paye_fot}" class="digitSeparators"/>
                    &nbsp;<label>سرمایه پایه فوت</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <select name="pishnehad.estelam.nahve_pardakht_hagh_bime" id="search_nahve_pardakht_hagh_bime">
                        <option ${pishnehad.estelam.nahve_pardakht_hagh_bime == '-' ? 'selected=selected' : '' }  value="">-</option>
                        <option ${pishnehad.estelam.nahve_pardakht_hagh_bime == 'mah' ? 'selected=selected' : '' }  value="mah">ماهانه</option>
                        <option ${pishnehad.estelam.nahve_pardakht_hagh_bime == '3mah' ? 'selected=selected': '' } value="3mah">سه ماهه</option>
                        <option ${pishnehad.estelam.nahve_pardakht_hagh_bime == '6mah' ? 'selected=selected': '' } value="6mah">شش ماهه</option>
                        <option ${pishnehad.estelam.nahve_pardakht_hagh_bime == 'sal' ? 'selected=selected' : '' } value="sal">سالانه</option>
                        <option ${pishnehad.estelam.nahve_pardakht_hagh_bime == 'yekja' ? 'selected=selected': '' } value="yekja">یکجا</option>
                    </select>
                    &nbsp;<label>نحوه پرداخت حق بیمه</label>
                </td>
                <td>
                    <input type="text" name="tarikhSodur" value="1386/01/01" id="sabt_az_tarikh_b"
                           class="datePkr validate[custom[date]]"/>
                    &nbsp;<label>ازتاریخ صدور</label>
                </td>
                <td>
                    <input type="text" name="bimename.tarikhSodour" value="<%=DateUtil.getCurrentDate()%>"
                           id="sabt_ta_tarikh_b" class="datePkr validate[custom[date]]"/>
                    &nbsp;<label>تا تاریخ صدور</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.bimeGozar.shakhs.name" id="search_nameBimeGozar_b" value="${pishnehad.bimeGozar.shakhs.name}"/>
                    &nbsp;<label>نام بیمه گذار</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.bimeGozar.shakhs.nameKhanevadegi" id="search_nameKhaBimeGozar" value="${pishnehad.bimeGozar.shakhs.nameKhanevadegi}" />
                    &nbsp;<label>نام خانوادگی بیمه گذار</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.bimeGozar.shakhs.kodeMelliShenasayi" id="search_kodMelliBimeGozar" value="${pishnehad.bimeGozar.shakhs.kodeMelliShenasayi}" />
                    &nbsp;<label>کد ملی بیمه گذار</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.bimeShode.shakhs.name" id="search_nameBimeShode_b" value="${pishnehad.bimeShode.shakhs.name}"/>
                    &nbsp;<label>نام بیمه شده</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.bimeShode.shakhs.nameKhanevadegi" id="search_nameKhaBimeShode" value="${pishnehad.bimeShode.shakhs.nameKhanevadegi}" />
                    &nbsp;<label>نام خانوادگی بیمه شده</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.bimeShode.shakhs.kodeMelliShenasayi" id="search_kodMelliBimeShode" value="${pishnehad.bimeShode.shakhs.kodeMelliShenasayi}" />
                    &nbsp;<label>کد ملی بیمه شده</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.estelam.noe_tarh" id="search_noeTarh" />
                    &nbsp;<label>نوع طرح</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.karshenas.firstName" id="search_karshenas" />
                    &nbsp;<label>کاربر ثبت کننده</label>
                </td>
                <td>
                    <%
                      if(request.getAttribute("grouhHa")!=null)
                      {
                    %>
                    <span class="noThing"></span>
                    <select id="bimename_goroh" name="groupId">
                        <%
                            for (Gharardad goroh : (List<Gharardad>)request.getAttribute("grouhHa")) { %>
                            <option value="<%=goroh.getId() == null ? "" : goroh.getId()%>" >
                                <%=goroh.getNameSherkat()%>
                            </option>
                        <%}%>
                    </select>
                    &nbsp;<label>گروه بيمه‌نامه</label>
                    <%}%>
                </td>
            </tr>
            <tr>
                <td>
                    <script type="text/javascript">
                        $(document).ready(function(){$('#search_subsetState').hide();});

                        function crtDrpDwn()
                        {
                            var valState=$('#search_state').val();
                            if(valState==500)
                            {
                                $('#search_subsetState').show();
                                $('#search_subsetState').empty();
                                var data={0:'-',1:'بیمه نامه دائم بدون الحاقیه',2:'بیمه نامه دائم دارای الحاقیه'};
                            }

                            else if(valState==510)
                            {
                                $('#search_subsetState').show();
                                $('#search_subsetState').empty();
                                var data={0:'-',1:'الحاقیه تغییرات',2:'بهره مندی از منافع',3:'خسارت'};
                            }
                            else
                            {
                                $('#search_subsetState').empty();
                                $('#search_subsetState').hide();
                            }
                            for(var val in data)
                            {
                               $('<option />', {value: val, text: data[val]}).appendTo('#search_subsetState');
                            }
                            s.appendTo('#dpTd');
                        }
                    </script>
                    <span class="noThing"></span>
                    <select name="bimename.state.id" id="search_state">
                        <option onclick="crtDrpDwn();" value="">-</option>
                        <option onclick="crtDrpDwn();" value="350">فسخ از طرف بیمه گذار</option>
                        <%--<option onclick="crtDrpDwn();" value="">فسخ از طرف بیمه گر</option>--%>
                        <option onclick="crtDrpDwn();" value="500" >بیمه نامه دائم</option>
                        <option onclick="crtDrpDwn();" value="510">الحاقیه در جریان</option>
                        <option onclick="crtDrpDwn();" value="540">ابطال شده</option>
                        <option onclick="crtDrpDwn();" value="550">بازخرید شده</option>
                    </select>
                    &nbsp;<label>وضعیت بیمه نامه</label>
                </td>
                <td id="dpTd">
                    <span class="noThing"></span>
                    <select name="subsetState" id="search_subsetState"/>
                </td>
                <td>
                    <%--<td>--%>
                    <div class="dblRadio" style="">
                        <input type="checkbox" style="float:left;"   value="CODE_MOVAGHAT" name="pishnehad.options" id="code_movaghat" title="کد موقت"  />
                        <label for="code_movaghat" style="float:left;width:100px" >کد موقت</label>

                    </div>
                    <%--</td>--%>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <script type="text/javascript">
                        function clearSeachFrom_b() {
                            $('#search_kod_rahgiri_b').val('');
                            $('#search_shomare_b').val('');
                            $('#search_sarmaye_fot_b').val('');
                            $('#bimenameSearch_nameBimeGozar_b').val('');
                            $('#bimenameSearch_nameBimeShode_b').val('');
                            $('#sabt_ta_tarikh_b').val('<%=DateUtil.getCurrentDate()%>');
                        }
                    </script>
                    <input type="button" style="margin-left:25px" value="پاک کردن فرم" onclick="clearSeachFrom_b()">
                    <%--<input type="submit" style="margin-left:25px" value="جستجو">--%>
                    <input type="button" onclick="searchBimenameAjax()" style="margin-left:25px" value="جستجو">
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function searchBimenameAjax() {
        $.ajax({
            type: "POST",
            url: 'resultTab3.action',
            data: $('#form_search_for_bimename').serialize(),
            success: function (response) {
                // we have the response
                $('#displayTab3').html(response);
            }
        });
    }

</script>