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
            <form action="/fin/listGozareshVosouli" method="get">
                <table dir="rtl" class="inputList">
                    <tr>
                        <td>از تاریخ ایجاد</td>
                        <td>
                            <input type="text" name="createdDateFrom" id="createdDateFrom" class="datePkr" readonly="true"/>
                        </td>
                        <td>تا تاریخ ایجاد</td>
                        <td>
                            <input type="text" name="createdDateTo" id="createdDateTo" class="datePkr" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>شماره بیمه نامه</td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <input type="text" name="identifier" id="identifier"/>
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
        <display:table export="true" id="credebitListTbl" uid="row" name="credebitListPaginated"
                       sort="external" htmlId="credebitListTbl"
                       partialList="true"
                       size="${credebitListPaginated.fullListSize}"
                       pagesize="${credebitListPaginated.objectsPerPage}"
                       requestURI="" clearStatus="true" keepStatus="false"
                       excludedParams="" style="width: 100%; margin: 0 auto;">
            <display:column title="ردیف" style="">${row_rowNum+((credebitListPaginated.pageNumber-1)*credebitListPaginated.objectsPerPage)}</display:column>
            <display:column property="identifier" title="شماره بیمه نامه" style=""/>
            <display:column property="shenaseCredebit" title="شناسه بدهی" style=""/>
            <display:column property="amount" title="مبلغ کل" style=""/>
            <display:column property="remainingAmount" title="مبلغ مانده" style=""/>
            <display:column property="credebitTypeFarsi" title="نوع" style=""/>
            <display:column property="uniqueCode" title="کد " style=""/>
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