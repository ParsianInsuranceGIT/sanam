<%@ page import="com.bitarts.parsian.model.bimename.Gharardad" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="" id="form_search_taghsit" method="post">


<table class="inputList" cellspacing="5" width="90%">
    <tr>
        <td>
            <input type="text" name="batchTaghsitVMS.fromTarikhShoroNewYear" id="new_year_date_from"
                   value="${batchTaghsitVMS.fromTarikhShoroNewYear}" class="datePkr validate[custom[date]]"/>
            &nbsp;<label>تاریخ شروع سال جدید (از) </label>
        </td>
        <td>
            <input type="text" name="batchTaghsitVMS.toTarikhShoroNewYear" id="new_year_date_to"
                   value="${batchTaghsitVMS.toTarikhShoroNewYear}" class="datePkr validate[custom[date]]"/>
            &nbsp;<label>تاریخ شروع سال جدید (تا)</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type="text" name="batchTaghsitVMS.saleBimeei" value="${batchTaghsitVMS.saleBimeei}" id="sale_bime"/>
                &nbsp;<label>سال بیمه ای</label>
        </td>
    </tr>
    <tr>
        <td>
            <input type="text" name="batchTaghsitVMS.fromTarikheTaghsit" value="${batchTaghsitVMS.fromTarikheTaghsit}"
                   id="taghsit_from" class="datePkr validate[custom[date]]"/>
            &nbsp;<label>از تاریخ تقسیط</label>
        </td>
        <td>
            <input type="text" name="batchTaghsitVMS.toTarikheTaghsit" value="${batchTaghsitVMS.toTarikheTaghsit}"
                   id="taghsit_to" class="datePkr validate[custom[date]]"/>
            &nbsp;<label>تا تاریخ تقسیط</label>
        </td>
        <td>
            <span class="noThing"></span>
            <select name="batchTaghsitVMS.nahvePardakht" id="search_nahve_pardakht_hagh_bime">
                <option ${batchTaghsitVMS.nahvePardakht ==null ? 'selected=selected' : '' } value="">-
                </option>
                <option ${batchTaghsitVMS.nahvePardakht == 'mah' ? 'selected=selected' : '' } value="mah">
                    ماهانه
                </option>
                <option ${batchTaghsitVMS.nahvePardakht == '3mah' ? 'selected=selected': '' } value="3mah">
                    سه
                    ماهه
                </option>
                <option ${batchTaghsitVMS.nahvePardakht == '6mah' ? 'selected=selected': '' } value="6mah">
                    شش
                    ماهه
                </option>
                <option ${batchTaghsitVMS.nahvePardakht == 'sal' ? 'selected=selected' : '' } value="sal">
                    سالانه
                </option>
                <option ${batchTaghsitVMS.nahvePardakht == 'yekja' ? 'selected=selected': '' } value="yekja">
                    یکجا
                </option>
            </select>
            &nbsp;<label>نحوه پرداخت حق بیمه</label>
        </td>
    </tr>
    <tr>
        <td>
            &nbsp;<label>نمایندگی</label>
            <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_namayandegiId','search_namayandegiName', '');" style="float:left;" title="جستجو"></span>
            <input type="hidden" name="batchTaghsitVMS.namayandeId" id="search_namayandegiId" value="${batchTaghsitVMS.namayandeName}"/>
            <input type="text" name="batchTaghsitVMS.namayandeName" id="search_namayandegiName" value="${batchTaghsitVMS.namayandeName}" readonly="true"/>
        </td>
        <td>
            &nbsp;<label>واحد صدور</label>
            <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_vahedeSodurId','search_vahedeSodurName', '');" style="float:left;" title="جستجو"></span>
            <input type="hidden" name="batchTaghsitVMS.vahedSodurId" id="search_vahedeSodurId" value="${batchTaghsitVMS.vahedSodurId}"/>
            <input type="text" name="batchTaghsitVMS.vahedSodurName" id="search_vahedeSodurName" value="${batchTaghsitVMS.vahedSodurName}" readonly="true"/>

        </td>
        <td>
            <%
                if (request.getAttribute("grouhHa") != null)
                {
            %>
            <span class="noThing"></span>
            <select id="bimename_goroh" name="batchTaghsitVMS.gorohId">
                <%
                    for (Gharardad goroh : (List<Gharardad>) request.getAttribute("grouhHa"))
                    {
                %>
                <option value="<%=goroh.getId() == null ? "" : goroh.getId()%>">
                    <%=goroh.getNameSherkat()%>
                </option>
                <%}%>
            </select>
            &nbsp;<label>گروه بيمه‌نامه</label>
            <%}%>
        </td>
    </tr>
    <%-------------------------------------------------------------------------------------------------------%>
    <tr>
        <td>
            <span class="noThing"></span>
            <input type="text" name="batchTaghsitVMS.noe_tarh" id="noe_tarh" value="${batchTaghsitVMS.noe_tarh}"/>
            &nbsp;<label>نوع طرح</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type="text" name="batchTaghsitVMS.noe_gharardad" id="noe_gharardad" value="${batchTaghsitVMS.noe_gharardad}"/>
            &nbsp;<label>نوع قرارداد</label>
        </td>
        <td>
            <span class="noThing"></span>
            <select name="batchTaghsitVMS.karshenasId" id="batchTaghsitVMS_karshenasId">
                <option value="0">-</option>
                <%--<option value="-2">ندارد</option>--%>
                <c:forEach var="karshenas" items="${karshenasha}">
                    <option ${ batchTaghsitVMS.karshenasId == karshenas.id ? 'selected=selected' : ''}
                            value="${karshenas.id}">${karshenas.firstName}&nbsp;${karshenas.lastName}</option>
                </c:forEach>
            </select>
            &nbsp;<label>نام کارشناس</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <input type="text" name="batchTaghsitVMS.kodeMelliBimeGozar" id="kodeMelli" value="${batchTaghsitVMS.kodeMelliBimeGozar}"/>
            &nbsp;<label>کد ملی بیمه گذار</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type="text" name="batchTaghsitVMS.shomareBimename" id="shomareBimename"
                   value="${batchTaghsitVMS.shomareBimename}"/>
            &nbsp;<label>شماره بیمه نامه</label>
        </td>
    </tr>
    <tr>
        <td colspan="3">
            <script type="text/javascript">
                function clearSeachFrom_b()
                {
                    //                $('#search_kod_rahgiri_b').val('');
                    //                $('#search_sarmaye_fot_b').val('');
                    //                $('#bimenameSearch_nameBimeGozar_b').val('');
                    //                $('#bimenameSearch_nameBimeShode_b').val('');
                    <%--$('#sabt_ta_tarikh_b').val('<%=DateUtil.getCurrentDate()%>');--%>
                }
                function searchTaghsit()
                {
                    $('#form_search_taghsit').removeAttr('target');
                    $('#form_search_taghsit').attr('action', 'batchTaghsitReport.action');
                    $("#form_search_taghsit").submit();
                }

            </script>
            <input type="button" style="margin-left:25px" value="پاک کردن فرم" onclick="clearSeachFrom_b()">

            <input type="button" onclick="searchTaghsit();" style="margin-left:25px" value="جستجو">
        </td>
    </tr>
</table>
</form>
<br/><hr style="color: #1d5987"/>
</div>
