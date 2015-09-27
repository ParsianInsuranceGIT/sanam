<%@ page import="com.bitarts.parsian.model.pishnahad.Pishnehad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Pishnehad pishnehadRun = (Pishnehad) request.getAttribute("pishnehad");
%>
<p style="text-align:right;">
شرایط جدید پیشنهادی:
</p>
<br/>
<br/>
<table class="jtable" align="center" width="50%" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
        <thead>
        <tr>
            <th style ="text-align:center;">
                نام فیلد
            </th>
            <th style ="text-align:center;">
                مقدار قبلی
            </th>
            <th style ="text-align:center;">
                مقدار جدید
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>حق بیمه اولیه   (ریال)</td>
            <%--<%=pishnehadRun.getSharayeteJadid().getHagh_bime_pardakhti()%>--%>
            <td>${pishnehad.estelam.mablagh_seporde_ebtedaye_sal}</td>
            <td>${pishnehad.sharayeteJadid.mablagh_seporde_ebtedaye_sal}</td>
        </tr>
        <tr>
            <td>حق بیمه منظم پرداختی (ریال)</td>
            <%--<%=pishnehadRun.getSharayeteJadid().getHagh_bime_pardakhti()%>--%>
            <td>${pishnehad.estelam.hagh_bime_pardakhti}</td>
            <td>${pishnehad.sharayeteJadid.hagh_bime_pardakhti}</td>
        </tr>
        <tr>
            <td>درصد افزایش سالانه حق بیمه</td>
            <td>%${pishnehad.estelam.nerkh_afzayesh_salaneh_hagh_bime}</td>
            <td>%${pishnehad.sharayeteJadid.nerkh_afzayesh_salaneh_hagh_bime}</td>
        </tr>
        <tr>
            <td>درصد افزایش سالانه سرمایه فوت </td>
            <td>%${pishnehad.estelam.nerkh_afzayesh_salaneh_sarmaye_fot}</td>
            <td>%${pishnehad.sharayeteJadid.nerkh_afzayesh_salaneh_sarmaye_fot}</td>
        </tr>
        <tr>
            <td>مدت بیمه نامه (سال)</td>
            <td>${pishnehad.estelam.modat_bimename}</td>
            <td>${pishnehad.sharayeteJadid.modat_bimename}</td>
        </tr>
        <tr>
            <td>نحوه پرداخت حق بیمه</td>
            <td>
                <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime=='mah'}">
                    ماهانه
                </c:if>
                <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime=='3mah'}">
                    سه ماهه
                </c:if>
                <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime=='6mah'}">
                    شش ماهه
                </c:if>
                <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime=='sal'}">
                    سالانه
                </c:if>
                <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime=='yekja'}">
                    یکجا
                </c:if>
            </td>
            <td>
                <c:if test="${pishnehad.sharayeteJadid.nahve_pardakht_hagh_bime=='mah'}">
                    ماهانه
                </c:if>
                <c:if test="${pishnehad.sharayeteJadid.nahve_pardakht_hagh_bime=='3mah'}">
                    سه ماهه
                </c:if>
                <c:if test="${pishnehad.sharayeteJadid.nahve_pardakht_hagh_bime=='6mah'}">
                    شش ماهه
                </c:if>
                <c:if test="${pishnehad.sharayeteJadid.nahve_pardakht_hagh_bime=='sal'}">
                    سالانه
                </c:if>
                <c:if test="${pishnehad.sharayeteJadid.nahve_pardakht_hagh_bime=='yekja'}">
                    یکجا
                </c:if>
            </td>
        </tr>
        <tr>
            <td>پوشش فوت در اثر حادثه</td>
            <td>
                <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_haadese=='yes'}">
                    دارد
                </c:if>
                <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_haadese!='yes'}">
                    ندارد
                </c:if>
            </td>
            <td>
                <c:if test="${pishnehad.sharayeteJadid.pooshesh_fot_dar_asar_haadese=='yes'}">
                    دارد
                </c:if>
                <c:if test="${pishnehad.sharayeteJadid.pooshesh_fot_dar_asar_haadese!='yes'}">
                    ندارد
                </c:if>
            </td>
        </tr>
        <tr>
            <td>پوشش امراض خاص</td>
            <td>
                <c:if test="${pishnehad.estelam.pooshesh_amraz_khas=='yes'}">
                    دارد
                </c:if>
                <c:if test="${pishnehad.estelam.pooshesh_amraz_khas!='yes'}">
                    ندارد
                </c:if>
            </td>
            <td>
                <c:if test="${pishnehad.sharayeteJadid.pooshesh_amraz_khas=='yes'}">
                    دارد
                </c:if>
                <c:if test="${pishnehad.sharayeteJadid.pooshesh_amraz_khas!='yes'}">
                    ندارد
                </c:if>
            </td>
        </tr>
        <tr>
            <td>پوشش نقص عضو</td>
            <td>
                <c:if test="${pishnehad.estelam.pooshesh_naghs_ozv=='yes'}">
                    دارد
                </c:if>
                <c:if test="${pishnehad.estelam.pooshesh_naghs_ozv!='yes'}">
                    ندارد
                </c:if>
            </td>
            <td>
                <c:if test="${pishnehad.sharayeteJadid.pooshesh_naghs_ozv=='yes'}">
                    دارد
                </c:if>
                <c:if test="${pishnehad.sharayeteJadid.pooshesh_naghs_ozv!='yes'}">
                    ندارد
                </c:if>
            </td>
        </tr>
        <tr>
            <td>پوشش معافیت</td>
            <td>
                <c:if test="${pishnehad.estelam.pooshesh_moafiat=='yes'}">
                    دارد
                </c:if>
                <c:if test="${pishnehad.estelam.pooshesh_moafiat!='yes'}">
                    ندارد
                </c:if>
            </td>
            <td>
                <c:if test="${pishnehad.sharayeteJadid.pooshesh_moafiat=='yes'}">
                    دارد
                </c:if>
                <c:if test="${pishnehad.sharayeteJadid.pooshesh_moafiat!='yes'}">
                    ندارد
                </c:if>
            </td>
        </tr>
        <tr>
            <td>سرمایه پایه فوت (ریال)</td>
            <td>${pishnehad.estelam.sarmaye_paye_fot}</td>
            <td>${pishnehad.sharayeteJadid.sarmaye_paye_fot}</td>
        </tr>
        <tr>
            <td>سرمایه پایه فوت در اثر حادثه (ریال)</td>
            <td>
            ${pishnehad.estelam.sarmaye_paye_fot_dar_asar_hadese}
                <c:if test="${pishnehad.estelam.sarmaye_paye_fot_dar_asar_hadese==null}">-</c:if>
            </td>
            <td>
                ${pishnehad.sharayeteJadid.sarmaye_paye_fot_dar_asar_hadese}
                <c:if test="${pishnehad.sharayeteJadid.sarmaye_paye_fot_dar_asar_hadese==null}">-</c:if>
            </td>
        </tr>
        <tr>
            <td>سرمایه پوشش نقص عضو (ریال)</td>
            <td>
                ${pishnehad.estelam.sarmaye_pooshesh_naghs_ozv}
                <c:if test="${pishnehad.estelam.sarmaye_pooshesh_naghs_ozv==null}">-</c:if>

            </td>
            <td>
                ${pishnehad.sharayeteJadid.sarmaye_pooshesh_naghs_ozv}
                <c:if test="${pishnehad.sharayeteJadid.sarmaye_pooshesh_naghs_ozv==null}">-</c:if>
            </td>
        </tr>
        <tr>
            <td>سرمایه پوشش امراض خاص (ریال)</td>
            <td>
                ${pishnehad.estelam.sarmaye_pooshesh_amraz_khas}
                <c:if test="${pishnehad.estelam.sarmaye_pooshesh_amraz_khas==null}">-</c:if>

            </td>
            <td>
                ${pishnehad.sharayeteJadid.sarmaye_pooshesh_amraz_khas}
                <c:if test="${pishnehad.sharayeteJadid.sarmaye_pooshesh_amraz_khas==null}">-</c:if>
            </td>
        </tr>
        </tbody>
    </table>
<br/>
<br/>
<br/>
<br/>
<input type="button" onclick="switchToMohasebatPaye();" value="جدول محاسبات پایه"/>

<script type="text/javascript">
function switchToMohasebatPaye(){
    $("#tab_19").show();
    $('.tabsbtn').removeClass('activesubmit');
    $('#tab_19').addClass('activesubmit');
    $('.content').hide();
    $('#content_19').show();
    showMohasebatPaye();
}
</script>