<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 4/7/11
  Time: 2:33 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" name="pishnehad.foroshande.id" value="${pishnehad.foroshande.id}">
<table class="inputList" id="disableForSomeSecondsForushande_tbl" border="0" cellspacing="5" cellpadding="1" style="width:780px;text-align:right;">
    <col class=inputCol><col class=inputCol>
    <tr>
        <td>
            <span class="noThing"></span>
            <div class="dblRadio" onclick="refreshButtonset();">
                <input type="radio" name="pishnehad.foroshande.pishnahaad_dahande_mishenaasid" id="pishnahaad_dahande_mishenaasid_n"
                       <c:if test="${pishnehad.foroshande.pishnahaad_dahande_mishenaasid == 'kheir'}">checked="checked"</c:if>
                       onchange="if($(this).attr('checked')){deactivate('modat_shenaakht');}" value="kheir">
                <input type="radio" name="pishnehad.foroshande.pishnahaad_dahande_mishenaasid" id="pishnahaad_dahande_mishenaasid_y"
                       <c:if test="${pishnehad.foroshande.pishnahaad_dahande_mishenaasid != 'kheir'}">checked="checked"</c:if>
                       onchange="if($(this).attr('checked')){activate('modat_shenaakht');}" value="bali">
                <label for="pishnahaad_dahande_mishenaasid_n">خیر</label>
                <label for="pishnahaad_dahande_mishenaasid_y">بلی</label>
            </div>
            &nbsp;<label>آیا پیشنهاد دهنده را شخصاً می شناسید؟</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type=text name="pishnehad.foroshande.modat_shenaakht" value='${pishnehad.foroshande.modat_shenaakht}' id="modat_shenaakht"
                   class="validate[required] text-input"/>
            &nbsp;<label for="">از چه مدتی:</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <div class="dblRadio" onclick="refreshButtonset();">
                <input type="radio" name="pishnehad.foroshande.molaahezaat_az_salaamat_feli" id="molaahezaat_az_salaamat_feli_n"
                       <c:if test="${pishnehad.foroshande.molaahezaat_az_salaamat_feli == 'kheir'}">checked="checked"</c:if>
                       onchange="if($(this).attr('checked')){deactivate('tozihe_molaahezaat');}" value="kheir"/>
                <input type="radio" name="pishnehad.foroshande.molaahezaat_az_salaamat_feli" id="molaahezaat_az_salaamat_feli_y"
                       <c:if test="${pishnehad.foroshande.molaahezaat_az_salaamat_feli != 'kheir'}">checked="checked"</c:if>
                       onchange="if($(this).attr('checked')){activate('tozihe_molaahezaat');}" value="bali"/>
                <label for="molaahezaat_az_salaamat_feli_n">خیر</label>
                <label for="molaahezaat_az_salaamat_feli_y">بلی</label>
            </div>
            &nbsp;<label>آیا ملاحظات خاصی از سلامت فعلی یا گذشته او دارید؟</label>
        </td>
        <td>
            <span class="noThing"></span>
            <input type=text name="pishnehad.foroshande.tozihe_molaahezaat" value='${pishnehad.foroshande.tozihe_molaahezaat}' id="tozihe_molaahezaat"
                   class="validate[required] text-input"/>
            &nbsp;<label for="tozihe_molaahezaat">شرح دهيد</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <div class="dblRadio" onclick="refreshButtonset();">
                <input type="radio" name="pishnehad.foroshande.etelaat_kaamel" id="etelaat_kaamel_n"
                       <c:if test="${pishnehad.foroshande.etelaat_kaamel != 'bali'}">checked="checked"</c:if>
                       value="kheir"/>
                <input type="radio" name="pishnehad.foroshande.etelaat_kaamel" id="etelaat_kaamel_y"
                       <c:if test="${pishnehad.foroshande.etelaat_kaamel == 'bali'}">checked="checked"</c:if>
                       value="bali"/>
                <label for="etelaat_kaamel_n">خیر</label>
                <label for="etelaat_kaamel_y">بلی</label>
            </div>
            &nbsp;<label>آیا درباره شرایط بیمه عمر اطلاعات کامل در اختیار بیمه گذار و بیمه شده گذارده            اید؟</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="noThing"></span>
            <div class="dblRadio" onclick="refreshButtonset();">
                <input type="radio" name="pishnehad.foroshande.sehat_emzaa" id="sehat_emzaa_n"
                       <c:if test="${pishnehad.foroshande.sehat_emzaa != 'bali'}">checked="checked"</c:if>
                       value="kheir"/>
                <input type="radio" name="pishnehad.foroshande.sehat_emzaa" id="sehat_emzaa_y"
                       <c:if test="${pishnehad.foroshande.sehat_emzaa == 'bali'}">checked="checked"</c:if>
                       value="bali"/>
                <label for="sehat_emzaa_n">خیر</label>
                <label for="sehat_emzaa_y">بلی</label>
            </div>
            &nbsp;<label>آيا صحت امضاء بيمه‌شده و بيمه گذار مورد تأييد می باشد؟</label>
        </td>
    </tr>
</table>
<br/>
<br/>
<table>
    <sec:authorize ifAnyGranted="ROLE_SUPERVISOR,ROLE_ADMIN,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_PEZESHK,ROLE_RAEIS_SODUR">
        <c:if test="${pishnehad.bimename != null&&pishnehad.state.id>=250}">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <label>امضا كننده اول:</label>
                    &nbsp;<input type="text" value="${pishnehad.bimename.emzaKonandeAval.user.firstName} ${pishnehad.bimename.emzaKonandeAval.user.lastName}" readonly="readonly">
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <label>	امضا كننده دوم:</label>
                    &nbsp;<input type="text" value="${pishnehad.bimename.emzaKonandeDovom.user.firstName} ${pishnehad.bimename.emzaKonandeDovom.user.lastName}" readonly="readonly">
                </td>
            </tr>
        </c:if>
    </sec:authorize>
</table>
<script type="text/javascript">
//    function disableForSomeSeconds(id){
//        var size = 4;
//        var counter = 0;
//        $('#disableForSomeSecondsForushande_tbl .dblRadio').each(function(){
//            if(counter%1 == 1 || counter == id)$(this).buttonset().buttonset("disable");
//            counter++;
//            if(counter == size){
//                counter = 0;
//                $('#disableForSomeSecondsForushande_tbl .dblRadio').each(function(){
//                    if(counter%1 == 1 || counter == id)$(this).buttonset().buttonset("enable");
//                    counter++;
//                });
//            }
//        });
//    }
    function refreshButtonset()
    {

        $('.dblRadio').buttonset("refresh");

    }
</script>