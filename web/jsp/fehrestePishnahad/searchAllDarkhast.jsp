<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@include file="/jsp/josteju/searchNamayandegi.jsp"%>--%>
<div id="allofem" class="listenTextFieldEnter" functionCall="searchAllDarkhastAjax()">
    <form action="/searchForAllDarkhasts.action" id="form_search_for_darkhast1" method="post">
        <input type="hidden" name="selectedTab" id="sel_sel_tab" value="tabs-8"/>
        <table class="inputList" cellspacing="5" width="90%">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="searchDarkhast.shomareBimename" id="search_shomareBime1" />
                    &nbsp;<label>شماره بیمه نامه</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="searchDarkhast.kodeMelliShenasayiBimeGozar" id="search_kodMelliBimeGozar1" />
                    &nbsp;<label>کد ملی بیمه گذار</label>
                </td>
                <%--<td>--%>
                    <%--<span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_namayandegiIdDarkhast','search_namayandegiNameDarkhast', '');" style="float:left;" title="جستجو"></span>--%>
                    <%--&nbsp;<label>نمایندگی</label>--%>
                    <%--<input type="hidden" name="namayande.id" id="search_namayandegiIdDarkhast"/>--%>
                    <%--<input type="text" name="namayande.name"  id="search_namayandegiNameDarkhast" readonly="true"/>--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<span class="noThing"></span>--%>
                    <%--<input type="text" name="karshenas" id="search_nameKarshenas" />--%>
                    <%--&nbsp;<label>نام کارشناس</label>--%>
                <%--</td>--%>
            </tr>
            <tr>
                <td>
                    <script type="text/javascript">

                        function tgrStt1()
                        {
                            var arg = document.getElementById("stt1").options[stt1.selectedIndex].id;
                            if (arg.contains('TGR'))
                                $('#type_Tgr1').attr("selected", true) ;
                            else if (arg.contains('VAM'))
                                    $('#type_Vam1').attr("selected", true) ;
                            else if (arg.contains('EBT'))
                            {
                                if (document.getElementById("search_darkhastType1").options[search_darkhastType1.selectedIndex].value != 'BAZKHARID')
                                    $('#type_Ebtal1').attr("selected", true);
                            }
                            else if (arg.contains('KSR'))
                            {
                                $('#type_Khesarat1').attr("selected", true);
                            }
                        }

                        function changeDarkhastAll()
                        {
                            var arg = document.getElementById("search_darkhastType1").options[search_darkhastType1.selectedIndex].value
                            $("#stt1 option[value='9040']").remove();
                            $("#stt1 option[value='9050']").remove();
                            $("#stt1 option[value='9070']").remove();
                            $("#stt1 option[value='9090']").remove();
                            $("#stt1 option[value='9110']").remove();
                            $("#stt1 option[value='9100']").remove();
                            $("#stt1 option[value='9120']").remove();
                            $("#stt1 option[value='9160']").remove();
                            $("#stt1 option[value='9170']").remove();
                            $("#stt1 option[value='9190']").remove();
                            $("#stt1 option[value='9200']").remove();

                            $("#stt1 option[value='10080']").remove();
                            $("#stt1 option[value='10090']").remove();
                            $("#stt1 option[value='10100']").remove();
                            $("#stt1 option[value='10110']").remove();
                            $("#stt1 option[value='10120']").remove();
                            $("#stt1 option[value='10160']").remove();

                            $("#stt1 option[value='1300']").remove();
                            $("#stt1 option[value='1100']").remove();

                            $("#stt1 option[value='600']").remove();
                            $("#stt1 option[value='610']").remove();
                            $("#stt1 option[value='620']").remove();
                            $("#stt1 option[value='644']").remove();
                            $("#stt1 option[value='645']").remove();
                            $("#stt1 option[value='646']").remove();
                            $("#stt1 option[value='647']").remove();
                            $("#stt1 option[value='648']").remove();
                            $("#stt1 option[value='652']").remove();
                            $("#stt1 option[value='653']").remove();
                            $("#stt1 option[value='654']").remove();
                            $("#stt1 option[value='655']").remove();
                            $("#stt1 option[value='657']").remove();

                            if (arg == 'ALL')
                            {
                                $('#stt1_td').show();
                                $('#stt_EHRAZ1').val('9160,1450,10070');
                                $('#stt_MOVAGHAT1').val('9010,1000,10000,640');
                                $('#stt_JADID1').val('9020,1010,10010,619');
                                $('#stt_MOSHAHEDE1').val('9030,1020,10020,642');
                                $('#stt_ESLAH1').val('9140,1100,10050,650');
                                $('#stt_NAGHS1').val('9130,1300,10040,649');
                                $('#stt_KARSHENASI1').val('9080,1200,10030,658');
                                $('#stt_ADAME_TATABOGH_EMZA1').val('9150,1400,10060');
                                $('#stt_NAHAEI1').val('9180,1500,10130,656');
                                $('#stt_ENSERAF1').val('9210,1600,10140,630');
                                $('#stt_MONTAFI1').val('9220,1700,10150,651');

                                $('#stt1').append('<option id="stt_TGR_TAEID_MASHROT1" value="9040">تاييد مشروط درخواست الحاقيه</option>');
                                $('#stt1').append('<option id="stt_TGR_GHABUL_TAGHIR_SHARAYET1" value="9050">قبول تغيير شرايط الحاقيه</option>');
                                $('#stt1').append('<option id="stt_TGR_SABT_NAZAR_RAEIS_KHADAMAT1" value="9070">ثبت نظر رئيس اداره خدمات پس از صدور</option>');
                                $('#stt1').append('<option id="stt_TGR_SODUR_MOAREFI_PEZESHK1" value="9090">صدور معرفي نامه پزشكي</option>');
                                $('#stt1').append('<option id="stt_TGR_MONTAZER_PEZESHK1" value="9110">منتظر بررسي پزشك</option>');
                                $('#stt1').append('<option id="stt_TGR_MONTAR_PASOKH_AZMAYESH1" value="9100">چاپ معرفي‌نامه و منتظر پاسخ آزمايش</option>');
                                $('#stt1').append('<option id="stt_TGR_SABT_PEZESHK1" value="9120">ثبت نظر پزشك</option>');
                                $('#stt1').append('<option id="stt_TGR_EHRAZ_HOVIAT1" value="9160">احراز هويت شده</option>');
                                $('#stt1').append('<option id="stt_TGR_ELAM_MALI_TGR_ELH1" value="9170">اعلام به مالي تغييرات الحاقيه</option>');
                                $('#stt1').append('<option id="stt_TGR_ERSAL_ELHAGHIE1" value="9190">ارسال الحاقيه</option>');
                                $('#stt1').append('<option id="stt_TGR_DARYAFT_ELH_KHATEME1" value="9200">دريافت الحاقيه و خاتمه</option>');

                                $('#stt1').append('<option id="stt_VAM_ESTELAM_VAM1" value="10080">استعلام شرایط وام</option>');
                                $('#stt1').append('<option id="stt_VAM_GHESTBANDI_VAM1" value="10090">وام قسط‌ بندي شده</option>');
                                $('#stt1').append('<option id="stt_VAM_ELAM_MALI_VAM1" value="10100">اعلام به مالي وام</option>');
                                $('#stt1').append('<option id="stt_VAM_SODUR_NAHAEI_VAM1" value="10110">صدور نهايي و واريز وام</option>');
                                $('#stt1').append('<option id="stt_VAM_ERSAL_DAFTARCHE_VAM1" value="10120">ارسال دفترچه وام</option>');
                                $('#stt1').append('<option id="stt_VAM_DARYAFT_KHATEME_VAM1" value="10160">دريافت دفترچه وام و خاتمه</option>');

                                $('#stt1').append('<option id="stt_EBT_MNTZR_TAKMIL_MDRK1" value="1300">منتظر تکمیل مدارک</option>');
                                $('#stt1').append('<option id="stt_EBT_TAKMIL_MDRK1" value="1100">تکمیل مدارک</option>');

                                $('#stt1').append('<option id="stt_KSR_TSHKL_PRVNDE1" value="600">تشکیل پرونده</option>                 ');
                                $('#stt1').append('<option id="stt_KSR_TSVIE_PRDKT_SHD1" value="610">تسویه پرداخت شده</option>          ');
                                $('#stt1').append('<option id="stt_KSR_TSVIE_PRDKT_NA_SHD1" value="620">تسویه غیرقابل پرداخت</option>   ');
                                $('#stt1').append('<option id="stt_KSR_AKHZ_MJVZ1" value="644">نياز به اخذ مجوز</option>                ');
                                $('#stt1').append('<option id="stt_KSR_SDR_MJVZ1" value="645">صدور مجوز</option>                        ');
                                $('#stt1').append('<option id="stt_KSR_ADAM_SDR_MJVZ1" value="646">عدم صدور مجوز</option>               ');
                                $('#stt1').append('<option id="stt_KSR_KHTME_PRVNDE1" value="647">خاتمه پرونده خسارت</option>           ');
                                $('#stt1').append('<option id="stt_KSR_KAR_KHES1" value="648">منتظر بررسی کارشناس خسارت</option>        ');
                                $('#stt1').append('<option id="stt_KSR_EL_JADD1" value="652">درخواست الحاقيه خسارت جديد</option>        ');
                                $('#stt1').append('<option id="stt_KSR_BRSI_MJDD1" value="653">بررسي مجدد پرونده خسارت</option>         ');
                                $('#stt1').append('<option id="stt_KSR_TAEID1" value="654">تاييد بررسي پرونده خسارت</option>            ');
                                $('#stt1').append('<option id="stt_KSR_MNTZR_TAEID1" value="655">منتظر تاييد نهايي پرونده خسارت</option>');
                                $('#stt1').append('<option id="stt_KSR_MSHDE_SHDE1" value="657">الحاقیه خسارت مشاهده شده</option>       ');

                            }
                            else if (arg == 'TAGHIRAT')
                            {
                                $('#stt1_td').show();
                                $('#stt_EHRAZ1').val('9160');
                                $('#stt_MOVAGHAT1').val('9010');
                                $('#stt_JADID1').val('9020');
                                $('#stt_MOSHAHEDE1').val('9030');
                                $('#stt_ESLAH1').val('9140');
                                $('#stt_NAGHS1').val('9130');
                                $('#stt_KARSHENASI1').val('9080');
                                $('#stt_ADAME_TATABOGH_EMZA1').val('9150');
                                $('#stt_NAHAEI1').val('9180');
                                $('#stt_ENSERAF1').val('9210');
                                $('#stt_MONTAFI1').val('9220');

                                $('#stt1').append('<option id="stt_TGR_TAEID_MASHROT1" value="9040">تاييد مشروط درخواست الحاقيه</option>');
                                $('#stt1').append('<option id="stt_TGR_GHABUL_TAGHIR_SHARAYET1" value="9050">قبول تغيير شرايط الحاقيه</option>');
                                $('#stt1').append('<option id="stt_TGR_SABT_NAZAR_RAEIS_KHADAMAT1" value="9070">ثبت نظر رئيس اداره خدمات پس از صدور</option>');
                                $('#stt1').append('<option id="stt_TGR_SODUR_MOAREFI_PEZESHK1" value="9090">صدور معرفي نامه پزشكي</option>');
                                $('#stt1').append('<option id="stt_TGR_MONTAZER_PEZESHK1" value="9110">منتظر بررسي پزشك</option>');
                                $('#stt1').append('<option id="stt_TGR_MONTAR_PASOKH_AZMAYESH1" value="9100">چاپ معرفي‌نامه و منتظر پاسخ آزمايش</option>');
                                $('#stt1').append('<option id="stt_TGR_SABT_PEZESHK1" value="9120">ثبت نظر پزشك</option>');
                                $('#stt1').append('<option id="stt_TGR_EHRAZ_HOVIAT1" value="9160">احراز هويت شده</option>');
                                $('#stt1').append('<option id="stt_TGR_ELAM_MALI_TGR_ELH1" value="9170">اعلام به مالي تغييرات الحاقيه</option>');
                                $('#stt1').append('<option id="stt_TGR_ERSAL_ELHAGHIE1" value="9190">ارسال الحاقيه</option>');
                                $('#stt1').append('<option id="stt_TGR_DARYAFT_ELH_KHATEME1" value="9200">دريافت الحاقيه و خاتمه</option>');
                            }
                            else if (arg == 'EBTAL' || arg == 'BAZKHARID')
                            {
                                $('#stt1_td').show();
                                $('#stt_EHRAZ1').val('1450');
                                $('#stt_MOVAGHAT1').val('1000');
                                $('#stt_JADID1').val('1010');
                                $('#stt_MOSHAHEDE1').val('1020');
                                $('#stt_ESLAH1').val('1100');
                                $('#stt_NAGHS1').val('1300');
                                $('#stt_KARSHENASI1').val('1200');
                                $('#stt_ADAME_TATABOGH_EMZA1').val('1400');
                                $('#stt_NAHAEI1').val('1500');
                                $('#stt_ENSERAF1').val('1600');
                                $('#stt_MONTAFI1').val('1700');
                                $('#stt1').append('<option id="stt_EBT_MNTZR_TAKMIL_MDRK1" value="1300">منتظر تکمیل مدارک</option>');
                                $('#stt1').append('<option id="stt_EBT_TAKMIL_MDRK1" value="1100">تکمیل مدارک</option>');
                            }
                            else if (arg == 'VAM')
                            {
                                $('#stt_td1').show();
                                $('#stt_EHRAZ1').val('10070');
                                $('#stt_MOVAGHAT1').val('10000');
                                $('#stt_JADID1').val('10010');
                                $('#stt_MOSHAHEDE1').val('10020');
                                $('#stt_ESLAH1').val('10050');
                                $('#stt_NAGHS1').val('10040');
                                $('#stt_KARSHENASI1').val('10030');
                                $('#stt_ADAME_TATABOGH_EMZA1').val('10060');
                                $('#stt_NAHAEI1').val('10130');
                                $('#stt_ENSERAF1').val('10140');
                                $('#stt_MONTAFI1').val('10150');
                                $('#stt1').append('<option id="stt_ESTELAM_VAM1" value="10080">استعلام شرایط وام</option>');
                                $('#stt1').append('<option id="stt_GHESTBANDI_VAM1" value="10090">وام قسط‌ بندي شده</option>');
                                $('#stt1').append('<option id="stt_ELAM_MALI_VAM1" value="10100">اعلام به مالي وام</option>');
                                $('#stt1').append('<option id="stt_SODUR_NAHAEI_VAM1" value="10110">صدور نهايي و واريز وام</option>');
                                $('#stt1').append('<option id="stt_ERSAL_DAFTARCHE_VAM1" value="10120">ارسال دفترچه وام</option>');
                                $('#stt1').append('<option id="stt_DARYAFT_KHATEME_VAM1" value="10160">دريافت دفترچه وام و خاتمه</option>');
                            }
                            else if (arg == 'BARDASHT')
                            {
                                $('#stt_td1').show();
                                $('#stt_EHRAZ1').val('11070');
                                $('#stt_MOVAGHAT1').val('11000');
                                $('#stt_JADID1').val('11010');
                                $('#stt_MOSHAHEDE1').val('11020');
                                $('#stt_ESLAH1').val('11050');
                                $('#stt_NAGHS1').val('11040');
                                $('#stt_KARSHENASI1').val('11030');
                                $('#stt_ADAME_TATABOGH_EMZA1').val('11060');
                                $('#stt_NAHAEI1').val('11090');
                                $('#stt_ENSERAF1').val('11100');
                                $('#stt_MONTAFI1').val('11110');
                            }
                            else if(arg == 'KHESARAT')
                            {
                                $("#stt_EHRAZ1").remove();
                                $("#stt_ADAME_TATABOGH_EMZA1").remove();
                                $('#stt_MOVAGHAT1').val('640');
                                $('#stt_JADID1').val('619');
                                $('#stt_MOSHAHEDE1').val('642');
                                $('#stt_ESLAH1').val('650');
                                $('#stt_NAGHS1').val('649');
                                $('#stt_KARSHENASI1').val('658');
                                $('#stt_NAHAEI1').val('656');
                                $('#stt_ENSERAF1').val('630');
                                $('#stt_MONTAFI1').val('651');

                                $('#stt1').append('<option id="stt_KSR_TSHKL_PRVNDE1" value="600">تشکیل پرونده</option>                 ');
                                $('#stt1').append('<option id="stt_KSR_TSVIE_PRDKT_SHD1" value="610">تسویه پرداخت شده</option>          ');
                                $('#stt1').append('<option id="stt_KSR_TSVIE_PRDKT_NA_SHD1" value="620">تسویه غیرقابل پرداخت</option>   ');
                                $('#stt1').append('<option id="stt_KSR_AKHZ_MJVZ1" value="644">نياز به اخذ مجوز</option>                ');
                                $('#stt1').append('<option id="stt_KSR_SDR_MJVZ1" value="645">صدور مجوز</option>                        ');
                                $('#stt1').append('<option id="stt_KSR_ADAM_SDR_MJVZ1" value="646">عدم صدور مجوز</option>               ');
                                $('#stt1').append('<option id="stt_KSR_KHTME_PRVNDE1" value="647">خاتمه پرونده خسارت</option>           ');
                                $('#stt1').append('<option id="stt_KSR_KAR_KHES1" value="648">منتظر بررسی کارشناس خسارت</option>        ');
                                $('#stt1').append('<option id="stt_KSR_EL_JADD1" value="652">درخواست الحاقيه خسارت جديد</option>        ');
                                $('#stt1').append('<option id="stt_KSR_BRSI_MJDD1" value="653">بررسي مجدد پرونده خسارت</option>         ');
                                $('#stt1').append('<option id="stt_KSR_TAEID1" value="654">تاييد بررسي پرونده خسارت</option>            ');
                                $('#stt1').append('<option id="stt_KSR_MNTZR_TAEID1" value="655">منتظر تاييد نهايي پرونده خسارت</option>');
                                $('#stt1').append('<option id="stt_KSR_MSHDE_SHDE1" value="657">الحاقیه خسارت مشاهده شده</option>       ');
                            }
                            else
                            {
                                $('#stt1_EHRAZ').val('-');
                                $('#stt1_MOVAGHAT').val('-');
                                $('#stt1_JADID').val('-');
                                $('#stt1_MOSHAHEDE').val('-');
                                $('#stt1_ESLAH').val('-');
                                $('#stt1_NAGHS').val('-');
                                $('#stt1_KARSHENASI').val('-');
                                $('#stt1_ADAME_TATABOGH_EMZA').val('-');
                                $('#stt1_NAHAEI').val('-');
                                $('#stt1_ENSERAF').val('-');
                                $('#stt1_MONTAFI').val('-');
                                $('#stt1_td').hide();
                            }
                        }
                    </script>
                    <span class="noThing"></span>
                    <select name="searchDarkhast.darkhastType" id="search_darkhastType1" onchange="changeDarkhastAll()">
                        <option value="ALL" >-</option>
                        <option value="TAGHIRAT" id="type_Tgr1" >تغییرات</option>
                        <option value="EBTAL" id="type_Ebtal1" >ابطال</option>
                        <option value="BAZKHARID" >بازخرید</option>
                        <option value="TAGHIR_CODE" >تغییر کد</option>
                        <option value="TOZIH" >توضیح</option>
                        <option value="VAM" id="type_Vam1" >وام</option>
                        <option value="BARDASHT" id="type_Bardasht1" >برداشت از اندوخته</option>
                        <option value="KHESARAT" id="type_Khesarat1" >خسارت</option>
                    </select>
                    &nbsp;<label>نوع درخواست</label>
                </td>
                <td id="stt1_td">
                    <span class="noThing"></span>
                    <select name="searchDarkhast.darkhastState" id="stt1" onchange="tgrStt1()">
                        <option id="stt_ALL1">-</option>
                        <option value="9160,1450,10070,11070" id="stt_EHRAZ1">احراز هویت شده</option>
                        <option value="9010,1000,10000,11000,640" id="stt_MOVAGHAT1">موقت</option>
                        <option value="9020,1010,10010,11010,619" id="stt_JADID1">جدید</option>
                        <option value="9030,1020,10020,11020,642" id="stt_MOSHAHEDE1">مشاهده شده</option>
                        <option value="9140,1100,10050,11050,650" id="stt_ESLAH1">اصلاح درخواست</option>
                        <option value="9130,1300,10040,11040,649" id="stt_NAGHS1">نیازمند اصلاح درخواست</option>
                        <option value="9080,1200,10030,11030,658" id="stt_KARSHENASI1">کارشناسی</option>
                        <option value="9150,1400,10060,11060" id="stt_ADAME_TATABOGH_EMZA1">عدم تطابق امضاء</option>
                        <option value="9180,1500,10130,11090,656" id="stt_NAHAEI1">درخواست نهایی</option>
                        <option value="9210,1600,10140,11100,630" id="stt_ENSERAF1">انصراف</option>
                        <option value="9220,1700,10150,11110,651" id="stt_MONTAFI1">منتفی</option>

                        <option id="stt_TGR_TAEID_MASHROT1" value="9040">تاييد مشروط درخواست الحاقيه</option>
                        <option id="stt_TGR_GHABUL_TAGHIR_SHARAYET1" value="9050">قبول تغيير شرايط                            الحاقيه</option>
                        <option id="stt_TGR_SABT_NAZAR_RAEIS_KHADAMAT1" value="9070">ثبت نظر رئيس اداره                        </option>
                        <option id="stt_TGR_SODUR_MOAREFI_PEZESHK1" value="9090">صدور معرفي نامه پزشكي</option>
                        <option id="stt_TGR_MONTAZER_PEZESHK1" value="9110">منتظر بررسي پزشك</option>
                        <option id="stt_TGR_MONTAR_PASOKH_AZMAYESH1" value="9100">چاپ معرفي‌نامه و                            منتظر</option>
                        <option id="stt_TGR_SABT_PEZESHK1" value="9120">ثبت نظر پزشك</option>
                        <option id="stt_TGR_EHRAZ_HOVIAT1" value="9160">احراز هويت شده</option>
                        <option id="stt_TGR_ELAM_MALI_TGR_ELH1" value="9170">اعلام به مالي تغييرات</option>
                        <option id="stt_TGR_ERSAL_ELHAGHIE1" value="9190">ارسال الحاقيه</option>
                        <option id="stt_TGR_DARYAFT_ELH_KHATEME1" value="9200">دريافت الحاقيه و خاتمه</option>

                        <option id="stt_ESTELAM_VAM1" value="10080">استعلام شرایط وام</option>
                        <option id="stt_GHESTBANDI_VAM1" value="10090">وام قسط‌ بندي شده</option>
                        <option id="stt_ELAM_MALI_VAM1" value="10100">اعلام به مالي وام</option>
                        <option id="stt_SODUR_NAHAEI_VAM1" value="10110">صدور نهايي و واريز                            وام</option>
                        <option id="stt_ERSAL_DAFTARCHE_VAM1" value="10120">ارسال دفترچه وام</option>
                        <option id="stt_DARYAFT_KHATEME_VAM1" value="10160">دریافت دفترچه وام و خاتمه</option>

                        <option id="stt_EBT_MNTZR_TAKMIL_MDRK1" value="1300">منتظر تکمیل مدارک</option>
                        <option id="stt_EBT_TAKMIL_MDRK1" value="1100">تکمیل مدارک</option>

                        <option id="stt_KSR_TSHKL_PRVNDE1" value="600">تشکیل پرونده</option>
                        <option id="stt_KSR_TSVIE_PRDKT_SHD1" value="610">تسویه پرداخت شده</option>
                        <option id="stt_KSR_TSVIE_PRDKT_NA_SHD1" value="620">تسویه غیرقابل پرداخت</option>
                        <option id="stt_KSR_AKHZ_MJVZ1" value="644">نياز به اخذ مجوز</option>
                        <option id="stt_KSR_SDR_MJVZ1" value="645">صدور مجوز</option>
                        <option id="stt_KSR_ADAM_SDR_MJVZ1" value="646">عدم صدور مجوز</option>
                        <option id="stt_KSR_KHTME_PRVNDE1" value="647">خاتمه پرونده خسارت</option>
                        <option id="stt_KSR_KAR_KHES1" value="648">منتظر بررسی کارشناس خسارت</option>
                        <option id="stt_KSR_EL_JADD1" value="652">درخواست الحاقيه خسارت جديد</option>
                        <option id="stt_KSR_BRSI_MJDD1" value="653">بررسي مجدد پرونده خسارت</option>
                        <option id="stt_KSR_TAEID1" value="654">تاييد بررسي پرونده خسارت</option>
                        <option id="stt_KSR_MNTZR_TAEID1" value="655">منتظر تاييد نهايي پرونده خسارت</option>
                        <option id="stt_KSR_MSHDE_SHDE1" value="657">الحاقیه خسارت مشاهده شده</option>
                    </select>
                    &nbsp;<label>وضعیت</label>
                </td>
            </tr>
            <%--<tr>--%>
               <%--<td>--%>
                    <%--&lt;%&ndash;kode darkhast&ndash;%&gt;--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>--%>
                    <%--<span class="noThing"></span>--%>
                    <%--<input type="text" name="pishnehad.bimeGozar.shakhs.name" id="search_nameBimeGozarDarkhast" />--%>
                    <%--&nbsp;<label>نام بیمه گذار</label>--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<span class="noThing"></span>--%>
                    <%--<input type="text" name="pishnehad.bimeGozar.shakhs.nameKhanevadegi" id="search_familyBimeGozarDarkhast" />--%>
                    <%--&nbsp;<label>نام خانوادگی بیمه گذار</label>--%>
                <%--</td>--%>

            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>--%>
                    <%--<span class="noThing"></span>--%>
                    <%--<input type="text" name="pishnehad.bimeShode.shakhs.name" id="search_nameBimeShodeDarkhast"/>--%>
                    <%--&nbsp;<label>نام بیمه شده</label>--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<span class="noThing"></span>--%>
                    <%--<input type="text" name="pishnehad.bimeShode.shakhs.nameKhanevadegi" id="search_familyBimeShodeDarkhast"  />--%>
                    <%--&nbsp;<label>نام خانوادگی بیمه شده</label>--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<span class="noThing"></span>--%>
                    <%--<input type="text" name="pishnehad.bimeShode.shakhs.kodeMelliShenasayi" id="search_kodMelliBimeShodeDarkhast" />--%>
                    <%--&nbsp;<label>کد ملی بیمه شده</label>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <tr>
                <td></td>
                <td >
                    <script type="text/javascript">
                        function all_darkhast_clearSeachFrom_b() {
//                            $('#search_kodMelliBimeShodeDarkhast').val('');
//                            $('#search_familyBimeShodeDarkhast').val('');
//                            $('#search_nameBimeShodeDarkhast').val('');
                            $('#search_kodMelliBimeGozar1').val('');
//                            $('#search_familyBimeGozarDarkhast').val('');
//                            $('#search_nameBimeGozarDarkhast').val('');
//                            $('#search_taTarikhDarkhast').val('');
//                            $('#search_azTarikhDarkhast').val('');
//                            $('#search_nameKarshenas').val('');
//                            $('#search_namayandegiNameDarkhast').val('');
                            $('#search_shomareBime1').val('');
                        }
                    </script>
                    <input type="button" style="margin-left:25px" value="پاک کردن فرم" onclick="all_darkhast_clearSeachFrom_b()">
                    <%--<input type="submit" style="margin-left:25px" value="جستجو">--%>
                    <input type="button" onclick="searchAllDarkhastAjax()" style="margin-left:25px" value="جستجو">
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function searchAllDarkhastAjax() {
        $.ajax({
            type: "POST",
            url: 'resultTab8.action',
            data: $('#form_search_for_darkhast1').serialize(),
            success: function (response) {
                // we have the response
                $('#displayTab8').html(response);
            }
        });
    }
</script>