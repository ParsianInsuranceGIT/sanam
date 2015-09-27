<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ include file="../taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <script type="text/javascript" src="../../js/jquery.print.js"></script>
    <title>سیستم استعلام مستمری</title>
</head>
<body>
<form class="mainFrame" id="mainFormMostamari" method="post" action="/mohaasebeMostamari.action"
      onsubmit="$('.content').slideDown(0)">

    <div class="pageTitle">
        سيستم استعلام مستمري اندوخته انتهاي دوره
    </div>
        <div class="expandableTitleBar">
            <p class="heading">
                <span class="ui-icon ui-icon-plus" style="float:right;"></span>مشخصات مستمري
            </p>

            <div class="content">
                <%@include file="mostamari_moshakhasateMostamari.jsp"%>
            </div>
        </div>
    <div>
        <input type="hidden" id="mostamari_nam_bimeshode" value="${estelam.nam_bime_shode}">
        <input type="hidden" id="mostamari_darsad_ezafe_nerkh_pezeshki" value="${estelam.darsad_ezafe_nerkh_pezeshki}">
        <input type="hidden" id="mostamari_sarmaye_paye_fot" value="${estelam.sarmaye_paye_fot}">
        <input type="hidden" id="mostamari_sarmaye_paye_fot_dar_asar_hadese" value="${estelam.sarmaye_paye_fot_dar_asar_hadese}">
        <input type="hidden" id="mostamari_sarmaye_pooshesh_amraz_khas" value="${estelam.sarmaye_pooshesh_amraz_khas}">
        <input type="hidden" id="mostamari_sarmaye_pooshesh_naghs_ozv" value="${estelam.sarmaye_pooshesh_naghs_ozv}">
        <input type="hidden" id="mostamari_hagh_bime_pardakhti" value="${estelam.hagh_bime_pardakhti}">
        <input type="hidden" id="mostamari_nahve_pardakht_hagh_bime" value="${estelam.nahve_pardakht_hagh_bime}">
        <input type="hidden" id="mostamari_nerkh_afzayesh_salaneh_hagh_bime" value="${estelam.nerkh_afzayesh_salaneh_hagh_bime}">
        <input type="hidden" id="mostamari_nerkh_afzayesh_salaneh_sarmaye_fot" value="${estelam.nerkh_afzayesh_salaneh_sarmaye_fot}">
    </div>
    <div style="height:25px;margin-top:5px;margin-bottom:15px;">
        <input type="button" onclick="mohasebeMostamari();" value="محاسبه" style="margin-right: 20px;float:right;"/>
        <input type="button" class="prt ui-state-disabled" style="margin-right:10px;float:right;" value="پرینت"  disabled/>
        <sec:authorize ifAllGranted="ROLE_USER">
            <input type="button" onclick="window.location='/loginUser.action';" value="بازگشت" style="margin-right: 10px;float:right;cursor:pointer"/>
        </sec:authorize>
        <sec:authorize ifAllGranted="ROLE_SUPERVISOR">
            <input type="button" onclick="addComment();" value="نظر شما" style="margin-right: 10px;float:right;"/>
        </sec:authorize>
    </div>

    <div id="resultMostamari"></div>
</form>
<script type="text/javascript">

    $( ".prt" ).bind('click', function(e){
        var srvr_date = '<%=DateUtil.getCurrentDate()%>';
        $('#Taarikh_akhz_gozareshMostamari').html(srvr_date.split('/')[0]+'/'+srvr_date.split('/')[1]+'/'+srvr_date.split('/')[2]);
        $('#gozaresh_sen_bime_shode_v_mostamari').html($('#sen_mostamari_begir').val());
        $('#gozaresh_naam_naam_khaanevaadegi_mostamari').html($('#mostamari_nam_bimeshode').val());
        $('#gozaresh_darsad_ezafe_nerkh_pezeshki_mostamari').html($('#mostamari_darsad_ezafe_nerkh_pezeshki').val());
        $('#gozaresh_sarmaye_paye_fot_mostamari').html($('#mostamari_sarmaye_paye_fot').val());
        $('#gozaresh_sarmaye_paye_fot_dar_asar_hadese_mostamari').html($('#mostamari_sarmaye_paye_fot_dar_asar_hadese').val());
        $('#gozaresh_sarmaye_pooshesh_amraz_khas_mostamari').html($('#mostamari_sarmaye_pooshesh_amraz_khas').val());
        $('#gozaresh_sarmaye_pooshesh_naghs_ozv_mostamari').html($('#mostamari_sarmaye_pooshesh_naghs_ozv').val());
        $('#gozaresh_hagh_bime_pardakhti_mostamari').html($('#mostamari_hagh_bime_pardakhti').val());

        var nahvePardakht=$('#mostamari_nahve_pardakht_hagh_bime').val();
        var resultNahvePardakht= "";
        if(nahvePardakht=='mah'){
            resultNahvePardakht= "ماهانه";
        }else if(nahvePardakht=='3mah'){
            resultNahvePardakht= "سه ماهه";
        }else if(nahvePardakht=='6mah'){
            resultNahvePardakht= "شش ماهه";
        }else if(nahvePardakht=='sal'){
            resultNahvePardakht= "سالانه";
        }
        $('#gozaresh_nahve_pardakht_haghbime_mostamari').html(resultNahvePardakht);
        $('#gozaresh_nerkh_afzayesh_salaneh_hagh_bime_mostamari').html($('#mostamari_nerkh_afzayesh_salaneh_hagh_bime').val());
        $('#gozaresh_nerkh_afzayesh_salaneh_sarmaye_fot_mostamari').html($('#mostamari_nerkh_afzayesh_salaneh_sarmaye_fot').val());
        var noeMostamari = $('#noe_mostamari_darkhasti').val();
        var resNoeMostamari = "";
        if(noeMostamari == 'modatMoayan'){
            resNoeMostamari = "مستمری مدت معین";
        }else{
            resNoeMostamari = "مستمري مادام العمر يك نفره با دوره تضمين پرداخت ";
        }
        $('#gozaresh_noe_mostamari_darkhasti').html(resNoeMostamari);

        var nahvePardakht = $("#nahve_pardakht_mostamari").val();

        var resNahvePardakht = "";
        if (nahvePardakht == 'enteha'){
            resNahvePardakht = "مستمری در انتهای دوره";
        }else{
            resNahvePardakht = "مستمری در ابتدای دوره"
        }
        $('#gozaresh_nahve_pardakht_mostamari').html(resNahvePardakht);
        $('#gozaresh_dore_tazmin_pardakht').html($('#dowre_tazmin_pardakht').val());
        $('.jtable').css('direction', 'rtl');
        $('.jtable').css('font-size', '9px');
        <%----%>
        $('#prtDataContainerMostamari').html($('#printHeaderMostamari').html());

        $('#prtDataContainerMostamari').append($('#prtDivMostamari').html());

        $('#prtDataContainerMostamari').append($('#printFooterMostamari').html());
        $('#prtDataContainerMostamari .jtable').attr('border', 1);
        $('#prtDataContainerMostamari .jtable').css('font-family', 'B Nazanin');

        $("#prtDataContainerMostamari").print();
        $('.jtable').css('font-size', '11px');
        return( false );
        <%----%>
    });

    function mohasebeMostamari(){
        var mostamariData = $("#mainFormMostamari").serialize();
        $.post($('#mainFormMostamari').attr('action'),mostamariData, function(msg){
            $("#resultMostamari").html(msg);
            $('.prt').removeClass('ui-state-disabled');
            $('.prt').removeAttr('disabled');
        });

    }
</script>
</body>
</html>