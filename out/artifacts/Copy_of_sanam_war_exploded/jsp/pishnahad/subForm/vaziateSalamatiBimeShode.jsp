<%@ page import="com.bitarts.parsian.model.pishnahad.VaziateSalamatiBimeShode" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 4/7/11
  Time: 2:27 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    function setSharhOrSharhBimari(id){
        if(id == 22 || id == 23 || id == 24){
            $("#sharh_or_sharhbimari").text("شرح  : ");
        }
    }
    $(function() {
        $('.comment').click(function(){
            var id = this.id.split('_')[1];
            if(id==22||id==23||id==24){
                $('.mavaredeMasrafi').show();
                $('.mavaredeMasrafi input').removeAttr("disabled");
                $('.sayereMavared').hide();
                $('.sayereMavared input').attr("disabled", true);
            }else{
                $('.sayereMavared').show();
                $('.sayereMavared input').removeAttr("disabled");
                $('.mavaredeMasrafi').hide();
                $('.mavaredeMasrafi input').attr("disabled", true);
            }
            $('#shoroeZamaneBimari_txt').val($('#shoroeZamaneBimari_'+id).val());
            $('#vaziateFelieBimeShode_txt').val($('#vaziateFelieBimeShode_'+id).val());
            $('#daroyeMasrafi_txt').val($('#daroyeMasrafi_'+id).val());
            $('#meghdareMasraf_txt').val($('#meghdareMasraf_'+id).val());
            $('#modateMasraf_txt').val($('#modateMasraf_'+id).val());
            $('#modateMasraf2_txt').val($('#modateMasraf2_'+id).val());
            $('#darmaneAnjamShode_txt').val($('#darmaneAnjamShode_'+id).val());
            $('#tarikheJarahi_txt').val($('#tarikheJarahi_'+id).val());
            $('#tozih_txtArea').val($('#tozih_'+id).val());
            $('#mtn_soal').text($('#spn_'+id).text());
            dialogForm('addTozih','فرم ثبت و ویرایش وضعیت سلامتی',function(){
                $('#tBtn_'+id).attr("title", $('#tozih_txtArea').val());
                $('#shoroeZamaneBimari_'+id).val($('#shoroeZamaneBimari_txt').val());
                $('#vaziateFelieBimeShode_'+id).val($('#vaziateFelieBimeShode_txt').val());
                $('#daroyeMasrafi_'+id).val($('#daroyeMasrafi_txt').val());
                $('#meghdareMasraf_'+id).val($('#meghdareMasraf_txt').val());
                $('#modateMasraf_'+id).val($('#modateMasraf_txt').val());
                $('#modateMasraf2_'+id).val($('#modateMasraf2_txt').val());
                $('#darmaneAnjamShode_'+id).val($('#darmaneAnjamShode_txt').val());
                $('#tarikheJarahi_'+id).val($('#tarikheJarahi_txt').val());
                $('#tozih_'+id).val($('#tozih_txtArea').val());
            });
        });
    });
    var sizeOfSoals = ${fn:length(pishnehadConstants.constantSoalateVaziateSalamatiSet)};
    function validateJavabeSoalha_vaziateSalamati(){
        for(var i = 0 ; i < sizeOfSoals ; i++){
            if(i==22||i==23||i==24){
                if($('#soal_y_'+i).attr('checked')){
                    if(!$('#meghdareMasraf_'+i).val()){
                        alertMessage('سوال شماره '+(i+1).toString()+' نیاز به تعیین مقدار مصرف دارد.')
                        return false;
                    }
                    if(!$('#modateMasraf_'+i).val()){
                        alertMessage('سوال شماره '+(i+1).toString()+' نیاز به تعیین مدت مصرف دارد.')
                        return false;
                    }
                }
            }else if(i==3){

                if($('#soal_x_'+i).attr('checked')){
                    if(!$('#vaziateFelieBimeShode_'+i).val()){
                        alertMessage('سوال شماره '+(i+1).toString()+' نیاز به تعیین وضعیت فعلی بیمه شده دارد.')
                        return false;
                    }
                }
            }else{
                if(($('#soal_y_'+i).attr('checked'))&&(!$("#soal_x_"+i).attr("readonly"))){
                    if(!$('#vaziateFelieBimeShode_'+i).val()){
                        alertMessage('سوال شماره '+(i+1).toString()+' نیاز به تعیین وضعیت فعلی بیمه شده دارد.')
                        return false;
                    }
                }
            }
        }

        return true;
    }
    <%--This function is for bug in change speedly of radio buttons--%>
    <%--function disableForSomeSeconds(id){--%>
        <%--// obsolete--%>
        <%--var size = ${fn:length(pishnehadConstants.constantSoalateVaziateSalamatiSet)};--%>
        <%--var counter = 0;--%>
        <%--$('#disableForSomeSeconds_tbl .dblRadio').each(function(){--%>
            <%--if(counter%6 == 1 || counter == id)$(this).buttonset().buttonset("disable");--%>
            <%--counter++;--%>
            <%--if(counter == size){--%>
                <%--counter = 0;--%>
                <%--$('#disableForSomeSeconds_tbl .dblRadio').each(function(){--%>
                    <%--if(counter%6 == 1 || counter == id)$(this).buttonset().buttonset("enable");--%>
                    <%--counter++;--%>
                <%--});--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>

    function refreshButtonset()
    {

        $('.dblRadio').buttonset("refresh");

    }
</script>
<div id="addTozih" style="display:none;direction:rtl;padding:5px">
    <table cellspacing="10" class="inputList">

        <tr>
            <td colspan="2" style="text-align:right;"><span style="float:right;">نام بیماری : </span><span style="float:right;margin-right:25px" id="mtn_soal"></span></td>
        </tr>
        <tr class="mavaredeMasrafi">
            <td>
                <span class="noThing">&nbsp;</span>
                <input type=text id="meghdareMasraf_txt" class="validate[required]" disabled="disabled"/>
                &nbsp;<label>مقدار مصرف</label>
            </td>
            <td>
                <span class="noThing">&nbsp;</span>
                <input type=text id="modateMasraf_txt" class="validate[required]" disabled="disabled"/>
                &nbsp;<label>مدت مصرف</label>
            </td>
        </tr>
        <tr class="sayereMavared">
            <td>
                <span class="noThing">&nbsp;</span>
                <input type=text id="shoroeZamaneBimari_txt" class=""/>
                &nbsp;<label>شروع زمان بيماري</label>
            </td>
            <td>
                <span class="noThing">&nbsp;</span>
                <input type=text id="vaziateFelieBimeShode_txt" class="validate[required]" disabled="disabled"/>
                &nbsp;<label>وضعيت فعلي بيمه شده</label>
            </td>
        </tr>
        <tr class="sayereMavared">
            <td>
                <span class="noThing">&nbsp;</span>
                <input type=text id="daroyeMasrafi_txt" class=""/>
                &nbsp;<label>داروي مصرفي</label>
            </td>
            <td>
                <span class="noThing">&nbsp;</span>
                <input type=text id="modateMasraf2_txt" class=""/>
                &nbsp;<label>مدت مصرف</label>
            </td>
        </tr>
        <tr class="sayereMavared">
            <td>
                <span class="noThing">&nbsp;</span>
                <input type=text id="darmaneAnjamShode_txt" class=""/>
                &nbsp;<label>درمان انجام شده</label>
            </td>
            <td>
                <input type=text id="tarikheJarahi_txt" class="datePkr"/>
                &nbsp;<label>تاريخ جراحي</label>
            </td>
        </tr>
        <tr>
            <td colspan="2">

                <span class="noThing">&nbsp;</span>
                <textarea rows="5" cols="80" id="tozih_txtArea"></textarea>
                <label id="sharh_or_sharhbimari" style="vertical-align:top;">شرح بیماری : </label>
            </td>
        </tr>
    </table>
</div>

<sec:authorize ifAnyGranted="ROLE_SUPERVISOR,ROLE_ADMIN,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_PEZESHK,ROLE_RAEIS_SODUR">
      <c:if test="${pishnehad.bimename != null&&pishnehad.state.id>=250}">
در صد اضافه نرخ پزشکی :
          ${pishnehad.estelam.darsad_ezafe_nerkh_pezeshki}
      </c:if>
</sec:authorize>

<table class="grid" id="disableForSomeSeconds_tbl" border="0" cellspacing="1" cellpadding="0" style="width:680px;">
    <tr class=tableHeaders>
        <th>بیماری</th>
    </tr>
    <c:set scope="page" var="BALI" value="<%=VaziateSalamatiBimeShode.JavabeSolal.BALI.toString()%>"/>
    <c:set scope="page" var="KHEIR" value="<%=VaziateSalamatiBimeShode.JavabeSolal.KHEIR.toString()%>"/>
    <c:forEach var="soal" items="${pishnehadConstants.constantSoalateVaziateSalamatiSet}" varStatus="i">
        <input type="hidden" value="${pishnehad.vaziateSalamatiBimeShodeList[i.index].id}" name="pishnehad.vaziateSalamatiBimeShodeList[${i.index}].id"/>
        <input type="hidden" value="${soal.id}" name="pishnehad.vaziateSalamatiBimeShodeList[${i.index}].constantSoalItem.id"/>
        <c:if test="${i.index == 22}">
            <tr class=tableHeaders>
                <th>مصرف</th>
            </tr>
        </c:if>
        <c:if test="${i.index == 25}">
            <tr class="tableHeaders" id="banovanHeader">
                <th>سوالات مربوط به بانوان</th>
            </tr>
        </c:if>
        <tr class="${i.index % 2 == 0 ? 'odd': 'even'}" id="theTr${i.index+1}">
            <td>

            <span class="comment my-comments" title="${pishnehad.vaziateSalamatiBimeShodeList[i.index].tozih == null||pishnehad.vaziateSalamatiBimeShodeList[i.index].tozih == '' ? 'توضیح' : pishnehad.vaziateSalamatiBimeShodeList[i.index].tozih}"
                  id="tBtn_${i.index}" onclick="setSharhOrSharhBimari(${i.index});">&nbsp;
                <%--<c:if test="${i.index == 22 || i.index==23 || i.index==24}">--%>
                <%--<input type="hidden" value="شرح" id="sharh_or_sharhbimari${i.index}">--%>
                <%--</c:if>--%>
                <%--<c:if test="${i.index != 22 && i.index!=23 && i.index!=24}">--%>
                    <%--<input type="hidden" value="شرح بیماری" id="sharh_or_sharhbimari${i.index}">--%>
                <%--</c:if>--%>
                <input type="hidden" value="${pishnehad.vaziateSalamatiBimeShodeList[i.index].shoroeZamaneBimari}" id="shoroeZamaneBimari_${i.index}" name="pishnehad.vaziateSalamatiBimeShodeList[${i.index}].shoroeZamaneBimari"/>
                <input type="hidden" value="${pishnehad.vaziateSalamatiBimeShodeList[i.index].vaziateFelieBimeShode}" id="vaziateFelieBimeShode_${i.index}" name="pishnehad.vaziateSalamatiBimeShodeList[${i.index}].vaziateFelieBimeShode"/>
                <input type="hidden" value="${pishnehad.vaziateSalamatiBimeShodeList[i.index].daroyeMasrafi}" id="daroyeMasrafi_${i.index}" name="pishnehad.vaziateSalamatiBimeShodeList[${i.index}].daroyeMasrafi"/>
                <input type="hidden" value="${pishnehad.vaziateSalamatiBimeShodeList[i.index].meghdareMasraf}" id="meghdareMasraf_${i.index}" name="pishnehad.vaziateSalamatiBimeShodeList[${i.index}].meghdareMasraf"/>
                <input type="hidden" value="${pishnehad.vaziateSalamatiBimeShodeList[i.index].modateMasraf}" id="modateMasraf_${i.index}" name="pishnehad.vaziateSalamatiBimeShodeList[${i.index}].modateMasraf"/>
                <input type="hidden" value="${pishnehad.vaziateSalamatiBimeShodeList[i.index].modateMasraf2}" id="modateMasraf2_${i.index}" name="pishnehad.vaziateSalamatiBimeShodeList[${i.index}].modateMasraf2"/>
                <input type="hidden" value="${pishnehad.vaziateSalamatiBimeShodeList[i.index].darmaneAnjamShode}" id="darmaneAnjamShode_${i.index}" name="pishnehad.vaziateSalamatiBimeShodeList[${i.index}].darmaneAnjamShode"/>
                <input type="hidden" value="${pishnehad.vaziateSalamatiBimeShodeList[i.index].tarikheJarahi}" id="tarikheJarahi_${i.index}" name="pishnehad.vaziateSalamatiBimeShodeList[${i.index}].tarikheJarahi"/>
                <input type="hidden" value="${pishnehad.vaziateSalamatiBimeShodeList[i.index].tozih}" id="tozih_${i.index}" name="pishnehad.vaziateSalamatiBimeShodeList[${i.index}].tozih"/>
            </span>
                <div class="dblRadio" onclick="refreshButtonset()">
                    <input type="radio" id="soal_x_${i.index}" name="pishnehad.vaziateSalamatiBimeShodeList[${i.index}].javabeSolal"
                    <c:if test="${(pishnehad.vaziateSalamatiBimeShodeList[i.index].javabeSolal == null && i.index == 3)
                            || pishnehad.vaziateSalamatiBimeShodeList[i.index].javabeSolal != BALI}">checked="checked"</c:if>
                    value="${KHEIR}"/>

                    <input type="radio" id="soal_y_${i.index}" name="pishnehad.vaziateSalamatiBimeShodeList[${i.index}].javabeSolal"
                    <c:if test="${(pishnehad.vaziateSalamatiBimeShodeList[i.index].javabeSolal == null && i.index != 3)
                            || pishnehad.vaziateSalamatiBimeShodeList[i.index].javabeSolal == BALI}">checked="checked"</c:if>
                    value="${BALI}"/>
                    <label for="soal_x_${i.index}">خیر</label>
                    <label for="soal_y_${i.index}">بلی</label>
                </div>
                &nbsp;<label><span style="color:#daa520;">${i.index + 1}</span>. <span id="spn_${i.index}">${soal.matneSoal}</span></label>
            </td>
        </tr>
    </c:forEach>

</table>
<script type="text/javascript">
    function enableTheComments(index){
        if(index==3){
            $("#tBtn_"+index).hide();
        }else{
            $("#tBtn_"+index).show();
        }
    }
    function disableTheComments(index){
        if(index == 3){
            $("#tBtn_"+index).show();
        }else{
            $("#tBtn_"+index).hide();
        }
    }

</script>