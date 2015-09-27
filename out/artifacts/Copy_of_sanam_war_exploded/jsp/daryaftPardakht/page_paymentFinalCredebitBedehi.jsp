<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<s:actionmessage/>
    <div align="center" id="scs_div">
        <div  class="expandableTitleBar" id="expandableAsl" >
            <p class="heading" align="center" style="color: #FDB417; font-size: 15px">
                <b>&nbsp;</b>
            </p>
        </div>
        <table class="inputList" cellspacing="5">
            <tr>
                <td><br/></td>
            </tr>
            <tr>
                <td><br/></td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="authorityId" id="authority_id" value="${sanad.getKhateSanadSet[0].getEtebarCredebit().authorityId}" readonly="readonly"/>
                    <label>کد رهگیری پرداخت </label>&nbsp;
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="time" id="time" value="${sanad.getKhateSanadSet[0].getEtebarCredebit().dateFish} - ${sanad.getKhateSanadSet[0].getEtebarCredebit().timeFish}" readonly="readonly"/>
                    <label>زمان پرداخت</label>&nbsp;
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="amount" id="amount" value="${sanad.getKhateSanadSet[0].getEtebarCredebit().amount}"
                           readonly="readonly"/>
                    <label>مبلغ پرداختی (ریال) </label>&nbsp;
                </td>
            </tr>
            <tr>
                <td><br/></td>
            </tr>
        </table>

    </div>
    <input type="button" onclick="print();" value="چاپ رسید"/>
    <s:else>
        <input type="button"  onclick="window.location='/ePayment.action'" value="پرداخت مجدد"/>
    </s:else>
    <br/><br/><br/><br/>
<script type="text/javascript">
    function print()
    {

        var mywindow = window.open('', 'my div', 'height=400,width=500');
        mywindow.document.write('<html><head><title></title>');
        mywindow.document.write('</head><body>');
        mywindow.document.write('<p align="center" style="font-size: 15px;  color:green;"><b>');
        mywindow.document.write('پرداخت شما با موفقیت انجام شد');
        mywindow.document.write('</b></p>');
        mywindow.document.write('<table dir="rtl" align="center">');
        mywindow.document.write('<th style="background: #ffcc33" colspan="2">');
        mywindow.document.write('رسید پرداخت');
        mywindow.document.write('</th>');

        mywindow.document.write('<tr style="background: #dfeffc">');
        mywindow.document.write('<td>');
        mywindow.document.write('کد رهگیری پرداخت : ');
        mywindow.document.write('</td>');
        mywindow.document.write('<td dir="ltr">');
        mywindow.document.write($('#authority_id').val());
        mywindow.document.write('</td>');
        mywindow.document.write('</tr>');

        mywindow.document.write('<tr style="background: #dfeffc">');
        mywindow.document.write('<td>');
        mywindow.document.write('زمان پرداخت : ');
        mywindow.document.write('</td>');
        mywindow.document.write('<td dir="ltr">');
        mywindow.document.write($('#time').val());
        mywindow.document.write('</td>');
        mywindow.document.write('</tr>');

        mywindow.document.write('<tr style="background: #dfeffc">');
        mywindow.document.write('<td>');
        mywindow.document.write('مبلغ پرداختی : ');
        mywindow.document.write('</td>');
        mywindow.document.write('<td dir="ltr">');
        mywindow.document.write($('#amount').val());
        mywindow.document.write('</td>');
        mywindow.document.write('</tr>');
        mywindow.document.write('</table>');
        mywindow.print();
//        mywindow.close();
    }
</script>
</body>
</html>