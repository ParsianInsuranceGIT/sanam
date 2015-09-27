<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<script type="text/javascript">
    function loadDebitInfo()
    {
        if ($.validationEngine.submitValidation($('#input_user'))==false){
        $.post
        (
            "/debitSearch.action?credebit.shomareMoshtari=" + $('#code_moshtari').val(),
            function(rslt)
            {
                var isErr=rslt.split('^^')[1];
                if(isErr=="true")
                {
                    $('#p').fadeIn(1000);
                    $('#p').html('* اطلاعاتی با شناسه پرداخت وارد شده پیدا نشد.');
                }
                else
                {
                    $('#input_user').fadeOut(1000,function(){ $(this).remove();  $('#rslt').fadeIn(1000); });
                    $('#crdbt_id').val(rslt.split('^^')[2]);
                    $('#mablagh').val(rslt.split('^^')[3]);
                    $('#mande').val(rslt.split('^^')[4]);
                    $('#bime_gozar').val(rslt.split('^^')[5]);
                    $('#sarresid_date').val(rslt.split('^^')[6]);
                    $('#type').val(rslt.split('^^')[7]);
                    $('#nmbr_cstmr').val(rslt.split('^^')[8]);
                }
            }
        );}
    }
</script>
<div  class="expandableTitleBar" id="expandableAsl" >
    <p class="heading" align="center" style="color: #FDB417; font-size: 15px">
        <b>&nbsp;</b>
    </p>
           <p style="color:#cd0a0a; font-size: 11px">
            <b style="color:#cd0a0a; font-size: 15px;">
*
            </b>
    سقف پرداخت اینترنتی مبلغ 200,000,000 ریال می باشد؛ لذا در صورتیکه مبلغ باقی مانده بدهی بیش از این مقدار است، می توانید بیشتر از یک بار پرداخت اینترنتی انجام دهید.
          </p>
</div>

<div id="input_user">
    <br/><br/>
    <label for="code_moshtari">کد مشتری</label>
    <input type="text" name="codeMoshtari" id="code_moshtari" class="validate[required]"/>
    <input type="button" onclick="loadDebitInfo();"  value="جستجو"/>
    <br/><br/>
    <p style="display: none; color: #cd0a0a; font-style: inherit; text-align: center;" align="center" id="p"></p>
</div>

<div id="rslt" style="display: none">
    <form action="/paymentDebit.action" method="POST" name="pymnt_frm">
        <table class="inputList" cellspacing="5" width="90%">
            <tr>
                <td><br/></td>
                <td><br/></td>
            </tr>
            <tr>
                <input type="hidden" name="credebit.id" id="crdbt_id"/>
                <td>
                    <span class="noThing"></span>
                    <input type="text" id="mablagh" readonly="readonly"/>
                    <label>مقدار بدهی (ریال)</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" id="mande" readonly="readonly"/>
                    <label>باقی مانده بدهی (ریال)</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" id="bime_gozar" readonly="readonly"/>
                    <label>بیمه گذار</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" id="sarresid_date" readonly="readonly"/>
                    <label>تاریخ سر رسید </label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" id="type" readonly="readonly"/>
                    <label>نوع بدهی </label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" id="nmbr_cstmr" readonly="readonly"/>
                    <label>شناسه پرداخت </label>
                </td>
            </tr>
            <tr>
                <td><br/></td>
                <td><br/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <span class="noThing"></span>
                    <input type="submit" value="تایید و ادامه"/>
                    <input type="button" onclick="window.location='/ePayment.action'" value="انصراف" />
                </td>
            </tr>
        </table>
    </form>
</div>
<div style="border-style: inherit; border-width:1px; border-color: #999999; width:70%;  margin-top:50px; margin-bottom:100px; margin-right: 15%; text-align: justify; padding: 15px;">

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
    <p dir="LTR" style="text-align: left;color: #cd0a0a;"><b>I understand the risks --> Add Exception --> Confirm</b></p>
</div>
</body>
</html>