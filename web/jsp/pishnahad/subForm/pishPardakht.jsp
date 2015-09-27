<%@ page import="com.bitarts.common.util.StringUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.bitarts.parsian.model.pishnahad.Fish" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    long pishValue = 0;
    if(pishnehadRun!=null){
                        long bazvalue = 0;
                        long baztafazol = 0;
                        long bazhagheBime = 0;
                        List<Fish> fishs = pishnehadRun.getFishs();
                        if(fishs != null){
                            for (Fish fish : fishs) {
                                if(!fish.getFound().equalsIgnoreCase("false")){
                                    String mablaghBaz = fish.getMablagh();
                                    mablaghBaz = mablaghBaz.replaceAll(",","");
                                    bazvalue = bazvalue+Long.parseLong(mablaghBaz);
                                }
                            }
                            if(pishnehadRun.getState().getId() == 90)
                            {
                                bazhagheBime = Long.parseLong(pishnehadRun.getEstelam().getHagh_bime_pardakhti().replaceAll(",","")) + Long.parseLong(pishnehadRun.getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",",""));
                            }
                            else if(Long.parseLong(pishnehadRun.getEstelam().getHagh_bime_pardakhti().replaceAll(",","")) == 0)
                            {
                                bazhagheBime = 500000;
                            }
                            else
                            {
                                bazhagheBime = Long.parseLong(pishnehadRun.getEstelam().getHagh_bime_pardakhti().replaceAll(",",""));
                            }
                            baztafazol = bazhagheBime - bazvalue;
                            if(baztafazol == 0) baztafazol = 500000;
							pishValue = baztafazol;
						}else{
							if(pishnehadRun.getState().getId() == 90)
                            {
                                pishValue = Long.parseLong(pishnehadRun.getEstelam().getHagh_bime_pardakhti().replaceAll(",","")) + Long.parseLong(pishnehadRun.getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",",""));
                            }
                            else if(Integer.valueOf(StringUtil.removeNoneDigits(pishnehadRun.getEstelam().getHagh_bime_pardakhti())) == 0){
								pishValue = 500000;
							}
							else if(Integer.valueOf(StringUtil.removeNoneDigits(pishnehadRun.getEstelam().getHagh_bime_pardakhti())) < 2000000){
								pishValue = Integer.valueOf(StringUtil.removeNoneDigits(pishnehadRun.getEstelam().getHagh_bime_pardakhti()));
							}else{
								pishValue = 2000000;
							}
                        }
        if(pishValue < 0) pishValue = 0;
    }
%>
<c:if test="${errorsMap['maxMablaghInterneti']!=null}">
<table width="60%" style="border-spacing:1px;margin-left:auto;margin-right:auto;">
    <tr>
        <td>
            <p style="color:red;"><c:out value="${errorsMap['maxMablaghInterneti']}"/></p>
        </td>
    </tr>
</table>
</c:if>


<c:if test="${messagesMap['pardakht.success']!=null}">
<table width="60%" style="border-spacing:1px;margin-left:auto;margin-right:auto;">
    <tr>
        <td>
            <p style="color:green;">${messagesMap['pardakht.success']}</p>
        </td>
    </tr>
</table>
</c:if>
<c:set var="pishpardakhtValue" value="<%=pishValue%>"/>
<table width="37%" dir="rtl" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <tr>
        <td>
            &nbsp;
        </td>
    </tr>
    <tr>
        <td>
            <s:actionmessage/>
        </td>
    </tr>
    <tr>
        <td>
            <c:if test="${shenaseIjadShode != null}">
                <b><p style="color: green;">شناسه پرداخت شما:</p></b> &nbsp; <p style="color: green;"><c:out value="${shenaseIjadShode}"/> </p>
            </c:if>
        </td>
    </tr>
    <c:if test="${errorPardakht == true}">
        <tr>
            <td>
                <label style="color:red">در پرداخت اینترنتی اشکالی ایجاد شده است. لطفا با مدیر سیستم تماس بگیرید.</label>
            </td>
        </tr>
    </c:if>
    <c:if test="${errorPardakhtShenasedar == true}">
        <tr>
            <td>
                <label style="color:red">در پرداخت شناسه دار اشکالی ایجاد شده است. لطفا با مدیر سیستم تماس بگیرید.</label>
            </td>
        </tr>
    </c:if>
</table>
<table border="0" cellspacing="5" cellpadding="1" style="width:780px;text-align:right;">
    <tr>
        <td width="25%">
            نحوه ی پرداخت:&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <td width="25%">
            <input type="radio" name="typeChooser" value="fish" checked="checked" onclick="showFishHideOthers();"/><label>فیش</label>&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <td width="25%">
            <input type="radio" name="typeChooser" value="interneti" onclick="showInternetHideOthers();" id="internetiButton"/><label>پرداخت اینترنتی</label>&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <%--<td width="25%">--%>
            <%--<input type="radio" name="typeChooser" value="shenasedar" onclick="showShenasedarHideOthers();"/><label>شناسه دار</label>&nbsp;&nbsp;&nbsp;&nbsp;--%>
        <%--</td>--%>
    </tr>
</table>

<br/>
<br/>
<br/>
<%--<form></form>--%>
<%--<form></form>--%>

<form id="form4fishforshenasedar" name="shenasedar" method="post" action="/pardakhtShenasedar.action">
    <input type="hidden" name="pishnehad.id" value="<%=pishnehadRun.getId()%>"/>
    <table id="table4shenase" width="37%" dir="rtl" cellpadding="0" cellspacing="0" style="display:none; border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tbody>
        <tr>
            <td>
                مبلغ پرداخت (ریال)&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="fish.mablagh" id="fish_mablagh_shenasedar3" value="${pishpardakhtValue}" />
            </td>
        </tr>
        <tr>
            <td>
                <input type="hidden" id="shenasedar_fish_shomare" name="fish.shomare"/>

            </td>
        </tr>
        <tr>
            <td>
                تاریخ پرداخت&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <%--<input type="text" id="EKASB_tarikhfishfish" name="fish.tarikh" class="validate[required,custom[date]] text-input datePkr">--%>
                <input type="text" id="fish_tarikh_shenasedar" name="fish.tarikh" class="datePkr" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="ثبت پرداخت" />
            </td>
        </tr>
        </tbody>
    </table>
</form>

<form id="form4internet" name="form1" method="post" action="/pardakhtInterneti.action">
    <input type="hidden" name="pishnehad.id" value="<%=pishnehadRun.getId()%>"/>
    <table id="table4internet" style="margin-left: auto;margin-right: auto; display:none;">
        <tr>
            <td>
                مبلغ فیش (ریال)&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="fish.mablagh" class="digitSeparators" id="fish_mablagh_shenasedar2" value="${pishpardakhtValue}" />

            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="پرداخت"/>
            </td>
        </tr>
    </table>
    <div  style="display: none; border: 1px solid #999999; margin-right: 10%; width:80%; margin-top:5px; margin-bottom:10px; text-align: justify; padding: 15px;"
         id="err_pish_payment">
        <img src="/img/delete.png" height="15px" width="15px" style="float: left;"
             onclick="$('#err_pish_payment').fadeOut(50);" title="بستن"/>
    <span dir="rtl" style="text-align: justify;color: #cd0a0a;">
    <b>
        خواهشمند است قبل از پرداخت اقساط خود از طریق اینترنت (پرداخت آنلاین) موارد ذيل را بدقت مطالعه فرماييد:
    </b>
    </span><br/><br/><br/>

    <span dir="rtl" style="text-align: justify; ">
*        مشترک گرامی، شما می توانيد با  تمامی کارتهای اعبتاری بانکهای عضو شبکه شتاب و از طريق درگاه اینترنتی سیستم عمر، اقساط خود را پرداخت نماييد.
    </span><br/><br/>
    <span dir="rtl" style="text-align: justify;">
* توجه فرمایید، جهت بستن صفحه بانک، حتما از كليدهاي تعريف شده استفاده فرموده و جداً از بستن صفحه بانک بصورت مستقيم خودداري فرماييد.
    </span><br/><br/>
    <span dir="rtl" style="text-align: justify;">
*     با توجه به محدوديت در زمان باز بودن (مشاهده و ثبت) صفحه بانک بدلايل امنيتي خواهشمند است در سريعترين زمان ممكن
    اقدام به تكميل فيلدهاي از پيش تعيين شده فرماييد.
    </span><br/><br/>
    <span dir="rtl" style="text-align: right;">
*     درصورتيكه پس از مراجعه به صفحه اينترنتي بانك و تكميل فيلدهاي از پيش تعيين شده، پيغام خطا و يا عدم دريافت صورت
    خلاصه وضعيت پرداخت را مشاهده نموديد، جهت جلوگيري از كسر مجدد وجه از حساب بانكي خود، اقدام به پرداخت دوباره نفرمایید.
    </span><br/><br/>
    <span dir="rtl" style="text-align: right;">
*     توجه فرمایید هنگام پرداخت اینترنتی اقساط در صورت دیدن صفحه خطا مراحل زیر را که در همان صفحه باز شده است،
    انجام دهید:
</span><br/>

        <p dir="LTR" style="text-align: left;color: #cd0a0a;"><b>I understand the risks --> Add Exception -->
            Confirm</b></p>
    </div>
</form>


<form id="fishformfishfinal" name="form4dish" action="/pardakhtBaFishMakeFinal.action" method="post" accept-charset="UTF-8">
    <input type="hidden" name="pishnehadId" value="<%=pishnehadRun.getId()%>"/>
    <input type="hidden" id="transid" name="transitionId" value=""/>
    <input type="hidden" name="logmessage" value="test"/>
    <table id="table4fish" width="70%" dir="rtl" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tbody>
        <tr>
            <td>
                مبلغ فیش (ریال)&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="fish.mablagh" class="digitSeparators" id="fish_mablagh_shenasedar" value="${pishpardakhtValue}"/>

            </td>
            <td>
                <p style="color:red;">
                    <c:if test="${errorsMap['emptyMablagh']!=null}">
                        <c:out value="${errorsMap['emptyMablagh']}"/>
                    </c:if>
                    <c:if test="${errorsMap['notdigitMablagh']!=null}">
                        <c:out value="${errorsMap['notdigitMablagh']}"/>
                    </c:if>
                    <c:if test="${errorsMap['duplicateFish']!=null}">
                        <c:out value="${errorsMap['duplicateFish']}"/>
                    </c:if>
                </p>
            </td>
        </tr>
        <tr>
            <td>
                شماره فیش&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="fish.shomare" class="validate[custom[onlyNumber]]" id="fish_shomare_fish" value="${fish.shomare}" />
            </td>
            <td>
                <p style="color:red;">
                    از درج صفر قبل از شماره فيش، مميز و عدد قبل از مميز خودداري نماييد
                    <c:if test="${errorsMap['emptyShomare']!=null}">
                        <c:out value="${errorsMap['emptyShomare']}"/>
                    </c:if>
                    <c:if test="${errorsMap['notdigitShomare']!=null}">
                        <c:out value="${errorsMap['notdigitShomare']}"/>
                    </c:if>
                </p>
            </td>
        </tr>
        <tr>
            <td>
                نام بانک&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <span class="noThing"></span>
                <%--<input type="text" name="fish.bankName" id="fish_bankname_fish" value="${fish.bankName}"/>--%>
                <select name="fish.bankName" id="fish_bankname_fish">
                    <option ${fish.bankName == 'پارسیان' ? 'selected=selected' : '' } >پارسیان</option>
                    <option ${fish.bankName == 'تجارت' ? 'selected=selected' : ''} >تجارت</option>
                </select>
            </td>
            <td>
                <p style="color:red;">
                    <c:if test="${errorsMap['emptyBankName']!=null}">
                        <c:out value="${errorsMap['emptyBankName']}"/>
                    </c:if>
                    <c:if test="${errorsMap['notwordBankName']!=null}">
                        <c:out value="${errorsMap['notwordBankName']}"/>
                    </c:if>
                </p>
            </td>
        </tr>
        <tr>
            <td>
                کد شعبه&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="fish.kodeShobe" class="validate[custom[onlyNumber]]" id="fish_kodeshobe_fish" value="${fish.kodeShobe}"/>
            </td>
            <td>
                <p style="color:red;">
                    <c:if test="${errorsMap['emptyKodeShobe']!=null}">
                        <c:out value="${errorsMap['emptyKodeShobe']}"/>
                    </c:if>
                    <c:if test="${errorsMap['notdigitKodeShobe']!=null}">
                        <c:out value="${errorsMap['notdigitKodeShobe']}"/>
                    </c:if>
                </p>
            </td>
        </tr>
        <tr>
            <td>
                تاریخ فیش&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td width="40%">
                <input type="text" name="fish.tarikh" id="fish_tarikh_fish" class="datePkr validate[custom[date]]" value="${fish.tarikh}"/>
            </td>
            <td>
                <p style="color:red;">
                    <c:if test="${errorsMap['emptyTarikh']!=null}">
                        <c:out value="${errorsMap['emptyTarikh']}"/>
                    </c:if>
                </p>
            </td>
        </tr>
        <tr>
            <td>
                دقیقه و ثانیه پرداخت فيش&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="fish.time" id="fish_time_fish" class="validate[custom[time]]" value="${fish.time}"/>
            </td>
            <td>
                <p style="color:red;">
                    <c:if test="${errorsMap['emptyTime']!=null}">
                        <c:out value="${errorsMap['emptyTime']}"/>
                    </c:if>
                    <c:if test="${errorsMap['notTime']!=null}">
                        <c:out value="${errorsMap['notTime']}"/>
                    </c:if>

                </p>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <p style="color:red;">
                صرفاً دقيقه و ثانيه پرداخت فيش را مطابق با پرفراژ بانكي‌ (مندرج در سمت چپ تاريخ ثبت بانكي) در فرمت mm:ss درج نماييد. نمونه: 03:50
                </p>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" value="ثبت پرداخت" onclick="fillInputFishFinal();"/>
            </td>
        </tr>

        </tbody>
    </table>

</form>

<div id="shenasedar_id_holder">

</div>

<script type="text/javascript">
    function showFishHideOthers(){
        $("#table4fish").show();
        $("#table4internet").hide();
        $("#table4shenase").hide();
        $("#err_pish_payment").hide();
    }
    function showInternetHideOthers(){
        $("#table4fish").hide();
        $("#table4internet").show();
        $("#table4shenase").hide();
        $("#err_pish_payment").show();

    }

    $(document).ready(function(){
        <c:if test="${errorsMap['maxMablaghInterneti']!=null}">
            $('#internetiButton').click();
        </c:if>
    });

    function showShenasedarHideOthers(){


        $("#table4fish").hide();
        $("#table4internet").hide();
        $("#table4shenase").show();
        $.post('/getOrderIdForShenase.action',function(msg) {
            $("#shenasedar_id_holder").html(msg);
            $("#shenasedar_fish_shomare").val($("#shenase_ijad_shode").val());
        });
    }
    function fillInputFishFinal(){
        var allFieldsEntered = true;
        if($("#fish_mablagh_shenasedar").val().trim().length < 5)
        {
            allFieldsEntered = false;
        }
        if($("#fish_shomare_fish").val().trim().length < 1)
        {
            allFieldsEntered = false;
        }
        if($("#fish_kodeshobe_fish").val().trim().length < 1)
        {
            allFieldsEntered = false;
        }
        if($("#fish_tarikh_fish").val().trim().length < 5)
        {
            allFieldsEntered = false;
        }
        if($("#fish_time_fish").val().trim().length < 4)
        {
            allFieldsEntered = false;
        }
        if(!allFieldsEntered)
        {
            alertMessage("لطفا تمامی فیلدهای مربوط به فیش را وارد نمایید.");
        }
        else
        {
            $("#transid").val($("#transitionSelector").val());
            $("#fishformfishfinal").submit();
        }
    }
</script>

