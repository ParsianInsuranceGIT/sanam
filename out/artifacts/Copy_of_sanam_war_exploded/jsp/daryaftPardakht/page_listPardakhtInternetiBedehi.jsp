<%@ page import="com.bitarts.parsian.Core.Constant" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron0
  Date: Sep 6, 2011
  Time: 5:53:55 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<html>
<head><title>پرداخت اینترنتی بدهی</title></head>
<body>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>

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


    function displaytagform_bedehiSelection(formname, fields){
        var objfrm = document.forms[formname];
        if($(objfrm).serialize().indexOf('_chk') == -1){
            alertMessage("رکوردی انتخاب نشده است");
        }else{
            for (j=fields.length-1;j>=0;j--){
                $(objfrm).prepend("<input type='hidden' id='embedI_"+fields[j].f+"' name='"+fields[j].f+"' value='"+fields[j].v+"'></input>");
            }
            objfrm.action = "/fin/operationSelectBedehiNamayande";
            objfrm.submit();
        }
    }

    $(function(){
        $('#pishnehadsSelectAll').bind("change" ,function(){
            $('input[name="_chk"]').each(function(){
                $(this).attr('checked', $('#pishnehadsSelectAll').attr('checked'));
            })
        });
    });
</script>


    <div class="expandableTitleBar" id="expandableAsl">
        <p class="heading">
            <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                            جستجو
        </p>
        <div class="content" style="display:none;" id="searchAslContent1">
            <form action="/fin/listPardakhtInternetiBedehi" method="get">
                <table dir="rtl" class="inputList">
                    <tr>
                        <td>شماره بیمه نامه</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="identifier" id="identifier"/>
                        </td>
                        <td>نام بیمه گذار</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="rcptName" id="rcptName"/>
                        </td>
                    </tr>
                    <tr>
                        <td>از تاریخ سر رسید</td>
                        <td>
                            <input type="text" name="sarresidDateFrom" id="sarresidDateFrom" class="datePkr" readonly="true"/>
                        </td>
                        <td>تا تاریخ سر رسید</td>
                        <td>
                            <input type="text" name="sarresidDateTo" id="sarresidDateTo" class="datePkr" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>تاریخ ایجاد</td>
                        <td>
                            <input type="text" name="createdDateFrom" id="createdDateFrom" class="datePkr" readonly="true"/>
                        </td>
                        <td>تاریخ ایجاد</td>
                        <td>
                            <input type="text" name="createdDateTo" id="createdDateTo" class="datePkr" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>مبلغ کل</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="amount" id="amount"  class="digitSeparators" />
                        </td>
                        <td>مبلغ دریافت شده</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="paidReceivedAmount" id="paidReceivedAmount" class="digitSeparators"/>
                        </td>
                    </tr>
                    <tr>
                        <td>وضعیت سند</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:select list="#{'':'تمام موارد','SANAD_KHORDE':'سند خورده','SANAD_NA_KHORDE':'سند نخورده'}" name="statusFarsi" id="statusFarsi" label="" key="statusFarsi" theme="simple"/>
                        </td>
                        <td><label>نمايندگي</label></td>
                        <td>
                            <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('namayandeId','namayandeName', '');" style="float:left;" title="جستجو"></span>
                            <input type="hidden" name="namayandeId" id="namayandeId" />
                            <input type="text" name="namayandeName" id="namayandeName"  readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>مبلغ مانده</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="remainingAmount" id="remainingAmount" class="digitSeparators"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="3">
                            <script type="text/javascript">
                                function clearSeachFrom()
                                {
                                    $('#identifier').val('');
                                    $('#shomareMoshtari').val('');
                                    $('#rcptName').val('');
                                    $('#sarresidDateFrom').val('');
                                    $('#sarresidDateTo').val('');
                                    $('#createdDateFrom').val('');
                                    $('#createdDateTo').val('');
                                    $('#amount').val('');
                                    $('#paidReceivedAmount').val('');
                                    $('#remainingAmount').val('');
                                    $('#namayandeName').val('');
                                    $('#namayandeId').val('');
                                    $('#bankName').val('');
                                }

                                function printFish(fishId,bankFish,credebitTypeFarsi){
                                    if (credebitTypeFarsi == "پرداخت شناسه دار"){

                                            if (bankFish.contains('2177777733') || bankFish.contains('4757575763'))
                                                window.open('/fin/printeFishBankMellat?credebitReport.id='+fishId);
                                            else if (bankFish.contains('17038494') || bankFish.contains('17123130'))
                                                window.open('/fin/printeFishBankTejarat?credebitReport.id='+fishId);
                                            else if (bankFish.contains('0200234164006') || bankFish.contains('81011989'))
                                                window.open('/fin/printeFishBankParsian?credebitReport.id='+fishId);
                                            else
                                                alertMessage("قابلیت پرینت برای این رکورد وجود ندارد");

                                    }else{
                                        alertMessage("قابلیت پرینت برای این رکورد وجود ندارد");
                                    }
                                }
                            </script>
                            <span class="noThing">&nbsp;</span>
                            <input type="submit" value="جستجو" theme="simple"/>
                            <span class="noThing"></span>
                            <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom()"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div style="overflow: auto;">
        <form name="pishListFormOfRKM" action="?" method="POST">
            <display:table export="true" id="credebitListTbl" uid="row" name="credebitListPaginated"
                           sort="external" htmlId="credebitListPaginated"
                           decorator="org.displaytag.decorator.CheckboxTableDecorator"
                           partialList="true"
                           size="${credebitListPaginated.fullListSize}"
                           pagesize="${credebitListPaginated.objectsPerPage}"
                           requestURI="" clearStatus="true" keepStatus="false"
                           excludedParams="selectedTab decorator pishnehadSearch* _chk" style="width: 100%; margin: 0 auto;">
                <display:column property="checkbox" title="<input id='pishnehadsSelectAll' type='checkbox' title='انتخاب/حذف همه'/>" />
                <display:column title="ردیف" style="">${row_rowNum+((credebitListPaginated.pageNumber-1)*credebitListPaginated.objectsPerPage)}</display:column>
                <display:column property="identifier" title="شماره بیمه نامه" style=""/>
                <display:column property="rcptName" title="نام بیمه گذار" style="" />
                <display:column property="namayandeName" title="نام نماینده" style="" />
                <display:column property="sarresidDate" title="تاریخ سررسید" style=""/>
                <display:column property="createdDate" title="تاریخ ایجاد" style=""/>
                <display:column property="amount" title="مبلغ کل" style=""/>
                <display:column property="paidReceivedAmountFormat" title="مبلغ دریافت شده" style=""/>
                <display:column property="remainingAmount" title="مبلغ مانده" style=""/>
                <display:column property="credebitTypeFarsi" title="نوع" style=""/>
                <display:column property="statusFarsi" title="وضعیت سند" style=""/>
                <display:column property="vosoulStateFarsi" title="وضعیت وصول" style=""/>
            </display:table>
        </form>
    </div>

<br>
<sec:authorize ifAllGranted="ROLE_MALI_PARDAKHT_INTERNETI">
    <table  style="width: 100%">
        <tr>
            <td style="width: 20%"></td>
            <td style="width: 60%">
                <div>
                    <table class="inputList" cellspacing="1" cellpadding="5" style="border:1px solid black;text-align: center" >
                        <tr>
                            <td>
                                <label>با انتخاب بدهی ها از جدول بالا و انتخاب حساب از منوی زیر، می توانید بدهی های خود را به صورت اینترنتی پرداخت و سند بزنید</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <select name="credebit.bankName" id="bankMaghsadAutomobil" style="width: 300px;">
                                    <option value="<%=Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD%>"><%=Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD%></option>
                                </select>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="button" onclick="javascript:displaytagform_bedehiSelection('pishListFormOfRKM',[{f:'par',v:['aa%22az']}])" value="ارسال به پرداخت اینترنتی"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
            <td style="width: 20%"></td>
        </tr>
    </table>

</sec:authorize>

<br>
    <sec:authorize ifNotGranted="ROLE_KARBAR_MALI">
        <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
    </sec:authorize>
    <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
        <input type="button" onclick="window.location='/loginUser.action'" value="بازگشت"/>
    </sec:authorize>


</body>
</html>