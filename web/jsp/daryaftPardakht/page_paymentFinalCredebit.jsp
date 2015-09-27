<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<s:actionmessage/>
<s:actionerror />

    <div align="center" id="scs_div">
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
                    <input type="text" name="authorityId" id="authority_id" value="${authorityId}" readonly="readonly"/>
                    <label>کد رهگیری پرداخت </label>&nbsp;
                </td>
            </tr>
       <%--     <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="time" id="time" value="${etebar.dateFish} - ${etebar.timeFish}" readonly="readonly"/>
                    <label>زمان پرداخت</label>&nbsp;
                </td>
            </tr>--%>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <c:if test="${bankResponse == '0'}">

                        <input type="text" name="amount" id="amount" value="${creebitAmount}"  readonly="readonly"/>

                    </c:if>
                    <c:if test="${bankResponse != '0'}">

                        <input type="text" name="amount" id="amount" value="0"   readonly="readonly"/>

                    </c:if>


                    <label>مبلغ پرداختی (ریال) </label>&nbsp;
                </td>
            </tr>
            <tr>
                <td><br/></td>
            </tr>
            <tr>
                <td>
                    <c:if test="${bankResponse == '0'}">
                        <input type="button" onclick="window.open('/printeResidePardakhteInterneti?sanadId=${sanadId}&credebitReport.id=${credebitId}');"  id="print_btn" value="چاپ رسید"/>

                    </c:if>

                    <input type="button"   onclick="window.location='/fin/listBedehiNamayande'" value="بازگشت"/>
                </td>
            </tr>
        </table>
        </div>




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