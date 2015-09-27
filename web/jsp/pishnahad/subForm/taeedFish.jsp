<%@ page import="com.bitarts.parsian.model.BankInfo" %>
<%@ page import="com.bitarts.parsian.model.pishnahad.Fish" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bitarts.parsian.model.asnadeSodor.Credebit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    List<Credebit> foundFishes = (List< Credebit>) request.getAttribute("foundFishes");
    List<Fish> allTheFishes = (List<Fish>) pishnehadRun.getFishs();
    List<Fish> wantedFishes = new ArrayList<Fish>();
    String theSelectedFish = (String) request.getAttribute("theFishSelecterHolder");
%>

<form id="searchfishform" name="form4dish" action="/makeInnerTransition.action" method="post" accept-charset="UTF-8">

    <input type="hidden" name="pishnehad.id" value="<%=pishnehadRun.getId()%>"/>
    <input type="hidden" id="transidforfishform" name="transitionId" value="6"/>
    <input type="hidden" name="logmessage" value="test"/>
    <%if(allTheFishes!=null){

        if (allTheFishes.size() >0){

            for (Fish theFish : allTheFishes) {
//                if (theFish.getPaymentType().equalsIgnoreCase(Constant.FISH)){
                    wantedFishes.add(theFish);
//                }
            }
    %>
    <table id="table4fish" class="jtable" dir="rtl" style="border-spacing:1px;margin-left:auto;margin-right:auto;">
        <thead>
        <th>شماره/ردیف</th>
        <th>تایید/عدم تایید</th>
        <th>شماره فیش</th>
        <th>نام بانک</th>
        <th>کد شعبه</th>
        <th>نام پرداخت کننده</th>
        <th>شماره سند</th>
        <th>مبلغ</th>
        <th>تاریخ پرداخت</th>
        <th>ساعت پرداخت</th>
        <th>حستجوی فیش</th>
        </thead>
        <tbody id="tbodyforfoundfishes">

        <%  int fishesGoodCounter = 1;

            for (Fish allTheFish : wantedFishes) {
                String tozihat = "";
                String sanad = "";
                if(allTheFish.getCredebit() != null && !allTheFish.getPaymentType().equals(Constant.INTERNETI))
                {
                    tozihat = allTheFish.getCredebit().getDaryafteFish().getTozihat();
                    sanad = allTheFish.getCredebit().getDaryafteFish().getShomareSanadBank();
                }

                        %>

        <input type="hidden" name="pishnehad.id" id="pishidholder<%=fishesGoodCounter%>" value="<%=pishnehadRun.getId()%>"/>
        <input type="hidden" name="fish.id" id="fishidholder<%=fishesGoodCounter%>" value="<%=allTheFish.getId()%>"/>
        <input type="hidden" name="fish.shomare" id="fishshomareholder<%=fishesGoodCounter%>" value="<%=allTheFish.getShomare()%>" />
        <input type="hidden" name="fish.bankName" id="fishbanknameholder<%=fishesGoodCounter%>" value="<%=allTheFish.getBankName()%>"/>
        <input type="hidden" name="fish.kodeShobe" id="fishbankshobeholder<%=fishesGoodCounter%>" value="<%=allTheFish.getKodeShobe()%>"/>
        <input type="hidden" name="fish.mablagh" id="fishmablaghholder<%=fishesGoodCounter%>" value="<%=allTheFish.getMablagh()%>"/>
        <input type="hidden" name="fish.tarikh" id="fishtarikhholder<%=fishesGoodCounter%>" value="<%=allTheFish.getTarikh()%>"/>
        <input type="hidden" name="fish.time" id="fishtimeholder<%=fishesGoodCounter%>" value="<%=allTheFish.getTime()%>"/>
        <input type="hidden" name="fish.credebit.daryafteFish.tozihat" id="fishtozihatholder${i.index}" value="${fish.credebit.daryafteFish.tozihat}"/>
        <input type="hidden" name="fish.credebit.daryafteFish.shomareSanadBank" id="fishsanadholder${i.index}" value="${fish.credebit.daryafteFish.shomareSanadBank}"/>

        <tr id="fishrow<%=fishesGoodCounter%>">
            <td>
                <%=fishesGoodCounter%>

            </td>
            <td id="testtesttest<%=fishesGoodCounter%>">
                <%
                    if (allTheFish.getFound().equalsIgnoreCase("true")){
                %>
                <p style="color:#006400;">تایید</p>
                <%
                }else{
                %>
                <p class="td_search_final" style="color:red;" >عدم تایید</p>
                <%
                    }
                %>
            </td>
            <td>
                <%=allTheFish.getShomare()%>
            </td>
            <td>
                <%=allTheFish.getBankName()%>
            </td>
            <td>
                <%=allTheFish.getKodeShobe()%>
            </td>
            <td>
                <%=tozihat%>
            </td>
            <td>
                <%=sanad%>
            </td>
            <td>
                <%=allTheFish.getMablagh()%>
            </td>
            <td>
                <%=allTheFish.getTarikh()%>
            </td>
            <td>
                <%=allTheFish.getTime()%>
            </td>
            <td id="taeed_final_result<%=fishesGoodCounter%>">
                <%
                    if (allTheFish.getFound().equalsIgnoreCase("true")){
                %>
                <p style="color:#006400;">تایید شد</p>
                <%
                }else{
                %>
                <a href="javascript:void(0);" onclick="$('.td_search').css('font-weight','normal');$(this).css('font-weight','bold');startSearching(<%=fishesGoodCounter%>);"class="td_search">جستجوی فیش</a>
                <%
                    }
                %>
            </td>
        </tr>
        <%fishesGoodCounter++;
        }%>
        </tbody>
    </table>
    <table>
        <tr>
            <td colspan="2">
                <input type="button" value="تایید فیش" onclick="taeedFish();"/>
            </td>
        </tr>
    </table>
    <%--</form>--%>
    <%}else{%>
    <table id="table4fish" dir="rtl" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tr>
            <td>
                <b>
                    فیشی وجود ندارد!
                </b>
            </td>
        </tr>
    </table>
    <%}
    }
    %>
</form>
<br/>
<br/>
<br/>
<form id="search4thewantedfish" name="form4dish" method="post" accept-charset="UTF-8" onsubmit="return nowSendingToSearch();">
    <%--<%if(theSelectedFish!=null)%>--%>
    <input type="hidden" id="changeReader" name="theFishSelecterHolder" value="<%=theSelectedFish%>"/>
    <% if (pishnehadRun!=null){%>
    <input type="hidden" name="pishnehad.id" value="<%=pishnehadRun.getId()%>"/>
    <input type="hidden" name="theSelectedFish" id="the_selected_fish" value="0"/>
    <%}%>
        <table id="table4fishsearching" dir="rtl" cellpadding="0" cellspacing="0" style="display: none; border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tr>
            <td colspan="2">
                جستجو در فیش های سیستم
            </td>
        </tr>
        <tr>
            <td>
                &nbsp;
            </td>
        </tr>
        <tr>
            <td>
                تاریخ پیش پرداخت:
            </td>
            <td>
                <input type="text" name="bankInfo.taarikh" id="bankinfo_tarikh"/>
            </td>
            <td>
دقیقه و ثانیه:
            </td>
            <td>
                <input type="text" name="bankInfo.time" id="bankinfo_time">
            </td>
        </tr>
        <tr>
            <td>
                &nbsp;
            </td>
        </tr>
        <tr>
            <td>
                کد شعبه:
            </td>
            <td>
                <input type="text" name="bankInfo.kodeShobe" id="bankinfo_kodeshobe"/>
            </td>
            <td>
                مبلغ فیش پرداختی:
            </td>
            <td>
                <input type="text" name="bankInfo.mablagh" id="bankinfo_mablagh" class="digitSeparators"/>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <input type="submit" class="noAnyDisable" value="جستجو" onclick="nowSendingToSearch();"/>
            </td>
        </tr>
    </table>
</form>
<br/>
<br/>
<div style="display:none;" id="resultholderForSearchedFishes">
    <table id="tableresult4fishsearch" dir="rtl" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto; display:none;" border="0">
        <thead id="tableresult4fishsearchthead">
        <tr>
            <th>
                ردیف
            </th>
            <th>
                انتخاب
            </th>
            <th>
                شماره فیش
            </th>
            <th>
                نام بانک
            </th>
            <th>
                کد شعبه
            </th>
            <th>نام پرداخت کننده</th>
            <th>شماره سند</th>
            <th>
                مبلغ فیش
            </th>
            <th>
                تاریخ فیش
            </th>
            <th>
                ساعت پرداخت
            </th>
        </tr>
        </thead>
        <tbody class="transient_tbody" id="transient_tbody_forfish">
        </tbody>
        <tr id="tr_agree_fish" style="display: none;">
            <td>
                <input type="button" onclick="replaceFishes()" value="تایید"/>
            </td>
        </tr>
    </table>
</div>


<script type="text/javascript">
    function nowSendingToSearch(){
        $(".transient_tbody").html("");
        $("#the_selected_fish").val($("#chageReader").val());
        var theSearchData = $('#search4thewantedfish').serialize();
        $.post('/makeSearchForFish.action', theSearchData,function(msg) {
            $("#resultholderForSearchedFishes").show();
            $("#tableresult4fishsearch").show();

            $("#transient_tbody_forfish").html(msg);
            if($('#selected_found1').length)
            {
                $('#tr_agree_fish').show();
            }
        });
        return false;
        //        $("#search4thewantedfish").submit();

    }

    var numRep = 0;

    function replaceFishes(){
        var selectedId = $('input[name=selectedFound]:checked', '').attr("id");
        var toBeReplaced = ($("#changeReader").val());
        selectedId = selectedId.replace("selected_found","");
        var selectedForSanad = $("#foundfishid"+selectedId).val();
        $("#fishshomareholder"+toBeReplaced).val($("#foundfishshomare"+selectedId).text());
        $("#fishbanknameholder"+toBeReplaced).val($("#foundfishnamebank"+selectedId).text());
        $("#fishbankshobeholder"+toBeReplaced).val($("#foundfishnameshobe"+selectedId).text());
        $("#fishmablaghholder"+toBeReplaced).val($("#foundfishmablagh"+selectedId).text());
        $("#fishtarikhholder"+toBeReplaced).val($("#foundfishtarikh"+selectedId).text());
        $("#fishtimeholder"+toBeReplaced).val($("#foundfishtime"+selectedId).text());
        $("#fishtozihatholder"+toBeReplaced).val($("#foundfishtozihat"+selectedId).text());
        $("#fishsanadholder"+toBeReplaced).val($("#foundfishsanad"+selectedId).text());



        var shomareFish = $("#foundfishshomare"+selectedId).text();
        var namebank = $("#foundfishnamebank"+selectedId).text();
        var shobe = $("#foundfishnameshobe"+selectedId).text();
        var mablagh = $("#foundfishmablagh"+selectedId).text();
        var tarikh = $("#foundfishtarikh"+selectedId).text();
        var time = $("#foundfishtime"+selectedId).text();
        var tozihat = $("#foundfishtozihat"+selectedId).text();
        var sanad = $("#foundfishsanad"+selectedId).text();
        var str = "<td class='ui-widget-content'>"+(parseInt(toBeReplaced)+1)+"</td><td class='ui-widget-content' id='testtesttest"+(parseInt(toBeReplaced))+"'><p style='color:red' class='td_search_final'>عدم تایید</p> </td><td class='ui-widget-content'>"+shomareFish+"</td><td class='ui-widget-content'>"+namebank+"</td><td class='ui-widget-content'>"+shobe+"</td><td class='ui-widget-content'>"+tozihat+"</td><td class='ui-widget-content'>"+sanad+"</td><td class='ui-widget-content'>"+mablagh+"</td><td class='ui-widget-content'>"+tarikh+"</td><td class='ui-widget-content'>"+time+"</td><td id='taeed_final_result"+(parseInt(toBeReplaced))+"' class='ui-widget-content'><a href='javascript:void(0);' onclick='ajaxlySubmitTagheereFish("+selectedForSanad+","+toBeReplaced+")' style='font-weight:bold;color:red;'>تایید تغییر</a>|";
        str+="<a href='javascript:void(0);' onclick='$(\".td_search\").css(\"font-weight\",\"normal\");$(this).css(\"font-weight\",\"bold\");startSearching("+(parseInt(toBeReplaced))+");'class='td_search'>جستجوی فیش</a>";
        str+= "</td>";
        $("#fishrow"+toBeReplaced+"").html(str);
        numRep++;

    }
    function startSearching(value){
        $("#bankinfo_tarikh").val($("#fishtarikhholder"+(value)).val());
        $("#bankinfo_kodeshobe").val($("#fishbankshobeholder"+(value)).val());
        $("#bankinfo_time").val($("#fishtimeholder"+(value)).val());
        $("#bankinfo_mablagh").val($("#fishmablaghholder"+(value)).val());
        $("#changeReader").val(value);
        $("#table4fishsearching").show();
        $("#resultholderForSearchedFishes").hide();

    }

    function taeedFish(){
        var taeedNashodeCount = 0;
        $(".td_search_final").each(function(i){
            if($(this).text() != "تایید")
            {
                taeedNashodeCount++;
            }
        });
        if(taeedNashodeCount == 0)
        {
        <c:if test="${pishnehad.state.id==100}">
            $("#transitionSelector").val('14');
            submitTransitionForm();
        </c:if>
        <c:if test="${pishnehad.state.id==40}">
            $("#transitionSelector").val('7');
            submitTransitionForm();
        </c:if>
        <c:if test="${pishnehad.state.id==60}">
            $("#transitionSelector").val('6');
            submitTransitionForm();
        </c:if>
        }
        else
        {
            alertMessage('برای ادامه تمامی فیش ها باید تایید شده باشند.');
        }
    }
    function ajaxlySubmitTagheereFish(val,valAddress){
        $.post("/ajaxFishSanadzani.action?credebit.id="+val,function(msg){

        });
//        $.ajaxSetup({ scriptCharset: "utf-8" ,contentType: "application/x-www-form-urlencoded; charset=UTF-8" });
        var pishId = $("#pishidholder"+valAddress).val();
        var fishId = $("#fishidholder"+valAddress).val();
        var shomare = $("#fishshomareholder"+valAddress).val();
        var bankName = $("#fishbanknameholder"+valAddress).val();
        var kodeShobe =$("#fishbankshobeholder"+valAddress).val();
        var mablagh = $("#fishmablaghholder"+valAddress).val();
        var tarikh = $("#fishtarikhholder"+valAddress).val();
        var time = $("#fishtimeholder"+valAddress).val();
        var tozihat = $("#foundfishtozihat"+valAddress).text();
        var sanad = $("#foundfishsanad"+valAddress).text();
    <%
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    %>

        $.post('/ajaxFishUpdate.action?pishnehad.id='+pishId+'&fish.kodeShobe='+kodeShobe+'&fish.id='+fishId+'&fish.mablagh='+mablagh+'&fish.shomare='+shomare+'&fish.tarikh='+tarikh+'&fish.time='+time+'&fish.bankName='+bankName+'&credebit.id='+val+'&credebit.daryafteFish.tozihat='+tozihat+'&credebit.credebit.daryafteFish.shomareSanadBank='+sanad,function(msg) {
//            changelist 91-06-23
//            alertMessage(msg);
            $("#taeed_final_result"+valAddress).html("<p style='font-weight:bold;color:green'>تایید شد</p>");
            $("#testtesttest"+valAddress).html("<p style='font-weight:bold;color:green'>تایید</p>");
            $("#resultholderForSearchedFishes").hide();
            $("#table4fishsearching").hide();
        });

    }
</script>

