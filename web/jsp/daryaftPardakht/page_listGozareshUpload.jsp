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
<head><title>لیست اعتبارات و بدهی ها</title></head>
<body>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<script>
    $(function(){
        $(".jtable tr").click(function(){
            $(this).children("td").toggleClass("ui-state-highlight");
        });
    });

</script>

    <div class="expandableTitleBar" id="expandableAsl">
        <p class="heading">
            <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                            جستجو
        </p>
        <div class="content" style="display:none;" id="searchAslContent1">
            <form action="/fin/listGozareshUpload" method="get">
                <table dir="rtl" class="inputList">
                    <tr>
                        <td>از تاریخ ایجاد در سیستم</td>
                        <td>
                            <input type="text" name="createdDateFrom" id="createdDateFrom" class="datePkr" readonly="true"/>
                        </td>
                        <td>تا تاریخ ایجاد در سیستم</td>
                        <td>
                            <input type="text" name="createdDateTo" id="createdDateTo" class="datePkr" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>از تاریخ واریز</td>
                        <td>
                            <input type="text" name="sarresidDateFrom" id="sarresidDateFrom" class="datePkr" readonly="true"/>
                        </td>
                        <td>تا تاریخ واریز</td>
                        <td>
                            <input type="text" name="sarresidDateTo" id="sarresidDateTo" class="datePkr" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>نام کاربر آپلود کننده</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="rcptName" id="rcptName" />
                        </td>
                        <td>شناسه پرداخت</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="shomareMoshtari" id="shomareMoshtari" />
                        </td>
                    </tr>
                    <tr>
                        <td>وضعیت وصول</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:select list="#{'':'تمام موارد','VOSOL_TEKRARI':'وصول تکراری','MABLAGH_MAZAD':'مبلغ مازاد','MABLAGH_KAMTAR':'مبلغ کمتر',
                            'MABLAGH_MOSAVI':'مبلغ مساوی','AGHSAT_OMR':'اقساط عمر','PISH_PARDAKHT_OMR':'پیش پرداخت عمر','TEKRARI':'تکراری'}" name="vosoulDesc" id="vosoulDesc" label="" key="vosoulDesc" theme="simple" />
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="3">
                            <script type="text/javascript">
                                function clearSeachFrom()
                                {
                                    $('#createdDateFrom').val('');
                                    $('#createdDateTo').val('');
                                    $('#sarresidDateFrom').val('');
                                    $('#sarresidDateTo').val('');
                                    $('#rcptName').val('');
                                    $('#shomareMoshtari').val('');
                                    $('#vosoulDesc').val('');
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
        <display:table export="true" id="bankInfoPaginatedList" uid="row" name="bankInfoPaginatedList"
                       sort="external" htmlId="bankInfoPaginatedList"
                       partialList="true"
                       size="${bankInfoPaginatedList.fullListSize}"
                       pagesize="${bankInfoPaginatedList.objectsPerPage}"
                       requestURI="" clearStatus="true" keepStatus="false"
                       excludedParams="" style="width: 100%; margin: 0 auto;">
            <display:column title="ردیف" style="">${row_rowNum+((bankInfoPaginatedList.pageNumber-1)* bankInfoPaginatedList.objectsPerPage)}</display:column>
            <display:column property="createdDate" title="تاریخ ایجاد در سیستم" style=""/>
            <display:column property="createdTime" title="زمان ایجاد در سیستم" style=""/>
            <display:column property="taarikh" title="تاریخ واریز" style=""/>
            <display:column property="time" title="زمان واریز" style=""/>
            <display:column property="bargozarandeh.user.fullName" title="نام آپلود کننده" style=""/>
            <display:column property="codeMoshtari" title="شناسه پرداخت" style=""/>
            <display:column property="vaziyateEtebarDesc" title="وضعیت وصول" style=""/>
        </display:table>
    </div>

<br>
    <sec:authorize ifNotGranted="ROLE_KARBAR_MALI">
        <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
    </sec:authorize>
    <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
        <input type="button" onclick="window.location='/loginUser.action'" value="بازگشت"/>
    </sec:authorize>


</body>
</html>