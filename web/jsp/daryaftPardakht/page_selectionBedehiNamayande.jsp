<%@ page import="com.bitarts.parsian.Core.Constant" %>
<%--
  Created by IntelliJ IDEA.
  User: a.sabzechian
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
<head><title>بدهای های انتخاب شده</title>
    <style>
        h4 {
            width: 400px;
            height: 20px;
            background: #ffb6c1;
            -webkit-animation: myfirst 10s;
            animation: myfirst 10s;
        }
        @keyframes myfirst {
              from {background:  #ffff00;}
              to {background: #ffb6c1;}
          }
    </style>
</head>
<body>
<script type="text/javascript">
    function displaytagform_bedehiSelection(formname, fields){
        var objfrm = document.forms[formname];
        if($(objfrm).serialize().indexOf('_chk') == -1){
            alertMessage("اعتباری انتخاب نشده است.");
        }else{
            for (j=fields.length-1;j>=0;j--){
                $(objfrm).prepend("<input type='hidden' id='embedI_"+fields[j].f+"' name='"+fields[j].f+"' value='"+fields[j].v+"'></input>");
            }
            objfrm.action = "/fin/pardakhtInternetiBedehi";
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
<div>
    <h4   >در طول مراحل پرداخت به هیچ عنوان از دکمه
     backاستفاده نکنید </h4>
</div>
    <div style="overflow: auto;">
        <form name="pishListFormOfRKM" action="?" method="POST">
            <s:actionmessage/>
            <s:actionerror/>
            <display:table   excludedParams="selectedTab pishnehadSearch* _chk"
                             decorator="org.displaytag.decorator.CheckboxTableDecorator"
                             export="true" id="credebitListPaginated" uid="row" name="credebitListPaginated"
                             sort="external" partialList="true" htmlId="credebitListPaginated"
                             size="${credebitListPaginated.fullListSize}"
                             pagesize="${credebitListPaginated.objectsPerPage}"
                             requestURI="?${pagingParams}" clearStatus="true" keepStatus="false"
                             style="width: 100%; margin: 0 auto;">
                <display:column property="checkbox" title="<input id='pishnehadsSelectAll' type='checkbox' title='انتخاب/حذف همه'/>" />
                <display:column title="ردیف" style="">${row_rowNum}</display:column>
                <display:column property="identifier" title="شماره بیمه نامه" style=""/>
                <display:column property="rcptName" title="نام بیمه گذار" style="" />
                <display:column property="namayandeName" title="نام نماینده" style="" />
                <display:column property="sarresidDate" title="تاریخ سررسید" style=""/>
                <display:column property="createdDate" title="تاریخ ایجاد" style=""/>
                <display:column property="amount" title="مبلغ کل" style=""/>
                <display:column property="paidReceivedAmountFormat" title="مبلغ دریافت شده" style=""/>
                <display:column property="remainingAmount" title="مبلغ مانده" style=""/>
                <display:column property="credebitTypeFarsi" title="نوع" style=""/>
                <display:column property="shomareGharardad" title="شماره قرارداد" style=""/>
                <display:column property="statusFarsi" title="وضعیت سند" style=""/>
                <display:column property="vosoulStateFarsi" title="وضعیت وصول" style=""/>
            </display:table>
        </form>
    </div>

<table class="inputList" cellspacing="1" cellpadding="3" >
    <tr>
        <td>
            <label>مبلغ قابل پرداخت</label>
            </td>
        <td>
            <input type="text" name="amount" id="amount" class="validate[required] digitSeparators" value="${amount}" readonly="true" >
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="button" onclick="javascript:displaytagform_bedehiSelection('pishListFormOfRKM',[{f:'par',v:['aa%22az']}])"  value="پرداخت اینترنتی"/>
            <input type="button" onclick="window.location='/fin/listBedehiNamayande.action'" value="بازگشت"/>
        </td>
    </tr>
</table>
</body>
</html>