<%@ page import="com.bitarts.common.displaytag.PaginatedListImpl" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: a-khezri
  Date: 2/17/13
  Time: 11:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>سریال بیمه نامه</title></head>

<body>
    <%@include file="page_operationsSerials.jsp"%>
    <s:actionmessage/>
    <s:actionerror/>
    <%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
    <div class="expandableTitleBar" id="expandableAsl">
        <p class="heading">
            <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                            جستجو
        </p>
        <div class="content" style="display:none;" id="searchAslContent1">
            <form action="/listAllSerial" method="get">
                <table dir="rtl" class="inputList">
                    <tr>
                        <td>نوع بیمه نامه:</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:textfield name="bimenameType" id="bimenameType" theme="simple"/>
                        </td>
                        <td>وضعیت دسته:</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:select list="#{'':'تمام موارد','FAAL':'فعال', 'GHEYRE_FAAL':'غیرفعال', 'BASTE_SHODE':'بسته شده'}"
                               name="vaziateDaste" id="vaziatDaste" label="" key="vaziateDaste" theme="simple"/>
                        </td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td>از شماره سریال:</td>--%>
                        <%--<td>--%>
                            <%--<span class="noThing">&nbsp;</span>--%>
                            <%--<s:textfield name="serialFirst" id="serialFirst" theme="simple"/>--%>
                        <%--</td>--%>
                        <%--<td>تا شماره سریال:</td>--%>
                        <%--<td>--%>
                            <%--<span class="noThing">&nbsp;</span>--%>
                            <%--<s:textfield name="serialLast" id="serialLast" theme="simple"/>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <tr>
                        <td>نمایندگی:</td>
                        <td>
                            <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('namayandegiId','namayandegiName', '');" style="float:left;" title="جستجو"></span>
                            <s:hidden name="namayandegiId" id="namayandegiId"/>
                            <s:textfield name="namayandegiName" id="namayandegiName" theme="simple"/>
                        </td>
                        <td>
                            <script type="text/javascript">
                                function clearSeachFrom_b()
                                {
                                    $('#bimenameType').val('');
                                    $('#serialFirst').val('');
                                    $('#serialLast').val('');
                                    $('#namayandegiId').val('');
                                    $('#namayandegiName').val('');
                                    $('#vaziateDaste').val('');
                                }
                            </script>
                        </td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:submit value="جستجو" theme="simple"/>
                            <span class="noThing"></span>
                            <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom_b()"/>
                        </td>
                     </tr>
                </table>
            </form>
        </div>
    </div>
    <div class="expandableTitleBar" id="expandableAsll">
        <p class="heading">
            <span class="ui-icon ui-icon-plus" style="float:right;"></span>
عملیات
        </p>
        <div class="content" style="display:none;" id="operateAslContent1">
            <input type="button"  value="ایجاد دسته جدید"  onclick="createDasteSerial();"/>
            <input type="button"  value="ابطال گروهی سریال ها" onclick="ebtalSerials();"/>
            <input type="button"  value="تنظیم چاپ بیمه نامه" onclick="" class="ui-state-disabled"/>
            <input type="button"  value="گزارش سریال های استفاده نشده" onclick="javascript:displaytagform_transition('pishListFormOfRKM')"/>
            <%--<input type="button"  value="گزارش سریال های استفاده نشده" onclick="viewSerialsEstefadeNashode();"/>--%>
            <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
        </div>
    </div>
    <script type="text/javascript">
<%----%>
        $(function()
        {
            $('#selectAll').change(function(){
                $('input[name="_chk"]').each(function(){
                    $(this).attr('checked', $('#selectAll').attr('checked'));
                })
            });
        });
        function displaytagform_transition(formname)
        {
            var objfrm = document.forms[formname];
            if($(objfrm).serialize().indexOf('_chk') == -1) alertMessage("دسته سریالی انتخاب نشده است.");
            else objfrm.submit();
        }

    </script>
    <form name="pishListFormOfRKM" action="/serialsEstefadeNashode.action" method="POST">
    <display:table export="false" id="tbldasteSerialList" uid="dasteSerial" htmlId="tbldasteSerialList"
               name="serialsVMPgList" partialList="true" clearStatus="true" keepStatus="false"
               style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
               size="${serialsVMPgList.fullListSize}"    decorator="org.displaytag.decorator.CheckboxTableDecorator"
               pagesize="${serialsVMPgList.objectsPerPage}">
        <display:column property="checkbox"  title="<input id='selectAll' type='checkbox' title='انتخاب/حذف همه'/>"/>
        <display:column title="ردیف">${dasteSerial_rowNum+((serialsVmPgList.pageNumber-1)*serialsVmPgList.objectsPerPage)}</display:column>
        <display:column property="kodeDaste" title="کد دسته"></display:column>
        <display:column property="firstShomareSerial" title="از سریال"></display:column>
        <display:column property="lastShomareSerial" title="تا سریال"></display:column>
        <display:column property="tarikheSabt" title="تاریخ ثبت"></display:column>
        <display:column property="bimenameType" title="نوع بیمه نامه"></display:column>
        <display:column property="vahedSodurName" title="واحد صدور"></display:column>
        <display:column property="namayandeName" title="نمایندگی"></display:column>
        <display:column property="bazaryabName" title="بازاریاب"></display:column>
        <display:column property="vaziateDasteFa" title="وضعیت"></display:column>
        <display:column property="countSerials" title="تعداد کل"></display:column>
        <display:column property="countSerialsEbtali" title="تعداد ابطالی"></display:column>
        <display:column property="mizaneJabejayi" title="میزان جابه جایی"></display:column>
        <input type="hidden" id="dasteSerialId"  value="${dasteSerial.id}"/>
        <input type="hidden" id="dasteSerialKodeDaste"  value="${dasteSerial.kodeDaste}"/>
        <display:column>
            <c:if test="${dasteSerial.countSerialsEstefadeNashode==0}">
            <input  type="button"  onclick="editDasteSerial('${dasteSerial.id}','${dasteSerial.kodeDaste}',1);" value="تغییر وضعیت"/>
            </c:if>
            <c:if test="${dasteSerial.countSerialsEstefadeNashode>0}">
            <input  type="button"  onclick="editDasteSerial('${dasteSerial.id}','${dasteSerial.kodeDaste}',2);" value="تغییر وضعیت"/>
            </c:if>
        </display:column>
    </display:table>
    <%--<display:table export="false" id="tbldasteSerialList" uid="dasteSerial" htmlId="tbldasteSerialList"--%>
               <%--name="sessionScope.listAllSerial.list" partialList="true" clearStatus="true" keepStatus="false"--%>
               <%--style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""--%>
               <%--size="${sessionScope.listAllSerial.fullListSize}"    decorator="org.displaytag.decorator.CheckboxTableDecorator"--%>
               <%--pagesize="${sessionScope.listAllSerial.objectsPerPage}">--%>
        <%--<display:column property="checkbox"  title="<input id='selectAll' type='checkbox' title='انتخاب/حذف همه'/>"/>--%>
        <%--<display:column property="kodeDaste" title="کد دسته"></display:column>--%>
        <%--<display:column property="serialFirst.shomareSerial" title="از سریال"></display:column>--%>
        <%--<display:column property="serialLast.shomareSerial" title="تا سریال"></display:column>--%>
        <%--<display:column property="tarikheSabt" title="تاریخ ثبت"></display:column>--%>
        <%--<display:column property="bimenameType" title="نوع بیمه نامه"></display:column>--%>
        <%--<display:column property="vahedeSodur.name" title="واحد صدور"></display:column>--%>
        <%--<display:column property="namayandegi.name" title="نمایندگی"></display:column>--%>
        <%--<display:column title="بازاریاب">${dasteSerial.bazaryab.firstName}&nbsp;${dasteSerial.bazaryab.lastName}</display:column>--%>
        <%--<display:column property="vaziateDasteFarsi" title="وضعیت"></display:column>--%>
        <%--<display:column property="countSerials" title="تعداد کل"></display:column>--%>
        <%--<display:column property="countSerialsEbtali" title="تعداد ابطالی"></display:column>--%>
        <%--<display:column property="mizaneJabejayi" title="میزان جابه جایی"></display:column>--%>
        <%--<input type="hidden" id="dasteSerialId"  value="${dasteSerial.id}"/>--%>
        <%--<input type="hidden" id="dasteSerialKodeDaste"  value="${dasteSerial.kodeDaste}"/>--%>
        <%--<display:column>--%>
            <%--<c:if test="${dasteSerial.serialsEstefadeNashode==0}">--%>
            <%--<input  type="button"  onclick="editDasteSerial('${dasteSerial.id}','${dasteSerial.kodeDaste}',1);" value="تغییر وضعیت"/>--%>
            <%--</c:if>--%>
            <%--<c:if test="${dasteSerial.serialsEstefadeNashode>0}">--%>
            <%--<input  type="button"  onclick="editDasteSerial('${dasteSerial.id}','${dasteSerial.kodeDaste}',2);" value="تغییر وضعیت"/>--%>
            <%--</c:if>--%>
        <%--</display:column>--%>
    <%--</display:table>--%>
    </form>
</body>
</html>