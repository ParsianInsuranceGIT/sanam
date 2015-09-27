<%@ page import="com.bitarts.parsian.service.calculations.Constants.NSPConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form id="createSodurBimename" name="form4dish" action="/sodoureBimenameh.action" method="post" accept-charset="UTF-8">
<input type="hidden" name="pishnehad.id" value="${pishnehad.id}"/>
<input type="hidden" id="transidbimename" name="transitionId"/>
<input type="hidden" id="sodurbimlgmsg" name="logmessage" value="test"/>

<input type="hidden" id="NafareEmza" />
<div id="tblEmza" style="display: none">
    <table cellpadding="3" cellspacing="3" border="0"
           style="margin-left:auto;margin-right:auto;align:center;direction: rtl;">
        <tr>
            <td colspan="5">جستحو شخص امضا کننده</td>
        </tr>
        <tr>
            <td>کد پرسنلی</td>
            <td><input type="text" id="emzaPersonalCode"/></td>
            <td>نام</td>
            <td><input type="text" id="emzaName"/></td>
            <td><input type="button" value="جستجو" id="btnSearch"
                       onclick="searchEmaz()"></td>
        </tr>
    </table>
    <div id="searchResualt"></div>
</div>

<table class="mystrippedtable" id="table4sodourbimenameh" dir="rtl" cellpadding="2px" cellspacing="0px" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
<tr class="odd">
    <td>
        <label>شماره پیشنهاد:</label>
    </td>
    <td colspan="3">
        <input type="text" name="shomarePishnehad" readonly="readonly" value="${pishnehad.radif}"/>
    </td>
    <td>
        <label>شماره بیمه نامه:</label>
    </td>
    <td colspan="3">
        <input type="text" name="bimename.shomare" readonly="readonly" value=""/>
    </td>
</tr>
<tr class="odd">
    <td>
        <label>وضعیت:</label>
    </td>
    <td colspan="3">
        <input type="text" readonly="readonly" value="${pishnehad.state.stateName}"/>
    </td>
    <td>
        <label>سریال عمر:</label>
    </td>
    <td colspan="3">
        <input type="text" name="bimename.serial" readonly="readonly" value=""/>
    </td>
</tr>
<tr class="even">
    <td rowspan="2" colspan="2">
        مشخصات بیمه گذار
    </td>
    <td>
        <label>نام و نام خانوادگی:</label>
    </td>
    <td>
        <input type="text" name="fullname" readonly="readonly" value="${pishnehad.bimeGozar.shakhs.name}&nbsp;${pishnehad.bimeGozar.shakhs.nameKhanevadegi}"/>
    </td>
    <td>
        <label>سن:</label>
    </td>
    <td colspan="3">
        <input type="text" name="sen" readonly="readonly" value="" id="sen_bimeGozar"/>
    </td>
</tr>
<tr class="even">
    <td>
        <label>شغل اصلی:</label>
    </td>
    <td>
        <input type="text" name="fullname" readonly="readonly" value="${pishnehad.bimeGozar.shakhs.shoghleAsli}"/>
    </td>
    <td>
        <label>شغل فرعی:</label>
    </td>
    <td colspan="3">
        <input type="text" name="sen" readonly="readonly" value="${pishnehad.bimeGozar.shakhs.shoghleFarei}"/>
    </td>
</tr>
<tr class="odd">
    <td rowspan="2" colspan="2">
        مشخصات بیمه شده
    </td>
    <td>
        <label>نام و نام خانوادگی:</label>
    </td>
    <td>
        <input type="text" name="fullname" readonly="readonly" value="${pishnehad.bimeShode.shakhs.name}&nbsp;${pishnehad.bimeShode.shakhs.nameKhanevadegi}"/>
    </td>
    <td>
        <label>سن:</label>
    </td>
    <td colspan="3">
        <input type="text" name="sen" readonly="readonly" value="" id="sen_bimeShode"/>
    </td>
</tr>
<tr class="odd">
    <td>
        <label>شغل اصلی:</label>
    </td>
    <td>
        <input type="text" name="fullname" readonly="readonly" value="${pishnehad.bimeShode.shakhs.shoghleAsli}"/>
    </td>
    <td>
        <label>شغل فرعی:</label>
    </td>
    <td colspan="3">
        <input type="text" name="sen" readonly="readonly" value="${pishnehad.bimeShode.shakhs.shoghleFarei}"/>
    </td>
</tr>
<tr class="even">
    <td rowspan="4" colspan="2">
        مشخصات بیمه نامه
    </td>
    <td>
        <label>حق بیمه اولیه:</label>
    </td>
    <td>
        <input type="text" name="اhaghbimeavaliye" readonly="readonly" value="${pishnehad.estelam.mablagh_seporde_ebtedaye_sal}"/>
    </td>
    <td>
        <label>حق بیمه منظم پرداختی:</label>
    </td>
    <td colspan="3">
        <input type="text" name="" readonly="readonly" value="${pishnehad.estelam.hagh_bime_pardakhti}"/>
    </td>
</tr>
<tr class="even">
    <td>
        <label>نحوه پرداخت:</label>
    </td>
    <td>
        <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime == 'mah'}">
            <input type="text" name="" readonly="readonly" value="ماهانه"/>
        </c:if>
        <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime == 'sal'}">
            <input type="text" name="" readonly="readonly" value="سالانه"/>
        </c:if>
        <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime == '6mah'}">
            <input type="text" name="" readonly="readonly" value="شش ماهه"/>
        </c:if>
        <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime == '3mah'}">
            <input type="text" name="" readonly="readonly" value="سه ماهه"/>
        </c:if>
        <c:if test="${pishnehad.estelam.nahve_pardakht_hagh_bime == 'yekja'}">
            <input type="text" name="" readonly="readonly" value="یکجا"/>
        </c:if>
    </td>
    <td>
        <label>سرمایه فوت:</label>
    </td>
    <td colspan="3">
        <input type="text" name="" readonly="readonly" value="${pishnehad.estelam.sarmaye_paye_fot}"/>
    </td>
</tr>
<tr class="even">
    <td>
        <label>نرخ افزایش حق بیمه:</label>
    </td>
    <td>
        <input type="text" name="" readonly="readonly" value="<c:if test="${pishnehad.estelam.nerkh_afzayesh_salaneh_hagh_bime == null}">0</c:if><c:if test="${pishnehad.estelam.nerkh_afzayesh_salaneh_hagh_bime != null}">${pishnehad.estelam.nerkh_afzayesh_salaneh_hagh_bime}</c:if>"/>
    </td>
    <td>
        <label>نرخ افزایش سرمایه فوت:</label>
    </td>
    <td colspan="3">
        <input type="text" name="" readonly="readonly" value="${pishnehad.estelam.nerkh_afzayesh_salaneh_sarmaye_fot}"/>
    </td>
</tr>
<tr class="even">
    <td>
        <label>اضافه نرخ پزشکی:</label>
    </td>
    <td colspan="5">
        <input type="text" name="" readonly="readonly" value="${pishnehad.estelam.darsad_ezafe_nerkh_pezeshki}"/>
    </td>
</tr>
<tr class="odd">
    <td rowspan="2" colspan="2">
        مدت
    </td>
    <td>
        <label>مدت:</label>
    </td>
    <td>
        <input type="text" name="moddat" readonly="readonly" value="${pishnehad.estelam.modat_bimename}"/>
    </td>
    <td>
        <label>تاریخ صدور:</label>
    </td>
    <td colspan="3">
        <input type="text" name="bimename.tarikhSodour" readonly="readonly" value="<%=DateUtil.getCurrentDate()%>"/>
    </td>
</tr>
<tr class="odd">
    <td>
        <label>تاریخ شروع:</label>

    </td>
    <td colspan="2">
        <span class="noThing"></span>
        <span class="noThing"></span>
        <span class="noThing"></span>
        <span class="noThing"></span>
        <input type="text" id="bimename_tarikhShorou" name="bimename.tarikhShorou" value="<%=DateUtil.getCurrentDate()%>" onchange="setTarikhEnghezaAjaxly();" class="datePkr"/>
    </td>
    <td>
        <label>تاریخ انقضا:</label>
    </td>
    <td colspan="2">
        <input type="text" id="bimename_tarikhEngheza" name="bimename.tarikhEngheza" readonly="readonly" value="<%=DateUtil.addYear(DateUtil.getCurrentDate(),Integer.parseInt(pishnehadRun.getEstelam().getModat_bimename()))%>"/>
    </td>
</tr>
<tr class="even">
    <td colspan="2">پوشش های اضافی</td>
    <td colspan="6">
        <table class="mystrippedtable jtable" dir="rtl" cellpadding="2px" cellspacing="0px" style="border-spacing:1px;margin-left:auto;margin-right:auto;">
            <thead>
            <tr>
                <th>
                    نام پوشش اضافی
                </th>
                <th>
                    جزئیات خطر
                </th>
                <th>
                    سرمایه تعهد
                </th>
                <th>
                    نرخ تعهد
                </th>
                <th>
                    تاریخ شروع
                </th>
                <th>
                    تاریخ پایان
                </th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_haadese == 'yes'}">
                <tr class="odd">
                    <td>
                        حادثه - فوت در اثر حادثه
                    </td>
                    <td>
                        <select id="pishnehad_estelam_tabaghe_khatar" name="pishnehad.estelam.tabaghe_khatar" onchange="submitNewTabagheKhatar();">
                            <option ${pishnehad.estelam.tabaghe_khatar == '1'?'selected=selected':''} value="1">طبقه خطر ۱</option>
                            <option ${pishnehad.estelam.tabaghe_khatar == '2'?'selected=selected':''} value="2">طبقه خطر ۲</option>
                            <option ${pishnehad.estelam.tabaghe_khatar == '3'?'selected=selected':''} value="3">طبقه خطر ۳</option>
                            <option ${pishnehad.estelam.tabaghe_khatar == '4'?'selected=selected':''} value="4">طبقه خطر ۴</option>
                            <option ${pishnehad.estelam.tabaghe_khatar == '5'?'selected=selected':''} value="5">طبقه خطر ۵</option>
                        </select>
                    </td>
                    <td>${pishnehad.estelam.sarmaye_paye_fot_dar_asar_hadese}</td>
                    <td>-</td>
                    <td><label class="tarikh_shoru_pooshesh"><%=DateUtil.getCurrentDate()%></label><input type="hidden" name="estelam.pooshesh_fot_tarikh_shoru" value="<%=DateUtil.getCurrentDate()%>"/></td>
                    <td><label class="tarikh_etmam_pooshesh"><%=DateUtil.addYear(DateUtil.getCurrentDate(),Integer.parseInt(pishnehadRun.getEstelam().getModat_bimename()))%></label><input type="hidden" name="estelam.pooshesh_fot_tarikh_payan" value="<%=DateUtil.addYear(DateUtil.getCurrentDate(),Integer.parseInt(pishnehadRun.getEstelam().getModat_bimename()))%>"/></td>
                </tr>
            </c:if>
            <c:if test="${pishnehad.estelam.pooshesh_naghs_ozv == 'yes'}">
                <tr class="odd">
                    <td>
                        حادثه - نقص عضو در صورت از کار افتادگی کامل
                    </td>
                    <td>
                        <select id="pishnehad_estelam_tabaghe_khatar_naghsozv" name="pishnehad.estelam.tabaghe_khatar_naghsozv" onchange="submitNewTabagheKhatar();">
                            <option ${pishnehad.estelam.tabaghe_khatar_naghsozv == '1'?'selected=selected':''} value="1">طبقه خطر ۱</option>
                            <option ${pishnehad.estelam.tabaghe_khatar_naghsozv == '2'?'selected=selected':''} value="2">طبقه خطر ۲</option>
                            <option ${pishnehad.estelam.tabaghe_khatar_naghsozv == '3'?'selected=selected':''} value="3">طبقه خطر ۳</option>
                            <option ${pishnehad.estelam.tabaghe_khatar_naghsozv == '4'?'selected=selected':''} value="4">طبقه خطر ۴</option>
                            <option ${pishnehad.estelam.tabaghe_khatar_naghsozv == '5'?'selected=selected':''} value="5">طبقه خطر ۵</option>
                        </select>
                    </td>
                    <td>${pishnehad.estelam.sarmaye_pooshesh_naghs_ozv}</td>
                    <td>-</td>
                    <td><label class="tarikh_shoru_pooshesh"><%=DateUtil.getCurrentDate()%></label><input type="hidden" name="estelam.pooshesh_naghs_tarikh_shoru" value="<%=DateUtil.getCurrentDate()%>"/></td></td>
                    <td><label class="tarikh_etmam_pooshesh"><%=DateUtil.addYear(DateUtil.getCurrentDate(),Integer.parseInt(pishnehadRun.getEstelam().getModat_bimename()))%></label><input type="hidden" name="estelam.pooshesh_naghs_tarikh_payan" value="<%=DateUtil.addYear(DateUtil.getCurrentDate(),Integer.parseInt(pishnehadRun.getEstelam().getModat_bimename()))%>"/></td>
                </tr>
            </c:if>
            <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_zelzele == 'yes'}">
                <tr class="odd">
                    <td>
                        حادثه - فوت در اثر زلزله
                    </td>
                    <td>
                        -
                    </td>
                    <td>-</td>
                    <td>-</td>
                    <td><label class="tarikh_shoru_pooshesh"><%=DateUtil.getCurrentDate()%></label><input type="hidden" name="estelam.pooshesh_zelzele_tarikh_payan" value="<%=DateUtil.getCurrentDate()%>"/></td>
                    <td><label class="tarikh_etmam_pooshesh"><%=DateUtil.addYear(DateUtil.getCurrentDate(),Integer.parseInt(pishnehadRun.getEstelam().getModat_bimename()))%></label><input type="hidden" name="estelam.pooshesh_zelzele_tarikh_payan" value="<%=DateUtil.addYear(DateUtil.getCurrentDate(),Integer.parseInt(pishnehadRun.getEstelam().getModat_bimename()))%>"/></td>
                </tr>
            </c:if>
            <c:if test="${pishnehad.estelam.pooshesh_amraz_khas == 'yes'}">
                <tr class="odd">
                    <td>
                        امراض خاص - با پیوند اعضا
                    </td>
                    <td>
                        <select name="pishnehad.estelam.joziyate_pooshesh_amraz_khas">
                            <option>با پيوند اعضا</option>
                            <option>بدون پيوند اعضا</option>
                        </select>
                    </td>
                    <td>${pishnehad.estelam.sarmaye_pooshesh_amraz_khas}</td>
                    <td>-</td>
                    <td><label class="tarikh_shoru_pooshesh"><%=DateUtil.getCurrentDate()%></label><input type="hidden" name="estelam.pooshesh_amraz_tarikh_shoru" value="<%=DateUtil.getCurrentDate()%>"/></td>
                    <td><label class="tarikh_etmam_pooshesh"><%=DateUtil.addYear(DateUtil.getCurrentDate(), Integer.parseInt(pishnehadRun.getEstelam().getModat_bimename()))%></label><input type="hidden" name="estelam.pooshesh_amraz_tarikh_payan" value="<%=DateUtil.addYear(DateUtil.getCurrentDate(),Integer.parseInt(pishnehadRun.getEstelam().getModat_bimename()))%>"/></td>
                </tr>
            </c:if>
            <c:if test="${pishnehad.estelam.pooshesh_moafiat == 'yes'}">
                <tr class="odd">
                    <td>
                        معافیت از پرداخت اقساط مانده حق بیمه
                    </td>
                    <td>
                         <select name="pishnehad.estelam.joziyate_pooshesh_moafiat">
                            <option>بيمه‌شده</option>
                            <option>بيمه‌گذار</option>
                        </select>
                    </td>
                    <td>-</td>
                    <td>-</td>
                    <td><label class="tarikh_shoru_pooshesh"><%=DateUtil.getCurrentDate()%></label></td>
                    <td><label class="tarikh_etmam_pooshesh"><%=DateUtil.addYear(DateUtil.getCurrentDate(),Integer.parseInt(pishnehadRun.getEstelam().getModat_bimename()))%></label></td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </td>
</tr>
<tr class="odd">
    <td colspan="2">
        شرایط خصوصی
    </td>
    <td colspan="6">
        <%--name="bimename.sharayeteKhosusi"--%>
        <%--<textarea rows="9" cols="80" style="text-align:right;" name="pishnehadReport.tozihatBimenameForPrint">--%>
<%--الف)حداقل سود اعطايي به اين بيمه نامه 15 درصد به صورت سالانه و روزشمار مي باشد، كه براي مدت 10 سال تضمين شده است. در پايان 10 سال، نرخ سود، مطابق اعلام و تحت نظارت بيمه مركزي ايران اعمال خواهد شد.--%>
<%--ب)در صورت كسب سود مازاد 15 درصد از سرمايه گذاري اندوخته بيمه گذاران، 85 درصد از اين سود، به صورت مشاركت در منافع در مقاطع زماني مختلف به حساب بيمه گذار منظور و مراتب كتبا به بيمه گذار اعلام خواهد شد.--%>
<%--ج)دريافت وام، سود مشاركت در منافع و برداشت از اندوخته مطابق مقررات بيمه اي و منوط به پرداخت كامل و به موقع حق بيمه ها مي باشد.--%>
<%--د)پرداخت سود و محاسبه اندوخته به صورت روزشمار بوده و به حق بيمه هاي پرداخت شده زودتر از سررسيد، سود تشويقي اعطا خواهد شد.--%>
<%--<c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_haadese == 'yes'}">ه)فوت در اثر زلزله جزء پوشش هاي اضافي مي باشد.</c:if>--%>
        <%--</textarea>--%>
            <textarea rows="9" cols="80" style="text-align:right;" name="pishnehadReport.tozihatBimenameForPrint">
                ${pishnehad.tozihateBimenameForPrint}
            </textarea>
    </td>
</tr>
<tr class="even">
    <td colspan="1">
        امضا کنندگان
    </td>
    <td>
        امضا کننده اول:
    </td>
    <td colspan="2">
        <input type="text" id="bimename_emzaKonandeAval" style="width:100px" value="${user.firstName} ${user.lastName}"/>
        <input type="hidden" name="bimename.emzaKonandeAval.id" id="bimename_emzaKonandeAval_id" value="${user.emzaKonande.id}" />
        <input type="button" value="جستجو" onclick="$('#NafareEmza').val('#bimename_emzaKonandeAval');fillEmze();" />
    </td>
    <td>
        امضا کننده دوم:
    </td>
    <td colspan="3">
        <input type="text" id="bimename_emzaKonandeDovom" style="width:100px" />
        <input type="hidden" name="bimename.emzaKonandeDovom.id" id="bimename_emzaKonandeDovom_id" />
        <input type="button" value="جستجو" onclick="$('#NafareEmza').val('#bimename_emzaKonandeDovom');fillEmze(); " />
    </td>
</tr>
<tr class="odd">

    <td colspan="8">
        <input type="checkbox" name="codeMovaghat"
            <c:if test="${pishnehad!=null && pishnehad.id!=null && pishnehad.options=='CODE_MOVAGHAT'}">
                checked="checked"
            </c:if>
                />
        <label>صدور با کد موقت 111120</label>
    </td>
</tr>
<tr>
    <td>
        <input type="button" onclick="checkIfDateIsLessThanNow();" value="صدور"/>
    </td>
    <td>
        <input type="button" onclick="chapAzmayeshi();" value="چاپ آزمایشی"/>
    </td>
</tr>
<tr>
    <td id="dateresholder">

    </td>
</tr>
</table>
</form>

<script type="text/javascript">
    function sodoureBimename(){
        $("#transitionSelector").val(95);
        $("#transidbimename").val($("#transitionSelector").val());
        openDialogBoxAndSubmitTo("sodurbimlgmsg","createSodurBimename","صدور بیمه نامه");
    }
    function checkIfDateIsLessThanNow(){
        $.post('/showVaziateGhestBandi.action?pishnehad.id='+${pishnehad.id},function(msg) {
//            $("#dateresholder").html(msg);
            if(msg.indexOf('hasError') == -1){
                if(($("#bimename_emzaKonandeAval_id").val().length < 1)||($("#bimename_emzaKonandeDovom_id").val().length < 1))
                {
                    alertMessage('لطفا امضاكننده‌هاي بيمه‌نامه را مشخص نماييد.');
                }
                else
                {
                    if (msg.indexOf('fishEQghest') != -1)
                    {
                        var ghestAmount= msg.split("\"/>")[0];
//                        window.alert(ghestAmount.substring(ghestAmount.lastIndexOf("value=\"")+7));

                        var fishAmount= msg.split("\"/>")[1];
//                        window.alert(fishAmount.substring(fishAmount.lastIndexOf("value=\"")+7));//
                        window.confirmMessage("مبلغ پیش پرداخت با مبلغ قسط تطابق ندارد؛ از صدور بیمه نامه مطمئن هستید؟","",function(){sodoureBimename();});
                    }
                    else
                    {
                        sodoureBimename();
                    }
                }
            } else {
                alertMessage("پیشنهاد دارای خطای محاسباتی است.");
            }
//            else{
//                alertMessage("تاریخ شروع باید حتما بزرگتر و یا برابر تاریخ روز باشد.");
//                $("#bimename_tarikhShorou").val($("#tarikhe_rouz").val());
//                $("#bimename_tarikhEngheza").val($("#defaultexpiry_result").val());
//                $(".tarikh_shoru_pooshesh").text($("#tarikhe_rouz").val());
//                $(".tarikh_etmam_pooshesh").text($("#defaultexpiry_result").val());
//            }
        });
    }
    function setTarikhEnghezaAjaxly(){
        $.post('/setTarikhEnghezaAjaxly.action?theDate='+$("#bimename_tarikhShorou").val()+'&pishnehad.estelam.modat_bimename=${pishnehad.estelam.modat_bimename}',function(msg) {
            $("#resultholderForSearchedFishes").show();
            $("#dateresholder").html(msg);
            if($("#datecheck_result").val() == "true"){
                $("#bimename_tarikhEngheza").val($("#expirydate_result").val());
                $(".tarikh_shoru_pooshesh").text($("#bimename_tarikhShorou").val());
                $(".tarikh_etmam_pooshesh").text($("#expirydate_result").val());
                $('#sen_bimeGozar').val(mohasebeyeSen('${pishnehad.bimeGozar.shakhs.tarikheTavallod}', $('#bimename_tarikhShorou').val()));
                $('#sen_bimeShode').val(mohasebeyeSen('${pishnehad.bimeShode.shakhs.tarikheTavallod}', $('#bimename_tarikhShorou').val()));
            }else{
                alertMessage("تاریخ شروع باید حتما بزرگتر و یا برابر تاریخ روز باشد.");
                $("#bimename_tarikhShorou").val($("#tarikhe_rouz").val());
                $("#bimename_tarikhEngheza").val($("#defaultexpiry_result").val());
                $(".tarikh_shoru_pooshesh").text($("#tarikhe_rouz").val());
                $(".tarikh_etmam_pooshesh").text($("#defaultexpiry_result").val());
            }
        });
    }

    function searchEmaz()
    {
        var emzaName = $('#emzaName').val();
        var emzaPersonalCode = $('#emzaPersonalCode').val();


        $.post("/findEmzaKonande.action?user.firstName="+ emzaName + "&user.personalCode=" + emzaPersonalCode + "&pishnehad.id=" + "${pishnehad.id}" , function(msg){
            $('#searchResualt').html(msg);
      });

        fillEmze();
    }

    $(document).ready(function() {

        <c:if test="${pishnehad!=null && pishnehad.state!=null && pishnehad.state.id==245 && pishnehad.namayande.codeCanceled}">
            alertMessage("<p style='color: #C00000'> "+"کارشناس محترم، نماینده ی این پیشنهاد لغو کد شده است. بدیهی است در صورت صدور بیمه نامه عواقب آن بر عهده صادر کننده خواهد بود."+"</p>");
        </c:if>

        $('#emzaName').val('');
        $('#emzaPersonalCode').val('');

        $('#tblEmza').dialog({
            modal:true, resizable:false, autoOpen: false,
            width: 700, maxHeight:500, minHeight: 100,
            title: "جستحو شخص امضا کننده"});
        $('#sen_bimeGozar').val(mohasebeyeSen('${pishnehad.bimeGozar.shakhs.tarikheTavallod}', $('#bimename_tarikhShorou').val()));
        $('#sen_bimeShode').val(mohasebeyeSen('${pishnehad.bimeShode.shakhs.tarikheTavallod}', $('#bimename_tarikhShorou').val()));
    });

    function fillEmze()
    {
        $('#tblEmza').dialog('open');
    }

    function selectRow(id, fn, ln)
    {
        var ctrlId = $('#NafareEmza').val();
        $(ctrlId).val(fn  + " " + ln);
        $(ctrlId+'_id').val(id);

        hideEmzaModal();
    }

    function hideEmzaModal()
    {
        $('#emzaName').val('');
        $('#emzaPersonalCode').val('');
        $('#tblEmza').dialog('close');
        $('#searchResualt').html('');
    }
    function chapAzmayeshi(){
        window.open("/printBimename?pishnehadReport.pishnehad.id=${pishnehad.id}&"+$('#createSodurBimename').serialize(),"_blank");
    }
</script>