<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@include file="/jsp/josteju/searchNamayandegi.jsp"%>--%>
<div id="allofem" class="listenTextFieldEnter" functionCall="searchDarkhastDarJaryanAjax()">
    <form action="/searchForDarkhasts.action" id="form_search_for_darkhast" method="post">
        <input type="hidden" name="selectedTab" id="sel_sel_tab" value="tabs-4"/>
        <table class="inputList" cellspacing="5" width="90%">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="bimename.shomare" id="search_shomareBime" />
                    &nbsp;<label>شماره بیمه نامه</label>
                </td>
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('search_namayandegiIdDarkhast','search_namayandegiNameDarkhast', '');" style="float:left;" title="جستجو"></span>
                    &nbsp;<label>نمایندگی</label>
                    <input type="hidden" name="namayande.id" id="search_namayandegiIdDarkhast"/>
                    <input type="text" name="namayande.name"  id="search_namayandegiNameDarkhast" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="karshenas" id="search_nameKarshenas"/>
                    &nbsp;<label>نام کارشناس</label>
                </td>
                <td>

                    <input type="text" name="azTarikheDarkhast" id="search_azTarikhDarkhast" class="validate[custom[date] datePkr"/>
                    &nbsp;<label>تاریخ درخواست (از)</label>
                </td>
                <td>

                    <input type="text" name="taTarikheDarkhast" id="search_taTarikhDarkhast" class="validate[custom[date] datePkr"/>
                    &nbsp;<label>تاریخ درخواست (تا)</label>
                </td>
            </tr>
            <tr>
               <td>
                    <%--kode darkhast--%>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.bimeGozar.shakhs.name" id="search_nameBimeGozarDarkhast" />
                    &nbsp;<label>نام بیمه گذار</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.bimeGozar.shakhs.nameKhanevadegi" id="search_familyBimeGozarDarkhast" />
                    &nbsp;<label>نام خانوادگی بیمه گذار</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.bimeGozar.shakhs.kodeMelliShenasayi" id="search_kodMelliBimeGozar" />
                    &nbsp;<label>کد ملی بیمه گذار</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.bimeShode.shakhs.name" id="search_nameBimeShodeDarkhast"/>
                    &nbsp;<label>نام بیمه شده</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.bimeShode.shakhs.nameKhanevadegi" id="search_familyBimeShodeDarkhast"  />
                    &nbsp;<label>نام خانوادگی بیمه شده</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="pishnehad.bimeShode.shakhs.kodeMelliShenasayi" id="search_kodMelliBimeShodeDarkhast" />
                    &nbsp;<label>کد ملی بیمه شده</label>
                </td>
            </tr>
            <tr>
                <td>
                    <script type="text/javascript">
                        function tgrStt()
                        {
                            var arg=document.getElementById("stt").options[stt.selectedIndex].id;
                            if(arg.contains('TGR'))
                                $('#type_Tgr').attr("selected", true) ;
                            else if(arg.contains('VAM'))
                                $('#type_Vam').attr("selected", true) ;
                            else if(arg.contains('EBT'))
                            {
                                if (document.getElementById("search_darkhastType").options[search_darkhastType.selectedIndex].value!='BAZKHARID')
                                    $('#type_Ebtal').attr("selected", true) ;
                            }
                            else if (arg.contains('KSR'))
                            {
                                $('#type_Khesarat').attr("selected", true);
                            }

                        }

                        function changeDarkhast()
                        {
                            var arg= document.getElementById("search_darkhastType").options[search_darkhastType.selectedIndex].value
                            $("#stt option[value='9040']").remove();
                            $("#stt option[value='9050']").remove();
                            $("#stt option[value='9070']").remove();
                            $("#stt option[value='9090']").remove();
                            $("#stt option[value='9110']").remove();
                            $("#stt option[value='9100']").remove();
                            $("#stt option[value='9120']").remove();
                            $("#stt option[value='9160']").remove();
                            $("#stt option[value='9170']").remove();
                            $("#stt option[value='9190']").remove();
                            $("#stt option[value='9200']").remove();

                            $("#stt option[value='10080']").remove();
                            $("#stt option[value='10090']").remove();
                            $("#stt option[value='10100']").remove();
                            $("#stt option[value='10110']").remove();
                            $("#stt option[value='10120']").remove();
                            $("#stt option[value='10160']").remove();

                            $("#stt option[value='1300']").remove();
                            $("#stt option[value='1100']").remove();

                            $("#stt option[value='600']").remove();
                            $("#stt option[value='610']").remove();
                            $("#stt option[value='620']").remove();
                            $("#stt option[value='644']").remove();
                            $("#stt option[value='645']").remove();
                            $("#stt option[value='646']").remove();
                            $("#stt option[value='647']").remove();
                            $("#stt option[value='648']").remove();
                            $("#stt option[value='652']").remove();
                            $("#stt option[value='653']").remove();
                            $("#stt option[value='654']").remove();
                            $("#stt option[value='655']").remove();
                            $("#stt option[value='657']").remove();



                            if (arg == 'ALL')
                            {
                                $('#stt_td').show();
                                $('#stt_EHRAZ').val('9160,1450,10070');
                                $('#stt_MOVAGHAT').val('9010,1000,10000');
                                $('#stt_JADID').val('9020,1010,10010');
                                $('#stt_MOSHAHEDE').val('9030,1020,10020');
                                $('#stt_ESLAH').val('9140,1100,10050');
                                $('#stt_NAGHS').val('9130,1300,10040');
                                $('#stt_KARSHENASI').val('9080,1200,10030');
                                $('#stt_ADAME_TATABOGH_EMZA').val('9150,1400,10060');
                                $('#stt_NAHAEI').val('9180,1500,10130');
                                $('#stt_ENSERAF').val('9210,1600,10140');
                                $('#stt_MONTAFI').val('9220,1700,10150');

                                $('#stt').append('<option id="stt_ESTELAM_VAM" value="10080">استعلام شرایط وام</option>');
                                $('#stt').append('<option id="stt_GHESTBANDI_VAM" value="10090">وام قسط‌ بندي شده</option>');
                                $('#stt').append('<option id="stt_ELAM_MALI_VAM" value="10100">اعلام به مالي وام</option>');
                                $('#stt').append('<option id="stt_SODUR_NAHAEI_VAM" value="10110">صدور نهايي و واريز وام</option>');
                                $('#stt').append('<option id="stt_ERSAL_DAFTARCHE_VAM" value="10120">ارسال دفترچه وام</option>');
                                $('#stt').append('<option id="stt_DARYAFT_KHATEME_VAM" value="10160">دريافت دفترچه وام و خاتمه</option>');

                                $('#stt').append('<option id="stt_EBT_MNTZR_TAKMIL_MDRK" value="1300">منتظر تکمیل مدارک</option>');
                                $('#stt').append('<option id="stt_EBT_TAKMIL_MDRK" value="1100">تکمیل مدارک</option>');

                                $('#stt').append('<option  id="stt_TGR_ERSAL_ELHAGHIE" value="9190">ارسال الحاقيه</option>');
                                $('#stt').append('<option  id="stt_TGR_DARYAFT_ELH_KHATEME" value="9200">دريافت الحاقيه و خاتمه</option>');
                                $('#stt').append('<option  id="stt_TGR_TAEID_MASHROT" value="9040">تاييد مشروط درخواست الحاقيه</option>');
                                $('#stt').append('<option  id="stt_TGR_GHABUL_TAGHIR_SHARAYET" value="9050">قبول تغيير شرايط الحاقيه</option>');
                                $('#stt').append('<option  id="stt_TGR_SABT_NAZAR_RAEIS_KHADAMAT" value="9070">ثبت نظر رئيس اداره خدمات پس از صدور</option>');
                                $('#stt').append('<option  id="stt_TGR_SODUR_MOAREFI_PEZESHK" value="9090">صدور معرفي نامه پزشكي</option>');
                                $('#stt').append('<option  id="stt_TGR_MONTAZER_PEZESHK" value="9110">منتظر بررسي پزشك</option>');
                                $('#stt').append('<option  id="stt_TGR_MONTAR_PASOKH_AZMAYESH" value="9100">چاپ معرفي‌نامه و منتظر پاسخ آزمايش</option>');
                                $('#stt').append('<option  id="stt_TGR_SABT_PEZESHK" value="9120">ثبت نظر پزشك</option>');
                                $('#stt').append('<option  id="stt_TGR_EHRAZ_HOVIAT" value="9160">احراز هويت شده</option>');
                                $('#stt').append('<option  id="stt_TGR_ELAM_MALI_TGR_ELH" value="9170">اعلام به مالي تغييرات الحاقيه</option>');

                                $('#stt').append('<option id="stt_KSR_TSHKL_PRVNDE" value="600">تشکیل پرونده</option>                 ');
                                $('#stt').append('<option id="stt_KSR_TSVIE_PRDKT_SHD" value="610">تسویه پرداخت شده</option>          ');
                                $('#stt').append('<option id="stt_KSR_TSVIE_PRDKT_NA_SHD" value="620">تسویه غیرقابل پرداخت</option>   ');
                                $('#stt').append('<option id="stt_KSR_AKHZ_MJVZ" value="644">نياز به اخذ مجوز</option>                ');
                                $('#stt').append('<option id="stt_KSR_SDR_MJVZ" value="645">صدور مجوز</option>                        ');
                                $('#stt').append('<option id="stt_KSR_ADAM_SDR_MJVZ" value="646">عدم صدور مجوز</option>               ');
                                $('#stt').append('<option id="stt_KSR_KHTME_PRVNDE" value="647">خاتمه پرونده خسارت</option>           ');
                                $('#stt').append('<option id="stt_KSR_KAR_KHES" value="648">منتظر بررسی کارشناس خسارت</option>        ');
                                $('#stt').append('<option id="stt_KSR_EL_JADD" value="652">درخواست الحاقيه خسارت جديد</option>        ');
                                $('#stt').append('<option id="stt_KSR_BRSI_MJDD" value="653">بررسي مجدد پرونده خسارت</option>         ');
                                $('#stt').append('<option id="stt_KSR_TAEID" value="654">تاييد بررسي پرونده خسارت</option>            ');
                                $('#stt').append('<option id="stt_KSR_MNTZR_TAEID" value="655">منتظر تاييد نهايي پرونده خسارت</option>');
                                $('#stt').append('<option id="stt_KSR_MSHDE_SHDE" value="657">الحاقیه خسارت مشاهده شده</option>       ');

                            }
                            else if (arg == 'TAGHIRAT')
                            {
                                $('#stt_td').show();
                                $('#stt_EHRAZ').val('9160');
                                $('#stt_MOVAGHAT').val('9010');
                                $('#stt_JADID').val('9020');
                                $('#stt_MOSHAHEDE').val('9030');
                                $('#stt_ESLAH').val('9140');
                                $('#stt_NAGHS').val('9130');
                                $('#stt_KARSHENASI').val('9080');
                                $('#stt_ADAME_TATABOGH_EMZA').val('9150');
                                $('#stt_NAHAEI').val('9180');
                                $('#stt_ENSERAF').val('9210');
                                $('#stt_MONTAFI').val('9220');
                                $('#stt').append('<option id="stt_TGR_TAEID_MASHROT" value="9040">تاييد مشروط درخواست الحاقيه</option>');
                                $('#stt').append('<option id="stt_TGR_GHABUL_TAGHIR_SHARAYET" value="9050">قبول تغيير شرايط الحاقيه</option>');
                                $('#stt').append('<option id="stt_TGR_SABT_NAZAR_RAEIS_KHADAMAT" value="9070">ثبت نظر رئيس اداره خدمات پس از صدور</option>');
                                $('#stt').append('<option id="stt_TGR_SODUR_MOAREFI_PEZESHK" value="9090">صدور معرفي نامه پزشكي</option>');
                                $('#stt').append('<option id="stt_TGR_MONTAZER_PEZESHK" value="9110">منتظر بررسي پزشك</option>');
                                $('#stt').append('<option id="stt_TGR_MONTAR_PASOKH_AZMAYESH" value="9100">چاپ معرفي‌نامه و منتظر پاسخ آزمايش</option>');
                                $('#stt').append('<option id="stt_TGR_SABT_PEZESHK" value="9120">ثبت نظر پزشك</option>');
                                $('#stt').append('<option id="stt_TGR_EHRAZ_HOVIAT" value="9160">احراز هويت شده</option>');
                                $('#stt').append('<option id="stt_TGR_ELAM_MALI_TGR_ELH" value="9170">اعلام به مالي تغييرات الحاقيه</option>');
                                $('#stt').append('<option id="stt_TGR_ERSAL_ELHAGHIE" value="9190">ارسال الحاقيه</option>');
                                $('#stt').append('<option id="stt_TGR_DARYAFT_ELH_KHATEME" value="9200">دريافت الحاقيه و خاتمه</option>');
                            }
                            else if (arg == 'EBTAL' || arg == 'BAZKHARID')
                            {
                                $('#stt_td').show();
                                $('#stt_EHRAZ').val('1450');
                                $('#stt_MOVAGHAT').val('1000');
                                $('#stt_JADID').val('1010');
                                $('#stt_MOSHAHEDE').val('1020');
                                $('#stt_ESLAH').val('1100');
                                $('#stt_NAGHS').val('1300');
                                $('#stt_KARSHENASI').val('1200');
                                $('#stt_ADAME_TATABOGH_EMZA').val('1400');
                                $('#stt_NAHAEI').val('1500');
                                $('#stt_ENSERAF').val('1600');
                                $('#stt_MONTAFI').val('1700');
                                $('#stt').append('<option id="stt_EBT_MNTZR_TAKMIL_MDRK" value="1300">منتظر تکمیل مدارک</option>');
                                $('#stt').append('<option id="stt_EBT_TAKMIL_MDRK" value="1100">تکمیل مدارک</option>');
                            }
                            else if (arg == 'VAM')
                            {
                                $('#stt_td').show();
                                $('#stt_EHRAZ').val('10070');
                                $('#stt_MOVAGHAT').val('10000');
                                $('#stt_JADID').val('10010');
                                $('#stt_MOSHAHEDE').val('10020');
                                $('#stt_ESLAH').val('10050');
                                $('#stt_NAGHS').val('10040');
                                $('#stt_KARSHENASI').val('10030');
                                $('#stt_ADAME_TATABOGH_EMZA').val('10060');
                                $('#stt_NAHAEI').val('10130');
                                $('#stt_ENSERAF').val('10140');
                                $('#stt_MONTAFI').val('10150');
                                $('#stt').append('<option id="stt_ESTELAM_VAM" value="10080">استعلام شرایط وام</option>');
                                $('#stt').append('<option id="stt_GHESTBANDI_VAM" value="10090">وام قسط‌ بندي شده</option>');
                                $('#stt').append('<option id="stt_ELAM_MALI_VAM" value="10100">اعلام به مالي وام</option>');
                                $('#stt').append('<option id="stt_SODUR_NAHAEI_VAM" value="10110">صدور نهايي و واريز وام</option>');
                                $('#stt').append('<option id="stt_ERSAL_DAFTARCHE_VAM" value="10120">ارسال دفترچه وام</option>');
                                $('#stt').append('<option id="stt_DARYAFT_KHATEME_VAM" value="10160">دريافت دفترچه وام و خاتمه</option>');
                            }
                            else if (arg == 'BARDASHT')
                            {
                                $('#stt_td').show();
                                $('#stt_EHRAZ').val('11070');
                                $('#stt_MOVAGHAT').val('11000');
                                $('#stt_JADID').val('11010');
                                $('#stt_MOSHAHEDE').val('11020');
                                $('#stt_ESLAH').val('11050');
                                $('#stt_NAGHS').val('11040');
                                $('#stt_KARSHENASI').val('11030');
                                $('#stt_ADAME_TATABOGH_EMZA').val('11060');
                                $('#stt_NAHAEI').val('11090');
                                $('#stt_ENSERAF').val('11100');
                                $('#stt_MONTAFI').val('11110');
                            }
                            else if (arg == 'KHESARAT')
                            {
                                $("#stt_EHRAZ").remove();
                                $("#stt_ADAME_TATABOGH_EMZA").remove();
                                $('#stt_MOVAGHAT').val('640');
                                $('#stt_JADID').val('619');
                                $('#stt_MOSHAHEDE').val('642');
                                $('#stt_ESLAH').val('650');
                                $('#stt_NAGHS').val('649');
                                $('#stt_KARSHENASI').val('658');
                                $('#stt_NAHAEI').val('656');
                                $('#stt_ENSERAF').val('630');
                                $('#stt_MONTAFI').val('651');

                                $('#stt').append('<option id="stt_KSR_TSHKL_PRVNDE" value="600">تشکیل پرونده</option>                 ');
                                $('#stt').append('<option id="stt_KSR_TSVIE_PRDKT_SHD" value="610">تسویه پرداخت شده</option>          ');
                                $('#stt').append('<option id="stt_KSR_TSVIE_PRDKT_NA_SHD" value="620">تسویه غیرقابل پرداخت</option>   ');
                                $('#stt').append('<option id="stt_KSR_AKHZ_MJVZ" value="644">نياز به اخذ مجوز</option>                ');
                                $('#stt').append('<option id="stt_KSR_SDR_MJVZ" value="645">صدور مجوز</option>                        ');
                                $('#stt').append('<option id="stt_KSR_ADAM_SDR_MJVZ" value="646">عدم صدور مجوز</option>               ');
                                $('#stt').append('<option id="stt_KSR_KHTME_PRVNDE" value="647">خاتمه پرونده خسارت</option>           ');
                                $('#stt').append('<option id="stt_KSR_KAR_KHES" value="648">منتظر بررسی کارشناس خسارت</option>        ');
                                $('#stt').append('<option id="stt_KSR_EL_JADD" value="652">درخواست الحاقيه خسارت جديد</option>        ');
                                $('#stt').append('<option id="stt_KSR_BRSI_MJDD" value="653">بررسي مجدد پرونده خسارت</option>         ');
                                $('#stt').append('<option id="stt_KSR_TAEID" value="654">تاييد بررسي پرونده خسارت</option>            ');
                                $('#stt').append('<option id="stt_KSR_MNTZR_TAEID" value="655">منتظر تاييد نهايي پرونده خسارت</option>');
                                $('#stt').append('<option id="stt_KSR_MSHDE_SHDE" value="657">الحاقیه خسارت مشاهده شده</option>       ');
                            }
                            else
                            {
                                $('#stt_EHRAZ').val('-');
                                $('#stt_MOVAGHAT').val('-');
                                $('#stt_JADID').val('-');
                                $('#stt_MOSHAHEDE').val('-');
                                $('#stt_ESLAH').val('-');
                                $('#stt_NAGHS').val('-');
                                $('#stt_KARSHENASI').val('-');
                                $('#stt_ADAME_TATABOGH_EMZA').val('-');
                                $('#stt_NAHAEI').val('-');
                                $('#stt_ENSERAF').val('-');
                                $('#stt_MONTAFI').val('-');
                                $('#stt_td').hide();
                            }
                        }
                    </script>
                    <span class="noThing"></span>
                    <select name="darkhastType" id="search_darkhastType" onchange="changeDarkhast()">
                        <option value="ALL" >-</option>
                        <option value="TAGHIRAT" id="type_Tgr" >تغییرات</option>
                        <option value="EBTAL" id="type_Ebtal" >ابطال</option>
                        <option value="BAZKHARID" id="type_Bazkharid"  >بازخرید</option>
                        <option value="TAGHIR_CODE" >تغییر کد</option>
                        <option value="TOZIH" >توضیح</option>
                        <option value="VAM" id="type_Vam" >وام</option>
                        <option value="BARDASHT" id="type_Bardasht" >برداشت از اندوخته</option>
                        <option value="KHESARAT" id="type_Khesarat">خسارت</option>
                    </select>
                    &nbsp;<label>نوع درخواست</label>
                </td>
                <td id="stt_td">
                    <span class="noThing"></span>
                    <select name="darkhastState" id="stt" onchange="tgrStt();">
                        <option id="stt_ALL">-</option>
                        <option id="stt_TGR_SABT_NAZAR_RAEIS_KHADAMAT" noedarkhast="TGR" value="9070">ثبت نظر رئيس
                            اداره
                        </option>
                        <option id="stt_TGR_SODUR_MOAREFI_PEZESHK" noedarkhast="TGR" value="9090">صدور معرفي نامه
                            پزشكي
                        </option>
                        <option id="stt_TGR_MONTAZER_PEZESHK" value="9110">منتظر بررسي پزشك
                        </option>
                        <option id="stt_TGR_MONTAR_PASOKH_AZMAYESH" value="9100">چاپ معرفي‌نامه و
                            منتظر
                        </option>
                        <option id="stt_TGR_SABT_PEZESHK" noeDarkhast="TGR" value="9120">ثبت نظر پزشك</option>
                        <option id="stt_TGR_EHRAZ_HOVIAT" value="9160">احراز هويت شده</option>
                        <option id="stt_TGR_ELAM_MALI_TGR_ELH" value="9170">اعلام به مالي تغييرات
                        </option>
                        <option id="stt_TGR_ERSAL_ELHAGHIE" value="9190">ارسال الحاقيه</option>
                        <option id="stt_TGR_DARYAFT_ELH_KHATEME" value="9200">دريافت الحاقيه و
                            خاتمه
                        </option>
                        <option id="stt_TGR_TAEID_MASHROT" value="9040">تاييد مشروط درخواست
                            الحاقيه
                        </option>
                        <option id="stt_TGR_GHABUL_TAGHIR_SHARAYET" value="9050">قبول تغيير
                            شرايط الحاقيه
                        </option>

                        <option value="9160,1450,10070,11070" id="stt_EHRAZ">احراز هویت شده</option>
                        <option value="9010,1000,10000,11000" id="stt_MOVAGHAT">موقت</option>
                        <option value="9020,1010,10010,11010" id="stt_JADID">جدید</option>
                        <option value="9030,1020,10020,11020" id="stt_MOSHAHEDE">مشاهده شده</option>
                        <option value="9140,1100,10050,11050" id="stt_ESLAH">اصلاح درخواست</option>
                        <option value="9130,1300,10040,11040" id="stt_NAGHS">نیازمند اصلاح درخواست</option>
                        <option value="9080,1200,10030,11030" id="stt_KARSHENASI">کارشناسی</option>
                        <option value="9150,1400,10060,11060" id="stt_ADAME_TATABOGH_EMZA">عدم تطابق امضاء</option>
                        <option value="9180,1500,10130,11090" id="stt_NAHAEI">درخواست نهایی</option>
                        <option value="9210,1600,10140,11100" id="stt_ENSERAF">انصراف</option>
                        <option value="9220,1700,10150,11110" id="stt_MONTAFI">منتفی</option>

                        <option id="stt_ESTELAM_VAM" value="10080">استعلام شرایط وام</option>
                        <option id="stt_GHESTBANDI_VAM" value="10090">وام قسط‌ بندي شده</option>
                        <option id="stt_ELAM_MALI_VAM" value="10100">اعلام به مالي وام</option>
                        <option id="stt_SODUR_NAHAEI_VAM" value="10110">صدور نهايي و واريز وام</option>
                        <option id="stt_ERSAL_DAFTARCHE_VAM" value="10120">ارسال دفترچه وام</option>
                        <option id="stt_DARYAFT_KHATEME_VAM" value="10160">دريافت دفترچه وام و خاتمه</option>

                        <option id="stt_EBT_MNTZR_TAKMIL_MDRK" value="1300">منتظر تکمیل مدارک</option>
                        <option id="stt_EBT_TAKMIL_MDRK" value="1100">تکمیل مدارک</option>

                        <option id="stt_KSR_TSHKL_PRVNDE" value="600">تشکیل پرونده</option>
                        <option id="stt_KSR_TSVIE_PRDKT_SHD" value="610">تسویه پرداخت شده</option>
                        <option id="stt_KSR_TSVIE_PRDKT_NA_SHD" value="620">تسویه غیرقابل پرداخت</option>
                        <option id="stt_KSR_AKHZ_MJVZ" value="644">نياز به اخذ مجوز</option>
                        <option id="stt_KSR_SDR_MJVZ" value="645">صدور مجوز</option>
                        <option id="stt_KSR_ADAM_SDR_MJVZ" value="646">عدم صدور مجوز</option>
                        <option id="stt_KSR_KHTME_PRVNDE" value="647">خاتمه پرونده خسارت</option>
                        <option id="stt_KSR_KAR_KHES" value="648">منتظر بررسی کارشناس خسارت</option>
                        <option id="stt_KSR_EL_JADD" value="652">درخواست الحاقيه خسارت جديد</option>
                        <option id="stt_KSR_BRSI_MJDD" value="653">بررسي مجدد پرونده خسارت</option>
                        <option id="stt_KSR_TAEID" value="654">تاييد بررسي پرونده خسارت</option>
                        <option id="stt_KSR_MNTZR_TAEID" value="655">منتظر تاييد نهايي پرونده خسارت</option>
                        <option id="stt_KSR_MSHDE_SHDE" value="657">الحاقیه خسارت مشاهده شده</option>



                    </select>
                    &nbsp;<label>وضعیت</label>
                </td>
                <td>
                    <input type="button" style="margin-left:25px" value="پاک کردن فرم" onclick="darkhast_clearSeachFrom_b()">
                    <%--<input type="submit" style="margin-left:25px" value="جستجو">--%>
                    <input type="button" onclick="searchDarkhastDarJaryanAjax()" style="margin-left:25px" value="جستجو">
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function darkhast_clearSeachFrom_b()
    {
        $('#search_kodMelliBimeShodeDarkhast').val('');
        $('#search_familyBimeShodeDarkhast').val('');
        $('#search_nameBimeShodeDarkhast').val('');
        $('search_kodMelliBimeGozar').val('');
        $('#search_familyBimeGozarDarkhast').val('');
        $('#search_nameBimeGozarDarkhast').val('');
        $('#search_taTarikhDarkhast').val('');
        $('#search_azTarikhDarkhast').val('');
        $('#search_nameKarshenas').val('');
        $('#search_namayandegiNameDarkhast').val('');
        $('#search_namayandegiIdDarkhast').val('');
        $('#search_shomareBime').val('');
        $('#search_kodMelliBimeGozar').val('');
    }

    function searchDarkhastDarJaryanAjax() {
        $.ajax({
            type: "POST",
            url: 'resultTab4.action',
            data: $('#form_search_for_darkhast').serialize(),
            success: function (response) {
                // we have the response
                $('#displayTab4').html(response);
            }
        });
    }
</script>
