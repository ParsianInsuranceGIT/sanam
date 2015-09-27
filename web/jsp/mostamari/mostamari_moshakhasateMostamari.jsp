<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="inputList" cellspacing="5" width="90%">
    <col class="inputCol">
    <col class="inputCol">
    <tr>
        <td>
            <span class="help"
                          title="توضیحات مربوطه بیاید"></span>
            <input type="text" name="mostamari.andukhteEntehaModatBimename" id="andukhte_enteha_modat_bimename"
                   class="digitSeparators text-input" readonly="readonly"
                   value='${mostamari.andukhteEntehaModatBimename}' />
            &nbsp;<label>اندوخته انتهاي مدت بيمه نامه(ريال)</label>
        </td>
        <td>
            <span class="help"
                          title="توضیحات مربوطه بیاید"></span>
            <input type="text" name="mostamari.senMostamariBegir" id="sen_mostamari_begir"
                   class="" readonly="readonly"
                   value='${mostamari.senMostamariBegir}' />
            &nbsp;<label>سن مستمري بگير</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help"
                  title="توضیحات مربوطه بیاید"></span>
            <input type="text" name="mostamari.dowreTazminPardakht" id="dowre_tazmin_pardakht"
                   class=""
                   value='0' onchange="checkAgainForSen();" onkeypress="checkAgainForSen();" onkeydown="checkAgainForSen();" onkeyup="checkAgainForSen();" />
            &nbsp;<label>دوره تضمين پرداخت (سال)</label>
        </td>
        <td>
            <span class="help" title="توسط فرد خبره پر شود!!"></span>
            <select name="mostamari.noeMostamariDarkhasti" id="noe_mostamari_darkhasti">
                <option value="modatMoayan">مستمري مدت معين</option>
                <option id="mostamari_madam" value="madamolomr">مستمري مادام العمر يك نفره با دوره تضمين پرداخت </option>
            </select>&nbsp;<label>نوع مستمري درخواستي</label>
        </td>
    </tr>
    <tr>
        <td>
            <span class="help" title="نرخی است که بر اساس آن میزان مستمری سالانه افزایش پیدا می کند."></span>
            <select name="mostamari.nerkhAfzayeshSalaneMostamari" id="nerkh_afzayesh_salane_mostamari"
                    class="">
                <option value="0">0 درصد</option>
                <option value="5">5 درصد</option>
                <option value="10">10 درصد</option>
                <option value="15">15 درصد</option>
                <option value="20">20 درصد</option>
            </select>
            &nbsp;<label>نرخ افزایش سالانه مستمری</label>
        </td>
        <td>
            <span class="help"
                          title="نشان دهنده چگونگی دریافت مستمری توسط بیمه شده... بقیه شو نمیدونم!!"></span>
            <select name="mostamari.nahvePardakhtMostamari" id="nahve_pardakht_mostamari">

                <option value="enteha">مستمری در انتهای دوره</option>
                <option value="ebteda">مستمری در ابتدای دوره</option>
            </select>&nbsp;<label>نحوه پرداخت مستمری</label>
        </td>
    </tr>
</table>
<script type="text/javascript">
    var sen = "${mostamari.senMostamariBegir}";
    var dowre = $("#dowre_tazmin_pardakht").val();
    var majmu = parseInt(sen)+parseInt(dowre);
    if(majmu<60){
        $("#mostamari_madam").hide();
    }else{
        $("#mostamari_madam").show();
    }
    function checkAgainForSen(){
        var sen = "${mostamari.senMostamariBegir}";
        var dowre = $("#dowre_tazmin_pardakht").val();
        var majmu = parseInt(sen)+parseInt(dowre);
        if(majmu>=60){
            $("#mostamari_madam").show();
        }else{
            $("#mostamari_madam").hide();
        }
    }
</script>
