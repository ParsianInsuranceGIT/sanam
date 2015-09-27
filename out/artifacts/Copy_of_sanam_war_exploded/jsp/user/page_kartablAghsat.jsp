<%--<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/jsp/taglibs.jsp" %>
<head>
    <title>صفحه اصلی</title>
</head>
<div align="center">
    <div style="border: 1px solid #999999; width:80%;  margin-top:5px; margin-bottom:10px; text-align: justify; padding: 15px;" id="err_payment">
        <img src="/img/delete.png" height="15px" width="15px" style="float: left;" onclick="$('#err_payment').fadeOut(50);" title="بستن"/>
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
    <div id="tabs-kartabl">
        <ul>
            <li id="litabs-bime"><a href="#tabs-bime">اقساط بیمه نامه</a></li>
            <li id="litabs-vam"/><a href="#tabs-vam">اقساط وام</a></li>
        </ul>


        <s:if test="pishnehad.bimename.ghestBandiList.size()>0">
        <div id="tabs-bime">
            <s:iterator value="pishnehad.bimename.ghestBandiList" status="stat" var="gh">
            <s:if test="%{!#gh.saleBimei.equals('')}">
            <table class="jtable">
                    <tr>
                        <td>سال بیمه ای <s:property value="%{#gh.saleBimeiInt+1}"/></td>
                    </tr>
                <s:iterator value="%{#gh.ghestList}" var="res" status="stat2">
                <s:if test="%{credebit!=null}">
                <s:if test="%{#stat2.index==0}">
                <tr>
                    <td>
                        <table class="jtable">
                            <tr>
                                <th>ردیف</th>
                                <th>شناسه پرداخت</th>
                                <th>مبلغ قسط - ریال</th>
                                <th>مبلغ وصولی - ریال</th>
                                <th>مبلغ مانده - ریال</th>
                                <th>تاریخ سررسید</th>
                                <th>وضعیت پرداخت</th>
                                <th>عملیات</th>
                                <th>جزئیات سند</th>
                            </tr>
                            </s:if>
                            <tr>
                                <s:if test="%{#res.isMoavagh()}">
                                    <c:set var="theColor" value="lightcoral"/>
                                </s:if>
                                <s:if test="%{#res.isNareside()}">
                                    <c:set var="theColor" value="lightblue"/>
                                </s:if>
                                <s:if test="%{!#res.getTarikhPardakht().equals('')}">
                                    <s:if test="%{#res.getCredebitList().get(0).getRemainingAmount_long()==0}">
                                        <c:set var="theColor" value="lightgreen"/>
                                    </s:if>
                                    <s:else>
                                        <c:set var="theColor" value="lightsalmon"/>
                                    </s:else>
                                </s:if>
                                <td style=" background:${theColor}"><s:property value="%{#stat2.index+1}"/></td>
                                <td style="background:${theColor}"><s:property
                                        value="credebit.shomareMoshtari"/></td>
                                <td style="background:${theColor}"><s:property value="credebit.amount"/></td>
                                <td style="background:${theColor}"><s:property value="mablaghVosuli"/></td>
                                <td style="background:${theColor}"><s:property
                                        value="credebit.remainingAmount"/></td>
                                <td style="background:${theColor}"><s:property value="sarresidDate"/></td>
                                <td style="background:${theColor}"><s:property
                                        value="credebit.vaziatPardakht"/></td>
                                <td style="background:${theColor}">
                                    <c:if test="${!res.credebit.remainingAmount.equals('0')}">

                                        <input type="button" value="پرداخت اینترنتی"  onclick="window.location='/pardakhtInternetiGhest?ghest.id=<s:property value="id"/>'" class="noAnyDisable"/>
                                    </c:if>
                                </td>
                                <td style="background:${theColor}">
                                    <input type="button" value="..." onclick="details(${credebit.id});"/>
                                </td>
                            </tr>
                            </s:if>

                            </s:iterator>
                            <s:if test="%{#gh.ghestList.size()>0}">
                        </table>
                    </td>
                </tr>
                </s:if>

            </table>
            </s:if>
            </s:iterator>

        </div>
        <div id="tabs-vam">
            <s:iterator value="pishnehad.bimename.ghestBandiList" status="stat" var="gh">
                <s:if test="%{!#gh.shomareVam.equals('')}">
                    <table class="jtable">
                        <tr>
                            <td>قسط وام به شماره <s:property value="%{#gh.shomareVam}"/></td>
                        </tr>
                        <s:iterator value="%{#gh.ghestList}" var="res" status="stat2">
                        <s:if test="%{credebit!=null}">
                        <s:if test="%{#stat2.index==0}">
                        <tr>
                            <td>
                                <table class="jtable">
                                    <tr>
                                        <th>ردیف</th>
                                        <th>شناسه پرداخت</th>
                                        <th>مبلغ قسط - ریال</th>
                                        <th>مبلغ وصولی - ریال</th>
                                        <th>مبلغ مانده - ریال</th>
                                        <th>تاریخ سررسید</th>
                                        <th>وضعیت پرداخت</th>
                                        <th>عملیات</th>
                                        <th>جزئیات سند</th>
                                    </tr>
                                    </s:if>
                                    <tr>
                                        <s:if test="%{#res.isMoavagh()}">
                                            <c:set var="theColor" value="lightcoral"/>
                                        </s:if>
                                        <s:if test="%{#res.isNareside()}">
                                            <c:set var="theColor" value="lightblue"/>
                                        </s:if>
                                        <s:if test="%{!#res.getTarikhPardakht().equals('')}">
                                            <s:if test="%{#res.getCredebitList().get(0).getRemainingAmount_long()==0}">
                                                <c:set var="theColor" value="lightgreen"/>
                                            </s:if>
                                            <s:else>
                                                <c:set var="theColor" value="lightsalmon"/>
                                            </s:else>
                                        </s:if>
                                        <td style="background:${theColor}"><s:property value="%{#stat2.index+1}"/></td>
                                        <td style="background:${theColor}"><s:property
                                                value="credebit.shomareMoshtari"/></td>
                                        <td style="background:${theColor}"><s:property value="credebit.amount"/></td>
                                        <td style="background:${theColor}"><s:property value="mablaghVosuli"/></td>
                                        <td style="background:${theColor}"><s:property
                                                value="credebit.remainingAmount"/></td>
                                        <td style="background:${theColor}"><s:property value="sarresidDate"/></td>
                                        <td style="background:${theColor}"><s:property
                                                value="credebit.vaziatPardakht"/></td>
                                        <td style="background:${theColor}">
                                            <c:if test="${!res.credebit.remainingAmount.equals('0')}">

                                                <input type="button" value="پرداخت اینترنتی" onclick="window.location='/pardakhtInternetiGhest?ghest.id=<s:property value="id"/>'"
                                                       class="noAnyDisable"/>
                                            </c:if>
                                        </td>
                                        <td style="background:${theColor}">
                                            <input type="button" value="..." onclick="details(${credebit.id});"/>
                                        </td>
                                    </tr>
                                    </s:if>

                                    </s:iterator>
                                    <s:if test="%{#gh.ghestList.size()>0}">
                                </table>
                            </td>
                        </tr>
                        </s:if>

                    </table>
                </s:if>
            </s:iterator>

        </div>


        </s:if>
        <s:else>
            بیمه نامه قسط بندی نشده است.
        </s:else>

    </div>          <br/>
    <table class="jtable">
        <tr>
            <th>
              اندوخته قطعی
            </th>
            <th>
                <s:property value="pishnehad.bimename.andukhteEmroozCalculate"/>
            </th>
        </tr>
    </table>
    <div style="float:right; width:110px;">

        <div align="center">راهنمای رنگ ها :</div>
        <hr align="right" width="110px"/>
        <div align="right" style="font-size:xx-small;">&nbsp;&nbsp;<label style="background:#ADD8E6;">
            &nbsp;&nbsp;&nbsp;</label>&nbsp;اقساط پرداخت نشده&nbsp;
        </div>
        <div align="right" style="font-size:xx-small;">&nbsp;&nbsp;<label style="background:lightgreen;">
            &nbsp;&nbsp;&nbsp;</label>&nbsp;اقساط پرداخت شده&nbsp;
        </div>
        <div align="right" style="font-size:xx-small;">&nbsp;&nbsp;<label style="background:lightsalmon;">
            &nbsp;&nbsp;&nbsp;</label>&nbsp;اقساط دارای مانده&nbsp;
        </div>
        <div align="right" style="font-size:xx-small;">&nbsp;&nbsp;<label style="background:lightcoral;">
            &nbsp;&nbsp;&nbsp;</label>&nbsp;اقساط معوق&nbsp;
        </div>



    </div>
    <br/>

</div>
<script type="text/javascript">
    $(function () {
        $("#tabs-kartabl").tabs({
            selected: 0
        });
    });
    function details(arg) {
        $('#detail_ghest').dialog
        (
                {
                    modal: true,
                    width: 650,
                    resizable: false,
                    closeText: "",
                    title: "جزئیات قسط",
                    buttons: { "بستن": function () {
                        $(this).dialog("close");
                    }}
                }
        );
        $.post
        (
                "/loadDetailsOfGhest.action?credebitId=" + arg,
                function (rslt) {
                    $('#detail_ghest').html(rslt);
                }
        );
    }
</script>
<div id="detail_ghest">

</div>