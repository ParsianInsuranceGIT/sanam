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
<head><title>اطلاعات مالی نماینده</title></head>
<body>

<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
    <div class="expandableTitleBar" id="expandableAsl">
        <p class="heading">
            <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                            جستجو
        </p>
        <div class="content" style="display:none;" id="searchAslContent1">
            <form action="/fin/listMaliNamayande" method="get">
                <table dir="rtl" class="inputList">
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
                    <%--<tr>--%>
                        <%--<td>از تاریخ ایجاد</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="createdDateFrom" id="createdDateFrom" class="datePkr" readonly="true"/>--%>
                        <%--</td>--%>
                        <%--<td>تا تاریخ ایجاد</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="createdDateTo" id="createdDateTo" class="datePkr" readonly="true"/>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <tr>
                        <td><label>نمايندگي</label></td>
                        <td>
                            <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('namayandeId','namayandeName', '');" style="float:left;" title="جستجو"></span>
                            <input type="hidden" name="namayandeId" id="namayandeId" />
                            <input type="text" name="namayandeName" id="namayandeName"  readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="3">
                            <script type="text/javascript">
                                function clearSeachFrom()
                                {
                                    $('#sarresidDateFrom').val('');
                                    $('#sarresidDateTo').val('');
                                    $('#createdDateFrom').val('');
                                    $('#createdDateTo').val('');
                                    $('#namayandeName').val('');
                                    $('#namayandeId').val('');
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
    <%@include file="bankInfosDialog.jsp"%>
    <div style="overflow: auto;">
        <display:table export="true" id="namayandeListPaginated" uid="row" name="namayandeListPaginated"
                       sort="external" htmlId="namayandeListPaginated"
                       partialList="true"
                       size="${namayandeListPaginated.fullListSize}"
                       pagesize="${namayandeListPaginated.objectsPerPage}"
                       requestURI="" clearStatus="true" keepStatus="false"
                       excludedParams="" style="width: 100%; margin: 0 auto;">
            <display:column title="ردیف" style="">${row_rowNum}</display:column>
            <display:column property="namayande.name" title="نام نماینده" style=""/>
            <display:column property="namayande.kodeNamayandeKargozar" title="کد نماینده" style=""/>
            <display:column property="elameBeMaliHaghBime" title="اعلام به مالی حق بیمه" style=""/>
            <display:column property="elameBeMaliBargashti" title="اعلام به مالی برگشتی" style=""/>
            <display:column property="khalesBedehi" title="خالص بدهی" style=""/>
            <display:column property="mablaghVosoulShode" title="مبلغ وصول شده" style=""/>
        </display:table>
    </div>

</body>
</html>