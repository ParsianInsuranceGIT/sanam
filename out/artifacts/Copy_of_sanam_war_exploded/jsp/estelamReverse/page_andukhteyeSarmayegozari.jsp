<%@ include file="../taglibs.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="../../css/andukhteyeSarmayegozari.css"/>
    <script type="text/javascript" src="../../js/validation/estelam_valFuncs.js"></script>
    <script type="text/javascript" src="../../js/jquery.print.js"></script>

    <title>سيستم استعلام بيمه نامه هاي عمروسرمايه‌گذاري</title>
</head>
<body>
<%@include file="/jsp/estelam/estelam_js.jsp"%>
<div id="addCommetnDiv" class="vld" style="display:none;direction:rtl;">
    <form id="addCommetnForm">
        <table style="width:600px;border-spacing:10px" align="center" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td colspan="4">
                    <div id="mesg" style="display:none;" class="successMessage">نظر شما با موفقیت ثبت شد.</div>
                </td>
            </tr>
            <tr>
                <td>&nbsp;<label>نام و نام خانوادگی</label></td>
                <td><input type="text" class="validate[required,custom[onlyPersianLetter]] text-input" name="userComment.userName" id="userComment_userName"/></td>
                <td>&nbsp;<label>پست الکترونیک</label></td>
                <td><input type="text" class="validate[required,custom[email]] text-input" name="userComment.email" id="userComment_email"/></td>
            </tr>
            <tr>
                <td>
                    &nbsp;<label>کد نمایندگی</label>
                </td>
                <td>
                    <input type="text" class="validate[required] text-input" name="userComment.codeNamayandegi" id="userComment_codeNamayandegi"/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;<label>نظر</label></td>
                <td colspan="3"><textarea class="validate[required] text-input" name="userComment.nazar" id="userComment_nazar" rows="5" cols="68" style="width:92%;"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<form class="mainFrame" id="mainFormReverse" method="post" action="/saveEstelamAction.action"
      onsubmit="$('.content').slideDown(0)">

    <div class="pageTitle">
        سيستم استعلام عمروسرمايه گذاري (استعلام بر مبناي اندوخته انتهاي مدت بيمه نامه)
    </div>
    <fieldset>
        <legend>مشخصات بیمه شده</legend>
        <div class="expandableTitleBar">
            <p class="heading">
                <span class="ui-icon ui-icon-plus" style="float:right;"></span>مشخصات بیمه شده
            </p>

            <div class="content">
                <%@include file="/jsp/estelamReverse/estelam_moshakasateBimeShode.jsp"%>
            </div>
        </div>
    </fieldset>
    <fieldset>
        <legend>مشخصات بیمه نامه</legend>
        <div class="expandableTitleBar">
            <p class="heading">
                <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                پوشش های اصلی</p>

            <div class="content">
                <%@include file="estelam_moshakasateBimeName.jsp"%>
            </div>
        </div>
        <div class="expandableTitleBar">
            <p class="heading">
                <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                پوشش های اضافی</p>

            <div class="content">
                <%@include file="estelam_posheshHayeEzafi.jsp"%>
            </div>
        </div>
        <div class="expandableTitleBar" id="takhfifeGrouhi">
            <p class="heading">
                <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                تخفیف فروش گروهی</p>

            <div class="content">
                <%@include file="/jsp/estelam/estelam_takhfifeForosheGroohi.jsp"%>
            </div>
        </div>
    </fieldset>



    <div style="height:25px;margin-top:5px;margin-bottom:15px;">
        <input type="button" onclick="mohasebeReverse();" value="محاسبه" style="margin-right: 20px;float:right;"/>
        <input type="button" onclick="resetFormReverse();" value="جدید" style="margin-right: 10px;float:right;"/>
        <input type="button" class="prt ui-state-disabled" style="margin-right:10px;float:right;" value="پرینت"  disabled/>
        <sec:authorize ifAllGranted="ROLE_USER">
            <%--<input type="submit" value="ایجاد پیشنهاد" style="margin-right: 10px;float:right;cursor:pointer"/>--%>
            <input type="button" onclick="window.location='/loginUser.action';" value="بازگشت" style="margin-right: 10px;float:right;cursor:pointer"/>
        </sec:authorize>
        <sec:authorize ifAllGranted="ROLE_SUPERVISOR">
            <input type="button" onclick="addComment();" value="نظر شما" style="margin-right: 10px;float:right;"/>
        </sec:authorize>
        <input type="button" style="float:right;margin-right: 10px;display: none;" value="استعلام مستمری"id="dokme_mostamari_from_2" onclick="submitForMostamari();" />
    </div>

    <div id="result"></div>

    <input type="hidden" id="zelzele" name="estelamReverse.pooshesh_fot_dar_asar_zelzele" value="no"/>
</form>
<form action="" id="form_for_mostamari_from_estelam_2" method="post">
    <input type="hidden" name="mostamari.andukhteEntehaModatBimename" id="andukhte_15_from_2" value="0"/>
    <input type="hidden" name="mostamari.senMostamariBegir" id="sen_plus_moddat_bimename_from_2" value="0"/>
    <input type="hidden" name="estelam.nam_bime_shode" id="estelam_nam_bime_shode" value="0"/>
    <input type="hidden" name="estelam.darsad_ezafe_nerkh_pezeshki" id="estelam_darsad_ezafe_nerkh_pezeshki" value="0"/>
    <input type="hidden" name="estelam.sarmaye_paye_fot" id="estelam_sarmaye_paye_fot" value="0"/>
    <input type="hidden" name="estelam.sarmaye_paye_fot_dar_asar_hadese" id="estelam_sarmaye_paye_fot_dar_asar_hadese" value="0"/>
    <input type="hidden" name="estelam.sarmaye_pooshesh_amraz_khas" id="estelam_sarmaye_pooshesh_amraz_khas" value="0"/>
    <input type="hidden" name="estelam.sarmaye_pooshesh_naghs_ozv" id="estelam_sarmaye_pooshesh_naghs_ozv" value="0"/>
    <input type="hidden" name="estelam.hagh_bime_pardakhti" id="estelam_hagh_bime_pardakhti" value="0"/>
    <input type="hidden" name="estelam.nahve_pardakht_hagh_bime" id="estelam_nahve_pardakht_hagh_bime" value="0"/>
    <input type="hidden" name="estelam.nerkh_afzayesh_salaneh_hagh_bime" id="estelam_nerkh_afzayesh_salaneh_hagh_bime" value="0"/>
    <input type="hidden" name="estelam.nerkh_afzayesh_salaneh_sarmaye_fot" id="estelam_nerkh_afzayesh_salaneh_sarmaye_fot" value="0"/>
</form>
<script t-ype="text/javascript">
    $("#dokme_mostamari_from_2").hide();
    function submitForMostamari(){
        fillInTheForm();
        $("#form_for_mostamari_from_estelam_2").attr("action","/beginMohaasebeMostamari.action");
        $("#form_for_mostamari_from_estelam_2").submit();
    }
    function fillInTheForm(){
        $("#estelam_nam_bime_shode").val($("#nam_bime_shode").val());
        $("#estelam_darsad_ezafe_nerkh_pezeshki").val($("#darsad_ezafe_nerkh_pezeshki").val());
        $("#estelam_sarmaye_paye_fot").val($("#sarmaye_paye_fot").val());
        $("#estelam_sarmaye_paye_fot_dar_asar_hadese").val($("#sarmaye_paye_fot_dar_asar_hadese").val());
        $("#estelam_sarmaye_pooshesh_amraz_khas").val($("#sarmaye_pooshesh_amraz_khas").val());
        $("#estelam_sarmaye_pooshesh_naghs_ozv").val($("#sarmaye_pooshesh_naghs_ozv").val());
        $("#estelam_hagh_bime_pardakhti").val($("#hagh_bime_pardakhti").val());
        $("#estelam_nahve_pardakht_hagh_bime").val($("#nahve_pardakht_hagh_bime").val());
        $("#estelam_nerkh_afzayesh_salaneh_hagh_bime").val($("#nerkh_afzayesh_salaneh_hagh_bime").val());
        $("#estelam_nerkh_afzayesh_salaneh_sarmaye_fot").val($("#nerkh_afzayesh_salaneh_sarmaye_fot").val());

    }
</script>
</body>
</html>