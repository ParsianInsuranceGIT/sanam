<%@ page import="com.bitarts.parsian.model.bimename.Bimename" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:actionmessage/>

<form id="havaleKhesarataction" name="formElhaghie" action="havaleKhesarataction" method="post"
      accept-charset="UTF-8">

<input type="hidden" name="pishnehad.id" value="${pishnehad.id}"/>

<script>


    function validationShaba(){
        if (document.getElementById('shomareSheba').value != '')  {
            $.ajax({
                type: "POST",
                async : false,
                data: "shomareShaba="+document.getElementById('shomareSheba').value+"&codeMelli="+document.getElementById('codeMelli').value+"&darkhastBazkharid.id=" +${darkhastBazkharid.id}
                       +"&shomareShenasname="+document.getElementById('shomareShenasname').value+"&telephoneHamrah=" + document.getElementById('telephoneHamrah').value ,
                url: "validationShomareShabaAjax",
                success: function (msg) {
                    if (msg == 'true'){
                        //document.forms[0].submit();
//                        $("#havaleKhesarataction").submit();
                        $('#send_request_btn2').attr('disabled', 'disabled');
                        alertMessage("درخواست با موفقیت انجام شد.");

                    }
                    else{
                        alertMessage("شماره شبا اشتباه است");
                    }

                }
            });
        } else {
            alertMessage('شماره شبا و کد ملی را وارد کنید');
        }
    }


    function validationCheck(){
        $.ajax({
            type: "POST",
            async : false,
            data: "hesabBanki="+document.getElementById('hesabBanki').value+"tarikhSarresid="+document.getElementById('tarikhSarresid').value+
                    "shomareCheck="+document.getElementById('shomareCheck').value+"seriCheck="+document.getElementById('seriCheck').value+"&pishnehad.id="+${pishnehad.id},
            url: "validationCheckAjax",
            success: function (msg) {
                if (msg == 'true'){
                    alertMessage("درخواست با موفقیت انجام شد.")
                }
                else{
                    alertMessage("شماره شبا اشتباه است");
                }

            }
        });
    }


    function showHideField(num){
        if (num == 1){
            $("#pardakhtShaba").show();
            $("#pardakhtCheck").hide();
        } else {
            $("#pardakhtShaba").hide();
            $("#pardakhtCheck").show();
        }

    }


</script>

<table  cellpadding="3" cellspacing="3" border="0"
        style="margin-left:auto;margin-right:auto;align:center;direction: rtl;">

    <tr>
        <td>
            نحوه پرداخت
        </td>
        <td>
            <select style="float:left; margin-left: 10px;" id="havaleKhesaratSelect" name="transitionSelector3">
                <option  onclick="showHideField(1)" value='1'>از طریق شبا</option>
                <%--<option  onclick="showHideField(2)" value='2'>از طریق چک</option>--%>
            </select>
        </td>
    </tr>
    <tr>

        <table id="pardakhtShaba" align="center">
            <tr>
                <td>شماره شبا</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                        <input type="text" name="shomareShaba" id="shomareSheba" class="validate[required,funcCall[shaba]]"/>
                </td>
            </tr>
            <tr>
                <td>شماره شناسنامه</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="shomareShenasname" id="shomareShenasname" class="validate[required]" />
                </td>
            </tr>
            <tr>
                <td>تلفن همراه</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="telephoneHamrah" id="telephoneHamrah" class="validate[required]" />
                </td>
            </tr>
            <tr>
                <td>کد ملی</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                    <input type="text" name="codeMelli" id="codeMelli" class="validate[required]" />
                </td>
            </tr>
            <tr>
                <td>مبلغ کل</td>
                <td>
                    <span class="noThing">&nbsp;</span>
                        <input type="text" name="finalAmount" value="${darkhastBazkharid.finalAmountKhesarat}" id="amount" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <span class="noThing">&nbsp;</span>
                    <input type="button" style="float:left;margin-left:2px" id="send_request_btn2" value="ارسال" onclick="validationShaba();"
                        <c:if test="${darkhastBazkharid.credebitKhesarat!=null}">disabled="disabled"</c:if>
                        /></td>
            </tr>
        </table>
    </tr>
    <tr>
        <table id="pardakhtCheck">
            <tr>
                <td>شماره حساب</td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="shomareHesab" id="hesabBanki" class="validate[required]">
                </td>
                <td>شماره چک</td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="shomareCheck" id="shomareCheck" class="validate[required]">
                </td>
                <td>در وجه </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="shomareCheck" id="darVajh" class="validate[required]">
                </td>
            </tr>
            <tr>
                <td>تاریخ سررسید</td>
                <td>
                    <input type="text" name="tarikhSarresid" id="tarikhSarresid" class="datePkr validate[required]">
                </td>
                <td>سری</td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="seriCheck" id="seriCheck" class="">
                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <span class="noThing"></span>
                    <input type="button" style="float:left;margin-left:2px" id="send_request_btn3" value="ارسال" onclick="validationCheck();"/>
                </td>
            </tr>
        </table>
    </tr>

</table>

<script>
    showHideField(1);
</script>

</form>


