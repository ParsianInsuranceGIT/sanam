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
            <form action="/fin/listPardakhtPaya" method="post">
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
            <s:actionmessage/>
            <s:actionerror/>
            <display:table export="true" id="credebitListTbl" uid="row" name="credebitListPaginated"
                           sort="external" htmlId="credebitListPaginated"
                           decorator="org.displaytag.decorator.CheckboxTableDecorator"
                           partialList="true"
                           size="${credebitListPaginated.fullListSize}"
                           pagesize="${credebitListPaginated.objectsPerPage}"
                           requestURI="" clearStatus="true" keepStatus="false"
                           excludedParams="selectedTab decorator pishnehadSearch* _chk" style="width: 100%; margin: 0 auto;">

                <display:column title="ردیف" style="${css}">${row_rowNum+((credebitListPaginated.pageNumber-1)*credebitListPaginated.objectsPerPage)}</display:column>
                <display:column property="identifier" title="شماره بیمه نامه" style="${css}"/>
                <display:column property="rcptName" title="نام بیمه گذار" style="${css}" />
                <display:column property="namayandeName" title="نام نماینده" style="${css}" />
                <display:column property="sarresidDate" title="تاریخ سررسید" style="${css}"/>
                <display:column property="createdDate" title="تاریخ ایجاد" style="${css}"/>
                <display:column property="amount" title="مبلغ کل" style="${css}"/>
                <display:column property="paidReceivedAmountFormat" title="مبلغ دریافت شده" style="${css}"/>
                <display:column property="remainingAmount" title="مبلغ مانده" style="${css}"/>
                <display:column property="credebitTypeFarsi" title="نوع" style="${css}"/>
                <display:column property="statusFarsi" title="وضعیت سند" style="${css}"/>
                <display:column property="vosoulStateFarsi" title="وضعیت وصول" style="${css}"/>
                <display:column style="${css}" title="عملیات" >
                    <input type="button" onclick="window.location='/fin/viewPardakhtPayaForm?credebit.id=${row.id}'" value="پرداخت پایا"/>
                </display:column>
            </display:table>
        </form>
    </div>

    <sec:authorize ifNotGranted="ROLE_KARBAR_MALI">
        <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
    </sec:authorize>
    <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
        <input type="button" onclick="window.location='/loginUser.action'" value="بازگشت"/>
    </sec:authorize>

</body>
</html>