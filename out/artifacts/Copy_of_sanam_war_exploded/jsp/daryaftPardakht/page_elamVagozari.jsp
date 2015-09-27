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
<head><title>اعلام واگذاری</title></head>
<s:actionmessage/>
<s:actionerror/>
<body>
<script type="text/javascript">
    $(function(){
        $('input[name="_chk"]').each(function () {
            $(this).attr('checked', false);
        })
    });
    function displaytagform_elamVagozari(formname, fields){
        var objfrm = document.forms[formname];
        if($(objfrm).serialize().indexOf('_chk') == -1){
            alertMessage("اعتباری انتخاب نشده است.");
        }else{
            var b =  '${namayandeId}';
            if(b==''){
               alertMessage("کاربری شما فاقد نمایندگی می باشد");
        }else{
                for (j=fields.length-1;j>=0;j--){
                $(objfrm).prepend("<input type='hidden' id='embedI_"+fields[j].f+"' name='"+fields[j].f+"' value='"+fields[j].v+"'></input>");
            }
            objfrm.action = "/fin/operationCheckList";
            objfrm.submit();
            }
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
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<div class="expandableTitleBar" id="expandableAsl">
    <p class="heading">
        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        جستجو
    </p>
    <div class="content" style="display:none;" id="searchAslContent1">
        <form action="/fin/elamVagozari" method="get">
            <table dir="rtl" class="inputList">
                <tr>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <s:textfield name="shomareHesab" id="shomareHesab" theme="simple"/>
                        <label>شماره حساب</label>
                    </td>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <input type="text" name="shomareCheck" id="shomareCheck" class="text-input"/>
                        <label>شماره چک</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <input type="text" name="sarresidDate" id="sarresidDate" class="datePkr" readonly="true"/>
                        <label>تاریخ سررسید</label>
                    </td>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <input type="text" name="checkSerri" id="checkSerri" class="text-input"/>
                        <label>سری چک</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <input type="text" name="bankName" id="bankName" class="text-input"/>
                        <label>نام بانک</label>
                    </td>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <input type="text" name="rcptName" id="rcptName" class="text-input"/>
                        <label>طرف حساب</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <input type="text" name="branchCode" id="branchCode" class="text-input"/>
                        <label>کد شعبه</label>
                    </td>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <input type="text" name="accountOwnerName" id="accountOwnerName" class="text-input"/>
                        <label>نام دارنده چک</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="noThing">&nbsp;</span>
                        <input type="text" name="amount" id="amount" class="digitSeparators"/>
                        <label>مبلغ چک</label>
                    </td>
                    <td>

                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                    <script type="text/javascript">
                        function clearSeachFrom_b() {
                            $('#shomareHesab').val('');
                            $('#shomareCheck').val('');
                            $('#sarresidDate').val('');
                            $('#checkSerri').val('');
                            $('#bankName').val('');
                            $('#rcptName').val('');
                            $('#branchCode').val('');
                            $('#accountOwnerName').val('');
                            $('#amount').val('');
                        }
                    </script>
                    <span class="noThing">&nbsp;</span>
                    <input type="submit" value="جستجو" theme="simple"/>
                    <span class="noThing"></span>
                    <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom_b()"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    </div>


    <div style="overflow: auto;">
        <form name="pishListFormOfRKM" action="?" method="POST">
            <input type="hidden" name="flagPage" value="0"/>
            <display:table   excludedParams="selectedTab pishnehadSearch* _chk"
                             decorator="org.displaytag.decorator.CheckboxTableDecorator"
                             export="true" id="tableforaslid" uid="rpResult" name="credebitListPaginated"
                             sort="external" partialList="true" htmlId="tblforaslid"
                             size="${credebitListPaginated.fullListSize}"
                             pagesize="${credebitListPaginated.objectsPerPage}"
                             requestURI="?${pagingParams}" clearStatus="true" keepStatus="false"
                             style="width: 100%; margin: 0 auto;">
                <display:column property="checkbox" title="<input id='pishnehadsSelectAll' type='checkbox' title='انتخاب/حذف همه'/>"
                                style="background:${row.roleColor};"/>
                <c:choose>
                    <c:when test="${sessionScope.daftar_id==1}">
                        <c:set var="css" value=""/>
                    </c:when>

                    <c:otherwise>
                        <c:set var="css" value="background:#ffffcc;"/>
                    </c:otherwise>
                </c:choose>
                <display:column title="ردیف" style="${css}">${rpResult_rowNum+((credebitListPaginated.pageNumber-1)*credebitListPaginated.objectsPerPage)}</display:column>
                <display:column property="shenaseCredebit" title="شماره اعتبار" style="${css}"/>
                <display:column property="shomareMoshtari" title="شناسه پرداخت" style="${css}"/>
                <display:column property="daryafteCheck.statusFarsi" title="وضعیت" style="${css}"/>
                <display:column property="daryafteCheck.hesabBanki" title="  شماره حساب " style="${css}"/>
                <display:column property="daryafteCheck.serial" title="شماره چک" style="${css}"/>
                <display:column property="sarresidDate" title="تاریخ سررسید" style="${css}"/>
                <display:column property="daryafteCheck.seri" title="سری" style="${css}"/>
                <display:column property="bankName" title="نام بانک" style="${css}"/>
                <display:column property="rcptName" title="طرف حساب" style="${css}"/>
                <display:column property="daryafteCheck.branchName" title="نام شعبه" style="${css}"/>
                <display:column property="daryafteCheck.branchCode" title="کد شعبه" style="${css}"/>
                <display:column property="daryafteCheck.accountOwnerName" title="نام دارنده چک" style="${css}"/>
                <display:column property="amount" title="مبلغ" style="${css}"/>
                <display:column property="description" title="توضیحات" style="${css}"/>
            </display:table>
            <c:if test="${flagPage == '1'}"> <!--check vagozar shode-->
                <script type="text/javascript">
                    alertMessage("تعدادی از اعتبارات انتخاب شده، واگذار شده می باشند");
                </script>
            </c:if>
            <c:if test="${flagPage == '2'}"> <!--check vagozar shode-->
                <script type="text/javascript">
                    alertMessage("تعدادی از اعتبارات انتخاب شده، شرایط واگذاری را ندارند.");
                </script>
            </c:if>
        </form>
    </div>

<br>
<table class="inputList" cellspacing="1" cellpadding="5" style="width:50%; border:1px solid black;" >
    <tr>
        <td>
            <label>با انتخاب اعتبارها در جدول بالا و همچنین شماره حساب در منوی آبشاری، می توانید عملیات اعلام واگذاری را انجام دهید</label>
        </td>
    </tr>
    <tr>
        <td>
            <select name="credebit.bankName" id="bankMaghsadAutomobil">
                <option value="<%=Constant.CREDEBIT_BANK_MELAT_VANAK%>"><%=Constant.CREDEBIT_BANK_MELAT_VANAK%></option>
            </select>

        </td>
    </tr>
    <tr>
        <td>
            <input type="button" onclick="javascript:displaytagform_elamVagozari('pishListFormOfRKM',[{f:'par',v:['aa%22az']}])" value="اعلام واگذاری"/>
        </td>
    </tr>
</table>

    <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
        <input type="button" onclick="window.location='/loginUser.action'" value="بازگشت"/>
    </sec:authorize>


</body>
</html>