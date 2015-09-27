<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<html>
<head><title>پرداخت پایا</title></head>
<body>

<script type="text/javascript">
            $(".jtable th").each(function(){
                $(this).addClass("ui-state-default");
            });
    $(".jtable td").each(function(){
        $(this).addClass("ui-widget-content");
    });
    $(".jtable tr").hover(function(){
        //        $(this).children("td").addClass("ui-state-hover");
    },function(){
        //        $(this).children("td").removeClass("ui-state-hover");
    });
    $("input,textarea").each(function(){$(this).addClass("ui-state-default  ui-corner-all");});
    $("select").each(function(){$(this).addClass("ui-state-default  ui-corner-all");});
    $(".help, .comment").tipsy({gravity:'s'});
    //    $("form, .vld").validationEngine({promptPosition:"topLeft"});
    $('.dblRadio').buttonset();
    $(".tipsi").tipsy({gravity:'s'});


    $(".digitSeparators").each(function(){$(this).keyup();});
    $(".digitSeparatorsManfi").each(function(){$(this).keyup();});

    $(function(){
        $('#pishnehadsSelectAll').bind("change" ,function(){
            $('input[name="_chk"]').each(function(){
                $(this).attr('checked', $('#pishnehadsSelectAll').attr('checked'));
            })
        });
    });

    function validationShaba(){
        $.ajax({
            type: "POST",
            async : false,
            data: "identifier="+document.getElementById('identifier').value,
            url: "validationIdentifierShaba",
            success: function (msg) {
                if (msg == 'false'){
                    alertMessage("این بیمه نامه به دلیل داشتن بدهی، قادر به انجام پرداخت پایا  نمی باشد");
                    return;
                }
                else {
                    if (document.getElementById('shomareSheba').value != '' && document.getElementById('codeMelli').value)  {
                        confirmMessage("شما برای الحاقیه "+ document.getElementById('identifier').value + "با مبلغ " +  "  " + document.getElementById('remainingAmount').value + "  " + "  " + "و بیمه گذار  "+ "  " + document.getElementById('rcptName').value + "  " + "درخواست پرداخت از طریق پایا داده اید. آیا مطمئن هستید ؟","پرداخت پایا", function () {

                            if (document.getElementById('remainingAmount').value.replace(/,/g,'') > 50000000) {
                                alertMessage('مبلغ بیش از حد مجاز است');
                            }
                            else
                            {
                                $.ajax({
                                    type: "POST",
                                    async : false,
                                    data: "shomareShaba="+document.getElementById('shomareSheba').value,
                                    url: "validationShomareShaba",
                                    success: function (msg) {
                                        if (msg == 'true'){
                                            document.forms[0].submit();
                                        }
                                        else{
                                            alertMessage("شماره شبا اشتباه است");
                                        }

                                    }
                                });
                            }
                        });
                    } else {
                        alertMessage('شماره شبا و کد ملی را وارد کنید');
                    }
                }

            }
        });


    }
</script>
        <div class="content" style="" id="searchAslContent1">
            <form action="/fin/sabtPardakhtPayaForm" method="post">
                <s:actionmessage/>
                <s:actionerror/>
                <table dir="rtl" class="inputList" style="width: 30%;">
                    <tr>
                        <td>نامه بیمه گذار</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="${credebit.rcptName}" value="${credebit.rcptName}" id="rcptName" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>شماره بیمه نامه</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="${credebit.identifier}" value="${credebit.identifier}" id="identifier" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>تاریخ ایجاد</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="${credebit.createdDate}" value="${credebit.createdDate}" id="createdDate" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>مبلغ کل</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="${credebit.amount}" value="${credebit.amount}" id="amount" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>مبلغ مانده</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="${credebit.remainingAmount}" value="${credebit.remainingAmount}" id="remainingAmount" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>شماره شبا</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shomareShaba" id="shomareSheba" class="validate[required]" />
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
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="credebitId" value="${credebit.id}" id="credebitId" style="display: none;" />
                        </td>
                    </tr>
                </table>
                <input type="button" onclick="validationShaba()" value="ثبت"/>
            </form>
        </div>
</body>
</html>